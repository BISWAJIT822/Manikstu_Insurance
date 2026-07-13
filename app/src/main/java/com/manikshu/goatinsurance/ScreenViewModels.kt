package com.manikshu.goatinsurance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/** Generic load state for read screens. */
sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>
}

/** State for a one-shot submit action (enroll, record, review, ...). */
sealed interface SubmitState {
    data object Idle : SubmitState
    data object Submitting : SubmitState
    data class Success(val message: String? = null) : SubmitState
    data class Error(val message: String) : SubmitState
}

/** Profile Settings: canonical account details fetched from the backend DB. */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repo: Repository,
    private val session: SessionManager,
) : ViewModel() {

    private val _profile = MutableStateFlow<ProfileResponse?>(null)
    val profile = _profile.asStateFlow()

    init { refresh() }

    fun refresh() {
        viewModelScope.launch {
            repo.safeCall { profile() }.onSuccess { p ->
                _profile.value = p
                // Sync the local cache so every screen shows the DB values.
                session.saveProfileDetails(name = p.fullName, village = p.village, mobile = p.mobileNumber)
            }
            // On failure the screen falls back to cached session values.
        }
    }
}

/** Backs the cascading State -> District -> Block -> Village dropdowns on profile setup. */
@HiltViewModel
class LocationViewModel @Inject constructor(
    private val repo: Repository,
) : ViewModel() {
    private val _states = MutableStateFlow<List<String>>(emptyList())
    val states = _states.asStateFlow()

    private val _districts = MutableStateFlow<List<String>>(emptyList())
    val districts = _districts.asStateFlow()

    private val _blocks = MutableStateFlow<List<String>>(emptyList())
    val blocks = _blocks.asStateFlow()

    private val _villages = MutableStateFlow<List<String>>(emptyList())
    val villages = _villages.asStateFlow()

    init { loadStates() }

    fun loadStates() {
        viewModelScope.launch {
            repo.safeCall { states() }.onSuccess { _states.value = it.states }
        }
    }

    fun loadDistricts(state: String) {
        _districts.value = emptyList(); _blocks.value = emptyList(); _villages.value = emptyList()
        if (state.isBlank()) return
        viewModelScope.launch {
            repo.safeCall { districts(state) }.onSuccess { _districts.value = it.districts }
        }
    }

    fun loadBlocks(state: String, district: String) {
        _blocks.value = emptyList(); _villages.value = emptyList()
        if (state.isBlank() || district.isBlank()) return
        viewModelScope.launch {
            repo.safeCall { blocks(state, district) }.onSuccess { _blocks.value = it.blocks }
        }
    }

    fun loadVillages(state: String, district: String, block: String) {
        _villages.value = emptyList()
        if (state.isBlank() || district.isBlank() || block.isBlank()) return
        viewModelScope.launch {
            repo.safeCall { villages(state, district, block) }.onSuccess { _villages.value = it.villages }
        }
    }
}

// =====================================================================================
//  Suraksha Didi
// =====================================================================================

@HiltViewModel
class DidiDashboardViewModel @Inject constructor(
    private val repo: Repository,
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<SdDashboard>>(UiState.Loading)
    val state = _state.asStateFlow()

    init { load() }

    fun load() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            repo.safeCall { sdDashboard() }
                .onSuccess { _state.value = UiState.Success(it) }
                .onFailure { _state.value = UiState.Error(it.message ?: "Failed to load dashboard") }
        }
    }
}

@HiltViewModel
class GoatListViewModel @Inject constructor(
    private val repo: Repository,
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<GoatListResponse>>(UiState.Loading)
    val state = _state.asStateFlow()

    var tab: String = "all"; private set
    var search: String? = null; private set

    init { load() }

    fun setTab(newTab: String) { tab = newTab; load() }
    fun setSearch(query: String?) { search = query?.takeIf { it.isNotBlank() }; load() }

    fun load() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            repo.safeCall { sdGoats(tab = tab, search = search) }
                .onSuccess { _state.value = UiState.Success(it) }
                .onFailure { _state.value = UiState.Error(it.message ?: "Failed to load goats") }
        }
    }
}

@HiltViewModel
class GoatDetailViewModel @Inject constructor(
    private val repo: Repository,
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<GoatDetail>>(UiState.Loading)
    val state = _state.asStateFlow()

    fun load(goatId: Int) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            repo.safeCall { sdGoatDetail(goatId) }
                .onSuccess { _state.value = UiState.Success(it) }
                .onFailure { _state.value = UiState.Error(it.message ?: "Failed to load goat") }
        }
    }
}

@HiltViewModel
class PolicyDetailViewModel @Inject constructor(
    private val repo: Repository,
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<PolicyDetail>>(UiState.Loading)
    val state = _state.asStateFlow()

    /** Idle -> Submitting while downloading; Success carries the saved PDF path. */
    private val _certificate = MutableStateFlow<SubmitState>(SubmitState.Idle)
    val certificate = _certificate.asStateFlow()

    fun load(policyNumber: String) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            repo.safeCall { farmerPolicyDetail(policyNumber) }
                .onSuccess { _state.value = UiState.Success(it) }
                .onFailure { _state.value = UiState.Error(it.message ?: "Failed to load policy") }
        }
    }

    fun downloadCertificate(earTag: String, targetDir: java.io.File) {
        viewModelScope.launch {
            _certificate.value = SubmitState.Submitting
            repo.safeCall { policyCertificate(earTag) }
                .onSuccess { body ->
                    runCatching {
                        val file = java.io.File(targetDir, "policy_$earTag.pdf")
                        body.byteStream().use { input ->
                            file.outputStream().use { output -> input.copyTo(output) }
                        }
                        file.absolutePath
                    }.onSuccess { path ->
                        _certificate.value = SubmitState.Success(path)
                    }.onFailure {
                        _certificate.value = SubmitState.Error("Could not save the certificate")
                    }
                }
                .onFailure { _certificate.value = SubmitState.Error(it.message ?: "Download failed") }
        }
    }

    fun resetCertificate() { _certificate.value = SubmitState.Idle }
}

@HiltViewModel
class VaccinationViewModel @Inject constructor(
    private val repo: Repository,
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<VaccinationListResponse>>(UiState.Loading)
    val state = _state.asStateFlow()

    private val _submit = MutableStateFlow<SubmitState>(SubmitState.Idle)
    val submit = _submit.asStateFlow()

    var filter: String = "all"; private set

    init { load() }

    fun setFilter(f: String) { filter = f; load() }

    fun load() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            repo.safeCall { sdVaccinations(filter) }
                .onSuccess { _state.value = UiState.Success(it) }
                .onFailure { _state.value = UiState.Error(it.message ?: "Failed to load vaccinations") }
        }
    }

    fun record(req: RecordVaccinationRequest) {
        viewModelScope.launch {
            _submit.value = SubmitState.Submitting
            repo.safeCall { recordVaccination(req) }
                .onSuccess {
                    if (it.status == "success") {
                        _submit.value = SubmitState.Success("Vaccination recorded")
                        load()
                    } else _submit.value = SubmitState.Error(it.reason ?: "Failed to record")
                }
                .onFailure { _submit.value = SubmitState.Error(it.message ?: "Failed to record") }
        }
    }

    fun resetSubmit() { _submit.value = SubmitState.Idle }
}

@HiltViewModel
class SdClaimsViewModel @Inject constructor(
    private val repo: Repository,
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<SdClaimListResponse>>(UiState.Loading)
    val state = _state.asStateFlow()

    init { load() }

    fun load() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            repo.safeCall { sdClaims() }
                .onSuccess { _state.value = UiState.Success(it) }
                .onFailure { _state.value = UiState.Error(it.message ?: "Failed to load claims") }
        }
    }
}

@HiltViewModel
class EarningsViewModel @Inject constructor(
    private val repo: Repository,
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<EarningListResponse>>(UiState.Loading)
    val state = _state.asStateFlow()

    init { load() }

    fun load() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            repo.safeCall { sdEarnings() }
                .onSuccess { _state.value = UiState.Success(it) }
                .onFailure { _state.value = UiState.Error(it.message ?: "Failed to load earnings") }
        }
    }
}

/** Backs the multi-step enrollment: uploads photos then POST /sd/enroll_goat. */
@HiltViewModel
class EnrollmentViewModel @Inject constructor(
    private val repo: Repository,
    private val uploader: PhotoUploader,
) : ViewModel() {
    private val _submit = MutableStateFlow<SubmitState>(SubmitState.Idle)
    val submit = _submit.asStateFlow()

    private val _result = MutableStateFlow<EnrollGoatResponse?>(null)
    val result = _result.asStateFlow()

    fun reset() { _submit.value = SubmitState.Idle; _result.value = null }

    /** [photoUris] are the local content URIs for the 4 goat photos (left/right/face/ear_tag order). */
    fun enroll(request: EnrollGoatRequest, photoUris: List<android.net.Uri>) {
        viewModelScope.launch {
            _submit.value = SubmitState.Submitting
            try {
                val types = listOf("left", "right", "face", "ear_tag")
                val photos = photoUris.mapIndexedNotNull { i, uri ->
                    val bytes = uploader.readBytes(uri) ?: return@mapIndexedNotNull null
                    val url = repo.uploadPhoto(bytes, "goat_${System.currentTimeMillis()}_$i.jpg")
                    GoatPhotoIn(photoType = types.getOrElse(i) { "face" }, url = url)
                }
                if (photos.size < 4) {
                    _submit.value = SubmitState.Error("Please capture all 4 goat photos before submitting.")
                    return@launch
                }
                val res = repo.enrollGoat(request.copy(photos = photos))
                if (res.status == "success") {
                    _result.value = res
                    _submit.value = SubmitState.Success("Enrolled. Policy ${res.policyNumber}")
                } else {
                    _submit.value = SubmitState.Error(res.reason ?: "Enrollment failed")
                }
            } catch (e: Exception) {
                _submit.value = SubmitState.Error(e.message ?: "Enrollment failed")
            }
        }
    }
}

/** Backs the mortality reporting flow (report + upload carcass photos). */
@HiltViewModel
class MortalityViewModel @Inject constructor(
    private val repo: Repository,
    private val uploader: PhotoUploader,
) : ViewModel() {
    private val _submit = MutableStateFlow<SubmitState>(SubmitState.Idle)
    val submit = _submit.asStateFlow()

    fun reset() { _submit.value = SubmitState.Idle }

    /** Reports the death then uploads the 4 carcass photos (full_body/ear_tag/close_up/location). */
    fun report(goatId: Int, cause: String, dateIso: String, photoUris: List<android.net.Uri>) {
        viewModelScope.launch {
            _submit.value = SubmitState.Submitting
            try {
                val idRes = repo.reportMortality(
                    ReportMortalityRequest(goatId = goatId, dateOfDeath = dateIso, causeOfDeath = cause)
                )
                val mortalityId = idRes.mortalityId
                if (idRes.status != "success" || mortalityId == null) {
                    _submit.value = SubmitState.Error(idRes.reason ?: "Failed to report mortality")
                    return@launch
                }
                val types = listOf("full_body", "ear_tag", "close_up", "location")
                val photos = photoUris.mapIndexedNotNull { i, uri ->
                    val bytes = uploader.readBytes(uri) ?: return@mapIndexedNotNull null
                    val url = repo.uploadPhoto(bytes, "carcass_${System.currentTimeMillis()}_$i.jpg")
                    MortalityPhotoIn(photoType = types.getOrElse(i) { "full_body" }, url = url)
                }
                if (photos.size >= 4) {
                    val up = repo.uploadMortalityPhotos(UploadMortalityPhotosRequest(mortalityId, photos))
                    if (up.status != "success") {
                        _submit.value = SubmitState.Error(up.reason ?: "Failed to upload carcass photos")
                        return@launch
                    }
                }
                _submit.value = SubmitState.Success("Mortality report submitted")
            } catch (e: Exception) {
                _submit.value = SubmitState.Error(e.message ?: "Failed to submit report")
            }
        }
    }
}

// =====================================================================================
//  Farmer
// =====================================================================================

@HiltViewModel
class FarmerHomeViewModel @Inject constructor(
    private val repo: Repository,
) : ViewModel() {
    private val _policies = MutableStateFlow<UiState<MyPoliciesResponse>>(UiState.Loading)
    val policies = _policies.asStateFlow()

    private val _schedule = MutableStateFlow<List<VaccinationScheduleItem>>(emptyList())
    val schedule = _schedule.asStateFlow()

    init { load() }

    fun load() {
        viewModelScope.launch {
            _policies.value = UiState.Loading
            repo.safeCall { farmerPolicies() }
                .onSuccess { _policies.value = UiState.Success(it) }
                .onFailure { _policies.value = UiState.Error(it.message ?: "Failed to load policies") }
            repo.safeCall { farmerVaccinationSchedule() }.onSuccess { _schedule.value = it }
        }
    }
}

@HiltViewModel
class FarmerClaimsViewModel @Inject constructor(
    private val repo: Repository,
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<FarmerClaimListResponse>>(UiState.Loading)
    val state = _state.asStateFlow()

    private val _submit = MutableStateFlow<SubmitState>(SubmitState.Idle)
    val submit = _submit.asStateFlow()

    init { load() }

    fun load() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            repo.safeCall { farmerClaims() }
                .onSuccess { _state.value = UiState.Success(it) }
                .onFailure { _state.value = UiState.Error(it.message ?: "Failed to load claims") }
        }
    }

    fun reportDeath(goatId: Int, dateIso: String) {
        viewModelScope.launch {
            _submit.value = SubmitState.Submitting
            repo.safeCall { farmerReportDeath(ReportDeathRequest(goatId, dateIso, null, true)) }
                .onSuccess {
                    if (it.status == "success") { _submit.value = SubmitState.Success("Death reported"); load() }
                    else _submit.value = SubmitState.Error(it.reason ?: "Failed to report")
                }
                .onFailure { _submit.value = SubmitState.Error(it.message ?: "Failed to report") }
        }
    }

    fun resetSubmit() { _submit.value = SubmitState.Idle }
}

// =====================================================================================
//  Coordinator
// =====================================================================================

@HiltViewModel
class CoordinatorDashboardViewModel @Inject constructor(
    private val repo: Repository,
) : ViewModel() {
    private val _dashboard = MutableStateFlow<UiState<CoDashboard>>(UiState.Loading)
    val dashboard = _dashboard.asStateFlow()

    private val _activity = MutableStateFlow<List<ActivityItem>>(emptyList())
    val activity = _activity.asStateFlow()

    private val _team = MutableStateFlow<List<TeamMember>>(emptyList())
    val team = _team.asStateFlow()

    private val _clusters = MutableStateFlow<List<ClusterDto>>(emptyList())
    val clusters = _clusters.asStateFlow()

    init { load() }

    fun load() {
        viewModelScope.launch {
            _dashboard.value = UiState.Loading
            repo.safeCall { coDashboard() }
                .onSuccess { _dashboard.value = UiState.Success(it) }
                .onFailure { _dashboard.value = UiState.Error(it.message ?: "Failed to load dashboard") }
            repo.safeCall { coLiveActivity() }.onSuccess { _activity.value = it }
            repo.safeCall { coTeam() }.onSuccess { _team.value = it.team }
            repo.safeCall { coClusterMap() }.onSuccess { _clusters.value = it }
        }
    }
}

@HiltViewModel
class CoordinatorClaimsViewModel @Inject constructor(
    private val repo: Repository,
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<ClaimListResponse>>(UiState.Loading)
    val state = _state.asStateFlow()

    private val _review = MutableStateFlow<UiState<ClaimReview>?>(null)
    val review = _review.asStateFlow()

    private val _submit = MutableStateFlow<SubmitState>(SubmitState.Idle)
    val submit = _submit.asStateFlow()

    var status: String? = null; private set

    init { load() }

    fun setStatus(s: String?) { status = s; load() }

    fun load() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            repo.safeCall { coClaims(status = status) }
                .onSuccess { _state.value = UiState.Success(it) }
                .onFailure { _state.value = UiState.Error(it.message ?: "Failed to load claims") }
        }
    }

    fun loadReview(claimNumber: String) {
        viewModelScope.launch {
            _review.value = UiState.Loading
            repo.safeCall { coClaimReview(claimNumber) }
                .onSuccess { _review.value = UiState.Success(it) }
                .onFailure { _review.value = UiState.Error(it.message ?: "Failed to load claim") }
        }
    }

    fun review(claimNumber: String, action: String, amount: Double? = null) {
        viewModelScope.launch {
            _submit.value = SubmitState.Submitting
            repo.safeCall { reviewClaim(ReviewClaimRequest(claimNumber, action, amount)) }
                .onSuccess {
                    if (it.status == "success") { _submit.value = SubmitState.Success("Claim $action"); load() }
                    else _submit.value = SubmitState.Error(it.reason ?: "Action failed")
                }
                .onFailure { _submit.value = SubmitState.Error(it.message ?: "Action failed") }
        }
    }

    fun resetSubmit() { _submit.value = SubmitState.Idle }
}

@HiltViewModel
class CoordinatorReportsViewModel @Inject constructor(
    private val repo: Repository,
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<ReportsResponse>>(UiState.Loading)
    val state = _state.asStateFlow()

    init { load("summary") }

    fun load(tab: String) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            repo.safeCall { coReports(tab = tab) }
                .onSuccess { _state.value = UiState.Success(it) }
                .onFailure { _state.value = UiState.Error(it.message ?: "Failed to load reports") }
        }
    }
}
