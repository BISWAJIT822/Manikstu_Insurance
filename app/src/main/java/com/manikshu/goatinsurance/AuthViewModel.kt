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
    private val repository: InsuranceRepository
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState = _authState.asStateFlow()

    /** Dev-mode OTP echoed back by the backend (shown as a toast to ease testing). */
    private val _devOtp = MutableStateFlow<String?>(null)
    val devOtp = _devOtp.asStateFlow()

    fun resetState() {
        _authState.value = AuthState.Idle
        _devOtp.value = null
    }

    fun sendOtp(phone: String, role: UserRole) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val res = repository.requestOtp(phone, role.name)
                _devOtp.value = res.message  // backend returns the OTP in `message` (dev mode)
                _authState.value = AuthState.OtpSent
            } catch (e: Exception) {
                _authState.value = AuthState.Error(mapError(e, "Failed to send OTP"))
            }
        }
    }

    fun verifyOtp(phone: String, otp: String, role: UserRole) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val res = repository.verifyLogin(phone, role.name, otp)
                if (res.status == "success" && res.token != null) {
                    AuthTokenHolder.token = res.token
                    _authState.value = AuthState.Authenticated(role)
                } else {
                    _authState.value = AuthState.Error(res.message.ifBlank { "Login failed" })
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error(mapError(e, "Invalid OTP"))
            }
        }
    }

    private fun mapError(e: Exception, fallback: String): String = when (e) {
        is retrofit2.HttpException -> when (e.code()) {
            429 -> "Too many OTP requests. Wait a minute."
            403 -> "Account inactive or pending admin approval."
            else -> fallback
        }
        is java.net.ConnectException, is java.net.UnknownHostException, is java.net.SocketTimeoutException ->
            "Cannot reach server. Is the backend running?"
        else -> e.message ?: fallback
    }
}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object OtpSent : AuthState()
    data class Authenticated(val role: UserRole) : AuthState()
    data class Error(val message: String) : AuthState()
}
