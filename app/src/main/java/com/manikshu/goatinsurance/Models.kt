package com.manikshu.goatinsurance

import kotlinx.serialization.Serializable

enum class UserRole {
    SURAKSHA_DIDI, FARMER, COORDINATOR
}

@Serializable
data class User(
    val id: String,
    val name: String,
    val phone: String,
    val role: UserRole,
    val village: String? = null
)

@Serializable
data class Farmer(
    val id: String,
    val name: String,
    val phone: String,
    val aadhaar: String,
    val village: String,
    val block: String,
    val district: String,
    val bankAccount: String,
    val ifsc: String,
    val photoUrl: String,
    val latitude: Double,
    val longitude: Double
)

@Serializable
data class Goat(
    val id: String,
    val earTag: String,
    val breed: String,
    val gender: String,
    val ageMonths: Int,
    val weightKg: Double,
    val colour: String,
    val photoUrls: List<String>,
    val farmerId: String,
    val status: String // Enrolled, Tagged, Vaccinated, Claimed
)

@Serializable
data class Vaccination(
    val id: String,
    val goatId: String,
    val type: String, // PPR, ET_TT, FMD, GOAT_POX
    val date: String,
    val nextDueDate: String,
    val batchNumber: String,
    val status: String
)

@Serializable
data class Policy(
    val policyNumber: String,
    val goatId: String,
    val issueDate: String,
    val endDate: String,
    val sumInsured: Int,
    val status: String
)

@Serializable
data class Premium(
    val receiptNumber: String,
    val goatId: String,
    val amount: Int = 350,
    val paymentMode: String,
    val date: String
)

@Serializable
data class Claim(
    val claimNumber: String,
    val goatId: String,
    val dateOfDeath: String,
    val cause: String,
    val vaccinationCount: Int,
    val tierAmount: Int,
    val status: String, // PendingVerification, UnderReview, Approved, Rejected, Hold, Paid
    val photoUrls: List<String>,
    val latitude: Double,
    val longitude: Double
)

data class DashboardStats(
    val enrolledCount: Int,
    val earnings: Int,
    val pendingClaims: Int,
    val upcomingVaccinations: Int
)
