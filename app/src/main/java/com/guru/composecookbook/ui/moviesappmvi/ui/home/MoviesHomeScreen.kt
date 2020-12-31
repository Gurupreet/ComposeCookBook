package com.guru.composecookbook.ui.moviesappmvi.ui.home

import android.util.Log
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.AmbientAnimationClock
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
    MovieHomeScreenContent(moviesHomeInteractionEvents)
}

@Composable
fun MovieHomeScreenContent(moviesHomeInteractionEvents: (MoviesHomeInteractionEvents) -> Unit) {
    //TODO dynamic gradient from poster via coil right now It's just getting from local images
    var imageId = remember { mutableStateOf(R.drawable.camelia) }
    val defaultBitmap = imageResource(id = imageId.value).asAndroidBitmap()
    var currentBitmap = mutableStateOf(defaultBitmap)
    val swatch = generateDominantColorState(currentBitmap.value)
    val dominantColors = listOf(Color(swatch.rgb), Color.Black)

    ScrollableColumn(
        modifier = Modifier.fillMaxSize().verticalGradientBackground(dominantColors)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Now Showing",
            style = typography.h5.copy(fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally)
        )
        MoviesPager(imageId, moviesHomeInteractionEvents)
    }
}

@Composable
fun MoviesPager(
    imageId: MutableState<Int>,
    moviesHomeInteractionEvents: (MoviesHomeInteractionEvents) -> Unit
) {
    val moviesViewModel: MoviesHomeViewModel = viewModel()
    val movies by moviesViewModel.nowShowingLiveData.observeAsState(emptyList())
    val genres by moviesViewModel.genresLiveData.observeAsState(emptyList())
    val error by moviesViewModel.errorLiveData.observeAsState()

    if (movies.isNotEmpty()) {
        val pagerState: PagerState = run {
            val clock = AmbientAnimationClock.current
            remember(clock) {
                PagerState(clock, 0, 0, movies.size - 1)
            }
        }
        Pager(state = pagerState, modifier = Modifier.preferredHeight(645.dp)) {
            Log.d("pager offset", currentPageOffset.toString())
            val movie = movies[page]
            imageId.value = imageIds[pagerState.currentPage]
            val isSelected = pagerState.currentPage == page

            MoviePagerItem(
                movie,
                genres,
                isSelected,
                { moviesHomeInteractionEvents(MoviesHomeInteractionEvents.AddToMyWatchlist(movie)) }
            ) {
                moviesHomeInteractionEvents(
                    MoviesHomeInteractionEvents.OpenMovieDetail(movie, imageId.value)
                )
            }
        }
    } else {
        if (error.isNullOrEmpty()) {
            CircularProgressIndicator(
                modifier = Modifier.padding(24.dp)
            )
        } else {
            Text(
                text = error ?: "Unknown error",
                modifier = Modifier,
                color = MaterialTheme.colors.error
            )
        }
    }
}

val imageIds =
    listOf(
        R.drawable.camelia,
        R.drawable.camelia,
        R.drawable.khalid,
        R.drawable.lana,
        R.drawable.edsheeran,
        R.drawable.dualipa,
        R.drawable.sam,
        R.drawable.marsh,
        R.drawable.wolves,
        R.drawable.camelia,
        R.drawable.khalid,
        R.drawable.lana,
        R.drawable.edsheeran,
        R.drawable.dualipa,
        R.drawable.sam,
        R.drawable.marsh,
        R.drawable.wolves,
        R.drawable.camelia,
        R.drawable.khalid,
        R.drawable.lana,
        R.drawable.edsheeran,
        R.drawable.dualipa,
        R.drawable.sam,
        R.drawable.marsh,
        R.drawable.wolves,
    )