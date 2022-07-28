package com.guru.composecookbook.moviesapp.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import coil.annotation.ExperimentalCoilApi
import com.guru.composecookbook.moviesapp.data.db.models.Movie
import com.guru.composecookbook.moviesapp.ui.details.components.MovieDetailContent
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme


@ExperimentalCoilApi
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
            ComposeCookBookMaterial3Theme {
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
