package com.manikshu.goatinsurance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.net.Uri

data class DeathNotificationFormState(
    val selectedGoat: Goat? = null,
    val dateTime: String = "15 Jun 2024 at 09:30 AM",
    val photoUri: Uri? = null,
    val isConfirmed: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

@HiltViewModel
class DeathNotificationViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _formState = MutableStateFlow(DeathNotificationFormState())
    val formState: StateFlow<DeathNotificationFormState> = _formState.asStateFlow()

    private val _availableGoats = MutableStateFlow<List<Goat>>(emptyList())
    val availableGoats: StateFlow<List<Goat>> = _availableGoats.asStateFlow()

    init {
        loadGoats()
    }

    private fun loadGoats() {
        viewModelScope.launch {
            try {
                // Mocking available goats for UI development
                _availableGoats.value = listOf(
                    Goat(
                        id = "TAG-001",
                        earTag = "ET-2400501-0001",
                        breed = "Barbari",
                        gender = "Female",
                        ageMonths = 12,
                        weightKg = 15.0,
                        colour = "Brown",
                        photoUrls = emptyList(),
                        farmerId = "FARM-123",
                        status = "Enrolled"
                    )
                )
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun onGoatSelected(goat: Goat) {
        _formState.value = _formState.value.copy(selectedGoat = goat)
    }

    fun onDateTimeChanged(dateTime: String) {
        _formState.value = _formState.value.copy(dateTime = dateTime)
    }

    fun onPhotoUploaded(uri: Uri?) {
        _formState.value = _formState.value.copy(photoUri = uri)
    }

    fun onConfirmationChanged(confirmed: Boolean) {
        _formState.value = _formState.value.copy(isConfirmed = confirmed)
    }

    fun submitReport() {
        val current = _formState.value
        if (current.selectedGoat == null || !current.isConfirmed) return

        viewModelScope.launch {
            _formState.value = _formState.value.copy(isLoading = true)
            try {
                // Mock API call
                // repository.reportDeath(...)
                _formState.value = _formState.value.copy(isLoading = false, isSuccess = true)
            } catch (e: Exception) {
                _formState.value = _formState.value.copy(isLoading = false, error = e.message)
            }
        }
    }
}
