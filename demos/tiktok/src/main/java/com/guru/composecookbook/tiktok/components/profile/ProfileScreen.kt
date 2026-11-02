package com.guru.composecookbook.tiktok.components.profile

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.data.model.Album
import com.guru.composecookbook.theme.ComposeCookBookTheme

@Composable
fun ProfileScreen(userId: String = "10", navHostController: NavHostController) {
  val album: Album = AlbumsDataProvider.albums.first { it.id.toString() == userId }
  ComposeCookBookTheme(darkTheme = false) {
    // This Scaffold is nested inside the outer TikTok Scaffold (TiktokAppContent), which already
    // applies the status-bar / system-bar insets via contentWindowInsets. M3 Scaffold defaults to
    // contentWindowInsets = WindowInsets.systemBars(), so pass 0 to avoid double top padding. The
    // M3 TopAppBar windowInsets are likewise zeroed in ProfileAppBar.
    Scaffold(
      topBar = { ProfileAppBar(album, navHostController) },
      contentWindowInsets = WindowInsets(0),
    ) { paddingValues ->
      LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(paddingValues),
      ) {
        item { ProfileTopSection(album) }
      }
    }
  }
}
