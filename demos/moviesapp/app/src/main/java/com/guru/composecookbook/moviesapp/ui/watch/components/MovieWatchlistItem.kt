package com.guru.composecookbook.moviesapp.ui.watch.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.guru.composecookbook.moviesapp.data.db.models.Movie
import com.guru.composecookbook.theme.typography

@Composable
fun MovieWatchlistItem(
    movie: Movie,
    onMovieSelected: () -> Unit,
    onRemoveFromWatchlist: () -> Unit
) {
    Box(modifier = Modifier.clickable(onClick = onMovieSelected)) {
        Image(
            painter = rememberImagePainter(
                data = "https://image.tmdb.org/t/p/original/${movie.backdrop_path}"
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(//overlay
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(Color.Black.copy(alpha = 0.1f))
        )
        Text(
            text = movie.title,
            style = typography.h6.copy(fontWeight = FontWeight.ExtraBold),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp)
        )
        IconButton(
            onClick = { onRemoveFromWatchlist.invoke() },
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(imageVector = Icons.Default.RemoveCircleOutline, contentDescription = null)
        }
    }
}
