package com.guru.composecookbook.datingapp.components.chat

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.theme.purple

@Composable
fun MatchSection() {
  val matches = AlbumsDataProvider.albums.takeLast(10)
  Text(
    text = "New matches",
    style =
      MaterialTheme.typography.titleLarge.copy(fontSize = 16.sp, lineHeight = TextUnit.Unspecified),
    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
    color = purple,
  )
  LazyRow { items(items = matches, itemContent = { MatchesImage(imageId = it.imageId) }) }
  Spacer(modifier = Modifier.height(24.dp))
}
