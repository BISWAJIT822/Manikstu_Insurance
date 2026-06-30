package com.manikshu.goatinsurance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/** Generic async UI state. */
sealed class UiState<out T> {
    object Idle : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

private fun errMsg(e: Throwable, fallback: String = "Something went wrong"): String = when (e) {
    is retrofit2.HttpException -> when (e.code()) {
        401 -> "Session expired. Please login again."
        403 -> "Not allowed for this role."
        404 -> "Not found."
        429 -> "Too many requests, slow down."
        else -> "Server error (${e.code()})"
    }
    is java.net.ConnectException, is java.net.UnknownHostException ->
        "Cannot reach server. Is the backend running?"
    else -> e.message ?: fallback
}

// ==================== Suraksha Didi: dashboard ====================
@HiltViewModel
class SdDashboardViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    private val _state = MutableStateFlow<UiState<SdDashboard>>(UiState.Idle)
    val state = _state.asStateFlow()

    fun load() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            _state.value = try {
                UiState.Success(repo.sdDashboard())
            } catch (e: Exception) { UiState.Error(errMsg(e)) }
        }
    }
}

// ==================== Goats: list + detail ====================
@HiltViewModel
class GoatViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    private val _list = MutableStateFlow<UiState<GoatListResponse>>(UiState.Idle)
    val list = _list.asStateFlow()

    private val _detail = MutableStateFlow<UiState<Map<String, kotlinx.serialization.json.JsonElement>>>(UiState.Idle)
    val detail = _detail.asStateFlow()

    fun loadGoats(tab: String = "all", search: String? = null) {
        viewModelScope.launch {
            _list.value = UiState.Loading
            _list.value = try {
                UiState.Success(repo.sdGoats(tab = tab, search = search?.ifBlank { null }))
            } catch (e: Exception) { UiState.Error(errMsg(e)) }
        }
    }

    fun loadDetail(id: Int) {
        viewModelScope.launch {
            _detail.value = UiState.Loading
            _detail.value = try {
                UiState.Success(repo.sdGoatDetail(id))
            } catch (e: Exception) { UiState.Error(errMsg(e)) }
        }
    }
}

// ==================== Enrollment ====================
@HiltViewModel
class EnrollmentViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    private val _state = MutableStateFlow<UiState<EnrollGoatResponse>>(UiState.Idle)
    val state = _state.asStateFlow()

    fun submit(req: EnrollGoatRequest) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            _state.value = try {
                val res = repo.enrollGoat(req)
                if (res.status == "success") UiState.Success(res)
                else UiState.Error(res.reason ?: "Enrollment failed")
            } catch (e: Exception) { UiState.Error(errMsg(e)) }
        }
    }

    fun reset() { _state.value = UiState.Idle }
}

// ==================== Vaccination ====================
@HiltViewModel
class VaccinationViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    private val _list = MutableStateFlow<UiState<VaccinationListResponse>>(UiState.Idle)
    val list = _list.asStateFlow()

    private val _record = MutableStateFlow<UiState<StatusResponse>>(UiState.Idle)
    val record = _record.asStateFlow()

    fun load(filter: String = "all") {
        viewModelScope.launch {
            _list.value = UiState.Loading
            _list.value = try {
                UiState.Success(repo.sdVaccinations(filter))
            } catch (e: Exception) { UiState.Error(errMsg(e)) }
        }
    }

    fun record(req: RecordVaccinationRequest) {
        viewModelScope.launch {
            _record.value = UiState.Loading
            _record.value = try {
                val res = repo.recordVaccination(req)
                if (res.status == "success") UiState.Success(res) else UiState.Error(res.reason ?: "Failed")
            } catch (e: Exception) { UiState.Error(errMsg(e)) }
        }
    }

    fun resetRecord() { _record.value = UiState.Idle }
}

// ==================== Mortality ====================
@HiltViewModel
class MortalityViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    private val _state = MutableStateFlow<UiState<MortalityIdResponse>>(UiState.Idle)
    val state = _state.asStateFlow()

    fun report(req: ReportMortalityRequest) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            _state.value = try {
                val res = repo.reportMortality(req)
                if (res.status == "success") UiState.Success(res) else UiState.Error(res.reason ?: "Failed")
            } catch (e: Exception) { UiState.Error(errMsg(e)) }
        }
    }

    fun reset() { _state.value = UiState.Idle }
}

// ==================== Claims (coordinator) ====================
@HiltViewModel
class ClaimViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    private val _list = MutableStateFlow<UiState<ClaimListResponse>>(UiState.Idle)
    val list = _list.asStateFlow()

    private val _detail = MutableStateFlow<UiState<Map<String, kotlinx.serialization.json.JsonElement>>>(UiState.Idle)
    val detail = _detail.asStateFlow()

    private val _review = MutableStateFlow<UiState<StatusResponse>>(UiState.Idle)
    val review = _review.asStateFlow()

    fun loadClaims(status: String? = null) {
        viewModelScope.launch {
            _list.value = UiState.Loading
            _list.value = try {
                UiState.Success(repo.coClaims(status = status))
            } catch (e: Exception) { UiState.Error(errMsg(e)) }
        }
    }

    fun loadDetail(claimNumber: String) {
        viewModelScope.launch {
            _detail.value = UiState.Loading
            _detail.value = try {
                UiState.Success(repo.coClaimReview(claimNumber))
            } catch (e: Exception) { UiState.Error(errMsg(e)) }
        }
    }

    fun review(claimNumber: String, action: String, amount: Double? = null) {
        viewModelScope.launch {
            _review.value = UiState.Loading
            _review.value = try {
                val res = repo.reviewClaim(ReviewClaimRequest(claimNumber, action, amount))
                if (res.status == "success") UiState.Success(res) else UiState.Error(res.reason ?: "Failed")
            } catch (e: Exception) { UiState.Error(errMsg(e)) }
        }
    }

    fun resetReview() { _review.value = UiState.Idle }
}

// ==================== Coordinator ====================
@HiltViewModel
class CoordinatorViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    private val _dashboard = MutableStateFlow<UiState<CoDashboard>>(UiState.Idle)
    val dashboard = _dashboard.asStateFlow()

    private val _activity = MutableStateFlow<UiState<List<ActivityItem>>>(UiState.Idle)
    val activity = _activity.asStateFlow()

    private val _clusters = MutableStateFlow<UiState<List<ClusterDto>>>(UiState.Idle)
    val clusters = _clusters.asStateFlow()

    private val _reports = MutableStateFlow<UiState<ReportsResponse>>(UiState.Idle)
    val reports = _reports.asStateFlow()

    private val _team = MutableStateFlow<UiState<TeamListResponse>>(UiState.Idle)
    val team = _team.asStateFlow()

    fun loadDashboard() {
        viewModelScope.launch {
            _dashboard.value = UiState.Loading
            _dashboard.value = try { UiState.Success(repo.coDashboard()) } catch (e: Exception) { UiState.Error(errMsg(e)) }
        }
    }

    fun loadActivity() {
        viewModelScope.launch {
            _activity.value = UiState.Loading
            _activity.value = try { UiState.Success(repo.coLiveActivity()) } catch (e: Exception) { UiState.Error(errMsg(e)) }
        }
    }

    fun loadClusters() {
        viewModelScope.launch {
            _clusters.value = UiState.Loading
            _clusters.value = try { UiState.Success(repo.coClusterMap()) } catch (e: Exception) { UiState.Error(errMsg(e)) }
        }
    }

    fun loadReports(tab: String = "summary") {
        viewModelScope.launch {
            _reports.value = UiState.Loading
            _reports.value = try { UiState.Success(repo.coReports(tab = tab)) } catch (e: Exception) { UiState.Error(errMsg(e)) }
        }
    }

    fun loadTeam() {
        viewModelScope.launch {
            _team.value = UiState.Loading
            _team.value = try { UiState.Success(repo.coTeam()) } catch (e: Exception) { UiState.Error(errMsg(e)) }
        }
    }
}

// ==================== Farmer ====================
@HiltViewModel
class FarmerViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    private val _policies = MutableStateFlow<UiState<MyPoliciesResponse>>(UiState.Idle)
    val policies = _policies.asStateFlow()

    private val _schedule = MutableStateFlow<UiState<List<VaccinationScheduleItem>>>(UiState.Idle)
    val schedule = _schedule.asStateFlow()

    private val _report = MutableStateFlow<UiState<StatusResponse>>(UiState.Idle)
    val report = _report.asStateFlow()

    fun loadPolicies() {
        viewModelScope.launch {
            _policies.value = UiState.Loading
            _policies.value = try { UiState.Success(repo.farmerPolicies()) } catch (e: Exception) { UiState.Error(errMsg(e)) }
        }
    }

    fun loadSchedule() {
        viewModelScope.launch {
            _schedule.value = UiState.Loading
            _schedule.value = try { UiState.Success(repo.farmerVaccinationSchedule()) } catch (e: Exception) { UiState.Error(errMsg(e)) }
        }
    }

    fun reportDeath(req: ReportDeathRequest) {
        viewModelScope.launch {
            _report.value = UiState.Loading
            _report.value = try {
                val res = repo.farmerReportDeath(req)
                if (res.status == "success") UiState.Success(res) else UiState.Error(res.reason ?: "Failed")
            } catch (e: Exception) { UiState.Error(errMsg(e)) }
        }
    }

    fun resetReport() { _report.value = UiState.Idle }
}

// ==================== Profile (shared) ====================
@HiltViewModel
class ProfileViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    private val _state = MutableStateFlow<UiState<ProfileResponse>>(UiState.Idle)
    val state = _state.asStateFlow()

    fun load() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            _state.value = try { UiState.Success(repo.profile()) } catch (e: Exception) { UiState.Error(errMsg(e)) }
        }
    }

    fun logout(onDone: () -> Unit) {
        viewModelScope.launch {
            try { repo.logout() } catch (_: Exception) {}
            onDone()
        }
    }
}
