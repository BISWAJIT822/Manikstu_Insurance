package com.manikshu.goatinsurance

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Single source of truth for showing server timestamps.
 *
 * Every timestamp the backend records is UTC (it uses `datetime.utcnow()`), and it
 * is serialized as a naive string ("2026-07-23T05:15:00" or space-separated, with or
 * without fractional seconds / a Z / an offset). These helpers parse that value as
 * UTC and render it in IST (Asia/Kolkata) with one consistent format, so a record
 * created at 10:45 AM IST shows "23 Jul 2026, 10:45 AM" everywhere - notifications,
 * activity, receipts, PDFs, dashboards - regardless of the device's clock or timezone.
 */
object AppTime {
    private val IST: TimeZone = TimeZone.getTimeZone("Asia/Kolkata")
    private val UTC: TimeZone = TimeZone.getTimeZone("UTC")

    private val PATTERNS = listOf(
        "yyyy-MM-dd'T'HH:mm:ss",
        "yyyy-MM-dd'T'HH:mm",
        "yyyy-MM-dd",
    )

    /** Parse a backend timestamp as UTC. Tolerates space/'T' separators, fractional
     *  seconds, and a trailing Z or +offset (all treated as UTC). Returns null if blank/unparseable. */
    fun parseUtc(raw: String?): Date? {
        if (raw.isNullOrBlank()) return null
        var s = raw.trim().replace(' ', 'T')
        val dot = s.indexOf('.')
        if (dot > 0) s = s.substring(0, dot)          // drop fractional seconds
        s = s.removeSuffix("Z")
        val plus = s.indexOf('+', 11)
        if (plus > 0) s = s.substring(0, plus)         // drop +HH:MM offset
        for (p in PATTERNS) {
            try {
                val f = SimpleDateFormat(p, Locale.US)
                f.timeZone = UTC
                f.isLenient = false
                return f.parse(s)
            } catch (_: Exception) { /* try next pattern */ }
        }
        return null
    }

    private fun render(pattern: String, raw: String?, fallback: String): String {
        val d = parseUtc(raw) ?: return fallback
        val f = SimpleDateFormat(pattern, Locale.ENGLISH)
        f.timeZone = IST
        return f.format(d)
    }

    /** "23 Jul 2026, 10:45 AM" (IST). */
    fun dateTime(raw: String?, fallback: String = "—"): String = render("dd MMM yyyy, hh:mm a", raw, fallback)

    /** "23 Jul 2026" (IST). */
    fun date(raw: String?, fallback: String = "—"): String = render("dd MMM yyyy", raw, fallback)

    /** "10:45 AM" (IST). */
    fun time(raw: String?, fallback: String = "—"): String = render("hh:mm a", raw, fallback)

    /** Relative age ("just now", "5 min ago", "2 h ago", "3 d ago"); older than a week -> the date. */
    fun timeAgo(raw: String?, fallback: String = "—"): String {
        val d = parseUtc(raw) ?: return fallback
        val diff = System.currentTimeMillis() - d.time
        return when {
            diff < 0L -> dateTime(raw, fallback)
            diff < 60_000L -> "just now"
            diff < 3_600_000L -> "${diff / 60_000L} min ago"
            diff < 86_400_000L -> "${diff / 3_600_000L} h ago"
            diff < 7L * 86_400_000L -> "${diff / 86_400_000L} d ago"
            else -> date(raw, fallback)
        }
    }
}
