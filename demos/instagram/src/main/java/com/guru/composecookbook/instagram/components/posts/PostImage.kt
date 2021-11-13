package com.guru.composecookbook.instagram.components.posts

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.DemoDataProvider

@Composable
fun PostImage(
    @DrawableRes imageId: Int,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = imageId),
        modifier = modifier.fillMaxWidth().height(450.dp),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun PostImagePreview() {
    PostImage(
        imageId = DemoDataProvider.tweetList.first { it.tweetImageId != 0 }.tweetImageId,
        contentDescription = null
    )
}