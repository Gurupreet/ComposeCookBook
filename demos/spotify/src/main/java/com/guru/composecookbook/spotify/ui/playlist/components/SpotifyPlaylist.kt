package com.guru.composecookbook.spotify.ui.playlist.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.data.model.Album
import com.guru.composecookbook.spotify.data.SpotifyDataProvider
import com.guru.composecookbook.spotify.ui.details.SpotifyDetailActivity
import com.guru.composecookbook.spotify.ui.home.components.SpotifyTitle
import com.guru.composecookbook.theme.modifiers.horizontalGradientBackground
import com.guru.composecookbook.verticalgrid.StaggeredVerticalGrid
import kotlin.random.Random

@Composable
fun SpotifyPlayList() {
    val albums = remember { AlbumsDataProvider.albums }
    val surfaceGradient = SpotifyDataProvider.spotifySurfaceGradient(isSystemInDarkTheme())
    val context = LocalContext.current
    LazyColumn(modifier = Modifier.horizontalGradientBackground(surfaceGradient)) {
        // use `item` for separate elements like headers
        // and `items` for lists of identical elements
        item { Spacer(modifier = Modifier.height(50.dp)) }
        item { SpotifyTitle(text = "Your Library") }
        item { Spacer(modifier = Modifier.height(20.dp)) }
        item {
            StaggeredVerticalGrid(maxColumnWidth = 250.dp) {
                albums.forEach {
                    PlaylistItemWithRandomHeight(it, context)
                }
            }
        }
    }
}

@Composable
fun PlaylistItemWithRandomHeight(album: Album, context: Context) {
    // Randomly pick height for album but remember the same height for that album.
    val randomHeight = remember(album.id) { Random.nextInt(180, 380).dp }

    Card(
        elevation = 8.dp, modifier = Modifier
            .padding(6.dp)
            .clickable(onClick = {
                //Disclaimer: We should pass event top level and there should startActivity
                context.startActivity(SpotifyDetailActivity.newIntent(context, album))
            })
    ) {
        Column {
            Image(
                painter = painterResource(album.imageId),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier.height(randomHeight)
            )
            Text(
                text = album.artist,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.h6.copy(fontSize = 14.sp)
            )
        }
    }
}