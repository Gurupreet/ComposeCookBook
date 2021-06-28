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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.R
import com.guru.composecookbook.spotify.ui.home.SpotifyNavType
import com.guru.composecookbook.theme.twitterColor
import com.guru.composecookbook.theme.typography
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
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
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
                Icon(
                    painter = painterResource(id = R.drawable.ic_instagram),
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(painter = painterResource(id = R.drawable.ic_send), contentDescription = null)
            }
        }
    )

    Spacer(modifier = Modifier.height(8.dp))

    TopAppBar(
        title = {
            Icon(
                painter = painterResource(id = R.drawable.ic_twitter),
                contentDescription = null,
                tint = twitterColor,
                modifier = Modifier.fillMaxWidth()
            )
        },
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
        elevation = 8.dp,
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.p6),
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .size(32.dp)
                    .clip(CircleShape)
            )
        },
        actions = {
            Icon(
                imageVector = Icons.Default.StarBorder,
                contentDescription = null,
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
            Icon(imageVector = Icons.Default.MoreHoriz, contentDescription = null)
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
            icon = { Icon(imageVector = Icons.Outlined.Home, contentDescription = null) },
            selected = spotifyNavItemState.value == SpotifyNavType.HOME,
            onClick = { spotifyNavItemState.value = SpotifyNavType.HOME },
            label = { Text(text = stringResource(id = R.string.spotify_nav_home)) },
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = null) },
            selected = spotifyNavItemState.value == SpotifyNavType.SEARCH,
            onClick = { spotifyNavItemState.value = SpotifyNavType.SEARCH },
            label = { Text(text = stringResource(id = R.string.spotify_nav_search)) }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.LibraryMusic, contentDescription = null) },
            selected = spotifyNavItemState.value == SpotifyNavType.LIBRARY,
            onClick = { spotifyNavItemState.value = SpotifyNavType.LIBRARY },
            label = { Text(text = stringResource(id = R.string.spotify_nav_library)) }
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.ReadMore, contentDescription = null) },
            selected = spotifyNavItemState.value == SpotifyNavType.HOME,
            onClick = { spotifyNavItemState.value = SpotifyNavType.HOME },
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = null) },
            selected = spotifyNavItemState.value == SpotifyNavType.SEARCH,
            onClick = { spotifyNavItemState.value = SpotifyNavType.SEARCH },
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.CleanHands, contentDescription = null) },
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
