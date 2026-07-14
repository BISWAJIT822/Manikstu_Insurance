package com.manikshu.goatinsurance

import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {

    // ----------------- APP META -----------------
    @GET("app/version")
    suspend fun appVersion(): AppVersionResponse

    // ----------------- AUTH -----------------
    @GET("auth/config")
    suspend fun authConfig(): AuthConfigResponse

    @POST("auth/login_password")
    suspend fun loginPassword(@Body body: LoginPasswordRequest): LoginResponse

    @GET("auth/request_otp")
    suspend fun requestOtp(
        @Query("mobile_number") mobile: String,
        @Query("role") role: String,
    ): OtpResponse

    @GET("auth/verify_login")
    suspend fun verifyLogin(
        @Query("mobile_number") mobile: String,
        @Query("role") role: String,
        @Query("otp") otp: String,
    ): LoginResponse

    @GET("auth/request_otp_signup")
    suspend fun requestOtpSignup(
        @Query("full_name") fullName: String,
        @Query("mobile_number") mobile: String,
        @Query("role") role: String,
    ): OtpResponse

    @POST("auth/verify_signup")
    suspend fun verifySignup(@Body body: VerifySignupRequest): StatusResponse

    @GET("auth/profile")
    suspend fun profile(): ProfileResponse

    @PUT("auth/profile")
    suspend fun updateProfile(@Body body: UpdateProfileRequest): ProfileResponse

    @Streaming
    @GET("farmer/policy_certificate/{tag}")
    suspend fun policyCertificate(@Path("tag") tag: String): okhttp3.ResponseBody

    @Streaming
    @GET("farmer/policy_certificate_batch")
    suspend fun policyCertificateBatch(@Query("tags") tags: String): okhttp3.ResponseBody

    @GET("auth/logout")
    suspend fun logout(): StatusResponse

    // ----------------- UPLOAD -----------------
    @Multipart
    @POST("upload")
    suspend fun uploadPhoto(@Part file: MultipartBody.Part): UploadResponse

    // ----------------- LOCATIONS (public) -----------------
    @GET("locations/states")
    suspend fun states(): StatesResponse

    @GET("locations/districts")
    suspend fun districts(@Query("state") state: String): DistrictsResponse

    @GET("locations/blocks")
    suspend fun blocks(
        @Query("state") state: String,
        @Query("district") district: String,
    ): BlocksResponse

    @GET("locations/villages")
    suspend fun villages(
        @Query("state") state: String,
        @Query("district") district: String,
        @Query("block") block: String,
    ): VillagesResponse

    @GET("locations/pincode")
    suspend fun pincode(
        @Query("state") state: String,
        @Query("district") district: String,
        @Query("block") block: String,
    ): PincodeResponse

    // ----------------- ADMIN -----------------
    @GET("admin/overview")
    suspend fun adminOverview(): AdminOverview

    @GET("admin/users")
    suspend fun listUsers(
        @Query("role") role: String? = null,
        @Query("is_approved") isApproved: Boolean? = null,
        @Query("is_active") isActive: Boolean? = null,
        @Query("search") search: String? = null,
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 20,
    ): UserListResponse

    @GET("admin/users/{id}")
    suspend fun userDetail(@Path("id") id: Int): UserDto

    @POST("admin/users/{id}/approve")
    suspend fun approveUser(@Path("id") id: Int): StatusResponse

    @POST("admin/users/{id}/reject")
    suspend fun rejectUser(@Path("id") id: Int): StatusResponse

    @POST("admin/users/{id}/activate")
    suspend fun activateUser(@Path("id") id: Int): StatusResponse

    @POST("admin/users/{id}/deactivate")
    suspend fun deactivateUser(@Path("id") id: Int): StatusResponse

    // ----------------- SURAKSHA DIDI -----------------
    @GET("sd/profile")
    suspend fun sdProfile(): ProfileResponse

    @GET("sd/dashboard")
    suspend fun sdDashboard(): SdDashboard

    @POST("sd/enroll_goat")
    suspend fun enrollGoat(@Body body: EnrollGoatRequest): EnrollGoatResponse

    @GET("sd/goats")
    suspend fun sdGoats(
        @Query("tab") tab: String = "all",
        @Query("search") search: String? = null,
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 20,
    ): GoatListResponse

    @GET("sd/goats/{id}")
    suspend fun sdGoatDetail(@Path("id") id: Int): GoatDetail

    @GET("sd/vaccinations")
    suspend fun sdVaccinations(@Query("filter") filter: String = "all"): VaccinationListResponse

    @POST("sd/record_vaccination")
    suspend fun recordVaccination(@Body body: RecordVaccinationRequest): StatusResponse

    @POST("sd/report_mortality")
    suspend fun reportMortality(@Body body: ReportMortalityRequest): MortalityIdResponse

    @POST("sd/upload_mortality_photos")
    suspend fun uploadMortalityPhotos(@Body body: UploadMortalityPhotosRequest): StatusResponse

    @GET("sd/mortality/{id}/progress")
    suspend fun mortalityProgress(@Path("id") id: Int): MortalityProgress

    @POST("sd/ai_assistant")
    suspend fun aiAssistant(@Body body: AiAssistantRequest): AiAssistantResponse

    @GET("sd/earnings")
    suspend fun sdEarnings(): EarningListResponse

    @GET("sd/claims")
    suspend fun sdClaims(): SdClaimListResponse

    // ----------------- FARMER -----------------
    @GET("farmer/profile")
    suspend fun farmerProfile(): ProfileResponse

    @GET("farmer/policies")
    suspend fun farmerPolicies(): MyPoliciesResponse

    @GET("farmer/policies/{policyNumber}")
    suspend fun farmerPolicyDetail(@Path("policyNumber") policyNumber: String): PolicyDetail

    @POST("farmer/report_death")
    suspend fun farmerReportDeath(@Body body: ReportDeathRequest): StatusResponse

    @GET("farmer/vaccination_schedule")
    suspend fun farmerVaccinationSchedule(
        @Query("goat_id") goatId: Int? = null,
    ): List<VaccinationScheduleItem>

    @GET("farmer/claims")
    suspend fun farmerClaims(): FarmerClaimListResponse

    // ----------------- COORDINATOR -----------------
    @GET("coordinator/profile")
    suspend fun coProfile(): ProfileResponse

    @GET("coordinator/dashboard")
    suspend fun coDashboard(): CoDashboard

    @GET("coordinator/live_activity")
    suspend fun coLiveActivity(
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 20,
    ): List<ActivityItem>

    @GET("coordinator/cluster_map")
    suspend fun coClusterMap(@Query("block") block: String? = null): List<ClusterDto>

    @GET("coordinator/claims")
    suspend fun coClaims(
        @Query("status") status: String? = null,
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 20,
    ): ClaimListResponse

    @GET("coordinator/claims/{claimNumber}")
    suspend fun coClaimReview(@Path("claimNumber") claimNumber: String): ClaimReview

    @POST("coordinator/review_claim")
    suspend fun reviewClaim(@Body body: ReviewClaimRequest): StatusResponse

    @GET("coordinator/reports")
    suspend fun coReports(
        @Query("date_from") dateFrom: String? = null,
        @Query("date_to") dateTo: String? = null,
        @Query("tab") tab: String = "summary",
    ): ReportsResponse

    @GET("coordinator/team")
    suspend fun coTeam(): TeamListResponse
}
