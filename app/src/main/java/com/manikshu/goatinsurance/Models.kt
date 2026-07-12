package com.manikshu.goatinsurance

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// =====================================================================================
//  UI domain enums (used across the Compose UI)
// =====================================================================================

enum class UserRole {
    SURAKSHA_DIDI, FARMER, COORDINATOR;

    /** Backend role code expected by the API ("sd" / "fr" / "co"). */
    val apiCode: String
        get() = when (this) {
            SURAKSHA_DIDI -> "sd"
            FARMER -> "fr"
            COORDINATOR -> "co"
        }

    companion object {
        fun fromApiCode(code: String?): UserRole? = when (code) {
            "sd" -> SURAKSHA_DIDI
            "fr" -> FARMER
            "co" -> COORDINATOR
            else -> null
        }
    }
}

enum class AppLanguage(val code: String, val label: String) {
    ENGLISH("en", "English"),
    HINDI("hi", "हिन्दी"),
    ODIA("or", "ଓଡ଼ିଆ");
}

fun AppLanguage.getT(en: String, hi: String, or: String): String {
    return when (this) {
        AppLanguage.ENGLISH -> en
        AppLanguage.HINDI -> hi
        AppLanguage.ODIA -> or
    }
}

// =====================================================================================
//  Auth
// =====================================================================================

@Serializable
data class OtpResponse(val response: String)   // "<6-digit otp>" or "duplicate"

@Serializable
data class LoginResponse(
    val status: String,
    @SerialName("access_token") val accessToken: String? = null,
    @SerialName("token_type") val tokenType: String? = null,
    val reason: String? = null,
)

@Serializable
data class StatusResponse(
    val status: String,
    val reason: String? = null,
    @SerialName("access_token") val accessToken: String? = null,
    @SerialName("token_type") val tokenType: String? = null,
)

@Serializable
data class VerifySignupRequest(
    @SerialName("full_name") val fullName: String,
    @SerialName("mobile_number") val mobileNumber: String,
    val role: String,
    val otp: String,
    val village: String? = null,
    @SerialName("aadhaar_id") val aadhaarId: String? = null,
    val photo: String? = null,
)

@Serializable
data class ProfileResponse(
    val photo: String? = null,
    @SerialName("mobile_number") val mobileNumber: String,
    @SerialName("full_name") val fullName: String,
    val role: String,
    val village: String? = null,
)

@Serializable
data class UploadResponse(val url: String)

// ----------------------- locations (cascading dropdowns) -----------------------
@Serializable
data class StatesResponse(val states: List<String> = emptyList())

@Serializable
data class DistrictsResponse(val districts: List<String> = emptyList())

@Serializable
data class BlocksResponse(val blocks: List<String> = emptyList())

@Serializable
data class VillagesResponse(val villages: List<String> = emptyList())

// =====================================================================================
//  Admin (no admin UI in the app, kept for completeness)
// =====================================================================================

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

@Serializable
data class UserDto(
    val id: Int,
    @SerialName("full_name") val fullName: String,
    @SerialName("mobile_number") val mobileNumber: String,
    val role: String,
    val village: String? = null,
    val photo: String? = null,
    @SerialName("is_approved") val isApproved: Boolean = false,
    @SerialName("is_active") val isActive: Boolean = true,
)

@Serializable
data class UserListResponse(
    val total: Int = 0,
    val page: Int = 1,
    @SerialName("page_size") val pageSize: Int = 20,
    val users: List<UserDto> = emptyList(),
)

// =====================================================================================
//  Suraksha Didi
// =====================================================================================

@Serializable
data class SdDashboard(
    @SerialName("total_enrolled") val totalEnrolled: Int,
    @SerialName("pending_queries") val pendingQueries: Int,
    @SerialName("policies_issued") val policiesIssued: Int,
    @SerialName("premium_collected") val premiumCollected: Double,
)

@Serializable
data class GoatPhotoIn(
    @SerialName("photo_type") val photoType: String,   // left / right / face / ear_tag
    val url: String,
)

@Serializable
data class VaccineIn(
    @SerialName("vaccine_type") val vaccineType: String,  // ppr / et_tt / fmd / goat_pox
    val status: String = "pending",                       // done / pending
    @SerialName("batch_number") val batchNumber: String? = null,
    @SerialName("vaccination_date") val vaccinationDate: String? = null,
    @SerialName("next_vaccination_date") val nextVaccinationDate: String? = null,
)

@Serializable
data class EnrollGoatRequest(
    // farmer
    @SerialName("farmer_name") val farmerName: String,
    @SerialName("farmer_mobile") val farmerMobile: String,
    val village: String? = null,
    @SerialName("gps_location") val gpsLocation: String? = null,
    @SerialName("aadhaar_id") val aadhaarId: String? = null,
    // goat
    @SerialName("ear_tag_number") val earTagNumber: String,
    val barcode: String? = null,
    val breed: String,
    val gender: String,                 // male / female
    @SerialName("age_months") val ageMonths: Int,
    @SerialName("weight_kg") val weightKg: Double,
    val color: String? = null,
    @SerialName("identification_mark") val identificationMark: String? = null,
    // photos + vaccines
    val photos: List<GoatPhotoIn> = emptyList(),
    val vaccines: List<VaccineIn> = emptyList(),
    // premium
    @SerialName("payment_mode") val paymentMode: String,  // cash / upi / wallet / other
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
    val total: Int = 0,
    val counts: Map<String, Int> = emptyMap(),
    val page: Int = 1,
    @SerialName("page_size") val pageSize: Int = 20,
    val goats: List<GoatListItem> = emptyList(),
)

@Serializable
data class GoatFarmerInfo(
    val name: String? = null,
    val mobile: String? = null,
    val village: String? = null,
)

@Serializable
data class GoatPhotoOut(val type: String, val url: String)

@Serializable
data class GoatVaccinationOut(
    val type: String,
    val status: String,
    val next: String? = null,
)

@Serializable
data class GoatPolicyOut(
    @SerialName("policy_number") val policyNumber: String,
    @SerialName("valid_from") val validFrom: String,
    @SerialName("valid_to") val validTo: String,
    @SerialName("sum_insured") val sumInsured: Double,
)

@Serializable
data class GoatDetail(
    val id: Int,
    @SerialName("ear_tag_number") val earTagNumber: String,
    val barcode: String? = null,
    val breed: String,
    val gender: String,
    @SerialName("age_months") val ageMonths: Int,
    @SerialName("weight_kg") val weightKg: Double,
    val color: String? = null,
    @SerialName("identification_mark") val identificationMark: String? = null,
    val status: String,
    val farmer: GoatFarmerInfo? = null,
    val photos: List<GoatPhotoOut> = emptyList(),
    val vaccinations: List<GoatVaccinationOut> = emptyList(),
    val policy: GoatPolicyOut? = null,
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
    val counts: Map<String, Int> = emptyMap(),   // upcoming / due
    val items: List<VaccinationItem> = emptyList(),
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
data class MortalityPhotoIn(
    @SerialName("photo_type") val photoType: String,  // full_body / ear_tag / close_up / location
    val url: String,
)

@Serializable
data class UploadMortalityPhotosRequest(
    @SerialName("mortality_id") val mortalityId: Int,
    val photos: List<MortalityPhotoIn>,
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

@Serializable
data class EarningItem(
    val id: Int,
    val source: String,
    val amount: Double,
    @SerialName("earned_on") val earnedOn: String? = null,
    @SerialName("ear_tag_number") val earTagNumber: String? = null,
)

@Serializable
data class EarningListResponse(
    val total: Double = 0.0,
    val items: List<EarningItem> = emptyList(),
)

@Serializable
data class SdClaimItem(
    @SerialName("mortality_id") val mortalityId: Int,
    @SerialName("goat_id") val goatId: Int,
    @SerialName("ear_tag_number") val earTagNumber: String,
    val farmer: String? = null,
    @SerialName("date_of_death") val dateOfDeath: String? = null,
    @SerialName("cause_of_death") val causeOfDeath: String? = null,
    @SerialName("claim_number") val claimNumber: String? = null,
    @SerialName("claim_status") val claimStatus: String? = null,
    @SerialName("claim_amount") val claimAmount: Double? = null,
    @SerialName("death_notification") val deathNotification: String,
    @SerialName("site_visit") val siteVisit: String,
    @SerialName("carcass_verification") val carcassVerification: String,
    @SerialName("ai_assessment") val aiAssessment: String,
    @SerialName("claim_submission") val claimSubmission: String,
)

@Serializable
data class SdClaimListResponse(
    val total: Int = 0,
    val claims: List<SdClaimItem> = emptyList(),
)

// =====================================================================================
//  Farmer
// =====================================================================================

@Serializable
data class PolicyOut(
    @SerialName("policy_number") val policyNumber: String,
    @SerialName("goat_id") val goatId: Int = 0,
    @SerialName("ear_tag_number") val earTagNumber: String,
    val breed: String,
    @SerialName("valid_from") val validFrom: String,
    @SerialName("valid_to") val validTo: String,
    @SerialName("sum_insured") val sumInsured: Double,
    val status: String,
)

@Serializable
data class MyPoliciesResponse(val policies: List<PolicyOut> = emptyList())

@Serializable
data class PolicyGoatInfo(
    @SerialName("ear_tag_number") val earTagNumber: String,
    val breed: String,
    val gender: String,
    @SerialName("age_months") val ageMonths: Int,
)

@Serializable
data class PolicyDetail(
    @SerialName("policy_number") val policyNumber: String,
    @SerialName("valid_from") val validFrom: String,
    @SerialName("valid_to") val validTo: String,
    @SerialName("sum_insured") val sumInsured: Double,
    @SerialName("annual_premium") val annualPremium: Double,
    val status: String,
    @SerialName("certificate_url") val certificateUrl: String? = null,
    val goat: PolicyGoatInfo? = null,
    @SerialName("next_vaccination_date") val nextVaccinationDate: String? = null,
)

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

@Serializable
data class FarmerClaimItem(
    @SerialName("mortality_id") val mortalityId: Int,
    @SerialName("goat_id") val goatId: Int,
    @SerialName("ear_tag_number") val earTagNumber: String,
    val breed: String? = null,
    @SerialName("date_of_death") val dateOfDeath: String? = null,
    @SerialName("cause_of_death") val causeOfDeath: String? = null,
    @SerialName("claim_number") val claimNumber: String? = null,
    @SerialName("claim_status") val claimStatus: String? = null,
    @SerialName("claim_amount") val claimAmount: Double? = null,
    @SerialName("sum_insured") val sumInsured: Double? = null,
    val progress: Map<String, String> = emptyMap(),
)

@Serializable
data class FarmerClaimListResponse(
    val total: Int = 0,
    val claims: List<FarmerClaimItem> = emptyList(),
)

// =====================================================================================
//  Coordinator
// =====================================================================================

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
data class ClaimListResponse(
    val total: Int = 0,
    val claims: List<ClaimListItem> = emptyList(),
)

@Serializable
data class ClaimGoatInfo(
    @SerialName("ear_tag_number") val earTagNumber: String,
    val breed: String,
)

@Serializable
data class ClaimAiAssessment(val confidence: Double? = null)

@Serializable
data class ClaimReview(
    @SerialName("claim_number") val claimNumber: String,
    val status: String,
    val goat: ClaimGoatInfo? = null,
    val farmer: String? = null,
    @SerialName("date_of_death") val dateOfDeath: String? = null,
    @SerialName("cause_of_death") val causeOfDeath: String? = null,
    @SerialName("sum_insured") val sumInsured: Double,
    @SerialName("ai_assessment") val aiAssessment: ClaimAiAssessment? = null,
    @SerialName("site_visit") val siteVisit: String? = null,
    @SerialName("claim_amount") val claimAmount: Double? = null,
)

@Serializable
data class ReviewClaimRequest(
    @SerialName("claim_number") val claimNumber: String,
    val action: String,                 // approve / reject / hold
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
    val enrollments: Int = 0,
    @SerialName("is_active") val isActive: Boolean = true,
)

@Serializable
data class TeamListResponse(val team: List<TeamMember> = emptyList())
