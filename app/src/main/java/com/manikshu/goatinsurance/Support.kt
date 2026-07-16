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
 * Handles a "call support" tap: opens the phone dialer pre-filled with the
 * support number so the user can place a call.
 *
 * Calling and chatting are separate actions on purpose - see [chatOnWhatsApp].
 */
fun Context.callSupport() {
    val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+${SupportContact.E164_DIGITS}"))
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    try {
        startActivity(dialIntent)
    } catch (_: Exception) {
        Toast.makeText(this, "Unable to open the phone dialer", Toast.LENGTH_SHORT).show()
    }
}

/**
 * Handles a "chat on WhatsApp" tap: opens a WhatsApp conversation with the
 * support number. Separate from [callSupport], which dials instead.
 */
fun Context.chatOnWhatsApp() {
    // wa.me is WhatsApp's official deep link: it opens the app directly when
    // installed, and falls back to the browser (which offers WhatsApp) when not.
    val chatIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/${SupportContact.E164_DIGITS}"))
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    try {
        startActivity(chatIntent)
    } catch (_: Exception) {
        Toast.makeText(this, "Unable to open WhatsApp", Toast.LENGTH_SHORT).show()
    }
}
