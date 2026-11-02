package com.guru.composecookbook.ui.home.customfling

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.R
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.ui.utils.TestTags

/**
 * This Activity renders the Settings page.
 *
 * @author https://github.com/iamjosephmj
 */
class FlingListSettingsActivity : ComponentActivity() {

  // Default Light/Dark selection from previous activity.
  private val isDarkTheme: Boolean by lazy { intent?.getBooleanExtra(DARK_THEME, false) ?: false }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      BaseView(isDarkTheme) {
        SettingsContent({
          /*
           * To handle onBackPressed in the activity, This will redirect the user
           * to the previous page. The Previous page will be listening to the result
           * from this activity.
           */
          finishWithStatus(Activity.RESULT_CANCELED)
        }) {
          /*
           * This is the onApply Click action. With this action,
           * all the values set by the user are saved.
           */
          finishWithStatus(Activity.RESULT_OK)
        }
      }
    }
  }

  /**
   * This function stops the activity with a specific status.
   *
   * @param status: This can be Activity.RESULT_OK or Activity.CANCEL
   */
  private fun finishWithStatus(status: Int) {
    setResult(status)
    finish()
  }

  companion object {
    const val DARK_THEME = "darkTheme"

    /** Starts the {@see FlingListSettingsActivity} with current theme as {@see Intent} data. */
    fun newIntent(context: Context, isDarkTheme: Boolean) =
      Intent(context, FlingListSettingsActivity::class.java).apply {
        putExtra(DARK_THEME, isDarkTheme)
      }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsContent(onback: () -> Unit, onApply: () -> Unit) {
  /*
   * This is done to match up the designs of the present page with the other pages of the App.
   */
  // The old M2 TopAppBar used the default primarySurface background (primary in light, an elevated
  // surface in dark) and carried an 8.dp elevation. The M3 bar has no static elevation (accepted
  // standard-M3 delta: flat bar), so the background is reproduced from the colorScheme: primary /
  // onPrimary in light, surfaceContainer / onSurface in dark (derived from the surface
  // luminance, never isSystemInDarkTheme).
  val isDark = MaterialTheme.colorScheme.surface.luminance() < 0.5f
  val barContainer =
    if (isDark) MaterialTheme.colorScheme.surfaceContainer else MaterialTheme.colorScheme.primary
  val barContent =
    if (isDark) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onPrimary
  Scaffold(
    topBar = {
      TopAppBar(
        colors =
          TopAppBarDefaults.topAppBarColors(
            containerColor = barContainer,
            titleContentColor = barContent,
            navigationIconContentColor = barContent,
          ),
        title = {
          Column(modifier = Modifier.padding(4.dp)) {
            /*
             * Presently I am setting the heading as `Settings`.
             * @gurupreet, please let me know If I need to change this.
             */
            Text(text = "Settings", modifier = Modifier.testTag(TestTags.HOME_FLING_SETTINGS_ROOT))
          }
        },
        navigationIcon = {
          IconButton(onClick = onback) {
            Icon(Icons.Filled.ArrowBack, contentDescription = stringResource(id = R.string.cd_back))
          }
        },
      )
    },
    content = { paddingValues ->
      RenderSettingsPage(onApplyClick = onApply, modifier = Modifier.padding(paddingValues))
    },
  )
}

@Composable
private fun BaseView(isDarkTheme: Boolean, content: @Composable () -> Unit) {
  ComposeCookBookTheme(isDarkTheme) { content() }
}
