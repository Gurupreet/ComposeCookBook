package com.guru.composecookbook.spotify.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.guru.composecookbook.spotify.R
import com.guru.composecookbook.spotify.ui.home.components.SpotifyHome
import com.guru.composecookbook.spotify.ui.playlist.components.SpotifyPlayList
import com.guru.composecookbook.spotify.ui.search.components.SpotifySearchScreen
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.theme.graySurface

enum class SpotifyNavType {
    HOME, SEARCH, LIBRARY
}

class SpotifyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        setContent {
            ComposeCookBookTheme {
                SpotifyAppContent()
            }
        }
    }

    companion object {
        const val DARK_THEME = "darkTheme"
        fun newIntent(context: Context, isDarkTheme: Boolean) =
            Intent(context, SpotifyActivity::class.java).apply {
                putExtra(DARK_THEME, isDarkTheme)
            }
    }
}

@Composable
fun SpotifyAppContent() {
    val spotifyNavItemState = rememberSaveable { mutableStateOf(SpotifyNavType.HOME) }
    Scaffold(
        bottomBar = { SpotifyBottomNavigation(spotifyNavItemState) },
        content = { SpotifyBodyContent(spotifyNavItemState.value) }
    )
}

@Composable
fun SpotifyBottomNavigation(spotifyNavItemState: MutableState<SpotifyNavType>) {
    val bottomNavBackground =
        if (isSystemInDarkTheme()) graySurface else MaterialTheme.colors.background
    BottomNavigation(backgroundColor = bottomNavBackground) {
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
}

@Composable
fun SpotifyBodyContent(spotifyNavType: SpotifyNavType) {
    Crossfade(targetState = spotifyNavType) { navType ->
        when (navType) {
            SpotifyNavType.HOME -> SpotifyHome()
            SpotifyNavType.SEARCH -> SpotifySearchScreen()
            SpotifyNavType.LIBRARY -> SpotifyPlayList()
        }
    }
}


@Preview
@Composable
fun DefaultPreview() {
    ComposeCookBookTheme {
        SpotifyAppContent()
    }
}