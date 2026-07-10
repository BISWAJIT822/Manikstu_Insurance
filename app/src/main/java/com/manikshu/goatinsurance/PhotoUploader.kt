package com.manikshu.goatinsurance

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/** Reads local image content URIs into byte arrays for multipart upload. */
@Singleton
class PhotoUploader @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    fun readBytes(uri: Uri): ByteArray? = try {
        context.contentResolver.openInputStream(uri)?.use { it.readBytes() }
    } catch (e: Exception) {
        null
    }
}
