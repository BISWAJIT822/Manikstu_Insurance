package com.manikshu.goatinsurance

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun sendOtp(phone: String, role: String) = apiService.sendOtp(OtpRequest(phone, role))
    suspend fun verifyOtp(phone: String, otp: String, role: String) = apiService.verifyOtp(VerifyOtpRequest(phone, otp, role))
    suspend fun getStats(role: String) = apiService.getStats(role)
    suspend fun getGoats() = apiService.getGoats()
}
