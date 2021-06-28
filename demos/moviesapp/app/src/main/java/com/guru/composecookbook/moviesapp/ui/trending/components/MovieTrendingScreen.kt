package com.guru.composecookbook.moviesapp.ui.trending.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guru.composecookbook.moviesapp.ui.home.MoviesHomeInteractionEvents
import com.guru.composecookbook.moviesapp.ui.internal.theme.Colors
import com.guru.composecookbook.moviesapp.ui.trending.TrendingViewModel
import com.guru.composecookbook.moviesapp.ui.trending.TrendingViewModelFactory
import com.guru.composecookbook.theme.modifiers.horizontalGradientBackground

@Composable
fun MovieTrendingScreen(moviesHomeInteractionEvents: (MoviesHomeInteractionEvents) -> Unit) {
    val surfaceGradient = Colors.moviesSurfaceGradient(isSystemInDarkTheme())
    val viewModel: TrendingViewModel = viewModel(
        factory = TrendingViewModelFactory(LocalContext.current)
    )
    val showLoading = remember { mutableStateOf(true) }
    val listOfSections = listOf(
        "Trending this week",
        "Popular this week",
        "Top rated movies",
        "Trending TV shows",
        "Top rated TV shows",
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .horizontalGradientBackground(surfaceGradient)
            .verticalScroll(rememberScrollState())
    ) {
        if (showLoading.value) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        listOfSections.forEach {
            DynamicSection(it, viewModel, showLoading, moviesHomeInteractionEvents)
        }
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