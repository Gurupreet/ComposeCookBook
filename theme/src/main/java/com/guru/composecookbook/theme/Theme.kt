package com.guru.composecookbook.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme as Material2MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme as Material3MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.guru.composecookbook.theme.ColorPallet.*

// Material 2 bridge for unmigrated modules; removed in the #238 cleanup PR.
// dark palettes
private val DarkGreenColorPalette =
  darkColors(
    primary = green200,
    primaryVariant = green700,
    secondary = teal200,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
  )

private val DarkPurpleColorPalette =
  darkColors(
    primary = purple200,
    primaryVariant = purple700,
    secondary = teal200,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
  )

private val DarkBlueColorPalette =
  darkColors(
    primary = blue200,
    primaryVariant = blue700,
    secondary = teal200,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
  )

private val DarkOrangeColorPalette =
  darkColors(
    primary = orange200,
    primaryVariant = orange700,
    secondary = teal200,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
  )

// Material 2 bridge for unmigrated modules; removed in the #238 cleanup PR.
// Light pallets
private val LightGreenColorPalette =
  lightColors(
    primary = green500,
    primaryVariant = green700,
    secondary = teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
  )

private val LightPurpleColorPalette =
  lightColors(
    primary = purple,
    primaryVariant = purple700,
    secondary = teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
  )

private val LightBlueColorPalette =
  lightColors(
    primary = blue500,
    primaryVariant = blue700,
    secondary = teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
  )

private val LightOrangeColorPalette =
  lightColors(
    primary = orange500,
    primaryVariant = orange700,
    secondary = teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
  )

enum class ColorPallet {
  PURPLE,
  GREEN,
  ORANGE,
  BLUE,
  WALLPAPER,
}

/**
 * Material 3 color scheme for a [colorPallet] in light mode, built from the same palette colors as
 * the Material 2 [LightGreenColorPalette]/[LightPurpleColorPalette]/[LightBlueColorPalette]/
 * [LightOrangeColorPalette] palettes above. The secondary and tertiary roles reuse the M2 palette
 * colors; the remaining roles (surface containers, inverse, outline and tertiary tones) are set
 * explicitly so no component falls back to the Material 3 baseline purple. Shared with
 * [ComposeCookBookMaterial3Theme].
 */
internal fun lightColorSchemeFor(colorPallet: ColorPallet): ColorScheme =
  when (colorPallet) {
    GREEN ->
      lightColorScheme(
        primary = green500,
        primaryContainer = green200,
        onPrimaryContainer = green700,
        secondary = teal200,
        secondaryContainer = green700,
        onSecondaryContainer = Color.White,
        tertiary = teal200,
        onTertiary = Color.Black,
        tertiaryContainer = Color(0xFFE0F5F9),
        onTertiaryContainer = Color.Black,
        background = Color.White,
        surface = Color.White,
        surfaceVariant = Color.White,
        onPrimary = Color.White,
        onSecondary = Color.Black,
        onBackground = Color.Black,
        onSurface = Color.Black,
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
        surfaceTint = green500,
        inversePrimary = green200,
      )
    PURPLE ->
      lightColorScheme(
        primary = purple,
        primaryContainer = purple700,
        onPrimaryContainer = Color.White,
        secondary = teal200,
        secondaryContainer = purple700,
        onSecondaryContainer = Color.White,
        tertiary = teal200,
        onTertiary = Color.Black,
        tertiaryContainer = Color(0xFFE0F5F9),
        onTertiaryContainer = Color.Black,
        background = Color.White,
        surface = Color.White,
        surfaceVariant = Color.White,
        onPrimary = Color.White,
        onSecondary = Color.Black,
        onBackground = Color.Black,
        onSurface = Color.Black,
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
        surfaceTint = purple,
        inversePrimary = purple200,
      )
    ORANGE ->
      lightColorScheme(
        primary = orange500,
        primaryContainer = orange700,
        onPrimaryContainer = Color.White,
        secondary = teal200,
        secondaryContainer = orange700,
        onSecondaryContainer = Color.White,
        tertiary = teal200,
        onTertiary = Color.Black,
        tertiaryContainer = Color(0xFFE0F5F9),
        onTertiaryContainer = Color.Black,
        background = Color.White,
        surface = Color.White,
        surfaceVariant = Color.White,
        onPrimary = Color.White,
        onSecondary = Color.Black,
        onBackground = Color.Black,
        onSurface = Color.Black,
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
        surfaceTint = orange500,
        inversePrimary = orange200,
      )
    BLUE ->
      lightColorScheme(
        primary = blue500,
        primaryContainer = blue700,
        onPrimaryContainer = Color.White,
        secondary = teal200,
        secondaryContainer = blue700,
        onSecondaryContainer = Color.White,
        tertiary = teal200,
        onTertiary = Color.Black,
        tertiaryContainer = Color(0xFFE0F5F9),
        onTertiaryContainer = Color.Black,
        background = Color.White,
        surface = Color.White,
        surfaceVariant = Color.White,
        onPrimary = Color.White,
        onSecondary = Color.Black,
        onBackground = Color.Black,
        onSurface = Color.Black,
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
        surfaceTint = blue500,
        inversePrimary = blue200,
      )
    else ->
      lightColorScheme(
        primary = blue500,
        primaryContainer = blue700,
        onPrimaryContainer = Color.White,
        secondary = teal200,
        secondaryContainer = blue700,
        onSecondaryContainer = Color.White,
        tertiary = teal200,
        onTertiary = Color.Black,
        tertiaryContainer = Color(0xFFE0F5F9),
        onTertiaryContainer = Color.Black,
        background = Color.White,
        surface = Color.White,
        surfaceVariant = Color.White,
        onPrimary = Color.White,
        onSecondary = Color.Black,
        onBackground = Color.Black,
        onSurface = Color.Black,
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
        surfaceTint = blue500,
        inversePrimary = blue200,
      )
  }

/**
 * Material 3 color scheme for a [colorPallet] in dark mode, built from the same palette colors as
 * the Material 2 [DarkGreenColorPalette]/[DarkPurpleColorPalette]/[DarkBlueColorPalette]/
 * [DarkOrangeColorPalette] palettes above. The secondary and tertiary roles reuse the M2 palette
 * colors; the remaining roles (surface containers, inverse, outline and tertiary tones) are set
 * explicitly so no component falls back to the Material 3 baseline purple. Shared with
 * [ComposeCookBookMaterial3Theme].
 */
internal fun darkColorSchemeFor(colorPallet: ColorPallet): ColorScheme =
  when (colorPallet) {
    GREEN ->
      darkColorScheme(
        primary = green500,
        primaryContainer = green200,
        onPrimaryContainer = green700,
        secondary = teal200,
        secondaryContainer = green700,
        onSecondaryContainer = Color.Black,
        tertiary = teal200,
        onTertiary = Color.Black,
        tertiaryContainer = teal200,
        onTertiaryContainer = Color.Black,
        background = Color.Black,
        surface = Color.Black,
        surfaceVariant = Color.Black,
        onPrimary = Color.Black,
        onSecondary = Color.White,
        onBackground = Color.White,
        onSurface = Color.White,
        onSurfaceVariant = Color.White,
        inverseSurface = Color.White,
        inverseOnSurface = Color.Black,
        outline = Color(0xFF757575),
        outlineVariant = Color(0xFF1F1F1F),
        scrim = Color.White,
        surfaceContainerLowest = Color(0xFF0E0E0E),
        surfaceContainerLow = Color(0xFF131313),
        surfaceContainer = Color(0xFF191919),
        surfaceContainerHigh = Color(0xFF212121),
        surfaceContainerHighest = Color(0xFF2A2A2A),
        surfaceDim = Color(0xFF0E0E0E),
        surfaceBright = Color(0xFF2A2A2A),
        surfaceTint = green500,
        inversePrimary = green700,
        error = Color.Red,
        onError = Color.White,
      )
    PURPLE ->
      darkColorScheme(
        primary = purple200,
        primaryContainer = purple700,
        onPrimaryContainer = Color.White,
        secondary = teal200,
        secondaryContainer = purple700,
        onSecondaryContainer = Color.White,
        tertiary = teal200,
        onTertiary = Color.Black,
        tertiaryContainer = teal200,
        onTertiaryContainer = Color.Black,
        background = Color.Black,
        surface = Color.Black,
        surfaceVariant = Color.Black,
        onPrimary = Color.Black,
        onSecondary = Color.White,
        onBackground = Color.White,
        onSurface = Color.White,
        onSurfaceVariant = Color.White,
        inverseSurface = Color.White,
        inverseOnSurface = Color.Black,
        outline = Color(0xFF757575),
        outlineVariant = Color(0xFF1F1F1F),
        scrim = Color.White,
        surfaceContainerLowest = Color(0xFF0E0E0E),
        surfaceContainerLow = Color(0xFF131313),
        surfaceContainer = Color(0xFF191919),
        surfaceContainerHigh = Color(0xFF212121),
        surfaceContainerHighest = Color(0xFF2A2A2A),
        surfaceDim = Color(0xFF0E0E0E),
        surfaceBright = Color(0xFF2A2A2A),
        surfaceTint = purple200,
        inversePrimary = purple700,
        error = Color.Red,
        onError = Color.White,
      )
    ORANGE ->
      darkColorScheme(
        primary = orange200,
        primaryContainer = orange700,
        onPrimaryContainer = Color.White,
        secondary = teal200,
        secondaryContainer = orange700,
        onSecondaryContainer = Color.White,
        tertiary = teal200,
        onTertiary = Color.Black,
        tertiaryContainer = teal200,
        onTertiaryContainer = Color.Black,
        background = Color.Black,
        surface = Color.Black,
        surfaceVariant = Color.Black,
        onPrimary = Color.Black,
        onSecondary = Color.White,
        onBackground = Color.White,
        onSurface = Color.White,
        onSurfaceVariant = Color.White,
        inverseSurface = Color.White,
        inverseOnSurface = Color.Black,
        outline = Color(0xFF757575),
        outlineVariant = Color(0xFF1F1F1F),
        scrim = Color.White,
        surfaceContainerLowest = Color(0xFF0E0E0E),
        surfaceContainerLow = Color(0xFF131313),
        surfaceContainer = Color(0xFF191919),
        surfaceContainerHigh = Color(0xFF212121),
        surfaceContainerHighest = Color(0xFF2A2A2A),
        surfaceDim = Color(0xFF0E0E0E),
        surfaceBright = Color(0xFF2A2A2A),
        surfaceTint = orange200,
        inversePrimary = orange700,
        error = Color.Red,
        onError = Color.White,
      )
    BLUE ->
      darkColorScheme(
        primary = blue200,
        primaryContainer = blue700,
        onPrimaryContainer = Color.White,
        secondary = teal200,
        secondaryContainer = blue700,
        onSecondaryContainer = Color.White,
        tertiary = teal200,
        onTertiary = Color.Black,
        tertiaryContainer = teal200,
        onTertiaryContainer = Color.Black,
        background = Color.Black,
        surface = Color.Black,
        surfaceVariant = Color.Black,
        onPrimary = Color.Black,
        onSecondary = Color.White,
        onBackground = Color.White,
        onSurface = Color.White,
        onSurfaceVariant = Color.White,
        inverseSurface = Color.White,
        inverseOnSurface = Color.Black,
        outline = Color(0xFF757575),
        outlineVariant = Color(0xFF1F1F1F),
        scrim = Color.White,
        surfaceContainerLowest = Color(0xFF0E0E0E),
        surfaceContainerLow = Color(0xFF131313),
        surfaceContainer = Color(0xFF191919),
        surfaceContainerHigh = Color(0xFF212121),
        surfaceContainerHighest = Color(0xFF2A2A2A),
        surfaceDim = Color(0xFF0E0E0E),
        surfaceBright = Color(0xFF2A2A2A),
        surfaceTint = blue200,
        inversePrimary = blue700,
        error = Color.Red,
        onError = Color.White,
      )
    else ->
      darkColorScheme(
        primary = blue200,
        primaryContainer = blue700,
        onPrimaryContainer = Color.White,
        secondary = teal200,
        secondaryContainer = blue700,
        onSecondaryContainer = Color.White,
        tertiary = teal200,
        onTertiary = Color.Black,
        tertiaryContainer = teal200,
        onTertiaryContainer = Color.Black,
        background = Color.Black,
        surface = Color.Black,
        surfaceVariant = Color.Black,
        onPrimary = Color.Black,
        onSecondary = Color.White,
        onBackground = Color.White,
        onSurface = Color.White,
        onSurfaceVariant = Color.White,
        inverseSurface = Color.White,
        inverseOnSurface = Color.Black,
        outline = Color(0xFF757575),
        outlineVariant = Color(0xFF1F1F1F),
        scrim = Color.White,
        surfaceContainerLowest = Color(0xFF0E0E0E),
        surfaceContainerLow = Color(0xFF131313),
        surfaceContainer = Color(0xFF191919),
        surfaceContainerHigh = Color(0xFF212121),
        surfaceContainerHighest = Color(0xFF2A2A2A),
        surfaceDim = Color(0xFF0E0E0E),
        surfaceBright = Color(0xFF2A2A2A),
        surfaceTint = blue200,
        inversePrimary = blue700,
        error = Color.Red,
        onError = Color.White,
      )
  }

@Composable
fun ComposeCookBookTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  colorPallet: ColorPallet = GREEN,
  content: @Composable () -> Unit,
) {
  val colorScheme =
    if (darkTheme) darkColorSchemeFor(colorPallet) else lightColorSchemeFor(colorPallet)

  // Typography and shapes stay at Material 3 defaults, matching ComposeCookBookMaterial3Theme, so
  // screens already on Material 3 keep their current look.
  Material3MaterialTheme(colorScheme = colorScheme) {
    // Material 2 bridge for unmigrated modules; removed in the #238 cleanup PR.
    val colors =
      when (colorPallet) {
        GREEN -> if (darkTheme) DarkGreenColorPalette else LightGreenColorPalette
        PURPLE -> if (darkTheme) DarkPurpleColorPalette else LightPurpleColorPalette
        ORANGE -> if (darkTheme) DarkOrangeColorPalette else LightOrangeColorPalette
        BLUE -> if (darkTheme) DarkBlueColorPalette else LightBlueColorPalette
        else -> if (darkTheme) DarkBlueColorPalette else LightBlueColorPalette
      }

    Material2MaterialTheme(
      colors = colors,
      typography = typography,
      shapes = shapes,
      content = content,
    )
  }
}
