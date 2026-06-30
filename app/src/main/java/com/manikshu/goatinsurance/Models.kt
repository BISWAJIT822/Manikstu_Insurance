package com.manikshu.goatinsurance

import kotlinx.serialization.Serializable

enum class UserRole {
    SURAKSHA_DIDI, FARMER, COORDINATOR
}

enum class AppLanguage(val code: String, val label: String) {
    ENGLISH("EN", "English"),
    HINDI("HI", "हिन्दी"),
    ODIA("OR", "ଓଡ଼ିଆ");

    fun getT(en: String, hi: String, or: String): String {
        return when (this) {
            HINDI -> hi
            ODIA -> or
            ENGLISH -> en
        }
    }
}

@Serializable
data class User(
    val id: String,
    val name: String,
    val phone: String,
    val role: UserRole,
    val village: String? = null
)

@Serializable
data class Farmer(
    val id: String,
    val name: String,
    val phone: String,
    val aadhaar: String,
    val village: String,
    val block: String,
    val district: String,
    val bankAccount: String,
    val ifsc: String,
    val photoUrl: String,
    val latitude: Double,
    val longitude: Double
)

@Serializable
data class Goat(
    val id: String,
    val earTag: String,
    val breed: String,
    val gender: String,
    val ageMonths: Int,
    val weightKg: Double,
    val colour: String,
    val photoUrls: List<String>,
    val farmerId: String,
    val status: String // Enrolled, Tagged, Vaccinated, Claimed
)

@Serializable
data class Vaccination(
    val id: String,
    val goatId: String,
    val type: String, // PPR, ET_TT, FMD, GOAT_POX
    val date: String,
    val nextDueDate: String,
    val batchNumber: String,
    val status: String
)

@Serializable
data class Policy(
    val policyNumber: String,
    val goatId: String,
    val issueDate: String,
    val endDate: String,
    val sumInsured: Int,
    val status: String
)

@Serializable
data class Premium(
    val receiptNumber: String,
    val goatId: String,
    val amount: Int = 350,
    val paymentMode: String,
    val date: String
)

@Serializable
data class Claim(
    val claimNumber: String,
    val goatId: String,
    val dateOfDeath: String,
    val cause: String,
    val vaccinationCount: Int,
    val tierAmount: Int,
    val status: String, // PendingVerification, UnderReview, Approved, Rejected, Hold, Paid
    val photoUrls: List<String>,
    val latitude: Double,
    val longitude: Double
)

data class DashboardStats(
    val enrolledCount: Int,
    val earnings: Int,
    val pendingClaims: Int,
    val upcomingVaccinations: Int
)

@Serializable
data class OtpResponse(val status: String, val message: String)

@Serializable
data class LoginResponse(val status: String, val message: String, val token: String? = null)

@Serializable
data class VerifySignupRequest(
    val fullName: String,
    val mobile: String,
    val role: String,
    val otp: String
)

@Serializable
data class StatusResponse(val status: String, val message: String)

@Serializable
data class ProfileResponse(val status: String, val profile: UserDto)

@Serializable
data class AdminOverview(val totalUsers: Int, val pendingApprovals: Int)

@Serializable
data class UserListResponse(val users: List<UserDto>)

@Serializable
data class UserDto(val id: Int, val fullName: String, val role: String)

@Serializable
data class SdDashboard(val totalEnrollments: Int)

@Serializable
data class EnrollGoatRequest(val farmerName: String, val mobileNumber: String)

@Serializable
data class EnrollGoatResponse(val status: String, val goatId: Int)

@Serializable
data class GoatListResponse(val goats: List<Goat>)

@Serializable
data class VaccinationListResponse(val vaccinations: List<Vaccination>)

@Serializable
data class RecordVaccinationRequest(val goatId: Int, val vaccinationType: String)

@Serializable
data class ReportMortalityRequest(val goatId: Int, val cause: String)

@Serializable
data class MortalityIdResponse(val mortalityId: Int)

@Serializable
data class UploadMortalityPhotosRequest(val mortalityId: Int, val photos: List<String>)

@Serializable
data class MortalityProgress(val status: String)

@Serializable
data class AiAssistantRequest(val topic: String, val message: String)

@Serializable
data class AiAssistantResponse(val reply: String)

@Serializable
data class MyPoliciesResponse(val policies: List<Policy>)

@Serializable
data class ReportDeathRequest(val policyNumber: String, val reason: String)

@Serializable
data class VaccinationScheduleItem(val goatId: Int, val vaccineName: String, val date: String)

@Serializable
data class CoDashboard(val activeClaims: Int)

@Serializable
data class ActivityItem(val id: Int, val title: String)

@Serializable
data class ClusterDto(val id: Int, val name: String)

@Serializable
data class ClaimListResponse(val claims: List<Claim>)

@Serializable
data class ReviewClaimRequest(val claimNumber: String, val status: String)

@Serializable
data class ReportsResponse(val data: String)

@Serializable
data class TeamListResponse(val members: List<UserDto>)
