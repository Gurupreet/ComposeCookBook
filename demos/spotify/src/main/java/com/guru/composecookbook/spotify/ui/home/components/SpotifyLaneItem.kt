package com.guru.composecookbook.spotify.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.data.model.Album
import com.guru.composecookbook.spotify.ui.details.SpotifyDetailActivity
import com.guru.composecookbook.theme.typography

@Composable
fun SpotifyLaneItem(album: Album) {
    val context = LocalContext.current
    Column(
        modifier =
        Modifier
            .width(180.dp)
            .padding(8.dp)
            .clickable(
                onClick = {
                    //Disclaimer: We should pass event top level and there should startActivity
                    context.startActivity(SpotifyDetailActivity.newIntent(context, album))
                })
    ) {
        Image(
            painter = painterResource(id = album.imageId),
            modifier = Modifier
                .width(180.dp)
                .height(160.dp),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            text = "${album.song}: ${album.descriptions}",
            style = typography.body2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Preview
@Composable
fun PreviewLaneItem() {
    val album = remember { AlbumsDataProvider.album }
    SpotifyLaneItem(album)
}
