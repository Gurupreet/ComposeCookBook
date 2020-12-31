package com.guru.composecookbook.ui.demoui.spotify

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.ui.demoui.spotify.data.Album
import com.guru.composecookbook.ui.demoui.spotify.data.SpotifyDataProvider
import com.guru.composecookbook.ui.demoui.spotify.details.SpotifyDetailActivity
import com.guru.composecookbook.ui.utils.StaggeredVerticalGrid
import com.guru.composecookbook.ui.utils.horizontalGradientBackground
import kotlin.random.Random

@Composable
fun SpotifyPlayList() {
    val albums = remember { SpotifyDataProvider.albums }
    val surfaceGradient = SpotifyDataProvider.spotifySurfaceGradient(isSystemInDarkTheme())

    val context = AmbientContext.current
    ScrollableColumn(modifier = Modifier.horizontalGradientBackground(surfaceGradient)) {
        Spacer(modifier = Modifier.height(50.dp))
        SpotifyTitle(text = "Your Library")
        Spacer(modifier = Modifier.height(20.dp))
        StaggeredVerticalGrid(maxColumnWidth = 250.dp) {
            albums.forEach {
                PlaylistItemWithRandomHeight(it, context)
            }
        }
    }
}

@Composable
fun PlaylistItemWithRandomHeight(album: Album, context: Context) {
    // Randomly pick height for album but remember the same height for that album.
    val randomHeight = remember(album.id) { Random.nextInt(180, 380).dp }

    Card(elevation = 8.dp, modifier = Modifier.padding(6.dp).clickable(onClick = {
        //Disclaimer: We should pass event top level and there should startActivity
        context.startActivity(SpotifyDetailActivity.newIntent(context, album))
    })) {
        Column {
            Image(
                bitmap = imageResource(album.imageId),
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(randomHeight)
            )
            Text(
                text = album.artist, modifier = Modifier.padding(8.dp), style = MaterialTheme.typography.h6.copy(fontSize = 14.sp))
        }
    }
}