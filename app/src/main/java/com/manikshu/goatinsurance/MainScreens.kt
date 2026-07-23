package com.manikshu.goatinsurance

import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material.icons.automirrored.filled.HelpOutline

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
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Scale
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.automirrored.filled.Send
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
import androidx.compose.material.icons.filled.NoteAlt
import androidx.compose.material.icons.filled.HowToReg
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.filled.FactCheck
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Transgender
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
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Vaccines
import androidx.compose.material.icons.filled.PendingActions
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Path
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Coronavirus
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.MonitorWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

val LocalWindowSizeClass = staticCompositionLocalOf<WindowSizeClass?> { null }
val LocalAppLanguage = compositionLocalOf { mutableStateOf(AppLanguage.ENGLISH) }
val LocalProfileImage = compositionLocalOf { mutableStateOf<Uri?>(null) }
val LocalNotificationsEnabled = compositionLocalOf { mutableStateOf(true) }

/** Resolve a server media path ("/media/x.jpg") to an absolute URL Coil can load. */
fun absoluteMediaUrl(path: String): String =
    if (path.startsWith("http")) path
    else BuildConfig.BASE_URL.trimEnd('/') + "/" + path.trimStart('/')

/**
 * Bridges the server profile photo into the shared avatar state that every dashboard
 * header renders. Without it a photo set on one device (or before a reinstall) never
 * shows, because the headers only read the local pick - and a fresh session has none,
 * even though the photo is safely on the server.
 *
 * A local pick (file/content Uri) is never overwritten; only an empty slot or a stale
 * remote URL is replaced, so cross-device updates still land.
 */
@Composable
fun SyncRemoteProfilePhoto(photo: String?) {
    val state = LocalProfileImage.current
    LaunchedEffect(photo, state.value) {
        val remote = photo?.takeIf { it.isNotBlank() }?.let { absoluteMediaUrl(it) } ?: return@LaunchedEffect
        val current = state.value
        val isLocalPick = current != null && current.scheme != "http" && current.scheme != "https"
        if (!isLocalPick && current?.toString() != remote) {
            state.value = Uri.parse(remote)
        }
    }
}

/**
 * The avatar a dashboard header should render: the image the user picked this session
 * if there is one, otherwise the photo stored on the server. Mirrors the ProfileScreen
 * fallback so every surface agrees even when [SyncRemoteProfilePhoto] has not yet bridged
 * the remote photo into the shared state (stale/failed profile fetch, a leftover pick from
 * a previous login). Returns a Coil-loadable model (Uri or absolute URL String) or null.
 */
@Composable
fun rememberHeaderAvatar(remotePhoto: String?): Any? {
    val localPick = LocalProfileImage.current.value
    return localPick ?: remotePhoto?.takeIf { it.isNotBlank() }?.let { absoluteMediaUrl(it) }
}
// Unified theme: farmer/coordinator dashboards use the same green as the rest.
private val PrimaryBlue = PrimaryGreen

@Composable
fun AppNavigation(navController: NavHostController, sessionManager: SessionManager) {
    val scope = rememberCoroutineScope()
    val userRole by sessionManager.userRole.collectAsState(initial = null)
    val context = LocalContext.current
    val languageState = LocalAppLanguage.current
    val profileImageState = LocalProfileImage.current
    
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
                onContinue = { role, name, phone ->
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
                onBack = { navController.popBackStack() }
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
                onBack = { navController.popBackStack() }
            ) 
        }
        composable("policy_details/{tag}") { backStackEntry ->
            val tag = backStackEntry.arguments?.getString("tag") ?: ""
            PolicyDetailsScreen(navController = navController, tag = tag, userRole = userRole, onBack = { navController.popBackStack() })
        }
        composable("goat_details/{tag}") { backStackEntry ->
            val tag = backStackEntry.arguments?.getString("tag") ?: ""
            GoatDetailsScreen(navController = navController, tag = tag, userRole = userRole, onBack = { navController.popBackStack() })
        }
        composable("earning_history") {
            EarningHistoryScreen(navController = navController, onBack = { navController.popBackStack() })
        }
        composable("profile") { 
            ProfileScreen(
                navController = navController,
                userRole = userRole,
                sessionManager = sessionManager,
                onLogout = {
                    scope.launch {
                        sessionManager.clearSession()
                        // Drop the in-memory avatar too; otherwise the next user who logs in
                        // on this device inherits the previous user's picked photo, which also
                        // blocks their own server photo from being bridged in.
                        profileImageState.value = null
                        navController.navigate("login") {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                },
                onBack = { navController.popBackStack() }
            ) 
        }
        composable("change_password") {
            ChangePasswordScreen(
                navController = navController,
                userRole = userRole,
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
fun SignUpScreen(onContinue: (UserRole, String, String) -> Unit, onNavigateToLogin: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf<UserRole?>(null) }

    val context = LocalContext.current
    val languageState = LocalAppLanguage.current
    val lang = languageState.value

    val authViewModel: AuthViewModel = hiltViewModel()
    val authState by authViewModel.authState.collectAsState()
    val isAuthLoading = authState is AuthState.Loading
    LaunchedEffect(authState) {
        when (val s = authState) {
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
            run {
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
                            else -> {
                                // The profile step needs camera/location, so ask here.
                                val allGranted = permissionsToRequest.all {
                                    ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
                                }
                                if (allGranted) onContinue(selectedRole!!, name, phone)
                                else showPermissionDialog = true
                            }
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
                        lang.getT("Sign Up", "साइन अप करें", "ସାଇନ୍ ଅପ୍"),
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
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var showForgotDialog by remember { mutableStateOf(false) }
    var selectedRole by remember { mutableStateOf<UserRole?>(null) }

    val context = LocalContext.current
    val languageState = LocalAppLanguage.current
    val lang = languageState.value

    val authViewModel: AuthViewModel = hiltViewModel()
    val authState by authViewModel.authState.collectAsState()
    val isAuthLoading = authState is AuthState.Loading
    LaunchedEffect(authState) {
        when (val s = authState) {
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

    if (showForgotDialog) {
        ForgotPasswordDialog(
            lang = lang,
            initialPhone = phone,
            authViewModel = authViewModel,
            onDismiss = { showForgotDialog = false; authViewModel.resetForgot() },
        )
    }

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
            run {
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
                Button(
                    onClick = {
                        when {
                            phone.length != 10 -> Toast.makeText(context, lang.getT("Enter a valid 10-digit mobile number", "मान्य 10 अंकों का मोबाइल नंबर दर्ज करें", "ଏକ ବୈଧ ୧୦ ଅଙ୍କ ମୋବାଇଲ୍ ନମ୍ବର ଲେଖନ୍ତୁ"), Toast.LENGTH_SHORT).show()
                            password.isBlank() -> Toast.makeText(context, lang.getT("Enter your password", "अपना पासवर्ड दर्ज करें", "ଆପଣଙ୍କ ପାସୱାର୍ଡ ଲେଖନ୍ତୁ"), Toast.LENGTH_SHORT).show()
                            selectedRole == null -> Toast.makeText(context, lang.getT("Choose a role", "एक भूमिका चुनें", "ଏକ ଭୂମିକା ବାଛନ୍ତୁ"), Toast.LENGTH_SHORT).show()
                            else -> authViewModel.passwordLogin(phone, password, selectedRole!!)
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
                        lang.getT("Sign In", "साइन इन करें", "ସାଇନ୍ ଇନ୍"),
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
private fun ForgotPasswordDialog(
    lang: AppLanguage,
    initialPhone: String,
    authViewModel: AuthViewModel,
    onDismiss: () -> Unit,
) {
    var phone by remember { mutableStateOf(initialPhone) }
    val state by authViewModel.forgotState.collectAsState()
    val submitting = state is SubmitState.Submitting
    val sent = state is SubmitState.Success

    AlertDialog(
        onDismissRequest = { if (!submitting) onDismiss() },
        icon = { Icon(Icons.Default.Lock, contentDescription = null, tint = PrimaryGreen) },
        title = {
            Text(
                if (sent) lang.getT("Request Sent", "अनुरोध भेजा गया", "ଅନୁରୋଧ ପଠାଗଲା")
                else lang.getT("Forgot Password?", "पासवर्ड भूल गए?", "ପାସୱାର୍ଡ ଭୁଲିଗଲେ?"),
                fontWeight = FontWeight.Bold, color = Color.Black
            )
        },
        text = {
            Column {
                if (sent) {
                    // The backend answers identically for registered and unregistered
                    // numbers, so the wording must not imply the account exists.
                    Text(
                        lang.getT(
                            "If this number is registered, an admin will contact you and set a new password.",
                            "यदि यह नंबर पंजीकृत है, तो एडमिन आपसे संपर्क करेंगे और नया पासवर्ड सेट करेंगे।",
                            "ଯଦି ଏହି ନମ୍ବର ପଞ୍ଜିକୃତ ଅଛି, ଆଡମିନ୍ ଆପଣଙ୍କ ସହ ଯୋଗାଯୋଗ କରି ନୂଆ ପାସୱାର୍ଡ ସେଟ୍ କରିବେ।"
                        ),
                        color = Color.DarkGray
                    )
                } else {
                    Text(
                        lang.getT(
                            "Enter your mobile number. An admin will verify you and set a new password.",
                            "अपना मोबाइल नंबर दर्ज करें। एडमिन आपकी पुष्टि करके नया पासवर्ड सेट करेंगे।",
                            "ଆପଣଙ୍କ ମୋବାଇଲ୍ ନମ୍ବର ଲେଖନ୍ତୁ। ଆଡମିନ୍ ଆପଣଙ୍କୁ ଯାଞ୍ଚ କରି ନୂଆ ପାସୱାର୍ଡ ସେଟ୍ କରିବେ।"
                        ),
                        color = Color.DarkGray
                    )
                    Spacer(Modifier.height(14.dp))
                    OutlinedTextField(
                        value = phone,
                        onValueChange = { if (it.length <= 10 && it.all(Char::isDigit)) phone = it },
                        singleLine = true,
                        enabled = !submitting,
                        leadingIcon = { Icon(Icons.Default.Phone, null, tint = Color.Gray) },
                        placeholder = { Text(lang.getT("Mobile number", "मोबाइल नंबर", "ମୋବାଇଲ୍ ନମ୍ବର")) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryGreen)
                    )
                    (state as? SubmitState.Error)?.let {
                        Spacer(Modifier.height(8.dp))
                        Text(it.message ?: "", color = Color.Red, fontSize = 12.sp)
                    }
                }
            }
        },
        confirmButton = {
            if (sent) {
                TextButton(onClick = onDismiss) {
                    Text(lang.getT("Got it", "समझ गया", "ବୁଝିଗଲି"), color = PrimaryGreen, fontWeight = FontWeight.Bold)
                }
            } else {
                TextButton(
                    onClick = { authViewModel.requestPasswordReset(phone) },
                    enabled = phone.length == 10 && !submitting,
                ) {
                    Text(
                        if (submitting) lang.getT("Sending…", "भेजा जा रहा…", "ପଠାଉଛି…")
                        else lang.getT("Send Request", "अनुरोध भेजें", "ଅନୁରୋଧ ପଠାନ୍ତୁ"),
                        color = PrimaryGreen, fontWeight = FontWeight.Bold
                    )
                }
            }
        },
        dismissButton = {
            if (!sent) {
                TextButton(onClick = onDismiss, enabled = !submitting) {
                    Text(lang.getT("Cancel", "रद्द करें", "ବାତିଲ୍"), color = Color.Gray)
                }
            }
        },
        shape = RoundedCornerShape(20.dp)
    )
}

/**
 * Back-press exit confirmation for a top-level dashboard. It must live INSIDE the
 * dashboard (composed after the NavHost) so its BackHandler wins over the NavHost's
 * own back handling - otherwise a stale back-stack entry gets popped and the exit
 * dialog never shows.
 */
@Composable
fun ExitConfirmationHandler() {
    val context = LocalContext.current
    val lang = LocalAppLanguage.current
    var show by remember { mutableStateOf(false) }
    BackHandler(enabled = true) { show = true }
    if (show) {
        AlertDialog(
            onDismissRequest = { show = false },
            title = { Text(lang.value.getT("Exit App", "ऐप से बाहर निकलें", "ଆପ୍ ରୁ ବାହାରନ୍ତୁ"), fontWeight = FontWeight.Bold, color = Color.Black) },
            text = { Text(lang.value.getT("Are you sure you want to exit?", "क्या आप वाकई बाहर निकलना चाहते हैं?", "ଆପଣ ନିଶ୍ଚିତ ଭାବରେ ବାହାରିବାକୁ ଚାହୁଁଛନ୍ତି କି?"), color = Color.Black) },
            confirmButton = {
                TextButton(onClick = { (context as? Activity)?.finish() }) {
                    Text(lang.value.getT("Yes", "हाँ", "ହଁ"), fontWeight = FontWeight.Bold, color = Color.Black)
                }
            },
            dismissButton = {
                TextButton(onClick = { show = false }) {
                    Text(lang.value.getT("No", "नहीं", "ନା"), fontWeight = FontWeight.Bold, color = Color.Black)
                }
            },
            containerColor = Color.White,
            shape = RoundedCornerShape(16.dp)
        )
    }
}

@Composable
fun DidiDashboard(navController: NavHostController, sessionManager: SessionManager) {
    ExitConfirmationHandler()
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
    SyncRemoteProfilePhoto(dbProfile?.photo)

    val dashVm: DidiDashboardViewModel = hiltViewModel()
    val dashState by dashVm.state.collectAsState()
    val stats = (dashState as? UiState.Success)?.data

    // Re-read the dashboard counts on return, so a death reported elsewhere is
    // reflected here instead of showing stale numbers.
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) dashVm.refresh()
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    ResponsiveLayout(
        compact = {
            val navInset = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
            Box(modifier = Modifier.fillMaxSize().background(PageBackground)) {
                DidiContent(PaddingValues(bottom = 74.dp + navInset), navController, userName, stats, dbProfile?.photo) { showNotifications = true }
                Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                    DidiBottomBar(navController)
                }
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
                DidiContent(PaddingValues(0.dp), navController, userName, stats, dbProfile?.photo) { showNotifications = true }
            }
        }
    )
}

@Composable
fun DidiContent(padding: PaddingValues, navController: NavHostController, userName: String, stats: SdDashboard?, profilePhoto: String? = null, onNotificationClick: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val context = LocalContext.current
    val notifVm: NotificationsViewModel = androidx.hilt.navigation.compose.hiltViewModel()
    val unread by notifVm.unread.collectAsState()
    LaunchedEffect(Unit) { notifVm.refreshUnread() }

    val inr = remember { java.text.NumberFormat.getInstance(java.util.Locale("en", "IN")) }

    // The greeting, notification bell and the assigned-farmers hero stay pinned;
    // only Overview and everything below it scrolls.
    Column(
        modifier = Modifier
            .padding(bottom = padding.calculateBottomPadding())
            .fillMaxSize()
            .background(PageBackground)
    ) {
        DidiHomeHeader(userName, unread, onProfileClick = { navController.navigate("profile") }, onNotificationClick = onNotificationClick, remotePhoto = profilePhoto)

        Spacer(Modifier.height(12.dp))

        Column(modifier = Modifier.padding(horizontal = 18.dp)) {
            // ---- Hero: My Assigned Farmers ----
            HeroAssignedFarmersCard(
                count = stats?.assignedFarmers,
                onViewAll = { navController.navigate("goat_list") }
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 18.dp)
        ) {
            Spacer(Modifier.height(22.dp))
            SectionTitle(languageState.value.getT("Overview", "अवलोकन", "ସମୀକ୍ଷା"))
            Spacer(Modifier.height(12.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                OverviewCard(
                    modifier = Modifier.weight(1f),
                    label = languageState.value.getT("TOTAL GOATS", "कुल बकरियां", "ମୋଟ ଛେଳି"),
                    value = stats?.totalEnrolled?.toString() ?: "…",
                    subtitle = languageState.value.getT("All goats under your care", "आपकी देखरेख में सभी बकरियां", "ଆପଣଙ୍କ ଯତ୍ନରେ ଥିବା ସମସ୍ତ ଛେଳି"),
                    accent = Color(0xFF2D6FE0),
                    cardBg = Color(0xFFEAF1FC),
                    footerBg = Color(0xFFD9E6FB),
                    badgeIcon = painterResource(R.drawable.ic_ewe_custom),
                    watermark = painterResource(R.drawable.ic_ewe_custom),
                    onClick = { navController.navigate("goat_list") }
                )
                OverviewCard(
                    modifier = Modifier.weight(1f),
                    label = languageState.value.getT("EARNINGS", "आय", "ଉପାର୍ଜନ"),
                    value = "₹ " + (stats?.premiumCollected?.let { inr.format(it.toLong()) } ?: "…"),
                    subtitle = languageState.value.getT("Total earnings till date", "आज तक की कुल आय", "ଆଜି ପର୍ଯ୍ୟନ୍ତ ମୋଟ ଉପାର୍ଜନ"),
                    accent = PrimaryGreen,
                    cardBg = Color(0xFFEBF3E6),
                    footerBg = Color(0xFFDBECD1),
                    badgeIcon = Icons.Default.AccountBalanceWallet,
                    watermark = Icons.AutoMirrored.Filled.TrendingUp,
                    onClick = { navController.navigate("earning_history") }
                )
            }

            Spacer(Modifier.height(22.dp))
            SectionTitle(languageState.value.getT("Quick Actions", "त्वरित कार्रवाइयां", "ତ୍ୱରିତ କାର୍ଯ୍ୟାନୁଷ୍ଠାନ"))
            Spacer(Modifier.height(12.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                DidiQuickAction(Modifier.weight(1f), languageState.value.getT("Enroll Goat", "बकरी दर्ज करें", "ଛେଳି ପଞ୍ଜିକରଣ"), Icons.Default.PersonAdd, accent = IconBlue) { navController.navigate("enrollment") }
                DidiQuickAction(Modifier.weight(1f), languageState.value.getT("Report Death", "मृत्यु रिपोर्ट", "ମୃତ୍ୟୁ ରିପୋର୍ଟ"), Icons.Default.Description, accent = IconRose) { navController.navigate("mortality_queue") }
            }
            Spacer(Modifier.height(14.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                DidiQuickAction(Modifier.weight(1f), languageState.value.getT("Vaccine Register", "टीका रजिस्टर", "ଟୀକା ରେଜିଷ୍ଟର"), Icons.Default.Vaccines, accent = IconTeal) { navController.navigate("vaccine_list") }
                DidiQuickAction(Modifier.weight(1f), languageState.value.getT("Pending Claims", "लंबित दावे", "ବାକି ଦାବି"), Icons.Default.PendingActions, accent = IconAmber) { navController.navigate("claim_list") }
            }

            Spacer(Modifier.height(22.dp))
            MakingDifferenceBanner(languageState.value)
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A))
}

@Composable
fun DidiHomeHeader(userName: String, unread: Int, onProfileClick: () -> Unit, onNotificationClick: () -> Unit, remotePhoto: String? = null) {
    val languageState = LocalAppLanguage.current
    val avatar = rememberHeaderAvatar(remotePhoto)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(start = 18.dp, end = 18.dp, top = 12.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(onClick = onProfileClick, color = Color(0xFFF0EAD6), shape = CircleShape, modifier = Modifier.size(52.dp)) {
            Box(contentAlignment = Alignment.Center) {
                if (avatar != null) {
                    AsyncImage(model = avatar, contentDescription = null, modifier = Modifier.fillMaxSize().clip(CircleShape), contentScale = androidx.compose.ui.layout.ContentScale.Crop)
                } else {
                    Image(painterResource(R.drawable.avatar_didi), null, modifier = Modifier.fillMaxSize().clip(CircleShape), contentScale = androidx.compose.ui.layout.ContentScale.Crop)
                }
            }
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(languageState.value.getT("Hello,", "नमस्ते,", "ନମସ୍କାର,"), fontSize = 15.sp, color = Color(0xFF5B6660))
            Text(userName.ifBlank { languageState.value.getT("Suraksha Didi", "सुरक्षा दीदी", "ସୁରକ୍ଷା ଦିଦି") }, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = PrimaryGreen)
        }
        Box {
            Surface(onClick = onNotificationClick, color = Color.White, shape = CircleShape, shadowElevation = 2.dp, modifier = Modifier.size(46.dp)) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Notifications, null, tint = Color(0xFF2A2A2A))
                }
            }
            if (unread > 0) {
                Box(modifier = Modifier.align(Alignment.TopEnd).offset(x = 2.dp, y = (-2).dp).size(20.dp).clip(CircleShape).background(Color(0xFFE53935)), contentAlignment = Alignment.Center) {
                    Text(if (unread > 9) "9+" else "$unread", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun HeroAssignedFarmersCard(count: Int?, showViewAll: Boolean = true, onViewAll: () -> Unit = {}) {
    val languageState = LocalAppLanguage.current
    Card(shape = RoundedCornerShape(22.dp), modifier = Modifier.fillMaxWidth().height(138.dp), elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)) {
        Box(Modifier.fillMaxSize()) {
            Image(
                painterResource(R.drawable.farmer_banner), null,
                // Slight zoom + right shift crops the empty margin after the third
                // farmer, so his arm meets the card's right border.
                modifier = Modifier.fillMaxSize().graphicsLayer {
                    scaleX = 1.15f
                    scaleY = 1.15f
                    translationX = size.width * 0.05f
                },
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                alignment = Alignment.TopEnd
            )
            val heroText = Color(0xFF0C3218) // deep green, reads clearly on the banner
            // Reference layout: title with the count tight beneath it, View all pinned
            // to the bottom-left with clear air in between. The column stays on the left
            // 55% of the card so the text never runs into the farmer artwork.
            Column(Modifier.fillMaxWidth(0.58f).fillMaxHeight().padding(horizontal = 20.dp, vertical = 14.dp)) {
                Text(
                    languageState.value.getT("My Assigned Farmers", "मेरे सौंपे गए किसान", "ମୋର ନ୍ୟସ୍ତ କୃଷକ"),
                    color = heroText, fontSize = 14.sp, fontWeight = FontWeight.Bold, maxLines = 1
                )
                Text(count?.toString() ?: "…", color = heroText, fontSize = 40.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.weight(1f))
                if (showViewAll) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { onViewAll() }) {
                        Text(languageState.value.getT("View all", "सभी देखें", "ସବୁ ଦେଖନ୍ତୁ"), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        Icon(Icons.Default.ChevronRight, null, tint = Color.White, modifier = Modifier.size(20.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun OverviewCard(
    modifier: Modifier,
    label: String,
    value: String,
    subtitle: String,
    accent: Color,
    cardBg: Color,
    footerBg: Color,
    badgeIcon: Any,
    watermark: Any,
    onClick: () -> Unit
) {
    val lang = LocalAppLanguage.current.value
    Card(
        modifier = modifier.height(118.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        onClick = onClick
    ) {
        Column(Modifier.fillMaxSize()) {
            Box(Modifier.fillMaxWidth().weight(1f).padding(horizontal = 14.dp, vertical = 12.dp), contentAlignment = Alignment.CenterStart) {
                // faint watermark, bottom-right
                when (watermark) {
                    is Painter -> Icon(watermark, null, tint = accent.copy(alpha = 0.10f), modifier = Modifier.align(Alignment.BottomEnd).size(66.dp))
                    is ImageVector -> Icon(watermark, null, tint = accent.copy(alpha = 0.13f), modifier = Modifier.align(Alignment.BottomEnd).size(54.dp))
                }
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            Modifier.size(38.dp).clip(CircleShape).background(Color.White)
                                .border(1.5.dp, accent.copy(alpha = 0.35f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            when (badgeIcon) {
                                is Painter -> Icon(badgeIcon, null, tint = accent, modifier = Modifier.size(22.dp))
                                is ImageVector -> Icon(badgeIcon, null, tint = accent, modifier = Modifier.size(20.dp))
                            }
                        }
                        Spacer(Modifier.width(7.dp))
                        Column(Modifier.weight(1f)) {
                            Text(label, color = accent, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.sp, maxLines = 1)
                            Text(value, color = Color(0xFF14231A), fontSize = 20.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                        }
                    }
                }
            }
            Surface(color = footerBg, modifier = Modifier.fillMaxWidth()) {
                Row(
                    Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(lang.getT("View Details", "विवरण देखें", "ବିବରଣୀ ଦେଖନ୍ତୁ"), color = accent, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Icon(Icons.Default.ChevronRight, null, tint = accent, modifier = Modifier.size(18.dp))
                }
            }
        }
    }
}

@Composable
fun DidiQuickAction(modifier: Modifier, label: String, icon: ImageVector, accent: Color = PrimaryGreen, onClick: () -> Unit) {
    Card(modifier = modifier.height(72.dp), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color.White), elevation = CardDefaults.cardElevation(defaultElevation = 2.dp), onClick = onClick) {
        Row(Modifier.fillMaxSize().padding(horizontal = 12.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier.size(36.dp).clip(RoundedCornerShape(11.dp)).background(accent.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, null, tint = accent, modifier = Modifier.size(20.dp))
            }
            Spacer(Modifier.width(8.dp))
            Text(
                label, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF14231A),
                lineHeight = 15.sp, maxLines = 2, overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            Icon(Icons.Default.ChevronRight, null, tint = Color(0xFFB0B7B0), modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
fun MakingDifferenceBanner(lang: AppLanguage) {
    Image(
        painter = painterResource(R.drawable.didi_banner_l),
        contentDescription = lang.getT("You are making a difference!", "आप बदलाव ला रहे हैं!", "ଆପଣ ପରିବର୍ତ୍ତନ ଆଣୁଛନ୍ତି!"),
        modifier = Modifier.fillMaxWidth(),
        contentScale = androidx.compose.ui.layout.ContentScale.FillWidth
    )
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
    var cause by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var expandedCause by remember { mutableStateOf(false) }

    val farmerVm: FarmerHomeViewModel = hiltViewModel()
    val policiesState by farmerVm.policies.collectAsState()
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

    val goats = policies.map { Triple(it.earTagNumber, it.breed, it.policyNumber) }
    var selectedIndex by remember { mutableStateOf(0) }
    val selectedGoat = goats.getOrNull(selectedIndex) ?: Triple("—", "—", "—")
    var expandedGoatSelector by remember { mutableStateOf(false) }

    // Photo state for 4 slots
    var goatPhotoUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var tagPhotoUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var sideVisitPhotoUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var documentPhotoUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    
    var tempUriStr by rememberSaveable { mutableStateOf<String?>(null) }
    var captureSlot by rememberSaveable { mutableStateOf(0) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && tempUriStr != null) {
            val uri = Uri.parse(tempUriStr!!)
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
    ) { isGranted ->
        if (isGranted) {
            tempUriStr?.let { cameraLauncher.launch(Uri.parse(it)) }
        } else {
            Toast.makeText(context, languageState.value.getT("Camera permission required", "कैमरा अनुमति आवश्यक है", "କ୍ୟାମେରା ଅନୁମତି ଆବଶ୍ୟକ"), Toast.LENGTH_SHORT).show()
        }
    }

    fun launchCamera(slot: Int) {
        try {
            captureSlot = slot
            val directory = File(context.cacheDir, "death_photos")
            if (!directory.exists()) directory.mkdirs()
            val file = File(directory, "death_${selectedGoat.first}_slot${slot}_${System.currentTimeMillis()}.jpg")
            val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
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

    val navHostController = androidx.navigation.compose.rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize().navigationBarsPadding(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(languageState.value.getT("Report Goat Death", "बकरी की मृत्यु की रिपोर्ट", "ଛେଳି ମୃତ୍ୟୁ ରିପୋର୍ଟ"), fontWeight = FontWeight.Bold, fontSize = 18.sp)
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
        bottomBar = { FarmerBottomBar(navHostController) },
        containerColor = PageBackground
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Selected Goat Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = { expandedGoatSelector = true },
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color(0xFFE8F5E9))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box {
                            Surface(modifier = Modifier.size(84.dp), shape = CircleShape, color = Color(0xFFF0F0F0)) {
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
                            Text(selectedGoat.first, fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Color.Black)
                            Surface(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(8.dp)) {
                                Text(selectedGoat.second, modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp), fontSize = 12.sp, color = Color.Gray)
                            }
                            Text(languageState.value.getT("Ear Tag: ${selectedGoat.first}", "कान का टैग: ${selectedGoat.first}", "କାନ ଟ୍ୟାଗ୍: ${selectedGoat.first}"), fontSize = 14.sp, color = Color.Gray)
                        }
                        Surface(color = Color(0xFFE8F5E9), shape = RoundedCornerShape(12.dp)) {
                            Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.size(6.dp).background(PrimaryGreen, CircleShape))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(languageState.value.getT("Active", "सक्रिय", "ସକ୍ରିୟ"), color = PrimaryGreen, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(20.dp))
                    HorizontalDivider(color = Color(0xFFF0F0F0))
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        MortalityStatBox(Icons.Default.CalendarToday, languageState.value.getT("Valid Till", "कब तक मान्य", "ବୈଧତା ଅବଧି"), "16 Jul 2027", Modifier.weight(1f))
                        MortalityStatBox(Icons.Default.Shield, languageState.value.getT("Sum Insured", "बीमा राशि", "ବୀମା ରାଶି"), "₹ 20,000", Modifier.weight(1f))
                        MortalityStatBox(Icons.Default.Description, languageState.value.getT("Policy No.", "पॉलिसी संख्या", "ପଲିସି ନମ୍ବର"), selectedGoat.third, Modifier.weight(1f))
                    }
                }
            }

            DropdownMenu(expanded = expandedGoatSelector, onDismissRequest = { expandedGoatSelector = false }, modifier = Modifier.fillMaxWidth(0.9f)) {
                policies.forEachIndexed { index, p ->
                    DropdownMenuItem(
                        text = { Text("${p.earTagNumber} (${p.breed})") },
                        onClick = { selectedIndex = index; expandedGoatSelector = false }
                    )
                }
            }

            // Death Information Section
            DeathInfoSection(
                languageState = languageState,
                deathDate = deathDate,
                onDateClick = {
                    val cal = Calendar.getInstance()
                    DatePickerDialog(context, { _, y, m, d_ ->
                        deathDate = String.format("%02d/%02d/%04d", d_, m + 1, y)
                        deathDateIso = String.format("%04d-%02d-%02d", y, m + 1, d_)
                    }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
                },
                deathTime = deathTime,
                onTimeClick = {
                    val cal = Calendar.getInstance()
                    TimePickerDialog(context, { _, h, min ->
                        val amPm = if (h >= 12) "PM" else "AM"
                        val h_ = if (h % 12 == 0) 12 else h % 12
                        deathTime = String.format("%02d:%02d %s", h_, min, amPm)
                    }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show()
                },
                cause = cause,
                onCauseChange = { cause = it },
                description = description,
                onDescriptionChange = { description = it }
            )

            Button(
                onClick = {
                    val goatId = policies.getOrNull(selectedIndex)?.goatId
                    if (goatId != null) {
                        val iso = if (deathDateIso.isNotBlank()) "${deathDateIso}T00:00:00" else ""
                        claimsVm.reportDeath(goatId, iso, cause.ifBlank { null })
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                enabled = deathDate.isNotBlank() && deathTime.isNotBlank() && cause.isNotBlank() && !isSubmitting,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
            ) {
                Icon(Icons.AutoMirrored.Filled.Send, null, tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    if (isSubmitting) languageState.value.getT("Submitting…", "सबमिट हो रहा है…", "ଦାଖଲ ହେଉଛି...") 
                    else languageState.value.getT("Submit Report", "रिपोर्ट जमा करें", "ରିପୋର୍ଟ ଦାଖଲ କରନ୍ତୁ"), 
                    fontWeight = FontWeight.Bold, fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun MortalityStatBox(icon: ImageVector, label: String, value: String, modifier: Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.Start) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = PrimaryGreen, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(label, fontSize = 11.sp, color = Color.Gray)
        }
        Text(value, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = PrimaryGreen, modifier = Modifier.padding(start = 22.dp))
    }
}

@Composable
fun DeathInfoSection(
    languageState: MutableState<AppLanguage>,
    deathDate: String,
    onDateClick: () -> Unit,
    deathTime: String,
    onTimeClick: () -> Unit,
    cause: String,
    onCauseChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.AutoMirrored.Filled.Assignment, null, tint = PrimaryGreen, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(languageState.value.getT("Death Information", "मृत्यु की जानकारी", "ମୃତ୍ୟୁ ସୂଚନା"), fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
        
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            DeathFormField(languageState.value.getT("Date of Death *", "मृत्यु की तारीख *", "ମୃତ୍ୟୁ ତାରିଖ *"), deathDate, "DD/MM/YYYY", Icons.Default.CalendarToday, Modifier.weight(1f), true, onDateClick)
            DeathFormField(languageState.value.getT("Time of Death *", "मृत्यु का समय *", "ମୃତ୍ୟୁ ସମୟ *"), deathTime, "HH:MM AM/PM", Icons.Default.History, Modifier.weight(1f), true, onTimeClick)
        }
        
        DeathFormField(
            label = languageState.value.getT("Probable Cause of Death *", "मृत्यु का संभावित कारण *", "ମୃତ୍ୟୁର ସମ୍ଭାବ୍ୟ କାରଣ *"),
            value = cause,
            placeholder = languageState.value.getT("Enter cause of death", "मृत्यु का कारण दर्ज करें", "ମୃତ୍ୟୁର କାରଣ ଲେଖନ୍ତୁ"),
            icon = Icons.Default.Warning,
            onValueChange = onCauseChange
        )
        
        DeathFormField(
            label = languageState.value.getT("Description (Optional)", "विवरण (वैकल्पिक)", "ବିବରଣୀ (ବୈକଳ୍ପିକ)"), 
            value = description, 
            placeholder = languageState.value.getT("Provide additional details about the incident", "घटना के बारे में अतिरिक्त विवरण प्रदान करें", "ଘଟଣା ବିଷୟରେ ଅଧିକ ସୂଚନା ଦିଅନ୍ତୁ"), 
            icon = Icons.Default.Description,
            onValueChange = onDescriptionChange,
            singleLine = false
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeathFormField(
    label: String,
    value: String,
    placeholder: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    onClick: (() -> Unit)? = null,
    onValueChange: (String) -> Unit = {},
    trailingIcon: ImageVector? = null,
    singleLine: Boolean = true
) {
    val styledLabel = buildAnnotatedString {
        label.forEach { char ->
            if (char == '*') withStyle(style = SpanStyle(color = Color.Red)) { append(char) }
            else append(char)
        }
    }
    // Picker fields (date/time) pass an onClick. A read-only-but-enabled text field
    // swallows the tap, so those are rendered disabled with a transparent clickable
    // overlay on top - that reliably opens the dialog. Plain text fields (no onClick)
    // stay enabled and editable.
    val isPicker = onClick != null
    Column(modifier = modifier) {
        Text(styledLabel, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(bottom = 8.dp))
        Box {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(placeholder, color = Color.Gray, fontSize = 13.sp) },
                leadingIcon = { Icon(icon, null, tint = Color.Gray, modifier = Modifier.size(20.dp)) },
                trailingIcon = if (trailingIcon != null) { { Icon(trailingIcon, null, tint = Color.Gray) } } else null,
                shape = RoundedCornerShape(12.dp),
                readOnly = readOnly,
                enabled = !isPicker,
                singleLine = singleLine,
                minLines = if (singleLine) 1 else 3,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryGreen,
                    unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
                    disabledBorderColor = Color.LightGray.copy(alpha = 0.5f),
                    disabledTextColor = Color.Black,
                    disabledLeadingIconColor = Color.Gray,
                    disabledTrailingIconColor = Color.Gray,
                    disabledPlaceholderColor = Color.Gray
                )
            )
            if (isPicker) {
                Box(Modifier.matchParentSize().clip(RoundedCornerShape(12.dp)).clickable { onClick?.invoke() })
            }
        }
    }
}

@Composable
fun DeathPhotoUploadSection(
    languageState: MutableState<AppLanguage>,
    goatPhotoUri: Uri?,
    tagPhotoUri: Uri?,
    sideVisitPhotoUri: Uri?,
    documentPhotoUri: Uri?,
    onCapture: (Int) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.CameraAlt, null, tint = PrimaryGreen, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(languageState.value.getT("Upload Photos (Mandatory)", "फोटो अपलोड करें (अनिवार्य)", "ଫଟୋ ଅପଲୋଡ୍ କରନ୍ତୁ (ବାଧ୍ୟତାମୂଳକ)"), fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
        Text(languageState.value.getT("Please upload clear photos for verification", "कृपया सत्यापन के लिए स्पष्ट फोटो अपलोड करें", "ଦୟାକରି ଯାଞ୍ଚ ପାଇଁ ସ୍ପଷ୍ଟ ଫଟୋ ଅପଲୋଡ୍ କରନ୍ତୁ"), fontSize = 12.sp, color = Color.Gray)
        
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            DeathPhotoSlot(languageState.value.getT("Goat Photo *", "बकरी की फोटो *", "ଛେଳି ଫଟୋ *"), languageState.value.getT("Upload clear photo of the goat", "बकरी की स्पष्ट फोटो अपलोड करें", "ଛେଳିର ସ୍ପଷ୍ଟ ଫଟୋ ଅପଲୋଡ୍ କରନ୍ତୁ"), Icons.Default.Pets, goatPhotoUri, Modifier.weight(1f)) { onCapture(1) }
            DeathPhotoSlot(languageState.value.getT("Tag Photo *", "टैग की फोटो *", "ଟ୍ୟାଗ୍ ଫଟୋ *"), languageState.value.getT("Upload photo of the tag", "टैग की फोटो अपलोड करें", "ଟ୍ୟାଗ୍ ଫଟୋ ଅପଲୋଡ୍ କରନ୍ତୁ"), Icons.Default.Label, tagPhotoUri, Modifier.weight(1f)) { onCapture(2) }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            DeathPhotoSlot(languageState.value.getT("Side Visit Photo *", "साइड विजिट फोटो *", "ସାଇଟ୍ ପରିଦର୍ଶନ ଫଟୋ *"), languageState.value.getT("Upload photo of the side visit", "साइड विजिट की फोटो अपलोड करें", "ସାଇଟ୍ ପରିଦର୍ଶନ ଫଟୋ ଅପଲୋଡ୍ କରନ୍ତୁ"), Icons.Default.LocationOn, sideVisitPhotoUri, Modifier.weight(1f)) { onCapture(3) }
            DeathPhotoSlot(languageState.value.getT("Document *", "दस्तावेज़ *", "ଦଲିଲ *"), languageState.value.getT("Upload relevant documents", "प्रासंगिक दस्तावेज़ अपलोड करें", "ପ୍ରାସଙ୍ଗିକ ଦଲିଲ ଅପଲୋଡ୍ କରନ୍ତୁ"), Icons.Default.Description, documentPhotoUri, Modifier.weight(1f)) { onCapture(4) }
        }
    }
}

@Composable
fun DeathPhotoSlot(label: String, subtitle: String, icon: ImageVector, uri: Uri?, modifier: Modifier, onCapture: () -> Unit) {
    val stroke = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    Column(modifier = modifier) {
        Surface(
            onClick = onCapture,
            modifier = Modifier.fillMaxWidth().height(120.dp).drawBehind {
                drawRoundRect(
                    color = Color.LightGray,
                    style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2f, pathEffect = stroke),
                    cornerRadius = CornerRadius(12.dp.toPx())
                )
            },
            shape = RoundedCornerShape(12.dp),
            color = Color.Transparent
        ) {
            Box(contentAlignment = Alignment.Center) {
                if (uri != null) {
                    AsyncImage(model = uri, contentDescription = null, modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(12.dp)), contentScale = ContentScale.Crop)
                } else {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(modifier = Modifier.size(40.dp).background(Color(0xFFF1F8E9), CircleShape), contentAlignment = Alignment.Center) {
                            Icon(icon, null, tint = PrimaryGreen, modifier = Modifier.size(24.dp))
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

@Composable
fun VerificationConfirmSection(languageState: MutableState<AppLanguage>, isConfirmed: Boolean, onConfirmedChange: (Boolean) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.VerifiedUser, null, tint = PrimaryGreen, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(languageState.value.getT("Verification Confirmation", "सत्यापन पुष्टि", "ଯାଞ୍ଚ ନିଶ୍ଚିତକରଣ"), fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f))
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(verticalAlignment = Alignment.Top) {
                    Checkbox(
                        checked = isConfirmed,
                        onCheckedChange = onConfirmedChange,
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
    val track = Color(0xFFE7ECE6)      // soft grey-green connector for upcoming steps
    val idleFill = Color(0xFFF1F4EF)   // upcoming circle fill
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalSteps) { index ->
            val step = index + 1
            val isCompleted = step < currentStep
            val isCurrent = step == currentStep
            val done = isCompleted || isCurrent

            Box(
                modifier = Modifier
                    // Current step wears a soft halo ring; keeps it the clear focal point.
                    .then(
                        if (isCurrent) Modifier.size(34.dp).clip(CircleShape)
                            .background(PrimaryGreen.copy(alpha = 0.14f))
                        else Modifier.size(30.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(if (isCurrent) 30.dp else 30.dp)
                        .clip(CircleShape)
                        .background(if (done) PrimaryGreen else idleFill)
                        .border(1.dp, if (done) PrimaryGreen else track, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    if (isCompleted) {
                        Icon(Icons.Default.Check, null, modifier = Modifier.size(16.dp), tint = Color.White)
                    } else {
                        Text(
                            text = step.toString(),
                            color = if (isCurrent) Color.White else Color(0xFF9AA69B),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            if (index < totalSteps - 1) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(2.dp)
                        .clip(RoundedCornerShape(1.dp))
                        .background(if (step < currentStep) PrimaryGreen else track)
                )
            }
        }
    }
}

/**
 * Premium greeting banner shown at the top of the enrollment flow, matching the
 * Didi dashboard header: circular avatar, "Hello, <name>", and the farm illustration.
 * Purely decorative - no field, step, or workflow is affected.
 */
@Composable
fun EnrollmentGreetingBanner(name: String) {
    val languageState = LocalAppLanguage.current
    val profileImage = LocalProfileImage.current.value
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(
                Brush.horizontalGradient(listOf(Color(0xFFEFF6EC), Color(0xFFE3F0DB)))
            )
    ) {
        // Farm / goat illustration bleeds off the right edge, behind the text.
        Image(
            painter = painterResource(R.drawable.didi_banner_l),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxHeight()
                .fillMaxWidth(0.5f)
                .clip(RoundedCornerShape(topEnd = 18.dp, bottomEnd = 18.dp)),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop,
            alignment = Alignment.CenterEnd,
        )
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(color = Color.White, shape = CircleShape, shadowElevation = 2.dp, modifier = Modifier.size(56.dp)) {
                Box(contentAlignment = Alignment.Center) {
                    if (profileImage != null) {
                        AsyncImage(model = profileImage, contentDescription = null,
                            modifier = Modifier.fillMaxSize().clip(CircleShape),
                            contentScale = androidx.compose.ui.layout.ContentScale.Crop)
                    } else {
                        Image(painterResource(R.drawable.avatar_didi), null,
                            modifier = Modifier.fillMaxSize().clip(CircleShape),
                            contentScale = androidx.compose.ui.layout.ContentScale.Crop)
                    }
                }
            }
            Spacer(Modifier.width(12.dp))
            Column {
                Text(languageState.value.getT("Hello,", "नमस्ते,", "ନମସ୍କାର,"), fontSize = 14.sp, color = Color(0xFF5B6660))
                Text(
                    name.ifBlank { languageState.value.getT("Suraksha Didi", "सुरक्षा दीदी", "ସୁରକ୍ଷା ଦିଦି") },
                    fontSize = 19.sp, fontWeight = FontWeight.Bold, color = PrimaryGreen
                )
            }
        }
    }
}

/** Circular light-green badge that holds a field's leading icon (enrollment fields). */
@Composable
fun FieldLeadingBadge(icon: ImageVector, tint: Color) {
    Box(Modifier.size(32.dp).clip(CircleShape).background(tint.copy(alpha = 0.12f)), contentAlignment = Alignment.Center) {
        Icon(icon, null, tint = tint, modifier = Modifier.size(19.dp))
    }
}

@Composable
fun FieldLeadingBadge(painter: Painter, tint: Color) {
    Box(Modifier.size(32.dp).clip(CircleShape).background(tint.copy(alpha = 0.12f)), contentAlignment = Alignment.Center) {
        Icon(painter, null, tint = tint, modifier = Modifier.size(19.dp))
    }
}

/** Step title with a circular icon badge, matching the reference "Farmer Information" header. */
@Composable
fun EnrollmentSectionHeader(title: String, icon: ImageVector) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier.size(46.dp).clip(CircleShape).background(PrimaryGreen.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, null, tint = PrimaryGreen, modifier = Modifier.size(24.dp))
        }
        Spacer(Modifier.width(14.dp))
        Text(title, fontSize = 19.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A))
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

    // Logged-in Didi's name for the greeting banner (decorative only).
    val profileVm: ProfileViewModel = hiltViewModel()
    val didiProfile by profileVm.profile.collectAsState()
    val didiName = didiProfile?.fullName ?: ""

    // Icon badge shown beside each step title, aligned to `steps` below.
    val stepIcons = listOf(
        Icons.Default.Person,        // 1 Farmer Information
        Icons.Default.Pets,          // 2 Goat Details
        Icons.Default.CameraAlt,     // 3 Goat Photos
        Icons.Default.Vaccines,      // 4 Vaccination History
        Icons.AutoMirrored.Filled.List, // 5 Goats Added
        Icons.Default.Payments,      // 6 Premium Payment
        Icons.Default.VerifiedUser,  // 7 Policy Generated
    )
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
    var farmerState by rememberSaveable { mutableStateOf("") }
    var farmerDistrict by rememberSaveable { mutableStateOf("") }
    var farmerBlock by rememberSaveable { mutableStateOf("") }
    var village by rememberSaveable { mutableStateOf("") }
    var location by rememberSaveable { mutableStateOf("") }
    var aadhaar by rememberSaveable { mutableStateOf("") }

    // Cascading location dropdowns (State -> District -> Block), served by the backend.
    // The reset-on-change lives in the field callbacks below, so a programmatic
    // auto-fill (from the farmer lookup) can set all three without being wiped.
    val locationVm: LocationViewModel = hiltViewModel()
    val stateOptions by locationVm.states.collectAsState()
    val districtOptions by locationVm.districts.collectAsState()
    val blockOptions by locationVm.blocks.collectAsState()

    // Step 1: the farmer's mobile must already be registered. Look it up as the
    // Didi types; auto-fill the farmer's details when found, warn (and block Next)
    // when not. Re-runs after a process restart because the saved mobile re-keys it.
    val farmerLookup by enrollVm.farmerLookup.collectAsState()
    var showNotRegisteredDialog by remember { mutableStateOf(false) }
    LaunchedEffect(mobileNumber) { enrollVm.lookupFarmer(mobileNumber) }
    LaunchedEffect(farmerLookup) {
        when (val fl = farmerLookup) {
            is FarmerLookup.Found -> {
                val f = fl.farmer
                f.fullName?.let { farmerName = it }
                f.village?.let { village = it }
                f.aadhaarId?.let { aadhaar = it }
                if (!f.state.isNullOrBlank()) { farmerState = f.state!!; locationVm.loadDistricts(f.state!!) }
                if (!f.district.isNullOrBlank()) { farmerDistrict = f.district!!; locationVm.loadBlocks(f.state ?: "", f.district!!) }
                if (!f.block.isNullOrBlank()) farmerBlock = f.block!!
            }
            is FarmerLookup.NotFound -> showNotRegisteredDialog = true
            else -> {}
        }
    }
    
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
    // Premium per goat, editable on step 6 and sent with the batch.
    var premiumAmount by rememberSaveable { mutableStateOf("350") }
    var paymentMode by rememberSaveable { mutableStateOf("Cash") }

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
        // Vaccination is now captured per goat, so reset it to the defaults too.
        pprGiven = true; etttGiven = true; fmdGiven = false; poxGiven = false
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
            pprGiven = pprGiven, etttGiven = etttGiven, fmdGiven = fmdGiven, poxGiven = poxGiven,
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
        pprGiven = g.pprGiven; etttGiven = g.etttGiven; fmdGiven = g.fmdGiven; poxGiven = g.poxGiven
    }

    val steps = listOf(
        languageState.value.getT("Farmer Information", "किसान जानकारी", "କୃଷକ ସୂଚନା"),
        languageState.value.getT("Goat Details", "बकरी का विवरण", "ଛେଳି ବିବରଣୀ"),
        languageState.value.getT("Goat Photos", "बकरी की तस्वीरें", "ଛେଳି ଫଟୋ"),
        languageState.value.getT("Vaccination History", "टीकाकरण इतिहास", "ଟୀକାକରଣ ଇତିହାସ"),
        languageState.value.getT("Goats Added", "जोड़ी गई बकरियां", "ଯୋଡ଼ାଯାଇଥିବା ଛେଳି"),
        languageState.value.getT("Premium Payment", "प्रीमियम भुगतान", "ପ୍ରିମିୟମ ଦେୟ"),
        languageState.value.getT("Policy Generated", "पॉलिसी जेनरेट हुई", "ନୀତି ପ୍ରସ୍ତୁତ ହୋଇଛି")
    )

    if (showNotRegisteredDialog) {
        AlertDialog(
            onDismissRequest = { showNotRegisteredDialog = false },
            icon = { Icon(Icons.Default.Warning, null, tint = Color(0xFFD32F2F)) },
            title = { Text(languageState.value.getT("Farmer not registered", "किसान पंजीकृत नहीं है", "କୃଷକ ପଞ୍ଜିକୃତ ନୁହେଁ"), fontWeight = FontWeight.Bold, color = Color.Black) },
            text = {
                Text(
                    languageState.value.getT(
                        "This mobile number is not registered yet. Please register the farmer first.",
                        "यह मोबाइल नंबर अभी तक पंजीकृत नहीं है। कृपया पहले किसान को पंजीकृत करें।",
                        "ଏହି ମୋବାଇଲ୍ ନମ୍ବର ଏପର୍ଯ୍ୟନ୍ତ ପଞ୍ଜିକୃତ ନୁହେଁ। ଦୟାକରି ପ୍ରଥମେ କୃଷକଙ୍କୁ ପଞ୍ଜିକୃତ କରନ୍ତୁ।"
                    ),
                    color = Color(0xFF5B6660)
                )
            },
            confirmButton = {
                TextButton(onClick = { showNotRegisteredDialog = false }) {
                    Text(languageState.value.getT("OK", "ठीक है", "ଠିକ୍ ଅଛି"), color = PrimaryGreen, fontWeight = FontWeight.Bold)
                }
            }
        )
    }

    Scaffold(
        // background before the inset padding, so white also covers the area behind
        // the transparent system nav bar instead of leaving the window showing.
        modifier = Modifier.fillMaxSize().background(Color.White).navigationBarsPadding(),
        containerColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color(0xFF14231A),
                    navigationIconContentColor = Color(0xFF14231A)
                ),
                title = {
                    Text(
                        languageState.value.getT("Enrollment (Step $currentStep of 7)", "नामांकन (चरण $currentStep of 7)", "ପଞ୍ଜିକରଣ (ପଦକ୍ଷେପ $currentStep of 7)"),
                        fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF14231A),
                        maxLines = 1
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
                EnrollmentSectionHeader(steps[currentStep - 1], stepIcons[currentStep - 1])

                Spacer(modifier = Modifier.height(20.dp))
                
                when(currentStep) {
                    1 -> EnrollmentFarmerStep(
                        farmerName, { if (it.all { char -> char.isLetter() || char.isWhitespace() }) farmerName = it },
                        mobileNumber, { if (it.length <= 10) mobileNumber = it },
                        farmerState, { farmerState = it; farmerDistrict = ""; farmerBlock = ""; locationVm.loadDistricts(it) }, stateOptions,
                        farmerDistrict, { farmerDistrict = it; farmerBlock = ""; locationVm.loadBlocks(farmerState, it) }, districtOptions,
                        farmerBlock, { farmerBlock = it }, blockOptions,
                        village, { if (it.all { char -> char.isLetter() || char.isWhitespace() }) village = it },
                        location, { location = it },
                        aadhaar, { if (it.length <= 12) aadhaar = it },
                        mobileLookup = farmerLookup
                    )
                    2 -> EnrollmentGoatStep(breed, { breed = it }, gender, { gender = it }, age, { age = it }, ageUnit, { ageUnit = it }, weight, { weight = it }, colorMarks, { colorMarks = it }, earTagNumber, { earTagNumber = it })
                    3 -> EnrollmentPhotoStep(
                        leftUri = leftPhotoUri, onLeftCapture = { leftPhotoUri = it },
                        rightUri = rightPhotoUri, onRightCapture = { rightPhotoUri = it },
                        frontUri = frontPhotoUri, onFrontCapture = { frontPhotoUri = it },
                        tagUri = tagPhotoUri, onTagCapture = { tagPhotoUri = it }
                    )
                    4 -> EnrollmentVaccinationStep(
                        pprGiven, { pprGiven = it }, etttGiven, { etttGiven = it },
                        fmdGiven, { fmdGiven = it }, poxGiven, { poxGiven = it }
                    )
                    5 -> EnrollmentGoatsAddedStep(
                        goats = goats,
                        onAddAnother = { resetGoatForm(); editingIndex = null; currentStep = 2 },
                        onEdit = { i -> loadGoatIntoForm(goats[i]); editingIndex = i; currentStep = 2 },
                        onDelete = { i -> goats.removeAt(i) },
                        onContinue = { currentStep = 6 }
                    )
                    6 -> EnrollmentPaymentStep(
                        goatCount = goats.size,
                        amount = premiumAmount,
                        onAmountChange = { premiumAmount = it.filter { c -> c.isDigit() }.take(6) },
                        selectedMethod = paymentMode,
                        onMethodChange = { paymentMode = it },
                    )
                    7 -> EnrollmentPoliciesStep(farmerName, enrollResults, goats, onFinish = onComplete)
                }
            }

            val tagIsUnique = goats.filterIndexed { i, _ -> i != editingIndex }
                .none { it.earTag.equals(earTagNumber.trim(), ignoreCase = true) }
            val isStepValid = when(currentStep) {
                1 -> farmerLookup is FarmerLookup.Found && farmerName.isNotBlank() && mobileNumber.length == 10 && farmerState.isNotBlank() && farmerDistrict.isNotBlank() && farmerBlock.isNotBlank() && village.isNotBlank() && location.isNotBlank() && aadhaar.length == 12
                2 -> breed.isNotBlank() && gender.isNotBlank() && age.isNotBlank() && weight.isNotBlank() && colorMarks.isNotBlank() && earTagNumber.isNotBlank() && tagIsUnique
                3 -> leftPhotoUri != null && rightPhotoUri != null && frontPhotoUri != null && tagPhotoUri != null
                6 -> (premiumAmount.toIntOrNull() ?: 0) > 0
                else -> true
            }

            // Steps 5 (Goats Added) and 7 (Policy Generated) carry their own buttons.
            if (currentStep != 5 && currentStep != 7) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    if (currentStep == 2 || currentStep == 3 || currentStep == 4 || currentStep == 6) {
                        OutlinedButton(
                            onClick = {
                                when (currentStep) {
                                    2 -> { editingIndex = null; currentStep = if (goats.isNotEmpty()) 5 else 1 }
                                    4 -> currentStep = 3   // Vaccination -> Photos
                                    else -> currentStep--   // 3 -> 2, 6 -> 5 (Goats Added)
                                }
                            },
                            modifier = Modifier.weight(1f).height(56.dp),
                            shape = RoundedCornerShape(16.dp),
                            border = BorderStroke(1.5.dp, PrimaryGreen)
                        ) {
                            Text(languageState.value.getT("Back", "पीछे", "ପଛକୁ"), color = PrimaryGreen, fontWeight = FontWeight.Bold)
                        }
                    }

                    Button(
                        onClick = {
                            when (currentStep) {
                                3 -> currentStep = 4   // Photos -> Vaccination (goat committed after vaccination)
                                4 -> {
                                    // Vaccination done for THIS goat: commit it (with its own vaccines)
                                    // into the list, then show Goats Added.
                                    val draft = currentGoatDraft()
                                    val idx = editingIndex
                                    if (idx != null && idx in goats.indices) goats[idx] = draft else goats.add(draft)
                                    editingIndex = null
                                    currentStep = 5
                                }
                                6 -> {
                                    // Submit the whole batch: each goat carries its own vaccines now.
                                    enrollVm.enrollBatch(
                                        farmerName = farmerName, farmerMobile = mobileNumber,
                                        village = village.ifBlank { null },
                                        block = farmerBlock.ifBlank { null },
                                        district = farmerDistrict.ifBlank { null },
                                        state = farmerState.ifBlank { null },
                                        gpsLocation = location.ifBlank { null },
                                        aadhaarId = aadhaar.ifBlank { null },
                                        goats = goats.toList(),
                                        paymentMode = paymentMode.lowercase(),
                                        amount = premiumAmount.toDoubleOrNull() ?: 350.0,
                                    )
                                }
                                7 -> onComplete()
                                else -> currentStep++   // 1 -> 2, 2 -> 3
                            }
                        },
                        enabled = isStepValid && !isSubmitting,
                        modifier = Modifier.weight(1f).height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp, pressedElevation = 0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen, disabledContainerColor = PrimaryGreen.copy(alpha = 0.5f))
                    ) {
                        Text(
                            text = when {
                                isSubmitting -> enrollProgress ?: languageState.value.getT("Submitting…", "प्रस्तुत हो रहा है…", "ଦାଖଲ ହେଉଛି…")
                                currentStep == 7 -> languageState.value.getT("Finish Enrollment", "नामांकन पूरा करें", "ପଞ୍ଜିକରଣ ଶେଷ କରନ୍ତୁ")
                                currentStep == 6 -> languageState.value.getT("Submit", "जमा करें", "ଦାଖଲ କରନ୍ତୁ")
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
fun EnrollmentFarmerStep(
    name: String, onNameChange: (String) -> Unit,
    phone: String, onPhoneChange: (String) -> Unit,
    state: String, onStateChange: (String) -> Unit, stateOptions: List<String>,
    district: String, onDistrictChange: (String) -> Unit, districtOptions: List<String>,
    block: String, onBlockChange: (String) -> Unit, blockOptions: List<String>,
    village: String, onVillageChange: (String) -> Unit,
    location: String, onLocationChange: (String) -> Unit,
    aadhaar: String, onAadhaarChange: (String) -> Unit,
    mobileLookup: FarmerLookup = FarmerLookup.Idle
) {
    val languageState = LocalAppLanguage.current
    val fetchGps = rememberGpsFetcher(onResult = { onLocationChange(it) })
    val mobileError = mobileLookup is FarmerLookup.NotFound || mobileLookup is FarmerLookup.Error
    Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
        EnrollmentTextField(label = languageState.value.getT("Farmer Name *", "किसान का नाम *", "କୃଷକଙ୍କ ନାମ *"), value = name, onValueChange = onNameChange, placeholder = "Full Name", leadingIcon = Icons.Default.Person, iconTint = IconGreen)
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            EnrollmentTextField(label = languageState.value.getT("Mobile Number *", "मोबाइल नंबर *", "ମୋବାଇଲ୍ ନମ୍ବର *"), value = phone, onValueChange = onPhoneChange, placeholder = "10-digit number", keyboardType = KeyboardType.Phone, prefix = "+91 ", leadingIcon = Icons.Default.Phone, borderColor = if (mobileError) Color(0xFFD32F2F) else PrimaryGreen, iconTint = if (mobileError) Color(0xFFD32F2F) else IconBlue)
            when (val fl = mobileLookup) {
                is FarmerLookup.Loading -> Text(languageState.value.getT("Checking registration…", "पंजीकरण जांच हो रही है…", "ପଞ୍ଜିକରଣ ଯାଞ୍ଚ ହେଉଛି…"), fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(start = 4.dp))
                is FarmerLookup.Found -> Text("✓ " + languageState.value.getT("Registered", "पंजीकृत", "ପଞ୍ଜିକୃତ") + ": ${fl.farmer.fullName ?: "-"}", fontSize = 12.sp, color = PrimaryGreen, fontWeight = FontWeight.Medium, modifier = Modifier.padding(start = 4.dp))
                is FarmerLookup.NotFound -> Text(languageState.value.getT("This mobile number is not registered yet. Please register the farmer first.", "यह मोबाइल नंबर अभी तक पंजीकृत नहीं है। कृपया पहले किसान को पंजीकृत करें।", "ଏହି ମୋବାଇଲ୍ ନମ୍ବର ଏପର୍ଯ୍ୟନ୍ତ ପଞ୍ଜିକୃତ ନୁହେଁ। ଦୟାକରି ପ୍ରଥମେ କୃଷକଙ୍କୁ ପଞ୍ଜିକୃତ କରନ୍ତୁ।"), fontSize = 12.sp, color = Color(0xFFD32F2F), modifier = Modifier.padding(start = 4.dp))
                is FarmerLookup.Error -> Text(fl.message, fontSize = 12.sp, color = Color(0xFFD32F2F), modifier = Modifier.padding(start = 4.dp))
                else -> {}
            }
        }
        EnrollmentDropdownField(label = languageState.value.getT("State *", "राज्य *", "ରାଜ୍ୟ *"), selectedValue = state, options = stateOptions, onValueChange = onStateChange, placeholder = languageState.value.getT("Select State", "राज्य चुनें", "ରାଜ୍ୟ ବାଛନ୍ତୁ"), leadingIcon = Icons.Default.Public, iconTint = IconIndigo)
        EnrollmentDropdownField(label = languageState.value.getT("District *", "जिला *", "ଜିଲ୍ଲା *"), selectedValue = district, options = districtOptions, onValueChange = onDistrictChange, placeholder = languageState.value.getT("Select District", "जिला चुनें", "ଜିଲ୍ଲା ବାଛନ୍ତୁ"), leadingIcon = Icons.Default.LocationCity, iconTint = IconBlue)
        EnrollmentDropdownField(label = languageState.value.getT("Block *", "ब्लॉक *", "ବ୍ଲକ୍ *"), selectedValue = block, options = blockOptions, onValueChange = onBlockChange, placeholder = languageState.value.getT("Select Block", "ब्लॉक चुनें", "ବ୍ଲକ୍ ବାଛନ୍ତୁ"), leadingIcon = Icons.Default.Business, iconTint = IconTeal)
        EnrollmentTextField(label = languageState.value.getT("Village *", "गाँव *", "ଗ୍ରାମ *"), value = village, onValueChange = onVillageChange, placeholder = "Village Name", leadingIcon = Icons.Default.Home, iconTint = IconAmber)
        EnrollmentTextField(
            label = languageState.value.getT("GPS Location *", "जीपीएस स्थान *", "GPS ଅବସ୍ଥାନ *"),
            value = location, onValueChange = onLocationChange,
            placeholder = languageState.value.getT("Tap the icon to fetch GPS", "जीपीएस लाने के लिए आइकन दबाएं", "GPS ଆଣିବାକୁ ଆଇକନ୍ ଦବାନ୍ତୁ"),
            leadingIcon = Icons.Default.LocationOn,
            iconTint = IconTeal,
            trailingIcon = Icons.Default.MyLocation,
            onTrailingIconClick = { fetchGps() }
        )
        EnrollmentTextField(label = languageState.value.getT("Aadhaar / Gov ID *", "आधार / सरकारी आईडी *", "ଆଧାର / ସରକାରୀ ID *"), value = aadhaar, onValueChange = onAadhaarChange, placeholder = "12-digit number", keyboardType = KeyboardType.Number, leadingIcon = Icons.Default.Badge, iconTint = IconIndigo)
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
            onValueChange = onBreedChange,
            leadingPainter = painterResource(R.drawable.ic_ewe_custom),
            iconTint = IconRose
        )
        EnrollmentDropdownField(
            label = languageState.value.getT("Gender *", "लिंग *", "ଲିଙ୍ଗ *"),
            selectedValue = gender,
            placeholder = languageState.value.getT("Select Gender", "लिंग चुनें", "ଲିଙ୍ଗ ବାଛନ୍ତୁ"),
            options = listOf(
                languageState.value.getT("Female", "मादा", "ମାଈ"),
                languageState.value.getT("Male", "नर", "ଅଣ୍ଡିରା")
            ),
            onValueChange = onGenderChange,
            leadingIcon = Icons.Default.Transgender,
            iconTint = IconPurple
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Box(modifier = Modifier.weight(0.6f)) {
                EnrollmentTextField(label = languageState.value.getT("Age (Approx) *", "आयु (लगभग) *", "ବୟସ (ପ୍ରାୟ) *"), value = age, onValueChange = onAgeChange, placeholder = "12", keyboardType = KeyboardType.Number, leadingIcon = Icons.Default.CalendarToday, iconTint = IconAmber)
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

        EnrollmentTextField(label = languageState.value.getT("Weight (Approx) *", "वजन (लगभग) *", "ଓଜନ (ପ୍ରୟ) *"), value = weight, onValueChange = onWeightChange, placeholder = "18", keyboardType = KeyboardType.Number, suffix = "KG", leadingIcon = Icons.Default.Scale, iconTint = IconTeal)
        EnrollmentTextField(label = languageState.value.getT("Color / Marks *", "रंग / निशान *", "ରଙ୍ଗ / ଚିହ୍ନ *"), value = color, onValueChange = onColorChange, placeholder = "Black with White Spots", leadingIcon = Icons.Default.Palette, iconTint = IconIndigo)

        EnrollmentTextField(
            label = languageState.value.getT("Ear Tag Number *", "कान का टैग नंबर *", "କାନ ଟ୍ୟାଗ୍ ନମ୍ବର *"),
            value = earTag, onValueChange = onTagChange, placeholder = "e.g. ET-240801",
            leadingIcon = Icons.Default.Label,
            iconTint = IconBlue,
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
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F5F0)),
            shape = RoundedCornerShape(14.dp)
        ) {
            Row(
                modifier = Modifier.padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(34.dp).clip(CircleShape).background(PrimaryGreen.copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Info, null, tint = PrimaryGreen, modifier = Modifier.size(20.dp))
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    languageState.value.getT("Ensure clear photos in good lighting", "अच्छी रोशनी में स्पष्ट फोटो सुनिश्चित करें", "ଭଲ ଆଲୋକରେ ସ୍ପଷ୍ଟ ଫଟୋ ନିଶ୍ଚିତ କରନ୍ତୁ"),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF3D473E)
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
                    tint = PrimaryGreen,
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
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        VaccineStatusItem("PPR Vaccine", pprGiven) { onPpr(it) }
        VaccineStatusItem("ET + TT Vaccine", etttGiven) { onEtt(it) }
        VaccineStatusItem("FMD Vaccine", fmdGiven) { onFmd(it) }
        VaccineStatusItem("Goat Pox Vaccine", poxGiven) { onPox(it) }
    }
}

@Composable
fun EnrollmentPaymentStep(
    goatCount: Int = 1,
    amount: String = "350",
    onAmountChange: (String) -> Unit = {},
    selectedMethod: String = "Cash",
    onMethodChange: (String) -> Unit = {},
) {
    val languageState = LocalAppLanguage.current
    val total = (amount.toIntOrNull() ?: 0) * goatCount.coerceAtLeast(1)

    Column {
        // Premium hero banner: rich green, with the amount centred on it.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(Brush.linearGradient(listOf(Color(0xFF2E7D32), Color(0xFF15501C)))),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(languageState.value.getT("Total Premium Amount", "कुल प्रीमियम राशि", "ମୋଟ ପ୍ରିମିୟମ ପରିମାଣ"), fontSize = 15.sp, color = Color.White)
                Spacer(modifier = Modifier.height(4.dp))
                Text("₹ $total", fontSize = 38.sp, color = Color.White, fontWeight = FontWeight.Bold)
                if (goatCount > 1) {
                    Text("$goatCount × ₹${amount.ifBlank { "0" }}", fontSize = 12.sp, color = Color.White.copy(alpha = 0.85f))
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        Text(languageState.value.getT("Select Payment Method", "भुगतान विधि चुनें", "ଦେୟ ପଦ୍ଧତି ଚୟନ କରନ୍ତୁ"), fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            PaymentMethodChip("Cash", selectedMethod == "Cash", modifier = Modifier.weight(1f)) { onMethodChange("Cash") }
            PaymentMethodChip("UPI", selectedMethod == "UPI", modifier = Modifier.weight(1f)) { onMethodChange("UPI") }
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
        EnrollmentTextField(label = languageState.value.getT("Premium Amount", "प्रीमियम राशि", "ପ୍ରିମିୟମ ପରିମାଣ"), value = amount, onValueChange = onAmountChange, keyboardType = KeyboardType.Number, prefix = "₹", leadingIcon = Icons.Default.Payments, iconTint = IconGreen)
        Spacer(modifier = Modifier.height(16.dp))
        EnrollmentTextField(label = languageState.value.getT("Receipt Number", "रसीद संख्या", "ରସିଦ ନମ୍ବର"), value = "", onValueChange = {}, readOnly = true, placeholder = languageState.value.getT("Auto-generated after enrollment", "नामांकन के बाद स्वतः जनरेट", "ପଞ୍ଜିକରଣ ପରେ ସ୍ୱୟଂ ପ୍ରସ୍ତୁତ"), leadingIcon = Icons.Default.Receipt, iconTint = IconAmber)
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
        // Total counter — the banner fills the card, with the count sitting on the
        // artwork's open left side. matchParentSize means the Row still sets the height.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
        ) {
            Image(
                painter = painterResource(R.drawable.add_goat),
                contentDescription = null,
                modifier = Modifier.matchParentSize(),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
            )
            Row(
                modifier = Modifier.padding(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(48.dp).clip(RoundedCornerShape(12.dp)).background(PrimaryGreen),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Description, null, tint = Color.White, modifier = Modifier.size(24.dp))
                }
                Spacer(modifier = Modifier.width(14.dp))
                Column {
                    Text(languageState.value.getT("Total Goats Added", "जोड़ी गई कुल बकरियां", "ମୋଟ ଯୋଡ଼ାଯାଇଥିବା ଛେଳି"), fontSize = 13.sp, color = Color(0xFF5B6660))
                    Text("${goats.size}", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = PrimaryGreen)
                }
            }
        }

        goats.forEachIndexed { index, goat ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(18.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                border = BorderStroke(1.dp, Color(0xFFEDF0EA))
            ) {
                Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        val photo = goat.photos.getOrNull(2) ?: goat.photos.firstOrNull()
                        Box(
                            modifier = Modifier.size(72.dp).clip(CircleShape).background(Color(0xFFF0F0F0)),
                            contentAlignment = Alignment.Center
                        ) {
                            if (photo != null) {
                                AsyncImage(model = photo, contentDescription = null, modifier = Modifier.fillMaxSize(), contentScale = androidx.compose.ui.layout.ContentScale.Crop)
                            } else {
                                Icon(painterResource(R.drawable.ic_ewe_custom), null, tint = Color.Gray, modifier = Modifier.size(34.dp))
                            }
                        }
                        Spacer(modifier = Modifier.width(14.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(goat.earTag, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFF14231A))
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Label, null, tint = PrimaryGreen, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    languageState.value.getT("Ear Tag: ", "कान का टैग: ", "କାନ ଟ୍ୟାଗ୍: "),
                                    fontSize = 13.sp, color = Color.Gray
                                )
                                Text(goat.earTag, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = PrimaryGreen)
                            }
                        }
                        // Edit / Delete as tinted badge buttons, matching the reference.
                        GoatActionBadge(Icons.Default.Edit, languageState.value.getT("Edit", "संपादित करें", "ସମ୍ପାଦନା"), PrimaryGreen, Color(0xFFEAF3E7)) { onEdit(index) }
                        Spacer(modifier = Modifier.width(10.dp))
                        GoatActionBadge(Icons.Default.Delete, languageState.value.getT("Delete", "हटाएं", "ବିଲୋପ"), Color(0xFFD32F2F), Color(0xFFFDECEC)) { onDelete(index) }
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    HorizontalDivider(color = Color(0xFFEDF0EA))
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        GoatInfoCell(Icons.Default.Pets, languageState.value.getT("Breed", "नस्ल", "ପ୍ରଜାତି"), goat.breed, Modifier.weight(1f))
                        GoatInfoCell(Icons.Default.Transgender, languageState.value.getT("Gender", "लिंग", "ଲିଙ୍ଗ"), goat.genderLabel, Modifier.weight(1f))
                        GoatInfoCell(Icons.Default.Scale, languageState.value.getT("Weight", "वजन", "ଓଜନ"), goat.weightLabel, Modifier.weight(1f))
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        "${languageState.value.getT("Age", "आयु", "ବୟସ")}: ${goat.ageLabel}   •   ${goat.color}",
                        fontSize = 12.sp, color = Color.Gray
                    )
                }
            }
        }

        OutlinedButton(
            onClick = onAddAnother,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.5.dp, PrimaryGreen)
        ) {
            Icon(Icons.Default.Add, null, tint = PrimaryGreen)
            Spacer(modifier = Modifier.width(8.dp))
            Text(languageState.value.getT("Add Another Goat", "एक और बकरी जोड़ें", "ଆଉ ଏକ ଛେଳି ଯୋଡ଼ନ୍ତୁ"), color = PrimaryGreen, fontWeight = FontWeight.Bold)
        }

        Button(
            onClick = onContinue,
            enabled = goats.isNotEmpty(),
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp, pressedElevation = 0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen, disabledContainerColor = PrimaryGreen.copy(alpha = 0.5f))
        ) {
            Text(languageState.value.getT("Continue", "जारी रखें", "ଜାରି ରଖନ୍ତୁ"), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

/** Tinted rounded-square action button with a label below (Edit / Delete on a goat card). */
@Composable
fun GoatActionBadge(icon: ImageVector, label: String, tint: Color, bg: Color, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(onClick = onClick, shape = RoundedCornerShape(12.dp), color = bg, modifier = Modifier.size(44.dp)) {
            Box(contentAlignment = Alignment.Center) {
                Icon(icon, contentDescription = label, tint = tint, modifier = Modifier.size(22.dp))
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(label, fontSize = 11.sp, color = tint, fontWeight = FontWeight.Medium)
    }
}

/** Icon + label/value cell used in the goat summary card's info row. */
@Composable
fun GoatInfoCell(icon: ImageVector, label: String, value: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = PrimaryGreen, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Column {
            Text(label, fontSize = 11.sp, color = Color.Gray)
            Text(value, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A), maxLines = 1)
        }
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
    val receiptNumber = results.firstOrNull()?.receiptNumber ?: "—"
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
fun EnrollmentTextField(label: String, value: String, onValueChange: (String) -> Unit, placeholder: String = "", keyboardType: KeyboardType = KeyboardType.Text, prefix: String? = null, suffix: String? = null, leadingIcon: ImageVector? = null, trailingIcon: ImageVector? = null, onTrailingIconClick: (() -> Unit)? = null, readOnly: Boolean = false, borderColor: Color = PrimaryGreen, iconTint: Color = borderColor, isPassword: Boolean = false) {
    var passwordVisible by remember { mutableStateOf(false) }
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
        Text(styledLabel, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A), modifier = Modifier.padding(bottom = 8.dp))
        Box {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(placeholder, color = Color(0xFF9AA69B)) },
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                singleLine = true,
                readOnly = readOnly,
                visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
                leadingIcon = leadingIcon?.let { { FieldLeadingBadge(it, iconTint) } },
                trailingIcon = when {
                    isPassword -> {
                        {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff, null, tint = Color(0xFF8A7A63))
                            }
                        }
                    }
                    trailingIcon != null && onTrailingIconClick != null -> {
                        // Circular tinted action button (e.g. fetch-GPS), matching the reference.
                        {
                            Surface(
                                onClick = onTrailingIconClick,
                                shape = CircleShape,
                                color = borderColor.copy(alpha = 0.12f),
                                modifier = Modifier.padding(end = 6.dp).size(38.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(trailingIcon, null, tint = borderColor, modifier = Modifier.size(20.dp))
                                }
                            }
                        }
                    }
                    trailingIcon != null -> {
                        { Icon(trailingIcon, null, tint = Color.DarkGray) }
                    }
                    else -> null
                },
                prefix = prefix?.let { { Text(it, color = Color.Black, fontWeight = FontWeight.Bold) } },
                suffix = suffix?.let { { Text(it, color = Color.Black, fontWeight = FontWeight.SemiBold) } },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    unfocusedContainerColor = Color(0xFFFBFCFA),
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = Color(0xFFE1E6DE),
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
fun EnrollmentDropdownField(label: String, selectedValue: String, options: List<String>, onValueChange: (String) -> Unit, borderColor: Color = PrimaryGreen, iconTint: Color = borderColor, placeholder: String? = null, leadingIcon: ImageVector? = null, leadingPainter: Painter? = null) {
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
        Text(styledLabel, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A), modifier = Modifier.padding(bottom = 8.dp))
        Box {
            OutlinedTextField(
                value = selectedValue,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                singleLine = true,
                // Shown only while nothing is selected, so an empty field reads as a
                // prompt rather than as a value.
                placeholder = placeholder?.let { { Text(it, color = Color(0xFF9AA69B)) } },
                leadingIcon = when {
                    leadingPainter != null -> { { FieldLeadingBadge(leadingPainter, iconTint) } }
                    leadingIcon != null -> { { FieldLeadingBadge(leadingIcon, iconTint) } }
                    else -> null
                },
                trailingIcon = {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Color.DarkGray)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    unfocusedContainerColor = Color(0xFFFBFCFA),
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = Color(0xFFE1E6DE),
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
    val dashColor = Color(0xFFC7D2C2)
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .aspectRatio(1.05f)
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                // An empty slot shows a dashed outline; a captured photo fills the tile.
                .then(
                    if (uri == null) Modifier.drawBehind {
                        drawRoundRect(
                            color = dashColor,
                            style = Stroke(
                                width = 1.5.dp.toPx(),
                                pathEffect = PathEffect.dashPathEffect(floatArrayOf(16f, 12f), 0f)
                            ),
                            cornerRadius = CornerRadius(18.dp.toPx())
                        )
                    } else Modifier
                )
                .clickable { onCapture() },
            contentAlignment = Alignment.Center
        ) {
            if (uri != null) {
                AsyncImage(
                    model = uri,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(18.dp)),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                )
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier.size(64.dp).clip(CircleShape).background(PrimaryGreen.copy(alpha = 0.10f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.AddAPhoto, null, tint = PrimaryGreen, modifier = Modifier.size(30.dp))
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Capture", fontSize = 13.sp, color = Color(0xFF6B7280))
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(label, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF14231A))
    }
}

@Composable
fun VaccineStatusItem(name: String, isGiven: Boolean, themeColor: Color = PrimaryGreen, onToggle: (Boolean) -> Unit) {
    val languageState = LocalAppLanguage.current
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onToggle(!isGiven) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        border = BorderStroke(1.dp, Color(0xFFEDF0EA))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (isGiven) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                contentDescription = null,
                tint = if (isGiven) themeColor else Color(0xFFBFC8BC),
                modifier = Modifier.size(26.dp)
            )
            Spacer(modifier = Modifier.width(14.dp))
            Text(name, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color(0xFF14231A), modifier = Modifier.weight(1f))
            Surface(
                color = if (isGiven) themeColor.copy(alpha = 0.12f) else Color(0xFFFFF1E0),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = if (isGiven) languageState.value.getT("Given", "दिया गया", "ଦିଆଯାଇଛି")
                           else languageState.value.getT("Pending", "लंबित", "ବାକି ଅଛି"),
                    color = if (isGiven) themeColor else AccentOrange,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp)
                )
            }
        }
    }
}

@Composable
fun PaymentMethodChip(label: String, isSelected: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Surface(
        onClick = { onClick() },
        modifier = modifier.height(60.dp),
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) PrimaryGreen else Color.White,
        border = if (isSelected) null else BorderStroke(1.dp, Color(0xFFE1E6DE)),
        shadowElevation = if (isSelected) 2.dp else 0.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(label, color = if (isSelected) Color.White else Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
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
    ExitConfirmationHandler()
    var showNotifications by remember { mutableStateOf(false) }
    if (showNotifications) NotificationSheet(userRole = UserRole.FARMER, themeColor = PrimaryBlue) { showNotifications = false }
    val languageState = LocalAppLanguage.current
    val savedName by sessionManager.userName.collectAsState(initial = null)
    val profileVm: ProfileViewModel = hiltViewModel()
    val dbProfile by profileVm.profile.collectAsState()
    val userName = dbProfile?.fullName ?: savedName ?: ""
    SyncRemoteProfilePhoto(dbProfile?.photo)

    val farmerVm: FarmerHomeViewModel = hiltViewModel()
    val policiesState by farmerVm.policies.collectAsState()
    val schedule by farmerVm.schedule.collectAsState()
    val policies = (policiesState as? UiState.Success)?.data?.policies ?: emptyList()

    // Re-fetch whenever the dashboard returns to the foreground, so the insured
    // count drops right after a death report instead of waiting for a restart.
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) farmerVm.refresh()
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    ResponsiveLayout(
        compact = {
            Scaffold(
                modifier = Modifier.fillMaxSize().navigationBarsPadding(),
                bottomBar = { FarmerBottomBar(navController) },
                contentWindowInsets = WindowInsets(0, 0, 0, 0)
            ) { padding ->
                FarmerContent(padding, navController, userName, policies, schedule, dbProfile?.photo) { showNotifications = true }
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
                FarmerContent(PaddingValues(0.dp), navController, userName, policies, schedule, dbProfile?.photo) { showNotifications = true }
            }
        }
    )
}

@Composable
fun FarmerContent(padding: PaddingValues, navController: NavHostController, userName: String, policies: List<PolicyOut>, schedule: List<VaccinationScheduleItem>, profilePhoto: String? = null, onNotificationClick: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val lang = languageState.value
    val inr = remember { java.text.NumberFormat.getInstance(java.util.Locale("en", "IN")) }
    val notifVm: NotificationsViewModel = androidx.hilt.navigation.compose.hiltViewModel()
    val unread by notifVm.unread.collectAsState()
    LaunchedEffect(Unit) { notifVm.refreshUnread() }

    Column(
        modifier = Modifier
            .padding(bottom = padding.calculateBottomPadding())
            .fillMaxSize()
            .background(Color.White)
    ) {
        FarmerHeader(
            name = userName,
            unreadCount = unread,
            onNotificationClick = onNotificationClick,
            onProfileClick = { navController.navigate("profile") },
            remotePhoto = profilePhoto
        )

        // Only alive, actively insured goats count on this screen: a reported-dead
        // goat comes back as "dead" (then "claimed") and drops out of both counts.
        val activeGoats = policies.count { it.status == "active" }

        // Matches the Didi dashboard's header ↔ hero gap.
        Spacer(Modifier.height(12.dp))

        // ---- hero: My Insured Goats — pinned above the scroll, like the Didi hero ----
        Card(shape = RoundedCornerShape(22.dp), modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).height(138.dp), elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)) {
            Box(Modifier.fillMaxSize()) {
                Image(
                    painterResource(R.drawable.claim_banner), null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                    alignment = Alignment.TopEnd
                )
                Box(Modifier.fillMaxSize().background(
                    Brush.horizontalGradient(0.0f to Color(0xCC1B5230), 0.45f to Color(0x881B5230), 1.0f to Color(0x001B5230))
                ))
                Column(Modifier.fillMaxSize().padding(horizontal = 20.dp, vertical = 16.dp), verticalArrangement = Arrangement.SpaceBetween) {
                    Text(lang.getT("My Insured Goats", "मेरी बीमित बकरियां", "ମୋର ବୀମାକୃତ ଛେଳି"), color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                    Text("$activeGoats", color = Color.White, fontSize = 38.sp, fontWeight = FontWeight.Bold)
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { navController.navigate("goat_list") }) {
                        Text(lang.getT("View all", "सभी देखें", "ସବୁ ଦେଖନ୍ତୁ"), color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                        Icon(Icons.Default.ChevronRight, null, tint = Color.White, modifier = Modifier.size(20.dp))
                    }
                }
            }
        }

        // ---- everything below the hero scrolls ----
        Column(
            modifier = Modifier.weight(1f).fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(24.dp))

            // ---- My Goat header ----
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(lang.getT("My Goat ($activeGoats)", "मेरी बकरी ($activeGoats)", "ମୋର ଛେଳି ($activeGoats)"), fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A))
                Text(
                    lang.getT("View All", "सभी देखें", "ସବୁ ଦେଖନ୍ତୁ"),
                    color = PrimaryGreen, fontWeight = FontWeight.Bold, fontSize = 14.sp,
                    modifier = Modifier.clickable { navController.navigate("goat_list") }
                )
            }

            Spacer(Modifier.height(14.dp))

            // Home shows only active goats, newest enrolled first. Dead/claimed/inactive
            // goats never appear here — they live on the My Goats list (View All).
            // Sequential policy numbers (…-0003 > …-0002) give the newest first.
            val activeHomeGoats = policies
                .filter { it.status == "active" }
                .sortedByDescending { it.policyNumber }

            if (activeHomeGoats.isEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, Color(0xFFEDF0EA)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(painterResource(R.drawable.ic_ewe_custom), null, tint = Color.LightGray, modifier = Modifier.size(40.dp))
                        Spacer(Modifier.height(8.dp))
                        Text(lang.getT("No active goats", "कोई सक्रिय बकरी नहीं", "କୌଣସି ସକ୍ରିୟ ଛେଳି ନାହିଁ"), color = Color.Gray, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                        Text(lang.getT("Your enrolled goats will appear here.", "आपकी नामांकित बकरियां यहां दिखाई देंगी।", "ଆପଣଙ୍କ ପଞ୍ଜିକୃତ ଛେଳି ଏଠାରେ ଦେଖାଯିବ।"), color = Color.LightGray, fontSize = 12.sp, textAlign = TextAlign.Center)
                    }
                }
            } else {
                // Home previews the most recently enrolled active goat; View All shows the rest.
                activeHomeGoats.first().let { p ->
                    FarmerGoatListCard(
                        policy = p,
                        money = { "₹ " + inr.format(it.toLong()) },
                        onReportDeath = { navController.navigate("farmer_report_death") },
                        onTrackClaim = { navController.navigate("claim_list") },
                        onViewDetails = { navController.navigate("goat_details/${p.policyNumber}") },
                    )
                    Spacer(Modifier.height(14.dp))
                }
            }

            Spacer(Modifier.height(6.dp))

            // ---- reassurance banner (text is baked into the artwork) ----
            Image(
                painter = painterResource(R.drawable.farmer_bannerlower),
                contentDescription = lang.getT("Keep your goats insured for a worry-free future", "एक चिंता-मुक्त भविष्य के लिए अपनी बकरियों का बीमा कराएं", "ଚିନ୍ତାମୁକ୍ତ ଭବିଷ୍ୟତ ପାଇଁ ଆପଣଙ୍କ ଛେଳିର ବୀମା କରନ୍ତୁ"),
                modifier = Modifier.fillMaxWidth()
                    .aspectRatio(1024f / 278f)
                    .clip(RoundedCornerShape(18.dp)),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
            )

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun FarmerPolicyStat(icon: ImageVector, label: String, value: String, modifier: Modifier = Modifier, tint: Color = PrimaryGreen) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier.size(38.dp).clip(RoundedCornerShape(10.dp)).background(tint.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, null, tint = tint, modifier = Modifier.size(20.dp))
        }
        Spacer(Modifier.width(10.dp))
        Column {
            Text(label, fontSize = 12.sp, color = Color(0xFF8A908A))
            Text(value, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = tint, maxLines = 1)
        }
    }
}

// Mirrors DidiHomeHeader's dimensions and styling (kept as its own function so the
// two dashboards stay independent). Avatar prefers the local pick, else the remote
// profile photo (rememberHeaderAvatar), else the packaged farmer illustration.
@Composable
fun FarmerHeader(name: String, unreadCount: Int, onNotificationClick: () -> Unit = {}, onProfileClick: () -> Unit = {}, remotePhoto: String? = null) {
    val languageState = LocalAppLanguage.current
    val avatar = rememberHeaderAvatar(remotePhoto)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(start = 18.dp, end = 18.dp, top = 12.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(onClick = onProfileClick, color = Color(0xFFF0EAD6), shape = CircleShape, modifier = Modifier.size(52.dp)) {
            Box(contentAlignment = Alignment.Center) {
                if (avatar != null) {
                    AsyncImage(model = avatar, contentDescription = null, modifier = Modifier.fillMaxSize().clip(CircleShape), contentScale = androidx.compose.ui.layout.ContentScale.Crop)
                } else {
                    Image(painterResource(R.drawable.avatar_farmer), null, modifier = Modifier.fillMaxSize().clip(CircleShape), contentScale = androidx.compose.ui.layout.ContentScale.Crop)
                }
            }
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(languageState.value.getT("Hello,", "नमस्ते,", "ନମସ୍କାର,"), fontSize = 15.sp, color = Color(0xFF5B6660))
            Text(name.ifBlank { languageState.value.getT("Farmer", "किसान", "କୃଷକ") }, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = PrimaryGreen)
        }
        Box {
            Surface(onClick = onNotificationClick, color = Color.White, shape = CircleShape, shadowElevation = 2.dp, modifier = Modifier.size(46.dp)) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Notifications, null, tint = Color(0xFF2A2A2A))
                }
            }
            if (unreadCount > 0) {
                Box(modifier = Modifier.align(Alignment.TopEnd).offset(x = 2.dp, y = (-2).dp).size(20.dp).clip(CircleShape).background(Color(0xFFE53935)), contentAlignment = Alignment.Center) {
                    Text(if (unreadCount > 9) "9+" else "$unreadCount", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
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
fun DidiBottomBar(navController: NavHostController) {
    val languageState = LocalAppLanguage.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    fun go(route: String) {
        if (currentRoute != route) {
            navController.navigate(route) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
      Box(modifier = Modifier.fillMaxWidth().height(84.dp)) {
        Surface(
            color = Color.White,
            shadowElevation = 10.dp,
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().height(68.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(horizontal = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DidiNavItem(Modifier.weight(1f), Icons.Default.Home, languageState.value.getT("Home", "होम", "ମୁଖ୍ୟ ପୃଷ୍ଠା"), currentRoute == "didi_dashboard") { go("didi_dashboard") }
                DidiNavItem(Modifier.weight(1f), Icons.Default.Groups, languageState.value.getT("Farmers", "किसान", "କୃଷକ"), currentRoute == "goat_list" || currentRoute?.startsWith("goat_details") == true) { go("goat_list") }
                Spacer(Modifier.weight(1f))
                DidiNavItem(Modifier.weight(1f), Icons.Default.PendingActions, languageState.value.getT("Claims", "दावे", "ଦାବି"), currentRoute == "claim_list" || currentRoute == "claim_tracker") { go("claim_list") }
                DidiNavItem(Modifier.weight(1f), Icons.Default.SupportAgent, languageState.value.getT("Help Center", "सहायता", "ସହାୟତା"), currentRoute == "help_support") { go("help_support") }
            }
        }
        // Center raised FAB -> enrollment
        Surface(
            onClick = { go("enrollment") },
            shape = CircleShape,
            color = Color(0xFFB98A34),
            shadowElevation = 8.dp,
            border = BorderStroke(4.dp, Color.White),
            modifier = Modifier.align(Alignment.TopCenter).size(64.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(Icons.Default.Add, null, tint = Color.White, modifier = Modifier.size(30.dp))
            }
        }
      }
        Spacer(modifier = Modifier.fillMaxWidth().windowInsetsBottomHeight(WindowInsets.navigationBars).background(Color.White))
    }
}

@Composable
private fun DidiNavItem(modifier: Modifier, icon: ImageVector, label: String, selected: Boolean, onClick: () -> Unit) {
    val c = if (selected) PrimaryGreen else Color(0xFF8A908A)
    Column(
        modifier = modifier.clickable { onClick() }.padding(vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, null, tint = c, modifier = Modifier.size(24.dp))
        Spacer(Modifier.height(3.dp))
        Text(label, color = c, fontSize = 11.sp, fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal, maxLines = 1)
        Spacer(Modifier.height(3.dp))
        Box(
            Modifier.width(18.dp).height(3.dp).clip(RoundedCornerShape(2.dp))
                .background(if (selected) PrimaryGreen else Color.Transparent)
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
            label = { Text(languageState.value.getT("Goats", "बकरियां", "ଛେଳି"), fontSize = 9.sp) },
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
            icon = { Icon(Icons.Default.PendingActions, null) },
            label = { Text(languageState.value.getT("Claim", "दावा", "ଦାବି"), fontSize = 9.sp) },
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
        val time = AppTime.dateTime(n.createdAt)
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
    val languageState = LocalAppLanguage.current
    val lang = languageState.value
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
    val passwordState by profileVm.password.collectAsState()

    LaunchedEffect(saveState) {
        when (val s = saveState) {
            is SubmitState.Success -> {
                Toast.makeText(context, lang.getT("Profile updated", "प्रोफ़ाइल अपडेट किया गया", "ପ୍ରୋଫାଇଲ୍ ଅପଡେଟ୍ ହୋଇଛି"), Toast.LENGTH_SHORT).show()
                profileVm.resetSave()
            }
            is SubmitState.Error -> {
                Toast.makeText(context, s.message, Toast.LENGTH_LONG).show()
                profileVm.resetSave()
            }
            else -> {}
        }
    }

    var showChangePassword by remember { mutableStateOf(false) }
    LaunchedEffect(passwordState) {
        when (val s = passwordState) {
            is SubmitState.Success -> {
                Toast.makeText(context, lang.getT("Password updated", "पासवर्ड अपडेट हुआ", "ପାସୱାର୍ଡ ଅପଡେଟ୍ ହୋଇଛି"), Toast.LENGTH_SHORT).show()
                showChangePassword = false
                profileVm.resetPassword()
            }
            is SubmitState.Error -> {
                Toast.makeText(context, s.message, Toast.LENGTH_LONG).show()
                profileVm.resetPassword()
            }
            else -> {}
        }
    }

    val userName = dbProfile?.fullName ?: savedName ?: ""
    val userMobile = dbProfile?.mobileNumber ?: savedMobile ?: ""
    val userVillage = dbProfile?.village ?: savedVillage

    val effectiveRole = when (dbProfile?.role) {
        "fr" -> UserRole.FARMER
        "co" -> UserRole.COORDINATOR
        "sd" -> UserRole.SURAKSHA_DIDI
        else -> userRole
    }
    val roleLabel = when (effectiveRole) {
        UserRole.FARMER -> lang.getT("Farmer", "किसान", "କୃଷକ")
        UserRole.COORDINATOR -> lang.getT("Coordinator", "समन्वयक", "ସମନ୍ଵୟକାରୀ")
        else -> lang.getT("Suraksha Didi", "सुरक्षा दीदी", "ସୁରକ୍ଷା ଦିଦି")
    }

    fun saveImageToInternalStorage(uri: Uri): Uri? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val file = File(context.filesDir, "profile_pic.jpg")
            val outputStream = FileOutputStream(file)
            inputStream?.use { input -> outputStream.use { output -> input.copyTo(output) } }
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
                profileVm.updateProfile(name = null, village = null, photoUri = internalUri)
            }
        }
    }

    var tempUriStr by rememberSaveable { mutableStateOf<String?>(null) }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && tempUriStr != null) {
            val internalUri = saveImageToInternalStorage(Uri.parse(tempUriStr!!))
            if (internalUri != null) {
                profileImageState.value = internalUri
                profileVm.updateProfile(name = null, village = null, photoUri = internalUri)
            }
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted -> if (isGranted) tempUriStr?.let { cameraLauncher.launch(Uri.parse(it)) } }

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

    var showPhotoOptions by remember { mutableStateOf(false) }
    var showEditProfile by remember { mutableStateOf(false) }
    var showLanguagePicker by remember { mutableStateOf(false) }
    var showLogoutConfirm by remember { mutableStateOf(false) }

    // ---------------- dialogs ----------------
    if (showPhotoOptions) {
        AlertDialog(
            onDismissRequest = { showPhotoOptions = false },
            containerColor = Color.White,
            shape = RoundedCornerShape(20.dp),
            title = { Text(lang.getT("Profile Photo", "प्रोफ़ाइल फोटो", "ପ୍ରୋଫାଇଲ୍ ଫଟୋ"), fontWeight = FontWeight.Bold, color = Color.Black) },
            text = {
                Column {
                    Surface(color = Color.Transparent, onClick = { showPhotoOptions = false; launchCamera() }) {
                        Row(Modifier.fillMaxWidth().padding(vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CameraAlt, null, tint = PrimaryGreen, modifier = Modifier.size(26.dp))
                            Spacer(Modifier.width(16.dp))
                            Text(lang.getT("Take Photo", "फोटो लें", "ଫଟୋ ନିଅନ୍ତୁ"), fontSize = 16.sp, color = Color.Black)
                        }
                    }
                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.4f))
                    Surface(color = Color.Transparent, onClick = { showPhotoOptions = false; imagePickerLauncher.launch("image/*") }) {
                        Row(Modifier.fillMaxWidth().padding(vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.PhotoLibrary, null, tint = PrimaryGreen, modifier = Modifier.size(26.dp))
                            Spacer(Modifier.width(16.dp))
                            Text(lang.getT("Choose from Gallery", "गैलरी से चुनें", "ଗ୍ୟାଲେରୀରୁ ବାଛନ୍ତୁ"), fontSize = 16.sp, color = Color.Black)
                        }
                    }
                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.4f))
                    Surface(color = Color.Transparent, onClick = { showPhotoOptions = false; showEditProfile = true }) {
                        Row(Modifier.fillMaxWidth().padding(vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Edit, null, tint = PrimaryGreen, modifier = Modifier.size(26.dp))
                            Spacer(Modifier.width(16.dp))
                            Text(lang.getT("Edit Details", "विवरण संपादित करें", "ବିବରଣୀ ସଂପାଦନ କରନ୍ତୁ"), fontSize = 16.sp, color = Color.Black)
                        }
                    }
                }
            },
            confirmButton = {},
            dismissButton = { TextButton(onClick = { showPhotoOptions = false }) { Text(lang.getT("Cancel", "रद्द करें", "ବାତିଲ୍"), color = Color.Gray) } }
        )
    }

    if (showEditProfile) {
        var editName by remember { mutableStateOf(userName) }
        var editVillage by remember { mutableStateOf(userVillage ?: "") }
        AlertDialog(
            onDismissRequest = { showEditProfile = false },
            containerColor = Color.White,
            shape = RoundedCornerShape(20.dp),
            title = { Text(lang.getT("Edit Profile", "प्रोफ़ाइल संपादित करें", "ପ୍ରୋଫାଇଲ୍ ସଂପାଦନ କରନ୍ତୁ"), fontWeight = FontWeight.Bold, color = Color.Black) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedTextField(
                        value = editName,
                        onValueChange = { if (it.all { c -> c.isLetter() || c.isWhitespace() }) editName = it },
                        label = { Text(lang.getT("Full Name", "पूरा नाम", "ପୁରା ନାମ")) },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.Black, unfocusedTextColor = Color.Black, focusedBorderColor = PrimaryGreen)
                    )
                    OutlinedTextField(
                        value = editVillage,
                        onValueChange = { editVillage = it },
                        label = { Text(lang.getT("Location", "स्थान", "ଅବସ୍ଥାନ")) },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.Black, unfocusedTextColor = Color.Black, focusedBorderColor = PrimaryGreen)
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    profileVm.updateProfile(name = editName, village = editVillage, photoUri = null)
                    showEditProfile = false
                }) { Text(lang.getT("Save", "सहेजें", "ସଂରକ୍ଷଣ"), color = PrimaryGreen, fontWeight = FontWeight.Bold) }
            },
            dismissButton = { TextButton(onClick = { showEditProfile = false }) { Text(lang.getT("Cancel", "रद्द करें", "ବାତିଲ୍"), color = Color.Gray) } }
        )
    }

    if (showLanguagePicker) {
        AlertDialog(
            onDismissRequest = { showLanguagePicker = false },
            containerColor = Color.White,
            shape = RoundedCornerShape(20.dp),
            title = { Text(lang.getT("Language", "भाषा", "ଭାଷା"), fontWeight = FontWeight.Bold, color = Color.Black) },
            text = {
                Column {
                    AppLanguage.entries.forEach { option ->
                        Surface(color = Color.Transparent, onClick = { languageState.value = option; showLanguagePicker = false }) {
                            Row(Modifier.fillMaxWidth().padding(vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                                Text(option.label, fontSize = 16.sp, color = Color.Black, modifier = Modifier.weight(1f))
                                if (languageState.value == option) Icon(Icons.Default.CheckCircle, null, tint = PrimaryGreen)
                            }
                        }
                    }
                }
            },
            confirmButton = {},
            dismissButton = { TextButton(onClick = { showLanguagePicker = false }) { Text(lang.getT("Close", "बंद करें", "ବନ୍ଦ କରନ୍ତୁ"), color = Color.Gray) } }
        )
    }

    if (showLogoutConfirm) {
        AlertDialog(
            onDismissRequest = { showLogoutConfirm = false },
            containerColor = Color.White,
            shape = RoundedCornerShape(20.dp),
            title = { Text(lang.getT("Logout", "लॉग आउट", "ଲଗଆଉଟ୍"), fontWeight = FontWeight.Bold, color = Color.Black) },
            text = { Text(lang.getT("Are you sure you want to logout?", "क्या आप वाकई लॉग आउट करना चाहते हैं?", "ଆପଣ ନିଶ୍ଚିତ ଭାବରେ ଲଗଆଉଟ୍ କରିବାକୁ ଚାହାଁନ୍ତି?"), color = Color.Black) },
            confirmButton = {
                TextButton(onClick = { showLogoutConfirm = false; onLogout() }) {
                    Text(lang.getT("Logout", "लॉग आउट", "ଲଗଆଉଟ୍"), color = Color(0xFFD32F2F), fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = { TextButton(onClick = { showLogoutConfirm = false }) { Text(lang.getT("Cancel", "रद्द करें", "ବାତିଲ୍"), color = Color.Gray) } }
        )
    }

    // ---------------- screen ----------------
    Box(Modifier.fillMaxSize().background(Color(0xFFF3F4DD))) {
        // faint farm scene behind the header
        Image(
            painter = painterResource(R.drawable.profile_banner),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter),
            contentScale = androidx.compose.ui.layout.ContentScale.FillWidth
        )
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            // top bar over the cream header
            Box(Modifier.fillMaxWidth().statusBarsPadding().padding(vertical = 8.dp)) {
                IconButton(onClick = onBack, modifier = Modifier.align(Alignment.CenterStart).padding(start = 6.dp)) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF1B1B1B))
                }
                Text(
                    lang.getT("Profile", "प्रोफ़ाइल", "ପ୍ରୋଫାଇଲ୍"),
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B1B1B)
                )
            }

            Spacer(Modifier.height(18.dp))

            Box(Modifier.fillMaxWidth()) {
                // white panel with a rounded top; the avatar straddles its edge
                Column(
                    Modifier.fillMaxWidth()
                        .padding(top = 64.dp)
                        .clip(RoundedCornerShape(topStart = 46.dp, topEnd = 46.dp))
                        .background(Color(0xFFFCFAF6))
                        .navigationBarsPadding()
                ) {
                    Spacer(Modifier.height(74.dp))
                    Text(userName, fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                    Spacer(Modifier.height(4.dp))
                    Text(userMobile, fontSize = 16.sp, color = Color(0xFF8A908A), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)

                    Spacer(Modifier.height(22.dp))

                    // My Information
                    ProfileSectionCard {
                        Text(
                            lang.getT("My Information", "मेरी जानकारी", "ମୋର ସୂଚନା"),
                            fontSize = 17.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A),
                            modifier = Modifier.padding(start = 16.dp, top = 13.dp, bottom = 2.dp)
                        )
                        ProfileInfoRow(Icons.Default.Person, lang.getT("Name", "नाम", "ନାମ"), userName)
                        ProfileRowDivider()
                        ProfileInfoRow(Icons.Default.Phone, lang.getT("Mobile Number", "मोबाइल नंबर", "ମୋବାଇଲ୍ ନମ୍ବର"), userMobile)
                        ProfileRowDivider()
                        ProfileInfoRow(Icons.Default.Shield, lang.getT("Role", "भूमिका", "ଭୂମିକା"), roleLabel)
                        Spacer(Modifier.height(8.dp))
                    }

                    Spacer(Modifier.height(16.dp))

                    // Settings
                    ProfileSectionCard {
                        ProfileSettingRow(Icons.Default.Lock, lang.getT("Change Password", "पासवर्ड बदलें", "ପାସୱାର୍ଡ ବଦଳାନ୍ତୁ")) { navController.navigate("change_password") }
                        ProfileRowDivider()
                        ProfileSettingRow(Icons.Default.Language, lang.getT("Language", "भाषा", "ଭାଷା"), trailing = lang.label) { showLanguagePicker = true }
                        ProfileRowDivider()
                        ProfileSettingRow(Icons.Default.SupportAgent, lang.getT("Help & Support", "सहायता और समर्थन", "ସହାୟତା")) { navController.navigate("help_support") }
                        ProfileRowDivider()
                        ProfileSettingRow(Icons.Default.Shield, lang.getT("Privacy Policy", "गोपनीयता नीति", "ଗୋପନୀୟତା ନୀତି")) { navController.navigate("privacy_policy") }
                        ProfileRowDivider()
                        ProfileSettingRow(Icons.Default.Description, lang.getT("Terms & Conditions", "नियम और शर्तें", "ନିୟମ ଏବଂ ସର୍ତ୍ତ")) { navController.navigate("terms_of_service") }
                    }

                    Spacer(Modifier.height(20.dp))

                    // Logout
                    OutlinedButton(
                        onClick = { showLogoutConfirm = true },
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp).height(56.dp),
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.5.dp, Color(0xFFE57373)),
                        colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.Logout, null, tint = Color(0xFFD32F2F), modifier = Modifier.size(22.dp))
                        Spacer(Modifier.width(10.dp))
                        Text(lang.getT("Logout", "लॉग आउट", "ଲଗଆଉଟ୍"), color = Color(0xFFD32F2F), fontWeight = FontWeight.Bold, fontSize = 17.sp)
                    }

                    Spacer(Modifier.height(28.dp))
                }

                // avatar + Edit pill, centred over the panel edge
                Box(Modifier.align(Alignment.TopCenter)) {
                    Surface(
                        modifier = Modifier.size(128.dp),
                        shape = CircleShape,
                        color = Color.White,
                        shadowElevation = 4.dp,
                        onClick = { showPhotoOptions = true }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            // The API returns a relative path ("/uploads/x.jpg"); Coil needs an absolute URL.
                            val remotePhoto = dbProfile?.photo?.takeIf { it.isNotBlank() }?.let {
                                if (it.startsWith("http")) it
                                else BuildConfig.BASE_URL.trimEnd('/') + "/" + it.trimStart('/')
                            }
                            when {
                                profileImageState.value != null -> AsyncImage(
                                    model = profileImageState.value, contentDescription = null,
                                    modifier = Modifier.fillMaxSize().padding(5.dp).clip(CircleShape),
                                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                                )
                                remotePhoto != null -> AsyncImage(
                                    model = remotePhoto, contentDescription = null,
                                    modifier = Modifier.fillMaxSize().padding(5.dp).clip(CircleShape),
                                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                                )
                                else -> Icon(Icons.Default.Person, null, tint = Color(0xFFBDBDBD), modifier = Modifier.size(64.dp))
                            }
                        }
                    }
                    Surface(
                        onClick = { showPhotoOptions = true },
                        shape = RoundedCornerShape(20.dp),
                        color = Color.White,
                        shadowElevation = 3.dp,
                        modifier = Modifier.align(Alignment.BottomEnd).offset(x = 10.dp)
                    ) {
                        Row(Modifier.padding(horizontal = 12.dp, vertical = 7.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Edit, null, tint = PrimaryGreen, modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(5.dp))
                            Text(lang.getT("Edit", "संपादित करें", "ସଂପାଦନ"), color = PrimaryGreen, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        }
                    }
                }
            }
        }
    }
}

/** White rounded card used for the Profile page sections. */
@Composable
private fun ProfileSectionCard(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFEFEBE3)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) { Column { content() } }
}

@Composable
private fun ProfileRowDivider() {
    HorizontalDivider(color = Color(0xFFF1EEE7), modifier = Modifier.padding(start = 72.dp, end = 16.dp))
}

@Composable
private fun ProfileInfoRow(icon: ImageVector, label: String, value: String) {
    Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 9.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(38.dp).clip(RoundedCornerShape(11.dp)).background(PrimaryGreen.copy(alpha = 0.10f)), contentAlignment = Alignment.Center) {
            Icon(icon, null, tint = PrimaryGreen, modifier = Modifier.size(20.dp))
        }
        Spacer(Modifier.width(12.dp))
        Column {
            Text(label, fontSize = 12.sp, color = Color(0xFF9AA09A))
            Text(value.ifBlank { "—" }, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A))
        }
    }
}

@Composable
private fun ProfileSettingRow(icon: ImageVector, label: String, trailing: String? = null, onClick: () -> Unit) {
    Row(
        Modifier.fillMaxWidth().clickable { onClick() }.padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.size(38.dp).clip(RoundedCornerShape(11.dp)).background(PrimaryGreen.copy(alpha = 0.10f)), contentAlignment = Alignment.Center) {
            Icon(icon, null, tint = PrimaryGreen, modifier = Modifier.size(20.dp))
        }
        Spacer(Modifier.width(12.dp))
        Text(label, fontSize = 15.sp, color = Color(0xFF14231A), modifier = Modifier.weight(1f))
        if (trailing != null) {
            Text(trailing, color = PrimaryGreen, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(Modifier.width(6.dp))
        }
        Icon(Icons.Default.ChevronRight, null, tint = Color(0xFFB6BCB6), modifier = Modifier.size(20.dp))
    }
}

/**
 * System back on a bottom-bar destination returns to the dashboard rather than
 * unwinding the stack. Those tabs are siblings, not steps in a journey, so backing
 * out of Claims into whatever screen happened to open it reads as going sideways.
 */
@Composable
private fun BackToDashboard(navController: NavHostController, userRole: UserRole?) {
    val home = when (userRole) {
        UserRole.FARMER -> "farmer_dashboard"
        UserRole.COORDINATOR -> "coordinator_dashboard"
        else -> "didi_dashboard"
    }
    BackHandler {
        navController.navigate(home) {
            popUpTo(navController.graph.startDestinationId)
            launchSingleTop = true
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpSupportScreen(navController: NavHostController, userRole: UserRole?, onBack: () -> Unit) {
    BackToDashboard(navController, userRole)
    val lang = LocalAppLanguage.current.value
    val context = LocalContext.current
    val cardBorder = Color(0xFFEFEBE3)
    val softGreen = Color(0xFFEDF4E9)

    Box(Modifier.fillMaxSize().background(Color(0xFFF3F4DD))) {
        // same faint farm scene used on the Profile header
        Image(
            painter = painterResource(R.drawable.profile_banner),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter),
            contentScale = androidx.compose.ui.layout.ContentScale.FillWidth
        )
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            Box(Modifier.fillMaxWidth().statusBarsPadding().padding(vertical = 8.dp)) {
                IconButton(onClick = onBack, modifier = Modifier.align(Alignment.CenterStart).padding(start = 6.dp)) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF1B1B1B))
                }
                Text(
                    lang.getT("Help & Support", "सहायता और समर्थन", "ସହାୟତା ଏବଂ ସମର୍ଥନ"),
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B1B1B)
                )
            }

            Spacer(Modifier.height(14.dp))

            Box(Modifier.fillMaxWidth()) {
                Column(
                    Modifier.fillMaxWidth()
                        .padding(top = 54.dp)
                        .clip(RoundedCornerShape(topStart = 46.dp, topEnd = 46.dp))
                        .background(Color(0xFFFCFAF6))
                        .navigationBarsPadding()
                ) {
                    Spacer(Modifier.height(64.dp))
                    Text(
                        lang.getT("How can we help you?", "हम आपकी कैसे मदद कर सकते हैं?", "ଆମେ ଆପଣଙ୍କୁ କିପରି ସାହାଯ୍ୟ କରିପାରିବୁ?"),
                        fontSize = 23.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A),
                        modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        lang.getT("We're here to assist you with\nany queries or issues.", "किसी भी प्रश्न या समस्या में\nहम आपकी सहायता के लिए हैं।", "ଯେକୌଣସି ପ୍ରଶ୍ନ କିମ୍ବା ସମସ୍ୟାରେ\nଆମେ ସାହାଯ୍ୟ ପାଇଁ ଅଛୁ।"),
                        fontSize = 15.sp, color = Color(0xFF7A8078), lineHeight = 21.sp,
                        modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(22.dp))
                    SupportSectionTitle(lang.getT("Popular Topics", "लोकप्रिय विषय", "ଲୋକପ୍ରିୟ ବିଷୟ"))
                    Spacer(Modifier.height(10.dp))

                    // Each topic expands in place to show its answer. Nothing navigates
                    // away and nothing opens WhatsApp - the answer lives on this screen.
                    val topics = supportTopics(lang)
                    var expandedTopic by rememberSaveable { mutableIntStateOf(-1) }
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, cardBorder),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Column {
                            topics.forEachIndexed { index, topic ->
                                if (index > 0) SupportRowDivider()
                                SupportTopicRow(
                                    topic = topic,
                                    expanded = expandedTopic == index,
                                    onToggle = { expandedTopic = if (expandedTopic == index) -1 else index },
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(22.dp))
                    SupportSectionTitle(lang.getT("Still need help?", "अभी भी मदद चाहिए?", "ଏବେ ବି ସାହାଯ୍ୟ ଦରକାର?"))
                    Spacer(Modifier.height(10.dp))

                    // Contact Us - soft blue, so it reads as the call to action among
                    // the green cards around it.
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE9F0FD)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Box(Modifier.size(52.dp).clip(CircleShape).background(Color.White), contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.SupportAgent, null, tint = IconBlue, modifier = Modifier.size(28.dp))
                            }
                            Spacer(Modifier.width(14.dp))
                            Column(Modifier.weight(1f)) {
                                Text(lang.getT("Contact Us", "हमसे संपर्क करें", "ଆମ ସହ ଯୋଗାଯୋଗ କରନ୍ତୁ"), color = IconBlue, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text(lang.getT("Our support team is ready to help you.", "हमारी सहायता टीम आपकी मदद के लिए तैयार है।", "ଆମର ସହାୟତା ଦଳ ଆପଣଙ୍କୁ ସାହାଯ୍ୟ କରିବାକୁ ପ୍ରସ୍ତୁତ।"), color = Color(0xFF63707E), fontSize = 13.sp, lineHeight = 18.sp)
                            }
                        }
                    }

                    Spacer(Modifier.height(14.dp))

                    // Chat / Call
                    Row(Modifier.fillMaxWidth().padding(horizontal = 18.dp), horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                        SupportContactTile(
                            modifier = Modifier.weight(1f),
                            icon = Icons.AutoMirrored.Filled.Chat,
                            label = lang.getT("Chat Us", "चैट करें", "ଚାଟ୍ କରନ୍ତୁ"),
                            bg = softGreen
                        ) { context.chatOnWhatsApp() }
                        SupportContactTile(
                            modifier = Modifier.weight(1f),
                            icon = Icons.Default.Phone,
                            label = lang.getT("Call Us", "कॉल करें", "କଲ୍ କରନ୍ତୁ"),
                            bg = softGreen
                        ) { context.callSupport() }
                    }

                    Spacer(Modifier.height(14.dp))

                    // Support timing
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, cardBorder),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Box(Modifier.size(48.dp).clip(CircleShape).background(softGreen), contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.AccessTime, null, tint = PrimaryGreen, modifier = Modifier.size(26.dp))
                            }
                            Spacer(Modifier.width(14.dp))
                            Column {
                                Text(lang.getT("Support Timing", "सहायता समय", "ସହାୟତା ସମୟ"), fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF14231A))
                                Text(lang.getT("Mon - Sat: 9:00 AM - 7:00 PM", "सोम - शनि: सुबह 9:00 - शाम 7:00", "ସୋମ - ଶନି: ସକାଳ ୯ଟା - ସନ୍ଧ୍ୟା ୭ଟା"), fontSize = 13.sp, color = Color(0xFF4A544C))
                                Text(lang.getT("(Except Sundays & Public Holidays)", "(रविवार और सार्वजनिक अवकाश को छोड़कर)", "(ରବିବାର ଓ ସରକାରୀ ଛୁଟି ବ୍ୟତୀତ)"), fontSize = 12.sp, color = Color(0xFF8A908A))
                            }
                        }
                    }

                    Spacer(Modifier.height(28.dp))
                }

                // headset badge straddling the panel edge
                ScreenHeaderBadge {
                    Icon(Icons.Default.SupportAgent, null, tint = PrimaryGreen, modifier = Modifier.size(44.dp))
                }
            }
        }
    }
}

@Composable
private fun SupportSectionTitle(text: String) {
    Text(text, fontSize = 19.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A), modifier = Modifier.padding(start = 18.dp))
}

/**
 * The circular badge that straddles the top edge of the rounded panel on
 * Help & Support, Privacy Policy and Terms & Conditions. The white outer ring
 * separates it from the farm banner behind it; the inner circle carries the
 * green outline. Call it as the last child of the Box holding the panel.
 */
@Composable
private fun BoxScope.ScreenHeaderBadge(icon: @Composable () -> Unit) {
    Box(
        Modifier.align(Alignment.TopCenter).size(104.dp).clip(CircleShape).background(Color.White).padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier.fillMaxSize().clip(CircleShape).background(Color(0xFFF3F7F0)).border(2.dp, PrimaryGreen, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            icon()
        }
    }
}

@Composable
private fun SupportRowDivider() {
    HorizontalDivider(color = Color(0xFFF1EEE7), modifier = Modifier.padding(start = 68.dp, end = 16.dp))
}

/** One Popular Topics entry: the row label plus the answer it expands to show. */
private data class SupportTopic(
    val icon: ImageVector,
    val accent: Color,
    val title: String,
    val subtitle: String,
    val body: String,
)

private fun supportTopics(lang: AppLanguage): List<SupportTopic> = listOf(
    SupportTopic(
        icon = Icons.Default.Description,
        accent = IconBlue,
        title = lang.getT("Policy related queries", "पॉलिसी संबंधी प्रश्न", "ପଲିସି ସମ୍ବନ୍ଧୀୟ ପ୍ରଶ୍ନ"),
        subtitle = lang.getT("Coverage, renewal and documents", "कवरेज, नवीनीकरण और दस्तावेज़", "କଭରେଜ, ନବୀକରଣ ଏବଂ ଦଲିଲ"),
        body = lang.getT(
            "For any questions related to your policy, coverage, benefits, exclusions, renewals or policy documents, please select the relevant category or contact our support team.",
            "अपनी पॉलिसी, कवरेज, लाभ, अपवाद, नवीनीकरण या पॉलिसी दस्तावेज़ों से जुड़े किसी भी प्रश्न के लिए संबंधित श्रेणी चुनें या हमारी सहायता टीम से संपर्क करें।",
            "ଆପଣଙ୍କ ପଲିସି, କଭରେଜ, ସୁବିଧା, ବ୍ୟତିକ୍ରମ, ନବୀକରଣ କିମ୍ବା ପଲିସି ଦଲିଲ ସମ୍ବନ୍ଧୀୟ ଯେକୌଣସି ପ୍ରଶ୍ନ ପାଇଁ ସମ୍ପୃକ୍ତ ବର୍ଗ ବାଛନ୍ତୁ କିମ୍ବା ଆମ ସହାୟତା ଦଳ ସହ ଯୋଗାଯୋଗ କରନ୍ତୁ।",
        ),
    ),
    SupportTopic(
        icon = Icons.Default.CurrencyRupee,
        accent = IconAmber,
        title = lang.getT("Premium payment", "प्रीमियम भुगतान", "ପ୍ରିମିୟମ ଦେୟ"),
        subtitle = lang.getT("Payments, receipts and dues", "भुगतान, रसीद और बकाया", "ଦେୟ, ରସିଦ ଏବଂ ବକେୟା"),
        body = lang.getT(
            "The annual premium is ₹350 per goat. You can pay by cash, UPI or wallet at the time of enrolment. A receipt is generated for every payment and you can view it in your policy details.",
            "प्रति बकरी वार्षिक प्रीमियम ₹350 है। आप नामांकन के समय नकद, यूपीआई या वॉलेट से भुगतान कर सकते हैं। हर भुगतान की रसीद बनती है, जिसे आप पॉलिसी विवरण में देख सकते हैं।",
            "ପ୍ରତି ଛେଳି ପାଇଁ ବାର୍ଷିକ ପ୍ରିମିୟମ ₹350। ନାମଲେଖା ସମୟରେ ଆପଣ ନଗଦ, UPI କିମ୍ବା ୱାଲେଟ ମାଧ୍ୟମରେ ଦେୟ ଦେଇପାରିବେ। ପ୍ରତ୍ୟେକ ଦେୟ ପାଇଁ ରସିଦ ତିଆରି ହୁଏ, ଯାହା ପଲିସି ବିବରଣୀରେ ଦେଖିପାରିବେ।",
        ),
    ),
    SupportTopic(
        icon = Icons.Default.Shield,
        accent = IconPurple,
        title = lang.getT("Claim process", "दावा प्रक्रिया", "ଦାବି ପ୍ରକ୍ରିୟା"),
        subtitle = lang.getT("How to raise and track a claim", "दावा कैसे करें और ट्रैक करें", "ଦାବି କିପରି କରିବେ ଓ ଟ୍ରାକ୍ କରିବେ"),
        body = lang.getT(
            "Report the death of an insured goat as soon as possible. Your Suraksha Didi will visit the site, upload the required carcass photos and submit the claim. You can follow the progress from the Claims section at any time.",
            "बीमित बकरी की मृत्यु की सूचना जल्द से जल्द दें। आपकी सुरक्षा दीदी मौके पर आकर आवश्यक फ़ोटो अपलोड करेंगी और दावा जमा करेंगी। आप दावा अनुभाग में कभी भी प्रगति देख सकते हैं।",
            "ବୀମାଭୁକ୍ତ ଛେଳିର ମୃତ୍ୟୁ ଯଥାଶୀଘ୍ର ଜଣାନ୍ତୁ। ଆପଣଙ୍କ ସୁରକ୍ଷା ଦିଦି ସ୍ଥାନ ପରିଦର୍ଶନ କରି ଆବଶ୍ୟକ ଫଟୋ ଅପଲୋଡ୍ କରି ଦାବି ଦାଖଲ କରିବେ। ଆପଣ ଦାବି ବିଭାଗରେ ଯେକୌଣସି ସମୟରେ ପ୍ରଗତି ଦେଖିପାରିବେ।",
        ),
    ),
    SupportTopic(
        icon = Icons.Default.Description,
        accent = IconTeal,
        title = lang.getT("Policy details", "पॉलिसी विवरण", "ପଲିସି ବିବରଣୀ"),
        subtitle = lang.getT("Policy number, validity and cover", "पॉलिसी नंबर, वैधता और कवर", "ପଲିସି ନମ୍ବର, ବୈଧତା ଏବଂ କଭର"),
        body = lang.getT(
            "Every enrolled goat gets its own policy, valid for one year from the date of enrolment with a sum insured of ₹3,000. Open My Policies to see the policy number, validity dates and the certificate.",
            "हर नामांकित बकरी की अपनी पॉलिसी होती है, जो नामांकन की तारीख से एक वर्ष तक वैध रहती है और बीमित राशि ₹3,000 है। पॉलिसी नंबर, वैधता और प्रमाणपत्र देखने के लिए मेरी पॉलिसी खोलें।",
            "ପ୍ରତ୍ୟେକ ନାମଲେଖା ହୋଇଥିବା ଛେଳିର ନିଜସ୍ୱ ପଲିସି ଥାଏ, ଯାହା ନାମଲେଖା ତାରିଖରୁ ଏକ ବର୍ଷ ପର୍ଯ୍ୟନ୍ତ ବୈଧ ଏବଂ ବୀମା ରାଶି ₹3,000। ପଲିସି ନମ୍ବର, ବୈଧତା ଏବଂ ପ୍ରମାଣପତ୍ର ଦେଖିବାକୁ ମୋ ପଲିସି ଖୋଲନ୍ତୁ।",
        ),
    ),
    SupportTopic(
        icon = Icons.Default.Person,
        accent = IconRose,
        title = lang.getT("Account & profile", "खाता और प्रोफ़ाइल", "ଖାତା ଏବଂ ପ୍ରୋଫାଇଲ୍"),
        subtitle = lang.getT("Login, password and personal details", "लॉगिन, पासवर्ड और व्यक्तिगत विवरण", "ଲଗଇନ୍, ପାସୱାର୍ଡ ଏବଂ ବ୍ୟକ୍ତିଗତ ବିବରଣୀ"),
        body = lang.getT(
            "You can update your name, village and photo from the Profile screen. To change your password use Set Password, and if you have forgotten it, raise a reset request - an admin will verify you and set a new one.",
            "आप प्रोफ़ाइल स्क्रीन से अपना नाम, गांव और फ़ोटो बदल सकते हैं। पासवर्ड बदलने के लिए सेट पासवर्ड का उपयोग करें, और भूल जाने पर रीसेट अनुरोध भेजें - एडमिन आपकी पुष्टि कर नया पासवर्ड सेट करेंगे।",
            "ପ୍ରୋଫାଇଲ୍ ସ୍କ୍ରିନରୁ ଆପଣ ନାମ, ଗ୍ରାମ ଏବଂ ଫଟୋ ପରିବର୍ତ୍ତନ କରିପାରିବେ। ପାସୱାର୍ଡ ବଦଳାଇବାକୁ ସେଟ୍ ପାସୱାର୍ଡ ବ୍ୟବହାର କରନ୍ତୁ, ଭୁଲିଗଲେ ରିସେଟ୍ ଅନୁରୋଧ ପଠାନ୍ତୁ - ଆଡମିନ୍ ଯାଞ୍ଚ କରି ନୂଆ ପାସୱାର୍ଡ ସେଟ୍ କରିବେ।",
        ),
    ),
    SupportTopic(
        icon = Icons.AutoMirrored.Filled.HelpOutline,
        accent = IconIndigo,
        title = lang.getT("Other queries", "अन्य प्रश्न", "ଅନ୍ୟ ପ୍ରଶ୍ନ"),
        subtitle = lang.getT("Can't find what you're looking for?\nWe're here to help.", "जो खोज रहे हैं वह नहीं मिला?\nहम मदद के लिए हैं।", "ଯାହା ଖୋଜୁଛନ୍ତି ମିଳୁନାହିଁ?\nଆମେ ସାହାଯ୍ୟ ପାଇଁ ଅଛୁ।"),
        body = lang.getT(
            "If your question isn't covered above, our support team is happy to help. Use the Chat Us or Call Us options below and we'll get back to you during support hours.",
            "यदि आपका प्रश्न ऊपर शामिल नहीं है, तो हमारी सहायता टीम मदद के लिए तैयार है। नीचे चैट करें या कॉल करें विकल्प का उपयोग करें, हम सहायता समय में आपसे संपर्क करेंगे।",
            "ଯଦି ଆପଣଙ୍କ ପ୍ରଶ୍ନ ଉପରେ ନାହିଁ, ଆମର ସହାୟତା ଦଳ ସାହାଯ୍ୟ ପାଇଁ ପ୍ରସ୍ତୁତ। ତଳେ ଥିବା ଚାଟ୍ କରନ୍ତୁ କିମ୍ବା କଲ୍ କରନ୍ତୁ ବିକଳ୍ପ ବ୍ୟବହାର କରନ୍ତୁ, ଆମେ ସହାୟତା ସମୟରେ ଯୋଗାଯୋଗ କରିବୁ।",
        ),
    ),
)

@Composable
private fun SupportTopicRow(topic: SupportTopic, expanded: Boolean, onToggle: () -> Unit) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier.fillMaxWidth().clickable { onToggle() }.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.size(40.dp).clip(CircleShape).background(topic.accent.copy(alpha = 0.12f)), contentAlignment = Alignment.Center) {
                Icon(topic.icon, null, tint = topic.accent, modifier = Modifier.size(21.dp))
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(topic.title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A))
                Text(topic.subtitle, fontSize = 13.sp, color = Color(0xFF7A8078), lineHeight = 18.sp)
            }
            Spacer(Modifier.width(8.dp))
            Icon(
                if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.ChevronRight,
                contentDescription = null, tint = Color(0xFFB6BCB6), modifier = Modifier.size(20.dp)
            )
        }
        if (expanded) {
            Box(
                Modifier.fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 14.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFFF2F6EF))
                    .padding(horizontal = 14.dp, vertical = 13.dp)
            ) {
                Text(topic.body, fontSize = 13.sp, color = Color(0xFF55605A), lineHeight = 20.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SupportContactTile(modifier: Modifier, icon: ImageVector, label: String, bg: Color, accent: Color = PrimaryGreen, onClick: () -> Unit) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = bg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        onClick = onClick
    ) {
        Column(
            Modifier.fillMaxWidth().padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(Modifier.size(48.dp).clip(CircleShape).background(Color.White), contentAlignment = Alignment.Center) {
                Icon(icon, null, tint = accent, modifier = Modifier.size(26.dp))
            }
            Spacer(Modifier.height(8.dp))
            Text(label, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color(0xFF14231A))
            Spacer(Modifier.height(2.dp))
            Text(SupportContact.DISPLAY, color = PrimaryGreen, fontWeight = FontWeight.Bold, fontSize = 13.sp)
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
fun GoatListScreen(navController: NavHostController, userRole: UserRole?, onBack: () -> Unit) {
    BackToDashboard(navController, userRole)
    val languageState = LocalAppLanguage.current
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableIntStateOf(0) }

    val isFarmer = userRole == UserRole.FARMER
    val themeColor = if (isFarmer) PrimaryBlue else PrimaryGreen

    // ---- real data ----
    var isLoading by remember { mutableStateOf(true) }
    var errorMsg by remember { mutableStateOf<String?>(null) }
    var goats by remember { mutableStateOf<List<Triple<String, String, String>>>(emptyList()) }
    var assignedFarmers by remember { mutableStateOf<Int?>(null) }
    var statusByTag by remember { mutableStateOf<Map<String, String>>(emptyMap()) }
    var clickIdByTag by remember { mutableStateOf<Map<String, String>>(emptyMap()) }
    var counts by remember { mutableStateOf(mapOf("all" to 0, "active" to 0, "expired" to 0, "claimed" to 0)) }
    // Raw farmer policies power the redesigned My Goats cards (breed, gender, age, etc.).
    var farmerPolicies by remember { mutableStateOf<List<PolicyOut>>(emptyList()) }

    if (isFarmer) {
        val farmerVm: FarmerHomeViewModel = hiltViewModel()
        val policiesState by farmerVm.policies.collectAsState()
        // Re-fetch on return so the counts follow a fresh death report / claim update.
        val lifecycleOwner = LocalLifecycleOwner.current
        DisposableEffect(lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_RESUME) farmerVm.refresh()
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
        }
        LaunchedEffect(policiesState) {
            when (val s = policiesState) {
                is UiState.Loading -> { isLoading = true; errorMsg = null }
                is UiState.Error -> { isLoading = false; errorMsg = s.message }
                is UiState.Success -> {
                    isLoading = false; errorMsg = null
                    val ps = s.data.policies
                    farmerPolicies = ps
                    goats = ps.map { Triple(it.earTagNumber, "${it.breed}", "") }
                    statusByTag = ps.associate { it.earTagNumber to it.status }
                    clickIdByTag = ps.associate { it.earTagNumber to it.policyNumber }
                    counts = mapOf(
                        "all" to ps.size,
                        "active" to ps.count { it.status == "active" },
                        "expired" to ps.count { it.status == "expired" },
                        "claimed" to ps.count { it.status == "claimed" },
                        "dead" to ps.count { it.status == "dead" },
                    )
                }
            }
        }
    } else {
        val dashVm: DidiDashboardViewModel = hiltViewModel()
        val dashState by dashVm.state.collectAsState()
        assignedFarmers = (dashState as? UiState.Success)?.data?.assignedFarmers

        val goatVm: GoatListViewModel = hiltViewModel()
        val goatState by goatVm.state.collectAsState()
        LaunchedEffect(searchQuery) { goatVm.setSearch(searchQuery) }
        // Re-read status from the backend on return, so a goat reported dead by the
        // farmer stops showing as active here.
        val listLifecycleOwner = LocalLifecycleOwner.current
        DisposableEffect(listLifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_RESUME) { goatVm.refresh(); dashVm.load() }
            }
            listLifecycleOwner.lifecycle.addObserver(observer)
            onDispose { listLifecycleOwner.lifecycle.removeObserver(observer) }
        }
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
                        "dead" to (s.data.counts["dead"] ?: 0),
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
                    if (isFarmer) {
                        FarmerGoatListTopBar(onBack = onBack)
                    } else {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(languageState.value.getT("Farmers List", "किसानों की सूची", "କୃଷକ ତାଲିକା"), fontWeight = FontWeight.Bold)
                            },
                            navigationIcon = {
                                IconButton(onClick = onBack) {
                                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.White,
                                titleContentColor = Color.Black,
                                navigationIconContentColor = Color.Black
                            )
                        )
                    }
                },
                containerColor = PageBackground
            ) { padding ->
                Box(Modifier.fillMaxSize()) {
                    val navInset = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
                    val contentPadding = PaddingValues(top = padding.calculateTopPadding(), bottom = 74.dp + navInset)
                    if (isFarmer) {
                        FarmerGoatListContent(
                            padding = contentPadding,
                            policies = farmerPolicies,
                            counts = counts,
                            searchQuery = searchQuery,
                            onSearchChange = { searchQuery = it },
                            selectedTab = selectedTab,
                            onTabChange = { selectedTab = it },
                            isLoading = isLoading,
                            errorMsg = errorMsg,
                            onReportDeath = { navController.navigate("farmer_report_death") },
                            onTrackClaim = { navController.navigate("claim_list") },
                            onViewDetails = { navController.navigate("goat_details/$it") },
                        )
                    } else {
                        GoatListContent(contentPadding, tabs, searchQuery, { searchQuery = it }, selectedTab, { selectedTab = it }, themeColor, isFarmer, goats, statusByTag, clickIdByTag, isLoading, errorMsg, assignedFarmers, onReportDeath = { navController.navigate("farmer_report_death") }) { id ->
                            navController.navigate("goat_details/$id")
                        }
                    }
                    Box(Modifier.align(Alignment.BottomCenter)) {
                        if (isFarmer) FarmerBottomBar(navController) else DidiBottomBar(navController)
                    }
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
                            title = {
                                val titleText = if (isFarmer) languageState.value.getT("Goat List", "बकरियों की सूची", "ଛେଳି ତାଲିକା")
                                else languageState.value.getT("Farmers List", "किसानों की सूची", "କୃଷକ ତାଲିକା")
                                Text(titleText, fontWeight = FontWeight.Bold)
                            },
                            colors = if (isFarmer) {
                                TopAppBarDefaults.topAppBarColors(
                                    containerColor = themeColor,
                                    titleContentColor = Color.White
                                )
                            } else {
                                TopAppBarDefaults.topAppBarColors(
                                    containerColor = Color.White,
                                    titleContentColor = Color.Black
                                )
                            }
                        )
                    },
                    containerColor = PageBackground
                ) { padding ->
                    if (isFarmer) {
                        FarmerGoatListContent(
                            padding = padding,
                            policies = farmerPolicies,
                            counts = counts,
                            searchQuery = searchQuery,
                            onSearchChange = { searchQuery = it },
                            selectedTab = selectedTab,
                            onTabChange = { selectedTab = it },
                            isLoading = isLoading,
                            errorMsg = errorMsg,
                            onReportDeath = { navController.navigate("farmer_report_death") },
                            onTrackClaim = { navController.navigate("claim_list") },
                            onViewDetails = { navController.navigate("goat_details/$it") },
                        )
                    } else {
                        GoatListContent(padding, tabs, searchQuery, { searchQuery = it }, selectedTab, { selectedTab = it }, themeColor, isFarmer, goats, statusByTag, clickIdByTag, isLoading, errorMsg, assignedFarmers, onReportDeath = { navController.navigate("farmer_report_death") }) { id ->
                            navController.navigate("goat_details/$id")
                        }
                    }
                }
            }
        }
    )
}

// =====================================================================================
//  Farmer "My Goats" list — premium redesign (farmer role only; Didi path is unchanged)
// =====================================================================================

/** Turns an ISO date ("2027-07-16") into "16 Jul 2027" without java.time (desugaring-free). */
private fun prettyGoatDate(iso: String): String = try {
    val parts = iso.split("T")[0].split("-")
    val months = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
    "${parts[2].toInt()} ${months[parts[1].toInt() - 1]} ${parts[0]}"
} catch (e: Exception) { iso }

/** "24" months -> "2 Years", "18" -> "1.5 Years"; 0/unknown -> "—". */
private fun ageLabelYears(lang: AppLanguage, months: Int): String {
    if (months <= 0) return "—"
    val years = months / 12.0
    val txt = if (years == years.toLong().toDouble()) years.toLong().toString() else String.format(java.util.Locale.US, "%.1f", years)
    return lang.getT("$txt Years", "$txt साल", "$txt ବର୍ଷ")
}

@Composable
private fun FarmerGoatListTopBar(onBack: () -> Unit) {
    val lang = LocalAppLanguage.current.value
    Surface(color = Color.White) {
        Box(
            modifier = Modifier.fillMaxWidth().statusBarsPadding().padding(horizontal = 6.dp, vertical = 10.dp)
        ) {
            IconButton(onClick = onBack, modifier = Modifier.align(Alignment.CenterStart)) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF14231A))
            }
            Text(
                lang.getT("My Goats", "मेरी बकरियां", "ମୋର ଛେଳି"),
                modifier = Modifier.align(Alignment.Center),
                fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A)
            )
        }
    }
}

@Composable
private fun FarmerGoatListContent(
    padding: PaddingValues,
    policies: List<PolicyOut>,
    counts: Map<String, Int>,
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    selectedTab: Int,
    onTabChange: (Int) -> Unit,
    isLoading: Boolean,
    errorMsg: String?,
    onReportDeath: () -> Unit,
    onTrackClaim: () -> Unit,
    onViewDetails: (String) -> Unit,
) {
    val lang = LocalAppLanguage.current.value
    val inr = remember { java.text.NumberFormat.getInstance(java.util.Locale("en", "IN")) }
    val statusFilter = when (selectedTab) { 1 -> "active"; 2 -> "claimed"; 3 -> "expired"; else -> null }
    // Alive goats float to the top, dead/claimed sink to the bottom; the list reorders
    // automatically because it re-derives from `policies` whenever a status changes.
    fun statusRank(status: String) = when (status) {
        "active" -> 0
        "expired" -> 1
        "dead" -> 2
        "claimed" -> 3
        else -> 4
    }
    val filtered = policies.filter { p ->
        val statusOk = when (statusFilter) {
            null -> true
            "claimed" -> p.status == "claimed" || p.status == "dead"
            else -> p.status == statusFilter
        }
        val searchOk = searchQuery.isBlank() || p.earTagNumber.contains(searchQuery, true) || p.breed.contains(searchQuery, true)
        statusOk && searchOk
    }.sortedBy { statusRank(it.status) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = padding.calculateTopPadding() + 8.dp,
            bottom = padding.calculateBottomPadding() + 16.dp,
            start = 16.dp, end = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            FarmerGoatsHeroCard(
                total = counts["all"] ?: policies.size,
                active = counts["active"] ?: 0,
                expired = counts["expired"] ?: 0,
                // A settled (claimed) death is still a dead goat, so it stays counted.
                dead = (counts["dead"] ?: 0) + (counts["claimed"] ?: 0),
            )
        }
        item { FarmerGoatsTabRow(selectedTab, onTabChange) }
        item { FarmerGoatsSearchRow(searchQuery, onSearchChange) }
        if (isLoading) item { LinearProgressIndicator(Modifier.fillMaxWidth(), color = PrimaryGreen) }
        errorMsg?.let { msg -> item { Text(msg, color = Color.Red, fontSize = 13.sp) } }
        if (!isLoading && filtered.isEmpty()) {
            item {
                Column(Modifier.fillMaxWidth().padding(top = 40.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(painterResource(R.drawable.ic_ewe_custom), null, tint = Color.LightGray, modifier = Modifier.size(44.dp))
                    Spacer(Modifier.height(10.dp))
                    Text(lang.getT("No goats here", "यहां कोई बकरी नहीं", "ଏଠାରେ କୌଣସି ଛେଳି ନାହିଁ"), color = Color.Gray, fontWeight = FontWeight.Medium)
                }
            }
        }
        items(filtered) { p ->
            FarmerGoatListCard(
                policy = p,
                money = { "₹ " + inr.format(it.toLong()) },
                onReportDeath = onReportDeath,
                onTrackClaim = onTrackClaim,
                onViewDetails = { onViewDetails(p.policyNumber) },
            )
        }
    }
}

@Composable
private fun FarmerGoatsHeroCard(total: Int, active: Int, expired: Int, dead: Int) {
    val lang = LocalAppLanguage.current.value
    Box(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(22.dp))
            .background(Brush.linearGradient(listOf(Color(0xFF2E7D32), Color(0xFF15501C))))
            .padding(vertical = 20.dp, horizontal = 20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Left: the three policy/mortality stats.
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                FarmerHeroStat(Icons.Default.VerifiedUser, lang.getT("Active Policies", "सक्रिय नीतियां", "ସକ୍ରିୟ ନୀତି"), active)
                FarmerHeroStat(Icons.Default.Schedule, lang.getT("Expired Policies", "समाप्त नीतियां", "ମିଆଦ ସରିଥିବା ନୀତି"), expired)
                FarmerHeroStat(Icons.Default.Warning, lang.getT("Dead Goats", "मृत बकरियां", "ମୃତ ଛେଳି"), dead)
            }
            Spacer(Modifier.width(16.dp))
            Box(Modifier.width(1.dp).height(120.dp).background(Color.White.copy(alpha = 0.25f)))
            Spacer(Modifier.width(16.dp))
            // Right: total-enrolled highlight.
            Column(
                modifier = Modifier.width(120.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    lang.getT("Total Insured Goats", "कुल बीमित बकरियां", "ମୋଟ ବୀମାକୃତ ଛେଳି"),
                    color = Color.White.copy(alpha = 0.92f), fontSize = 13.sp, fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center, lineHeight = 16.sp
                )
                Spacer(Modifier.height(6.dp))
                Text("$total", color = Color.White, fontSize = 46.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(2.dp))
                Text(
                    lang.getT("Total Goats", "कुल बकरियां", "ମୋଟ ଛେଳି"),
                    color = Color.White.copy(alpha = 0.75f), fontSize = 12.sp, textAlign = TextAlign.Center
                )
            }
        }
    }
}

/** One "label ........ value" row for the farmer goats hero card. */
@Composable
private fun FarmerHeroStat(icon: ImageVector, label: String, value: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = Color.White, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(8.dp))
        Text(label, color = Color.White.copy(alpha = 0.92f), fontSize = 13.sp, modifier = Modifier.weight(1f), maxLines = 1)
        Spacer(Modifier.width(8.dp))
        Text("$value", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun FarmerGoatsTabRow(selected: Int, onTabChange: (Int) -> Unit) {
    val lang = LocalAppLanguage.current.value
    val tabs = listOf(
        Triple(lang.getT("All", "सभी", "ସମସ୍ତ"), null as androidx.compose.ui.graphics.vector.ImageVector?, 0),
        Triple(lang.getT("Active", "सक्रिय", "ସକ୍ରିୟ"), Icons.Default.Shield, 1),
        Triple(lang.getT("Claims", "दावे", "ଦାବି"), Icons.Default.Info, 2),
        Triple(lang.getT("Expired", "समाप्त", "ସମାପ୍ତ"), Icons.Default.CalendarToday, 3),
    )
    Row(
        Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        tabs.forEach { (label, icon, idx) ->
            val isSel = selected == idx
            Surface(
                onClick = { onTabChange(idx) },
                color = if (isSel) PrimaryGreen else Color.White,
                shape = RoundedCornerShape(50),
                border = if (isSel) null else BorderStroke(1.dp, Color(0xFFE3E7DE)),
                shadowElevation = if (isSel) 2.dp else 0.dp
            ) {
                Row(Modifier.padding(horizontal = 18.dp, vertical = 10.dp), verticalAlignment = Alignment.CenterVertically) {
                    if (icon != null) {
                        Icon(icon, null, tint = if (isSel) Color.White else PrimaryGreen, modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(6.dp))
                    }
                    Text(label, color = if (isSel) Color.White else Color(0xFF465043), fontSize = 13.sp, fontWeight = if (isSel) FontWeight.Bold else FontWeight.Medium)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FarmerGoatsSearchRow(searchQuery: String, onSearchChange: (String) -> Unit) {
    val lang = LocalAppLanguage.current.value
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(lang.getT("Search Goat ID / Ear Tag", "बकरी आईडी / कान टैग खोजें", "ଛେଳି ID / କାନ ଟ୍ୟାଗ୍ ଖୋଜନ୍ତୁ"), fontSize = 14.sp) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray) },
        shape = RoundedCornerShape(16.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White, unfocusedContainerColor = Color.White,
            unfocusedBorderColor = Color(0xFFE3E7DE), focusedBorderColor = PrimaryGreen
        )
    )
}

@Composable
private fun FarmerGoatInfoCell(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String, modifier: Modifier = Modifier, tint: Color = Color(0xFF7C8A79)) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = tint, modifier = Modifier.size(16.dp))
        Spacer(Modifier.width(6.dp))
        Column {
            Text(label, fontSize = 11.sp, color = Color(0xFF8A908A))
            Text(value, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF2A362A), maxLines = 1)
        }
    }
}

@Composable
private fun FarmerGoatListCard(
    policy: PolicyOut,
    money: (Double) -> String,
    onReportDeath: () -> Unit,
    onTrackClaim: () -> Unit,
    onViewDetails: () -> Unit,
) {
    val lang = LocalAppLanguage.current.value
    val status = policy.status
    val isActive = status == "active"
    val isClaimed = status == "claimed"
    val isDead = status == "dead"
    val isExpired = status == "expired"
    val isGone = isClaimed || isDead
    val statusColor = when {
        isActive -> PrimaryGreen
        isClaimed -> Color(0xFFE29B0B)
        else -> Color(0xFFD32F2F)
    }
    val statusLabel = when {
        isActive -> lang.getT("Active", "सक्रिय", "ସକ୍ରିୟ")
        isClaimed -> lang.getT("Claim", "दावा", "ଦାବି")
        isDead -> lang.getT("Reported", "सूचित", "ରିପୋର୍ଟ")
        isExpired -> lang.getT("Expired", "समाप्त", "ସମାପ୍ତ")
        else -> status.replaceFirstChar { it.uppercase() }
    }
    val genderKey = policy.gender.lowercase()
    val genderIcon = when (genderKey) {
        "male" -> Icons.Default.Male
        "female" -> Icons.Default.Female
        else -> Icons.Default.Transgender
    }
    val genderLabel = when (genderKey) {
        "male" -> lang.getT("Male", "नर", "ପୁରୁଷ")
        "female" -> lang.getT("Female", "मादा", "ମାଈ")
        else -> policy.gender.ifBlank { "—" }.replaceFirstChar { it.uppercase() }
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        border = BorderStroke(1.dp, Color(0xFFEDF0EA))
    ) {
        Column {
            Row(Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(contentAlignment = Alignment.TopStart) {
                    Box(Modifier.size(64.dp).clip(CircleShape).background(statusColor.copy(alpha = 0.10f)), contentAlignment = Alignment.Center) {
                        val goatPhoto = policy.photo?.takeIf { it.isNotBlank() }?.let { absoluteMediaUrl(it) }
                        if (goatPhoto != null) {
                            AsyncImage(
                                model = goatPhoto,
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize().clip(CircleShape),
                                contentScale = androidx.compose.ui.layout.ContentScale.Crop
                            )
                        } else {
                            Icon(painterResource(R.drawable.ic_ewe_custom), null, tint = statusColor, modifier = Modifier.size(34.dp))
                        }
                    }
                    Box(Modifier.size(22.dp).clip(CircleShape).background(Color.White), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.VerifiedUser, null, tint = statusColor, modifier = Modifier.size(18.dp))
                    }
                }
                Spacer(Modifier.width(14.dp))
                Column(Modifier.weight(1f)) {
                    Text(lang.getT("GOAT ID", "बकरी आईडी", "ଛେଳି ID"), fontSize = 11.sp, color = Color(0xFF8A908A), letterSpacing = 0.6.sp)
                    Text(policy.earTagNumber, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A), maxLines = 1)
                    Spacer(Modifier.height(4.dp))
                    Surface(color = Color(0xFFEFF2EC), shape = RoundedCornerShape(8.dp)) {
                        Text(policy.breed.ifBlank { "—" }, fontSize = 12.sp, color = Color(0xFF5B6660), modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp))
                    }
                }
                Surface(color = Color.White, shape = RoundedCornerShape(50), border = BorderStroke(1.dp, statusColor)) {
                    Row(Modifier.padding(horizontal = 10.dp, vertical = 5.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(7.dp).clip(CircleShape).background(statusColor))
                        Spacer(Modifier.width(5.dp))
                        Text(statusLabel, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = statusColor)
                    }
                }
            }

            Spacer(Modifier.height(14.dp))
            Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                FarmerGoatInfoCell(Icons.Default.Label, lang.getT("Ear Tag", "कान का टैग", "କାନ ଟ୍ୟାଗ୍"), policy.earTagNumber, Modifier.weight(1f), tint = IconBlue)
                FarmerGoatInfoCell(genderIcon, lang.getT("Gender", "लिंग", "ଲିଙ୍ଗ"), genderLabel, Modifier.weight(1f), tint = IconPurple)
                FarmerGoatInfoCell(Icons.Default.Schedule, lang.getT("Age", "उम्र", "ବୟସ"), ageLabelYears(lang, policy.ageMonths), Modifier.weight(1f), tint = IconAmber)
            }
            Spacer(Modifier.height(14.dp))
            HorizontalDivider(color = Color(0xFFEDF0EA))

            Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                FarmerPolicyStat(Icons.Default.CalendarToday, lang.getT("Valid Till", "मान्यता तक", "ବୈଧତା ପର୍ଯ୍ୟନ୍ତ"), prettyGoatDate(policy.validTo), Modifier.weight(1f), tint = IconBlue)
                Box(Modifier.height(38.dp).width(1.dp).background(Color(0xFFEDF0EA)))
                Spacer(Modifier.width(12.dp))
                FarmerPolicyStat(Icons.Default.Shield, lang.getT("Sum Insured", "बीमा राशि", "ବୀମା ରାଶି"), money(policy.sumInsured), Modifier.weight(1f), tint = IconGreen)
            }
            HorizontalDivider(color = Color(0xFFEDF0EA))

            Row(Modifier.fillMaxWidth().padding(12.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedButton(
                    onClick = onViewDetails,
                    modifier = Modifier.weight(1f).height(46.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryGreen),
                    border = BorderStroke(1.5.dp, PrimaryGreen),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(lang.getT("View Details", "विवरण देखें", "ବିବରଣୀ ଦେଖନ୍ତୁ"), fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
                if (isGone) {
                    Button(
                        onClick = onTrackClaim,
                        modifier = Modifier.weight(1f).height(46.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = if (isClaimed) Color(0xFFE29B0B) else Color(0xFF15501C)),
                        shape = RoundedCornerShape(12.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(lang.getT("Track Claim", "दावा ट्रैक करें", "ଦାବି ଟ୍ରାକ୍"), fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color.White)
                    }
                } else {
                    Button(
                        onClick = onReportDeath,
                        modifier = Modifier.weight(1f).height(46.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF15501C)),
                        shape = RoundedCornerShape(12.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(lang.getT("Report Death", "मृत्यु रिपोर्ट", "ମୃତ୍ୟୁ ରିପୋର୍ଟ"), fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color.White)
                    }
                }
            }
        }
    }
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
    assignedFarmers: Int? = null,
    onReportDeath: (String) -> Unit,
    onGoatClick: (String) -> Unit
) {
    val languageState = LocalAppLanguage.current
    val context = LocalContext.current
    Column(modifier = Modifier.padding(padding)) {
        // The same hero the dashboard shows. No View all link here - this is that list.
        if (!isFarmer) {
            Spacer(Modifier.height(12.dp))
            Box(Modifier.padding(horizontal = 16.dp)) {
                HeroAssignedFarmersCard(count = assignedFarmers, showViewAll = false)
            }
        }
        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
            placeholder = {
                val placeholderText = if (isFarmer) languageState.value.getT("Search by ear tag or breed", "कान के टैग या नस्ल से खोजें", "କାନ ଟ୍ୟାଗ୍ କିମ୍ବା ପ୍ରଜାତି ଅନୁସାରେ ଖୋଜନ୍ତୁ")
                else languageState.value.getT("Search by Farmer Name", "किसान का नाम से खोजें", "କୃଷକଙ୍କ ନାମ ଅନୁସାରେ ଖୋଜନ୍ତୁ")
                Text(placeholderText)
            },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray) },
            shape = RoundedCornerShape(24.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White, unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f))
        )

        // Tabs
        if (isFarmer) {
            ScrollableTabRow(selectedTabIndex = selectedTab, containerColor = Color.Transparent, edgePadding = 16.dp, divider = {}, indicator = {}) {
                tabs.forEachIndexed { index, title ->
                    val isSelected = selectedTab == index
                    Tab(selected = isSelected, onClick = { onTabChange(index) }, modifier = Modifier.padding(horizontal = 4.dp)) {
                        Surface(
                            color = if (isSelected) themeColor else Color(0xFFF1F3EE),
                            shape = RoundedCornerShape(50),
                            shadowElevation = if (isSelected) 3.dp else 0.dp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Text(text = title, modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp), color = if (isSelected) Color.White else Color(0xFF465043), fontSize = 12.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium)
                        }
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
            if (!isFarmer) return@filter true
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
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 16.dp),
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
                    val statusColor = when {
                        isExpired || isDead -> Color(0xFFD32F2F)
                        isClaimed -> Color(0xFFE29B0B)
                        else -> SuccessGreen
                    }
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(1.dp, Color(0xFFEDEFEA))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(contentAlignment = Alignment.BottomEnd) {
                                    Surface(
                                        modifier = Modifier.size(66.dp),
                                        shape = CircleShape,
                                        color = statusColor.copy(alpha = 0.10f),
                                        border = BorderStroke(2.dp, statusColor.copy(alpha = 0.30f))
                                    ) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Icon(painterResource(R.drawable.ic_ewe_custom), null, tint = themeColor, modifier = Modifier.size(34.dp))
                                        }
                                    }
                                    Box(
                                        modifier = Modifier.size(16.dp).clip(CircleShape).background(Color.White),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Box(Modifier.size(10.dp).clip(CircleShape).background(statusColor))
                                    }
                                }
                                Spacer(modifier = Modifier.width(14.dp))
                                Column {
                                    Text(goat.first, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1A1A1A))
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
                                        modifier = Modifier.weight(1f).height(44.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                                        shape = RoundedCornerShape(12.dp),
                                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                                        contentPadding = PaddingValues(0.dp)
                                    ) {
                                        Text(languageState.value.getT("Report Death", "मृत्यु की सूचना", "ମୃତ୍ୟୁ ରିପୋର୍ଟ"), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                    }
                                }
                                OutlinedButton(
                                    onClick = { onGoatClick(clickIdByTag[goat.first] ?: goat.first) },
                                    modifier = Modifier.weight(1f).height(44.dp),
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = themeColor),
                                    border = BorderStroke(1.5.dp, themeColor),
                                    shape = RoundedCornerShape(12.dp),
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
                            Column(modifier = Modifier.padding(12.dp)) {
                                // Farmer Info Header (Clickable to Expand)
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { expandedFarmers[farmerName] = !isExpanded },
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    // Farmer avatar
                                    Surface(
                                        modifier = Modifier.size(52.dp),
                                        shape = RoundedCornerShape(10.dp),
                                        color = Color(0xFFF5F5F5)
                                    ) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Image(
                                                painter = painterResource(R.drawable.avatar_farmer),
                                                contentDescription = null,
                                                modifier = Modifier.fillMaxSize(),
                                                contentScale = ContentScale.Crop
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.width(12.dp))

                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(farmerName, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black, maxLines = 1)
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(Icons.Default.Phone, null, tint = Color.Gray, modifier = Modifier.size(13.dp))
                                            Spacer(Modifier.width(4.dp))
                                            Text("+91 98765 43210", color = Color.Gray, fontSize = 12.sp) // Mock phone
                                        }
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(Icons.Default.LocationOn, null, tint = Color.Gray, modifier = Modifier.size(13.dp))
                                            Spacer(Modifier.width(4.dp))
                                            Text(village.ifBlank { "Jamdihi Village, Block: Jamdihi, Dist: Giridih" }, color = Color.Gray, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                        }
                                    }

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.background(Color(0xFFF1F8E9), RoundedCornerShape(8.dp)).padding(horizontal = 10.dp, vertical = 6.dp)
                                    ) {
                                        Text("${goats.size}", color = SuccessGreen, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
                                        Spacer(Modifier.width(4.dp))
                                        Text(languageState.value.getT("Goats", "बकरियां", "ଛେଳି"), fontSize = 10.sp, color = Color.Gray)
                                    }

                                    Spacer(modifier = Modifier.width(8.dp))
                                    Icon(
                                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.ChevronRight,
                                        contentDescription = null,
                                        tint = if (isExpanded) PrimaryGreen else Color.Gray
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
                                        // Render the goat's real status. This used to test
                                        // only "expired", so a dead or claimed goat fell
                                        // through and was labelled "Policy Active".
                                        val goatStatus = statusByTag[goat.first]
                                        val (statusLabel, statusTint) = when (goatStatus) {
                                            "expired" -> languageState.value.getT("Policy Expired", "पॉलिसी समाप्त", "ନୀତି ସମାପ୍ତ") to Color.Red
                                            "dead" -> languageState.value.getT("Reported Dead", "मृत घोषित", "ମୃତ ରିପୋର୍ଟ") to Color(0xFFD32F2F)
                                            "claimed" -> languageState.value.getT("Claim Settled", "दावा निपटा", "ଦାବି ନିଷ୍ପତ୍ତି") to Color(0xFFE29B0B)
                                            else -> languageState.value.getT("Policy Active", "पॉलिसी सक्रिय", "ନୀତି ସକ୍ରିୟ") to SuccessGreen
                                        }
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
                                                        statusLabel,
                                                        color = statusTint,
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
    val lang = LocalAppLanguage.current.value
    val isFarmer = userRole == UserRole.FARMER

    // For SD/Coordinator `tag` carries the goat id; for Farmer it carries the policy number.
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
    val breed = detail?.breed ?: policy?.goat?.breed
    val gender = detail?.gender ?: policy?.goat?.gender
    val ageMonths = detail?.ageMonths ?: policy?.goat?.ageMonths
    val statusStr = detail?.status ?: policy?.status ?: ""
    val birthDate = detail?.dateOfBirth ?: policy?.goat?.dateOfBirth
    val photoUrls = (detail?.photos ?: policy?.photos).orEmpty().map { it.url }

    val policyNumber = detail?.policy?.policyNumber ?: policy?.policyNumber
    val validFrom = detail?.policy?.validFrom ?: policy?.validFrom
    val validTo = detail?.policy?.validTo ?: policy?.validTo
    val sumInsured = detail?.policy?.sumInsured ?: policy?.sumInsured
    // Insurer shown as "Issued By"; the app only falls back if an older server
    // doesn't send it.
    val issuedBy = detail?.policy?.issuedBy ?: policy?.issuedBy ?: "AjahFi Goat Insurance"
    // Premium paid: whatever was actually collected, else the policy's annual premium.
    // This used to read the farmer payload only, so it rendered blank for the Didi
    // and Coordinator, who load the goat-detail payload instead.
    val premium = detail?.policy?.amountPaid ?: policy?.amountPaid
        ?: detail?.policy?.annualPremium ?: policy?.annualPremium

    val inr = remember { java.text.NumberFormat.getInstance(java.util.Locale("en", "IN")) }
    fun money(v: Double?) = v?.let { "₹ " + inr.format(it.toLong()) } ?: "—"

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = PageBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { padding ->
      Box(Modifier.fillMaxSize()) {
        val navInset = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
        Column(Modifier.padding(top = padding.calculateTopPadding(), bottom = 74.dp + navInset).fillMaxSize()) {

            // ---- header ----
            Box(
                Modifier.fillMaxWidth().background(Color.White).statusBarsPadding()
                    .padding(top = 8.dp, bottom = 12.dp)
            ) {
                IconButton(onClick = onBack, modifier = Modifier.align(Alignment.CenterStart).padding(start = 6.dp)) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF14231A))
                }
                Text(
                    lang.getT("Goat Details", "बकरी का विवरण", "ଛେଳି ବିବରଣୀ"),
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A)
                )
            }

            Column(
                Modifier.fillMaxSize().verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(Modifier.height(12.dp))

                // ---- hero: id, breed, status, photo, with the policy strip overlapping ----
                Box(Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFEDF4E4)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Box(Modifier.fillMaxWidth().height(172.dp)) {
                            Image(
                                painter = painterResource(R.drawable.goat_banner),
                                contentDescription = null,
                                // Zoomed a touch so the goat reads larger, and nudged down.
                                modifier = Modifier.matchParentSize().graphicsLayer {
                                    scaleX = 1.25f
                                    scaleY = 1.25f
                                    translationY = size.height * 0.10f
                                },
                                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                                alignment = Alignment.CenterStart
                            )
                            // Green wash on the left so the dark id text stays crisp over
                            // the artwork; clears well before the goat on the right.
                            Box(
                                Modifier.matchParentSize().background(
                                    Brush.horizontalGradient(
                                        0.0f to Color(0xF0CBE4BB),
                                        0.42f to Color(0xADCBE4BB),
                                        0.75f to Color(0x00CBE4BB),
                                    )
                                )
                            )
                            // Shield sits beside the ID block, with breed and status
                            // stacked under it so every label shares one left edge.
                            Column(
                                Modifier.fillMaxWidth(0.62f).padding(start = 16.dp, top = 34.dp, bottom = 32.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                // The shield centres against the ID lines only, so it stays
                                // centred when the system font scale grows the text.
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        Icons.Default.VerifiedUser, null,
                                        tint = Color.White, modifier = Modifier.size(38.dp)
                                    )
                                    Spacer(Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            lang.getT("GOAT ID", "बकरी आईडी", "ଛେଳି ID"),
                                            fontSize = 11.sp, color = Color(0xFF4A554F),
                                            fontWeight = FontWeight.Medium, letterSpacing = 0.8.sp
                                        )
                                        Text(displayTag, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A), maxLines = 1)
                                    }
                                }
                                Spacer(Modifier.height(8.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    if (!breed.isNullOrBlank()) {
                                        Surface(color = Color.White.copy(alpha = 0.85f), shape = RoundedCornerShape(8.dp)) {
                                            Text(
                                                breed, fontSize = 11.sp, color = Color(0xFF5B6660),
                                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp), maxLines = 1
                                            )
                                        }
                                        Spacer(Modifier.width(6.dp))
                                    }
                                    GoatStatusChip(statusStr, lang)
                                }
                            }
                        }
                    }

                    // policy strip, straddling the hero's bottom edge
                    Card(
                        modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().padding(horizontal = 8.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Row(Modifier.fillMaxWidth().padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                            GoatPolicyStat(Icons.Default.CalendarToday, lang.getT("Valid Till", "मान्यता तक", "ବୈଧତା ପର୍ଯ୍ୟନ୍ତ"), validTo ?: "—", IconBlue, Modifier.weight(1f))
                            GoatStatDivider()
                            GoatPolicyStat(Icons.Default.Description, lang.getT("Policy No.", "पॉलिसी संख्या", "ପଲିସି ନମ୍ବର"), policyNumber ?: "—", IconIndigo, Modifier.weight(1f))
                        }
                    }
                }

                Spacer(Modifier.height(14.dp))

                // ---- goat information ----
                GoatSectionCard(Icons.Default.Pets, lang.getT("Goat Information", "बकरी की जानकारी", "ଛେଳି ସୂଚନା"), IconGreen) {
                    Row(Modifier.fillMaxWidth().padding(horizontal = 14.dp)) {
                        GoatInfoCell(Icons.Default.Label, lang.getT("Ear Tag", "कान का टैग", "କାନ ଟ୍ୟାଗ୍"), displayTag, IconBlue, Modifier.weight(1f))
                        GoatCellDivider()
                        GoatInfoCell(Icons.Default.Person, lang.getT("Gender", "लिंग", "ଲିଙ୍ଗ"), gender?.replaceFirstChar { it.uppercase() } ?: "—", IconPurple, Modifier.weight(1f))
                    }
                    GoatRowDivider()
                    Row(Modifier.fillMaxWidth().padding(horizontal = 14.dp)) {
                        GoatInfoCell(Icons.Default.CalendarToday, lang.getT("Age", "आयु", "ବୟସ"), ageMonths?.let { goatAgeLabel(it, lang) } ?: "—", IconAmber, Modifier.weight(1f))
                        GoatCellDivider()
                        GoatInfoCell(Icons.Default.Scale, lang.getT("Weight", "वज़न", "ଓଜନ"), (detail?.weightKg ?: policy?.weightKg)?.let { "${it.toInt()} kg" } ?: "—", IconTeal, Modifier.weight(1f))
                    }
                    GoatRowDivider()
                    Row(Modifier.fillMaxWidth().padding(horizontal = 14.dp)) {
                        GoatInfoCell(Icons.Default.Pets, lang.getT("Breed", "नस्ल", "ଜାତି"), breed ?: "—", IconRose, Modifier.weight(1f))
                        GoatCellDivider()
                        // Prefer the backend's stable birth date; only fall back to the
                        // age-based estimate if an older server doesn't send one.
                        GoatInfoCell(
                            Icons.Default.CalendarToday,
                            lang.getT("Date of Birth", "जन्म तिथि", "ଜନ୍ମ ତାରିଖ"),
                            birthDate?.let { prettyGoatDate(it) } ?: ageMonths?.let { goatBirthDateLabel(it) } ?: "—",
                            IconIndigo, Modifier.weight(1f)
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                }

                Spacer(Modifier.height(14.dp))

                // ---- policy & insurance ----
                GoatSectionCard(Icons.Default.VerifiedUser, lang.getT("Policy & Insurance Details", "पॉलिसी और बीमा विवरण", "ପଲିସି ଓ ବୀମା ବିବରଣୀ"), IconIndigo) {
                    Row(Modifier.fillMaxWidth().padding(horizontal = 14.dp)) {
                        GoatStackedStat(lang.getT("Policy Start", "पॉलिसी प्रारंभ", "ପଲିସି ଆରମ୍ଭ"), validFrom ?: "—", Modifier.weight(1f))
                        GoatCellDivider()
                        GoatStackedStat(lang.getT("Policy End", "पॉलिसी समाप्ति", "ପଲିସି ଶେଷ"), validTo ?: "—", Modifier.weight(1f))
                        GoatCellDivider()
                        GoatStackedStat(lang.getT("Premium Paid", "भुगतान प्रीमियम", "ଦିଆଯାଇଥିବା ପ୍ରିମିୟମ"), money(premium), Modifier.weight(1f))
                    }
                    GoatRowDivider()
                    Row(Modifier.fillMaxWidth().padding(horizontal = 14.dp)) {
                        GoatInfoCell(Icons.Default.VerifiedUser, lang.getT("Insured By", "बीमाकृत", "ବୀମାଭୁକ୍ତ"), (detail?.farmer?.name ?: policy?.farmer?.name) ?: "—", IconGreen, Modifier.weight(1f))
                        GoatCellDivider()
                        // Insurer name comes from the backend setting, not hardcoded here.
                        GoatInfoCell(Icons.Default.Business, lang.getT("Issued By", "जारीकर्ता", "ଜାରିକର୍ତ୍ତା"), issuedBy, IconBlue, Modifier.weight(1f))
                    }
                    Spacer(Modifier.height(10.dp))
                }

                // ---- goat images ----
                if (photoUrls.isNotEmpty()) {
                    Spacer(Modifier.height(14.dp))
                    var showAllPhotos by rememberSaveable { mutableStateOf(false) }
                    GoatSectionCard(
                        Icons.Default.Image,
                        lang.getT("Goat Images", "बकरी की तस्वीरें", "ଛେଳି ଛବି"),
                        IconAmber,
                        action = if (photoUrls.size <= 2) null else {
                            {
                                Text(
                                    if (showAllPhotos) lang.getT("Show Less", "कम दिखाएं", "କମ୍ ଦେଖାନ୍ତୁ")
                                    else lang.getT("View All", "सभी देखें", "ସବୁ ଦେଖନ୍ତୁ"),
                                    color = PrimaryGreen, fontWeight = FontWeight.Bold, fontSize = 13.sp,
                                    modifier = Modifier.clickable { showAllPhotos = !showAllPhotos }
                                )
                            }
                        }
                    ) {
                        // Two across at rest; View All opens the full 2x2.
                        val shown = if (showAllPhotos) photoUrls.take(4) else photoUrls.take(2)
                        shown.chunked(2).forEach { rowUrls ->
                            Row(
                                Modifier.fillMaxWidth().padding(horizontal = 14.dp, vertical = 4.dp),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                rowUrls.forEach { url ->
                                    GoatPhotoTile(url, Modifier.weight(1f))
                                }
                                // keep a half-width photo half-width on an odd row
                                repeat(2 - rowUrls.size) { Spacer(Modifier.weight(1f)) }
                            }
                        }
                        Spacer(Modifier.height(10.dp))
                    }
                }

                Spacer(Modifier.height(18.dp))

                // ---- actions ----
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedButton(
                        onClick = { navController.navigate("policy_details/$tag") },
                        modifier = Modifier.weight(1f).height(52.dp),
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.dp, PrimaryGreen.copy(alpha = 0.4f)),
                        colors = ButtonDefaults.outlinedButtonColors(containerColor = Color(0xFFEDF4E9))
                    ) {
                        Icon(Icons.Default.Description, null, tint = PrimaryGreen, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text(lang.getT("View Policy Details", "पॉलिसी विवरण देखें", "ପଲିସି ବିବରଣୀ"), color = PrimaryGreen, fontWeight = FontWeight.Bold, fontSize = 13.sp, maxLines = 1)
                    }
                    if (isFarmer) {
                        Button(
                            onClick = { navController.navigate("farmer_report_death") },
                            modifier = Modifier.weight(1f).height(52.dp),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
                        ) {
                            Icon(Icons.Default.NotificationsActive, null, tint = Color.White, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                            Text(lang.getT("Report Death", "मृत्यु रिपोर्ट", "ମୃତ୍ୟୁ ରିପୋର୍ଟ"), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp, maxLines = 1)
                        }
                    }
                    }

                Spacer(Modifier.height(20.dp))
            }
        }
        Box(Modifier.align(Alignment.BottomCenter)) {
            if (isFarmer) FarmerBottomBar(navController) else DidiBottomBar(navController)
        }
      }
    }
}

/** Policy Details, reached from the Goat Details screen. `tag` is the goat id for a
 *  Didi/Coordinator and the policy number for a Farmer, matching Goat Details. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PolicyDetailsScreen(navController: NavHostController, tag: String, userRole: UserRole?, onBack: () -> Unit) {
    val lang = LocalAppLanguage.current.value
    val context = LocalContext.current
    val isFarmer = userRole == UserRole.FARMER

    // One PolicyDetailViewModel drives the certificate download for every role (the
    // /farmer/policy_certificate endpoint accepts any authenticated user); for a farmer
    // it also loads the policy shown on this screen.
    val certVm: PolicyDetailViewModel = hiltViewModel()
    val certState by certVm.certificate.collectAsState()

    var detail by remember { mutableStateOf<GoatDetail?>(null) }
    var farmerPolicy by remember { mutableStateOf<PolicyDetail?>(null) }
    if (isFarmer) {
        val st by certVm.state.collectAsState()
        LaunchedEffect(tag) { certVm.load(tag) }
        farmerPolicy = (st as? UiState.Success)?.data
    } else {
        val vm: GoatDetailViewModel = hiltViewModel()
        val st by vm.state.collectAsState()
        LaunchedEffect(tag) { tag.toIntOrNull()?.let { vm.load(it) } }
        detail = (st as? UiState.Success)?.data
    }

    val p = detail?.policy
    val policyNumber = p?.policyNumber ?: farmerPolicy?.policyNumber
    val validFrom = p?.validFrom ?: farmerPolicy?.validFrom
    val validTo = p?.validTo ?: farmerPolicy?.validTo
    val sumInsured = p?.sumInsured ?: farmerPolicy?.sumInsured
    val premium = p?.annualPremium ?: farmerPolicy?.annualPremium
    val paymentMode = p?.paymentMode ?: farmerPolicy?.paymentMode
    val receiptNumber = p?.receiptNumber ?: farmerPolicy?.receiptNumber
    val paidAt = p?.paidAt ?: farmerPolicy?.paidAt
    val status = p?.status ?: farmerPolicy?.status ?: detail?.status ?: ""

    val goatTag = detail?.earTagNumber ?: farmerPolicy?.goat?.earTagNumber
    val goatBreed = detail?.breed ?: farmerPolicy?.goat?.breed
    val goatPhoto = detail?.photos.orEmpty().firstOrNull()?.url

    val inr = remember { java.text.NumberFormat.getInstance(java.util.Locale("en", "IN")) }
    fun money(v: Double?) = v?.let { "₹ " + inr.format(it.toLong()) } ?: "—"

    // Open the downloaded certificate PDF, or report a failure.
    LaunchedEffect(certState) {
        when (val s = certState) {
            is SubmitState.Success -> {
                s.message?.let { path ->
                    val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", File(path))
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        setDataAndType(uri, "application/pdf")
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }
                    try {
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        Toast.makeText(context, lang.getT("No PDF viewer installed", "कोई पीडीएफ व्यूअर नहीं मिला", "କୌଣସି PDF ଭ୍ୟୁଅର୍ ନାହିଁ"), Toast.LENGTH_LONG).show()
                    }
                }
                certVm.resetCertificate()
            }
            is SubmitState.Error -> {
                Toast.makeText(context, s.message ?: lang.getT("Download failed", "डाउनलोड विफल", "ଡାଉନଲୋଡ୍ ବିଫଳ"), Toast.LENGTH_LONG).show()
                certVm.resetCertificate()
            }
            else -> {}
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = PageBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { padding ->
      Box(Modifier.fillMaxSize()) {
        val navInset = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
        Column(Modifier.padding(top = padding.calculateTopPadding(), bottom = 74.dp + navInset).fillMaxSize()) {

            // ---- header ----
            Box(
                Modifier.fillMaxWidth().background(Color.White).statusBarsPadding()
                    .padding(top = 8.dp, bottom = 12.dp)
            ) {
                IconButton(onClick = onBack, modifier = Modifier.align(Alignment.CenterStart).padding(start = 6.dp)) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF14231A))
                }
                Text(
                    lang.getT("Policy Details", "पॉलिसी विवरण", "ପଲିସି ବିବରଣୀ"),
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A)
                )
                // Download this goat's policy certificate PDF and open it.
                IconButton(
                    onClick = {
                        val earTag = goatTag
                        if (earTag.isNullOrBlank()) {
                            Toast.makeText(context, lang.getT("Loading policy, please wait…", "पॉलिसी लोड हो रही है, प्रतीक्षा करें…", "ନୀତି ଲୋଡ୍ ହେଉଛି, ଅପେକ୍ଷା କରନ୍ତୁ…"), Toast.LENGTH_SHORT).show()
                        } else {
                            certVm.downloadCertificate(earTag, context.cacheDir)
                        }
                    },
                    enabled = certState !is SubmitState.Submitting,
                    modifier = Modifier.align(Alignment.CenterEnd).padding(end = 6.dp)
                ) {
                    if (certState is SubmitState.Submitting) {
                        CircularProgressIndicator(modifier = Modifier.size(22.dp), strokeWidth = 2.dp, color = PrimaryGreen)
                    } else {
                        Icon(Icons.Default.FileDownload, contentDescription = "Download policy certificate", tint = Color(0xFF14231A))
                    }
                }
            }

            Column(
                Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(horizontal = 16.dp)
            ) {
                Spacer(Modifier.height(12.dp))

                // ---- hero ----
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEDF4E4)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                  Box(Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(R.drawable.policy_banner),
                        contentDescription = null,
                        modifier = Modifier.matchParentSize(),
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                        alignment = Alignment.CenterEnd
                    )
                    Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.fillMaxWidth(0.62f)) {
                            Text(
                                lang.getT("Policy Number", "पॉलिसी संख्या", "ପଲିସି ନମ୍ବର"),
                                fontSize = 12.sp, color = Color(0xFF5B6660)
                            )
                            Spacer(Modifier.height(2.dp))
                            Text(
                                policyNumber ?: "—",
                                fontSize = 18.sp, fontWeight = FontWeight.Bold, color = PrimaryGreen,
                                lineHeight = 22.sp, maxLines = 2
                            )
                            Spacer(Modifier.height(8.dp))
                            PolicyStatusChip(status, lang)
                            Spacer(Modifier.height(8.dp))
                            Text(
                                policyStatusLine(status, lang),
                                fontSize = 12.sp, color = Color(0xFF5B6660), lineHeight = 17.sp
                            )
                        }
                    }
                  }
                }

                // ---- policy information ----
                Spacer(Modifier.height(18.dp))
                PolicySectionTitle(Icons.Default.Description, lang.getT("Policy Information", "पॉलिसी जानकारी", "ପଲିସି ସୂଚନା"))
                Spacer(Modifier.height(8.dp))
                PolicyCard {
                    PolicyRow(lang.getT("Policy Number", "पॉलिसी संख्या", "ପଲିସି ନମ୍ବର"), policyNumber ?: "—")
                    PolicyRowDivider()
                    PolicyRow(lang.getT("Policy Start Date", "प्रारंभ तिथि", "ଆରମ୍ଭ ତାରିଖ"), formatPolicyDate(validFrom))
                    PolicyRowDivider()
                    PolicyRow(lang.getT("Policy End Date", "समाप्ति तिथि", "ଶେଷ ତାରିଖ"), formatPolicyDate(validTo))
                    PolicyRowDivider()
                    PolicyRow(lang.getT("Policy Tenure", "पॉलिसी अवधि", "ପଲିସି ଅବଧି"), policyTenure(validFrom, validTo, lang))
                    PolicyRowDivider()
                    PolicyRow(lang.getT("Policy Type", "पॉलिसी प्रकार", "ପଲିସି ପ୍ରକାର"), lang.getT("Goat Insurance Policy", "बकरी बीमा पॉलिसी", "ଛେଳି ବୀମା ପଲିସି"))
                    PolicyRowDivider()
                    PolicyRow(
                        lang.getT("Policy Status", "पॉलिसी स्थिति", "ପଲିସି ସ୍ଥିତି"),
                        policyStatusLabel(status, lang),
                        valueColor = PrimaryGreen
                    )
                }

                // ---- insured goat ----
                if (goatTag != null) {
                    Spacer(Modifier.height(18.dp))
                    PolicySectionTitle(Icons.Default.Pets, lang.getT("Insured Goat", "बीमित बकरी", "ବୀମାଭୁକ୍ତ ଛେଳି"))
                    Spacer(Modifier.height(8.dp))
                    PolicyCard {
                        Row(Modifier.fillMaxWidth().padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                Modifier.size(56.dp).clip(CircleShape).background(Color(0xFFF1F3EE)),
                                contentAlignment = Alignment.Center
                            ) {
                                var photoFailed by remember(goatPhoto) { mutableStateOf(false) }
                                if (goatPhoto != null && !photoFailed) {
                                    AsyncImage(
                                        model = absoluteMediaUrl(goatPhoto),
                                        contentDescription = null,
                                        modifier = Modifier.fillMaxSize().clip(CircleShape),
                                        contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                                        onError = { photoFailed = true }
                                    )
                                } else {
                                    Icon(
                                        painterResource(R.drawable.ic_ewe_custom), null,
                                        tint = PrimaryGreen.copy(alpha = 0.35f), modifier = Modifier.size(30.dp)
                                    )
                                }
                            }
                            Spacer(Modifier.width(12.dp))
                            Column(Modifier.weight(1f)) {
                                Text(lang.getT("GOAT ID", "बकरी आईडी", "ଛେଳି ID"), fontSize = 10.sp, color = Color(0xFF8A908A))
                                Text(goatTag, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A), maxLines = 1)
                                if (!goatBreed.isNullOrBlank()) {
                                    Spacer(Modifier.height(3.dp))
                                    Surface(color = Color(0xFFEDF4E9), shape = RoundedCornerShape(8.dp)) {
                                        Text(
                                            goatBreed, fontSize = 11.sp, color = PrimaryGreen, fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp), maxLines = 1
                                        )
                                    }
                                }
                            }
                            TextButton(onClick = { onBack() }) {
                                Text(
                                    lang.getT("View Goat", "बकरी देखें", "ଛେଳି ଦେଖନ୍ତୁ"),
                                    color = PrimaryGreen, fontWeight = FontWeight.Bold, fontSize = 12.sp, maxLines = 1
                                )
                                Icon(Icons.Default.ChevronRight, null, tint = PrimaryGreen, modifier = Modifier.size(16.dp))
                            }
                        }
                    }
                }

                // ---- financial information ----
                Spacer(Modifier.height(18.dp))
                PolicySectionTitle(Icons.Default.CurrencyRupee, lang.getT("Financial Information", "वित्तीय जानकारी", "ଆର୍ଥିକ ସୂଚନା"))
                Spacer(Modifier.height(8.dp))
                PolicyCard {
                    PolicyRow(lang.getT("Sum Insured", "बीमा राशि", "ବୀମା ରାଶି"), money(sumInsured))
                    PolicyRowDivider()
                    PolicyRow(lang.getT("Premium Amount", "प्रीमियम राशि", "ପ୍ରିମିୟମ ରାଶି"), money(premium))
                    PolicyRowDivider()
                    PolicyRow(lang.getT("Payment Date", "भुगतान तिथि", "ଦେୟ ତାରିଖ"), AppTime.dateTime(paidAt, fallback = "—"))
                    PolicyRowDivider()
                    PolicyRow(lang.getT("Payment Mode", "भुगतान माध्यम", "ଦେୟ ମାଧ୍ୟମ"), paymentMode?.uppercase() ?: "—")
                    PolicyRowDivider()
                    PolicyRow(lang.getT("Receipt No.", "रसीद संख्या", "ରସିଦ ନମ୍ବର"), receiptNumber ?: "—")
                }

                // ---- coverage ----
                Spacer(Modifier.height(18.dp))
                PolicySectionTitle(Icons.Default.VerifiedUser, lang.getT("Coverage Details", "कवरेज विवरण", "କଭରେଜ ବିବରଣୀ"))
                Spacer(Modifier.height(8.dp))
                PolicyCard {
                    PolicyCoverageRow(lang.getT("Death Due to Accident", "दुर्घटना से मृत्यु", "ଦୁର୍ଘଟଣାଜନିତ ମୃତ୍ୟୁ"), lang)
                    PolicyRowDivider()
                    PolicyCoverageRow(lang.getT("Death Due to Disease", "बीमारी से मृत्यु", "ରୋଗଜନିତ ମୃତ୍ୟୁ"), lang)
                    PolicyRowDivider()
                    PolicyCoverageRow(lang.getT("Natural Calamities", "प्राकृतिक आपदा", "ପ୍ରାକୃତିକ ବିପର୍ଯ୍ୟୟ"), lang)
                }

                // ---- note ----
                Spacer(Modifier.height(18.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEDF4E9)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(Modifier.fillMaxWidth().padding(14.dp), verticalAlignment = Alignment.Top) {
                        Icon(Icons.Default.Info, null, tint = PrimaryGreen, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(10.dp))
                        Column {
                            Text(lang.getT("Note", "नोट", "ଦ୍ରଷ୍ଟବ୍ୟ"), fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color(0xFF14231A))
                            Spacer(Modifier.height(3.dp))
                            Text(
                                lang.getT(
                                    "Please read the policy terms and conditions carefully. Report a death as soon as possible so the claim can be verified.",
                                    "कृपया पॉलिसी के नियम और शर्तें ध्यान से पढ़ें। दावे की जाँच के लिए मृत्यु की सूचना जल्द से जल्द दें।",
                                    "ଦୟାକରି ପଲିସିର ନିୟମ ଓ ସର୍ତ୍ତାବଳୀ ଧ୍ୟାନର ସହ ପଢନ୍ତୁ। ଦାବି ଯାଞ୍ଚ ପାଇଁ ମୃତ୍ୟୁ ଯଥାଶୀଘ୍ର ଜଣାନ୍ତୁ।"
                                ),
                                fontSize = 12.sp, color = Color(0xFF5B6660), lineHeight = 17.sp
                            )
                        }
                    }
                }

                Spacer(Modifier.height(20.dp))
            }
        }
        Box(Modifier.align(Alignment.BottomCenter)) {
            if (isFarmer) FarmerBottomBar(navController) else DidiBottomBar(navController)
        }
      }
    }
}

/** "16 Jul 2025" from the API's yyyy-MM-dd, left as-is if it is any other shape. */
private fun formatPolicyDate(iso: String?): String {
    if (iso.isNullOrBlank()) return "—"
    val parts = iso.take(10).split("-")
    if (parts.size != 3) return iso
    val months = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
    val m = parts[1].toIntOrNull() ?: return iso
    return "${parts[2]} ${months.getOrElse(m - 1) { parts[1] }} ${parts[0]}"
}

/** Whole years between the two dates, so the tenure is not another stored field. */
private fun policyTenure(from: String?, to: String?, lang: AppLanguage): String {
    val y1 = from?.take(4)?.toIntOrNull()
    val y2 = to?.take(4)?.toIntOrNull()
    if (y1 == null || y2 == null) return "—"
    val years = (y2 - y1).coerceAtLeast(1)
    return "$years " + if (years == 1) lang.getT("Year", "वर्ष", "ବର୍ଷ") else lang.getT("Years", "वर्ष", "ବର୍ଷ")
}

private fun policyStatusLabel(status: String, lang: AppLanguage): String = when (status) {
    "active" -> lang.getT("Active", "सक्रिय", "ସକ୍ରିୟ")
    "expired" -> lang.getT("Expired", "समाप्त", "ସମାପ୍ତ")
    "claimed" -> lang.getT("Claimed", "दावा किया", "ଦାବି ହୋଇଛି")
    else -> status.takeIf { it.isNotBlank() } ?: "—"
}

private fun policyStatusLine(status: String, lang: AppLanguage): String = when (status) {
    "active" -> lang.getT("This policy is valid and active.", "यह पॉलिसी वैध और सक्रिय है।", "ଏହି ପଲିସି ବୈଧ ଏବଂ ସକ୍ରିୟ ଅଛି।")
    "expired" -> lang.getT("This policy has expired.", "इस पॉलिसी की अवधि समाप्त हो गई है।", "ଏହି ପଲିସିର ଅବଧି ଶେଷ ହୋଇଯାଇଛି।")
    "claimed" -> lang.getT("A claim has been made on this policy.", "इस पॉलिसी पर दावा किया गया है।", "ଏହି ପଲିସିରେ ଦାବି କରାଯାଇଛି।")
    else -> ""
}

@Composable
private fun PolicyStatusChip(status: String, lang: AppLanguage) {
    val colour = when (status) {
        "active" -> PrimaryGreen
        "expired" -> IconRose
        "claimed" -> IconIndigo
        else -> Color(0xFF7A8078)
    }
    Surface(color = Color.White, shape = RoundedCornerShape(10.dp)) {
        Row(Modifier.padding(horizontal = 10.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(6.dp).clip(CircleShape).background(colour))
            Spacer(Modifier.width(6.dp))
            Text(policyStatusLabel(status, lang), color = colour, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun PolicySectionTitle(icon: ImageVector, title: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = PrimaryGreen, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(8.dp))
        Text(title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = PrimaryGreen)
    }
}

@Composable
private fun PolicyCard(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFEFEBE3)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) { Column { content() } }
}

@Composable
private fun PolicyRow(label: String, value: String, valueColor: Color = Color(0xFF14231A)) {
    Row(
        Modifier.fillMaxWidth().padding(horizontal = 14.dp, vertical = 11.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, fontSize = 13.sp, color = Color(0xFF7A8078), modifier = Modifier.weight(1f))
        Text(value, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = valueColor, maxLines = 1)
    }
}

@Composable
private fun PolicyCoverageRow(label: String, lang: AppLanguage) {
    Row(
        Modifier.fillMaxWidth().padding(horizontal = 14.dp, vertical = 11.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.CheckCircle, null, tint = PrimaryGreen, modifier = Modifier.size(17.dp))
        Spacer(Modifier.width(10.dp))
        Text(label, fontSize = 13.sp, color = Color(0xFF14231A), modifier = Modifier.weight(1f))
        Text(lang.getT("Covered", "कवर", "କଭର"), fontSize = 12.sp, color = Color(0xFF7A8078))
    }
}

@Composable
private fun PolicyRowDivider() {
    HorizontalDivider(color = Color(0xFFF1EEE7), modifier = Modifier.padding(horizontal = 14.dp))
}

/** Approximate birth date. The record stores age in months, not a date. */
private fun goatBirthDateLabel(months: Int): String {
    val cal = java.util.Calendar.getInstance()
    cal.add(java.util.Calendar.MONTH, -months)
    return java.text.SimpleDateFormat("dd MMM yyyy", java.util.Locale.ENGLISH).format(cal.time)
}

/** "2 years 3 months" from a month count, in the active language. */
private fun goatAgeLabel(months: Int, lang: AppLanguage): String {
    val y = months / 12
    val m = months % 12
    val yearWord = lang.getT("yr", "वर्ष", "ବର୍ଷ")
    val monthWord = lang.getT("mo", "माह", "ମାସ")
    return when {
        y > 0 && m > 0 -> "$y $yearWord $m $monthWord"
        y > 0 -> "$y $yearWord"
        else -> "$m $monthWord"
    }
}

@Composable
private fun GoatStatusChip(status: String, lang: AppLanguage) {
    val (label, colour) = when (status) {
        "active" -> lang.getT("Active", "सक्रिय", "ସକ୍ରିୟ") to PrimaryGreen
        "expired" -> lang.getT("Expired", "समाप्त", "ସମାପ୍ତ") to IconRose
        "claimed" -> lang.getT("Claimed", "दावा किया", "ଦାବି ହୋଇଛି") to IconIndigo
        "dead" -> lang.getT("Dead", "मृत", "ମୃତ") to IconRose
        else -> (status.takeIf { it.isNotBlank() } ?: "—") to Color(0xFF7A8078)
    }
    Surface(color = Color.White, shape = RoundedCornerShape(10.dp)) {
        Row(
            Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.size(6.dp).clip(CircleShape).background(colour))
            Spacer(Modifier.width(5.dp))
            Text(label, color = colour, fontSize = 11.sp, fontWeight = FontWeight.Bold, maxLines = 1)
        }
    }
}

/** One gallery photo. Falls back to the goat mark so a tile never renders blank. */
@Composable
private fun GoatPhotoTile(url: String, modifier: Modifier) {
    var failed by remember(url) { mutableStateOf(false) }
    Box(
        modifier.height(110.dp).clip(RoundedCornerShape(12.dp)).background(Color(0xFFF1F3EE)),
        contentAlignment = Alignment.Center
    ) {
        if (failed) {
            Icon(
                painterResource(R.drawable.ic_ewe_custom), null,
                tint = PrimaryGreen.copy(alpha = 0.3f), modifier = Modifier.size(40.dp)
            )
        } else {
            AsyncImage(
                model = absoluteMediaUrl(url),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                onError = { failed = true }
            )
        }
    }
}

@Composable
private fun GoatPolicyStat(icon: ImageVector, label: String, value: String, accent: Color, modifier: Modifier) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = accent, modifier = Modifier.size(14.dp))
            Spacer(Modifier.width(5.dp))
            Text(label, fontSize = 10.sp, color = Color(0xFF8A908A), maxLines = 1)
        }
        Spacer(Modifier.height(2.dp))
        Text(
            value, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = accent,
            maxLines = 1, overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun GoatStatDivider() {
    Box(Modifier.height(28.dp).width(1.dp).background(Color(0xFFEFEBE3)))
}

@Composable
private fun GoatCellDivider() {
    Box(
        Modifier.padding(horizontal = 12.dp).height(38.dp).width(1.dp)
            .background(Color(0xFFF1EEE7))
    )
}

@Composable
private fun GoatRowDivider() {
    HorizontalDivider(color = Color(0xFFF1EEE7), modifier = Modifier.padding(horizontal = 14.dp))
}

/** White card with a tinted icon, a title, and stacked rows underneath. */
@Composable
private fun GoatSectionCard(
    icon: ImageVector,
    title: String,
    accent: Color,
    action: (@Composable () -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFEFEBE3)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column {
            Row(
                Modifier.fillMaxWidth().padding(start = 14.dp, end = 14.dp, top = 14.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    Modifier.size(30.dp).clip(RoundedCornerShape(9.dp)).background(accent.copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, null, tint = accent, modifier = Modifier.size(17.dp))
                }
                Spacer(Modifier.width(10.dp))
                Text(title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A), modifier = Modifier.weight(1f))
                action?.invoke()
            }
            HorizontalDivider(color = Color(0xFFF1EEE7))
            Spacer(Modifier.height(10.dp))
            content()
        }
    }
}

@Composable
private fun GoatInfoCell(icon: ImageVector, label: String, value: String, accent: Color, modifier: Modifier) {
    Row(modifier.padding(vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = accent, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(10.dp))
        Column(Modifier.weight(1f)) {
            Text(label, fontSize = 11.sp, color = Color(0xFF8A908A), maxLines = 1)
            Text(value, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A), maxLines = 2)
        }
    }
}

@Composable
private fun GoatStackedStat(label: String, value: String, modifier: Modifier) {
    Column(modifier.padding(vertical = 6.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, fontSize = 10.sp, color = Color(0xFF8A908A), maxLines = 1)
        Spacer(Modifier.height(2.dp))
        Text(value, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = PrimaryGreen, maxLines = 1)
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
        // Completed rows show the date it was given and by whom; pending rows show the due date.
        val isDone = it.status == "done"
        val shownDate = if (isDone) (it.vaccinationDate ?: "") else (it.nextVaccinationDate ?: "")
        mapOf(
            "name" to name, "tag" to (it.earTag ?: "—"),
            "date" to shownDate,
            "vaccinatedBy" to (it.vaccinatedBy ?: ""),
            "farmer" to (it.farmer ?: languageState.value.getT("Unknown farmer", "अज्ञात किसान", "ଅଜ୍ଞାତ କୃଷକ")),
            "village" to (it.village ?: ""),
            "status" to it.status, "goatId" to it.goatId.toString(),
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
                    CenterAlignedTopAppBar(
                        title = { Text(languageState.value.getT("Vaccinations", "टीकाकरण", "ଟୀକାକରଣ"), fontWeight = FontWeight.Bold) },
                        navigationIcon = {
                            IconButton(onClick = onBack) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                            }
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
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
                    VaccineListContent(PaddingValues(top = padding.calculateTopPadding(), bottom = 74.dp + navInset), tabs, searchQuery, { searchQuery = it }, selectedTab, { selectedTab = it }, vaccList, isLoading, errorMsg, onRecord)
                    Box(Modifier.align(Alignment.BottomCenter)) { DidiBottomBar(navController) }
                }
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
                        CenterAlignedTopAppBar(
                            title = { Text(languageState.value.getT("Vaccinations", "टीकाकरण", "ଟୀକାକରଣ"), fontWeight = FontWeight.Bold) },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = Color.White,
                                titleContentColor = Color(0xFF14231A)
                            )
                        )
                    },
                    containerColor = PageBackground
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
        Image(
            painter = painterResource(R.drawable.vaccine),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(140.dp)
                .padding(horizontal = 16.dp).padding(top = 12.dp)
                .clip(RoundedCornerShape(18.dp)),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop,
        )

        // Search Bar — soft, borderless, rounded (reference style).
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            placeholder = { Text(languageState.value.getT("Search by farmer or ear tag", "किसान या कान के टैग से खोजें", "କୃଷକ କିମ୍ବା କାନ ଟ୍ୟାଗ୍ ଅନୁସାରେ ଖୋଜନ୍ତୁ"), color = Color(0xFF9AA69B)) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color(0xFF9AA69B)) },
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF1F4EF), unfocusedContainerColor = Color(0xFFF1F4EF),
                focusedBorderColor = Color.Transparent, unfocusedBorderColor = Color.Transparent
            )
        )

        // Tabs — premium pills.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            tabs.forEachIndexed { index, title ->
                val isSelected = selectedTab == index
                Surface(
                    onClick = { onTabChange(index) },
                    modifier = Modifier.weight(1f),
                    color = if (isSelected) PrimaryGreen else Color.White,
                    shape = RoundedCornerShape(20.dp),
                    border = if (isSelected) null else BorderStroke(1.dp, Color(0xFFE1E6DE)),
                    shadowElevation = if (isSelected) 2.dp else 0.dp
                ) {
                    Text(
                        text = title,
                        modifier = Modifier.padding(vertical = 10.dp),
                        color = if (isSelected) Color.White else Color(0xFF5B6660),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        if (isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth().padding(top = 8.dp), color = PrimaryGreen)
        }
        errorMsg?.let {
            Text(it, color = Color.Red, fontSize = 13.sp, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
        }

        val filteredVaccines = vaccinations.filter {
            (it["name"] ?: "").contains(searchQuery, ignoreCase = true) ||
                (it["tag"] ?: "").contains(searchQuery, ignoreCase = true) ||
                (it["farmer"] ?: "").contains(searchQuery, ignoreCase = true)
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
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                        border = BorderStroke(1.dp, Color(0xFFEDF0EA))
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
                                    color = PrimaryGreen.copy(alpha = 0.12f),
                                    border = BorderStroke(1.5.dp, PrimaryGreen.copy(alpha = 0.4f))
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(Icons.Default.Person, null, tint = PrimaryGreen, modifier = Modifier.size(28.dp))
                                    }
                                }
                                Spacer(modifier = Modifier.width(16.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(farmerName, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = PrimaryGreen)
                                    if (village.isNotBlank()) Text(village, fontSize = 13.sp, color = Color.Gray)
                                }
                                Icon(
                                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                    contentDescription = null,
                                    tint = PrimaryGreen
                                )
                            }

                            if (isExpanded) {
                                Spacer(modifier = Modifier.height(14.dp))
                                HorizontalDivider(color = Color(0xFFEDF0EA))
                                Spacer(modifier = Modifier.height(12.dp))

                                // One card per goat — a goat with several vaccinations
                                // must not repeat. Tapping opens its vaccination details.
                                val goatGroups = vaccines.groupBy { it["goatId"] ?: "" }.values.toList()
                                goatGroups.forEachIndexed { index, goatVaccines ->
                                    val first = goatVaccines.first()
                                    val goatId = first["goatId"] ?: ""
                                    val dueCount = goatVaccines.count { it["status"] != "done" }
                                    val doneCount = goatVaccines.count { it["status"] == "done" }
                                    // Soonest due date if anything is pending, else the latest given date.
                                    val dueDate = goatVaccines.filter { it["status"] != "done" }
                                        .mapNotNull { it["date"]?.takeIf { d -> d.isNotBlank() } }.minOrNull()
                                    val givenDate = goatVaccines.filter { it["status"] == "done" }
                                        .mapNotNull { it["date"]?.takeIf { d -> d.isNotBlank() } }.maxOrNull()

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        // Timeline dot + connector (reference style).
                                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(16.dp)) {
                                            Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(PrimaryGreen))
                                            if (index < goatGroups.size - 1) {
                                                Box(modifier = Modifier.width(2.dp).height(52.dp).background(Color(0xFFD8E0D4)))
                                            }
                                        }
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Surface(
                                            onClick = { onRecord(goatId) },
                                            modifier = Modifier.weight(1f),
                                            color = Color.Transparent,
                                            shape = RoundedCornerShape(12.dp)
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(vertical = 8.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Surface(modifier = Modifier.size(44.dp), shape = CircleShape, color = PrimaryGreen.copy(alpha = 0.10f)) {
                                                    Box(contentAlignment = Alignment.Center) { Icon(painterResource(R.drawable.ic_ewe_custom), null, tint = PrimaryGreen, modifier = Modifier.size(22.dp)) }
                                                }
                                                Spacer(modifier = Modifier.width(12.dp))
                                                Column(modifier = Modifier.weight(1f)) {
                                                    Text(first["tag"] ?: "", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color(0xFF14231A))
                                                    Spacer(modifier = Modifier.height(3.dp))
                                                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                                                        if (dueCount > 0) {
                                                            Surface(color = AccentOrange.copy(alpha = 0.14f), shape = RoundedCornerShape(8.dp)) {
                                                                Text(
                                                                    "$dueCount " + languageState.value.getT("Due", "देय", "ଦେୟ"),
                                                                    fontWeight = FontWeight.Bold, fontSize = 11.sp, color = AccentOrange,
                                                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                                                )
                                                            }
                                                        }
                                                        if (doneCount > 0) {
                                                            Surface(color = SuccessGreen.copy(alpha = 0.12f), shape = RoundedCornerShape(8.dp)) {
                                                                Text(
                                                                    "$doneCount " + languageState.value.getT("Done", "पूर्ण", "ସମ୍ପୂର୍ଣ୍ଣ"),
                                                                    fontWeight = FontWeight.Bold, fontSize = 11.sp, color = SuccessGreen,
                                                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                                                )
                                                            }
                                                        }
                                                    }
                                                    val dateLine = when {
                                                        dueCount > 0 && dueDate != null -> languageState.value.getT("Due ", "देय ", "ଦେୟ ") + dueDate
                                                        givenDate != null -> languageState.value.getT("Given ", "दिया गया ", "ଦିଆଯାଇଛି ") + givenDate
                                                        else -> ""
                                                    }
                                                    if (dateLine.isNotBlank()) {
                                                        Text(dateLine, fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(top = 3.dp))
                                                    }
                                                }
                                                OutlinedButton(
                                                    onClick = { onRecord(goatId) },
                                                    modifier = Modifier.height(34.dp),
                                                    shape = RoundedCornerShape(10.dp),
                                                    border = BorderStroke(1.dp, PrimaryGreen),
                                                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp)
                                                ) {
                                                    Text(languageState.value.getT("Record", "रिकॉर्ड", "ରେକର୍ଡ"), color = PrimaryGreen, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                Spacer(modifier = Modifier.height(10.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(painterResource(R.drawable.ic_ewe_custom), null, tint = PrimaryGreen, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    // Count distinct goats, not vaccination rows.
                                    val goatCount = vaccines.map { it["goatId"] }.distinct().size
                                    Text(
                                        text = "$goatCount " + languageState.value.getT(
                                            if (goatCount == 1) "Goat" else "Goats",
                                            if (goatCount == 1) "बकरी" else "बकरियां",
                                            "ଛେଳି"
                                        ),
                                        fontSize = 13.sp,
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordVaccinationScreen(tag: String, onBack: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val lang = languageState.value
    val context = LocalContext.current

    // `tag` is the goat id; load the goat so the header shows its real particulars.
    val goatId = tag.toIntOrNull()
    val goatVm: GoatDetailViewModel = hiltViewModel()
    LaunchedEffect(goatId) { goatId?.let { goatVm.load(it) } }
    val goat = (goatVm.state.collectAsState().value as? UiState.Success)?.data

    // Each vaccine the Didi can tick, with its backend code, label, subtitle and icon.
    data class VaccineOption(val code: String, val title: String, val subtitle: String, val icon: ImageVector, val tint: Color)
    val options = listOf(
        VaccineOption("ppr", lang.getT("PPR Vaccine", "पीपीआर टीका", "PPR ଟୀକା"),
            lang.getT("Peste des Petits Ruminants Vaccine", "पेस्ट डेस पेटिट्स रुमिनेंट्स टीका", "ପେଷ୍ଟେ ଡେସ୍ ପେଟିଟ୍ସ ରୁମିନାଣ୍ଟସ୍ ଟୀକା"),
            Icons.Default.Vaccines, IconGreen),
        VaccineOption("et_tt", lang.getT("ET Plus + TT Vaccine", "ईटी प्लस + टीटी टीका", "ET Plus + TT ଟୀକା"),
            lang.getT("Enterotoxaemia + Tetanus Toxoid Vaccine", "एंटरोटॉक्सिमिया + टेटनस टॉक्साइड टीका", "ଏଣ୍ଟେରୋଟକ୍ସେମିଆ + ଟିଟାନସ୍ ଟକ୍ସଏଡ୍ ଟୀକା"),
            Icons.Default.HealthAndSafety, IconBlue),
        VaccineOption("fmd", lang.getT("FMD Vaccine", "एफएमडी टीका", "FMD ଟୀକା"),
            lang.getT("Foot and Mouth Disease Vaccine", "फुट एंड माउथ रोग टीका", "ପାଦ ଓ ମୁଖ ରୋଗ ଟୀକା"),
            Icons.Default.Shield, IconAmber),
        VaccineOption("goat_pox", lang.getT("Goat Pox Vaccine", "बकरी पॉक्स टीका", "ଛେଳି ପକ୍ସ ଟୀକା"),
            lang.getT("Goat Pox Vaccine", "बकरी पॉक्स टीका", "ଛେଳି ପକ୍ସ ଟୀକା"),
            Icons.Default.Coronavirus, IconRose),
    )
    // Vaccines already completed for this goat: they show checked and locked, and are
    // never re-submitted. Derived from the goat's stored vaccination records.
    val lockedCodes = goat?.vaccinations?.filter { it.status == "done" }?.map { it.type }?.toSet() ?: emptySet()
    // Ticked vaccines. Completed ones are seeded in (so they render checked) once the
    // goat loads; the user can only add pending ones on top.
    val selected = remember { mutableStateListOf<String>() }
    LaunchedEffect(lockedCodes) {
        lockedCodes.forEach { if (it !in selected) selected.add(it) }
    }
    var batchNumber by remember { mutableStateOf("") }
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

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> if (uri != null) capturedPhotoUri = uri }

    var showPhotoSource by remember { mutableStateOf(false) }

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

    if (showPhotoSource) {
        AlertDialog(
            onDismissRequest = { showPhotoSource = false },
            containerColor = Color.White,
            shape = RoundedCornerShape(20.dp),
            title = { Text(lang.getT("Add Photo", "फोटो जोड़ें", "ଫଟୋ ଯୋଡ଼ନ୍ତୁ"), fontWeight = FontWeight.Bold, color = Color.Black) },
            text = {
                Column {
                    Surface(color = Color.Transparent, onClick = { showPhotoSource = false; launchCamera() }) {
                        Row(Modifier.fillMaxWidth().padding(vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CameraAlt, null, tint = PrimaryGreen, modifier = Modifier.size(26.dp))
                            Spacer(Modifier.width(16.dp))
                            Text(lang.getT("Take Photo", "फोटो लें", "ଫଟୋ ନିଅନ୍ତୁ"), fontSize = 16.sp, color = Color.Black)
                        }
                    }
                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.4f))
                    Surface(color = Color.Transparent, onClick = { showPhotoSource = false; galleryLauncher.launch("image/*") }) {
                        Row(Modifier.fillMaxWidth().padding(vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.PhotoLibrary, null, tint = PrimaryGreen, modifier = Modifier.size(26.dp))
                            Spacer(Modifier.width(16.dp))
                            Text(lang.getT("Choose from Gallery", "गैलरी से चुनें", "ଗ୍ୟାଲେରୀରୁ ବାଛନ୍ତୁ"), fontSize = 16.sp, color = Color.Black)
                        }
                    }
                }
            },
            confirmButton = {},
            dismissButton = {
                TextButton(onClick = { showPhotoSource = false }) {
                    Text(lang.getT("Cancel", "रद्द करें", "ବାତିଲ୍"), color = Color.Gray)
                }
            }
        )
    }

    fun submit() {
        if (goatId == null) {
            Toast.makeText(context, "Invalid goat reference", Toast.LENGTH_SHORT).show(); return
        }
        // Only the newly ticked (pending) vaccines are saved; completed ones are locked.
        val toRecord = selected.filter { it !in lockedCodes }
        if (toRecord.isEmpty()) {
            Toast.makeText(context, lang.getT("Select a pending vaccine to record", "रिकॉर्ड करने के लिए एक लंबित टीका चुनें", "ରେକର୍ଡ କରିବାକୁ ଏକ ବାକି ଥିବା ଟୀକା ବାଛନ୍ତୁ"), Toast.LENGTH_SHORT).show(); return
        }
        if (vaccinationDateIso.isBlank()) {
            Toast.makeText(context, lang.getT("Please select the vaccination date", "कृपया टीकाकरण की तारीख चुनें", "ଦୟାକରି ଟୀକାକରଣ ତାରିଖ ବାଛନ୍ତୁ"), Toast.LENGTH_SHORT).show(); return
        }
        vaccVm.recordMany(
            toRecord.map { code ->
                RecordVaccinationRequest(
                    goatId = goatId, vaccineType = code,
                    batchNumber = batchNumber.ifBlank { null }, vaccinationDate = vaccinationDateIso,
                )
            }
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(lang.getT("Vaccination Records", "टीकाकरण रिकॉर्ड", "ଟୀକାକରଣ ରେକର୍ଡ"), fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF14231A)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF14231A))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            Surface(color = Color.White, shadowElevation = 12.dp) {
                Box(Modifier.navigationBarsPadding().padding(horizontal = 20.dp, vertical = 14.dp)) {
                    Button(
                        onClick = { submit() },
                        enabled = !isSaving,
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF15501C))
                    ) {
                        Text(
                            if (isSaving) lang.getT("Saving…", "सहेज रहा है…", "ସଂରକ୍ଷଣ…")
                            else lang.getT("Submit Records", "रिकॉर्ड जमा करें", "ରେକର୍ଡ ଦାଖଲ କରନ୍ତୁ"),
                            fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White
                        )
                    }
                }
            }
        },
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // ---- goat summary ----
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                border = BorderStroke(1.dp, Color(0xFFEDF0EA))
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    val photo = goat?.photos?.firstOrNull()?.url
                    Box(
                        modifier = Modifier.size(64.dp).clip(CircleShape).background(Color(0xFFEDF4E4)),
                        contentAlignment = Alignment.Center
                    ) {
                        if (photo != null) {
                            AsyncImage(model = absoluteMediaUrl(photo), contentDescription = null, modifier = Modifier.fillMaxSize(), contentScale = androidx.compose.ui.layout.ContentScale.Crop)
                        } else {
                            Icon(painterResource(R.drawable.ic_ewe_custom), null, tint = PrimaryGreen, modifier = Modifier.size(32.dp))
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            lang.getT("GOAT ID: ", "बकरी आईडी: ", "ଛେଳି ID: ") + (goat?.earTagNumber ?: tag),
                            fontWeight = FontWeight.Bold, fontSize = 17.sp, color = PrimaryGreen, maxLines = 1
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            val gender = goat?.gender?.replaceFirstChar { it.uppercase() }
                            VaccineGoatStat(if (goat?.gender == "male") Icons.Default.Male else Icons.Default.Female, gender ?: "—", tint = IconPurple)
                            Spacer(Modifier.width(14.dp))
                            VaccineGoatStat(Icons.Default.Schedule, goat?.ageMonths?.let { goatAgeLabel(it, lang) } ?: "—", tint = IconBlue)
                            Spacer(Modifier.width(14.dp))
                            VaccineGoatStat(Icons.Default.MonitorWeight, goat?.weightKg?.let { "${it.toInt()} kg" } ?: "—", tint = IconTeal)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            // ---- vaccine picker ----
            Text(lang.getT("Select Vaccination", "टीका चुनें", "ଟୀକା ବାଛନ୍ତୁ"), fontSize = 17.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A))
            Spacer(modifier = Modifier.height(2.dp))
            Text(lang.getT("Choose the vaccination given to this goat", "इस बकरी को दिया गया टीका चुनें", "ଏହି ଛେଳିକୁ ଦିଆଯାଇଥିବା ଟୀକା ବାଛନ୍ତୁ"), fontSize = 13.sp, color = Color(0xFF8A908A))
            Spacer(modifier = Modifier.height(14.dp))

            options.forEach { opt ->
                val isLocked = opt.code in lockedCodes            // already completed
                val isChecked = opt.code in selected
                fun toggle() { if (!isLocked) { if (isChecked) selected.remove(opt.code) else selected.add(opt.code) } }
                val cardModifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
                    .let { if (isLocked) it else it.clickable { toggle() } }
                Card(
                    modifier = cardModifier,
                    colors = CardDefaults.cardColors(containerColor = if (isLocked) Color(0xFFF3F7EF) else Color.White),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    border = BorderStroke(if (isChecked) 1.5.dp else 1.dp, if (isChecked) PrimaryGreen else Color(0xFFEDF0EA))
                ) {
                    Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier.size(52.dp).clip(RoundedCornerShape(14.dp)).background(opt.tint.copy(alpha = 0.12f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(opt.icon, null, tint = opt.tint, modifier = Modifier.size(26.dp))
                        }
                        Spacer(modifier = Modifier.width(14.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(opt.title, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color(0xFF14231A))
                            // This goat's stored record for this vaccine, if any.
                            val record = goat?.vaccinations?.firstOrNull { it.type == opt.code }
                            if (isLocked) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.CheckCircle, null, tint = PrimaryGreen, modifier = Modifier.size(13.dp))
                                    Spacer(Modifier.width(4.dp))
                                    Text(lang.getT("Completed", "पूर्ण", "ସମ୍ପୂର୍ଣ୍ଣ"), fontSize = 12.sp, fontWeight = FontWeight.Bold, color = PrimaryGreen)
                                }
                                record?.date?.takeIf { it.isNotBlank() }?.let {
                                    Text(lang.getT("Given ", "दिया गया ", "ଦିଆଯାଇଛି ") + it, fontSize = 11.sp, color = Color(0xFF8A908A))
                                }
                                record?.next?.takeIf { it.isNotBlank() }?.let {
                                    Text(lang.getT("Next due ", "अगली देय ", "ପରବର୍ତ୍ତୀ ଦେୟ ") + it, fontSize = 11.sp, color = Color(0xFF8A908A))
                                }
                            } else {
                                Text(opt.subtitle, fontSize = 12.sp, color = Color(0xFF8A908A), lineHeight = 15.sp)
                                record?.next?.takeIf { it.isNotBlank() }?.let {
                                    Text(lang.getT("Due ", "देय ", "ଦେୟ ") + it, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = AccentOrange)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = if (isLocked) null else ({ toggle() }),
                            enabled = !isLocked,
                            colors = CheckboxDefaults.colors(
                                checkedColor = PrimaryGreen, uncheckedColor = Color(0xFFC7D2C2),
                                disabledCheckedColor = PrimaryGreen
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            EnrollmentTextField(
                label = lang.getT("Batch Number", "बैच संख्या", "ବ୍ୟାଚ୍ ନମ୍ବର"),
                value = batchNumber,
                onValueChange = { batchNumber = it },
                placeholder = lang.getT("Enter batch number", "बैच संख्या दर्ज करें", "ବ୍ୟାଚ୍ ନମ୍ବର ଲେଖନ୍ତୁ"),
            )

            Spacer(modifier = Modifier.height(16.dp))

            EnrollmentTextField(
                label = lang.getT("Vaccination Date", "टीकाकरण की तारीख", "ଟୀକାକରଣ ତାରିଖ"),
                value = vaccinationDate,
                onValueChange = { vaccinationDate = it },
                placeholder = lang.getT("Select vaccination date", "टीकाकरण की तारीख चुनें", "ଟୀକାକରଣ ତାରିଖ ବାଛନ୍ତୁ"),
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

            Spacer(modifier = Modifier.height(20.dp))

            Text(lang.getT("Vaccination Level Photo", "टीकाकरण स्तर फोटो", "ଟୀକାକରଣ ସ୍ତର ଫଟୋ"), fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A))
            Spacer(modifier = Modifier.height(2.dp))
            Text(lang.getT("Upload a clear photo of vaccination level", "टीकाकरण स्तर की स्पष्ट फोटो अपलोड करें", "ଟୀକାକରଣ ସ୍ତରର ଏକ ସ୍ପଷ୍ଟ ଫଟୋ ଅପଲୋଡ୍ କରନ୍ତୁ"), fontSize = 12.sp, color = Color(0xFF8A908A))
            Spacer(modifier = Modifier.height(10.dp))

            Surface(
                modifier = Modifier.fillMaxWidth().height(150.dp),
                shape = RoundedCornerShape(14.dp),
                color = Color(0xFFF7F9F5),
                onClick = { showPhotoSource = true }
            ) {
                Box(
                    modifier = Modifier.fillMaxSize().dashedBorder(1.dp, Color(0xFFC7D2C2), 14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (capturedPhotoUri != null) {
                        AsyncImage(model = capturedPhotoUri, contentDescription = null, modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(14.dp)), contentScale = androidx.compose.ui.layout.ContentScale.Crop)
                    } else {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Default.CloudUpload, null, tint = PrimaryGreen, modifier = Modifier.size(40.dp))
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(lang.getT("Tap to upload photo", "फोटो अपलोड करने के लिए टैप करें", "ଫଟୋ ଅପଲୋଡ୍ କରିବାକୁ ଟ୍ୟାପ୍ କରନ୍ତୁ"), color = PrimaryGreen, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(lang.getT("JPG, PNG up to 5 MB", "जेपीजी, पीएनजी 5 एमबी तक", "JPG, PNG 5 MB ପର୍ଯ୍ୟନ୍ତ"), color = Color(0xFF8A908A), fontSize = 11.sp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

/** Rounded dashed outline, matching the enrollment photo tiles. */
fun Modifier.dashedBorder(width: Dp, color: Color, cornerRadius: Dp): Modifier = drawBehind {
    drawRoundRect(
        color = color,
        style = Stroke(
            width = width.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(16f, 12f), 0f)
        ),
        cornerRadius = CornerRadius(cornerRadius.toPx())
    )
}

@Composable
private fun VaccineGoatStat(icon: ImageVector, value: String, tint: Color = PrimaryGreen) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = tint, modifier = Modifier.size(16.dp))
        Spacer(Modifier.width(4.dp))
        Text(value, fontSize = 13.sp, color = Color(0xFF3D473E), maxLines = 1)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClaimListScreen(navController: NavHostController, userRole: UserRole?, onBack: () -> Unit) {
    BackToDashboard(navController, userRole)
    val languageState = LocalAppLanguage.current
    val lang = languageState.value
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableIntStateOf(0) }

    val themeColor = when (userRole) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }

    // --- DATA LOADING ---
    var isLoading by remember { mutableStateOf(false) }
    var claims by remember { mutableStateOf<List<Map<String, String>>>(emptyList()) }
    var counts by remember { mutableStateOf(mapOf("all" to 0, "pending" to 0, "approved" to 0, "rejected" to 0)) }

    if (userRole == UserRole.COORDINATOR) {
        val vm: CoordinatorClaimsViewModel = hiltViewModel()
        val st by vm.state.collectAsState()
        LaunchedEffect(st) {
            (st as? UiState.Success)?.data?.claims?.let { list ->
                claims = list.map {
                    mapOf(
                        "id" to it.claimNumber, "farmer" to (it.farmer ?: "—"),
                        "tag" to it.earTagNumber, "status" to it.status,
                        "date" to (it.dateOfDeath?.take(10) ?: ""),
                        "amount" to ((it.claimAmount ?: it.sumInsured).toInt().toString())
                    )
                }
                counts = mapOf(
                    "all" to list.size,
                    "pending" to list.count { it.status == "pending" || it.status == "hold" },
                    "approved" to list.count { it.status == "approved" || it.status == "claimed" },
                    "rejected" to list.count { it.status == "rejected" }
                )
            }
        }
    } else if (userRole == UserRole.SURAKSHA_DIDI) {
        val vm: SdClaimsViewModel = hiltViewModel()
        val st by vm.state.collectAsState()
        LaunchedEffect(st) {
            (st as? UiState.Success)?.data?.claims?.let { list ->
                claims = list.map {
                    mapOf(
                        "id" to (it.claimNumber ?: "MORT-${it.mortalityId}"),
                        "farmer" to (it.farmer ?: "—"), "tag" to it.earTagNumber,
                        "status" to (it.claimStatus ?: "pending"),
                        "date" to (it.dateOfDeath?.take(10) ?: "")
                    )
                }
                counts = mapOf(
                    "all" to list.size,
                    "pending" to list.count { it.claimStatus == "pending" || it.claimStatus == "hold" },
                    "approved" to list.count { it.claimStatus == "approved" || it.claimStatus == "claimed" },
                    "rejected" to list.count { it.claimStatus == "rejected" }
                )
            }
        }
    } else {
        val vm: FarmerClaimsViewModel = hiltViewModel()
        val st by vm.state.collectAsState()
        LaunchedEffect(st) {
            (st as? UiState.Success)?.data?.claims?.let { list ->
                claims = list.map {
                    mapOf(
                        "id" to (it.claimNumber ?: "MORT-${it.mortalityId}"),
                        "farmer" to (it.earTagNumber), "tag" to it.earTagNumber,
                        "status" to (it.claimStatus ?: "pending"),
                        "date" to (it.dateOfDeath?.take(10) ?: "")
                    )
                }
                counts = mapOf(
                    "all" to list.size,
                    "pending" to list.count { it.claimStatus == "pending" || it.claimStatus == "hold" },
                    "approved" to list.count { it.claimStatus == "approved" || it.claimStatus == "claimed" },
                    "rejected" to list.count { it.claimStatus == "rejected" }
                )
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(lang.getT("My Claims", "मेरे दावे", "ମୋର ଦାବି"), fontWeight = FontWeight.Bold, fontSize = 20.sp)
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
        Column(
            modifier = Modifier
                .padding(top = padding.calculateTopPadding(), bottom = 74.dp + navInset)
                .fillMaxSize()
        ) {
            // Summary Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(180.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = themeColor)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(R.drawable.claim_banner),
                        contentDescription = null,
                        // Shifted down so the goat's face sits clear of the top edge.
                        modifier = Modifier.matchParentSize().graphicsLayer {
                            scaleX = 1.25f
                            scaleY = 1.25f
                            translationY = size.height * 0.04f
                        },
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.CenterEnd
                    )
                    // Green fade along the bottom so the status row reads over the grass.
                    Box(
                        Modifier.align(Alignment.BottomCenter).fillMaxWidth().height(100.dp)
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        Color.Transparent,
                                        Color(0x8812401F),
                                        Color(0xD912401F),
                                        Color(0xFF12401F),
                                    )
                                )
                            )
                    )

                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(lang.getT("Total Claims", "कुल दावे", "ମୋଟ ଦାବି"), color = Color(0xFF0C3218), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Text("${counts["all"]}", color = Color(0xFF0C3218), fontSize = 48.sp, fontWeight = FontWeight.ExtraBold)
                        
                        Spacer(modifier = Modifier.weight(1f))
                        
                        // Status Breakdown
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            StatusMiniStat(lang.getT("Pending", "लंबित", "ବାକି"), "${counts["pending"]}", Icons.Default.AccessTime, Color(0xFFFFB300))
                            Box(modifier = Modifier.width(1.dp).height(30.dp).background(Color.White.copy(alpha = 0.2f)).align(Alignment.CenterVertically))
                            StatusMiniStat(lang.getT("Approved", "स्वीकृत", "ଅନୁମୋଦିତ"), "${counts["approved"]}", Icons.Default.CheckCircle, Color(0xFF81C784))
                            Box(modifier = Modifier.width(1.dp).height(30.dp).background(Color.White.copy(alpha = 0.2f)).align(Alignment.CenterVertically))
                            StatusMiniStat(lang.getT("Rejected", "अस्वीकृत", "ପ୍ରତ୍ୟାଖ୍ୟାନ"), "${counts["rejected"]}", Icons.Default.Cancel, Color(0xFFE57373))
                        }
                    }
                }
            }

            // Filter Tabs
            val tabs = listOf(
                lang.getT("All", "सभी", "ସମସ୍ତ"),
                lang.getT("Pending", "लंबित", "ବାକି"),
                lang.getT("Approved", "स्वीकृत", "ଅନୁମୋଦିତ"),
                lang.getT("Rejected", "अस्वीकृत", "ପ୍ରତ୍ୟାଖ୍ୟାନ")
            )
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                tabs.forEachIndexed { index, title ->
                    val isSelected = selectedTab == index
                    Surface(
                        onClick = { selectedTab = index },
                        shape = RoundedCornerShape(12.dp),
                        color = if (isSelected) themeColor else Color.White,
                        border = BorderStroke(1.dp, if (isSelected) themeColor else Color.LightGray.copy(alpha = 0.4f)),
                        modifier = Modifier.weight(1f).height(42.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(title, color = if (isSelected) Color.White else Color.Gray, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Search Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text(lang.getT("Search Claim ID / Goat ID / Farmer Name", "दावा आईडी / बकरी आईडी / किसान का नाम खोजें", "ଦାବି ID / ଛେଳି ID / କୃଷକଙ୍କ ନାମ ଖୋଜନ୍ତୁ"), fontSize = 12.sp) },
                    leadingIcon = { Icon(Icons.Default.Search, null, tint = Color.Gray) },
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        unfocusedBorderColor = Color.LightGray.copy(alpha = 0.4f)
                    )
                )
            }

            val filtered = claims.filter {
                (it["id"] ?: "").contains(searchQuery, true) || 
                (it["farmer"] ?: "").contains(searchQuery, true) || 
                (it["tag"] ?: "").contains(searchQuery, true)
            }.filter {
                when (selectedTab) {
                    1 -> (it["status"] ?: "").lowercase() in listOf("pending", "hold")
                    2 -> (it["status"] ?: "").lowercase() in listOf("approved", "claimed")
                    3 -> (it["status"] ?: "").lowercase() == "rejected"
                    else -> true
                }
            }

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (filtered.isEmpty()) {
                    item {
                        Text(lang.getT("No claims found", "कोई दावा नहीं मिला", "କୌଣସି ଦାବି ମିଳିଲା ନାହିଁ"), color = Color.Gray, modifier = Modifier.padding(16.dp))
                    }
                }
                items(filtered) { claim ->
                    DidiClaimCard(claim, themeColor) {
                        navController.navigate("claim_review/${claim["id"]}")
                    }
                }
                
                item {
                    // Banner at the end. Farmer gets the supplied artwork (text baked in);
                    // the Didi/Coordinator keep the composed card.
                    if (userRole == UserRole.FARMER) {
                        Image(
                            painter = painterResource(R.drawable.claim_lowerbanner),
                            contentDescription = lang.getT("Secure Claims. Stronger Tomorrow.", "सुरक्षित दावे। मजबूत कल।", "ସୁରକ୍ଷିତ ଦାବି | ମଜବୁତ କାଲି |"),
                            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                                .aspectRatio(5358f / 768f)
                                .clip(RoundedCornerShape(16.dp)),
                            contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                        )
                    } else {
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9))
                        ) {
                            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                                Surface(color = themeColor, shape = RoundedCornerShape(8.dp), modifier = Modifier.size(40.dp)) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(Icons.Default.Shield, null, tint = Color.White, modifier = Modifier.size(24.dp))
                                    }
                                }
                                Spacer(modifier = Modifier.width(16.dp))
                                Column {
                                    Text(lang.getT("Secure Claims. Stronger Tomorrow.", "सुरक्षित दावे। मजबूत कल।", "ସୁରକ୍ଷିତ ଦାବି | ମଜବୁତ କାଲି |"), fontWeight = FontWeight.Bold, fontSize = 14.sp, color = themeColor)
                                    Text(lang.getT("We're with you every step of the way.", "हम हर कदम पर आपके साथ हैं।", "ଆମେ ପ୍ରତି ପଦକ୍ଷେପରେ ଆପଣଙ୍କ ସହିତ ଅଛୁ |"), fontSize = 12.sp, color = Color.Gray)
                                }
                            }
                        }
                    }
                }
            }
        }
        Box(Modifier.align(Alignment.BottomCenter)) {
            if (userRole == UserRole.FARMER) FarmerBottomBar(navController) else DidiBottomBar(navController)
        }
      }
    }
}

@Composable
fun StatusMiniStat(label: String, value: String, icon: ImageVector, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = color, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
        Text(label, color = Color.White.copy(alpha = 0.7f), fontSize = 11.sp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DidiClaimCard(claim: Map<String, String>, themeColor: Color, onClick: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val lang = languageState.value
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.2f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            // Leading Doc Icon
            Surface(
                color = Color(0xFFF2F4EE),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.size(56.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Description, null, tint = Color(0xFFD9A441), modifier = Modifier.size(28.dp))
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        claim["id"] ?: "",
                        fontWeight = FontWeight.ExtraBold, fontSize = 15.sp, color = Color.Black,
                        maxLines = 1, overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f, fill = false)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    val status = (claim["status"] ?: "").lowercase()
                    val (statusText, statusBg, statusColor) = when(status) {
                        "approved", "claimed" -> Triple(lang.getT("Approved", "स्वीकृत", "ଅନୁମୋଦିତ"), Color(0xFFE8F5E9), Color(0xFF2E7D32))
                        "rejected" -> Triple(lang.getT("Rejected", "अस्वीकृत", "ପ୍ରତ୍ୟାଖ୍ୟାନ"), Color(0xFFFFEBEE), Color(0xFFC62828))
                        "hold" -> Triple(lang.getT("On Hold", "होल्ड पर", "ହୋଲ୍ଡ"), Color(0xFFE3F2FD), Color(0xFF1565C0))
                        else -> Triple(lang.getT("Pending", "लंबित", "ବାକି"), Color(0xFFFCEEDA), Color(0xFFC68A2E))
                    }
                    
                    Surface(color = statusBg, shape = RoundedCornerShape(50)) {
                        Text(
                            text = statusText,
                            color = statusColor,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            softWrap = false
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(6.dp))
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(lang.getT("Goat ID: ", "बकरी आईडी: ", "ଛେଳି ID: "), fontSize = 12.sp, color = Color(0xFF7A8078))
                    Text(claim["tag"] ?: "", fontSize = 12.sp, color = Color(0xFF14231A), fontWeight = FontWeight.Bold)
                    Text("  |  ", color = Color(0xFFD5D8D2), fontSize = 12.sp)
                    Text(lang.getT("Farmer: ", "किसान: ", "କୃଷକ: ") + (claim["farmer"] ?: ""), fontSize = 12.sp, color = Color(0xFF7A8078), maxLines = 1)
                }
                
                Spacer(modifier = Modifier.height(6.dp))
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CalendarToday, null, tint = Color.Gray, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(lang.getT("Date of Death: ", "मृत्यु की तारीख: ", "ମୃତ୍ୟୁ ତାରିଖ: ") + (claim["date"] ?: ""), fontSize = 12.sp, color = Color.Gray)
                }
            }
            
            Icon(Icons.Default.ChevronRight, null, tint = themeColor, modifier = Modifier.size(20.dp))
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
            UserRole.FARMER -> claimsVm.loadFarmerReview(claimId)
            else -> {}
        }
    }
    val review = (reviewState as? UiState.Success)?.data

    if (isFarmerRole) {
        // Farmer Claim Details — the same layout as the Didi's, rendered by its own
        // body function (FarmerClaimDetailsBody) so the two screens stay independent.
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(languageState.value.getT("Claim Details", "दावा विवरण", "ଦାବି ବିବରଣୀ"), fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF14231A)) },
                    navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF14231A)) } },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
                )
            },
            containerColor = PageBackground
        ) { padding ->
            val loaded = review
            if (loaded == null) {
                Box(Modifier.padding(padding).fillMaxSize(), Alignment.Center) { CircularProgressIndicator(color = PrimaryBlue) }
                return@Scaffold
            }
            FarmerClaimDetailsBody(review = loaded, onContactSupport = { context.callSupport() }, modifier = Modifier.padding(padding))
        }
        return
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = languageState.value.getT("Claim Details", "दावा विवरण", "ଦାବି ବିବରଣୀ"),
                        fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF14231A)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF14231A))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = PageBackground
    ) { padding ->
        val loaded = review
        if (loaded == null) {
            Box(Modifier.padding(padding).fillMaxSize(), Alignment.Center) {
                CircularProgressIndicator(color = PrimaryGreen)
            }
            return@Scaffold
        }
        ClaimDetailsBody(
            review = loaded,
            onContactSupport = { context.callSupport() },
            modifier = Modifier.padding(padding)
        )
    }
}

/** Claim Details, as supplied: hero, goat, death, evidence, progress. */
@Composable
private fun ClaimDetailsBody(
    review: ClaimReview,
    onContactSupport: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lang = LocalAppLanguage.current.value
    val inr = remember { java.text.NumberFormat.getInstance(java.util.Locale("en", "IN")) }
    fun money(v: Double?) = v?.let { "₹ " + inr.format(it.toLong()) } ?: "—"

    Column(modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(horizontal = 16.dp)) {
        Spacer(Modifier.height(12.dp))

        // ---- hero ----
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEDF4E4)),
            border = BorderStroke(1.dp, Color(0xFFEFEBE3)),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Box(Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(R.drawable.claim_banner),
                    contentDescription = null,
                    // Same banner and framing as the My Claims summary card.
                    modifier = Modifier.matchParentSize().graphicsLayer {
                        scaleX = 1.25f
                        scaleY = 1.25f
                        translationY = size.height * 0.06f
                    },
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                    alignment = Alignment.CenterEnd
                )
                // Green fade along the bottom, matching the My Claims card, so the
                // reported-on / reported-by stats read over the grass.
                Box(
                    Modifier.align(Alignment.BottomCenter).fillMaxWidth().height(100.dp)
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    Color(0x8812401F),
                                    Color(0xD912401F),
                                    Color(0xFF12401F),
                                )
                            )
                        )
                )
                Column(Modifier.fillMaxWidth().padding(14.dp)) {
                    Row(Modifier.fillMaxWidth(0.7f), verticalAlignment = Alignment.CenterVertically) {
                        Column {
                            Text(lang.getT("Claim ID", "दावा आईडी", "ଦାବି ID"), fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF5B6660))
                            Text(review.claimNumber, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A), maxLines = 1)
                            Spacer(Modifier.height(4.dp))
                            ClaimStatusPill(review.status, lang)
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                    HorizontalDivider(color = Color(0x4DFFFFFF), modifier = Modifier.fillMaxWidth(0.7f))
                    Spacer(Modifier.height(10.dp))
                    // These sit on the green fade, so they render in white.
                    Row(Modifier.fillMaxWidth()) {
                        ClaimHeroStat(
                            Icons.Default.CalendarToday,
                            lang.getT("Claim Reported On", "दावा दर्ज", "ଦାବି ଦାଖଲ"),
                            claimDate(review.claimReportedOn), Modifier.weight(1f),
                            accent = Color(0xFF9BC7FF), onDark = true
                        )
                        Box(Modifier.height(34.dp).width(1.dp).background(Color(0x33FFFFFF)))
                        Spacer(Modifier.width(10.dp))
                        ClaimHeroStat(
                            Icons.Default.Person,
                            lang.getT("Reported By", "रिपोर्ट कर्ता", "ରିପୋର୍ଟକର୍ତ୍ତା"),
                            review.reportedBy ?: review.farmer ?: "—", Modifier.weight(1f),
                            accent = Color(0xFFD3B8FF), onDark = true
                        )
                    }
                }
            }
        }

        // ---- goat information ----
        Spacer(Modifier.height(14.dp))
        ClaimSectionCard(Icons.Default.Pets, lang.getT("Goat Information", "बकरी की जानकारी", "ଛେଳି ସୂଚନା"), IconGreen) {
            Row(
                Modifier.fillMaxWidth().padding(horizontal = 14.dp),
                verticalAlignment = Alignment.Top
            ) {
                ClaimGoatPhoto(review.goat?.photo, Modifier.size(104.dp))
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Row {
                        ClaimField(Icons.Default.Label, lang.getT("Ear Tag", "कान का टैग", "କାନ ଟ୍ୟାଗ୍"), review.goat?.earTagNumber ?: "—", Modifier.weight(1f), accent = IconBlue)
                        ClaimField(Icons.Default.Pets, lang.getT("Breed", "नस्ल", "ଜାତି"), review.goat?.breed ?: "—", Modifier.weight(1f), accent = IconRose)
                    }
                    Row {
                        ClaimField(Icons.Default.Person, lang.getT("Gender", "लिंग", "ଲିଙ୍ଗ"), review.goat?.gender?.replaceFirstChar { it.uppercase() } ?: "—", Modifier.weight(1f), accent = IconPurple)
                        ClaimField(Icons.Default.CalendarToday, lang.getT("Age", "आयु", "ବୟସ"), review.goat?.ageMonths?.let { goatAgeLabel(it, lang) } ?: "—", Modifier.weight(1f), accent = IconAmber)
                    }
                }
            }
            HorizontalDivider(color = Color(0xFFF1EEE7), modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp))
            Row(Modifier.fillMaxWidth().padding(horizontal = 14.dp)) {
                ClaimField(Icons.Default.Shield, lang.getT("Sum Insured", "बीमा राशि", "ବୀମା ରାଶି"), money(review.sumInsured), Modifier.weight(1f), accent = IconGreen)
                ClaimField(Icons.Default.VerifiedUser, lang.getT("Policy No.", "पॉलिसी संख्या", "ପଲିସି ନମ୍ବର"), review.policyNumber ?: "—", Modifier.weight(1f), accent = IconIndigo)
            }
            Row(Modifier.fillMaxWidth().padding(horizontal = 14.dp)) {
                ClaimField(Icons.Default.CalendarToday, lang.getT("Policy Valid Till", "पॉलिसी वैधता", "ପଲିସି ବୈଧତା"), claimDate(review.policyValidTo), Modifier.weight(1f), accent = IconBlue)
                ClaimField(Icons.Default.Description, lang.getT("Claim Amount", "दावा राशि", "ଦାବି ରାଶି"), money(review.claimAmount), Modifier.weight(1f), accent = IconTeal)
            }
            Spacer(Modifier.height(10.dp))
        }

        // ---- death information ----
        Spacer(Modifier.height(14.dp))
        ClaimSectionCard(Icons.Default.Info, lang.getT("Death Information", "मृत्यु की जानकारी", "ମୃତ୍ୟୁ ସୂଚନା"), IconRose) {
            Row(Modifier.fillMaxWidth().padding(horizontal = 14.dp)) {
                ClaimField(Icons.Default.CalendarToday, lang.getT("Date of Death", "मृत्यु की तारीख", "ମୃତ୍ୟୁ ତାରିଖ"), claimDate(review.dateOfDeath), Modifier.weight(1f), accent = IconBlue)
                ClaimField(Icons.Default.Info, lang.getT("Cause of Death", "मृत्यु का कारण", "ମୃତ୍ୟୁର କାରଣ"), review.causeOfDeath ?: "—", Modifier.weight(1f), accent = IconRose)
            }
            Row(Modifier.fillMaxWidth().padding(horizontal = 14.dp)) {
                ClaimField(Icons.Default.LocationOn, lang.getT("Location", "स्थान", "ସ୍ଥାନ"), review.location ?: "—", Modifier.weight(1f), accent = IconTeal)
                ClaimField(Icons.Default.Person, lang.getT("Reported By", "रिपोर्ट कर्ता", "ରିପୋର୍ଟକର୍ତ୍ତା"), review.reportedBy ?: review.farmer ?: "—", Modifier.weight(1f), accent = IconPurple)
            }
            Spacer(Modifier.height(10.dp))
        }

        // ---- uploaded evidence ----
        if (review.evidence.isNotEmpty()) {
            Spacer(Modifier.height(14.dp))
            ClaimSectionCard(Icons.Default.CameraAlt, lang.getT("Uploaded Evidence", "अपलोड किए गए प्रमाण", "ଅପଲୋଡ୍ ପ୍ରମାଣ"), IconAmber) {
                Row(
                    Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())
                        .padding(horizontal = 14.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    review.evidence.forEach { e -> ClaimEvidenceTile(e, lang) }
                }
                Spacer(Modifier.height(12.dp))
            }
        }

        // ---- claim progress ----
        Spacer(Modifier.height(14.dp))
        ClaimSectionCard(Icons.AutoMirrored.Filled.TrendingUp, lang.getT("Claim Progress", "दावा प्रगति", "ଦାବି ପ୍ରଗତି"), IconIndigo) {
            ClaimProgressTrack(review, lang)
            Spacer(Modifier.height(12.dp))
        }

        Spacer(Modifier.height(18.dp))
        Button(
            
            onClick = onContactSupport,
            modifier = Modifier.fillMaxWidth().height(54.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
        ) {
            Icon(Icons.Default.SupportAgent, null, tint = Color.White, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(10.dp))
            Text(
                lang.getT("Contact Support", "सहायता से संपर्क करें", "ସହାୟତା ସହ ଯୋଗାଯୋଗ"),
                color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp
            )
        }
        Spacer(Modifier.height(20.dp))
    }
}

/** Farmer Claim Details — an independent copy of ClaimDetailsBody's layout, so the
 *  farmer and Didi claim screens can evolve separately. */
@Composable
private fun FarmerClaimDetailsBody(
    review: ClaimReview,
    onContactSupport: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lang = LocalAppLanguage.current.value
    val inr = remember { java.text.NumberFormat.getInstance(java.util.Locale("en", "IN")) }
    fun money(v: Double?) = v?.let { "₹ " + inr.format(it.toLong()) } ?: "—"

    Column(modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(horizontal = 16.dp)) {
        Spacer(Modifier.height(12.dp))

        // ---- hero ----
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEDF4E4)),
            border = BorderStroke(1.dp, Color(0xFFEFEBE3)),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Box(Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(R.drawable.claim_banner),
                    contentDescription = null,
                    modifier = Modifier.matchParentSize().graphicsLayer {
                        scaleX = 1.25f
                        scaleY = 1.25f
                        translationY = size.height * 0.06f
                    },
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                    alignment = Alignment.CenterEnd
                )
                Box(
                    Modifier.align(Alignment.BottomCenter).fillMaxWidth().height(100.dp)
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, Color(0x8812401F), Color(0xD912401F), Color(0xFF12401F))
                            )
                        )
                )
                Column(Modifier.fillMaxWidth().padding(14.dp)) {
                    Row(Modifier.fillMaxWidth(0.7f), verticalAlignment = Alignment.CenterVertically) {
                        Column {
                            Text(lang.getT("Claim ID", "दावा आईडी", "ଦାବି ID"), fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF5B6660))
                            Text(review.claimNumber, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A), maxLines = 1)
                            Spacer(Modifier.height(4.dp))
                            ClaimStatusPill(review.status, lang)
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                    HorizontalDivider(color = Color(0x4DFFFFFF), modifier = Modifier.fillMaxWidth(0.7f))
                    Spacer(Modifier.height(10.dp))
                    Row(Modifier.fillMaxWidth()) {
                        ClaimHeroStat(
                            Icons.Default.CalendarToday,
                            lang.getT("Claim Reported On", "दावा दर्ज", "ଦାବି ଦାଖଲ"),
                            claimDate(review.claimReportedOn), Modifier.weight(1f),
                            accent = Color(0xFF9BC7FF), onDark = true
                        )
                        Box(Modifier.height(34.dp).width(1.dp).background(Color(0x33FFFFFF)))
                        Spacer(Modifier.width(10.dp))
                        ClaimHeroStat(
                            Icons.Default.Person,
                            lang.getT("Reported By", "रिपोर्ट कर्ता", "ରିପୋର୍ଟକର୍ତ୍ତା"),
                            review.reportedBy ?: review.farmer ?: "—", Modifier.weight(1f),
                            accent = Color(0xFFD3B8FF), onDark = true
                        )
                    }
                }
            }
        }

        // ---- goat information ----
        Spacer(Modifier.height(14.dp))
        ClaimSectionCard(Icons.Default.Pets, lang.getT("Goat Information", "बकरी की जानकारी", "ଛେଳି ସୂଚନା"), IconGreen) {
            Row(
                Modifier.fillMaxWidth().padding(horizontal = 14.dp),
                verticalAlignment = Alignment.Top
            ) {
                ClaimGoatPhoto(review.goat?.photo, Modifier.size(104.dp))
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Row {
                        ClaimField(Icons.Default.Label, lang.getT("Ear Tag", "कान का टैग", "କାନ ଟ୍ୟାଗ୍"), review.goat?.earTagNumber ?: "—", Modifier.weight(1f), accent = IconBlue)
                        ClaimField(Icons.Default.Pets, lang.getT("Breed", "नस्ल", "ଜାତି"), review.goat?.breed ?: "—", Modifier.weight(1f), accent = IconRose)
                    }
                    Row {
                        ClaimField(Icons.Default.Person, lang.getT("Gender", "लिंग", "ଲିଙ୍ଗ"), review.goat?.gender?.replaceFirstChar { it.uppercase() } ?: "—", Modifier.weight(1f), accent = IconPurple)
                        ClaimField(Icons.Default.CalendarToday, lang.getT("Age", "आयु", "ବୟସ"), review.goat?.ageMonths?.let { goatAgeLabel(it, lang) } ?: "—", Modifier.weight(1f), accent = IconAmber)
                    }
                }
            }
            HorizontalDivider(color = Color(0xFFF1EEE7), modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp))
            Row(Modifier.fillMaxWidth().padding(horizontal = 14.dp)) {
                ClaimField(Icons.Default.Shield, lang.getT("Sum Insured", "बीमा राशि", "ବୀମା ରାଶି"), money(review.sumInsured), Modifier.weight(1f), accent = IconGreen)
                ClaimField(Icons.Default.VerifiedUser, lang.getT("Policy No.", "पॉलिसी संख्या", "ପଲିସି ନମ୍ବର"), review.policyNumber ?: "—", Modifier.weight(1f), accent = IconIndigo)
            }
            Row(Modifier.fillMaxWidth().padding(horizontal = 14.dp)) {
                ClaimField(Icons.Default.CalendarToday, lang.getT("Policy Valid Till", "पॉलिसी वैधता", "ପଲିସି ବୈଧତା"), claimDate(review.policyValidTo), Modifier.weight(1f), accent = IconBlue)
                ClaimField(Icons.Default.Description, lang.getT("Claim Amount", "दावा राशि", "ଦାବି ରାଶି"), money(review.claimAmount), Modifier.weight(1f), accent = IconTeal)
            }
            Spacer(Modifier.height(10.dp))
        }

        // ---- death information ----
        Spacer(Modifier.height(14.dp))
        ClaimSectionCard(Icons.Default.Info, lang.getT("Death Information", "मृत्यु की जानकारी", "ମୃତ୍ୟୁ ସୂଚନା"), IconRose) {
            Row(Modifier.fillMaxWidth().padding(horizontal = 14.dp)) {
                ClaimField(Icons.Default.CalendarToday, lang.getT("Date of Death", "मृत्यु की तारीख", "ମୃତ୍ୟୁ ତାରିଖ"), claimDate(review.dateOfDeath), Modifier.weight(1f), accent = IconBlue)
                ClaimField(Icons.Default.Info, lang.getT("Cause of Death", "मृत्यु का कारण", "ମୃତ୍ୟୁର କାରଣ"), review.causeOfDeath ?: "—", Modifier.weight(1f), accent = IconRose)
            }
            Row(Modifier.fillMaxWidth().padding(horizontal = 14.dp)) {
                ClaimField(Icons.Default.LocationOn, lang.getT("Location", "स्थान", "ସ୍ଥାନ"), review.location ?: "—", Modifier.weight(1f), accent = IconTeal)
                ClaimField(Icons.Default.Person, lang.getT("Reported By", "रिपोर्ट कर्ता", "ରିପୋର୍ଟକର୍ତ୍ତା"), review.reportedBy ?: review.farmer ?: "—", Modifier.weight(1f), accent = IconPurple)
            }
            Spacer(Modifier.height(10.dp))
        }

        // ---- uploaded evidence ----
        if (review.evidence.isNotEmpty()) {
            Spacer(Modifier.height(14.dp))
            ClaimSectionCard(Icons.Default.CameraAlt, lang.getT("Uploaded Evidence", "अपलोड किए गए प्रमाण", "ଅପଲୋଡ୍ ପ୍ରମାଣ"), IconAmber) {
                Row(
                    Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())
                        .padding(horizontal = 14.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    review.evidence.forEach { e -> ClaimEvidenceTile(e, lang) }
                }
                Spacer(Modifier.height(12.dp))
            }
        }

        // ---- claim progress ----
        Spacer(Modifier.height(14.dp))
        ClaimSectionCard(Icons.AutoMirrored.Filled.TrendingUp, lang.getT("Claim Progress", "दावा प्रगति", "ଦାବି ପ୍ରଗତି"), IconIndigo) {
            ClaimProgressTrack(review, lang)
            Spacer(Modifier.height(12.dp))
        }

        Spacer(Modifier.height(18.dp))
        Button(
            onClick = onContactSupport,
            modifier = Modifier.fillMaxWidth().height(54.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
        ) {
            Icon(Icons.Default.SupportAgent, null, tint = Color.White, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(10.dp))
            Text(
                lang.getT("Contact Support", "सहायता से संपर्क करें", "ସହାୟତା ସହ ଯୋଗାଯୋଗ"),
                color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp
            )
        }
        Spacer(Modifier.height(20.dp))
    }
}

/** "17 Jul 2026" (IST) from the API's yyyy-MM-dd or a full UTC timestamp. */
private fun claimDate(raw: String?): String = AppTime.date(raw)

@Composable
private fun ClaimStatusPill(status: String, lang: AppLanguage) {
    val (label, bg, fg) = when (status) {
        "approved", "claimed" -> Triple(lang.getT("Approved", "स्वीकृत", "ଅନୁମୋଦିତ"), Color(0xFFE3F3E4), Color(0xFF2E7D32))
        "rejected" -> Triple(lang.getT("Rejected", "अस्वीकृत", "ପ୍ରତ୍ୟାଖ୍ୟାନ"), Color(0xFFFBE7E7), Color(0xFFC62828))
        "hold" -> Triple(lang.getT("On Hold", "होल्ड पर", "ହୋଲ୍ଡ"), Color(0xFFE6EFFA), Color(0xFF1565C0))
        // Solid amber: the old pale cream vanished against the banner sky.
        else -> Triple(lang.getT("Pending", "लंबित", "ବାକି"), Color(0xFFD98200), Color.White)
    }
    Surface(color = bg, shape = RoundedCornerShape(50)) {
        Text(label, color = fg, fontSize = 11.sp, fontWeight = FontWeight.Bold,
             modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp))
    }
}

@Composable
private fun ClaimHeroStat(icon: ImageVector, label: String, value: String, modifier: Modifier, accent: Color = PrimaryGreen, onDark: Boolean = false) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = accent, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(7.dp))
        Column {
            Text(label, fontSize = 11.sp, lineHeight = 12.sp, color = if (onDark) Color.White.copy(alpha = 0.8f) else Color(0xFF5B6660), maxLines = 1)
            Spacer(Modifier.height(3.dp))
            Text(value, fontSize = 13.sp, lineHeight = 14.sp, fontWeight = FontWeight.Bold, color = if (onDark) Color.White else Color(0xFF14231A), maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Composable
private fun ClaimSectionCard(icon: ImageVector, title: String, accent: Color = PrimaryGreen, content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFEFEBE3)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column {
            Row(Modifier.fillMaxWidth().padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, null, tint = accent, modifier = Modifier.size(22.dp))
                Spacer(Modifier.width(9.dp))
                Text(title, fontSize = 17.sp, fontWeight = FontWeight.Bold, color = accent)
            }
            HorizontalDivider(color = Color(0xFFF1EEE7))
            Spacer(Modifier.height(10.dp))
            content()
        }
    }
}

@Composable
private fun ClaimField(icon: ImageVector, label: String, value: String, modifier: Modifier, accent: Color = PrimaryGreen) {
    Column(modifier.padding(vertical = 5.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = accent, modifier = Modifier.size(17.dp))
            Spacer(Modifier.width(6.dp))
            Text(label, fontSize = 12.sp, color = Color(0xFF8A908A), maxLines = 1)
        }
        Text(
            value, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A),
            maxLines = 1, overflow = TextOverflow.Ellipsis,
            // matches the icon (17dp) + its 6dp gap, so values line up under the labels
            modifier = Modifier.padding(start = 23.dp)
        )
    }
}

@Composable
private fun ClaimGoatPhoto(url: String?, modifier: Modifier) {
    var failed by remember(url) { mutableStateOf(false) }
    Box(
        modifier.clip(RoundedCornerShape(14.dp)).background(Color(0xFFEDF4E4)),
        contentAlignment = Alignment.Center
    ) {
        if (url != null && !failed) {
            AsyncImage(
                model = absoluteMediaUrl(url), contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                onError = { failed = true }
            )
        } else {
            Icon(
                painterResource(R.drawable.ic_ewe_custom), null,
                tint = PrimaryGreen.copy(alpha = 0.35f), modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Composable
private fun ClaimEvidenceTile(e: ClaimEvidence, lang: AppLanguage) {
    val caption = when (e.type) {
        "ear_tag" -> lang.getT("Tag Photo", "टैग फोटो", "ଟ୍ୟାଗ୍ ଫଟୋ")
        "full_body" -> lang.getT("Dead Goat Photo", "मृत बकरी फोटो", "ମୃତ ଛେଳି ଫଟୋ")
        "location" -> lang.getT("Site Visit Photo", "साइट विजिट फोटो", "ସାଇଟ୍ ଫଟୋ")
        else -> lang.getT("Documents", "दस्तावेज़", "ଦଲିଲ")
    }
    var failed by remember(e.url) { mutableStateOf(false) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            Modifier.size(96.dp).clip(RoundedCornerShape(12.dp)).background(Color(0xFFF1F3EE)),
            contentAlignment = Alignment.Center
        ) {
            if (!failed) {
                AsyncImage(
                    model = absoluteMediaUrl(e.url), contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                    onError = { failed = true }
                )
            } else {
                Icon(Icons.Default.Image, null, tint = Color(0xFFB0B7B0), modifier = Modifier.size(28.dp))
            }
        }
        Spacer(Modifier.height(5.dp))
        Text(caption, fontSize = 11.sp, color = Color(0xFF5B6660), maxLines = 1)
    }
}

/** The five claim stages, driven by the progress map the API already returns. */
@Composable
private fun ClaimProgressTrack(review: ClaimReview, lang: AppLanguage) {
    data class Step(val label: String, val done: Boolean, val current: Boolean, val date: String?)

    val p = review.progress
    fun doneOf(key: String) = p[key] == "completed"
    val decided = review.status in listOf("approved", "rejected", "claimed")

    val steps = listOf(
        Step(lang.getT("Death Reported", "मृत्यु दर्ज", "ମୃତ୍ୟୁ ଦାଖଲ"), doneOf("death_notification"), false, review.reportedAt),
        Step(lang.getT("Didi Verification", "दीदी सत्यापन", "ଦିଦି ଯାଞ୍ଚ"), doneOf("site_visit"), false, review.verifiedAt),
        Step(lang.getT("Coordinator Review", "समन्वयक समीक्षा", "ସମନ୍ୱୟକ ସମୀକ୍ଷା"), doneOf("claim_review"), doneOf("claim_submission") && !doneOf("claim_review"), null),
        Step(lang.getT("Admin Approval", "प्रशासक अनुमोदन", "ପ୍ରଶାସକ ଅନୁମୋଦନ"), decided, false, null),
        Step(lang.getT("Payment Completed", "भुगतान पूर्ण", "ଦେୟ ସମ୍ପୂର୍ଣ୍ଣ"), review.status == "claimed", false, null),
    )

    Row(Modifier.fillMaxWidth().padding(horizontal = 8.dp)) {
        steps.forEach { step ->
            Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    Modifier.size(24.dp).clip(CircleShape)
                        .background(if (step.done) PrimaryGreen else Color(0xFFE3E6E1))
                        .border(
                            width = if (step.current) 3.dp else 0.dp,
                            color = if (step.current) PrimaryGreen else Color.Transparent,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (step.done) Icon(Icons.Default.Check, null, tint = Color.White, modifier = Modifier.size(14.dp))
                }
                Spacer(Modifier.height(6.dp))
                Text(
                    step.label, fontSize = 9.sp, lineHeight = 12.sp,
                    color = if (step.done || step.current) Color(0xFF14231A) else Color(0xFF8A908A),
                    fontWeight = if (step.done || step.current) FontWeight.Bold else FontWeight.Normal,
                    textAlign = TextAlign.Center, maxLines = 2
                )
                Spacer(Modifier.height(3.dp))
                Text(
                    when {
                        step.date != null -> claimDate(step.date)
                        step.current -> lang.getT("In Progress", "जारी", "ଚାଲିଛି")
                        step.done -> lang.getT("Done", "पूर्ण", "ସମ୍ପୂର୍ଣ୍ଣ")
                        else -> lang.getT("Pending", "लंबित", "ବାକି")
                    },
                    fontSize = 8.sp,
                    color = if (step.current) PrimaryGreen else Color(0xFF8A908A),
                    textAlign = TextAlign.Center, maxLines = 1
                )
            }
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
        containerColor = if (isDidi) PageBackground else MaterialTheme.colorScheme.background,
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
        containerColor = PageBackground
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
        containerColor = PageBackground
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
    var gender by remember { mutableStateOf("") }
    
    var state by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }
    var block by remember { mutableStateOf("") }
    var village by remember { mutableStateOf("") }
    var pincode by remember { mutableStateOf("") }
    
    var aadhaarNumber by remember { mutableStateOf("") }
    var aadhaarPhotoUri by remember { mutableStateOf<Uri?>(null) }
    // Which photo target the Camera/Gallery chooser is open for ("profile"/"aadhaar"), or null.
    var photoChooserTarget by remember { mutableStateOf<String?>(null) }

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

    if (photoChooserTarget != null) {
        val target = photoChooserTarget!!
        AlertDialog(
            onDismissRequest = { photoChooserTarget = null },
            containerColor = Color.White,
            shape = RoundedCornerShape(20.dp),
            title = { Text(languageState.value.getT("Add Photo", "फोटो जोड़ें", "ଫଟୋ ଯୋଡନ୍ତୁ"), fontWeight = FontWeight.Bold, color = Color.Black) },
            text = {
                Column {
                    Surface(color = Color.Transparent, onClick = { photoChooserTarget = null; launchCamera(target) }) {
                        Row(Modifier.fillMaxWidth().padding(vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CameraAlt, null, tint = themeColor, modifier = Modifier.size(26.dp))
                            Spacer(Modifier.width(16.dp))
                            Text(languageState.value.getT("Take Photo", "फोटो लें", "ଫଟୋ ନିଅନ୍ତୁ"), fontSize = 16.sp, color = Color.Black)
                        }
                    }
                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.4f))
                    Surface(color = Color.Transparent, onClick = {
                        photoChooserTarget = null
                        if (target == "profile") imagePickerLauncher.launch("image/*") else aadhaarPickerLauncher.launch("image/*")
                    }) {
                        Row(Modifier.fillMaxWidth().padding(vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.PhotoLibrary, null, tint = themeColor, modifier = Modifier.size(26.dp))
                            Spacer(Modifier.width(16.dp))
                            Text(languageState.value.getT("Choose from Gallery", "गैलरी से चुनें", "ଗ୍ୟାଲେରୀରୁ ବାଛନ୍ତୁ"), fontSize = 16.sp, color = Color.Black)
                        }
                    }
                }
            },
            confirmButton = {},
            dismissButton = {
                TextButton(onClick = { photoChooserTarget = null }) {
                    Text(languageState.value.getT("Cancel", "रद्द करें", "ବାତିଲ୍"), color = Color.Gray)
                }
            }
        )
    }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFFAF6EE))) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .navigationBarsPadding()
        ) {
            // Curved green header with a floating "Upload Photo" avatar.
            Box(modifier = Modifier.fillMaxWidth().height(226.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .drawBehind {
                            val w = size.width
                            val h = size.height
                            val p = Path().apply {
                                moveTo(0f, 0f)
                                lineTo(w, 0f)
                                lineTo(w, h * 0.54f)
                                // asymmetric wave: left side rides higher, right side dips lower
                                cubicTo(w * 0.90f, h * 0.38f, w * 0.78f, h * 0.42f, w * 0.66f, h * 0.62f)
                                cubicTo(w * 0.56f, h * 0.70f, w * 0.44f, h * 0.70f, w * 0.34f, h * 0.56f)
                                cubicTo(w * 0.22f, h * 0.30f, w * 0.10f, h * 0.26f, 0f, h * 0.42f)
                                close()
                            }
                            drawPath(p, themeColor)
                        }
                ) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.align(Alignment.TopStart).statusBarsPadding().padding(start = 4.dp, top = 4.dp)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                    Text(
                        languageState.value.getT("Setup Profile", "प्रोफ़ाइल सेटअप", "ପ୍ରୋଫାଇଲ୍ ସେଟଅପ୍"),
                        color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.TopCenter).statusBarsPadding().padding(top = 14.dp)
                    )
                }
                Surface(
                    modifier = Modifier.align(Alignment.TopCenter).padding(top = 104.dp).size(118.dp),
                    shape = CircleShape,
                    color = Color.White,
                    shadowElevation = 5.dp,
                    onClick = { photoChooserTarget = "profile" }
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        if (profilePhotoUri != null) {
                            AsyncImage(
                                model = profilePhotoUri,
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize().clip(CircleShape),
                                contentScale = androidx.compose.ui.layout.ContentScale.Crop
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(104.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFF6F1E6))
                                    .drawBehind {
                                        drawCircle(
                                            color = Color(0xFFB99C74),
                                            style = Stroke(width = 2.dp.toPx(), pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 12f), 0f))
                                        )
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(Icons.Default.AddAPhoto, null, tint = Color(0xFF8A7A63), modifier = Modifier.size(30.dp))
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(languageState.value.getT("Upload Photo", "फोटो अपलोड करें", "ଫଟୋ ଅପଲୋଡ୍"), fontSize = 12.sp, color = Color(0xFF8A7A63))
                                }
                            }
                        }
                    }
                }
            }

            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
                ProfileSetupSection(languageState.value.getT("Basic Info", "मूल जानकारी", "ମୌଳିକ ସୂଚନା"), Icons.Default.Person, themeColor) {
                    EnrollmentTextField(
                        label = languageState.value.getT("Full Name *", "पूरा नाम *", "ପୁରା ନାମ *"),
                        value = name,
                        onValueChange = { if (it.all { char -> char.isLetter() || char.isWhitespace() }) name = it },
                        placeholder = languageState.value.getT("Enter full name", "पूरा नाम दर्ज करें", "ପୁରା ନାମ ଲେଖନ୍ତୁ"),
                        leadingIcon = Icons.Default.Person,
                        borderColor = themeColor
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    EnrollmentTextField(
                        label = languageState.value.getT("Phone Number *", "फोन नंबर *", "ଫୋନ୍ ନମ୍ବର *"),
                        value = phone,
                        onValueChange = {},
                        placeholder = languageState.value.getT("Enter phone number", "फोन नंबर दर्ज करें", "ଫୋନ୍ ନମ୍ବର ଲେଖନ୍ତୁ"),
                        leadingIcon = Icons.Default.Phone,
                        readOnly = true,
                        borderColor = themeColor
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    EnrollmentTextField(
                        label = languageState.value.getT("Date of Birth *", "जन्म तिथि *", "ଜନ୍ମ ତାରିଖ *"),
                        value = dob,
                        onValueChange = { dob = it },
                        placeholder = "DD/MM/YYYY",
                        leadingIcon = Icons.Default.CalendarToday,
                        readOnly = true,
                        onTrailingIconClick = {
                            DatePickerDialog(
                                context,
                                { _, year, month, dayOfMonth -> dob = "$dayOfMonth/${month + 1}/$year" },
                                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
                            ).show()
                        },
                        borderColor = themeColor
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    EnrollmentDropdownField(
                        label = languageState.value.getT("Gender *", "लिंग *", "ଲିଙ୍ଗ *"),
                        selectedValue = gender,
                        options = listOf("Female", "Male", "Other"),
                        onValueChange = { gender = it },
                        placeholder = languageState.value.getT("Select Gender", "लिंग चुनें", "ଲିଙ୍ଗ ବାଛନ୍ତୁ"),
                        leadingIcon = Icons.Default.Person,
                        borderColor = themeColor
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                ProfileSetupSection(languageState.value.getT("Location", "स्थान", "ଅବସ୍ଥାନ"), Icons.Default.LocationOn, themeColor) {
                    EnrollmentDropdownField(label = languageState.value.getT("State *", "राज्य *", "ରାଜ୍ୟ *"), selectedValue = state, options = stateOptions, onValueChange = { state = it }, placeholder = languageState.value.getT("Select State", "राज्य चुनें", "ରାଜ୍ୟ ବାଛନ୍ତୁ"), leadingIcon = Icons.Default.Map, borderColor = themeColor)
                    Spacer(modifier = Modifier.height(14.dp))
                    EnrollmentDropdownField(label = languageState.value.getT("District *", "जिला *", "ଜିଲ୍ଲା *"), selectedValue = district, options = districtOptions, onValueChange = { district = it }, placeholder = languageState.value.getT("Select District", "जिला चुनें", "ଜିଲ୍ଲା ବାଛନ୍ତୁ"), leadingIcon = Icons.Default.LocationCity, borderColor = themeColor)
                    Spacer(modifier = Modifier.height(14.dp))
                    EnrollmentDropdownField(label = languageState.value.getT("Block *", "ब्लॉक *", "ବ୍ଲକ *"), selectedValue = block, options = blockOptions, onValueChange = { block = it }, placeholder = languageState.value.getT("Select Block", "ब्लॉक चुनें", "ବ୍ଲକ ବାଛନ୍ତୁ"), leadingIcon = Icons.Default.GridView, borderColor = themeColor)
                    Spacer(modifier = Modifier.height(14.dp))
                    EnrollmentTextField(label = locationLabel, value = village, onValueChange = { village = it }, placeholder = languageState.value.getT("Enter Village", "गाँव दर्ज करें", "ଗ୍ରାମ ଲେଖନ୍ତୁ"), leadingIcon = Icons.Default.Home, borderColor = themeColor)
                    Spacer(modifier = Modifier.height(14.dp))
                    EnrollmentTextField(label = languageState.value.getT("Pincode *", "पिनकोड *", "ପିନକୋଡ୍ *"), value = pincode, onValueChange = { if(it.length <= 6) pincode = it }, placeholder = languageState.value.getT("Enter Pincode", "पिनकोड दर्ज करें", "ପିନକୋଡ୍ ଲେଖନ୍ତୁ"), leadingIcon = Icons.Default.LocationOn, keyboardType = KeyboardType.Number, borderColor = themeColor)
                }

                Spacer(modifier = Modifier.height(20.dp))

                ProfileSetupSection(languageState.value.getT("Identity / KYC", "पहचान / केवाईसी", "ପରିଚୟ / KYC"), Icons.Default.Shield, themeColor) {
                    EnrollmentTextField(label = languageState.value.getT("Aadhaar Number *", "आधार नंबर *", "ଆଧାର ନମ୍ବର *"), value = aadhaarNumber, onValueChange = { if(it.length <= 12) aadhaarNumber = it }, placeholder = languageState.value.getT("Enter Aadhaar Number", "आधार नंबर दर्ज करें", "ଆଧାର ନମ୍ବର ଲେଖନ୍ତୁ"), leadingIcon = Icons.Default.CreditCard, keyboardType = KeyboardType.Number, borderColor = themeColor)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(languageState.value.getT("Aadhaar Photo (Optional)", "आधार फोटो (वैकल्पिक)", "ଆଧାର ଫଟୋ (ବୈକଳ୍ପିକ)"), fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Spacer(modifier = Modifier.height(8.dp))
                    Surface(
                        modifier = Modifier.fillMaxWidth().height(110.dp),
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFFBF8F1),
                        border = BorderStroke(1.dp, Color(0xFFE3DCCB)),
                        onClick = { photoChooserTarget = "aadhaar" }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            if (aadhaarPhotoUri != null) {
                                AsyncImage(model = aadhaarPhotoUri, contentDescription = null, modifier = Modifier.fillMaxSize(), contentScale = androidx.compose.ui.layout.ContentScale.Fit)
                            } else {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(Icons.Default.CameraAlt, null, tint = Color(0xFF8A7A63), modifier = Modifier.size(26.dp))
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(languageState.value.getT("Tap to upload", "अपलोड करने के लिए टैप करें", "ଅପଲୋଡ୍ କରିବାକୁ ଟାପ୍ କରନ୍ତୁ"), fontSize = 12.sp, color = Color(0xFF8A7A63))
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                ProfileSetupSection(languageState.value.getT("Login Password", "लॉगिन पासवर्ड", "ଲଗଇନ୍ ପାସୱାର୍ଡ"), Icons.Default.Lock, themeColor) {
                    Surface(color = Color(0xFFFBF4E6), shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth()) {
                        Text(
                            languageState.value.getT(
                                "You will use this password to log in after your registration is approved.",
                                "अनुमोदन के बाद लॉगिन करने के लिए आप इस पासवर्ड का उपयोग करेंगे।",
                                "ଅନୁମୋଦନ ପରେ ଲଗଇନ୍ କରିବାକୁ ଆପଣ ଏହି ପାସୱାର୍ଡ ବ୍ୟବହାର କରିବେ |"
                            ),
                            fontSize = 12.sp, color = Color(0xFF7A6A52), modifier = Modifier.padding(10.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    EnrollmentTextField(
                        label = languageState.value.getT("Password (min 6 characters) *", "पासवर्ड (न्यूनतम 6 अक्षर) *", "ପାସୱାର୍ଡ (ସର୍ବନିମ୍ନ 6 ଅକ୍ଷର) *"),
                        value = signupPassword, onValueChange = { signupPassword = it },
                        placeholder = languageState.value.getT("Enter password", "पासवर्ड दर्ज करें", "ପାସୱାର୍ଡ ଲେଖନ୍ତୁ"),
                        leadingIcon = Icons.Default.Lock, keyboardType = KeyboardType.Password, borderColor = themeColor, isPassword = true
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    EnrollmentTextField(
                        label = languageState.value.getT("Confirm Password *", "पासवर्ड की पुष्टि करें *", "ପାସୱାର୍ଡ ନିଶ୍ଚିତ କରନ୍ତୁ *"),
                        value = signupPasswordConfirm, onValueChange = { signupPasswordConfirm = it },
                        placeholder = languageState.value.getT("Confirm password", "पासवर्ड की पुष्टि करें", "ପାସୱାର୍ଡ ନିଶ୍ଚିତ କରନ୍ତୁ"),
                        leadingIcon = Icons.Default.Lock, keyboardType = KeyboardType.Password, borderColor = themeColor, isPassword = true
                    )
                    if (signupPasswordConfirm.isNotEmpty() && signupPassword != signupPasswordConfirm) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(languageState.value.getT("Passwords do not match", "पासवर्ड मेल नहीं खाते", "ପାସୱାର୍ଡ ମେଳ ଖାଉନାହିଁ"), fontSize = 12.sp, color = Color.Red)
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                Button(
                    onClick = {
                        authViewModel.completeSignup(
                            fullName = name, phone = phone, role = role,
                            village = village, aadhaarId = aadhaarNumber,
                            password = signupPassword,
                        )
                    },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = themeColor, disabledContainerColor = themeColor.copy(alpha = 0.5f)),
                    enabled = !isSubmitting && name.isNotBlank() && dob.isNotBlank() && state.isNotBlank() && district.isNotBlank() && block.isNotBlank() && village.isNotBlank() && pincode.length == 6 && aadhaarNumber.length == 12 && signupPassword.length >= 6 && signupPassword == signupPasswordConfirm
                ) {
                    Text(languageState.value.getT("Save & Continue", "सहेजें और जारी रखें", "ସଂରକ୍ଷଣ ଏବଂ ଜାରି ରଖନ୍ତୁ"), fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
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
fun ProfileSetupSection(title: String, icon: ImageVector, themeColor: Color, content: @Composable ColumnScope.() -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = themeColor, modifier = Modifier.size(22.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = themeColor)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color(0xFFECE6D8)),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                content()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EarningHistoryScreen(navController: NavHostController, onBack: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val context = LocalContext.current
    val backgroundColor = Color(0xFFF8F9F5)

    val earningsVm: EarningsViewModel = hiltViewModel()
    val earningsState by earningsVm.state.collectAsState()
    val earningsData = (earningsState as? UiState.Success)?.data
    val withdrawState by earningsVm.withdraw.collectAsState()
    var showWithdrawConfirm by remember { mutableStateOf(false) }
    // null = every month; otherwise a "yyyy-MM" key taken from the earning dates.
    var monthFilter by rememberSaveable { mutableStateOf<String?>(null) }
    var showMonthMenu by remember { mutableStateOf(false) }

    LaunchedEffect(withdrawState) {
        when (val s = withdrawState) {
            is SubmitState.Success -> {
                Toast.makeText(context, s.message ?: "Withdrawal requested", Toast.LENGTH_LONG).show()
                earningsVm.resetWithdraw(); earningsVm.load()
            }
            is SubmitState.Error -> {
                Toast.makeText(context, s.message, Toast.LENGTH_LONG).show(); earningsVm.resetWithdraw()
            }
            else -> {}
        }
    }

    if (showWithdrawConfirm) {
        AlertDialog(
            onDismissRequest = { showWithdrawConfirm = false },
            icon = { Icon(Icons.Default.AccountBalanceWallet, null, tint = PrimaryGreen) },
            title = { Text(languageState.value.getT("Withdraw Earnings", "आय निकालें", "ଉପାର୍ଜନ ଉଠାନ୍ତୁ"), fontWeight = FontWeight.Bold) },
            text = {
                Text(languageState.value.getT(
                    "This sends a withdrawal request for your balance of ₹${(earningsData?.total ?: 0.0).toInt()}. An admin will process the payout.",
                    "यह ₹${(earningsData?.total ?: 0.0).toInt()} की निकासी अनुरोध भेजेगा। एडमिन भुगतान संसाधित करेंगे।",
                    "ଏହା ₹${(earningsData?.total ?: 0.0).toInt()} ଉଠାଣ ଅନୁରୋଧ ପଠାଇବ। ଆଡମିନ୍ ଦେୟ ପ୍ରକ୍ରିୟା କରିବେ।"
                ), color = Color.DarkGray)
            },
            confirmButton = {
                TextButton(onClick = { showWithdrawConfirm = false; earningsVm.requestWithdrawal() }) {
                    Text(languageState.value.getT("Request", "अनुरोध करें", "ଅନୁରୋଧ"), color = PrimaryGreen, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showWithdrawConfirm = false }) {
                    Text(languageState.value.getT("Cancel", "रद्द करें", "ବାତିଲ୍"), color = Color.Gray)
                }
            },
            shape = RoundedCornerShape(20.dp)
        )
    }

    // White root: the window shows through under the transparent system nav bar,
    // which reads as a black band unless the screen paints that area itself.
    Box(Modifier.fillMaxSize().background(Color.White)) {
    Column(
        modifier = Modifier.fillMaxSize().background(backgroundColor)
    ) {
        // Top bar — outside the scrolling section, so it stays put.
        Row(
            modifier = Modifier.fillMaxWidth().statusBarsPadding().padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(onClick = onBack, shape = CircleShape, color = Color.White, shadowElevation = 1.dp, modifier = Modifier.size(40.dp)) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = PrimaryGreen, modifier = Modifier.size(20.dp))
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(languageState.value.getT("Earnings", "आय", "ଉପାର୍ଜନ"), style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color(0xFF14231A))
            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(40.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Hero card: this-month total, Withdraw, and real stats. Pinned above the
        // scroll so the balance and Withdraw stay reachable from anywhere in the list.
        Box(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Brush.linearGradient(listOf(Color(0xFF2E7D32), Color(0xFF15501C))))
                .padding(20.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(languageState.value.getT("Total Earnings", "कुल आय", "ମୋଟ ଉପାର୍ଜନ"), color = Color.White.copy(alpha = 0.85f), fontSize = 13.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("₹ ${(earningsData?.total ?: 0.0).toInt()}", fontSize = 32.sp, color = Color.White, fontWeight = FontWeight.Bold)
                    }
                    Surface(onClick = { showWithdrawConfirm = true }, shape = RoundedCornerShape(14.dp), color = Color.White,
                        enabled = withdrawState !is SubmitState.Submitting) {
                        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.AccountBalanceWallet, null, tint = PrimaryGreen, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(languageState.value.getT("Withdraw", "निकालें", "ଉଠାନ୍ତୁ"), color = PrimaryGreen, fontWeight = FontWeight.Bold)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = Color.White.copy(alpha = 0.2f))
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    EarningHeroStat(languageState.value.getT("Earned This Month", "इस महीने कमाई", "ଏହି ମାସ ଉପାର୍ଜନ"), "₹ ${(earningsData?.thisMonth ?: 0.0).toInt()}", Modifier.weight(1f))
                    EarningHeroStat(languageState.value.getT("Total Earned", "कुल कमाई", "ମୋଟ ଉପାର୍ଜନ"), "₹ ${(earningsData?.total ?: 0.0).toInt()}", Modifier.weight(1f))
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        val allItems = earningsData?.items ?: emptyList()
        // This month plus the six before it, newest first, as "2026-07" keys. Fixed
        // rather than derived from the data so a month with no earnings is still
        // selectable — that is itself an answer.
        val months = remember {
            val cal = java.util.Calendar.getInstance()
            (0..6).map {
                val key = "%04d-%02d".format(
                    cal.get(java.util.Calendar.YEAR), cal.get(java.util.Calendar.MONTH) + 1
                )
                cal.add(java.util.Calendar.MONTH, -1)
                key
            }
        }
        val items = monthFilter?.let { m -> allItems.filter { it.earnedOn?.take(7) == m } } ?: allItems

        // Heading and filter stay put; only the rows below them scroll.
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
                Text(languageState.value.getT("Earnings Breakdown", "आय विवरण", "ଉପାର୍ଜନ ବିବରଣୀ"), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color(0xFF14231A))
                Spacer(modifier = Modifier.weight(1f))
                Box {
                    Surface(
                        onClick = { showMonthMenu = true },
                        shape = RoundedCornerShape(20.dp),
                        color = Color.White,
                        border = BorderStroke(1.dp, Color(0xFFDDE3DA))
                    ) {
                        Row(
                            modifier = Modifier.padding(start = 12.dp, end = 8.dp, top = 7.dp, bottom = 7.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.CalendarToday, null, tint = PrimaryGreen, modifier = Modifier.size(15.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                monthFilter?.let { monthLabel(it) }
                                    ?: languageState.value.getT("All", "सभी", "ସମସ୍ତ"),
                                fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A)
                            )
                            Icon(Icons.Default.ArrowDropDown, null, tint = Color(0xFF5B6660), modifier = Modifier.size(18.dp))
                        }
                    }
                    DropdownMenu(expanded = showMonthMenu, onDismissRequest = { showMonthMenu = false }) {
                        DropdownMenuItem(
                            text = { Text(languageState.value.getT("All", "सभी", "ସମସ୍ତ")) },
                            onClick = { monthFilter = null; showMonthMenu = false }
                        )
                        months.forEach { m ->
                            DropdownMenuItem(
                                text = { Text(monthLabel(m)) },
                                onClick = { monthFilter = m; showMonthMenu = false }
                            )
                        }
                    }
                }
        }
        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            if (items.isEmpty()) {
                Text(
                    if (monthFilter == null) languageState.value.getT("No earnings yet.", "अभी तक कोई आय नहीं।", "ଏପର୍ଯ୍ୟନ୍ତ କୌଣସି ଉପାର୍ଜନ ନାହିଁ।")
                    else languageState.value.getT("No earnings this month.", "इस महीने कोई आय नहीं।", "ଏହି ମାସରେ କୌଣସି ଉପାର୍ଜନ ନାହିଁ।"),
                    color = Color.Gray, fontSize = 13.sp, modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            items.forEach { e ->
                val isVacc = e.source == "vaccination"
                EarningBreakdownRow(
                    icon = if (isVacc) Icons.Default.Vaccines else Icons.Default.Badge,
                    title = e.detail ?: (if (isVacc) "Vaccination" else "Field Services"),
                    subtitle = e.earTagNumber ?: "—",
                    amount = "₹ ${e.amount.toInt()}",
                    time = AppTime.date(e.earnedOn, fallback = ""),
                )
            }

            // Clears the bottom bar so the last row isn't trapped behind it.
            Spacer(modifier = Modifier.height(104.dp))
        }
    }
        Box(Modifier.align(Alignment.BottomCenter)) {
            DidiBottomBar(navController)
        }
    }
}

/** "2026-07" -> "Jul 2026". Falls back to the raw key if it isn't in that shape. */
private fun monthLabel(key: String): String {
    val parts = key.split("-")
    val month = parts.getOrNull(1)?.toIntOrNull() ?: return key
    val names = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun",
                       "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
    return "${names.getOrNull(month - 1) ?: return key} ${parts[0]}"
}

@Composable
private fun EarningHeroStat(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(value, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold, maxLines = 1)
        Text(label, color = Color.White.copy(alpha = 0.8f), fontSize = 11.sp, lineHeight = 13.sp)
    }
}

@Composable
private fun EarningBreakdownRow(icon: ImageVector, title: String, subtitle: String, amount: String, time: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        border = BorderStroke(1.dp, Color(0xFFEDF0EA))
    ) {
        Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(42.dp).clip(CircleShape).background(PrimaryGreen.copy(alpha = 0.12f)), contentAlignment = Alignment.Center) {
                Icon(icon, null, tint = PrimaryGreen, modifier = Modifier.size(22.dp))
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF14231A))
                Text(subtitle, fontSize = 12.sp, color = Color.Gray)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(amount, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = PrimaryGreen)
                if (time.isNotBlank()) Text(time, fontSize = 11.sp, color = Color.Gray)
            }
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

@Composable
fun PrivacyPolicyScreen(userRole: UserRole?, onBack: () -> Unit) {
    val lang = LocalAppLanguage.current.value
    val sections = privacyPolicySections(lang)

    Box(Modifier.fillMaxSize().background(Color(0xFFF3F4DD))) {
        // same faint farm scene used on the Profile and Help & Support headers
        Image(
            painter = painterResource(R.drawable.profile_banner),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter),
            contentScale = androidx.compose.ui.layout.ContentScale.FillWidth
        )
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            Box(Modifier.fillMaxWidth().statusBarsPadding().padding(vertical = 8.dp)) {
                IconButton(onClick = onBack, modifier = Modifier.align(Alignment.CenterStart).padding(start = 6.dp)) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF1B1B1B))
                }
                Text(
                    lang.getT("Privacy Policy", "गोपनीयता नीति", "ଗୋପନୀୟତା ନୀତି"),
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B1B1B)
                )
            }

            Spacer(Modifier.height(14.dp))

            Box(Modifier.fillMaxWidth()) {
                Column(
                    Modifier.fillMaxWidth()
                        .padding(top = 54.dp)
                        .clip(RoundedCornerShape(topStart = 46.dp, topEnd = 46.dp))
                        .background(Color(0xFFFCFAF6))
                        .navigationBarsPadding()
                ) {
                    Spacer(Modifier.height(64.dp))

                    // Intro - same treatment as the Terms & Conditions intro card
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFEDF4E9)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Box(Modifier.size(48.dp).clip(CircleShape).background(Color.White), contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.Lock, null, tint = PrimaryGreen, modifier = Modifier.size(26.dp))
                            }
                            Spacer(Modifier.width(14.dp))
                            Column {
                                Text(
                                    lang.getT("Your privacy matters to us", "आपकी गोपनीयता हमारे लिए महत्वपूर्ण है", "ଆପଣଙ୍କ ଗୋପନୀୟତା ଆମ ପାଇଁ ଗୁରୁତ୍ୱପୂର୍ଣ୍ଣ"),
                                    fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color(0xFF14231A)
                                )
                                Text(
                                    lang.getT(
                                        "At AjahFi, we value your privacy and are committed to protecting your personal information. This Privacy Policy explains how we collect, use, store, and protect your data.",
                                        "AjahFi में, हम आपकी गोपनीयता को महत्व देते हैं और आपकी व्यक्तिगत जानकारी की सुरक्षा के लिए प्रतिबद्ध हैं। यह गोपनीयता नीति बताती है कि हम आपका डेटा कैसे एकत्र, उपयोग, संग्रहीत और सुरक्षित करते हैं।",
                                        "AjahFi ରେ, ଆମେ ଆପଣଙ୍କ ଗୋପନୀୟତାକୁ ମୂଲ୍ୟ ଦେଉ ଏବଂ ଆପଣଙ୍କ ବ୍ୟକ୍ତିଗତ ସୂଚନା ସୁରକ୍ଷା ପାଇଁ ପ୍ରତିବଦ୍ଧ। ଏହି ଗୋପନୀୟତା ନୀତି ବର୍ଣ୍ଣନା କରେ ଯେ ଆମେ ଆପଣଙ୍କ ତଥ୍ୟ କିପରି ସଂଗ୍ରହ, ବ୍ୟବହାର, ସଂରକ୍ଷଣ ଏବଂ ସୁରକ୍ଷା କରୁ।",
                                    ),
                                    fontSize = 13.sp, color = Color(0xFF4A544C), lineHeight = 18.sp,
                                    textAlign = TextAlign.Justify
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(22.dp))

                    sections.forEachIndexed { index, section ->
                        PrivacySectionRow(index = index + 1, section = section)
                        if (index < sections.size - 1) {
                            HorizontalDivider(
                                color = Color(0xFFF1EEE7),
                                modifier = Modifier.padding(horizontal = 22.dp)
                            )
                        }
                    }

                    Spacer(Modifier.height(20.dp))

                    // Last updated
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.CalendarToday, null,
                            tint = Color(0xFF9AA09A), modifier = Modifier.size(15.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            lang.getT(
                                "Last updated: $PRIVACY_LAST_UPDATED_EN",
                                "अंतिम अद्यतन: $PRIVACY_LAST_UPDATED_HI",
                                "ଶେଷ ଅପଡେଟ୍: $PRIVACY_LAST_UPDATED_OR",
                            ),
                            fontSize = 13.sp, color = Color(0xFF7A8078)
                        )
                    }

                    Spacer(Modifier.height(28.dp))
                }

                // policy badge straddling the panel edge
                ScreenHeaderBadge {
                    Icon(
                        painter = painterResource(R.drawable.privacy_policy),
                        contentDescription = null,
                        tint = PrimaryGreen,
                        modifier = Modifier.size(44.dp)
                    )
                }
            }
        }
    }
}

// Bump these together whenever the policy copy above changes.
private const val PRIVACY_LAST_UPDATED_EN = "20 July 2026"
private const val PRIVACY_LAST_UPDATED_HI = "20 जुलाई 2026"
private const val PRIVACY_LAST_UPDATED_OR = "୨୦ ଜୁଲାଇ ୨୦୨୬"

/** One numbered clause of the Privacy Policy. */
private data class PrivacySection(val icon: ImageVector, val accent: Color, val title: String, val body: String)

private fun privacyPolicySections(lang: AppLanguage): List<PrivacySection> = listOf(
    PrivacySection(
        icon = Icons.Default.Person,
        accent = IconBlue,
        title = lang.getT("Information We Collect", "जानकारी जो हम एकत्र करते हैं", "ଆମେ ସଂଗ୍ରହ କରୁଥିବା ସୂଚନା"),
        body = lang.getT(
            "We collect personal information that you provide to us, such as your name, mobile number, role, and other details required for using our services.",
            "हम वह व्यक्तिगत जानकारी एकत्र करते हैं जो आप हमें देते हैं, जैसे आपका नाम, मोबाइल नंबर, भूमिका और हमारी सेवाओं के उपयोग के लिए आवश्यक अन्य विवरण।",
            "ଆପଣ ଆମକୁ ଦେଉଥିବା ବ୍ୟକ୍ତିଗତ ସୂଚନା ଆମେ ସଂଗ୍ରହ କରୁ, ଯେପରି ଆପଣଙ୍କ ନାମ, ମୋବାଇଲ୍ ନମ୍ବର, ଭୂମିକା ଏବଂ ଆମ ସେବା ବ୍ୟବହାର ପାଇଁ ଆବଶ୍ୟକ ଅନ୍ୟ ବିବରଣୀ।",
        ),
    ),
    PrivacySection(
        icon = Icons.Default.Public,
        accent = IconTeal,
        title = lang.getT("How We Use Your Information", "हम आपकी जानकारी का उपयोग कैसे करते हैं", "ଆମେ ଆପଣଙ୍କ ସୂଚନା କିପରି ବ୍ୟବହାର କରୁ"),
        body = lang.getT(
            "We use your information to provide and improve our services, process your requests, communicate with you, and ensure the security of our platform.",
            "हम आपकी जानकारी का उपयोग अपनी सेवाएँ प्रदान करने और बेहतर बनाने, आपके अनुरोध संसाधित करने, आपसे संवाद करने और अपने प्लेटफ़ॉर्म की सुरक्षा सुनिश्चित करने के लिए करते हैं।",
            "ଆମେ ଆପଣଙ୍କ ସୂଚନାକୁ ଆମର ସେବା ପ୍ରଦାନ ଓ ଉନ୍ନତ କରିବା, ଆପଣଙ୍କ ଅନୁରୋଧ ପ୍ରକ୍ରିୟାକରଣ, ଆପଣଙ୍କ ସହ ଯୋଗାଯୋଗ ଏବଂ ଆମ ପ୍ଲାଟଫର୍ମର ସୁରକ୍ଷା ନିଶ୍ଚିତ କରିବା ପାଇଁ ବ୍ୟବହାର କରୁ।",
        ),
    ),
    PrivacySection(
        icon = Icons.Default.Security,
        accent = IconGreen,
        title = lang.getT("Data Protection", "डेटा सुरक्षा", "ତଥ୍ୟ ସୁରକ୍ଷା"),
        body = lang.getT(
            "We implement appropriate technical and organizational measures to protect your personal data from unauthorized access, use, or disclosure.",
            "आपके व्यक्तिगत डेटा को अनधिकृत पहुँच, उपयोग या प्रकटीकरण से बचाने के लिए हम उपयुक्त तकनीकी और संगठनात्मक उपाय अपनाते हैं।",
            "ଆପଣଙ୍କ ବ୍ୟକ୍ତିଗତ ତଥ୍ୟକୁ ଅନଧିକୃତ ପ୍ରବେଶ, ବ୍ୟବହାର କିମ୍ବା ପ୍ରକାଶରୁ ରକ୍ଷା କରିବାକୁ ଆମେ ଉପଯୁକ୍ତ ବୈଷୟିକ ଓ ସାଂଗଠନିକ ବ୍ୟବସ୍ଥା ଗ୍ରହଣ କରୁ।",
        ),
    ),
    PrivacySection(
        icon = Icons.Default.Groups,
        accent = IconPurple,
        title = lang.getT("Information Sharing", "जानकारी साझा करना", "ସୂଚନା ଅଂଶୀଦାର"),
        body = lang.getT(
            "We do not sell or rent your personal information. We may share your data only with trusted service providers who assist us in operating our app, under strict confidentiality agreements.",
            "हम आपकी व्यक्तिगत जानकारी न बेचते हैं न किराए पर देते हैं। हम आपका डेटा केवल उन विश्वसनीय सेवा प्रदाताओं के साथ साझा कर सकते हैं जो सख्त गोपनीयता समझौतों के तहत हमारे ऐप के संचालन में सहायता करते हैं।",
            "ଆମେ ଆପଣଙ୍କ ବ୍ୟକ୍ତିଗତ ସୂଚନା ବିକ୍ରି କିମ୍ବା ଭଡ଼ାରେ ଦେଉନାହୁଁ। କଡ଼ା ଗୋପନୀୟତା ଚୁକ୍ତି ଅଧୀନରେ ଆମ ଆପ୍ ପରିଚାଳନାରେ ସହାୟତା କରୁଥିବା ବିଶ୍ୱସ୍ତ ସେବା ପ୍ରଦାନକାରୀଙ୍କ ସହ ହିଁ ଆମେ ଆପଣଙ୍କ ତଥ୍ୟ ଅଂଶୀଦାର କରିପାରୁ।",
        ),
    ),
    PrivacySection(
        icon = Icons.Default.CalendarToday,
        accent = IconAmber,
        title = lang.getT("Data Retention", "डेटा प्रतिधारण", "ତଥ୍ୟ ସଂରକ୍ଷଣ"),
        body = lang.getT(
            "We retain your information only for as long as necessary to fulfill the purposes outlined in this policy or as required by law.",
            "हम आपकी जानकारी केवल तब तक रखते हैं जब तक इस नीति में बताए गए उद्देश्यों को पूरा करने या कानून द्वारा आवश्यक हो।",
            "ଏହି ନୀତିରେ ଦର୍ଶାଯାଇଥିବା ଉଦ୍ଦେଶ୍ୟ ପୂରଣ ପାଇଁ କିମ୍ବା ଆଇନ ଅନୁଯାୟୀ ଆବଶ୍ୟକ ସମୟ ପର୍ଯ୍ୟନ୍ତ ହିଁ ଆମେ ଆପଣଙ୍କ ସୂଚନା ରଖୁ।",
        ),
    ),
    PrivacySection(
        icon = Icons.Default.HowToReg,
        accent = IconRose,
        title = lang.getT("Your Rights", "आपके अधिकार", "ଆପଣଙ୍କ ଅଧିକାର"),
        body = lang.getT(
            "You have the right to access, update, or delete your personal information. You may contact us anytime for assistance regarding your data.",
            "आपको अपनी व्यक्तिगत जानकारी देखने, अपडेट करने या हटाने का अधिकार है। अपने डेटा से जुड़ी सहायता के लिए आप कभी भी हमसे संपर्क कर सकते हैं।",
            "ଆପଣଙ୍କ ବ୍ୟକ୍ତିଗତ ସୂଚନା ଦେଖିବା, ଅପଡେଟ୍ କରିବା କିମ୍ବା ଲିଭାଇବାର ଅଧିକାର ଆପଣଙ୍କର ଅଛି। ଆପଣଙ୍କ ତଥ୍ୟ ସମ୍ବନ୍ଧୀୟ ସହାୟତା ପାଇଁ ଆପଣ ଯେକୌଣସି ସମୟରେ ଆମ ସହ ଯୋଗାଯୋଗ କରିପାରିବେ।",
        ),
    ),
    PrivacySection(
        icon = Icons.Default.NoteAlt,
        accent = IconIndigo,
        title = lang.getT("Changes to This Policy", "इस नीति में बदलाव", "ଏହି ନୀତିରେ ପରିବର୍ତ୍ତନ"),
        body = lang.getT(
            "We may update this Privacy Policy from time to time. Any changes will be posted on this page with the updated date.",
            "हम समय-समय पर इस गोपनीयता नीति को अपडेट कर सकते हैं। कोई भी बदलाव अद्यतन तिथि के साथ इसी पृष्ठ पर प्रकाशित किया जाएगा।",
            "ଆମେ ସମୟ ସମୟରେ ଏହି ଗୋପନୀୟତା ନୀତି ଅପଡେଟ୍ କରିପାରୁ। ଯେକୌଣସି ପରିବର୍ତ୍ତନ ଅପଡେଟ୍ ତାରିଖ ସହ ଏହି ପୃଷ୍ଠାରେ ପ୍ରକାଶିତ ହେବ।",
        ),
    ),
)

@Composable
private fun PrivacySectionRow(index: Int, section: PrivacySection) {
    Row(
        Modifier.fillMaxWidth().padding(horizontal = 22.dp, vertical = 14.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            Modifier.size(40.dp).clip(CircleShape).background(section.accent.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(section.icon, null, tint = section.accent, modifier = Modifier.size(20.dp))
        }
        Spacer(Modifier.width(14.dp))
        Column(Modifier.weight(1f)) {
            Text(
                "$index. ${section.title}",
                fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                section.body,
                fontSize = 14.sp, color = Color(0xFF5B6660), lineHeight = 20.sp,
                textAlign = TextAlign.Justify, modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsOfServiceScreen(userRole: UserRole?, onBack: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val lang = languageState.value
    val sections = termsOfServiceSections(lang)
    val softGreen = Color(0xFFEDF4E9)

    Box(Modifier.fillMaxSize().background(Color(0xFFF3F4DD))) {
        Image(
            painter = painterResource(R.drawable.profile_banner),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter),
            contentScale = androidx.compose.ui.layout.ContentScale.FillWidth
        )
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            Box(Modifier.fillMaxWidth().statusBarsPadding().padding(vertical = 8.dp)) {
                IconButton(onClick = onBack, modifier = Modifier.align(Alignment.CenterStart).padding(start = 6.dp)) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF14231A))
                }
                Text(
                    lang.getT("Terms & Conditions", "नियम और शर्तें", "ନିୟମ ଓ ସର୍ତ୍ତାବଳୀ"),
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A)
                )
            }

            Spacer(Modifier.height(14.dp))

            Box(Modifier.fillMaxWidth()) {
                Column(
                    Modifier.fillMaxWidth()
                        .padding(top = 54.dp)
                        .clip(RoundedCornerShape(topStart = 46.dp, topEnd = 46.dp))
                        .background(Color(0xFFFCFAF6))
                        .navigationBarsPadding()
                ) {
                    Column(Modifier.fillMaxWidth().padding(horizontal = 18.dp)) {
                        Spacer(Modifier.height(64.dp))

                        // Warning Box
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(containerColor = softGreen),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                        ) {
                            Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                                Box(Modifier.size(48.dp).clip(CircleShape).background(Color.White), contentAlignment = Alignment.Center) {
                                    Icon(Icons.Default.Security, null, tint = PrimaryGreen, modifier = Modifier.size(26.dp))
                                }
                                Spacer(Modifier.width(14.dp))
                                Column {
                                    Text(lang.getT("Please read these terms carefully", "कृपया इन शर्तों को ध्यान से पढ़ें", "ଦୟାକରି ଏହି ସର୍ତ୍ତାବଳୀକୁ ଧ୍ୟାନର ସହିତ ପଢନ୍ତୁ"), fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color(0xFF14231A))
                                    Text(lang.getT("By using AjahFi, you agree to comply with and be bound by the following terms and conditions.", "AjahFi का उपयोग करके, आप निम्नलिखित नियमों और शर्तों का पालन करने और उनसे बंधे रहने के लिए सहमत हैं।", "AjahFi ବ୍ୟବହାର କରି, ଆପଣ ନିମ୍ନଲିଖିତ ନିୟମ ଓ ସର୍ତ୍ତାବଳୀ ଅନୁଯାୟୀ କାର୍ଯ୍ୟ କରିବାକୁ ସମ୍ମତ ହୁଅନ୍ତି।"), fontSize = 13.sp, color = Color(0xFF4A544C), lineHeight = 18.sp)
                                }
                            }
                        }

                        Spacer(Modifier.height(24.dp))

                        sections.forEachIndexed { index, section ->
                            TermsSectionRow(index = index + 1, section = section)
                            if (index < sections.size - 1) {
                                HorizontalDivider(color = Color(0xFFF1EEE7), modifier = Modifier.padding(vertical = 12.dp))
                            }
                        }

                        Spacer(Modifier.height(24.dp))

                        // Last Updated Box
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = softGreen),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                        ) {
                            Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.CalendarToday, null, tint = PrimaryGreen, modifier = Modifier.size(20.dp))
                                Spacer(Modifier.width(12.dp))
                                Column {
                                    Text(lang.getT("Last Updated: 20 July 2026", "अंतिम अद्यतन: 20 जुलाई 2026", "ଶେଷ ଅପଡେଟ୍: ୨୦ ଜୁଲାଇ ୨୦୨୬"), fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF14231A))
                                    Text(lang.getT("We recommend reviewing these terms periodically.", "हम इन शर्तों की समय-समय पर समीक्षा करने की सलाह देते हैं।", "ଆମେ ସମୟ ସମୟରେ ଏହି ସର୍ତ୍ତାବଳୀର ସମୀକ୍ଷା କରିବାକୁ ପରାମର୍ଶ ଦେଉ।"), fontSize = 12.sp, color = Color(0xFF5B6660))
                                }
                            }
                        }

                        Spacer(Modifier.height(14.dp))
                    }

                    Button(
                        onClick = onBack,
                        // no top padding: the spacer above already sets the gap
                        modifier = Modifier.fillMaxWidth()
                            .padding(start = 18.dp, end = 18.dp, bottom = 18.dp)
                            .height(56.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20))
                    ) {
                        Text(lang.getT("I Agree", "मैं सहमत हूँ", "ମୁଁ ସମ୍ମତ"), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                    }
                }

                ScreenHeaderBadge {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Assignment,
                        contentDescription = null,
                        tint = PrimaryGreen,
                        modifier = Modifier.size(44.dp)
                    )
                }
            }
        }
    }
}

private data class TermsSection(val icon: ImageVector, val accent: Color, val title: String, val body: String)

private fun termsOfServiceSections(lang: AppLanguage): List<TermsSection> = listOf(
    TermsSection(
        icon = Icons.Default.Groups,
        accent = IconBlue,
        title = lang.getT("Acceptance of Terms", "शर्तों की स्वीकृति", "ସର୍ତ୍ତାବଳୀର ଗ୍ରହଣ"),
        body = lang.getT(
            "By accessing or using the AjahFi app, you agree to be bound by these Terms & Conditions and our Privacy Policy. If you do not agree, please do not use the app.",
            "AjahFi ऐप का उपयोग करके, आप इन नियमों और शर्तों और हमारी गोपनीयता नीति से बंधे रहने के लिए सहमत हैं। यदि आप सहमत नहीं हैं, तो कृपया ऐप का उपयोग न करें।",
            "AjahFi ଆପ୍ ବ୍ୟବହାର କରି, ଆପଣ ଏହି ନିୟମ ଓ ସର୍ତ୍ତାବଳୀ ଏବଂ ଆମର ଗୋପନୀୟତା ନୀତି ଦ୍ୱାରା ବାଧ୍ୟ ହେବାକୁ ସମ୍ମତ ହୁଅନ୍ତି। ଯଦି ଆପଣ ସମ୍ମତ ନୁହଁନ୍ତି, ଦୟାକରି ଆପ୍ ବ୍ୟବହାର କରନ୍ତୁ ନାହିଁ।",
        ),
    ),
    TermsSection(
        icon = Icons.Default.Phone,
        accent = IconTeal,
        title = lang.getT("Use of the App", "ऐप का उपयोग", "ଆପ୍ ର ବ୍ୟବହାର"),
        body = lang.getT(
            "AjahFi is intended to help users manage and insure their goats. You agree to use the app only for lawful purposes and in accordance with these terms.",
            "AjahFi का उद्देश्य उपयोगकर्ताओं को उनकी बकरियों का प्रबंधन और बीमा करने में मदद करना है। आप केवल वैध उद्देश्यों के लिए और इन शर्तों के अनुसार ऐप का उपयोग करने के लिए सहमत हैं।",
            "AjahFi ର ଉଦ୍ଦେଶ୍ୟ ହେଉଛି ବ୍ୟବହାରକାରୀଙ୍କୁ ସେମାନଙ୍କ ଛେଳିର ପରିଚାଳନା ଏବଂ ବୀମା କରିବାରେ ସାହାଯ୍ୟ କରିବା। ଆପଣ କେବଳ ବୈଧ ଉଦ୍ଦେଶ୍ୟରେ ଏବଂ ଏହି ସର୍ତ୍ତାବଳୀ ଅନୁଯାୟୀ ଆପ୍ ବ୍ୟବହାର କରିବାକୁ ସମ୍ମତ ହୁଅନ୍ତି।",
        ),
    ),
    TermsSection(
        icon = Icons.Default.Person,
        accent = IconPurple,
        title = lang.getT("User Responsibilities", "उपयोगकर्ता की जिम्मेदारियां", "ବ୍ୟବହାରକାରୀଙ୍କ ଦାୟିତ୍ୱ"),
        body = lang.getT(
            "You agree to provide accurate information and keep your account secure. You are responsible for all activities under your account.",
            "आप सटीक जानकारी प्रदान करने और अपने खाते को सुरक्षित रखने के लिए सहमत हैं। आप अपने खाते के तहत सभी गतिविधियों के लिए जिम्मेदार हैं।",
            "ଆପଣ ସଠିକ୍ ସୂଚନା ପ୍ରଦାନ କରିବାକୁ ଏବଂ ଆପଣଙ୍କ ଆକାଉଣ୍ଟକୁ ସୁରକ୍ଷିତ ରଖିବାକୁ ସମ୍ମତ ହୁଅନ୍ତି। ଆପଣଙ୍କ ଆକାଉଣ୍ଟ ଅଧୀନରେ ଥିବା ସମସ୍ତ କାର୍ଯ୍ୟକଳାପ ପାଇଁ ଆପଣ ଦାୟୀ।",
        ),
    ),
    TermsSection(
        icon = Icons.Default.Lock,
        accent = IconGreen,
        title = lang.getT("Data & Privacy", "डेटा और गोपनीयता", "ତଥ୍ୟ ଓ ଗୋପନୀୟତା"),
        body = lang.getT(
            "Your privacy is important to us. Please review our Privacy Policy to understand how we collect, use, and protect your information.",
            "आपकी गोपनीयता हमारे लिए महत्वपूर्ण है। कृपया यह समझने के लिए हमारी गोपनीयता नीति की समीक्षा करें कि हम आपकी जानकारी कैसे एकत्र करते हैं, उपयोग करते हैं और सुरक्षित रखते हैं।",
            "ଆପଣଙ୍କ ଗୋପନୀୟତା ଆମ ପାଇଁ ଗୁରୁତ୍ୱପୂର୍ଣ୍ଣ। ଆମେ କିପରି ଆପଣଙ୍କ ସୂଚନା ସଂଗ୍ରହ, ବ୍ୟବହାର ଏବଂ ସୁରକ୍ଷିତ କରୁ ତାହା ବୁଝିବା ପାଇଁ ଦୟାକରି ଆମର ଗୋପନୀୟତା ନୀତି ସମୀକ୍ଷା କରନ୍ତୁ।",
        ),
    ),
    TermsSection(
        icon = Icons.Default.Error,
        accent = IconRose,
        title = lang.getT("Limitation of Liability", "दायित्व की सीमा", "ଦାୟିତ୍ୱର ସୀମା"),
        body = lang.getT(
            "AjahFi is not liable for any indirect, incidental, or consequential damages resulting from the use of the app.",
            "AjahFi ऐप के उपयोग के परिणामस्वरूप किसी भी अप्रत्यक्ष, आकस्मिक या परिणामी नुकसान के लिए उत्तरदायी नहीं है।",
            "ଆପ୍ ର ବ୍ୟବହାର ଯୋଗୁଁ ହେଉଥିବା କୌଣସି ପରୋକ୍ଷ, ଆନୁସଙ୍ଗିକ କିମ୍ବା ପରିଣାମସ୍ୱରୂପ କ୍ଷତି ପାଇଁ AjahFi ଦାୟୀ ନୁହେଁ।",
        ),
    ),
    TermsSection(
        icon = Icons.Default.History,
        accent = IconIndigo,
        title = lang.getT("Changes to Terms", "शर्तों में बदलाव", "ସର୍ତ୍ତାବଳୀରେ ପରିବର୍ତ୍ତନ"),
        body = lang.getT(
            "We may update these Terms & Conditions from time to time. Continued use of the app after changes means you accept the revised terms.",
            "हम समय-समय पर इन नियमों और शर्तों को अपडेट कर सकते हैं। बदलावों के बाद ऐप का निरंतर उपयोग करने का मतलब है कि आप संशोधित शर्तों को स्वीकार करते हैं।",
            "ଆମେ ସମୟ ସମୟରେ ଏହି ନିୟମ ଓ ସର୍ତ୍ତାବଳୀକୁ ଅପଡେଟ୍ କରିପାରୁ। ପରିବର୍ତ୍ତନ ପରେ ଆପ୍ ର ନିରନ୍ତର ବ୍ୟବହାରର ଅର୍ଥ ହେଉଛି ଆପଣ ସଂଶୋଧିତ ସର୍ତ୍ତାବଳୀକୁ ଗ୍ରହଣ କରନ୍ତି।",
        ),
    ),
)

@Composable
private fun TermsSectionRow(index: Int, section: TermsSection) {
    Row(
        Modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            Modifier.size(40.dp).clip(CircleShape).background(section.accent.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(section.icon, null, tint = section.accent, modifier = Modifier.size(20.dp))
        }
        Spacer(Modifier.width(14.dp))
        Column(Modifier.weight(1f)) {
            Text(
                "$index. ${section.title}",
                fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                section.body,
                fontSize = 14.sp, color = Color(0xFF5B6660), lineHeight = 20.sp,
                textAlign = TextAlign.Justify
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen(
    navController: NavHostController,
    userRole: UserRole?,
    onBack: () -> Unit
) {
    val languageState = LocalAppLanguage.current
    val lang = languageState.value
    val context = LocalContext.current
    val profileImageState = LocalProfileImage.current

    val profileVm: ProfileViewModel = hiltViewModel()
    val dbProfile by profileVm.profile.collectAsState()
    val passwordState by profileVm.password.collectAsState()

    val userName = dbProfile?.fullName ?: ""
    val userMobile = dbProfile?.mobileNumber ?: ""

    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var currentVisible by remember { mutableStateOf(false) }
    var newVisible by remember { mutableStateOf(false) }
    var confirmVisible by remember { mutableStateOf(false) }

    val busy = passwordState is SubmitState.Submitting

    LaunchedEffect(passwordState) {
        when (val s = passwordState) {
            is SubmitState.Success -> {
                Toast.makeText(context, lang.getT("Password updated", "पासवर्ड अपडेट हुआ", "ପାସୱାର୍ଡ ଅପଡେଟ୍ ହୋଇଛି"), Toast.LENGTH_SHORT).show()
                profileVm.resetPassword()
                onBack()
            }
            is SubmitState.Error -> {
                Toast.makeText(context, s.message, Toast.LENGTH_LONG).show()
                profileVm.resetPassword()
            }
            else -> {}
        }
    }

    val themeColor = when (userRole) {
        UserRole.FARMER -> PrimaryBlue
        UserRole.COORDINATOR -> CoordinatorOrange
        else -> PrimaryGreen
    }

    Box(Modifier.fillMaxSize().background(Color(0xFFF3F4DD))) {
        Image(
            painter = painterResource(R.drawable.profile_banner),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter),
            contentScale = androidx.compose.ui.layout.ContentScale.FillWidth
        )
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            Box(Modifier.fillMaxWidth().statusBarsPadding().padding(vertical = 8.dp)) {
                IconButton(onClick = onBack, modifier = Modifier.align(Alignment.CenterStart).padding(start = 6.dp)) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF14231A))
                }
                Text(
                    lang.getT("Change Password", "पासवर्ड बदलें", "ପାସୱାର୍ଡ ବଦଳାନ୍ତୁ"),
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A)
                )
            }

            Spacer(Modifier.height(14.dp))

            Box(Modifier.fillMaxWidth()) {
                Column(
                    Modifier.fillMaxWidth()
                        .padding(top = 64.dp)
                        .clip(RoundedCornerShape(topStart = 46.dp, topEnd = 46.dp))
                        .background(Color(0xFFFCFAF6))
                        .navigationBarsPadding()
                ) {
                    Column(Modifier.fillMaxWidth().padding(horizontal = 24.dp)) {
                        Spacer(Modifier.height(74.dp))
                        Text(userName, fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                        Spacer(Modifier.height(4.dp))
                        Text(userMobile, fontSize = 16.sp, color = Color(0xFF8A908A), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)

                        Spacer(Modifier.height(22.dp))

                        // Current Password
                        Text(lang.getT("Current Password", "वर्तमान पासवर्ड", "ବର୍ତ୍ତମାନର ପାସୱାର୍ଡ"), fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A))
                        Spacer(Modifier.height(8.dp))
                        PasswordField(
                            value = currentPassword,
                            onValueChange = { currentPassword = it },
                            placeholder = lang.getT("Enter current password", "वर्तमान पासवर्ड दर्ज करें", "ବର୍ତ୍ତମାନର ପାସୱାର୍ଡ ଲେଖନ୍ତୁ"),
                            isVisible = currentVisible,
                            onToggleVisible = { currentVisible = !currentVisible },
                            themeColor = themeColor,
                            enabled = !busy
                        )

                        Spacer(Modifier.height(16.dp))

                        // New Password
                        Text(lang.getT("New Password", "नया पासवर्ड", "ନୂଆ ପାସୱାର୍ଡ"), fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A))
                        Spacer(Modifier.height(8.dp))
                        PasswordField(
                            value = newPassword,
                            onValueChange = { newPassword = it },
                            placeholder = lang.getT("Enter new password", "नया पासवर्ड दर्ज करें", "ନୂଆ ପାସୱାର୍ଡ ଲେଖନ୍ତୁ"),
                            isVisible = newVisible,
                            onToggleVisible = { newVisible = !newVisible },
                            themeColor = themeColor,
                            enabled = !busy
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            lang.getT(
                                "Password must be at least 6 characters long and include a combination of letters, numbers & symbols.",
                                "पासवर्ड कम से कम 6 अक्षर लंबा होना चाहिए और इसमें अक्षरों, संख्याओं और प्रतीकों का संयोजन होना चाहिए।",
                                "ପାସୱାର୍ଡ ଅତି କମରେ ୬ଟି ଅକ୍ଷର ବିଶିଷ୍ଟ ହେବା ଉଚିତ ଏବଂ ଏଥିରେ ଅକ୍ଷର, ସଂଖ୍ୟା ଏବଂ ପ୍ରତୀକର ମିଶ୍ରଣ ରହିବା ଉଚିତ।"
                            ),
                            fontSize = 12.sp, color = Color(0xFF8A908A), lineHeight = 18.sp
                        )

                        Spacer(Modifier.height(16.dp))

                        // Confirm New Password
                        Text(lang.getT("Confirm New Password", "नए पासवर्ड की पुष्टि करें", "ନୂଆ ପାସୱାର୍ଡ ନିଶ୍ଚିତ କରନ୍ତୁ"), fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF14231A))
                        Spacer(Modifier.height(8.dp))
                        PasswordField(
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            placeholder = lang.getT("Re-enter new password", "नया पासवर्ड फिर से दर्ज करें", "ନୂଆ ପାସୱାର୍ଡ ପୁଣି ଲେଖନ୍ତୁ"),
                            isVisible = confirmVisible,
                            onToggleVisible = { confirmVisible = !confirmVisible },
                            themeColor = themeColor,
                            enabled = !busy
                        )

                        Spacer(Modifier.height(24.dp))

                        Button(
                            onClick = {
                                if (newPassword != confirmPassword) {
                                    Toast.makeText(context, lang.getT("Passwords do not match", "पासवर्ड मेल नहीं खाते", "ପାସୱାର୍ଡ ମେଳ ଖାଉନାହିଁ"), Toast.LENGTH_SHORT).show()
                                } else if (newPassword.length < 6) {
                                    Toast.makeText(context, lang.getT("Password too short", "पासवर्ड बहुत छोटा है", "ପାସୱାର୍ଡ ବହୁତ ଛୋଟ"), Toast.LENGTH_SHORT).show()
                                } else {
                                    profileVm.changePassword(newPassword, currentPassword)
                                }
                            },
                            modifier = Modifier.fillMaxWidth().height(56.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20)),
                            enabled = !busy && currentPassword.isNotBlank() && newPassword.isNotBlank() && confirmPassword.isNotBlank()
                        ) {
                            Text(
                                if (busy) lang.getT("Updating...", "अपडेट किया जा रहा है...", "ଅପଡେଟ୍ କରାଯାଉଛି...")
                                else lang.getT("Update Password", "पासवर्ड अपडेट करें", "ପାସୱାର୍ଡ ଅପଡେଟ୍ କରନ୍ତୁ"),
                                fontWeight = FontWeight.Bold, fontSize = 17.sp, color = Color.White
                            )
                        }

                        Spacer(Modifier.height(16.dp))

                        // Security Card
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F7F0)),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                        ) {
                            Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                                Box(Modifier.size(40.dp).clip(CircleShape).background(Color.White), contentAlignment = Alignment.Center) {
                                    Icon(Icons.Default.Security, null, tint = PrimaryGreen, modifier = Modifier.size(24.dp))
                                }
                                Spacer(Modifier.width(14.dp))
                                Column {
                                    Text(lang.getT("Keep your account secure", "अपने खाते को सुरक्षित रखें", "ଆପଣଙ୍କ ଆକାଉଣ୍ଟକୁ ସୁରକ୍ଷିତ ରଖନ୍ତୁ"), fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF1B5E20))
                                    Text(lang.getT("Choose a strong password that you don't use on other websites.", "एक मजबूत पासवर्ड चुनें जिसे आप अन्य वेबसाइटों पर उपयोग नहीं करते हैं।", "ଏକ ଶକ୍ତିଶାଳୀ ପାସୱାର୍ଡ ବାଛନ୍ତୁ ଯାହାକୁ ଆପଣ ଅନ୍ୟ ୱେବସାଇଟ୍‌ରେ ବ୍ୟବହାର କରନ୍ତି ନାହିଁ।"), fontSize = 12.sp, color = Color(0xFF5B6660), lineHeight = 18.sp)
                                }
                            }
                        }
                        Spacer(Modifier.height(24.dp))
                    }
                }

                // Profile photo, drawn the same way as on the Profile page: a white
                // circle with a soft shadow, no outline. Not editable from here.
                Surface(
                    modifier = Modifier.align(Alignment.TopCenter).size(128.dp),
                    shape = CircleShape,
                    color = Color.White,
                    shadowElevation = 4.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        // The API returns a relative path ("/uploads/x.jpg"); Coil needs an absolute URL.
                        val remotePhoto = dbProfile?.photo?.takeIf { it.isNotBlank() }?.let {
                            if (it.startsWith("http")) it
                            else BuildConfig.BASE_URL.trimEnd('/') + "/" + it.trimStart('/')
                        }
                        when {
                            profileImageState.value != null -> AsyncImage(
                                model = profileImageState.value, contentDescription = null,
                                modifier = Modifier.fillMaxSize().padding(5.dp).clip(CircleShape),
                                contentScale = androidx.compose.ui.layout.ContentScale.Crop
                            )
                            remotePhoto != null -> AsyncImage(
                                model = remotePhoto, contentDescription = null,
                                modifier = Modifier.fillMaxSize().padding(5.dp).clip(CircleShape),
                                contentScale = androidx.compose.ui.layout.ContentScale.Crop
                            )
                            else -> Icon(Icons.Default.Person, null, tint = Color(0xFFBDBDBD), modifier = Modifier.size(64.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isVisible: Boolean,
    onToggleVisible: () -> Unit,
    themeColor: Color,
    enabled: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(placeholder, color = Color.Gray) },
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        enabled = enabled,
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = { Icon(Icons.Default.Lock, null, tint = Color(0xFF1B5E20)) },
        trailingIcon = {
            IconButton(onClick = onToggleVisible) {
                Icon(if (isVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff, null, tint = Color.Gray)
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
            focusedBorderColor = themeColor
        )
    )
}




