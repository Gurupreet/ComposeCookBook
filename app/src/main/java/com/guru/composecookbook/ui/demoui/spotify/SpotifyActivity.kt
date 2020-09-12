package com.guru.composecookbook.ui.demoui.spotify

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.R
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.theme.graySurface
import com.guru.composecookbook.ui.dynamic.DynamicUIActivity

class SpotifyActivity : AppCompatActivity() {

    private val isDarkTheme: Boolean by lazy {
        intent?.getBooleanExtra(DynamicUIActivity.DARK_THEME, false) ?: false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
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
    val spotifyNavItemState = savedInstanceState { SpotifyNavType.HOME }
    Scaffold(
        bottomBar = { SpotifyBottomNavigation(spotifyNavItemState) },
        bodyContent = { SpotifyBodyContent(spotifyNavItemState.value) }
    )
}

@Composable
fun SpotifyBottomNavigation(spotifyNavItemState: MutableState<SpotifyNavType>) {
    val bottomNavBackground =
        if (isSystemInDarkTheme()) graySurface else MaterialTheme.colors.background
    BottomNavigation(backgroundColor = bottomNavBackground) {
        BottomNavigationItem(
            icon = { Icon(asset = Icons.Outlined.Home) },
            selected = spotifyNavItemState.value == SpotifyNavType.HOME,
            onSelect = { spotifyNavItemState.value = SpotifyNavType.HOME },
            label = { Text(text = stringResource(id = R.string.spotify_nav_home)) },
        )
        BottomNavigationItem(
            icon = { Icon(asset = Icons.Outlined.Search) },
            selected = spotifyNavItemState.value == SpotifyNavType.SEARCH,
            onSelect = { spotifyNavItemState.value = SpotifyNavType.SEARCH },
            label = { Text(text = stringResource(id = R.string.spotify_nav_search)) }
        )
        BottomNavigationItem(
            icon = { Icon(asset = Icons.Outlined.LibraryMusic) },
            selected = spotifyNavItemState.value == SpotifyNavType.LIBRARY,
            onSelect = { spotifyNavItemState.value = SpotifyNavType.LIBRARY },
            label = { Text(text = stringResource(id = R.string.spotify_nav_library)) }
        )
    }
}

@Composable
fun SpotifyBodyContent(spotifyNavType: SpotifyNavType) {
    when(spotifyNavType) {
        SpotifyNavType.HOME -> SpotifyHome()
        SpotifyNavType.SEARCH -> SpotifySearchScreen()
        SpotifyNavType.LIBRARY -> Text(text = "lib")
    }
}
    

@Preview    
@Composable
fun DefaultPreview() {
    ComposeCookBookTheme {
       SpotifyAppContent()
    }
}