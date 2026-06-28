package com.manikshu.goatinsurance

import android.content.Context
import android.net.Uri
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_session")

class SessionManager(private val context: Context) {
    companion object {
        private val USER_ROLE_KEY = stringPreferencesKey("user_role")
        private val LANGUAGE_KEY = stringPreferencesKey("app_language")
        private val PROFILE_IMAGE_KEY = stringPreferencesKey("profile_image_uri")
        private val NOTIFICATIONS_ENABLED_KEY = androidx.datastore.preferences.core.booleanPreferencesKey("notifications_enabled")
    }

    suspend fun saveSession(role: UserRole) {
        context.dataStore.edit { preferences ->
            preferences[USER_ROLE_KEY] = role.name
        }
    }

    val userRole: Flow<UserRole?> = context.dataStore.data.map { preferences ->
        preferences[USER_ROLE_KEY]?.let { UserRole.valueOf(it) }
    }

    suspend fun saveLanguage(language: AppLanguage) {
        context.dataStore.edit { preferences ->
            preferences[LANGUAGE_KEY] = language.name
        }
    }

    val appLanguage: Flow<AppLanguage> = context.dataStore.data.map { preferences ->
        preferences[LANGUAGE_KEY]?.let { AppLanguage.valueOf(it) } ?: AppLanguage.ENGLISH
    }

    suspend fun saveProfileImage(uri: Uri?) {
        context.dataStore.edit { preferences ->
            if (uri != null) {
                preferences[PROFILE_IMAGE_KEY] = uri.toString()
            } else {
                preferences.remove(PROFILE_IMAGE_KEY)
            }
        }
    }

    val profileImageUri: Flow<Uri?> = context.dataStore.data.map { preferences ->
        preferences[PROFILE_IMAGE_KEY]?.let { Uri.parse(it) }
    }

    suspend fun saveNotificationsEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[NOTIFICATIONS_ENABLED_KEY] = enabled
        }
    }

    val notificationsEnabled: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[NOTIFICATIONS_ENABLED_KEY] ?: true
    }

    suspend fun clearSession() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_ROLE_KEY)
            preferences.remove(LANGUAGE_KEY)
            preferences.remove(PROFILE_IMAGE_KEY)
            preferences.remove(NOTIFICATIONS_ENABLED_KEY)
        }
    }
}
