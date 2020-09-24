package com.guru.composecookbook.ui.moviesappmvi.ui

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.moviesappmvi.data.models.Movie
import dev.chrisbanes.accompanist.coil.CoilImage


@Composable
fun MoviesLaneItem(movies: List<Movie>, title: String = "", onMovieSelected: (Movie) -> Unit) {
    if (title.isNotEmpty()) {
        Text(
            text = title,
            style = typography.h6,
            modifier = Modifier.padding(start = 16.dp, end = 8.dp, bottom = 8.dp, top = 24.dp)
        )
    }
    LazyRowFor(items = movies) { movie ->
        CoilImage(
            data = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
            modifier = Modifier
                .preferredWidth(190.dp)
                .preferredHeight(300.dp)
                .padding(12.dp)
                .clip(RoundedCornerShape(12.dp))
                .clickable(onClick = { onMovieSelected(movie) }),
            contentScale = ContentScale.Crop
        )
    }
}