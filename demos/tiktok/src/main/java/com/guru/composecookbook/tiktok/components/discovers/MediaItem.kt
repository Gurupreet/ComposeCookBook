package com.guru.composecookbook.tiktok.components.discovers

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun MediaItem(
    id: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = rememberImagePainter(
            data = "https://picsum.photos/id/${id}/200/200"
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .height(150.dp)
            .width(120.dp)
            .padding(2.dp),
    )
}
