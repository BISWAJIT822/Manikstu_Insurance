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
