package com.guru.composecookbook.ui.home.advancelists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.data.model.Album
import kotlinx.coroutines.launch

/**
 * Demonstrates Material3 [SwipeToDismissBox]:
 * - swipe left (end to start) to delete an item,
 * - swipe right (start to end) to archive it,
 * - either action removes the item and offers Undo via a [Snackbar].
 */
@Composable
fun SwipeableLists() {
  val albums = remember { AlbumsDataProvider.albums.toMutableStateList() }
  val snackbarHostState = remember { SnackbarHostState() }
  val scope = rememberCoroutineScope()

  Box(modifier = Modifier.fillMaxSize()) {
    LazyColumn {
      items(items = albums, key = { it.id }) { album ->
        SwipeableListItem(
          album = album,
          onItemSwiped = { dismissValue ->
            val index = albums.indexOf(album)
            if (index >= 0) {
              albums.removeAt(index)
              val action =
                if (dismissValue == SwipeToDismissBoxValue.StartToEnd) "archived" else "deleted"
              scope.launch {
                val result =
                  snackbarHostState.showSnackbar(
                    message = "${album.song} $action",
                    actionLabel = "Undo",
                    duration = SnackbarDuration.Short,
                  )
                if (result == SnackbarResult.ActionPerformed) {
                  albums.add(index.coerceAtMost(albums.size), album)
                }
              }
            }
          },
        )
      }
    }
    SnackbarHost(hostState = snackbarHostState, modifier = Modifier.align(Alignment.BottomCenter))
  }
}

@Composable
fun SwipeableListItem(album: Album, onItemSwiped: (SwipeToDismissBoxValue) -> Unit) {
  val dismissState =
    rememberSwipeToDismissBoxState(
      confirmValueChange = { dismissValue ->
        if (dismissValue != SwipeToDismissBoxValue.Settled) {
          onItemSwiped(dismissValue)
          true
        } else {
          false
        }
      },
      // require half of the item width to be dragged before dismissing
      positionalThreshold = { totalDistance -> totalDistance * 0.5f },
    )

  SwipeToDismissBox(
    state = dismissState,
    backgroundContent = { SwipeActionBackground(dismissState.dismissDirection) },
    content = { ListItemContent(album) },
    enableDismissFromStartToEnd = true,
    enableDismissFromEndToStart = true,
  )
}

@Composable
private fun SwipeActionBackground(direction: SwipeToDismissBoxValue) {
  val isArchive = direction == SwipeToDismissBoxValue.StartToEnd
  val background =
    if (isArchive) MaterialTheme.colorScheme.secondaryContainer
    else MaterialTheme.colorScheme.errorContainer
  Box(
    modifier =
      Modifier.fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .clip(MaterialTheme.shapes.medium)
        .background(background)
        .padding(horizontal = 16.dp),
    contentAlignment = if (isArchive) Alignment.CenterStart else Alignment.CenterEnd,
  ) {
    if (isArchive) {
      Icon(
        imageVector = Icons.Default.Archive,
        contentDescription = "Archive",
        tint = MaterialTheme.colorScheme.onSecondaryContainer,
      )
    } else {
      Icon(
        imageVector = Icons.Default.Delete,
        contentDescription = "Delete",
        tint = MaterialTheme.colorScheme.onErrorContainer,
      )
    }
  }
}

@Composable
private fun ListItemContent(album: Album) {
  Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
    Row(
      modifier = Modifier.fillMaxWidth().padding(16.dp),
      horizontalArrangement = Arrangement.spacedBy(16.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = album.song,
          style = MaterialTheme.typography.titleMedium,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
        )
        Text(
          text = album.artist,
          style = MaterialTheme.typography.bodyMedium,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
        )
      }
      IconButton(onClick = {}) {
        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More options")
      }
    }
  }
}
