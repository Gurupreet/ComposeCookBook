package com.guru.composecookbook.spotify.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.spotify.R
import com.guru.composecookbook.spotify.ui.home.components.SpotifyHome
import com.guru.composecookbook.spotify.ui.playlist.components.SpotifyPlayList
import com.guru.composecookbook.spotify.ui.search.components.SpotifySearchScreen
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.theme.graySurface

enum class SpotifyNavType {
  HOME,
  SEARCH,
  LIBRARY,
}

class SpotifyActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    setContent { ComposeCookBookTheme { SpotifyAppContent() } }
  }

  companion object {
    const val DARK_THEME = "darkTheme"

    fun newIntent(context: Context, isDarkTheme: Boolean) =
      Intent(context, SpotifyActivity::class.java).apply { putExtra(DARK_THEME, isDarkTheme) }
  }
}

@Composable
fun SpotifyAppContent() {
  val spotifyNavItemState = rememberSaveable { mutableStateOf(SpotifyNavType.HOME) }
  Scaffold(
    bottomBar = { SpotifyBottomNavigation(spotifyNavItemState) },
    content = { paddingValues ->
      SpotifyBodyContent(spotifyNavItemState.value, modifier = Modifier.padding(paddingValues))
    },
  )
}

@Composable
fun SpotifyBottomNavigation(spotifyNavItemState: MutableState<SpotifyNavType>) {
  val bottomNavBackground =
    if (isSystemInDarkTheme()) graySurface else MaterialTheme.colorScheme.background
  // M2 BottomNavigation's onBackground content color maps to the M3 onSurface role; the M2
  // unselected icon/text alpha of 0.6 is kept (the M3 default is onSurfaceVariant, which
  // is full contrast in this theme).
  val navigationBarItemColors =
    NavigationBarItemDefaults.colors(
      selectedIconColor = MaterialTheme.colorScheme.onSurface,
      selectedTextColor = MaterialTheme.colorScheme.onSurface,
      unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
      unselectedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
    )
  NavigationBar(
    containerColor = bottomNavBackground,
    contentColor = MaterialTheme.colorScheme.onSurface,
    // tonalElevation 0 keeps the bar's exact background (background == surface would
    // otherwise pick up a surfaceTint wash).
    tonalElevation = 0.dp,
  ) {
    NavigationBarItem(
      icon = { Icon(imageVector = Icons.Outlined.Home, contentDescription = null) },
      selected = spotifyNavItemState.value == SpotifyNavType.HOME,
      colors = navigationBarItemColors,
      modifier = Modifier.testTag("spotify_nav_home"),
      onClick = { spotifyNavItemState.value = SpotifyNavType.HOME },
      label = { Text(text = stringResource(id = R.string.spotify_nav_home)) },
    )
    NavigationBarItem(
      icon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = null) },
      selected = spotifyNavItemState.value == SpotifyNavType.SEARCH,
      colors = navigationBarItemColors,
      modifier = Modifier.testTag("spotify_nav_search"),
      onClick = { spotifyNavItemState.value = SpotifyNavType.SEARCH },
      label = { Text(text = stringResource(id = R.string.spotify_nav_search)) },
    )
    NavigationBarItem(
      icon = { Icon(imageVector = Icons.Outlined.LibraryMusic, contentDescription = null) },
      selected = spotifyNavItemState.value == SpotifyNavType.LIBRARY,
      colors = navigationBarItemColors,
      modifier = Modifier.testTag("spotify_nav_library"),
      onClick = { spotifyNavItemState.value = SpotifyNavType.LIBRARY },
      label = { Text(text = stringResource(id = R.string.spotify_nav_library)) },
    )
  }
}

@Composable
fun SpotifyBodyContent(spotifyNavType: SpotifyNavType, modifier: Modifier = Modifier) {
  Crossfade(targetState = spotifyNavType, modifier = modifier) { navType ->
    when (navType) {
      SpotifyNavType.HOME -> SpotifyHome()
      SpotifyNavType.SEARCH -> SpotifySearchScreen()
      SpotifyNavType.LIBRARY -> SpotifyPlayList()
    }
  }
}

@PreviewLightDark
@Composable
fun DefaultPreview() {
  ComposeCookBookTheme { SpotifyAppContent() }
}
