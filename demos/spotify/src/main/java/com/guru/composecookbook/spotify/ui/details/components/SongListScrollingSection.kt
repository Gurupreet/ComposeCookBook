package com.guru.composecookbook.spotify.ui.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.theme.green700

@Composable
fun SongListScrollingSection() {
  ShuffleButton()
  DownloadedRow()
  val items = remember { AlbumsDataProvider.albums }
  items.forEach { SpotifySongListItem(album = it) }
}

@Composable
fun DownloadedRow() {
  Row(
    modifier = Modifier.fillMaxWidth().padding(16.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
  ) {
    Text(
      text = "Download",
      style = MaterialTheme.typography.titleLarge.copy(fontSize = 14.sp),
      color = MaterialTheme.colorScheme.onSurface,
    )
    var switched by remember { mutableStateOf(true) }
    Switch(
      checked = switched,
      colors =
        SwitchDefaults.colors(
          checkedThumbColor = MaterialTheme.colorScheme.primary,
          // The M3 checked track defaults to primary, the same color as the thumb; the M2
          // checked track was primary at 0.54 alpha, so keep the thumb visible.
          checkedTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.54f),
        ),
      modifier = Modifier.padding(8.dp),
      onCheckedChange = { switched = it },
    )
  }
}

@Composable
fun ShuffleButton() {
  Button(
    onClick = {},
    colors = ButtonDefaults.buttonColors(containerColor = green700),
    modifier =
      Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 100.dp).clip(CircleShape),
  ) {
    Text(
      text = "SHUFFLE PLAY",
      style = MaterialTheme.typography.titleLarge.copy(fontSize = 14.sp),
      color = MaterialTheme.colorScheme.onSurface,
      modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
    )
  }
}
