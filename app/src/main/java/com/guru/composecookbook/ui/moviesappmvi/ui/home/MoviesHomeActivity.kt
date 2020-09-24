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
import androidx.compose.material.icons.outlined.Subscriptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.theme.graySurface
import com.guru.composecookbook.ui.moviesappmvi.data.models.Movie
import com.guru.composecookbook.ui.moviesappmvi.ui.details.MovieDetailActivity

sealed class MoviesHomeInteractionEvents {
    data class OpenMovieDetail(val movie: Movie, val imageId: Int = 0) : MoviesHomeInteractionEvents()
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
                val viewModel: MoviesHomeViewModel = viewModel()
                Scaffold(
                    bottomBar = { MoviesBottomBar(navType) }
                ) {
                    Crossfade(current = navType) {
                        when (navType.value) {
                            MovieNavType.SHOWING -> MovieHomeScreen(
                                moviesHomeInteractionEvents = {
                                    handleInteractionEvents(it, viewModel)
                                }
                            )
                            MovieNavType.TRENDING -> MovieTrendingScreen(
                                moviesHomeInteractionEvents = {
                                    handleInteractionEvents(it, viewModel)
                                }
                            )
                            MovieNavType.WATCHLIST -> WatchlistScreen(
                                moviesHomeInteractionEvents =  {
                                    handleInteractionEvents(it, viewModel)
                                }
                            )
                        }
                    }
                }


            }
        }
    }

    fun handleInteractionEvents(
        interactionEvents: MoviesHomeInteractionEvents,
        viewModel: MoviesHomeViewModel
    ) {
        when (interactionEvents) {
            is MoviesHomeInteractionEvents.OpenMovieDetail -> {
                startActivity(MovieDetailActivity.newIntent(
                    this, interactionEvents.movie, interactionEvents.imageId))
                overridePendingTransition(0, 0)
            }
            is MoviesHomeInteractionEvents.AddToMyWatchlist -> {
                viewModel.addToMyWatchlist(interactionEvents.movie)
            }
            is MoviesHomeInteractionEvents.RemoveFromMyWatchlist -> {
                viewModel.removeFromMyWatchlist(interactionEvents.movie)
            }
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
            icon = { Icon(asset = Icons.Outlined.Subscriptions) },
            selected = navType.value == MovieNavType.TRENDING,
            onClick = { navType.value = MovieNavType.TRENDING },
            label = { Text(text = "Trending") }
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