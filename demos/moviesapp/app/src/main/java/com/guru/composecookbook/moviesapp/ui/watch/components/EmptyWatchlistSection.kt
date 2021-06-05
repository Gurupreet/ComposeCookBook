package com.guru.composecookbook.moviesapp.ui.watch.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.typography

@Composable
fun EmptyWatchlistSection() {
    Column {
        Spacer(modifier = Modifier.padding(100.dp))
        Text(
            text = "Watchlist is empty",
            style = typography.h6,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Please add some movies to your watchlist",
            style = typography.caption,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
