package com.manikshu.goatinsurance

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// ============================================================
// Roles & language (used across the UI)
// ============================================================
enum class UserRole(val code: String) {
    SURAKSHA_DIDI("sd"),
    FARMER("fr"),
    COORDINATOR("co");

    companion object {
        fun fromCode(code: String): UserRole = when (code.lowercase()) {
            "sd" -> SURAKSHA_DIDI
            "fr" -> FARMER
            "co" -> COORDINATOR
            else -> SURAKSHA_DIDI
        }
    }
}

enum class AppLanguage(val code: String, val label: String) {
    ENGLISH("EN", "English"),
    HINDI("HI", "हिन्दी"),
    ODIA("OR", "ଓଡ଼ିଆ");

    fun getT(en: String, hi: String, or: String): String = when (this) {
        HINDI -> hi
        ODIA -> or
        ENGLISH -> en
    }
}

// ============================================================
// Auth DTOs (GENERAL)
// ============================================================
@Serializable
data class OtpResponse(val response: String)               // 6-digit otp or "duplicate"

@Serializable
data class StatusResponse(val status: String, val reason: String? = null)

@Serializable
data class LoginResponse(
    val status: String,
    @SerialName("access_token") val accessToken: String? = null,
    @SerialName("token_type") val tokenType: String? = "bearer",
    val reason: String? = null,
)

@Serializable
data class VerifySignupRequest(
    @SerialName("full_name") val fullName: String,
    @SerialName("mobile_number") val mobileNumber: String,
    val role: String,
    val otp: String,
)

@Serializable
data class ProfileResponse(
    val photo: String? = null,
    @SerialName("mobile_number") val mobileNumber: String,
    @SerialName("full_name") val fullName: String,
    val role: String,
    val village: String? = null,
)

// ============================================================
// Admin DTOs
// ============================================================
@Serializable
data class UserDto(
    val id: Int,
    @SerialName("full_name") val fullName: String,
    @SerialName("mobile_number") val mobileNumber: String,
    val role: String,
    val village: String? = null,
    val photo: String? = null,
    @SerialName("is_approved") val isApproved: Boolean,
    @SerialName("is_active") val isActive: Boolean,
    @SerialName("created_at") val createdAt: String? = null,
)

@Serializable
data class UserListResponse(
    val total: Int,
    val page: Int,
    @SerialName("page_size") val pageSize: Int,
    val users: List<UserDto>,
)

@Serializable
data class AdminOverview(
    @SerialName("total_users") val totalUsers: Int,
    @SerialName("pending_approvals") val pendingApprovals: Int,
    @SerialName("active_users") val activeUsers: Int,
    @SerialName("inactive_users") val inactiveUsers: Int,
    @SerialName("total_goats") val totalGoats: Int,
    @SerialName("total_policies") val totalPolicies: Int,
    @SerialName("total_claims") val totalClaims: Int,
)

// ============================================================
// Suraksha Didi DTOs
// ============================================================
@Serializable
data class SdDashboard(
    @SerialName("total_enrolled") val totalEnrolled: Int,
    @SerialName("pending_queries") val pendingQueries: Int,
    @SerialName("policies_issued") val policiesIssued: Int,
    @SerialName("premium_collected") val premiumCollected: Double,
)

@Serializable
data class GoatPhotoDto(
    @SerialName("photo_type") val photoType: String,   // left|right|face|ear_tag
    val url: String,
)

@Serializable
data class VaccineDto(
    @SerialName("vaccine_type") val vaccineType: String, // ppr|et_tt|fmd|goat_pox
    val status: String = "pending",
    @SerialName("batch_number") val batchNumber: String? = null,
    @SerialName("vaccination_date") val vaccinationDate: String? = null,
    @SerialName("next_vaccination_date") val nextVaccinationDate: String? = null,
)

@Serializable
data class EnrollGoatRequest(
    @SerialName("farmer_name") val farmerName: String,
    @SerialName("farmer_mobile") val farmerMobile: String,
    val village: String? = null,
    @SerialName("gps_location") val gpsLocation: String? = null,
    @SerialName("aadhaar_id") val aadhaarId: String? = null,
    @SerialName("ear_tag_number") val earTagNumber: String,
    val barcode: String? = null,
    val breed: String,
    val gender: String,
    @SerialName("age_months") val ageMonths: Int,
    @SerialName("weight_kg") val weightKg: Double,
    val color: String? = null,
    @SerialName("identification_mark") val identificationMark: String? = null,
    val photos: List<GoatPhotoDto> = emptyList(),
    val vaccines: List<VaccineDto> = emptyList(),
    @SerialName("payment_mode") val paymentMode: String,
    val amount: Double,
    @SerialName("receipt_number") val receiptNumber: String,
)

@Serializable
data class EnrollGoatResponse(
    val status: String,
    @SerialName("goat_id") val goatId: Int? = null,
    @SerialName("policy_number") val policyNumber: String? = null,
    @SerialName("valid_from") val validFrom: String? = null,
    @SerialName("valid_to") val validTo: String? = null,
    @SerialName("sum_insured") val sumInsured: Double? = null,
    val reason: String? = null,
)

@Serializable
data class GoatListItem(
    val id: Int,
    @SerialName("ear_tag_number") val earTagNumber: String,
    val breed: String,
    val gender: String,
    @SerialName("age_months") val ageMonths: Int,
    val farmer: String? = null,
    val village: String? = null,
    @SerialName("enrolled_date") val enrolledDate: String? = null,
    val status: String,
)

@Serializable
data class GoatListResponse(
    val total: Int,
    val counts: Map<String, Int>,
    val page: Int,
    @SerialName("page_size") val pageSize: Int,
    val goats: List<GoatListItem>,
)

@Serializable
data class RecordVaccinationRequest(
    @SerialName("goat_id") val goatId: Int,
    @SerialName("vaccine_type") val vaccineType: String,
    @SerialName("batch_number") val batchNumber: String? = null,
    @SerialName("vaccination_date") val vaccinationDate: String,
    @SerialName("next_vaccination_date") val nextVaccinationDate: String? = null,
)

@Serializable
data class VaccinationItem(
    @SerialName("goat_id") val goatId: Int,
    @SerialName("ear_tag") val earTag: String? = null,
    @SerialName("vaccine_type") val vaccineType: String,
    @SerialName("next_vaccination_date") val nextVaccinationDate: String? = null,
    val status: String,
)

@Serializable
data class VaccinationListResponse(
    val counts: Map<String, Int>,
    val items: List<VaccinationItem>,
)

@Serializable
data class ReportMortalityRequest(
    @SerialName("goat_id") val goatId: Int,
    @SerialName("date_of_death") val dateOfDeath: String,
    @SerialName("cause_of_death") val causeOfDeath: String? = null,
    val photo: String? = null,
)

@Serializable
data class MortalityIdResponse(
    val status: String,
    @SerialName("mortality_id") val mortalityId: Int? = null,
    val reason: String? = null,
)

@Serializable
data class MortalityPhotoDto(
    @SerialName("photo_type") val photoType: String,  // full_body|ear_tag|close_up|location
    val url: String,
)

@Serializable
data class UploadMortalityPhotosRequest(
    @SerialName("mortality_id") val mortalityId: Int,
    val photos: List<MortalityPhotoDto>,
)

@Serializable
data class MortalityProgress(
    @SerialName("death_notification") val deathNotification: String,
    @SerialName("site_visit") val siteVisit: String,
    @SerialName("carcass_verification") val carcassVerification: String,
    @SerialName("ai_assessment") val aiAssessment: String,
    @SerialName("claim_submission") val claimSubmission: String,
)

@Serializable
data class AiAssistantRequest(val topic: String, val message: String)

@Serializable
data class AiAssistantResponse(val answer: String)

// ============================================================
// Farmer DTOs
// ============================================================
@Serializable
data class PolicyDto(
    @SerialName("policy_number") val policyNumber: String,
    @SerialName("ear_tag_number") val earTagNumber: String,
    val breed: String,
    @SerialName("valid_from") val validFrom: String,
    @SerialName("valid_to") val validTo: String,
    @SerialName("sum_insured") val sumInsured: Double,
    val status: String,
)

@Serializable
data class MyPoliciesResponse(val policies: List<PolicyDto>)

@Serializable
data class ReportDeathRequest(
    @SerialName("goat_id") val goatId: Int,
    @SerialName("date_time_of_death") val dateTimeOfDeath: String,
    val photo: String? = null,
    val confirm: Boolean,
)

@Serializable
data class VaccinationScheduleItem(
    @SerialName("goat_id") val goatId: Int,
    @SerialName("ear_tag_number") val earTagNumber: String,
    @SerialName("vaccine_type") val vaccineType: String,
    @SerialName("next_vaccination_date") val nextVaccinationDate: String? = null,
    val status: String,
)

// ============================================================
// Coordinator DTOs
// ============================================================
@Serializable
data class CoDashboard(
    @SerialName("total_enrollments") val totalEnrollments: Int,
    @SerialName("claims_today") val claimsToday: Int,
    @SerialName("policies_expiring_pct") val policiesExpiringPct: Double,
    @SerialName("active_policies") val activePolicies: Int,
)

@Serializable
data class ActivityItem(
    val type: String,
    val actor: String,
    val detail: String,
    val time: String,
)

@Serializable
data class ClusterDto(
    val id: Int,
    val name: String,
    val block: String? = null,
    @SerialName("gps_location") val gpsLocation: String? = null,
    @SerialName("claim_level") val claimLevel: String,
)

@Serializable
data class ClaimListItem(
    @SerialName("claim_number") val claimNumber: String,
    @SerialName("ear_tag_number") val earTagNumber: String,
    val farmer: String? = null,
    @SerialName("date_of_death") val dateOfDeath: String? = null,
    @SerialName("cause_of_death") val causeOfDeath: String? = null,
    @SerialName("sum_insured") val sumInsured: Double,
    @SerialName("ai_confidence") val aiConfidence: Double? = null,
    val status: String,
)

@Serializable
data class ClaimListResponse(val total: Int, val claims: List<ClaimListItem>)

@Serializable
data class ReviewClaimRequest(
    @SerialName("claim_number") val claimNumber: String,
    val action: String,  // approve|reject|hold
    @SerialName("claim_amount") val claimAmount: Double? = null,
)

@Serializable
data class ReportsResponse(
    @SerialName("total_enrollments") val totalEnrollments: Int,
    @SerialName("total_claims_filed") val totalClaimsFiled: Int,
    @SerialName("claims_approved") val claimsApproved: Int,
    @SerialName("claims_rejected") val claimsRejected: Int,
    @SerialName("total_premium") val totalPremium: Double,
    @SerialName("total_claims_paid") val totalClaimsPaid: Double,
)

@Serializable
data class TeamMember(
    val id: Int,
    @SerialName("full_name") val fullName: String,
    @SerialName("mobile_number") val mobileNumber: String,
    val village: String? = null,
    val enrollments: Int,
    @SerialName("is_active") val isActive: Boolean,
)

@Serializable
data class TeamListResponse(val team: List<TeamMember>)
