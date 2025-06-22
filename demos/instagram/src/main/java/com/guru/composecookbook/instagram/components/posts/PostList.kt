package com.guru.composecookbook.instagram.components.posts

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.guru.composecookbook.data.model.Tweet
import kotlin.random.Random

@Composable
fun PostList(
  posts: List<Tweet>,
  onLikeClicked: () -> Unit,
  onCommentsClicked: () -> Unit,
  onSendClicked: () -> Unit,
  modifier: Modifier = Modifier,
) {
  LazyColumn(modifier = modifier) {
    items(posts) {
      PostItem(
        post = it,
        isLiked = Random.nextBoolean(),
        onLikeClicked = onLikeClicked,
        onCommentsClicked = onCommentsClicked,
        onSendClicked = onSendClicked
      )
    }
  }
}
