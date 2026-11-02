package com.guru.composecookbook.moviesapp.ui.watch.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun EmptyWatchlistSection() {
  Column {
    Spacer(modifier = Modifier.padding(100.dp))
    Text(
      text = "Watchlist is empty",
      style = MaterialTheme.typography.titleLarge,
      modifier = Modifier.fillMaxWidth(),
      textAlign = TextAlign.Center,
    )
    Text(
      text = "Please add some movies to your watchlist",
      style = MaterialTheme.typography.bodySmall,
      textAlign = TextAlign.Center,
      modifier = Modifier.fillMaxWidth(),
    )
  }
}
