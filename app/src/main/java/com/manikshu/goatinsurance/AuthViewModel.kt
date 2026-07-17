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

    /**
     * The app is password-only.
     *
     * This used to default to "otp" and then ask /auth/config, which meant the OTP
     * form rendered first and swapped to the password form once the server answered
     * - a wrong-form flash that lasts as long as the request takes (up to ~53s on a
     * cold start). Since OTP needs SMS delivery, which is disabled in production,
     * there was never anything to switch to. Rendering password immediately is both
     * correct and instant, with no network round-trip to decide.
     */
    private val _loginMethod = MutableStateFlow("password")
    val loginMethod = _loginMethod.asStateFlow()

    fun reset() { _authState.value = AuthState.Idle }

    // ------------------------------------------------------------------ login
    fun sendOtp(phone: String, role: UserRole) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            repository.safeCall { requestOtp(phone, role.apiCode) }
                .onSuccess { _authState.value = AuthState.OtpSent }
                .onFailure { _authState.value = AuthState.Error(it.message ?: "Failed to send OTP") }
        }
    }

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

    fun verifyOtp(phone: String, otp: String, role: UserRole) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            repository.safeCall { verifyLogin(phone, role.apiCode, otp) }
                .onSuccess { resp ->
                    if (resp.status == "success" && !resp.accessToken.isNullOrBlank()) {
                        session.saveAuthToken(resp.accessToken)
                        // Fetch the canonical profile so the dashboards show the real name/village.
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
                        _authState.value = AuthState.Error(resp.reason ?: "Invalid or expired OTP")
                    }
                }
                .onFailure { _authState.value = AuthState.Error(it.message ?: "Login failed") }
        }
    }

    // ----------------------------------------------------------------- signup
    fun sendSignupOtp(fullName: String, phone: String, role: UserRole) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            repository.safeCall { requestOtpSignup(fullName, phone, role.apiCode) }
                .onSuccess { resp ->
                    if (resp.response == "duplicate") {
                        _authState.value = AuthState.Error("This mobile number is already registered. Please log in.")
                    } else {
                        _authState.value = AuthState.OtpSent
                    }
                }
                .onFailure { _authState.value = AuthState.Error(it.message ?: "Failed to send OTP") }
        }
    }

    /**
     * Creates the (pending) account. Called at the end of profile setup so the
     * village / aadhaar collected there are stored on the user. The account stays
     * unapproved until an admin approves it, so we route to a pending screen.
     */
    fun completeSignup(
        fullName: String,
        phone: String,
        role: UserRole,
        otp: String,
        village: String?,
        aadhaarId: String?,
        password: String? = null,
    ) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val body = VerifySignupRequest(
                fullName = fullName, mobileNumber = phone, role = role.apiCode, otp = otp,
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
    object OtpSent : AuthState()
    data class Authenticated(val role: UserRole) : AuthState()
    object SignupPendingApproval : AuthState()
    data class Error(val message: String) : AuthState()
}
