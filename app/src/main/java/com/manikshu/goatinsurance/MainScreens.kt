package com.manikshu.goatinsurance


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.Vaccines
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.filled.FactCheck
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.ThumbUpOffAlt
import androidx.compose.material.icons.filled.ThumbUpOffAlt
import androidx.compose.material.icons.filled.ThumbDownOffAlt
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.draw.drawBehind
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import android.content.Intent
import android.Manifest
import android.app.Activity


import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.SpanStyle
import android.net.Uri
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import java.util.Calendar
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import coil.compose.AsyncImage
import java.io.File
import java.io.FileOutputStream
import androidx.core.content.FileProvider
import androidx.activity.compose.BackHandler
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.material.icons.filled.Description
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.res.stringResource
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import kotlinx.coroutines.launch

fun AppLanguage.getT(en: String, hi: String, or: String): String {
    return when (this) {
        AppLanguage.ENGLISH -> en
        AppLanguage.HINDI -> hi
        AppLanguage.ODIA -> or
    }
}

val LocalWindowSizeClass = staticCompositionLocalOf<WindowSizeClass?> { null }
val LocalAppLanguage = compositionLocalOf { mutableStateOf(AppLanguage.ENGLISH) }
val LocalProfileImage = compositionLocalOf { mutableStateOf<Uri?>(null) }
val LocalNotificationsEnabled = compositionLocalOf { mutableStateOf(true) }
private val PrimaryBlue = Color(0xFF1976D2)

@Composable
fun AppNavigation(navController: NavHostController, sessionManager: SessionManager) {
    val scope = rememberCoroutineScope()
    val userRole by sessionManager.userRole.collectAsState(initial = null)
    val context = LocalContext.current
    
    var showExitDialog by remember { mutableStateOf(false) }

    BackHandler(enabled = true) {
        if (navController.previousBackStackEntry == null) {
            showExitDialog = true
        } else {
            navController.popBackStack()
        }
    }

    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = { Text(stringResource(R.string.exit_app_title), fontWeight = FontWeight.Bold, color = Color.Black) },
            text = { Text(stringResource(R.string.exit_app_message), color = Color.Black) },
            confirmButton = {
                TextButton(onClick = { (context as? Activity)?.finish() }) {
                    Text(stringResource(R.string.yes), fontWeight = FontWeight.Bold, color = Color.Black)
                }
            },
            dismissButton = {
                TextButton(onClick = { showExitDialog = false }) {
                    Text(stringResource(R.string.no), fontWeight = FontWeight.Bold, color = Color.Black)
                }
            },
            containerColor = Color.White,
            shape = RoundedCornerShape(16.dp)
        )
    }

    // Auto-navigate if already logged in
    LaunchedEffect(userRole) {
        userRole?.let { role ->
            val currentRoute = navController.currentDestination?.route
            if (currentRoute == "login" || currentRoute == "signup") {
                val route = when(role) {
                    UserRole.SURAKSHA_DIDI -> "didi_dashboard"
                    UserRole.FARMER -> "farmer_dashboard"
                    UserRole.COORDINATOR -> "coordinator_dashboard"
                }
                navController.navigate(route) { popUpTo("login") { inclusive = true } }
            }
        }
    }

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { 
            LoginScreen(
                onLoginSuccess = { role ->
                    scope.launch { sessionManager.saveSession(role) }
                    val route = when(role) {
                        UserRole.SURAKSHA_DIDI -> "didi_dashboard"
                        UserRole.FARMER -> "farmer_dashboard"
                        UserRole.COORDINATOR -> "coordinator_dashboard"
                    }
                    navController.navigate(route) { popUpTo("login") { inclusive = true } }
                },
                onNavigateToSignUp = { navController.navigate("signup") }
            ) 
        }
        composable("signup") {
            SignUpScreen(
                onVerifyOtp = { role, name, phone ->
                    navController.navigate("setup_profile/$role/$name/$phone")
                },
                onNavigateToLogin = { navController.popBackStack() }
            )
        }
        composable("setup_profile/{role}/{name}/{phone}") { backStackEntry ->
            val roleStr = backStackEntry.arguments?.getString("role") ?: UserRole.FARMER.name
            val role = try { UserRole.valueOf(roleStr) } catch(e: Exception) { UserRole.FARMER }
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val phone = backStackEntry.arguments?.getString("phone") ?: ""
            SetupProfileScreen(
                role = role,
                initialName = name,
                initialPhone = phone,
                onComplete = { selectedRole, name, location ->
                    scope.launch { sessionManager.saveSession(selectedRole, name, location) }
                    val route = when(selectedRole) {
                        UserRole.SURAKSHA_DIDI -> "didi_dashboard"
                        UserRole.FARMER -> "farmer_dashboard"
                        UserRole.COORDINATOR -> "coordinator_dashboard"
                    }
                    navController.navigate(route) { popUpTo("signup") { inclusive = true } }
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable("didi_dashboard") { DidiDashboard(navController, sessionManager) }
        composable("farmer_dashboard") { FarmerDashboard(navController, sessionManager) }
        composable("coordinator_dashboard") { CoordinatorDashboard(navController, sessionManager) }
        composable("cluster_map") { ClusterMapScreen(navController) }
        composable("activity_report") { ActivityReportScreen(navController) }
        composable("coordinator_reports") { CoordinatorReportsScreen(navController) }
        composable("enrollment") { 
            EnrollmentStepper(
                onBack = { navController.popBackStack() },
                onComplete = { 
                    navController.navigate("goat_list") {
                        popUpTo("didi_dashboard") { inclusive = false }
                    }
                }
            ) 
        }
        composable("premium_collection") { PremiumCollectionScreen(onComplete = { navController.popBackStack() }) }
        composable("mortality_report") { MortalityReportScreen(onBack = { navController.popBackStack() }, onComplete = { navController.popBackStack() }) }
        composable("farmer_report_death") { FarmerReportDeathScreen(onBack = { navController.popBackStack() }, onComplete = { navController.popBackStack() }) }
        composable("claim_tracker") { ClaimStatusTracker() }
        composable("claim_list") { 
            ClaimListScreen(
                navController = navController, 
                userRole = userRole,
                onBack = { navController.popBackStack() }
            ) 
        }
        composable("claim_review/{claimId}") { backStackEntry ->
            val claimId = backStackEntry.arguments?.getString("claimId") ?: ""
            ClaimReviewScreen(
                navController = navController, 
                claimId = claimId, 
                userRole = userRole,
                onBack = { navController.popBackStack() }
            )
        }
        composable("claim_bank_details/{claimId}") { backStackEntry ->
            val claimId = backStackEntry.arguments?.getString("claimId") ?: ""
            ClaimBankDetailsScreen(
                navController = navController,
                claimId = claimId,
                userRole = userRole,
                onBack = { navController.popBackStack() }
            )
        }
        composable("claim_verify/{claimId}") { backStackEntry ->
            val claimId = backStackEntry.arguments?.getString("claimId") ?: ""
            ClaimVerifyScreen(
                navController = navController,
                claimId = claimId,
                userRole = userRole,
                onBack = { navController.popBackStack() }
            )
        }
        composable("claim_payout/{claimId}") { backStackEntry ->
            val claimId = backStackEntry.arguments?.getString("claimId") ?: ""
            ClaimPayoutScreen(
                navController = navController,
                claimId = claimId,
                userRole = userRole,
                onBack = { navController.popBackStack() }
            )
        }
        composable("claim_approved/{claimId}") { backStackEntry ->
            val claimId = backStackEntry.arguments?.getString("claimId") ?: ""
            ClaimApprovedScreen(
                navController = navController,
                claimId = claimId,
                userRole = userRole
            )
        }
        composable("help_support") { 
            HelpSupportScreen(
                navController = navController,
                userRole = userRole,
                onReportIssue = { navController.navigate("report_issue") },
                onFaqs = { navController.navigate("faqs") },
                onContactSupport = { navController.navigate("contact_support_home") },
                onBack = { navController.popBackStack() }
            ) 
        }
        composable("contact_support_home") {
            ContactSupportHomeScreen(
                userRole = userRole,
                onStartChat = { navController.navigate("pre_chat_form") },
                onViewPrevious = { /* Navigate to history if implemented */ },
                onBack = { navController.popBackStack() }
            )
        }
        composable("pre_chat_form") {
            PreChatFormScreen(
                userRole = userRole,
                onStartChat = { navController.navigate("chat_conversation") },
                onBack = { navController.popBackStack() }
            )
        }
        composable("chat_conversation") {
            ChatConversationScreen(
                userRole = userRole,
                onBack = { navController.popBackStack() }
            )
        }
        composable("faqs") {
            FaqsScreen(
                userRole = userRole,
                onBack = { navController.popBackStack() },
                onContactSupport = { navController.navigate("call_support") }
            )
        }
        composable("call_support") {
            CallSupportScreen(
                userRole = userRole,
                onBack = { navController.popBackStack() },
                onCallEnded = { number, duration, time ->
                    navController.navigate("call_summary/$number/$duration/$time") {
                        popUpTo("call_support") { inclusive = true }
                    }
                }
            )
        }
        composable("call_summary/{number}/{duration}/{time}") { backStackEntry ->
            val number = backStackEntry.arguments?.getString("number") ?: ""
            val duration = backStackEntry.arguments?.getString("duration") ?: ""
            val time = backStackEntry.arguments?.getString("time") ?: ""
            CallSummaryScreen(
                number = number,
                duration = duration,
                time = time,
                userRole = userRole,
                onBackHome = { navController.popBackStack() },
                onCallAgain = { navController.navigate("call_support") }
            )
        }
        composable("report_issue") { 
            ReportIssueScreen(
                userRole = userRole,
                onBack = { navController.popBackStack() },
                onViewReports = { navController.navigate("my_reports") },
                onIssueSubmitted = { ticketId, type, date, status ->
                    navController.navigate("issue_success/$ticketId/$type/$date/$status") {
                        popUpTo("help_support") { inclusive = false }
                    }
                }
            ) 
        }
        composable("my_reports") {
            MyReportsScreen(
                navController = navController,
                userRole = userRole,
                onBack = { navController.popBackStack() }
            )
        }
        composable("issue_success/{ticketId}/{type}/{date}/{status}") { backStackEntry ->
            val ticketId = backStackEntry.arguments?.getString("ticketId") ?: ""
            val type = backStackEntry.arguments?.getString("type") ?: ""
            val date = backStackEntry.arguments?.getString("date") ?: ""
            val status = backStackEntry.arguments?.getString("status") ?: ""
            IssueSuccessScreen(
                navController = navController,
                ticketId = ticketId,
                type = type,
                date = date,
                status = status,
                userRole = userRole,
                onBackHome = { 
                    val route = when(userRole) {
                        UserRole.SURAKSHA_DIDI -> "didi_dashboard"
                        UserRole.FARMER -> "farmer_dashboard"
                        UserRole.COORDINATOR -> "coordinator_dashboard"
                        else -> "login"
                    }
                    navController.navigate(route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onViewReports = {
                    navController.navigate("my_reports")
                }
            )
        }
        composable("vaccine_list") { 
            VaccineListScreen(
                navController = navController,
                onBack = { navController.popBackStack() },
                onRecord = { tag -> navController.navigate("record_vaccination/$tag") }
            ) 
        }
        composable("record_vaccination/{tag}") { backStackEntry ->
            val tag = backStackEntry.arguments?.getString("tag") ?: ""
            RecordVaccinationScreen(tag = tag, onBack = { navController.popBackStack() })
        }
        composable("goat_list") { 
            GoatListScreen(
                navController = navController,
                userRole = userRole,
                onBack = { navController.popBackStack() },
                onAddGoat = { navController.navigate("enrollment") }
            ) 
        }
        composable("goat_details/{tag}") { backStackEntry ->
            val tag = backStackEntry.arguments?.getString("tag") ?: ""
            GoatDetailsScreen(navController = navController, tag = tag, userRole = userRole, onBack = { navController.popBackStack() })
        }
        composable("earning_history") { 
            EarningHistoryScreen(onBack = { navController.popBackStack() }) 
        }
        composable("profile") { 
            ProfileScreen(
                navController = navController,
                userRole = userRole,
                sessionManager = sessionManager,
                onLogout = { 
                    scope.launch { 
                        sessionManager.clearSession()
                        navController.navigate("login") { 
                            popUpTo(0) { inclusive = true }
                        } 
                    }
                },
                onBack = { navController.popBackStack() }
            ) 
        }
        composable("privacy_policy") {
            PrivacyPolicyScreen(
                userRole = userRole,
                onBack = { navController.popBackStack() }
            )
        }
        composable("terms_of_service") {
            TermsOfServiceScreen(
                userRole = userRole,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

private val CardLightGreen = Color(0xFFE8F5E9)
private val CardLightOrange = Color(0xFFFFF3E0)
private val CardLightBlue = Color(0xFFE3F2FD)
private val CardLightPurple = Color(0xFFF3E5F5)
private val CardLightRed = Color(0xFFFFEBEE)

// --- RESPONSIVE HELPERS ---

@Composable
fun ResponsiveLayout(
    compact: @Composable () -> Unit,
    expanded: @Composable () -> Unit,
) {
    val window = LocalWindowSizeClass.current
    if (window?.widthSizeClass == WindowWidthSizeClass.Compact) {
        compact()
    } else {
        expanded()
    }
}

@Composable
fun SignUpScreen(onVerifyOtp: (UserRole, String, String) -> Unit, onNavigateToLogin: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var step by remember { mutableIntStateOf(1) }
    var selectedRole by remember { mutableStateOf<UserRole?>(null) }
    
    val context = LocalContext.current
    val languageState = LocalAppLanguage.current

    // Permission Handling
    var showPermissionDialog by remember { mutableStateOf(false) }
    val permissionsToRequest = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.values.all { it }
        if (!allGranted) {
            Toast.makeText(context, context.getString(R.string.permissions_required_message), Toast.LENGTH_LONG).show()
        }
    }
    
    if (showPermissionDialog) {
        AlertDialog(
            onDismissRequest = { showPermissionDialog = false },
            title = { Text(stringResource(R.string.permissions_required_title), fontWeight = FontWeight.Bold) },
            text = { 
                Text(stringResource(R.string.permissions_required_message))
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showPermissionDialog = false
                        permissionLauncher.launch(permissionsToRequest)
                    }
                ) {
                    Text(stringResource(R.string.allow))
                }
            },
            dismissButton = {
                TextButton(onClick = { showPermissionDialog = false }) {
                    Text(stringResource(R.string.deny))
                }
            },
            shape = RoundedCornerShape(16.dp)
        )
    }

    var showLanguagePicker by remember { mutableStateOf(false) }

    val backgroundColor = Color(0xFFF8F9F5)
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Green Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                .background(PrimaryGreen)
        ) {
            // Language selector
            Surface(
                onClick = { showLanguagePicker = true },
                color = Color.White.copy(alpha = 0.15f),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 48.dp, end = 24.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Public, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = languageState.value.code.uppercase(),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                }

                MaterialTheme(
                    colorScheme = MaterialTheme.colorScheme.copy(surface = Color.White.copy(alpha = 0.9f))
                ) {
                    DropdownMenu(
                        expanded = showLanguagePicker,
                        onDismissRequest = { showLanguagePicker = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("English") },
                            onClick = {
                                languageState.value = AppLanguage.ENGLISH
                                showLanguagePicker = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("हिन्दी") },
                            onClick = {
                                languageState.value = AppLanguage.HINDI
                                showLanguagePicker = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("ଓଡ଼ିଆ") },
                            onClick = {
                                languageState.value = AppLanguage.ODIA
                                showLanguagePicker = false
                            }
                        )
                    }
                }
            }

            // Logo
            Icon(
                painterResource(R.drawable.ic_logo_custom),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(240.dp)
                    .offset(y = 20.dp)
            )
        }

        // SignUp Card
        Card(
            modifier = Modifier.padding(horizontal = 24.dp).offset(y = (-40).dp).fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 32.dp, horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(R.string.create_account),
                    style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold
                )
                Text(
                    stringResource(R.string.register_subtitle),
                    style = MaterialTheme.typography.bodyMedium, color = Color.Gray, textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(32.dp))

                if (step == 1) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { if (it.all { char -> char.isLetter() || char.isWhitespace() }) name = it },
                        placeholder = { Text(stringResource(R.string.full_name), color = Color.Gray) },
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = Color.DarkGray) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.Black, unfocusedTextColor = Color.Black, unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f), focusedBorderColor = PrimaryGreen)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = phone,
                        onValueChange = { if (it.length <= 10) phone = it },
                        placeholder = { Text(stringResource(R.string.mobile_number), color = Color.Gray) },
                        leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = Color.DarkGray) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.Black, unfocusedTextColor = Color.Black, unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f), focusedBorderColor = PrimaryGreen)
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { 
                            if (selectedRole == null) {
                                Toast.makeText(context, context.getString(R.string.scroll_choose_role_toast), Toast.LENGTH_SHORT).show()
                            } else {
                                step = 2 
                            }
                        },
                        enabled = (name.isNotBlank() && phone.length == 10),
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen, disabledContainerColor = PrimaryGreen.copy(alpha = 0.5f))
                    ) {
                        Text(stringResource(R.string.send_otp), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = stringResource(R.string.already_have_account_login),
                        color = PrimaryGreen, fontWeight = FontWeight.Bold, fontSize = 14.sp,
                        modifier = Modifier.clickable { onNavigateToLogin() }
                    )
                } else {
                    Text(stringResource(R.string.enter_otp_sent_to, phone), style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(24.dp))
                    OtpInput(otp, { otp = it }, onDone = { 
                        if (otp.length == 6) {
                            val allGranted = permissionsToRequest.all {
                                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
                            }
                            if (allGranted) {
                                onVerifyOtp(selectedRole!!, name, phone)
                            } else {
                                showPermissionDialog = true
                            }
                        }
                    })
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { 
                            val allGranted = permissionsToRequest.all {
                                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
                            }
                            if (allGranted) {
                                onVerifyOtp(selectedRole!!, name, phone)
                            } else {
                                showPermissionDialog = true
                            }
                        },
                        enabled = otp.length == 6,
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
                    ) { Text(stringResource(R.string.verify_next), fontSize = 16.sp, fontWeight = FontWeight.Bold) }
                    
                    TextButton(onClick = { step = 1 }) { Text(stringResource(R.string.go_back), color = PrimaryGreen) }
                }
            }
        }

        if (step == 1) {
            // Divider
            Row(modifier = Modifier.padding(horizontal = 24.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray.copy(alpha = 0.5f))
                Text(stringResource(R.string.choose_a_role), modifier = Modifier.padding(horizontal = 16.dp), color = Color.Gray, fontSize = 14.sp)
                HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray.copy(alpha = 0.5f))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Role Selection
            Row(modifier = Modifier.padding(horizontal = 24.dp).fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                RoleCard(UserRole.SURAKSHA_DIDI, stringResource(R.string.role_suraksha_didi), Icons.Default.Person, selectedRole == UserRole.SURAKSHA_DIDI, modifier = Modifier.weight(1f)) { selectedRole = it }
                RoleCard(UserRole.FARMER, stringResource(R.string.role_farmer), Icons.Default.Agriculture, selectedRole == UserRole.FARMER, modifier = Modifier.weight(1f)) { selectedRole = it }
                RoleCard(UserRole.COORDINATOR, stringResource(R.string.role_coordinator), Icons.Default.Badge, selectedRole == UserRole.COORDINATOR, modifier = Modifier.weight(1f)) { selectedRole = it }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }

        // Footer
        Row(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(stringResource(R.string.version_label, "1.0.0"), color = Color.Gray, fontSize = 12.sp)
            Surface(color = Color(0xFFE8F5E9), shape = RoundedCornerShape(16.dp)) {
                Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFF4CAF50)))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(stringResource(R.string.online), color = Color(0xFF4CAF50), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

// --- SCREENS ---

@Composable
fun LoginScreen(onLoginSuccess: (UserRole) -> Unit, onNavigateToSignUp: () -> Unit) {
    var phone by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var step by remember { mutableIntStateOf(1) }
    var selectedRole by remember { mutableStateOf<UserRole?>(null) }
    
    val context = LocalContext.current
    val languageState = LocalAppLanguage.current

    // Permission Handling
    var showPermissionDialog by remember { mutableStateOf(false) }
    val permissionsToRequest = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.values.all { it }
        if (!allGranted) {
            Toast.makeText(context, context.getString(R.string.permissions_required_message), Toast.LENGTH_LONG).show()
        }
    }
    
    if (showPermissionDialog) {
        AlertDialog(
            onDismissRequest = { showPermissionDialog = false },
            title = { Text(stringResource(R.string.permissions_required_title), fontWeight = FontWeight.Bold, color = Color.Black) },
            text = { 
                Text(stringResource(R.string.permissions_required_message), color = Color.Black)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showPermissionDialog = false
                        permissionLauncher.launch(permissionsToRequest)
                    }
                ) {
                    Text(stringResource(R.string.allow))
                }
            },
            dismissButton = {
                TextButton(onClick = { showPermissionDialog = false }) {
                    Text(stringResource(R.string.deny))
                }
            },
            shape = RoundedCornerShape(16.dp)
        )
    }

    var showLanguagePicker by remember { mutableStateOf(false) }

    val backgroundColor = Color(0xFFF8F9F5)
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Green Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                .background(PrimaryGreen)
        ) {
            // Language selector
            Surface(
                onClick = { showLanguagePicker = true },
                color = Color.White.copy(alpha = 0.15f),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 48.dp, end = 24.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Public, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = languageState.value.code.uppercase(),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                }

                MaterialTheme(
                    colorScheme = MaterialTheme.colorScheme.copy(surface = Color.White.copy(alpha = 0.9f))
                ) {
                    DropdownMenu(
                        expanded = showLanguagePicker,
                        onDismissRequest = { showLanguagePicker = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("English") },
                            onClick = {
                                languageState.value = AppLanguage.ENGLISH
                                showLanguagePicker = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("हिन्दी") },
                            onClick = {
                                languageState.value = AppLanguage.HINDI
                                showLanguagePicker = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("ଓଡ଼ିଆ") },
                            onClick = {
                                languageState.value = AppLanguage.ODIA
                                showLanguagePicker = false
                            }
                        )
                    }
                }
            }

            // Logo
            Icon(
                painterResource(R.drawable.ic_logo_custom),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(240.dp)
                    .offset(y = 20.dp)
            )
        }

        // Login Card
        Card(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .offset(y = (-40).dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 32.dp, horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(R.string.welcome_back),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    stringResource(R.string.login_subtitle),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(32.dp))

                if (step == 1) {
                    OutlinedTextField(
                        value = phone,
                        onValueChange = { if (it.length <= 10) phone = it },
                        placeholder = { Text(stringResource(R.string.mobile_number), color = Color.Gray) },
                        leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = Color.DarkGray) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
                            focusedBorderColor = PrimaryGreen
                        )
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { 
                            if (selectedRole == null) {
                                Toast.makeText(context, context.getString(R.string.scroll_choose_role_toast), Toast.LENGTH_SHORT).show()
                            } else {
                                step = 2 
                            }
                        },
                        enabled = phone.length == 10,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryGreen,
                            disabledContainerColor = PrimaryGreen.copy(alpha = 0.5f)
                        )
                    ) {
                        Text(stringResource(R.string.send_otp), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = stringResource(R.string.dont_have_account_signup),
                        color = PrimaryGreen,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        modifier = Modifier.clickable { onNavigateToSignUp() }
                    )
                } else {
                    Text(stringResource(R.string.enter_otp_sent_to, phone), style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(24.dp))
                    OtpInput(otp, { otp = it }, onDone = { 
                        if (otp.length == 6) {
                            val allGranted = permissionsToRequest.all {
                                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
                            }
                            if (allGranted) {
                                onLoginSuccess(selectedRole!!)
                            } else {
                                showPermissionDialog = true
                            }
                        }
                    })
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { 
                            val allGranted = permissionsToRequest.all {
                                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
                            }
                            if (allGranted) {
                                onLoginSuccess(selectedRole!!)
                            } else {
                                showPermissionDialog = true
                            }
                        },
                        enabled = otp.length == 6,
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
                    ) { Text(stringResource(R.string.verify_login), fontSize = 16.sp, fontWeight = FontWeight.Bold) }
                    
                    TextButton(onClick = { step = 1 }) { Text(stringResource(R.string.change_number), color = PrimaryGreen) }
                }
            }
        }

        if (step == 1) {
            // Divider
            Row(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray.copy(alpha = 0.5f))
                Text(
                    stringResource(R.string.choose_a_role),
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray.copy(alpha = 0.5f))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Role Selection
            Row(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                RoleCard(UserRole.SURAKSHA_DIDI, stringResource(R.string.role_suraksha_didi), Icons.Default.Person, selectedRole == UserRole.SURAKSHA_DIDI, modifier = Modifier.weight(1f)) { selectedRole = it }
                RoleCard(UserRole.FARMER, stringResource(R.string.role_farmer), Icons.Default.Agriculture, selectedRole == UserRole.FARMER, modifier = Modifier.weight(1f)) { selectedRole = it }
                RoleCard(UserRole.COORDINATOR, stringResource(R.string.role_coordinator), Icons.Default.Badge, selectedRole == UserRole.COORDINATOR, modifier = Modifier.weight(1f)) { selectedRole = it }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }

        // Footer
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(R.string.version_label, "1.0.0"), color = Color.Gray, fontSize = 12.sp)
            Surface(
                color = Color(0xFFE8F5E9),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFF4CAF50)))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(stringResource(R.string.online), color = Color(0xFF4CAF50), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun RoleCard(role: UserRole, label: String, icon: ImageVector, isSelected: Boolean, modifier: Modifier = Modifier, onClick: (UserRole) -> Unit) {
    val roleColor = when(role) {
        UserRole.SURAKSHA_DIDI -> PrimaryGreen
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
    }
    
    val roleBgColor = when(role) {
        UserRole.SURAKSHA_DIDI -> Color(0xFFE8F5E9)
        UserRole.FARMER -> Color(0xFFE3F2FD)
        UserRole.COORDINATOR -> Color(0xFFFFF3E0)
    }

    Card(
        onClick = { onClick(role) },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) roleBgColor else Color.White,
        ),
        modifier = modifier
            .height(110.dp)
            .border(
                width = 1.dp,
                color = if (isSelected) roleColor else Color.LightGray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 2.dp else 0.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = if (isSelected) roleColor else Color.DarkGray,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                label,
                fontSize = 10.sp,
                lineHeight = 12.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) roleColor else Color.DarkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}

@Composable
fun DidiDashboard(navController: NavHostController, sessionManager: SessionManager) {
    var showNotifications by remember { mutableStateOf(false) }
    if (showNotifications) NotificationSheet(themeColor = PrimaryGreen) { showNotifications = false }
    val userName by sessionManager.userName.collectAsState(initial = "Sushma Didi")

    ResponsiveLayout(
        compact = {
            Scaffold(
                modifier = Modifier.fillMaxSize().navigationBarsPadding(),
                bottomBar = { DidiBottomBar(navController) },
                contentWindowInsets = WindowInsets(0, 0, 0, 0)
            ) { padding ->
                DidiContent(padding, navController, userName ?: "Sushma Didi") { showNotifications = true }
            }
        },
        expanded = {
            Row(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing).navigationBarsPadding()) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                
                NavigationRail {
                    NavigationRailItem(
                        selected = currentRoute == "didi_dashboard", 
                        onClick = { 
                            if (currentRoute != "didi_dashboard") {
                                navController.navigate("didi_dashboard") {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        }, 
                        icon = { Icon(Icons.Default.Home, null) }, 
                        label = { Text(stringResource(R.string.home)) },
                        colors = NavigationRailItemDefaults.colors(
                            selectedIconColor = PrimaryGreen,
                            selectedTextColor = PrimaryGreen,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray,
                            indicatorColor = Color.Transparent
                        )
                    )
                    NavigationRailItem(
                        selected = currentRoute == "goat_list", 
                        onClick = { 
                            if (currentRoute != "goat_list") {
                                navController.navigate("goat_list") {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        }, 
                        icon = { Icon(painterResource(R.drawable.ic_ewe_custom), null) }, 
                        label = { Text(stringResource(R.string.goats)) },
                        colors = NavigationRailItemDefaults.colors(
                            selectedIconColor = PrimaryGreen,
                            selectedTextColor = PrimaryGreen,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray,
                            indicatorColor = Color.Transparent
                        )
                    )
                    NavigationRailItem(
                        selected = currentRoute == "vaccine_list", 
                        onClick = { 
                            if (currentRoute != "vaccine_list") {
                                navController.navigate("vaccine_list") {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        }, 
                        icon = { Icon(Icons.Default.MedicalServices, null) }, 
                        label = { Text(stringResource(R.string.vaccines)) },
                        colors = NavigationRailItemDefaults.colors(
                            selectedIconColor = PrimaryGreen,
                            selectedTextColor = PrimaryGreen,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
                DidiContent(PaddingValues(0.dp), navController, userName ?: "Sushma Didi") { showNotifications = true }
            }
        }
    )
}

@Composable
fun DidiContent(padding: PaddingValues, navController: NavHostController, userName: String, onNotificationClick: () -> Unit) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(bottom = padding.calculateBottomPadding())
            .fillMaxSize()
            .background(Color(0xFFF8F9F5))
    ) {
        DashboardHeader(
            navController,
            userName,
            stringResource(R.string.role_suraksha_didi),
            onNotificationClick,
            hasNotifications = true,
            onProfileClick = { navController.navigate("profile") }
        )
        
        val window = LocalWindowSizeClass.current
        val isCompact = window?.widthSizeClass == WindowWidthSizeClass.Compact
        val gridColumns = if (isCompact) 6 else 12

        LazyVerticalGrid(
            columns = GridCells.Fixed(gridColumns),
            modifier = Modifier.weight(1f).fillMaxWidth(),
            contentPadding = PaddingValues(bottom = 32.dp, start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item(span = { GridItemSpan(gridColumns) }) {
                Column {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        stringResource(R.string.this_month_overview),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            // Stats (2 columns on mobile, 4 on tablet)
            val statSpan = if (isCompact) 3 else 3
            items(4, span = { GridItemSpan(statSpan) }) { index ->
                when(index) {
                    0 -> StatCard(stringResource(R.string.goats_enrolled), "128", painterResource(R.drawable.ic_ewe_custom), PrimaryGreen, CardLightGreen)
                    1 -> StatCard(stringResource(R.string.pending_claims), "12", Icons.AutoMirrored.Filled.Assignment, AccentOrange, CardLightOrange)
                    2 -> StatCard(stringResource(R.string.today_visits), "24", Icons.Default.CalendarToday, InfoBlue, CardLightBlue)
                    3 -> StatCard(
                        label = stringResource(R.string.earnings), 
                        value = "₹8,450", 
                        icon = Icons.Default.Payments, 
                        iconColor = Color(0xFF9C27B0), 
                        iconBgColor = CardLightPurple,
                        modifier = Modifier.clickable { navController.navigate("earning_history") }
                    )
                }
            }

            item(span = { GridItemSpan(gridColumns) }) {
                Column {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        stringResource(R.string.quick_actions),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Quick Actions (3 columns on mobile, 6 on tablet)
            val actionSpan = if (isCompact) 2 else 2
            items(6, span = { GridItemSpan(actionSpan) }) { index ->
                when(index) {
                    0 -> QuickActionGridCard(stringResource(R.string.enroll_goat), painterResource(R.drawable.ic_ewe_custom), PrimaryGreen, CardLightGreen) { navController.navigate("enrollment") }
                    1 -> QuickActionGridCard(stringResource(R.string.vaccination), Icons.Default.MedicalServices, InfoBlue, CardLightBlue) { navController.navigate("vaccine_list") }
                    2 -> QuickActionGridCard(stringResource(R.string.mortality_report), Icons.Default.LocationOn, Color(0xFFD32F2F), CardLightRed) { navController.navigate("mortality_report") }
                    3 -> QuickActionGridCard(stringResource(R.string.claims), Icons.AutoMirrored.Filled.Assignment, AccentOrange, CardLightOrange) { navController.navigate("claim_list") }
                    4 -> QuickActionGridCard(stringResource(R.string.goat_list), Icons.AutoMirrored.Filled.FactCheck, Color(0xFF2E7D32), CardLightGreen) { navController.navigate("goat_list") }
                    5 -> {
                        val comingSoonMsg = context.getString(R.string.coming_soon)
                        QuickActionGridCard(stringResource(R.string.ai_assistant), Icons.Default.AccountBox, Color(0xFF7B1FA2), CardLightPurple) {
                            Toast.makeText(context, comingSoonMsg, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun EarningItem(title: String, subtitle: String, amount: String, time: String, icon: Any, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(40.dp),
            shape = RoundedCornerShape(10.dp),
            color = color.copy(alpha = 0.1f)
        ) {
            Box(contentAlignment = Alignment.Center) {
                when (icon) {
                    is ImageVector -> Icon(icon, null, tint = color, modifier = Modifier.size(20.dp))
                    is Painter -> Icon(icon, null, tint = color, modifier = Modifier.size(20.dp))
                }
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)
            Text(subtitle, fontSize = 12.sp, color = Color.Gray)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(amount, fontWeight = FontWeight.ExtraBold, fontSize = 14.sp, color = Color(0xFF2E7D32))
            Text(time, fontSize = 11.sp, color = Color.Gray)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PremiumCollectionScreen(onComplete: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize().navigationBarsPadding(),
        topBar = { CenterAlignedTopAppBar(title = { Text(stringResource(R.string.premium_collection)) }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(stringResource(R.string.total_premium_with_value, "350"), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    BreakdownItem(stringResource(R.string.insurance_company), "₹240")
                    BreakdownItem(stringResource(R.string.vaccination_program), "₹80")
                    BreakdownItem(stringResource(R.string.suraksha_didi_fee), "₹20")
                    BreakdownItem(stringResource(R.string.fpc_corpus_fund), "₹10")
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = onComplete, modifier = Modifier.fillMaxWidth()) { Text(stringResource(R.string.confirm_payment_generate_receipt)) }
        }
    }
}

enum class MortalityStepStatus {
    PENDING, IN_PROGRESS, COMPLETED
}

data class MortalityStepData(
    val title: String,
    val status: MortalityStepStatus,
    val statusText: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MortalityReportScreen(onBack: () -> Unit, onComplete: () -> Unit) {
    // 0: Notification, 1: Visit, 2: Verification, 3: Photos, 4: AI, 5: Handover, 6: Claim
    var currentStepIndex by rememberSaveable { mutableIntStateOf(2) } 
    var detailStepIndex by rememberSaveable { mutableStateOf<Int?>(null) }
    
    val steps = listOf(
        MortalityStepData(
            stringResource(R.string.death_notification),
            if (currentStepIndex > 0) MortalityStepStatus.COMPLETED else if (currentStepIndex == 0) MortalityStepStatus.IN_PROGRESS else MortalityStepStatus.PENDING,
            if (currentStepIndex > 0) stringResource(R.string.status_completed) else if (currentStepIndex == 0) stringResource(R.string.status_in_progress) else stringResource(R.string.status_pending)
        ),
        MortalityStepData(
            stringResource(R.string.site_visit),
            if (currentStepIndex > 1) MortalityStepStatus.COMPLETED else if (currentStepIndex == 1) MortalityStepStatus.IN_PROGRESS else MortalityStepStatus.PENDING,
            if (currentStepIndex > 1) stringResource(R.string.status_completed) else if (currentStepIndex == 1) stringResource(R.string.status_in_progress) else stringResource(R.string.status_pending)
        ),
        MortalityStepData(
            stringResource(R.string.carcass_verification),
            if (currentStepIndex > 2) MortalityStepStatus.COMPLETED else if (currentStepIndex == 2) MortalityStepStatus.IN_PROGRESS else MortalityStepStatus.PENDING,
            if (currentStepIndex > 2) stringResource(R.string.status_completed) else if (currentStepIndex == 2) stringResource(R.string.status_in_progress) else stringResource(R.string.status_pending)
        ),
        MortalityStepData(
            stringResource(R.string.photo_capture),
            if (currentStepIndex > 3) MortalityStepStatus.COMPLETED else if (currentStepIndex == 3) MortalityStepStatus.IN_PROGRESS else MortalityStepStatus.PENDING,
            if (currentStepIndex > 3) stringResource(R.string.status_completed) else if (currentStepIndex == 3) stringResource(R.string.status_in_progress) else stringResource(R.string.status_pending)
        ),
        MortalityStepData(
            stringResource(R.string.ai_assessment),
            if (currentStepIndex > 4) MortalityStepStatus.COMPLETED else if (currentStepIndex == 4) MortalityStepStatus.IN_PROGRESS else MortalityStepStatus.PENDING,
            if (currentStepIndex > 4) stringResource(R.string.status_completed) else if (currentStepIndex == 4) stringResource(R.string.status_in_progress) else stringResource(R.string.status_pending)
        ),
        MortalityStepData(
            stringResource(R.string.carcass_handover),
            if (currentStepIndex > 5) MortalityStepStatus.COMPLETED else if (currentStepIndex == 5) MortalityStepStatus.IN_PROGRESS else MortalityStepStatus.PENDING,
            if (currentStepIndex > 5) stringResource(R.string.status_completed) else if (currentStepIndex == 5) stringResource(R.string.status_in_progress) else stringResource(R.string.status_pending)
        ),
        MortalityStepData(
            stringResource(R.string.claim_submission),
            if (currentStepIndex > 6) MortalityStepStatus.COMPLETED else if (currentStepIndex == 6) MortalityStepStatus.IN_PROGRESS else MortalityStepStatus.PENDING,
            if (currentStepIndex > 6) stringResource(R.string.status_completed) else if (currentStepIndex == 6) stringResource(R.string.status_in_progress) else stringResource(R.string.status_pending)
        )
    )

    if (detailStepIndex != null) {
        MortalityStepDetailScreen(
            stepIndex = detailStepIndex!!,
            onBack = { detailStepIndex = null },
            onComplete = {
                currentStepIndex++
                detailStepIndex = null
                if (currentStepIndex == 7) onComplete()
            }
        )
    } else {
        Scaffold(
            modifier = Modifier.fillMaxSize().navigationBarsPadding(),
            topBar = { 
                TopAppBar(
                    title = { Text(stringResource(R.string.mortality_report), fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back),
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = PrimaryGreen,
                        titleContentColor = Color.White
                    )
                ) 
            },
            containerColor = Color(0xFFF8F9FB)
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    LazyColumn(
                        modifier = Modifier.padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(0.dp)
                    ) {
                        itemsIndexed(steps) { index, step ->
                            MortalityVerticalStepItem(
                                index = index + 1,
                                title = step.title,
                                status = step.status,
                                statusText = step.statusText,
                                isLast = index == steps.size - 1,
                                onClick = {
                                    if (index == currentStepIndex) {
                                        detailStepIndex = index
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MortalityStepDetailScreen(stepIndex: Int, onBack: () -> Unit, onComplete: () -> Unit) {
    val context = LocalContext.current
    
    var capturedPhotoUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var tempUriStr by rememberSaveable { mutableStateOf<String?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && tempUriStr != null) {
            capturedPhotoUri = Uri.parse(tempUriStr!!)
        }
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            tempUriStr?.let { cameraLauncher.launch(Uri.parse(it)) }
        } else {
            Toast.makeText(context, context.getString(R.string.camera_permission_required), Toast.LENGTH_SHORT).show()
        }
    }

    fun launchCamera() {
        try {
            val directory = File(context.cacheDir, "mortality_detail_photos")
            if (!directory.exists()) directory.mkdirs()
            val file = File(directory, "mortality_step_${stepIndex}_${System.currentTimeMillis()}.jpg")
            val uri = FileProvider.getUriForFile(
                context, 
                "${context.packageName}.fileprovider", 
                file
            )
            tempUriStr = uri.toString()
            
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                cameraLauncher.launch(uri)
            } else {
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.step_details)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(20.dp).fillMaxSize()) {
            when(stepIndex) {
                1 -> { // Site Visit
                    Text(stringResource(R.string.physical_site_visit), style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(stringResource(R.string.didi_site_visit_instruction))
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = onComplete, modifier = Modifier.fillMaxWidth()) {
                        Text(stringResource(R.string.mark_visit_complete))
                    }
                }
                2 -> { // Carcass Verification
                    Text(stringResource(R.string.carcass_verification), style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(stringResource(R.string.check_tag_breed))
                    // checklist logic here...
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = onComplete, modifier = Modifier.fillMaxWidth()) {
                        Text(stringResource(R.string.verify_continue))
                    }
                }
                3 -> { // Photo Capture
                    Text(stringResource(R.string.photo_capture), style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(stringResource(R.string.capture_carcass_instruction))
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Surface(
                        onClick = { launchCamera() },
                        modifier = Modifier.fillMaxWidth().height(250.dp),
                        shape = RoundedCornerShape(12.dp),
                        color = Color.White,
                        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            if (capturedPhotoUri != null) {
                                AsyncImage(
                                    model = capturedPhotoUri,
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(12.dp)),
                                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                                )
                            } else {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(Icons.Default.CameraAlt, null, modifier = Modifier.size(48.dp), tint = Color.Gray)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(stringResource(R.string.tap_to_capture), color = Color.Gray)
                                }
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = onComplete, enabled = capturedPhotoUri != null, modifier = Modifier.fillMaxWidth()) {
                        Text(stringResource(R.string.save_photos))
                    }
                }
                4 -> { // AI Assessment
                    Text(stringResource(R.string.ai_assessment), style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(16.dp))
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                    Text(stringResource(R.string.ai_checking_instruction), modifier = Modifier.padding(top = 16.dp))
                    
                    LaunchedEffect(Unit) {
                        kotlinx.coroutines.delay(3000)
                        onComplete()
                    }
                }
                5 -> { // Carcass Handover
                    Text(stringResource(R.string.carcass_handover), style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(stringResource(R.string.confirm_disposal_handover))
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = onComplete, modifier = Modifier.fillMaxWidth()) {
                        Text(stringResource(R.string.confirm_handover))
                    }
                }
                6 -> { // Claim Submission
                    Text(stringResource(R.string.claim_submission), style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(stringResource(R.string.final_review_submit))
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = onComplete, modifier = Modifier.fillMaxWidth()) {
                        Text(stringResource(R.string.submit_claim))
                    }
                }
            }
        }
    }
}

@Composable
fun MortalityVerticalStepItem(
    index: Int,
    title: String,
    status: MortalityStepStatus,
    statusText: String,
    isLast: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = status == MortalityStepStatus.IN_PROGRESS) { onClick() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(40.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(
                        when (status) {
                            MortalityStepStatus.COMPLETED -> PrimaryGreen
                            MortalityStepStatus.IN_PROGRESS -> PrimaryGreen
                            MortalityStepStatus.PENDING -> Color.White
                        }
                    )
                    .border(
                        width = 1.dp,
                        color = if (status == MortalityStepStatus.PENDING) Color.LightGray else PrimaryGreen,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (status == MortalityStepStatus.COMPLETED) {
                    Icon(Icons.Default.Check, null, modifier = Modifier.size(18.dp), tint = Color.White)
                } else {
                    Text(
                        text = index.toString(),
                        color = if (status == MortalityStepStatus.IN_PROGRESS) Color.White else Color.Gray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(40.dp)
                        .background(if (status == MortalityStepStatus.COMPLETED) PrimaryGreen else Color.LightGray.copy(alpha = 0.5f))
                )
            }
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(
            modifier = Modifier.padding(bottom = if (isLast) 0.dp else 24.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = if (status == MortalityStepStatus.IN_PROGRESS) FontWeight.Bold else FontWeight.Medium,
                color = if (status == MortalityStepStatus.PENDING) Color.Gray else Color.Black
            )
            Text(
                text = statusText,
                style = MaterialTheme.typography.bodySmall,
                color = when (status) {
                    MortalityStepStatus.COMPLETED -> PrimaryGreen
                    MortalityStepStatus.IN_PROGRESS -> PrimaryGreen
                    MortalityStepStatus.PENDING -> Color.Gray
                },
                fontWeight = if (status == MortalityStepStatus.IN_PROGRESS) FontWeight.SemiBold else FontWeight.Normal
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FarmerReportDeathScreen(onBack: () -> Unit, onComplete: () -> Unit) {
    val context = LocalContext.current
    var isConfirmed by remember { mutableStateOf(false) }
    
    var deathDate by remember { mutableStateOf("") }
    var deathTime by remember { mutableStateOf("") }

    val goats = listOf(
        Triple("ET-340801-0001", "Black Bengal", "12M"),
        Triple("ET-240801-0002", "Jamunapari", "18M"),
        Triple("ET-140801-0004", "Sirohi", "24M")
    )
    var selectedGoat by remember { mutableStateOf(goats[0]) }
    var expanded by remember { mutableStateOf(false) }

    val calendar = Calendar.getInstance()
    var capturedPhotoUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var tempUriStr by rememberSaveable { mutableStateOf<String?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && tempUriStr != null) {
            capturedPhotoUri = Uri.parse(tempUriStr!!)
        }
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            tempUriStr?.let { cameraLauncher.launch(Uri.parse(it)) }
        } else {
            Toast.makeText(context, context.getString(R.string.camera_permission_required), Toast.LENGTH_SHORT).show()
        }
    }

    fun launchCamera() {
        try {
            val directory = File(context.cacheDir, "death_photos")
            if (!directory.exists()) directory.mkdirs()
            val file = File(directory, "death_${selectedGoat.first}_${System.currentTimeMillis()}.jpg")
            val uri = FileProvider.getUriForFile(
                context, 
                "${context.packageName}.fileprovider", 
                file
            )
            tempUriStr = uri.toString()
            
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                cameraLauncher.launch(uri)
            } else {
                cameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize().navigationBarsPadding(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.report_goat_death), color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.back), tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryBlue)
            )
        },
        containerColor = Color(0xFFF8F9F5)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Select Goat
            Text(
                buildAnnotatedString {
                    append(stringResource(R.string.select_goat))
                    withStyle(style = SpanStyle(color = Color.Red)) { append(" *") }
                },
                fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            
            Box {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { expanded = true },
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            modifier = Modifier.size(56.dp),
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFFF0F0F0)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(painterResource(R.drawable.ic_ewe_custom), null, tint = Color.Gray, modifier = Modifier.size(28.dp))
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            val blackBengal = stringResource(R.string.black_bengal)
                            val female = stringResource(R.string.female)
                            val age = stringResource(R.string.age_months_suffix, 12)
                            Text(selectedGoat.first, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)
                            Text(stringResource(R.string.breed_gender_age_format, blackBengal, female, age), color = Color.Gray, fontSize = 13.sp)
                        }
                        Icon(Icons.Default.ArrowDropDown, null, tint = Color.LightGray)
                    }
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth(0.85f).background(Color.White)
                ) {
                    goats.forEach { goat ->
                        DropdownMenuItem(
                            text = { 
                                Column {
                                    Text(goat.first, fontWeight = FontWeight.Bold, color = Color.Black)
                                    Text("${goat.second} • ${goat.third}", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                                }
                            },
                            onClick = {
                                selectedGoat = goat
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Date of Death
            EnrollmentTextField(
                label = stringResource(R.string.date_of_death),
                value = deathDate,
                onValueChange = { deathDate = it },
                placeholder = "DD/MM/YYYY",
                trailingIcon = Icons.Default.CalendarToday,
                readOnly = true,
                onTrailingIconClick = {
                    DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            deathDate = "$dayOfMonth/${month + 1}/$year"
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Time of Death
            EnrollmentTextField(
                label = stringResource(R.string.time_of_death),
                value = deathTime,
                onValueChange = { deathTime = it },
                placeholder = "HH:MM AM/PM",
                trailingIcon = Icons.Default.History,
                readOnly = true,
                onTrailingIconClick = {
                    TimePickerDialog(
                        context,
                        { _, hourOfDay, minute ->
                            val amPm = if (hourOfDay < 12) "AM" else "PM"
                            val hour = if (hourOfDay % 12 == 0) 12 else hourOfDay % 12
                            deathTime = String.format("%02d:%02d %s", hour, minute, amPm)
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        false
                    ).show()
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Photo Upload
            Text(
                stringResource(R.string.upload_photo_optional),
                fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                modifier = Modifier.size(100.dp),
                shape = RoundedCornerShape(12.dp),
                color = Color.White,
                border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f)),
                onClick = { launchCamera() }
            ) {
                Box(contentAlignment = Alignment.Center) {
                    if (capturedPhotoUri != null) {
                        AsyncImage(
                            model = capturedPhotoUri,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(12.dp)),
                            contentScale = androidx.compose.ui.layout.ContentScale.Crop
                        )
                    } else {
                        Icon(Icons.Default.CameraAlt, null, tint = Color.Black, modifier = Modifier.size(32.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Checkbox
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isConfirmed,
                    onCheckedChange = { isConfirmed = it },
                    colors = CheckboxDefaults.colors(checkedColor = PrimaryBlue)
                )
                Text(
                    stringResource(R.string.confirm_info_correct),
                    fontSize = 13.sp, color = Color.Black
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(32.dp))
            
            Button(
                onClick = onComplete,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                enabled = isConfirmed && deathDate.isNotBlank() && deathTime.isNotBlank(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
            ) {
                Text(stringResource(R.string.submit_alert), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun ClaimStatusTracker() {
    val stages = listOf(
        stringResource(R.string.death_reported),
        stringResource(R.string.didi_visit),
        stringResource(R.string.verification),
        stringResource(R.string.coordinator_review),
        stringResource(R.string.payment_sent)
    )
    val currentStage = 2
    Column(modifier = Modifier.fillMaxSize().navigationBarsPadding().padding(16.dp)) {
        Text(stringResource(R.string.claim_id_placeholder, "CLM7890"), style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        stages.forEachIndexed { index, stage ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(24.dp).clip(CircleShape).background(if (index <= currentStage) SuccessGreen else Color.LightGray))
                Text(stage, modifier = Modifier.padding(16.dp), color = if (index <= currentStage) Color.Black else Color.Gray)
            }
        }
    }
}

// --- SUB-COMPONENTS ---

@Composable
fun BreakdownItem(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label)
        Text(value, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun MortalityStep1(photoUri: Uri?, onCapture: () -> Unit) {
    Text(stringResource(R.string.carcass_verification_step_1), style = MaterialTheme.typography.titleLarge)
    Spacer(modifier = Modifier.height(8.dp))
    Text(stringResource(R.string.capture_carcass_instruction), color = Color.Gray)
    Spacer(modifier = Modifier.height(24.dp))
    Surface(
        onClick = onCapture,
        modifier = Modifier.fillMaxWidth().height(250.dp),
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
    ) {
        Box(contentAlignment = Alignment.Center) {
            if (photoUri != null) {
                AsyncImage(
                    model = photoUri,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(12.dp)),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                )
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.CameraAlt, null, modifier = Modifier.size(48.dp), tint = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(stringResource(R.string.tap_to_capture), color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun MortalityStep2() { 
    Text(stringResource(R.string.check_tag_breed_step_2)) 
}
@Composable
fun MortalityStep3(photoUri: Uri?, onCapture: () -> Unit) { 
    Text(stringResource(R.string.capture_farmer_site_photo_step_3), style = MaterialTheme.typography.titleLarge) 
    Spacer(modifier = Modifier.height(8.dp))
    Text(stringResource(R.string.capture_farmer_site_instruction), color = Color.Gray)
    Spacer(modifier = Modifier.height(24.dp))
    Surface(
        onClick = onCapture,
        modifier = Modifier.fillMaxWidth().height(250.dp),
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
    ) {
        Box(contentAlignment = Alignment.Center) {
            if (photoUri != null) {
                AsyncImage(
                    model = photoUri,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(12.dp)),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                )
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.CameraAlt, null, modifier = Modifier.size(48.dp), tint = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(stringResource(R.string.tap_to_capture), color = Color.Gray)
                }
            }
        }
    }
}
@Composable
fun MortalityStep4() { 
    Text(stringResource(R.string.cause_of_death_sign_step_4))
}

@Composable
fun StepProgressIndicator(currentStep: Int, totalSteps: Int) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalSteps) { index ->
            val step = index + 1
            val isCompleted = step < currentStep
            val isCurrent = step == currentStep
            
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(if (isCompleted || isCurrent) PrimaryGreen else Color.LightGray.copy(alpha = 0.3f))
                    .border(1.dp, if (isCompleted || isCurrent) PrimaryGreen else Color.LightGray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (isCompleted) {
                    Icon(Icons.Default.Check, null, modifier = Modifier.size(16.dp), tint = Color.White)
                } else {
                    Text(
                        text = step.toString(),
                        color = if (isCurrent) Color.White else Color.Gray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            if (index < totalSteps - 1) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(2.dp)
                        .background(if (step < currentStep) PrimaryGreen else Color.LightGray.copy(alpha = 0.3f))
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnrollmentStepper(onBack: () -> Unit, onComplete: () -> Unit) {
    var currentStep by rememberSaveable { mutableIntStateOf(1) }
    
    // Form State
    var farmerName by rememberSaveable { mutableStateOf("") }
    var mobileNumber by rememberSaveable { mutableStateOf("") }
    var village by rememberSaveable { mutableStateOf("") }
    var location by rememberSaveable { mutableStateOf("") }
    var aadhaar by rememberSaveable { mutableStateOf("") }
    
    var breed by rememberSaveable { mutableStateOf("Black Bengal") }
    var gender by rememberSaveable { mutableStateOf("") }
    var age by rememberSaveable { mutableStateOf("") }
    var ageUnit by rememberSaveable { mutableStateOf("") }
    var weight by rememberSaveable { mutableStateOf("") }
    var colorMarks by rememberSaveable { mutableStateOf("") }
    
    var earTagNumber by rememberSaveable { mutableStateOf("") }
    
    // Photo State
    var leftPhotoUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var rightPhotoUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var frontPhotoUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var tagPhotoUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    
    val steps = listOf(
        stringResource(R.string.farmer_information),
        stringResource(R.string.goat_details),
        stringResource(R.string.photo_capture),
        stringResource(R.string.ear_tagging),
        stringResource(R.string.vaccination_history),
        stringResource(R.string.premium_payment),
        stringResource(R.string.policy_generated)
    )

    val femaleLabel = stringResource(R.string.female)
    val monthsLabel = stringResource(R.string.months)
    
    if (gender.isEmpty()) gender = femaleLabel
    if (ageUnit.isEmpty()) ageUnit = monthsLabel

    Scaffold(
        modifier = Modifier.fillMaxSize().navigationBarsPadding(),
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black
                ),
                title = { 
                    Text(
                        stringResource(R.string.enrollment_step_n, currentStep),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = { if (currentStep > 1) currentStep-- else onBack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().background(Color.White).padding(horizontal = 20.dp)) {
            StepProgressIndicator(currentStep, 7)
            
            val scrollState = rememberScrollState()
            LaunchedEffect(currentStep) {
                scrollState.scrollTo(0)
            }

            Column(modifier = Modifier.weight(1f).verticalScroll(scrollState)) {
                Text(
                    text = steps[currentStep-1],
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                when(currentStep) {
                    1 -> EnrollmentFarmerStep(farmerName, { if (it.all { char -> char.isLetter() || char.isWhitespace() }) farmerName = it }, mobileNumber, { if (it.length <= 10) mobileNumber = it }, village, { if (it.all { char -> char.isLetter() || char.isWhitespace() }) village = it }, location, { location = it }, aadhaar, { if (it.length <= 12) aadhaar = it })
                    2 -> EnrollmentGoatStep(breed, { breed = it }, gender, { gender = it }, age, { age = it }, ageUnit, { ageUnit = it }, weight, { weight = it }, colorMarks, { colorMarks = it })
                    3 -> EnrollmentPhotoStep(
                        leftUri = leftPhotoUri, onLeftCapture = { leftPhotoUri = it },
                        rightUri = rightPhotoUri, onRightCapture = { rightPhotoUri = it },
                        frontUri = frontPhotoUri, onFrontCapture = { frontPhotoUri = it },
                        tagUri = tagPhotoUri, onTagCapture = { tagPhotoUri = it }
                    )
                    4 -> EnrollmentTaggingStep(earTagNumber) { earTagNumber = it }
                    5 -> EnrollmentVaccinationStep()
                    6 -> EnrollmentPaymentStep()
                    7 -> EnrollmentPolicyStep(farmerName, earTagNumber)
                }
            }

            val isStepValid = when(currentStep) {
                1 -> farmerName.isNotBlank() && mobileNumber.length == 10 && village.isNotBlank() && location.isNotBlank() && aadhaar.length == 12
                2 -> breed.isNotBlank() && gender.isNotBlank() && age.isNotBlank() && weight.isNotBlank() && colorMarks.isNotBlank()
                3 -> leftPhotoUri != null && rightPhotoUri != null && frontPhotoUri != null && tagPhotoUri != null
                4 -> earTagNumber.isNotBlank()
                else -> true
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (currentStep in 2..6) {
                    OutlinedButton(
                        onClick = { currentStep-- },
                        modifier = Modifier.weight(1f).height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, PrimaryGreen)
                    ) {
                        Text(stringResource(R.string.back), color = PrimaryGreen, fontWeight = FontWeight.Bold)
                    }
                }
                
                Button(
                    onClick = { if (currentStep < 7) currentStep++ else onComplete() },
                    enabled = isStepValid,
                    modifier = Modifier.weight(1f).height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen, disabledContainerColor = PrimaryGreen.copy(alpha = 0.5f))
                ) {
                    Text(
                        text = if (currentStep == 7) stringResource(R.string.finish_enrollment) 
                               else stringResource(R.string.next),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun EnrollmentFarmerStep(name: String, onNameChange: (String) -> Unit, phone: String, onPhoneChange: (String) -> Unit, village: String, onVillageChange: (String) -> Unit, location: String, onLocationChange: (String) -> Unit, aadhaar: String, onAadhaarChange: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        EnrollmentTextField(label = stringResource(R.string.farmer_name), value = name, onValueChange = onNameChange, placeholder = stringResource(R.string.full_name_placeholder))
        EnrollmentTextField(label = stringResource(R.string.mobile_number), value = phone, onValueChange = onPhoneChange, placeholder = stringResource(R.string.mobile_number_placeholder), keyboardType = KeyboardType.Phone, prefix = "+91 ")
        EnrollmentTextField(label = stringResource(R.string.village), value = village, onValueChange = onVillageChange, placeholder = stringResource(R.string.village_placeholder))
        EnrollmentTextField(label = stringResource(R.string.gps_location), value = location, onValueChange = onLocationChange, placeholder = stringResource(R.string.gps_location_placeholder), trailingIcon = Icons.Default.LocationOn)
        EnrollmentTextField(label = stringResource(R.string.aadhaar_gov_id), value = aadhaar, onValueChange = onAadhaarChange, placeholder = stringResource(R.string.aadhaar_placeholder), keyboardType = KeyboardType.Number)
    }
}

@Composable
fun EnrollmentGoatStep(breed: String, onBreedChange: (String) -> Unit, gender: String, onGenderChange: (String) -> Unit, age: String, onAgeChange: (String) -> Unit, ageUnit: String, onAgeUnitChange: (String) -> Unit, weight: String, onWeightChange: (String) -> Unit, color: String, onColorChange: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        EnrollmentDropdownField(
            label = stringResource(R.string.breed),
            selectedValue = breed,
            options = listOf(
                stringResource(R.string.black_bengal),
                stringResource(R.string.jamunapari),
                stringResource(R.string.sirohi),
                stringResource(R.string.barbari),
                stringResource(R.string.beetal),
                stringResource(R.string.ganjam),
                stringResource(R.string.osmanabadi),
                stringResource(R.string.anjori)
            ),
            onValueChange = onBreedChange
        )
        EnrollmentDropdownField(
            label = stringResource(R.string.gender),
            selectedValue = gender,
            options = listOf(
                stringResource(R.string.female),
                stringResource(R.string.male)
            ),
            onValueChange = onGenderChange
        )
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Box(modifier = Modifier.weight(0.6f)) {
                EnrollmentTextField(label = stringResource(R.string.age_approx), value = age, onValueChange = onAgeChange, placeholder = stringResource(R.string.age_placeholder), keyboardType = KeyboardType.Number)
            }
            Box(modifier = Modifier.weight(0.4f)) {
                EnrollmentDropdownField(
                    label = stringResource(R.string.unit),
                    selectedValue = ageUnit,
                    options = listOf(
                        stringResource(R.string.months),
                        stringResource(R.string.years)
                    ),
                    onValueChange = onAgeUnitChange
                )
            }
        }

        EnrollmentTextField(label = stringResource(R.string.weight_approx), value = weight, onValueChange = onWeightChange, placeholder = stringResource(R.string.weight_placeholder), keyboardType = KeyboardType.Number, suffix = "KG")
        EnrollmentTextField(label = stringResource(R.string.color_marks), value = color, onValueChange = onColorChange, placeholder = stringResource(R.string.color_marks_placeholder))
    }
}

@Composable
fun EnrollmentPhotoStep(
    leftUri: Uri?, onLeftCapture: (Uri) -> Unit,
    rightUri: Uri?, onRightCapture: (Uri) -> Unit,
    frontUri: Uri?, onFrontCapture: (Uri) -> Unit,
    tagUri: Uri?, onTagCapture: (Uri) -> Unit
) {
    val context = LocalContext.current

    var tempUriStr by rememberSaveable { mutableStateOf<String?>(null) }
    var captureType by rememberSaveable { mutableStateOf("") }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && tempUriStr != null) {
            val uri = Uri.parse(tempUriStr!!)
            when (captureType) {
                "left" -> onLeftCapture(uri)
                "right" -> onRightCapture(uri)
                "front" -> onFrontCapture(uri)
                "tag" -> onTagCapture(uri)
            }
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            tempUriStr?.let { cameraLauncher.launch(Uri.parse(it)) }
        } else {
            Toast.makeText(context, context.getString(R.string.camera_permission_required), Toast.LENGTH_SHORT).show()
        }
    }

    fun launchCamera(type: String) {
        try {
            captureType = type
            val directory = File(context.cacheDir, "enroll_photos")
            if (!directory.exists()) directory.mkdirs()
            val file = File(directory, "img_${type}_${System.currentTimeMillis()}.jpg")
            val uri = FileProvider.getUriForFile(
                context, 
                "${context.packageName}.fileprovider", 
                file
            )
            tempUriStr = uri.toString()
            
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                cameraLauncher.launch(uri)
            } else {
                permissionLauncher.launch(android.Manifest.permission.CAMERA)
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    Column {
        Text(
            stringResource(R.string.upload_4_photos_instruction),
            style = MaterialTheme.typography.bodyMedium, color = Color.Gray
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            PhotoCaptureBox(
                stringResource(R.string.left_side),
                uri = leftUri,
                modifier = Modifier.weight(1f)
            ) {
                launchCamera("left")
            }
            PhotoCaptureBox(
                stringResource(R.string.right_side),
                uri = rightUri,
                modifier = Modifier.weight(1f)
            ) {
                launchCamera("right")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            PhotoCaptureBox(
                stringResource(R.string.front_view),
                uri = frontUri,
                modifier = Modifier.weight(1f)
            ) {
                launchCamera("front")
            }
            PhotoCaptureBox(
                stringResource(R.string.ear_tag),
                uri = tagUri,
                modifier = Modifier.weight(1f)
            ) {
                launchCamera("tag")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = InfoBlue.copy(alpha = 0.1f)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Info, null, tint = InfoBlue, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    stringResource(R.string.ensure_clear_photos),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun EnrollmentTaggingStep(earTag: String, onTagChange: (String) -> Unit) {
    val context = LocalContext.current
    
    val scanLauncher = rememberLauncherForActivityResult(
        contract = com.journeyapps.barcodescanner.ScanContract()
    ) { result ->
        if (result.contents != null) {
            onTagChange(result.contents)
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        EnrollmentTextField(label = stringResource(R.string.ear_tag_number), value = earTag, onValueChange = onTagChange, placeholder = stringResource(R.string.ear_tag_number_placeholder))
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            stringResource(R.string.scan_qr_code_optional),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Surface(
            onClick = { 
                val options = com.journeyapps.barcodescanner.ScanOptions()
                options.setDesiredBarcodeFormats(com.journeyapps.barcodescanner.ScanOptions.QR_CODE)
                options.setPrompt(context.getString(R.string.scan_ear_tag_qr))
                options.setBeepEnabled(true)
                options.setOrientationLocked(false)
                scanLauncher.launch(options)
            },
            modifier = Modifier.fillMaxWidth().height(160.dp),
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, 
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Default.QrCodeScanner, 
                    contentDescription = null, 
                    tint = Color.Black, 
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    stringResource(R.string.tap_to_scan), 
                    color = Color.Gray, 
                    fontSize = 14.sp
                )
            }
        }
        
        if (earTag.isNotBlank()) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.CheckCircle, 
                    contentDescription = null, 
                    tint = PrimaryGreen, 
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    stringResource(R.string.ear_tag_verified_successfully),
                    color = PrimaryGreen,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun EnrollmentVaccinationStep() {
    var pprGiven by remember { mutableStateOf(true) }
    var etttGiven by remember { mutableStateOf(true) }
    var fmdGiven by remember { mutableStateOf(false) }
    var poxGiven by remember { mutableStateOf(false) }
    
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        VaccineStatusItem(stringResource(R.string.vaccine_ppr), pprGiven) { pprGiven = it }
        VaccineStatusItem(stringResource(R.string.vaccine_ettt), etttGiven) { etttGiven = it }
        VaccineStatusItem(stringResource(R.string.vaccine_fmd), fmdGiven) { fmdGiven = it }
        VaccineStatusItem(stringResource(R.string.vaccine_goat_pox), poxGiven) { poxGiven = it }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F7F8)),
            border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f))
        ) {
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.CalendarToday, null, tint = Color.DarkGray, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(stringResource(R.string.next_vaccination_due), fontSize = 12.sp, color = Color.Gray)
                    Text("15 Aug 2024", fontWeight = FontWeight.Bold, color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun EnrollmentPaymentStep() {
    var selectedMethod by remember { mutableStateOf("Cash") }
    
    Column {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9))
        ) {
            Column(
                modifier = Modifier.padding(24.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(stringResource(R.string.total_premium_amount), fontSize = 14.sp, color = Color.Gray)
                Text(stringResource(R.string.total_premium_with_value_rs, "350"), style = MaterialTheme.typography.headlineLarge, color = PrimaryGreen, fontWeight = FontWeight.Bold)
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        Text(stringResource(R.string.select_payment_method), fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            PaymentMethodChip(stringResource(R.string.payment_method_cash), selectedMethod == "Cash", modifier = Modifier.weight(1f)) { selectedMethod = "Cash" }
            PaymentMethodChip(stringResource(R.string.payment_method_upi), selectedMethod == "UPI", modifier = Modifier.weight(1f)) { selectedMethod = "UPI" }
        }
        
        if (selectedMethod == "UPI") {
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
            ) {
                Column(
                    modifier = Modifier.padding(24.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(stringResource(R.string.scan_to_pay), fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .background(Color.White, RoundedCornerShape(12.dp))
                            .border(1.dp, Color.LightGray.copy(alpha = 0.3f), RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_upi_qr),
                            contentDescription = "UPI QR Code",
                            modifier = Modifier.size(180.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Pay via any UPI App", fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        EnrollmentTextField(label = stringResource(R.string.premium_amount_editable), value = "350", onValueChange = {}, prefix = "₹")
        Spacer(modifier = Modifier.height(16.dp))
        EnrollmentTextField(label = stringResource(R.string.receipt_number), value = "RCP-240801-001", onValueChange = {}, leadingIcon = Icons.Default.Receipt)
    }
}

@Composable
fun EnrollmentPolicyStep(farmer: String, tag: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier.size(64.dp).clip(CircleShape).background(Color(0xFFE8F5E9)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.VerifiedUser, null, tint = SuccessGreen, modifier = Modifier.size(32.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(R.string.policy_generated_successfully), fontWeight = FontWeight.Bold, fontSize = 18.sp)
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f))
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                PolicyDetailRow(stringResource(R.string.policy_number), stringResource(R.string.policy_number_value))
                PolicyDetailRow(stringResource(R.string.farmer_name), farmer.ifBlank { stringResource(R.string.farmer_name_placeholder) })
                PolicyDetailRow(stringResource(R.string.ear_tag), tag.ifBlank { stringResource(R.string.ear_tag_value) })
                PolicyDetailRow(stringResource(R.string.premium_paid), stringResource(R.string.premium_paid_value))
                PolicyDetailRow(stringResource(R.string.validity), stringResource(R.string.validity_value))
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                PolicyDetailRow(stringResource(R.string.sum_insured), stringResource(R.string.sum_insured_value), true)
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        OutlinedButton(
            onClick = { /* Download */ },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, PrimaryGreen)
        ) {
            Icon(Icons.Default.FileDownload, null, tint = PrimaryGreen)
            Spacer(modifier = Modifier.width(8.dp))
            Text(stringResource(R.string.download_certificate), color = PrimaryGreen, fontWeight = FontWeight.Bold)
        }
    }
}

// --- ENROLLMENT HELPERS ---

@Composable
fun EnrollmentTextField(label: String, value: String, onValueChange: (String) -> Unit, placeholder: String = "", keyboardType: KeyboardType = KeyboardType.Text, prefix: String? = null, suffix: String? = null, leadingIcon: ImageVector? = null, trailingIcon: ImageVector? = null, onTrailingIconClick: (() -> Unit)? = null, readOnly: Boolean = false, borderColor: Color = PrimaryGreen) {
    val styledLabel = buildAnnotatedString {
        label.forEach { char ->
            if (char == '*') {
                withStyle(style = SpanStyle(color = Color.Red)) {
                    append(char)
                }
            } else {
                append(char)
            }
        }
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(styledLabel, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(bottom = 8.dp))
        Box {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(placeholder, color = Color.Gray) },
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                singleLine = true,
                readOnly = readOnly,
                leadingIcon = leadingIcon?.let { { Icon(it, null, tint = Color.DarkGray) } },
                trailingIcon = trailingIcon?.let { 
                    { 
                        if (onTrailingIconClick != null) {
                            IconButton(onClick = onTrailingIconClick) {
                                Icon(it, null, tint = Color.DarkGray)
                            }
                        } else {
                            Icon(it, null, tint = Color.DarkGray)
                        }
                    } 
                },
                prefix = prefix?.let { { Text(it, color = Color.Black) } },
                suffix = suffix?.let { { Text(it, color = Color.Black) } },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
                    focusedBorderColor = borderColor
                )
            )
            if (readOnly && onTrailingIconClick != null) {
                Box(modifier = Modifier.matchParentSize().clickable { onTrailingIconClick() })
            }
        }
    }
}

@Composable
fun EnrollmentDropdownField(label: String, selectedValue: String, options: List<String>, onValueChange: (String) -> Unit, borderColor: Color = PrimaryGreen) {
    var expanded by remember { mutableStateOf(false) }
    val styledLabel = buildAnnotatedString {
        label.forEach { char ->
            if (char == '*') {
                withStyle(style = SpanStyle(color = Color.Red)) {
                    append(char)
                }
            } else {
                append(char)
            }
        }
    }
    
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(styledLabel, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(bottom = 8.dp))
        Box {
            OutlinedTextField(
                value = selectedValue,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                trailingIcon = {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Color.DarkGray)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
                    focusedBorderColor = borderColor
                )
            )
            // Invisible clickable overlay to trigger dropdown
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable { expanded = true }
            )
            
            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme.copy(surface = Color.White)
            ) {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth(0.9f).background(Color.White)
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option, color = Color.Black) },
                            onClick = {
                                onValueChange(option)
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
fun PhotoCaptureBox(label: String, uri: Uri?, modifier: Modifier = Modifier, onCapture: () -> Unit) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            onClick = onCapture,
            modifier = Modifier.aspectRatio(1.2f).fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                if (uri != null) {
                    AsyncImage(
                        model = uri,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(16.dp)),
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop
                    )
                } else {
                    Icon(Icons.Default.AddAPhoto, null, tint = Color.Gray, modifier = Modifier.size(32.dp))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Capture", fontSize = 11.sp, color = Color.Gray)
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(label, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color.Black)
    }
}

@Composable
fun VaccineStatusItem(name: String, isGiven: Boolean, themeColor: Color = PrimaryGreen, onToggle: (Boolean) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onToggle(!isGiven) },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier.padding(16.dp), 
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (isGiven) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                contentDescription = null,
                tint = if (isGiven) themeColor else Color.Gray,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(name, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.weight(1f))
            Surface(
                color = if (isGiven) themeColor.copy(alpha = 0.1f) else Color(0xFFFFF3E0),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = if (isGiven) stringResource(R.string.given) 
                           else stringResource(R.string.pending),
                    color = if (isGiven) themeColor else AccentOrange,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
fun PaymentMethodChip(label: String, isSelected: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Surface(
        onClick = { onClick() },
        modifier = modifier.height(48.dp),
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) PrimaryGreen else Color.White,
        border = if (isSelected) null else BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(label, color = if (isSelected) Color.White else Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun PolicyDetailRow(label: String, value: String, isBold: Boolean = false) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = Color.Gray, fontSize = 14.sp)
        Text(value, fontWeight = if (isBold) FontWeight.ExtraBold else FontWeight.Bold, fontSize = if (isBold) 16.sp else 14.sp)
    }
}


@Composable
fun FarmerDashboard(navController: NavHostController, sessionManager: SessionManager) {
    var showNotifications by remember { mutableStateOf(false) }
    if (showNotifications) NotificationSheet(themeColor = PrimaryBlue) { showNotifications = false }
    val userName by sessionManager.userName.collectAsState(initial = "Ramesh Naik")

    ResponsiveLayout(
        compact = {
            Scaffold(
                modifier = Modifier.fillMaxSize().navigationBarsPadding(),
                bottomBar = { FarmerBottomBar(navController) },
                contentWindowInsets = WindowInsets(0, 0, 0, 0)
            ) { padding ->
                FarmerContent(padding, navController, userName ?: "Ramesh Naik") { showNotifications = true }
            }
        },
        expanded = {
            Row(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing).navigationBarsPadding()) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                
                NavigationRail(
                    containerColor = Color.White,
                    header = {
                        IconButton(onClick = { navController.navigate("profile") }) {
                            Icon(Icons.Default.Person, null, tint = PrimaryBlue)
                        }
                    }
                ) {
                    NavigationRailItem(
                        selected = true, 
                        onClick = { }, 
                        icon = { Icon(Icons.Default.Home, null) }, 
                        label = { Text(stringResource(R.string.home)) },
                        colors = NavigationRailItemDefaults.colors(
                            selectedIconColor = PrimaryBlue,
                            selectedTextColor = PrimaryBlue,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray,
                            indicatorColor = Color.Transparent
                        )
                    )
                    NavigationRailItem(
                        selected = false, 
                        onClick = { navController.navigate("goat_list") }, 
                        icon = { Icon(painterResource(R.drawable.ic_ewe_custom), null) }, 
                        label = { Text(stringResource(R.string.my_goats)) },
                        colors = NavigationRailItemDefaults.colors(
                            selectedIconColor = PrimaryBlue,
                            selectedTextColor = PrimaryBlue,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
                FarmerContent(PaddingValues(0.dp), navController, userName ?: "Ramesh Naik") { showNotifications = true }
            }
        }
    )
}

@Composable
fun FarmerContent(padding: PaddingValues, navController: NavHostController, userName: String, onNotificationClick: () -> Unit) {
    val context = LocalContext.current
    
    // Blue Theme Palette for Farmer
    val lightBlue = Color(0xFFE3F2FD)
    val medBlue = Color(0xFF64B5F6)
    val deepBlue = Color(0xFF1976D2)
    val skyBlue = Color(0xFFB3E5FC)
    val royalBlue = Color(0xFF0D47A1)

    var showVaccineSchedule by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(bottom = padding.calculateBottomPadding())
            .fillMaxSize()
            .background(Color(0xFFF8F9F5)) // Reverted to off-white background
    ) {
        FarmerHeader(
            navController,
            userName,
            stringResource(R.string.role_farmer),
            onNotificationClick,
            hasNotifications = true,
            onProfileClick = { navController.navigate("profile") }
        )
        
        val window = LocalWindowSizeClass.current
        val isCompact = window?.widthSizeClass == WindowWidthSizeClass.Compact
        val gridColumns = if (isCompact) 6 else 12

        LazyVerticalGrid(
            columns = GridCells.Fixed(gridColumns),
            modifier = Modifier.weight(1f).fillMaxWidth(),
            contentPadding = PaddingValues(bottom = 32.dp, start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item(span = { GridItemSpan(gridColumns) }) {
                Column {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        stringResource(R.string.insurance_summary),
                        style = MaterialTheme.typography.titleMedium,
                        color = royalBlue,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            // Stats (Top 2)
            val statSpan = if (isCompact) 3 else 6
            items(2, span = { GridItemSpan(statSpan) }) { index ->
                when(index) {
                    0 -> StatCard(stringResource(R.string.active_policies), "02", Icons.AutoMirrored.Filled.Assignment, royalBlue, skyBlue)
                    1 -> StatCard(stringResource(R.string.total_goats), "05", painterResource(R.drawable.ic_ewe_custom), PrimaryGreen, CardLightGreen)
                }
            }

            // Next Vaccination Due Banner
            item(span = { GridItemSpan(gridColumns) }) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, medBlue.copy(alpha = 0.3f)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                stringResource(R.string.next_vaccination_due),
                                fontSize = 13.sp,
                                color = deepBlue,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                stringResource(R.string.today_at, "15 Aug 2024"),
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                fontSize = 15.sp
                            )
                        }
                        OutlinedButton(
                            onClick = { 
                                showVaccineSchedule = !showVaccineSchedule
                            },
                            border = BorderStroke(1.dp, deepBlue),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = deepBlue),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
                        ) {
                            Text(
                                if (showVaccineSchedule) stringResource(R.string.hide) 
                                else stringResource(R.string.view),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            if (showVaccineSchedule) {
                item(span = { GridItemSpan(gridColumns) }) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            stringResource(R.string.upcoming_vaccinations),
                            style = MaterialTheme.typography.titleSmall,
                            color = royalBlue,
                            fontWeight = FontWeight.Bold
                        )
                        
                        val schedules = listOf(
                            Triple(stringResource(R.string.vaccine_ppr), "15 Aug 2024", "ET-340801-0001"),
                            Triple(stringResource(R.string.vaccine_ettt), "20 Aug 2024", "ET-340801-0006"),
                            Triple(stringResource(R.string.vaccine_fmd), "05 Sep 2024", "ET-240801-0002"),
                            Triple(stringResource(R.string.vaccine_goat_pox), "12 Sep 2024", "ET-340801-0005"),
                            Triple(stringResource(R.string.vaccine_ppr_booster), "20 Sep 2024", "ET-240801-0002")
                        )
                        
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 200.dp)
                                .verticalScroll(rememberScrollState()),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            schedules.forEach { (name, date, tag) ->
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = Color.White),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                                    shape = RoundedCornerShape(12.dp),
                                    border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.2f))
                                ) {
                                    Row(
                                        modifier = Modifier.padding(16.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column {
                                            Text(name, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)
                                            Text(tag, fontSize = 12.sp, color = Color.Gray)
                                        }
                                        Text(date, fontWeight = FontWeight.SemiBold, color = deepBlue, fontSize = 13.sp)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            item(span = { GridItemSpan(gridColumns) }) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            stringResource(R.string.my_policies_count, 2),
                            style = MaterialTheme.typography.titleMedium,
                            color = royalBlue,
                            fontWeight = FontWeight.Bold
                        )
                        TextButton(onClick = { navController.navigate("goat_list") }) {
                            Text(stringResource(R.string.view_all), color = deepBlue)
                        }
                    }
                }
            }

            // Policy Card (Single)
            item(span = { GridItemSpan(gridColumns) }) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                modifier = Modifier.size(70.dp),
                                shape = RoundedCornerShape(12.dp),
                                color = Color(0xFFF0F0F0)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(painterResource(R.drawable.ic_ewe_custom), null, tint = Color.Gray, modifier = Modifier.size(32.dp))
                                }
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                val blackBengal = stringResource(R.string.black_bengal)
                                val female = stringResource(R.string.female)
                                val age = stringResource(R.string.age_months_suffix, 12)
                                Text("ET-340801-0001", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)
                                Text(stringResource(R.string.breed_gender_age_format, blackBengal, female, age), color = Color.Gray, fontSize = 12.sp)
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(stringResource(R.string.policy_active), color = SuccessGreen, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                Text(stringResource(R.string.valid_till, "31 May 2025"), fontSize = 11.sp, color = Color.Gray)
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            Button(
                                onClick = { navController.navigate("farmer_report_death") },
                                modifier = Modifier.weight(1f).height(40.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(stringResource(R.string.report_death), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }
                            Button(
                                onClick = { navController.navigate("goat_details/ET-340801-0001") },
                                modifier = Modifier.weight(1f).height(40.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen),
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(stringResource(R.string.view_policy), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun FarmerHeader(navController: NavHostController, name: String, role: String, onNotificationClick: () -> Unit = {}, hasNotifications: Boolean = false, onProfileClick: () -> Unit = {}) {
    val languageState = LocalAppLanguage.current
    val profileImageState = LocalProfileImage.current
    Surface(
        color = PrimaryBlue,
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
                color = Color.White.copy(alpha = 0.2f),
                shape = CircleShape,
                modifier = Modifier.size(64.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    if (profileImageState.value != null) {
                        AsyncImage(
                            model = profileImageState.value,
                            contentDescription = "Profile",
                            modifier = Modifier.fillMaxSize().clip(CircleShape),
                            contentScale = androidx.compose.ui.layout.ContentScale.Crop
                        )
                    } else {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Profile",
                            modifier = Modifier.size(40.dp),
                            tint = Color.White
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(R.string.welcome_comma),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$name 👋",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = role,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
            
            Surface(
                onClick = onNotificationClick,
                color = Color.White.copy(alpha = 0.15f),
                shape = CircleShape,
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        tint = Color.White
                    )
                    if (hasNotifications) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .align(Alignment.TopEnd)
                                .offset(x = (-8).dp, y = 8.dp)
                                .clip(CircleShape)
                                .background(Color.Red)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DashboardHeader(navController: NavHostController, name: String, role: String, onNotificationClick: () -> Unit = {}, hasNotifications: Boolean = false, onProfileClick: () -> Unit = {}) {
    val profileImageState = LocalProfileImage.current
    Surface(
        color = PrimaryGreen,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 56.dp, bottom = 28.dp, start = 20.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Image
            Surface(
                onClick = onProfileClick,
                color = Color(0xFFFFCCBC),
                shape = CircleShape,
                modifier = Modifier.size(64.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    if (profileImageState.value != null) {
                        AsyncImage(
                            model = profileImageState.value,
                            contentDescription = "Profile Settings",
                            modifier = Modifier.fillMaxSize().clip(CircleShape),
                            contentScale = androidx.compose.ui.layout.ContentScale.Crop
                        )
                    } else {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Profile Settings",
                            modifier = Modifier.size(40.dp),
                            tint = Color.White
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(R.string.hello_comma),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$name 👋",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = role,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
            
            // Notification Bell
            Surface(
                onClick = onNotificationClick,
                color = Color.White.copy(alpha = 0.15f),
                shape = CircleShape,
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        tint = Color.White
                    )
                    if (hasNotifications) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .align(Alignment.TopEnd)
                                .offset(x = (-8).dp, y = 8.dp)
                                .clip(CircleShape)
                                .background(Color.Red)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StatCard(label: String, value: String, icon: Any, iconColor: Color, iconBgColor: Color, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(iconBgColor),
                contentAlignment = Alignment.Center
            ) {
                when (icon) {
                    is ImageVector -> Icon(icon, null, tint = iconColor, modifier = Modifier.size(20.dp))
                    is Painter -> Icon(icon, null, tint = iconColor, modifier = Modifier.size(20.dp))
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(value, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.Black, maxLines = 1)
                Text(label, style = MaterialTheme.typography.labelSmall, color = Color.Black, maxLines = 1)
            }
        }
    }
}

@Composable
fun QuickActionGridCard(label: String, icon: Any, iconColor: Color, iconBgColor: Color, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.aspectRatio(1f),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(iconBgColor),
                contentAlignment = Alignment.Center
            ) {
                when (icon) {
                    is ImageVector -> Icon(icon, null, tint = iconColor, modifier = Modifier.size(28.dp))
                    is Painter -> Icon(icon, null, tint = iconColor, modifier = Modifier.size(28.dp))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                label,
                fontSize = 10.sp,
                lineHeight = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}

@Composable
fun PolicyCard(id: String, status: String, expiry: String) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(id, fontWeight = FontWeight.Bold)
                Surface(color = SuccessGreen, shape = RoundedCornerShape(4.dp)) {
                    Text(status, color = Color.White, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp), fontSize = 10.sp)
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text("Expires: $expiry", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun OtpInput(value: String, onValueChange: (String) -> Unit, onDone: () -> Unit = {}) {
    val focusRequester = remember { FocusRequester() }
    
    // Auto-focus the field when displayed
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth().clickable { focusRequester.requestFocus() }) {
        // Hidden text field to capture input
        BasicTextField(
            value = value,
            onValueChange = { input -> if ((input.length <= 6) && input.all { it.isDigit() }) onValueChange(input) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onDone() }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .focusRequester(focusRequester),
            decorationBox = {
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    repeat(6) { i ->
                        val char = value.getOrNull(i)?.toString() ?: ""
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .border(
                                    width = 1.dp,
                                    color = if (value.length == i) PrimaryGreen else Color.LightGray,
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = char,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun DidiBottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = currentRoute == "didi_dashboard",
            onClick = {
                if (currentRoute != "didi_dashboard") {
                    navController.navigate("didi_dashboard") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            },
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text(stringResource(R.string.home), fontSize = 9.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryGreen,
                selectedTextColor = PrimaryGreen,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = currentRoute == "goat_list" || currentRoute?.startsWith("goat_details") == true,
            onClick = {
                if (currentRoute != "goat_list") {
                    navController.navigate("goat_list") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            },
            icon = { Icon(painterResource(R.drawable.ic_ewe_custom), null, modifier = Modifier.size(24.dp)) },
            label = { Text(stringResource(R.string.goats), fontSize = 9.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryGreen,
                selectedTextColor = PrimaryGreen,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = currentRoute == "vaccine_list",
            onClick = {
                if (currentRoute != "vaccine_list") {
                    navController.navigate("vaccine_list") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            },
            icon = { Icon(Icons.Default.MedicalServices, null) },
            label = { Text(stringResource(R.string.vaccines), fontSize = 9.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryGreen,
                selectedTextColor = PrimaryGreen,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = currentRoute == "claim_list" || currentRoute?.startsWith("claim_review") == true,
            onClick = {
                if (currentRoute != "claim_list") {
                    navController.navigate("claim_list") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            },
            icon = { Icon(Icons.AutoMirrored.Filled.Assignment, null) },
            label = { Text(stringResource(R.string.claims), fontSize = 9.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryGreen,
                selectedTextColor = PrimaryGreen,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
fun FarmerBottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = currentRoute == "farmer_dashboard",
            onClick = {
                if (currentRoute != "farmer_dashboard") {
                    navController.navigate("farmer_dashboard") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            },
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text(stringResource(R.string.home), fontSize = 9.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryBlue,
                selectedTextColor = PrimaryBlue,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = currentRoute == "goat_list",
            onClick = {
                if (currentRoute != "goat_list") {
                    navController.navigate("goat_list") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            },
            icon = { Icon(painterResource(R.drawable.ic_ewe_custom), null, modifier = Modifier.size(24.dp)) },
            label = { Text(stringResource(R.string.my_goats), fontSize = 9.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryBlue,
                selectedTextColor = PrimaryBlue,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = currentRoute == "claim_list" || currentRoute == "claim_tracker",
            onClick = {
                if (currentRoute != "claim_list") {
                    navController.navigate("claim_list") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            },
            icon = { Icon(Icons.AutoMirrored.Filled.Assignment, null) },
            label = { Text(stringResource(R.string.claims), fontSize = 9.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryBlue,
                selectedTextColor = PrimaryBlue,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = currentRoute == "help_support",
            onClick = {
                if (currentRoute != "help_support") {
                    navController.navigate("help_support") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            },
            icon = { Icon(Icons.Default.SupportAgent, null) },
            label = { Text(stringResource(R.string.help), fontSize = 9.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryBlue,
                selectedTextColor = PrimaryBlue,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    CommunityGoatTheme {
        LoginScreen({}, {})
    }
}

// --- NOTIFICATION COMPONENTS ---

data class AppNotification(val title: String, val message: String, val time: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationSheet(themeColor: Color = PrimaryGreen, onDismiss: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val notifications = listOf(
        AppNotification(stringResource(R.string.new_enrollment), stringResource(R.string.new_enrollment_msg), stringResource(R.string.time_2_mins_ago)),
        AppNotification(stringResource(R.string.vaccination_due), stringResource(R.string.vaccination_due_msg), stringResource(R.string.time_1_hour_ago)),
        AppNotification(stringResource(R.string.claim_approved), stringResource(R.string.claim_approved_msg), stringResource(R.string.time_yesterday)),
        AppNotification(stringResource(R.string.visit_reminder), stringResource(R.string.visit_reminder_msg), stringResource(R.string.time_today_4pm))
    )
    
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        dragHandle = @Composable { BottomSheetDefaults.DragHandle(color = Color.LightGray) },
        contentWindowInsets = { WindowInsets(0, 0, 0, 0) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            Text(
                stringResource(R.string.notifications),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(bottom = 32.dp)
            ) {
                items(notifications) { notification ->
                    NotificationItem(notification, themeColor)
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 12.dp),
                        color = Color.LightGray.copy(alpha = 0.4f)
                    )
                }
            }
        }
    }
}

@Composable
fun NotificationItem(notification: AppNotification, themeColor: Color = PrimaryGreen) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(themeColor.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.Notifications,
                contentDescription = null,
                tint = themeColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                notification.title,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 15.sp
            )
            Text(
                notification.message,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                lineHeight = 18.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                notification.time,
                style = MaterialTheme.typography.labelSmall,
                color = themeColor,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController, userRole: UserRole?, sessionManager: SessionManager, onLogout: () -> Unit, onBack: () -> Unit) {
    val backgroundColor = Color(0xFFF8F9F5)
    val languageState = LocalAppLanguage.current
    var showLanguagePicker by remember { mutableStateOf(false) }
    
    val context = LocalContext.current
    val profileImageState = LocalProfileImage.current
    
    val savedName by sessionManager.userName.collectAsState(initial = null)
    val savedVillage by sessionManager.village.collectAsState(initial = null)

    val isFarmer = userRole == UserRole.FARMER
    val isCoordinator = userRole == UserRole.COORDINATOR
    val themeColor = when(userRole) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }
    val userName = savedName ?: when {
        isFarmer -> stringResource(R.string.ramesh_naik)
        isCoordinator -> stringResource(R.string.lalu)
        else -> stringResource(R.string.sushma_didi)
    }
    
    val roleLabel = when(userRole) {
        UserRole.FARMER -> stringResource(R.string.role_farmer)
        UserRole.COORDINATOR -> stringResource(R.string.role_coordinator)
        else -> stringResource(R.string.role_suraksha_didi)
    }

    fun saveImageToInternalStorage(uri: Uri): Uri? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val file = File(context.filesDir, "profile_pic.jpg")
            val outputStream = FileOutputStream(file)
            inputStream?.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            Uri.fromFile(file)
        } catch (e: Exception) {
            null
        }
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            val internalUri = saveImageToInternalStorage(uri)
            if (internalUri != null) {
                profileImageState.value = internalUri
                Toast.makeText(context, context.getString(R.string.saved), Toast.LENGTH_SHORT).show()
            }
        }
    }

    var tempUriStr by rememberSaveable { mutableStateOf<String?>(null) }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && tempUriStr != null) {
            val uri = Uri.parse(tempUriStr!!)
            val internalUri = saveImageToInternalStorage(uri)
            if (internalUri != null) {
                profileImageState.value = internalUri
                Toast.makeText(context, context.getString(R.string.saved), Toast.LENGTH_SHORT).show()
            }
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            tempUriStr?.let { cameraLauncher.launch(Uri.parse(it)) }
        }
    }

    fun launchCamera() {
        val file = File(context.filesDir, "camera_profile.jpg")
        val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
        tempUriStr = uri.toString()
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            cameraLauncher.launch(uri)
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    fun getLanguageName(lang: AppLanguage) = when(lang) {
        AppLanguage.HINDI -> "हिन्दी (HI)"
        AppLanguage.ODIA -> "ଓଡ଼ିଆ (OR)"
        AppLanguage.ENGLISH -> "English (EN)"
    }
    
    var showPhotoOptions by remember { mutableStateOf(false) }

    if (showPhotoOptions) {
        AlertDialog(
            onDismissRequest = { showPhotoOptions = false },
            title = { Text(stringResource(R.string.update_profile_photo), fontWeight = FontWeight.Bold, color = Color.Black) },
            text = { Text(stringResource(R.string.choose_source), color = Color.Black) },
            confirmButton = {
                TextButton(onClick = { 
                    showPhotoOptions = false
                    launchCamera()
                }) {
                    Text(stringResource(R.string.camera), fontWeight = FontWeight.Bold, color = themeColor)
                }
            },
            dismissButton = {
                TextButton(onClick = { 
                    showPhotoOptions = false
                    imagePickerLauncher.launch("image/*")
                }) {
                    Text(stringResource(R.string.gallery), fontWeight = FontWeight.Bold, color = themeColor)
                }
            },
            containerColor = Color.White,
            shape = RoundedCornerShape(16.dp)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
    ) {
        // Dynamic Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                .background(themeColor)
                .padding(top = 48.dp, bottom = 32.dp, start = 20.dp, end = 20.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                    Text(
                        stringResource(R.string.profile_settings),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Profile Image
                Box(contentAlignment = Alignment.Center) {
                    Surface(
                        color = Color(0xFFFFCCBC),
                        shape = CircleShape,
                        modifier = Modifier.size(100.dp),
                        border = BorderStroke(4.dp, Color.White.copy(alpha = 0.2f))
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            if (profileImageState.value != null) {
                                AsyncImage(
                                    model = profileImageState.value,
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize().clip(CircleShape),
                                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                                )
                            } else {
                                Icon(
                                    Icons.Default.Person,
                                    contentDescription = null,
                                    modifier = Modifier.size(60.dp),
                                    tint = Color.White
                                )
                            }
                        }
                    }
                    Surface(
                        onClick = { showPhotoOptions = true },
                        color = Color.White,
                        shape = CircleShape,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(32.dp)
                            .border(1.dp, Color.LightGray.copy(alpha = 0.5f), CircleShape),
                        shadowElevation = 4.dp
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.Edit, null, tint = themeColor, modifier = Modifier.size(16.dp))
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    userName,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    if (isFarmer) "+91 94370 12345" else if (isCoordinator) "+91 99999 88888" else "+91 98765 43210",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Info Cards
            ProfileInfoSection(stringResource(R.string.account_details), themeColor) {
                ProfileInfoItem(
                    stringResource(R.string.full_name),
                    userName
                )
                ProfileInfoItem(
                    stringResource(R.string.role),
                    roleLabel
                )
                ProfileInfoItem(
                    stringResource(R.string.location),
                    savedVillage ?: when {
                        isFarmer -> stringResource(R.string.pipili_odisha)
                        isCoordinator -> stringResource(R.string.bhubaneswar_odisha)
                        else -> stringResource(R.string.gopalpur_odisha)
                    }
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            ProfileInfoSection(stringResource(R.string.app_settings), themeColor) {
                val notificationsEnabled = LocalNotificationsEnabled.current
                SettingsToggleItem(
                    label = stringResource(R.string.enable_notifications),
                    checked = notificationsEnabled.value,
                    onCheckedChange = { notificationsEnabled.value = it },
                    themeColor = themeColor
                )
                Box {
                    SettingsClickItem(stringResource(R.string.language), getLanguageName(languageState.value)) {
                        showLanguagePicker = true
                    }
                    DropdownMenu(
                        expanded = showLanguagePicker,
                        onDismissRequest = { showLanguagePicker = false }
                    ) {
                        DropdownMenuItem(text = { Text("English") }, onClick = { languageState.value = AppLanguage.ENGLISH; showLanguagePicker = false })
                        DropdownMenuItem(text = { Text("हिन्दी") }, onClick = { languageState.value = AppLanguage.HINDI; showLanguagePicker = false })
                        DropdownMenuItem(text = { Text("ଓଡ଼ିଆ") }, onClick = { languageState.value = AppLanguage.ODIA; showLanguagePicker = false })
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            ProfileInfoSection(stringResource(R.string.support_legal), themeColor) {
                SettingsClickItem(stringResource(R.string.help_support), "") {
                    navController.navigate("help_support")
                }
                SettingsClickItem(stringResource(R.string.privacy_policy), "") {
                    navController.navigate("privacy_policy")
                }
                SettingsClickItem(stringResource(R.string.terms_of_service), "") {
                    navController.navigate("terms_of_service")
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Button(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.Logout, null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(stringResource(R.string.logout), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            Text(stringResource(R.string.version_label, "1.0.0"), color = Color.Gray, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpSupportScreen(navController: NavHostController, userRole: UserRole?, onReportIssue: () -> Unit, onFaqs: () -> Unit, onContactSupport: () -> Unit, onBack: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val themeColor = when(userRole) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        stringResource(R.string.help_support), 
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = themeColor,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        containerColor = Color(0xFFF8F9F5)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HelpItemCard(
                title = stringResource(R.string.faqs),
                subtitle = stringResource(R.string.faqs_subtitle),
                icon = Icons.AutoMirrored.Filled.Help,
                themeColor = themeColor,
                onClick = onFaqs
            )
            HelpItemCard(
                title = stringResource(R.string.contact_support),
                subtitle = stringResource(R.string.contact_support_subtitle),
                icon = Icons.AutoMirrored.Filled.Chat,
                themeColor = themeColor,
                onClick = onContactSupport
            )
            HelpItemCard(
                title = stringResource(R.string.call_support),
                subtitle = "0000-000-008",
                icon = Icons.Default.Phone,
                themeColor = themeColor,
                onClick = { navController.navigate("call_support") }
            )
            HelpItemCard(
                title = stringResource(R.string.report_issue),
                subtitle = stringResource(R.string.report_issue_subtitle),
                icon = Icons.Default.Error,
                themeColor = themeColor,
                onClick = onReportIssue
            )
        }
    }
}

@Composable
fun HelpItemCard(title: String, subtitle: String, icon: ImageVector, themeColor: Color, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                color = themeColor.copy(alpha = 0.1f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(icon, null, tint = themeColor, modifier = Modifier.size(24.dp))
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                Text(subtitle, fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportIssueScreen(
    userRole: UserRole?, 
    onBack: () -> Unit, 
    onViewReports: () -> Unit,
    onIssueSubmitted: (String, String, String, String) -> Unit
) {
    var showForm by remember { mutableStateOf(false) }
    var selectedType by remember { mutableStateOf("Bug") }
    
    val languageState = LocalAppLanguage.current
    val themeColor = when (userRole) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }

    if (showForm) {
        SubmitIssueForm(
            initialType = selectedType,
            onBack = { showForm = false },
            onComplete = onIssueSubmitted,
            themeColor = themeColor
        )
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            stringResource(R.string.report_issue),
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = themeColor,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White
                    )
                )
            },
            containerColor = Color(0xFFF8F9F5)
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                // Illustration
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Surface(
                        modifier = Modifier.size(100.dp),
                        shape = CircleShape,
                        color = themeColor.copy(alpha = 0.1f)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Error,
                                contentDescription = null,
                                modifier = Modifier.size(50.dp),
                                tint = themeColor
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = stringResource(R.string.something_not_working),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stringResource(R.string.let_us_know_issue),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Options
                Column(
                    modifier = Modifier.padding(bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ReportOptionCard(
                        title = stringResource(R.string.report_bug),
                        subtitle = stringResource(R.string.report_bug_subtitle),
                        icon = Icons.Default.BugReport,
                        iconColor = Color(0xFFE57373),
                        onClick = { 
                            selectedType = "Bug"
                            showForm = true 
                        }
                    )
                    ReportOptionCard(
                        title = stringResource(R.string.feature_request),
                        subtitle = stringResource(R.string.feature_request_subtitle),
                        icon = Icons.Default.Lightbulb,
                        iconColor = Color(0xFF64B5F6),
                        onClick = {
                            selectedType = "Feature"
                            showForm = true
                        }
                    )
                    ReportOptionCard(
                        title = stringResource(R.string.general_feedback),
                        subtitle = stringResource(R.string.general_feedback_subtitle),
                        icon = Icons.Default.Feedback,
                        iconColor = Color(0xFF81C784),
                        onClick = {
                            selectedType = "Feedback"
                            showForm = true
                        }
                    )
                    ReportOptionCard(
                        title = stringResource(R.string.view_my_reports),
                        subtitle = stringResource(R.string.view_my_reports_subtitle),
                        icon = Icons.AutoMirrored.Filled.Assignment,
                        iconColor = Color(0xFFBA68C8),
                        onClick = onViewReports
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubmitIssueForm(
    initialType: String,
    onBack: () -> Unit,
    onComplete: (String, String, String, String) -> Unit,
    themeColor: Color
) {
    val languageState = LocalAppLanguage.current
    var selectedType by remember { mutableStateOf(initialType) }
    var category by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var attachmentUri by remember { mutableStateOf<Uri?>(null) }
    
    val attachmentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> attachmentUri = uri }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.report_issue), fontWeight = FontWeight.Bold, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = themeColor)
            )
        },
        bottomBar = {
            Button(
                onClick = { 
                    val ticketId = "TKT-" + (10000..99999).random()
                    val date = "20 May 2024" // Mock current date
                    onComplete(ticketId, selectedType, date, "Open")
                },
                modifier = Modifier.fillMaxWidth().navigationBarsPadding().padding(20.dp).height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen),
                enabled = category.isNotBlank() && title.isNotBlank() && description.isNotBlank()
            ) {
                Text(stringResource(R.string.submit_issue), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        },
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Issue Type
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(stringResource(R.string.issue_type), fontWeight = FontWeight.Bold, color = Color.Black)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    IssueTypeChip(stringResource(R.string.report_bug), selectedType == "Bug", Color(0xFFD32F2F), Icons.Default.BugReport, modifier = Modifier.weight(1f)) { selectedType = "Bug" }
                    IssueTypeChip(stringResource(R.string.feature_request), selectedType == "Feature", PrimaryBlue, Icons.Default.Lightbulb, modifier = Modifier.weight(1f)) { selectedType = "Feature" }
                    IssueTypeChip(stringResource(R.string.feedback), selectedType == "Feedback", SuccessGreen, Icons.Default.Feedback, modifier = Modifier.weight(1f)) { selectedType = "Feedback" }
                }
            }

            // Category
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(stringResource(R.string.category), fontWeight = FontWeight.Bold, color = Color.Black)
                EnrollmentDropdownField(
                    label = "",
                    selectedValue = category.ifEmpty { stringResource(R.string.select_category) },
                    options = listOf(
                        stringResource(R.string.app_crash),
                        stringResource(R.string.login_issue),
                        stringResource(R.string.payment_failed),
                        stringResource(R.string.missing_data),
                        stringResource(R.string.other_label)
                    ),
                    onValueChange = { category = it },
                    borderColor = Color.LightGray.copy(alpha = 0.5f)
                )
            }

            // Issue Title
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(stringResource(R.string.issue_title_required), fontWeight = FontWeight.Bold, color = Color.Black)
                OutlinedTextField(
                    value = title,
                    onValueChange = { if (it.length <= 100) title = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(stringResource(R.string.briefly_describe_issue), color = Color.Gray) },
                    shape = RoundedCornerShape(12.dp),
                    supportingText = { Text("${title.length}/100", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End) },
                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.Black, unfocusedTextColor = Color.Black, unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f))
                )
            }

            // Describe the Issue
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(stringResource(R.string.describe_issue_required), fontWeight = FontWeight.Bold, color = Color.Black)
                OutlinedTextField(
                    value = description,
                    onValueChange = { if (it.length <= 1000) description = it },
                    modifier = Modifier.fillMaxWidth().height(150.dp),
                    placeholder = { Text(stringResource(R.string.describe_issue_placeholder), color = Color.Gray) },
                    shape = RoundedCornerShape(12.dp),
                    supportingText = { Text("${description.length}/1000", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End) },
                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.Black, unfocusedTextColor = Color.Black, unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f))
                )
            }

            // Attachment
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(stringResource(R.string.attach_screenshot_video_optional), fontWeight = FontWeight.Bold, color = Color.Black)
                AttachmentBox(attachmentUri) { attachmentLauncher.launch("image/*") }
            }
            
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun IssueTypeChip(label: String, isSelected: Boolean, color: Color, icon: ImageVector, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        shape = RoundedCornerShape(8.dp),
        color = if (isSelected) color.copy(alpha = 0.05f) else Color.White,
        border = BorderStroke(1.dp, if (isSelected) color else Color.LightGray.copy(alpha = 0.3f))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Icon(icon, null, tint = color, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(label, color = color, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
    }
}

@Composable
fun AttachmentBox(selectedUri: Uri? = null, onAttachClick: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val stroke = Stroke(width = 2f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onAttachClick() }
            .drawBehind {
                drawRoundRect(
                    color = Color.LightGray,
                    style = stroke,
                    cornerRadius = CornerRadius(12.dp.toPx())
                )
            },
        contentAlignment = Alignment.Center
    ) {
        if (selectedUri != null) {
            AsyncImage(
                model = selectedUri,
                contentDescription = null,
                modifier = Modifier.fillMaxSize().padding(8.dp),
                contentScale = androidx.compose.ui.layout.ContentScale.Fit
            )
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.CloudUpload, null, tint = Color.Gray, modifier = Modifier.size(40.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Text(stringResource(R.string.tap_to_upload), fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)
                Text("PNG, JPG up to 5MB", fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun ReportOptionCard(title: String, subtitle: String, icon: ImageVector, iconColor: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                color = iconColor.copy(alpha = 0.1f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(icon, null, tint = iconColor, modifier = Modifier.size(24.dp))
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                Text(subtitle, fontSize = 12.sp, color = Color.Gray, lineHeight = 16.sp)
            }
            Icon(Icons.Default.ChevronRight, null, tint = Color.LightGray)
        }
    }
}

@Composable
fun ProfileInfoSection(title: String, themeColor: Color = PrimaryGreen, content: @Composable ColumnScope.() -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = themeColor)
        Spacer(modifier = Modifier.height(12.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                content()
            }
        }
    }
}

@Composable
fun ProfileInfoItem(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = Color.Gray)
        Text(value, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}

@Composable
fun SettingsToggleItem(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit, themeColor: Color = PrimaryGreen) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, fontWeight = FontWeight.Medium)
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(checkedThumbColor = themeColor, checkedTrackColor = themeColor.copy(alpha = 0.3f))
        )
    }
}

@Composable
fun SettingsClickItem(label: String, value: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(label, fontWeight = FontWeight.Medium)
            if (value.isNotEmpty()) {
                Text(value, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
        }
        Icon(Icons.Default.ChevronRight, null, tint = Color.Gray)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoatListScreen(navController: NavHostController, userRole: UserRole?, onBack: () -> Unit, onAddGoat: () -> Unit) {
    val languageState = LocalAppLanguage.current
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableIntStateOf(0) }
    
    val isFarmer = userRole == UserRole.FARMER
    val themeColor = if (isFarmer) PrimaryBlue else PrimaryGreen

    val tabs = listOf(
        stringResource(R.string.all_count, 128),
        stringResource(R.string.active_count, 110),
        stringResource(R.string.expired_count, 10),
        stringResource(R.string.claimed)
    )

    ResponsiveLayout(
        compact = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(stringResource(R.string.goat_list), fontWeight = FontWeight.Bold) },
                        navigationIcon = {
                            IconButton(onClick = onBack) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = themeColor,
                            titleContentColor = Color.White,
                            navigationIconContentColor = Color.White
                        )
                    )
                },
                bottomBar = { 
                    if (isFarmer) FarmerBottomBar(navController) else DidiBottomBar(navController) 
                },
                floatingActionButton = {
                    if (!isFarmer) {
                        FloatingActionButton(
                            onClick = onAddGoat,
                            containerColor = themeColor,
                            contentColor = Color.White,
                            shape = CircleShape
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "Add Goat")
                        }
                    }
                },
                containerColor = Color(0xFFF8F9F5)
            ) { padding ->
                GoatListContent(padding, tabs, searchQuery, { searchQuery = it }, selectedTab, { selectedTab = it }, themeColor, isFarmer, onReportDeath = { navController.navigate("farmer_report_death") }) { tag ->
                    navController.navigate("goat_details/$tag")
                }
            }
        },
        expanded = {
            Row(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing).navigationBarsPadding()) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                
                NavigationRail {
                    NavigationRailItem(
                        selected = currentRoute == "didi_dashboard" || currentRoute == "farmer_dashboard", 
                        onClick = { 
                            val route = if (isFarmer) "farmer_dashboard" else "didi_dashboard"
                            if (currentRoute != route) navController.navigate(route) 
                        }, 
                        icon = { Icon(Icons.Default.Home, null) }, 
                        label = { Text(stringResource(R.string.home)) },
                        colors = NavigationRailItemDefaults.colors(selectedIconColor = themeColor, selectedTextColor = themeColor, unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray, indicatorColor = Color.Transparent)
                    )
                    NavigationRailItem(
                        selected = currentRoute == "goat_list", 
                        onClick = { if (currentRoute != "goat_list") navController.navigate("goat_list") }, 
                        icon = { Icon(painterResource(R.drawable.ic_ewe_custom), null) }, 
                        label = { Text(stringResource(R.string.goats)) },
                        colors = NavigationRailItemDefaults.colors(selectedIconColor = themeColor, selectedTextColor = themeColor, unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray, indicatorColor = Color.Transparent)
                    )
                    if (!isFarmer) {
                        NavigationRailItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Vaccines, null) }, label = { Text(stringResource(R.string.vaccines)) }, colors = NavigationRailItemDefaults.colors(unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray))
                    }
                }
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(stringResource(R.string.goat_list), fontWeight = FontWeight.Bold) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = themeColor,
                                titleContentColor = Color.White
                            )
                        )
                    },
                    floatingActionButton = {
                        if (!isFarmer) {
                            FloatingActionButton(onClick = onAddGoat, containerColor = themeColor, contentColor = Color.White, shape = CircleShape) {
                                Icon(Icons.Default.Add, contentDescription = "Add Goat")
                            }
                        }
                    },
                    containerColor = Color(0xFFF8F9F5)
                ) { padding ->
                    GoatListContent(padding, tabs, searchQuery, { searchQuery = it }, selectedTab, { selectedTab = it }, themeColor, isFarmer, onReportDeath = { navController.navigate("farmer_report_death") }) { tag ->
                        navController.navigate("goat_details/$tag")
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoatListContent(
    padding: PaddingValues,
    tabs: List<String>,
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    selectedTab: Int,
    onTabChange: (Int) -> Unit,
    themeColor: Color,
    isFarmer: Boolean,
    onReportDeath: (String) -> Unit,
    onGoatClick: (String) -> Unit
) {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(padding)) {
        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            placeholder = { Text(stringResource(R.string.search_goat_placeholder)) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray) },
            shape = RoundedCornerShape(24.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White, unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f))
        )

        // Tabs
        ScrollableTabRow(selectedTabIndex = selectedTab, containerColor = Color.Transparent, edgePadding = 16.dp, divider = {}, indicator = {}) {
            tabs.forEachIndexed { index, title ->
                val isSelected = selectedTab == index
                Tab(selected = isSelected, onClick = { onTabChange(index) }, modifier = Modifier.padding(horizontal = 4.dp)) {
                    Surface(
                        color = if (isSelected) themeColor else Color.White,
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(1.dp, if (isSelected) themeColor else Color.LightGray.copy(alpha = 0.5f)),
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        Text(text = title, modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp), color = if (isSelected) Color.White else Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }

        val mockGoats = listOf(
            Triple("ET-340801-0001", "Ramesh Naik", "Pipili"),
            Triple("ET-340801-0006", "Ramesh Naik", "Pipili"),
            Triple("ET-240801-0002", "Suresh Behera", "Balianta"),
            Triple("ET-340801-0003", "Manoj Sahoo", "Pipili"),
            Triple("ET-140801-0004", "Alok Dash", "Puri"),
            Triple("ET-540801-0005", "Prakash Rout", "Cuttack")
        )

        val filteredGoats = mockGoats.filter { 
            it.first.contains(searchQuery, ignoreCase = true) || it.second.contains(searchQuery, ignoreCase = true)
        }.filter { goat ->
            when (selectedTab) {
                1 -> goat.first != "ET-340801-0003" // Mock Active
                2 -> goat.first == "ET-340801-0003" // Mock Expired
                3 -> false // Mock Claimed
                else -> true // All
            }
        }

        val groupedGoats = filteredGoats.groupBy { it.second }
        val expandedFarmers = remember { mutableStateMapOf<String, Boolean>() }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (isFarmer) {
                // Simple list for Farmer
                items(filteredGoats) { goat ->
                    val isExpired = goat.first == "ET-340801-0003"
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Surface(
                                    modifier = Modifier.size(70.dp),
                                    shape = RoundedCornerShape(12.dp),
                                    color = Color(0xFFF0F0F0)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(painterResource(R.drawable.ic_ewe_custom), null, tint = Color.Gray, modifier = Modifier.size(32.dp))
                                    }
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    val blackBengal = stringResource(R.string.black_bengal)
                                    val female = stringResource(R.string.female)
                                    val age = stringResource(R.string.age_months_suffix, 12)
                                    Text(goat.first, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)
                                    Text(stringResource(R.string.breed_gender_age_format, blackBengal, female, age), color = Color.Gray, fontSize = 12.sp)
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        if (isExpired) stringResource(R.string.policy_expired) 
                                        else stringResource(R.string.policy_active), 
                                        color = if (isExpired) Color.Red else SuccessGreen, 
                                        fontWeight = FontWeight.Bold, 
                                        fontSize = 13.sp
                                    )
                                    Text(
                                        if (isExpired) stringResource(R.string.expired_on, "31 May 2024") 
                                        else stringResource(R.string.valid_till, "31 May 2025"), 
                                        fontSize = 11.sp, 
                                        color = Color.Gray
                                    )
                                }
                            }
                            
                            Spacer(modifier = Modifier.height(16.dp))
                            
                            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                Button(
                                    onClick = { onReportDeath(goat.first) },
                                    modifier = Modifier.weight(1f).height(40.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                                    shape = RoundedCornerShape(8.dp),
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Text(stringResource(R.string.report_death), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                }
                                Button(
                                    onClick = { onGoatClick(goat.first) },
                                    modifier = Modifier.weight(1f).height(40.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen),
                                    shape = RoundedCornerShape(8.dp),
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Text(stringResource(R.string.view_policy), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                }
                            }
                        }
                    }
                }
            } else {
                // Grouped list for Suraksha Didi / Coordinator
                groupedGoats.forEach { (farmerName, goats) ->
                    item {
                        val village = goats.firstOrNull()?.third ?: ""
                        val isExpanded = expandedFarmers[farmerName] ?: false
                        
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            shape = RoundedCornerShape(24.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                            border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f))
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                // Farmer Info Header (Clickable to Expand)
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { expandedFarmers[farmerName] = !isExpanded },
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Surface(
                                        modifier = Modifier.size(52.dp),
                                        shape = CircleShape,
                                        color = themeColor.copy(alpha = 0.1f)
                                    ) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Icon(Icons.Default.Person, null, tint = themeColor, modifier = Modifier.size(28.dp))
                                        }
                                    }
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = farmerName,
                                            style = MaterialTheme.typography.titleLarge,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = Color.Black
                                        )
                                        Text(
                                            text = village,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = Color.Gray
                                        )
                                    }
                                    Icon(
                                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                        contentDescription = null,
                                        tint = Color.Gray
                                    )
                                }
                                
                                if (isExpanded) {
                                    Spacer(modifier = Modifier.height(16.dp))
                                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                                    Spacer(modifier = Modifier.height(16.dp))

                                    // Goats List
                                    Text(
                                        text = stringResource(R.string.registered_goats),
                                        style = MaterialTheme.typography.labelMedium,
                                        color = themeColor,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))

                                    goats.forEachIndexed { index, goat ->
                                        val isExpired = goat.first == "ET-340801-0003"
                                        Surface(
                                            onClick = { onGoatClick(goat.first) },
                                            modifier = Modifier.fillMaxWidth(),
                                            color = Color.Transparent,
                                            shape = RoundedCornerShape(12.dp)
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(vertical = 8.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Surface(
                                                    modifier = Modifier.size(44.dp),
                                                    shape = RoundedCornerShape(10.dp),
                                                    color = Color(0xFFF5F5F5)
                                                ) {
                                                    Box(contentAlignment = Alignment.Center) {
                                                        Icon(painterResource(R.drawable.ic_ewe_custom), null, tint = Color.Gray, modifier = Modifier.size(20.dp))
                                                    }
                                                }
                                                Spacer(modifier = Modifier.width(12.dp))
                                                Column(modifier = Modifier.weight(1f)) {
                                                    Text(goat.first, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                                    Text(
                                                        if (isExpired) stringResource(R.string.policy_expired) 
                                                        else stringResource(R.string.policy_active),
                                                        color = if (isExpired) Color.Red else SuccessGreen,
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 12.sp
                                                    )
                                                }
                                                Icon(Icons.Default.ChevronRight, null, tint = Color.LightGray)
                                            }
                                        }
                                        if (index < goats.size - 1) {
                                            HorizontalDivider(
                                                modifier = Modifier.padding(start = 56.dp, top = 4.dp, bottom = 4.dp),
                                                color = Color.LightGray.copy(alpha = 0.2f)
                                            )
                                        }
                                    }
                                } else {
                                    // Show summary when collapsed
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = stringResource(R.string.goats_registered_count, goats.size),
                                        style = MaterialTheme.typography.bodySmall,
                                        color = themeColor,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoatDetailsScreen(navController: NavHostController, tag: String, userRole: UserRole?, onBack: () -> Unit) {
    val backgroundColor = Color(0xFFF8F9F5)
    
    val isFarmer = userRole == UserRole.FARMER
    val themeColor = if (isFarmer) PrimaryBlue else PrimaryGreen

    val mockGoats = listOf(
        Triple("ET-340801-0001", "Ramesh Naik", "Pipili"),
        Triple("ET-340801-0006", "Ramesh Naik", "Pipili"),
        Triple("ET-240801-0002", "Suresh Behera", "Balianta"),
        Triple("ET-340801-0003", "Manoj Sahoo", "Pipili"),
        Triple("ET-140801-0004", "Alok Dash", "Puri"),
        Triple("ET-540801-0005", "Prakash Rout", "Cuttack")
    )
    val goat = mockGoats.find { it.first == tag } ?: Triple(tag, "Unknown", "Unknown")
    val isExpired = goat.first == "ET-340801-0003"

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { if (isFarmer) FarmerBottomBar(navController) else DidiBottomBar(navController) },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            // Header (FIXED)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                    .background(themeColor)
                    .padding(top = 48.dp, bottom = 32.dp, start = 20.dp, end = 20.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = onBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                        }
                        Text(
                            stringResource(R.string.goat_details),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Goat Profile Image
                    Surface(
                        color = Color.White.copy(alpha = 0.2f),
                        shape = CircleShape,
                        modifier = Modifier.size(100.dp),
                        border = BorderStroke(4.dp, Color.White.copy(alpha = 0.2f))
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                painterResource(R.drawable.ic_ewe_custom),
                                contentDescription = null,
                                modifier = Modifier.size(60.dp),
                                tint = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        tag,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        if (isExpired) stringResource(R.string.policy_expired)
                        else stringResource(R.string.policy_active),
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (isExpired) Color(0xFFFFCDD2) else Color.White.copy(alpha = 0.8f)
                    )
                }
            }

            // Information (SCROLLABLE)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Farmer Information
                ProfileInfoSection(stringResource(R.string.farmer_information), themeColor) {
                    ProfileInfoItem(stringResource(R.string.farmer_name), goat.second)
                    ProfileInfoItem(stringResource(R.string.village), goat.third)
                    ProfileInfoItem(stringResource(R.string.aadhaar_number), "**** **** 1234")
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Goat Details
                ProfileInfoSection(stringResource(R.string.goat_details), themeColor) {
                    ProfileInfoItem(stringResource(R.string.breed), stringResource(R.string.black_bengal))
                    ProfileInfoItem(stringResource(R.string.gender), stringResource(R.string.female))
                    ProfileInfoItem(stringResource(R.string.age), "14 " + stringResource(R.string.months))
                    ProfileInfoItem(stringResource(R.string.weight), "19 KG")
                    ProfileInfoItem(stringResource(R.string.color_marks_label), stringResource(R.string.color_marks_value))
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Policy Details
                ProfileInfoSection(stringResource(R.string.policy_information), themeColor) {
                    ProfileInfoItem(stringResource(R.string.policy_number), if (isExpired) "POL-2023-00567" else "POL-2024-00125")
                    ProfileInfoItem(stringResource(R.string.issue_date), if (isExpired) "01 Jun 2023" else "01 Aug 2024")
                    ProfileInfoItem(stringResource(R.string.end_date), if (isExpired) "31 May 2024" else "31 Jul 2025")
                    ProfileInfoItem(stringResource(R.string.sum_insured), "₹ 8,500")
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Vaccination History
                ProfileInfoSection(stringResource(R.string.vaccination_history), themeColor) {
                    VaccineStatusItem(stringResource(R.string.vaccine_ppr), true, themeColor) {}
                    VaccineStatusItem(stringResource(R.string.vaccine_ettt), true, themeColor) {}
                    VaccineStatusItem(stringResource(R.string.vaccine_fmd), false, themeColor) {}
                    VaccineStatusItem(stringResource(R.string.vaccine_goat_pox), false, themeColor) {}
                }

                if (isFarmer) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { /* Download Policy Logic */ },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = themeColor)
                    ) {
                        Icon(Icons.Default.FileDownload, null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(stringResource(R.string.download_policy), fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VaccineListScreen(navController: NavHostController, onBack: () -> Unit, onRecord: (String) -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableIntStateOf(0) }

    val tabs = listOf(
        stringResource(R.string.upcoming_count, 15),
        stringResource(R.string.completed_count),
        stringResource(R.string.all_label)
    )

    ResponsiveLayout(
        compact = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(stringResource(R.string.vaccinations), fontWeight = FontWeight.Bold) },
                        navigationIcon = {
                            IconButton(onClick = onBack) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = PrimaryGreen,
                            titleContentColor = Color.White,
                            navigationIconContentColor = Color.White
                        )
                    )
                },
                bottomBar = { DidiBottomBar(navController) },
                containerColor = Color(0xFFF8F9F5)
            ) { padding ->
                VaccineListContent(padding, tabs, searchQuery, { searchQuery = it }, selectedTab, { selectedTab = it }, onRecord)
            }
        },
        expanded = {
            Row(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing).navigationBarsPadding()) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                
                NavigationRail {
                    NavigationRailItem(
                        selected = currentRoute == "didi_dashboard", 
                        onClick = { if (currentRoute != "didi_dashboard") navController.navigate("didi_dashboard") }, 
                        icon = { Icon(Icons.Default.Home, null) }, 
                        label = { Text(stringResource(R.string.home)) },
                        colors = NavigationRailItemDefaults.colors(selectedIconColor = PrimaryGreen, selectedTextColor = PrimaryGreen, unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray, indicatorColor = Color.Transparent)
                    )
                    NavigationRailItem(
                        selected = currentRoute == "goat_list", 
                        onClick = { if (currentRoute != "goat_list") navController.navigate("goat_list") }, 
                        icon = { Icon(painterResource(R.drawable.ic_ewe_custom), null) }, 
                        label = { Text(stringResource(R.string.goats)) },
                        colors = NavigationRailItemDefaults.colors(selectedIconColor = PrimaryGreen, selectedTextColor = PrimaryGreen, unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray, indicatorColor = Color.Transparent)
                    )
                    NavigationRailItem(
                        selected = currentRoute == "vaccine_list", 
                        onClick = { if (currentRoute != "vaccine_list") navController.navigate("vaccine_list") }, 
                        icon = { Icon(Icons.Default.MedicalServices, null) }, 
                        label = { Text(stringResource(R.string.vaccines)) },
                        colors = NavigationRailItemDefaults.colors(selectedIconColor = PrimaryGreen, selectedTextColor = PrimaryGreen, unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray, indicatorColor = Color.Transparent)
                    )
                }
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(stringResource(R.string.vaccinations), fontWeight = FontWeight.Bold) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = PrimaryGreen,
                                titleContentColor = Color.White
                            )
                        )
                    },
                    containerColor = Color(0xFFF8F9F5)
                ) { padding ->
                    VaccineListContent(padding, tabs, searchQuery, { searchQuery = it }, selectedTab, { selectedTab = it }, onRecord)
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VaccineListContent(
    padding: PaddingValues,
    tabs: List<String>,
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    selectedTab: Int,
    onTabChange: (Int) -> Unit,
    onRecord: (String) -> Unit
) {
    Column(modifier = Modifier.padding(padding)) {
        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            placeholder = { Text(stringResource(R.string.search_vaccine_placeholder)) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray) },
            shape = RoundedCornerShape(24.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White, unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f))
        )

        // Tabs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            tabs.forEachIndexed { index, title ->
                val isSelected = selectedTab == index
                Surface(
                    onClick = { onTabChange(index) },
                    modifier = Modifier.weight(1f),
                    color = if (isSelected) PrimaryGreen else Color.White,
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(1.dp, if (isSelected) PrimaryGreen else Color.LightGray.copy(alpha = 0.5f))
                ) {
                    Text(
                        text = title,
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = if (isSelected) Color.White else Color.Black,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        val mockVaccinations = listOf(
            mapOf("name" to "PPR Vaccine", "tag" to "ET-340801-0001", "date" to "15 Aug 2024", "farmer" to "Ramesh Naik", "village" to "Pipili"),
            mapOf("name" to "ET + TT Vaccine", "tag" to "ET-240801-0002", "date" to "20 Aug 2024", "farmer" to "Suresh Behera", "village" to "Balianta"),
            mapOf("name" to "FMD Vaccine", "tag" to "ET-340801-0003", "date" to "10 Jul 2024", "farmer" to "Manoj Sahoo", "village" to "Pipili"),
            mapOf("name" to "PPR Vaccine", "tag" to "ET-140801-0004", "date" to "05 Sep 2024", "farmer" to "Alok Dash", "village" to "Puri"),
            mapOf("name" to "Goat Pox Vaccine", "tag" to "ET-540801-0005", "date" to "12 Aug 2024", "farmer" to "Prakash Rout", "village" to "Cuttack")
        )

        val filteredVaccines = mockVaccinations.filter { 
            (it["name"] ?: "").contains(searchQuery, ignoreCase = true) || (it["tag"] ?: "").contains(searchQuery, ignoreCase = true)
        }.filter { vaccine ->
            val isCompleted = vaccine["tag"] == "ET-340801-0003"
            when (selectedTab) {
                0 -> !isCompleted // Upcoming (Due)
                1 -> isCompleted  // Completed
                else -> true      // All
            }
        }

        val groupedVaccines = filteredVaccines.groupBy { it["farmer"] ?: "Unknown" }
        val expandedFarmers = remember { mutableStateMapOf<String, Boolean>() }

        LazyColumn(
            modifier = Modifier.fillMaxSize(), 
            contentPadding = PaddingValues(16.dp), 
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            groupedVaccines.forEach { (farmerName, vaccines) ->
                item {
                    val village = vaccines.firstOrNull()?.get("village") ?: ""
                    val isExpanded = expandedFarmers[farmerName] ?: false
                    
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(24.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            // Farmer Info Header
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { expandedFarmers[farmerName] = !isExpanded },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Surface(
                                    modifier = Modifier.size(52.dp),
                                    shape = CircleShape,
                                    color = PrimaryGreen.copy(alpha = 0.1f)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(Icons.Default.Person, null, tint = PrimaryGreen, modifier = Modifier.size(28.dp))
                                    }
                                }
                                Spacer(modifier = Modifier.width(16.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(farmerName, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.ExtraBold, color = Color.Black)
                                    Text(village, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                                }
                                Icon(
                                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                    contentDescription = null,
                                    tint = Color.Gray
                                )
                            }
                            
                            if (isExpanded) {
                                Spacer(modifier = Modifier.height(16.dp))
                                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                                Spacer(modifier = Modifier.height(16.dp))

                                vaccines.forEachIndexed { index, vaccine ->
                                    val isCompleted = vaccine["tag"] == "ET-340801-0003"
                                    Surface(
                                        onClick = { onRecord(vaccine["tag"] ?: "") },
                                        modifier = Modifier.fillMaxWidth(),
                                        color = Color.Transparent,
                                        shape = RoundedCornerShape(12.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(vertical = 8.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Surface(modifier = Modifier.size(44.dp), shape = RoundedCornerShape(10.dp), color = Color(0xFFF5F5F5)) {
                                                Box(contentAlignment = Alignment.Center) { Icon(painterResource(R.drawable.ic_ewe_custom), null, tint = Color.Gray, modifier = Modifier.size(20.dp)) }
                                            }
                                            Spacer(modifier = Modifier.width(12.dp))
                                            Column(modifier = Modifier.weight(1f)) {
                                                Text(vaccine["tag"] ?: "", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                                Text("${vaccine["name"]} • ${if (isCompleted) stringResource(R.string.done) else stringResource(R.string.pending)}", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = if (isCompleted) SuccessGreen else AccentOrange)
                                                Text(vaccine["date"] ?: "", fontSize = 12.sp, color = Color.Black)
                                            }
                                            OutlinedButton(
                                                onClick = { onRecord(vaccine["tag"] ?: "") },
                                                modifier = Modifier.height(32.dp),
                                                shape = RoundedCornerShape(8.dp),
                                                border = BorderStroke(1.dp, PrimaryGreen),
                                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp)
                                            ) {
                                                Text(stringResource(R.string.record), color = PrimaryGreen, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                            }
                                        }
                                    }
                                    if (index < vaccines.size - 1) {
                                        HorizontalDivider(modifier = Modifier.padding(start = 56.dp, top = 4.dp, bottom = 4.dp), color = Color.LightGray.copy(alpha = 0.2f))
                                    }
                                }
                            } else {
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = stringResource(R.string.vaccinations_pending_scheduled, vaccines.size),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = PrimaryGreen,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordVaccinationScreen(tag: String, onBack: () -> Unit) {
    val context = LocalContext.current
    val pprLabel = stringResource(R.string.vaccine_ppr_label)
    var vaccineType by remember(pprLabel) { mutableStateOf(pprLabel) }
    var batchNumber by remember { mutableStateOf("PPR-2406-01") }
    var vaccinationDate by remember { mutableStateOf("15 Jun 2024") }
    
    var capturedPhotoUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var tempUriStr by rememberSaveable { mutableStateOf<String?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && tempUriStr != null) {
            capturedPhotoUri = Uri.parse(tempUriStr!!)
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            tempUriStr?.let { cameraLauncher.launch(Uri.parse(it)) }
        }
    }

    fun launchCamera() {
        val directory = File(context.cacheDir, "vaccine_photos")
        if (!directory.exists()) directory.mkdirs()
        val file = File(directory, "vaccine_${tag}_${System.currentTimeMillis()}.jpg")
        val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
        tempUriStr = uri.toString()
        
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            cameraLauncher.launch(uri)
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.record_vaccination), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryGreen,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        containerColor = Color(0xFFF8F9F5)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            
            // Goat Summary Card
            val mockGoats = listOf(
                Triple("ET-340801-0001", "Ramesh Naik", "Pipili"),
                Triple("ET-340801-0006", "Ramesh Naik", "Pipili"),
                Triple("ET-240801-0002", "Suresh Behera", "Balianta"),
                Triple("ET-340801-0003", "Manoj Sahoo", "Pipili"),
                Triple("ET-140801-0004", "Alok Dash", "Puri"),
                Triple("ET-540801-0005", "Prakash Rout", "Cuttack")
            )
            val goat = mockGoats.find { it.first == tag } ?: Triple(tag, "Unknown", "Unknown")

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Surface(modifier = Modifier.size(70.dp), shape = RoundedCornerShape(12.dp), color = Color(0xFFF0F0F0)) {
                        Box(contentAlignment = Alignment.Center) { Icon(painterResource(R.drawable.ic_ewe_custom), null, tint = Color.Gray) }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        val blackBengal = stringResource(R.string.black_bengal)
                        val female = stringResource(R.string.female)
                        val age = stringResource(R.string.age_months_suffix, 12)
                        Text(tag, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        Text(goat.second, fontSize = 13.sp, color = Color.Gray)
                        Text(stringResource(R.string.breed_gender_age_format, blackBengal, female, age), fontSize = 13.sp, color = Color.Gray)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            EnrollmentDropdownField(
                label = stringResource(R.string.vaccine_type),
                selectedValue = vaccineType,
                options = listOf(
                    stringResource(R.string.vaccine_ppr_label),
                    stringResource(R.string.vaccine_ettt_label),
                    stringResource(R.string.vaccine_fmd_label),
                    stringResource(R.string.vaccine_goat_pox_label)
                ),
                onValueChange = { vaccineType = it }
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            EnrollmentDropdownField(
                label = stringResource(R.string.batch_number),
                selectedValue = batchNumber,
                options = listOf("PPR-2406-01", "PPR-2406-02", "ETTT-2405-09"),
                onValueChange = { batchNumber = it }
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            EnrollmentTextField(
                label = stringResource(R.string.vaccination_date),
                value = vaccinationDate,
                onValueChange = { vaccinationDate = it },
                trailingIcon = Icons.Default.CalendarToday,
                readOnly = true,
                onTrailingIconClick = {
                    val calendar = Calendar.getInstance()
                    DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            vaccinationDate = "$dayOfMonth/${month + 1}/$year"
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                stringResource(R.string.photo_vaccine_label),
                fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Surface(
                modifier = Modifier.size(100.dp),
                shape = RoundedCornerShape(12.dp),
                color = Color.White,
                border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f)),
                onClick = { launchCamera() }
            ) {
                Box(contentAlignment = Alignment.Center) {
                    if (capturedPhotoUri != null) {
                        AsyncImage(model = capturedPhotoUri, contentDescription = null, modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(12.dp)), contentScale = androidx.compose.ui.layout.ContentScale.Crop)
                    } else {
                        Icon(Icons.Default.CameraAlt, null, tint = Color.Gray)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Button(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
            ) {
                Text(stringResource(R.string.save_record), fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClaimListScreen(navController: NavHostController, userRole: UserRole?, onBack: () -> Unit) {
    val languageState = LocalAppLanguage.current
    var searchQuery by remember { mutableStateOf("") }
    
    val isCoordinator = userRole == UserRole.COORDINATOR
    val isFarmer = userRole == UserRole.FARMER
    val themeColor = if (isCoordinator) CoordinatorOrange else if (isFarmer) PrimaryBlue else PrimaryGreen

    ResponsiveLayout(
        compact = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(languageState.value.getT("Claims", "दावे", "ଦାବି"), fontWeight = FontWeight.Bold, color = Color.White) },
                        navigationIcon = {
                            IconButton(onClick = onBack) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = themeColor,
                            titleContentColor = Color.White,
                            navigationIconContentColor = Color.White
                        )
                    )
                },
                bottomBar = { 
                    if (isCoordinator) {
                        CoordinatorBottomBar(navController)
                    } else if (isFarmer) {
                        FarmerBottomBar(navController)
                    } else {
                        DidiBottomBar(navController)
                    }
                },
                containerColor = Color(0xFFF8F9F5)
            ) { padding ->
                if (isCoordinator) {
                    CoordinatorClaimListContent(padding, searchQuery, { searchQuery = it }, themeColor) { claimId ->
                        navController.navigate("claim_review/$claimId")
                    }
                } else {
                    DidiClaimListContent(padding, searchQuery, { searchQuery = it }, themeColor, isFarmer) { claimId ->
                        navController.navigate("claim_review/$claimId")
                    }
                }
            }
        },
        expanded = {
            // Expanded layout remains similar but uses redesigned content
            Row(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing).navigationBarsPadding()) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                
                if (isCoordinator) {
                    NavigationRail(containerColor = Color.White) {
                        NavigationRailItem(
                            selected = true, 
                            onClick = { }, 
                            icon = { Icon(Icons.Default.Home, null) }, 
                            label = { Text(stringResource(R.string.dashboard)) },
                            colors = NavigationRailItemDefaults.colors(selectedIconColor = CoordinatorOrange, selectedTextColor = CoordinatorOrange, unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray, indicatorColor = Color.Transparent)
                        )
                    }
                } else if (isFarmer) {
                    NavigationRail(
                        containerColor = Color.White,
                        header = {
                            IconButton(onClick = { navController.navigate("profile") }) {
                                Icon(Icons.Default.Person, null, tint = PrimaryBlue)
                            }
                        }
                    ) {
                        NavigationRailItem(
                            selected = true, 
                            onClick = { }, 
                            icon = { Icon(Icons.Default.Home, null) }, 
                            label = { Text(stringResource(R.string.home)) },
                            colors = NavigationRailItemDefaults.colors(
                                selectedIconColor = PrimaryBlue,
                                selectedTextColor = PrimaryBlue,
                                unselectedIconColor = Color.Gray,
                                unselectedTextColor = Color.Gray,
                                indicatorColor = Color.Transparent
                            )
                        )
                        NavigationRailItem(
                            selected = false, 
                            onClick = { navController.navigate("goat_list") }, 
                            icon = { Icon(painterResource(R.drawable.ic_ewe_custom), null, modifier = Modifier.size(24.dp)) }, 
                            label = { Text(stringResource(R.string.my_goats)) },
                            colors = NavigationRailItemDefaults.colors(
                                selectedIconColor = PrimaryBlue,
                                selectedTextColor = PrimaryBlue,
                                unselectedIconColor = Color.Gray,
                                unselectedTextColor = Color.Gray,
                                indicatorColor = Color.Transparent
                            )
                        )
                    }
                } else {
                    NavigationRail {
                        NavigationRailItem(
                            selected = currentRoute == "didi_dashboard", 
                            onClick = { if (currentRoute != "didi_dashboard") navController.navigate("didi_dashboard") }, 
                            icon = { Icon(Icons.Default.Home, null) }, 
                            label = { Text(stringResource(R.string.home)) },
                            colors = NavigationRailItemDefaults.colors(selectedIconColor = PrimaryGreen, selectedTextColor = PrimaryGreen, unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray, indicatorColor = Color.Transparent)
                        )
                        NavigationRailItem(
                            selected = currentRoute == "goat_list", 
                            onClick = { if (currentRoute != "goat_list") navController.navigate("goat_list") }, 
                            icon = { Icon(painterResource(R.drawable.ic_ewe_custom), null) }, 
                            label = { Text(stringResource(R.string.goats)) },
                            colors = NavigationRailItemDefaults.colors(selectedIconColor = PrimaryGreen, selectedTextColor = PrimaryGreen, unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray, indicatorColor = Color.Transparent)
                        )
                        NavigationRailItem(
                            selected = currentRoute == "vaccine_list", 
                            onClick = { if (currentRoute != "vaccine_list") navController.navigate("vaccine_list") }, 
                            icon = { Icon(Icons.Default.MedicalServices, null) }, 
                            label = { Text(stringResource(R.string.vaccines)) },
                            colors = NavigationRailItemDefaults.colors(selectedIconColor = PrimaryGreen, selectedTextColor = PrimaryGreen, unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray, indicatorColor = Color.Transparent)
                        )
                    }
                }
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(stringResource(R.string.claims), fontWeight = FontWeight.Bold, color = Color.White) },
                            navigationIcon = {
                                IconButton(onClick = onBack) {
                                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = themeColor,
                                titleContentColor = Color.White,
                                navigationIconContentColor = Color.White
                            )
                        )
                    },
                    containerColor = Color(0xFFF8F9F5)
                ) { padding ->
                    DidiClaimListContent(padding, searchQuery, { searchQuery = it }, themeColor, isFarmer) { claimId ->
                        navController.navigate("claim_review/$claimId")
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoordinatorClaimListContent(
    padding: PaddingValues,
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    themeColor: Color,
    onClaimClick: (String) -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    
    Column(modifier = Modifier.padding(padding)) {
        // Coordinator Stats Grid
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = themeColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    CoordinatorStatCard(
                        label = stringResource(R.string.total_claims),
                        value = "152",
                        icon = Icons.AutoMirrored.Filled.Assignment,
                        modifier = Modifier.weight(1f)
                    )
                    Box(modifier = Modifier.width(1.dp).height(40.dp).background(Color.White.copy(alpha = 0.2f)).align(Alignment.CenterVertically))
                    CoordinatorStatCard(
                        label = stringResource(R.string.in_progress),
                        value = "54",
                        icon = Icons.Default.History,
                        modifier = Modifier.weight(1f)
                    )
                }
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = Color.White.copy(alpha = 0.2f))
                Row(modifier = Modifier.fillMaxWidth()) {
                    CoordinatorStatCard(
                        label = stringResource(R.string.status_completed),
                        value = "68",
                        icon = Icons.AutoMirrored.Filled.FactCheck,
                        modifier = Modifier.weight(1f)
                    )
                    Box(modifier = Modifier.width(1.dp).height(40.dp).background(Color.White.copy(alpha = 0.2f)).align(Alignment.CenterVertically))
                    CoordinatorStatCard(
                        label = stringResource(R.string.pending_action),
                        value = "30",
                        icon = Icons.Default.History, 
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(56.dp),
            placeholder = { Text(stringResource(R.string.search_claim_placeholder), fontSize = 12.sp) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray) },
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f)
            )
        )

        // Tabs
        val tabs = listOf(
            stringResource(R.string.all_total_count, 152),
            stringResource(R.string.in_progress_count, 54),
            stringResource(R.string.status_pending) + " (30)"
        )
        
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color.Transparent,
            divider = {},
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    height = 3.dp,
                    color = themeColor
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                val isSelected = selectedTab == index
                Tab(
                    selected = isSelected,
                    onClick = { selectedTab = index },
                    modifier = Modifier.padding(vertical = 12.dp)
                ) {
                    Text(
                        text = title,
                        color = if (isSelected) themeColor else Color.Gray,
                        fontSize = 13.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                }
            }
        }

        val mockClaims = listOf(
            mapOf("id" to "CLM7890", "farmer" to "Ramesh Kumar", "tag" to "TG-982311", "status" to "Coordinator Review", "date" to "20 May 2024", "didi" to "Sushma Didi"),
            mapOf("id" to "CLM7885", "farmer" to "Savitri Bai", "tag" to "TG-773210", "status" to "Verification", "date" to "19 May 2024", "didi" to "Sushma Didi"),
            mapOf("id" to "CLM7880", "farmer" to "Kamla Bai", "tag" to "TG-661122", "status" to "Didi Visit", "date" to "18 May 2024", "didi" to "Rekha Didi"),
            mapOf("id" to "CLM7875", "farmer" to "Geeta Bai", "tag" to "TG-554433", "status" to "Pending", "date" to "17 May 2024", "didi" to "Rekha Didi"),
            mapOf("id" to "CLM7868", "farmer" to "Mohan Lal", "tag" to "TG-332211", "status" to "Completed", "date" to "16 May 2024", "didi" to "Anjali Didi")
        )

        val groupedClaims = mockClaims.groupBy { it["didi"] ?: "Unknown" }
        val expandedDidis = remember { mutableStateMapOf<String, Boolean>() }

        LazyColumn(
            modifier = Modifier.fillMaxSize(), 
            contentPadding = PaddingValues(16.dp), 
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            groupedClaims.forEach { (didiName, claims) ->
                item {
                    val isExpanded = expandedDidis[didiName] ?: false
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(24.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { expandedDidis[didiName] = !isExpanded },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Surface(
                                    modifier = Modifier.size(52.dp),
                                    shape = CircleShape,
                                    color = themeColor.copy(alpha = 0.1f)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(Icons.Default.Person, null, tint = themeColor, modifier = Modifier.size(28.dp))
                                    }
                                }
                                Spacer(modifier = Modifier.width(16.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(didiName, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.ExtraBold, color = Color.Black)
                                    Text(stringResource(R.string.claims_by_didi, claims.size), style = MaterialTheme.typography.bodySmall, color = themeColor)
                                }
                                Icon(
                                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                    contentDescription = null,
                                    tint = Color.Gray
                                )
                            }
                            
                            if (isExpanded) {
                                Spacer(modifier = Modifier.height(16.dp))
                                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                                Spacer(modifier = Modifier.height(16.dp))

                                claims.forEachIndexed { index, claim ->
                                    DidiClaimCard(claim, themeColor) { onClaimClick(claim["id"] ?: "") }
                                    if (index < claims.size - 1) {
                                        Spacer(modifier = Modifier.height(12.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CoordinatorStatCard(label: String, value: String, icon: ImageVector, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(36.dp),
            shape = RoundedCornerShape(8.dp),
            color = Color.White.copy(alpha = 0.15f)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(icon, null, tint = Color.White, modifier = Modifier.size(20.dp))
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(label, color = Color.White.copy(alpha = 0.8f), fontSize = 11.sp, lineHeight = 13.sp)
            Text(value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DidiClaimListContent(
    padding: PaddingValues,
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    themeColor: Color,
    isFarmer: Boolean = false,
    onClaimClick: (String) -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    
    Column(modifier = Modifier.padding(padding)) {
        // redesigned Header with stats (Reduced Height and Justified)
        if (!isFarmer) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = themeColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = stringResource(R.string.total_claims),
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "48",
                            fontSize = 32.sp,
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                        Text(
                            text = stringResource(R.string.this_month),
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    
                    // Vertical Divider
                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .height(50.dp)
                            .background(Color.White.copy(alpha = 0.2f))
                    )
                    
                    Spacer(modifier = Modifier.width(20.dp))
                    
                    Column(
                        modifier = Modifier.weight(1.3f),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        ClaimStatRow(stringResource(R.string.in_progress), "28")
                        ClaimStatRow(stringResource(R.string.status_completed), "15")
                        ClaimStatRow(stringResource(R.string.status_pending), "5")
                    }
                }
            }
        } else {
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(56.dp),
            placeholder = { Text(stringResource(R.string.search_claim_placeholder), fontSize = 12.sp) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray) },
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tabs
        val tabs = if (isFarmer) {
            listOf(
                stringResource(R.string.all_label),
                stringResource(R.string.in_progress),
                stringResource(R.string.status_completed)
            )
        } else {
            listOf(
                stringResource(R.string.all_total_count, 48),
                stringResource(R.string.my_assigned_count, 18),
                stringResource(R.string.in_progress_count, 28)
            )
        }
        
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color.Transparent,
            divider = {},
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    height = 3.dp,
                    color = themeColor
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                val isSelected = selectedTab == index
                Tab(
                    selected = isSelected,
                    onClick = { selectedTab = index },
                    modifier = Modifier.padding(vertical = 12.dp)
                ) {
                    Text(
                        text = title,
                        color = if (isSelected) themeColor else Color.Gray,
                        fontSize = 13.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                }
            }
        }

        val mockClaims = listOf(
            mapOf("id" to "CLM7890", "farmer" to "Ramesh Kumar", "tag" to "TG-982311", "status" to "In Progress", "date" to "20 May 2024", "didi" to "Sushma Didi"),
            mapOf("id" to "CLM7885", "farmer" to "Savitri Bai", "tag" to "TG-773210", "status" to "Verification", "date" to "19 May 2024", "didi" to "Sushma Didi"),
            mapOf("id" to "CLM7880", "farmer" to "Kamla Bai", "tag" to "TG-661122", "status" to "Didi Visit", "date" to "18 May 2024", "didi" to "Rekha Didi"),
            mapOf("id" to "CLM7875", "farmer" to "Geeta Bai", "tag" to "TG-554433", "status" to "Pending", "date" to "17 May 2024", "didi" to "Rekha Didi"),
            mapOf("id" to "CLM7860", "farmer" to "Ramesh Kumar", "tag" to "TG-112233", "status" to "Completed", "date" to "15 May 2024", "didi" to "Sushma Didi")
        )

        val filteredClaims = mockClaims.filter { 
            (it["id"] ?: "").contains(searchQuery, ignoreCase = true) || 
            (it["tag"] ?: "").contains(searchQuery, ignoreCase = true)
        }.filter {
            if (isFarmer) {
                // Farmer dashboard: only show claims belonging to the logged-in farmer (Mock: Ramesh Kumar)
                it["farmer"] == "Ramesh Kumar" && when (selectedTab) {
                    1 -> it["status"] == "In Progress" || it["status"] == "Verification" || it["status"] == "Didi Visit"
                    2 -> it["status"] == "Completed"
                    else -> true
                }
            } else {
                when (selectedTab) {
                    1 -> it["didi"] == "Sushma Didi"
                    2 -> it["status"] == "In Progress"
                    else -> true
                }
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(), 
            contentPadding = PaddingValues(16.dp), 
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (isFarmer) {
                // Farmer view: direct list of claims
                items(filteredClaims) { claim ->
                    DidiClaimCard(claim, themeColor, isFarmer = true) { onClaimClick(claim["id"] ?: "") }
                }
            } else {
                // Didi view: grouped by farmer
                val groupedClaims = filteredClaims.groupBy { it["farmer"] ?: "Unknown" }
                val expandedFarmers = mutableStateMapOf<String, Boolean>()
                
                groupedClaims.forEach { (farmerName, claims) ->
                    item {
                        var isExpanded by remember { mutableStateOf(false) }
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            shape = RoundedCornerShape(16.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                            border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f))
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                // Farmer Info Header
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { isExpanded = !isExpanded },
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Surface(
                                        modifier = Modifier.size(40.dp),
                                        shape = CircleShape,
                                        color = themeColor.copy(alpha = 0.1f)
                                    ) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Icon(Icons.Default.Person, null, tint = themeColor, modifier = Modifier.size(20.dp))
                                        }
                                    }
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(farmerName, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.Black)
                                        Text(stringResource(R.string.claims_by_farmer, claims.size), style = MaterialTheme.typography.bodySmall, color = themeColor)
                                    }
                                    Icon(
                                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                        contentDescription = null,
                                        tint = Color.Gray
                                    )
                                }
                                
                                if (isExpanded) {
                                    Spacer(modifier = Modifier.height(12.dp))
                                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.4f))
                                    Spacer(modifier = Modifier.height(12.dp))

                                    claims.forEachIndexed { index, claim ->
                                        DidiClaimCard(claim, themeColor) { onClaimClick(claim["id"] ?: "") }
                                        if (index < claims.size - 1) {
                                            Spacer(modifier = Modifier.height(12.dp))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ClaimStatRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium
        )
        Surface(
            color = Color.White.copy(alpha = 0.12f),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(
                text = value,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DidiClaimCard(claim: Map<String, String>, themeColor: Color, isFarmer: Boolean = false, onClick: () -> Unit) {
    val languageState = LocalAppLanguage.current
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.2f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // If farmer, Tag No is the main title. If Didi, Claim ID is the title.
                val cardTitle = if (isFarmer) claim["tag"] ?: "" else claim["id"] ?: ""
                Text(cardTitle, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = Color.Black)
                
                val status = claim["status"] ?: ""
                val (statusText, statusBg, statusColor) = when(status) {
                    "In Progress" -> Triple(stringResource(R.string.in_progress), Color(0xFFFFF3E0), Color(0xFFE65100))
                    "Verification" -> Triple(stringResource(R.string.verification), Color(0xFFE8F5E9), Color(0xFF2E7D32))
                    "Didi Visit" -> Triple(stringResource(R.string.didi_visit), Color(0xFFE3F2FD), Color(0xFF1565C0))
                    else -> Triple(stringResource(R.string.status_pending), Color(0xFFF5F5F5), Color(0xFF616161))
                }
                
                Surface(
                    color = statusBg,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = statusText,
                        color = statusColor,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            if (isFarmer) {
                // Farmer dashboard details
                Text(
                    text = stringResource(R.string.claim_id_label_colon) + (claim["id"] ?: ""),
                    fontSize = 13.sp,
                    color = Color.Gray
                )
                val didi = claim["didi"] ?: ""
                val didiInfo = if (didi.isNotEmpty()) stringResource(R.string.approved_by_label_colon, didi) 
                               else stringResource(R.string.assigned_to_sushma_didi)
                Text(didiInfo, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.DarkGray)
            } else {
                // Didi dashboard details
                Text(claim["farmer"] ?: "", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.DarkGray)
                Text(
                    text = stringResource(R.string.tag_no_label, claim["tag"] ?: ""),
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.reported_on_label, claim["date"] ?: ""),
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.LightGray)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClaimListContent(
    padding: PaddingValues,
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    themeColor: Color,
    isFarmer: Boolean = false,
    onClaimClick: (String) -> Unit
) {
    val languageState = LocalAppLanguage.current
    Column(modifier = Modifier.padding(padding)) {
        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            placeholder = { Text(stringResource(R.string.search_claim_placeholder)) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray) },
            shape = RoundedCornerShape(24.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White, unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f))
        )

        val mockClaims = listOf(
            mapOf("id" to "CLM-240001-0021", "tag" to "ET-240001-0001", "status" to "Pending", "farmer" to "Ramesh Naik", "village" to "Pipili", "date" to "20 May 2024"),
            mapOf("id" to "CLM-1002", "tag" to "ET-240801-0002", "status" to "Approved", "farmer" to "Suresh Behera", "village" to "Balianta", "date" to "19 May 2024"),
            mapOf("id" to "CLM-1003", "tag" to "ET-340801-0003", "status" to "Rejected", "farmer" to "Manoj Sahoo", "village" to "Pipili", "date" to "18 May 2024"),
            mapOf("id" to "CLM-1004", "tag" to "ET-140801-0004", "status" to "Pending", "farmer" to "Alok Dash", "village" to "Puri", "date" to "17 May 2024"),
            mapOf("id" to "CLM-1005", "tag" to "ET-540801-0005", "status" to "Approved", "farmer" to "Prakash Rout", "village" to "Cuttack", "date" to "16 May 2024"),
            mapOf("id" to "CLM-1006", "tag" to "ET-340801-0001", "status" to "Approved", "farmer" to "Ramesh Naik", "village" to "Pipili", "date" to "15 May 2024"),
            mapOf("id" to "CLM-1007", "tag" to "ET-240001-0001", "status" to "Rejected", "farmer" to "Ramesh Naik", "village" to "Pipili", "date" to "14 May 2024"),
            mapOf("id" to "CLM-1008", "tag" to "ET-340801-0006", "status" to "Pending", "farmer" to "Ramesh Naik", "village" to "Pipili", "date" to "13 May 2024"),
            mapOf("id" to "CLM-1009", "tag" to "ET-340801-0003", "status" to "Approved", "farmer" to "Manoj Sahoo", "village" to "Pipili", "date" to "12 May 2024"),
            mapOf("id" to "CLM-1010", "tag" to "ET-140801-0004", "status" to "Approved", "farmer" to "Alok Dash", "village" to "Puri", "date" to "11 May 2024")
        )

        val filteredClaims = mockClaims.filter { 
            (it["id"] ?: "").contains(searchQuery, ignoreCase = true) || (it["tag"] ?: "").contains(searchQuery, ignoreCase = true)
        }.filter {
            if (isFarmer) it["farmer"] == "Ramesh Naik" else true
        }

        val groupedClaims = filteredClaims.groupBy { it["tag"] ?: "Unknown" }
        val expandedGoats = remember { mutableStateMapOf<String, Boolean>() }

        LazyColumn(
            modifier = Modifier.fillMaxSize(), 
            contentPadding = PaddingValues(16.dp), 
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (isFarmer) {
                items(filteredClaims) { claim ->
                    Card(
                        onClick = { onClaimClick(claim["id"] ?: "") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f))
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = claim["id"] ?: "",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        color = Color.Black
                                    )
                                    
                                    val status = claim["status"] ?: ""
                                    val (statusText, statusColor) = when(status) {
                                        "Pending" -> stringResource(R.string.in_progress) to AccentOrange
                                        "Approved" -> stringResource(R.string.verification) to SuccessGreen
                                        "Rejected" -> stringResource(R.string.didi_visit) to InfoBlue
                                        else -> stringResource(R.string.status_pending) to Color.Gray
                                    }

                                    Surface(
                                        color = statusColor.copy(alpha = 0.1f),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text(
                                            text = statusText,
                                            color = statusColor,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                                
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(claim["farmer"] ?: "", fontSize = 14.sp, color = Color.DarkGray)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = stringResource(R.string.tag_no_label, claim["tag"] ?: ""),
                                    fontSize = 13.sp,
                                    color = Color.Gray
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = stringResource(R.string.reported_on_label, claim["date"] ?: ""),
                                    fontSize = 13.sp,
                                    color = Color.Gray
                                )
                            }
                            
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.LightGray)
                        }
                    }
                }
            } else {
                // Grouped list by Goat (Tag ID) for others
                groupedClaims.forEach { (tagId, claims) ->
                    item {
                        val isExpanded = expandedGoats[tagId] ?: false
                        
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            shape = RoundedCornerShape(24.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                            border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f))
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                // Goat Header (Clickable to Expand)
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { expandedGoats[tagId] = !isExpanded },
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Surface(
                                        modifier = Modifier.size(52.dp),
                                        shape = CircleShape,
                                        color = themeColor.copy(alpha = 0.1f)
                                    ) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Icon(painterResource(R.drawable.ic_ewe_custom), null, tint = themeColor, modifier = Modifier.size(28.dp))
                                        }
                                    }
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(tagId, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.ExtraBold, color = Color.Black)
                                        Text(claims.firstOrNull()?.get("farmer") ?: "", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                                    }
                                    Icon(
                                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                        contentDescription = null,
                                        tint = Color.Gray
                                    )
                                }
                                
                                if (isExpanded) {
                                    Spacer(modifier = Modifier.height(16.dp))
                                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                                    Spacer(modifier = Modifier.height(16.dp))

                                    claims.forEachIndexed { index, claim ->
                                        Surface(
                                            onClick = { onClaimClick(claim["id"] ?: "") },
                                            modifier = Modifier.fillMaxWidth(),
                                            color = Color.Transparent,
                                            shape = RoundedCornerShape(12.dp)
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(vertical = 12.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Surface(modifier = Modifier.size(44.dp), shape = RoundedCornerShape(10.dp), color = themeColor.copy(alpha = 0.1f)) {
                                                    Box(contentAlignment = Alignment.Center) { Icon(Icons.AutoMirrored.Filled.Assignment, null, tint = themeColor, modifier = Modifier.size(20.dp)) }
                                                }
                                                Spacer(modifier = Modifier.width(16.dp))
                                                Column(modifier = Modifier.weight(1f)) {
                                                    Text(claim["id"] ?: "", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                                    Text(
                                                        when(claim["status"]) {
                                                            "Pending" -> stringResource(R.string.verification_in_progress)
                                                            "Approved" -> stringResource(R.string.claim_approved)
                                                            else -> stringResource(R.string.claim_rejected)
                                                        },
                                                        fontSize = 12.sp,
                                                        color = when(claim["status"]) {
                                                            "Approved" -> SuccessGreen
                                                            "Rejected" -> Color.Red
                                                            else -> AccentOrange
                                                        },
                                                        fontWeight = FontWeight.Medium
                                                    )
                                                }
                                                Surface(
                                                    color = when(claim["status"]) {
                                                        "Approved" -> SuccessGreen.copy(alpha = 0.1f)
                                                        "Rejected" -> Color.Red.copy(alpha = 0.1f)
                                                        else -> AccentOrange.copy(alpha = 0.1f)
                                                    },
                                                    shape = RoundedCornerShape(8.dp)
                                                ) {
                                                    Text(
                                                        when(claim["status"]) {
                                                            "Pending" -> stringResource(R.string.status_pending)
                                                            "Approved" -> stringResource(R.string.approved)
                                                            else -> stringResource(R.string.rejected)
                                                        },
                                                        color = when(claim["status"]) {
                                                            "Approved" -> SuccessGreen
                                                            "Rejected" -> Color.Red
                                                            else -> AccentOrange
                                                        },
                                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                                        fontSize = 11.sp,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                }
                                            }
                                        }
                                        if (index < claims.size - 1) {
                                            HorizontalDivider(modifier = Modifier.padding(start = 56.dp, top = 4.dp, bottom = 4.dp), color = Color.LightGray.copy(alpha = 0.2f))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClaimReviewScreen(navController: NavHostController, claimId: String, userRole: UserRole?, onBack: () -> Unit) {
    val context = LocalContext.current
    var selectedTab by remember { mutableIntStateOf(0) }

    val themeColor = when (userRole) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }

    val mockClaim = mapOf(
        "id" to "CLM7890",
        "status" to "In Progress",
        "tag" to "TG-982311",
        "farmer" to "Ramesh Kumar",
        "date" to "20 May 2024",
        "time" to "10:30 AM",
        "policy" to "POL12345678",
        "cause" to "High Fever"
    )

    if (userRole == UserRole.FARMER) {
        FarmerClaimFlow(navController, mockClaim, themeColor, onBack)
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = stringResource(R.string.claim_details),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = themeColor)
            )
        },
        containerColor = Color(0xFFF8F9F5)
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            DidiClaimDetailsContent(
                claim = mockClaim,
                themeColor = themeColor,
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it },
                userRole = userRole,
                onSubmit = {
                    if (userRole == UserRole.SURAKSHA_DIDI) {
                        navController.navigate("claim_bank_details/$claimId")
                    } else if (userRole == UserRole.COORDINATOR) {
                        navController.navigate("claim_verify/$claimId")
                    } else {
                        Toast.makeText(context, "Send to coordinator", Toast.LENGTH_SHORT).show()
                        onBack()
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClaimBankDetailsScreen(navController: NavHostController, claimId: String, userRole: UserRole?, onBack: () -> Unit) {
    val context = LocalContext.current
    val isDidi = userRole == UserRole.SURAKSHA_DIDI
    
    val themeColor = when (userRole) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }

    var accHolder by remember { mutableStateOf("") }
    var bankName by remember { mutableStateOf("") }
    var accNumber by remember { mutableStateOf("") }

    var ifscCode by remember { mutableStateOf("") }
    var branch by remember { mutableStateOf("") }
    var passbookPhotoUri by remember { mutableStateOf<Uri?>(null) }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> passbookPhotoUri = uri }

    Scaffold(
        containerColor = if (isDidi) Color(0xFFF8F9F5) else MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = stringResource(R.string.bank_details), 
                        fontWeight = FontWeight.Bold, 
                        color = Color.White
                    ) 
                },
                navigationIcon = { 
                    IconButton(onClick = onBack) { 
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack, 
                            contentDescription = null, 
                            tint = Color.White
                        ) 
                    } 
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = themeColor,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState())) {
            Text(
                text = stringResource(R.string.enter_bank_details_instruction), 
                style = MaterialTheme.typography.titleMedium, 
                fontWeight = FontWeight.Bold,
                color = if (isDidi) Color.Black else MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(24.dp))

            EnrollmentTextField(label = stringResource(R.string.acc_holder_name), value = accHolder, onValueChange = { accHolder = it }, borderColor = themeColor)
            Spacer(modifier = Modifier.height(16.dp))
            EnrollmentTextField(label = stringResource(R.string.bank_name), value = bankName, onValueChange = { bankName = it }, borderColor = themeColor)
            Spacer(modifier = Modifier.height(16.dp))
            EnrollmentTextField(label = stringResource(R.string.acc_number), value = accNumber, onValueChange = { accNumber = it }, keyboardType = KeyboardType.Number, borderColor = themeColor)
            Spacer(modifier = Modifier.height(16.dp))
            EnrollmentTextField(label = stringResource(R.string.ifsc_code), value = ifscCode, onValueChange = { ifscCode = it.uppercase() }, borderColor = themeColor)
            Spacer(modifier = Modifier.height(16.dp))
            EnrollmentTextField(label = stringResource(R.string.branch), value = branch, onValueChange = { branch = it }, borderColor = themeColor)
            
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(R.string.upload_passbook_instruction), 
                fontWeight = FontWeight.Bold, 
                fontSize = 14.sp,
                color = if (isDidi) Color.Black else MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            AttachmentBox(passbookPhotoUri) { photoPickerLauncher.launch("image/*") }

            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { 
                    Toast.makeText(context, "Claim submitted to coordinator", Toast.LENGTH_SHORT).show()
                    navController.navigate("didi_dashboard") {
                        popUpTo("didi_dashboard") { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = themeColor),
                enabled = accHolder.isNotBlank() && bankName.isNotBlank() && accNumber.isNotBlank() && ifscCode.isNotBlank() && passbookPhotoUri != null
            ) {
                Text(stringResource(R.string.submit_claim), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DidiClaimDetailsContent(
    claim: Map<String, String>,
    themeColor: Color,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    userRole: UserRole? = null,
    onSubmit: () -> Unit
) {
    val languageState = LocalAppLanguage.current
    Column(modifier = Modifier.fillMaxSize()) {
        // Header Info Card
        Card(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.2f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(claim["id"] ?: "", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.ExtraBold)
                    
                    Surface(
                        color = Color(0xFFFFF3E0),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.in_progress),
                            color = Color(0xFFE65100),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(claim["farmer"] ?: "", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.DarkGray)
                Text(
                    text = stringResource(R.string.tag_no_label, claim["tag"] ?: ""),
                    fontSize = 13.sp,
                    color = Color.Gray
                )
                Text(
                    text = stringResource(R.string.reported_on_label, (claim["date"] ?: "") + ", " + (claim["time"] ?: "")),
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        }

        // Tabs
        val tabs = listOf(stringResource(R.string.overview_label), stringResource(R.string.documents_label))
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color.Transparent,
            divider = {},
            indicator = {}
        ) {
            tabs.forEachIndexed { index, title ->
                val isSelected = selectedTab == index
                Tab(
                    selected = isSelected,
                    onClick = { onTabSelected(index) },
                    modifier = Modifier.padding(horizontal = 4.dp)
                ) {
                    Surface(
                        color = if (isSelected) themeColor.copy(alpha = 0.1f) else Color.Transparent,
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = title,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                            color = if (isSelected) themeColor else Color.Gray,
                            fontSize = 13.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        )
                    }
                }
            }
        }

        // Tab Content
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when (selectedTab) {
                0 -> { // Overview
                    // Claim Progress
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.2f))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(stringResource(R.string.claim_progress), fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            Spacer(modifier = Modifier.height(20.dp))
                            
                            val steps = listOf(
                                Triple(stringResource(R.string.death_reported_label), "20 May 2024, 10:30 AM", true),
                                Triple(stringResource(R.string.didi_site_visit_label), "20 May 2024, 02:15 PM", true),
                                Triple(stringResource(R.string.verification_label), "21 May 2024, 11:40 AM", true),
                                Triple(stringResource(R.string.coordinator_review_label), stringResource(R.string.status_pending), false),
                                Triple(stringResource(R.string.payment_sent_label), stringResource(R.string.upcoming_label), false)
                            )
                            
                            steps.forEachIndexed { index, step ->
                                DidiClaimProgressItem(
                                    stepNumber = index + 1,
                                    title = step.first,
                                    subtitle = step.second,
                                    isCompleted = step.third,
                                    isLast = index == steps.size - 1,
                                    themeColor = themeColor
                                )
                            }
                        }
                    }

                    // Claim Information
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.2f))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(stringResource(R.string.claim_information), fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            Spacer(modifier = Modifier.height(16.dp))
                            
                            DidiInfoRow(stringResource(R.string.policy_number), claim["policy"] ?: "")
                            DidiInfoRow(stringResource(R.string.goats_insured), claim["tag"] ?: "")
                            DidiInfoRow(stringResource(R.string.date_of_death), "19 May 2024")
                            DidiInfoRow(stringResource(R.string.mortality_report), claim["cause"] ?: "")
                        }
                    }
                }
                1 -> { // Documents
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.2f))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(stringResource(R.string.uploaded_documents), fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            DidiDocItem("Death Report Photo", "20 May 2024, 10:31 AM", themeColor)
                            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.2f))
                            DidiDocItem("Didi Site Visit Photo", "20 May 2024, 02:20 PM", themeColor)
                            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.2f))
                            DidiDocItem("Goat Ear Tag Photo", "20 May 2024, 02:21 PM", themeColor)
                            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.2f))
                            DidiDocItem("Veterinary Certificate", "20 May 2024, 03:10 PM", themeColor, icon = Icons.Default.MedicalServices)
                            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.2f))
                            DidiDocItem("Other Supporting Document", "20 May 2024, 03:15 PM", themeColor)
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.VerifiedUser, null, tint = SuccessGreen, modifier = Modifier.size(24.dp))
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                stringResource(R.string.documents_secured_tip),
                                fontSize = 13.sp,
                                color = Color.DarkGray,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }
        }

        // Bottom Action Button
        Button(
            onClick = onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = themeColor)
        ) {
            val buttonText = if (userRole == UserRole.SURAKSHA_DIDI || userRole == UserRole.COORDINATOR) 
                stringResource(R.string.next)
            else 
                stringResource(R.string.submit)
            Text(buttonText, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun DidiClaimProgressItem(
    stepNumber: Int,
    title: String,
    subtitle: String,
    isCompleted: Boolean,
    isLast: Boolean,
    themeColor: Color
) {
    val languageState = LocalAppLanguage.current
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(if (isCompleted) themeColor else Color.White)
                    .border(1.dp, if (isCompleted) themeColor else Color.LightGray.copy(alpha = 0.5f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (isCompleted) {
                    Icon(Icons.Default.Check, null, tint = Color.White, modifier = Modifier.size(16.dp))
                } else {
                    Text(stepNumber.toString(), color = Color.Gray, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(1.5.dp)
                        .height(40.dp)
                        .background(if (isCompleted) themeColor else Color.LightGray.copy(alpha = 0.3f))
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.padding(bottom = if (isLast) 0.dp else 20.dp)) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)
            Text(text = subtitle, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Composable
fun DidiInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.Gray, fontSize = 13.sp)
        Text(value, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color.Black)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClaimVerifyScreen(navController: NavHostController, claimId: String, userRole: UserRole?, onBack: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val context = LocalContext.current
    val themeColor = CoordinatorOrange
    
    var remarks by remember { mutableStateOf("Goat died due to PPR disease.") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.verify_claim), fontWeight = FontWeight.Bold, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = themeColor)
            )
        },
        containerColor = Color(0xFFF8F9F5)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Subtitle with Tag ID
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.CheckCircle, 
                    contentDescription = null, 
                    tint = SuccessGreen, 
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "ET-240801-0001", 
                    color = Color.Gray, 
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = stringResource(R.string.verification_checklist),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))

            val checklistItems = listOf(
                stringResource(R.string.ear_tag_verified),
                stringResource(R.string.photos_verified),
                stringResource(R.string.policy_active),
                stringResource(R.string.vaccination_valid)
            )

            checklistItems.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Check, null, modifier = Modifier.size(20.dp), tint = Color.Black)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = item, modifier = Modifier.weight(1f), fontSize = 16.sp, color = Color.Black)
                    Icon(Icons.Default.CheckCircle, null, tint = SuccessGreen, modifier = Modifier.size(20.dp))
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = stringResource(R.string.remarks_optional),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = remarks,
                onValueChange = { remarks = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
                    focusedBorderColor = themeColor
                )
            )

            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = { 
                    Toast.makeText(context, "Claim Approved", Toast.LENGTH_SHORT).show()
                    navController.navigate("claim_payout/$claimId")
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = themeColor)
            ) {
                Text(
                    text = stringResource(R.string.approve_claim_label), 
                    fontWeight = FontWeight.Bold, 
                    fontSize = 16.sp
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Button(
                onClick = { 
                    Toast.makeText(context, "Claim Rejected", Toast.LENGTH_SHORT).show()
                    navController.navigate("claim_list") {
                        popUpTo("claim_list") { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
            ) {
                Text(
                    text = stringResource(R.string.reject_claim_label),
                    fontWeight = FontWeight.Bold, 
                    fontSize = 16.sp
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClaimPayoutScreen(navController: NavHostController, claimId: String, userRole: UserRole?, onBack: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val context = LocalContext.current
    val themeColor = CoordinatorOrange
    var isPayoutConfirmed by remember { mutableStateOf(false) }

    val mockClaim = mapOf(
        "id" to "CLM-240801-0001",
        "accHolder" to "Ramesh Naik",
        "accNumber" to "XXXXXXXX4321",
        "ifsc" to "SBIN0001234",
        "amount" to "₹3,000"
    )

    LaunchedEffect(isPayoutConfirmed) {
        if (isPayoutConfirmed) {
            kotlinx.coroutines.delay(1500)
            navController.navigate("claim_approved/$claimId")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.claim_payout), fontWeight = FontWeight.Bold, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = themeColor)
            )
        },
        containerColor = Color(0xFFF8F9F5)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.CheckCircle, null, tint = SuccessGreen, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = mockClaim["id"] ?: "", color = Color.Gray, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = stringResource(R.string.bank_details),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    PayoutDetailRow(stringResource(R.string.acc_holder_name), mockClaim["accHolder"] ?: "")
                    PayoutDetailRow(stringResource(R.string.acc_number), mockClaim["accNumber"] ?: "")
                    PayoutDetailRow(stringResource(R.string.ifsc_code), mockClaim["ifsc"] ?: "")
                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
                    PayoutDetailRow(stringResource(R.string.approved_amount), mockClaim["amount"] ?: "", isBold = true)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { isPayoutConfirmed = true },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = themeColor)
            ) {
                Text(stringResource(R.string.confirm_payout), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            if (isPayoutConfirmed) {
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(Icons.Default.CheckCircle, null, tint = SuccessGreen, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(stringResource(R.string.payout_successful), color = Color.Black, fontWeight = FontWeight.Medium)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun ClaimApprovedScreen(navController: NavHostController, claimId: String, userRole: UserRole?) {
    val languageState = LocalAppLanguage.current
    val themeColor = CoordinatorOrange
    
    val mockClaim = mapOf(
        "id" to "CLM-240801-0001",
        "amount" to "₹3,000"
    )

    Scaffold(
        containerColor = Color(0xFFF8F9FB)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.3f))
            
            Surface(
                modifier = Modifier.size(100.dp),
                shape = CircleShape,
                color = SuccessGreen
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Check, null, tint = Color.White, modifier = Modifier.size(60.dp))
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Text(
                text = stringResource(R.string.claim_approved),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = themeColor
            )
            
            Text(
                text = mockClaim["id"] ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(48.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = stringResource(R.string.approved_amount),
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = mockClaim["amount"] ?: "",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp)) {
                Text(text = stringResource(R.string.payout_mode), style = MaterialTheme.typography.labelMedium, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = stringResource(R.string.bank_transfer), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = Color.Black)
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Text(text = stringResource(R.string.status), style = MaterialTheme.typography.labelMedium, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = stringResource(R.string.approved), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = SuccessGreen)
            }

            Spacer(modifier = Modifier.weight(1f))
            
            Button(
                onClick = {
                    navController.navigate("coordinator_dashboard") {
                        popUpTo("coordinator_dashboard") { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = themeColor)
            ) {
                Text(stringResource(R.string.mark_as_paid), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun PayoutDetailRow(label: String, value: String, isBold: Boolean = false) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = Color.Gray, fontSize = 14.sp)
        Text(value, fontWeight = if (isBold) FontWeight.ExtraBold else FontWeight.Bold, fontSize = if (isBold) 16.sp else 14.sp)
    }
}

@Composable
fun DidiDocItem(title: String, time: String, themeColor: Color, icon: ImageVector = Icons.AutoMirrored.Filled.FactCheck) {
    val languageState = LocalAppLanguage.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(40.dp),
            shape = RoundedCornerShape(8.dp),
            color = Color(0xFFF5F5F5)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(icon, null, tint = Color.Gray, modifier = Modifier.size(20.dp))
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)
            Text(time, fontSize = 12.sp, color = Color.Gray)
        }
        Row(
            modifier = Modifier.clickable { /* View */ },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.RemoveRedEye, null, tint = SuccessGreen, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(stringResource(R.string.view), color = SuccessGreen, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
    }
}


@Composable
fun FarmerClaimFlow(navController: NavHostController, claim: Map<String, String>, themeColor: Color, onBack: () -> Unit) {
    var showDetails by remember { mutableStateOf(false) }
    
    if (showDetails) {
        FarmerClaimDetailsScreen(navController, claim, themeColor) { showDetails = false }
    } else {
        FarmerClaimStatusScreen(navController, claim, themeColor, onBack) { showDetails = true }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FarmerClaimStatusScreen(navController: NavHostController, claim: Map<String, String>, themeColor: Color, onBack: () -> Unit, onViewDetails: () -> Unit) {
    val languageState = LocalAppLanguage.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = "Claim #${claim["id"] ?: "CLM7890"}", 
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ) 
                },
                navigationIcon = { 
                    IconButton(onClick = onBack) { 
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Color.White) 
                    } 
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = themeColor,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Status Banner Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = themeColor.copy(alpha = 0.05f)),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, themeColor.copy(alpha = 0.1f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            modifier = Modifier.size(56.dp),
                            shape = RoundedCornerShape(12.dp),
                            color = themeColor
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.VerifiedUser, null, tint = Color.White, modifier = Modifier.size(32.dp))
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = stringResource(R.string.your_claim_in_progress),
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = themeColor
                            )
                            Text(
                                text = stringResource(R.string.we_are_reviewing_claim),
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(20.dp))
                    
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column {
                            Text(text = stringResource(R.string.claim_id_label), fontSize = 12.sp, color = Color.Gray)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = claim["id"] ?: "CLM7890", 
                                    fontWeight = FontWeight.Bold, 
                                    fontSize = 15.sp,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(Icons.Default.History, contentDescription = "Copy", modifier = Modifier.size(16.dp), tint = Color.Gray)
                            }
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(text = stringResource(R.string.reported_date), fontSize = 12.sp, color = Color.Gray)
                            Text(
                                text = claim["deathDate"] ?: "20 May 2024, 10:30 AM", 
                                fontWeight = FontWeight.Bold, 
                                fontSize = 15.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Claim Progress Stepper Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.2f))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = stringResource(R.string.claim_progress),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = themeColor
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    val steps = listOf(
                        Triple("Death Reported", "You have reported the death of your goat.", "20 May 2024, 10:30 AM"),
                        Triple("Didi Site Visit", "Our Didi has visited your location.", "20 May 2024, 02:15 PM"),
                        Triple("Verification", "Verification completed successfully.", "21 May 2024, 11:40 AM"),
                        Triple("Coordinator Review", "Your claim is under review.", ""),
                        Triple("Payment Sent", "Payment will be sent to your account.", "")
                    )
                    
                    steps.forEachIndexed { index, step ->
                        ClaimProgressItem(
                            stepNumber = index + 1,
                            title = step.first,
                            subtitle = step.second,
                            time = step.third,
                            isCompleted = index <= 2,
                            isLast = index == steps.size - 1,
                            themeColor = themeColor
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Info Note
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = themeColor.copy(alpha = 0.05f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.AutoMirrored.Filled.Chat, null, tint = themeColor, modifier = Modifier.size(32.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = stringResource(R.string.claim_update_notify),
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                        lineHeight = 20.sp
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Button(
                onClick = onViewDetails,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = themeColor)
            ) {
                Text(
                    text = stringResource(R.string.view_claim_details),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun ClaimProgressItem(
    stepNumber: Int,
    title: String,
    subtitle: String,
    time: String,
    isCompleted: Boolean,
    isLast: Boolean,
    themeColor: Color = SuccessGreen
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(if (isCompleted) themeColor else Color(0xFFEEEEEE)),
                contentAlignment = Alignment.Center
            ) {
                if (isCompleted) {
                    Icon(Icons.Default.Check, null, tint = Color.White, modifier = Modifier.size(16.dp))
                } else {
                    Text(stepNumber.toString(), color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(54.dp)
                        .background(if (isCompleted) themeColor else Color(0xFFEEEEEE))
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.padding(bottom = if (isLast) 0.dp else 24.dp)) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = if (isCompleted) Color.Black else Color.DarkGray
            )
            Text(
                text = subtitle,
                fontSize = 13.sp,
                color = Color.Gray,
                lineHeight = 18.sp
            )
            if (time.isNotEmpty()) {
                Text(
                    text = time,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FarmerClaimDetailsScreen(navController: NavHostController, claim: Map<String, String>, themeColor: Color, onBack: () -> Unit) {
    val languageState = LocalAppLanguage.current
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf(
        stringResource(R.string.details_label),
        stringResource(R.string.uploaded_documents),
        stringResource(R.string.history_label)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Claim #${claim["id"]}", fontWeight = FontWeight.Bold) },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) } },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = themeColor,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().background(Color(0xFFF8F9FB))) {
            // Tabs
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color.White,
                contentColor = themeColor,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(Modifier.tabIndicatorOffset(tabPositions[selectedTab]), color = themeColor)
                },
                divider = {}
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title, fontWeight = FontWeight.Bold, color = if (selectedTab == index) themeColor else Color.Gray) }
                    )
                }
            }

            Column(modifier = Modifier.weight(1f).verticalScroll(rememberScrollState()).padding(16.dp)) {
                when(selectedTab) {
                    0 -> { // Details
                        FarmerClaimInfoSection(stringResource(R.string.claim_information), themeColor) {
                            FarmerClaimInfoItem(stringResource(R.string.claim_id_label), claim["id"] ?: "")
                            FarmerClaimInfoItem(stringResource(R.string.policy_number), claim["policy"] ?: "")
                            FarmerClaimInfoItem(stringResource(R.string.goats_insured), claim["tag"] ?: "")
                            FarmerClaimInfoItem(stringResource(R.string.goat_name_label), "Rani")
                            FarmerClaimInfoItem(stringResource(R.string.breed), claim["breed"] ?: "")
                            FarmerClaimInfoItem(stringResource(R.string.date_of_death), "19 May 2024")
                            FarmerClaimInfoItem(stringResource(R.string.reported_date), claim["deathDate"] ?: "")
                            FarmerClaimInfoItem(stringResource(R.string.mortality_report), claim["deathCause"] ?: "")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        FarmerClaimInfoSection(stringResource(R.string.farmer_information), themeColor) {
                            FarmerClaimInfoItem(stringResource(R.string.farmer_name), claim["farmer"] ?: "")
                            FarmerClaimInfoItem(stringResource(R.string.mobile_number), claim["phone"] ?: "")
                            FarmerClaimInfoItem(stringResource(R.string.village), "Rampur")
                            FarmerClaimInfoItem(stringResource(R.string.block), "Bhopal")
                            FarmerClaimInfoItem(stringResource(R.string.district), "Bhopal, MP")
                        }
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        OutlinedButton(
                            onClick = { navController.navigate("help_support") },
                            modifier = Modifier.fillMaxWidth().height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, themeColor)
                        ) {
                            Icon(Icons.Default.Phone, null, tint = themeColor, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(stringResource(R.string.contact_support), color = themeColor, fontWeight = FontWeight.Bold)
                        }
                    }
                    1 -> { // Documents
                        Text(stringResource(R.string.uploaded_documents), fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        val docs = listOf(
                            Pair("Death Report Photo", "20 May 2024, 10:31 AM"),
                            Pair("Didi Site Visit Photo", "20 May 2024, 02:20 PM"),
                            Pair("Goat Ear Tag Photo", "20 May 2024, 02:21 PM"),
                            Pair("Veterinary Certificate", "20 May 2024, 03:10 PM"),
                            Pair("Other Supporting Document", "20 May 2024, 03:15 PM")
                        )
                        
                        docs.forEach { doc ->
                            FarmerDocItem(doc.first, doc.second, themeColor)
                        }
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F7F8)),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.VerifiedUser, null, tint = SuccessGreen, modifier = Modifier.size(24.dp))
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(stringResource(R.string.documents_secured_tip), fontSize = 12.sp, color = Color.Gray)
                            }
                        }
                    }
                    2 -> { // History
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth().padding(top = 40.dp)) {
                            Icon(Icons.Default.History, null, modifier = Modifier.size(48.dp), tint = Color.LightGray)
                            Text(stringResource(R.string.no_history_available_msg), color = Color.Gray, modifier = Modifier.padding(top = 8.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FarmerClaimInfoSection(title: String, themeColor: Color, content: @Composable ColumnScope.() -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = themeColor)
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f))
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                content()
            }
        }
    }
}

@Composable
fun FarmerClaimInfoItem(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = Color.Gray, fontSize = 14.sp)
        Text(value, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)
    }
}

@Composable
fun FarmerDocItem(title: String, time: String, themeColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f))
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Gavel, null, tint = Color.Gray, modifier = Modifier.size(24.dp)) // Generic doc icon
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)
                Text(time, fontSize = 12.sp, color = Color.Gray)
            }
            TextButton(onClick = { /* View doc */ }) {
                Icon(Icons.Default.RemoveRedEye, null, tint = themeColor, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(stringResource(R.string.view), color = themeColor, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun ClaimDetailsStep(claim: Map<String, String>, themeColor: Color = PrimaryGreen, isFarmer: Boolean = false, onNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(claim["id"] ?: "", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(claim["status"] ?: "", color = AccentOrange, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
        
        Spacer(modifier = Modifier.height(24.dp))

        // Goat Info
        ClaimInfoSection("Goat") {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(modifier = Modifier.size(48.dp), shape = CircleShape, color = Color(0xFFF0F0F0)) {
                    Box(contentAlignment = Alignment.Center) { Icon(painterResource(R.drawable.ic_ewe_custom), null, tint = Color.Gray) }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(claim["tag"] ?: "", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    Text("${claim["breed"]} • ${claim["gender"]} • ${claim["age"]}", color = Color.Gray, fontSize = 13.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (!isFarmer) {
            // Farmer Info
            ClaimInfoSection("Farmer") {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(modifier = Modifier.size(48.dp), shape = CircleShape, color = Color(0xFFF0F0F0)) {
                        Box(contentAlignment = Alignment.Center) { Icon(Icons.Default.Person, null, tint = Color.Gray) }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(claim["farmer"] ?: "", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        Text(claim["phone"] ?: "", color = Color.Gray, fontSize = 13.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (isFarmer && claim["approvedByDidi"] != null) {
            Spacer(modifier = Modifier.height(16.dp))
            // Didi Info (Approver)
            ClaimInfoSection("Verified By") {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(modifier = Modifier.size(48.dp), shape = CircleShape, color = themeColor.copy(alpha = 0.1f)) {
                        Box(contentAlignment = Alignment.Center) { Icon(Icons.Default.Person, null, tint = themeColor) }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(claim["approvedByDidi"] ?: "", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        Text(claim["didiPhone"] ?: "", color = Color.Gray, fontSize = 13.sp)
                        Text("Approved and sent to coordinator", color = SuccessGreen, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Other Details
        ClaimDetailItem("Date of Death", claim["deathDate"] ?: "")
        ClaimDetailItem("Cause", claim["deathCause"] ?: "")
        ClaimDetailItem("Vaccine", claim["vaccine"] ?: "")
        ClaimDetailItem("Policy Number", claim["policy"] ?: "")
        ClaimDetailItem("Sum Insured", claim["sumInsured"] ?: "")

        if (!isFarmer) {
            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onNext,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = themeColor)
            ) {
                Text("View Documents", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun ClaimDocumentsStep(claim: Map<String, String>, themeColor: Color = PrimaryGreen, onNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Icon(Icons.Default.CheckCircle, null, tint = SuccessGreen, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(claim["tag"] ?: "", color = Color.Gray, fontSize = 14.sp)
        }
        
        Spacer(modifier = Modifier.height(24.dp))

        Text("Photos of Dead Goat", fontWeight = FontWeight.Bold, fontSize = 15.sp)
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            repeat(3) {
                Box(modifier = Modifier.weight(1f).aspectRatio(1f).background(Color.LightGray.copy(alpha = 0.3f), RoundedCornerShape(12.dp)))
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            repeat(3) {
                Box(modifier = Modifier.weight(1f).aspectRatio(1f).background(Color.LightGray.copy(alpha = 0.3f), RoundedCornerShape(12.dp)))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Ear Tag Photo", fontWeight = FontWeight.Bold, fontSize = 15.sp)
        Spacer(modifier = Modifier.height(12.dp))
        Box(modifier = Modifier.size(100.dp).background(Color.LightGray.copy(alpha = 0.3f), RoundedCornerShape(12.dp)))

        Spacer(modifier = Modifier.height(24.dp))

        Text("Other Documents", fontWeight = FontWeight.Bold, fontSize = 15.sp)
        Spacer(modifier = Modifier.height(12.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
        ) {
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.CheckCircle, null, tint = SuccessGreen, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Text("Vet Report (Optional)", modifier = Modifier.weight(1f))
                Text("Uploaded", color = SuccessGreen, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onNext,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = themeColor)
        ) {
            Text("Next", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

@Composable
fun ClaimVerifyStep(
    claim: Map<String, String>,
    themeColor: Color = PrimaryGreen,
    userRole: UserRole? = null,
    onApprove: () -> Unit,
    onReject: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Icon(Icons.Default.CheckCircle, null, tint = SuccessGreen, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(claim["tag"] ?: "", color = Color.Gray, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Verification Checklist", fontWeight = FontWeight.Bold, fontSize = 15.sp)
        Spacer(modifier = Modifier.height(16.dp))

        val checks = listOf("Ear Tag Verified", "Photos Verified", "Policy Active", "Vaccination Valid")
        checks.forEach { check ->
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Check, null, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Text(check, modifier = Modifier.weight(1f))
                Icon(Icons.Default.CheckCircle, null, tint = SuccessGreen, modifier = Modifier.size(20.dp))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Remarks (Optional)", fontWeight = FontWeight.Bold, fontSize = 15.sp)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "Goat died due to PPR disease.",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth().height(100.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f))
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onApprove,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = themeColor)
        ) {
            Text(
                if (userRole == UserRole.SURAKSHA_DIDI) "Approve & Send" else "Approve Claim",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = onReject,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
        ) {
            Text("Reject Claim", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

@Composable
fun ClaimPayoutStep(claim: Map<String, String>, themeColor: Color = PrimaryGreen, onDone: () -> Unit) {
    var isPayoutConfirmed by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(isPayoutConfirmed) {
        if (isPayoutConfirmed) {
            Toast.makeText(context, "Payout successful", Toast.LENGTH_SHORT).show()
            kotlinx.coroutines.delay(1500)
            onDone()
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Icon(Icons.Default.CheckCircle, null, tint = SuccessGreen, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(claim["id"] ?: "", color = Color.Gray, fontSize = 14.sp)
        }
        
        Spacer(modifier = Modifier.height(32.dp))

        Text("Bank Details", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f))
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                PayoutDetailItem("A/C Holder", claim["accHolder"] ?: "")
                PayoutDetailItem("A/C Number", claim["accNumber"] ?: "")
                PayoutDetailItem("IFSC", claim["ifsc"] ?: "")
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                PayoutDetailItem("Amount", claim["approvedAmount"] ?: "", isBold = true)
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { isPayoutConfirmed = true },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = themeColor)
        ) {
            Text("Confirm Payout", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
        
        if (isPayoutConfirmed) {
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(Icons.Default.CheckCircle, null, tint = SuccessGreen, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("Payout successful", color = Color.Black, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}

@Composable
fun PayoutDetailItem(label: String, value: String, isBold: Boolean = false) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = Color.Gray, fontSize = 14.sp)
        Text(value, fontWeight = if (isBold) FontWeight.ExtraBold else FontWeight.Bold, fontSize = if (isBold) 16.sp else 14.sp)
    }
}

@Composable
fun ClaimApprovedStep(claim: Map<String, String>, themeColor: Color = PrimaryGreen, onDone: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.3f))
        
        // Success Circle
        Surface(
            modifier = Modifier.size(100.dp),
            shape = CircleShape,
            color = SuccessGreen
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(Icons.Default.Check, null, tint = Color.White, modifier = Modifier.size(60.dp))
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = "Claim Approved",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = themeColor
        )
        
        Text(
            text = claim["id"] ?: "",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Amount Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Approved Amount",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = claim["approvedAmount"] ?: "",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Payout Mode
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp)) {
            Text(
                text = "Payout Mode",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Bank Transfer",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "Status",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Approved",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = SuccessGreen
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        
        Button(
            onClick = onDone,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = themeColor)
        ) {
            Text("Mark as Paid", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun ClaimInfoSection(label: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(label, fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding(bottom = 8.dp))
        content()
    }
}

@Composable
fun ClaimDetailItem(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.Gray, fontSize = 14.sp)
        Text(value, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupProfileScreen(
    role: UserRole,
    initialName: String,
    initialPhone: String,
    onComplete: (UserRole, String, String) -> Unit,
    onBack: () -> Unit
) {
    val languageState = LocalAppLanguage.current
    val context = LocalContext.current
    
    // Permission Handling
    var showPermissionRationale by remember { mutableStateOf(true) }
    val permissionsToRequest = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.values.all { it }
        if (!allGranted) {
            Toast.makeText(context, context.getString(R.string.permissions_required_denied_msg), Toast.LENGTH_LONG).show()
            (context as? Activity)?.finish()
        }
    }

    if (showPermissionRationale) {
        AlertDialog(
            onDismissRequest = { (context as? Activity)?.finish() },
            title = { Text(stringResource(R.string.permissions_required_title), fontWeight = FontWeight.Bold, color = Color.Black) },
            text = { 
                Text(stringResource(R.string.permissions_required_message), color = Color.Black)
            },
            confirmButton = {
                Button(
                    onClick = {
                        showPermissionRationale = false
                        permissionLauncher.launch(permissionsToRequest)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
                ) {
                    Text(stringResource(R.string.allow))
                }
            },
            dismissButton = {
                TextButton(onClick = { (context as? Activity)?.finish() }) {
                    Text(stringResource(R.string.deny_close_label), color = Color.Red)
                }
            },
            containerColor = Color.White,
            shape = RoundedCornerShape(16.dp)
        )
    }

    var profilePhotoUri by remember { mutableStateOf<Uri?>(null) }
    var name by remember { mutableStateOf(initialName) }
    val phone by remember { mutableStateOf(initialPhone) }
    var dob by remember { mutableStateOf("") }
    val femaleLabel = stringResource(R.string.female)
    var gender by remember(femaleLabel) { mutableStateOf(femaleLabel) }
    
    var state by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }
    var block by remember { mutableStateOf("") }
    var village by remember { mutableStateOf("") }
    var pincode by remember { mutableStateOf("") }
    
    var aadhaarNumber by remember { mutableStateOf("") }
    var aadhaarPhotoUri by remember { mutableStateOf<Uri?>(null) }

    val themeColor = when(role) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }

    val locationLabel = if (role == UserRole.COORDINATOR) 
        stringResource(R.string.location_required_label)
    else 
        stringResource(R.string.village_required_label)

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> profilePhotoUri = uri }

    val aadhaarPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> aadhaarPhotoUri = uri }

    var tempUriStr by rememberSaveable { mutableStateOf<String?>(null) }
    var captureTarget by rememberSaveable { mutableStateOf("") } // "profile" or "aadhaar"

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && tempUriStr != null) {
            val uri = Uri.parse(tempUriStr!!)
            if (captureTarget == "profile") profilePhotoUri = uri
            else if (captureTarget == "aadhaar") aadhaarPhotoUri = uri
        }
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            tempUriStr?.let { cameraLauncher.launch(Uri.parse(it)) }
        }
    }

    fun launchCamera(target: String) {
        try {
            captureTarget = target
            val directory = File(context.cacheDir, "profile_setup")
            if (!directory.exists()) directory.mkdirs()
            val file = File(directory, "${target}_${System.currentTimeMillis()}.jpg")
            val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
            tempUriStr = uri.toString()
            
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                cameraLauncher.launch(uri)
            } else {
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    val calendar = Calendar.getInstance()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.setup_profile), color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = themeColor)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF8F9F5))
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Section 1: Basic Info
            ProfileSetupSection(stringResource(R.string.basic_info), themeColor) {
                // Profile Photo
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Box {
                        Surface(
                            modifier = Modifier.size(100.dp),
                            shape = CircleShape,
                            color = Color.LightGray.copy(alpha = 0.3f),
                            onClick = { launchCamera("profile") }
                        ) {
                            if (profilePhotoUri != null) {
                                AsyncImage(
                                    model = profilePhotoUri,
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize().clip(CircleShape),
                                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                                )
                            } else {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(Icons.Default.Person, null, modifier = Modifier.size(60.dp), tint = Color.Gray)
                                }
                            }
                        }
                        Surface(
                            modifier = Modifier.size(32.dp).align(Alignment.BottomEnd),
                            shape = CircleShape,
                            color = themeColor,
                            onClick = { imagePickerLauncher.launch("image/*") }
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.CameraAlt, null, modifier = Modifier.size(18.dp), tint = Color.White)
                            }
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                EnrollmentTextField(
                    label = stringResource(R.string.full_name_required_label),
                    value = name,
                    onValueChange = { if (it.all { char -> char.isLetter() || char.isWhitespace() }) name = it },
                    borderColor = themeColor
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                EnrollmentTextField(
                    label = stringResource(R.string.mobile_number),
                    value = phone,
                    onValueChange = {},
                    readOnly = true,
                    borderColor = themeColor
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                EnrollmentTextField(
                    label = stringResource(R.string.dob_required_label),
                    value = dob,
                    onValueChange = { dob = it },
                    placeholder = "DD/MM/YYYY",
                    trailingIcon = Icons.Default.CalendarToday,
                    readOnly = true,
                    onTrailingIconClick = {
                        DatePickerDialog(
                            context,
                            { _, year, month, dayOfMonth ->
                                dob = "$dayOfMonth/${month + 1}/$year"
                            },
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                        ).show()
                    },
                    borderColor = themeColor
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                EnrollmentDropdownField(
                    label = stringResource(R.string.gender_required_label),
                    selectedValue = gender,
                    options = listOf(
                        stringResource(R.string.female),
                        stringResource(R.string.male),
                        stringResource(R.string.other_label)
                    ),
                    onValueChange = { gender = it },
                    borderColor = themeColor
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Section 2: Location
            ProfileSetupSection(stringResource(R.string.location), themeColor) {
                EnrollmentTextField(label = stringResource(R.string.state_required_label), value = state, onValueChange = { if (it.all { char -> char.isLetter() || char.isWhitespace() }) state = it }, borderColor = themeColor)
                Spacer(modifier = Modifier.height(12.dp))
                EnrollmentTextField(label = stringResource(R.string.district_required_label), value = district, onValueChange = { if (it.all { char -> char.isLetter() || char.isWhitespace() }) district = it }, borderColor = themeColor)
                Spacer(modifier = Modifier.height(12.dp))
                EnrollmentTextField(label = stringResource(R.string.block_required_label), value = block, onValueChange = { if (it.all { char -> char.isLetter() || char.isWhitespace() }) block = it }, borderColor = themeColor)
                Spacer(modifier = Modifier.height(12.dp))
                EnrollmentTextField(label = locationLabel, value = village, onValueChange = { if (it.all { char -> char.isLetter() || char.isWhitespace() }) village = it }, borderColor = themeColor)
                Spacer(modifier = Modifier.height(12.dp))
                EnrollmentTextField(label = stringResource(R.string.pincode_required_label), value = pincode, onValueChange = { if(it.length <= 6) pincode = it }, keyboardType = KeyboardType.Number, borderColor = themeColor)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Section 3: Identity/KYC
            ProfileSetupSection(stringResource(R.string.identity_kyc), themeColor) {
                EnrollmentTextField(label = stringResource(R.string.aadhaar_number_required_label), value = aadhaarNumber, onValueChange = { if(it.length <= 12) aadhaarNumber = it }, keyboardType = KeyboardType.Number, borderColor = themeColor)
                Spacer(modifier = Modifier.height(16.dp))
                Text(stringResource(R.string.aadhaar_photo_optional), fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    modifier = Modifier.fillMaxWidth().height(120.dp),
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White,
                    border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f)),
                    onClick = { launchCamera("aadhaar") }
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        if (aadhaarPhotoUri != null) {
                            AsyncImage(model = aadhaarPhotoUri, contentDescription = null, modifier = Modifier.fillMaxSize(), contentScale = androidx.compose.ui.layout.ContentScale.Fit)
                        } else {
                            Icon(Icons.Default.AddAPhoto, null, tint = Color.Gray)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = { onComplete(role, name, village) },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = themeColor),
                enabled = name.isNotBlank() && dob.isNotBlank() && state.isNotBlank() && district.isNotBlank() && block.isNotBlank() && village.isNotBlank() && pincode.length == 6 && aadhaarNumber.length == 12
            ) {
                Text(stringResource(R.string.save_continue), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun ProfileSetupSection(title: String, themeColor: Color, content: @Composable ColumnScope.() -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = themeColor)
        Spacer(modifier = Modifier.height(12.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                content()
            }
        }
    }
}@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EarningHistoryScreen(onBack: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val backgroundColor = Color(0xFFF8F9F5)
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
    ) {
        // Green Header Area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                .background(PrimaryGreen)
                .padding(top = 48.dp, bottom = 32.dp, start = 20.dp, end = 20.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                    Text(
                        stringResource(R.string.earning_history),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Total Earnings Summary Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.15f)),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(stringResource(R.string.total_earnings), color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                        Text("₹ 8,450", style = MaterialTheme.typography.headlineLarge, color = Color.White, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(12.dp))
                        Surface(color = Color.White.copy(alpha = 0.2f), shape = RoundedCornerShape(12.dp)) {
                            Text(
                                text = stringResource(R.string.this_month),
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .fillMaxWidth()
        ) {
            Text(
                stringResource(R.string.recent_transactions),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            val mockEarnings = listOf(
                EarningData(stringResource(R.string.goat_enrollment), "TAG-453678 • Ramesh Naik", "+ ₹20", stringResource(R.string.two_hours_ago), painterResource(R.drawable.ic_ewe_custom), PrimaryGreen),
                EarningData(stringResource(R.string.vaccination_fee), stringResource(R.string.vaccine_ppr) + " • 5 Goats", "+ ₹50", stringResource(R.string.today_at, "10:30 AM"), Icons.Default.MedicalServices, InfoBlue),
                EarningData(stringResource(R.string.goat_enrollment), "TAG-223456 • Suresh Behera", "+ ₹20", stringResource(R.string.yesterday), painterResource(R.drawable.ic_ewe_custom), PrimaryGreen),
                EarningData(stringResource(R.string.vaccination_fee), stringResource(R.string.vaccine_ettt) + " • 8 Goats", "+ ₹80", stringResource(R.string.n_days_ago, 2), Icons.Default.MedicalServices, InfoBlue),
                EarningData(stringResource(R.string.mortality_report), "TAG-112233 • Manoj Sahoo", "+ ₹50", stringResource(R.string.n_days_ago, 3), Icons.Default.LocationOn, Color.Red),
                EarningData(stringResource(R.string.goat_enrollment), "TAG-998877 • Alok Dash", "+ ₹20", stringResource(R.string.n_days_ago, 4), painterResource(R.drawable.ic_ewe_custom), PrimaryGreen)
            )
            
            mockEarnings.forEach { data ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Box(modifier = Modifier.padding(12.dp)) {
                        EarningItem(data.title, data.subtitle, data.amount, data.time, data.icon, data.color)
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

data class EarningData(val title: String, val subtitle: String, val amount: String, val time: String, val icon: Any, val color: Color)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CallSupportScreen(userRole: UserRole?, onBack: () -> Unit, onCallEnded: (String, String, String) -> Unit) {
    val languageState = LocalAppLanguage.current
    val context = LocalContext.current
    val themeColor = when(userRole) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }
    
    var callState by remember { mutableStateOf("Idle") } // Idle, Connecting, InCall

    LaunchedEffect(callState) {
        if (callState == "Connecting") {
            kotlinx.coroutines.delay(1000)
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:0000000008"))
            context.startActivity(intent)
            callState = "InCall"
        } else if (callState == "InCall") {
            kotlinx.coroutines.delay(2000)
            onCallEnded("0000-000-008", "00:07:24", "20 May 2024, 10:30 AM")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.call_support), fontWeight = FontWeight.Bold, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = themeColor)
            )
        },
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (callState == "Idle") {
                Spacer(modifier = Modifier.height(40.dp))
                Surface(
                    modifier = Modifier.size(120.dp),
                    shape = CircleShape,
                    color = themeColor.copy(alpha = 0.1f)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Phone, null, tint = themeColor, modifier = Modifier.size(50.dp))
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = stringResource(R.string.we_are_here_to_help),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.chat_quick_solutions),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(40.dp))
                Card(
                    onClick = { callState = "Connecting" },
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Phone, null, tint = themeColor, modifier = Modifier.size(32.dp))
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text("0000-000-008", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = themeColor)
                            Text(stringResource(R.string.tap_to_call), color = themeColor, fontSize = 14.sp)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(themeColor))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(stringResource(R.string.available_247_msg), color = Color.Gray, fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.height(32.dp))
                CallSupportFeatureItem(themeColor, Icons.Default.History, stringResource(R.string.quick_connect_label), stringResource(R.string.quick_connect_msg))
                CallSupportFeatureItem(themeColor, Icons.Default.VerifiedUser, stringResource(R.string.secure_private_label), stringResource(R.string.secure_private_msg))
                CallSupportFeatureItem(themeColor, Icons.Default.SupportAgent, stringResource(R.string.trained_experts_label), stringResource(R.string.trained_experts_msg))
                
                Spacer(modifier = Modifier.height(32.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = themeColor.copy(alpha = 0.05f)),
                    border = BorderStroke(1.dp, themeColor.copy(alpha = 0.2f)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.CalendarToday, null, tint = themeColor, modifier = Modifier.size(32.dp))
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(stringResource(R.string.support_hours), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text("Mon - Sat: 9:00 AM - 6:00 PM", fontSize = 12.sp, color = Color.Gray)
                            Text("Sunday: 10:00 AM - 4:00 PM", fontSize = 12.sp, color = Color.Gray)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            } else {
                Spacer(modifier = Modifier.height(100.dp))
                Surface(
                    modifier = Modifier.size(120.dp),
                    shape = CircleShape,
                    color = themeColor.copy(alpha = 0.1f)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        if (callState == "Connecting") {
                            Icon(Icons.Default.MoreHoriz, null, tint = themeColor, modifier = Modifier.size(50.dp))
                        } else {
                            Icon(Icons.Default.SupportAgent, null, tint = themeColor, modifier = Modifier.size(50.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = if (callState == "Connecting") stringResource(R.string.connecting_label) else stringResource(R.string.in_progress),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (callState == "Connecting") stringResource(R.string.system_connecting_msg) else stringResource(R.string.user_speaks_msg),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun CallSupportFeatureItem(themeColor: Color, icon: ImageVector, title: String, subtitle: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(44.dp),
                shape = CircleShape,
                color = themeColor.copy(alpha = 0.1f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(icon, null, tint = themeColor, modifier = Modifier.size(20.dp))
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)
                Text(subtitle, fontSize = 13.sp, color = Color.Gray)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CallSummaryScreen(number: String, duration: String, time: String, userRole: UserRole?, onBackHome: () -> Unit, onCallAgain: () -> Unit) {
    val context = LocalContext.current
    val themeColor = when(userRole) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }
    
    var rating by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.call_support), fontWeight = FontWeight.Bold, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackHome) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = themeColor)
            )
        },
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Surface(
                modifier = Modifier.size(100.dp),
                shape = CircleShape,
                color = themeColor
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Check, null, tint = Color.White, modifier = Modifier.size(60.dp))
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.thank_you),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.your_call_has_ended),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            
            Spacer(modifier = Modifier.height(40.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = stringResource(R.string.call_details),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CallDetailRow(themeColor, Icons.Default.Phone, stringResource(R.string.number), number)
                    CallDetailRow(themeColor, Icons.Default.History, stringResource(R.string.duration), duration)
                    CallDetailRow(themeColor, Icons.Default.CalendarToday, stringResource(R.string.time), time)
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.experience_feedback),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = stringResource(R.string.feedback_helps_us_improve),
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                repeat(5) { index ->
                    val starIndex = index + 1
                    IconButton(onClick = { rating = starIndex }) {
                        Icon(
                            imageVector = if (starIndex <= rating) Icons.Default.Star else Icons.Default.StarOutline,
                            contentDescription = null,
                            tint = if (starIndex <= rating) Color(0xFFFFB300) else Color.Gray,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = onBackHome,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = themeColor)
            ) {
                Text(stringResource(R.string.back_to_home), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onCallAgain,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color.LightGray)
            ) {
                Text(stringResource(R.string.call_again), color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun CallDetailRow(themeColor: Color, icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, tint = themeColor, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(label, color = Color.Gray, fontSize = 14.sp, modifier = Modifier.weight(1f))
        Text(value, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FaqsScreen(userRole: UserRole?, onBack: () -> Unit, onContactSupport: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val themeColor = when(userRole) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }
    
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }
    val categories = listOf("All", "Insurance", "Claims", "Policy", "Account")

    val faqs = listOf(
        FaqData(stringResource(R.string.faq_q1), stringResource(R.string.faq_a1), "Insurance"),
        FaqData(stringResource(R.string.faq_q2), stringResource(R.string.faq_a2), "Insurance"),
        FaqData(stringResource(R.string.faq_q3), stringResource(R.string.faq_a3), "Policy"),
        FaqData(stringResource(R.string.faq_q4), stringResource(R.string.faq_a4), "Policy"),
        FaqData(stringResource(R.string.faq_q5), stringResource(R.string.faq_a5), "Claims"),
        FaqData(stringResource(R.string.faq_q6), stringResource(R.string.faq_a6), "Claims"),
        FaqData(stringResource(R.string.faq_q7), stringResource(R.string.faq_a7), "Claims"),
        FaqData(stringResource(R.string.faq_q8), stringResource(R.string.faq_a8), "Policy")
    )

    val filteredFaqs = faqs.filter { 
        (selectedCategory == "All" || it.category == selectedCategory) &&
        (it.question.contains(searchQuery, ignoreCase = true) || it.answer.contains(searchQuery, ignoreCase = true))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.faqs), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = themeColor, titleContentColor = Color.White, navigationIconContentColor = Color.White)
            )
        },
        containerColor = Color(0xFFF8F9F5)
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                placeholder = { Text(stringResource(R.string.search_questions_placeholder)) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray) },
                trailingIcon = { Icon(Icons.Default.FilterList, contentDescription = null, tint = themeColor) },
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White, unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f))
            )

            // Categories
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                items(categories) { category ->
                    val isSelected = selectedCategory == category
                    Surface(
                        onClick = { selectedCategory = category },
                        color = if (isSelected) themeColor else Color.White,
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(1.dp, if (isSelected) themeColor else Color.LightGray.copy(alpha = 0.5f))
                    ) {
                        Text(
                            text = category,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            color = if (isSelected) Color.White else Color.Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // FAQ List
            if (filteredFaqs.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.AutoMirrored.Filled.Help, null, modifier = Modifier.size(64.dp), tint = Color.LightGray)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(stringResource(R.string.no_faqs_found), fontWeight = FontWeight.Bold, color = Color.Gray)
                    TextButton(onClick = { searchQuery = ""; selectedCategory = "All" }) {
                        Text(stringResource(R.string.clear_search_label), color = themeColor)
                    }
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    itemsIndexed(filteredFaqs) { index, faq ->
                        FaqItem(index + 1, faq, themeColor)
                    }
                }
            }

            // Still need help?
            Card(
                onClick = onContactSupport,
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier.size(44.dp),
                        shape = CircleShape,
                        color = themeColor.copy(alpha = 0.1f)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.AutoMirrored.Filled.Chat, null, tint = themeColor, modifier = Modifier.size(20.dp))
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(stringResource(R.string.still_need_help_label), fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        Text(stringResource(R.string.chat_support_msg), fontSize = 13.sp, color = Color.Gray)
                    }
                    Icon(Icons.Default.ChevronRight, null, tint = Color.LightGray)
                }
            }
        }
    }
}

data class FaqData(val question: String, val answer: String, val category: String)

@Composable
fun FaqItem(index: Int, faq: FaqData, themeColor: Color) {
    var expanded by remember { mutableStateOf(false) }
    val languageState = LocalAppLanguage.current

    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = { expanded = !expanded },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, if (expanded) themeColor.copy(alpha = 0.5f) else Color.LightGray.copy(alpha = 0.3f)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$index. ${faq.question}",
                    modifier = Modifier.weight(1f),
                    fontWeight = if (expanded) FontWeight.Bold else FontWeight.Medium,
                    fontSize = 15.sp,
                    color = if (expanded) themeColor else Color.Black
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
            
            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = faq.answer,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray,
                    lineHeight = 20.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.2f))
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(stringResource(R.string.was_helpful_label), fontSize = 12.sp, color = Color.Gray)
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        Icon(Icons.Default.ThumbUpOffAlt, null, tint = Color.Gray, modifier = Modifier.size(18.dp).clickable { /* Feedback */ })
                        Icon(Icons.Default.ThumbDownOffAlt, null, tint = Color.Gray, modifier = Modifier.size(18.dp).clickable { /* Feedback */ })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactSupportHomeScreen(
    userRole: UserRole?,
    onStartChat: () -> Unit,
    onViewPrevious: () -> Unit,
    onBack: () -> Unit
) {
    val languageState = LocalAppLanguage.current
    val themeColor = when (userRole) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.contact_support), fontWeight = FontWeight.Bold, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = themeColor)
            )
        },
        containerColor = Color(0xFFF8F9F5)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Surface(
                modifier = Modifier.size(100.dp),
                shape = CircleShape,
                color = themeColor.copy(alpha = 0.1f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.SupportAgent, null, tint = themeColor, modifier = Modifier.size(50.dp))
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(R.string.we_are_here_to_help),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.chat_quick_solutions),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))
            SupportFeatureItem(themeColor, Icons.Default.History, stringResource(R.string.quick_response), stringResource(R.string.quick_response_msg))
            SupportFeatureItem(themeColor, Icons.Default.Person, stringResource(R.string.expert_support), stringResource(R.string.expert_support_msg))
            SupportFeatureItem(themeColor, Icons.Default.VerifiedUser, stringResource(R.string.safe_secure), stringResource(R.string.safe_secure_msg))

            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(R.string.choose_support_way),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(16.dp))
            HelpItemCard(
                title = stringResource(R.string.start_chat_label),
                subtitle = stringResource(R.string.chat_support_msg),
                icon = Icons.AutoMirrored.Filled.Chat,
                themeColor = themeColor,
                onClick = onStartChat
            )
            Spacer(modifier = Modifier.height(12.dp))
            HelpItemCard(
                title = stringResource(R.string.view_previous_conversations),
                subtitle = stringResource(R.string.view_previous_conversations_subtitle),
                icon = Icons.Default.History,
                themeColor = themeColor,
                onClick = onViewPrevious
            )

            Spacer(modifier = Modifier.height(32.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = themeColor.copy(alpha = 0.05f)),
                border = BorderStroke(1.dp, themeColor.copy(alpha = 0.2f)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CalendarToday, null, tint = themeColor, modifier = Modifier.size(32.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(stringResource(R.string.support_hours), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text("Mon - Sat: 9:00 AM - 6:00 PM", fontSize = 12.sp, color = Color.Gray)
                        Text("Sunday: 10:00 AM - 4:00 PM", fontSize = 12.sp, color = Color.Gray)
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun SupportFeatureItem(themeColor: Color, icon: ImageVector, title: String, subtitle: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(40.dp),
            shape = CircleShape,
            color = themeColor.copy(alpha = 0.1f)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(icon, null, tint = themeColor, modifier = Modifier.size(20.dp))
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 15.sp)
            Text(subtitle, fontSize = 13.sp, color = Color.Gray)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreChatFormScreen(
    userRole: UserRole?,
    onStartChat: () -> Unit,
    onBack: () -> Unit
) {
    val languageState = LocalAppLanguage.current
    val themeColor = when (userRole) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }

    var subject by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var attachmentUri by remember { mutableStateOf<Uri?>(null) }
    
    val attachmentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> attachmentUri = uri }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.contact_support), fontWeight = FontWeight.Bold, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = themeColor)
            )
        },
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Surface(
                modifier = Modifier.size(80.dp),
                shape = CircleShape,
                color = themeColor.copy(alpha = 0.1f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.AutoMirrored.Filled.Chat, null, tint = themeColor, modifier = Modifier.size(40.dp))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.start_conversation),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.start_conversation_subtitle),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))
            EnrollmentDropdownField(
                label = stringResource(R.string.subject),
                selectedValue = subject.ifEmpty { stringResource(R.string.select_topic) },
                options = listOf(
                    stringResource(R.string.enrollment_issue),
                    stringResource(R.string.claim_process),
                    stringResource(R.string.payment_issue),
                    stringResource(R.string.policy_status),
                    stringResource(R.string.other_label)
                ),
                onValueChange = { subject = it },
                borderColor = Color.LightGray.copy(alpha = 0.5f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            EnrollmentDropdownField(
                label = stringResource(R.string.category),
                selectedValue = category.ifEmpty { stringResource(R.string.select_category) },
                options = listOf(
                    stringResource(R.string.technical_support),
                    stringResource(R.string.billing),
                    stringResource(R.string.information),
                    stringResource(R.string.feedback)
                ),
                onValueChange = { category = it },
                borderColor = Color.LightGray.copy(alpha = 0.5f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(stringResource(R.string.description), fontWeight = FontWeight.Bold, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier.fillMaxWidth().height(120.dp),
                    placeholder = { Text(stringResource(R.string.tell_us_more), color = Color.Gray) },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f))
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(stringResource(R.string.attach_screenshot), fontWeight = FontWeight.Bold, fontSize = 14.sp, modifier = Modifier.align(Alignment.Start))
            Spacer(modifier = Modifier.height(8.dp))
            AttachmentBox(attachmentUri) { attachmentLauncher.launch("image/*") }

            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = onStartChat,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = themeColor),
                enabled = subject.isNotBlank() && category.isNotBlank() && description.isNotBlank()
            ) {
                Text(stringResource(R.string.start_chat), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatConversationScreen(
    userRole: UserRole?,
    onBack: () -> Unit
) {
    val languageState = LocalAppLanguage.current
    val themeColor = when (userRole) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }

    var messageText by remember { mutableStateOf("") }
    val messages = remember {
        mutableStateListOf(
            ChatMessage("Hi there! 👋\nWelcome to Support. How can we help you today?", false, "10:30 AM"),
            ChatMessage("I am facing an issue while registering my goat.", true, "10:31 AM"),
            ChatMessage("I'm sorry to hear that. Could you please share more details about the issue?", false, "10:32 AM"),
            ChatMessage("It shows \"Something went wrong\" after entering details.", true, "10:32 AM"),
            ChatMessage("Thanks for the information. Our team is checking this for you. Please stay connected.", false, "10:34 AM"),
            ChatMessage("We've identified the issue and it has been resolved. Please try again and let us know.", false, "10:39 AM")
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(modifier = Modifier.size(32.dp), shape = CircleShape, color = Color.LightGray.copy(alpha = 0.3f)) {
                            Box(contentAlignment = Alignment.Center) { Icon(Icons.Default.SupportAgent, null, tint = themeColor, modifier = Modifier.size(18.dp)) }
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(stringResource(R.string.support_team), fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Color(0xFF4CAF50)))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(stringResource(R.string.online), fontSize = 11.sp, color = Color.White.copy(alpha = 0.8f))
                            }
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = {}) { Icon(Icons.Default.MoreVert, null, tint = Color.White) }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = themeColor)
            )
        },
        bottomBar = {
            Surface(tonalElevation = 8.dp, color = Color.White, shadowElevation = 16.dp) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(12.dp).navigationBarsPadding(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {}) { Icon(painterResource(R.drawable.ic_ewe_custom), null, tint = Color.Gray, modifier = Modifier.size(24.dp)) /* Using goat icon as attachment placeholder */ }
                    OutlinedTextField(
                        value = messageText,
                        onValueChange = { messageText = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text(stringResource(R.string.type_message), color = Color.Gray) },
                        shape = RoundedCornerShape(24.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            focusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedContainerColor = Color(0xFFF5F5F5)
                        ),
                        maxLines = 4
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        onClick = {
                            if (messageText.isNotBlank()) {
                                messages.add(ChatMessage(messageText, true, "Just now"))
                                messageText = ""
                            }
                        },
                        shape = CircleShape,
                        color = themeColor,
                        modifier = Modifier.size(48.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.AutoMirrored.Filled.Chat, null, tint = Color.White, modifier = Modifier.size(20.dp))
                        }
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize().background(Color(0xFFF8F9FB))
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Surface(color = Color.LightGray.copy(alpha = 0.2f), shape = RoundedCornerShape(8.dp)) {
                            Text(stringResource(R.string.date_today), modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp), fontSize = 12.sp, color = Color.Gray)
                        }
                    }
                }
                items(messages) { message ->
                    ChatMessageItem(message, themeColor)
                }
            }
        }
    }
}

data class ChatMessage(val text: String, val isUser: Boolean, val time: String)

@Composable
fun ChatMessageItem(message: ChatMessage, themeColor: Color) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (message.isUser) Alignment.End else Alignment.Start
    ) {
        Surface(
            color = if (message.isUser) themeColor else Color.White,
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = if (message.isUser) 16.dp else 0.dp,
                bottomEnd = if (message.isUser) 0.dp else 16.dp
            ),
            border = if (message.isUser) null else BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.2f)),
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(12.dp),
                color = if (message.isUser) Color.White else Color.Black,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        }
        Row(
            modifier = Modifier.padding(top = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = message.time, fontSize = 10.sp, color = Color.Gray)
            if (message.isUser) {
                Spacer(modifier = Modifier.width(4.dp))
                Icon(Icons.Default.CheckCircle, null, tint = SuccessGreen, modifier = Modifier.size(12.dp))
            }
        }
    }
}

@Composable
fun IssueSuccessScreen(
    navController: NavHostController,
    ticketId: String,
    type: String,
    date: String,
    status: String,
    userRole: UserRole?,
    onBackHome: () -> Unit,
    onViewReports: () -> Unit
) {
    val languageState = LocalAppLanguage.current
    val themeColor = when (userRole) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }

    Scaffold(
        bottomBar = {
            when (userRole) {
                UserRole.FARMER -> FarmerBottomBar(navController)
                UserRole.COORDINATOR -> CoordinatorBottomBar(navController)
                else -> DidiBottomBar(navController)
            }
        },
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(64.dp))
            
            // Success Icon
            Surface(
                modifier = Modifier.size(100.dp),
                shape = CircleShape,
                color = SuccessGreen
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Check, null, tint = Color.White, modifier = Modifier.size(60.dp))
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
            
            Text(
                text = stringResource(R.string.thank_you),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
            
            Text(
                text = stringResource(R.string.issue_reported_successfully),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Ticket Details Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.4f)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = stringResource(R.string.ticket_details),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TicketDetailRow(stringResource(R.string.ticket_id), ticketId)
                    TicketDetailRow(stringResource(R.string.issue_type), type)
                    TicketDetailRow(stringResource(R.string.reported_date), date)
                    TicketDetailRow(stringResource(R.string.status), status, valueColor = InfoBlue)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = InfoBlue.copy(alpha = 0.08f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Info, null, tint = InfoBlue, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = stringResource(R.string.review_soon_message),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.DarkGray
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onViewReports,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen)
            ) {
                Text(stringResource(R.string.view_my_reports), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            OutlinedButton(
                onClick = onBackHome,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color.LightGray)
            ) {
                Text(stringResource(R.string.back_to_home), color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyReportsScreen(navController: NavHostController, userRole: UserRole?, onBack: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val themeColor = when (userRole) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }
    
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf(
        stringResource(R.string.all_label),
        stringResource(R.string.open_label),
        stringResource(R.string.in_progress),
        stringResource(R.string.resolved_label),
        stringResource(R.string.closed_label)
    )

    val mockReports = listOf(
        ReportItemData("App crashes on dashboard", "ISS-2024-000145", "20 May 2024, 10:30 AM", "In Progress", Color(0xFFFFA000), Icons.Default.BugReport, Color(0xFFD32F2F)),
        ReportItemData("Add dark mode feature", "ISS-2024-000142", "18 May 2024, 04:15 PM", "Open", SuccessGreen, Icons.Default.Lightbulb, Color(0xFF1976D2)),
        ReportItemData("Payment failed but amount deducted", "ISS-2024-000138", "17 May 2024, 11:30 AM", "Resolved", PrimaryBlue, Icons.AutoMirrored.Filled.Help, SuccessGreen),
        ReportItemData("Improve profile page UI", "ISS-2024-000120", "15 May 2024, 09:40 AM", "Closed", Color.Gray, Icons.AutoMirrored.Filled.Assignment, Color.Gray)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.my_reports), fontWeight = FontWeight.Bold, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = themeColor)
            )
        },
        bottomBar = {
            when (userRole) {
                UserRole.FARMER -> FarmerBottomBar(navController)
                UserRole.COORDINATOR -> CoordinatorBottomBar(navController)
                else -> DidiBottomBar(navController)
            }
        },
        containerColor = Color(0xFFF8F9F5)
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            ScrollableTabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color.White,
                edgePadding = 16.dp,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = themeColor
                    )
                },
                divider = {}
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title, color = if (selectedTab == index) themeColor else Color.Gray, fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal) }
                    )
                }
            }

            val filteredReports = when (selectedTab) {
                0 -> mockReports
                1 -> mockReports.filter { it.status == "Open" }
                2 -> mockReports.filter { it.status == "In Progress" }
                3 -> mockReports.filter { it.status == "Resolved" }
                4 -> mockReports.filter { it.status == "Closed" }
                else -> mockReports
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredReports) { report ->
                    ReportItemCard(report)
                }
                
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(stringResource(R.string.cant_find_issue), fontWeight = FontWeight.Bold)
                            Text(stringResource(R.string.contact_support_direct), fontSize = 12.sp, color = Color.Gray, textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(12.dp))
                            TextButton(onClick = { navController.navigate("contact_support_home") }) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.AutoMirrored.Filled.Chat, null, modifier = Modifier.size(18.dp), tint = SuccessGreen)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(stringResource(R.string.contact_support), color = SuccessGreen, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

data class ReportItemData(val title: String, val id: String, val date: String, val status: String, val statusColor: Color, val icon: ImageVector, val iconColor: Color)

@Composable
fun ReportItemCard(report: ReportItemData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.2f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                color = report.iconColor.copy(alpha = 0.1f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(report.icon, null, tint = report.iconColor, modifier = Modifier.size(20.dp))
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(report.title, fontWeight = FontWeight.Bold, fontSize = 14.sp, modifier = Modifier.weight(1f), maxLines = 1)
                    Icon(Icons.Default.ChevronRight, null, tint = Color.LightGray, modifier = Modifier.size(16.dp))
                }
                Text(report.id, fontSize = 12.sp, color = Color.Gray)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(report.date, fontSize = 11.sp, color = Color.LightGray)
                    Surface(
                        color = report.statusColor.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            report.status,
                            color = report.statusColor,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TicketDetailRow(label: String, value: String, valueColor: Color = Color.Black) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.Gray, fontSize = 14.sp)
        Text(value, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = valueColor)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyPolicyScreen(userRole: UserRole?, onBack: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val themeColor = when (userRole) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.privacy_policy),
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = themeColor,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Illustration Header
            Box(contentAlignment = Alignment.Center) {
                Surface(
                    modifier = Modifier.size(120.dp),
                    shape = CircleShape,
                    color = themeColor.copy(alpha = 0.1f)
                ) {}
                
                Surface(
                    modifier = Modifier.size(70.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = themeColor,
                    shadowElevation = 4.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = stringResource(R.string.privacy_matters),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.privacy_intro),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 40.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Policy Sections Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(24.dp),
                border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = stringResource(R.string.in_this_policy),
                        color = themeColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    val sections = listOf(
                        PolicySection(
                            title = stringResource(R.string.policy_s1_title),
                            content = stringResource(R.string.policy_s1_desc),
                            icon = Icons.Default.Description
                        ),
                        PolicySection(
                            title = stringResource(R.string.policy_s2_title),
                            content = stringResource(R.string.policy_s2_desc),
                            icon = Icons.Default.Security
                        ),
                        PolicySection(
                            title = stringResource(R.string.policy_s3_title),
                            content = stringResource(R.string.policy_s3_desc),
                            icon = Icons.Default.Share
                        ),
                        PolicySection(
                            title = stringResource(R.string.policy_s4_title),
                            content = stringResource(R.string.policy_s4_desc),
                            icon = Icons.Default.VerifiedUser
                        ),
                        PolicySection(
                            title = stringResource(R.string.policy_s5_title),
                            content = stringResource(R.string.policy_s5_desc),
                            icon = Icons.Default.Person
                        ),
                        PolicySection(
                            title = stringResource(R.string.policy_s6_title),
                            content = stringResource(R.string.policy_s6_desc),
                            icon = Icons.Default.Cookie
                        ),
                        PolicySection(
                            title = stringResource(R.string.policy_s7_title),
                            content = stringResource(R.string.policy_s7_desc),
                            icon = Icons.Default.ChildCare
                        ),
                        PolicySection(
                            title = stringResource(R.string.policy_s8_title),
                            content = stringResource(R.string.policy_s8_desc),
                            icon = Icons.Default.Edit
                        )
                    )

                    sections.forEachIndexed { index, section ->
                        PolicySectionItem(
                            icon = section.icon,
                            title = section.title,
                            content = section.content,
                            themeColor = themeColor
                        )
                        if (index < sections.size - 1) {
                            PolicySectionDivider()
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Footer Agreement Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 24.dp),
                colors = CardDefaults.cardColors(containerColor = themeColor.copy(alpha = 0.05f)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = null,
                        tint = themeColor,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = stringResource(R.string.privacy_agreement_tip),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.DarkGray,
                        lineHeight = 18.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

data class PolicySection(val title: String, val content: String, val icon: ImageVector)

@Composable
fun PolicySectionItem(icon: ImageVector, title: String, content: String, themeColor: Color) {
    var expanded by remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .padding(vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(36.dp),
                shape = RoundedCornerShape(8.dp),
                color = themeColor.copy(alpha = 0.1f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = themeColor,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Color.Black
            )
            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.ChevronRight,
                contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier.size(20.dp)
            )
        }
        
        if (expanded) {
            Text(
                text = content,
                style = MaterialTheme.typography.bodySmall,
                color = Color.DarkGray,
                modifier = Modifier.padding(start = 52.dp, top = 8.dp),
                lineHeight = 18.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsOfServiceScreen(userRole: UserRole?, onBack: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val themeColor = when (userRole) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.terms_of_service),
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = themeColor,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Illustration Header
            Box(contentAlignment = Alignment.Center) {
                Surface(
                    modifier = Modifier.size(120.dp),
                    shape = CircleShape,
                    color = themeColor.copy(alpha = 0.1f)
                ) {}
                
                Surface(
                    modifier = Modifier.size(70.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = themeColor,
                    shadowElevation = 4.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = stringResource(R.string.terms_trust),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.terms_intro),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 40.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Terms Sections Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(24.dp),
                border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = stringResource(R.string.in_this_document),
                        color = themeColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    val sections = listOf(
                        PolicySection(
                            title = stringResource(R.string.terms_s1_title),
                            content = stringResource(R.string.terms_s1_desc),
                            icon = Icons.AutoMirrored.Filled.Assignment
                        ),
                        PolicySection(
                            title = stringResource(R.string.terms_s2_title),
                            content = stringResource(R.string.terms_s2_desc),
                            icon = Icons.Default.VerifiedUser
                        ),
                        PolicySection(
                            title = stringResource(R.string.terms_s3_title),
                            content = stringResource(R.string.terms_s3_desc),
                            icon = Icons.Default.Person
                        ),
                        PolicySection(
                            title = stringResource(R.string.terms_s4_title),
                            content = stringResource(R.string.terms_s4_desc),
                            icon = Icons.Default.Payments
                        ),
                        PolicySection(
                            title = stringResource(R.string.terms_s5_title),
                            content = stringResource(R.string.terms_s5_desc),
                            icon = Icons.Default.Security
                        ),
                        PolicySection(
                            title = stringResource(R.string.terms_s6_title),
                            content = stringResource(R.string.terms_s6_desc),
                            icon = Icons.Default.Lock
                        ),
                        PolicySection(
                            title = stringResource(R.string.terms_s7_title),
                            content = stringResource(R.string.terms_s7_desc),
                            icon = Icons.Default.Gavel
                        ),
                        PolicySection(
                            title = stringResource(R.string.terms_s8_title),
                            content = stringResource(R.string.terms_s8_desc),
                            icon = Icons.Default.Edit
                        ),
                        PolicySection(
                            title = stringResource(R.string.terms_s9_title),
                            content = stringResource(R.string.terms_s9_desc),
                            icon = Icons.Default.SupportAgent
                        )
                    )

                    sections.forEachIndexed { index, section ->
                        PolicySectionItem(
                            icon = section.icon,
                            title = section.title,
                            content = section.content,
                            themeColor = themeColor
                        )
                        if (index < sections.size - 1) {
                            PolicySectionDivider()
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Footer Agreement Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 24.dp),
                colors = CardDefaults.cardColors(containerColor = themeColor.copy(alpha = 0.05f)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = null,
                        tint = themeColor,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = stringResource(R.string.terms_binding_tip),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.DarkGray,
                        lineHeight = 18.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun PolicySectionDivider() {
    HorizontalDivider(
        color = Color.LightGray.copy(alpha = 0.2f),
        modifier = Modifier.padding(start = 52.dp)
    )
}



