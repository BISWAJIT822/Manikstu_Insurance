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

val LocalWindowSizeClass = staticCompositionLocalOf<WindowSizeClass?> { null }
val LocalAppLanguage = compositionLocalOf { mutableStateOf(AppLanguage.ENGLISH) }

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
        composable("farmer_dashboard") { FarmerDashboard(navController) }
        composable("coordinator_dashboard") { CoordinatorDashboard(navController) }
        composable("enrollment") { EnrollmentStepper(onComplete = { navController.popBackStack() }) }
        composable("premium_collection") { PremiumCollectionScreen(onComplete = { navController.popBackStack() }) }
        composable("mortality_report") { MortalityReportScreen(onComplete = { navController.popBackStack() }) }
        composable("claim_tracker") { ClaimStatusTracker() }
        composable("profile") { 
            ProfileScreen(
                onLogout = { 
                    navController.navigate("login") { 
                        popUpTo("didi_dashboard") { inclusive = true }
                        popUpTo("farmer_dashboard") { inclusive = true }
                        popUpTo("coordinator_dashboard") { inclusive = true }
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
                                Toast.makeText(context, languageState.value.getT("Scroll down and choose a role", "नीचे स्क्रॉल करें and choose a role", "ତଳକୁ ସ୍କ୍ରୋଲ୍ କରନ୍ତୁ ଏବଂ ଏକ ଭୂମିକା ବାଛନ୍ତୁ"), Toast.LENGTH_SHORT).show()
                            } else {
                                step = 2 
                            }
                        },
                        enabled = name.isNotBlank() && phone.length == 10,
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
                                Toast.makeText(context, languageState.value.getT("Scroll down and choose a role", "नीचे स्क्रॉल करें and choose a role", "ତଳକୁ ସ୍କ୍ରୋଲ୍ କରନ୍ତୁ ଏବଂ ଏକ ଭୂମିକା ବାଛନ୍ତୁ"), Toast.LENGTH_SHORT).show()
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
    if (showNotifications) NotificationSheet { showNotifications = false }

    ResponsiveLayout(
        compact = {
            Scaffold(
                modifier = Modifier.fillMaxSize().navigationBarsPadding(),
                bottomBar = { DidiBottomBar(navController) },
                contentWindowInsets = WindowInsets(0, 0, 0, 0)
            ) { padding ->
                DidiContent(padding, navController, onNotificationClick = { showNotifications = true })
            }
        },
        expanded = {
            Row(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing).navigationBarsPadding()) {
                NavigationRail {
                    NavigationRailItem(selected = true, onClick = {}, icon = { Icon(Icons.Default.Home, null) }, label = { Text("Home") })
                    NavigationRailItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Pets, null) }, label = { Text("Goats") })
                    NavigationRailItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Vaccines, null) }, label = { Text("Vaccines") })
                }
                DidiContent(PaddingValues(0.dp), navController, onNotificationClick = { showNotifications = true })
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
                    1 -> StatCard(languageState.value.getT("Pending Claims", "लंबित दावे", "ବାକି ରହିଥିବା ଦାବି"), "12", Icons.Default.Assignment, AccentOrange, CardLightOrange)
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
                    3 -> QuickActionGridCard(languageState.value.getT("Claims", "दावे", "ଦାବି"), Icons.Default.Assignment, AccentOrange, CardLightOrange) { navController.navigate("claim_tracker") }
                    4 -> QuickActionGridCard(languageState.value.getT("Goat List", "बकरियों की सूची", "ଛେଳି ତାଲିକା"), Icons.Default.FactCheck, Color(0xFF2E7D32), CardLightGreen) { /* TODO */ }
                    5 -> QuickActionGridCard(languageState.value.getT("AI Assistant", "AI सहायक", "AI ସହାୟକ"), Icons.Default.AccountBox, Color(0xFF7B1FA2), CardLightPurple) { /* TODO */ }
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
            LinearProgressIndicator(progress = { currentStep / 7f }, modifier = Modifier.fillMaxWidth())
            
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
fun FarmerDashboard(navController: NavHostController) {
    var showNotifications by remember { mutableStateOf(false) }
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
                hasNotifications = true,
                onProfileClick = { navController.navigate("profile") }
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
                hasNotifications = true,
                onProfileClick = { navController.navigate("profile") }
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
                item { StatCard(languageState.value.getT("Mortality Rate", "मृत्यु दर", "ମୃତ୍ୟୁ ହାର"), "2.4%", Icons.Default.TrendingUp, ErrorRed, CardLightRed) }
            }
        }
    }
}

@Composable
fun DashboardHeader(name: String, role: String, onNotificationClick: () -> Unit = {}, hasNotifications: Boolean = false, onProfileClick: () -> Unit = {}) {
    val languageState = LocalAppLanguage.current
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
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "Profile Settings",
                        modifier = Modifier.size(40.dp),
                        tint = Color.White
                    )
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

@Composable
fun DidiBottomBar(navController: NavHostController) {
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
        NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Assignment, null) }, label = { Text(languageState.value.getT("Claims", "दावे", "ଦାବି"), fontSize = 9.sp) })
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
    val notifications = listOf(
        AppNotification("New Enrollment", "Farmer Ram added a new goat.", "2 mins ago"),
        AppNotification("Vaccination Due", "PPR Vaccine due for TAG-12345.", "1 hour ago"),
        AppNotification("Claim Approved", "Claim #CLM7890 has been approved.", "Yesterday"),
        AppNotification("Visit Reminder", "Scheduled visit to Village Site B.", "Today, 4 PM")
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onLogout: () -> Unit, onBack: () -> Unit) {
    val backgroundColor = Color(0xFFF8F9F5)
    val languageState = LocalAppLanguage.current
    var showLanguagePicker by remember { mutableStateOf(false) }

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
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
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
                            Icon(
                                Icons.Default.Person,
                                contentDescription = null,
                                modifier = Modifier.size(60.dp),
                                tint = Color.White
                            )
                        }
                    }
                    Surface(
                        onClick = { /* Edit Photo */ },
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
                SettingsToggleItem(languageState.value.getT("Enable Notifications", "सूचनाएं सक्षम करें", "ବିଜ୍ଞପ୍ତି ସକ୍ଷମ କରନ୍ତୁ"), true) {}
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
                Icon(Icons.Default.Logout, null)
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
