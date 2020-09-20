package com.guru.composecookbook.ui.moviesappmvi.ui

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Icon
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.align
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.AnimationClockAmbient
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import com.guru.composecookbook.R
import com.guru.composecookbook.theme.graySurface
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.carousel.Pager
import com.guru.composecookbook.ui.carousel.PagerState
import com.guru.composecookbook.ui.demoui.spotify.SpotifyNavType
import com.guru.composecookbook.ui.demoui.spotify.generateDominantColorState
import com.guru.composecookbook.ui.utils.verticalGradientBackground

@Composable
fun MovieHomeScreen(moviesHomeInteractionEvents: (MoviesHomeInteractionEvents) -> Unit) {
    Scaffold(
        bottomBar = { MoviesBottomBar() }
    ) {
        MovieHomeScreenContent()
    }
}

@Composable
fun MovieHomeScreenContent() {
    //TODO dynamic gradient from poster right now It's just getting from local images
    var imageId =  remember {  mutableStateOf(R.drawable.camelia) }
    val defaultBitmap = imageResource(id = imageId.value).asAndroidBitmap()
    var currentBitmap =   mutableStateOf(defaultBitmap)
    val swatch =  generateDominantColorState(currentBitmap.value)
    val dominantColors = listOf(Color(swatch.rgb), Color.Black)

    ScrollableColumn(modifier = Modifier.fillMaxSize().verticalGradientBackground(dominantColors)) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Now Showing",
            style = typography.h5.copy(fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally)
        )
        MoviesPager(imageId)
    }
}

@Composable
fun MoviesPager(imageId: MutableState<Int>) {
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
        Pager(state = pagerState, modifier = Modifier.preferredHeight(620.dp)) {
            Log.d("pager offset", currentPageOffset.toString())
            val movie = movies[page]
            imageId.value = imageIds[pagerState.currentPage]
            val isSelected = pagerState.currentPage == page

            MoviePagerItem(movie, isSelected)
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

@Composable
fun MoviesBottomBar() {
        val bottomNavBackground =
            if (isSystemInDarkTheme()) graySurface else MaterialTheme.colors.background
        BottomNavigation(backgroundColor = bottomNavBackground) {
            BottomNavigationItem(
                icon = { Icon(asset = Icons.Outlined.MovieCreation) },
                selected = true,
                onClick = {  },
                label = { Text(text = "Showing") },
            )
            BottomNavigationItem(
                icon = { Icon(asset = Icons.Outlined.Search) },
                selected = false,
                onClick = {  },
                label = { Text(text = "Search") }
            )
            BottomNavigationItem(
                icon = { Icon(asset = Icons.Outlined.LibraryAdd) },
                selected = false,
                onClick = {  },
                label = { Text(text = "Watchlist") }
            )
        }
}

enum class MovieNavType{
    SHOWING, TRENDING, WATCHLIST
}

val imageIds  =
        listOf(
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