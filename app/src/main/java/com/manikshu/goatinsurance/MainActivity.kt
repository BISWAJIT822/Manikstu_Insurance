package com.manikshu.goatinsurance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val navController = rememberNavController()
            val sessionManager = remember { SessionManager(this) }
            
            val savedLanguage by sessionManager.appLanguage.collectAsState(initial = AppLanguage.ENGLISH)
            val savedProfileImage by sessionManager.profileImageUri.collectAsState(initial = null)
            val savedNotificationsEnabled by sessionManager.notificationsEnabled.collectAsState(initial = true)
            
            val languageState = remember { mutableStateOf(AppLanguage.ENGLISH) }
            val profileImageState = remember { mutableStateOf<android.net.Uri?>(null) }
            val notificationsEnabledState = remember { mutableStateOf(true) }

            // Sync initial values from DataStore
            LaunchedEffect(savedLanguage) {
                languageState.value = savedLanguage
            }
            LaunchedEffect(savedProfileImage) {
                profileImageState.value = savedProfileImage
            }
            LaunchedEffect(savedNotificationsEnabled) {
                notificationsEnabledState.value = savedNotificationsEnabled
            }

            // Save values when changed
            LaunchedEffect(languageState.value) {
                sessionManager.saveLanguage(languageState.value)
            }
            LaunchedEffect(profileImageState.value) {
                sessionManager.saveProfileImage(profileImageState.value)
            }
            LaunchedEffect(notificationsEnabledState.value) {
                sessionManager.saveNotificationsEnabled(notificationsEnabledState.value)
            }

            CommunityGoatTheme {
                CompositionLocalProvider(
                    LocalWindowSizeClass provides windowSizeClass,
                    LocalAppLanguage provides languageState,
                    LocalProfileImage provides profileImageState,
                    LocalNotificationsEnabled provides notificationsEnabledState
                ) {
                    AppNavigation(navController, sessionManager)
                }
            }
        }
    }
}
