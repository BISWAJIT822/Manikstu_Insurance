package com.manikshu.goatinsurance


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MyLocation
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
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Groups
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextDecoration
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

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
    val languageState = LocalAppLanguage.current
    
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
            title = { Text(languageState.value.getT("Exit App", "ऐप से बाहर निकलें", "ଆପ୍ ରୁ ବାହାରନ୍ତୁ"), fontWeight = FontWeight.Bold, color = Color.Black) },
            text = { Text(languageState.value.getT("Are you sure you want to exit?", "क्या आप वाकई बाहर निकलना चाहते हैं?", "ଆପଣ ନିଶ୍ଚିତ ଭାବରେ ବାହାରିବାକୁ ଚାହୁଁଛନ୍ତି କି?"), color = Color.Black) },
            confirmButton = {
                TextButton(onClick = { (context as? Activity)?.finish() }) {
                    Text(languageState.value.getT("Yes", "हाँ", "ହଁ"), fontWeight = FontWeight.Bold, color = Color.Black)
                }
            },
            dismissButton = {
                TextButton(onClick = { showExitDialog = false }) {
                    Text(languageState.value.getT("No", "नहीं", "ନା"), fontWeight = FontWeight.Bold, color = Color.Black)
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
                onVerifyOtp = { role, name, phone, otp ->
                    navController.navigate("setup_profile/$role/$name/$phone/$otp")
                },
                onNavigateToLogin = { navController.popBackStack() }
            )
        }
        composable("setup_profile/{role}/{name}/{phone}/{otp}") { backStackEntry ->
            val roleStr = backStackEntry.arguments?.getString("role") ?: UserRole.FARMER.name
            val role = try { UserRole.valueOf(roleStr) } catch(e: Exception) { UserRole.FARMER }
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val phone = backStackEntry.arguments?.getString("phone") ?: ""
            val otp = backStackEntry.arguments?.getString("otp") ?: ""
            SetupProfileScreen(
                role = role,
                initialName = name,
                initialPhone = phone,
                otp = otp,
                onComplete = { authedRole ->
                    if (authedRole != null) {
                        // Auto-approved and logged in: go straight to the dashboard.
                        scope.launch { sessionManager.saveSession(authedRole) }
                        val dest = when (authedRole) {
                            UserRole.SURAKSHA_DIDI -> "didi_dashboard"
                            UserRole.FARMER -> "farmer_dashboard"
                            UserRole.COORDINATOR -> "coordinator_dashboard"
                        }
                        navController.navigate(dest) { popUpTo("signup") { inclusive = true } }
                    } else {
                        navController.navigate("pending_approval") { popUpTo("signup") { inclusive = true } }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable("pending_approval") {
            PendingApprovalScreen(
                onBackToLogin = {
                    navController.navigate("login") { popUpTo(0) { inclusive = true } }
                }
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
        // Didi review queue for farmer-reported deaths + detail (complete -> claim).
        composable("mortality_queue") { MortalityQueueScreen(navController, onBack = { navController.popBackStack() }) }
        composable("mortality_detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
            MortalityDetailScreen(navController, id, onBack = { navController.popBackStack() })
        }
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
            val context = LocalContext.current
            FaqsScreen(
                userRole = userRole,
                onBack = { navController.popBackStack() },
                onChatSupport = { context.chatOnWhatsApp() }
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
fun SignUpScreen(onVerifyOtp: (UserRole, String, String, String) -> Unit, onNavigateToLogin: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var step by remember { mutableIntStateOf(1) }
    var selectedRole by remember { mutableStateOf<UserRole?>(null) }

    val context = LocalContext.current
    val languageState = LocalAppLanguage.current
    val lang = languageState.value

    val authViewModel: AuthViewModel = hiltViewModel()
    val authState by authViewModel.authState.collectAsState()
    val loginMethod by authViewModel.loginMethod.collectAsState()
    // Password mode: no OTP round-trip at signup - the button becomes "Sign Up"
    // and leads straight to the profile form (which collects the password).
    val isPasswordMode = loginMethod == "password"
    val isAuthLoading = authState is AuthState.Loading
    LaunchedEffect(authState) {
        when (val s = authState) {
            is AuthState.OtpSent -> { step = 2; authViewModel.reset() }
            is AuthState.Error -> {
                Toast.makeText(context, s.message, Toast.LENGTH_LONG).show()
                authViewModel.reset()
            }
            else -> {}
        }
    }

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
            text = { Text(stringResource(R.string.permissions_required_message)) },
            confirmButton = {
                TextButton(onClick = {
                    showPermissionDialog = false
                    permissionLauncher.launch(permissionsToRequest)
                }) { Text(stringResource(R.string.allow)) }
            },
            dismissButton = {
                TextButton(onClick = { showPermissionDialog = false }) { Text(stringResource(R.string.deny)) }
            },
            shape = RoundedCornerShape(16.dp)
        )
    }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFF8F4E7))) {
        Image(
            painter = painterResource(R.drawable.ic_bg_login),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            Modifier.fillMaxSize().background(
                Brush.verticalGradient(
                    0.0f to Color(0xFFFBF6E9).copy(alpha = 0.05f),
                    0.42f to Color(0xFFFBF6E9).copy(alpha = 0.55f),
                    0.70f to Color(0xFFF4EFDC).copy(alpha = 0.42f),
                    1.0f to Color(0xFFF0F3DB).copy(alpha = 0.20f)
                )
            )
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .padding(horizontal = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(110.dp))
            AjahFiLogo()
            if (step == 1) {
                Spacer(Modifier.height(18.dp))
                Text(
                    lang.getT("Create Account", "खाता बनाएं", "ଖାତା ତିଆରି କରନ୍ତୁ"),
                    fontSize = 22.sp, fontWeight = FontWeight.Bold, color = LoginWelcomeGreen
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    lang.getT("Sign up to get started", "आरंभ करने के लिए साइन अप करें", "ଆରମ୍ଭ କରିବାକୁ ସାଇନ୍ ଅପ୍ କରନ୍ତୁ"),
                    fontSize = 14.sp, color = LoginNavy, fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.height(16.dp))
                LoginField(
                    value = name,
                    onValueChange = { if (it.all { c -> c.isLetter() || c.isWhitespace() }) name = it },
                    placeholder = lang.getT("Enter name", "नाम दर्ज करें", "ନାମ ଲେଖନ୍ତୁ"),
                    leading = Icons.Default.Person
                )
                Spacer(Modifier.height(14.dp))
                LoginField(
                    value = phone,
                    onValueChange = { if (it.length <= 10 && it.all(Char::isDigit)) phone = it },
                    placeholder = lang.getT("Enter mobile number", "मोबाइल नंबर दर्ज करें", "ମୋବାଇଲ୍ ନମ୍ବର ଲେଖନ୍ତୁ"),
                    leading = Icons.Default.Phone,
                    keyboardType = KeyboardType.Phone
                )
                Spacer(Modifier.height(18.dp))
                Button(
                    onClick = {
                        when {
                            name.isBlank() -> Toast.makeText(context, lang.getT("Enter your name", "अपना नाम दर्ज करें", "ଆପଣଙ୍କ ନାମ ଲେଖନ୍ତୁ"), Toast.LENGTH_SHORT).show()
                            phone.length != 10 -> Toast.makeText(context, lang.getT("Enter a valid 10-digit mobile number", "मान्य 10 अंकों का मोबाइल नंबर दर्ज करें", "ଏକ ବୈଧ ୧୦ ଅଙ୍କ ମୋବାଇଲ୍ ନମ୍ବର ଲେଖନ୍ତୁ"), Toast.LENGTH_SHORT).show()
                            selectedRole == null -> Toast.makeText(context, lang.getT("Choose a role", "एक भूमिका चुनें", "ଏକ ଭୂମିକା ବାଛନ୍ତୁ"), Toast.LENGTH_SHORT).show()
                            isPasswordMode -> onVerifyOtp(selectedRole!!, name, phone, "NA")
                            else -> authViewModel.sendSignupOtp(name, phone, selectedRole!!)
                        }
                    },
                    enabled = !isAuthLoading,
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LoginButtonGreen, contentColor = Color.White,
                        disabledContainerColor = LoginButtonGreen, disabledContentColor = Color.White
                    )
                ) {
                    Text(
                        if (isPasswordMode) lang.getT("Sign Up", "साइन अप करें", "ସାଇନ୍ ଅପ୍")
                        else lang.getT("Send OTP", "ओटीपी भेजें", "ଓଟିପି ପଠାନ୍ତୁ"),
                        fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White
                    )
                }
                Spacer(Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(lang.getT("Already have an account? ", "पहले से खाता है? ", "ପୂର୍ବରୁ ଖାତା ଅଛି? "), color = LoginNavy, fontSize = 14.sp)
                    Text(
                        lang.getT("Sign In", "साइन इन करें", "ସାଇନ୍ ଇନ୍"),
                        color = LoginWelcomeGreen, fontWeight = FontWeight.Bold, fontSize = 14.sp,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable { onNavigateToLogin() }
                    )
                }
                Spacer(Modifier.height(14.dp))
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    HorizontalDivider(modifier = Modifier.weight(1f), thickness = 2.dp, color = LoginGold)
                    Text(
                        lang.getT("Choose a Role", "एक भूमिका चुनें", "ଏକ ଭୂମିକା ବାଛନ୍ତୁ"),
                        modifier = Modifier.padding(horizontal = 12.dp),
                        color = Color.White, fontWeight = FontWeight.Bold, fontSize = 17.sp
                    )
                    HorizontalDivider(modifier = Modifier.weight(1f), thickness = 2.dp, color = LoginGold)
                }
                Spacer(Modifier.height(12.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    LoginRoleCard(UserRole.SURAKSHA_DIDI, lang.getT("Suraksha", "सुरक्षा दीदी", "ସୁରକ୍ଷା ଦିଦି"), R.drawable.avatar_didi, selectedRole == UserRole.SURAKSHA_DIDI, modifier = Modifier.weight(1f)) { selectedRole = it }
                    LoginRoleCard(UserRole.FARMER, lang.getT("Farmer", "किसान", "କୃଷକ"), R.drawable.avatar_farmer, selectedRole == UserRole.FARMER, modifier = Modifier.weight(1f)) { selectedRole = it }
                    LoginRoleCard(UserRole.COORDINATOR, lang.getT("Coordinator", "समन्वयक", "ସମନ୍ଵୟକାରୀ"), R.drawable.avatar_coordinator, selectedRole == UserRole.COORDINATOR, modifier = Modifier.weight(1f)) { selectedRole = it }
                }
                Spacer(Modifier.weight(1f))            // pin version to the bottom
                Text(
                    lang.getT("Version 2.0.0", "संस्करण 2.0.0", "ସଂସ୍କରଣ ୨.୦.୦"),
                    fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color.White
                )
                Spacer(Modifier.height(14.dp))
            } else {
                Spacer(Modifier.height(28.dp))
                Surface(color = Color.White, shape = RoundedCornerShape(20.dp), shadowElevation = 4.dp, modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(lang.getT("Enter the OTP sent to +91 $phone", "+91 $phone पर भेजा गया ओटीपी दर्ज करें", "+91 $phone କୁ ପଠାଯାଇଥିବା ଓଟିପି ଦିଅନ୍ତୁ"), fontSize = 14.sp, textAlign = TextAlign.Center, color = LoginNavy)
                        Spacer(Modifier.height(18.dp))
                        OtpInput(otp, { otp = it }, onDone = {
                            if (otp.length == 6) {
                                val allGranted = permissionsToRequest.all {
                                    ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
                                }
                                if (allGranted) onVerifyOtp(selectedRole!!, name, phone, otp) else showPermissionDialog = true
                            }
                        })
                        Spacer(Modifier.height(18.dp))
                        Button(
                            onClick = {
                                val allGranted = permissionsToRequest.all {
                                    ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
                                }
                                if (allGranted) onVerifyOtp(selectedRole!!, name, phone, otp) else showPermissionDialog = true
                            },
                            enabled = otp.length == 6,
                            modifier = Modifier.fillMaxWidth().height(52.dp),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = LoginButtonGreen)
                        ) { Text(stringResource(R.string.verify_next), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White) }
                        TextButton(onClick = { step = 1 }) { Text(stringResource(R.string.go_back), color = LoginWelcomeGreen) }
                    }
                }
            }
        }
    }
}

// --- SCREENS ---


private val LoginNavy = Color(0xFF1C3B5E)
private val LoginWelcomeGreen = Color(0xFF2E7D32)
private val LoginButtonGreen = Color(0xFF15672C)
private val LoginGold = Color(0xFFC79A3B)
private val RoleCardCream = Color(0xFFF7F0DD)

@Composable
fun LoginScreen(onLoginSuccess: (UserRole) -> Unit, onNavigateToSignUp: () -> Unit) {
    var phone by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var showForgotDialog by remember { mutableStateOf(false) }
    var step by remember { mutableIntStateOf(1) }
    var selectedRole by remember { mutableStateOf<UserRole?>(null) }

    val context = LocalContext.current
    val languageState = LocalAppLanguage.current
    val lang = languageState.value

    val authViewModel: AuthViewModel = hiltViewModel()
    val authState by authViewModel.authState.collectAsState()
    val loginMethod by authViewModel.loginMethod.collectAsState()
    val isPasswordMode = loginMethod == "password"
    val isAuthLoading = authState is AuthState.Loading
    LaunchedEffect(authState) {
        when (val s = authState) {
            is AuthState.OtpSent -> { step = 2; authViewModel.reset() }
            is AuthState.Authenticated -> { onLoginSuccess(s.role); authViewModel.reset() }
            is AuthState.Error -> {
                Toast.makeText(context, s.message, Toast.LENGTH_LONG).show()
                authViewModel.reset()
            }
            else -> {}
        }
    }

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
            Toast.makeText(context, lang.getT("Required permissions denied.", "आवश्यक अनुमतियां अस्वीकार कर दी गईं।", "ଆବଶ୍ୟକ ଅନୁମତି ପ୍ରତ୍ୟାଖ୍ୟାନ କରାଯାଇଛି |"), Toast.LENGTH_LONG).show()
        }
    }

    if (showPermissionDialog) {
        AlertDialog(
            onDismissRequest = { showPermissionDialog = false },
            title = { Text(lang.getT("Permissions Required", "अनुमतियाँ आवश्यक हैं", "ଅନୁମତି ଆବଶ୍ୟକ"), fontWeight = FontWeight.Bold, color = Color.Black) },
            text = { Text(lang.getT(
                "This app needs Camera and Location access to verify livestock and record service areas.",
                "पशुधन को सत्यापित करने और सेवा क्षेत्रों को रिकॉर्ड करने के लिए इस ऐप को कैमरा और स्थान पहुंच की आवश्यकता है।",
                "ପ୍ରାଣୀସମ୍ପଦ ଯାଞ୍ଚ କରିବା ଏବଂ ସେବା କ୍ଷେତ୍ର ରେକର୍ଡ କରିବାକୁ ଏହି ଆପ୍‌ କ୍ୟାମେରା ଏବଂ ଅବସ୍ଥାନ ଅନୁମତି ଆବଶ୍ୟକ କରେ |"
            ), color = Color.Black) },
            confirmButton = {
                TextButton(onClick = {
                    showPermissionDialog = false
                    permissionLauncher.launch(permissionsToRequest)
                }) { Text(lang.getT("Allow", "अनुमति दें", "ଅନୁମତି ଦିଅନ୍ତୁ")) }
            },
            dismissButton = {
                TextButton(onClick = { showPermissionDialog = false }) { Text(lang.getT("Deny", "अस्वीकार करें", "ପ୍ରତ୍ୟାଖ୍ୟାନ କରନ୍ତୁ")) }
            },
            shape = RoundedCornerShape(16.dp)
        )
    }

    if (showForgotDialog) ForgotPasswordDialog(lang) { showForgotDialog = false }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFF8F4E7))) {
        Image(
            painter = painterResource(R.drawable.ic_bg_login),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        // Soft cream scrim: keeps text legible over the bright lower hills and
        // gives the photo the faded, pastel look of the design reference.
        Box(
            Modifier.fillMaxSize().background(
                Brush.verticalGradient(
                    0.0f to Color(0xFFFBF6E9).copy(alpha = 0.05f),
                    0.42f to Color(0xFFFBF6E9).copy(alpha = 0.55f),
                    0.70f to Color(0xFFF4EFDC).copy(alpha = 0.42f),
                    1.0f to Color(0xFFF0F3DB).copy(alpha = 0.20f)
                )
            )
        )
        // Fixed layout mapped from the 709x1536 spec (scale ~0.58 dp/px). No scroll.
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .padding(horizontal = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(110.dp))            // top padding 190px
            AjahFiLogo()                               // logo 370x165px
            if (step == 1) {
                Spacer(Modifier.height(15.dp))         // gap logo -> Welcome
                Text(
                    lang.getT("Welcome Back!", "वापसी पर स्वागत है!", "ପୁଣି ସ୍ଵାଗତ!"),
                    fontSize = 22.sp, fontWeight = FontWeight.Bold, color = LoginWelcomeGreen  // title
                )
                Spacer(Modifier.height(5.dp))          // gap from title
                Text(
                    lang.getT("Sign in to continue", "जारी रखने के लिए साइन इन करें", "ଆଗକୁ ବଢିବା ପାଇଁ ସାଇନ୍ ଇନ୍ କରନ୍ତୁ"),
                    fontSize = 14.sp, color = LoginNavy, fontWeight = FontWeight.Medium       // subtitle
                )
                Spacer(Modifier.height(14.dp))         // gap subtitle -> fields
                LoginField(
                    value = phone,
                    onValueChange = { if (it.length <= 10 && it.all(Char::isDigit)) phone = it },
                    placeholder = lang.getT("Enter mobile number", "मोबाइल नंबर दर्ज करें", "ମୋବାଇଲ୍ ନମ୍ବର ଲେଖନ୍ତୁ"),
                    leading = Icons.Default.Phone,
                    keyboardType = KeyboardType.Phone
                )
                if (isPasswordMode) {
                    Spacer(Modifier.height(12.dp))     // gap between fields
                    LoginField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = lang.getT("Enter password", "पासवर्ड दर्ज करें", "ପାସୱାର୍ଡ ଲେଖନ୍ତୁ"),
                        leading = Icons.Default.Lock,
                        keyboardType = KeyboardType.Password,
                        isPassword = true,
                        passwordVisible = passwordVisible,
                        onTogglePassword = { passwordVisible = !passwordVisible }
                    )
                    Spacer(Modifier.height(8.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        Text(
                            lang.getT("Forgot Password?", "पासवर्ड भूल गए?", "ପାସୱାର୍ଡ ଭୁଲିଗଲେ?"),
                            color = LoginWelcomeGreen, fontWeight = FontWeight.SemiBold, fontSize = 11.sp,  // forgot password
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.clickable { showForgotDialog = true }
                        )
                    }
                    Spacer(Modifier.height(6.dp))      // gap forgot -> Sign In
                } else {
                    Spacer(Modifier.height(22.dp))
                }
                Button(
                    onClick = {
                        when {
                            phone.length != 10 -> Toast.makeText(context, lang.getT("Enter a valid 10-digit mobile number", "मान्य 10 अंकों का मोबाइल नंबर दर्ज करें", "ଏକ ବୈଧ ୧୦ ଅଙ୍କ ମୋବାଇଲ୍ ନମ୍ବର ଲେଖନ୍ତୁ"), Toast.LENGTH_SHORT).show()
                            isPasswordMode && password.isBlank() -> Toast.makeText(context, lang.getT("Enter your password", "अपना पासवर्ड दर्ज करें", "ଆପଣଙ୍କ ପାସୱାର୍ଡ ଲେଖନ୍ତୁ"), Toast.LENGTH_SHORT).show()
                            selectedRole == null -> Toast.makeText(context, lang.getT("Choose a role", "एक भूमिका चुनें", "ଏକ ଭୂମିକା ବାଛନ୍ତୁ"), Toast.LENGTH_SHORT).show()
                            isPasswordMode -> authViewModel.passwordLogin(phone, password, selectedRole!!)
                            else -> authViewModel.sendOtp(phone, selectedRole!!)
                        }
                    },
                    enabled = !isAuthLoading,
                    modifier = Modifier.fillMaxWidth().height(48.dp),   // button 480x82px
                    shape = RoundedCornerShape(12.dp),                  // radius 18px
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LoginButtonGreen, contentColor = Color.White,
                        disabledContainerColor = LoginButtonGreen, disabledContentColor = Color.White
                    )
                ) {
                    Text(
                        if (isPasswordMode) lang.getT("Sign In", "साइन इन करें", "ସାଇନ୍ ଇନ୍")
                        else lang.getT("Send OTP", "ओटीपी भेजें", "ଓଟିପି ପଠାନ୍ତୁ"),
                        fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White
                    )
                }
                Spacer(Modifier.height(10.dp))         // gap Sign In -> signup
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(lang.getT("Don't have an account? ", "खाता नहीं है? ", "ଖାତା ନାହିଁ? "), color = LoginNavy, fontSize = 14.sp)
                    Text(
                        lang.getT("Sign Up", "साइन अप करें", "ସାଇନ୍ ଅପ୍"),
                        color = LoginWelcomeGreen, fontWeight = FontWeight.Bold, fontSize = 14.sp,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable { onNavigateToSignUp() }
                    )
                }
                Spacer(Modifier.height(13.dp))
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    HorizontalDivider(modifier = Modifier.weight(1f), thickness = 2.dp, color = LoginGold)
                    Text(
                        lang.getT("Choose a Role", "एक भूमिका चुनें", "ଏକ ଭୂମିକା ବାଛନ୍ତୁ"),
                        modifier = Modifier.padding(horizontal = 12.dp),
                        color = Color.White, fontWeight = FontWeight.Bold, fontSize = 17.sp
                    )
                    HorizontalDivider(modifier = Modifier.weight(1f), thickness = 2.dp, color = LoginGold)
                }
                Spacer(Modifier.height(12.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {   // gap 20px
                    LoginRoleCard(UserRole.SURAKSHA_DIDI, lang.getT("Suraksha", "सुरक्षा दीदी", "ସୁରକ୍ଷା ଦିଦି"), R.drawable.avatar_didi, selectedRole == UserRole.SURAKSHA_DIDI, modifier = Modifier.weight(1f)) { selectedRole = it }
                    LoginRoleCard(UserRole.FARMER, lang.getT("Farmer", "किसान", "କୃଷକ"), R.drawable.avatar_farmer, selectedRole == UserRole.FARMER, modifier = Modifier.weight(1f)) { selectedRole = it }
                    LoginRoleCard(UserRole.COORDINATOR, lang.getT("Coordinator", "समन्वयक", "ସମନ୍ଵୟକାରୀ"), R.drawable.avatar_coordinator, selectedRole == UserRole.COORDINATOR, modifier = Modifier.weight(1f)) { selectedRole = it }
                }
                Spacer(Modifier.weight(1f))            // pin version to the bottom
                Text(
                    lang.getT("Version 2.0.0", "संस्करण 2.0.0", "ସଂସ୍କରଣ ୨.୦.୦"),
                    fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color.White
                )
                Spacer(Modifier.height(14.dp))
            } else {
                Spacer(Modifier.height(28.dp))
                Surface(color = Color.White, shape = RoundedCornerShape(20.dp), shadowElevation = 4.dp, modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(lang.getT("Enter 6-digit OTP sent to +91 $phone", "+91 $phone पर भेजा गया ओटीपी दर्ज करें", "+91 $phone କୁ ପଠାଯାଇଥିବା ଓଟିପି ଦିଅନ୍ତୁ"), fontSize = 14.sp, textAlign = TextAlign.Center, color = LoginNavy)
                        Spacer(Modifier.height(18.dp))
                        OtpInput(otp, { otp = it }, onDone = {
                            if (otp.length == 6) {
                                val allGranted = permissionsToRequest.all {
                                    ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
                                }
                                if (allGranted) authViewModel.verifyOtp(phone, otp, selectedRole!!) else showPermissionDialog = true
                            }
                        })
                        Spacer(Modifier.height(18.dp))
                        Button(
                            onClick = {
                                val allGranted = permissionsToRequest.all {
                                    ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
                                }
                                if (allGranted) authViewModel.verifyOtp(phone, otp, selectedRole!!) else showPermissionDialog = true
                            },
                            enabled = otp.length == 6,
                            modifier = Modifier.fillMaxWidth().height(52.dp),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = LoginButtonGreen)
                        ) { Text(lang.getT("Verify & Login", "सत्यापित करें और लॉगिन", "ଯାଞ୍ଚ ଏବଂ ଲଗଇନ୍"), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White) }
                        TextButton(onClick = { step = 1 }) { Text(lang.getT("Change Number", "नंबर बदलें", "ନମ୍ବର ବଦଳାନ୍ତୁ"), color = LoginWelcomeGreen) }
                    }
                }
            }
        }
    }
}

/** AjahFi brand lockup (shield + wordmark + tagline). */
@Composable
private fun AjahFiLogo() {
    Image(
        painter = painterResource(R.drawable.ic_ajahfi_logo),
        contentDescription = "AjahFi",
        contentScale = ContentScale.Fit,
        modifier = Modifier.width(248.dp)
    )
}

/** White rounded input field that floats over the landscape background. */
@Composable
private fun LoginField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leading: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onTogglePassword: (() -> Unit)? = null,
) {
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 3.dp,
        modifier = Modifier.fillMaxWidth().height(50.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(start = 18.dp, end = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(leading, contentDescription = null, tint = PrimaryGreen, modifier = Modifier.size(24.dp))
            Spacer(Modifier.width(14.dp))
            Box(Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                if (value.isEmpty()) {
                    Text(placeholder, color = Color(0xFF9AA0A6), fontSize = 16.sp)
                }
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    singleLine = true,
                    textStyle = TextStyle(color = Color(0xFF1A1A1A), fontSize = 16.sp),
                    cursorBrush = SolidColor(PrimaryGreen),
                    visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            if (isPassword && onTogglePassword != null) {
                IconButton(onClick = onTogglePassword) {
                    Icon(
                        if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null,
                        tint = Color(0xFF6D6D6D)
                    )
                }
            }
        }
    }
}

/** Cream role card with an illustrated avatar, used on the login screen. */
@Composable
private fun LoginRoleCard(
    role: UserRole,
    label: String,
    avatar: Int,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: (UserRole) -> Unit,
) {
    Card(
        onClick = { onClick(role) },
        colors = CardDefaults.cardColors(containerColor = RoleCardCream),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier
            .height(108.dp)
            .border(
                width = if (isSelected) 2.5.dp else 0.dp,
                color = if (isSelected) LoginWelcomeGreen else Color.Transparent,
                shape = RoundedCornerShape(18.dp)
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(vertical = 8.dp, horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(avatar),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxWidth().weight(1f)
            )
            Spacer(Modifier.height(6.dp))
            Text(
                label,
                fontSize = 12.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight.Bold,
                color = LoginWelcomeGreen,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }
    }
}

@Composable
fun RoleCard(role: UserRole, label: String, icon: ImageVector, isSelected: Boolean, modifier: Modifier = Modifier, onClick: (UserRole) -> Unit) {
    val roleColor = when (role) {
        UserRole.SURAKSHA_DIDI -> PrimaryGreen
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
    }

    val roleBgColor = when (role) {
        UserRole.SURAKSHA_DIDI -> Color(0xFFE8F5E9)
        UserRole.FARMER -> Color(0xFFE3F2FD)
        UserRole.COORDINATOR -> Color(0xFFFFF3E0)
    }

    Card(
        onClick = { onClick(role) },
        colors = CardDefaults.cardColors(containerColor = if (isSelected) roleBgColor else Color.White),
        modifier = modifier
            .height(126.dp)
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) roleColor else Color.LightGray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(18.dp)
            ),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 3.dp else 1.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.size(56.dp).clip(CircleShape).background(roleBgColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = roleColor, modifier = Modifier.size(30.dp))
            }
            Spacer(Modifier.height(10.dp))
            Text(
                label,
                fontSize = 11.sp,
                lineHeight = 13.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) roleColor else Color(0xFF37474F),
                textAlign = TextAlign.Center,
                maxLines = 2,
                modifier = Modifier.padding(horizontal = 2.dp)
            )
        }
    }
}

// ------------------------------------------------------------------ auth screen UI helpers

/** Cream backdrop used by the login and sign-up screens (matches the AjahFi brand mock). */
private val AuthCream = Color(0xFFFBF6EA)

/** The AjahFi lockup (goat shield + wordmark + tagline) drawn in its natural colour. */
@Composable
private fun AuthLogo() {
    Image(
        painter = painterResource(R.drawable.ic_logo_custom),
        contentDescription = "AjahFi",
        contentScale = ContentScale.Fit,
        modifier = Modifier.fillMaxWidth(0.72f).heightIn(max = 96.dp)
    )
}

@Composable
private fun LanguageSelector(languageState: MutableState<AppLanguage>) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        Surface(
            onClick = { expanded = true },
            color = Color.White,
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
            shadowElevation = 1.dp
        ) {
            Row(
                Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Public, contentDescription = null, tint = PrimaryGreen, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(6.dp))
                Text(languageState.value.label, color = DarkGreen, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = DarkGreen, modifier = Modifier.size(18.dp))
            }
        }
        MaterialTheme(colorScheme = MaterialTheme.colorScheme.copy(surface = Color.White)) {
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                DropdownMenuItem(text = { Text("English") }, onClick = { languageState.value = AppLanguage.ENGLISH; expanded = false })
                DropdownMenuItem(text = { Text("हिन्दी") }, onClick = { languageState.value = AppLanguage.HINDI; expanded = false })
                DropdownMenuItem(text = { Text("ଓଡ଼ିଆ") }, onClick = { languageState.value = AppLanguage.ODIA; expanded = false })
            }
        }
    }
}

/** Rounded, icon-led text field shared by both auth screens. */
@Composable
private fun AuthField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leading: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onTogglePassword: (() -> Unit)? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = Color(0xFF9E9E9E)) },
        leadingIcon = { Icon(leading, contentDescription = null, tint = Color(0xFF6D6D6D)) },
        trailingIcon = if (isPassword && onTogglePassword != null) {
            {
                IconButton(onClick = onTogglePassword) {
                    Icon(
                        if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null,
                        tint = Color(0xFF6D6D6D)
                    )
                }
            }
        } else null,
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color(0xFFF7F7F2),
            unfocusedBorderColor = Color(0xFFE0E0E0),
            focusedBorderColor = PrimaryGreen
        )
    )
}

@Composable
private fun ChooseRoleHeader(lang: AppLanguage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(Modifier.width(28.dp).height(2.dp).clip(RoundedCornerShape(1.dp)).background(PrimaryGreen.copy(alpha = 0.4f)))
        Text(
            lang.getT("Choose a Role", "एक भूमिका चुनें", "ଏକ ଭୂମିକା ବାଛନ୍ତୁ"),
            modifier = Modifier.padding(horizontal = 12.dp),
            color = DarkGreen, fontWeight = FontWeight.Bold, fontSize = 16.sp
        )
        Box(Modifier.width(28.dp).height(2.dp).clip(RoundedCornerShape(1.dp)).background(PrimaryGreen.copy(alpha = 0.4f)))
    }
}

@Composable
private fun FeatureBar(lang: AppLanguage) {
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 1.dp,
        border = BorderStroke(1.dp, Color(0xFFEEEAD9)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(vertical = 14.dp, horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FeatureItem(Icons.Default.Security, lang.getT("Secure &\nReliable", "सुरक्षित और\nविश्वसनीय", "ସୁରକ୍ଷିତ ଓ\nନିର୍ଭରଯୋଗ୍ୟ"), Modifier.weight(1f))
            FeatureDivider()
            FeatureItem(Icons.Default.Eco, lang.getT("Rural\nFocused", "ग्रामीण\nकेंद्रित", "ଗ୍ରାମୀଣ\nକେନ୍ଦ୍ରିତ"), Modifier.weight(1f))
            FeatureDivider()
            FeatureItem(Icons.Default.Pets, lang.getT("Livestock\nProtection", "पशुधन\nसुरक्षा", "ପଶୁଧନ\nସୁରକ୍ଷା"), Modifier.weight(1f))
            FeatureDivider()
            FeatureItem(Icons.Default.Groups, lang.getT("Empowering\nRural Wealth", "ग्रामीण\nसमृद्धि", "ଗ୍ରାମୀଣ\nସମୃଦ୍ଧି"), Modifier.weight(1f))
        }
    }
}

@Composable
private fun FeatureItem(icon: ImageVector, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, contentDescription = null, tint = PrimaryGreen, modifier = Modifier.size(22.dp))
        Spacer(Modifier.height(6.dp))
        Text(label, fontSize = 9.sp, lineHeight = 11.sp, color = Color(0xFF5A5A5A), fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
    }
}

@Composable
private fun FeatureDivider() {
    Box(Modifier.height(28.dp).width(1.dp).background(Color(0xFFE8E4D5)))
}

/**
 * Guidance for password recovery. The app is password-only and SMS delivery is off
 * in production, so a self-service reset can't deliver a code; recovery is handled by
 * the Admin/Coordinator who manages the account.
 */
@Composable
private fun ForgotPasswordDialog(lang: AppLanguage, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = { Icon(Icons.Default.Lock, contentDescription = null, tint = PrimaryGreen) },
        title = { Text(lang.getT("Forgot Password?", "पासवर्ड भूल गए?", "ପାସୱାର୍ଡ ଭୁଲିଗଲେ?"), fontWeight = FontWeight.Bold, color = Color.Black) },
        text = {
            Text(
                lang.getT(
                    "To reset your password, please contact your Coordinator or the Admin who manages your account. They can set a new password for you.",
                    "अपना पासवर्ड रीसेट करने के लिए, कृपया अपने समन्वयक या आपके खाते का प्रबंधन करने वाले एडमिन से संपर्क करें। वे आपके लिए नया पासवर्ड सेट कर सकते हैं।",
                    "ଆପଣଙ୍କ ପାସୱାର୍ଡ ରିସେଟ୍ କରିବାକୁ, ଦଯାକରି ଆପଣଙ୍କ ସମନ୍ଵୟକାରୀ କିମ୍ବା ଆପଣଙ୍କ ଖାତା ପରିଚାଳନା କରୁଥିବା ଆଡମିନ୍‌ଙ୍କ ସହ ଯୋଗାଯୋଗ କରନ୍ତୁ। ସେମାନେ ଆପଣଙ୍କ ପାଇଁ ନୂଆ ପାସୱାର୍ଡ ସେଟ୍ କରିପାରିବେ।"
                ),
                color = Color.DarkGray
            )
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text(lang.getT("Got it", "समझ गया", "ବୁଝିଗଲି"), color = PrimaryGreen, fontWeight = FontWeight.Bold) }
        },
        shape = RoundedCornerShape(20.dp)
    )
}

@Composable
fun DidiDashboard(navController: NavHostController, sessionManager: SessionManager) {
    var showNotifications by remember { mutableStateOf(false) }
    if (showNotifications) NotificationSheet(
        userRole = UserRole.SURAKSHA_DIDI, themeColor = PrimaryGreen,
        onOpenMortality = { navController.navigate("mortality_queue") }
    ) { showNotifications = false }
    val languageState = LocalAppLanguage.current
    val savedName by sessionManager.userName.collectAsState(initial = null)
    // Canonical name from the backend (also re-syncs the session cache).
    val profileVm: ProfileViewModel = hiltViewModel()
    val dbProfile by profileVm.profile.collectAsState()
    val userName = dbProfile?.fullName ?: savedName ?: ""

    val dashVm: DidiDashboardViewModel = hiltViewModel()
    val dashState by dashVm.state.collectAsState()
    val stats = (dashState as? UiState.Success)?.data

    ResponsiveLayout(
        compact = {
            Scaffold(
                modifier = Modifier.fillMaxSize().navigationBarsPadding(),
                bottomBar = { DidiBottomBar(navController) },
                contentWindowInsets = WindowInsets(0, 0, 0, 0)
            ) { padding ->
                DidiContent(padding, navController, userName, stats) { showNotifications = true }
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
                        label = { Text(languageState.value.getT("Home", "होम", "ମୁଖ୍ୟ ପୃଷ୍ଠା")) },
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
                        label = { Text(languageState.value.getT("Goats", "बकरियां", "ଛେଳି")) },
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
                        label = { Text(languageState.value.getT("Vaccines", "टीकाकरण", "ଟୀକା")) },
                        colors = NavigationRailItemDefaults.colors(
                            selectedIconColor = PrimaryGreen,
                            selectedTextColor = PrimaryGreen,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
                DidiContent(PaddingValues(0.dp), navController, userName, stats) { showNotifications = true }
            }
        }
    )
}

@Composable
fun DidiContent(padding: PaddingValues, navController: NavHostController, userName: String, stats: SdDashboard?, onNotificationClick: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val context = LocalContext.current
    val notifVm: NotificationsViewModel = androidx.hilt.navigation.compose.hiltViewModel()
    val unread by notifVm.unread.collectAsState()
    LaunchedEffect(Unit) { notifVm.refreshUnread() }
    Column(
        modifier = Modifier
            .padding(bottom = padding.calculateBottomPadding())
            .fillMaxSize()
            .background(Color(0xFFF8F9F5))
    ) {
        DashboardHeader(
            navController,
            userName,
            languageState.value.getT("Suraksha Didi", "सुरक्षा दीदी", "ସୁରକ୍ଷା ଦିଦି"),
            onNotificationClick,
            hasNotifications = unread > 0,
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
                        languageState.value.getT("This Month Overview", "इस महीने का अवलोकन", "ଏହି ମାସର ସମୀକ୍ଷା"),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            // Stats (2 columns on mobile, 4 on tablet)
            val statSpan = if (isCompact) 3 else 3
            items(4, span = { GridItemSpan(statSpan) }) { index ->
                val dash = if (stats == null) "…" else null
                when(index) {
                    0 -> StatCard(languageState.value.getT("Goats Enrolled", "पंजीकृत बकरियां", "ପଞ୍ଜିକୃତ ଛେଳି"), dash ?: "${stats!!.totalEnrolled}", painterResource(R.drawable.ic_ewe_custom), PrimaryGreen, CardLightGreen)
                    1 -> StatCard(languageState.value.getT("Pending Claims", "लंबित दावे", "ବାକି ରହିଥିବା ଦାବି"), dash ?: "${stats!!.pendingQueries}", Icons.AutoMirrored.Filled.Assignment, AccentOrange, CardLightOrange)
                    2 -> StatCard(languageState.value.getT("Policies Issued", "जारी नीतियां", "ଜାରି ପଲିସି"), dash ?: "${stats!!.policiesIssued}", Icons.Default.CalendarToday, InfoBlue, CardLightBlue)
                    3 -> StatCard(
                        label = languageState.value.getT("Earnings", "आय", "ଉପାର୍ଜନ"),
                        value = dash ?: "₹${stats!!.premiumCollected.toInt()}",
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
                        languageState.value.getT("Quick Actions", "त्वरित कार्रवाइयां", "ତ୍ୱରିତ କାର୍ଯ୍ୟାନୁଷ୍ଠାନ"),
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
                    0 -> QuickActionGridCard(languageState.value.getT("Enroll Goat", "बकरी का नामांकन", "ଛେଳି ପଞ୍ଜିକରଣ"), painterResource(R.drawable.ic_ewe_custom), PrimaryGreen, CardLightGreen) { navController.navigate("enrollment") }
                    1 -> QuickActionGridCard(languageState.value.getT("Vaccination", "टीकाकरण", "ଟୀକାକରଣ"), Icons.Default.MedicalServices, InfoBlue, CardLightBlue) { navController.navigate("vaccine_list") }
                    2 -> QuickActionGridCard(languageState.value.getT("Mortality Report", "मृत्यु रिपोर्ट", "ମୃତ୍ୟୁ ରିପୋର୍ଟ"), Icons.Default.LocationOn, Color(0xFFD32F2F), CardLightRed) { navController.navigate("mortality_queue") }
                    3 -> QuickActionGridCard(languageState.value.getT("Claims", "दावे", "ଦାବି"), Icons.AutoMirrored.Filled.Assignment, AccentOrange, CardLightOrange) { navController.navigate("claim_list") }
                    4 -> QuickActionGridCard(languageState.value.getT("Goat List", "बकरियों की सूची", "ଛେଳି ତାଲିକା"), Icons.AutoMirrored.Filled.FactCheck, Color(0xFF2E7D32), CardLightGreen) { navController.navigate("goat_list") }
                    5 -> {
                        val comingSoonMsg = languageState.value.getT("Coming Soon", "जल्द ही आ रहा है", "ଖୁବ୍ ଶୀଘ୍ର ଆସୁଛି")
                        QuickActionGridCard(languageState.value.getT("AI Assistant", "AI सहायक", "AI ସହାୟକ"), Icons.Default.AccountBox, Color(0xFF7B1FA2), CardLightPurple) {
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
    val languageState = LocalAppLanguage.current
    Scaffold(
        modifier = Modifier.fillMaxSize().navigationBarsPadding(),
        topBar = { CenterAlignedTopAppBar(title = { Text(languageState.value.getT("Premium Collection", "प्रीमियम संग्रह", "ପ୍ରିମିୟମ ସଂଗ୍ରହ")) }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(languageState.value.getT("Total Premium: ₹350", "कुल प्रीमियम: ₹350", "ମୋଟ ପ୍ରିମିୟମ: ₹୩୫୦"), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    BreakdownItem(languageState.value.getT("Insurance Company", "बीमा कंपनी", "ବୀମା କମ୍ପାନୀ"), "₹240")
                    BreakdownItem(languageState.value.getT("Vaccination Program", "टीकाकरण कार्यक्रम", "ଟୀକାକରଣ କାର୍ଯ୍ୟକ୍ରମ"), "₹80")
                    BreakdownItem(languageState.value.getT("Suraksha Didi Fee", "सुरक्षा दीदी शुल्क", "ସୁରକ୍ଷା ଦିଦି ଶୁଳ୍କ"), "₹20")
                    BreakdownItem(languageState.value.getT("FPC Corpus Fund", "एफपीसी कॉर्पस फंड", "FPC କର୍ପସ ପାଣ୍ଠି"), "₹10")
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = onComplete, modifier = Modifier.fillMaxWidth()) { Text(languageState.value.getT("Confirm Payment & Generate Receipt", "भुगतान की पुष्टि करें और रसीद उत्पन्न करें", "ଦେୟ ନିଶ୍ଚିତ କରନ୍ତୁ ଏବଂ ରସିଦ ପ୍ରସ୍ତୁତ କରନ୍ତୁ")) }
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
    val languageState = LocalAppLanguage.current
    val context = LocalContext.current
    
    // 0: Notification, 1: Visit, 2: Verification, 3: Photos, 4: AI, 5: Handover, 6: Claim
    var currentStepIndex by rememberSaveable { mutableIntStateOf(2) } 
    var detailStepIndex by rememberSaveable { mutableStateOf<Int?>(null) }
    
    val steps = listOf(
        MortalityStepData(
            languageState.value.getT("Death Notification", "मृत्यु की सूचना", "ମୃତ୍ୟୁ ବିଜ୍ଞପ୍ତି"),
            if (currentStepIndex > 0) MortalityStepStatus.COMPLETED else if (currentStepIndex == 0) MortalityStepStatus.IN_PROGRESS else MortalityStepStatus.PENDING,
            if (currentStepIndex > 0) languageState.value.getT("Completed", "पूरा हुआ", "ସମ୍ପୂର୍ଣ୍ଣ") else if (currentStepIndex == 0) languageState.value.getT("In Progress", "प्रगति पर है", "ଚାଲୁଅଛି") else languageState.value.getT("Pending", "लंबित", "ବାକି ଅଛି")
        ),
        MortalityStepData(
            languageState.value.getT("Site Visit", "साइट दौरा", "ସାଇଟ୍ ପରିଦର୍ଶନ"),
            if (currentStepIndex > 1) MortalityStepStatus.COMPLETED else if (currentStepIndex == 1) MortalityStepStatus.IN_PROGRESS else MortalityStepStatus.PENDING,
            if (currentStepIndex > 1) languageState.value.getT("Completed", "पूरा हुआ", "ସମ୍ପୂର୍ଣ୍ଣ") else if (currentStepIndex == 1) languageState.value.getT("In Progress", "प्रगति पर है", "ଚାଲୁଅଛି") else languageState.value.getT("Pending", "लंबित", "ବାକି ଅଛି")
        ),
        MortalityStepData(
            languageState.value.getT("Carcass Verification", "शव सत्यापन", "ଶବ ଯାଞ୍ଚ"),
            if (currentStepIndex > 2) MortalityStepStatus.COMPLETED else if (currentStepIndex == 2) MortalityStepStatus.IN_PROGRESS else MortalityStepStatus.PENDING,
            if (currentStepIndex > 2) languageState.value.getT("Completed", "पूरा हुआ", "ସମ୍ପୂର୍ଣ୍ଣ") else if (currentStepIndex == 2) languageState.value.getT("In Progress", "प्रगति पर है", "ଚାଲୁଅଛି") else languageState.value.getT("Pending", "लंबित", "ବାକି ଅଛି")
        ),
        MortalityStepData(
            languageState.value.getT("Photo Capture", "फोटो कैप्चर", "ଫଟୋ କ୍ୟାପଚର"),
            if (currentStepIndex > 3) MortalityStepStatus.COMPLETED else if (currentStepIndex == 3) MortalityStepStatus.IN_PROGRESS else MortalityStepStatus.PENDING,
            if (currentStepIndex > 3) languageState.value.getT("Completed", "पूरा हुआ", "ସମ୍ପୂର୍ଣ୍ଣ") else if (currentStepIndex == 3) languageState.value.getT("In Progress", "प्रगति पर है", "ଚାଲୁଅଛି") else languageState.value.getT("Pending", "लंबित", "ବାକି ଅଛି")
        ),
        MortalityStepData(
            languageState.value.getT("AI Assessment", "AI मूल्यांकन", "AI ମୂଲ୍ୟାଙ୍କନ"),
            if (currentStepIndex > 4) MortalityStepStatus.COMPLETED else if (currentStepIndex == 4) MortalityStepStatus.IN_PROGRESS else MortalityStepStatus.PENDING,
            if (currentStepIndex > 4) languageState.value.getT("Completed", "पूरा हुआ", "ସମ୍ପୂର୍ଣ୍ଣ") else if (currentStepIndex == 4) languageState.value.getT("In Progress", "प्रगति पर है", "ଚାଲୁଅଛି") else languageState.value.getT("Pending", "लंबित", "ବାକି ଅଛି")
        ),
        MortalityStepData(
            languageState.value.getT("Carcass Handover", "शव हैंडओवर", "ଶବ ହସ୍ତାନ୍ତର"),
            if (currentStepIndex > 5) MortalityStepStatus.COMPLETED else if (currentStepIndex == 5) MortalityStepStatus.IN_PROGRESS else MortalityStepStatus.PENDING,
            if (currentStepIndex > 5) languageState.value.getT("Completed", "पूरा हुआ", "ସମ୍ପୂର୍ଣ୍ଣ") else if (currentStepIndex == 5) languageState.value.getT("In Progress", "प्रगति पर है", "ଚାଲୁଅଛି") else languageState.value.getT("Pending", "लंबित", "ବାକି ଅଛି")
        ),
        MortalityStepData(
            languageState.value.getT("Claim Submission", "दावा प्रस्तुत करना", "ଦାବି ଦାଖଲ"),
            if (currentStepIndex > 6) MortalityStepStatus.COMPLETED else if (currentStepIndex == 6) MortalityStepStatus.IN_PROGRESS else MortalityStepStatus.PENDING,
            if (currentStepIndex > 6) languageState.value.getT("Completed", "पूरा हुआ", "ସମ୍ପୂର୍ଣ୍ଣ") else if (currentStepIndex == 6) languageState.value.getT("In Progress", "प्रगति पर है", "ଚାଲୁଅଛି") else languageState.value.getT("Pending", "लंबित", "ବାକି ଅଛି")
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
                    title = { Text(languageState.value.getT("Mortality Report", "मृत्यु रिपोर्ट", "ମୃତ୍ୟୁ ରିପୋର୍ଟ"), fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
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
    val languageState = LocalAppLanguage.current
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
            Toast.makeText(context, languageState.value.getT("Camera permission required", "कैमरा अनुमति आवश्यक है", "କ୍ୟାମେରା ଅନୁମତି ଆବଶ୍ୟକ"), Toast.LENGTH_SHORT).show()
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
                title = { Text(languageState.value.getT("Step Details", "कदम का विवरण", "ପଦକ୍ଷେପ ବିବରଣୀ")) },
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
                    Text("Physical Site Visit", style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Didi must physically visit the location where the carcass is located.")
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = onComplete, modifier = Modifier.fillMaxWidth()) {
                        Text("Mark Visit Complete")
                    }
                }
                2 -> { // Carcass Verification
                    Text("Carcass Verification", style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Check tag number and breed.")
                    // checklist logic here...
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = onComplete, modifier = Modifier.fillMaxWidth()) {
                        Text("Verify & Continue")
                    }
                }
                3 -> { // Photo Capture
                    Text("Photo Capture", style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Capture clear photos of the carcass.")
                    
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
                                    Text(languageState.value.getT("Tap to Capture", "कैप्चर करने के लिए टैप करें", "କ୍ୟାପଚର କରିବାକୁ ଟ୍ୟାପ୍ କରନ୍ତୁ"), color = Color.Gray)
                                }
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = onComplete, enabled = capturedPhotoUri != null, modifier = Modifier.fillMaxWidth()) {
                        Text("Save Photos")
                    }
                }
                4 -> { // AI Assessment
                    Text("AI Assessment", style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(16.dp))
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                    Text("Backend AI model is checking the photos...", modifier = Modifier.padding(top = 16.dp))
                    
                    LaunchedEffect(Unit) {
                        kotlinx.coroutines.delay(3000)
                        onComplete()
                    }
                }
                5 -> { // Carcass Handover
                    Text("Carcass Handover", style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Confirm disposal or handover.")
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = onComplete, modifier = Modifier.fillMaxWidth()) {
                        Text("Confirm Handover")
                    }
                }
                6 -> { // Claim Submission
                    Text("Claim Submission", style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Final review and submit claim.")
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = onComplete, modifier = Modifier.fillMaxWidth()) {
                        Text("Submit Claim")
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
    val languageState = LocalAppLanguage.current
    val context = LocalContext.current
    var isConfirmed by remember { mutableStateOf(false) }
    
    var deathDate by remember { mutableStateOf("") }
    var deathTime by remember { mutableStateOf("") }

    val farmerVm: FarmerHomeViewModel = hiltViewModel()
    val policiesState by farmerVm.policies.collectAsState()
    // A goat whose death is already reported (or whose claim is approved) can't be
    // reported again — the backend rejects it, so keep it out of the picker.
    val policies = ((policiesState as? UiState.Success)?.data?.policies ?: emptyList())
        .filter { it.status != "dead" && it.status != "claimed" }
    val claimsVm: FarmerClaimsViewModel = hiltViewModel()
    val submitState by claimsVm.submit.collectAsState()
    val isSubmitting = submitState is SubmitState.Submitting
    var deathDateIso by remember { mutableStateOf("") }
    LaunchedEffect(submitState) {
        when (val s = submitState) {
            is SubmitState.Success -> { Toast.makeText(context, s.message ?: "Reported", Toast.LENGTH_SHORT).show(); claimsVm.resetSubmit(); onComplete() }
            is SubmitState.Error -> { Toast.makeText(context, s.message, Toast.LENGTH_LONG).show(); claimsVm.resetSubmit() }
            else -> {}
        }
    }

    val goats = policies.map { Triple(it.earTagNumber, it.breed, "") }
    var selectedIndex by remember { mutableStateOf(0) }
    val selectedGoat = goats.getOrNull(selectedIndex) ?: Triple("—", "", "")
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
            Toast.makeText(context, languageState.value.getT("Camera permission required", "कैमरा अनुमति आवश्यक है", "କ୍ୟାମେରା ଅନୁମତି ଆବଶ୍ୟକ"), Toast.LENGTH_SHORT).show()
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
                title = { Text(languageState.value.getT("Report Goat Death", "बकरी की मृत्यु की रिपोर्ट", "ଛେଳି ମୃତ୍ୟୁ ରିପୋର୍ଟ"), color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
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
                    append(languageState.value.getT("Select Goat", "बकरी चुनें", "ଛେଳି ବାଛନ୍ତୁ"))
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
                        Column(modifier = Modifier.weight(1f)) {
                            Text(selectedGoat.first, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)
                            Text("${selectedGoat.second} • ${languageState.value.getT("Female", "मादा", "ମାଈ")} • ${selectedGoat.third}", color = Color.Gray, fontSize = 13.sp)
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
                                selectedIndex = goats.indexOf(goat)
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Date of Death
            EnrollmentTextField(
                label = languageState.value.getT("Date of Death *", "मृत्यु की तारीख *", "ମୃତ୍ୟୁର ତାରିଖ *"),
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
                            deathDateIso = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
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
                label = languageState.value.getT("Time of Death *", "मृत्यु का समय *", "ମୃତ୍ୟୁର ସମୟ *"),
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
                languageState.value.getT("Upload Photo (Optional)", "फोटो अपलोड करें (वैकल्पिक)", "ଫଟୋ ଅପଲୋଡ୍ କରନ୍ତୁ (ବୈକଳ୍ପିକ)"),
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
                    languageState.value.getT("I confirm the above information is correct.", "मैं पुष्टि करता हूं कि उपरोक्त जानकारी सही है।", "ମୁଁ ନିଶ୍ଚିତ କରୁଛି ଯେ ଉପରୋକ୍ତ ସୂଚନା ସଠିକ୍ ଅଟେ |"),
                    fontSize = 13.sp, color = Color.Black
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(32.dp))
            
            Button(
                onClick = {
                    val goatId = policies.getOrNull(selectedIndex)?.goatId
                    if (goatId == null) {
                        Toast.makeText(context, languageState.value.getT("Select a goat", "एक बकरी चुनें", "ଏକ ଛେଳି ବାଛନ୍ତୁ"), Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    // Backend accepts an ISO datetime; append a nominal time.
                    val iso = if (deathDateIso.isNotBlank()) "${deathDateIso}T00:00:00" else ""
                    claimsVm.reportDeath(goatId, iso)
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                enabled = isConfirmed && deathDate.isNotBlank() && deathTime.isNotBlank() && !isSubmitting,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
            ) {
                Text(languageState.value.getT("Submit Alert", "अलर्ट भेजें", "ସୂଚନା ଦିଅନ୍ତୁ"), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun ClaimStatusTracker() {
    val languageState = LocalAppLanguage.current
    val stages = listOf(
        languageState.value.getT("Death Reported", "मृत्यु की सूचना दी गई", "ମୃତ୍ୟୁ ରିପୋର୍ଟ କରାଯାଇଛି"),
        languageState.value.getT("Didi Site Visit", "दीदी का साइट दौरा", "ଦିଦିଙ୍କ ସାଇଟ୍ ପରିଦର୍ଶନ"),
        languageState.value.getT("Verification", "सत्यापन", "ଯାଞ୍ଚ"),
        languageState.value.getT("Coordinator Review", "समन्वयक समीक्षा", "ସମନ୍ଵୟକାରୀ ସମୀକ୍ଷା"),
        languageState.value.getT("Payment Sent", "भुगतान भेज दिया गया", "ଦେୟ ପଠାଯାଇଛି")
    )
    val currentStage = 2
    Column(modifier = Modifier.fillMaxSize().navigationBarsPadding().padding(16.dp)) {
        Text(languageState.value.getT("Claim #CLM7890", "दावा #CLM7890", "ଦାବି #CLM7890"), style = MaterialTheme.typography.headlineSmall)
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
    val languageState = LocalAppLanguage.current
    Text(languageState.value.getT("Step 1: Carcass Verification", "चरण 1: शव सत्यापन", "ପଦକ୍ଷେପ ୧: ଶବ ଯାଞ୍ଚ"), style = MaterialTheme.typography.titleLarge)
    Spacer(modifier = Modifier.height(8.dp))
    Text(languageState.value.getT("Capture clear photo of the carcass with the ear tag visible.", "कान के टैग के साथ शव की स्पष्ट फोटो लें।", "କାନ ଟ୍ୟାଗ୍ ସହିତ ଶବର ସ୍ପଷ୍ଟ ଫଟୋ ନିଅନ୍ତୁ |"), color = Color.Gray)
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
                    Text(languageState.value.getT("Tap to Capture", "कैप्चर करने के लिए टैप करें", "କ୍ୟାପଚର କରିବାକୁ ଟ୍ୟାପ୍ କରନ୍ତୁ"), color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun MortalityStep2() { 
    val languageState = LocalAppLanguage.current
    Text(languageState.value.getT("Step 2: Check Ear Tag & Breed", "चरण 2: कान का टैग और नस्ल की जाँच करें", "ପଦକ୍ଷେପ ୨: କାନ ଟ୍ୟାଗ୍ ଏବଂ ପ୍ରଜାତି ଯାଞ୍ଚ କରନ୍ତୁ")) 
}
@Composable
fun MortalityStep3(photoUri: Uri?, onCapture: () -> Unit) { 
    val languageState = LocalAppLanguage.current
    Text(languageState.value.getT("Step 3: Capture Farmer & Site Photo", "चरण 3: किसान और साइट की फोटो लें", "ପଦକ୍ଷେପ ୩: କୃଷକ ଏବଂ ସାଇଟ୍ ଫଟୋ ନିଅନ୍ତୁ"), style = MaterialTheme.typography.titleLarge) 
    Spacer(modifier = Modifier.height(8.dp))
    Text(languageState.value.getT("Capture a photo of the farmer at the site with the carcass.", "शव के साथ साइट पर किसान की एक फोटो लें।", "ଶବ ସହିତ ସାଇଟରେ କୃଷକଙ୍କର ଏକ ଫଟୋ ନିଅନ୍ତୁ |"), color = Color.Gray)
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
                    Text(languageState.value.getT("Tap to Capture", "कैप्चर करने के लिए टैप करें", "କ୍ୟାପଚର କରିବାକୁ ଟ୍ୟାପ୍ କରନ୍ତୁ"), color = Color.Gray)
                }
            }
        }
    }
}
@Composable
fun MortalityStep4() { 
    val languageState = LocalAppLanguage.current
    Text(languageState.value.getT("Step 4: Cause of Death & Sign", "चरण 4: मृत्यु का कारण और हस्ताक्षर", "ପଦକ୍ଷେପ ୪: ମୃତ୍ୟୁର କାରଣ ଏବଂ ସ୍ଵାକ୍ଷର")) 
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
    val languageState = LocalAppLanguage.current
    val context = LocalContext.current

    val enrollVm: EnrollmentViewModel = hiltViewModel()
    val submitState by enrollVm.submit.collectAsState()
    val enrollResults by enrollVm.results.collectAsState()
    val enrollProgress by enrollVm.progress.collectAsState()
    val isSubmitting = submitState is SubmitState.Submitting
    LaunchedEffect(submitState) {
        when (val s = submitState) {
            is SubmitState.Success -> { currentStep = 7 }
            is SubmitState.Error -> Toast.makeText(context, s.message, Toast.LENGTH_LONG).show()
            else -> {}
        }
    }

    // Form State
    var farmerName by rememberSaveable { mutableStateOf("") }
    var mobileNumber by rememberSaveable { mutableStateOf("") }
    var village by rememberSaveable { mutableStateOf("") }
    var location by rememberSaveable { mutableStateOf("") }
    var aadhaar by rememberSaveable { mutableStateOf("") }
    
    // Current goat form (one goat being added/edited).
    // Breed and gender start empty so the Didi has to choose: a pre-filled default
    // is silently accepted by the step-2 isNotBlank() check and enrols the wrong goat.
    var breed by rememberSaveable { mutableStateOf("") }
    var gender by rememberSaveable { mutableStateOf("") }
    var age by rememberSaveable { mutableStateOf("") }
    var ageUnit by rememberSaveable { mutableStateOf(languageState.value.getT("Months", "महीने", "ମାସ")) }
    var weight by rememberSaveable { mutableStateOf("") }
    var colorMarks by rememberSaveable { mutableStateOf("") }
    var earTagNumber by rememberSaveable { mutableStateOf("") }

    // Photo State (current goat)
    var leftPhotoUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var rightPhotoUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var frontPhotoUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var tagPhotoUri by rememberSaveable { mutableStateOf<Uri?>(null) }

    // Goats collected so far in this enrollment, and which one is being edited (null = new).
    val goats = remember { mutableStateListOf<GoatDraft>() }
    var editingIndex by remember { mutableStateOf<Int?>(null) }

    // Vaccination step State — which vaccines were given during enrollment (shared by all goats).
    var pprGiven by rememberSaveable { mutableStateOf(true) }
    var etttGiven by rememberSaveable { mutableStateOf(true) }
    var fmdGiven by rememberSaveable { mutableStateOf(false) }
    var poxGiven by rememberSaveable { mutableStateOf(false) }

    val monthsLabel = languageState.value.getT("Months", "महीने", "ମାସ")

    fun resetGoatForm() {
        breed = ""; gender = ""; age = ""; ageUnit = monthsLabel
        weight = ""; colorMarks = ""; earTagNumber = ""
        leftPhotoUri = null; rightPhotoUri = null; frontPhotoUri = null; tagPhotoUri = null
    }

    fun currentGoatDraft(): GoatDraft {
        val isMale = gender == languageState.value.getT("Male", "नर", "ଅଣ୍ଡିରା")
        val isYears = ageUnit == languageState.value.getT("Years", "साल", "ବର୍ଷ")
        val ageMonths = (age.toIntOrNull() ?: 0) * (if (isYears) 12 else 1)
        return GoatDraft(
            breed = breed, genderRaw = if (isMale) "male" else "female", genderLabel = gender,
            ageMonths = ageMonths, ageLabel = "$age${if (isYears) "Y" else "M"}",
            weightKg = weight.toDoubleOrNull() ?: 0.0, weightLabel = "$weight Kg",
            color = colorMarks, earTag = earTagNumber,
            photos = listOfNotNull(leftPhotoUri, rightPhotoUri, frontPhotoUri, tagPhotoUri),
        )
    }

    fun loadGoatIntoForm(g: GoatDraft) {
        breed = g.breed
        gender = g.genderLabel
        ageUnit = if (g.ageLabel.endsWith("Y")) languageState.value.getT("Years", "साल", "ବର୍ଷ") else monthsLabel
        age = g.ageLabel.dropLast(1)
        weight = g.weightLabel.removeSuffix(" Kg")
        colorMarks = g.color; earTagNumber = g.earTag
        leftPhotoUri = g.photos.getOrNull(0); rightPhotoUri = g.photos.getOrNull(1)
        frontPhotoUri = g.photos.getOrNull(2); tagPhotoUri = g.photos.getOrNull(3)
    }

    val steps = listOf(
        languageState.value.getT("Farmer Information", "किसान जानकारी", "କୃଷକ ସୂଚନା"),
        languageState.value.getT("Goat Details", "बकरी का विवरण", "ଛେଳି ବିବରଣୀ"),
        languageState.value.getT("Goat Photos", "बकरी की तस्वीरें", "ଛେଳି ଫଟୋ"),
        languageState.value.getT("Goats Added", "जोड़ी गई बकरियां", "ଯୋଡ଼ାଯାଇଥିବା ଛେଳି"),
        languageState.value.getT("Vaccination History", "टीकाकरण इतिहास", "ଟୀକାକରଣ ଇତିହାସ"),
        languageState.value.getT("Premium Payment", "प्रीमियम भुगतान", "ପ୍ରିମିୟମ ଦେୟ"),
        languageState.value.getT("Policy Generated", "पॉलिसी जेनरेट हुई", "ନୀତି ପ୍ରସ୍ତୁତ ହୋଇଛି")
    )

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
                        languageState.value.getT("Enrollment (Step $currentStep of 7)", "नामांकन (चरण $currentStep of 7)", "ପଞ୍ଜିକରଣ (ପଦକ୍ଷେପ $currentStep of 7)"),
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
                    2 -> EnrollmentGoatStep(breed, { breed = it }, gender, { gender = it }, age, { age = it }, ageUnit, { ageUnit = it }, weight, { weight = it }, colorMarks, { colorMarks = it }, earTagNumber, { earTagNumber = it })
                    3 -> EnrollmentPhotoStep(
                        leftUri = leftPhotoUri, onLeftCapture = { leftPhotoUri = it },
                        rightUri = rightPhotoUri, onRightCapture = { rightPhotoUri = it },
                        frontUri = frontPhotoUri, onFrontCapture = { frontPhotoUri = it },
                        tagUri = tagPhotoUri, onTagCapture = { tagPhotoUri = it }
                    )
                    4 -> EnrollmentGoatsAddedStep(
                        goats = goats,
                        onAddAnother = { resetGoatForm(); editingIndex = null; currentStep = 2 },
                        onEdit = { i -> loadGoatIntoForm(goats[i]); editingIndex = i; currentStep = 2 },
                        onDelete = { i -> goats.removeAt(i) },
                        onContinue = { currentStep = 5 }
                    )
                    5 -> EnrollmentVaccinationStep(
                        pprGiven, { pprGiven = it }, etttGiven, { etttGiven = it },
                        fmdGiven, { fmdGiven = it }, poxGiven, { poxGiven = it }
                    )
                    6 -> EnrollmentPaymentStep(goats.size)
                    7 -> EnrollmentPoliciesStep(farmerName, enrollResults, goats, onFinish = onComplete)
                }
            }

            val tagIsUnique = goats.filterIndexed { i, _ -> i != editingIndex }
                .none { it.earTag.equals(earTagNumber.trim(), ignoreCase = true) }
            val isStepValid = when(currentStep) {
                1 -> farmerName.isNotBlank() && mobileNumber.length == 10 && village.isNotBlank() && location.isNotBlank() && aadhaar.length == 12
                2 -> breed.isNotBlank() && gender.isNotBlank() && age.isNotBlank() && weight.isNotBlank() && colorMarks.isNotBlank() && earTagNumber.isNotBlank() && tagIsUnique
                3 -> leftPhotoUri != null && rightPhotoUri != null && frontPhotoUri != null && tagPhotoUri != null
                else -> true
            }

            // Steps 4 (Goats Added) and 7 (Policy Generated) carry their own buttons.
            if (currentStep != 4 && currentStep != 7) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    if (currentStep == 2 || currentStep == 3 || currentStep == 5 || currentStep == 6) {
                        OutlinedButton(
                            onClick = {
                                when (currentStep) {
                                    2 -> { editingIndex = null; currentStep = if (goats.isNotEmpty()) 4 else 1 }
                                    5 -> currentStep = 4
                                    else -> currentStep--   // 3 -> 2, 6 -> 5
                                }
                            },
                            modifier = Modifier.weight(1f).height(56.dp),
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, PrimaryGreen)
                        ) {
                            Text(languageState.value.getT("Back", "पीछे", "ପଛକୁ"), color = PrimaryGreen, fontWeight = FontWeight.Bold)
                        }
                    }

                    Button(
                        onClick = {
                            when (currentStep) {
                                3 -> {
                                    // Commit the current goat into the list, then show Goats Added.
                                    val draft = currentGoatDraft()
                                    val idx = editingIndex
                                    if (idx != null && idx in goats.indices) goats[idx] = draft else goats.add(draft)
                                    editingIndex = null
                                    currentStep = 4
                                }
                                6 -> {
                                    // Submit the whole batch: shared vaccines + payment for every goat.
                                    val cal = java.util.Calendar.getInstance()
                                    fun isoOf(c: java.util.Calendar) = String.format(
                                        "%04d-%02d-%02d", c.get(java.util.Calendar.YEAR),
                                        c.get(java.util.Calendar.MONTH) + 1, c.get(java.util.Calendar.DAY_OF_MONTH)
                                    )
                                    val todayIso = isoOf(cal)
                                    cal.add(java.util.Calendar.MONTH, 6)
                                    val boosterIso = isoOf(cal)
                                    val vaccineList = listOf(
                                        "ppr" to pprGiven, "et_tt" to etttGiven,
                                        "fmd" to fmdGiven, "goat_pox" to poxGiven
                                    ).map { (type, given) ->
                                        if (given) VaccineIn(
                                            vaccineType = type, status = "done",
                                            vaccinationDate = todayIso, nextVaccinationDate = boosterIso
                                        ) else VaccineIn(
                                            vaccineType = type, status = "pending",
                                            nextVaccinationDate = todayIso
                                        )
                                    }
                                    enrollVm.enrollBatch(
                                        farmerName = farmerName, farmerMobile = mobileNumber,
                                        village = village.ifBlank { null }, gpsLocation = location.ifBlank { null },
                                        aadhaarId = aadhaar.ifBlank { null },
                                        goats = goats.toList(), vaccines = vaccineList,
                                        paymentMode = "cash", amount = 350.0,
                                    )
                                }
                                7 -> onComplete()
                                else -> currentStep++   // 1 -> 2, 2 -> 3, 5 -> 6
                            }
                        },
                        enabled = isStepValid && !isSubmitting,
                        modifier = Modifier.weight(1f).height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen, disabledContainerColor = PrimaryGreen.copy(alpha = 0.5f))
                    ) {
                        Text(
                            text = when {
                                isSubmitting -> enrollProgress ?: languageState.value.getT("Submitting…", "प्रस्तुत हो रहा है…", "ଦାଖଲ ହେଉଛି…")
                                currentStep == 7 -> languageState.value.getT("Finish Enrollment", "नामांकन पूरा करें", "ପଞ୍ଜିକରଣ ଶେଷ କରନ୍ତୁ")
                                currentStep == 6 -> languageState.value.getT("Submit & Generate Policy", "जमा करें और पॉलिसी बनाएं", "ଦାଖଲ କରି ପଲିସି ପ୍ରସ୍ତୁତ କରନ୍ତୁ")
                                currentStep == 3 -> languageState.value.getT("Save Photos", "तस्वीरें सहेजें", "ଫଟୋ ସେଭ୍ କରନ୍ତୁ")
                                else -> languageState.value.getT("Next", "अगला", "ପରବର୍ତ୍ତୀ")
                            },
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EnrollmentFarmerStep(name: String, onNameChange: (String) -> Unit, phone: String, onPhoneChange: (String) -> Unit, village: String, onVillageChange: (String) -> Unit, location: String, onLocationChange: (String) -> Unit, aadhaar: String, onAadhaarChange: (String) -> Unit) {
    val languageState = LocalAppLanguage.current
    val fetchGps = rememberGpsFetcher(onResult = { onLocationChange(it) })
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        EnrollmentTextField(label = languageState.value.getT("Farmer Name *", "किसान का नाम *", "କୃଷକଙ୍କ ନାମ *"), value = name, onValueChange = onNameChange, placeholder = "Full Name")
        EnrollmentTextField(label = languageState.value.getT("Mobile Number *", "मोबाइल नंबर *", "ମୋବାଇଲ୍ ନମ୍ବର *"), value = phone, onValueChange = onPhoneChange, placeholder = "10-digit number", keyboardType = KeyboardType.Phone, prefix = "+91 ")
        EnrollmentTextField(label = languageState.value.getT("Village *", "गाँव *", "ଗ୍ରାମ *"), value = village, onValueChange = onVillageChange, placeholder = "Village Name")
        EnrollmentTextField(
            label = languageState.value.getT("GPS Location *", "जीपीएस स्थान *", "GPS ଅବସ୍ଥାନ *"),
            value = location, onValueChange = onLocationChange,
            placeholder = languageState.value.getT("Tap the icon to fetch GPS", "जीपीएस लाने के लिए आइकन दबाएं", "GPS ଆଣିବାକୁ ଆଇକନ୍ ଦବାନ୍ତୁ"),
            trailingIcon = Icons.Default.MyLocation,
            onTrailingIconClick = { fetchGps() }
        )
        EnrollmentTextField(label = languageState.value.getT("Aadhaar / Gov ID *", "आधार / सरकारी आईडी *", "ଆଧାର / ସରକାରୀ ID *"), value = aadhaar, onValueChange = onAadhaarChange, placeholder = "12-digit number", keyboardType = KeyboardType.Number)
    }
}

@Composable
fun EnrollmentGoatStep(breed: String, onBreedChange: (String) -> Unit, gender: String, onGenderChange: (String) -> Unit, age: String, onAgeChange: (String) -> Unit, ageUnit: String, onAgeUnitChange: (String) -> Unit, weight: String, onWeightChange: (String) -> Unit, color: String, onColorChange: (String) -> Unit, earTag: String, onTagChange: (String) -> Unit) {
    val languageState = LocalAppLanguage.current
    val scanLauncher = rememberLauncherForActivityResult(
        contract = com.journeyapps.barcodescanner.ScanContract()
    ) { result -> if (result.contents != null) onTagChange(result.contents) }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        EnrollmentDropdownField(
            label = languageState.value.getT("Breed *", "नस्ल *", "ପ୍ରଜାତି *"),
            selectedValue = breed,
            placeholder = languageState.value.getT("Select Breed", "नस्ल चुनें", "ପ୍ରଜାତି ବାଛନ୍ତୁ"),
            options = listOf("Black Bengal", "Jamunapari", "Sirohi", "Barbari", "Beetal", "Ganjam", "Osmanabadi", "Anjori"),
            onValueChange = onBreedChange
        )
        EnrollmentDropdownField(
            label = languageState.value.getT("Gender *", "लिंग *", "ଲିଙ୍ଗ *"),
            selectedValue = gender,
            placeholder = languageState.value.getT("Select Gender", "लिंग चुनें", "ଲିଙ୍ଗ ବାଛନ୍ତୁ"),
            options = listOf(
                languageState.value.getT("Female", "मादा", "ମାଈ"),
                languageState.value.getT("Male", "नर", "ଅଣ୍ଡିରା")
            ),
            onValueChange = onGenderChange
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Box(modifier = Modifier.weight(0.6f)) {
                EnrollmentTextField(label = languageState.value.getT("Age (Approx) *", "आयु (लगभग) *", "ବୟସ (ପ୍ରାୟ) *"), value = age, onValueChange = onAgeChange, placeholder = "12", keyboardType = KeyboardType.Number)
            }
            Box(modifier = Modifier.weight(0.4f)) {
                EnrollmentDropdownField(
                    label = languageState.value.getT("Unit *", "इकाई *", "ଏକକ *"),
                    selectedValue = ageUnit,
                    options = listOf(
                        languageState.value.getT("Months", "महीने", "ମାସ"),
                        languageState.value.getT("Years", "साल", "ବର୍ଷ")
                    ),
                    onValueChange = onAgeUnitChange
                )
            }
        }

        EnrollmentTextField(label = languageState.value.getT("Weight (Approx) *", "वजन (लगभग) *", "ଓଜନ (ପ୍ରୟ) *"), value = weight, onValueChange = onWeightChange, placeholder = "18", keyboardType = KeyboardType.Number, suffix = "KG")
        EnrollmentTextField(label = languageState.value.getT("Color / Marks *", "रंग / निशान *", "ରଙ୍ଗ / ଚିହ୍ନ *"), value = color, onValueChange = onColorChange, placeholder = "Black with White Spots")

        EnrollmentTextField(
            label = languageState.value.getT("Ear Tag Number *", "कान का टैग नंबर *", "କାନ ଟ୍ୟାଗ୍ ନମ୍ବର *"),
            value = earTag, onValueChange = onTagChange, placeholder = "e.g. ET-240801",
            trailingIcon = Icons.Default.QrCodeScanner,
            onTrailingIconClick = {
                val options = com.journeyapps.barcodescanner.ScanOptions()
                options.setDesiredBarcodeFormats(com.journeyapps.barcodescanner.ScanOptions.QR_CODE)
                options.setPrompt(languageState.value.getT("Scan Ear Tag QR Code", "कान के टैग का क्यूआर कोड स्कैन करें", "କାନ ଟ୍ୟାଗ୍ QR କୋଡ୍ ସ୍କାନ୍ କରନ୍ତୁ"))
                options.setBeepEnabled(true)
                options.setOrientationLocked(false)
                scanLauncher.launch(options)
            }
        )
        Text(
            languageState.value.getT("Ear tag must be unique", "कान का टैग अद्वितीय होना चाहिए", "କାନ ଟ୍ୟାଗ୍ ଅନନ୍ୟ ହେବା ଆବଶ୍ୟକ"),
            fontSize = 12.sp, color = Color.Gray
        )
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
    val languageState = LocalAppLanguage.current

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
            Toast.makeText(context, languageState.value.getT("Camera permission required", "कैमरा अनुमति आवश्यक है", "କ୍ୟାମେରା ଅନୁମତି ଆବଶ୍ୟକ"), Toast.LENGTH_SHORT).show()
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
            languageState.value.getT("Please upload 4 mandatory photos of the goat.", "कृपया बकरी की 4 अनिवार्य फोटो अपलोड करें।", "ଦୟାକରି ଛେଳିର ୪ଟି ବାଧ୍ୟତାମୂଳକ ଫଟୋ ଅପଲୋଡ୍ କରନ୍ତୁ |"),
            style = MaterialTheme.typography.bodyMedium, color = Color.Gray
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            PhotoCaptureBox(
                languageState.value.getT("Left Side", "बाईं ओर", "ବାମ ପାର୍ଶ୍ୱ"),
                uri = leftUri,
                modifier = Modifier.weight(1f)
            ) {
                launchCamera("left")
            }
            PhotoCaptureBox(
                languageState.value.getT("Right Side", "दाईं ओर", "ଡାହାଣ ପାର୍ଶ୍ୱ"),
                uri = rightUri,
                modifier = Modifier.weight(1f)
            ) {
                launchCamera("right")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            PhotoCaptureBox(
                languageState.value.getT("Front View", "सामने का दृश्य", "ସମ୍ମୁଖ ଦୃଶ୍ୟ"),
                uri = frontUri,
                modifier = Modifier.weight(1f)
            ) {
                launchCamera("front")
            }
            PhotoCaptureBox(
                languageState.value.getT("Ear Tag", "कान का टैग", "କାନ ଟ୍ୟାଗ୍"),
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
                    languageState.value.getT("Ensure clear photos in good lighting", "अच्छी रोशनी में स्पष्ट फोटो सुनिश्चित करें", "ଭଲ ଆଲୋକରେ ସ୍ପଷ୍ଟ ଫଟୋ ନିଶ୍ଚିତ କରନ୍ତୁ"),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun EnrollmentTaggingStep(earTag: String, onTagChange: (String) -> Unit) {
    val languageState = LocalAppLanguage.current
    val context = LocalContext.current
    
    val scanLauncher = rememberLauncherForActivityResult(
        contract = com.journeyapps.barcodescanner.ScanContract()
    ) { result ->
        if (result.contents != null) {
            onTagChange(result.contents)
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        EnrollmentTextField(label = languageState.value.getT("Ear Tag Number *", "कान का टैग नंबर *", "କାନ ଟ୍ୟାଗ୍ ନମ୍ବର *"), value = earTag, onValueChange = onTagChange, placeholder = "e.g. ET-240801")
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            languageState.value.getT("Scan QR Code (Optional)", "क्यूआर कोड स्कैन करें (वैकल्पिक)", "QR କୋଡ୍ ସ୍କାନ୍ କରନ୍ତୁ (ବୈକଳ୍ପିକ)"),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Surface(
            onClick = { 
                val options = com.journeyapps.barcodescanner.ScanOptions()
                options.setDesiredBarcodeFormats(com.journeyapps.barcodescanner.ScanOptions.QR_CODE)
                options.setPrompt(languageState.value.getT("Scan Ear Tag QR Code", "कान के टैग का क्यूआर कोड स्कैन करें", "କାନ ଟ୍ୟାଗ୍ QR କୋଡ୍ ସ୍କାନ୍ କରନ୍ତୁ"))
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
                    languageState.value.getT("Tap to scan", "स्कैन करने के लिए टैप करें", "ସ୍କାନ୍ କରିବାକୁ ଟ୍ୟାପ୍ କରନ୍ତୁ"), 
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
                    languageState.value.getT("Ear Tag Verified Successfully", "कान का टैग सफलतापूर्वक सत्यापित", "କାନ ଟ୍ୟାଗ୍ ସଫଳତାର ସହିତ ଯାଞ୍ଚ ହୋଇଛି"),
                    color = PrimaryGreen,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun EnrollmentVaccinationStep(
    pprGiven: Boolean, onPpr: (Boolean) -> Unit,
    etttGiven: Boolean, onEtt: (Boolean) -> Unit,
    fmdGiven: Boolean, onFmd: (Boolean) -> Unit,
    poxGiven: Boolean, onPox: (Boolean) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        VaccineStatusItem("PPR Vaccine", pprGiven) { onPpr(it) }
        VaccineStatusItem("ET + TT Vaccine", etttGiven) { onEtt(it) }
        VaccineStatusItem("FMD Vaccine", fmdGiven) { onFmd(it) }
        VaccineStatusItem("Goat Pox Vaccine", poxGiven) { onPox(it) }
    }
}

@Composable
fun EnrollmentPaymentStep(goatCount: Int = 1) {
    val languageState = LocalAppLanguage.current
    var selectedMethod by remember { mutableStateOf("Cash") }
    val total = 350 * goatCount.coerceAtLeast(1)

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
                Text(languageState.value.getT("Total Premium Amount", "कुल प्रीमियम राशि", "ମୋଟ ପ୍ରିମିୟମ ପରିମାଣ"), fontSize = 14.sp, color = Color.Gray)
                Text("₹ $total", style = MaterialTheme.typography.headlineLarge, color = PrimaryGreen, fontWeight = FontWeight.Bold)
                if (goatCount > 1) {
                    Text("$goatCount × ₹350", fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        Text(languageState.value.getT("Select Payment Method", "भुगतान विधि चुनें", "ଦେୟ ପଦ୍ଧତି ଚୟନ କରନ୍ତୁ"), fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            PaymentMethodChip("Cash", selectedMethod == "Cash", modifier = Modifier.weight(1f)) { selectedMethod = "Cash" }
            PaymentMethodChip("UPI", selectedMethod == "UPI", modifier = Modifier.weight(1f)) { selectedMethod = "UPI" }
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
        EnrollmentTextField(label = languageState.value.getT("Premium Amount (editable if needed)", "प्रीमियम राशि (यदि आवश्यक हो तो संपादन योग्य)", "ପ୍ରିମିୟମ ପରିମାଣ (ଆବଶ୍ୟକ ହେଲେ ସଂପାଦନ ଯୋଗ୍ୟ)"), value = "350", onValueChange = {}, prefix = "₹")
        Spacer(modifier = Modifier.height(16.dp))
        EnrollmentTextField(label = languageState.value.getT("Receipt Number", "रसीद संख्या", "ରସିଦ ନମ୍ବର"), value = "RCP-240801-001", onValueChange = {}, leadingIcon = Icons.Default.Receipt)
    }
}

@Composable
fun EnrollmentPolicyStep(farmer: String, tag: String, result: EnrollGoatResponse? = null) {
    val languageState = LocalAppLanguage.current
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier.size(64.dp).clip(CircleShape).background(Color(0xFFE8F5E9)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.VerifiedUser, null, tint = SuccessGreen, modifier = Modifier.size(32.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(languageState.value.getT("Policy Generated Successfully!", "पॉलिसी सफलतापूर्वक जेनरेट हुई!", "ନୀତି ସଫଳତାର ସହିତ ପ୍ରସ୍ତୁତ ହୋଇଛି!"), fontWeight = FontWeight.Bold, fontSize = 18.sp)
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f))
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                PolicyDetailRow(languageState.value.getT("Policy Number", "पॉलिसी नंबर", "ନୀତି ନମ୍ବର"), result?.policyNumber ?: "—")
                PolicyDetailRow(languageState.value.getT("Farmer Name", "किसान का नाम", "କୃଷକଙ୍କ ନାମ"), farmer.ifBlank { "—" })
                PolicyDetailRow(languageState.value.getT("Ear Tag", "कान का टैग", "କାନ ଟ୍ୟାଗ୍"), tag.ifBlank { "—" })
                PolicyDetailRow(languageState.value.getT("Premium Paid", "प्रीमियम भुगतान", "ଦିଆଯାଇଥିବା ପ୍ରିମିୟମ"), "₹ 350")
                PolicyDetailRow(languageState.value.getT("Validity", "वैधता", "ବୈଧତା"), "${result?.validFrom ?: "—"} - ${result?.validTo ?: "—"}")
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                PolicyDetailRow(languageState.value.getT("Sum Insured", "बीमा राशि", "ବୀମା ରାଶି"), result?.sumInsured?.let { "₹ ${it.toInt()}" } ?: "—", true)
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
            Text(languageState.value.getT("Download Certificate", "प्रमाण पत्र डाउनलोड करें", "ପ୍ରମାଣପତ୍ର ଡାଉନଲୋଡ୍ କରନ୍ତୁ"), color = PrimaryGreen, fontWeight = FontWeight.Bold)
        }
    }
}

/** Step 4: the running list of goats added during this enrollment. */
@Composable
fun EnrollmentGoatsAddedStep(
    goats: List<GoatDraft>,
    onAddAnother: () -> Unit,
    onEdit: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    onContinue: () -> Unit,
) {
    val languageState = LocalAppLanguage.current
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // Total counter
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(languageState.value.getT("Total Goats Added", "जोड़ी गई कुल बकरियां", "ମୋଟ ଯୋଡ଼ାଯାଇଥିବା ଛେଳି"), fontSize = 13.sp, color = Color.Gray)
                    Text("${goats.size}", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = PrimaryGreen)
                }
                Icon(painterResource(R.drawable.ic_ewe_custom), null, tint = PrimaryGreen, modifier = Modifier.size(40.dp))
            }
        }

        goats.forEachIndexed { index, goat ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.4f))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val photo = goat.photos.getOrNull(2) ?: goat.photos.firstOrNull()
                    Box(
                        modifier = Modifier.size(56.dp).clip(RoundedCornerShape(10.dp)).background(Color(0xFFF0F0F0)),
                        contentAlignment = Alignment.Center
                    ) {
                        if (photo != null) {
                            AsyncImage(model = photo, contentDescription = null, modifier = Modifier.fillMaxSize(), contentScale = androidx.compose.ui.layout.ContentScale.Crop)
                        } else {
                            Icon(painterResource(R.drawable.ic_ewe_custom), null, tint = Color.Gray, modifier = Modifier.size(28.dp))
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(goat.earTag, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)
                        Text("${goat.breed} • ${goat.genderLabel} • ${goat.ageLabel}", fontSize = 12.sp, color = Color.Gray)
                        Text("${goat.weightLabel} • ${goat.color}", fontSize = 12.sp, color = Color.Gray)
                    }
                    IconButton(onClick = { onEdit(index) }) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit", tint = PrimaryGreen, modifier = Modifier.size(20.dp))
                    }
                    IconButton(onClick = { onDelete(index) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red, modifier = Modifier.size(20.dp))
                    }
                }
            }
        }

        OutlinedButton(
            onClick = onAddAnother,
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, PrimaryGreen)
        ) {
            Icon(Icons.Default.Add, null, tint = PrimaryGreen)
            Spacer(modifier = Modifier.width(8.dp))
            Text(languageState.value.getT("Add Another Goat", "एक और बकरी जोड़ें", "ଆଉ ଏକ ଛେଳି ଯୋଡ଼ନ୍ତୁ"), color = PrimaryGreen, fontWeight = FontWeight.Bold)
        }

        Button(
            onClick = onContinue,
            enabled = goats.isNotEmpty(),
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen, disabledContainerColor = PrimaryGreen.copy(alpha = 0.5f))
        ) {
            Text(languageState.value.getT("Continue to Vaccination", "टीकाकरण के लिए जारी रखें", "ଟୀକାକରଣକୁ ଜାରି ରଖନ୍ତୁ"), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

/** Step 7: single consolidated policy summary for the whole batch (matches the mockup). */
@Composable
fun EnrollmentPoliciesStep(farmer: String, results: List<EnrollGoatResponse>, goats: List<GoatDraft>, onFinish: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val context = LocalContext.current
    val enrollVm: EnrollmentViewModel = androidx.hilt.navigation.compose.hiltViewModel()
    val certState by enrollVm.certificate.collectAsState()

    val policyNumber = results.firstOrNull()?.policyNumber ?: "—"
    val receiptNumber = results.firstOrNull()?.policyNumber?.replaceFirst(Regex("^[A-Za-z]+"), "RCP") ?: "—"
    val totalPremium = 350 * results.size.coerceAtLeast(1)

    LaunchedEffect(certState) {
        when (val s = certState) {
            is SubmitState.Success -> {
                s.message?.let { path ->
                    val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", File(path))
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        setDataAndType(uri, "application/pdf")
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }
                    try { context.startActivity(intent) }
                    catch (e: Exception) {
                        Toast.makeText(context, languageState.value.getT("No PDF viewer installed", "कोई पीडीएफ व्यूअर नहीं मिला", "କୌଣସି PDF ଭ୍ୟୁଅର୍ ନାହିଁ"), Toast.LENGTH_LONG).show()
                    }
                }
                enrollVm.resetCertificate()
            }
            is SubmitState.Error -> {
                Toast.makeText(context, s.message, Toast.LENGTH_LONG).show()
                enrollVm.resetCertificate()
            }
            else -> {}
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier.size(72.dp).clip(CircleShape).background(SuccessGreen),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Check, null, tint = Color.White, modifier = Modifier.size(40.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            languageState.value.getT("Policy Generated Successfully!", "पॉलिसी सफलतापूर्वक जेनरेट हुई!", "ନୀତି ସଫଳତାର ସହିତ ପ୍ରସ୍ତୁତ ହୋଇଛି!"),
            fontWeight = FontWeight.Bold, fontSize = 18.sp, color = PrimaryGreen, textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            languageState.value.getT(
                "One policy has been created for ${results.size} goats.",
                "${results.size} बकरियों के लिए एक पॉलिसी बनाई गई है।",
                "${results.size} ଛେଳି ପାଇଁ ଗୋଟିଏ ନୀତି ପ୍ରସ୍ତୁତ ହୋଇଛି।"
            ),
            fontSize = 13.sp, color = Color.Gray, textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                PolicyDetailRow(languageState.value.getT("Policy Number", "पॉलिसी नंबर", "ନୀତି ନମ୍ବର"), policyNumber)
                PolicyDetailRow(languageState.value.getT("Receipt Number", "रसीद संख्या", "ରସିଦ ନମ୍ବର"), receiptNumber)
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                PolicyDetailRow(languageState.value.getT("Total Premium", "कुल प्रीमियम", "ମୋଟ ପ୍ରିମିୟମ"), "₹$totalPremium.00", true)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(
            onClick = { enrollVm.downloadBatchCertificate(goats.map { it.earTag }, context.cacheDir) },
            enabled = certState !is SubmitState.Submitting && goats.isNotEmpty(),
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, PrimaryGreen)
        ) {
            if (certState is SubmitState.Submitting) {
                Text(languageState.value.getT("Preparing…", "तैयार हो रहा है…", "ପ୍ରସ୍ତୁତ ହେଉଛି…"), color = PrimaryGreen, fontWeight = FontWeight.Bold)
            } else {
                Icon(Icons.Default.FileDownload, null, tint = PrimaryGreen)
                Spacer(modifier = Modifier.width(8.dp))
                Text(languageState.value.getT("View Policy", "पॉलिसी देखें", "ନୀତି ଦେଖନ୍ତୁ"), color = PrimaryGreen, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onFinish,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
        ) {
            Text(languageState.value.getT("Finish Enrollment", "नामांकन पूरा करें", "ପଞ୍ଜିକରଣ ଶେଷ କରନ୍ତୁ"), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

// --- ENROLLMENT HELPERS ---

@Composable
fun EnrollmentTextField(label: String, value: String, onValueChange: (String) -> Unit, placeholder: String = "", keyboardType: KeyboardType = KeyboardType.Text, prefix: String? = null, suffix: String? = null, leadingIcon: ImageVector? = null, trailingIcon: ImageVector? = null, onTrailingIconClick: (() -> Unit)? = null, readOnly: Boolean = false, borderColor: Color = PrimaryGreen, isPassword: Boolean = false) {
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
                visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
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
fun EnrollmentDropdownField(label: String, selectedValue: String, options: List<String>, onValueChange: (String) -> Unit, borderColor: Color = PrimaryGreen, placeholder: String? = null) {
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
                // Shown only while nothing is selected, so an empty field reads as a
                // prompt rather than as a value.
                placeholder = placeholder?.let { { Text(it, color = Color.Gray) } },
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
    val languageState = LocalAppLanguage.current
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
                    text = if (isGiven) languageState.value.getT("Given", "दिया गया", "ଦିଆଯାଇଛି") 
                           else languageState.value.getT("Pending", "लंबित", "ବାକି ଅଛି"),
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
    if (showNotifications) NotificationSheet(userRole = UserRole.FARMER, themeColor = PrimaryBlue) { showNotifications = false }
    val languageState = LocalAppLanguage.current
    val savedName by sessionManager.userName.collectAsState(initial = null)
    val profileVm: ProfileViewModel = hiltViewModel()
    val dbProfile by profileVm.profile.collectAsState()
    val userName = dbProfile?.fullName ?: savedName ?: ""

    val farmerVm: FarmerHomeViewModel = hiltViewModel()
    val policiesState by farmerVm.policies.collectAsState()
    val schedule by farmerVm.schedule.collectAsState()
    val policies = (policiesState as? UiState.Success)?.data?.policies ?: emptyList()

    ResponsiveLayout(
        compact = {
            Scaffold(
                modifier = Modifier.fillMaxSize().navigationBarsPadding(),
                bottomBar = { FarmerBottomBar(navController) },
                contentWindowInsets = WindowInsets(0, 0, 0, 0)
            ) { padding ->
                FarmerContent(padding, navController, userName, policies, schedule) { showNotifications = true }
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
                        label = { Text(languageState.value.getT("Home", "होम", "ମୁଖ୍ୟ ପୃଷ୍ଠା")) },
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
                        label = { Text(languageState.value.getT("My Goats", "मेरी बकरियां", "ମୋର ଛେଳି")) },
                        colors = NavigationRailItemDefaults.colors(
                            selectedIconColor = PrimaryBlue,
                            selectedTextColor = PrimaryBlue,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
                FarmerContent(PaddingValues(0.dp), navController, userName, policies, schedule) { showNotifications = true }
            }
        }
    )
}

@Composable
fun FarmerContent(padding: PaddingValues, navController: NavHostController, userName: String, policies: List<PolicyOut>, schedule: List<VaccinationScheduleItem>, onNotificationClick: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val context = LocalContext.current
    val activeCount = policies.count { it.status == "active" }
    val notifVm: NotificationsViewModel = androidx.hilt.navigation.compose.hiltViewModel()
    val unread by notifVm.unread.collectAsState()
    LaunchedEffect(Unit) { notifVm.refreshUnread() }
    
    // Blue Theme Palette for Farmer
    val lightBlue = Color(0xFFE3F2FD)
    val medBlue = Color(0xFF64B5F6)
    val deepBlue = Color(0xFF1976D2)
    val skyBlue = Color(0xFFB3E5FC)
    val royalBlue = Color(0xFF0D47A1)


    Column(
        modifier = Modifier
            .padding(bottom = padding.calculateBottomPadding())
            .fillMaxSize()
            .background(Color(0xFFF8F9F5)) // Reverted to off-white background
    ) {
        FarmerHeader(
            navController,
            userName,
            languageState.value.getT("Farmer", "किसान", "କୃଷକ"),
            onNotificationClick,
            hasNotifications = unread > 0,
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
                        languageState.value.getT("My Insurance Summary", "मेरी बीमा सारांश", "ମୋର ବୀମା ସାରାଂଶ"),
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
                    0 -> StatCard(languageState.value.getT("Active Policies", "सक्रिय नीतियां", "ସକ୍ରିୟ ନୀତି"), "%02d".format(activeCount), Icons.AutoMirrored.Filled.Assignment, royalBlue, skyBlue)
                    1 -> StatCard(languageState.value.getT("Total Goats", "कुल बकरियां", "ମୋଟ ଛେଳି"), "%02d".format(policies.size), painterResource(R.drawable.ic_ewe_custom), PrimaryGreen, CardLightGreen)
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
                            languageState.value.getT("My Policies (${policies.size})", "मेरी नीतियां (${policies.size})", "ମୋର ନୀତିଗୁଡ଼ିକ (${policies.size})"),
                            style = MaterialTheme.typography.titleMedium,
                            color = royalBlue,
                            fontWeight = FontWeight.Bold
                        )
                        TextButton(onClick = { navController.navigate("goat_list") }) {
                            Text(languageState.value.getT("View All", "सभी देखें", "ସବୁ ଦେଖନ୍ତୁ"), color = deepBlue)
                        }
                    }
                }
            }

            // Policy Card — only when the farmer actually has a policy.
            if (policies.isNotEmpty()) {
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
                            val p0 = policies.firstOrNull()
                            Column {
                                Text(p0?.earTagNumber ?: "—", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)
                                Text(p0?.breed ?: "—", color = Color.Gray, fontSize = 12.sp)
                                Spacer(modifier = Modifier.height(2.dp))
                                val isActive = (p0?.status ?: "active") == "active"
                                Text(
                                    if (isActive) languageState.value.getT("Policy Active", "पॉलिसी सक्रिय", "ନୀତି ସକ୍ରିୟ") else languageState.value.getT("Policy ${p0?.status ?: ""}", "पॉलिसी ${p0?.status ?: ""}", "ନୀତି ${p0?.status ?: ""}"),
                                    color = if (isActive) SuccessGreen else Color.Red, fontWeight = FontWeight.Bold, fontSize = 13.sp
                                )
                                Text(languageState.value.getT("Valid till ${p0?.validTo ?: "—"}", "${p0?.validTo ?: "—"} तक मान्य", "${p0?.validTo ?: "—"} ପର୍ଯ୍ୟନ୍ତ ବୈଧ"), fontSize = 11.sp, color = Color.Gray)
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
                                Text(languageState.value.getT("Report Death", "मृत्यु की सूचना", "ମୃତ୍ୟୁ ରିପୋର୍ଟ"), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }
                            Button(
                                onClick = { policies.firstOrNull()?.let { navController.navigate("goat_details/${it.policyNumber}") } },
                                modifier = Modifier.weight(1f).height(40.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen),
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(languageState.value.getT("View Policy", "नीति देखें", "ନୀତି ଦେଖନ୍ତୁ"), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
            } else {
                item(span = { GridItemSpan(gridColumns) }) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(painterResource(R.drawable.ic_ewe_custom), null, tint = Color.LightGray, modifier = Modifier.size(40.dp))
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                languageState.value.getT("No policies yet", "अभी तक कोई नीति नहीं", "ଏପର୍ଯ୍ୟନ୍ତ କୌଣସି ନୀତି ନାହିଁ"),
                                color = Color.Gray, fontSize = 14.sp, fontWeight = FontWeight.Medium
                            )
                            Text(
                                languageState.value.getT("Your enrolled goats will appear here.", "आपकी नामांकित बकरियां यहां दिखाई देंगी।", "ଆପଣଙ୍କ ପଞ୍ଜିକୃତ ଛେଳି ଏଠାରେ ଦେଖାଯିବ।"),
                                color = Color.LightGray, fontSize = 12.sp, textAlign = TextAlign.Center
                            )
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
                    text = languageState.value.getT("Welcome,", "स्वागत है,", "ସ୍ଵାଗତ,"),
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
    val languageState = LocalAppLanguage.current
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
                    text = languageState.value.getT("Hello,", "नमस्ते,", "ନମସ୍କାର,"),
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
    val languageState = LocalAppLanguage.current
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
            label = { Text(languageState.value.getT("Home", "होम", "ମୁଖ୍ୟ ପୃଷ୍ଠା"), fontSize = 9.sp) },
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
            label = { Text(languageState.value.getT("Goats", "बकरियां", "ଛେଳି"), fontSize = 9.sp) },
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
            label = { Text(languageState.value.getT("Vaccines", "टीकाकरण", "ଟୀକା"), fontSize = 9.sp) },
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
            label = { Text(languageState.value.getT("Claims", "दावे", "ଦାବି"), fontSize = 9.sp) },
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
    val languageState = LocalAppLanguage.current
    val context = LocalContext.current
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
            label = { Text(languageState.value.getT("Home", "होम", "ମୁଖ୍ୟ ପୃଷ୍ଠା"), fontSize = 9.sp) },
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
            label = { Text(languageState.value.getT("My Goats", "मेरी बकरियां", "ମୋର ଛେଳି"), fontSize = 9.sp) },
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
            label = { Text(languageState.value.getT("Claims", "दावे", "ଦାବି"), fontSize = 9.sp) },
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
            label = { Text(languageState.value.getT("Help", "सहायता", "ସାହାଯ୍ୟ"), fontSize = 9.sp) },
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
        LoginScreen(onLoginSuccess = {}, onNavigateToSignUp = {})
    }
}

// --- NOTIFICATION COMPONENTS ---

data class AppNotification(val title: String, val message: String, val time: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationSheet(userRole: UserRole?, themeColor: Color = PrimaryGreen, onOpenMortality: (() -> Unit)? = null, onDismiss: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val vm: NotificationsViewModel = androidx.hilt.navigation.compose.hiltViewModel()
    val activity by vm.items.collectAsState()
    val loading by vm.loading.collectAsState()
    // Load and mark everything read as soon as the sheet opens.
    LaunchedEffect(Unit) { vm.load(); vm.markAllRead() }

    fun toNotification(n: NotificationOut): AppNotification {
        val time = n.createdAt.take(16).replace("T", " ")
        return AppNotification(n.title, n.body ?: "", time)
    }

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
                languageState.value.getT("Notifications", "सूचनाएं", "ବିଜ୍ଞପ୍ତି"),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            when {
                loading -> Box(modifier = Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = themeColor)
                }
                activity.isEmpty() -> Text(
                    languageState.value.getT("No recent activity", "कोई हालिया गतिविधि नहीं", "କୌଣସି ସାମ୍ପ୍ରତିକ କାର୍ଯ୍ୟକଳାପ ନାହିଁ"),
                    color = Color.Gray, fontSize = 14.sp, modifier = Modifier.padding(vertical = 24.dp)
                )
                else -> LazyColumn(
                    modifier = Modifier.fillMaxWidth().heightIn(max = 480.dp),
                    contentPadding = PaddingValues(bottom = 32.dp)
                ) {
                    items(activity) { item ->
                        val tappable = item.type == "mortality" && onOpenMortality != null
                        Box(modifier = if (tappable) Modifier.clickable { onDismiss(); onOpenMortality!!() } else Modifier) {
                            NotificationItem(toNotification(item), themeColor)
                        }
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 12.dp),
                            color = Color.LightGray.copy(alpha = 0.4f)
                        )
                    }
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
    val savedMobile by sessionManager.mobileNumber.collectAsState(initial = null)

    // Canonical account details straight from the backend DB; session values
    // are the fallback while the call is in flight or offline.
    val profileVm: ProfileViewModel = hiltViewModel()
    val dbProfile by profileVm.profile.collectAsState()
    val saveState by profileVm.save.collectAsState()
    val isSavingProfile = saveState is SubmitState.Submitting
    LaunchedEffect(saveState) {
        when (val s = saveState) {
            is SubmitState.Success -> {
                Toast.makeText(context, languageState.value.getT("Profile updated", "प्रोफ़ाइल अपडेट किया गया", "ପ୍ରୋଫାଇଲ୍ ଅପଡେଟ୍ ହୋଇଛି"), Toast.LENGTH_SHORT).show()
                profileVm.resetSave()
            }
            is SubmitState.Error -> {
                Toast.makeText(context, s.message, Toast.LENGTH_LONG).show()
                profileVm.resetSave()
            }
            else -> {}
        }
    }

    val isFarmer = userRole == UserRole.FARMER
    val isCoordinator = userRole == UserRole.COORDINATOR
    val themeColor = when(userRole) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }
    val userName = dbProfile?.fullName ?: savedName ?: ""
    val userMobile = dbProfile?.mobileNumber ?: savedMobile
    val userVillage = dbProfile?.village ?: savedVillage

    val effectiveRole = when (dbProfile?.role) {
        "fr" -> UserRole.FARMER
        "co" -> UserRole.COORDINATOR
        "sd" -> UserRole.SURAKSHA_DIDI
        else -> userRole
    }
    val roleLabel = when(effectiveRole) {
        UserRole.FARMER -> languageState.value.getT("Farmer", "किसान", "କୃଷକ")
        UserRole.COORDINATOR -> languageState.value.getT("Coordinator", "समन्वयक", "ସମନ୍ଵୟକାରୀ")
        else -> languageState.value.getT("Suraksha Didi", "सुरक्षा दीदी", "ସୁରକ୍ଷା ଦିଦି")
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
                // Upload the new photo and persist it on the account.
                profileVm.updateProfile(name = null, village = null, photoUri = internalUri)
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
                // Upload the new photo and persist it on the account.
                profileVm.updateProfile(name = null, village = null, photoUri = internalUri)
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
    var showEditProfile by remember { mutableStateOf(false) }

    if (showEditProfile) {
        var editName by remember { mutableStateOf(userName) }
        var editVillage by remember { mutableStateOf(userVillage ?: "") }
        AlertDialog(
            onDismissRequest = { showEditProfile = false },
            title = { Text(languageState.value.getT("Edit Profile", "प्रोफ़ाइल संपादित करें", "ପ୍ରୋଫାଇଲ୍ ସଂପାଦନ କରନ୍ତୁ"), fontWeight = FontWeight.Bold, color = Color.Black) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedTextField(
                        value = editName,
                        onValueChange = { if (it.all { c -> c.isLetter() || c.isWhitespace() }) editName = it },
                        label = { Text(languageState.value.getT("Full Name", "पूरा नाम", "ପୁରା ନାମ")) },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.Black, unfocusedTextColor = Color.Black, focusedBorderColor = themeColor)
                    )
                    OutlinedTextField(
                        value = editVillage,
                        onValueChange = { editVillage = it },
                        label = { Text(languageState.value.getT("Location", "स्थान", "ଅବସ୍ଥାନ")) },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.Black, unfocusedTextColor = Color.Black, focusedBorderColor = themeColor)
                    )
                }
            },
            confirmButton = {
                TextButton(
                    enabled = editName.isNotBlank() && !isSavingProfile,
                    onClick = {
                        profileVm.updateProfile(name = editName, village = editVillage, photoUri = null)
                        showEditProfile = false
                    }
                ) { Text(languageState.value.getT("Save", "सहेजें", "ସେଭ୍"), fontWeight = FontWeight.Bold, color = themeColor) }
            },
            dismissButton = {
                TextButton(onClick = { showEditProfile = false }) {
                    Text(languageState.value.getT("Cancel", "रद्द करें", "ବାତିଲ୍"), color = Color.Gray)
                }
            }
        )
    }

    if (showPhotoOptions) {
        AlertDialog(
            onDismissRequest = { showPhotoOptions = false },
            title = { Text(languageState.value.getT("Update Profile Photo", "प्रोफ़ाइल फ़ोटो अपडेट करें", "ପ୍ରୋଫାଇଲ୍ ଫଟୋ ଅପଡେଟ୍ କରନ୍ତୁ"), fontWeight = FontWeight.Bold, color = Color.Black) },
            text = { Text(languageState.value.getT("Choose a source", "एक स्रोत चुनें", "ଏକ ଉତ୍ସ ବାଛନ୍ତୁ"), color = Color.Black) },
            confirmButton = {
                TextButton(onClick = { 
                    showPhotoOptions = false
                    launchCamera()
                }) {
                    Text(languageState.value.getT("Camera", "कैमरा", "କ୍ୟାମେରା"), fontWeight = FontWeight.Bold, color = themeColor)
                }
            },
            dismissButton = {
                TextButton(onClick = { 
                    showPhotoOptions = false
                    imagePickerLauncher.launch("image/*")
                }) {
                    Text(languageState.value.getT("Gallery", "गैलरी", "ଗ୍ୟାଲେରୀ"), fontWeight = FontWeight.Bold, color = themeColor)
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
                        languageState.value.getT("Profile Settings", "प्रोफ़ाइल सेटिंग्स", "ପ୍ରୋଫାଇଲ୍ ସେଟିଙ୍ଗ୍ସ"),
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        userName,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(onClick = { showEditProfile = true }, modifier = Modifier.size(28.dp)) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit profile", tint = Color.White, modifier = Modifier.size(18.dp))
                    }
                }
                Text(
                    userMobile?.let { "+91 $it" } ?: "",
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
            // Info Cards - values come from the backend profile (session cache as fallback)
            ProfileInfoSection(languageState.value.getT("Account Details", "खाता विवरण", "ଖାତା ବିବରଣୀ"), themeColor) {
                ProfileInfoItem(
                    languageState.value.getT("Full Name", "पूरा नाम", "ପୁରା ନାମ"),
                    userName
                )
                ProfileInfoItem(
                    languageState.value.getT("Mobile Number", "मोबाइल नंबर", "ମୋବାଇଲ୍ ନମ୍ବର"),
                    userMobile ?: "-"
                )
                ProfileInfoItem(
                    languageState.value.getT("Role", "भूमिका", "ଭୂମିକା"),
                    roleLabel
                )
                ProfileInfoItem(
                    languageState.value.getT("Location", "स्थान", "ଅବସ୍ଥାନ"),
                    userVillage ?: "-"
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            ProfileInfoSection(languageState.value.getT("App Settings", "ऐप सेटिंग्स", "ଆପ୍ ସେଟିଙ୍ଗ୍ସ"), themeColor) {
                val notificationsEnabled = LocalNotificationsEnabled.current
                SettingsToggleItem(
                    label = languageState.value.getT("Enable Notifications", "सूचनाएं सक्षम करें", "ବିଜ୍ଞପ୍ତି ସକ୍ଷମ କରନ୍ତୁ"),
                    checked = notificationsEnabled.value,
                    onCheckedChange = { notificationsEnabled.value = it },
                    themeColor = themeColor
                )
                Box {
                    SettingsClickItem(languageState.value.getT("Language", "भाषा", "ଭାଷା"), getLanguageName(languageState.value)) {
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
            
            ProfileInfoSection(languageState.value.getT("Support & Legal", "समर्थन और कानूनी", "ସମର୍ଥନ ଏବଂ ଆଇନଗତ"), themeColor) {
                SettingsClickItem(languageState.value.getT("Help & Support", "सहायता और समर्थन", "ସାହାଯ୍ୟ ଏବଂ ସମର୍ଥନ"), "") {
                    navController.navigate("help_support")
                }
                SettingsClickItem(languageState.value.getT("Privacy Policy", "गोपनीयता नीति", "ଗୋପନୀୟତା ନୀତି"), "") {
                    navController.navigate("privacy_policy")
                }
                SettingsClickItem(languageState.value.getT("Terms of Service", "सेवा की शर्तें", "ସେବା ସର୍ତ୍ତାବଳୀ"), "") {
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
                Text(languageState.value.getT("Logout", "लॉग आउट", "ଲଗ୍ ଆଉଟ୍"), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            Text(languageState.value.getT("Version 1.0.0", "संस्करण 1.0.0", "ସଂସ୍କରଣ ୧.୦.୦"), color = Color.Gray, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpSupportScreen(navController: NavHostController, userRole: UserRole?, onReportIssue: () -> Unit, onFaqs: () -> Unit, onContactSupport: () -> Unit, onBack: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val context = LocalContext.current
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
                        languageState.value.getT("Help & Support", "सहायता और समर्थन", "ସାହାଯ୍ୟ ଏବଂ ସମର୍ଥନ"), 
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
                title = languageState.value.getT("FAQs", "सामान्य प्रश्न", "FAQs"),
                subtitle = languageState.value.getT("Frequently asked questions", "अक्सर पूछे जाने वाले प्रश्न", "ବାରମ୍ବାର ପଚରାଯାଉଥିବା ପ୍ରଶ୍ନ"),
                icon = Icons.AutoMirrored.Filled.Help,
                themeColor = themeColor,
                onClick = onFaqs
            )
            HelpItemCard(
                title = languageState.value.getT("Contact Support", "सहायता से संपर्क करें", "ସହାୟତା ସହିତ ଯୋଗାଯୋଗ କରନ୍ତୁ"),
                subtitle = languageState.value.getT("Chat with us on WhatsApp", "व्हाट्सएप पर हमसे चैट करें", "ହ୍ୱାଟସ୍ଆପରେ ଆମ ସହ ଚାଟ୍ କରନ୍ତୁ"),
                icon = Icons.AutoMirrored.Filled.Chat,
                themeColor = themeColor,
                onClick = { context.chatOnWhatsApp() }
            )
            HelpItemCard(
                title = languageState.value.getT("Call Support", "कॉल सहायता", "କଲ୍ ସହାୟତା"),
                subtitle = SupportContact.DISPLAY,
                icon = Icons.Default.Phone,
                themeColor = themeColor,
                onClick = { context.callSupport() }
            )
            HelpItemCard(
                title = languageState.value.getT("Report an Issue", "एक समस्या की रिपोर्ट करें", "ଏକ ସମସ୍ୟା ରିପୋର୍ଟ କରନ୍ତୁ"),
                subtitle = languageState.value.getT("Let us know your problem", "हमें अपनी समस्या बताएं", "ଆମକୁ ଆପଣଙ୍କର ସମସ୍ୟା ଜଣାନ୍ତୁ"),
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
                            languageState.value.getT("Report an Issue", "एक समस्या की रिपोर्ट करें", "ଏକ ସମସ୍ୟା ରିପୋର୍ଟ କରନ୍ତୁ"),
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
                        text = languageState.value.getT("Something not working?", "कुछ काम नहीं कर रहा?", "କିଛି କାମ କରୁନାହିଁ କି?"),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = languageState.value.getT("Let us know about the issue you're facing.", "हमें उस समस्या के बारे में बताएं जिसका आप सामना कर रहे हैं।", "ଆପଣ କେଉଁ ସମସ୍ୟାର ସମ୍ମୁଖୀନ ହେଉଛନ୍ତି ଆମକୁ ଜଣାନ୍ତୁ |"),
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
                        title = languageState.value.getT("Report a Bug", "बग की रिपोर्ट करें", "ଏକ ବଗ୍ ରିପୋର୍ଟ କରନ୍ତୁ"),
                        subtitle = languageState.value.getT("Let us know if something isn't working as expected.", "हमें बताएं कि क्या कुछ उम्मीद के मुताबिक काम नहीं कर रहा है।", "ଯଦି କିଛି ଆଶା ଅନୁଯାୟୀ କାମ କରୁନାହିଁ ତେବେ ଆମକୁ ଜଣାନ୍ତୁ |"),
                        icon = Icons.Default.BugReport,
                        iconColor = Color(0xFFE57373),
                        onClick = { 
                            selectedType = "Bug"
                            showForm = true 
                        }
                    )
                    ReportOptionCard(
                        title = languageState.value.getT("Feature Request", "सुविधा का अनुरोध", "ଫିଚର୍ ଅନୁରୋଧ"),
                        subtitle = languageState.value.getT("Suggest a new feature or improvement.", "एक नई सुविधा या सुधार का सुझाव दें।", "ଏକ ନୂତନ ଫିଚର୍ କିମ୍ବା ଉନ୍ନତି ପାଇଁ ପରାମର୍ଶ ଦିଅନ୍ତୁ |"),
                        icon = Icons.Default.Lightbulb,
                        iconColor = Color(0xFF64B5F6),
                        onClick = {
                            selectedType = "Feature"
                            showForm = true
                        }
                    )
                    ReportOptionCard(
                        title = languageState.value.getT("General Feedback", "सामान्य प्रतिक्रिया", "ସାଧାରଣ ମତାମତ"),
                        subtitle = languageState.value.getT("Share your feedback or suggestions.", "अपनी प्रतिक्रिया या सुझाव साझा करें।", "ଆପଣଙ୍କର ମତାମତ କିମ୍ବା ପରାମର୍ଶ ସେୟାର କରନ୍ତୁ |"),
                        icon = Icons.Default.Feedback,
                        iconColor = Color(0xFF81C784),
                        onClick = {
                            selectedType = "Feedback"
                            showForm = true
                        }
                    )
                    ReportOptionCard(
                        title = languageState.value.getT("View My Reports", "मेरी रिपोर्ट देखें", "ମୋର ରିପୋର୍ଟ ଦେଖନ୍ତୁ"),
                        subtitle = languageState.value.getT("Track the status of your reported issues.", "अपने रिपोर्ट किए गए मुद्दों की स्थिति को ट्रैक करें।", "ଆପଣଙ୍କର ରିପୋର୍ଟ ହୋଇଥିବା ସମସ୍ୟାର ସ୍ଥିତି ଟ୍ରାକ୍ କରନ୍ତୁ |"),
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
                title = { Text(languageState.value.getT("Report an Issue", "एक समस्या की रिपोर्ट करें", "ଏକ ସମସ୍ୟା ରିପୋର୍ଟ କରନ୍ତୁ"), fontWeight = FontWeight.Bold, color = Color.White) },
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
                Text(languageState.value.getT("Submit Issue", "समस्या सबमिट करें", "ସମସ୍ୟା ଦାଖଲ କରନ୍ତୁ"), fontWeight = FontWeight.Bold, fontSize = 16.sp)
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
                Text(languageState.value.getT("Issue Type", "समस्या का प्रकार", "ସମସ୍ୟାର ପ୍ରକାର"), fontWeight = FontWeight.Bold, color = Color.Black)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    IssueTypeChip("Bug", selectedType == "Bug", Color(0xFFD32F2F), Icons.Default.BugReport, modifier = Modifier.weight(1f)) { selectedType = "Bug" }
                    IssueTypeChip("Feature", selectedType == "Feature", PrimaryBlue, Icons.Default.Lightbulb, modifier = Modifier.weight(1f)) { selectedType = "Feature" }
                    IssueTypeChip("Feedback", selectedType == "Feedback", SuccessGreen, Icons.Default.Feedback, modifier = Modifier.weight(1f)) { selectedType = "Feedback" }
                }
            }

            // Category
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(languageState.value.getT("Category", "श्रेणी", "ବର୍ଗ"), fontWeight = FontWeight.Bold, color = Color.Black)
                EnrollmentDropdownField(
                    label = "",
                    selectedValue = category.ifEmpty { languageState.value.getT("Select category", "श्रेणी चुनें", "ବର୍ଗ ଚୟନ କରନ୍ତୁ") },
                    options = listOf("App Crash", "Login Issue", "Payment Failed", "Missing Data", "Other"),
                    onValueChange = { category = it },
                    borderColor = Color.LightGray.copy(alpha = 0.5f)
                )
            }

            // Issue Title
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(languageState.value.getT("Issue Title *", "समस्या का शीर्षक *", "ସମସ୍ୟାର ଶୀର୍ଷକ *"), fontWeight = FontWeight.Bold, color = Color.Black)
                OutlinedTextField(
                    value = title,
                    onValueChange = { if (it.length <= 100) title = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(languageState.value.getT("Briefly describe the issue", "संक्षेप में समस्या का वर्णन करें", "ସମସ୍ୟାର ସଂକ୍ଷିପ୍ତ ବର୍ଣ୍ଣନା କରନ୍ତୁ"), color = Color.Gray) },
                    shape = RoundedCornerShape(12.dp),
                    supportingText = { Text("${title.length}/100", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End) },
                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.Black, unfocusedTextColor = Color.Black, unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f))
                )
            }

            // Describe the Issue
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(languageState.value.getT("Describe the Issue *", "समस्या का वर्णन करें *", "ସମସ୍ୟାର ବର୍ଣ୍ଣନା କରନ୍ତୁ *"), fontWeight = FontWeight.Bold, color = Color.Black)
                OutlinedTextField(
                    value = description,
                    onValueChange = { if (it.length <= 1000) description = it },
                    modifier = Modifier.fillMaxWidth().height(150.dp),
                    placeholder = { Text(languageState.value.getT("Please provide steps, what happened, and what you expected.", "कृपया चरण प्रदान करें, क्या हुआ, और आपने क्या अपेक्षा की थी।", "ଦୟାକରି ପଦକ୍ଷେପଗୁଡିକ ପ୍ରଦାନ କରନ୍ତୁ, କ’ଣ ଘଟିଲା, ଏବଂ ଆପଣ କ’ଣ ଆଶା କରିଥିଲେ |"), color = Color.Gray) },
                    shape = RoundedCornerShape(12.dp),
                    supportingText = { Text("${description.length}/1000", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End) },
                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.Black, unfocusedTextColor = Color.Black, unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f))
                )
            }

            // Attachment
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(languageState.value.getT("Attach Screenshot / Video (Optional)", "स्क्रीनशॉट / वीडियो संलग्न करें (वैकल्पिक)", "ସ୍କ୍ରିନସଟ୍ / ଭିଡିଓ ସଂଲଗ୍ନ କରନ୍ତୁ (ବୈକଳ୍ପିକ)"), fontWeight = FontWeight.Bold, color = Color.Black)
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
                Text(languageState.value.getT("Tap to upload", "अपलोड करने के लिए टैप करें", "ଅପଲୋଡ୍ କରିବାକୁ ଟ୍ୟାପ୍ କରନ୍ତୁ"), fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)
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

    // ---- real data ----
    var isLoading by remember { mutableStateOf(true) }
    var errorMsg by remember { mutableStateOf<String?>(null) }
    var goats by remember { mutableStateOf<List<Triple<String, String, String>>>(emptyList()) }
    var statusByTag by remember { mutableStateOf<Map<String, String>>(emptyMap()) }
    var clickIdByTag by remember { mutableStateOf<Map<String, String>>(emptyMap()) }
    var counts by remember { mutableStateOf(mapOf("all" to 0, "active" to 0, "expired" to 0, "claimed" to 0)) }

    if (isFarmer) {
        val farmerVm: FarmerHomeViewModel = hiltViewModel()
        val policiesState by farmerVm.policies.collectAsState()
        LaunchedEffect(policiesState) {
            when (val s = policiesState) {
                is UiState.Loading -> { isLoading = true; errorMsg = null }
                is UiState.Error -> { isLoading = false; errorMsg = s.message }
                is UiState.Success -> {
                    isLoading = false; errorMsg = null
                    val ps = s.data.policies
                    goats = ps.map { Triple(it.earTagNumber, "${it.breed}", "") }
                    statusByTag = ps.associate { it.earTagNumber to it.status }
                    clickIdByTag = ps.associate { it.earTagNumber to it.policyNumber }
                    counts = mapOf(
                        "all" to ps.size,
                        "active" to ps.count { it.status == "active" },
                        "expired" to ps.count { it.status == "expired" },
                        "claimed" to ps.count { it.status == "claimed" },
                    )
                }
            }
        }
    } else {
        val goatVm: GoatListViewModel = hiltViewModel()
        val goatState by goatVm.state.collectAsState()
        LaunchedEffect(searchQuery) { goatVm.setSearch(searchQuery) }
        LaunchedEffect(goatState) {
            when (val s = goatState) {
                is UiState.Loading -> { isLoading = true; errorMsg = null }
                is UiState.Error -> { isLoading = false; errorMsg = s.message }
                is UiState.Success -> {
                    isLoading = false; errorMsg = null
                    val gs = s.data.goats
                    goats = gs.map { Triple(it.earTagNumber, it.farmer ?: "—", it.village ?: "—") }
                    statusByTag = gs.associate { it.earTagNumber to it.status }
                    clickIdByTag = gs.associate { it.earTagNumber to it.id.toString() }
                    counts = mapOf(
                        "all" to (s.data.counts["all"] ?: gs.size),
                        "active" to (s.data.counts["active"] ?: 0),
                        "expired" to (s.data.counts["expired"] ?: 0),
                        "claimed" to (s.data.counts["claimed"] ?: 0),
                    )
                }
            }
        }
    }

    val tabs = listOf(
        languageState.value.getT("All (${counts["all"]})", "सभी (${counts["all"]})", "ସମସ୍ତ (${counts["all"]})"),
        languageState.value.getT("Active (${counts["active"]})", "सक्रिय (${counts["active"]})", "ସକ୍ରିୟ (${counts["active"]})"),
        languageState.value.getT("Expired (${counts["expired"]})", "समाप्त (${counts["expired"]})", "ସମାପ୍ତ (${counts["expired"]})"),
        languageState.value.getT("Claimed (${counts["claimed"]})", "दावा (${counts["claimed"]})", "ଦାବି (${counts["claimed"]})")
    )

    ResponsiveLayout(
        compact = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(languageState.value.getT("Goat List", "बकरियों की सूची", "ଛେଳି ତାଲିକା"), fontWeight = FontWeight.Bold) },
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
                GoatListContent(padding, tabs, searchQuery, { searchQuery = it }, selectedTab, { selectedTab = it }, themeColor, isFarmer, goats, statusByTag, clickIdByTag, isLoading, errorMsg, onReportDeath = { navController.navigate("farmer_report_death") }) { id ->
                    navController.navigate("goat_details/$id")
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
                        label = { Text(languageState.value.getT("Home", "होम", "ମୁଖ୍ୟ ପୃଷ୍ଠା")) },
                        colors = NavigationRailItemDefaults.colors(selectedIconColor = themeColor, selectedTextColor = themeColor, unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray, indicatorColor = Color.Transparent)
                    )
                    NavigationRailItem(
                        selected = currentRoute == "goat_list", 
                        onClick = { if (currentRoute != "goat_list") navController.navigate("goat_list") }, 
                        icon = { Icon(painterResource(R.drawable.ic_ewe_custom), null) }, 
                        label = { Text(languageState.value.getT("Goats", "बकरियां", "ଛେଳି")) },
                        colors = NavigationRailItemDefaults.colors(selectedIconColor = themeColor, selectedTextColor = themeColor, unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray, indicatorColor = Color.Transparent)
                    )
                    if (!isFarmer) {
                        NavigationRailItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Vaccines, null) }, label = { Text(languageState.value.getT("Vaccines", "टीकाकरण", "ଟୀକା")) }, colors = NavigationRailItemDefaults.colors(unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray))
                    }
                }
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(languageState.value.getT("Goat List", "बकरियों की सूची", "ଛେଳି ତାଲିକା"), fontWeight = FontWeight.Bold) },
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
                    GoatListContent(padding, tabs, searchQuery, { searchQuery = it }, selectedTab, { selectedTab = it }, themeColor, isFarmer, goats, statusByTag, clickIdByTag, isLoading, errorMsg, onReportDeath = { navController.navigate("farmer_report_death") }) { id ->
                        navController.navigate("goat_details/$id")
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
    goats: List<Triple<String, String, String>>,
    statusByTag: Map<String, String>,
    clickIdByTag: Map<String, String>,
    isLoading: Boolean,
    errorMsg: String?,
    onReportDeath: (String) -> Unit,
    onGoatClick: (String) -> Unit
) {
    val languageState = LocalAppLanguage.current
    val context = LocalContext.current
    Column(modifier = Modifier.padding(padding)) {
        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            placeholder = { Text(languageState.value.getT("Search by ear tag or farmer name", "कान के टैग या किसान के नाम से खोजें", "କାନ ଟ୍ୟାଗ୍ କିମ୍ବା କୃଷକଙ୍କ ନାମ ଅନୁସାରେ ଖୋଜନ୍ତୁ")) },
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

        if (isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth(), color = themeColor)
        }
        errorMsg?.let {
            Text(it, color = Color.Red, fontSize = 13.sp, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
        }

        val filteredGoats = goats.filter {
            it.first.contains(searchQuery, ignoreCase = true) || it.second.contains(searchQuery, ignoreCase = true)
        }.filter { goat ->
            when (selectedTab) {
                1 -> statusByTag[goat.first] == "active"
                2 -> statusByTag[goat.first] == "expired"
                3 -> statusByTag[goat.first] == "claimed"
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
                    val status = statusByTag[goat.first]
                    val isExpired = status == "expired"
                    // Death reported (or claim already approved): the goat is no longer
                    // live, so it can't be reported again — the claim section owns it now.
                    val isClaimed = status == "claimed"
                    val isDead = status == "dead"
                    val isGone = isDead || isClaimed
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
                                    Text(goat.first, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)
                                    Text("Black Bengal • ${languageState.value.getT("Female", "मादा", "ମାଈ")} • 12M", color = Color.Gray, fontSize = 12.sp)
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        when {
                                            isClaimed -> languageState.value.getT("Claim Approved", "दावा स्वीकृत", "ଦାବି ମଞ୍ଜୁର")
                                            isDead -> languageState.value.getT("Death Reported", "मृत्यु की सूचना दी गई", "ମୃତ୍ୟୁ ରିପୋର୍ଟ ହୋଇଛି")
                                            isExpired -> languageState.value.getT("Policy Expired", "पॉलिसी समाप्त", "ନୀତି ସମାପ୍ତ")
                                            else -> languageState.value.getT("Policy Active", "पॉलिसी सक्रिय", "ନୀତି ସକ୍ରିୟ")
                                        },
                                        color = if (isExpired || isDead) Color.Red else if (isClaimed) Color.Gray else SuccessGreen,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp
                                    )
                                    Text(
                                        when {
                                            isGone -> languageState.value.getT("See the Claims section", "दावा अनुभाग देखें", "ଦାବି ବିଭାଗ ଦେଖନ୍ତୁ")
                                            isExpired -> languageState.value.getT("Expired on: 31 May 2024", "31 मई 2024 को समाप्त", "୩୧ ମଇ ୨୦୨୪ ରେ ସମାପ୍ତ")
                                            else -> languageState.value.getT("Valid till 31 May 2025", "31 मई 2025 तक मान्य", "୩୧ ମଇ ୨୦୨୫ ପର୍ଯ୍ୟନ୍ତ ବୈଧ")
                                        },
                                        fontSize = 11.sp,
                                        color = Color.Gray
                                    )
                                }
                            }
                            
                            Spacer(modifier = Modifier.height(16.dp))
                            
                            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                if (!isGone) {
                                    Button(
                                        onClick = { onReportDeath(goat.first) },
                                        modifier = Modifier.weight(1f).height(40.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                                        shape = RoundedCornerShape(8.dp),
                                        contentPadding = PaddingValues(0.dp)
                                    ) {
                                        Text(languageState.value.getT("Report Death", "मृत्यु की सूचना", "ମୃତ୍ୟୁ ରିପୋର୍ଟ"), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                    }
                                }
                                Button(
                                    onClick = { onGoatClick(clickIdByTag[goat.first] ?: goat.first) },
                                    modifier = Modifier.weight(1f).height(40.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen),
                                    shape = RoundedCornerShape(8.dp),
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Text(languageState.value.getT("View Policy", "नीति देखें", "ନୀତି ଦେଖନ୍ତୁ"), fontWeight = FontWeight.Bold, fontSize = 12.sp)
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
                                        text = languageState.value.getT("Registered Goats", "पंजीकृत बकरियां", "ପଞ୍ଜିକୃତ ଛେଳି"),
                                        style = MaterialTheme.typography.labelMedium,
                                        color = themeColor,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))

                                    goats.forEachIndexed { index, goat ->
                                        val isExpired = statusByTag[goat.first] == "expired"
                                        Surface(
                                            onClick = { onGoatClick(clickIdByTag[goat.first] ?: goat.first) },
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
                                                        if (isExpired) languageState.value.getT("Policy Expired", "पॉलिसी समाप्त", "ନୀତି ସମାପ୍ତ") 
                                                        else languageState.value.getT("Policy Active", "पॉलिसी सक्रिय", "ନୀତି ସକ୍ରିୟ"),
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
                                        text = "${goats.size} " + languageState.value.getT("Goats Registered", "बकरियां पंजीकृत", "ଛେଳି ପଞ୍ଜିକୃତ"),
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
    val languageState = LocalAppLanguage.current
    
    val isFarmer = userRole == UserRole.FARMER
    val themeColor = if (isFarmer) PrimaryBlue else PrimaryGreen

    // Load real data. For SD/Coordinator `tag` carries the goat id; for Farmer it carries the policy number.
    var detail by remember { mutableStateOf<GoatDetail?>(null) }
    var policy by remember { mutableStateOf<PolicyDetail?>(null) }
    if (isFarmer) {
        val vm: PolicyDetailViewModel = hiltViewModel()
        val st by vm.state.collectAsState()
        LaunchedEffect(tag) { vm.load(tag) }
        policy = (st as? UiState.Success)?.data
    } else {
        val vm: GoatDetailViewModel = hiltViewModel()
        val st by vm.state.collectAsState()
        LaunchedEffect(tag) { tag.toIntOrNull()?.let { vm.load(it) } }
        detail = (st as? UiState.Success)?.data
    }

    val displayTag = detail?.earTagNumber ?: policy?.goat?.earTagNumber ?: tag
    val statusStr = detail?.status ?: policy?.status ?: ""
    val isExpired = statusStr == "expired"
    val farmerName = detail?.farmer?.name ?: "—"
    val farmerVillage = detail?.farmer?.village ?: "—"

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
                            languageState.value.getT("Goat Details", "बकरी का विवरण", "ଛେଳି ବିବରଣୀ"),
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
                        displayTag,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        if (isExpired) languageState.value.getT("Policy Expired", "पॉलिसी समाप्त", "ନୀତି ସମାପ୍ତ")
                        else languageState.value.getT("Policy Active", "पॉलिसी सक्रिय", "ନୀତି ସକ୍ରିୟ"),
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
                // Goat / policy source values
                val g = detail
                val pGoat = policy?.goat
                val breedVal = g?.breed ?: pGoat?.breed ?: "—"
                val genderVal = g?.gender ?: pGoat?.gender ?: "—"
                val ageVal = (g?.ageMonths ?: pGoat?.ageMonths)?.let { "$it " + languageState.value.getT("Months", "महीने", "ମାସ") } ?: "—"

                // Farmer Information (SD/Coordinator view has the farmer block)
                if (!isFarmer) {
                    ProfileInfoSection(languageState.value.getT("Farmer Information", "किसान जानकारी", "କୃଷକ ସୂଚନା"), themeColor) {
                        ProfileInfoItem(languageState.value.getT("Farmer Name", "किसान का नाम", "କୃଷକଙ୍କ ନାମ"), farmerName)
                        ProfileInfoItem(languageState.value.getT("Village", "गाँव", "ଗ୍ରାମ"), farmerVillage)
                        ProfileInfoItem(languageState.value.getT("Mobile", "मोबाइल", "ମୋବାଇଲ୍"), g?.farmer?.mobile ?: "—")
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }

                // Goat Details
                ProfileInfoSection(languageState.value.getT("Goat Details", "बकरी का विवरण", "ଛେଳି ବିବରଣୀ"), themeColor) {
                    ProfileInfoItem(languageState.value.getT("Breed", "नस्ल", "ପ୍ରଜାତି"), breedVal)
                    ProfileInfoItem(languageState.value.getT("Gender", "लिंग", "ଲିଙ୍ଗ"), genderVal)
                    ProfileInfoItem(languageState.value.getT("Age", "आयु", "ବୟସ"), ageVal)
                    g?.weightKg?.let { ProfileInfoItem(languageState.value.getT("Weight", "वजन", "ଓଜନ"), "${it} KG") }
                    (g?.color ?: g?.identificationMark)?.let { ProfileInfoItem(languageState.value.getT("Color / Marks", "रंग / निशान", "ରଙ୍ଗ / ଚିହ୍ନ"), it) }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Policy Details
                val pol = g?.policy
                ProfileInfoSection(languageState.value.getT("Policy Information", "पॉलिसी जानकारी", "ନୀତି ସୂଚନା"), themeColor) {
                    ProfileInfoItem(languageState.value.getT("Policy Number", "पॉलिसी नंबर", "ନୀତି ନମ୍ବର"), pol?.policyNumber ?: policy?.policyNumber ?: "—")
                    ProfileInfoItem(languageState.value.getT("Issue Date", "जारी करने की तारीख", "ପ୍ରଦାନ ତାରିଖ"), pol?.validFrom ?: policy?.validFrom ?: "—")
                    ProfileInfoItem(languageState.value.getT("End Date", "समाप्ति तिथि", "ଶେଷ ତାରିଖ"), pol?.validTo ?: policy?.validTo ?: "—")
                    ProfileInfoItem(languageState.value.getT("Sum Insured", "बीमा राशि", "ବୀମା ରାଶି"), (pol?.sumInsured ?: policy?.sumInsured)?.let { "₹ ${it.toInt()}" } ?: "—")
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Vaccination History
                ProfileInfoSection(languageState.value.getT("Vaccination History", "टीकाकरण इतिहास", "ଟୀକାକରଣ ଇତିହାସ"), themeColor) {
                    // SD/coordinator view goes through GoatDetail; the farmer view through PolicyDetail.
                    val vaccList = g?.vaccinations ?: policy?.vaccinations ?: emptyList()
                    val vaccByType = vaccList.associateBy { it.type }
                    fun done(t: String) = vaccByType[t]?.status == "done"
                    VaccineStatusItem("PPR Vaccine", done("ppr"), themeColor) {}
                    VaccineStatusItem("ET + TT Vaccine", done("et_tt"), themeColor) {}
                    VaccineStatusItem("FMD Vaccine", done("fmd"), themeColor) {}
                    VaccineStatusItem("Goat Pox Vaccine", done("goat_pox"), themeColor) {}
                }

                if (isFarmer) {
                    Spacer(modifier = Modifier.height(24.dp))
                    val certVm: PolicyDetailViewModel = hiltViewModel()
                    val certState by certVm.certificate.collectAsState()
                    val certContext = LocalContext.current
                    LaunchedEffect(certState) {
                        when (val s = certState) {
                            is SubmitState.Success -> {
                                s.message?.let { path ->
                                    val uri = FileProvider.getUriForFile(
                                        certContext, "${certContext.packageName}.fileprovider", File(path)
                                    )
                                    val intent = Intent(Intent.ACTION_VIEW).apply {
                                        setDataAndType(uri, "application/pdf")
                                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                                    }
                                    try {
                                        certContext.startActivity(intent)
                                    } catch (e: Exception) {
                                        Toast.makeText(certContext, languageState.value.getT("No PDF viewer installed", "कोई पीडीएफ व्यूअर नहीं मिला", "କୌଣସି PDF ଭ୍ୟୁଅର୍ ନାହିଁ"), Toast.LENGTH_LONG).show()
                                    }
                                }
                                certVm.resetCertificate()
                            }
                            is SubmitState.Error -> {
                                Toast.makeText(certContext, s.message, Toast.LENGTH_LONG).show()
                                certVm.resetCertificate()
                            }
                            else -> {}
                        }
                    }
                    Button(
                        onClick = { certVm.downloadCertificate(displayTag, certContext.cacheDir) },
                        enabled = certState !is SubmitState.Submitting,
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
    val languageState = LocalAppLanguage.current
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableIntStateOf(0) }

    val vaccVm: VaccinationViewModel = hiltViewModel()

    // Keep the list live: re-fetch whenever this screen returns to the foreground (e.g. after
    // recording a vaccination or enrolling a goat on another screen). Without this the list VM
    // only loads once and the Completed tab stays stale. refresh() reloads without a spinner flash.
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) vaccVm.refresh()
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    val vaccState by vaccVm.state.collectAsState()
    val isLoading = vaccState is UiState.Loading
    val errorMsg = (vaccState as? UiState.Error)?.message
    val vaccList: List<Map<String, String>> = ((vaccState as? UiState.Success)?.data?.items ?: emptyList()).map {
        val name = when (it.vaccineType) {
            "et_tt" -> "ET + TT Vaccine"; "fmd" -> "FMD Vaccine"; "goat_pox" -> "Goat Pox Vaccine"; else -> "PPR Vaccine"
        }
        mapOf(
            "name" to name, "tag" to (it.earTag ?: "—"),
            "date" to (it.nextVaccinationDate ?: ""), "farmer" to (it.earTag ?: "—"),
            "village" to "", "status" to it.status, "goatId" to it.goatId.toString(),
        )
    }

    val counts = vaccList.groupingBy { it["status"] }.eachCount()
    val tabs = listOf(
        languageState.value.getT("Upcoming (${vaccList.count { it["status"] != "done" }})", "आगामी (${vaccList.count { it["status"] != "done" }})", "ଆଗାମୀ (${vaccList.count { it["status"] != "done" }})"),
        languageState.value.getT("Completed (${counts["done"] ?: 0})", "पूरा (${counts["done"] ?: 0})", "ସମ୍ପୂର୍ଣ୍ଣ (${counts["done"] ?: 0})"),
        languageState.value.getT("All", "सभी", "ସମସ୍ତ")
    )

    ResponsiveLayout(
        compact = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(languageState.value.getT("Vaccinations", "टीकाकरण", "ଟୀକାକରଣ"), fontWeight = FontWeight.Bold) },
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
                VaccineListContent(padding, tabs, searchQuery, { searchQuery = it }, selectedTab, { selectedTab = it }, vaccList, isLoading, errorMsg, onRecord)
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
                        label = { Text(languageState.value.getT("Home", "होम", "ମୁଖ୍ୟ ପୃଷ୍ଠା")) },
                        colors = NavigationRailItemDefaults.colors(selectedIconColor = PrimaryGreen, selectedTextColor = PrimaryGreen, unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray, indicatorColor = Color.Transparent)
                    )
                    NavigationRailItem(
                        selected = currentRoute == "goat_list", 
                        onClick = { if (currentRoute != "goat_list") navController.navigate("goat_list") }, 
                        icon = { Icon(painterResource(R.drawable.ic_ewe_custom), null) }, 
                        label = { Text(languageState.value.getT("Goats", "बकरियां", "ଛେଳି")) },
                        colors = NavigationRailItemDefaults.colors(selectedIconColor = PrimaryGreen, selectedTextColor = PrimaryGreen, unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray, indicatorColor = Color.Transparent)
                    )
                    NavigationRailItem(
                        selected = currentRoute == "vaccine_list", 
                        onClick = { if (currentRoute != "vaccine_list") navController.navigate("vaccine_list") }, 
                        icon = { Icon(Icons.Default.MedicalServices, null) }, 
                        label = { Text(languageState.value.getT("Vaccines", "टीकाकरण", "ଟୀକା")) },
                        colors = NavigationRailItemDefaults.colors(selectedIconColor = PrimaryGreen, selectedTextColor = PrimaryGreen, unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray, indicatorColor = Color.Transparent)
                    )
                }
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(languageState.value.getT("Vaccinations", "टीकाकरण", "ଟୀକାକରଣ"), fontWeight = FontWeight.Bold) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = PrimaryGreen,
                                titleContentColor = Color.White
                            )
                        )
                    },
                    containerColor = Color(0xFFF8F9F5)
                ) { padding ->
                    VaccineListContent(padding, tabs, searchQuery, { searchQuery = it }, selectedTab, { selectedTab = it }, vaccList, isLoading, errorMsg, onRecord)
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
    vaccinations: List<Map<String, String>>,
    isLoading: Boolean,
    errorMsg: String?,
    onRecord: (String) -> Unit
) {
    val languageState = LocalAppLanguage.current
    Column(modifier = Modifier.padding(padding)) {
        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            placeholder = { Text(languageState.value.getT("Search by ear tag or vaccine name", "कान के टैग या टीके के नाम से खोजें", "କାନ ଟ୍ୟାଗ୍ କିମ୍ବା ଟୀକା ନାମ ଅନୁସାରେ ଖୋଜନ୍ତୁ")) },
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

        if (isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth(), color = PrimaryGreen)
        }
        errorMsg?.let {
            Text(it, color = Color.Red, fontSize = 13.sp, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
        }

        val filteredVaccines = vaccinations.filter {
            (it["name"] ?: "").contains(searchQuery, ignoreCase = true) || (it["tag"] ?: "").contains(searchQuery, ignoreCase = true)
        }.filter { vaccine ->
            val isCompleted = vaccine["status"] == "done"
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
                                    val isCompleted = vaccine["status"] == "done"
                                    Surface(
                                        onClick = { onRecord(vaccine["goatId"] ?: "") },
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
                                                Text("${vaccine["name"]} • ${if (isCompleted) "Done" else "Due"}", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = if (isCompleted) SuccessGreen else AccentOrange)
                                                Text(vaccine["date"] ?: "", fontSize = 12.sp, color = Color.Black)
                                            }
                                            OutlinedButton(
                                                onClick = { onRecord(vaccine["goatId"] ?: "") },
                                                modifier = Modifier.height(32.dp),
                                                shape = RoundedCornerShape(8.dp),
                                                border = BorderStroke(1.dp, PrimaryGreen),
                                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp)
                                            ) {
                                                Text(languageState.value.getT("Record", "रिकॉर्ड", "ରେକର୍ଡ"), color = PrimaryGreen, fontSize = 11.sp, fontWeight = FontWeight.Bold)
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
                                    text = "${vaccines.size} " + languageState.value.getT("Vaccinations Pending/Scheduled", "टीकाकरण लंबित/निर्धारित", "ଟୀକାକରଣ ବାକି ଅଛି"),
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
    val languageState = LocalAppLanguage.current
    val context = LocalContext.current
    var vaccineType by remember { mutableStateOf("PPR Vaccine") }
    var batchNumber by remember { mutableStateOf("PPR-2406-01") }
    var vaccinationDate by remember { mutableStateOf("") }
    var vaccinationDateIso by remember { mutableStateOf("") }

    val vaccVm: VaccinationViewModel = hiltViewModel()
    val vaccSubmit by vaccVm.submit.collectAsState()
    val isSaving = vaccSubmit is SubmitState.Submitting
    LaunchedEffect(vaccSubmit) {
        when (val s = vaccSubmit) {
            is SubmitState.Success -> { Toast.makeText(context, s.message ?: "Saved", Toast.LENGTH_SHORT).show(); vaccVm.resetSubmit(); onBack() }
            is SubmitState.Error -> { Toast.makeText(context, s.message, Toast.LENGTH_LONG).show(); vaccVm.resetSubmit() }
            else -> {}
        }
    }

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
                title = { Text(languageState.value.getT("Record Vaccination", "टीकाकरण रिकॉर्ड करें", "ଟୀକାକରଣ ରେକର୍ଡ କରନ୍ତୁ"), fontWeight = FontWeight.Bold) },
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
                        Text(tag, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        Text(goat.second, fontSize = 13.sp, color = Color.Gray)
                        Text("Black Bengal • ${languageState.value.getT("Female", "मादा", "ମାଈ")} • 12M", fontSize = 13.sp, color = Color.Gray)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            EnrollmentDropdownField(
                label = languageState.value.getT("Vaccine Type *", "टीका का प्रकार *", "ଟୀକା ପ୍ରକାର *"),
                selectedValue = vaccineType,
                options = listOf("PPR Vaccine", "ET + TT Vaccine", "FMD Vaccine", "Goat Pox Vaccine"),
                onValueChange = { vaccineType = it }
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            EnrollmentDropdownField(
                label = languageState.value.getT("Batch Number *", "बैच संख्या *", "ବ୍ୟାଚ୍ ନମ୍ବର *"),
                selectedValue = batchNumber,
                options = listOf("PPR-2406-01", "PPR-2406-02", "ETTT-2405-09"),
                onValueChange = { batchNumber = it }
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            EnrollmentTextField(
                label = languageState.value.getT("Vaccination Date *", "टीकाकरण की तारीख *", "ଟୀକାକରଣ ତାରିଖ *"),
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
                            vaccinationDateIso = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                languageState.value.getT("Photo (Vaccine Label) *", "फोटो (टीका लेबल) *", "ଫଟୋ (ଟୀକା ଲେବଲ୍) *"),
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
                onClick = {
                    val goatId = tag.toIntOrNull()
                    if (goatId == null) {
                        Toast.makeText(context, "Invalid goat reference", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    if (vaccinationDateIso.isBlank()) {
                        Toast.makeText(context, languageState.value.getT("Please select the vaccination date", "कृपया टीकाकरण की तारीख चुनें", "ଦୟାକରି ଟୀକାକରଣ ତାରିଖ ବାଛନ୍ତୁ"), Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    val typeCode = when (vaccineType) {
                        "ET + TT Vaccine" -> "et_tt"
                        "FMD Vaccine" -> "fmd"
                        "Goat Pox Vaccine" -> "goat_pox"
                        else -> "ppr"
                    }
                    vaccVm.record(
                        RecordVaccinationRequest(
                            goatId = goatId, vaccineType = typeCode,
                            batchNumber = batchNumber, vaccinationDate = vaccinationDateIso,
                        )
                    )
                },
                enabled = !isSaving,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
            ) {
                Text(if (isSaving) languageState.value.getT("Saving…", "सहेज रहा है…", "ସଂରକ୍ଷଣ…") else languageState.value.getT("Save Record", "रिकॉर्ड सहेजें", "ରେକର୍ଡ ସଂରକ୍ଷଣ କରନ୍ତୁ"), fontSize = 16.sp, fontWeight = FontWeight.Bold)
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

    // Coordinator claims data
    var coClaims by remember { mutableStateOf<List<Map<String, String>>>(emptyList()) }
    var coCounts by remember { mutableStateOf(mapOf("all" to 0, "progress" to 0, "pending" to 0, "completed" to 0)) }
    if (isCoordinator) {
        val claimsVm: CoordinatorClaimsViewModel = hiltViewModel()
        val claimsState by claimsVm.state.collectAsState()
        LaunchedEffect(claimsState) {
            (claimsState as? UiState.Success)?.data?.claims?.let { list ->
                coClaims = list.map {
                    mapOf(
                        "id" to it.claimNumber, "farmer" to (it.farmer ?: "—"),
                        "tag" to it.earTagNumber, "status" to it.status,
                        "date" to (it.dateOfDeath?.take(10) ?: ""),
                        "amount" to ((it.claimAmount ?: it.sumInsured).toInt().toString()),
                        "vaccines" to (it.vaccinationsDone?.toString() ?: ""),
                    )
                }
                coCounts = mapOf(
                    "all" to list.size,
                    "progress" to list.count { it.status == "pending" || it.status == "hold" },
                    "pending" to list.count { it.status == "pending" },
                    "completed" to list.count { it.status == "approved" || it.status == "rejected" || it.status == "claimed" },
                )
            }
        }
    }

    // SD / Farmer claims data
    var didiClaims by remember { mutableStateOf<List<Map<String, String>>>(emptyList()) }
    if (userRole == UserRole.SURAKSHA_DIDI) {
        val vm: SdClaimsViewModel = hiltViewModel()
        val st by vm.state.collectAsState()
        LaunchedEffect(st) {
            (st as? UiState.Success)?.data?.claims?.let { list ->
                didiClaims = list.map {
                    mapOf(
                        "id" to (it.claimNumber ?: "MORT-${it.mortalityId}"),
                        "farmer" to (it.farmer ?: "—"), "tag" to it.earTagNumber,
                        "status" to (it.claimStatus ?: "pending"),
                        "date" to (it.dateOfDeath?.take(10) ?: ""),
                        "amount" to (it.claimAmount?.toInt()?.toString() ?: ""),
                        "vaccines" to (it.vaccinationsDone?.toString() ?: ""),
                        "cause" to (it.causeOfDeath ?: ""),
                    )
                }
            }
        }
    } else if (userRole == UserRole.FARMER) {
        val vm: FarmerClaimsViewModel = hiltViewModel()
        val st by vm.state.collectAsState()
        LaunchedEffect(st) {
            (st as? UiState.Success)?.data?.claims?.let { list ->
                didiClaims = list.map {
                    mapOf(
                        "id" to (it.claimNumber ?: "MORT-${it.mortalityId}"),
                        "farmer" to (it.earTagNumber), "tag" to it.earTagNumber,
                        "status" to (it.claimStatus ?: "pending"),
                        "date" to (it.dateOfDeath?.take(10) ?: ""),
                        "amount" to (it.claimAmount?.toInt()?.toString() ?: ""),
                        "vaccines" to (it.vaccinationsDone?.toString() ?: ""),
                        "cause" to (it.causeOfDeath ?: ""),
                    )
                }
            }
        }
    }

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
                    CoordinatorClaimListContent(padding, searchQuery, { searchQuery = it }, themeColor, coClaims, coCounts) { claimId ->
                        navController.navigate("claim_review/$claimId")
                    }
                } else {
                    DidiClaimListContent(padding, searchQuery, { searchQuery = it }, themeColor, isFarmer, didiClaims) { claimId ->
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
                            label = { Text(languageState.value.getT("Dashboard", "डैशबोर्ड", "ଡ୍ୟାସବୋର୍ଡ")) },
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
                            label = { Text(languageState.value.getT("Home", "होम", "ମୁଖ୍ୟ ପୃଷ୍ଠା")) },
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
                            label = { Text(languageState.value.getT("My Goats", "मेरी बकरियां", "ମୋର ଛେଳି")) },
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
                            label = { Text(languageState.value.getT("Home", "होम", "ମୁଖ୍ୟ ପୃଷ୍ଠା")) },
                            colors = NavigationRailItemDefaults.colors(selectedIconColor = PrimaryGreen, selectedTextColor = PrimaryGreen, unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray, indicatorColor = Color.Transparent)
                        )
                        NavigationRailItem(
                            selected = currentRoute == "goat_list", 
                            onClick = { if (currentRoute != "goat_list") navController.navigate("goat_list") }, 
                            icon = { Icon(painterResource(R.drawable.ic_ewe_custom), null) }, 
                            label = { Text(languageState.value.getT("Goats", "बकरियां", "ଛେଳି")) },
                            colors = NavigationRailItemDefaults.colors(selectedIconColor = PrimaryGreen, selectedTextColor = PrimaryGreen, unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray, indicatorColor = Color.Transparent)
                        )
                        NavigationRailItem(
                            selected = currentRoute == "vaccine_list", 
                            onClick = { if (currentRoute != "vaccine_list") navController.navigate("vaccine_list") }, 
                            icon = { Icon(Icons.Default.MedicalServices, null) }, 
                            label = { Text(languageState.value.getT("Vaccines", "टीकाकरण", "ଟୀକା")) },
                            colors = NavigationRailItemDefaults.colors(selectedIconColor = PrimaryGreen, selectedTextColor = PrimaryGreen, unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray, indicatorColor = Color.Transparent)
                        )
                    }
                }
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
                    containerColor = Color(0xFFF8F9F5)
                ) { padding ->
                    if (isCoordinator) {
                        CoordinatorClaimListContent(padding, searchQuery, { searchQuery = it }, themeColor, coClaims, coCounts) { claimId ->
                            navController.navigate("claim_review/$claimId")
                        }
                    } else {
                        DidiClaimListContent(padding, searchQuery, { searchQuery = it }, themeColor, isFarmer, didiClaims) { claimId ->
                            navController.navigate("claim_review/$claimId")
                        }
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
    claims: List<Map<String, String>>,
    counts: Map<String, Int>,
    onClaimClick: (String) -> Unit
) {
    val languageState = LocalAppLanguage.current
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
                        label = languageState.value.getT("Total Claims", "कुल दावे", "ମୋଟ ଦାବି"),
                        value = "${counts["all"] ?: 0}",
                        icon = Icons.AutoMirrored.Filled.Assignment,
                        modifier = Modifier.weight(1f)
                    )
                    Box(modifier = Modifier.width(1.dp).height(40.dp).background(Color.White.copy(alpha = 0.2f)).align(Alignment.CenterVertically))
                    CoordinatorStatCard(
                        label = languageState.value.getT("In Progress", "प्रगति में", "ପ୍ରଗତିରେ ଅଛି"),
                        value = "${counts["progress"] ?: 0}",
                        icon = Icons.Default.History,
                        modifier = Modifier.weight(1f)
                    )
                }
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = Color.White.copy(alpha = 0.2f))
                Row(modifier = Modifier.fillMaxWidth()) {
                    CoordinatorStatCard(
                        label = languageState.value.getT("Completed", "पूरा हुआ", "ସମ୍ପୂର୍ଣ୍ଣ"),
                        value = "${counts["completed"] ?: 0}",
                        icon = Icons.AutoMirrored.Filled.FactCheck,
                        modifier = Modifier.weight(1f)
                    )
                    Box(modifier = Modifier.width(1.dp).height(40.dp).background(Color.White.copy(alpha = 0.2f)).align(Alignment.CenterVertically))
                    CoordinatorStatCard(
                        label = languageState.value.getT("Pending Action", "लंबित कार्रवाई", "ବାକି ଥିବା କାର୍ଯ୍ୟ"),
                        value = "${counts["pending"] ?: 0}",
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
            placeholder = { Text(languageState.value.getT("Search by Claim ID, Farmer, Tag No.", "दावा आईडी, किसान, टैग नंबर द्वारा खोजें", "ଦାବି ID, କୃଷକ, ଟ୍ୟାଗ୍ ନମ୍ବର ଦ୍ୱାରା ଖୋଜନ୍ତୁ"), fontSize = 12.sp) },
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
            languageState.value.getT("All (${counts["all"] ?: 0})", "सभी (${counts["all"] ?: 0})", "ସମସ୍ତ (${counts["all"] ?: 0})"),
            languageState.value.getT("Pending (${counts["pending"] ?: 0})", "लंबित (${counts["pending"] ?: 0})", "ବାକି (${counts["pending"] ?: 0})"),
            languageState.value.getT("Completed (${counts["completed"] ?: 0})", "पूरा (${counts["completed"] ?: 0})", "ସମ୍ପୂର୍ଣ୍ଣ (${counts["completed"] ?: 0})")
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

        val filtered = claims.filter {
            (it["id"] ?: "").contains(searchQuery, true) || (it["farmer"] ?: "").contains(searchQuery, true) || (it["tag"] ?: "").contains(searchQuery, true)
        }.filter {
            when (selectedTab) {
                1 -> it["status"] == "pending"
                2 -> it["status"] in listOf("approved", "rejected", "claimed")
                else -> true
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (filtered.isEmpty()) {
                item {
                    Text(
                        languageState.value.getT("No claims found.", "कोई दावा नहीं मिला।", "କୌଣସି ଦାବି ମିଳିଲା ନାହିଁ।"),
                        color = Color.Gray, fontSize = 13.sp, modifier = Modifier.padding(16.dp)
                    )
                }
            }
            items(filtered) { claim ->
                DidiClaimCard(claim, themeColor) { onClaimClick(claim["id"] ?: "") }
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
    claims: List<Map<String, String>> = emptyList(),
    onClaimClick: (String) -> Unit
) {
    val languageState = LocalAppLanguage.current
    var selectedTab by remember { mutableIntStateOf(0) }

    // Real, data-driven counts (was hardcoded mock values before).
    val inProgressCount = claims.count { (it["status"] ?: "") in listOf("pending", "hold") }
    val completedCount = claims.count { (it["status"] ?: "") in listOf("approved", "rejected", "claimed") }
    val pendingCount = claims.count { (it["status"] ?: "") == "pending" }

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
                            text = languageState.value.getT("Total Claims", "कुल दावे", "ମୋଟ ଦାବି"),
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "${claims.size}",
                            fontSize = 32.sp,
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                        Text(
                            text = languageState.value.getT("This Month", "इस महीने", "ଏହି ମାସ"),
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
                        ClaimStatRow(languageState.value.getT("In Progress", "प्रगति में", "ପ୍ରଗତିରେ ଅଛି"), "$inProgressCount")
                        ClaimStatRow(languageState.value.getT("Completed", "पूरा हुआ", "ସମ୍ପୂର୍ଣ୍ଣ"), "$completedCount")
                        ClaimStatRow(languageState.value.getT("Pending", "लंबित", "ବାକି ଅଛି"), "$pendingCount")
                    }
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
            placeholder = { Text(languageState.value.getT("Search by Claim ID, Farmer, Tag No.", "दावा आईडी, किसान, टैग नंबर द्वारा खोजें", "ଦାବି ID, କୃଷକ, ଟ୍ୟାଗ୍ ନମ୍ବର ଦ୍ୱାରା ଖୋଜନ୍ତୁ"), fontSize = 12.sp) },
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
            languageState.value.getT("All (${claims.size})", "सभी (${claims.size})", "ସମସ୍ତ (${claims.size})"),
            languageState.value.getT("In Progress ($inProgressCount)", "प्रगति में ($inProgressCount)", "ପ୍ରଗତିରେ ($inProgressCount)"),
            languageState.value.getT("Completed ($completedCount)", "पूरा ($completedCount)", "ସମ୍ପୂର୍ଣ୍ଣ ($completedCount)")
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

        val filtered = claims.filter {
            (it["id"] ?: "").contains(searchQuery, true) || (it["farmer"] ?: "").contains(searchQuery, true) || (it["tag"] ?: "").contains(searchQuery, true)
        }.filter {
            when (selectedTab) {
                1 -> (it["status"] ?: "") in listOf("pending", "hold")
                2 -> (it["status"] ?: "") in listOf("approved", "rejected", "claimed")
                else -> true
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (filtered.isEmpty()) {
                item {
                    Text(
                        languageState.value.getT("No claims yet.", "अभी तक कोई दावा नहीं।", "ଏପର୍ଯ୍ୟନ୍ତ କୌଣସି ଦାବି ନାହିଁ।"),
                        color = Color.Gray, fontSize = 13.sp, modifier = Modifier.padding(16.dp)
                    )
                }
            }
            items(filtered) { claim ->
                DidiClaimCard(claim, themeColor) { onClaimClick(claim["id"] ?: "") }
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
fun DidiClaimCard(claim: Map<String, String>, themeColor: Color, onClick: () -> Unit) {
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
                Text(claim["id"] ?: "", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = Color.Black)
                
                val status = claim["status"] ?: ""
                val (statusText, statusBg, statusColor) = when(status) {
                    "approved" -> Triple(languageState.value.getT("Approved", "स्वीकृत", "ଅନୁମୋଦିତ"), Color(0xFFE8F5E9), Color(0xFF2E7D32))
                    "claimed" -> Triple(languageState.value.getT("Paid", "भुगतान", "ଦିଆଯାଇଛି"), Color(0xFFE8F5E9), Color(0xFF2E7D32))
                    "rejected" -> Triple(languageState.value.getT("Rejected", "अस्वीकृत", "ପ୍ରତ୍ୟାଖ୍ୟାନ"), Color(0xFFFFEBEE), Color(0xFFC62828))
                    "hold" -> Triple(languageState.value.getT("On Hold", "होल्ड पर", "ହୋଲ୍ଡ"), Color(0xFFE3F2FD), Color(0xFF1565C0))
                    "In Progress" -> Triple(languageState.value.getT("In Progress", "प्रगति में", "ପ୍ରଗତିରେ ଅଛି"), Color(0xFFFFF3E0), Color(0xFFE65100))
                    else -> Triple(languageState.value.getT("Pending", "लंबित", "ବାକି ଅଛି"), Color(0xFFFFF3E0), Color(0xFFE65100))
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
            Text(claim["farmer"] ?: "", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.DarkGray)
            Text(
                text = languageState.value.getT("Tag No: ", "टैग नंबर: ", "ଟ୍ୟାଗ୍ ନମ୍ବର: ") + (claim["tag"] ?: ""),
                fontSize = 13.sp,
                color = Color.Gray
            )

            // Tiered payout entitlement (playbook: by completed vaccinations).
            val amount = claim["amount"] ?: ""
            val vaccines = claim["vaccines"] ?: ""
            if (amount.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = languageState.value.getT("Entitled: ", "पात्र: ", "ହକଦାର: ") + "₹$amount",
                        fontSize = 13.sp, fontWeight = FontWeight.Bold, color = PrimaryGreen
                    )
                    if (vaccines.isNotBlank()) {
                        Text(
                            text = "  •  " + languageState.value.getT("$vaccines/4 vaccines", "$vaccines/4 टीके", "$vaccines/4 ଟୀକା"),
                            fontSize = 12.sp, color = Color.Gray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = languageState.value.getT("Reported On: ", "रिपोर्ट की तारीख: ", "ରିପୋର୍ଟ ତାରିଖ: ") + (claim["date"] ?: ""),
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
            placeholder = { Text(languageState.value.getT("Search by claim ID or tag number", "दावा आईडी या टैग नंबर से खोजें", "ଦାବି ID କିମ୍ବା ଟ୍ୟାଗ୍ ନମ୍ବର ଦ୍ୱାରା ଖୋଜନ୍ତୁ")) },
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
                                        "Pending" -> languageState.value.getT("In Progress", "प्रगति पर है", "ଚାଲୁଅଛି") to AccentOrange
                                        "Approved" -> languageState.value.getT("Verification", "सत्यापन", "ଯାଞ୍ଚ") to SuccessGreen
                                        "Rejected" -> languageState.value.getT("Didi Visit", "दीदी का दौरा", "ଦିଦିଙ୍କ ପରିଦର୍ଶନ") to InfoBlue
                                        else -> languageState.value.getT("Pending", "लंबित", "ବାକି") to Color.Gray
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
                                    text = languageState.value.getT("Tag No: ", "टैग नंबर: ", "ଟ୍ୟାଗ୍ ନମ୍ବର: ") + (claim["tag"] ?: ""),
                                    fontSize = 13.sp,
                                    color = Color.Gray
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = languageState.value.getT("Reported On: ", "रिपोर्ट की तारीख: ", "ରିପୋର୍ଟ ତାରିଖ: ") + (claim["date"] ?: ""),
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
                                                            "Pending" -> languageState.value.getT("Verification in Progress", "सत्यापन प्रगति पर है", "ଯାଞ୍ଚ ଚାଲୁଛି")
                                                            "Approved" -> languageState.value.getT("Claim Approved", "दावा स्वीकृत", "ଦାବି ଅନୁମୋଦିତ")
                                                            else -> languageState.value.getT("Claim Rejected", "दावा अस्वीकृत", "ଦାବି ପ୍ରତ୍ୟାଖ୍ୟାନ")
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
                                                            "Pending" -> languageState.value.getT("Pending", "लंबित", "ବାକି")
                                                            "Approved" -> languageState.value.getT("Approved", "स्वीकृत", "ଅନୁମୋଦିତ")
                                                            else -> languageState.value.getT("Rejected", "अस्वीकृत", "ପ୍ରତ୍ୟାଖ୍ୟାନ")
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
    val languageState = LocalAppLanguage.current
    var selectedTab by remember { mutableIntStateOf(0) }

    val themeColor = when (userRole) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }

    val isFarmerRole = userRole == UserRole.FARMER

    val claimsVm: CoordinatorClaimsViewModel = hiltViewModel()
    val reviewState by claimsVm.review.collectAsState()
    LaunchedEffect(claimId) {
        when (userRole) {
            UserRole.COORDINATOR -> claimsVm.loadReview(claimId)
            UserRole.SURAKSHA_DIDI -> claimsVm.loadSdReview(claimId)
            else -> {}
        }
    }
    val review = (reviewState as? UiState.Success)?.data

    // There is no farmer-facing claim-detail endpoint, but /farmer/claims already
    // carries everything this screen shows, so pick this claim out of that list.
    var farmerClaim by remember { mutableStateOf<FarmerClaimItem?>(null) }
    if (isFarmerRole) {
        val farmerClaimsVm: FarmerClaimsViewModel = hiltViewModel()
        val farmerClaimsState by farmerClaimsVm.state.collectAsState()
        LaunchedEffect(farmerClaimsState, claimId) {
            farmerClaim = (farmerClaimsState as? UiState.Success)?.data?.claims
                ?.firstOrNull { (it.claimNumber ?: "MORT-${it.mortalityId}") == claimId }
        }
    }

    val claimNumber = if (isFarmerRole) farmerClaim?.claimNumber else review?.claimNumber
    val claimStatus = if (isFarmerRole) farmerClaim?.claimStatus else review?.status
    val earTag = if (isFarmerRole) farmerClaim?.earTagNumber else review?.goat?.earTagNumber
    val deathDate = if (isFarmerRole) farmerClaim?.dateOfDeath else review?.dateOfDeath
    val cause = if (isFarmerRole) farmerClaim?.causeOfDeath else review?.causeOfDeath
    val amount = if (isFarmerRole) (farmerClaim?.claimAmount ?: farmerClaim?.sumInsured)
                 else (review?.claimAmount ?: review?.sumInsured)
    val vaccines = if (isFarmerRole) farmerClaim?.vaccinationsDone else review?.vaccinationsDone
    val progress = (if (isFarmerRole) farmerClaim?.progress else review?.progress) ?: emptyMap()

    val claimData = mapOf(
        "id" to (claimNumber ?: claimId),
        "status" to (claimStatus ?: "—"),
        "tag" to (earTag ?: "—"),
        "farmer" to (review?.farmer ?: "—"),
        "date" to (deathDate?.take(10) ?: "—"),
        "deathDate" to (deathDate?.take(10) ?: "—"),
        "time" to "",
        "policy" to (claimNumber ?: "—"),
        "cause" to (cause ?: "—"),
        "amount" to (amount?.toInt()?.toString() ?: "—"),
        "vaccines" to (vaccines?.toString() ?: ""),
        // Claim progress stepper, straight from the backend.
        "p_death" to (progress["death_notification"] ?: "pending"),
        "p_site" to (progress["site_visit"] ?: "pending"),
        "p_verify" to (progress["carcass_verification"] ?: "pending"),
        "p_review" to (progress["claim_review"] ?: "pending"),
    )

    if (isFarmerRole) {
        FarmerClaimFlow(navController, claimData, themeColor, onBack)
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = languageState.value.getT("Claim Details", "दावा विवरण", "ଦାବି ବିବରଣୀ"),
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
                claim = claimData,
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
    val languageState = LocalAppLanguage.current
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
                        text = languageState.value.getT("Bank Details", "बैंक विवरण", "ବ୍ୟାଙ୍କ ବିବରଣୀ"), 
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
                text = languageState.value.getT("Enter Bank Details for Payout", "भुगतान के लिए बैंक विवरण दर्ज करें", "ଦେୟ ପାଇଁ ବ୍ୟାଙ୍କ ବିବରଣୀ ଦିଅନ୍ତୁ"), 
                style = MaterialTheme.typography.titleMedium, 
                fontWeight = FontWeight.Bold,
                color = if (isDidi) Color.Black else MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(24.dp))

            EnrollmentTextField(label = languageState.value.getT("Account Holder Name *", "खाता धारक का नाम *", "ଖାତାଧାରୀଙ୍କ ନାମ *"), value = accHolder, onValueChange = { accHolder = it }, borderColor = themeColor)
            Spacer(modifier = Modifier.height(16.dp))
            EnrollmentTextField(label = languageState.value.getT("Bank Name *", "बैंक का नाम *", "ବ୍ୟାଙ୍କ ନାମ *"), value = bankName, onValueChange = { bankName = it }, borderColor = themeColor)
            Spacer(modifier = Modifier.height(16.dp))
            EnrollmentTextField(label = languageState.value.getT("Account Number *", "खाता संख्या *", "ଖାତା ନମ୍ବର *"), value = accNumber, onValueChange = { accNumber = it }, keyboardType = KeyboardType.Number, borderColor = themeColor)
            Spacer(modifier = Modifier.height(16.dp))
            EnrollmentTextField(label = languageState.value.getT("IFSC Code *", "आईएफएससी कोड *", "IFSC କୋଡ୍ *"), value = ifscCode, onValueChange = { ifscCode = it.uppercase() }, borderColor = themeColor)
            Spacer(modifier = Modifier.height(16.dp))
            EnrollmentTextField(label = languageState.value.getT("Branch *", "शाखा *", "ଶାଖା *"), value = branch, onValueChange = { branch = it }, borderColor = themeColor)
            
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = languageState.value.getT("Upload Passbook / Cancelled Cheque *", "पासबुक / रद्द चेक अपलोड करें *", "ପାସବୁକ୍ / ବାତିଲ୍ ଚେକ୍ ଅପଲୋଡ୍ କରନ୍ତୁ *"), 
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
                Text(languageState.value.getT("Submit Claim", "दावा सबमिट करें", "ଦାବି ସବମିଟ୍ କରନ୍ତୁ"), fontWeight = FontWeight.Bold, fontSize = 16.sp)
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
                            text = languageState.value.getT("In Progress", "प्रगति में", "ପ୍ରଗତିରେ ଅଛି"),
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
                    text = languageState.value.getT("Tag No: ", "टैग नंबर: ", "ଟ୍ୟାଗ୍ ନମ୍ବର: ") + (claim["tag"] ?: ""),
                    fontSize = 13.sp,
                    color = Color.Gray
                )
                Text(
                    text = languageState.value.getT("Reported On: ", "रिपोर्ट की तारीख: ", "ରିପୋର୍ଟ ତାରିଖ: ") + (claim["date"] ?: "") + ", " + (claim["time"] ?: ""),
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        }

        // Tabs
        val tabs = listOf("Overview", "Documents")
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
                            Text(languageState.value.getT("Claim Progress", "दावा प्रगति", "ଦାବି ପ୍ରଗତି"), fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            Spacer(modifier = Modifier.height(20.dp))
                            
                            val doneText = languageState.value.getT("Completed", "पूर्ण", "ସମ୍ପୂର୍ଣ୍ଣ")
                            val pendingText = languageState.value.getT("Pending", "लंबित", "ବିଚାରାଧୀନ")
                            val reviewSubtitle = when (claim["status"]) {
                                "approved", "claimed" -> languageState.value.getT("Approved", "स्वीकृत", "ମଞ୍ଜୁର")
                                "rejected" -> languageState.value.getT("Rejected", "अस्वीकृत", "ଅସ୍ୱୀକୃତ")
                                "hold" -> languageState.value.getT("On hold", "होल्ड पर", "ଅପେକ୍ଷାରେ")
                                else -> pendingText
                            }
                            val steps = listOf(
                                Triple(languageState.value.getT("Death Reported", "मृत्यु की सूचना", "ମୃତ୍ୟୁ ରିପୋର୍ଟ"),
                                    claim["date"].takeUnless { it.isNullOrBlank() || it == "—" } ?: doneText,
                                    claim["p_death"] == "completed"),
                                Triple(languageState.value.getT("Didi Site Visit", "दीदी साइट विज़िट", "ଦିଦି ସାଇଟ୍ ପରିଦର୍ଶନ"),
                                    if (claim["p_site"] == "completed") doneText else pendingText,
                                    claim["p_site"] == "completed"),
                                Triple(languageState.value.getT("Verification", "सत्यापन", "ଯାଞ୍ଚ"),
                                    if (claim["p_verify"] == "completed") doneText else pendingText,
                                    claim["p_verify"] == "completed"),
                                Triple(languageState.value.getT("Coordinator Review", "समन्वयक समीक्षा", "ସମନ୍ୱୟକ ସମୀକ୍ଷା"),
                                    reviewSubtitle,
                                    claim["p_review"] == "completed"),
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
                            Text(languageState.value.getT("Claim Information", "दावा जानकारी", "ଦାବି ସୂଚନା"), fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            Spacer(modifier = Modifier.height(16.dp))
                            
                            DidiInfoRow(languageState.value.getT("Policy No.", "पॉलिसी नंबर", "ପଲିସି ନମ୍ବର"), claim["policy"] ?: "")
                            DidiInfoRow(languageState.value.getT("Insured Goat", "बीमित बकरी", "ବୀମାଭୁକ୍ତ ଛେଳି"), claim["tag"] ?: "")
                            DidiInfoRow(languageState.value.getT("Date of Death", "मृत्यु की तिथि", "ମୃତ୍ୟୁ ତାରିଖ"), claim["date"] ?: "—")
                            DidiInfoRow(languageState.value.getT("Cause of Death", "मृत्यु का कारण", "ମୃତ୍ୟୁର କାରଣ"), claim["cause"] ?: "")
                            val vaccines = claim["vaccines"] ?: ""
                            if (vaccines.isNotBlank()) {
                                DidiInfoRow(languageState.value.getT("Vaccinations Completed", "पूर्ण टीकाकरण", "ସମ୍ପୂର୍ଣ୍ଣ ଟୀକାକରଣ"), "$vaccines / 4")
                            }
                            val amount = claim["amount"] ?: ""
                            if (amount.isNotBlank() && amount != "—") {
                                DidiInfoRow(languageState.value.getT("Entitled Amount", "पात्र राशि", "ହକଦାର ରାଶି"), "₹$amount")
                            }
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
                            Text(languageState.value.getT("Uploaded Documents", "अपलोड किए गए दस्तावेज़", "ଅପଲୋଡ୍ ହୋଇଥିବା ଦଲିଲ"), fontWeight = FontWeight.Bold, fontSize = 15.sp)
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
                                languageState.value.getT("All documents are secured and used only for claim verification.", "सभी दस्तावेज़ सुरक्षित हैं और केवल दावा सत्यापन के लिए उपयोग किए जाते हैं।", "ସମସ୍ତ ଦଲିଲ ସୁରକ୍ଷିତ ଏବଂ କେବଳ ଦାବି ଯାଞ୍ଚ ପାଇଁ ବ୍ୟବହୃତ ହୁଏ |"),
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
                languageState.value.getT("Next", "अगला", "ପରବର୍ତ୍ତୀ")
            else 
                languageState.value.getT("Submit", "सबमिट करें", "ସବମିଟ୍")
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

    var remarks by remember { mutableStateOf("") }

    val claimsVm: CoordinatorClaimsViewModel = hiltViewModel()
    val submitState by claimsVm.submit.collectAsState()
    val reviewState by claimsVm.review.collectAsState()
    LaunchedEffect(claimId) { claimsVm.loadReview(claimId) }
    val review = (reviewState as? UiState.Success)?.data
    val isSubmitting = submitState is SubmitState.Submitting
    var pendingAction by remember { mutableStateOf("") }
    LaunchedEffect(submitState) {
        when (val s = submitState) {
            is SubmitState.Success -> {
                claimsVm.resetSubmit()
                if (pendingAction == "approve") navController.navigate("claim_payout/$claimId")
                else navController.navigate("claim_list") { popUpTo("claim_list") { inclusive = true } }
            }
            is SubmitState.Error -> { Toast.makeText(context, s.message, Toast.LENGTH_LONG).show(); claimsVm.resetSubmit() }
            else -> {}
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(languageState.value.getT("Verify Claim", "दावा सत्यापित करें", "ଦାବି ଯାଞ୍ଚ କରନ୍ତୁ"), fontWeight = FontWeight.Bold, color = Color.White) },
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
                    text = review?.goat?.earTagNumber ?: claimId,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = languageState.value.getT("Verification Checklist", "सत्यापन चेकलिस्ट", "ଯାଞ୍ଚ ଚେକଲିଷ୍ଟ"),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))

            val checklistItems = listOf(
                languageState.value.getT("Ear Tag Verified", "कान का टैग सत्यापित", "କାନ ଟ୍ୟାଗ୍ ଯାଞ୍ଚ ହୋଇଛି"),
                languageState.value.getT("Photos Verified", "फोटो सत्यापित", "ଫଟୋ ଯାଞ୍ଚ ହୋଇଛି"),
                languageState.value.getT("Policy Active", "पॉलिसी सक्रिय", "ନୀତି ସକ୍ରିୟ"),
                languageState.value.getT("Vaccination Valid", "टीकाकरण वैध", "ଟୀକାକରଣ ବୈଧ")
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

            Spacer(modifier = Modifier.height(24.dp))

            // Tiered payout summary (playbook: entitlement by completed vaccinations).
            val vaccDone = review?.vaccinationsDone
            val entitled = (review?.claimAmount ?: review?.sumInsured)?.toInt()
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        languageState.value.getT("Payout Entitlement", "भुगतान पात्रता", "ଦେୟ ହକଦାରୀ"),
                        fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFFE65100)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(languageState.value.getT("Vaccinations completed", "पूर्ण टीकाकरण", "ସମ୍ପୂର୍ଣ୍ଣ ଟୀକାକରଣ"), fontSize = 13.sp, color = Color.DarkGray)
                        Text("${vaccDone ?: "—"} / 4", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(languageState.value.getT("Entitled amount", "पात्र राशि", "ହକଦାର ରାଶି"), fontSize = 13.sp, color = Color.DarkGray)
                        Text(if (entitled != null) "₹$entitled" else "—", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold, color = PrimaryGreen)
                    }
                    if (vaccDone == 0) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            languageState.value.getT("No vaccination record — claim must be rejected.", "कोई टीकाकरण रिकॉर्ड नहीं — दावा अस्वीकार करें।", "କୌଣସି ଟୀକାକରଣ ରେକର୍ଡ ନାହିଁ — ଦାବି ପ୍ରତ୍ୟାଖ୍ୟାନ କରନ୍ତୁ।"),
                            fontSize = 12.sp, color = Color(0xFFC62828), fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = languageState.value.getT("Remarks (Optional)", "टिप्पणियां (वैकल्पिक)", "ମନ୍ତବ୍ୟ (ବୈକଳ୍ପିକ)"),
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
                    pendingAction = "approve"
                    // Pay the playbook-tiered entitlement, not the full sum insured.
                    claimsVm.review(claimId, "approve", review?.claimAmount ?: review?.sumInsured)
                },
                enabled = !isSubmitting,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = themeColor)
            ) {
                Text(
                    text = languageState.value.getT("Approve Claim", "दावा स्वीकृत करें", "ଦାବି ଅନୁମୋଦନ କରନ୍ତୁ"),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    pendingAction = "reject"
                    claimsVm.review(claimId, "reject")
                },
                enabled = !isSubmitting,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
            ) {
                Text(
                    text = languageState.value.getT("Reject Claim", "दावा अस्वीकृत करें", "ଦାବି ପ୍ରତ୍ୟାଖ୍ୟାନ କରନ୍ତୁ"), 
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
                title = { Text(languageState.value.getT("Claim Payout", "दावा भुगतान", "ଦାବି ପରିଶୋଧ"), fontWeight = FontWeight.Bold, color = Color.White) },
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
                text = languageState.value.getT("Bank Details", "बैंक विवरण", "ବ୍ୟାଙ୍କ ବିବରଣୀ"),
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
                    PayoutDetailRow(languageState.value.getT("A/C Holder", "खाता धारक", "A/C ଧାରକ"), mockClaim["accHolder"] ?: "")
                    PayoutDetailRow(languageState.value.getT("A/C Number", "खाता संख्या", "A/C ନମ୍ବର"), mockClaim["accNumber"] ?: "")
                    PayoutDetailRow(languageState.value.getT("IFSC", "आईएफएससी", "IFSC"), mockClaim["ifsc"] ?: "")
                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
                    PayoutDetailRow(languageState.value.getT("Amount", "राशि", "ପରିମାଣ"), mockClaim["amount"] ?: "", isBold = true)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { isPayoutConfirmed = true },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = themeColor)
            ) {
                Text(languageState.value.getT("Confirm Payout", "भुगतान की पुष्टि करें", "ପରିଶୋଧ ନିଶ୍ଚିତ କରନ୍ତୁ"), fontWeight = FontWeight.Bold, fontSize = 16.sp)
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
                        Text(languageState.value.getT("Payout successful", "भुगतान सफल", "ପରିଶୋଧ ସଫଳ"), color = Color.Black, fontWeight = FontWeight.Medium)
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
                text = languageState.value.getT("Claim Approved", "दावा स्वीकृत", "ଦାବି ଅନୁମୋଦିତ"),
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
                        text = languageState.value.getT("Approved Amount", "स्वीकृत राशि", "ଅନୁମୋଦିତ ପରିମାଣ"),
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
                Text(text = languageState.value.getT("Payout Mode", "भुगतान का तरीका", "ପରିଶୋଧ ପଦ୍ଧତି"), style = MaterialTheme.typography.labelMedium, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = languageState.value.getT("Bank Transfer", "बैंक ट्रांसफर", "ବ୍ୟାଙ୍କ ଟ୍ରାନ୍ସଫର"), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = Color.Black)
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Text(text = languageState.value.getT("Status", "स्थिति", "ସ୍ଥିତି"), style = MaterialTheme.typography.labelMedium, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = languageState.value.getT("Approved", "स्वीकृत", "ଅନୁମୋଦିତ"), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = SuccessGreen)
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
                Text(languageState.value.getT("Mark as Paid", "भुगतान के रूप में चिह्नित करें", "ପରିଶୋଧିତ ଚିହ୍ନଟ କରନ୍ତୁ"), fontWeight = FontWeight.Bold, fontSize = 16.sp)
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
            Text(LocalAppLanguage.current.value.getT("View", "देखें", "ଦେଖନ୍ତୁ"), color = SuccessGreen, fontWeight = FontWeight.Bold, fontSize = 14.sp)
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
                        val isApproved = claim["p_review"] == "completed" && claim["status"] != "rejected"
                        val isRejected = claim["status"] == "rejected"
                        Column {
                            Text(
                                text = when {
                                    isApproved -> languageState.value.getT("Your Claim is Approved", "आपका दावा स्वीकृत है", "ଆପଣଙ୍କ ଦାବି ମଞ୍ଜୁର ହୋଇଛି")
                                    isRejected -> languageState.value.getT("Your Claim was Rejected", "आपका दावा अस्वीकृत हुआ", "ଆପଣଙ୍କ ଦାବି ଅସ୍ୱୀକୃତ ହୋଇଛି")
                                    else -> languageState.value.getT("Your Claim is in Progress", "आपका दावा प्रगति पर है", "ଆପଣଙ୍କ ଦାବି ପ୍ରଗତିରେ ଅଛି")
                                },
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = if (isRejected) Color(0xFFD32F2F) else themeColor
                            )
                            Text(
                                text = when {
                                    isApproved -> languageState.value.getT("Your claim has been approved.", "आपका दावा स्वीकृत कर दिया गया है।", "ଆପଣଙ୍କ ଦାବି ମଞ୍ଜୁର କରାଯାଇଛି |")
                                    isRejected -> languageState.value.getT("Please contact your Didi for details.", "विवरण के लिए अपनी दीदी से संपर्क करें।", "ବିବରଣୀ ପାଇଁ ଆପଣଙ୍କ ଦିଦିଙ୍କ ସହ ଯୋଗାଯୋଗ କରନ୍ତୁ |")
                                    else -> languageState.value.getT("We are reviewing your claim.", "हम आपके दावे की समीक्षा कर रहे हैं।", "ଆମେ ଆପଣଙ୍କ ଦାବିର ସମୀକ୍ଷା କରୁଛୁ |")
                                },
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(20.dp))
                    
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column {
                            Text(text = languageState.value.getT("Claim ID", "दावा आईडी", "ଦାବି ID"), fontSize = 12.sp, color = Color.Gray)
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
                            Text(text = languageState.value.getT("Reported On", "रिपोर्ट किया गया", "ରିପୋର୍ଟ କରାଯାଇଛି"), fontSize = 12.sp, color = Color.Gray)
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
                        text = languageState.value.getT("Claim Progress", "दावा प्रगति", "ଦାବି ପ୍ରଗତି"),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = themeColor
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    val siteDone = claim["p_site"] == "completed"
                    val verifyDone = claim["p_verify"] == "completed"
                    val reviewDone = claim["p_review"] == "completed"
                    val steps = listOf(
                        Triple(
                            languageState.value.getT("Death Reported", "मृत्यु की सूचना", "ମୃତ୍ୟୁ ରିପୋର୍ଟ"),
                            languageState.value.getT("You have reported the death of your goat.", "आपने अपनी बकरी की मृत्यु की सूचना दी है।", "ଆପଣ ଆପଣଙ୍କ ଛେଳିର ମୃତ୍ୟୁ ରିପୋର୍ଟ କରିଛନ୍ତି |"),
                            claim["p_death"] == "completed"
                        ),
                        Triple(
                            languageState.value.getT("Didi Site Visit", "दीदी साइट विज़िट", "ଦିଦି ସାଇଟ୍ ପରିଦର୍ଶନ"),
                            if (siteDone) languageState.value.getT("Our Didi has visited your location.", "हमारी दीदी ने आपके स्थान का दौरा किया है।", "ଆମ ଦିଦି ଆପଣଙ୍କ ସ୍ଥାନ ପରିଦର୍ଶନ କରିଛନ୍ତି |")
                            else languageState.value.getT("Our Didi will visit your location.", "हमारी दीदी आपके स्थान पर आएंगी।", "ଆମ ଦିଦି ଆପଣଙ୍କ ସ୍ଥାନ ପରିଦର୍ଶନ କରିବେ |"),
                            siteDone
                        ),
                        Triple(
                            languageState.value.getT("Verification", "सत्यापन", "ଯାଞ୍ଚ"),
                            if (verifyDone) languageState.value.getT("Verification completed successfully.", "सत्यापन सफलतापूर्वक पूरा हुआ।", "ଯାଞ୍ଚ ସଫଳତାର ସହିତ ସମ୍ପୂର୍ଣ୍ଣ ହୋଇଛି |")
                            else languageState.value.getT("Verification is pending.", "सत्यापन लंबित है।", "ଯାଞ୍ଚ ବିଚାରାଧୀନ ଅଛି |"),
                            verifyDone
                        ),
                        Triple(
                            languageState.value.getT("Coordinator Review", "समन्वयक समीक्षा", "ସମନ୍ୱୟକ ସମୀକ୍ଷା"),
                            when {
                                claim["status"] == "rejected" -> languageState.value.getT("Your claim was rejected.", "आपका दावा अस्वीकृत कर दिया गया।", "ଆପଣଙ୍କ ଦାବି ଅସ୍ୱୀକୃତ ହୋଇଛି |")
                                reviewDone -> languageState.value.getT("Your claim was approved.", "आपका दावा स्वीकृत हो गया।", "ଆପଣଙ୍କ ଦାବି ମଞ୍ଜୁର ହୋଇଛି |")
                                else -> languageState.value.getT("Your claim is under review.", "आपका दावा समीक्षाधीन है।", "ଆପଣଙ୍କ ଦାବି ସମୀକ୍ଷାଧୀନ ଅଛି |")
                            },
                            reviewDone
                        ),
                    )

                    steps.forEachIndexed { index, step ->
                        ClaimProgressItem(
                            stepNumber = index + 1,
                            title = step.first,
                            subtitle = step.second,
                            time = if (index == 0) (claim["deathDate"].takeUnless { it.isNullOrBlank() || it == "—" } ?: "") else "",
                            isCompleted = step.third,
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
                        text = languageState.value.getT("We'll notify you once there is an update on your claim.", "दावे पर अपडेट होने पर हम आपको सूचित करेंगे।", "ଦାବିରେ ଅପଡେଟ୍ ହେଲେ ଆମେ ଆପଣଙ୍କୁ ଜଣାଇବୁ |"),
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
                    text = languageState.value.getT("View Claim Details", "दावा विवरण देखें", "ଦାବି ବିବରଣୀ ଦେଖନ୍ତୁ"),
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
    val context = LocalContext.current
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf(
        languageState.value.getT("Details", "विवरण", "ବିବରଣୀ"),
        languageState.value.getT("Documents", "दस्तावेज़", "ଦଲିଲ"),
        languageState.value.getT("History", "इतिहास", "ଇତିହାସ")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(languageState.value.getT("Claim #${claim["id"]}", "दावा #${claim["id"]}", "ଦାବି #${claim["id"]}"), fontWeight = FontWeight.Bold) },
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
                        FarmerClaimInfoSection(languageState.value.getT("Claim Information", "दावा जानकारी", "ଦାବି ସୂଚନା"), themeColor) {
                            FarmerClaimInfoItem(languageState.value.getT("Claim ID", "दावा आईडी", "ଦାବି ID"), claim["id"] ?: "")
                            FarmerClaimInfoItem(languageState.value.getT("Policy Number", "पॉलिसी नंबर", "ନୀତି ନମ୍ବର"), claim["policy"] ?: "")
                            FarmerClaimInfoItem(languageState.value.getT("Insured Goat", "बीमित बकरी", "ବୀମାଭୁକ୍ତ ଛେଳି"), claim["tag"] ?: "")
                            FarmerClaimInfoItem(languageState.value.getT("Goat Name", "बकरी का नाम", "ଛେଳିର ନାମ"), "Rani")
                            FarmerClaimInfoItem(languageState.value.getT("Breed", "नस्ल", "ପ୍ରଜାତି"), claim["breed"] ?: "")
                            FarmerClaimInfoItem(languageState.value.getT("Date of Death", "मृत्यु की तिथि", "ମୃତ୍ୟୁ ତାରିଖ"), "19 May 2024")
                            FarmerClaimInfoItem(languageState.value.getT("Reported On", "रिपोर्ट किया गया", "ରିପୋର୍ଟ କରାଯାଇଛି"), claim["deathDate"] ?: "")
                            FarmerClaimInfoItem(languageState.value.getT("Cause of Death", "मृत्यु का कारण", "ମୃତ୍ୟୁର କାରଣ"), claim["deathCause"] ?: "")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        FarmerClaimInfoSection(languageState.value.getT("Farmer Information", "किसान जानकारी", "କୃଷକ ସୂଚନା"), themeColor) {
                            FarmerClaimInfoItem(languageState.value.getT("Farmer Name", "किसान का नाम", "କୃଷକଙ୍କ ନାମ"), claim["farmer"] ?: "")
                            FarmerClaimInfoItem(languageState.value.getT("Phone Number", "फ़ोन नंबर", "ଫୋନ୍ ନମ୍ବର"), claim["phone"] ?: "")
                            FarmerClaimInfoItem(languageState.value.getT("Village", "गांव", "ଗ୍ରାମ"), "Rampur")
                            FarmerClaimInfoItem(languageState.value.getT("Block", "ब्लॉक", "ବ୍ଲକ"), "Bhopal")
                            FarmerClaimInfoItem(languageState.value.getT("District", "जिला", "ଜିଲ୍ଲା"), "Bhopal, MP")
                        }
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        OutlinedButton(
                            onClick = { context.callSupport() },
                            modifier = Modifier.fillMaxWidth().height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, themeColor)
                        ) {
                            Icon(Icons.Default.Phone, null, tint = themeColor, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(languageState.value.getT("Contact Support", "सहायता से संपर्क करें", "ସହାୟତା ସହିତ ଯୋଗାଯୋଗ କରନ୍ତୁ"), color = themeColor, fontWeight = FontWeight.Bold)
                        }
                    }
                    1 -> { // Documents
                        Text(languageState.value.getT("Uploaded Documents", "अपलोड किए गए दस्तावेज़", "ଅପଲୋଡ୍ ହୋଇଥିବା ଦଲିଲ"), fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
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
                                Spacer(modifier = Modifier.width(16.dp))
                                Text(languageState.value.getT("All documents are secured and used only for claim verification.", "सभी दस्तावेज़ सुरक्षित हैं और केवल दावा सत्यापन के लिए उपयोग किए जाते हैं।", "ସମସ୍ତ ଦଲିଲ ସୁରକ୍ଷିତ ଏବଂ କେବଳ ଦାବି ଯାଞ୍ଚ ପାଇଁ ବ୍ୟବହୃତ ହୁଏ |"), fontSize = 12.sp, color = Color.Gray)
                            }
                        }
                    }
                    2 -> { // History
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth().padding(top = 40.dp)) {
                            Icon(Icons.Default.History, null, modifier = Modifier.size(48.dp), tint = Color.LightGray)
                            Text(languageState.value.getT("No history available.", "कोई इतिहास उपलब्ध नहीं है।", "କୌଣସି ଇତିହାସ ଉପଲବ୍ଧ ନାହିଁ |"), color = Color.Gray, modifier = Modifier.padding(top = 8.dp))
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
                Text(LocalAppLanguage.current.value.getT("View", "देखें", "ଦେଖନ୍ତୁ"), color = themeColor, fontWeight = FontWeight.Bold, fontSize = 12.sp)
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
    otp: String,
    onComplete: (UserRole?) -> Unit,   // role -> go to dashboard; null -> pending approval
    onBack: () -> Unit
) {
    val languageState = LocalAppLanguage.current
    val context = LocalContext.current

    val authViewModel: AuthViewModel = hiltViewModel()
    val authState by authViewModel.authState.collectAsState()
    val isSubmitting = authState is AuthState.Loading
    LaunchedEffect(authState) {
        when (val s = authState) {
            is AuthState.Authenticated -> { onComplete(s.role); authViewModel.reset() }
            is AuthState.SignupPendingApproval -> { onComplete(null); authViewModel.reset() }
            is AuthState.Error -> {
                Toast.makeText(context, s.message, Toast.LENGTH_LONG).show()
                authViewModel.reset()
            }
            else -> {}
        }
    }
    
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
            Toast.makeText(context, languageState.value.getT("Required permissions denied. Closing app.", "आवश्यक अनुमतियां अस्वीकार कर दी गईं। ऐप बंद हो रहा है।", "ଆବଶ୍ୟକ ଅନୁମତି ପ୍ରତ୍ୟାଖ୍ୟାନ କରାଯାଇଛି | ଆପ୍ ବନ୍ଦ ହେଉଛି |"), Toast.LENGTH_LONG).show()
            (context as? Activity)?.finish()
        }
    }

    if (showPermissionRationale) {
        AlertDialog(
            onDismissRequest = { (context as? Activity)?.finish() },
            title = { Text(languageState.value.getT("Permissions Required", "अनुमतियाँ आवश्यक हैं", "ଅନୁମତି ଆବଶ୍ୟକ"), fontWeight = FontWeight.Bold, color = Color.Black) },
            text = { 
                Text(languageState.value.getT(
                    "This app needs Camera and Location access to verify livestock and record service areas. If you deny, the app will close.",
                    "पशुधन को सत्यापित करने और सेवा क्षेत्रों को रिकॉर्ड करने के लिए इस ऐप को कैमरा और स्थान पहुंच की आवश्यकता है। यदि आप अस्वीकार करते हैं, तो ऐप बंद हो जाएगा।",
                    "ପ୍ରାଣୀସମ୍ପଦ ଯାଞ୍ଚ କରିବା ଏବଂ ସେବା କ୍ଷେତ୍ର ରେକର୍ଡ କରିବାକୁ ଏହି ଆପ୍‌ କ୍ୟାମେରା ଏବଂ ଅବସ୍ଥାନ ଅନୁମତି ଆବଶ୍ୟକ କରେ | ଯଦି ଆପଣ ପ୍ରତ୍ୟାଖ୍ୟାନ କରନ୍ତି, ଆପ୍ ବନ୍ଦ ହୋଇଯିବ |"
                ), color = Color.Black)
            },
            confirmButton = {
                Button(
                    onClick = {
                        showPermissionRationale = false
                        permissionLauncher.launch(permissionsToRequest)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
                ) {
                    Text(languageState.value.getT("Allow", "अनुमति दें", "ଅନୁମତି ଦିଅନ୍ତୁ"))
                }
            },
            dismissButton = {
                TextButton(onClick = { (context as? Activity)?.finish() }) {
                    Text(languageState.value.getT("Deny & Close", "अस्वीकार करें और बंद करें", "ପ୍ରତ୍ୟାଖ୍ୟାନ କରନ୍ତୁ ଏବଂ ବନ୍ଦ କରନ୍ତୁ"), color = Color.Red)
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
    var gender by remember { mutableStateOf("Female") }
    
    var state by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }
    var block by remember { mutableStateOf("") }
    var village by remember { mutableStateOf("") }
    var pincode by remember { mutableStateOf("") }
    
    var aadhaarNumber by remember { mutableStateOf("") }
    var aadhaarPhotoUri by remember { mutableStateOf<Uri?>(null) }

    var signupPassword by remember { mutableStateOf("") }
    var signupPasswordConfirm by remember { mutableStateOf("") }

    // Cascading location dropdowns served by the backend (State -> District -> Block).
    val locationVm: LocationViewModel = hiltViewModel()
    val stateOptions by locationVm.states.collectAsState()
    val districtOptions by locationVm.districts.collectAsState()
    val blockOptions by locationVm.blocks.collectAsState()
    val autoPincode by locationVm.pincode.collectAsState()
    LaunchedEffect(state) { district = ""; block = ""; locationVm.loadDistricts(state) }
    LaunchedEffect(district) { block = ""; locationVm.loadBlocks(state, district) }
    LaunchedEffect(block) { locationVm.loadPincode(state, district, block) }
    // Auto-fill the pincode when a block is picked (still editable).
    LaunchedEffect(autoPincode) { if (autoPincode.isNotBlank()) pincode = autoPincode }

    val themeColor = when(role) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }

    val locationLabel = if (role == UserRole.COORDINATOR) 
        languageState.value.getT("Location *", "स्थान *", "ଅବସ୍ଥାନ *")
    else 
        languageState.value.getT("Village *", "गाँव *", "ଗ୍ରାମ *")

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
                title = { Text(languageState.value.getT("Setup Profile", "प्रोफ़ाइल सेटअप", "ପ୍ରୋଫାଇଲ୍ ସେଟଅପ୍"), color = Color.White, fontWeight = FontWeight.Bold) },
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
            ProfileSetupSection(languageState.value.getT("Basic Info", "기본 정보", "ମୌଳିକ ସୂଚନା"), themeColor) {
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
                    label = languageState.value.getT("Full Name *", "पूरा नाम *", "ପୁରା ନାମ *"),
                    value = name,
                    onValueChange = { if (it.all { char -> char.isLetter() || char.isWhitespace() }) name = it },
                    borderColor = themeColor
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                EnrollmentTextField(
                    label = languageState.value.getT("Phone Number", "फोन नंबर", "ଫୋନ୍ ନମ୍ବର"),
                    value = phone,
                    onValueChange = {},
                    readOnly = true,
                    borderColor = themeColor
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                EnrollmentTextField(
                    label = languageState.value.getT("Date of Birth *", "जन्म तिथि *", "ଜନ୍ମ ତାରିଖ *"),
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
                    label = languageState.value.getT("Gender *", "लिंग *", "ଲିଙ୍ଗ *"),
                    selectedValue = gender,
                    options = listOf("Female", "Male", "Other"),
                    onValueChange = { gender = it },
                    borderColor = themeColor
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Section 2: Location (cascading dropdowns from backend)
            ProfileSetupSection(languageState.value.getT("Location", "स्थान", "ଅବସ୍ଥାନ"), themeColor) {
                EnrollmentDropdownField(
                    label = languageState.value.getT("State *", "राज्य *", "ରାଜ୍ୟ *"),
                    selectedValue = state,
                    options = stateOptions,
                    onValueChange = { state = it },
                    borderColor = themeColor
                )
                Spacer(modifier = Modifier.height(12.dp))
                EnrollmentDropdownField(
                    label = languageState.value.getT("District *", "जिला *", "ଜିଲ୍ଲା *"),
                    selectedValue = district,
                    options = districtOptions,
                    onValueChange = { district = it },
                    borderColor = themeColor
                )
                Spacer(modifier = Modifier.height(12.dp))
                EnrollmentDropdownField(
                    label = languageState.value.getT("Block *", "ब्लॉक *", "ବ୍ଲକ *"),
                    selectedValue = block,
                    options = blockOptions,
                    onValueChange = { block = it },
                    borderColor = themeColor
                )
                Spacer(modifier = Modifier.height(12.dp))
                EnrollmentTextField(
                    label = locationLabel,
                    value = village,
                    onValueChange = { village = it },
                    borderColor = themeColor
                )
                Spacer(modifier = Modifier.height(12.dp))
                EnrollmentTextField(label = languageState.value.getT("Pincode *", "पिनकोड *", "ପିନକୋଡ୍ *"), value = pincode, onValueChange = { if(it.length <= 6) pincode = it }, keyboardType = KeyboardType.Number, borderColor = themeColor)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Section 3: Identity/KYC
            ProfileSetupSection(languageState.value.getT("Identity / KYC", "पहचान / केवाईसी", "ପରିଚୟ / KYC"), themeColor) {
                EnrollmentTextField(label = languageState.value.getT("Aadhaar Number *", "आधार नंबर *", "ଆଧାର ନମ୍ବର *"), value = aadhaarNumber, onValueChange = { if(it.length <= 12) aadhaarNumber = it }, keyboardType = KeyboardType.Number, borderColor = themeColor)
                Spacer(modifier = Modifier.height(16.dp))
                Text(languageState.value.getT("Aadhaar Photo (Optional)", "आधार फोटो (वैकल्पिक)", "ଆଧାର ଫଟୋ (ବୈକଳ୍ପିକ)"), fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
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

            Spacer(modifier = Modifier.height(24.dp))

            // Section 4: Login credentials (used once the admin approves the registration)
            ProfileSetupSection(languageState.value.getT("Login Password", "लॉगिन पासवर्ड", "ଲଗଇନ୍ ପାସୱାର୍ଡ"), themeColor) {
                Text(
                    languageState.value.getT(
                        "You will use this password to log in after your registration is approved.",
                        "अनुमोदन के बाद लॉगिन करने के लिए आप इस पासवर्ड का उपयोग करेंगे।",
                        "ଅନୁମୋଦନ ପରେ ଲଗଇନ୍ କରିବାକୁ ଆପଣ ଏହି ପାସୱାର୍ଡ ବ୍ୟବହାର କରିବେ |"
                    ),
                    fontSize = 12.sp, color = Color.Gray
                )
                Spacer(modifier = Modifier.height(12.dp))
                EnrollmentTextField(
                    label = languageState.value.getT("Password (min 6 characters) *", "पासवर्ड (न्यूनतम 6 अक्षर) *", "ପାସୱାର୍ଡ (ସର୍ବନିମ୍ନ 6 ଅକ୍ଷର) *"),
                    value = signupPassword,
                    onValueChange = { signupPassword = it },
                    keyboardType = KeyboardType.Password,
                    borderColor = themeColor,
                    isPassword = true
                )
                Spacer(modifier = Modifier.height(12.dp))
                EnrollmentTextField(
                    label = languageState.value.getT("Confirm Password *", "पासवर्ड की पुष्टि करें *", "ପାସୱାର୍ଡ ନିଶ୍ଚିତ କରନ୍ତୁ *"),
                    value = signupPasswordConfirm,
                    onValueChange = { signupPasswordConfirm = it },
                    keyboardType = KeyboardType.Password,
                    borderColor = themeColor,
                    isPassword = true
                )
                if (signupPasswordConfirm.isNotEmpty() && signupPassword != signupPasswordConfirm) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        languageState.value.getT("Passwords do not match", "पासवर्ड मेल नहीं खाते", "ପାସୱାର୍ଡ ମେଳ ଖାଉନାହିଁ"),
                        fontSize = 12.sp, color = Color.Red
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    authViewModel.completeSignup(
                        fullName = name, phone = phone, role = role, otp = otp,
                        village = village, aadhaarId = aadhaarNumber,
                        password = signupPassword,
                    )
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = themeColor),
                enabled = !isSubmitting && name.isNotBlank() && dob.isNotBlank() && state.isNotBlank() && district.isNotBlank() && block.isNotBlank() && village.isNotBlank() && pincode.length == 6 && aadhaarNumber.length == 12 && signupPassword.length >= 6 && signupPassword == signupPasswordConfirm
            ) {
                Text(languageState.value.getT("Save & Continue", "सहेजें और जारी रखें", "ସଂରକ୍ଷଣ ଏବଂ ଜାରି ରଖନ୍ତୁ"), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun PendingApprovalScreen(onBackToLogin: () -> Unit) {
    val languageState = LocalAppLanguage.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9F5))
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(modifier = Modifier.size(96.dp), shape = CircleShape, color = Color(0xFFFFF3E0)) {
            Box(contentAlignment = Alignment.Center) {
                Icon(Icons.Default.HourglassEmpty, contentDescription = null, tint = CoordinatorOrange, modifier = Modifier.size(52.dp))
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            languageState.value.getT("Account Pending Approval", "खाता अनुमोदन लंबित", "ଖାତା ଅନୁମୋଦନ ବାକି ଅଛି"),
            style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold,
            color = Color.Black, textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            languageState.value.getT(
                "Your registration was received. An administrator needs to approve your account before you can log in. Please check back later.",
                "आपका पंजीकरण प्राप्त हो गया है। लॉगिन करने से पहले एक व्यवस्थापक को आपके खाते को स्वीकृत करना होगा। कृपया बाद में पुनः जांचें।",
                "ଆପଣଙ୍କ ପଞ୍ଜିକରଣ ଗ୍ରହଣ କରାଯାଇଛି। ଲଗଇନ୍ କରିବା ପୂର୍ବରୁ ଜଣେ ପ୍ରଶାସକ ଆପଣଙ୍କ ଖାତା ଅନୁମୋଦନ କରିବେ। ଦୟାକରି ପରେ ପୁଣି ଯାଞ୍ଚ କରନ୍ତୁ।"
            ),
            style = MaterialTheme.typography.bodyMedium, color = Color.Gray, textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onBackToLogin,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
        ) {
            Text(languageState.value.getT("Back to Login", "लॉगिन पर वापस जाएं", "ଲଗଇନ୍ କୁ ଫେରନ୍ତୁ"), fontWeight = FontWeight.Bold, fontSize = 16.sp)
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

    val earningsVm: EarningsViewModel = hiltViewModel()
    val earningsState by earningsVm.state.collectAsState()
    val earningsData = (earningsState as? UiState.Success)?.data

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
                        languageState.value.getT("Earning History", "आय का इतिहास", "ଉପାର୍ଜନ ଇତିହାସ"),
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
                        Text(languageState.value.getT("Total Earnings", "कुल आय", "ମୋଟ ଉପାର୍ଜନ"), color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                        Text("₹ ${earningsData?.total?.toInt() ?: 0}", style = MaterialTheme.typography.headlineLarge, color = Color.White, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(12.dp))
                        Surface(color = Color.White.copy(alpha = 0.2f), shape = RoundedCornerShape(12.dp)) {
                            Text(
                                text = languageState.value.getT("This Month", "इस महीने", "ଏହି ମାସ"),
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
                languageState.value.getT("Recent Transactions", "हाल के लेनदेन", "ସାମ୍ପ୍ରତିକ କାରବାର"),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            val enrollIcon = painterResource(R.drawable.ic_ewe_custom)
            val earnings = (earningsData?.items ?: emptyList()).map { e ->
                val isVacc = e.source == "vaccination"
                EarningData(
                    title = if (isVacc) languageState.value.getT("Vaccination Fee", "टीकाकरण शुल्क", "ଟୀକାକରଣ ଶୁଳ୍କ")
                            else languageState.value.getT("Goat Enrollment", "बकरी नामांकन", "ଛେଳି ପଞ୍ଜିକରଣ"),
                    subtitle = e.earTagNumber ?: "—",
                    amount = "+ ₹${e.amount.toInt()}",
                    time = e.earnedOn ?: "",
                    icon = if (isVacc) Icons.Default.MedicalServices else enrollIcon,
                    color = if (isVacc) InfoBlue else PrimaryGreen,
                )
            }
            if (earnings.isEmpty()) {
                Text(languageState.value.getT("No earnings yet.", "अभी तक कोई आय नहीं।", "ଏପର୍ଯ୍ୟନ୍ତ କୌଣସି ଉପାର୍ଜନ ନାହିଁ।"), color = Color.Gray, fontSize = 13.sp)
            }
            earnings.forEach { data ->
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
            context.callSupport()
            callState = "InCall"
        } else if (callState == "InCall") {
            kotlinx.coroutines.delay(2000)
            onCallEnded(SupportContact.DISPLAY, "00:07:24", "20 May 2024, 10:30 AM")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(languageState.value.getT("Call Support", "कॉल सहायता", "କଲ୍ ସହାୟତା"), fontWeight = FontWeight.Bold, color = Color.White) },
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
                    text = languageState.value.getT("We're just a call away", "हम बस एक कॉल दूर हैं", "ଆମେ ମାତ୍ର ଏକ କଲ୍ ଦୂରରେ"),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = languageState.value.getT("Speak with our support team for quick assistance and guidance.", "त्वरित सहायता और मार्गदर्शन के लिए हमारी सहायता टीम से बात करें।", "ତ୍ୱରିତ ସହାୟତା ଏବଂ ମାର୍ଗଦର୍ଶନ ପାଇଁ ଆମର ସହାୟତା ଟିମ୍ ସହିତ କଥା ହୁଅନ୍ତୁ |"),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(40.dp))
                Card(
                    onClick = { context.callSupport() },
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
                            Text(SupportContact.DISPLAY, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = themeColor)
                            Text(languageState.value.getT("Tap to Chat or Call", "चैट या कॉल करने के लिए टैप करें", "ଚାଟ୍ କିମ୍ବା କଲ୍ କରିବାକୁ ଟ୍ୟାପ୍ କରନ୍ତୁ"), color = themeColor, fontSize = 14.sp)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(themeColor))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(languageState.value.getT("Available 24/7", "24/7 उपलब्ध", "ଉପଲବ୍ଧ ୨୪/୭"), color = Color.Gray, fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.height(32.dp))
                CallSupportFeatureItem(themeColor, Icons.Default.History, languageState.value.getT("Quick Connect", "त्वरित कनेक्ट", "ତ୍ୱରିତ ସଂଯୋଗ"), languageState.value.getT("Get connected to an agent within minutes.", "मिनटों के भीतर एक एजेंट से जुड़ें।", "କିଛି ମିନିଟ୍ ମଧ୍ୟରେ ଜଣେ ଏଜେଣ୍ଟଙ୍କ ସହିତ ସଂଯୋଗ କରନ୍ତୁ |"))
                CallSupportFeatureItem(themeColor, Icons.Default.VerifiedUser, languageState.value.getT("Secure & Private", "सुरक्षित और निजी", "ସୁରକ୍ଷା ଏବଂ ବ୍ୟକ୍ତିଗତ"), languageState.value.getT("Your call is safe and encrypted.", "आपकी कॉल सुरक्षित और एन्क्रिप्टेड है।", "ଆପଣଙ୍କର କଲ୍ ସୁରକ୍ଷିତ ଏବଂ ଏନକ୍ରିପ୍ଟ ଅଟେ |"))
                CallSupportFeatureItem(themeColor, Icons.Default.SupportAgent, languageState.value.getT("Trained Experts", "प्रशिक्षित विशेषज्ञ", "ପ୍ରଶିକ୍ଷିତ ବିଶେଷଜ୍ଞ"), languageState.value.getT("Talk to our experienced support specialists.", "हमारे अनुभवी सहायता विशेषज्ञों से बात करें।", "ଆମର ଅଭିଜ୍ଞ ସହାୟତା ବିଶେଷଜ୍ଞଙ୍କ ସହିତ କଥା ହୁଅନ୍ତୁ |"))
                
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
                            Text(languageState.value.getT("Support Hours", "सहायता के घंटे", "ସହାୟତା ସମୟ"), fontWeight = FontWeight.Bold, fontSize = 14.sp)
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
                    text = if (callState == "Connecting") languageState.value.getT("Connecting...", "जुड़ रहा है...", "ସଂଯୋଗ ହେଉଛି...") else languageState.value.getT("In Call", "कॉल पर", "କଲ୍ ରେ"),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (callState == "Connecting") languageState.value.getT("System is connecting the call to an agent", "सिस्टम एक एजेंट को कॉल कनेक्ट कर रहा है", "ସିଷ୍ଟମ ଜଣେ ଏଜେଣ୍ଟଙ୍କୁ କଲ୍ ସଂଯୋଗ କରୁଛି") else languageState.value.getT("User speaks with the support executive", "उपयोगकर्ता सहायता कार्यकारी के साथ बात करता है", "ଉପଭୋକ୍ତା ସହାୟତା କାର୍ଯ୍ୟନିର୍ବାହୀଙ୍କ ସହ କଥା ହୁଅନ୍ତି"),
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
    val languageState = LocalAppLanguage.current
    val themeColor = when(userRole) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }
    
    var rating by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(languageState.value.getT("Call Summary", "कॉल सारांश", "କଲ୍ ସାରାଂଶ"), fontWeight = FontWeight.Bold, color = Color.White) },
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
                text = languageState.value.getT("Thank you!", "धन्यवाद!", "ଧନ୍ୟବାଦ!"),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = languageState.value.getT("Your call has ended.", "आपकी कॉल समाप्त हो गई है।", "ଆପଣଙ୍କର କଲ୍ ସମାପ୍ତ ହୋଇଛି |"),
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
                        text = languageState.value.getT("Call Details", "कॉल विवरण", "କଲ୍ ବିବରଣୀ"),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CallDetailRow(themeColor, Icons.Default.Phone, languageState.value.getT("Number", "नंबर", "ନମ୍ବର"), number)
                    CallDetailRow(themeColor, Icons.Default.History, languageState.value.getT("Duration", "अवधि", "ଅବଧି"), duration)
                    CallDetailRow(themeColor, Icons.Default.CalendarToday, languageState.value.getT("Time", "समय", "ସମୟ"), time)
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = languageState.value.getT("How was your experience?", "आपका अनुभव कैसा रहा?", "ଆପଣଙ୍କ ଅଭିଜ୍ଞତା କିପରି ଥିଲା?"),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = languageState.value.getT("Your feedback helps us improve.", "आपकी प्रतिक्रिया हमें बेहतर बनाने में मदद करती है।", "ଆପଣଙ୍କ ମତାମତ ଆମକୁ ଉନ୍ନତି କରିବାରେ ସାହାଯ୍ୟ କରେ |"),
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
                Text(languageState.value.getT("Back to Home", "होम पर वापस जाएं", "ମୁଖ୍ୟ ପୃଷ୍ଠାକୁ ଫେରନ୍ତୁ"), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onCallAgain,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color.LightGray)
            ) {
                Text(languageState.value.getT("Call Again", "फिर से कॉल करें", "ପୁଣି କଲ୍ କରନ୍ତୁ"), color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
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
fun FaqsScreen(userRole: UserRole?, onBack: () -> Unit, onChatSupport: () -> Unit) {
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
        FaqData(
            languageState.value.getT("What is Goat Insurance?", "बकरी बीमा क्या है?", "ଛେଳି ବୀମା କ’ଣ?"),
            languageState.value.getT(
                "Goat Insurance is a protection plan that helps you in case of death of your insured goat due to accidents, diseases or natural calamities. You will receive compensation as per the policy terms and conditions.",
                "बकरी बीमा एक सुरक्षा योजना है जो दुर्घटनाओं, बीमारियों या प्राकृतिक आपदाओं के कारण आपकी बीमित बकरी की मृत्यु के मामले में आपकी सहायता करती है। आपको पॉलिसी के नियमों और शर्तों के अनुसार मुआवजा मिलेगा।",
                "ଛେଳି ବୀମା ହେଉଛି ଏକ ସୁରକ୍ଷା ଯୋଜନା ଯାହା ଦୁର୍ଘଟଣା, ରୋଗ କିମ୍ବା ପ୍ରାକୃତିକ ବିପର୍ଯ୍ୟୟ ଯୋଗୁଁ ତୁମର ବୀମାଭୁକ୍ତ ଛେଳିର ମୃତ୍ୟୁ ହେଲେ ତୁମକୁ ସାହାଯ୍ୟ କରେ | ପଲିସିର ସର୍ତ୍ତାବଳୀ ଅନୁଯାୟୀ ଆପଣ କ୍ଷତିପୂରଣ ପାଇବେ |"
            ),
            "Insurance"
        ),
        FaqData(
            languageState.value.getT("How do I register my goat for insurance?", "मैं बीमा के लिए अपनी बकरी को कैसे पंजीकृत करूं?", "ବୀମା ପାଇଁ ମୋ ଛେଳିକୁ କିପରି ପଞ୍ଜିକରଣ କରିବି?"),
            languageState.value.getT("You can register via the app by providing farmer and goat details, and uploading photos.", "आप किसान और बकरी का विवरण प्रदान करके और फोटो अपलोड करके ऐप के माध्यम से पंजीकरण कर सकते हैं।", "ଆପଣ କୃଷକ ଏବଂ ଛେଳି ବିବରଣୀ ପ୍ରଦାନ କରି ଏବଂ ଫଟୋ ଅପଲୋଡ୍ କରି ଆପ୍ ମାଧ୍ୟମରେ ପଞ୍ଜିକରଣ କରିପାରିବେ |"),
            "Insurance"
        ),
        FaqData(
            languageState.value.getT("What documents are required for registration?", "पंजीकरण के लिए कौन से दस्तावेज आवश्यक हैं?", "ପଞ୍ଜିକରଣ ପାଇଁ କେଉଁ ଦଲିଲ ଆବଶ୍ୟକ?"),
            languageState.value.getT("Aadhaar card, farmer details, and clear photos of the goat from different angles are required.", "आधार कार्ड, किसान का विवरण और विभिन्न कोणों से बकरी की स्पष्ट फोटो आवश्यक है।", "ଆଧାର କାର୍ଡ, କୃଷକଙ୍କ ବିବରଣୀ ଏବଂ ବିଭିନ୍ନ କୋଣରୁ ଛେଳିର ସ୍ପଷ୍ଟ ଫଟୋ ଆବଶ୍ୟକ |"),
            "Policy"
        ),
        FaqData(
            languageState.value.getT("How can I check my policy status?", "मैं अपनी पॉलिसी की स्थिति कैसे जांच सकता हूं?", "ମୁଁ ମୋର ପଲିସି ସ୍ଥିତି କିପରି ଯାଞ୍ଚ କରିପାରିବି?"),
            languageState.value.getT("Go to the 'Goat List' or 'Policy' section in your dashboard to view active and expired policies.", "सक्रिय और समाप्त नीतियों को देखने के लिए अपने डैशबोर्ड में 'बकरी सूची' या 'पॉलिसी' अनुभाग पर जाएं।", "ସକ୍ରିୟ ଏବଂ ସମାପ୍ତ ପଲିସିଗୁଡିକ ଦେଖିବା ପାଇଁ ଆପଣଙ୍କର ଡ୍ୟାସବୋର୍ଡର 'ଛେଳି ତାଲିକା' କିମ୍ବା 'ପଲିସି' ବିଭାଗକୁ ଯାଆନ୍ତୁ |"),
            "Policy"
        ),
        FaqData(
            languageState.value.getT("How do I file a claim?", "मैं दावा कैसे दायर करूं?", "ମୁଁ କିପରି ଦାବି ଦାଖଲ କରିବି?"),
            languageState.value.getT("Report the goat's death immediately through the app, upload photos, and Didi will visit for verification.", "ऐप के माध्यम से तुरंत बकरी की मृत्यु की सूचना दें, फोटो अपलोड करें, और दीदी सत्यापन के लिए आएंगी।", "ଆପ୍ ମାଧ୍ୟମରେ ଛେଳିର ମୃତ୍ୟୁ ବିଷୟରେ ତୁରନ୍ତ ସୂଚନା ଦିଅନ୍ତୁ, ଫଟୋ ଅପଲୋଡ୍ କରନ୍ତୁ ଏବଂ ଦିଦି ଯାଞ୍ଚ ପାଇଁ ଆସିବେ |"),
            "Claims"
        ),
        FaqData(
            languageState.value.getT("How long does claim approval take?", "दावा स्वीकृति में कितना समय लगता है?", "ଦାବି ଅନୁମୋଦନ ପାଇଁ କେତେ ସମୟ ଲାଗେ?"),
            languageState.value.getT("Usually, it takes 7-10 working days after the verification is completed by the coordinator.", "आमतौर पर, समन्वयक द्वारा सत्यापन पूरा होने के बाद 7-10 कार्य दिवस लगते हैं।", "ସାଧାରଣତଃ, ସମନ୍ଵୟକାରୀଙ୍କ ଦ୍ୱାରା ଯାଞ୍ଚ ଶେଷ ହେବାର ୭-୧୦ କାର୍ଯ୍ୟ ଦିବସ ଲାଗିଥାଏ |"),
            "Claims"
        ),
        FaqData(
            languageState.value.getT("What is the claim settlement ratio?", "दावा निपटान अनुपात क्या है?", "ଦାବି ସମାଧାନ ଅନୁପାତ କ’ଣ?"),
            languageState.value.getT("We have a high claim settlement ratio as the process is transparent and assisted by Suraksha Didis.", "हमारे पास उच्च दावा निपटान अनुपात है क्योंकि प्रक्रिया पारदर्शी है और सुरक्षा दीदियों द्वारा सहायता प्राप्त है।", "ଆମର ଏକ ଉଚ୍ଚ ଦାବି ସମାଧାନ ଅନୁପାତ ଅଛି କାରଣ ପ୍ରକ୍ରିୟା ସ୍ୱଚ୍ଛ ଏବଂ ସୁରକ୍ଷା ଦିଦିଙ୍କ ଦ୍ୱାରା ସହାୟତା ପ୍ରାପ୍ତ |"),
            "Claims"
        ),
        FaqData(
            languageState.value.getT("Can I cancel my policy?", "क्या मैं अपनी पॉलिसी रद्द कर सकता हूँ?", "ମୁଁ ମୋର ପଲିସି ବାତିଲ୍ କରିପାରିବି କି?"),
            languageState.value.getT("Yes, policies can be cancelled within a specific period. Please contact support for assistance.", "हाँ, विशिष्ट अवधि के भीतर नीतियां रद्द की जा सकती हैं। सहायता के लिए कृपया समर्थन से संपर्क करें।", "ହଁ, ଏକ ନିର୍ଦ୍ଦିଷ୍ଟ ସମୟ ମଧ୍ୟରେ ପଲିସି ବାତିଲ କରାଯାଇପାରିବ | ସହାୟତା ପାଇଁ ଦୟାକରି ସମର୍ଥନ ସହିତ ଯୋଗାଯୋଗ କରନ୍ତୁ |"),
            "Policy"
        )
    )

    val filteredFaqs = faqs.filter { 
        (selectedCategory == "All" || it.category == selectedCategory) &&
        (it.question.contains(searchQuery, ignoreCase = true) || it.answer.contains(searchQuery, ignoreCase = true))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(languageState.value.getT("FAQs", "सामान्य प्रश्न", "FAQs"), fontWeight = FontWeight.Bold) },
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
                placeholder = { Text(languageState.value.getT("Search questions...", "प्रश्न खोजें...", "ପ୍ରଶ୍ନ ଖୋଜନ୍ତୁ...")) },
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
                    Text(languageState.value.getT("No FAQs found", "कोई प्रश्न नहीं मिला", "କୌଣସି FAQs ମିଳିଲା ନାହିଁ"), fontWeight = FontWeight.Bold, color = Color.Gray)
                    TextButton(onClick = { searchQuery = ""; selectedCategory = "All" }) {
                        Text(languageState.value.getT("Clear Search", "खोज साफ़ करें", "ସନ୍ଧାନ ସଫା କରନ୍ତୁ"), color = themeColor)
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
                onClick = onChatSupport,
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
                        Text(languageState.value.getT("Still need help?", "अभी भी मदद चाहिए?", "ଏବେ ବି ସାହାଯ୍ୟ ଦରକାର କି?"), fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        Text(languageState.value.getT("Chat with our support team", "हमारी सहायता टीम के साथ चैट करें", "ଆମର ସହାୟତା ଟିମ୍ ସହିତ ଚାଟ୍ କରନ୍ତୁ"), fontSize = 13.sp, color = Color.Gray)
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
                    Text(languageState.value.getT("Was this helpful?", "क्या यह उपयोगी था?", "ଏହା ସାହାଯ୍ୟକାରୀ ଥିଲା କି?"), fontSize = 12.sp, color = Color.Gray)
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
    val context = LocalContext.current
    val themeColor = when (userRole) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(languageState.value.getT("Contact Support", "सहायता से संपर्क करें", "ସହାୟତା ସହିତ ଯୋଗାଯୋଗ କରନ୍ତୁ"), fontWeight = FontWeight.Bold, color = Color.White) },
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
                text = languageState.value.getT("We're here to help you!", "हम आपकी मदद के लिए यहां हैं!", "ଆମେ ଆପଣଙ୍କୁ ସାହାଯ୍ୟ କରିବାକୁ ଏଠାରେ ଅଛୁ!"),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = languageState.value.getT("Chat with our support team for quick solutions.", "त्वरित समाधान के लिए हमारी सहायता टीम के साथ चैट करें।", "ତ୍ୱରିତ ସମାଧାନ ପାଇଁ ଆମର ସହାୟତା ଟିମ୍ ସହିତ ଚାଟ୍ କରନ୍ତୁ |"),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))
            SupportFeatureItem(themeColor, Icons.Default.History, languageState.value.getT("Quick Response", "त्वरित प्रतिक्रिया", "ତ୍ୱରିତ ପ୍ରତିକ୍ରିୟା"), languageState.value.getT("We typically reply within a few minutes.", "हम आम तौर पर कुछ ही मिनटों में जवाब देते हैं।", "ଆମେ ସାଧାରଣତଃ କିଛି ମିନିଟ୍ ମଧ୍ୟରେ ଉତ୍ତର ଦେଇଥାଉ |"))
            SupportFeatureItem(themeColor, Icons.Default.Person, languageState.value.getT("Expert Support", "विशेषज्ञ सहायता", "ବିଶେଷଜ୍ଞ ସହାୟତା"), languageState.value.getT("Connect with our trained experts.", "हमारे प्रशिक्षित विशेषज्ञों से जुड़ें।", "ଆମର ପ୍ରଶିକ୍ଷିତ ବିଶେଷଜ୍ଞଙ୍କ ସହିତ ଯୋଗାଯୋଗ କରନ୍ତୁ |"))
            SupportFeatureItem(themeColor, Icons.Default.VerifiedUser, languageState.value.getT("Safe & Secure", "सुरक्षित और सुरक्षित", "ସୁରକ୍ଷିତ ଏବଂ ନିରାପଦ"), languageState.value.getT("Your conversations are private and secure.", "आपकी बातचीत निजी और सुरक्षित है।", "ଆପଣଙ୍କର କଥାବାର୍ତ୍ତା ବ୍ୟକ୍ତିଗତ ଏବଂ ସୁରକ୍ଷିତ ଅଟେ |"))

            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = languageState.value.getT("Choose a way to get support", "सहायता प्राप्त करने का तरीका चुनें", "ସହାୟତା ପାଇବା ପାଇଁ ଏକ ଉପାୟ ବାଛନ୍ତୁ"),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(16.dp))
            HelpItemCard(
                title = languageState.value.getT("Chat on WhatsApp", "व्हाट्सएप पर चैट करें", "ହ୍ୱାଟସ୍ଆପରେ ଚାଟ୍ କରନ୍ତୁ"),
                subtitle = SupportContact.DISPLAY,
                icon = Icons.AutoMirrored.Filled.Chat,
                themeColor = themeColor,
                onClick = { context.chatOnWhatsApp() }
            )
            Spacer(modifier = Modifier.height(12.dp))
            HelpItemCard(
                title = languageState.value.getT("Call Support", "कॉल सहायता", "କଲ୍ ସହାୟତା"),
                subtitle = SupportContact.DISPLAY,
                icon = Icons.Default.Phone,
                themeColor = themeColor,
                onClick = { context.callSupport() }
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
                        Text(languageState.value.getT("Support Hours", "सहायता के घंटे", "ସହାୟତା ସମୟ"), fontWeight = FontWeight.Bold, fontSize = 14.sp)
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
                title = { Text(languageState.value.getT("Contact Support", "सहायता से संपर्क करें", "ସହାୟତା ସହିତ ଯୋଗାଯୋଗ କରନ୍ତୁ"), fontWeight = FontWeight.Bold, color = Color.White) },
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
                text = languageState.value.getT("Start a Conversation", "बातचीत शुरू करें", "ଏକ କଥାବାର୍ତ୍ତା ଆରମ୍ଭ କରନ୍ତୁ"),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = languageState.value.getT("Please provide a few details so we can assist you better", "कृपया कुछ विवरण प्रदान करें ताकि हम आपकी बेहतर सहायता कर सकें", "ଦୟାକରି କିଛି ବିବରଣୀ ପ୍ରଦାନ କରନ୍ତୁ ଯାହା ଦ୍ୱାରା ଆମେ ଆପଣଙ୍କୁ ଭଲ ଭାବରେ ସାହାଯ୍ୟ କରିପାରିବୁ |"),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))
            EnrollmentDropdownField(
                label = languageState.value.getT("Subject", "विषय", "ବିଷୟ"),
                selectedValue = subject.ifEmpty { languageState.value.getT("Select a topic", "एक विषय चुनें", "ଏକ ବିଷୟ ବାଛନ୍ତୁ") },
                options = listOf("Enrollment Issue", "Claim Process", "Payment Issue", "Policy Status", "Other"),
                onValueChange = { subject = it },
                borderColor = Color.LightGray.copy(alpha = 0.5f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            EnrollmentDropdownField(
                label = languageState.value.getT("Category", "श्रेणी", "ବର୍ଗ"),
                selectedValue = category.ifEmpty { languageState.value.getT("Select a category", "एक श्रेणी चुनें", "ଏକ ବର୍ଗ ବାଛନ୍ତୁ") },
                options = listOf("Technical Support", "Billing", "Information", "Feedback"),
                onValueChange = { category = it },
                borderColor = Color.LightGray.copy(alpha = 0.5f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(languageState.value.getT("Description", "विवरण", "ବିବରଣୀ"), fontWeight = FontWeight.Bold, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier.fillMaxWidth().height(120.dp),
                    placeholder = { Text(languageState.value.getT("Tell us more about your issue...", "हमें अपनी समस्या के बारे में और बताएं...", "ଆପଣଙ୍କର ସମସ୍ୟା ବିଷୟରେ ଆମକୁ ଅଧିକ କୁହନ୍ତୁ ..."), color = Color.Gray) },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f))
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(languageState.value.getT("Attach Screenshot (Optional)", "स्क्रीनशॉट संलग्न करें (वैकल्पिक)", "ସ୍କ୍ରିନସଟ୍ ସଂଲଗ୍ନ କରନ୍ତୁ (ବୈକଳ୍ପିକ)"), fontWeight = FontWeight.Bold, fontSize = 14.sp, modifier = Modifier.align(Alignment.Start))
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
                Text(languageState.value.getT("Start Chat", "चैट शुरू करें", "ଚାଟ୍ ଆରମ୍ଭ କରନ୍ତୁ"), fontWeight = FontWeight.Bold, fontSize = 16.sp)
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
                            Text(languageState.value.getT("Support Team", "सहायता टीम", "ସହାୟତା ଟିମ୍"), fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Color(0xFF4CAF50)))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(languageState.value.getT("Online", "ऑनलाइन", "ଅନଲାଇନ୍"), fontSize = 11.sp, color = Color.White.copy(alpha = 0.8f))
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
                        placeholder = { Text(languageState.value.getT("Type a message...", "एक संदेश लिखें...", "ଗୋଟିଏ ବାର୍ତ୍ତା ଲେଖନ୍ତୁ ..."), color = Color.Gray) },
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
                            Text(languageState.value.getT("Today", "आज", "ଆଜି"), modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp), fontSize = 12.sp, color = Color.Gray)
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
                text = languageState.value.getT("Thank You!", "धन्यवाद!", "ଧନ୍ୟବାଦ!"),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
            
            Text(
                text = languageState.value.getT("Your issue has been reported successfully.", "आपकी समस्या सफलतापूर्वक रिपोर्ट कर दी गई है।", "ଆପଣଙ୍କର ସମସ୍ୟା ସଫଳତାର ସହିତ ରିପୋର୍ଟ କରାଯାଇଛି |"),
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
                        text = languageState.value.getT("Ticket Details", "टिकट विवरण", "ଟିକେଟ୍ ବିବରଣୀ"),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TicketDetailRow(languageState.value.getT("Ticket ID", "टिकट आईडी", "ଟିକେଟ୍ ID"), ticketId)
                    TicketDetailRow(languageState.value.getT("Issue Type", "समस्या का प्रकार", "ସମସ୍ୟାର ପ୍ରକାର"), type)
                    TicketDetailRow(languageState.value.getT("Reported Date", "रिपोर्ट की तारीख", "ରିପୋର୍ଟ ତାରିଖ"), date)
                    TicketDetailRow(languageState.value.getT("Status", "स्थिति", "ସ୍ଥିତି"), status, valueColor = InfoBlue)
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
                        text = languageState.value.getT("We will review your issue and get back to you soon.", "हम आपकी समस्या की समीक्षा करेंगे और जल्द ही आपसे संपर्क करेंगे।", "ଆମେ ଆପଣଙ୍କର ସମସ୍ୟାର ସମୀକ୍ଷା କରିବୁ ଏବଂ ଶୀଘ୍ର ଆପଣଙ୍କ ସହିତ ଯୋଗାଯୋଗ କରିବୁ |"),
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
                Text(languageState.value.getT("View My Reports", "मेरी रिपोर्ट देखें", "ମୋର ରିପୋର୍ଟ ଦେଖନ୍ତୁ"), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            OutlinedButton(
                onClick = onBackHome,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color.LightGray)
            ) {
                Text(languageState.value.getT("Back to Home", "होम पर वापस जाएं", "ମୁଖ୍ୟ ପୃଷ୍ଠାକୁ ଫେରନ୍ତୁ"), color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyReportsScreen(navController: NavHostController, userRole: UserRole?, onBack: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val context = LocalContext.current
    val themeColor = when (userRole) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }
    
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf(
        languageState.value.getT("All", "सभी", "ସମସ୍ତ"),
        languageState.value.getT("Open", "खुला", "ଖୋଲା"),
        languageState.value.getT("In Progress", "प्रगति पर", "ଚାଲୁଛି"),
        languageState.value.getT("Resolved", "सुलझाया हुआ", "ସମାଧାନ ହୋଇଛି"),
        languageState.value.getT("Closed", "बंद", "ବନ୍ଦ")
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
                title = { Text(languageState.value.getT("My Reports", "मेरी रिपोर्ट", "ମୋର ରିପୋର୍ଟ"), fontWeight = FontWeight.Bold, color = Color.White) },
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
                            Text(languageState.value.getT("Can't find your issue?", "अपनी समस्या नहीं मिल रही?", "ଆପଣଙ୍କ ସମସ୍ୟା ପାଇଲେ ନାହିଁ କି?"), fontWeight = FontWeight.Bold)
                            Text(languageState.value.getT("Contact our support team directly.", "हमारी सहायता टीम से सीधे संपर्क करें।", "ଆମର ସହାୟତା ଟିମ୍ ସହିତ ସିଧାସଳଖ ଯୋଗାଯୋଗ କରନ୍ତୁ |"), fontSize = 12.sp, color = Color.Gray, textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(12.dp))
                            TextButton(onClick = { context.callSupport() }) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.AutoMirrored.Filled.Chat, null, modifier = Modifier.size(18.dp), tint = SuccessGreen)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(languageState.value.getT("Contact Support", "सहायता से संपर्क करें", "ସହାୟତା ସହିତ ଯୋଗାଯୋଗ କରନ୍ତୁ"), color = SuccessGreen, fontWeight = FontWeight.Bold)
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
                        text = languageState.value.getT("Privacy Policy", "गोपनीयता नीति", "ଗୋପନୀୟତା ନୀତି"),
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
                text = languageState.value.getT("Your Privacy Matters", "आपकी गोपनीयता मायने रखती है", "ଆପଣଙ୍କ ଗୋପନୀୟତା ଗୁରୁତ୍ୱପୂର୍ଣ୍ଣ"),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = languageState.value.getT(
                    "We are committed to protecting your personal information and being transparent about how we use it.",
                    "हम आपकी व्यक्तिगत जानकारी की सुरक्षा करने और इसके उपयोग के तरीके के बारे में पारदर्शी होने के लिए प्रतिबद्ध हैं।",
                    "ଆମେ ଆପଣଙ୍କର ବ୍ୟକ୍ତିଗତ ସୂଚନାକୁ ସୁରକ୍ଷିତ ରଖିବା ଏବଂ ଏହାର ବ୍ୟବହାର ବିଷୟରେ ସ୍ୱଚ୍ଛ ରହିବାକୁ ପ୍ରତିଶ୍ରୁତିବଦ୍ଧ |"
                ),
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
                        text = languageState.value.getT("In this Policy", "इस नीति में", "ଏହି ନୀତିରେ"),
                        color = themeColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    val sections = listOf(
                        PolicySection(
                            title = languageState.value.getT("Information We Collect", "जानकारी जो हम एकत्र करते हैं", "ସୂଚନା ଯାହା ଆମେ ସଂଗ୍ରହ କରୁ"),
                            content = languageState.value.getT(
                                "We collect personal details like name, phone number, and location. We also collect livestock data including photos, tag numbers, and health records to facilitate insurance.",
                                "हम नाम, फोन नंबर और स्थान जैसे व्यक्तिगत विवरण एकत्र करते हैं। बीमा की सुविधा के लिए हम फोटो, टैग नंबर और स्वास्थ्य रिकॉर्ड सहित पशुधन डेटा भी एकत्र करते हैं।",
                                "ଆମେ ନାମ, ଫୋନ୍ ନମ୍ବର ଏବଂ ଅବସ୍ଥାନ ପରି ବ୍ୟକ୍ତିଗତ ବିବରଣୀ ସଂଗ୍ରହ କରୁ | ବୀମା ସୁବିଧା ପାଇଁ ଆମେ ଫଟୋ, ଟ୍ୟାଗ୍ ନମ୍ବର ଏବଂ ସ୍ୱାସ୍ଥ୍ୟ ରେକର୍ଡ ସହିତ ପ୍ରାଣୀସମ୍ପଦ ତଥ୍ୟ ମଧ୍ୟ ସଂଗ୍ରହ କରୁ |"
                            ),
                            icon = Icons.Default.Description
                        ),
                        PolicySection(
                            title = languageState.value.getT("How We Use Information", "हम जानकारी का उपयोग कैसे करते हैं", "ଆମେ ସୂଚନା କିପରି ବ୍ୟବହାର କରୁ"),
                            content = languageState.value.getT(
                                "Your information is used to process insurance applications, verify claims through AI assessment, and coordinate visits between Suraksha Didis and farmers.",
                                "आपकी जानकारी का उपयोग बीमा आवेदनों को संसाधित करने, एआई मूल्यांकन के माध्यम से दावों को सत्यापित करने और सुरक्षा दीदियों और किसानों के बीच यात्राओं के समन्वय के लिए किया जाता है।",
                                "ଆପଣଙ୍କର ସୂଚନା ବୀମା ଆବେଦନଗୁଡ଼ିକର ପ୍ରକ୍ରିୟାକରଣ ପାଇଁ, AI ମୂଲ୍ୟାଙ୍କନ ମାଧ୍ୟମରେ ଦାବିଗୁଡିକ ଯାଞ୍ଚ କରିବା ପାଇଁ ଏବଂ ସୁରକ୍ଷା ଦିଦି ଏବଂ କୃଷକମାନଙ୍କ ମଧ୍ୟରେ ପରିଦର୍ଶନ ସମନ୍ୱୟ ପାଇଁ ବ୍ୟବହୃତ ହୁଏ |"
                            ),
                            icon = Icons.Default.Security
                        ),
                        PolicySection(
                            title = languageState.value.getT("Data Sharing & Disclosure", "डेटा साझाकरण और प्रकटीकरण", "ଡାଟା ସେୟାରିଂ ଏବଂ ପ୍ରକାଶ"),
                            content = languageState.value.getT(
                                "We share data only with authorized insurance partners and relevant authorities for claim settlement. We do not sell or trade your personal information with third parties.",
                                "हम दावा निपटान के लिए केवल अधिकृत बीमा भागीदारों और संबंधित अधिकारियों के साथ डेटा साझा करते हैं। हम तृतीय पक्षों के साथ आपकी व्यक्तिगत जानकारी बेचते या व्यापार नहीं करते हैं।",
                                "ଆମେ କେବଳ ଦାବି ସମାଧାନ ପାଇଁ ଅଧିକୃତ ବୀମା ଅଂଶୀଦାର ଏବଂ ସମ୍ପୃକ୍ତ କର୍ତ୍ତୃପକ୍ଷଙ୍କ ସହିତ ତଥ୍ୟ ସେୟାର କରୁ | ଆମେ ତୃତୀୟ ପକ୍ଷ ସହିତ ଆପଣଙ୍କର ବ୍ୟକ୍ତିଗତ ସୂଚନା ବିକ୍ରୟ କିମ୍ବା ବ୍ୟବସାୟ କରୁନାହୁଁ |"
                            ),
                            icon = Icons.Default.Share
                        ),
                        PolicySection(
                            title = languageState.value.getT("Data Security", "डेटा सुरक्षा", "ଡାଟା ସୁରକ୍ଷା"),
                            content = languageState.value.getT(
                                "We employ industry-standard encryption and security measures to protect your data from unauthorized access, loss, or misuse.",
                                "हम आपके डेटा को अनधिकृत पहुंच, हानि या दुरुपयोग से बचाने के लिए उद्योग-मानक एन्क्रिप्शन और सुरक्षा उपायों का उपयोग करते हैं।",
                                "ଆପଣଙ୍କର ତଥ୍ୟକୁ ଅନଧିକୃତ ପ୍ରବେଶ, କ୍ଷତି କିମ୍ବା ଅପବ୍ୟବହାରରୁ ରକ୍ଷା କରିବା ପାଇଁ ଆମେ ଶିଳ୍ପ-ମାନକ ଏନକ୍ରିପ୍ସନ୍ ଏବଂ ସୁରକ୍ଷା ବ୍ୟବସ୍ଥା ବ୍ୟବହାର କରୁ |"
                            ),
                            icon = Icons.Default.VerifiedUser
                        ),
                        PolicySection(
                            title = languageState.value.getT("Your Rights", "आपके अधिकार", "ଆପଣଙ୍କର ଅଧିକାର"),
                            content = languageState.value.getT(
                                "You have the right to access, update, or request the deletion of your personal data. You can manage your profile settings directly in the app.",
                                "आपको अपने व्यक्तिगत डेटा तक पहुंचने, अपडेट करने या हटाने का अनुरोध करने का अधिकार है। आप सीधे ऐप में अपनी प्रोफ़ाइल सेटिंग प्रबंधित कर सकते हैं।",
                                "ଆପଣଙ୍କର ବ୍ୟକ୍ତିଗତ ତଥ୍ୟକୁ ପ୍ରବେଶ କରିବା, ଅପଡେଟ୍ କରିବା କିମ୍ବା ବିଲୋପ କରିବାକୁ ଅନୁରୋଧ କରିବାର ଅଧିକାର ଆପଣଙ୍କର ଅଛି | ଆପଣ ସିଧାସଳଖ ଆପ୍ ରେ ଆପଣଙ୍କର ପ୍ରୋଫାଇଲ୍ ସେଟିଙ୍ଗ୍ ପରିଚାଳନା କରିପାରିବେ |"
                            ),
                            icon = Icons.Default.Person
                        ),
                        PolicySection(
                            title = languageState.value.getT("Cookies & Tracking", "कुकीज़ और ट्रैकिंग", "କୁକିଜ୍ ଏବଂ ଟ୍ରାକିଂ"),
                            content = languageState.value.getT(
                                "We use location services to verify carcass locations during mortality reporting and essential session management tools for app security.",
                                "हम मृत्यु रिपोर्टिंग के दौरान शव स्थानों को सत्यापित करने के लिए स्थान सेवाओं और ऐप सुरक्षा के लिए आवश्यक सत्र प्रबंधन टूल का उपयोग करते हैं।",
                                "ଆମେ ମୃତ୍ୟୁ ରିପୋର୍ଟ ସମୟରେ ଶବର ଅବସ୍ଥାନ ଯାଞ୍ଚ କରିବା ପାଇଁ ଅବସ୍ଥାନ ସେବା ଏବଂ ଆପ୍ ସୁରକ୍ଷା ପାଇଁ ଆବଶ୍ୟକୀୟ ସେସନ୍ ପରିଚାଳନା ଉପକରଣ ବ୍ୟବହାର କରୁ |"
                            ),
                            icon = Icons.Default.Cookie
                        ),
                        PolicySection(
                            title = languageState.value.getT("Children's Privacy", "बच्चों की गोपनीयता", "ପିଲାମାନଙ୍କର ଗୋପନୀୟତା"),
                            content = languageState.value.getT(
                                "Our services are not intended for children under 18. we do not knowingly collect personal data from minors.",
                                "हमारी सेवाएँ 18 वर्ष से कम उम्र के बच्चों के लिए नहीं हैं। हम जानबूझकर नाबालिगों से व्यक्तिगत डेटा एकत्र नहीं करते हैं।",
                                "ଆମର ସେବାଗୁଡିକ ୧୮ ବର୍ଷରୁ କମ୍ ପିଲାମାନଙ୍କ ପାଇଁ ଉଦ୍ଦିଷ୍ଟ ନୁହେଁ | ଆମେ ଜାଣିଶୁଣି ନାବାଳକମାନଙ୍କଠାରୁ ବ୍ୟକ୍ତିଗତ ତଥ୍ୟ ସଂଗ୍ରହ କରୁନାହୁଁ |"
                            ),
                            icon = Icons.Default.ChildCare
                        ),
                        PolicySection(
                            title = languageState.value.getT("Changes to this Policy", "इस नीति में बदलाव", "ଏହି ନୀତିରେ ପରିବର୍ତ୍ତନ"),
                            content = languageState.value.getT(
                                "We may update this policy from time to time. We will notify you of any significant changes through app notifications.",
                                "हम समय-समय पर इस नीति को अपडेट कर सकते हैं। हम ऐप सूचनाओं के माध्यम से आपको किसी भी महत्वपूर्ण बदलाव के बारे में सूचित करेंगे।",
                                "ଆମେ ସମୟ ସମୟରେ ଏହି ନୀତି ଅପଡେଟ୍ କରିପାରିବା | ଆମେ ଆପ୍ ବିଜ୍ଞପ୍ତି ମାଧ୍ୟମରେ ଯେକୌଣସି ଗୁରୁତ୍ୱପୂର୍ଣ୍ଣ ପରିବର୍ତ୍ତନ ବିଷୟରେ ଆପଣଙ୍କୁ ଜଣାଇବୁ |"
                            ),
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
                        text = languageState.value.getT(
                            "By using our app, you agree to the collection and use of information in accordance with this policy.",
                            "हमारे ऐप का उपयोग करके, आप इस नीति के अनुसार जानकारी के संग्रह और उपयोग के लिए सहमत होते हैं।",
                            "ଆମର ଆପ୍ ବ୍ୟବହାର କରି, ଆପଣ ଏହି ନୀତି ଅନୁଯାୟୀ ସୂଚନା ସଂଗ୍ରହ ଏବଂ ବ୍ୟବହାର କରିବାକୁ ସମ୍ମତ ହୁଅନ୍ତି |"
                        ),
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
                        text = languageState.value.getT("Terms of Service", "सेवा की शर्तें", "ସେବା ସର୍ତ୍ତାବଳୀ"),
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
                text = languageState.value.getT("Our Terms, Your Trust", "हमारी शर्तें, आपका विश्वास", "ଆମର ସର୍ତ୍ତାବଳୀ, ଆପଣଙ୍କ ବିଶ୍ୱାସ"),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = languageState.value.getT(
                    "Welcome! Please read these Terms of Service carefully before using our app. By using the app, you agree to these terms and conditions.",
                    "स्वागत है! कृपया हमारे ऐप का उपयोग करने से पहले इन सेवा की शर्तों को ध्यान से पढ़ें। ऐप का उपयोग करके, आप इन नियमों और शर्तों से सहमत होते हैं।",
                    "ସ୍ଵାଗତ! ଦୟାକରି ଆମର ଆପ୍ ବ୍ୟବହାର କରିବା ପୂର୍ବରୁ ଏହି ସେବା ସର୍ତ୍ତାବଳୀକୁ ଧ୍ୟାନର ସହିତ ପଢନ୍ତୁ | ଆପ୍ ବ୍ୟବହାର କରି, ଆପଣ ଏହି ନିୟମ ଏବଂ ସର୍ତ୍ତାବଳୀରେ ସମ୍ମତ ହୁଅନ୍ତି |"
                ),
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
                        text = languageState.value.getT("In this Document", "इस दस्तावेज़ में", "ଏହି ଦଲିଲରେ"),
                        color = themeColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    val sections = listOf(
                        PolicySection(
                            title = languageState.value.getT("1. Acceptance of Terms", "1. शर्तों की स्वीकृति", "୧. ସର୍ତ୍ତାବଳୀର ଗ୍ରହଣ"),
                            content = languageState.value.getT(
                                "By accessing or using this application, you agree to be bound by these terms. If you do not agree, please do not use the services.",
                                "इस एप्लिकेशन तक पहुँचने या उपयोग करने से, आप इन शर्तों से बंधे होने के लिए सहमत होते हैं। यदि आप सहमत नहीं हैं, तो कृपया सेवाओं का उपयोग न करें।",
                                "ଏହି ଆପ୍ ବ୍ୟବହାର କରି, ଆପଣ ଏହି ସର୍ତ୍ତାବଳୀ ଦ୍ୱାରା ବାଧ୍ୟ ହେବାକୁ ସମ୍ମତ ହୁଅନ୍ତି | ଯଦି ଆପଣ ସମ୍ମତ ନୁହଁନ୍ତି, ଦୟାକରି ସେବାଗୁଡିକ ବ୍ୟବହାର କରନ୍ତୁ ନାହିଁ |"
                            ),
                            icon = Icons.AutoMirrored.Filled.Assignment
                        ),
                        PolicySection(
                            title = languageState.value.getT("2. Use of Our Services", "2. हमारी सेवाओं का उपयोग", "୨. ଆମର ସେବା ବ୍ୟବହାର"),
                            content = languageState.value.getT(
                                "Our services are designed to facilitate livestock insurance and related assistance. You agree to use the services only for lawful purposes.",
                                "हमारी सेवाएँ पशुधन बीमा और संबंधित सहायता की सुविधा के लिए डिज़ाइन की गई हैं। आप केवल वैध उद्देश्यों के लिए सेवाओं का उपयोग करने के लिए सहमत हैं।",
                                "ଆମର ସେବାଗୁଡିକ ପ୍ରାଣୀସମ୍ପଦ ବୀମା ଏବଂ ସମ୍ପୃକ୍ତ ସହାୟତା ପାଇଁ ପ୍ରସ୍ତୁତ କରାଯାଇଛି | ଆପଣ କେବଳ ଆଇନଗତ ଉଦ୍ଦେଶ୍ୟରେ ସେବା ବ୍ୟବହାର କରିବାକୁ ସମ୍ମତ ହୁଅନ୍ତି |"
                            ),
                            icon = Icons.Default.VerifiedUser
                        ),
                        PolicySection(
                            title = languageState.value.getT("3. User Responsibilities", "3. उपयोगकर्ता की जिम्मेदारियां", "୩. ବ୍ୟବହାରକାରୀଙ୍କ ଦାୟିତ୍ୱ"),
                            content = languageState.value.getT(
                                "Users are responsible for providing accurate information about livestock and maintaining the confidentiality of their account details.",
                                "उपयोगकर्ता पशुधन के बारे में सटीक जानकारी प्रदान करने और अपने खाते के विवरण की गोपनीयता बनाए रखने के लिए जिम्मेदार हैं।",
                                "ପ୍ରାଣୀସମ୍ପଦ ବିଷୟରେ ସଠିକ୍ ସୂଚନା ପ୍ରଦାନ କରିବା ଏବଂ ସେମାନଙ୍କ ଆକାଉଣ୍ଟ ବିବରଣୀର ଗୋପନୀୟତା ରକ୍ଷା କରିବା ପାଇଁ ବ୍ୟବହାରକାରୀମାନେ ଦାୟୀ |"
                            ),
                            icon = Icons.Default.Person
                        ),
                        PolicySection(
                            title = languageState.value.getT("4. Payments & Transactions", "4. भुगतान और लेनदेन", "୪. ପରିଶୋଧ ଏବଂ କାରବାର"),
                            content = languageState.value.getT(
                                "Insurance premiums and service fees must be paid as per the selected plans. All transactions are processed securely.",
                                "बीमा प्रीमियम और सेवा शुल्क का भुगतान चयनित योजनाओं के अनुसार किया जाना चाहिए। सभी लेनदेन सुरक्षित रूप से संसाधित किए जाते हैं।",
                                "ବୀମା ପ୍ରିମିୟମ ଏବଂ ସେବା ଶୁଳ୍କ ଚୟନ କରାଯାଇଥିବା ଯୋଜନା ଅନୁଯାୟୀ ପ୍ରଦାନ କରାଯିବା ଆବଶ୍ୟକ | ସମସ୍ତ କାରବାର ସୁରକ୍ଷିତ ଭାବରେ ପ୍ରକ୍ରିୟାକରଣ କରାଯାଏ |"
                            ),
                            icon = Icons.Default.Payments
                        ),
                        PolicySection(
                            title = languageState.value.getT("5. Data & Privacy", "5. डेटा और गोपनीयता", "୫. ଡାଟା ଏବଂ ଗୋପନୀୟତା"),
                            content = languageState.value.getT(
                                "Your use of the services is also governed by our Privacy Policy. We protect your data as described in the policy document.",
                                "सेवाओं का आपका उपयोग हमारी गोपनीयता नीति द्वारा भी शासित होता है। हम नीति दस्तावेज़ में वर्णित अनुसार आपके डेटा की सुरक्षा करते हैं।",
                                "ଆପଣଙ୍କର ସେବା ବ୍ୟବହାର ମଧ୍ୟ ଆମର ଗୋପନୀୟତା ନୀତି ଦ୍ୱାରା ପରିଚାଳିତ | ଆମେ ନୀତି ଦଲିଲରେ ବର୍ଣ୍ଣନା କରାଯାଇଥିବା ଅନୁଯାୟୀ ଆପଣଙ୍କ ତଥ୍ୟକୁ ସୁରକ୍ଷିତ ରଖୁ |"
                            ),
                            icon = Icons.Default.Security
                        ),
                        PolicySection(
                            title = languageState.value.getT("6. Termination", "6. समाप्ति", "୬. ସମାପ୍ତି"),
                            content = languageState.value.getT(
                                "We reserve the right to terminate or suspend access to our services for violations of these terms or fraudulent activities.",
                                "हम इन शर्तों के उल्लंघन या धोखाधड़ी की गतिविधियों के लिए अपनी सेवाओं तक पहुँच को समाप्त या निलंबित करने का अधिकार सुरक्षित रखते हैं।",
                                "ଏହି ସର୍ତ୍ତାବଳୀର ଉଲ୍ଲଂଘନ କିମ୍ବା ପ୍ରତାରଣାମୂଳକ କାର୍ଯ୍ୟକଳାପ ପାଇଁ ଆମର ସେବାଗୁଡିକର ଆକ୍ସେସ୍ ସମାପ୍ତ କିମ୍ବା ସ୍ଥଗିତ ରଖିବାର ଅଧିକାର ଆମେ ସଂରକ୍ଷିତ ରଖୁ |"
                            ),
                            icon = Icons.Default.Lock
                        ),
                        PolicySection(
                            title = languageState.value.getT("7. Limitation of Liability", "7. दायित्व की सीमा", "୭. ଦାୟିତ୍ୱର ସୀମା"),
                            content = languageState.value.getT(
                                "The company shall not be liable for any indirect, incidental, or consequential damages resulting from the use of our services.",
                                "कंपनी हमारी सेवाओं के उपयोग के परिणामस्वरूप होने वाले किसी भी अप्रत्यक्ष, आकस्मिक या परिणामी नुकसान के लिए उत्तरदायी नहीं होगी।",
                                "ଆମର ସେବାଗୁଡିକର ବ୍ୟବହାର ଦ୍ୱାରା ହେଉଥିବା କୌଣସି ପରୋକ୍ଷ, ଆନୁସଙ୍ଗିକ କିମ୍ବା ପରିଣାମସ୍ୱରୂପ କ୍ଷତି ପାଇଁ କମ୍ପାନୀ ଦାୟୀ ରହିବ ନାହିଁ |"
                            ),
                            icon = Icons.Default.Gavel
                        ),
                        PolicySection(
                            title = languageState.value.getT("8. Changes to Terms", "8. शर्तों में बदलाव", "୮. ସର୍ତ୍ତାବଳୀରେ ପରିବର୍ତ୍ତନ"),
                            content = languageState.value.getT(
                                "We may modify these terms at any time. Significant changes will be communicated through app notifications or email.",
                                "हम किसी भी समय इन शर्तों को संशोधित कर सकते हैं। महत्वपूर्ण बदलावों की सूचना ऐप नोटिफिकेशन या ईमेल के माध्यम से दी जाएगी।",
                                "ଆମେ ଯେକୌଣସି ସମୟରେ ଏହି ସର୍ତ୍ତାବଳୀରେ ସଂଶୋଧନ କରିପାରିବା | ଗୁରୁତ୍ୱପୂର୍ଣ୍ଣ ପରିବର୍ତ୍ତନଗୁଡିକ ଆପ୍ ବିଜ୍ଞପ୍ତି କିମ୍ବା ଇମେଲ୍ ମାଧ୍ୟମରେ ସୂଚନା ଦିଆଯିବ |"
                            ),
                            icon = Icons.Default.Edit
                        ),
                        PolicySection(
                            title = languageState.value.getT("9. Contact Us", "9. हमसे संपर्क करें", "୯. ଆମ ସହ ଯୋଗାଯୋଗ କରନ୍ତୁ"),
                            content = languageState.value.getT(
                                "If you have any questions about these Terms of Service, please contact our support team through the Help & Support section.",
                                "यदि आपके पास इन सेवा की शर्तों के बारे में कोई प्रश्न हैं, तो कृपया सहायता और समर्थन अनुभाग के माध्यम से हमारी सहायता टीम से संपर्क करें।",
                                "ଯଦି ଏହି ସେବା ସର୍ତ୍ତାବଳୀ ବିଷୟରେ ଆପଣଙ୍କର କୌଣସି ପ୍ରଶ୍ନ ଅଛି, ଦୟାକରି ସାହାଯ୍ୟ ଏବଂ ସମର୍ଥନ ବିଭାଗ ମାଧ୍ୟମରେ ଆମର ସହାୟତା ଟିମ୍ ସହିତ ଯୋଗାଯୋଗ କରନ୍ତୁ |"
                            ),
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
                        text = languageState.value.getT(
                            "These terms are legally binding between you and our company.",
                            "ये शर्तें आपके और हमारी कंपनी के बीच कानूनी रूप से बाध्यकारी हैं।",
                            "ଏହି ସର୍ତ୍ତାବଳୀ ଆପଣଙ୍କ ଏବଂ ଆମ କମ୍ପାନୀ ମଧ୍ୟରେ ଆଇନଗତ ଭାବରେ ବାଧ୍ୟତାମୂଳକ |"
                        ),
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



