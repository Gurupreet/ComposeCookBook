package com.guru.composecookbook.moviesapp.ui.details.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LibraryAdd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState
import com.guru.composecookbook.moviesapp.data.db.models.Movie
import com.guru.composecookbook.moviesapp.ui.details.MovieDetailViewModel
import com.guru.composecookbook.moviesapp.ui.details.MovieDetailViewModelFactory
import com.guru.composecookbook.theme.extensions.generateDominantColorState
import com.guru.composecookbook.theme.graySurface
import com.guru.composecookbook.theme.modifiers.verticalGradientBackground
import com.guru.composecookbook.theme.typography

@Composable
fun MovieDetailContent(movie: Movie, imageId: Int) {
    val expand = remember { mutableStateOf(false) }
    val viewModel: MovieDetailViewModel = viewModel(
        factory = MovieDetailViewModelFactory(LocalContext.current)
    )
    var dominantColors = listOf(graySurface, Color.Black)

    if (imageId != 0) {
        val context = LocalContext.current
        val currentBitmap = ImageBitmap.imageResource(context.resources, imageId)

        val swatch = currentBitmap.asAndroidBitmap().generateDominantColorState()
        dominantColors = listOf(Color(swatch.rgb), Color.Black)
    }

    LazyColumn(
        modifier = Modifier
            .verticalGradientBackground(dominantColors)
            .padding(
                animateDpAsState(
                    if (expand.value) 1.dp else 120.dp,
                    tween(350)
                ).value
            )
    ) {
        item {
            val painter = rememberCoilPainter(
                request = "https://image.tmdb.org/t/p/w500/${movie.poster_path}"
            )
            Image(
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .height(
                        600.dp
                    )
                    .fillMaxWidth(),
            )
            when (painter.loadState) {
                is ImageLoadState.Success -> expand.value = true
                else -> expand.value = false
            }
        }
        item {
            Column(modifier = Modifier.background(MaterialTheme.colors.onSurface)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = movie.title,
                        modifier = Modifier.padding(8.dp),
                        style = typography.h6
                    )
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.LibraryAdd,
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary
                        )
                    }
                }
                GenreSection(viewModel, movie.genre_ids)
                Text(
                    text = "Release: ${movie.release_date}",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    style = typography.h6.copy(fontSize = 12.sp)
                )
                Text(
                    text = "PG13  •  ${movie.vote_average}/10",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    style = typography.h6.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
                Text(
                    text = movie.overview,
                    modifier = Modifier
                        .padding(8.dp),
                    style = typography.subtitle2
                )
                Spacer(modifier = Modifier.height(20.dp))
                SimilarMoviesSection(movie, viewModel)
                Spacer(modifier = Modifier.height(50.dp))
                Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Get Tickets", modifier = Modifier.padding(8.dp))
                }
            }
        }

    }
}
