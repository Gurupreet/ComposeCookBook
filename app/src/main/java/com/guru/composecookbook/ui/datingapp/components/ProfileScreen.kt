package com.guru.composecookbook.ui.datingapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.ui.demoui.spotify.data.SpotifyDataProvider

@Composable
fun ProfileScreen() {
    val listItems = SpotifyDataProvider.albums
    ScrollableColumn(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        listItems.forEachIndexed { index, album ->
            Image(
                asset = imageResource(id = album.imageId),
                contentScale = ContentScale.Crop,
                modifier = Modifier.width(200.dp + (index * 10).dp).height(300.dp).padding(10.dp)
                    .drawLayer(
                        translationY = -index * 500f,
                        shadowElevation = index * 5f
                    )
            )
        }
    }
}