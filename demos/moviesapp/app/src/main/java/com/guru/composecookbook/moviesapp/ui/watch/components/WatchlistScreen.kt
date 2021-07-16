package com.guru.composecookbook.moviesapp.ui.watch.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guru.composecookbook.moviesapp.data.db.models.Movie
import com.guru.composecookbook.moviesapp.ui.home.MoviesHomeInteractionEvents
import com.guru.composecookbook.moviesapp.ui.home.MoviesHomeViewModel
import com.guru.composecookbook.moviesapp.ui.home.MoviesHomeViewModelFactory
import com.guru.composecookbook.moviesapp.ui.internal.theme.Colors
import com.guru.composecookbook.theme.modifiers.horizontalGradientBackground


@Composable
fun WatchlistScreen(moviesHomeInteractionEvents: (MoviesHomeInteractionEvents) -> Unit) {
    val surfaceGradient = Colors.moviesSurfaceGradient(isSystemInDarkTheme())
    val viewModel: MoviesHomeViewModel = viewModel(
        factory = MoviesHomeViewModelFactory(LocalContext.current)
    )
    val myWatchlist by viewModel.myWatchlist.observeAsState(emptyList())
    if (myWatchlist.isEmpty()) {
        EmptyWatchlistSection()

        return
    }

    Surface(modifier = Modifier.horizontalGradientBackground(surfaceGradient)) {
        LazyColumn {
            itemsIndexed(
                items = myWatchlist,
                itemContent = { _: Int, movie: Movie ->
                    MovieWatchlistItem(
                        movie,
                        {
                            moviesHomeInteractionEvents(
                                MoviesHomeInteractionEvents.OpenMovieDetail(movie)
                            )
                        },
                        {
                            moviesHomeInteractionEvents(
                                MoviesHomeInteractionEvents.RemoveFromMyWatchlist(movie)
                            )
                        }
                    )
                }
            )
        }
    }
}



