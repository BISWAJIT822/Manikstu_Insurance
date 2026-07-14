package com.manikshu.goatinsurance

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

/**
 * Single source of truth for the customer-support contact number.
 *
 * TODO: This is an interim number for the current release. Replace both values
 * below with the official customer-support number once it is finalized.
 */
object SupportContact {
    /** Human-readable form, shown anywhere the support number is displayed. */
    const val DISPLAY = "+91 92373 92721"

    /** Digits only in E.164 (country code + number), for wa.me / tel: URIs. */
    const val E164_DIGITS = "919237392721"
}

/**
 * Handles a "contact support" tap: opens a WhatsApp chat with the support number
 * (preferred), and falls back to the phone dialer with the same number if no
 * WhatsApp variant is installed.
 */
fun Context.contactSupport() {
    val waUrl = "https://wa.me/${SupportContact.E164_DIGITS}"
    // Prefer WhatsApp, then WhatsApp Business.
    for (pkg in listOf("com.whatsapp", "com.whatsapp.w4b")) {
        val waIntent = Intent(Intent.ACTION_VIEW, Uri.parse(waUrl))
            .setPackage(pkg)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            startActivity(waIntent)
            return
        } catch (_: Exception) {
            // This WhatsApp variant isn't installed; try the next option.
        }
    }
    // Fallback: open the dialer pre-filled with the support number.
    val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+${SupportContact.E164_DIGITS}"))
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    try {
        startActivity(dialIntent)
    } catch (_: Exception) {
        Toast.makeText(this, "Unable to open WhatsApp or the phone dialer", Toast.LENGTH_SHORT).show()
    }
}
