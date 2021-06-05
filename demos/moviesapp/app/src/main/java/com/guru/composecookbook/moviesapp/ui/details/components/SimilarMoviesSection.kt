package com.guru.composecookbook.moviesapp.ui.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.guru.composecookbook.moviesapp.data.db.models.Movie
import com.guru.composecookbook.moviesapp.ui.details.MovieDetailViewModel
import com.guru.composecookbook.theme.typography

@Composable
fun SimilarMoviesSection(currentMovie: Movie?, viewModel: MovieDetailViewModel) {
    viewModel.getSimilarMovies(currentMovie?.id.toString())
    val similarMovies by viewModel.similarMoviesLiveData.observeAsState()
    similarMovies?.let { movies ->
        Text(text = "Similar Movies", style = typography.h5, modifier = Modifier.padding(8.dp))
        LazyRow {
            items(
                items = movies,
                itemContent = { movie: Movie ->
                    Image(
                        painter = rememberCoilPainter(
                            request = "https://image.tmdb.org/t/p/w500/${movie.poster_path}"
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .width(200.dp)
                            .height(300.dp)
                            .padding(12.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
                })
        }
    }
}
