package com.guru.composecookbook.ui.demoapps.moviesappmvi.ui.home

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import com.guru.composecookbook.ui.demoapps.moviesappmvi.ui.MoviesLaneItem
import com.guru.composecookbook.ui.demoapps.spotify.data.SpotifyDataProvider
import com.guru.composecookbook.ui.utils.horizontalGradientBackground

@Composable
fun MovieTrendingScreen(moviesHomeInteractionEvents: (MoviesHomeInteractionEvents) -> Unit) {
    val surfaceGradient = SpotifyDataProvider.spotifySurfaceGradient(isSystemInDarkTheme())
    val statusBarHeight = 32.dp
    val viewModel: TrendingViewModel = viewModel()
    val showLoading = remember { mutableStateOf(true) }
    val listOfSections = listOf(
        "Trending this week",
        "Popular this week",
        "Top rated movies",
        "Trending TV shows",
        "Top rated TV shows",
    )
    ScrollableColumn(
        modifier = Modifier.fillMaxSize().horizontalGradientBackground(surfaceGradient)
    ) {
        Spacer(modifier = Modifier.height(statusBarHeight))

        if (showLoading.value) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        listOfSections.forEach {
            DynamicSection(it, viewModel, showLoading, moviesHomeInteractionEvents)
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun DynamicSection(
    type: String,
    viewModel: TrendingViewModel,
    showLoading: MutableState<Boolean>,
    moviesHomeInteractionEvents: (MoviesHomeInteractionEvents) -> Unit
) {
    val movies by when (type) {
        "Trending this week" -> viewModel.trendingMoviesLiveData.observeAsState(emptyList())
        "Popular this week" -> viewModel.popularMoviesLiveData.observeAsState(emptyList())
        "Trending TV shows" -> viewModel.trendingTVShowsLiveData.observeAsState(emptyList())
        "Top rated movies" -> viewModel.topRatedMovies.observeAsState(emptyList())
        "Top rated TV shows" -> viewModel.topRatedTVShows.observeAsState(emptyList())
        else -> viewModel.trendingMoviesLiveData.observeAsState(emptyList())
    }
    if (movies.isNotEmpty()) {
        showLoading.value = false
        MoviesLaneItem(movies = movies, title = type) { movie ->
            moviesHomeInteractionEvents(
                MoviesHomeInteractionEvents.OpenMovieDetail(movie = movie)
            )
        }
    }
}