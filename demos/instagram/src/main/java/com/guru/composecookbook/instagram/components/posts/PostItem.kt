package com.guru.composecookbook.instagram.components.posts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.Tweet
import com.guru.composecookbook.instagram.components.profile.ProfileSection
import com.guru.composecookbook.instagram.components.profile.ProfileSectionSizes

@Composable
fun PostItem(
  post: Tweet,
  isLiked: Boolean,
  onLikeClicked: () -> Unit,
  onCommentsClicked: () -> Unit,
  onSendClicked: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(modifier = modifier.fillMaxWidth().padding(8.dp)) {
    ProfileSection(
      firstImageId = post.authorImageId,
      text = post.author,
      size = ProfileSectionSizes.medium(),
      iconRight = {
        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "See more options")
      },
    )
    PostImage(
      imageId = post.tweetImageId,
      contentDescription = post.text,
      modifier = Modifier.padding(top = 8.dp),
    )
    PostInteractionBar(
      isLiked = isLiked,
      onLikeClicked = onLikeClicked,
      onCommentsClicked = onCommentsClicked,
      onSendClicked = onSendClicked,
    )
    ProfileSection(
      firstImageId = post.authorImageId,
      text =
        "Liked by ${DemoDataProvider.tweet.author} and ${DemoDataProvider.tweet.likesCount} others",
    )
    val mediumAlpha = 0.6f
    Text(
      text = "View all ${post.commentsCount} comments",
      style = MaterialTheme.typography.bodySmall,
      modifier = Modifier.padding(top = 4.dp),
      color = MaterialTheme.colorScheme.onSurface.copy(alpha = mediumAlpha),
    )
    Text(
      text = "${post.time} ago",
      style = MaterialTheme.typography.bodySmall,
      modifier = Modifier.padding(top = 4.dp),
      color = MaterialTheme.colorScheme.onSurface.copy(alpha = mediumAlpha),
    )
  }
}

@Preview
@Composable
fun PostItemPreview() {
  PostItem(
    post = DemoDataProvider.tweet,
    isLiked = true,
    onLikeClicked = {},
    onCommentsClicked = {},
    onSendClicked = {},
  )
}
