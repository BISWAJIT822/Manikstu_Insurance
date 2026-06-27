package com.manikshu.goatinsurance

import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {
    @POST("/auth/send-otp")
    suspend fun sendOtp(@Body request: OtpRequest): GenericResponse

    @POST("/auth/verify-otp")
    suspend fun verifyOtp(@Body request: VerifyOtpRequest): AuthResponse

    @GET("/dashboard/{role}/stats")
    suspend fun getStats(@Path("role") role: String): DashboardStats

    @POST("/enrollment/start")
    suspend fun startEnrollment(): EnrollmentDraftResponse

    @GET("/goats")
    suspend fun getGoats(@Query("search") search: String? = null): List<Goat>

    @POST("/claims/death")
    suspend fun reportDeath(@Body claim: ClaimRequest): GenericResponse
}

data class OtpRequest(val phone: String, val role: String)
data class VerifyOtpRequest(val phone: String, val otp: String, val role: String)
data class AuthResponse(val accessToken: String, val refreshToken: String, val user: User)
data class GenericResponse(val success: Boolean, val message: String)
data class EnrollmentDraftResponse(val draftId: String)
data class ClaimRequest(
    val goatId: String,
    val dateOfDeath: String,
    val cause: String,
    val latitude: Double,
    val longitude: Double,
    val photos: List<String>
)
