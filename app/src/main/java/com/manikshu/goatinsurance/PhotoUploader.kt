package com.manikshu.goatinsurance

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.max
import kotlin.math.roundToInt

/**
 * Prepares a captured image for upload: downsamples, corrects orientation, and
 * re-encodes as JPEG so uploads are small on rural connections and never exceed
 * the backend's 10 MB cap.
 *
 * Device-to-device: EXIF rotation is baked into the pixels here, so a photo taken
 * on a phone that records orientation as metadata (rather than rotating pixels)
 * still appears upright on every other device that later views it.
 *
 * These are claim-critical photos (ear tags, carcass), so the target is deliberately
 * gentle - big enough to keep an ear-tag number legible, small enough to upload fast.
 */
@Singleton
class PhotoUploader @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private companion object {
        const val TAG = "PhotoUploader"
        const val MAX_EDGE = 1600      // longest side after resize, px
        const val QUALITY = 85         // JPEG quality (identification photos stay readable)
    }

    /**
     * Reads [uri] and returns compressed JPEG bytes ready for multipart upload.
     *
     * On any failure it falls back to the original bytes rather than dropping the
     * photo, so a decode edge case never blocks an enrolment. Returns null only if
     * the image cannot be read at all.
     */
    fun readBytes(uri: Uri): ByteArray? {
        return try {
            compress(uri) ?: rawBytes(uri)
        } catch (e: Exception) {
            Log.w(TAG, "compression failed, uploading original", e)
            rawBytes(uri)
        }
    }

    private fun rawBytes(uri: Uri): ByteArray? = try {
        context.contentResolver.openInputStream(uri)?.use { it.readBytes() }
    } catch (e: Exception) {
        Log.e(TAG, "could not read image", e)
        null
    }

    private fun compress(uri: Uri): ByteArray? {
        // Pass 1: read only the dimensions so we can downsample during decode and
        // never load a full 12MP image (~48 MB as a bitmap) into memory.
        val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        context.contentResolver.openInputStream(uri)?.use {
            BitmapFactory.decodeStream(it, null, bounds)
        }
        if (bounds.outWidth <= 0 || bounds.outHeight <= 0) return null

        val opts = BitmapFactory.Options().apply {
            inSampleSize = sampleSize(bounds.outWidth, bounds.outHeight, MAX_EDGE)
        }
        var bitmap = context.contentResolver.openInputStream(uri)?.use {
            BitmapFactory.decodeStream(it, null, opts)
        } ?: return null

        bitmap = applyOrientation(uri, bitmap)
        bitmap = scaleDown(bitmap, MAX_EDGE)

        return ByteArrayOutputStream().use { out ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, QUALITY, out)
            bitmap.recycle()
            out.toByteArray()
        }
    }

    /** Largest power-of-two sample size that keeps the long edge >= [maxEdge]. */
    private fun sampleSize(width: Int, height: Int, maxEdge: Int): Int {
        var sample = 1
        var longEdge = max(width, height)
        while (longEdge / 2 >= maxEdge) {
            longEdge /= 2
            sample *= 2
        }
        return sample
    }

    /** Rotate/flip the bitmap to match the file's EXIF orientation tag. */
    private fun applyOrientation(uri: Uri, bitmap: Bitmap): Bitmap {
        val orientation = context.contentResolver.openInputStream(uri)?.use {
            ExifInterface(it).getAttributeInt(
                ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL,
            )
        } ?: ExifInterface.ORIENTATION_NORMAL

        val m = Matrix()
        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> m.postRotate(90f)
            ExifInterface.ORIENTATION_ROTATE_180 -> m.postRotate(180f)
            ExifInterface.ORIENTATION_ROTATE_270 -> m.postRotate(270f)
            ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> m.postScale(-1f, 1f)
            ExifInterface.ORIENTATION_FLIP_VERTICAL -> m.postScale(1f, -1f)
            ExifInterface.ORIENTATION_TRANSPOSE -> { m.postRotate(90f); m.postScale(-1f, 1f) }
            ExifInterface.ORIENTATION_TRANSVERSE -> { m.postRotate(270f); m.postScale(-1f, 1f) }
            else -> return bitmap  // ORIENTATION_NORMAL / UNDEFINED: nothing to do
        }
        val rotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, m, true)
        if (rotated != bitmap) bitmap.recycle()
        return rotated
    }

    /** Scale so the long edge is at most [maxEdge]; leaves smaller images untouched. */
    private fun scaleDown(bitmap: Bitmap, maxEdge: Int): Bitmap {
        val longEdge = max(bitmap.width, bitmap.height)
        if (longEdge <= maxEdge) return bitmap
        val ratio = maxEdge.toFloat() / longEdge
        val w = (bitmap.width * ratio).roundToInt()
        val h = (bitmap.height * ratio).roundToInt()
        val scaled = Bitmap.createScaledBitmap(bitmap, w, h, true)
        if (scaled != bitmap) bitmap.recycle()
        return scaled
    }
}
