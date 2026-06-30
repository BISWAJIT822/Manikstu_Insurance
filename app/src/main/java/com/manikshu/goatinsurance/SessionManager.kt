package com.manikshu.goatinsurance

import android.content.Context
import android.net.Uri
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_session")

/**
 * In-memory holder so the OkHttp interceptor can read the JWT synchronously.
 * Kept in sync with DataStore by SessionManager.
 */
object AuthTokenHolder {
    @Volatile
    var token: String? = null
}

@Singleton
class SessionManager @Inject constructor(@ApplicationContext private val context: Context) {
    companion object {
        private val USER_ROLE_KEY = stringPreferencesKey("user_role")
        private val LANGUAGE_KEY = stringPreferencesKey("app_language")
        private val PROFILE_IMAGE_KEY = stringPreferencesKey("profile_image_uri")
        private val NOTIFICATIONS_ENABLED_KEY = androidx.datastore.preferences.core.booleanPreferencesKey("notifications_enabled")
        private val AUTH_TOKEN_KEY = stringPreferencesKey("auth_token")
        private val USER_NAME_KEY = stringPreferencesKey("user_name")
        private val USER_MOBILE_KEY = stringPreferencesKey("user_mobile")
    }

    suspend fun saveSession(role: UserRole) {
        context.dataStore.edit { preferences ->
            preferences[USER_ROLE_KEY] = role.name
        }
    }

    val userRole: Flow<UserRole?> = context.dataStore.data.map { preferences ->
        preferences[USER_ROLE_KEY]?.let { UserRole.valueOf(it) }
    }

    // ---- auth token ----
    suspend fun saveAuthToken(token: String) {
        AuthTokenHolder.token = token
        context.dataStore.edit { it[AUTH_TOKEN_KEY] = token }
    }

    val authToken: Flow<String?> = context.dataStore.data.map { it[AUTH_TOKEN_KEY] }

    /** Call on app start to repopulate the in-memory token from disk. */
    suspend fun loadTokenIntoMemory() {
        AuthTokenHolder.token = context.dataStore.data.map { it[AUTH_TOKEN_KEY] }.first()
    }

    suspend fun saveUserInfo(name: String, mobile: String) {
        context.dataStore.edit {
            it[USER_NAME_KEY] = name
            it[USER_MOBILE_KEY] = mobile
        }
    }

    val userName: Flow<String?> = context.dataStore.data.map { it[USER_NAME_KEY] }
    val userMobile: Flow<String?> = context.dataStore.data.map { it[USER_MOBILE_KEY] }

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
            preferences.remove(PROFILE_IMAGE_KEY)
            preferences.remove(AUTH_TOKEN_KEY)
            preferences.remove(USER_NAME_KEY)
            preferences.remove(USER_MOBILE_KEY)
        }
    }
}
