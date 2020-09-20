package com.guru.composecookbook.ui.moviesappmvi.ui

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.align
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.AnimationClockAmbient
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import com.guru.composecookbook.R
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.carousel.Pager
import com.guru.composecookbook.ui.carousel.PagerState
import com.guru.composecookbook.ui.demoui.spotify.generateDominantColorState
import com.guru.composecookbook.ui.utils.verticalGradientBackground

@Composable
fun MovieHomeScreen(moviesHomeInteractionEvents: (MoviesHomeInteractionEvents) -> Unit) {
    Scaffold {
        MovieHomeScreenContent()
    }
}

@Composable
fun MovieHomeScreenContent() {
    //TODO dynamic gradient from poster
    val defaultBitmap = imageResource(id = R.drawable.imagindragon).asAndroidBitmap()
    var currentBitmap = remember { mutableStateOf(defaultBitmap) }
    val swatch = remember(currentBitmap) { generateDominantColorState(currentBitmap.value) }
    val dominantColors = listOf(
        Color(swatch.rgb),
        Color(swatch.rgb).copy(alpha = 0.6f),
        MaterialTheme.colors.surface
    )

    ScrollableColumn(modifier = Modifier.fillMaxSize().verticalGradientBackground(dominantColors)) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Now Showing",
            style = typography.h5.copy(fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally)
        )
        MoviesPager(currentBitmap)
    }
}

@Composable
fun MoviesPager(bitmap: MutableState<Bitmap>) {
    val moviesViewModel: MoviesHomeViewModel = viewModel()
    val movies by moviesViewModel.nowShowingLiveData.observeAsState(emptyList())
    val error by moviesViewModel.errorLiveData.observeAsState()
    if (movies.isNotEmpty()) {
        val pagerState: PagerState = run {
            val clock = AnimationClockAmbient.current
            remember(clock) {
                PagerState(clock, 0, 0, movies.size - 1)
            }
        }
        Pager(state = pagerState, modifier = Modifier.preferredHeight(600.dp)) {
            Log.d("pager offset", currentPageOffset.toString())
            val movie = movies[page]
            val isSelected = pagerState.currentPage == page
            MoviePagerItem(movie, isSelected, bitmap)
        }
    } else {
        if (error.isNullOrEmpty()) {
            CircularProgressIndicator(
                modifier = Modifier.padding(24.dp).align(Alignment.CenterHorizontally)
            )
        } else {
            Text(
                text = error ?: "Unknown error",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = MaterialTheme.colors.error
            )
        }
    }
}