package com.guru.composecookbook.spotify.ui.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.data.model.Album

@Composable
fun SpotifySongListItem(album: Album) {
  Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
    Image(
      painter = painterResource(id = album.imageId),
      contentDescription = null,
      contentScale = ContentScale.Crop,
      modifier = Modifier.size(55.dp).padding(4.dp),
    )
    Column(modifier = Modifier.padding(horizontal = 4.dp).weight(1f)) {
      Text(
        text = album.song,
        style = MaterialTheme.typography.titleLarge.copy(fontSize = 16.sp),
        color = MaterialTheme.colorScheme.onSurface,
      )
      Text(
        text = "${album.artist}, ${album.descriptions}",
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Normal),
        color = Color.Gray,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
      )
    }
    if (album.id % 3 == 0) {
      Icon(
        imageVector = Icons.Default.Favorite,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primaryContainer,
        modifier = Modifier.padding(4.dp).size(20.dp),
      )
    }
    Icon(
      imageVector = Icons.Default.MoreVert,
      contentDescription = null,
      tint = Color.LightGray,
      modifier = Modifier.padding(4.dp),
    )
  }
}

@Composable
@Preview
fun PreviewSpotifySongListItem() {
  val album = AlbumsDataProvider.album
  SpotifySongListItem(album = album)
}
