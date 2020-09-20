package com.guru.composecookbook.ui.moviesappmvi.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.ui.cryptoappmvvm.data.db.entities.Crypto
import com.guru.composecookbook.ui.cryptoappmvvm.ui.home.CryptoHomeActivity
import com.guru.composecookbook.ui.cryptoappmvvm.ui.home.CryptoHomeInteractionEvents
import com.guru.composecookbook.ui.moviesappmvi.data.models.Movie

sealed class MoviesHomeInteractionEvents {
    data class OpenMovieDetail(val movie: Movie) : MoviesHomeInteractionEvents()
    data class AddToMyWatchlist(val movie: Movie) : MoviesHomeInteractionEvents()
    data class RemoveFromMyWatchlist(val movie: Movie): MoviesHomeInteractionEvents()
}

class MoviesHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        setContent {
            ComposeCookBookTheme {
                MovieHomeScreen(
                    moviesHomeInteractionEvents = {
                        handleInteractionEvents(it)
                    }
                )
            }
        }
    }

    fun handleInteractionEvents(interactionEvents: MoviesHomeInteractionEvents) {

    }

    companion object {
        const val DARK_THEME = "darkTheme"
        fun newIntent(context: Context, isDarkTheme: Boolean) =
            Intent(context, MoviesHomeActivity::class.java).apply {
                putExtra(DARK_THEME, isDarkTheme)
            }
    }
}




@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview7() {
    ComposeCookBookTheme {
        Greeting("Android")
    }
}