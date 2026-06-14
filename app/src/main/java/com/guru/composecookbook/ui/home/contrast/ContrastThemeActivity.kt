package com.guru.composecookbook.ui.home.contrast

import android.app.UiModeManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

/**
 * Demonstrates accessibility-focused, contrast-aware Material 3 theming. Material 3 ships three
 * tonal contrast levels (Standard / Medium / High); higher contrast widens the luminance gap
 * between foreground and background for low-vision users.
 *
 * The demo lets the user switch contrast at runtime and, on Android 14+, reads the *system*
 * contrast preference (`UiModeManager.getContrast()`) as the initial value.
 */
class ContrastThemeActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent { ContrastThemeDemo() }
  }

  companion object {
    fun newIntent(context: Context) = Intent(context, ContrastThemeActivity::class.java)
  }
}

enum class ContrastLevel(val label: String) {
  Standard("Standard"),
  Medium("Medium"),
  High("High"),
}

/** Reads the system contrast setting (API 34+) and maps it to a [ContrastLevel]. */
private fun systemContrastLevel(context: Context): ContrastLevel {
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
    val uiModeManager = context.getSystemService(Context.UI_MODE_SERVICE) as? UiModeManager
    val contrast = uiModeManager?.contrast ?: 0f
    return when {
      contrast >= 0.66f -> ContrastLevel.High
      contrast >= 0.33f -> ContrastLevel.Medium
      else -> ContrastLevel.Standard
    }
  }
  return ContrastLevel.Standard
}

@Composable
private fun contrastColorScheme(level: ContrastLevel, dark: Boolean): ColorScheme {
  // Hand-tuned primaries per contrast level. In a real app these come from the Material Theme
  // Builder export, which generates Standard/Medium/High variants for the same source color.
  return if (dark) {
    when (level) {
      ContrastLevel.Standard ->
        darkColorScheme(primary = Color(0xFF9ECAFF), onPrimary = Color(0xFF003258))
      ContrastLevel.Medium ->
        darkColorScheme(primary = Color(0xFFC2E0FF), onPrimary = Color(0xFF001A33))
      ContrastLevel.High ->
        darkColorScheme(primary = Color(0xFFEAF1FF), onPrimary = Color(0xFF000000))
    }
  } else {
    when (level) {
      ContrastLevel.Standard ->
        lightColorScheme(primary = Color(0xFF0061A4), onPrimary = Color.White)
      ContrastLevel.Medium -> lightColorScheme(primary = Color(0xFF00497D), onPrimary = Color.White)
      ContrastLevel.High -> lightColorScheme(primary = Color(0xFF00264A), onPrimary = Color.White)
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContrastThemeDemo() {
  val context = LocalContext.current
  val dark = isSystemInDarkTheme()
  var level by remember { mutableStateOf(systemContrastLevel(context)) }

  MaterialTheme(colorScheme = contrastColorScheme(level, dark)) {
    Scaffold { padding ->
      Column(
        modifier =
          Modifier.fillMaxSize()
            .padding(padding)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
      ) {
        Text("Contrast-aware Material 3", style = MaterialTheme.typography.headlineSmall)
        Text(
          "Switch the contrast level and watch foreground/background separation increase. " +
            "On Android 14+ the initial level mirrors the system Accessibility › Contrast setting.",
          style = MaterialTheme.typography.bodyMedium,
        )

        SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
          ContrastLevel.entries.forEachIndexed { index, value ->
            SegmentedButton(
              selected = level == value,
              onClick = { level = value },
              shape =
                SegmentedButtonDefaults.itemShape(index = index, count = ContrastLevel.entries.size),
            ) {
              Text(value.label)
            }
          }
        }

        SampleComponents()
      }
    }
  }
}

@Composable
private fun SampleComponents() {
  Card(modifier = Modifier.fillMaxWidth()) {
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
      Text("Primary on surface", style = MaterialTheme.typography.titleMedium)
      Text("Body text against the surface color.", style = MaterialTheme.typography.bodyMedium)
      Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Button(onClick = {}) { Text("Filled") }
        FilledTonalButton(onClick = {}) { Text("Tonal") }
        OutlinedButton(onClick = {}) { Text("Outlined") }
      }
    }
  }
}
