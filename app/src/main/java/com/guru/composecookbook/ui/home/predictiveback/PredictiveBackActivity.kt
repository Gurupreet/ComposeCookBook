package com.guru.composecookbook.ui.home.predictiveback

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.PredictiveBackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme
import com.guru.composecookbook.ui.home.dynamic.DynamicUIActivity
import kotlin.coroutines.cancellation.CancellationException

/**
 * Demonstrates the Android 15+ predictive back gesture with Compose's [PredictiveBackHandler]. An
 * in-app navigation drawer follows the user's back-swipe progress in real time — it slides out and
 * scales as the gesture proceeds, commits closed when the gesture completes, and springs back if
 * the gesture is cancelled.
 */
class PredictiveBackActivity : ComponentActivity() {

  private val isDarkTheme: Boolean by lazy {
    intent?.getBooleanExtra(DynamicUIActivity.DARK_THEME, false) ?: false
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent { ComposeCookBookMaterial3Theme(isDarkTheme) { PredictiveBackDemoScreen() } }
  }

  companion object {
    fun newIntent(context: Context, isDarkTheme: Boolean) =
      Intent(context, PredictiveBackActivity::class.java).apply {
        putExtra(DynamicUIActivity.DARK_THEME, isDarkTheme)
      }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PredictiveBackDemoScreen() {
  var drawerOpen by remember { mutableStateOf(true) }
  // Live gesture progress (0f..1f) reported by the predictive back handler.
  var backProgress by remember { mutableFloatStateOf(0f) }

  // Only intercept back while the drawer is open; when closed the system handles back normally.
  PredictiveBackHandler(enabled = drawerOpen) { progress ->
    try {
      progress.collect { backEvent -> backProgress = backEvent.progress }
      // Gesture committed -> close the drawer instead of finishing the activity.
      drawerOpen = false
      backProgress = 0f
    } catch (e: CancellationException) {
      // Gesture cancelled -> snap the drawer back open.
      backProgress = 0f
      throw e
    }
  }

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("Predictive Back") },
        actions = {
          IconButton(onClick = { drawerOpen = !drawerOpen }) {
            Icon(Icons.AutoMirrored.Filled.MenuOpen, contentDescription = "Toggle drawer")
          }
        },
      )
    }
  ) { padding ->
    Box(modifier = Modifier.fillMaxSize().padding(padding)) {
      DetailContent(drawerOpen = drawerOpen, onOpenDrawer = { drawerOpen = true })

      if (drawerOpen) {
        // Translate + scale + fade the drawer in lock-step with the gesture so the user sees
        // exactly where releasing will take them (the predictive back affordance).
        val animatedProgress by animateFloatAsState(backProgress, label = "backProgress")
        DemoDrawer(
          modifier =
            Modifier.graphicsLayer {
              translationX = -size.width * 0.25f * animatedProgress
              scaleX = 1f - 0.1f * animatedProgress
              scaleY = 1f - 0.1f * animatedProgress
              alpha = 1f - 0.5f * animatedProgress
            }
        )
      }
    }
  }
}

@Composable
private fun DetailContent(drawerOpen: Boolean, onOpenDrawer: () -> Unit) {
  Column(
    modifier = Modifier.fillMaxSize().padding(24.dp),
    verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp),
  ) {
    Text("Content area", style = MaterialTheme.typography.headlineSmall)
    Text(
      if (drawerOpen)
        "Swipe from the screen edge (or press back) — the drawer follows your gesture and " +
          "closes when you commit, instead of leaving the screen."
      else "Drawer closed. Press back again to leave, or tap below to reopen it.",
      style = MaterialTheme.typography.bodyMedium,
    )
    if (!drawerOpen) {
      androidx.compose.material3.Button(onClick = onOpenDrawer) { Text("Reopen drawer") }
    }
  }
}

@Composable
private fun DemoDrawer(modifier: Modifier = Modifier) {
  val items = listOf("Inbox", "Starred", "Sent", "Drafts", "Spam", "Trash")
  Box(
    modifier =
      modifier
        .fillMaxHeight()
        .width(260.dp)
        .background(MaterialTheme.colorScheme.surfaceContainerHigh)
  ) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
      Text(
        "Mail",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
      )
      LazyColumn {
        items(items) { label ->
          ListItem(
            headlineContent = { Text(label) },
            colors =
              androidx.compose.material3.ListItemDefaults.colors(
                containerColor = androidx.compose.ui.graphics.Color.Transparent
              ),
          )
        }
      }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
      Text(
        "← swipe back to close",
        style = MaterialTheme.typography.labelSmall,
        modifier = Modifier.padding(16.dp),
      )
    }
  }
}
