package com.manikshu.goatinsurance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class VaccinationReminder(
    val vaccineName: String,
    val dueDate: String,
    val tagId: String
)

@HiltViewModel
class FarmerViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _policies = MutableStateFlow<List<Policy>>(emptyList())
    val policies: StateFlow<List<Policy>> = _policies.asStateFlow()

    private val _nextVaccination = MutableStateFlow<VaccinationReminder?>(null)
    val nextVaccination: StateFlow<VaccinationReminder?> = _nextVaccination.asStateFlow()

    init {
        loadDashboardData()
    }

    private fun loadDashboardData() {
        viewModelScope.launch {
            // Mock data for UI development
            _policies.value = listOf(
                Policy(
                    policyNumber = "ET-2400501-0001",
                    goatId = "TAG-001",
                    issueDate = "01 June 2024",
                    endDate = "31 May 2025",
                    sumInsured = 8000,
                    status = "Active"
                )
            )
            _nextVaccination.value = VaccinationReminder(
                vaccineName = "PPR Vaccine",
                dueDate = "15 Aug 2026",
                tagId = "ET-0001"
            )
        }
    }
}
