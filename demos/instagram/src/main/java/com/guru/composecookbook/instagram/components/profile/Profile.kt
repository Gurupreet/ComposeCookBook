package com.guru.composecookbook.instagram.components.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.DemoDataProvider

@Composable
fun ProfilePicture(
    @DrawableRes imageId: Int,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    size: Dp = ProfileSizes.medium,
) {
    Image(
        painter = painterResource(id = imageId),
        modifier = modifier
            .size(size)
            .clip(CircleShape),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop
    )
}

object ProfileSizes {
    val small = 20.dp
    val medium = 32.dp
    val large = 64.dp
}

@Preview
@Composable
fun ProfilePicturePreview() {
    Column {
        ProfilePicture(
            imageId = DemoDataProvider.tweetList.first().authorImageId,
            contentDescription = "Profile picture",
            size = ProfileSizes.small
        )
        ProfilePicture(
            imageId = DemoDataProvider.tweetList.first().authorImageId,
            contentDescription = "Profile picture",
            size = ProfileSizes.medium
        )
    }
}
