package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.outlined.CleanHands
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.ReadMore
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SmallTopAppBar
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarsDemo() {
    SubtitleText(subtitle = "Top App bar")

    SmallTopAppBar(
        title = { Text(text = "Home") },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.cd_back)
                )
            }
        }
    )

    Spacer(modifier = Modifier.height(8.dp))

    TopAppBar(
        title = { Text(text = "Instagram") },
        backgroundColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
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
        backgroundColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
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
    BottomNavigation(backgroundColor = MaterialTheme.colorScheme.surface) {
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
