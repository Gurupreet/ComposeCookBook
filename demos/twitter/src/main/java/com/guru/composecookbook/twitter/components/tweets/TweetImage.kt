package com.guru.composecookbook.twitter.components.tweets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun TweetImage(@DrawableRes imageId: Int, modifier: Modifier = Modifier) {
  if (imageId != 0) {
    Image(
      painter = painterResource(id = imageId),
      contentDescription = null,
      modifier = modifier.fillMaxWidth().height(150.dp).clip(RoundedCornerShape(4.dp)),
      contentScale = ContentScale.Crop
    )
  }
}
