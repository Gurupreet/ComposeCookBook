package com.guru.composecookbook.ui.home.pullrefreshdemos

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.spotify.ui.details.components.SpotifySongListItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Demonstrates Material3 [PullToRefreshBox]: pull the list down to trigger a simulated network
 * refresh (1.5s delay) that prepends a "new" album to the list.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullRefreshList(onPullRefresh: () -> Unit) {
  val albums = remember { AlbumsDataProvider.albums.toMutableStateList() }
  var isRefreshing by remember { mutableStateOf(false) }
  val scope = rememberCoroutineScope()
  val lazyListState = rememberLazyListState()

  PullToRefreshBox(
    isRefreshing = isRefreshing,
    onRefresh = {
      onPullRefresh.invoke()
      scope.launch {
        isRefreshing = true
        // Simulate a network reload
        delay(1500)
        albums.add(0, AlbumsDataProvider.album.copy(id = albums.size + 1))
        isRefreshing = false
        lazyListState.scrollToItem(0)
      }
    },
    modifier = Modifier.fillMaxSize(),
  ) {
    LazyColumn(state = lazyListState, modifier = Modifier.fillMaxSize()) {
      items(albums) { album -> SpotifySongListItem(album = album) }
    }
  }
}
