package com.guru.composecookbook.ui.moviesappmvi.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LibraryAdd
import androidx.compose.material.icons.outlined.MovieCreation
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.ui.moviesappmvi.data.models.Movie
import com.guru.composecookbook.ui.moviesappmvi.ui.details.MovieDetailActivity
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import com.guru.composecookbook.theme.graySurface

sealed class MoviesHomeInteractionEvents {
    data class OpenMovieDetail(val movie: Movie) : MoviesHomeInteractionEvents()
    data class AddToMyWatchlist(val movie: Movie) : MoviesHomeInteractionEvents()
    data class RemoveFromMyWatchlist(val movie: Movie) : MoviesHomeInteractionEvents()
}

class MoviesHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        setContent {
            ComposeCookBookTheme {
                val navType = savedInstanceState { MovieNavType.SHOWING }
                Scaffold(
                    bottomBar = { MoviesBottomBar(navType) }
                ) {
                    Crossfade(current = navType) {
                        when (navType.value) {
                            MovieNavType.SHOWING ->   MovieHomeScreen(
                                moviesHomeInteractionEvents = {
                                    handleInteractionEvents(it)
                                }
                            )
                            MovieNavType.TRENDING -> MovieTrendingScreen(
                                moviesHomeInteractionEvents = {
                                    handleInteractionEvents(it)
                                }
                            )
                            MovieNavType.WATCHLIST -> Text(text = "Watchlist")
                        }
                    }
                }


            }
        }
    }

    fun handleInteractionEvents(interactionEvents: MoviesHomeInteractionEvents) {
        when (interactionEvents) {
            is MoviesHomeInteractionEvents.OpenMovieDetail -> {
                startActivity(MovieDetailActivity.newIntent(this, interactionEvents.movie))
                overridePendingTransition(0, 0)
            }

            else -> {}
        }
    }

    companion object {
        const val DARK_THEME = "darkTheme"
        fun newIntent(context: Context, isDarkTheme: Boolean) =
            Intent(context, MoviesHomeActivity::class.java).apply {
                putExtra(DARK_THEME, isDarkTheme)
            }
    }
}

@Composable
fun MoviesBottomBar(navType: MutableState<MovieNavType>) {
    val bottomNavBackground =
        if (isSystemInDarkTheme()) graySurface else MaterialTheme.colors.background
    BottomNavigation(backgroundColor = bottomNavBackground) {
        BottomNavigationItem(
            icon = { Icon(asset = Icons.Outlined.MovieCreation) },
            selected = navType.value == MovieNavType.SHOWING,
            onClick = { navType.value = MovieNavType.SHOWING },
            label = { Text(text = "Showing") },
        )
        BottomNavigationItem(
            icon = { Icon(asset = Icons.Outlined.Search) },
            selected = navType.value == MovieNavType.TRENDING,
            onClick = { navType.value = MovieNavType.TRENDING },
            label = { Text(text = "Search") }
        )
        BottomNavigationItem(
            icon = { Icon(asset = Icons.Outlined.LibraryAdd) },
            selected = navType.value == MovieNavType.WATCHLIST,
            onClick = { navType.value = MovieNavType.WATCHLIST },
            label = { Text(text = "Watchlist") }
        )
    }
}

enum class MovieNavType {
    SHOWING, TRENDING, WATCHLIST
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview7() {
    ComposeCookBookTheme {
        Greeting("Android")
    }
}