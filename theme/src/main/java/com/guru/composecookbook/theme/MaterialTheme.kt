package com.guru.composecookbook.theme


import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.guru.composecookbook.theme.ColorPallet.*

// dark palettes
private val DarkGreenColorPalette = darkColorScheme(
    primary = green200,
    primaryContainer = green700,
    secondary = teal200,
    secondaryContainer = green700,
    onSecondaryContainer = Color.Black,
    background = Color.Black,
    surface = Color.Black,
    surfaceVariant = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onSurfaceVariant = Color.White,
    error = Color.Red,
)

private val DarkPurpleColorPalette = darkColorScheme(
    primary = purple200,
    primaryContainer = purple700,
    secondary = teal200,
    secondaryContainer = purple700,
    onSecondaryContainer = Color.White,
    background = Color.Black,
    surface = Color.Black,
    surfaceVariant = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onSurfaceVariant = Color.White,
    error = Color.Red,
)

private val DarkBlueColorPalette = darkColorScheme(
    primary = blue200,
    primaryContainer = blue700,
    secondary = teal200,
    secondaryContainer = blue700,
    onSecondaryContainer = Color.White,
    background = Color.Black,
    surface = Color.Black,
    surfaceVariant = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onSurfaceVariant = Color.White,
    error = Color.Red,
)

private val DarkOrangeColorPalette = darkColorScheme(
    primary = orange200,
    primaryContainer = orange700,
    secondary = teal200,
    secondaryContainer = orange700,
    onSecondaryContainer = Color.White,
    background = Color.Black,
    surface = Color.Black,
    surfaceVariant = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onSurfaceVariant = Color.White,
    error = Color.Red,
)

// Light pallets
private val LightGreenColorPalette = lightColorScheme(
    primary = green500,
    primaryContainer = green700,
    secondary = teal200,
    secondaryContainer = green700,
    onSecondaryContainer = Color.White,
    background = Color.White,
    surface = Color.White,
    surfaceVariant = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onSurfaceVariant = Color.Black
)

private val LightPurpleColorPalette = lightColorScheme(
    primary = purple,
    primaryContainer = purple700,
    secondary = teal200,
    secondaryContainer = purple700,
    onSecondaryContainer = Color.White,
    background = Color.White,
    surface = Color.White,
    surfaceVariant = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onSurfaceVariant = Color.Black,
)

private val LightBlueColorPalette = lightColorScheme(
    primary = blue500,
    primaryContainer = blue700,
    secondary = teal200,
    secondaryContainer = blue700,
    onSecondaryContainer = Color.White,
    background = Color.White,
    surface = Color.White,
    surfaceVariant = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onSurfaceVariant = Color.Black,
)

private val LightOrangeColorPalette = lightColorScheme(
    primary = orange500,
    primaryContainer = orange700,
    secondary = teal200,
    secondaryContainer = orange700,
    onSecondaryContainer = Color.White,
    background = Color.White,
    surface = Color.White,
    surfaceVariant = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onSurfaceVariant = Color.Black,
)


@Composable
fun ComposeCookBookMaterialTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colorPallet: ColorPallet = WALLPAPER,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current

    val colors = when (colorPallet) {
        GREEN -> if (darkTheme) DarkGreenColorPalette else LightGreenColorPalette
        PURPLE -> if (darkTheme) DarkPurpleColorPalette else LightPurpleColorPalette
        ORANGE -> if (darkTheme) DarkOrangeColorPalette else LightOrangeColorPalette
        BLUE -> if (darkTheme) DarkBlueColorPalette else LightBlueColorPalette
        WALLPAPER -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            if (darkTheme)
                dynamicDarkColorScheme(context)
            else
                dynamicLightColorScheme(context)
        else
            if (darkTheme)
                DarkGreenColorPalette
            else LightGreenColorPalette
    }
    androidx.compose.material3.MaterialTheme(
        colorScheme = colors,
        content = content
    )
}
