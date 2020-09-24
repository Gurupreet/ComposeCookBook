package com.guru.composecookbook.ui.moviesappmvi.ui.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.align
import androidx.compose.foundation.layout.RowScope.align
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import com.guru.composecookbook.theme.graySurface
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.demoui.spotify.data.SpotifyDataProvider
import com.guru.composecookbook.ui.moviesappmvi.data.models.Movie
import com.guru.composecookbook.ui.utils.horizontalGradientBackground
import com.guru.composecookbook.ui.utils.verticalGradientBackground
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun WatchlistScreen(moviesHomeInteractionEvents: (MoviesHomeInteractionEvents) -> Unit) {
    val surfaceGradient = SpotifyDataProvider.spotifySurfaceGradient(isSystemInDarkTheme())
    val viewModel: MoviesHomeViewModel = viewModel()
    val myWatchlist by viewModel.myWatchlist.observeAsState(emptyList())
    if (myWatchlist.isNotEmpty()) {
        Surface(modifier = Modifier.horizontalGradientBackground(surfaceGradient)) {
            LazyColumnForIndexed(
                items = myWatchlist
            ) { index, movie ->
                MovieWatchlistItem(
                    movie,
                    { moviesHomeInteractionEvents(
                        MoviesHomeInteractionEvents.OpenMovieDetail(movie))
                    },
                    { moviesHomeInteractionEvents(
                        MoviesHomeInteractionEvents.RemoveFromMyWatchlist(movie))
                    }
                )
                if (index == myWatchlist.size -1) {
                    Spacer(modifier = Modifier.padding(30.dp))
                }
            }
        }
    } else {
        EmptyWatchlistSection()
    }
}

@Composable
fun MovieWatchlistItem(
    movie: Movie,
    onMovieSelected: () -> Unit,
    onRemoveFromWatchlist: () -> Unit
) {
    Stack(modifier = Modifier.clickable(onClick = onMovieSelected)) {
        CoilImage(
            data = "https://image.tmdb.org/t/p/original/${movie.backdrop_path}",
            modifier = Modifier
                .fillMaxWidth()
                .preferredHeight(280.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(//overlay
            modifier = Modifier
                .fillMaxWidth()
                .preferredHeight(280.dp)
                .background(Color.Black.copy(alpha = 0.1f))
        )
        Text(
            text = movie.title,
            style = typography.h6.copy(fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.align(Alignment.BottomStart).padding(8.dp)
        )
        IconButton(
            onClick = { onRemoveFromWatchlist.invoke() },
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(asset = Icons.Default.RemoveCircleOutline)
        }
    }
}

@Composable
fun EmptyWatchlistSection() {
    Column {
        Spacer(modifier = Modifier.padding(100.dp))
        Text(
            text = "Watchlist is empty",
            style = typography.h6,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Please add some movies to your watchlist",
            style = typography.caption,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}