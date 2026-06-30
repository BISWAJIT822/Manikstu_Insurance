package com.manikshu.goatinsurance

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val api: ApiService,
) {
    // ---------------- auth ----------------
    suspend fun requestOtp(mobile: String, role: String) = api.requestOtp(mobile, role)
    suspend fun verifyLogin(mobile: String, role: String, otp: String) = api.verifyLogin(mobile, role, otp)
    suspend fun requestOtpSignup(fullName: String, mobile: String, role: String) =
        api.requestOtpSignup(fullName, mobile, role)
    suspend fun verifySignup(fullName: String, mobile: String, role: String, otp: String) =
        api.verifySignup(VerifySignupRequest(fullName, mobile, role, otp))
    suspend fun profile() = api.profile()
    suspend fun logout() = api.logout()

    // ---------------- admin ----------------
    suspend fun adminOverview() = api.adminOverview()
    suspend fun listUsers(
        role: String? = null, isApproved: Boolean? = null, isActive: Boolean? = null,
        search: String? = null, page: Int = 1, pageSize: Int = 20,
    ) = api.listUsers(role, isApproved, isActive, search, page, pageSize)
    suspend fun approveUser(id: Int) = api.approveUser(id)
    suspend fun rejectUser(id: Int) = api.rejectUser(id)
    suspend fun activateUser(id: Int) = api.activateUser(id)
    suspend fun deactivateUser(id: Int) = api.deactivateUser(id)

    // ---------------- suraksha didi ----------------
    suspend fun sdDashboard() = api.sdDashboard()
    suspend fun enrollGoat(body: EnrollGoatRequest) = api.enrollGoat(body)
    suspend fun sdGoats(tab: String = "all", search: String? = null, page: Int = 1) =
        api.sdGoats(tab, search, page)
    suspend fun sdGoatDetail(id: Int) = api.sdGoatDetail(id)
    suspend fun sdVaccinations(filter: String = "all") = api.sdVaccinations(filter)
    suspend fun recordVaccination(body: RecordVaccinationRequest) = api.recordVaccination(body)
    suspend fun reportMortality(body: ReportMortalityRequest) = api.reportMortality(body)
    suspend fun uploadMortalityPhotos(body: UploadMortalityPhotosRequest) = api.uploadMortalityPhotos(body)
    suspend fun mortalityProgress(id: Int) = api.mortalityProgress(id)
    suspend fun aiAssistant(topic: String, message: String) = api.aiAssistant(AiAssistantRequest(topic, message))

    // ---------------- farmer ----------------
    suspend fun farmerPolicies() = api.farmerPolicies()
    suspend fun farmerPolicyDetail(policyNumber: String) = api.farmerPolicyDetail(policyNumber)
    suspend fun farmerReportDeath(body: ReportDeathRequest) = api.farmerReportDeath(body)
    suspend fun farmerVaccinationSchedule(goatId: Int? = null) = api.farmerVaccinationSchedule(goatId)

    // ---------------- coordinator ----------------
    suspend fun coDashboard() = api.coDashboard()
    suspend fun coLiveActivity(page: Int = 1) = api.coLiveActivity(page)
    suspend fun coClusterMap(block: String? = null) = api.coClusterMap(block)
    suspend fun coClaims(status: String? = null, page: Int = 1) = api.coClaims(status, page)
    suspend fun coClaimReview(claimNumber: String) = api.coClaimReview(claimNumber)
    suspend fun reviewClaim(body: ReviewClaimRequest) = api.reviewClaim(body)
    suspend fun coReports(dateFrom: String? = null, dateTo: String? = null, tab: String = "summary") =
        api.coReports(dateFrom, dateTo, tab)
    suspend fun coTeam() = api.coTeam()
}
