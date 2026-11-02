package com.guru.composecookbook.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.guru.composecookbook.theme.ColorPallet.*

@Composable
fun ComposeCookBookMaterial3Theme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  colorPallet: ColorPallet = WALLPAPER,
  content: @Composable () -> Unit,
) {
  val context = LocalContext.current

  val colors =
    when (colorPallet) {
      GREEN -> if (darkTheme) darkColorSchemeFor(GREEN) else lightColorSchemeFor(GREEN)
      PURPLE -> if (darkTheme) darkColorSchemeFor(PURPLE) else lightColorSchemeFor(PURPLE)
      ORANGE -> if (darkTheme) darkColorSchemeFor(ORANGE) else lightColorSchemeFor(ORANGE)
      BLUE -> if (darkTheme) darkColorSchemeFor(BLUE) else lightColorSchemeFor(BLUE)
      WALLPAPER ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        else if (darkTheme) darkColorSchemeFor(GREEN) else lightColorSchemeFor(GREEN)
    }
  MaterialTheme(colorScheme = colors, content = content)
}
