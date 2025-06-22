package com.guru.composecookbook.ui.home.advancelists

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.data.model.Album

@Composable
fun SwipeableLists() {
  val albums by remember { mutableStateOf(AlbumsDataProvider.albums) }
  LazyColumn {
    itemsIndexed(
      items = albums,
      itemContent = { index, album -> SwipeableListItem(index, album) { index -> } }
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun SwipeableListItem(index: Int, album: Album, onItemSwiped: (Int) -> Unit) {
  val dismissState =
    rememberSwipeToDismissBoxState(
      confirmValueChange = { dismissValue ->
        if (dismissValue == SwipeToDismissBoxValue.EndToStart) {
          onItemSwiped(index)
          true
        } else {
          false
        }
      }
    )

  SwipeToDismissBox(
    state = dismissState,
    backgroundContent = {
      Box(
        modifier =
          Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.errorContainer)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterEnd
      ) {
        Icon(
          imageVector = Icons.Default.Delete,
          contentDescription = "Delete",
          tint = MaterialTheme.colorScheme.onErrorContainer
        )
      }
    },
    content = { ListItemContent(album) },
    enableDismissFromStartToEnd = false,
    enableDismissFromEndToStart = true
  )
}

@Composable
private fun ListItemContent(album: Album) {
  Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
    Row(
      modifier = Modifier.fillMaxWidth().padding(16.dp),
      horizontalArrangement = Arrangement.spacedBy(16.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = album.song,
          style = MaterialTheme.typography.titleMedium,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
        Text(
          text = album.artist,
          style = MaterialTheme.typography.bodyMedium,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
      }
      IconButton(onClick = {}) {
        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More options")
      }
    }
  }
}
