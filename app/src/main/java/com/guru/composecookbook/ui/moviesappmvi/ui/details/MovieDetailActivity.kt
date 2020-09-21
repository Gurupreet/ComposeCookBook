package com.guru.composecookbook.ui.moviesappmvi.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Icon
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LibraryAdd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.moviesappmvi.data.models.Movie
import dev.chrisbanes.accompanist.coil.CoilImage

class MovieDetailActivity : AppCompatActivity() {
    val movie by lazy {
        intent.getSerializableExtra(MOVIE) as Movie?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        setContent {
            ComposeCookBookTheme {
                val expand = remember { mutableStateOf(false) }
                ScrollableColumn(
                    modifier = Modifier.padding(
                        animate(
                            if (expand.value) 1.dp else 120.dp,
                            tween(350)
                        )
                    ),
                ) {
                    movie?.let { movie ->
                        CoilImage(
                            data = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .preferredHeight(
                                    600.dp
                                ).fillMaxWidth(),
                            onRequestCompleted = { expand.value = true }
                        )
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
                                IconToggleButton(checked = false, onCheckedChange = {}) {
                                    Icon(asset = Icons.Default.LibraryAdd)
                                }
                            }
                            Text(
                                text = "Release: ${movie.release_date}",
                                modifier = Modifier.padding(horizontal = 8.dp),
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
                            Text(
                                text = movie.overview,
                                modifier = Modifier
                                    .padding(8.dp),
                                style = typography.subtitle2
                            )
                            Spacer(modifier = Modifier.height(50.dp))
                            Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                                Text(text = "Get Tickets", modifier = Modifier.padding(8.dp))
                            }
                            Spacer(modifier = Modifier.height(100.dp))
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val MOVIE = "movie"
        fun newIntent(context: Context, movie: Movie) =
            Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(MOVIE, movie)
            }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview8() {
    ComposeCookBookTheme {

    }
}