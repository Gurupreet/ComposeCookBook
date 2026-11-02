package com.guru.composecookbook.meditation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme =
  lightColorScheme(
    primary = ButtonBlue,
    onPrimary = Color.White,
    primaryContainer = ButtonBlue,
    onPrimaryContainer = Color.White,
    inversePrimary = ButtonBlue,
    secondary = ButtonBlue,
    onSecondary = Color.White,
    secondaryContainer = ButtonBlue,
    onSecondaryContainer = Color.White,
    tertiary = ButtonBlue,
    onTertiary = Color.White,
    tertiaryContainer = ButtonBlue,
    onTertiaryContainer = Color.White,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    surfaceVariant = Color.White,
    onSurfaceVariant = Color.Black,
    inverseSurface = Color.Black,
    inverseOnSurface = Color.White,
    outline = Color(0xFF9E9E9E),
    outlineVariant = Color(0xFFE0E0E0),
    scrim = Color.Black,
    surfaceContainerLowest = Color.White,
    surfaceContainerLow = Color(0xFFF7F7F7),
    surfaceContainer = Color(0xFFF2F2F2),
    surfaceContainerHigh = Color(0xFFECECEC),
    surfaceContainerHighest = Color(0xFFE6E6E6),
    surfaceDim = Color.White,
    surfaceBright = Color.White,
    surfaceTint = ButtonBlue,
  )

@Composable
fun MeditationAppTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable() () -> Unit,
) {
  MaterialTheme(
    colorScheme = LightColorScheme,
    typography = Typography,
    shapes = Shapes,
    content = content,
  )
}
