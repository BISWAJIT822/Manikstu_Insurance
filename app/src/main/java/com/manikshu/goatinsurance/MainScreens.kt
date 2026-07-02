package com.manikshu.goatinsurance


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pets
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
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat


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
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import kotlinx.coroutines.launch

val LocalWindowSizeClass = staticCompositionLocalOf<WindowSizeClass?> { null }
val LocalAppLanguage = compositionLocalOf { mutableStateOf(AppLanguage.ENGLISH) }
val LocalProfileImage = compositionLocalOf { mutableStateOf<Uri?>(null) }
val LocalNotificationsEnabled = compositionLocalOf { mutableStateOf(true) }
private val PrimaryBlue = Color(0xFF1976D2)

@Composable
fun AppNavigation(navController: NavHostController, sessionManager: SessionManager) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val userRole by sessionManager.userRole.collectAsState(initial = null)
    
    var showExitDialog by remember { mutableStateOf(false) }

    // Exit Confirmation Dialog
    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = { Text(text = "Exit App") },
            text = { Text(text = "Do you want to exit?") },
            confirmButton = {
                TextButton(onClick = {
                    (context as? android.app.Activity)?.finish()
                }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = { showExitDialog = false }) {
                    Text("No")
                }
            }
        )
    }

    // Intercept back button on dashboards
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val isDashboard = currentRoute in listOf("didi_dashboard", "farmer_dashboard", "coordinator_dashboard")
    
    androidx.activity.compose.BackHandler(enabled = isDashboard) {
        showExitDialog = true
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
        composable("mortality_report") { MortalityReportScreen(onComplete = { navController.popBackStack() }) }
        composable("farmer_report_death") { FarmerReportDeathScreen(onBack = { navController.popBackStack() }, onComplete = { navController.popBackStack() }) }
        composable("claim_tracker") { ClaimStatusTracker() }
        composable("claim_list") { ClaimListScreen(navController = navController, onBack = { navController.popBackStack() }) }
        composable("claim_review/{claimId}") { backStackEntry ->
            val claimId = backStackEntry.arguments?.getString("claimId") ?: ""
            ClaimReviewScreen(navController = navController, claimId = claimId, onBack = { navController.popBackStack() })
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
        composable("profile") { 
            ProfileScreen(
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
                .height(320.dp)
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
                    Text(languageState.value.code, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                }

                MaterialTheme(
                    colorScheme = MaterialTheme.colorScheme.copy(surface = Color.White.copy(alpha = 0.9f))
                ) {
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

            // Logo and Title
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(modifier = Modifier.size(80.dp), shape = CircleShape, color = Color.White.copy(alpha = 0.2f)) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Pets, contentDescription = null, tint = Color.White, modifier = Modifier.size(40.dp))
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    languageState.value.getT("GOAT INSURANCE", "बकरी बीमा", "ଛେଳି ବୀମା"),
                    style = MaterialTheme.typography.headlineSmall, color = Color.White, fontWeight = FontWeight.Bold, letterSpacing = 1.sp
                )
                Text(
                    languageState.value.getT("Community Livestock Protection", "सामुदायिक पशुधन संरक्षण", "ଗୋଷ୍ଠୀ ପ୍ରାଣୀସମ୍ପଦ ସୁରକ୍ଷା"),
                    style = MaterialTheme.typography.bodySmall, color = Color.White.copy(alpha = 0.8f)
                )
            }
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
                    languageState.value.getT("Create Account", "खाता बनाएं", "ଖାତା ଖୋଲନ୍ତୁ"),
                    style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold
                )
                Text(
                    languageState.value.getT("Register to start protecting livestock", "पशुधन की सुरक्षा शुरू करने के लिए पंजीकरण करें", "ପ୍ରାଣୀସମ୍ପଦ ସୁରକ୍ଷା ଆରମ୍ଭ କରିବାକୁ ପଞ୍ଜିକରଣ କରନ୍ତୁ"),
                    style = MaterialTheme.typography.bodyMedium, color = Color.Gray, textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(32.dp))

                if (step == 1) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { if (it.all { char -> char.isLetter() || char.isWhitespace() }) name = it },
                        placeholder = { Text(languageState.value.getT("Full Name", "पूरा नाम", "ପୁରା ନାମ"), color = Color.Gray) },
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
                        placeholder = { Text(languageState.value.getT("Mobile Number", "मोबाइल नंबर", "ମୋବାଇଲ୍ ନମ୍ବର"), color = Color.Gray) },
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
                                Toast.makeText(context, languageState.value.getT("Scroll down and choose a role", "नीचे स्क्रॉल करें और एक भूमिका चुनें", "ତଳକୁ ସ୍କ୍ରୋଲ୍ କରନ୍ତୁ ଏବଂ ଏକ ଭୂମିକା ବାଛନ୍ତୁ"), Toast.LENGTH_SHORT).show()
                            } else {
                                step = 2 
                            }
                        },
                        enabled = (name.isNotBlank() && phone.length == 10),
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen, disabledContainerColor = PrimaryGreen.copy(alpha = 0.5f))
                    ) {
                        Text(languageState.value.getT("Send OTP", "ओटीपी भेजें", "ଓଟିପି ପଠାନ୍ତୁ"), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = languageState.value.getT("Already have an account? Login", "पहले से खाता है? लॉगिन करें", "ପୂର୍ବରୁ ଖାତା ଅଛି? ଲଗଇନ୍"),
                        color = PrimaryGreen, fontWeight = FontWeight.Bold, fontSize = 14.sp,
                        modifier = Modifier.clickable { onNavigateToLogin() }
                    )
                } else {
                    Text(languageState.value.getT("Enter 6-digit OTP sent to +91 $phone", "+91 $phone पर भेजा गया ओटीपी दर्ज करें", "+91 $phone କୁ ପଠାଯାଇଥିବା ଓଟିପି ଦିଅନ୍ତୁ"), style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(24.dp))
                    OtpInput(otp, { otp = it }, onDone = { if (otp.length == 6) onVerifyOtp(selectedRole!!, name, phone) })
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { onVerifyOtp(selectedRole!!, name, phone) },
                        enabled = otp.length == 6,
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
                    ) { Text(languageState.value.getT("Verify & Next", "सत्यापित करें और अगला", "ଯାଞ୍ଚ ଏବଂ ପରବର୍ତ୍ତୀ"), fontSize = 16.sp, fontWeight = FontWeight.Bold) }
                    
                    TextButton(onClick = { step = 1 }) { Text(languageState.value.getT("Go Back", "वापस जाएं", "ପଛକୁ ଯାଆନ୍ତୁ"), color = PrimaryGreen) }
                }
            }
        }

        if (step == 1) {
            // Divider
            Row(modifier = Modifier.padding(horizontal = 24.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray.copy(alpha = 0.5f))
                Text(languageState.value.getT("choose a role", "एक भूमिका चुनें", "ଗୋଟିଏ ଭୂମିକା ବାଛନ୍ତୁ"), modifier = Modifier.padding(horizontal = 16.dp), color = Color.Gray, fontSize = 14.sp)
                HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray.copy(alpha = 0.5f))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Role Selection
            Row(modifier = Modifier.padding(horizontal = 24.dp).fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                RoleCard(UserRole.SURAKSHA_DIDI, languageState.value.getT("Suraksha Didi", "सुरक्षा दीदी", "ସୁରକ୍ଷା ଦିଦି"), Icons.Default.Person, selectedRole == UserRole.SURAKSHA_DIDI, modifier = Modifier.weight(1f)) { selectedRole = it }
                RoleCard(UserRole.FARMER, languageState.value.getT("Farmer", "किसान", "କୃଷକ"), Icons.Default.Agriculture, selectedRole == UserRole.FARMER, modifier = Modifier.weight(1f)) { selectedRole = it }
                RoleCard(UserRole.COORDINATOR, languageState.value.getT("Coordinator", "समन्वयक", "ସମନ୍ଵୟକାରୀ"), Icons.Default.Badge, selectedRole == UserRole.COORDINATOR, modifier = Modifier.weight(1f)) { selectedRole = it }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }

        // Footer
        Row(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(languageState.value.getT("Version 2.0.4", "संस्करण 2.0.4", "ସଂସ୍କରଣ ୨.୦.୪"), color = Color.Gray, fontSize = 12.sp)
            Surface(color = Color(0xFFE8F5E9), shape = RoundedCornerShape(16.dp)) {
                Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFF4CAF50)))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(languageState.value.getT("Online", "ऑनलाइन", "ଅନଲାଇନ୍"), color = Color(0xFF4CAF50), fontSize = 12.sp, fontWeight = FontWeight.Bold)
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
                .height(320.dp)
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
                    Text(languageState.value.code, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
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

            // Logo and Title
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape,
                    color = Color.White.copy(alpha = 0.2f)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            Icons.Default.Pets,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    languageState.value.getT("GOAT INSURANCE", "बकरी बीमा", "ଛେଳି ବୀମା"),
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    languageState.value.getT("Community Livestock Protection", "सामुदायिक पशुधन संरक्षण", "ଗୋଷ୍ଠୀ ପ୍ରାଣୀସମ୍ପଦ ସୁରକ୍ଷା"),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
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
                    languageState.value.getT("Welcome Back", "वापसी पर स्वागत है", "ପୁଣି ସ୍ଵାଗତ"),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    languageState.value.getT("Login to your account to continue", "जारी रखने के लिए लॉगिन करें", "ଆଗକୁ ବଢିବା ପାଇଁ ଲଗଇନ୍ କରନ୍ତୁ"),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(32.dp))

                if (step == 1) {
                    OutlinedTextField(
                        value = phone,
                        onValueChange = { if (it.length <= 10) phone = it },
                        placeholder = { Text(languageState.value.getT("Mobile Number", "मोबाइल नंबर", "ମୋବାଇଲ୍ ନମ୍ବର"), color = Color.Gray) },
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
                                Toast.makeText(context, languageState.value.getT("Scroll down and choose a role", "नीचे स्क्रॉल करें और एक भूमिका चुनें", "ତଳକୁ ସ୍କ୍ରୋଲ୍ କରନ୍ତୁ ଏବଂ ଏକ ଭୂମିକା ବାଛନ୍ତୁ"), Toast.LENGTH_SHORT).show()
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
                        Text(languageState.value.getT("Send OTP", "ओटीपी भेजें", "ଓଟିପି ପଠାନ୍ତୁ"), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = languageState.value.getT("Don't have an account? Sign Up", "खाता नहीं है? साइन अप करें", "ଖାତା ନାହିଁ? ସାଇନ୍ ଅପ୍"),
                        color = PrimaryGreen,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        modifier = Modifier.clickable { onNavigateToSignUp() }
                    )
                } else {
                    Text(languageState.value.getT("Enter 6-digit OTP sent to +91 $phone", "+91 $phone पर भेजा गया ओटीपी दर्ज करें", "+91 $phone କୁ ପଠାଯାଇଥିବା ଓଟିପି ଦିଅନ୍ତୁ"), style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(24.dp))
                    OtpInput(otp, { otp = it }, onDone = { if (otp.length == 6) onLoginSuccess(selectedRole!!) })
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { onLoginSuccess(selectedRole!!) },
                        enabled = otp.length == 6,
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
                    ) { Text(languageState.value.getT("Verify & Login", "सत्यापित करें और लॉगिन", "ଯାଞ୍ଚ ଏବଂ ଲଗଇନ୍"), fontSize = 16.sp, fontWeight = FontWeight.Bold) }
                    
                    TextButton(onClick = { step = 1 }) { Text(languageState.value.getT("Change Number", "नंबर बदलें", "ନମ୍ବର ବଦଳାନ୍ତୁ"), color = PrimaryGreen) }
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
                    languageState.value.getT("choose a role", "एक भूमिका चुनें", "ଗୋଟିଏ ଭୂମିକା ବାଛନ୍ତୁ"),
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
                RoleCard(UserRole.SURAKSHA_DIDI, languageState.value.getT("Suraksha Didi", "सुरक्षा दीदी", "ସୁରକ୍ଷା ଦିଦି"), Icons.Default.Person, selectedRole == UserRole.SURAKSHA_DIDI, modifier = Modifier.weight(1f)) { selectedRole = it }
                RoleCard(UserRole.FARMER, languageState.value.getT("Farmer", "किसान", "କୃଷକ"), Icons.Default.Agriculture, selectedRole == UserRole.FARMER, modifier = Modifier.weight(1f)) { selectedRole = it }
                RoleCard(UserRole.COORDINATOR, languageState.value.getT("Coordinator", "समन्वयक", "ସମନ୍ଵୟକାରୀ"), Icons.Default.Badge, selectedRole == UserRole.COORDINATOR, modifier = Modifier.weight(1f)) { selectedRole = it }
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
            Text(languageState.value.getT("Version 2.0.4", "संस्करण 2.0.4", "ସଂସ୍କରଣ ୨.୦.୪"), color = Color.Gray, fontSize = 12.sp)
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
                    Text(languageState.value.getT("Online", "ऑनलाइन", "ଅନଲାଇନ୍"), color = Color(0xFF4CAF50), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun RoleCard(role: UserRole, label: String, icon: ImageVector, isSelected: Boolean, modifier: Modifier = Modifier, onClick: (UserRole) -> Unit) {
    Card(
        onClick = { onClick(role) },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFFE8F5E9) else Color.White,
        ),
        modifier = modifier
            .height(110.dp)
            .border(
                width = 1.dp,
                color = if (isSelected) PrimaryGreen else Color.LightGray.copy(alpha = 0.5f),
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
                tint = if (isSelected) PrimaryGreen else Color.DarkGray,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                label,
                fontSize = 10.sp,
                lineHeight = 12.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) PrimaryGreen else Color.DarkGray,
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
    val languageState = LocalAppLanguage.current
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
                        icon = { Icon(Icons.Default.Pets, null) }, 
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
                DidiContent(PaddingValues(0.dp), navController, userName ?: "Sushma Didi") { showNotifications = true }
            }
        }
    )
}

@Composable
fun DidiContent(padding: PaddingValues, navController: NavHostController, userName: String, onNotificationClick: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(bottom = padding.calculateBottomPadding())
            .fillMaxSize()
            .background(Color(0xFFF8F9F5))
    ) {
        DashboardHeader(
            userName,
            languageState.value.getT("Suraksha Didi", "सुरक्षा दीदी", "ସୁରକ୍ଷା ଦିଦି"),
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
                when(index) {
                    0 -> StatCard(languageState.value.getT("Goats Enrolled", "पंजीकृत बकरियां", "ପଞ୍ଜିକୃତ ଛେଳି"), "128", Icons.Default.Pets, PrimaryGreen, CardLightGreen)
                    1 -> StatCard(languageState.value.getT("Pending Claims", "लंबित दावे", "ବାକି ରହିଥିବା ଦାବି"), "12", Icons.AutoMirrored.Filled.Assignment, AccentOrange, CardLightOrange)
                    2 -> StatCard(languageState.value.getT("Today's Visits", "आज की मुलाकात", "ଆଜିର ପରିଦର୍ଶନ"), "24", Icons.Default.CalendarToday, InfoBlue, CardLightBlue)
                    3 -> StatCard(languageState.value.getT("Earnings", "आय", "ଉପାର୍ଜନ"), "₹8,450", Icons.Default.Payments, Color(0xFF9C27B0), CardLightPurple)
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
                    0 -> QuickActionGridCard(languageState.value.getT("Enroll Goat", "बकरी का नामांकन", "ଛେଳି ପଞ୍ଜିକରଣ"), Icons.Default.Pets, PrimaryGreen, CardLightGreen) { navController.navigate("enrollment") }
                    1 -> QuickActionGridCard(languageState.value.getT("Vaccination", "टीकाकरण", "ଟୀକାକରଣ"), Icons.Default.MedicalServices, InfoBlue, CardLightBlue) { navController.navigate("vaccine_list") }
                    2 -> QuickActionGridCard(languageState.value.getT("Mortality Report", "मृत्यु रिपोर्ट", "ମୃତ୍ୟୁ ରିପୋର୍ଟ"), Icons.Default.LocationOn, Color(0xFFD32F2F), CardLightRed) { navController.navigate("mortality_report") }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MortalityReportScreen(onComplete: () -> Unit) {
    var currentStep by remember { mutableIntStateOf(1) }
    val languageState = LocalAppLanguage.current
    Scaffold(
        modifier = Modifier.fillMaxSize().navigationBarsPadding(),
        topBar = { CenterAlignedTopAppBar(title = { Text(languageState.value.getT("Mortality Verification", "मृत्यु सत्यापन", "ମୃତ୍ୟୁ ଯାଞ୍ଚ")) }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            LinearProgressIndicator(progress = { currentStep / 4f }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(24.dp))
            when (currentStep) {
                1 -> MortalityStep1()
                2 -> MortalityStep2()
                3 -> MortalityStep3()
                4 -> MortalityStep4()
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { if (currentStep < 4) currentStep++ else onComplete() }, modifier = Modifier.fillMaxWidth()) {
                Text(if (currentStep == 4) languageState.value.getT("Submit Report", "रिपोर्ट जमा करें", "ରିପୋର୍ଟ ଦାଖଲ କରନ୍ତୁ") else languageState.value.getT("Continue", "जारी रखें", "ଆଗକୁ ବଢନ୍ତୁ"))
            }
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
    var capturedPhotoUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var tempUriStr by rememberSaveable { mutableStateOf<String?>(null) }

    fun createTempUri(): Uri {
        val file = File.createTempFile("death_report_", ".jpg", context.cacheDir)
        return FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && tempUriStr != null) {
            capturedPhotoUri = Uri.parse(tempUriStr)
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

    fun launchCamera() {
        val uri = createTempUri()
        tempUriStr = uri.toString()
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            cameraLauncher.launch(uri)
        } else {
            permissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    val goats = listOf(
        Triple("ET-340801-0001", "Black Bengal", "12M"),
        Triple("ET-240801-0002", "Jamunapari", "18M"),
        Triple("ET-140801-0004", "Sirohi", "24M")
    )
    var selectedGoat by remember { mutableStateOf(goats[0]) }
    var expanded by remember { mutableStateOf(false) }

    val calendar = Calendar.getInstance()

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
                                Icon(Icons.Default.Pets, null, tint = Color.Gray, modifier = Modifier.size(28.dp))
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
                languageState.value.getT("Upload Photo *", "फोटो अपलोड करें *", "ଫଟୋ ଅପଲୋଡ୍ କରନ୍ତୁ *"),
                fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                modifier = Modifier.size(120.dp),
                shape = RoundedCornerShape(12.dp),
                color = Color.White,
                border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f)),
                onClick = { launchCamera() }
            ) {
                Box(contentAlignment = Alignment.Center) {
                    if (capturedPhotoUri != null) {
                        AsyncImage(
                            model = capturedPhotoUri,
                            contentDescription = "Captured Goat",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
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
                onClick = onComplete,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                enabled = isConfirmed && deathDate.isNotBlank() && deathTime.isNotBlank() && capturedPhotoUri != null,
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
fun MortalityStep1() {
    val languageState = LocalAppLanguage.current
    Text(languageState.value.getT("Step 1: Carcass Verification", "चरण 1: शव सत्यापन", "ପଦକ୍ଷେପ ୧: ଶବ ଯାଞ୍ଚ"), style = MaterialTheme.typography.titleLarge)
    Text(languageState.value.getT("Capture clear photo of the carcass with the ear tag visible.", "कान के टैग के साथ शव की स्पष्ट फोटो लें।", "କାନ ଟ୍ୟାଗ୍ ସହିତ ଶବର ସ୍ପଷ୍ଟ ଫଟୋ ନିଅନ୍ତୁ |"))
    Box(modifier = Modifier.fillMaxWidth().height(200.dp).background(Color.LightGray, RoundedCornerShape(12.dp)), contentAlignment = Alignment.Center) {
        Icon(Icons.Default.CameraAlt, null, modifier = Modifier.size(48.dp))
    }
}

@Composable
fun MortalityStep2() { 
    val languageState = LocalAppLanguage.current
    Text(languageState.value.getT("Step 2: Check Ear Tag & Breed", "चरण 2: कान का टैग और नस्ल की जाँच करें", "ପଦକ୍ଷେପ ୨: କାନ ଟ୍ୟାଗ୍ ଏବଂ ପ୍ରଜାତି ଯାଞ୍ଚ କରନ୍ତୁ")) 
}
@Composable
fun MortalityStep3() { 
    val languageState = LocalAppLanguage.current
    Text(languageState.value.getT("Step 3: Capture Farmer & Site Photo", "चरण 3: किसान और साइट की फोटो लें", "ପଦକ୍ଷେପ ୩: କୃଷକ ଏବଂ ସାଇଟ୍ ଫଟୋ ନିଅନ୍ତୁ")) 
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
    var currentStep by remember { mutableIntStateOf(1) }
    val languageState = LocalAppLanguage.current
    
    // Form State
    var farmerName by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var village by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var aadhaar by remember { mutableStateOf("") }
    
    var breed by remember { mutableStateOf("Black Bengal") }
    var gender by remember { mutableStateOf(languageState.value.getT("Female", "मादा", "ମାଈ")) }
    var age by remember { mutableStateOf("") }
    var ageUnit by remember { mutableStateOf(languageState.value.getT("Months", "महीने", "ମାସ")) }
    var weight by remember { mutableStateOf("") }
    var colorMarks by remember { mutableStateOf("") }
    
    var earTagNumber by remember { mutableStateOf("") }
    
    // Photo State
    var leftPhotoUri by remember { mutableStateOf<Uri?>(null) }
    var rightPhotoUri by remember { mutableStateOf<Uri?>(null) }
    var frontPhotoUri by remember { mutableStateOf<Uri?>(null) }
    var tagPhotoUri by remember { mutableStateOf<Uri?>(null) }
    
    val steps = listOf(
        languageState.value.getT("Farmer Information", "किसान जानकारी", "କୃଷକ ସୂଚନା"),
        languageState.value.getT("Goat Details", "बकरी का विवरण", "ଛେଳି ବିବରଣୀ"),
        languageState.value.getT("Photo Capture", "फोटो कैप्चर", "ଫଟୋ କ୍ୟାପଚର"),
        languageState.value.getT("Ear Tagging", "कान की टैगिंग", "କାନ ଟ୍ୟାଗିଂ"),
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
                3 -> true // Camera access removed for bypass
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
                        Text(languageState.value.getT("Back", "पीछे", "ପଛକୁ"), color = PrimaryGreen, fontWeight = FontWeight.Bold)
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
                        text = if (currentStep == 7) languageState.value.getT("Finish Enrollment", "नामांकन पूरा करें", "ପଞ୍ଜିକରଣ ଶେଷ କରନ୍ତୁ") 
                               else languageState.value.getT("Next", "अगला", "ପରବର୍ତ୍ତୀ"),
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
    val languageState = LocalAppLanguage.current
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        EnrollmentTextField(label = languageState.value.getT("Farmer Name *", "किसान का नाम *", "କୃଷକଙ୍କ ନାମ *"), value = name, onValueChange = onNameChange, placeholder = "Full Name")
        EnrollmentTextField(label = languageState.value.getT("Mobile Number *", "मोबाइल नंबर *", "ମୋବାଇଲ୍ ନମ୍ବର *"), value = phone, onValueChange = onPhoneChange, placeholder = "10-digit number", keyboardType = KeyboardType.Phone, prefix = "+91 ")
        EnrollmentTextField(label = languageState.value.getT("Village *", "गाँव *", "ଗ୍ରାମ *"), value = village, onValueChange = onVillageChange, placeholder = "Village Name")
        EnrollmentTextField(label = languageState.value.getT("GPS Location *", "जीपीएस स्थान *", "GPS ଅବସ୍ଥାନ *"), value = location, onValueChange = onLocationChange, placeholder = "Auto-fetch coordinates", trailingIcon = Icons.Default.LocationOn)
        EnrollmentTextField(label = languageState.value.getT("Aadhaar / Gov ID *", "आधार / सरकारी आईडी *", "ଆଧାର / ସରକାରୀ ID *"), value = aadhaar, onValueChange = onAadhaarChange, placeholder = "12-digit number", keyboardType = KeyboardType.Number)
    }
}

@Composable
fun EnrollmentGoatStep(breed: String, onBreedChange: (String) -> Unit, gender: String, onGenderChange: (String) -> Unit, age: String, onAgeChange: (String) -> Unit, ageUnit: String, onAgeUnitChange: (String) -> Unit, weight: String, onWeightChange: (String) -> Unit, color: String, onColorChange: (String) -> Unit) {
    val languageState = LocalAppLanguage.current
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        EnrollmentDropdownField(
            label = languageState.value.getT("Breed *", "नस्ल *", "ପ୍ରଜାତି *"),
            selectedValue = breed,
            options = listOf("Black Bengal", "Jamunapari", "Sirohi", "Barbari", "Beetal", "Ganjam", "Osmanabadi"),
            onValueChange = onBreedChange
        )
        EnrollmentDropdownField(
            label = languageState.value.getT("Gender *", "लिंग *", "ଲିଙ୍ଗ *"),
            selectedValue = gender,
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

    fun createTempUri(prefix: String): Uri {
        val file = File.createTempFile("enroll_${prefix}_", ".jpg", context.cacheDir)
        return FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
    }

    var tempUriStr by rememberSaveable { mutableStateOf<String?>(null) }
    var captureType by rememberSaveable { mutableStateOf("") }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && tempUriStr != null) {
            val uri = Uri.parse(tempUriStr)
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
        captureType = type
        val uri = createTempUri(type)
        tempUriStr = uri.toString()
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            cameraLauncher.launch(uri)
        } else {
            permissionLauncher.launch(android.Manifest.permission.CAMERA)
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
            onClick = { /* Scan Tag */ },
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
fun EnrollmentVaccinationStep() {
    val languageState = LocalAppLanguage.current
    var pprGiven by remember { mutableStateOf(true) }
    var etttGiven by remember { mutableStateOf(true) }
    var fmdGiven by remember { mutableStateOf(false) }
    var poxGiven by remember { mutableStateOf(false) }
    
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        VaccineStatusItem("PPR Vaccine", pprGiven) { pprGiven = it }
        VaccineStatusItem("ET + TT Vaccine", etttGiven) { etttGiven = it }
        VaccineStatusItem("FMD Vaccine", fmdGiven) { fmdGiven = it }
        VaccineStatusItem("Goat Pox Vaccine", poxGiven) { poxGiven = it }
        
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
                    Text(languageState.value.getT("Next Vaccination Due", "अगला टीकाकरण देय", "ପରବର୍ତ୍ତୀ ଟୀକାକରଣ ବାକି"), fontSize = 12.sp, color = Color.Gray)
                    Text("15 Aug 2024", fontWeight = FontWeight.Bold, color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun EnrollmentPaymentStep() {
    val languageState = LocalAppLanguage.current
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
                Text(languageState.value.getT("Total Premium Amount", "कुल प्रीमियम राशि", "ମୋଟ ପ୍ରିମିୟମ ପରିମାଣ"), fontSize = 14.sp, color = Color.Gray)
                Text("₹ 350", style = MaterialTheme.typography.headlineLarge, color = PrimaryGreen, fontWeight = FontWeight.Bold)
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        Text(languageState.value.getT("Select Payment Method", "भुगतान विधि चुनें", "ଦେୟ ପଦ୍ଧତି ଚୟନ କରନ୍ତୁ"), fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            PaymentMethodChip("Cash", selectedMethod == "Cash", modifier = Modifier.weight(1f)) { selectedMethod = "Cash" }
            PaymentMethodChip("UPI", selectedMethod == "UPI", modifier = Modifier.weight(1f)) { selectedMethod = "UPI" }
            PaymentMethodChip("Wallet", selectedMethod == "Wallet", modifier = Modifier.weight(1f)) { selectedMethod = "Wallet" }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        EnrollmentTextField(label = languageState.value.getT("Premium Amount (editable if needed)", "प्रीमियम राशि (यदि आवश्यक हो तो संपादन योग्य)", "ପ୍ରିମିୟମ ପରିମାଣ (ଆବଶ୍ୟକ ହେଲେ ସଂପାଦନ ଯୋଗ୍ୟ)"), value = "350", onValueChange = {}, prefix = "₹")
        Spacer(modifier = Modifier.height(16.dp))
        EnrollmentTextField(label = languageState.value.getT("Receipt Number", "रसीद संख्या", "ରସିଦ ନମ୍ବର"), value = "RCP-240801-001", onValueChange = {}, leadingIcon = Icons.Default.Receipt)
    }
}

@Composable
fun EnrollmentPolicyStep(farmer: String, tag: String) {
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
                PolicyDetailRow(languageState.value.getT("Policy Number", "पॉलिसी नंबर", "ନୀତି ନମ୍ବର"), "POL-2024-00125")
                PolicyDetailRow(languageState.value.getT("Farmer Name", "किसान का नाम", "କୃଷକଙ୍କ ନାମ"), farmer.ifBlank { "RASGJM RYU" })
                PolicyDetailRow(languageState.value.getT("Ear Tag", "कान का टैग", "କାନ ଟ୍ୟାଗ୍"), tag.ifBlank { "ET-453678" })
                PolicyDetailRow(languageState.value.getT("Premium Paid", "प्रीमियम भुगतान", "ଦିଆଯାଇଥିବା ପ୍ରିମିୟମ"), "₹ 350")
                PolicyDetailRow(languageState.value.getT("Validity", "वैधता", "ବୈଧତା"), "01 Aug 2024 - 31 Jul 2025")
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                PolicyDetailRow(languageState.value.getT("Sum Insured", "बीमा राशि", "ବୀମା ରାଶି"), "₹ 8,500", true)
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
    if (showNotifications) NotificationSheet(themeColor = PrimaryBlue) { showNotifications = false }
    val languageState = LocalAppLanguage.current
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
                        icon = { Icon(Icons.Default.Pets, null) }, 
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
                FarmerContent(PaddingValues(0.dp), navController, userName ?: "Ramesh Naik") { showNotifications = true }
            }
        }
    )
}

@Composable
fun FarmerContent(padding: PaddingValues, navController: NavHostController, userName: String, onNotificationClick: () -> Unit) {
    val languageState = LocalAppLanguage.current
    val context = LocalContext.current
    
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
            userName,
            languageState.value.getT("Farmer", "किसान", "କୃଷକ"),
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
                    0 -> StatCard(languageState.value.getT("Active Policies", "सक्रिय नीतियां", "ସକ୍ରିୟ ନୀତି"), "02", Icons.AutoMirrored.Filled.Assignment, royalBlue, skyBlue)
                    1 -> StatCard(languageState.value.getT("Total Goats", "कुल बकरियां", "ମୋଟ ଛେଳି"), "05", Icons.Default.Pets, PrimaryGreen, CardLightGreen)
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
                                languageState.value.getT("Next Vaccination Due", "अगला टीकाकरण देय", "ପରବର୍ତ୍ତୀ ଟୀକାକରଣ ବାକି"),
                                fontSize = 13.sp,
                                color = deepBlue,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                languageState.value.getT("15 Aug 2024 (Tomorrow)", "15 अगस्त 2024 (कल)", "୧୫ ଅଗଷ୍ଟ ୨୦୨୪ (କାଲି)"),
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                fontSize = 15.sp
                            )
                        }
                        OutlinedButton(
                            onClick = { 
                                Toast.makeText(context, languageState.value.getT("Vaccination Schedule", "टीकाकरण अनुसूची", "ଟୀକାକରଣ ସୂଚୀ"), Toast.LENGTH_SHORT).show()
                            },
                            border = BorderStroke(1.dp, deepBlue),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = deepBlue),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
                        ) {
                            Text(
                                languageState.value.getT("View", "देखें", "ଦେଖନ୍ତୁ"),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
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
                            languageState.value.getT("My Policies (2)", "मेरी नीतियां (2)", "ମୋର ନୀତିଗୁଡ଼ିକ (୨)"),
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
                                    Icon(Icons.Default.Pets, null, tint = Color.Gray, modifier = Modifier.size(32.dp))
                                }
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text("ET-340801-0001", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)
                                Text("Black Bengal • ${languageState.value.getT("Female", "मादा", "ମାଈ")} • 12M", color = Color.Gray, fontSize = 12.sp)
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(languageState.value.getT("Policy Active", "पॉलिसी सक्रिय", "ନୀତି ସକ୍ରିୟ"), color = SuccessGreen, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                Text(languageState.value.getT("Valid till 31 May 2025", "31 मई 2025 तक मान्य", "୩୧ ମଇ ୨୦୨୫ ପର୍ଯ୍ୟନ୍ତ ବୈଧ"), fontSize = 11.sp, color = Color.Gray)
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
                                onClick = { navController.navigate("goat_list") },
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
        }
    }
}

@Composable
fun FarmerHeader(name: String, role: String, onNotificationClick: () -> Unit = {}, hasNotifications: Boolean = false, onProfileClick: () -> Unit = {}) {
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
fun DashboardHeader(name: String, role: String, onNotificationClick: () -> Unit = {}, hasNotifications: Boolean = false, onProfileClick: () -> Unit = {}) {
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
fun StatCard(label: String, value: String, icon: ImageVector, iconColor: Color, iconBgColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
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
                Icon(icon, null, tint = iconColor, modifier = Modifier.size(20.dp))
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
fun QuickActionGridCard(label: String, icon: ImageVector, iconColor: Color, iconBgColor: Color, onClick: () -> Unit) {
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
                Icon(icon, null, tint = iconColor, modifier = Modifier.size(28.dp))
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
            icon = { Icon(Icons.Default.Pets, null) },
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
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Default.MoreHoriz, null) },
            label = { Text(languageState.value.getT("More", "अधिक", "ଅଧିକ"), fontSize = 9.sp) },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
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
            icon = { Icon(Icons.Default.Pets, null) },
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
            selected = currentRoute == "claim_tracker",
            onClick = {
                if (currentRoute != "claim_tracker") {
                    navController.navigate("claim_tracker") {
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
            selected = false,
            onClick = {
                Toast.makeText(context, languageState.value.getT("Contacting Support...", "सहायता से संपर्क कर रहे हैं...", "ସହାୟତା ସହିତ ଯୋଗାଯୋଗ କରାଯାଉଛି..."), Toast.LENGTH_SHORT).show()
            },
            icon = { Icon(Icons.Default.SupportAgent, null) },
            label = { Text(languageState.value.getT("Help", "सहायता", "ସାହାଯ୍ୟ"), fontSize = 9.sp) },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Default.MoreHoriz, null) },
            label = { Text(languageState.value.getT("More", "अधिक", "ଅଧିକ"), fontSize = 9.sp) },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
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
        AppNotification(languageState.value.getT("New Enrollment", "नया नामांकन", "ନୂତନ ପଞ୍ଜିକରଣ"), languageState.value.getT("Farmer Ram added a new goat.", "किसान राम ने एक नई बकरी जोड़ी।", "କୃଷକ ରାମ ଏକ ନୂତନ ଛେଳି ଯୋଡିଛନ୍ତି।"), languageState.value.getT("2 mins ago", "2 मिनट पहले", "୨ ମିନିଟ୍ ପୂର୍ବରୁ")),
        AppNotification(languageState.value.getT("Vaccination Due", "टीकाकरण देय", "ଟୀକାକରଣ ବାକି"), languageState.value.getT("PPR Vaccine due for TAG-12345.", "TAG-12345 के लिए PPR वैक्सीन देय है।", "TAG-12345 ପାଇଁ PPR ଟୀକା ବାକି ଅଛି |"), languageState.value.getT("1 hour ago", "1 घंटे पहले", "୧ ଘଣ୍ଟା ପୂର୍ବରୁ")),
        AppNotification(languageState.value.getT("Claim Approved", "दावा स्वीकृत", "ଦାବି ଅନୁମୋଦିତ"), languageState.value.getT("Claim #CLM7890 has been approved.", "दावा #CLM7890 स्वीकृत हो गया है।", "ଦାବି #CLM7890 ଅନୁମୋଦିତ ହୋଇଛି।"), languageState.value.getT("Yesterday", "कल", "ଗତକାଲି")),
        AppNotification(languageState.value.getT("Visit Reminder", "मुलाकात अनुस्मारक", "ପରିଦର୍ଶନ ସ୍ମାରକୀ"), languageState.value.getT("Scheduled visit to Village Site B.", "गांव साइट बी की निर्धारित यात्रा।", "ଗ୍ରାମ ସାଇଟ୍ ବି କୁ ନିର୍ଦ୍ଧାରିତ ପରିଦର୍ଶନ |"), languageState.value.getT("Today, 4 PM", "आज, शाम 4 बजे", "ଆଜି, ଅପରାହ୍ନ ୪ ଟା"))
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
                languageState.value.getT("Notifications", "सूचनाएं", "ବିଜ୍ଞପ୍ତି"),
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
fun ProfileScreen(userRole: UserRole?, sessionManager: SessionManager, onLogout: () -> Unit, onBack: () -> Unit) {
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
        isFarmer -> languageState.value.getT("Ramesh Naik", "रमेश नायक", "ରମେଶ ନାୟକ")
        isCoordinator -> languageState.value.getT("lalu", "लालू", "ଲାଲୁ")
        else -> languageState.value.getT("Sushma Didi", "सुषमा दीदी", "ସୁସମା ଦିଦି")
    }
    
    val roleLabel = when(userRole) {
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
                Toast.makeText(context, languageState.value.getT("Saved", "सहेजा गया", "ସଂରକ୍ଷିତ"), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getLanguageName(lang: AppLanguage) = when(lang) {
        AppLanguage.HINDI -> "हिन्दी (HI)"
        AppLanguage.ODIA -> "ଓଡ଼ିଆ (OR)"
        AppLanguage.ENGLISH -> "English (EN)"
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
                        onClick = { imagePickerLauncher.launch("image/*") },
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
            ProfileInfoSection(languageState.value.getT("Account Details", "खाता विवरण", "ଖାତା ବିବରଣୀ"), themeColor) {
                ProfileInfoItem(
                    languageState.value.getT("Full Name", "पूरा नाम", "ପୁରା ନାମ"),
                    userName
                )
                ProfileInfoItem(
                    languageState.value.getT("Role", "भूमिका", "ଭୂମିକା"),
                    roleLabel
                )
                ProfileInfoItem(
                    languageState.value.getT("Location", "स्थान", "ଅବସ୍ଥାନ"),
                    savedVillage ?: when {
                        isFarmer -> languageState.value.getT("Pipili, Odisha", "पिपिली, ओडिशा", "ପିପିଲି, ଓଡ଼ିଶା")
                        isCoordinator -> languageState.value.getT("Bhubaneswar, Odisha", "भुवनेश्वर, ओडिशा", "ଭୁବନେଶ୍ୱର, ଓଡ଼ିଶା")
                        else -> languageState.value.getT("Gopalpur, Odisha", "गोपालपुर, ओडिशा", "ଗୋପାଳପୁର, ଓଡ଼ିଶା")
                    }
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
                SettingsClickItem(languageState.value.getT("Help & Support", "सहायता और समर्थन", "ସାହାଯ୍ୟ ଏବଂ ସମର୍ଥନ"), "") {}
                SettingsClickItem(languageState.value.getT("Privacy Policy", "गोपनीयता नीति", "ଗୋପନୀୟତା ନୀତି"), "") {}
                SettingsClickItem(languageState.value.getT("Terms of Service", "सेवा की शर्तें", "ସେବା ସର୍ତ୍ତାବଳୀ"), "") {}
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
            Text(languageState.value.getT("Version 2.0.4", "संस्करण 2.0.4", "ସଂସ୍କରଣ ୨.୦.୪"), color = Color.Gray, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(32.dp))
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
        languageState.value.getT("All (128)", "सभी (128)", "ସମସ୍ତ (୧୨୮)"),
        languageState.value.getT("Active (110)", "सक्रिय (110)", "ସକ୍ରିୟ (୧୧୦)"),
        languageState.value.getT("Expired (10)", "समाप्त (10)", "ସମାପ୍ତ (୧୦)"),
        languageState.value.getT("Claimed", "दावा किया गया", "ଦାବି ହୋଇଛି")
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
                GoatListContent(padding, tabs, searchQuery, { searchQuery = it }, selectedTab, { selectedTab = it }, themeColor) { tag ->
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
                        label = { Text(languageState.value.getT("Home", "होम", "ମୁଖ୍ୟ ପୃଷ୍ଠା")) },
                        colors = NavigationRailItemDefaults.colors(selectedIconColor = themeColor, selectedTextColor = themeColor, unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray, indicatorColor = Color.Transparent)
                    )
                    NavigationRailItem(
                        selected = currentRoute == "goat_list", 
                        onClick = { if (currentRoute != "goat_list") navController.navigate("goat_list") }, 
                        icon = { Icon(Icons.Default.Pets, null) }, 
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
                    GoatListContent(padding, tabs, searchQuery, { searchQuery = it }, selectedTab, { selectedTab = it }, themeColor) { tag ->
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
    onGoatClick: (String) -> Unit
) {
    val languageState = LocalAppLanguage.current
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

        val mockGoats = listOf(
            Triple("ET-340801-0001", "Ramesh Naik", "Pipili"),
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

        LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(filteredGoats) { goat ->
                val isExpired = goat.first == "ET-340801-0003"
                Card(
                    onClick = { onGoatClick(goat.first) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Surface(modifier = Modifier.size(70.dp), shape = RoundedCornerShape(12.dp), color = Color(0xFFF0F0F0)) {
                            Box(contentAlignment = Alignment.Center) { Icon(Icons.Default.Pets, null, tint = Color.Gray) }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(goat.first, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            Text(goat.second, fontSize = 13.sp, color = Color.Gray)
                            Text(goat.third, fontSize = 13.sp, color = Color.Gray)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(if (isExpired) languageState.value.getT("Policy Expired", "पॉलिसी समाप्त", "ନୀତି ସମାପ୍ତ") else languageState.value.getT("Policy Active", "पॉलिसी सक्रिय", "ନୀତି ସକ୍ରିୟ"), color = if (isExpired) Color.Red else SuccessGreen, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            Text(if (isExpired) languageState.value.getT("Expired on: 31 May 2024", "31 मई 2024 को समाप्त", "୩୧ ମଇ ୨୦୨୪ ରେ ସମାପ୍ତ") else languageState.value.getT("Next Vaccine: 15 Aug 2024", "अगला टीका: 15 अगस्त 2024", "ପରବର୍ତ୍ତୀ ଟୀକା: ୧୫ ଅଗଷ୍ଟ ୨୦୨୪"), fontSize = 12.sp, color = Color.Black)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Icon(Icons.Default.ChevronRight, null, tint = Color.LightGray)
                            Spacer(modifier = Modifier.height(16.dp))
                            if (isExpired) {
                                Icon(Icons.Default.Cancel, null, tint = Color.Red, modifier = Modifier.size(20.dp))
                            } else {
                                Icon(Icons.Default.CheckCircle, null, tint = SuccessGreen, modifier = Modifier.size(20.dp))
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

    val mockGoats = listOf(
        Triple("ET-340801-0001", "Ramesh Naik", "Pipili"),
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
                                Icons.Default.Pets,
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
                // Farmer Information
                ProfileInfoSection(languageState.value.getT("Farmer Information", "किसान जानकारी", "କୃଷକ ସୂଚନା"), themeColor) {
                    ProfileInfoItem(languageState.value.getT("Farmer Name", "किसान का नाम", "କୃଷକଙ୍କ ନାମ"), goat.second)
                    ProfileInfoItem(languageState.value.getT("Village", "गाँव", "ଗ୍ରାମ"), goat.third)
                    ProfileInfoItem(languageState.value.getT("Aadhaar Number", "आधार नंबर", "ଆଧାର ନମ୍ବର"), "**** **** 1234")
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Goat Details
                ProfileInfoSection(languageState.value.getT("Goat Details", "बकरी का विवरण", "ଛେଳି ବିବରଣୀ"), themeColor) {
                    ProfileInfoItem(languageState.value.getT("Breed", "नस्ल", "ପ୍ରଜାତି"), "Black Bengal")
                    ProfileInfoItem(languageState.value.getT("Gender", "लिंग", "ଲିଙ୍ଗ"), languageState.value.getT("Female", "मादा", "ମାଈ"))
                    ProfileInfoItem(languageState.value.getT("Age", "आयु", "ବୟସ"), "14 " + languageState.value.getT("Months", "महीने", "ମାସ"))
                    ProfileInfoItem(languageState.value.getT("Weight", "वजन", "ଓଜନ"), "19 KG")
                    ProfileInfoItem(languageState.value.getT("Color / Marks", "रंग / निशान", "ରଙ୍ଗ / ଚିହ୍ନ"), "Black with White Spots")
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Policy Details
                ProfileInfoSection(languageState.value.getT("Policy Information", "पॉलिसी जानकारी", "ନୀତି ସୂଚନା"), themeColor) {
                    ProfileInfoItem(languageState.value.getT("Policy Number", "पॉलिसी नंबर", "ନୀତି ନମ୍ବର"), if (isExpired) "POL-2023-00567" else "POL-2024-00125")
                    ProfileInfoItem(languageState.value.getT("Issue Date", "जारी करने की तारीख", "ପ୍ରଦାନ ତାରିଖ"), if (isExpired) "01 Jun 2023" else "01 Aug 2024")
                    ProfileInfoItem(languageState.value.getT("End Date", "समाप्ति तिथि", "ଶେଷ ତାରିଖ"), if (isExpired) "31 May 2024" else "31 Jul 2025")
                    ProfileInfoItem(languageState.value.getT("Sum Insured", "बीमा राशि", "ବୀମା ରାଶି"), "₹ 8,500")
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Vaccination History
                ProfileInfoSection(languageState.value.getT("Vaccination History", "टीकाकरण इतिहास", "ଟୀକାକରଣ ଇତିହାସ"), themeColor) {
                    VaccineStatusItem("PPR Vaccine", true, themeColor) {}
                    VaccineStatusItem("ET + TT Vaccine", true, themeColor) {}
                    VaccineStatusItem("FMD Vaccine", false, themeColor) {}
                    VaccineStatusItem("Goat Pox Vaccine", false, themeColor) {}
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

    val tabs = listOf(
        languageState.value.getT("Upcoming (15)", "आगामी (15)", "ଆଗାମୀ (୧୫)"),
        languageState.value.getT("Completed", "पूरा हुआ", "ସମ୍ପୂର୍ଣ୍ଣ"),
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
                        label = { Text(languageState.value.getT("Home", "होम", "ମୁଖ୍ୟ ପୃଷ୍ଠା")) },
                        colors = NavigationRailItemDefaults.colors(selectedIconColor = PrimaryGreen, selectedTextColor = PrimaryGreen, unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray, indicatorColor = Color.Transparent)
                    )
                    NavigationRailItem(
                        selected = currentRoute == "goat_list", 
                        onClick = { if (currentRoute != "goat_list") navController.navigate("goat_list") }, 
                        icon = { Icon(Icons.Default.Pets, null) }, 
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

        val mockVaccinations = listOf(
            Triple("PPR Vaccine", "ET-340801-0001", "15 Aug 2024"),
            Triple("ET + TT Vaccine", "ET-240801-0002", "20 Aug 2024"),
            Triple("FMD Vaccine", "ET-340801-0003", "10 Jul 2024"),
            Triple("PPR Vaccine", "ET-140801-0004", "05 Sep 2024"),
            Triple("Goat Pox Vaccine", "ET-540801-0005", "12 Aug 2024")
        )

        val filteredVaccines = mockVaccinations.filter { 
            it.first.contains(searchQuery, ignoreCase = true) || it.second.contains(searchQuery, ignoreCase = true)
        }.filter { vaccine ->
            val isCompleted = vaccine.second == "ET-340801-0003"
            when (selectedTab) {
                0 -> !isCompleted // Upcoming (Due)
                1 -> isCompleted  // Completed
                else -> true      // All
            }
        }

        LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(filteredVaccines) { vaccine ->
                val isCompleted = vaccine.second == "ET-340801-0003"
                val mockFarmer = when(vaccine.second) {
                    "ET-340801-0001" -> "Ramesh Naik"
                    "ET-240801-0002" -> "Suresh Behera"
                    "ET-140801-0004" -> "Alok Dash"
                    "ET-540801-0005" -> "Prakash Rout"
                    else -> "Manoj Sahoo"
                }
                
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Surface(modifier = Modifier.size(70.dp), shape = RoundedCornerShape(12.dp), color = Color(0xFFF0F0F0)) {
                            Box(contentAlignment = Alignment.Center) { Icon(Icons.Default.Pets, null, tint = Color.Gray) }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(vaccine.second, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            Text(mockFarmer, fontSize = 13.sp, color = Color.Gray)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("${vaccine.first} ${if (isCompleted) "Done" else "Due"}", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color.Black)
                            Text(vaccine.third, fontSize = 12.sp, color = Color.Black)
                        }
                        
                        OutlinedButton(
                            onClick = { onRecord(vaccine.second) },
                            modifier = Modifier.height(36.dp),
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, PrimaryGreen),
                            contentPadding = PaddingValues(horizontal = 12.dp)
                        ) {
                            Text(languageState.value.getT("Record", "रिकॉर्ड", "ରେକର୍ଡ"), color = PrimaryGreen, fontSize = 12.sp, fontWeight = FontWeight.Bold)
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
    var vaccinationDate by remember { mutableStateOf("15 Jun 2024") }
    
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
                        Box(contentAlignment = Alignment.Center) { Icon(Icons.Default.Pets, null, tint = Color.Gray) }
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
                border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.CameraAlt, null, tint = Color.Gray)
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Button(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
            ) {
                Text(languageState.value.getT("Save Record", "रिकॉर्ड सहेजें", "ରେକର୍ଡ ସଂରକ୍ଷଣ କରନ୍ତୁ"), fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClaimListScreen(navController: NavHostController, onBack: () -> Unit) {
    val languageState = LocalAppLanguage.current
    var searchQuery by remember { mutableStateOf("") }

    ResponsiveLayout(
        compact = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(languageState.value.getT("Claims", "दावे", "ଦାବି"), fontWeight = FontWeight.Bold) },
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
                ClaimListContent(padding, searchQuery, { searchQuery = it }) { claimId ->
                    navController.navigate("claim_review/$claimId")
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
                        icon = { Icon(Icons.Default.Pets, null) }, 
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
                            title = { Text(languageState.value.getT("Claims", "दावे", "ଦାବି"), fontWeight = FontWeight.Bold) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = PrimaryGreen,
                                titleContentColor = Color.White
                            )
                        )
                    },
                    containerColor = Color(0xFFF8F9F5)
                ) { padding ->
                    ClaimListContent(padding, searchQuery, { searchQuery = it }) { claimId ->
                        navController.navigate("claim_review/$claimId")
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClaimListContent(
    padding: PaddingValues,
    searchQuery: String,
    onSearchChange: (String) -> Unit,
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
            Triple("CLM-240001-0021", "ET-240001-0001", "Pending"),
            Triple("CLM-1002", "ET-240801-0002", "Approved"),
            Triple("CLM-1003", "ET-340801-0003", "Rejected"),
            Triple("CLM-1004", "ET-140801-0004", "Pending"),
            Triple("CLM-1005", "ET-540801-0005", "Approved")
        )

        val filteredClaims = mockClaims.filter { 
            it.first.contains(searchQuery, ignoreCase = true) || it.second.contains(searchQuery, ignoreCase = true)
        }

        LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(filteredClaims) { claim ->
                Card(
                    onClick = { onClaimClick(claim.first) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Surface(modifier = Modifier.size(50.dp), shape = RoundedCornerShape(12.dp), color = PrimaryGreen.copy(alpha = 0.1f)) {
                            Box(contentAlignment = Alignment.Center) { Icon(Icons.AutoMirrored.Filled.Assignment, null, tint = PrimaryGreen) }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(claim.first, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text(claim.second, fontSize = 14.sp, color = Color.Gray)
                            Text(
                                when(claim.third) {
                                    "Pending" -> languageState.value.getT("Verification in Progress", "सत्यापन प्रगति पर है", "ଯାଞ୍ଚ ଚାଲୁଛି")
                                    "Approved" -> languageState.value.getT("Claim Approved", "दावा स्वीकृत", "ଦାବି ଅନୁମୋଦିତ")
                                    else -> languageState.value.getT("Claim Rejected", "दावा अस्वीकृत", "ଦାବି ପ୍ରତ୍ୟାଖ୍ୟାନ")
                                },
                                fontSize = 12.sp,
                                color = when(claim.third) {
                                    "Approved" -> SuccessGreen
                                    "Rejected" -> Color.Red
                                    else -> AccentOrange
                                },
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Surface(
                            color = when(claim.third) {
                                "Approved" -> SuccessGreen.copy(alpha = 0.1f)
                                "Rejected" -> Color.Red.copy(alpha = 0.1f)
                                else -> AccentOrange.copy(alpha = 0.1f)
                            },
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                when(claim.third) {
                                    "Pending" -> languageState.value.getT("Pending", "लंबित", "ବାକି")
                                    "Approved" -> languageState.value.getT("Approved", "स्वीकृत", "ଅନୁମୋଦିତ")
                                    else -> languageState.value.getT("Rejected", "अस्वीकृत", "ପ୍ରତ୍ୟାଖ୍ୟାନ")
                                },
                                color = when(claim.third) {
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
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClaimReviewScreen(navController: NavHostController, claimId: String, onBack: () -> Unit) {
    val languageState = LocalAppLanguage.current
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Details", "Photos (6)", "AI Assessment", "History")

    val mockClaims = listOf(
        Triple("CLM-240001-0021", "ET-240001-0001", "Ramesh Naik"),
        Triple("CLM-1002", "ET-240801-0002", "Suresh Behera"),
        Triple("CLM-1003", "ET-340801-0003", "Manoj Sahoo"),
        Triple("CLM-1004", "ET-140801-0004", "Alok Dash"),
        Triple("CLM-1005", "ET-540801-0005", "Prakash Rout")
    )
    val claim = mockClaims.find { it.first == claimId } ?: Triple(claimId, "Unknown", "Unknown")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(languageState.value.getT("Claim Review", "दावा समीक्षा", "ଦାବି ସମୀକ୍ଷା"), fontWeight = FontWeight.Bold) },
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
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            // Header Card
            Card(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Surface(modifier = Modifier.size(60.dp), shape = CircleShape, color = Color.LightGray) {
                        Box(contentAlignment = Alignment.Center) { Icon(Icons.Default.Person, null, modifier = Modifier.size(30.dp)) }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(claim.first, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text("Filed on: 15 Jun 2024", color = Color.Gray, fontSize = 14.sp)
                    }
                }
            }

            // Tabs
            ScrollableTabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color.Transparent,
                edgePadding = 16.dp,
                divider = {},
                indicator = {}
            ) {
                tabs.forEachIndexed { index, title ->
                    val isSelected = selectedTab == index
                    Tab(
                        selected = isSelected,
                        onClick = { selectedTab = index },
                        modifier = Modifier.padding(horizontal = 4.dp)
                    ) {
                        Surface(
                            color = if (isSelected) PrimaryGreen else Color.White,
                            shape = RoundedCornerShape(20.dp),
                            border = BorderStroke(1.dp, if (isSelected) PrimaryGreen else Color.LightGray.copy(alpha = 0.5f)),
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Text(
                                text = title,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                                color = if (isSelected) Color.White else Color.Black,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            // Details Content
            Column(modifier = Modifier.weight(1f).padding(horizontal = 24.dp).verticalScroll(rememberScrollState())) {
                Spacer(modifier = Modifier.height(16.dp))
                ClaimDetailRow("Goat", claim.second)
                ClaimDetailRow("Farmer", claim.third)
                ClaimDetailRow("Date of Death", "15 Jun 2024")
                ClaimDetailRow("Cause (Didi)", "Sudden Death")
                ClaimDetailRow("AI Assessment", "PPR (High Confidence)", valueColor = AccentOrange)
                ClaimDetailRow("Risk Score", "Low Risk (12/100)", valueColor = SuccessGreen)
                ClaimDetailRow("Claim Amount", "₹ 8,000")
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Bottom Buttons
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { /* Approve */ },
                    modifier = Modifier.weight(1f).height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen)
                ) { Text("Approve", fontWeight = FontWeight.Bold) }
                
                Button(
                    onClick = { /* Reject */ },
                    modifier = Modifier.weight(1f).height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) { Text("Reject", fontWeight = FontWeight.Bold) }
                
                Button(
                    onClick = { /* Hold */ },
                    modifier = Modifier.weight(1f).height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AccentOrange)
                ) { Text("Hold", fontWeight = FontWeight.Bold) }
            }
        }
    }
}

@Composable
fun ClaimDetailRow(label: String, value: String, valueColor: Color = Color.Black) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.Gray, fontSize = 15.sp)
        Text(value, color = valueColor, fontWeight = FontWeight.Bold, fontSize = 15.sp)
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
                            onClick = { imagePickerLauncher.launch("image/*") }
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

            // Section 2: Location
            ProfileSetupSection(languageState.value.getT("Location", "स्थान", "ଅବସ୍ଥାନ"), themeColor) {
                EnrollmentTextField(label = languageState.value.getT("State *", "राज्य *", "ରାଜ୍ୟ *"), value = state, onValueChange = { if (it.all { char -> char.isLetter() || char.isWhitespace() }) state = it }, borderColor = themeColor)
                Spacer(modifier = Modifier.height(12.dp))
                EnrollmentTextField(label = languageState.value.getT("District *", "जिला *", "ଜିଲ୍ଲା *"), value = district, onValueChange = { if (it.all { char -> char.isLetter() || char.isWhitespace() }) district = it }, borderColor = themeColor)
                Spacer(modifier = Modifier.height(12.dp))
                EnrollmentTextField(label = languageState.value.getT("Block *", "ब्लॉक *", "ବ୍ଲକ *"), value = block, onValueChange = { if (it.all { char -> char.isLetter() || char.isWhitespace() }) block = it }, borderColor = themeColor)
                Spacer(modifier = Modifier.height(12.dp))
                EnrollmentTextField(label = locationLabel, value = village, onValueChange = { if (it.all { char -> char.isLetter() || char.isWhitespace() }) village = it }, borderColor = themeColor)
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
                    onClick = { aadhaarPickerLauncher.launch("image/*") }
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
                Text(languageState.value.getT("Save & Continue", "सहेजें और जारी रखें", "ସଂରକ୍ଷଣ ଏବଂ ଜାରି ରଖନ୍ତୁ"), fontWeight = FontWeight.Bold, fontSize = 16.sp)
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
}





