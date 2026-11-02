package com.guru.composecookbook.spotify.ui.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SpotifySearchBar() {
  Card(
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSurface),
    shape = RoundedCornerShape(4.dp),
    modifier = Modifier.fillMaxWidth().padding(12.dp),
  ) {
    Row(
      horizontalArrangement = Arrangement.Center,
      modifier = Modifier.padding(4.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Icon(imageVector = Icons.Default.Search, tint = Color.LightGray, contentDescription = null)
      Text(
        text = "Artists, songs, or podcasts",
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier.padding(8.dp),
        style = MaterialTheme.typography.titleLarge.copy(fontSize = 14.sp),
      )
    }
  }
}
