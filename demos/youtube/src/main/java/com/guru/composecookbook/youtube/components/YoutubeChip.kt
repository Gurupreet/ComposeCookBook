package com.guru.composecookbook.youtube.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun YoutubeChip(selected: Boolean, text: String, modifier: Modifier = Modifier) {
  Surface(
    color =
      when {
        // Derive light/dark from the applied colorScheme (not the system setting): this chip is
        // reused by the app Widgets tab and AdvanceListsActivity, whose dark mode is chosen by an
        // in-app toggle / intent flag and can disagree with the system.
        selected ->
          MaterialTheme.colorScheme.onSurface.copy(
            alpha = if (MaterialTheme.colorScheme.surface.luminance() < 0.5f) 1f else 0.7f
          )
        else -> MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.05f)
      },
    contentColor =
      when {
        selected -> MaterialTheme.colorScheme.surface
        else -> MaterialTheme.colorScheme.onSurface
      },
    shape = CircleShape,
    border =
      BorderStroke(
        width = 1.dp,
        color =
          when {
            selected -> MaterialTheme.colorScheme.surface
            else -> Color.LightGray
          },
      ),
    modifier = modifier,
  ) {
    Text(
      text = text,
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.bodyMedium,
      modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
    )
  }
}
