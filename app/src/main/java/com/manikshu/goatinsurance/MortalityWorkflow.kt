package com.manikshu.goatinsurance

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

private val MortalityRed = Color(0xFFD32F2F)

private fun prettyStatus(s: String) = s.replace('_', ' ').replaceFirstChar { it.uppercase() }

/** Didi-facing queue of farmer-reported goat deaths pending review. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MortalityQueueScreen(navController: androidx.navigation.NavHostController, onBack: () -> Unit) {
    val vm: MortalityQueueViewModel = hiltViewModel()
    val state by vm.list.collectAsState()
    LaunchedEffect(Unit) { vm.loadList() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mortality Reports", fontWeight = FontWeight.Bold, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryGreen)
            )
        },
        containerColor = Color(0xFFF8F9F5)
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            when (val s = state) {
                is UiState.Loading -> CircularProgressIndicator(
                    color = PrimaryGreen, modifier = Modifier.align(Alignment.Center))
                is UiState.Error -> Text(s.message, color = Color.Gray,
                    modifier = Modifier.align(Alignment.Center).padding(24.dp))
                is UiState.Success -> {
                    if (s.data.isEmpty()) {
                        Text("No death reports pending review.", color = Color.Gray,
                            modifier = Modifier.align(Alignment.Center).padding(24.dp))
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(s.data) { r ->
                                MortalityReportCard(r) { navController.navigate("mortality_detail/${r.id}") }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MortalityReportCard(r: MortalityReportItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.4f))
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.size(44.dp).clip(CircleShape).background(MortalityRed.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) { Icon(Icons.Default.Warning, null, tint = MortalityRed, modifier = Modifier.size(22.dp)) }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(r.earTagNumber, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)
                Text("Farmer: ${r.farmerName}", fontSize = 13.sp, color = Color.Gray)
                Text("Died: ${r.dateOfDeath.take(10)}", fontSize = 12.sp, color = Color.Gray)
            }
            Surface(color = MortalityRed.copy(alpha = 0.12f), shape = RoundedCornerShape(8.dp)) {
                Text(prettyStatus(r.status), color = MortalityRed, fontSize = 11.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
            }
        }
    }
}

/** Mortality detail page where the Didi completes the report and hands off to the claim. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MortalityDetailScreen(
    navController: androidx.navigation.NavHostController,
    reportId: Int,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val vm: MortalityQueueViewModel = hiltViewModel()
    val state by vm.detail.collectAsState()
    val complete by vm.complete.collectAsState()
    LaunchedEffect(reportId) { vm.loadDetail(reportId) }

    var cause by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var seeded by remember { mutableStateOf(false) }
    (state as? UiState.Success)?.data?.let { d ->
        if (!seeded) { cause = d.causeOfDeath ?: ""; notes = d.notes ?: ""; seeded = true }
    }

    LaunchedEffect(complete) {
        when (val c = complete) {
            is SubmitState.Success -> {
                val claimNumber = c.message  // claim number returned by backend
                vm.resetComplete()
                if (!claimNumber.isNullOrBlank()) {
                    // Hand off into the EXISTING claim section, unchanged.
                    navController.navigate("claim_review/$claimNumber") {
                        popUpTo("mortality_queue") { inclusive = false }
                    }
                }
            }
            is SubmitState.Error -> {
                Toast.makeText(context, c.message, Toast.LENGTH_LONG).show(); vm.resetComplete()
            }
            else -> {}
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mortality Report", fontWeight = FontWeight.Bold, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryGreen)
            )
        },
        containerColor = Color(0xFFF8F9F5)
    ) { padding ->
        when (val s = state) {
            is UiState.Loading -> Box(Modifier.padding(padding).fillMaxSize(), Alignment.Center) {
                CircularProgressIndicator(color = PrimaryGreen)
            }
            is UiState.Error -> Box(Modifier.padding(padding).fillMaxSize(), Alignment.Center) {
                Text(s.message, color = Color.Gray)
            }
            is UiState.Success -> {
                val d = s.data
                val alreadyClaimed = d.status == "claim_initiated" || d.claimNumber != null
                Column(
                    modifier = Modifier.padding(padding).fillMaxSize()
                        .verticalScroll(rememberScrollState()).padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.4f))
                    ) {
                        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            DetailRow("Goat", d.earTagNumber)
                            d.breed?.let { DetailRow("Breed", it) }
                            DetailRow("Farmer", d.farmerName)
                            d.farmerMobile?.let { DetailRow("Mobile", it) }
                            d.village?.let { DetailRow("Village", it) }
                            DetailRow("Date of death", d.dateOfDeath.take(16).replace("T", " "))
                            DetailRow("Status", prettyStatus(d.status))
                            d.claimNumber?.let { DetailRow("Claim", it) }
                        }
                    }

                    OutlinedTextField(
                        value = cause, onValueChange = { cause = it },
                        label = { Text("Cause of death") },
                        enabled = !alreadyClaimed,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryGreen)
                    )
                    OutlinedTextField(
                        value = notes, onValueChange = { notes = it },
                        label = { Text("Verification notes") },
                        enabled = !alreadyClaimed,
                        minLines = 3,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryGreen)
                    )

                    if (alreadyClaimed) {
                        Button(
                            onClick = { d.claimNumber?.let { navController.navigate("claim_review/$it") } },
                            enabled = d.claimNumber != null,
                            modifier = Modifier.fillMaxWidth().height(56.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
                        ) { Text("Go to Claim", fontWeight = FontWeight.Bold, fontSize = 16.sp) }
                    } else {
                        Button(
                            onClick = { vm.completeReport(d.id, cause, notes) },
                            enabled = complete !is SubmitState.Submitting,
                            modifier = Modifier.fillMaxWidth().height(56.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
                        ) {
                            Text(
                                if (complete is SubmitState.Submitting) "Processing…" else "Complete Mortality Report",
                                fontWeight = FontWeight.Bold, fontSize = 16.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = Color.Gray, fontSize = 13.sp)
        Text(value, color = Color.Black, fontSize = 13.sp, fontWeight = FontWeight.Medium)
    }
}
