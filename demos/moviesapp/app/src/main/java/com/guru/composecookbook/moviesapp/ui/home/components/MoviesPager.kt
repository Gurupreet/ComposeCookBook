package com.guru.composecookbook.moviesapp.ui.home.components

import android.util.Log
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guru.composecookbook.moviesapp.ui.home.MoviesHomeInteractionEvents
import com.guru.composecookbook.moviesapp.ui.home.MoviesHomeViewModel
import com.guru.composecookbook.moviesapp.ui.home.MoviesHomeViewModelFactory
import com.guru.composecookbook.carousel.Pager
import com.guru.composecookbook.carousel.PagerState
import com.guru.composecookbook.moviesapp.R

@Composable
fun MoviesPager(
    imageId: MutableState<Int>,
    moviesHomeInteractionEvents: (MoviesHomeInteractionEvents) -> Unit
) {
    val moviesViewModel: MoviesHomeViewModel = viewModel(
        factory = MoviesHomeViewModelFactory(LocalContext.current)
    )
    val movies by moviesViewModel.nowShowingLiveData.observeAsState(emptyList())
    val genres by moviesViewModel.genresLiveData.observeAsState(emptyList())
    val error by moviesViewModel.errorLiveData.observeAsState()

    if (movies.isNotEmpty()) {
        val pagerState: PagerState = run {
            remember {
                PagerState(0, 0, movies.size - 1)
            }
        }
        Pager(state = pagerState, modifier = Modifier.height(645.dp)) {
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
