package com.manikshu.goatinsurance

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

object AuthTokenHolder {
    var token: String? = null
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Backend base URL. Hardcoded (not BuildConfig) because AGP 9 does not expose the
    // generated BuildConfig on KSP's source path, which breaks Hilt/KSP processing of
    // this module ("BASE_URL could not be resolved"). Change the value here to switch:
    //  - emulator:      http://10.0.2.2:8000/   (current)
    //  - USB device:    http://localhost:8000/  (run `adb reverse tcp:8000 tcp:8000`)
    //  - localtunnel:   https://<your-subdomain>.loca.lt/
    private const val BASE_URL = "http://10.0.2.2:8000/"

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        explicitNulls = false
    }

    /** Adds `Authorization: Bearer <token>` when a token is present. */
    @Provides
    @Singleton
    fun provideAuthInterceptor(): Interceptor = Interceptor { chain ->
        val token = AuthTokenHolder.token
        val request = if (token.isNullOrBlank()) {
            chain.request()
        } else {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        }
        chain.proceed(request)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: Interceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}
