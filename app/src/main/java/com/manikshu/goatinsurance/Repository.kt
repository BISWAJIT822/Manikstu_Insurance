package com.manikshu.goatinsurance

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val api: ApiService,
) {
    // ---------------- error handling ----------------
    /**
     * Runs an API call and wraps the result in [Result], converting transport/HTTP
     * failures into a human-readable message (parsing FastAPI's `{"detail": ...}` body).
     */
    suspend fun <T> safeCall(block: suspend ApiService.() -> T): Result<T> = try {
        Result.success(api.block())
    } catch (e: HttpException) {
        Result.failure(Exception(parseHttpError(e)))
    } catch (e: IOException) {
        Result.failure(Exception("Network error. Check your connection and that the server is reachable."))
    } catch (e: Exception) {
        Result.failure(Exception(e.message ?: "Something went wrong"))
    }

    private fun parseHttpError(e: HttpException): String {
        val raw = try { e.response()?.errorBody()?.string() } catch (_: Exception) { null }
        val detail = raw?.let {
            try {
                val el = Json.parseToJsonElement(it).jsonObject["detail"]
                el?.jsonPrimitive?.content
            } catch (_: Exception) { null }
        }
        return detail ?: when (e.code()) {
            401 -> "Session expired. Please log in again."
            403 -> "You are not allowed to perform this action."
            404 -> "Not found."
            429 -> "Too many requests. Please wait a moment and try again."
            else -> "Request failed (${e.code()})."
        }
    }

    // ---------------- auth ----------------
    suspend fun forgotPassword(body: ForgotPasswordRequest) = api.forgotPassword(body)
    suspend fun verifySignup(body: VerifySignupRequest) = api.verifySignup(body)
    suspend fun profile() = api.profile()
    suspend fun setPassword(body: SetPasswordRequest) = api.setPassword(body)
    suspend fun logout() = api.logout()

    // ---------------- upload ----------------
    /** Uploads image [bytes] and returns the server URL (e.g. "/uploads/<name>.jpg"). */
    suspend fun uploadPhoto(bytes: ByteArray, fileName: String, mime: String = "image/jpeg"): String {
        val body = bytes.toRequestBody(mime.toMediaTypeOrNull())
        val part = MultipartBody.Part.createFormData("file", fileName, body)
        return api.uploadPhoto(part).url
    }

    // ---------------- locations ----------------
    suspend fun states() = api.states()
    suspend fun districts(state: String) = api.districts(state)
    suspend fun blocks(state: String, district: String) = api.blocks(state, district)
    suspend fun villages(state: String, district: String, block: String) = api.villages(state, district, block)

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
    suspend fun sdProfile() = api.sdProfile()
    suspend fun sdDashboard() = api.sdDashboard()
    suspend fun farmerLookup(mobile: String) = api.farmerLookup(mobile)
    suspend fun enrollGoat(body: EnrollGoatRequest) = api.enrollGoat(body)
    suspend fun sdGoats(tab: String = "all", search: String? = null, page: Int = 1, pageSize: Int = 20) =
        api.sdGoats(tab, search, page, pageSize)
    suspend fun sdGoatDetail(id: Int) = api.sdGoatDetail(id)
    suspend fun sdVaccinations(filter: String = "all") = api.sdVaccinations(filter)
    suspend fun recordVaccination(body: RecordVaccinationRequest) = api.recordVaccination(body)
    suspend fun reportMortality(body: ReportMortalityRequest) = api.reportMortality(body)
    suspend fun uploadMortalityPhotos(body: UploadMortalityPhotosRequest) = api.uploadMortalityPhotos(body)
    suspend fun mortalityProgress(id: Int) = api.mortalityProgress(id)
    suspend fun aiAssistant(topic: String, message: String) = api.aiAssistant(AiAssistantRequest(topic, message))
    suspend fun sdEarnings() = api.sdEarnings()
    suspend fun sdWithdraw() = api.sdWithdraw()
    suspend fun sdClaims() = api.sdClaims()
    suspend fun sdLiveActivity() = api.sdLiveActivity()

    // ---------------- farmer ----------------
    suspend fun farmerProfile() = api.farmerProfile()
    suspend fun farmerPolicies() = api.farmerPolicies()
    suspend fun farmerPolicyDetail(policyNumber: String) = api.farmerPolicyDetail(policyNumber)
    suspend fun farmerReportDeath(body: ReportDeathRequest) = api.farmerReportDeath(body)
    suspend fun farmerVaccinationSchedule(goatId: Int? = null) = api.farmerVaccinationSchedule(goatId)
    suspend fun farmerClaims() = api.farmerClaims()

    // ---------------- coordinator ----------------
    suspend fun coProfile() = api.coProfile()
    suspend fun coDashboard() = api.coDashboard()
    suspend fun coPerformance() = api.coPerformance()
    suspend fun coLiveActivity(page: Int = 1) = api.coLiveActivity(page)
    suspend fun coClusterMap(block: String? = null) = api.coClusterMap(block)
    suspend fun coClaims(status: String? = null, page: Int = 1) = api.coClaims(status, page)
    suspend fun coClaimReview(claimNumber: String) = api.coClaimReview(claimNumber)
    suspend fun reviewClaim(body: ReviewClaimRequest) = api.reviewClaim(body)
    suspend fun coReports(dateFrom: String? = null, dateTo: String? = null, tab: String = "summary") =
        api.coReports(dateFrom, dateTo, tab)
    suspend fun coTeam() = api.coTeam()
}
