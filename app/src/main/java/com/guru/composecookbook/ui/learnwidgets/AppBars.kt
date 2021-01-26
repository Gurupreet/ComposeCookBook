package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.R
import com.guru.composecookbook.theme.twitterColor
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.demoui.spotify.SpotifyNavType
import com.guru.composecookbook.ui.utils.SubtitleText
import com.guru.composecookbook.ui.utils.TitleText

@Composable
fun AppBars() {
    Text(text = "App Bars", style = typography.h5, modifier = Modifier.padding(8.dp))

    TopAppBarsDemo()
    BottomAppBarDemo()
    NavigationBarDemo()
}

@Composable
fun TopAppBarsDemo() {
    SubtitleText(subtitle = "Top App bar")

    TopAppBar(
        title = { Text(text = "Home") },
        elevation = 8.dp,
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.ArrowBack)
            }
        }
    )

    Spacer(modifier = Modifier.height(8.dp))

    TopAppBar(
        title = { Text(text = "Instagram") },
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
        elevation = 8.dp,
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(vectorResource(id = R.drawable.ic_instagram))
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(vectorResource(id = R.drawable.ic_send))
            }
        }
    )

    Spacer(modifier = Modifier.height(8.dp))

    TopAppBar(
        title = {
            Icon(
                vectorResource(id = R.drawable.ic_twitter),
                tint = twitterColor,
                modifier = Modifier.fillMaxWidth()
            )
        },
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
        elevation = 8.dp,
        navigationIcon = {
            Image(
                imageResource(id = R.drawable.p6),
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                    .preferredSize(32.dp).clip(CircleShape)
            )
        },
        actions = {
            Icon(
                Icons.Default.StarBorder,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    )
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun BottomAppBarDemo() {
    Spacer(modifier = Modifier.height(16.dp))
    SubtitleText("Bottom app bars: Note bottom app bar support FAB cutouts when used with scafolds see demoUI crypto app")

    BottomAppBar(
        cutoutShape = CircleShape
    ) {
        IconButton(onClick = {}) {
            Icon(Icons.Default.MoreHoriz)
        }
        TitleText(title = "Bottom App Bar")
    }
}

@Composable
fun NavigationBarDemo() {
    Spacer(modifier = Modifier.height(16.dp))
    SubtitleText(subtitle = "Bottom Navigation Bars")
    val spotifyNavItemState = remember { mutableStateOf(SpotifyNavType.HOME) }
    BottomNavigation(backgroundColor = MaterialTheme.colors.surface) {
        BottomNavigationItem(
            icon = { Icon(Icons.Outlined.Home) },
            selected = spotifyNavItemState.value == SpotifyNavType.HOME,
            onClick = { spotifyNavItemState.value = SpotifyNavType.HOME },
            label = { Text(text = stringResource(id = R.string.spotify_nav_home)) },
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Outlined.Search) },
            selected = spotifyNavItemState.value == SpotifyNavType.SEARCH,
            onClick = { spotifyNavItemState.value = SpotifyNavType.SEARCH },
            label = { Text(text = stringResource(id = R.string.spotify_nav_search)) }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Outlined.LibraryMusic) },
            selected = spotifyNavItemState.value == SpotifyNavType.LIBRARY,
            onClick = { spotifyNavItemState.value = SpotifyNavType.LIBRARY },
            label = { Text(text = stringResource(id = R.string.spotify_nav_library)) }
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(Icons.Outlined.ReadMore) },
            selected = spotifyNavItemState.value == SpotifyNavType.HOME,
            onClick = { spotifyNavItemState.value = SpotifyNavType.HOME },
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Outlined.Search) },
            selected = spotifyNavItemState.value == SpotifyNavType.SEARCH,
            onClick = { spotifyNavItemState.value = SpotifyNavType.SEARCH },
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Outlined.CleanHands) },
            selected = spotifyNavItemState.value == SpotifyNavType.LIBRARY,
            onClick = { spotifyNavItemState.value = SpotifyNavType.LIBRARY },
        )
    }
}


@Preview
@Composable
fun PreviewAppBars() {
    Column {
        AppBars()
    }
}
