package com.guru.composecookbook.spotify.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.data.model.Album
import com.guru.composecookbook.theme.graySurface

@Composable
fun SpotifyHomeGridItem(album: Album) {
  val cardColor = if (isSystemInDarkTheme()) graySurface else MaterialTheme.colorScheme.background
  Card(
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    colors = CardDefaults.cardColors(containerColor = cardColor),
    shape = RoundedCornerShape(8.dp),
    modifier = Modifier.clip(RoundedCornerShape(8.dp)).padding(8.dp).clickable(onClick = {}),
  ) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Image(
        painter = painterResource(id = album.imageId),
        contentDescription = null,
        modifier = Modifier.size(55.dp),
        contentScale = ContentScale.Crop,
      )
      Text(
        text = album.song,
        style = MaterialTheme.typography.titleLarge.copy(fontSize = 14.sp),
        modifier = Modifier.padding(horizontal = 8.dp),
      )
    }
  }
}

@Preview
@Composable
fun PreviewSpotifyHomeGridItem() {
  val album = remember { AlbumsDataProvider.album }
  SpotifyHomeGridItem(album)
}
