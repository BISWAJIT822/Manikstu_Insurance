package com.manikshu.goatinsurance

import android.os.Build
import android.os.Bundle
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import kotlinx.coroutines.flow.first
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Fully transparent system navigation bar so the app content shows behind it
        // (removes the grey/black scrim the OS otherwise draws behind the buttons).
        window.navigationBarColor = Color.TRANSPARENT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val navController = rememberNavController()
            val sessionManager = remember { SessionManager(this) }

            // Restore the persisted JWT into the in-memory holder the OkHttp interceptor
            // reads, before any authenticated screen makes a call.
            val tokenLoaded = remember { mutableStateOf(false) }
            LaunchedEffect(Unit) {
                AuthTokenHolder.token = sessionManager.currentToken()
                tokenLoaded.value = true
            }

            val savedLanguage by sessionManager.appLanguage.collectAsState(initial = AppLanguage.ENGLISH)
            val savedNotificationsEnabled by sessionManager.notificationsEnabled.collectAsState(initial = true)
            
            // Initialize from system locale to avoid flicker
            val currentLocaleCode = AppCompatDelegate.getApplicationLocales().get(0)?.language ?: Locale.getDefault().language
            val initialLanguage = AppLanguage.entries.find { it.code == currentLocaleCode } ?: AppLanguage.ENGLISH
            
            val languageState = remember { mutableStateOf(initialLanguage) }
            val profileImageState = remember { mutableStateOf<android.net.Uri?>(null) }
            // Guards the save effect below: it must not persist the initial null before
            // the stored image has been read, or the saved photo is wiped on every launch.
            val profileImageHydrated = remember { mutableStateOf(false) }
            val notificationsEnabledState = remember { mutableStateOf(true) }

            // Sync initial values from DataStore
            LaunchedEffect(savedLanguage) {
                savedLanguage?.let {
                    if (languageState.value != it) {
                        languageState.value = it
                    }
                }
            }
            // Hydrate once from DataStore, then allow saves. Reading it imperatively
            // (rather than a reactive collect that starts at null) means the save
            // effect can't clobber the stored image with an initial null.
            LaunchedEffect(Unit) {
                profileImageState.value = sessionManager.profileImageUri.first()
                profileImageHydrated.value = true
            }
            LaunchedEffect(savedNotificationsEnabled) {
                notificationsEnabledState.value = savedNotificationsEnabled
            }

            // Save values when changed
            LaunchedEffect(languageState.value) {
                sessionManager.saveLanguage(languageState.value)
                val currentLocale = AppCompatDelegate.getApplicationLocales().get(0)?.language
                if (currentLocale != languageState.value.code) {
                    AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(languageState.value.code))
                }
            }
            LaunchedEffect(profileImageState.value) {
                if (profileImageHydrated.value) sessionManager.saveProfileImage(profileImageState.value)
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
                    if (tokenLoaded.value) {
                        UpdateGate {
                            AppNavigation(navController, sessionManager)
                        }
                    }
                }
            }
        }
    }
}
