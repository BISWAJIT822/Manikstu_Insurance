package com.manikshu.goatinsurance

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val PrimaryGreen = Color(0xFF1B6B3A)
val DarkGreen = Color(0xFF0D4A26)
val AccentOrange = Color(0xFFFF6B35)
val InfoBlue = Color(0xFF4A90E2)
val SuccessGreen = Color(0xFF28A745)
val WarningYellow = Color(0xFFFFC107)
val ErrorRed = Color(0xFFDC3545)

/** The page canvas behind every dashboard screen. DidiBottomBar leaves a 16dp
 *  transparent strip above its white surface so the raised + can overhang, so
 *  whatever sits behind the bar shows through - it must be this, not white. */
val PageBackground = Color(0xFFF6F7F3)

// Per-icon accent colours for the Help & Support topic rows and the Privacy
// Policy clauses. Each is dark enough to stay legible at ~20dp against white and
// against its own 10% tint, which is what the rounded icon tiles use behind them.
// Profile deliberately stays uniform green.
val IconGreen = Color(0xFF1B6B3A)
val IconBlue = Color(0xFF2F6FED)
val IconPurple = Color(0xFF7A4FD6)
val IconTeal = Color(0xFF0E8C8C)
val IconAmber = Color(0xFFD98200)
val IconRose = Color(0xFFD6455D)
val IconIndigo = Color(0xFF3F51B5)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryGreen,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFB7F397),
    secondary = AccentOrange,
    error = ErrorRed,
    surface = Color.White,
    onSurface = Color.Black
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryGreen,
    onPrimary = Color.White,
    secondary = AccentOrange,
    error = ErrorRed
)

@Composable
fun CommunityGoatTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
