package com.guru.composecookbook.ui.demoapps.moviesappmvi.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LibraryAdd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.theme.graySurface
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.demoapps.moviesappmvi.data.models.Movie
import com.guru.composecookbook.ui.demoapps.spotify.generateDominantColorState
import com.guru.composecookbook.ui.templates.profile.InterestTag
import com.guru.composecookbook.ui.utils.verticalGradientBackground
import dev.chrisbanes.accompanist.coil.CoilImage

class MovieDetailActivity : ComponentActivity() {
    val movie by lazy {
        intent.getSerializableExtra(MOVIE) as Movie?
    }
    val imageId by lazy {
        intent.getIntExtra(IMAGE_ID, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        setContent {
            ComposeCookBookTheme {
                movie?.let {
                    MovieDetailContent(it, imageId)
                }
            }
        }
    }

    companion object {
        const val MOVIE = "movie"
        const val IMAGE_ID = "imageId"
        fun newIntent(context: Context, movie: Movie) =
            Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(MOVIE, movie)
            }

        fun newIntent(context: Context, movie: Movie, imageId: Int) =
            Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(MOVIE, movie)
                putExtra(IMAGE_ID, imageId)
            }
    }
}

@Composable
fun MovieDetailContent(movie: Movie, imageId: Int) {
    val expand = remember { mutableStateOf(false) }
    val viewModel: MovieDetailViewModel = viewModel()
    var dominantColors = listOf(graySurface, Color.Black)

    if (imageId != 0) {
        val context = LocalContext.current
        val currentBitmap = imageFromResource(
            res = context.resources,
            resId = imageId
        ).asAndroidBitmap()

        val swatch = generateDominantColorState(currentBitmap)
        dominantColors = listOf(Color(swatch.rgb), Color.Black)
    }

    LazyColumn(
        modifier = Modifier.verticalGradientBackground(dominantColors)
            .padding(
                animateDpAsState(
                    if (expand.value) 1.dp else 120.dp,
                    tween(350)
                ).value
            )
    ) {
        item {
            CoilImage(
                data = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .height(
                        600.dp
                    ).fillMaxWidth(),
                onRequestCompleted = {
                    expand.value = true
                }
            )
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
                    CoilImage(
                        data = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
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

@Composable
fun GenreSection(viewModel: MovieDetailViewModel, movieGenreIds: List<Int>?) {
    movieGenreIds?.let { movieGenreIds ->
        val genres by viewModel.genresLiveData.observeAsState(emptyList())
        val movieGenres = genres.filter { movieGenreIds.contains(it.id) }.take(3)
        Row {
            movieGenres.forEach {
                InterestTag(text = it.name)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview8() {
    ComposeCookBookTheme {

    }
}