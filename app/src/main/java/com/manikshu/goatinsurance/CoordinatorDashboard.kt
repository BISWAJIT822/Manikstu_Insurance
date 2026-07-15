package com.manikshu.goatinsurance

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

val CoordinatorOrange = Color(0xFFFF7043)
val CoordinatorLightOrange = Color(0xFFFFF3E0)

@Composable
fun CoordinatorDashboard(navController: NavHostController, sessionManager: SessionManager) {
    val languageState = LocalAppLanguage.current
    val savedName by sessionManager.userName.collectAsState(initial = null)
    // Canonical name from the backend (also re-syncs the session cache).
    val profileVm: ProfileViewModel = androidx.hilt.navigation.compose.hiltViewModel()
    val dbProfile by profileVm.profile.collectAsState()
    val userName = dbProfile?.fullName ?: savedName ?: ""

    val vm: CoordinatorDashboardViewModel = androidx.hilt.navigation.compose.hiltViewModel()
    val dashState by vm.dashboard.collectAsState()
    val activity by vm.activity.collectAsState()
    val dash = (dashState as? UiState.Success)?.data

    Scaffold(
        bottomBar = { CoordinatorBottomBar(navController) },
        containerColor = Color(0xFFF8F9FB),
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            CoordinatorHeader(
                navController,
                userName,
                stringResource(R.string.role_coordinator),
                onProfileClick = { navController.navigate("profile") }
            )

            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Spacer(modifier = Modifier.height(20.dp))
                
                // Stat Cards Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    CoordinatorStatCard(
                        modifier = Modifier.weight(1f),
                        label = stringResource(R.string.active_policies_label),
                        value = dash?.activePolicies?.toString() ?: "…",
                        trend = "",
                        trendColor = SuccessGreen,
                        icon = Icons.Default.Security,
                        points = listOf(0.2f, 0.4f, 0.3f, 0.6f, 0.5f, 0.8f)
                    )
                    CoordinatorStatCard(
                        modifier = Modifier.weight(1f).clickable { navController.navigate("claim_list") },
                        label = stringResource(R.string.claims_today_label),
                        value = dash?.claimsToday?.toString() ?: "…",
                        trend = "",
                        trendColor = SuccessGreen,
                        icon = Icons.Default.Assignment,
                        points = listOf(0.1f, 0.2f, 0.5f, 0.4f, 0.7f, 0.9f)
                    )
                    CoordinatorStatCard(
                        modifier = Modifier.weight(1f),
                        label = languageState.value.getT("Enrollments", "नामांकन", "ପଞ୍ଜିକରଣ"),
                        value = dash?.totalEnrollments?.toString() ?: "…",
                        trend = "",
                        trendColor = SuccessGreen,
                        icon = Icons.Default.Assessment,
                        points = listOf(0.8f, 0.7f, 0.9f, 0.6f, 0.4f, 0.2f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                PerformanceOverviewCard()

                Spacer(modifier = Modifier.height(24.dp))

                LiveActivitySection(navController, languageState.value, activity)
                
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun CoordinatorHeader(navController: NavHostController, name: String, role: String, onProfileClick: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val todayLabel = stringResource(R.string.date_today)
    val yesterdayLabel = stringResource(R.string.date_yesterday)
    val thisWeekLabel = stringResource(R.string.date_this_week)
    val thisMonthLabel = stringResource(R.string.date_this_month)
    var selectedDate by remember(todayLabel) { mutableStateOf(todayLabel) }

    Surface(
        color = CoordinatorOrange,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 56.dp, bottom = 28.dp, start = 20.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                onClick = onProfileClick,
                modifier = Modifier.size(72.dp),
                shape = CircleShape,
                color = Color.White
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Person, null, tint = Color.Gray, modifier = Modifier.size(40.dp))
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(R.string.hello_comma),
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.9f)
                )
                Text(
                    text = "$name 👋",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = role,
                    fontSize = 13.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }

            Box {
                Surface(
                    onClick = { expanded = true },
                    color = Color.White.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = selectedDate,
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            Icons.Default.ArrowDropDown,
                            null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(Color.White)
                ) {
                    val dateOptions = listOf(
                        todayLabel,
                        yesterdayLabel,
                        thisWeekLabel,
                        thisMonthLabel
                    )
                    dateOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option, color = Color.Black) },
                            onClick = {
                                selectedDate = option
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CoordinatorStatCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    trend: String,
    trendColor: Color,
    icon: Any,
    points: List<Float>
) {
    Card(
        modifier = modifier.height(140.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = CoordinatorOrange.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        when (icon) {
                            is ImageVector -> Icon(icon, null, tint = CoordinatorOrange, modifier = Modifier.size(18.dp))
                            is Painter -> Icon(icon, null, tint = CoordinatorOrange, modifier = Modifier.size(18.dp))
                        }
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        if (trend.startsWith("+")) Icons.Default.TrendingUp else Icons.Default.TrendingDown,
                        null,
                        tint = trendColor,
                        modifier = Modifier.size(12.dp)
                    )
                    Text(trend, color = trendColor, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            Text(label, fontSize = 11.sp, color = Color.Gray)
            Text(value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Canvas(modifier = Modifier.fillMaxWidth().height(1.dp)) {
                drawLine(
                    color = if (label.contains("Active")) SuccessGreen else Color.Transparent,
                    start = androidx.compose.ui.geometry.Offset(0f, 0f),
                    end = androidx.compose.ui.geometry.Offset(size.width * 0.7f, 0f),
                    strokeWidth = 2.dp.toPx()
                )
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            Canvas(modifier = Modifier.fillMaxWidth().height(20.dp)) {
                val path = Path()
                points.forEachIndexed { index, point ->
                    val x = index * size.width / (points.size - 1)
                    val y = size.height * (1f - point)
                    if (index == 0) path.moveTo(x, y) else path.lineTo(x, y)
                }
                drawPath(path, color = trendColor, style = Stroke(width = 2.dp.toPx()))
            }
        }
    }
}

@Composable
fun PerformanceOverviewCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        stringResource(R.string.performance_overview),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        stringResource(R.string.last_7_days_activity),
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
                Icon(Icons.Default.MoreVert, null, tint = Color.Gray)
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Bar Chart
            Row(
                modifier = Modifier.fillMaxWidth().height(150.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                val days = listOf(
        stringResource(R.string.mon),
        stringResource(R.string.tue),
        stringResource(R.string.wed),
        stringResource(R.string.thu),
        stringResource(R.string.fri),
        stringResource(R.string.sat),
        stringResource(R.string.sun)
    )
                val heights = listOf(0.6f, 0.3f, 0.7f, 0.4f, 0.8f, 0.6f, 0.7f)
                
                days.forEachIndexed { index, day ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .width(24.dp)
                                .fillMaxHeight(heights[index])
                                .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                                .background(CoordinatorOrange)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(day, fontSize = 10.sp, color = Color.Gray)
                    }
                }
            }
        }
    }
}

@Composable
fun LiveActivitySection(navController: NavHostController, language: AppLanguage, activity: List<ActivityItem> = emptyList()) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(R.string.live_activity),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                stringResource(R.string.recent),
                fontSize = 13.sp,
                color = Color.Gray
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                val shown = activity.take(5)
                if (shown.isEmpty()) {
                    Text(
                        language.getT("No recent activity", "कोई हालिया गतिविधि नहीं", "କୌଣସି ସାମ୍ପ୍ରତିକ କାର୍ଯ୍ୟକଳାପ ନାହିଁ"),
                        color = Color.Gray, fontSize = 13.sp, modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                shown.forEachIndexed { index, item ->
                    val isClaim = item.type == "claim"
                    LiveActivityItem(
                        title = "${item.actor}: ${item.detail}",
                        time = item.time.take(16).replace("T", " "),
                        status = if (isClaim) "Claim" else "New",
                        statusColor = if (isClaim) Color(0xFFFFB74D) else Color(0xFF4FC3F7),
                        icon = if (isClaim) Icons.AutoMirrored.Filled.Assignment else Icons.Default.PersonAdd
                    )
                    if (index < shown.size - 1) {
                        HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray.copy(alpha = 0.3f))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedButton(
                    onClick = { navController.navigate("activity_report") },
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
                ) {
                    Text(
                        stringResource(R.string.full_activity_report),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityReportScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            Surface(
                color = CoordinatorOrange,
                modifier = Modifier.fillMaxWidth()
            ) {
                TopAppBar(
                    title = { 
                        Text(
                            stringResource(R.string.activity_report_title),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        ) 
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                    modifier = Modifier.statusBarsPadding()
                )
            }
        },
        containerColor = Color(0xFFF8F9FB),
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(16.dp)) }
            
            val reports = listOf(
                "claim_filed" to ("Sushma Didi" to 2),
                "vaccination_completed" to ("Laxmi Didi" to 5),
                "new_enrollment" to ("Sushma Didi" to 10),
                "visit_scheduled" to ("Ramesh Didi" to 1),
                "system_update" to ("" to 2),
                "claim_approved" to ("CLM1002" to 5),
                "farmer_added" to ("Ram" to 0),
                "vaccination_batch" to ("PPR-01" to 0)
            )

            items(reports) { (type, data) ->
                val (title, time, status) = when(type) {
                    "claim_filed" -> Triple(stringResource(R.string.filed_a_claim, data.first), stringResource(R.string.n_mins_ago, data.second), "Pending")
                    "vaccination_completed" -> Triple(stringResource(R.string.completed_vaccination, data.first), stringResource(R.string.n_mins_ago, data.second), "Success")
                    "new_enrollment" -> Triple(stringResource(R.string.new_enrollment_by, data.first), stringResource(R.string.n_mins_ago, data.second), "New")
                    "visit_scheduled" -> Triple(stringResource(R.string.visit_scheduled_by, data.first), stringResource(R.string.n_hours_ago, data.second), "Info")
                    "system_update" -> Triple(stringResource(R.string.system_maintenance_update), stringResource(R.string.n_hours_ago, data.second), "System")
                    "claim_approved" -> Triple(stringResource(R.string.claim_approved_label, data.first), stringResource(R.string.n_hours_ago, data.second), "Success")
                    "farmer_added" -> Triple(stringResource(R.string.farmer_added_new_goat, data.first), stringResource(R.string.date_yesterday), "New")
                    "vaccination_batch" -> Triple(stringResource(R.string.vaccination_batch_used, data.first), stringResource(R.string.date_yesterday), "Info")
                    else -> Triple("", "", "")
                }

                val statusText = when(status) {
                    "Pending" -> stringResource(R.string.status_pending)
                    "Success" -> stringResource(R.string.status_success)
                    "New" -> stringResource(R.string.status_new)
                    else -> status
                }
                val statusColor = when(status) {
                    "Pending" -> Color(0xFFFFB74D)
                    "Success" -> SuccessGreen
                    "New" -> Color(0xFF4FC3F7)
                    "System" -> Color.Gray
                    else -> Color.LightGray
                }
                
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    LiveActivityItem(
                        title = title,
                        time = time,
                        status = statusText,
                        statusColor = statusColor,
                        icon = when(status) {
                            "Pending" -> Icons.AutoMirrored.Filled.Assignment
                            "Success" -> Icons.Default.CheckCircle
                            "New" -> Icons.Default.PersonAdd
                            else -> Icons.Default.Info
                        }
                    )
                }
            }
            
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
fun LiveActivityItem(
    title: String,
    time: String,
    status: String,
    statusColor: Color,
    icon: ImageVector
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(44.dp),
            shape = CircleShape,
            color = Color(0xFFF5F5F5)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(icon, null, tint = Color.Gray, modifier = Modifier.size(22.dp))
            }
        }
        
        Spacer(modifier = Modifier.width(12.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title, 
                fontSize = 15.sp, 
                fontWeight = FontWeight.Bold, 
                color = Color.Black,
                lineHeight = 18.sp
            )
            Text(text = time, fontSize = 13.sp, color = Color.Gray)
        }
        
        Spacer(modifier = Modifier.width(8.dp))
        
        Surface(
            color = statusColor.copy(alpha = 0.1f),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                text = status,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                color = statusColor,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClusterMapScreen(navController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableIntStateOf(0) } // 0 for Map, 1 for List

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        stringResource(R.string.cluster_map_title),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = CoordinatorOrange)
            )
        },
        bottomBar = { CoordinatorBottomBar(navController) },
        containerColor = Color(0xFFF8F9FB)
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text(stringResource(R.string.search_village_didi)) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray) },
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f)
                )
            )

            // Map/List Toggle
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(44.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFEEEEEE))
                    .padding(2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    onClick = { selectedTab = 0 },
                    modifier = Modifier.weight(1f).fillMaxHeight(),
                    color = if (selectedTab == 0) Color.White else Color.Transparent,
                    shape = RoundedCornerShape(6.dp),
                    shadowElevation = if (selectedTab == 0) 2.dp else 0.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            stringResource(R.string.map),
                            fontWeight = FontWeight.Bold,
                            color = if (selectedTab == 0) Color.Black else Color.Gray,
                            fontSize = 14.sp
                        )
                    }
                }
                Surface(
                    onClick = { selectedTab = 1 },
                    modifier = Modifier.weight(1f).fillMaxHeight(),
                    color = if (selectedTab == 1) Color.White else Color.Transparent,
                    shape = RoundedCornerShape(6.dp),
                    shadowElevation = if (selectedTab == 1) 2.dp else 0.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            stringResource(R.string.list),
                            fontWeight = FontWeight.Bold,
                            color = if (selectedTab == 1) Color.Black else Color.Gray,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (selectedTab == 0) {
                // Map View UI Placeholder (No interactive map integration)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(Color(0xFFE8F5E9)) // Light green background to simulate a map
                ) {
                    // Simulated Map Content (Grid lines and subtle variations)
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val strokeWidth = 1.dp.toPx()
                        val color = Color.LightGray.copy(alpha = 0.2f)
                        for (i in 1..10) {
                            drawLine(color, androidx.compose.ui.geometry.Offset(i * size.width / 10, 0f), androidx.compose.ui.geometry.Offset(i * size.width / 10, size.height), strokeWidth)
                            drawLine(color, androidx.compose.ui.geometry.Offset(0f, i * size.height / 10), androidx.compose.ui.geometry.Offset(size.width, i * size.height / 10), strokeWidth)
                        }
                    }

                    // UI Designed Cluster Markers
                    ClusterUI(Modifier.align(Alignment.Center).offset(x = (-80).dp, y = (-40).dp), "15", Color.Red)
                    ClusterUI(Modifier.align(Alignment.Center).offset(x = 60.dp, y = (-100).dp), "8", Color(0xFF4CAF50))
                    ClusterUI(Modifier.align(Alignment.Center).offset(x = 100.dp, y = 40.dp), "6", CoordinatorOrange)
                    ClusterUI(Modifier.align(Alignment.Center).offset(x = (-40).dp, y = 80.dp), "3", Color(0xFF2196F3))
                    ClusterUI(Modifier.align(Alignment.Center).offset(x = 0.dp, y = (-20).dp), "2", Color(0xFF8BC34A))

                    // Legend Overlay
                    Card(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 24.dp, start = 16.dp, end = 16.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            LegendItem(stringResource(R.string.high_claims), Color.Red)
                            LegendItem(stringResource(R.string.medium), CoordinatorOrange)
                            LegendItem(stringResource(R.string.low), Color(0xFF4CAF50))
                        }
                    }
                }
            } else {
                // List View UI (Light Theme)
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    val villages = listOf(
                        "Village Pipili" to "Sushma Didi",
                        "Village Balianta" to "Laxmi Didi",
                        "Village Puri" to "Ramesh Didi",
                        "Village Cuttack" to "Sita Didi",
                        "Village Khorda" to "Gita Didi",
                        "Village Nayagarh" to "Sunita Didi"
                    )

                    items(villages) { (village, didi) ->
                        ListItem(
                            modifier = Modifier.background(Color.White),
                            headlineContent = { 
                                Text(
                                    village, 
                                    fontWeight = FontWeight.Bold, 
                                    color = Color.Black 
                                ) 
                            },
                            supportingContent = { 
                                Text(
                                    "$didi • ${stringResource(R.string.active_claims_count, 5)}",
                                    color = Color.Gray 
                                ) 
                            },
                            leadingContent = { 
                                Surface(
                                    color = CoordinatorOrange.copy(alpha = 0.1f),
                                    shape = CircleShape,
                                    modifier = Modifier.size(40.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(
                                            Icons.Default.LocationOn, 
                                            null, 
                                            tint = CoordinatorOrange,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                }
                            },
                            trailingContent = { 
                                Icon(
                                    Icons.Default.ChevronRight, 
                                    null, 
                                    tint = Color.LightGray 
                                ) 
                            },
                            colors = ListItemDefaults.colors(containerColor = Color.White)
                        )
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 16.dp), 
                            color = Color.LightGray.copy(alpha = 0.3f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ClusterUI(modifier: Modifier, count: String, color: Color) {
    Box(
        modifier = modifier
            .size(50.dp),
        contentAlignment = Alignment.Center
    ) {
        // Outer glow/ring
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = CircleShape,
            color = color.copy(alpha = 0.2f)
        ) {}
        
        // Inner circle
        Surface(
            modifier = Modifier.size(34.dp),
            shape = CircleShape,
            color = color,
            border = BorderStroke(2.dp, Color.White)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = count,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun LegendItem(label: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(label, fontSize = 12.sp, fontWeight = FontWeight.Medium)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoordinatorReportsScreen(navController: NavHostController) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val thisMonthLabel = stringResource(R.string.date_this_month)
    var selectedDateRange by remember(thisMonthLabel) { mutableStateOf(thisMonthLabel) }
    var showExportModal by remember { mutableStateOf(false) }

    val vm: CoordinatorReportsViewModel = androidx.hilt.navigation.compose.hiltViewModel()
    val reportsState by vm.state.collectAsState()
    val rep = (reportsState as? UiState.Success)?.data

    val tabs = listOf(
        stringResource(R.string.enrollment_tab),
        stringResource(R.string.vaccination_tab),
        stringResource(R.string.claims_tab),
        stringResource(R.string.financial_tab),
        stringResource(R.string.performance_tab)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        stringResource(R.string.reports_title),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = CoordinatorOrange)
            )
        },
        bottomBar = { CoordinatorBottomBar(navController) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { showExportModal = true },
                containerColor = CoordinatorOrange,
                contentColor = Color.White,
                icon = { Icon(Icons.Default.FileDownload, null) },
                text = { Text(stringResource(R.string.export_report)) }
            )
        },
        containerColor = Color(0xFFF8F9FB)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Filters Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    FilterDropdown(Modifier.weight(1f), stringResource(R.string.date_range_label), selectedDateRange) { selectedDateRange = it }
                    FilterDropdown(Modifier.weight(1f), stringResource(R.string.region_label), stringResource(R.string.all_regions_label)) {}
                }
                FilterDropdown(Modifier.fillMaxWidth(), stringResource(R.string.suraksha_didi_label), stringResource(R.string.all_didis_label), isSearchable = true) {}
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Summary Cards
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ReportSummaryCard("Enrollments", rep?.totalEnrollments?.toString() ?: "…", "", SuccessGreen, Icons.Default.People)
                ReportSummaryCard("Claims Filed", rep?.totalClaimsFiled?.toString() ?: "…", "", SuccessGreen, painterResource(R.drawable.ic_ewe_custom))
                ReportSummaryCard("Approved", rep?.claimsApproved?.toString() ?: "…", "", SuccessGreen, Icons.Default.Assignment)
                ReportSummaryCard(stringResource(R.string.premium_label), rep?.let { "₹${it.totalPremium.toInt()}" } ?: "…", "", SuccessGreen, Icons.Default.Payments)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tabs
            ScrollableTabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color.Transparent,
                edgePadding = 16.dp,
                divider = {},
                indicator = { tabPositions ->
                    if (selectedTab < tabPositions.size) {
                        TabRowDefaults.SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                            color = CoordinatorOrange
                        )
                    }
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { 
                            Text(
                                title, 
                                color = if (selectedTab == index) CoordinatorOrange else Color.Gray,
                                fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                            ) 
                        }
                    )
                }
            }

            // Content Area per Tab
            Column(modifier = Modifier.padding(16.dp)) {
                // Chart Placeholder
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(stringResource(R.string.tab_breakup_title, tabs[selectedTab]), fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(16.dp))
                        // Simple bar chart visualization using Canvas
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            val data = listOf(0.4f, 0.7f, 0.5f, 0.9f, 0.3f, 0.8f)
                            val barWidth = size.width / (data.size * 2)
                            data.forEachIndexed { i, value ->
                                drawRect(
                                    color = CoordinatorOrange,
                                    topLeft = androidx.compose.ui.geometry.Offset(i * barWidth * 2 + barWidth / 2, size.height * (1 - value)),
                                    size = androidx.compose.ui.geometry.Size(barWidth, size.height * value)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // List Area
                Text(stringResource(R.string.detailed_data), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(12.dp))
                
                repeat(5) { i ->
                    ReportDataItem(
                        name = "Region ${'A' + i}",
                        status = if (i % 2 == 0) "Completed" else "Pending",
                        value = "${(i + 1) * 120}"
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
                
                Button(
                    onClick = { /* Generate Custom */ },
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = CoordinatorOrange),
                    border = BorderStroke(1.dp, CoordinatorOrange),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(stringResource(R.string.generate_custom_report), fontWeight = FontWeight.Bold)
                }
                
                Spacer(modifier = Modifier.height(80.dp)) // Space for FAB
            }
        }
    }

    if (showExportModal) {
        AlertDialog(
            onDismissRequest = { showExportModal = false },
            confirmButton = {
                TextButton(onClick = { showExportModal = false }) { Text(stringResource(R.string.cancel)) }
            },
            title = { Text(stringResource(R.string.export_report)) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(stringResource(R.string.select_format))
                    ExportOption(stringResource(R.string.pdf_document), Icons.Default.PictureAsPdf) { showExportModal = false }
                    ExportOption(stringResource(R.string.excel_spreadsheet), Icons.Default.Description) { showExportModal = false }
                }
            },
            containerColor = Color.White
        )
    }
}

@Composable
fun FilterDropdown(modifier: Modifier, label: String, value: String, isSearchable: Boolean = false, onSelect: (String) -> Unit = {}) {
    var expanded by remember { mutableStateOf(false) }
    val dateRangeLabel = stringResource(R.string.date_range_label)
    Box(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(label) },
            trailingIcon = { Icon(Icons.Default.ArrowDropDown, null, tint = Color.Black) },
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedLabelColor = CoordinatorOrange,
                unfocusedLabelColor = Color.Black.copy(alpha = 0.6f),
                focusedBorderColor = CoordinatorOrange,
                unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f)
            )
        )
        Box(modifier = Modifier.matchParentSize().clickable { expanded = true })
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier.background(Color.White)) {
            val options = if (label == dateRangeLabel) listOf(
                stringResource(R.string.date_today),
                stringResource(R.string.date_this_week),
                stringResource(R.string.date_this_month),
                stringResource(R.string.custom_label)
            ) else listOf(
                stringResource(R.string.option_1),
                stringResource(R.string.option_2)
            )
            options.forEach { opt ->
                DropdownMenuItem(text = { Text(opt, color = Color.Black) }, onClick = { onSelect(opt); expanded = false })
            }
        }
    }
}

@Composable
fun ReportSummaryCard(label: String, value: String, trend: String, trendColor: Color, icon: Any) {
    Card(
        modifier = Modifier.width(160.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Surface(color = CoordinatorOrange.copy(alpha = 0.1f), shape = CircleShape, modifier = Modifier.size(32.dp)) {
                    Box(contentAlignment = Alignment.Center) {
                        when (icon) {
                            is ImageVector -> Icon(icon, null, tint = CoordinatorOrange, modifier = Modifier.size(18.dp))
                            is Painter -> Icon(icon, null, tint = CoordinatorOrange, modifier = Modifier.size(18.dp))
                        }
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.TrendingUp, null, tint = trendColor, modifier = Modifier.size(12.dp))
                    Text(trend, color = trendColor, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(value, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
            Text(label, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Composable
fun ReportDataItem(name: String, status: String, value: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(name, fontWeight = FontWeight.Bold, color = Color.Black)
                val statusText = when(status) {
                    "Completed" -> stringResource(R.string.status_completed)
                    "Pending" -> stringResource(R.string.status_pending)
                    else -> status
                }
                Surface(
                    color = when(status) {
                        "Completed" -> SuccessGreen.copy(alpha = 0.1f)
                        "Pending" -> Color(0xFFFFB74D).copy(alpha = 0.1f)
                        else -> Color.Red.copy(alpha = 0.1f)
                    },
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        statusText,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        color = when(status) {
                            "Completed" -> SuccessGreen
                            "Pending" -> Color(0xFFFFB74D)
                            else -> Color.Red
                        },
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Text(value, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = CoordinatorOrange)
        }
    }
}

@Composable
fun ExportOption(label: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable { onClick() }.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, tint = Color.Gray)
        Spacer(modifier = Modifier.width(12.dp))
        Text(label)
    }
}

@Composable
fun CoordinatorBottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = currentRoute == "coordinator_dashboard",
            onClick = {
                if (currentRoute != "coordinator_dashboard") {
                    navController.navigate("coordinator_dashboard") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            },
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text(stringResource(R.string.dashboard), fontSize = 10.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = CoordinatorOrange,
                selectedTextColor = CoordinatorOrange,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = currentRoute == "claim_list",
            onClick = {
                if (currentRoute != "claim_list") {
                    navController.navigate("claim_list")
                }
            },
            icon = { Icon(Icons.AutoMirrored.Filled.Assignment, null) },
            label = { Text(stringResource(R.string.claims), fontSize = 10.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = CoordinatorOrange,
                selectedTextColor = CoordinatorOrange,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = currentRoute == "cluster_map",
            onClick = { 
                if (currentRoute != "cluster_map") {
                    navController.navigate("cluster_map")
                }
            },
            icon = { Icon(Icons.Default.Public, null) },
            label = { Text(stringResource(R.string.map), fontSize = 10.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = CoordinatorOrange,
                selectedTextColor = CoordinatorOrange,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = currentRoute == "coordinator_reports",
            onClick = { 
                if (currentRoute != "coordinator_reports") {
                    navController.navigate("coordinator_reports")
                }
            },
            icon = { Icon(Icons.Default.BarChart, null) },
            label = { Text(stringResource(R.string.reports), fontSize = 10.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = CoordinatorOrange,
                selectedTextColor = CoordinatorOrange,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
    }
}
