package com.manikshu.goatinsurance

import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import java.io.File
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.material.icons.automirrored.filled.Send
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import java.util.Calendar

private val MortalityRed = Color(0xFFD32F2F)
private val LightGreenBg = Color(0xFFF1F8E9)
private val CardBorderColor = Color(0xFFE0E0E0)

private fun prettyStatus(s: String) = s.replace('_', ' ').replaceFirstChar { it.uppercase() }

/** Didi-facing queue of farmer-reported goat deaths pending review.
 * Grouped by farmer as per the new design. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MortalityQueueScreen(navController: androidx.navigation.NavHostController, onBack: () -> Unit) {
    val vm: MortalityQueueViewModel = hiltViewModel()
    val state by vm.list.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    // Which farmer's goat list is open. Only one at a time so the list stays short.
    var expandedFarmerId by rememberSaveable { mutableStateOf<Int?>(null) }
    val languageState = LocalAppLanguage.current

    LaunchedEffect(Unit) { vm.loadList() }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(languageState.value.getT("Report Death", "मृत्यु रिपोर्ट", "ମୃତ୍ୟୁ ରିପୋର୍ଟ"), fontWeight = FontWeight.Bold, fontSize = 20.sp)
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color(0xFF14231A),
                    navigationIconContentColor = Color(0xFF14231A)
                )
            )
        },
        containerColor = PageBackground
    ) { padding ->
      Box(Modifier.fillMaxSize()) {
        val navInset = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
        Column(modifier = Modifier.padding(top = padding.calculateTopPadding(), bottom = 74.dp + navInset).fillMaxSize()) {
            // Important Note Card
            Card(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD).copy(alpha = 0.5f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.Top) {
                    Icon(Icons.Default.Info, null, tint = Color(0xFF1976D2), modifier = Modifier.size(24.dp))
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(languageState.value.getT("Important Note", "महत्वपूर्ण नोट", "ଗୁରୁତ୍ୱପୂର୍ଣ୍ଣ ସୂଚନା"), fontWeight = FontWeight.Bold, color = Color(0xFF1976D2), fontSize = 14.sp)
                        Text(
                            languageState.value.getT(
                                "Choose a farmer to view how many goat deaths have been reported.",
                                "यह देखने के लिए किसान चुनें कि कितनी बकरियों की मृत्यु की सूचना दी गई है।",
                                "କେତେ ଛେଳିର ମୃତ୍ୟୁ ରିପୋର୍ଟ କରାଯାଇଛି ଦେଖିବା ପାଇଁ କୃଷକ ବାଛନ୍ତୁ |"
                            ),
                            fontSize = 12.sp, color = Color.DarkGray
                        )
                    }
                    Spacer(Modifier.weight(1f))
                    Icon(painterResource(R.drawable.ic_ewe_custom), null, tint = Color(0xFF1976D2).copy(alpha = 0.2f), modifier = Modifier.size(40.dp))
                }
            }

            // Search Bar + Filter
            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text(languageState.value.getT("Search by Farmer Name / Mobile / Village", "किसान का नाम / मोबाइल / गांव से खोजें", "କୃଷକଙ୍କ ନାମ / ମୋବାଇଲ୍ / ଗ୍ରାମ ଅନୁସାରେ ଖୋଜନ୍ତୁ"), fontSize = 12.sp) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray) },
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White, unfocusedContainerColor = Color.White,
                        unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
                        focusedTextColor = Color(0xFF14231A),
                        unfocusedTextColor = Color(0xFF14231A),
                        cursorColor = PrimaryGreen
                    )
                )
            }

            Box(modifier = Modifier.fillMaxSize()) {
                when (val s = state) {
                    is UiState.Loading -> CircularProgressIndicator(
                        color = PrimaryGreen, modifier = Modifier.align(Alignment.Center))
                    is UiState.Error -> Text(s.message, color = Color.Gray,
                        modifier = Modifier.align(Alignment.Center).padding(24.dp))
                    is UiState.Success -> {
                        // Group reports by farmer.
                        val grouped = s.data.groupBy { it.farmerId ?: 0 }
                        val filteredFarmers = grouped.filter { (_, reports) ->
                            val f = reports.first()
                            searchQuery.isBlank() ||
                                f.farmerName.contains(searchQuery, ignoreCase = true) ||
                                (f.earTagNumber).contains(searchQuery, ignoreCase = true)
                        }

                        if (filteredFarmers.isEmpty()) {
                            Text(
                                languageState.value.getT("No records found.", "कोई रिकॉर्ड नहीं मिला।", "କୌଣସି ରେକର୍ଡ ମିଳିଲା ନାହିଁ |"),
                                color = Color.Gray,
                                modifier = Modifier.align(Alignment.Center).padding(24.dp)
                            )
                        } else {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(filteredFarmers.values.toList()) { reports ->
                                    val farmerId = reports.first().farmerId ?: 0
                                    FarmerMortalitySummaryCard(
                                        reports = reports,
                                        expanded = expandedFarmerId == farmerId,
                                        onToggle = {
                                            expandedFarmerId = if (expandedFarmerId == farmerId) null else farmerId
                                        },
                                        onGoatClick = { report ->
                                            navController.navigate("mortality_detail/${report.id}")
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        Box(Modifier.align(Alignment.BottomCenter)) { DidiBottomBar(navController) }
      }
    }
}

@Composable
private fun FarmerMortalitySummaryCard(
    reports: List<MortalityReportItem>,
    expanded: Boolean,
    onToggle: () -> Unit,
    onGoatClick: (MortalityReportItem) -> Unit,
) {
    val languageState = LocalAppLanguage.current
    val f = reports.first()
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.2f))
    ) {
        Column {
        Row(modifier = Modifier.clickable { onToggle() }.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            // Farmer avatar
            Surface(
                modifier = Modifier.size(52.dp),
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFF5F5F5)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Person, null, tint = Color.Gray, modifier = Modifier.size(26.dp))
                }
            }
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(f.farmerName, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black, maxLines = 1)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Phone, null, tint = Color.Gray, modifier = Modifier.size(12.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("+91 98765 43210", color = Color.Gray, fontSize = 12.sp, maxLines = 1) // Mock phone
                }
                Row(verticalAlignment = Alignment.Top) {
                    Icon(Icons.Default.LocationOn, null, tint = Color.Gray, modifier = Modifier.size(12.dp).padding(top = 2.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Jamdihi Village, Block: Jamdihi, Dist: Giridih", color = Color.Gray, fontSize = 11.sp, lineHeight = 15.sp, maxLines = 2) // Mock location
                }
            }
            
            Spacer(modifier = Modifier.width(8.dp))
            
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(languageState.value.getT("Reported Death", "रिपोर्ट की गई मृत्यु", "ମୃତ୍ୟୁ ରିପୋର୍ଟ"), fontSize = 9.sp, color = Color.Gray, maxLines = 1)
                Text("${reports.size}", color = Color.Red, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
                Text(languageState.value.getT("Goats", "बकरियां", "ଛେଳି"), fontSize = 9.sp, color = Color.Gray)
            }
            
            Icon(
                if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.ChevronRight,
                null, tint = PrimaryGreen, modifier = Modifier.size(20.dp)
            )
        }

        if (expanded) {
            HorizontalDivider(color = Color(0xFFF0F0F0))
            reports.forEachIndexed { index, report ->
                if (index > 0) HorizontalDivider(color = Color(0xFFF5F5F5), modifier = Modifier.padding(start = 48.dp))
                ReportedGoatRow(report) { onGoatClick(report) }
            }
        }
        }
    }
}

/** One reported-death goat inside an expanded farmer card. Opens Report Goat Death. */
@Composable
private fun ReportedGoatRow(report: MortalityReportItem, onClick: () -> Unit) {
    val languageState = LocalAppLanguage.current
    Row(
        modifier = Modifier.fillMaxWidth().clickable { onClick() }.padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier.size(30.dp).clip(RoundedCornerShape(9.dp)).background(IconRose.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(painterResource(R.drawable.ic_ewe_custom), null, tint = IconRose, modifier = Modifier.size(16.dp))
        }
        Spacer(Modifier.width(10.dp))
        Column(Modifier.weight(1f)) {
            Text(report.earTagNumber, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color.Black, maxLines = 1)
            Text(
                listOfNotNull(report.breed, report.dateOfDeath.take(10)).joinToString(" · "),
                fontSize = 11.sp, color = Color.Gray, maxLines = 1
            )
        }
        if (report.status == "claim_initiated") {
            Text(
                languageState.value.getT("Claimed", "दावा किया", "ଦାବି ହୋଇଛି"),
                fontSize = 10.sp, color = PrimaryGreen, fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.width(6.dp))
        }
        Icon(Icons.Default.ChevronRight, null, tint = Color(0xFFB0B7B0), modifier = Modifier.size(16.dp))
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
    val languageState = LocalAppLanguage.current
    val vm: MortalityQueueViewModel = hiltViewModel()
    val state by vm.detail.collectAsState()
    val complete by vm.complete.collectAsState()
    LaunchedEffect(reportId) { vm.loadDetail(reportId) }

    var deathDate by remember { mutableStateOf("") }
    var deathTime by remember { mutableStateOf("") }
    var cause by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    
    // Photo state for 4 slots
    var goatPhotoUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var tagPhotoUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var sideVisitPhotoUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var documentPhotoUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    
    var pendingPhotoUri by rememberSaveable { mutableStateOf<String?>(null) }
    var captureSlot by rememberSaveable { mutableStateOf(0) } // 1: Goat, 2: Tag, 3: Side Visit, 4: Document
    
    var siteVisitVerified by rememberSaveable { mutableStateOf(false) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            val uri = pendingPhotoUri?.let { Uri.parse(it) }
            when(captureSlot) {
                1 -> goatPhotoUri = uri
                2 -> tagPhotoUri = uri
                3 -> sideVisitPhotoUri = uri
                4 -> documentPhotoUri = uri
            }
        }
    }
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) pendingPhotoUri?.let { cameraLauncher.launch(Uri.parse(it)) }
        else Toast.makeText(context, languageState.value.getT("Camera permission required", "कैमरा अनुमति आवश्यक है", "କ୍ୟାମେରା ଅନୁମତି ଆବଶ୍ୟକ"), Toast.LENGTH_SHORT).show()
    }

    fun launchCamera(slot: Int) {
        try {
            captureSlot = slot
            val directory = File(context.cacheDir, "mortality_photos")
            if (!directory.exists()) directory.mkdirs()
            val file = File(directory, "mortality_${reportId}_slot${slot}_${System.currentTimeMillis()}.jpg")
            val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
            pendingPhotoUri = uri.toString()
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED
            ) {
                cameraLauncher.launch(uri)
            } else {
                cameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    var seeded by remember { mutableStateOf(false) }
    (state as? UiState.Success)?.data?.let { d ->
        if (!seeded) { 
            cause = d.causeOfDeath ?: ""; notes = d.notes ?: ""
            // Show what the pickers write, not the raw ISO the API returns.
            deathDate = d.dateOfDeath.take(10).split("-").let {
                if (it.size == 3) "${it[2]}/${it[1]}/${it[0]}" else d.dateOfDeath.take(10)
            }
            deathTime = d.dateOfDeath.drop(11).take(5).split(":").let { hm ->
                val h = hm.getOrNull(0)?.toIntOrNull()
                if (h != null && hm.size >= 2) {
                    String.format("%02d:%s %s", if (h % 12 == 0) 12 else h % 12, hm[1], if (h >= 12) "PM" else "AM")
                } else d.dateOfDeath.drop(11).take(5)
            }
            seeded = true 
        }
    }

    LaunchedEffect(complete) {
        when (val c = complete) {
            is SubmitState.Success -> {
                val claimNumber = c.message
                vm.resetComplete()
                if (!claimNumber.isNullOrBlank()) {
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
            CenterAlignedTopAppBar(
                title = {
                    Text(languageState.value.getT("Report Goat Death", "बकरी की मृत्यु की रिपोर्ट", "ଛେଳି ମୃତ୍ୟୁ ରିପୋର୍ଟ"), fontWeight = FontWeight.Bold, fontSize = 18.sp)
                },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color(0xFF14231A),
                    navigationIconContentColor = Color(0xFF14231A)
                )
            )
        },
        containerColor = PageBackground
    ) { padding ->
      Box(Modifier.fillMaxSize()) {
        val navInset = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
        val contentPad = PaddingValues(top = padding.calculateTopPadding(), bottom = 74.dp + navInset)
        when (val s = state) {
            is UiState.Loading -> Box(Modifier.padding(contentPad).fillMaxSize(), Alignment.Center) { CircularProgressIndicator(color = PrimaryGreen) }
            is UiState.Error -> Box(Modifier.padding(contentPad).fillMaxSize(), Alignment.Center) { Text(s.message, color = Color.Gray) }
            is UiState.Success -> {
                val d = s.data
                val alreadyClaimed = d.status == "claim_initiated" || d.claimNumber != null
                
                Column(
                    modifier = Modifier.padding(contentPad).fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Selected Goat Card
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFEDF4E9)),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(1.dp, PrimaryGreen.copy(alpha = 0.35f)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box {
                                    Surface(modifier = Modifier.size(84.dp), shape = CircleShape, color = Color.White) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Icon(painterResource(R.drawable.ic_ewe_custom), null, tint = Color.Gray, modifier = Modifier.size(44.dp))
                                        }
                                    }
                                    Icon(
                                        Icons.Default.CheckCircle, null, 
                                        tint = PrimaryGreen, 
                                        modifier = Modifier.align(Alignment.BottomEnd).size(24.dp).background(Color.White, CircleShape).padding(2.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(16.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(languageState.value.getT("Selected Goat", "चयनित बकरी", "ଚୟନିତ ଛେଳି"), fontSize = 12.sp, color = Color.Gray)
                                    Text(d.earTagNumber, fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Color.Black)
                                    Surface(color = Color.White, shape = RoundedCornerShape(8.dp)) {
                                        Text(d.breed ?: "Black Bengal", modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp), fontSize = 12.sp, color = PrimaryGreen, fontWeight = FontWeight.Bold)
                                    }
                                    Text(languageState.value.getT("Ear Tag: ${d.earTagNumber}", "कान का टैग: ${d.earTagNumber}", "କାନ ଟ୍ୟାଗ୍: ${d.earTagNumber}"), fontSize = 14.sp, color = Color.Gray)
                                }
                            }
                            
                            Spacer(modifier = Modifier.height(20.dp))
                            HorizontalDivider(color = Color(0xFFDCE8D6))
                            Spacer(modifier = Modifier.height(16.dp))
                            
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                MortalityStatItem(Icons.Default.Shield, languageState.value.getT("Sum Insured", "बीमा राशि", "ବୀମା ରାଶି"), "₹ 20,000", Modifier.weight(1f), accent = IconTeal)
                                MortalityStatItem(Icons.Default.Description, languageState.value.getT("Policy No.", "पॉलिसी संख्या", "ପଲିସି ନମ୍ବର"), d.claimNumber ?: "POL/25/07321", Modifier.weight(1f), accent = IconIndigo)
                            }
                        }
                    }

                    // Death Information Section
                    SectionHeader(Icons.AutoMirrored.Filled.Assignment, languageState.value.getT("Death Information", "मृत्यु की जानकारी", "ମୃତ୍ୟୁ ସୂଚନା"), accent = IconRose)
                    
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        MortalityField(
                            label = languageState.value.getT("Date of Death *", "मृत्यु की तारीख *", "ମୃତ୍ୟୁ ତାରିଖ *"),
                            value = deathDate,
                            onValueChange = { deathDate = it },
                            placeholder = "DD/MM/YYYY",
                            icon = Icons.Default.CalendarToday,
                            accent = IconBlue,
                            modifier = Modifier.weight(1f),
                            readOnly = true,
                            onClick = {
                                val cal = Calendar.getInstance()
                                DatePickerDialog(context, { _, y, m, d_ ->
                                    deathDate = String.format("%02d/%02d/%04d", d_, m + 1, y)
                                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
                            }
                        )
                        MortalityField(
                            label = languageState.value.getT("Time of Death *", "मृत्यु का समय *", "ମୃତ୍ୟୁ ସମୟ *"),
                            value = deathTime,
                            onValueChange = { deathTime = it },
                            placeholder = "HH:MM AM/PM",
                            icon = Icons.Default.History,
                            accent = IconPurple,
                            modifier = Modifier.weight(1f),
                            readOnly = true,
                            onClick = {
                                val cal = Calendar.getInstance()
                                TimePickerDialog(context, { _, h, min ->
                                    val amPm = if (h >= 12) "PM" else "AM"
                                    val h_ = if (h % 12 == 0) 12 else h % 12
                                    deathTime = String.format("%02d:%02d %s", h_, min, amPm)
                                }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show()
                            }
                        )
                    }

                    MortalityField(
                        label = languageState.value.getT("Probable Cause of Death *", "मृत्यु का संभावित कारण *", "ମୃତ୍ୟୁର ସମ୍ଭାବ୍ୟ କାରଣ *"),
                        value = cause,
                        onValueChange = { cause = it },
                        placeholder = languageState.value.getT("Enter cause of death", "मृत्यु का कारण दर्ज करें", "ମୃତ୍ୟୁର କାରଣ ଲେଖନ୍ତୁ"),
                        icon = Icons.Default.Warning,
                        accent = IconRose
                    )

                    MortalityField(
                        label = languageState.value.getT("Description (Optional)", "विवरण (वैकल्पिक)", "ବିବରଣୀ (ବୈକଳ୍ପିକ)"),
                        value = notes,
                        onValueChange = { notes = it },
                        placeholder = languageState.value.getT("Provide additional details about the incident", "घटना के बारे में अतिरिक्त विवरण प्रदान करें", "ଘଟଣା ବିଷୟରେ ଅଧିକ ସୂଚନା ଦିଅନ୍ତୁ"),
                        icon = Icons.Default.Description,
                        accent = IconTeal,
                        singleLine = false
                    )

                    // Upload Photos Section
                    SectionHeader(Icons.Default.CameraAlt, languageState.value.getT("Upload Photos (Mandatory)", "फोटो अपलोड करें (अनिवार्य)", "ଫଟୋ ଅପଲୋଡ୍ କରନ୍ତୁ (ବାଧ୍ୟତାମୂଳକ)"), accent = IconAmber)
                    Text(languageState.value.getT("Please upload clear photos for verification", "कृपया सत्यापन के लिए स्पष्ट फोटो अपलोड करें", "ଦୟାକରି ଯାଞ୍ଚ ପାଇଁ ସ୍ପଷ୍ଟ ଫଟୋ ଅପଲୋଡ୍ କରନ୍ତୁ"), fontSize = 12.sp, color = Color.Gray)
                    
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        PhotoSlot(languageState.value.getT("Goat Photo *", "बकरी की फोटो *", "ଛେଳି ଫଟୋ *"), languageState.value.getT("Upload clear photo of the goat", "बकरी की स्पष्ट फोटो अपलोड करें", "ଛେଳିର ସ୍ପଷ୍ଟ ଫଟୋ ଅପଲୋଡ୍ କରନ୍ତୁ"), Icons.Default.Pets, goatPhotoUri, Modifier.weight(1f), accent = IconBlue) { launchCamera(1) }
                        PhotoSlot(languageState.value.getT("Tag Photo *", "टैग की फोटो *", "ଟ୍ୟାଗ୍ ଫଟୋ *"), languageState.value.getT("Upload photo of the tag", "टैग की फोटो अपलोड करें", "ଟ୍ୟାଗ୍ ଫଟୋ ଅପଲୋଡ୍ କରନ୍ତୁ"), Icons.Default.Label, tagPhotoUri, Modifier.weight(1f), accent = IconAmber) { launchCamera(2) }
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        PhotoSlot(languageState.value.getT("Side Visit Photo *", "साइड विजिट फोटो *", "ସାଇଟ୍ ପରିଦର୍ଶନ ଫଟୋ *"), languageState.value.getT("Upload photo of the side visit", "साइड विजिट की फोटो अपलोड करें", "ସାଇଟ୍ ପରିଦର୍ଶନ ଫଟୋ ଅପଲୋଡ୍ କରନ୍ତୁ"), Icons.Default.LocationOn, sideVisitPhotoUri, Modifier.weight(1f), accent = IconRose) { launchCamera(3) }
                        PhotoSlot(languageState.value.getT("Document *", "दस्तावेज़ *", "ଦଲିଲ *"), languageState.value.getT("Upload relevant documents", "प्रासंगिक दस्तावेज़ अपलोड करें", "ପ୍ରାସଙ୍ଗିକ ଦଲିଲ ଅପଲୋଡ୍ କରନ୍ତୁ"), Icons.Default.Description, documentPhotoUri, Modifier.weight(1f), accent = IconIndigo) { launchCamera(4) }
                    }

                    // Verification Confirmation Section
                    SectionHeader(Icons.Default.VerifiedUser, languageState.value.getT("Verification Confirmation", "सत्यापन पुष्टि", "ଯାଞ୍ଚ ନିଶ୍ଚିତକରଣ"), accent = IconGreen)
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f))
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(verticalAlignment = Alignment.Top) {
                                Checkbox(
                                    checked = siteVisitVerified,
                                    onCheckedChange = { siteVisitVerified = it },
                                    colors = CheckboxDefaults.colors(checkedColor = PrimaryGreen)
                                )
                                Text(
                                    text = buildAnnotatedString {
                                        append(languageState.value.getT("I confirm that I have visited the site and verified the death of the goat and the carcass has been disposed of as per guidelines. ", "मैं पुष्टि करता हूं कि मैंने साइट का दौरा किया है और बकरी की मृत्यु को सत्यापित किया है और दिशानिर्देशों के अनुसार शव का निपटान किया गया है। ", "ମୁଁ ନିଶ୍ଚିତ କରୁଛି ଯେ ମୁଁ ସାଇଟ୍ ପରିଦର୍ଶନ କରିଛି ଏବଂ ଛେଳିର ମୃତ୍ୟୁ ଯାଞ୍ଚ କରିଛି ଏବଂ ନିର୍ଦ୍ଦେଶାବଳୀ ଅନୁଯାୟୀ ଶବକୁ ବିସର୍ଜନ କରାଯାଇଛି | "))
                                        withStyle(style = SpanStyle(color = Color.Red)) { append("(Mandatory)") }
                                    },
                                    fontSize = 13.sp, color = Color.Black, modifier = Modifier.padding(top = 10.dp)
                                )
                            }
                            
                            Spacer(modifier = Modifier.height(12.dp))
                            Surface(color = Color(0xFFF9FBE7), shape = RoundedCornerShape(8.dp)) {
                                Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.CheckCircle, null, tint = PrimaryGreen, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = buildAnnotatedString {
                                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append(languageState.value.getT("Note: ", "नोट: ", "ଦ୍ରଷ୍ଟବ୍ୟ: ")) }
                                            append(languageState.value.getT("Providing false information may lead to claim rejection and legal action.", "गलत जानकारी देने से दावा अस्वीकार और कानूनी कार्रवाई हो सकती है।", "ଭୁଲ ସୂଚନା ଦେବା ଦ୍ୱାରା ଦାବି ପ୍ରତ୍ୟାଖ୍ୟାନ ଏବଂ ଆଇନଗତ କାର୍ଯ୍ୟାନୁଷ୍ଠାନ ହୋଇପାରେ |"))
                                        },
                                        fontSize = 12.sp, color = Color.Black
                                    )
                                }
                            }
                        }
                    }

                    Button(
                        onClick = {
                            if (goatPhotoUri != null && tagPhotoUri != null && sideVisitPhotoUri != null && documentPhotoUri != null) {
                                vm.completeReport(d.id, cause.trim(), notes.ifBlank { null }, goatPhotoUri!!, tagPhotoUri!!, sideVisitPhotoUri!!, documentPhotoUri!!, true)
                            }
                        },
                        enabled = !alreadyClaimed && cause.isNotBlank() && goatPhotoUri != null && tagPhotoUri != null && sideVisitPhotoUri != null && documentPhotoUri != null && siteVisitVerified && complete !is SubmitState.Submitting,
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.Send, null, tint = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            if (complete is SubmitState.Submitting) languageState.value.getT("Processing…", "प्रक्रिया जारी है…", "ପ୍ରକ୍ରିୟା ଚାଲିଛି...") 
                            else languageState.value.getT("Submit Report", "रिपोर्ट जमा करें", "ରିପୋର୍ଟ ଦାଖଲ କରନ୍ତୁ"),
                            fontWeight = FontWeight.Bold, fontSize = 16.sp
                        )
                    }
                }
            }
        }
        Box(Modifier.align(Alignment.BottomCenter)) { DidiBottomBar(navController) }
      }
    }
}

@Composable
private fun MortalityStatItem(icon: ImageVector, label: String, value: String, modifier: Modifier, accent: Color = PrimaryGreen) {
    Column(modifier = modifier, horizontalAlignment = Alignment.Start) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = accent, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(label, fontSize = 11.sp, color = Color.Gray)
        }
        Text(value, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = accent, modifier = Modifier.padding(start = 22.dp))
    }
}

@Composable
private fun SectionHeader(icon: ImageVector, title: String, accent: Color = PrimaryGreen) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 8.dp)) {
        Icon(icon, null, tint = accent, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MortalityField(
    label: String, 
    value: String, 
    onValueChange: (String) -> Unit, 
    placeholder: String, 
    icon: ImageVector, 
    accent: Color = Color.Gray,
    trailingIcon: ImageVector? = null,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    onClick: (() -> Unit)? = null
) {
    val styledLabel = buildAnnotatedString {
        label.forEach { char ->
            if (char == '*') withStyle(style = SpanStyle(color = Color.Red)) { append(char) }
            else append(char)
        }
    }
    Column(modifier = modifier) {
        Text(styledLabel, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(bottom = 8.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(placeholder, color = Color.Gray, fontSize = 13.sp) },
            leadingIcon = { Icon(icon, null, tint = accent, modifier = Modifier.size(20.dp)) },
            trailingIcon = if (trailingIcon != null) { { Icon(trailingIcon, null, tint = Color.Gray) } } else null,
            shape = RoundedCornerShape(12.dp),
            readOnly = readOnly,
            enabled = !readOnly || onClick != null,
            singleLine = singleLine,
            minLines = if (singleLine) 1 else 3,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryGreen,
                unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
                disabledBorderColor = Color.LightGray.copy(alpha = 0.5f),
                // Pin the entered text dark. Left to the theme it resolves to
                // colorScheme.onSurface, which is a pale grey under dark mode and
                // renders almost invisible on this screen's light background.
                focusedTextColor = Color(0xFF14231A),
                unfocusedTextColor = Color(0xFF14231A),
                disabledTextColor = Color(0xFF14231A),
                cursorColor = PrimaryGreen
            )
        )
        // The text field handles its own pointer input, so a click modifier on it
        // is swallowed. Picker fields get a transparent target on top instead.
        if (onClick != null) {
            Box(Modifier.matchParentSize().clickable { onClick() })
        }
        }
    }
}

@Composable
private fun PhotoSlot(label: String, subtitle: String, icon: ImageVector, uri: Uri?, modifier: Modifier, accent: Color = PrimaryGreen, onCapture: () -> Unit) {
    val stroke = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    Column(modifier = modifier) {
        Surface(
            onClick = onCapture,
            modifier = Modifier.fillMaxWidth().height(120.dp).drawBehind {
                drawRoundRect(color = Color.LightGray, style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2f, pathEffect = stroke), cornerRadius = CornerRadius(12.dp.toPx()))
            },
            shape = RoundedCornerShape(12.dp),
            color = Color.Transparent
        ) {
            Box(contentAlignment = Alignment.Center) {
                if (uri != null) {
                    AsyncImage(model = uri, contentDescription = null, modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(12.dp)), contentScale = ContentScale.Crop)
                } else {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(modifier = Modifier.size(40.dp).background(LightGreenBg, CircleShape), contentAlignment = Alignment.Center) {
                            Icon(icon, null, tint = accent, modifier = Modifier.size(24.dp))
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(label, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color.Black)
                        Text(subtitle, fontSize = 9.sp, color = Color.Gray, textAlign = TextAlign.Center, modifier = Modifier.padding(horizontal = 8.dp), lineHeight = 11.sp)
                    }
                }
            }
        }
    }
}
