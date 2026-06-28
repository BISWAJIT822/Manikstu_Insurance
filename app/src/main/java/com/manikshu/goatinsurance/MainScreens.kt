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
    ResponsiveLayout(
        compact = {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = { DidiBottomBar(navController) }
            ) { padding ->
                DidiContent(padding, navController)
            }
        },
        expanded = {
            Row(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing)) {
                NavigationRail {
                    NavigationRailItem(selected = true, onClick = {}, icon = { Icon(Icons.Default.Home, null) }, label = { Text("Home") })
                    NavigationRailItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Pets, null) }, label = { Text("Goats") })
                    NavigationRailItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Vaccines, null) }, label = { Text("Vaccines") })
                }
                DidiContent(PaddingValues(0.dp), navController)
            }
        }
    )
}

@Composable
fun DidiContent(padding: PaddingValues, navController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(bottom = padding.calculateBottomPadding())
            .fillMaxSize()
            .background(Color(0xFFF8F9F5))
    ) {
        DashboardHeader("Sushma Didi", "Suraksha Didi")
        
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                "This Month Overview",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // 2x2 Grid for Stats
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Box(modifier = Modifier.weight(1f)) {
                        StatCard("Goats Enrolled", "128", Icons.Default.Pets, PrimaryGreen, CardLightGreen)
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        StatCard("Pending Claims", "12", Icons.Default.Assignment, AccentOrange, CardLightOrange)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Box(modifier = Modifier.weight(1f)) {
                        StatCard("Today's Visits", "24", Icons.Default.CalendarToday, InfoBlue, CardLightBlue)
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        StatCard("Earnings", "₹8,450", Icons.Default.Payments, Color(0xFF9C27B0), CardLightPurple)
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                "Quick Actions",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            // 3x2 Grid for Quick Actions
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Box(modifier = Modifier.weight(1f)) {
                        QuickActionGridCard("Enroll Goat", Icons.Default.Pets, PrimaryGreen, CardLightGreen) { navController.navigate("enrollment") }
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        QuickActionGridCard("Vaccination", Icons.Default.MedicalServices, InfoBlue, CardLightBlue) { /* TODO */ }
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        QuickActionGridCard("Mortality Report", Icons.Default.LocationOn, Color(0xFFD32F2F), CardLightRed) { navController.navigate("mortality_report") }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Box(modifier = Modifier.weight(1f)) {
                        QuickActionGridCard("Claims", Icons.Default.Assignment, AccentOrange, CardLightOrange) { navController.navigate("claim_tracker") }
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        QuickActionGridCard("Goat List", Icons.Default.FactCheck, Color(0xFF2E7D32), CardLightGreen) { /* TODO */ }
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        QuickActionGridCard("AI Assistant", Icons.Default.AccountBox, Color(0xFF7B1FA2), CardLightPurple) { /* TODO */ }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PremiumCollectionScreen(onComplete: () -> Unit) {
    Scaffold(topBar = { CenterAlignedTopAppBar(title = { Text("Premium Collection") }) }) { padding ->
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
    Scaffold(topBar = { CenterAlignedTopAppBar(title = { Text("Mortality Verification") }) }) { padding ->
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
    Column(modifier = Modifier.padding(16.dp)) {
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
fun FarmerDashboard(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { FarmerBottomBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(bottom = padding.calculateBottomPadding())
                .fillMaxSize()
        ) {
            DashboardHeader("Farmer Ram", "Farmer")
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Text("My Policies", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(16.dp))
                ResponsiveGrid {
                    item { PolicyCard("TAG-12345", "Active", "Jan 2026") }
                    item { PolicyCard("TAG-67890", "Active", "Feb 2026") }
                }
                Card(modifier = Modifier.padding(16.dp).fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = InfoBlue.copy(alpha = 0.1f))) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Info, null, tint = InfoBlue)
                        Spacer(modifier = Modifier.width(16.dp))
                        Text("Report death within 24 hours to ensure claim eligibility.")
                    }
                }
            }
        }
    }
}

@Composable
fun CoordinatorDashboard(navController: NavHostController) {
    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Column(
            modifier = Modifier
                .padding(bottom = padding.calculateBottomPadding())
                .fillMaxSize()
        ) {
            DashboardHeader("Cluster Coordinator", "Coordinator")
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                ResponsiveGrid {
                    item { StatCard("Pending Approvals", "14", Icons.Default.Gavel, PrimaryGreen, CardLightGreen) }
                    item { StatCard("Corpus Fund", "₹85,000", Icons.Default.AccountBalance, InfoBlue, CardLightBlue) }
                    item { StatCard("Mortality Rate", "2.4%", Icons.Default.TrendingUp, ErrorRed, CardLightRed) }
                }
            }
        }
    }
}

@Composable
fun DashboardHeader(name: String, role: String = "Suraksha Didi") {
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
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFCCBC)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Hello, $name 👋",
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
                onClick = { /* TODO */ },
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
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(iconBgColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, null, tint = iconColor, modifier = Modifier.size(24.dp))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text(label, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
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
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                label,
                fontSize = 11.sp,
                lineHeight = 13.sp,
                fontWeight = FontWeight.Bold,
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
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Home", fontSize = 10.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryGreen,
                selectedTextColor = PrimaryGreen,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Pets, null) }, label = { Text("Goats", fontSize = 10.sp) })
        NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.MedicalServices, null) }, label = { Text("Vaccination", fontSize = 10.sp) })
        NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Assignment, null) }, label = { Text("Claims", fontSize = 10.sp) })
        NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.MoreHoriz, null) }, label = { Text("More", fontSize = 10.sp) })
    }
}

@Composable
fun FarmerBottomBar(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(selected = true, onClick = {}, icon = { Icon(Icons.Default.Home, null) }, label = { Text("Home") })
        NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.History, null) }, label = { Text("Claims") })
        NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.SupportAgent, null) }, label = { Text("Help") })
    }
}

@Composable
fun ResponsiveGrid(content: LazyGridScope.() -> Unit) {
    val window = LocalWindowSizeClass.current
    val columns = when (window?.widthSizeClass) {
        WindowWidthSizeClass.Compact -> 1
        WindowWidthSizeClass.Medium -> 2
        else -> 3
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.heightIn(max = 2000.dp),
        userScrollEnabled = false,
        content = content
    )
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    CommunityGoatTheme {
        var phone by remember { mutableStateOf("9876543210") }
        LoginScreen({}, {})
    }
}
