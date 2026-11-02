package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.outlined.CleanHands
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.ReadMore
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.R
import com.guru.composecookbook.spotify.ui.home.SpotifyNavType
import com.guru.composecookbook.theme.twitterColor
import com.guru.composecookbook.ui.utils.SubtitleText
import com.guru.composecookbook.ui.utils.TitleText

@Composable
fun AppBars() {
  Text(
    text = "App Bars",
    style = MaterialTheme.typography.headlineSmall,
    modifier = Modifier.padding(8.dp),
  )

  TopAppBarsDemo()
  BottomAppBarDemo()
  NavigationBarDemo()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarsDemo() {
  SubtitleText(subtitle = "Top App bar")

  TopAppBar(
    title = { Text(text = "Home") },
    navigationIcon = {
      IconButton(onClick = {}) {
        Icon(
          imageVector = Icons.Default.ArrowBack,
          contentDescription = stringResource(id = R.string.cd_back),
        )
      }
    },
  )

  Spacer(modifier = Modifier.height(8.dp))

  // The old M2 TopAppBar used surface/onSurface with an 8.dp elevation. The M3 bar has no static
  // elevation, so it renders flat (accepted standard-M3 delta); surface/onSurface match the M3
  // defaults, so no explicit colors are needed.
  TopAppBar(
    title = { Text(text = "Instagram") },
    navigationIcon = {
      IconButton(onClick = {}) {
        Icon(
          painter = painterResource(id = com.guru.composecookbook.data.R.drawable.ic_instagram),
          contentDescription = null,
        )
      }
    },
    actions = {
      IconButton(onClick = {}) {
        Icon(
          painter = painterResource(id = com.guru.composecookbook.data.R.drawable.ic_send),
          contentDescription = null,
        )
      }
    },
  )

  Spacer(modifier = Modifier.height(8.dp))

  TopAppBar(
    title = {
      Icon(
        painter = painterResource(id = com.guru.composecookbook.theme.R.drawable.ic_twitter),
        contentDescription = null,
        tint = twitterColor,
        modifier = Modifier.fillMaxWidth(),
      )
    },
    navigationIcon = {
      Image(
        painter = painterResource(id = com.guru.composecookbook.data.R.drawable.p6),
        contentDescription = null,
        modifier =
          Modifier.padding(vertical = 4.dp, horizontal = 8.dp).size(32.dp).clip(CircleShape),
      )
    },
    actions = {
      Icon(
        imageVector = Icons.Default.StarBorder,
        contentDescription = null,
        modifier = Modifier.padding(horizontal = 8.dp),
      )
    },
  )
  Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun BottomAppBarDemo() {
  Spacer(modifier = Modifier.height(16.dp))
  SubtitleText(
    "Bottom app bar: the M3 bar hosts a floating action button; there is no M2-style FAB cutout " +
      "(see the crypto app demo)"
  )

  // M2 BottomAppBar had a cutoutShape that cut a hole for the FAB. The M3 bar has no cutout; the
  // FAB sits on top of the bar instead (see the crypto app demo). The old M2 default background
  // was primarySurface: primary in light, elevated surface in dark; the dark bar uses
  // surfaceContainer so it does not blend into the dark background.
  val isDark = MaterialTheme.colorScheme.surface.luminance() < 0.5f
  BottomAppBar(
    actions = { TitleText(title = "Bottom App Bar") },
    floatingActionButton = {
      FloatingActionButton(onClick = {}) {
        Icon(imageVector = Icons.Default.MoreHoriz, contentDescription = null)
      }
    },
    containerColor =
      if (isDark) MaterialTheme.colorScheme.surfaceContainer else MaterialTheme.colorScheme.primary,
    contentColor =
      if (isDark) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onPrimary,
  )
}

@Composable
fun NavigationBarDemo() {
  Spacer(modifier = Modifier.height(16.dp))
  SubtitleText(subtitle = "Bottom Navigation Bars")
  val spotifyNavItemState = remember { mutableStateOf(SpotifyNavType.HOME) }
  val isDark = MaterialTheme.colorScheme.surface.luminance() < 0.5f
  // The M2 BottomNavigation default background was primarySurface; this demo set surface. The M3
  // NavigationBar is flat (lost the M2 8.dp shadow), which is an accepted standard-M3 delta. The
  // M2 item colors were onSurface (selected) and onSurface at 0.6 (unselected) with no indicator
  // pill; M3 adds a secondaryContainer pill, which would show the palette color over this surface
  // bar, so it is replaced with the M2-style 0.12 onSurface tint.
  val labeledItemColors =
    NavigationBarItemDefaults.colors(
      selectedIconColor = MaterialTheme.colorScheme.onSurface,
      selectedTextColor = MaterialTheme.colorScheme.onSurface,
      unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
      unselectedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
      indicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
    )
  NavigationBar(
    containerColor = MaterialTheme.colorScheme.surface,
    contentColor = MaterialTheme.colorScheme.onSurface,
    tonalElevation = 0.dp,
  ) {
    NavigationBarItem(
      icon = { Icon(imageVector = Icons.Outlined.Home, contentDescription = null) },
      selected = spotifyNavItemState.value == SpotifyNavType.HOME,
      onClick = { spotifyNavItemState.value = SpotifyNavType.HOME },
      label = {
        Text(text = stringResource(id = com.guru.composecookbook.spotify.R.string.spotify_nav_home))
      },
      colors = labeledItemColors,
    )
    NavigationBarItem(
      icon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = null) },
      selected = spotifyNavItemState.value == SpotifyNavType.SEARCH,
      onClick = { spotifyNavItemState.value = SpotifyNavType.SEARCH },
      label = {
        Text(
          text = stringResource(id = com.guru.composecookbook.spotify.R.string.spotify_nav_search)
        )
      },
      colors = labeledItemColors,
    )
    NavigationBarItem(
      icon = { Icon(imageVector = Icons.Outlined.LibraryMusic, contentDescription = null) },
      selected = spotifyNavItemState.value == SpotifyNavType.LIBRARY,
      onClick = { spotifyNavItemState.value = SpotifyNavType.LIBRARY },
      label = {
        Text(
          text = stringResource(id = com.guru.composecookbook.spotify.R.string.spotify_nav_library)
        )
      },
      colors = labeledItemColors,
    )
  }

  Spacer(modifier = Modifier.height(16.dp))

  // The second bar relied on the M2 default background (primarySurface). Kept via the
  // colorScheme: primary in light, surfaceContainer in dark (never isSystemInDarkTheme). The M2
  // icons were the bar's content color, unselected at 0.6, with no indicator pill; M3 items use
  // palette roles by default, so the content colors and a transparent indicator are set
  // explicitly.
  val contentColor =
    if (isDark) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onPrimary
  val iconOnlyItemColors =
    NavigationBarItemDefaults.colors(
      selectedIconColor = contentColor,
      unselectedIconColor = contentColor.copy(alpha = 0.6f),
      indicatorColor = Color.Transparent,
    )
  NavigationBar(
    containerColor =
      if (isDark) MaterialTheme.colorScheme.surfaceContainer else MaterialTheme.colorScheme.primary,
    contentColor = contentColor,
    tonalElevation = 0.dp,
  ) {
    NavigationBarItem(
      icon = { Icon(imageVector = Icons.Outlined.ReadMore, contentDescription = null) },
      selected = spotifyNavItemState.value == SpotifyNavType.HOME,
      onClick = { spotifyNavItemState.value = SpotifyNavType.HOME },
      colors = iconOnlyItemColors,
    )
    NavigationBarItem(
      icon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = null) },
      selected = spotifyNavItemState.value == SpotifyNavType.SEARCH,
      onClick = { spotifyNavItemState.value = SpotifyNavType.SEARCH },
      colors = iconOnlyItemColors,
    )
    NavigationBarItem(
      icon = { Icon(imageVector = Icons.Outlined.CleanHands, contentDescription = null) },
      selected = spotifyNavItemState.value == SpotifyNavType.LIBRARY,
      onClick = { spotifyNavItemState.value = SpotifyNavType.LIBRARY },
      colors = iconOnlyItemColors,
    )
  }
}

@Preview
@Composable
fun PreviewAppBars() {
  Column { AppBars() }
}
