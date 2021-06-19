package com.guru.composecookbook.spotify.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.data.model.Album
import com.guru.composecookbook.spotify.ui.details.components.SpotifyDetailScreen
import com.guru.composecookbook.theme.ComposeCookBookTheme

class SpotifyDetailActivity : ComponentActivity() {

    private val album: Album by lazy {
        intent?.getSerializableExtra(ALBUM) as Album
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.black)
        setContent {
            ComposeCookBookTheme {
                SpotifyDetailScreen(album)
            }
        }
    }

    companion object {
        const val ALBUM = "album"
        fun newIntent(context: Context, album: Album) =
            Intent(context, SpotifyDetailActivity::class.java).apply {
                putExtra(ALBUM, album)
            }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSpotifyDetailActivity() {
    val album = AlbumsDataProvider.album
    ComposeCookBookTheme {
        SpotifyDetailScreen(album)
    }
}