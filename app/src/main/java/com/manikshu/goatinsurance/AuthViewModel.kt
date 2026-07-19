package com.manikshu.goatinsurance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: Repository,
    private val session: SessionManager,
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState = _authState.asStateFlow()

    /** Password-reset request state, driven by the Forgot Password dialog. */
    private val _forgotState = MutableStateFlow<SubmitState>(SubmitState.Idle)
    val forgotState = _forgotState.asStateFlow()

    fun reset() { _authState.value = AuthState.Idle }
    fun resetForgot() { _forgotState.value = SubmitState.Idle }

    /**
     * Ask an admin to reset this number's password.
     *
     * Not self-service: with SMS disabled there is no way to prove the requester owns
     * the number, so the backend only records the request and notifies admins, who
     * verify the person and set the new password. The reply is deliberately the same
     * for registered and unregistered numbers.
     */
    fun requestPasswordReset(phone: String) {
        viewModelScope.launch {
            _forgotState.value = SubmitState.Submitting
            repository.safeCall { forgotPassword(ForgotPasswordRequest(phone)) }
                .onSuccess {
                    _forgotState.value = SubmitState.Success(
                        it.reason ?: "If this number is registered, an admin will contact you."
                    )
                }
                .onFailure {
                    _forgotState.value = SubmitState.Error(it.message ?: "Could not send the request")
                }
        }
    }

    // ------------------------------------------------------------------ login
    fun passwordLogin(phone: String, password: String, role: UserRole) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            repository.safeCall { loginPassword(LoginPasswordRequest(phone, password, role.apiCode)) }
                .onSuccess { resp ->
                    if (resp.status == "success" && !resp.accessToken.isNullOrBlank()) {
                        session.saveAuthToken(resp.accessToken)
                        val profile = repository.safeCall { profile() }.getOrNull()
                        session.saveSession(
                            role = role,
                            name = profile?.fullName,
                            village = profile?.village,
                            mobile = phone,
                            token = resp.accessToken,
                        )
                        _authState.value = AuthState.Authenticated(role)
                    } else {
                        _authState.value = AuthState.Error(resp.reason ?: "Invalid mobile number or password")
                    }
                }
                .onFailure { _authState.value = AuthState.Error(it.message ?: "Login failed") }
        }
    }

    // ----------------------------------------------------------------- signup
    /**
     * Creates the (pending) account. Called at the end of profile setup so the
     * village / aadhaar collected there are stored on the user. The account stays
     * unapproved until an admin approves it, so we route to a pending screen.
     */
    fun completeSignup(
        fullName: String,
        phone: String,
        role: UserRole,
        village: String?,
        aadhaarId: String?,
        password: String? = null,
    ) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val body = VerifySignupRequest(
                fullName = fullName, mobileNumber = phone, role = role.apiCode,
                village = village?.takeIf { it.isNotBlank() },
                aadhaarId = aadhaarId?.takeIf { it.isNotBlank() },
                password = password?.takeIf { it.isNotBlank() },
            )
            repository.safeCall { verifySignup(body) }
                .onSuccess { resp ->
                    when {
                        resp.status == "success" && !resp.accessToken.isNullOrBlank() -> {
                            // Auto-approved: log straight in.
                            session.saveAuthToken(resp.accessToken)
                            session.saveSession(role = role, name = fullName, village = village, mobile = phone, token = resp.accessToken)
                            _authState.value = AuthState.Authenticated(role)
                        }
                        resp.status == "success" -> _authState.value = AuthState.SignupPendingApproval
                        else -> _authState.value = AuthState.Error(resp.reason ?: "Sign up failed")
                    }
                }
                .onFailure { _authState.value = AuthState.Error(it.message ?: "Sign up failed") }
        }
    }
}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Authenticated(val role: UserRole) : AuthState()
    object SignupPendingApproval : AuthState()
    data class Error(val message: String) : AuthState()
}
