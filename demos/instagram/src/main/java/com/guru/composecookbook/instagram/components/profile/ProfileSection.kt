package com.guru.composecookbook.instagram.components.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.DemoDataProvider

@Composable
fun ProfileSection(
  @DrawableRes firstImageId: Int,
  text: String,
  modifier: Modifier = Modifier,
  iconRight: @Composable () -> Unit = {},
  size: ProfileSectionSize = ProfileSectionSizes.small()
) {
  Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
    ProfilePicture(imageId = firstImageId, contentDescription = null, size = size.profileIconSize)
    Text(
      text = text,
      style = size.textStyle,
      color = MaterialTheme.colors.onBackground,
      modifier = Modifier.weight(1f).padding(start = 8.dp)
    )
    iconRight()
  }
}

data class ProfileSectionSize(val profileIconSize: Dp, val textStyle: TextStyle)

object ProfileSectionSizes {
  @Composable fun small() = ProfileSectionSize(ProfileSizes.small, MaterialTheme.typography.caption)

  @Composable fun medium() = ProfileSectionSize(ProfileSizes.medium, MaterialTheme.typography.body1)
}

@Preview
@Composable
fun ProfileInfoPreview() {
  ProfileSection(
    firstImageId = DemoDataProvider.tweetList.first().authorImageId,
    text = DemoDataProvider.tweetList.first().author,
    size = ProfileSectionSizes.medium(),
    iconRight = { Icon(imageVector = Icons.Default.MoreVert, contentDescription = null) }
  )
}

@Preview
@Composable
fun LikesSectionPreview() {
  val tweet = DemoDataProvider.tweetList.first()
  ProfileSection(
    firstImageId = tweet.authorImageId,
    text = "Liked by ${tweet.author} and ${tweet.likesCount} others"
  )
}
