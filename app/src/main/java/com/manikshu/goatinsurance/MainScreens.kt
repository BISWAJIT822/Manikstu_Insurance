package com.manikshu.goatinsurance

import androidx.compose.animation.*
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
import androidx.compose.material.icons.filled.*
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.hilt.navigation.compose.hiltViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.coroutines.launch

val LocalWindowSizeClass = staticCompositionLocalOf<WindowSizeClass?> { null }
val LocalAppLanguage = compositionLocalOf<MutableState<AppLanguage>> { error("No Language State provided") }
val LocalProfileImage = compositionLocalOf<MutableState<android.net.Uri?>> { error("No Profile Image State provided") }

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { 
            LoginScreen(
                onLoginSuccess = { role ->
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
        composable("farmer_dashboard") { 
            val farmerViewModel: FarmerViewModel = hiltViewModel()
            FarmerDashboard(navController, farmerViewModel) 
        }
        composable("coordinator_dashboard") { CoordinatorDashboard(navController) }
        composable("enrollment") { EnrollmentStepper(onComplete = { navController.popBackStack() }) }
        composable("premium_collection") { PremiumCollectionScreen(onComplete = { navController.popBackStack() }) }
        composable("mortality_report") { MortalityReportScreen(onComplete = { navController.popBackStack() }) }
        composable("claim_tracker") { ClaimStatusTracker() }
        composable("death_notification") { 
            val viewModel: DeathNotificationViewModel = hiltViewModel()
            DeathNotificationScreen(navController, viewModel) 
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
    expanded: @Composable () -> Unit
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
    var currentLanguage by remember { mutableStateOf("EN") }
    var showLanguagePicker by remember { mutableStateOf(false) }

    val backgroundColor = Color(0xFFF8F9F5)
    
    fun getT(en: String, hi: String, or: String): String {
        return when(currentLanguage) {
            "HI" -> hi
            "OR" -> or
            else -> en
        }
    }

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
                    Text(currentLanguage, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                }

                MaterialTheme(
                    colorScheme = MaterialTheme.colorScheme.copy(surface = Color.White.copy(alpha = 0.9f))
                ) {
                    DropdownMenu(
                        expanded = showLanguagePicker,
                        onDismissRequest = { showLanguagePicker = false }
                    ) {
                        DropdownMenuItem(text = { Text("English") }, onClick = { currentLanguage = "EN"; showLanguagePicker = false })
                        DropdownMenuItem(text = { Text("हिन्दी") }, onClick = { currentLanguage = "HI"; showLanguagePicker = false })
                        DropdownMenuItem(text = { Text("ଓଡ଼ିଆ") }, onClick = { currentLanguage = "OR"; showLanguagePicker = false })
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
                    getT("GOAT INSURANCE", "बकरी बीमा", "ଛେଳି ବୀମା"),
                    style = MaterialTheme.typography.headlineSmall, color = Color.White, fontWeight = FontWeight.Bold, letterSpacing = 1.sp
                )
                Text(
                    getT("Community Livestock Protection", "सामुदायिक पशुधन संरक्षण", "ଗୋଷ୍ଠୀ ପ୍ରାଣୀସମ୍ପଦ ସୁରକ୍ଷା"),
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
                    getT("Create Account", "खाता बनाएं", "ଖାତା ଖୋଲନ୍ତୁ"),
                    style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold
                )
                Text(
                    getT("Register to start protecting livestock", "पशुधन की सुरक्षा शुरू करने के लिए पंजीकरण करें", "ପ୍ରାଣୀସମ୍ପଦ ସୁରକ୍ଷା ଆରମ୍ଭ କରିବାକୁ ପଞ୍ଜିକରଣ କରନ୍ତୁ"),
                    style = MaterialTheme.typography.bodyMedium, color = Color.Gray, textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(32.dp))

                if (step == 1) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        placeholder = { Text(getT("Full Name", "पूरा नाम", "ପୁରା ନାମ"), color = Color.Gray) },
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
                        placeholder = { Text(getT("Mobile Number", "मोबाइल नंबर", "ମୋବାଇଲ୍ ନମ୍ବର"), color = Color.Gray) },
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
                                Toast.makeText(context, getT("Scroll down and choose a role", "नीचे स्क्रॉल करें और एक भूमिका चुनें", "ତଳକୁ ସ୍କ୍ରୋଲ୍ କରନ୍ତୁ ଏବଂ ଏକ ଭୂମିକା ବାଛନ୍ତୁ"), Toast.LENGTH_SHORT).show()
                            } else {
                                step = 2 
                            }
                        },
                        enabled = name.isNotBlank() && phone.length == 10,
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen, disabledContainerColor = PrimaryGreen.copy(alpha = 0.5f))
                    ) {
                        Text(getT("Send OTP", "ओटीपी भेजें", "ଓଟିପି ପଠାନ୍ତୁ"), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = getT("Already have an account? Login", "पहले से खाता है? लॉगिन करें", "ପୂର୍ବରୁ ଖାତା ଅଛି? ଲଗଇନ୍"),
                        color = PrimaryGreen, fontWeight = FontWeight.Bold, fontSize = 14.sp,
                        modifier = Modifier.clickable { onNavigateToLogin() }
                    )
                } else {
                    Text(getT("Enter 6-digit OTP sent to +91 $phone", "+91 $phone पर भेजा गया ओटीपी दर्ज करें", "+91 $phone କୁ ପଠାଯାଇଥିବା ଓଟିପି ଦିଅନ୍ତୁ"), style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(24.dp))
                    OtpInput(otp, { otp = it }, onDone = { if (otp.length == 6) onSignUpSuccess(selectedRole!!) })
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { onSignUpSuccess(selectedRole!!) },
                        enabled = otp.length == 6,
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
                    ) { Text(getT("Verify & Sign Up", "सत्यापित करें और साइन अप", "ଯାଞ୍ଚ ଏବଂ ସାଇନ୍ ଅପ୍"), fontSize = 16.sp, fontWeight = FontWeight.Bold) }
                    
                    TextButton(onClick = { step = 1 }) { Text(getT("Go Back", "वापस जाएं", "ପଛକୁ ଯାଆନ୍ତୁ"), color = PrimaryGreen) }
                }
            }
        }

        if (step == 1) {
            // Divider
            Row(modifier = Modifier.padding(horizontal = 24.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray.copy(alpha = 0.5f))
                Text(getT("choose a role", "एक भूमिका चुनें", "ଗୋଟିଏ ଭୂମିକା ବାଛନ୍ତୁ"), modifier = Modifier.padding(horizontal = 16.dp), color = Color.Gray, fontSize = 14.sp)
                HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray.copy(alpha = 0.5f))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Role Selection
            Row(modifier = Modifier.padding(horizontal = 24.dp).fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                RoleCard(UserRole.SURAKSHA_DIDI, getT("Suraksha Didi", "सुरक्षा दीदी", "ସୁରକ୍ଷା ଦିଦି"), Icons.Default.Person, selectedRole == UserRole.SURAKSHA_DIDI, modifier = Modifier.weight(1f)) { selectedRole = it }
                RoleCard(UserRole.FARMER, getT("Farmer", "किसान", "କୃଷକ"), Icons.Default.Agriculture, selectedRole == UserRole.FARMER, modifier = Modifier.weight(1f)) { selectedRole = it }
                RoleCard(UserRole.COORDINATOR, getT("Coordinator", "समन्वयक", "ସମନ୍ଵୟକାରୀ"), Icons.Default.Badge, selectedRole == UserRole.COORDINATOR, modifier = Modifier.weight(1f)) { selectedRole = it }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }

        // Footer
        Row(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(getT("Version 2.0.4", "संस्करण 2.0.4", "ସଂସ୍କରଣ ୨.୦.୪"), color = Color.Gray, fontSize = 12.sp)
            Surface(color = Color(0xFFE8F5E9), shape = RoundedCornerShape(16.dp)) {
                Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFF4CAF50)))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(getT("Online", "ऑनलाइन", "ଅନଲାଇନ୍"), color = Color(0xFF4CAF50), fontSize = 12.sp, fontWeight = FontWeight.Bold)
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
    var currentLanguage by remember { mutableStateOf("EN") }
    var showLanguagePicker by remember { mutableStateOf(false) }

    val backgroundColor = Color(0xFFF8F9F5)
    
    // Translation helper
    fun getT(en: String, hi: String, or: String): String {
        return when(currentLanguage) {
            "HI" -> hi
            "OR" -> or
            else -> en
        }
    }

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
                    Text(currentLanguage, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
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
                                currentLanguage = "EN"
                                showLanguagePicker = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("हिन्दी") },
                            onClick = {
                                currentLanguage = "HI"
                                showLanguagePicker = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("ଓଡ଼ିଆ") },
                            onClick = {
                                currentLanguage = "OR"
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
                    getT("GOAT INSURANCE", "बकरी बीमा", "ଛେଳି ବୀମା"),
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    getT("Community Livestock Protection", "सामुदायिक पशुधन संरक्षण", "ଗୋଷ୍ଠୀ ପ୍ରାଣୀସମ୍ପଦ ସୁରକ୍ଷା"),
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
                    getT("Welcome Back", "वापसी पर स्वागत है", "ପୁଣି ସ୍ଵାଗତ"),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    getT("Login to your account to continue", "जारी रखने के लिए लॉगिन करें", "ଆଗକୁ ବଢିବା ପାଇଁ ଲଗଇନ୍ କରନ୍ତୁ"),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(32.dp))

                if (step == 1) {
                    OutlinedTextField(
                        value = phone,
                        onValueChange = { if (it.length <= 10) phone = it },
                        placeholder = { Text(getT("Mobile Number", "मोबाइल नंबर", "ମୋବାଇଲ୍ ନମ୍ବର"), color = Color.Gray) },
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
                                Toast.makeText(context, getT("Scroll down and choose a role", "नीचे स्क्रॉल करें और एक भूमिका चुनें", "ତଳକୁ ସ୍କ୍ରୋଲ୍ କରନ୍ତୁ ଏବଂ ଏକ ଭୂମିକା ବାଛନ୍ତୁ"), Toast.LENGTH_SHORT).show()
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
                        Text(getT("Send OTP", "ओटीपी भेजें", "ଓଟିପି ପଠାନ୍ତୁ"), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = getT("Don't have an account? Sign Up", "खाता नहीं है? साइन अप करें", "ଖାତା ନାହିଁ? ସାଇନ୍ ଅପ୍"),
                        color = PrimaryGreen,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        modifier = Modifier.clickable { onNavigateToSignUp() }
                    )
                } else {
                    Text(getT("Enter 6-digit OTP sent to +91 $phone", "+91 $phone पर भेजा गया ओटीपी दर्ज करें", "+91 $phone କୁ ପଠାଯାଇଥିବା ଓଟିପି ଦିଅନ୍ତୁ"), style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(24.dp))
                    OtpInput(otp, { otp = it }, onDone = { if (otp.length == 6) onLoginSuccess(selectedRole!!) })
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { onLoginSuccess(selectedRole!!) },
                        enabled = otp.length == 6,
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
                    ) { Text(getT("Verify & Login", "सत्यापित करें और लॉगिन", "ଯାଞ୍ଚ ଏବଂ ଲଗଇନ୍"), fontSize = 16.sp, fontWeight = FontWeight.Bold) }
                    
                    TextButton(onClick = { step = 1 }) { Text(getT("Change Number", "नंबर बदलें", "ନମ୍ବର ବଦଳାନ୍ତୁ"), color = PrimaryGreen) }
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
                    getT("choose a role", "एक भूमिका चुनें", "ଗୋଟିଏ ଭୂମିକା ବାଛନ୍ତୁ"),
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
                RoleCard(UserRole.SURAKSHA_DIDI, getT("Suraksha Didi", "सुरक्षा दीदी", "ସୁରକ୍ଷା ଦିଦି"), Icons.Default.Person, selectedRole == UserRole.SURAKSHA_DIDI, modifier = Modifier.weight(1f)) { selectedRole = it }
                RoleCard(UserRole.FARMER, getT("Farmer", "किसान", "କୃଷକ"), Icons.Default.Agriculture, selectedRole == UserRole.FARMER, modifier = Modifier.weight(1f)) { selectedRole = it }
                RoleCard(UserRole.COORDINATOR, getT("Coordinator", "समन्वयक", "ସମନ୍ଵୟକାରୀ"), Icons.Default.Badge, selectedRole == UserRole.COORDINATOR, modifier = Modifier.weight(1f)) { selectedRole = it }
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
            Text(getT("Version 2.0.4", "संस्करण 2.0.4", "ସଂସ୍କରଣ ୨.୦.୪"), color = Color.Gray, fontSize = 12.sp)
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
                    Text(getT("Online", "ऑनलाइन", "ଅନଲାଇନ୍"), color = Color(0xFF4CAF50), fontSize = 12.sp, fontWeight = FontWeight.Bold)
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
            containerColor = if (isSelected) Color(0xFFE8F5E9) else Color.White
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
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    if (showNotifications) NotificationSheet { showNotifications = false }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ProfileDrawerContent(
                name = "Sushma Didi",
                role = "Suraksha Didi",
                id = "DIDI-12341",
                onClose = { scope.launch { drawerState.close() } }
            )
        }
    ) {
        ResponsiveLayout(
            compact = {
                Scaffold(
                    modifier = Modifier.fillMaxSize().navigationBarsPadding(),
                    bottomBar = { DidiBottomBar(navController) },
                    contentWindowInsets = WindowInsets(0, 0, 0, 0)
                ) { padding ->
                    DidiContent(
                        padding, 
                        navController, 
                        onNotificationClick = { showNotifications = true },
                        onProfileClick = { scope.launch { drawerState.open() } }
                    )
                }
            },
            expanded = {
                Row(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing).navigationBarsPadding()) {
                    NavigationRail {
                        NavigationRailItem(selected = true, onClick = {}, icon = { Icon(Icons.Default.Home, null) }, label = { Text("Home") })
                        NavigationRailItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Pets, null) }, label = { Text("Goats") })
                        NavigationRailItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Vaccines, null) }, label = { Text("Vaccines") })
                    }
                    DidiContent(
                        PaddingValues(0.dp), 
                        navController, 
                        onNotificationClick = { showNotifications = true },
                        onProfileClick = { scope.launch { drawerState.open() } }
                    )
                }
            }
        )
    }
}

@Composable
fun DidiContent(padding: PaddingValues, navController: NavHostController, onNotificationClick: () -> Unit, onProfileClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(bottom = padding.calculateBottomPadding())
            .fillMaxSize()
            .background(Color(0xFFF8F9F5))
    ) {
        DashboardHeader("Sushma Didi", "Suraksha Didi", onNotificationClick, onProfileClick = onProfileClick, hasNotifications = true)
        
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
                        "This Month Overview",
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
                    0 -> StatCard("Goats Enrolled", "128", Icons.Default.Pets, PrimaryGreen, CardLightGreen)
                    1 -> StatCard("Pending Claims", "12", Icons.Default.Assignment, AccentOrange, CardLightOrange)
                    2 -> StatCard("Today's Visits", "24", Icons.Default.CalendarToday, InfoBlue, CardLightBlue)
                    3 -> StatCard("Earnings", "₹8,450", Icons.Default.Payments, Color(0xFF9C27B0), CardLightPurple)
                }
            }

            item(span = { GridItemSpan(gridColumns) }) {
                Column {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "Quick Actions",
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
                    0 -> QuickActionGridCard("Enroll Goat", Icons.Default.Pets, PrimaryGreen, CardLightGreen) { navController.navigate("enrollment") }
                    1 -> QuickActionGridCard("Vaccination", Icons.Default.MedicalServices, InfoBlue, CardLightBlue) { /* TODO */ }
                    2 -> QuickActionGridCard("Mortality Report", Icons.Default.LocationOn, Color(0xFFD32F2F), CardLightRed) { navController.navigate("mortality_report") }
                    3 -> QuickActionGridCard("Claims", Icons.Default.Assignment, AccentOrange, CardLightOrange) { navController.navigate("claim_tracker") }
                    4 -> QuickActionGridCard("Goat List", Icons.Default.FactCheck, Color(0xFF2E7D32), CardLightGreen) { /* TODO */ }
                    5 -> QuickActionGridCard("AI Assistant", Icons.Default.AccountBox, Color(0xFF7B1FA2), CardLightPurple) { /* TODO */ }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PremiumCollectionScreen(onComplete: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize().navigationBarsPadding(),
        topBar = { CenterAlignedTopAppBar(title = { Text("Premium Collection") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Total Premium: ₹350", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    BreakdownItem("Insurance Company", "₹240")
                    BreakdownItem("Vaccination Program", "₹80")
                    BreakdownItem("Suraksha Didi Fee", "₹20")
                    BreakdownItem("FPC Corpus Fund", "₹10")
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = onComplete, modifier = Modifier.fillMaxWidth()) { Text("Confirm Payment & Generate Receipt") }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MortalityReportScreen(onComplete: () -> Unit) {
    var currentStep by remember { mutableIntStateOf(1) }
    Scaffold(
        modifier = Modifier.fillMaxSize().navigationBarsPadding(),
        topBar = { CenterAlignedTopAppBar(title = { Text("Mortality Verification") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            LinearProgressIndicator(progress = currentStep / 4f, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(24.dp))
            when (currentStep) {
                1 -> MortalityStep1()
                2 -> MortalityStep2()
                3 -> MortalityStep3()
                4 -> MortalityStep4()
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { if (currentStep < 4) currentStep++ else onComplete() }, modifier = Modifier.fillMaxWidth()) {
                Text(if (currentStep == 4) "Submit Report" else "Continue")
            }
        }
    }
}

@Composable
fun ClaimStatusTracker() {
    val stages = listOf("Death Reported", "Didi Site Visit", "Verification", "Coordinator Review", "Payment Sent")
    val currentStage = 2
    Column(modifier = Modifier.fillMaxSize().navigationBarsPadding().padding(16.dp)) {
        Text("Claim #CLM7890", style = MaterialTheme.typography.headlineSmall)
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
fun VaccinationItem(type: String, farmer: String, due: String) {
    Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Vaccines, null, tint = AccentOrange)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text("$type Vaccine", fontWeight = FontWeight.Bold)
                Text(farmer, style = MaterialTheme.typography.bodySmall)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(due, color = ErrorRed, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        }
    }
}

@Composable
fun MortalityStep1() {
    Text("Step 1: Carcass Verification", style = MaterialTheme.typography.titleLarge)
    Text("Capture clear photo of the carcass with the ear tag visible.")
    Box(modifier = Modifier.fillMaxWidth().height(200.dp).background(Color.LightGray, RoundedCornerShape(12.dp)), contentAlignment = Alignment.Center) {
        Icon(Icons.Default.CameraAlt, null, modifier = Modifier.size(48.dp))
    }
}

@Composable
fun MortalityStep2() { Text("Step 2: Check Ear Tag & Breed") }
@Composable
fun MortalityStep3() { Text("Step 3: Capture Farmer & Site Photo") }
@Composable
fun MortalityStep4() { Text("Step 4: Cause of Death & Sign") }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnrollmentStepper(onComplete: () -> Unit) {
    var currentStep by remember { mutableIntStateOf(1) }
    val steps = listOf("Farmer Registration", "Goat Details", "Photos", "Ear Tagging", "Vaccination", "Premium", "Policy")

    Scaffold(
        modifier = Modifier.fillMaxSize().navigationBarsPadding(),
        topBar = { CenterAlignedTopAppBar(title = { Text("New Enrollment") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp)) {
            Text("Step $currentStep: ${steps[currentStep-1]}", style = MaterialTheme.typography.headlineSmall, color = PrimaryGreen)
            Spacer(modifier = Modifier.height(16.dp))
            LinearProgressIndicator(progress = currentStep / 7f, modifier = Modifier.fillMaxWidth())
            
            Box(modifier = Modifier.weight(1f).padding(vertical = 24.dp)) {
                when(currentStep) {
                    1 -> EnrollmentFarmerForm()
                    2 -> EnrollmentGoatForm()
                    6 -> PremiumCollectionContent()
                    else -> Text("Field content for ${steps[currentStep-1]} goes here...")
                }
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                if (currentStep > 1) OutlinedButton(onClick = { currentStep-- }) { Text("Back") }
                else Spacer(Modifier.width(8.dp))
                Button(onClick = { if (currentStep < 7) currentStep++ else onComplete() }) {
                    Text(if (currentStep == 7) "Complete" else "Next Step")
                }
            }
        }
    }
}

@Composable
fun EnrollmentFarmerForm() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Farmer Name") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Aadhaar Number") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Village") }, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun EnrollmentGoatForm() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Ear Tag Number") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Breed") }, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun PremiumCollectionContent() {
    Column {
        Text("Collect ₹350 from Farmer", style = MaterialTheme.typography.titleLarge)
        Text("Didi Earning from this: ₹52", color = PrimaryGreen, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun FarmerDashboard(navController: NavHostController, viewModel: FarmerViewModel) {
    val policies by viewModel.policies.collectAsState()
    val nextVaccination by viewModel.nextVaccination.collectAsState()
    
    var showNotifications by remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    if (showNotifications) NotificationSheet { showNotifications = false }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ProfileDrawerContent(
                name = "Ramesh Yadav",
                role = "Farmer",
                id = "FARM-77821",
                onClose = { scope.launch { drawerState.close() } }
            )
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize().navigationBarsPadding(),
            bottomBar = { FarmerBottomBar(navController) },
            contentWindowInsets = WindowInsets(0, 0, 0, 0)
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(bottom = padding.calculateBottomPadding())
                    .fillMaxSize()
                    .background(Color(0xFFF3F5F7))
                    .verticalScroll(rememberScrollState())
            ) {
                DashboardHeader(
                    "Ramesh Yadav",
                    "Farmer",
                    onNotificationClick = { showNotifications = true },
                    onProfileClick = { scope.launch { drawerState.open() } },
                    hasNotifications = true
                )

                Column(modifier = Modifier.padding(16.dp)) {
                    // MY POLICIES section
                    SectionHeader(
                        title = "My Policies (${policies.size})", 
                        actionText = "View All",
                        onActionClick = { /* TODO */ }
                    )
                    
                    policies.forEach { policy ->
                        FarmerPolicyCard(
                            id = policy.policyNumber,
                            breed = "Barbari",
                            gender = "Female",
                            age = "12M",
                            status = "Valid till 31 May 2025",
                            expiryDate = policy.endDate,
                            navController = navController
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // NOTICES section
                    SectionHeader("Notices")
                    Card(
                        modifier = Modifier.fillMaxWidth().height(100.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("No new notices", color = Color.Gray, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // NEXT VACCINATION DUE banner
                    nextVaccination?.let { reminder ->
                        FarmerVaccinationBanner(reminder)
                    }
                }
            }
        }
    }
}

@Composable
fun FarmerVaccinationBanner(reminder: VaccinationReminder) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.CalendarToday, 
                contentDescription = null, 
                tint = PrimaryBlue,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(reminder.vaccineName, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(reminder.dueDate, color = Color.Gray, fontSize = 14.sp)
            }
            TextButton(onClick = { /* TODO */ }) {
                Text("View Schedule", color = PrimaryBlue, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, actionText: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            fontFamily = androidx.compose.ui.text.font.FontFamily.Serif
        )
        Text(
            text = actionText,
            style = MaterialTheme.typography.bodyMedium,
            color = PrimaryBlue,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun FarmerStatCard(icon: ImageVector, label: String, modifier: Modifier = Modifier, iconColor: Color = PrimaryBlue) {
    Card(
        modifier = modifier.height(110.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(icon, null, tint = iconColor, modifier = Modifier.size(28.dp))
            Text(
                text = label,
                fontSize = 11.sp,
                color = Color.Gray,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Serif
            )
        }
    }
}

@Composable
fun FarmerPolicyCard(id: String, breed: String, gender: String, age: String, status: String, expiryDate: String, navController: NavHostController) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Image placeholder
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFEEEEEE)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Pets, null, tint = Color.LightGray, modifier = Modifier.size(40.dp))
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(id, fontWeight = FontWeight.Bold, color = Color.Black)
                    Text("$breed • $gender • $age", color = Color.Gray)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(status, color = SuccessGreen, fontSize = 13.sp, fontWeight = FontWeight.Bold, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                GoatInsuranceButton(
                    text = "Report Death",
                    onClick = { navController.navigate("death_notification") },
                    containerColor = ErrorRed,
                    icon = Icons.Default.Warning,
                    modifier = Modifier.weight(1.1f)
                )
                GoatInsuranceButton(
                    text = "View Policy",
                    onClick = { /* TODO */ },
                    borderColor = PrimaryBlue,
                    modifier = Modifier.weight(0.9f)
                )
            }
        }
    }
}

@Composable
fun FarmerVaccinationCard(vaccineName: String, tagId: String, dueDate: String, dueStatus: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
        shape = RoundedCornerShape(24.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("$vaccineName • $tagId", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Due: $dueDate", color = Color.Gray, fontSize = 14.sp)
                Text(dueStatus, color = PrimaryBlue, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text("Schedule", color = PrimaryBlue, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun CoordinatorDashboard(navController: NavHostController) {
    var showNotifications by remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    if (showNotifications) NotificationSheet { showNotifications = false }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ProfileDrawerContent(
                name = "Cluster Coordinator",
                role = "Coordinator",
                id = "COORD-99012",
                onClose = { scope.launch { drawerState.close() } }
            )
        }
    ) {
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
                DashboardHeader("Cluster Coordinator", "Coordinator", onNotificationClick = { showNotifications = true }, onProfileClick = { scope.launch { drawerState.open() } }, hasNotifications = true)
            
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 300.dp),
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item { StatCard("Pending Approvals", "14", Icons.Default.Gavel, PrimaryGreen, CardLightGreen) }
                item { StatCard("Corpus Fund", "₹85,000", Icons.Default.AccountBalance, InfoBlue, CardLightBlue) }
                item { StatCard("Mortality Rate", "2.4%", Icons.Default.TrendingUp, ErrorRed, CardLightRed) }
            }
        }
    }
}
}

@Composable
fun DashboardHeader(name: String, role: String = "Suraksha Didi", onNotificationClick: () -> Unit = {}, onProfileClick: () -> Unit = {}, hasNotifications: Boolean = false) {
    Surface(
        color = if (role == "Farmer") PrimaryBlue else PrimaryGreen,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, bottom = 24.dp, start = 20.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Image
            Surface(
                onClick = onProfileClick,
                color = Color(0xFFFFAB91),
                shape = CircleShape,
                modifier = Modifier.size(60.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "Profile",
                        modifier = Modifier.size(36.dp),
                        tint = Color.White
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Welcome, $name",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Serif
                )
                Text(text = "👋", fontSize = 18.sp)
                Text(
                    text = role,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.8f),
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                )
            }
            
            // Notification Bell
            Surface(
                onClick = onNotificationClick,
                color = Color.White.copy(alpha = 0.2f),
                shape = CircleShape,
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        tint = Color.White
                    )
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
fun QuickActionCard(label: String, icon: ImageVector, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(onClick = onClick, modifier = modifier, colors = CardDefaults.cardColors(containerColor = PrimaryGreen)) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(icon, null, tint = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text(label, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
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
            onValueChange = { if (it.length <= 6 && it.all { it.isDigit() }) onValueChange(it) },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeathNotificationScreen(navController: NavHostController, viewModel: DeathNotificationViewModel) {
    val formState by viewModel.formState.collectAsState()
    val availableGoats by viewModel.availableGoats.collectAsState()

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val timePickerState = rememberTimePickerState()

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        val calendar = Calendar.getInstance()
                        calendar.timeInMillis = millis
                        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                        val datePart = sdf.format(calendar.time)
                        val timePart = formState.dateTime.substringAfter(" at ", "09:30 AM")
                        viewModel.onDateTimeChanged("$datePart at $timePart")
                    }
                    showDatePicker = false
                    showTimePicker = true
                }) { Text("Next") }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) { Text("Cancel") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (showTimePicker) {
        TimePickerDialog(
            onDismissRequest = { showTimePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    val isPm = timePickerState.hour >= 12
                    val hour = when {
                        timePickerState.hour == 0 -> 12
                        timePickerState.hour > 12 -> timePickerState.hour - 12
                        else -> timePickerState.hour
                    }
                    val timePart = String.format("%02d:%02d %s", hour, timePickerState.minute, if (isPm) "PM" else "AM")
                    val datePart = formState.dateTime.substringBefore(" at ", "15 Jun 2024")
                    viewModel.onDateTimeChanged("$datePart at $timePart")
                    showTimePicker = false
                }) { Text("Confirm") }
            },
            dismissButton = {
                TextButton(onClick = { showTimePicker = false }) { Text("Cancel") }
            }
        ) {
            TimePicker(state = timePickerState)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Report Goat Death", color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryBlue)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF3F5F7))
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // 1. Select Goat
            GoatDropdown(
                label = "Select Goat *",
                options = availableGoats,
                selectedOption = formState.selectedGoat,
                onOptionSelected = { viewModel.onGoatSelected(it) },
                optionToString = { "${it.earTag} | ${it.breed} • ${it.gender} • ${it.ageMonths}M" },
                placeholder = "Choose a goat"
            )

            // 2. Date & Time of Death
            GoatTextField(
                value = formState.dateTime,
                onValueChange = {},
                label = "Date & Time of Death *",
                leadingIcon = Icons.Default.CalendarToday,
                trailingIcon = Icons.Default.Schedule,
                readOnly = true,
                onTrailingIconClick = { showDatePicker = true },
                modifier = Modifier.clickable { showDatePicker = true }
            )

            // 3. Upload Photo
            Column {
                Text(
                    text = "Upload Photo (Optional)",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Serif,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .background(Color.White, RoundedCornerShape(16.dp))
                        .clickable { /* Launch Camera */ },
                    contentAlignment = Alignment.Center
                ) {
                    // Simulating dashed border with a helper or just a solid border for now
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.CameraAlt, contentDescription = null, modifier = Modifier.size(48.dp), tint = Color.Gray)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Capture Photo", color = Color.Gray)
                    }
                }
            }

            // Confirmation Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().clickable { viewModel.onConfirmationChanged(!formState.isConfirmed) }
            ) {
                Checkbox(
                    checked = formState.isConfirmed,
                    onCheckedChange = { viewModel.onConfirmationChanged(it) },
                    colors = CheckboxDefaults.colors(checkedColor = PrimaryBlue)
                )
                Text(
                    "I confirm the above information is correct",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Submit Button
            GoatInsuranceButton(
                text = if (formState.isLoading) "Submitting..." else "Submit Report",
                onClick = { viewModel.submitReport() },
                enabled = formState.isConfirmed && formState.selectedGoat != null && !formState.isLoading,
                modifier = Modifier.fillMaxWidth()
            )
            
            // Handle Success navigation
            LaunchedEffect(formState.isSuccess) {
                if (formState.isSuccess) {
                    // navController.navigate("mortality_progress")
                    navController.popBackStack() // Temporarily pop back
                }
            }
        }
    }
}

@Composable
fun DidiBottomBar(navController: NavHostController) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Home", fontSize = 9.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryGreen,
                selectedTextColor = PrimaryGreen,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Pets, null) }, label = { Text("Goats", fontSize = 9.sp) })
        NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.MedicalServices, null) }, label = { Text("Vaccination", fontSize = 9.sp) })
        NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Assignment, null) }, label = { Text("Claims", fontSize = 9.sp) })
        NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.MoreHoriz, null) }, label = { Text("More", fontSize = 9.sp) })
    }
}
@Composable
fun FarmerBottomBar(navController: NavHostController) {
    NavigationBar(
        containerColor = PrimaryBlue,
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = true, 
            onClick = {}, 
            icon = { Icon(Icons.Default.Home, null) }, 
            label = { Text("Home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryBlue,
                selectedTextColor = Color.White,
                unselectedIconColor = Color.White.copy(alpha = 0.6f),
                unselectedTextColor = Color.White.copy(alpha = 0.6f),
                indicatorColor = Color.White
            )
        )
        NavigationBarItem(
            selected = false, 
            onClick = {}, 
            icon = { Icon(Icons.Default.Pets, null) }, 
            label = { Text("My Goats") },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color.White.copy(alpha = 0.6f),
                unselectedTextColor = Color.White.copy(alpha = 0.6f)
            )
        )
        NavigationBarItem(
            selected = false, 
            onClick = {}, 
            icon = { Icon(Icons.Default.Notifications, null) }, 
            label = { Text("Notices") },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color.White.copy(alpha = 0.6f),
                unselectedTextColor = Color.White.copy(alpha = 0.6f)
            )
        )
        NavigationBarItem(
            selected = false, 
            onClick = {}, 
            icon = { Icon(Icons.Default.Person, null) }, 
            label = { Text("Profile") },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color.White.copy(alpha = 0.6f),
                unselectedTextColor = Color.White.copy(alpha = 0.6f)
            )
        )
    }
}



@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    CommunityGoatTheme {
        var phone by remember { mutableStateOf("9876543210") }
        LoginScreen({}, {})
    }
}

// --- NOTIFICATION COMPONENTS ---

@Composable
fun ProfileDrawerContent(name: String, role: String, id: String, onClose: () -> Unit) {
    ModalDrawerSheet {
        Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(PrimaryGreen.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, null, modifier = Modifier.size(48.dp), tint = PrimaryGreen)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text(role, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            Text("ID: $id", style = MaterialTheme.typography.labelSmall, color = PrimaryGreen)
            
            Spacer(modifier = Modifier.height(32.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))
            
            NavigationDrawerItem(
                label = { Text("Profile Settings") },
                selected = false,
                onClick = {},
                icon = { Icon(Icons.Default.Settings, null) }
            )
            NavigationDrawerItem(
                label = { Text("Help & Support") },
                selected = false,
                onClick = {},
                icon = { Icon(Icons.Default.Help, null) }
            )
            Spacer(modifier = Modifier.weight(1f))
            TextButton(onClick = onClose, modifier = Modifier.fillMaxWidth()) {
                Text("Close Menu", color = PrimaryGreen)
            }
        }
    }
}

data class AppNotification(val title: String, val message: String, val time: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationSheet(onDismiss: () -> Unit) {
    val notifications = listOf(
        AppNotification("New Enrollment", "Farmer Ram added a new goat.", "2 mins ago"),
        AppNotification("Vaccination Due", "PPR Vaccine due for TAG-12345.", "1 hour ago"),
        AppNotification("Claim Approved", "Claim #CLM7890 has been approved.", "Yesterday"),
        AppNotification("Visit Reminder", "Scheduled visit to Village Site B.", "Today, 4 PM")
    )
    
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        dragHandle = { BottomSheetDefaults.DragHandle(color = Color.LightGray) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            Text(
                "Notifications",
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
