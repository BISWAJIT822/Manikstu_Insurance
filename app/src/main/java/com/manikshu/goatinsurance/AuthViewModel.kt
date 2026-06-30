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
    private val sessionManager: SessionManager,
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState = _authState.asStateFlow()

    /** Dev mock OTP echoed back by the backend (shown to ease testing). */
    private val _devOtp = MutableStateFlow<String?>(null)
    val devOtp = _devOtp.asStateFlow()

    fun resetState() {
        _authState.value = AuthState.Idle
        _devOtp.value = null
    }

    // ---------------- login ----------------
    fun sendLoginOtp(phone: String, role: UserRole) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val res = repository.requestOtp(phone, role.code)
                _devOtp.value = res.response
                _authState.value = AuthState.OtpSent
            } catch (e: Exception) {
                _authState.value = AuthState.Error(parseError(e, "Failed to send OTP"))
            }
        }
    }

    fun verifyLogin(phone: String, otp: String, role: UserRole) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val res = repository.verifyLogin(phone, role.code, otp)
                if (res.status == "success" && res.accessToken != null) {
                    sessionManager.saveAuthToken(res.accessToken)
                    sessionManager.saveUserInfo(name = "", mobile = phone)
                    _authState.value = AuthState.Authenticated(role)
                } else {
                    _authState.value = AuthState.Error(res.reason ?: "Login failed")
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error(parseError(e, "Invalid OTP"))
            }
        }
    }

    // ---------------- signup ----------------
    fun sendSignupOtp(name: String, phone: String, role: UserRole) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val res = repository.requestOtpSignup(name, phone, role.code)
                if (res.response == "duplicate") {
                    _authState.value = AuthState.Error("This mobile number is already registered")
                } else {
                    _devOtp.value = res.response
                    _authState.value = AuthState.OtpSent
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error(parseError(e, "Failed to send OTP"))
            }
        }
    }

    fun verifySignup(name: String, phone: String, otp: String, role: UserRole) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val res = repository.verifySignup(name, phone, role.code, otp)
                if (res.status == "success") {
                    _authState.value = AuthState.SignupComplete(role)
                } else {
                    _authState.value = AuthState.Error(res.reason ?: "Signup failed")
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error(parseError(e, "Invalid OTP"))
            }
        }
    }

    private fun parseError(e: Exception, fallback: String): String = when (e) {
        is retrofit2.HttpException -> when (e.code()) {
            429 -> "Too many OTP requests. Please wait a minute."
            403 -> "Account not active or pending admin approval."
            else -> fallback
        }
        is java.net.ConnectException, is java.net.UnknownHostException ->
            "Cannot reach server. Is the backend running?"
        else -> e.message ?: fallback
    }
}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object OtpSent : AuthState()
    data class Authenticated(val role: UserRole) : AuthState()
    data class SignupComplete(val role: UserRole) : AuthState()
    data class Error(val message: String) : AuthState()
}
