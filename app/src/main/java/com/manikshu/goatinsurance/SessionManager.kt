package com.manikshu.goatinsurance

import android.content.Context
import android.net.Uri
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_session")

class SessionManager(private val context: Context) {
    companion object {
        private val USER_ROLE_KEY = stringPreferencesKey("user_role")
        private val USER_NAME_KEY = stringPreferencesKey("user_name")
        private val VILLAGE_KEY = stringPreferencesKey("village")
        private val MOBILE_KEY = stringPreferencesKey("mobile_number")
        private val TOKEN_KEY = stringPreferencesKey("auth_token")
        private val LANGUAGE_KEY = stringPreferencesKey("app_language")
        private val PROFILE_IMAGE_KEY = stringPreferencesKey("profile_image_uri")
        private val NOTIFICATIONS_ENABLED_KEY = androidx.datastore.preferences.core.booleanPreferencesKey("notifications_enabled")
    }

    suspend fun saveSession(
        role: UserRole,
        name: String? = null,
        village: String? = null,
        mobile: String? = null,
        token: String? = null,
    ) {
        context.dataStore.edit { preferences ->
            preferences[USER_ROLE_KEY] = role.name
            name?.let { preferences[USER_NAME_KEY] = it }
            village?.let { preferences[VILLAGE_KEY] = it }
            mobile?.let { preferences[MOBILE_KEY] = it }
            token?.let {
                preferences[TOKEN_KEY] = it
                AuthTokenHolder.token = it
            }
        }
    }

    /** Persist the JWT and populate the in-memory holder the OkHttp interceptor reads. */
    suspend fun saveAuthToken(token: String) {
        AuthTokenHolder.token = token
        context.dataStore.edit { it[TOKEN_KEY] = token }
    }

    val authToken: Flow<String?> = context.dataStore.data.map { it[TOKEN_KEY] }

    /** Read the token once (e.g. at app start) to seed [AuthTokenHolder]. */
    suspend fun currentToken(): String? = authToken.first()

    val userName: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[USER_NAME_KEY]
    }

    val village: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[VILLAGE_KEY]
    }

    val mobileNumber: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[MOBILE_KEY]
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
        AuthTokenHolder.token = null
        context.dataStore.edit { preferences ->
            preferences.remove(USER_ROLE_KEY)
            preferences.remove(USER_NAME_KEY)
            preferences.remove(VILLAGE_KEY)
            preferences.remove(MOBILE_KEY)
            preferences.remove(TOKEN_KEY)
            preferences.remove(PROFILE_IMAGE_KEY)
            // language + notification prefs intentionally preserved across logout
        }
    }
}
