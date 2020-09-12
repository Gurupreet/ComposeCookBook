package com.guru.composecookbook.ui.demoui.spotify

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.ui.demoui.spotify.data.Album
import com.guru.composecookbook.ui.demoui.spotify.data.SpotifyDataProvider
import com.guru.composecookbook.ui.dynamic.DynamicUIActivity

class SpotifyDetailActivity : AppCompatActivity() {

    private val album: Album by lazy {
        intent?.getSerializableExtra(ALBUM) as Album
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    val album = SpotifyDataProvider.album
    ComposeCookBookTheme {
        SpotifyDetailScreen(album)
    }
}