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
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector


import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import coil.compose.AsyncImage
import java.io.File
import java.io.FileOutputStream
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import kotlinx.coroutines.launch

val LocalWindowSizeClass = staticCompositionLocalOf<WindowSizeClass?> { null }
val LocalAppLanguage = compositionLocalOf { mutableStateOf(AppLanguage.ENGLISH) }
val LocalProfileImage = compositionLocalOf { mutableStateOf<Uri?>(null) }
val LocalNotificationsEnabled = compositionLocalOf { mutableStateOf(true) }

@Composable
fun AppNavigation(navController: NavHostController, sessionManager: SessionManager) {
    val scope = rememberCoroutineScope()
    val userRole by sessionManager.userRole.collectAsState(initial = null)
    
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
                onSignUpSuccess = { role ->
                    scope.launch { sessionManager.saveSession(role) }
                    val route = when(role) {
                        UserRole.SURAKSHA_DIDI -> "didi_dashboard"
                        UserRole.FARMER -> "farmer_dashboard"
                        UserRole.COORDINATOR -> "coordinator_dashboard"
                    }
                    navController.navigate(route) { popUpTo("signup") { inclusive = true } }
                },
                onNavigateToLogin = { navController.popBackStack() }
            )
        }
        composable("didi_dashboard") { DidiDashboard(navController) }
        composable("farmer_dashboard") { FarmerDashboard(navController) }
        composable("coordinator_dashboard") { CoordinatorDashboard(navController) }
        composable("enrollment") { EnrollmentStepper(onComplete = { navController.popBackStack() }) }
        composable("premium_collection") { PremiumCollectionScreen(onComplete = { navController.popBackStack() }) }
        composable("mortality_report") { MortalityReportScreen(onComplete = { navController.popBackStack() }) }
        composable("claim_tracker") { ClaimStatusTracker() }
        composable("profile") { 
            ProfileScreen(
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
fun SignUpScreen(onSignUpSuccess: (UserRole) -> Unit, onNavigateToLogin: () -> Unit) {
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
                        onValueChange = { name = it },
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
                    OtpInput(otp, { otp = it }, onDone = { if (otp.length == 6) onSignUpSuccess(selectedRole!!) })
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { onSignUpSuccess(selectedRole!!) },
                        enabled = otp.length == 6,
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
                    ) { Text(languageState.value.getT("Verify & Sign Up", "सत्यापित करें और साइन अप", "ଯାଞ୍ଚ ଏବଂ ସାଇନ୍ ଅପ୍"), fontSize = 16.sp, fontWeight = FontWeight.Bold) }
                    
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
fun DidiDashboard(navController: NavHostController) {
    var showNotifications by remember { mutableStateOf(false) }
    if (showNotifications) NotificationSheet { showNotifications = false }
    val languageState = LocalAppLanguage.current

    ResponsiveLayout(
        compact = {
            Scaffold(
                modifier = Modifier.fillMaxSize().navigationBarsPadding(),
                bottomBar = { DidiBottomBar(navController) },
                contentWindowInsets = WindowInsets(0, 0, 0, 0)
            ) { padding ->
                DidiContent(padding, navController) { showNotifications = true }
            }
        },
        expanded = {
            Row(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing).navigationBarsPadding()) {
                NavigationRail {
                    NavigationRailItem(selected = true, onClick = {}, icon = { Icon(Icons.Default.Home, null) }, label = { Text(languageState.value.getT("Home", "होम", "ମୁଖ୍ୟ ପୃଷ୍ଠା")) })
                    NavigationRailItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Pets, null) }, label = { Text(languageState.value.getT("Goats", "बकरियां", "ଛେଳି")) })
                    NavigationRailItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Vaccines, null) }, label = { Text(languageState.value.getT("Vaccines", "टीकाकरण", "ଟୀକା")) })
                }
                DidiContent(PaddingValues(0.dp), navController) { showNotifications = true }
            }
        }
    )
}

@Composable
fun DidiContent(padding: PaddingValues, navController: NavHostController, onNotificationClick: () -> Unit) {
    val languageState = LocalAppLanguage.current
    Column(
        modifier = Modifier
            .padding(bottom = padding.calculateBottomPadding())
            .fillMaxSize()
            .background(Color(0xFFF8F9F5))
    ) {
        DashboardHeader(
            languageState.value.getT("Sushma Didi", "सुषमा दीदी", "ସୁଷମା ଦିଦି"),
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
                    1 -> QuickActionGridCard(languageState.value.getT("Vaccination", "टीकाकरण", "ଟୀକାକରଣ"), Icons.Default.MedicalServices, InfoBlue, CardLightBlue) { /* TODO */ }
                    2 -> QuickActionGridCard(languageState.value.getT("Mortality Report", "मृत्यु रिपोर्ट", "ମୃତ୍ୟୁ ରିପୋର୍ଟ"), Icons.Default.LocationOn, Color(0xFFD32F2F), CardLightRed) { navController.navigate("mortality_report") }
                    3 -> QuickActionGridCard(languageState.value.getT("Claims", "दावे", "ଦାବି"), Icons.AutoMirrored.Filled.Assignment, AccentOrange, CardLightOrange) { navController.navigate("claim_tracker") }
                    4 -> QuickActionGridCard(languageState.value.getT("Goat List", "बकरियों की सूची", "ଛେଳି ତାଲିକା"), Icons.AutoMirrored.Filled.FactCheck, Color(0xFF2E7D32), CardLightGreen) { /* TODO */ }
                    5 -> QuickActionGridCard(languageState.value.getT("AI Assistant", "AI सहायक", "AI ସହାୟକ"), Icons.Default.AccountBox, Color(0xFF7B1FA2), CardLightPurple) { /* TODO */ }
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
fun EnrollmentStepper(onComplete: () -> Unit) {
    var currentStep by remember { mutableIntStateOf(1) }
    val languageState = LocalAppLanguage.current
    
    // Form State
    var farmerName by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var village by remember { mutableStateOf("") }
    var aadhaar by remember { mutableStateOf("") }
    
    var breed by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var colorMarks by remember { mutableStateOf("") }
    
    var earTagNumber by remember { mutableStateOf("") }
    
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
                    IconButton(onClick = { if (currentStep > 1) currentStep-- else onComplete() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().background(Color.White).padding(horizontal = 20.dp)) {
            StepProgressIndicator(currentStep, 7)
            
            Column(modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())) {
                Text(
                    text = steps[currentStep-1],
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                when(currentStep) {
                    1 -> EnrollmentFarmerStep(farmerName, { farmerName = it }, mobileNumber, { mobileNumber = it }, village, { village = it }, aadhaar, { aadhaar = it })
                    2 -> EnrollmentGoatStep(breed, { breed = it }, gender, { gender = it }, age, { age = it }, weight, { weight = it }, colorMarks, { colorMarks = it })
                    3 -> EnrollmentPhotoStep()
                    4 -> EnrollmentTaggingStep(earTagNumber) { earTagNumber = it }
                    5 -> EnrollmentVaccinationStep()
                    6 -> EnrollmentPaymentStep()
                    7 -> EnrollmentPolicyStep(farmerName, earTagNumber)
                }
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
                    modifier = Modifier.weight(1f).height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
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
fun EnrollmentFarmerStep(name: String, onNameChange: (String) -> Unit, phone: String, onPhoneChange: (String) -> Unit, village: String, onVillageChange: (String) -> Unit, aadhaar: String, onAadhaarChange: (String) -> Unit) {
    val languageState = LocalAppLanguage.current
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        EnrollmentTextField(label = languageState.value.getT("Farmer Name *", "किसान का नाम *", "କୃଷକଙ୍କ ନାମ *"), value = name, onValueChange = onNameChange, placeholder = "Full Name")
        EnrollmentTextField(label = languageState.value.getT("Mobile Number *", "मोबाइल नंबर *", "ମୋବାଇଲ୍ ନମ୍ବର *"), value = phone, onValueChange = onPhoneChange, placeholder = "10-digit number", keyboardType = KeyboardType.Phone)
        EnrollmentTextField(label = languageState.value.getT("Village", "गाँव", "ଗ୍ରାମ"), value = village, onValueChange = onVillageChange, placeholder = "Village Name")
        EnrollmentTextField(label = languageState.value.getT("Aadhaar / Gov ID", "आधार / सरकारी आईडी", "ଆଧାର / ସରକାରୀ ID"), value = aadhaar, onValueChange = onAadhaarChange, placeholder = "12-digit number", keyboardType = KeyboardType.Number)
    }
}

@Composable
fun EnrollmentGoatStep(breed: String, onBreedChange: (String) -> Unit, gender: String, onGenderChange: (String) -> Unit, age: String, onAgeChange: (String) -> Unit, weight: String, onWeightChange: (String) -> Unit, color: String, onColorChange: (String) -> Unit) {
    val languageState = LocalAppLanguage.current
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        EnrollmentTextField(label = languageState.value.getT("Breed", "नस्ल", "ପ୍ରଜାତି"), value = breed, onValueChange = onBreedChange, placeholder = "Black Bengal")
        EnrollmentTextField(label = languageState.value.getT("Gender", "लिंग", "ଲିଙ୍ଗ"), value = gender, onValueChange = onGenderChange, placeholder = "Female")
        EnrollmentTextField(label = languageState.value.getT("Age (Approx)", "आयु (लगभग)", "ବୟସ (ପ୍ରାୟ)"), value = age, onValueChange = onAgeChange, placeholder = "12 Months")
        EnrollmentTextField(label = languageState.value.getT("Weight (Approx)", "वजन (लगभग)", "ଓଜନ (ପ୍ରାୟ)"), value = weight, onValueChange = onWeightChange, placeholder = "18 KG")
        EnrollmentTextField(label = languageState.value.getT("Color / Marks", "रंग / निशान", "ରଙ୍ଗ / ଚିହ୍ନ"), value = color, onValueChange = onColorChange, placeholder = "Black with White Spots")
    }
}

@Composable
fun EnrollmentPhotoStep() {
    val languageState = LocalAppLanguage.current
    Column {
        Text(
            languageState.value.getT("Please upload 4 mandatory photos of the goat.", "कृपया बकरी की 4 अनिवार्य फोटो अपलोड करें।", "ଦୟାକରି ଛେଳିର ୪ଟି ବାଧ୍ୟତାମୂଳକ ଫଟୋ ଅପଲୋଡ୍ କରନ୍ତୁ |"),
            style = MaterialTheme.typography.bodyMedium, color = Color.Gray
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            PhotoCaptureBox(languageState.value.getT("Left Side", "बाईं ओर", "ବାମ ପାର୍ଶ୍ୱ"), modifier = Modifier.weight(1f))
            PhotoCaptureBox(languageState.value.getT("Right Side", "दाईं ओर", "ଡାହାଣ ପାର୍ଶ୍ୱ"), modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            PhotoCaptureBox(languageState.value.getT("Front View", "सामने का दृश्य", "ସମ୍ମୁଖ ଦୃଶ୍ୟ"), modifier = Modifier.weight(1f))
            PhotoCaptureBox(languageState.value.getT("Ear Tag", "कान का टैग", "କାନ ଟ୍ୟାଗ୍"), modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun EnrollmentTaggingStep(earTag: String, onTagChange: (String) -> Unit) {
    val languageState = LocalAppLanguage.current
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        EnrollmentTextField(label = languageState.value.getT("Ear Tag Number *", "कान का टैग नंबर *", "କାନ ଟ୍ୟାଗ୍ ନମ୍ବର *"), value = earTag, onValueChange = onTagChange, placeholder = "e.g. ET-240801")
        Spacer(modifier = Modifier.height(32.dp))
        Surface(
            onClick = { /* Scan Tag */ },
            modifier = Modifier.size(120.dp),
            shape = RoundedCornerShape(20.dp),
            color = Color.White,
            border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f)),
            shadowElevation = 2.dp
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Icon(Icons.Default.QrCodeScanner, null, tint = PrimaryGreen, modifier = Modifier.size(48.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Text(languageState.value.getT("Scan Tag", "टैग स्कैन करें", "ଟ୍ୟାଗ୍ ସ୍କାନ୍"), color = PrimaryGreen, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun EnrollmentVaccinationStep() {
    val languageState = LocalAppLanguage.current
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        VaccineStatusItem("PPR Vaccine", true)
        VaccineStatusItem("ET + TT Vaccine", true)
        VaccineStatusItem("FMD Vaccine", false)
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
        ) {
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.CalendarToday, null, tint = Color.Gray, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(languageState.value.getT("Next Vaccination Due", "अगला टीकाकरण देय", "ପରବର୍ତ୍ତୀ ଟୀକାକରଣ ବାକି"), fontSize = 12.sp, color = Color.Gray)
                    Text("15 Aug 2024", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun EnrollmentPaymentStep() {
    val languageState = LocalAppLanguage.current
    Column {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9))
        ) {
            Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(languageState.value.getT("Total Premium Amount", "कुल प्रीमियम राशि", "ମୋଟ ପ୍ରିମିୟମ ପରିମାଣ"), fontSize = 14.sp, color = Color.Gray)
                Text("₹ 350", style = MaterialTheme.typography.headlineLarge, color = PrimaryGreen, fontWeight = FontWeight.Bold)
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        Text(languageState.value.getT("Select Payment Method", "भुगतान विधि चुनें", "ଦେୟ ପଦ୍ଧତି ଚୟନ କରନ୍ତୁ"), fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            PaymentMethodChip("Cash", true, modifier = Modifier.weight(1f))
            PaymentMethodChip("UPI", false, modifier = Modifier.weight(1f))
            PaymentMethodChip("Wallet", false, modifier = Modifier.weight(1f))
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
fun EnrollmentTextField(label: String, value: String, onValueChange: (String) -> Unit, placeholder: String = "", keyboardType: KeyboardType = KeyboardType.Text, prefix: String? = null, leadingIcon: ImageVector? = null) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(label, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(bottom = 8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(placeholder, color = Color.Gray) },
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = true,
            leadingIcon = leadingIcon?.let { { Icon(it, null, tint = Color.DarkGray) } },
            prefix = prefix?.let { { Text(it, color = Color.Black) } },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
                focusedBorderColor = PrimaryGreen
            )
        )
    }
}

@Composable
fun PhotoCaptureBox(label: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            onClick = { /* Capture */ },
            modifier = Modifier.aspectRatio(1.2f).fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Icon(Icons.Default.AddAPhoto, null, tint = Color.Gray, modifier = Modifier.size(32.dp))
                Spacer(modifier = Modifier.height(4.dp))
                Text("Capture", fontSize = 11.sp, color = Color.Gray)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(label, fontWeight = FontWeight.Bold, fontSize = 13.sp)
    }
}

@Composable
fun VaccineStatusItem(name: String, isGiven: Boolean) {
    val languageState = LocalAppLanguage.current
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Text(name, fontWeight = FontWeight.Bold, color = Color.Black)
            Surface(
                color = if (isGiven) Color(0xFFE8F5E9) else Color(0xFFFFF3E0),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = if (isGiven) languageState.value.getT("Given", "दिया गया", "ଦିଆଯାଇଛି") 
                           else languageState.value.getT("Pending", "लंबित", "ବାକି ଅଛି"),
                    color = if (isGiven) SuccessGreen else AccentOrange,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
fun PaymentMethodChip(label: String, isSelected: Boolean, modifier: Modifier = Modifier) {
    Surface(
        onClick = { /* Select */ },
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
fun FarmerDashboard(navController: NavHostController) {
    var showNotifications by remember { mutableStateOf(value = false) }
    if (showNotifications) NotificationSheet { showNotifications = false }
    val languageState = LocalAppLanguage.current

    Scaffold(
        modifier = Modifier.fillMaxSize().navigationBarsPadding(),
        bottomBar = { FarmerBottomBar(navController) },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(bottom = padding.calculateBottomPadding())
                .fillMaxSize()
                .background(Color(0xFFF8F9F5))
        ) {
            DashboardHeader(
                languageState.value.getT("Farmer Ram", "किसान राम", "କୃଷକ ରାମ"),
                languageState.value.getT("Farmer", "किसान", "କୃଷକ"),
                onNotificationClick = { showNotifications = true },
            )

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 300.dp),
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Text(languageState.value.getT("My Policies", "मेरी नीतियां", "ମୋର ନୀତିଗୁଡ଼ିକ"), style = MaterialTheme.typography.titleMedium, color = Color.Black, fontWeight = FontWeight.Bold)
                }
                
                item { PolicyCard("TAG-12345", languageState.value.getT("Active", "सक्रिय", "ସକ୍ରିୟ"), languageState.value.getT("Jan 2026", "जनवरी 2026", "ଜାନୁଆରୀ ୨୦୨୬")) }
                item { PolicyCard("TAG-67890", languageState.value.getT("Active", "सक्रिय", "ସକ୍ରିୟ"), languageState.value.getT("Feb 2026", "फरवरी 2026", "ଫେବୃଆରୀ ୨୦୨୬")) }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = InfoBlue.copy(alpha = 0.1f))) {
                        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Info, null, tint = InfoBlue)
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(languageState.value.getT("Report death within 24 hours to ensure claim eligibility.", "दावा पात्रता सुनिश्चित करने के लिए 24 घंटे के भीतर मृत्यु की रिपोर्ट करें।", "ଦାବି ଯୋଗ୍ୟତା ନିଶ୍ଚିତ କରିବାକୁ ୨୪ ଘଣ୍ଟା ମଧ୍ୟରେ ମୃତ୍ୟୁ ରିପୋର୍ଟ କରନ୍ତୁ |"), color = Color.Black)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CoordinatorDashboard(navController: NavHostController) {
    var showNotifications by remember { mutableStateOf(false) }
    if (showNotifications) NotificationSheet { showNotifications = false }
    val languageState = LocalAppLanguage.current

    Scaffold(
        modifier = Modifier.fillMaxSize().navigationBarsPadding(),
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(bottom = padding.calculateBottomPadding())
                .fillMaxSize()
                .background(Color(0xFFF8F9F5))
        ) {
            DashboardHeader(
                languageState.value.getT("Cluster Coordinator", "क्लस्टर समन्वयक", "କ୍ଲଷ୍ଟର ସମନ୍ଵୟକାରୀ"),
                languageState.value.getT("Coordinator", "समन्वयक", "ସମନ୍ଵୟକାରୀ"),
                onNotificationClick = { showNotifications = true },
            )

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 300.dp),
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item { StatCard(languageState.value.getT("Pending Approvals", "लंबित स्वीकृतियां", "ବାକି ରହିଥିବା ଅନୁମୋଦନ"), "14", Icons.Default.Gavel, PrimaryGreen, CardLightGreen) }
                item { StatCard(languageState.value.getT("Corpus Fund", "कॉर्पस फंड", "କର୍ପସ ପାଣ୍ଠି"), "₹85,000", Icons.Default.AccountBalance, InfoBlue, CardLightBlue) }
                item { StatCard(languageState.value.getT("Mortality Rate", "मृत्यु दर", "ମୃତ୍ୟୁ ହାର"), "2.4%", Icons.AutoMirrored.Filled.TrendingUp, ErrorRed, CardLightRed) }
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
fun DidiBottomBar(@Suppress("UNUSED_PARAMETER") navController: NavHostController) {
    val languageState = LocalAppLanguage.current
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text(languageState.value.getT("Home", "होम", "ମୁଖ୍ୟ ପୃଷ୍ଠା"), fontSize = 9.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryGreen,
                selectedTextColor = PrimaryGreen,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Pets, null) }, label = { Text(languageState.value.getT("Goats", "बकरियां", "ଛେଳି"), fontSize = 9.sp) })
        NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.MedicalServices, null) }, label = { Text(languageState.value.getT("Vaccines", "टीकाकरण", "ଟୀକା"), fontSize = 9.sp) })
        NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.AutoMirrored.Filled.Assignment, null) }, label = { Text(languageState.value.getT("Claims", "दावे", "ଦାବି"), fontSize = 9.sp) })
        NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.MoreHoriz, null) }, label = { Text(languageState.value.getT("More", "अधिक", "ଅଧିକ"), fontSize = 9.sp) })
    }
}

@Composable
fun FarmerBottomBar(navController: NavHostController) {
    val languageState = LocalAppLanguage.current
    NavigationBar {
        NavigationBarItem(selected = true, onClick = {}, icon = { Icon(Icons.Default.Home, null) }, label = { Text(languageState.value.getT("Home", "होम", "ମୁଖ୍ୟ ପୃଷ୍ଠା")) })
        NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.History, null) }, label = { Text(languageState.value.getT("Claims", "दावे", "ଦାବି")) })
        NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.SupportAgent, null) }, label = { Text(languageState.value.getT("Help", "सहायता", "ସାହାଯ୍ୟ")) })
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
fun NotificationSheet(onDismiss: () -> Unit) {
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
                    NotificationItem(notification)
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
fun NotificationItem(notification: AppNotification) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(PrimaryGreen.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.Notifications,
                contentDescription = null,
                tint = PrimaryGreen,
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
                color = PrimaryGreen,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onLogout: () -> Unit, onBack: () -> Unit) {
    val backgroundColor = Color(0xFFF8F9F5)
    val languageState = LocalAppLanguage.current
    var showLanguagePicker by remember { mutableStateOf(false) }
    
    val context = LocalContext.current
    val profileImageState = LocalProfileImage.current

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
        // Green Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                .background(PrimaryGreen)
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
                            Icon(Icons.Default.Edit, null, tint = PrimaryGreen, modifier = Modifier.size(16.dp))
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    languageState.value.getT("Sushma Didi", "सुषमा दीदी", "ସୁଷମା ଦିଦି"),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    "+91 98765 43210",
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
            ProfileInfoSection(languageState.value.getT("Account Details", "खाता विवरण", "ଖାତା ବିବରଣୀ")) {
                ProfileInfoItem(
                    languageState.value.getT("Full Name", "पूरा नाम", "ପୁରା ନାମ"),
                    languageState.value.getT("Sushma Didi", "सुषमा दीदी", "ସୁଷମା ଦିଦି")
                )
                ProfileInfoItem(
                    languageState.value.getT("Role", "भूमिका", "ଭୂମିକା"),
                    languageState.value.getT("Suraksha Didi", "सुरक्षा दीदी", "ସୁରକ୍ଷା ଦିଦି")
                )
                ProfileInfoItem(
                    languageState.value.getT("Village", "गाँव", "ଗ୍ରାମ"),
                    languageState.value.getT("Gopalpur, Odisha", "गोपालपुर, ओडिशा", "ଗୋପାଳପୁର, ଓଡ଼ିଶା")
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            ProfileInfoSection(languageState.value.getT("App Settings", "ऐप सेटिंग्स", "ଆପ୍ ସେଟିଙ୍ଗ୍ସ")) {
                val notificationsEnabled = LocalNotificationsEnabled.current
                SettingsToggleItem(
                    label = languageState.value.getT("Enable Notifications", "सूचनाएं सक्षम करें", "ବିଜ୍ଞପ୍ତି ସକ୍ଷମ କରନ୍ତୁ"),
                    checked = notificationsEnabled.value,
                    onCheckedChange = { notificationsEnabled.value = it }
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
            
            ProfileInfoSection(languageState.value.getT("Support & Legal", "समर्थन और कानूनी", "ସମର୍ଥନ ଏବଂ ଆଇନଗତ")) {
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
fun ProfileInfoSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = PrimaryGreen)
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
        Text(value, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SettingsToggleItem(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, fontWeight = FontWeight.Medium)
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(checkedThumbColor = PrimaryGreen, checkedTrackColor = PrimaryGreen.copy(alpha = 0.3f))
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
