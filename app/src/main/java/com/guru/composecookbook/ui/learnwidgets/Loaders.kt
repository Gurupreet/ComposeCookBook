package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Loaders() {
  Text(
    text = "Progress bars",
    style = MaterialTheme.typography.titleLarge,
    modifier = Modifier.padding(8.dp),
  )

  // The M3 track defaults to secondaryContainer (a dark palette color here); the M2 track was the
  // bar color at 0.24 alpha and ran edge-to-edge with no stop indicators. The no-progress overload
  // (used here) has no stop indicators, so gapSize = 0.dp and trackColor reproduce the M2 look.
  val linearProgress: @Composable () -> Unit = {
    LinearProgressIndicator(
      trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.24f),
      gapSize = 0.dp,
    )
  }

  Row(modifier = Modifier.padding(8.dp)) {
    linearProgress()
    CircularProgressIndicator()
    CircularProgressIndicator(strokeWidth = 8.dp)
  }

  Column(
    modifier = Modifier.fillMaxWidth().padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    linearProgress()
    Text(text = "Loading with text...", modifier = Modifier.padding(8.dp))
  }
}
