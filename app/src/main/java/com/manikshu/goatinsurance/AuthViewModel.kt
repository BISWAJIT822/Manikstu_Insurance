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
    private val repository: Repository
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState = _authState.asStateFlow()

    fun sendOtp(phone: String, role: UserRole) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                repository.requestOtp(phone, role.name)
                _authState.value = AuthState.OtpSent
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Failed to send OTP")
            }
        }
    }

    fun verifyOtp(phone: String, otp: String, role: UserRole) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val response = repository.verifyLogin(phone, role.name, otp)
                if (response.token != null) {
                    AuthTokenHolder.token = response.token
                }
                _authState.value = AuthState.Authenticated(role)
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Invalid OTP")
            }
        }
    }
}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object OtpSent : AuthState()
    data class Authenticated(val role: UserRole) : AuthState()
    data class Error(val message: String) : AuthState()
}
