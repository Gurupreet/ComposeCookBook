package com.guru.composecookbook.ui.moviesappmvi.ui

import android.graphics.Bitmap
import com.guru.composecookbook.R
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AnimationClockAmbient
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.graySurface
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.carousel.Pager
import com.guru.composecookbook.ui.carousel.PagerState
import com.guru.composecookbook.ui.moviesappmvi.data.DemoMovieDataProvider
import com.guru.composecookbook.ui.moviesappmvi.data.models.Movie
import com.guru.composecookbook.ui.utils.horizontalGradientBackground
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.res.imageResource
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun MovieHomeScreen(moviesHomeInteractionEvents: (MoviesHomeInteractionEvents) -> Unit) {
    Scaffold(

    ) {
        MovieHomeScreenContent(DemoMovieDataProvider.movies)
    }
}

@Composable
fun MovieHomeScreenContent(movies: List<Movie>) {
    val backgroundGradient by remember { mutableStateOf(listOf(graySurface, graySurface.copy(alpha = 0.8f))) }
    val defaultBitmap = imageResource(id = R.drawable.adele21).asAndroidBitmap()
    val currentBitmap = remember { mutableStateOf(defaultBitmap)  }
    ScrollableColumn(modifier = Modifier.fillMaxSize().horizontalGradientBackground(backgroundGradient)) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Now Showing", style = typography.h6, modifier = Modifier.padding(16.dp))
        MoviesPager(movies = movies)
    }
}

@Composable
fun MoviesPager(movies: List<Movie>) {
    val movies = remember { DemoMovieDataProvider.movies }
    val pagerState: PagerState = run {
        val clock = AnimationClockAmbient.current
        remember(clock) { PagerState(clock, 2, 0, movies.size - 1) }
    }
    Pager(state = pagerState, modifier = Modifier.preferredHeight(500.dp)) {
        val movie = movies[page]
        val isSelected = pagerState.currentPage == page
        MoviePagerItem(movie, isSelected)
    }
}