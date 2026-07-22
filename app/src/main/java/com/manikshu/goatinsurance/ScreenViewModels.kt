package com.manikshu.goatinsurance

import android.net.Uri
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

/** Result of the enrollment step-1 farmer-mobile check. */
sealed interface FarmerLookup {
    data object Idle : FarmerLookup
    data object Loading : FarmerLookup
    data class Found(val farmer: FarmerLookupResponse) : FarmerLookup
    data object NotFound : FarmerLookup
    data class Error(val message: String) : FarmerLookup
}

/** Force-update gate: checks the app version against the backend on launch. */
sealed interface UpdateState {
    data object Checking : UpdateState
    data object Ok : UpdateState
    data class UpdateRequired(val updateUrl: String, val latestVersion: String) : UpdateState
}

@HiltViewModel
class UpdateGateViewModel @Inject constructor(
    private val repo: Repository,
) : ViewModel() {
    private val _state = MutableStateFlow<UpdateState>(UpdateState.Checking)
    val state = _state.asStateFlow()

    init { check() }

    fun check() {
        _state.value = UpdateState.Checking
        viewModelScope.launch {
            // Bound the check so a cold-starting/slow backend fails open fast
            // instead of leaving the user stuck on the splash.
            val version = kotlinx.coroutines.withTimeoutOrNull(6000) {
                repo.safeCall { appVersion() }.getOrNull()
            }
            _state.value = when {
                version == null -> UpdateState.Ok  // unreachable/slow -> fail open
                BuildConfig.VERSION_CODE < version.minVersionCode ->
                    UpdateState.UpdateRequired(version.updateUrl, version.latestVersionName)
                else -> UpdateState.Ok
            }
        }
    }
}

/** Profile Settings: canonical account details fetched from the backend DB. */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repo: Repository,
    private val session: SessionManager,
    private val uploader: PhotoUploader,
) : ViewModel() {

    private val _profile = MutableStateFlow<ProfileResponse?>(null)
    val profile = _profile.asStateFlow()

    private val _save = MutableStateFlow<SubmitState>(SubmitState.Idle)
    val save = _save.asStateFlow()

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

    /**
     * Persists profile edits. If [photoUri] is a freshly picked local image, it is
     * uploaded first and the resulting URL saved as the profile photo.
     */
    fun updateProfile(name: String?, village: String?, photoUri: android.net.Uri?) {
        viewModelScope.launch {
            _save.value = SubmitState.Submitting
            try {
                var photoUrl: String? = null
                if (photoUri != null) {
                    val bytes = uploader.readBytes(photoUri)
                    if (bytes == null) {
                        _save.value = SubmitState.Error("Could not read the selected image")
                        return@launch
                    }
                    photoUrl = repo.uploadPhoto(bytes, "profile_${System.currentTimeMillis()}.jpg")
                }
                val body = UpdateProfileRequest(
                    fullName = name?.trim()?.takeIf { it.isNotBlank() },
                    village = village?.trim(),
                    photo = photoUrl,
                )
                repo.safeCall { updateProfile(body) }
                    .onSuccess { p ->
                        _profile.value = p
                        session.saveProfileDetails(name = p.fullName, village = p.village, mobile = p.mobileNumber)
                        _save.value = SubmitState.Success("Profile updated")
                    }
                    .onFailure { _save.value = SubmitState.Error(it.message ?: "Update failed") }
            } catch (e: Exception) {
                _save.value = SubmitState.Error(e.message ?: "Update failed")
            }
        }
    }

    fun resetSave() { _save.value = SubmitState.Idle }

    private val _password = MutableStateFlow<SubmitState>(SubmitState.Idle)
    val password = _password.asStateFlow()

    /** Changes the login password (POST auth/set_password). */
    fun changePassword(newPassword: String, currentPassword: String? = null) {
        viewModelScope.launch {
            _password.value = SubmitState.Submitting
            repo.safeCall { setPassword(SetPasswordRequest(newPassword, currentPassword)) }
                .onSuccess { r ->
                    if (r.status == "success") _password.value = SubmitState.Success("Password updated")
                    else _password.value = SubmitState.Error(r.reason ?: "Could not change password")
                }
                .onFailure { _password.value = SubmitState.Error(it.message ?: "Could not change password") }
        }
    }

    fun resetPassword() { _password.value = SubmitState.Idle }
}

/** Loads the current user's in-app notifications (AjahFi notification workflow). */
@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val repo: Repository,
) : ViewModel() {
    private val _items = MutableStateFlow<List<NotificationOut>>(emptyList())
    val items = _items.asStateFlow()

    private val _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()

    private val _unread = MutableStateFlow(0)
    val unread = _unread.asStateFlow()

    fun load() {
        viewModelScope.launch {
            _loading.value = true
            repo.safeCall { notifications() }.onSuccess { _items.value = it }
            _loading.value = false
        }
    }

    fun refreshUnread() {
        viewModelScope.launch {
            repo.safeCall { notificationsUnread() }.onSuccess { _unread.value = it.unread }
        }
    }

    fun markAllRead() {
        viewModelScope.launch {
            repo.safeCall { markNotificationsRead() }
            _unread.value = 0
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

    // Pincode auto-filled from the selected block (blank if unknown).
    private val _pincode = MutableStateFlow("")
    val pincode = _pincode.asStateFlow()

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

    fun loadPincode(state: String, district: String, block: String) {
        _pincode.value = ""
        if (state.isBlank() || district.isBlank() || block.isBlank()) return
        viewModelScope.launch {
            repo.safeCall { pincode(state, district, block) }.onSuccess { _pincode.value = it.pincode }
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

    /**
     * Silently re-fetches without flipping to the Loading state, so the current list stays
     * visible (no spinner flash) while it updates. Used to keep the list live when the screen
     * comes back to the foreground after a vaccination is recorded elsewhere.
     */
    fun refresh() {
        viewModelScope.launch {
            repo.safeCall { sdVaccinations(filter) }
                .onSuccess { _state.value = UiState.Success(it) }
            // keep existing data on failure — this is a background refresh
        }
    }

    fun record(req: RecordVaccinationRequest) = recordMany(listOf(req))

    /**
     * Records one or more vaccinations for a goat (the redesigned screen lets the Didi
     * tick several at once). Each is a separate POST; success is reported only if all
     * succeed, so a partial failure surfaces rather than silently dropping vaccines.
     */
    fun recordMany(reqs: List<RecordVaccinationRequest>) {
        if (reqs.isEmpty()) return
        viewModelScope.launch {
            _submit.value = SubmitState.Submitting
            for (req in reqs) {
                val result = repo.safeCall { recordVaccination(req) }
                val failure = result.exceptionOrNull()?.message
                    ?: result.getOrNull()?.takeIf { it.status != "success" }?.reason
                if (failure != null) {
                    _submit.value = SubmitState.Error(failure)
                    return@launch
                }
            }
            _submit.value = SubmitState.Success(
                if (reqs.size == 1) "Vaccination recorded" else "${reqs.size} vaccinations recorded"
            )
            load()
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

    private val _withdraw = MutableStateFlow<SubmitState>(SubmitState.Idle)
    val withdraw = _withdraw.asStateFlow()

    fun load() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            repo.safeCall { sdEarnings() }
                .onSuccess { _state.value = UiState.Success(it) }
                .onFailure { _state.value = UiState.Error(it.message ?: "Failed to load earnings") }
        }
    }

    /** Raise a withdrawal request; an admin settles it out of band (no money moves here). */
    fun requestWithdrawal() {
        viewModelScope.launch {
            _withdraw.value = SubmitState.Submitting
            repo.safeCall { sdWithdraw() }
                .onSuccess {
                    if (it.status == "success") _withdraw.value = SubmitState.Success(it.reason)
                    else _withdraw.value = SubmitState.Error(it.reason ?: "Could not request withdrawal")
                }
                .onFailure { _withdraw.value = SubmitState.Error(it.message ?: "Could not request withdrawal") }
        }
    }

    fun resetWithdraw() { _withdraw.value = SubmitState.Idle }
}

/** Backs the multi-step enrollment: uploads photos then POST /sd/enroll_goat. */
@HiltViewModel
class EnrollmentViewModel @Inject constructor(
    private val repo: Repository,
    private val uploader: PhotoUploader,
) : ViewModel() {
    private val _submit = MutableStateFlow<SubmitState>(SubmitState.Idle)
    val submit = _submit.asStateFlow()

    // Results for every goat enrolled in the batch (used by the Policy Generated step).
    private val _results = MutableStateFlow<List<EnrollGoatResponse>>(emptyList())
    val results = _results.asStateFlow()

    // Human-readable progress while the batch is uploading/enrolling, e.g. "Enrolling goat 2 of 3…".
    private val _progress = MutableStateFlow<String?>(null)
    val progress = _progress.asStateFlow()

    // Consolidated policy-certificate download (Idle -> Submitting -> Success(path)/Error).
    private val _certificate = MutableStateFlow<SubmitState>(SubmitState.Idle)
    val certificate = _certificate.asStateFlow()

    // Step 1 farmer-mobile check: the farmer must be registered before enrollment.
    private val _farmerLookup = MutableStateFlow<FarmerLookup>(FarmerLookup.Idle)
    val farmerLookup = _farmerLookup.asStateFlow()
    private var lookupJob: kotlinx.coroutines.Job? = null

    /** Look up a farmer by [mobile]; only a 10-digit number is checked. */
    fun lookupFarmer(mobile: String) {
        lookupJob?.cancel()
        if (mobile.length != 10) {
            _farmerLookup.value = FarmerLookup.Idle
            return
        }
        lookupJob = viewModelScope.launch {
            _farmerLookup.value = FarmerLookup.Loading
            repo.safeCall { farmerLookup(mobile) }
                .onSuccess { _farmerLookup.value = if (it.found) FarmerLookup.Found(it) else FarmerLookup.NotFound }
                .onFailure { _farmerLookup.value = FarmerLookup.Error(it.message ?: "Could not verify the number") }
        }
    }

    fun resetFarmerLookup() { _farmerLookup.value = FarmerLookup.Idle }

    fun reset() {
        _submit.value = SubmitState.Idle
        _results.value = emptyList()
        _progress.value = null
    }

    /** Downloads the consolidated certificate for [earTags] and saves it under [targetDir]. */
    fun downloadBatchCertificate(earTags: List<String>, targetDir: java.io.File) {
        viewModelScope.launch {
            _certificate.value = SubmitState.Submitting
            repo.safeCall { policyCertificateBatch(earTags.joinToString(",")) }
                .onSuccess { body ->
                    runCatching {
                        val file = java.io.File(targetDir, "policy_${System.currentTimeMillis()}.pdf")
                        body.byteStream().use { input ->
                            file.outputStream().use { output -> input.copyTo(output) }
                        }
                        file.absolutePath
                    }.onSuccess { path -> _certificate.value = SubmitState.Success(path) }
                        .onFailure { _certificate.value = SubmitState.Error("Could not save the certificate") }
                }
                .onFailure { _certificate.value = SubmitState.Error(it.message ?: "Download failed") }
        }
    }

    fun resetCertificate() { _certificate.value = SubmitState.Idle }

    /**
     * Enrolls every goat in [goats] under one farmer, sharing the vaccination + payment
     * details, via the existing single-goat endpoint (one call per goat). Stops on the
     * first failure and reports which goat failed.
     */
    fun enrollBatch(
        farmerName: String,
        farmerMobile: String,
        village: String?,
        block: String?,
        district: String?,
        state: String?,
        gpsLocation: String?,
        aadhaarId: String?,
        goats: List<GoatDraft>,
        paymentMode: String,
        amount: Double,
    ) {
        viewModelScope.launch {
            _submit.value = SubmitState.Submitting
            val done = mutableListOf<EnrollGoatResponse>()
            try {
                // Vaccination dates are the same for the batch; the selections are per goat.
                val cal = java.util.Calendar.getInstance()
                fun isoOf(c: java.util.Calendar) = String.format(
                    "%04d-%02d-%02d", c.get(java.util.Calendar.YEAR),
                    c.get(java.util.Calendar.MONTH) + 1, c.get(java.util.Calendar.DAY_OF_MONTH)
                )
                val todayIso = isoOf(cal)
                cal.add(java.util.Calendar.MONTH, 6)
                val boosterIso = isoOf(cal)
                fun vaccinesFor(g: GoatDraft): List<VaccineIn> = listOf(
                    "ppr" to g.pprGiven, "et_tt" to g.etttGiven,
                    "fmd" to g.fmdGiven, "goat_pox" to g.poxGiven
                ).map { (type, given) ->
                    if (given) VaccineIn(vaccineType = type, status = "done",
                        vaccinationDate = todayIso, nextVaccinationDate = boosterIso)
                    else VaccineIn(vaccineType = type, status = "pending", nextVaccinationDate = todayIso)
                }

                val types = listOf("left", "right", "face", "ear_tag")
                goats.forEachIndexed { index, goat ->
                    _progress.value = "Enrolling goat ${index + 1} of ${goats.size}…"
                    val photos = goat.photos.mapIndexedNotNull { i, uri ->
                        val bytes = uploader.readBytes(uri) ?: return@mapIndexedNotNull null
                        val url = repo.uploadPhoto(bytes, "goat_${System.currentTimeMillis()}_${index}_$i.jpg")
                        GoatPhotoIn(photoType = types.getOrElse(i) { "face" }, url = url)
                    }
                    if (photos.size < 4) {
                        _submit.value = SubmitState.Error("Goat ${goat.earTag}: please capture all 4 photos.")
                        return@launch
                    }
                    val request = EnrollGoatRequest(
                        farmerName = farmerName, farmerMobile = farmerMobile,
                        village = village, block = block, district = district, state = state,
                        gpsLocation = gpsLocation, aadhaarId = aadhaarId,
                        earTagNumber = goat.earTag, breed = goat.breed, gender = goat.genderRaw,
                        ageMonths = goat.ageMonths, weightKg = goat.weightKg, color = goat.color.ifBlank { null },
                        photos = photos, vaccines = vaccinesFor(goat),
                        paymentMode = paymentMode, amount = amount,
                        receiptNumber = "RCP-${System.currentTimeMillis()}-$index",
                    )
                    val res = repo.enrollGoat(request)
                    if (res.status != "success") {
                        _submit.value = SubmitState.Error("Goat ${goat.earTag}: ${res.reason ?: "enrollment failed"}")
                        return@launch
                    }
                    done.add(res)
                }
                _results.value = done
                _progress.value = null
                _submit.value = SubmitState.Success("Enrolled ${done.size} goat(s)")
            } catch (e: Exception) {
                _submit.value = SubmitState.Error(e.message ?: "Enrollment failed")
            }
        }
    }
}

/** One goat collected during multi-goat enrollment, before the batch is submitted. */
data class GoatDraft(
    val breed: String,
    val genderRaw: String,      // "male" | "female" (backend value)
    val genderLabel: String,    // localized label for the summary card
    val ageMonths: Int,
    val ageLabel: String,       // e.g. "12M" / "2Y" for the summary card
    val weightKg: Double,
    val weightLabel: String,    // e.g. "25 Kg"
    val color: String,
    val earTag: String,
    val photos: List<android.net.Uri>,
    // Vaccination is captured per goat (step 4, before the goat is added to the list).
    val pprGiven: Boolean = true,
    val etttGiven: Boolean = true,
    val fmdGiven: Boolean = false,
    val poxGiven: Boolean = false,
)

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

    /** Silent re-fetch (no Loading flash) so the dashboard counts stay right when the
     *  screen returns to the foreground — e.g. straight after a death report. */
    fun refresh() {
        viewModelScope.launch {
            repo.safeCall { farmerPolicies() }.onSuccess { _policies.value = UiState.Success(it) }
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

    fun reportDeath(goatId: Int, dateIso: String, cause: String? = null) {
        viewModelScope.launch {
            _submit.value = SubmitState.Submitting
            // New workflow: farmer report -> Didi review. Notifies the Didi.
            repo.safeCall { mortalityReport(MortalityReportCreateRequest(goatId, dateIso, cause)) }
                .onSuccess {
                    if (it.status == "success") { _submit.value = SubmitState.Success("Death reported to your Didi"); load() }
                    else _submit.value = SubmitState.Error(it.reason ?: "Failed to report")
                }
                .onFailure { _submit.value = SubmitState.Error(it.message ?: "Failed to report") }
        }
    }

    fun resetSubmit() { _submit.value = SubmitState.Idle }
}

/** Didi-facing queue of farmer-reported goat deaths + the complete->claim handoff. */
@HiltViewModel
class MortalityQueueViewModel @Inject constructor(
    private val repo: Repository,
    private val photoUploader: PhotoUploader,
) : ViewModel() {
    private val _list = MutableStateFlow<UiState<List<MortalityReportItem>>>(UiState.Loading)
    val list = _list.asStateFlow()

    private val _detail = MutableStateFlow<UiState<MortalityReportDetail>>(UiState.Loading)
    val detail = _detail.asStateFlow()

    // Success carries the claim number to deep-link into the existing claim section.
    private val _complete = MutableStateFlow<SubmitState>(SubmitState.Idle)
    val complete = _complete.asStateFlow()

    fun loadList() {
        viewModelScope.launch {
            _list.value = UiState.Loading
            repo.safeCall { mortalityReports() }
                .onSuccess { _list.value = UiState.Success(it.reports) }
                .onFailure { _list.value = UiState.Error(it.message ?: "Failed to load reports") }
        }
    }

    fun loadDetail(id: Int) {
        viewModelScope.launch {
            _detail.value = UiState.Loading
            repo.safeCall { mortalityReportDetail(id) }
                .onSuccess { _detail.value = UiState.Success(it) }
                .onFailure { _detail.value = UiState.Error(it.message ?: "Failed to load report") }
        }
    }

    /**
     * Completes the report: uploads the carcass photo, saves the Didi's review
     * (cause, notes, site visit + carcass verification), then hands off to the claim.
     *
     * The screen only enables this once cause, photo and the verification tick are
     * all present, so a failure here is a transport problem, not a validation one.
     */
    fun completeReport(
        id: Int, cause: String, notes: String?,
        goatPhoto: Uri, tagPhoto: Uri, sideVisitPhoto: Uri, documentPhoto: Uri,
        siteVisitDone: Boolean,
    ) {
        viewModelScope.launch {
            _complete.value = SubmitState.Submitting

            // Upload all four carcass photos first: the review call stores their URLs,
            // so a failed upload must not leave the report completed without evidence.
            // Each slot maps to the carcass photo type the claim screen renders.
            val slots = listOf(
                "full_body" to goatPhoto,
                "ear_tag" to tagPhoto,
                "close_up" to sideVisitPhoto,
                "location" to documentPhoto,
            )
            val photos = mutableListOf<MortalityPhotoIn>()
            for ((type, uri) in slots) {
                val bytes = photoUploader.readBytes(uri)
                if (bytes == null) {
                    _complete.value = SubmitState.Error("Could not read a photo. Capture it again.")
                    return@launch
                }
                val url = runCatching { repo.uploadPhoto(bytes, "carcass_${id}_${type}_${System.currentTimeMillis()}.jpg") }.getOrNull()
                if (url == null) {
                    _complete.value = SubmitState.Error("Photo upload failed. Check your connection and retry.")
                    return@launch
                }
                photos.add(MortalityPhotoIn(type, url))
            }

            val review = repo.safeCall {
                mortalityReview(id, MortalityReviewRequest(
                    causeOfDeath = cause, notes = notes,
                    siteVisitDone = siteVisitDone, carcassVerified = true,
                    photos = photos,
                ))
            }
            if (review.isFailure) {
                _complete.value = SubmitState.Error(
                    review.exceptionOrNull()?.message ?: "Could not save the review")
                return@launch
            }

            repo.safeCall { mortalityComplete(id) }
                .onSuccess {
                    if (it.status == "success" && it.claimNumber != null) {
                        _complete.value = SubmitState.Success(it.claimNumber)  // claim number for handoff
                    } else {
                        _complete.value = SubmitState.Error(it.reason ?: "Could not create claim")
                    }
                }
                .onFailure { _complete.value = SubmitState.Error(it.message ?: "Failed to complete") }
        }
    }

    fun resetComplete() { _complete.value = SubmitState.Idle }
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

    /** Performance Overview chart: last 7 days of activity. */
    private val _performance = MutableStateFlow<UiState<PerformanceResponse>>(UiState.Loading)
    val performance = _performance.asStateFlow()

    private val _team = MutableStateFlow<List<TeamMember>>(emptyList())
    val team = _team.asStateFlow()

    private val _clusters = MutableStateFlow<List<ClusterDto>>(emptyList())
    val clusters = _clusters.asStateFlow()

    init { load() }

    /** Re-fetch without flashing the loading state - for returning to the screen. */
    fun refresh() = load(showLoading = false)

    fun load(showLoading: Boolean = true) {
        viewModelScope.launch {
            if (showLoading) _dashboard.value = UiState.Loading
            repo.safeCall { coDashboard() }
                .onSuccess { _dashboard.value = UiState.Success(it) }
                .onFailure { _dashboard.value = UiState.Error(it.message ?: "Failed to load dashboard") }
            if (showLoading) _performance.value = UiState.Loading
            repo.safeCall { coPerformance() }
                .onSuccess { _performance.value = UiState.Success(it) }
                .onFailure { _performance.value = UiState.Error(it.message ?: "Failed to load activity") }
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

    /** Same claim detail, fetched via the SD-scoped endpoint (for the Didi Claim Details screen). */
    fun loadSdReview(claimNumber: String) {
        viewModelScope.launch {
            _review.value = UiState.Loading
            repo.safeCall { sdClaimReview(claimNumber) }
                .onSuccess { _review.value = UiState.Success(it) }
                .onFailure { _review.value = UiState.Error(it.message ?: "Failed to load claim") }
        }
    }

    /** Same claim detail, fetched via the farmer-scoped endpoint (Farmer Claim Details). */
    fun loadFarmerReview(claimNumber: String) {
        viewModelScope.launch {
            _review.value = UiState.Loading
            repo.safeCall { farmerClaimReview(claimNumber) }
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
