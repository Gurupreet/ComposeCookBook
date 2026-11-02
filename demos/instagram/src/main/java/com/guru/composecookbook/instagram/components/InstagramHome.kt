package com.guru.composecookbook.instagram.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.R
import com.guru.composecookbook.data.model.Tweet
import com.guru.composecookbook.instagram.components.posts.PostList
import com.guru.composecookbook.instagram.components.stories.StoryList
import com.guru.composecookbook.instagram.components.stories.StoryPopup

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstagramHome(
  posts: List<Tweet>,
  profiles: List<Tweet>,
  onLikeClicked: () -> Unit,
  onCommentsClicked: () -> Unit,
  onSendClicked: () -> Unit,
  onProfileClicked: () -> Unit,
  onMessagingClicked: () -> Unit,
) {
  var showStory = remember { mutableStateOf(false) }

  Box(modifier = Modifier.fillMaxSize()) {
    Scaffold(
      topBar = {
        TopAppBar(
          title = { Text(text = "Instagram") },
          colors =
            TopAppBarDefaults.topAppBarColors(
              containerColor = MaterialTheme.colorScheme.surface,
              titleContentColor = MaterialTheme.colorScheme.onSurface,
              navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
              actionIconContentColor = MaterialTheme.colorScheme.onSurface,
            ),
          navigationIcon = {
            IconButton(onClick = {}) {
              Icon(
                painter = painterResource(id = R.drawable.ic_instagram),
                contentDescription = null,
              )
            }
          },
          actions = {
            IconButton(onClick = onMessagingClicked) {
              Icon(
                painter = painterResource(id = R.drawable.ic_send),
                contentDescription = "Go to messaging screen",
              )
            }
          },
        )
      },
      content = { paddingValues ->
        Surface {
          Column(modifier = Modifier.padding(paddingValues)) {
            StoryList(
              profiles = profiles,
              onProfileClicked = {
                showStory.value = true
                onProfileClicked.invoke()
              },
            )
            HorizontalDivider()
            PostList(
              posts = posts,
              onLikeClicked = onLikeClicked,
              onCommentsClicked = onCommentsClicked,
              onSendClicked = onSendClicked,
            )
          }
        }
      },
    )
    AnimatedVisibility(
      visible = showStory.value,
      enter = expandVertically() + fadeIn(),
      exit = shrinkVertically() + fadeOut(),
    ) {
      StoryPopup(imageIds = DemoDataProvider.itemList.take(5)) { showStory.value = false }
    }
  }
}

@Preview
@Composable
fun PreviewInstagramHome() {
  InstagramHome(
    posts = DemoDataProvider.tweetList.filter { it.tweetImageId != 0 },
    profiles = DemoDataProvider.tweetList,
    onLikeClicked = {},
    onCommentsClicked = {},
    onSendClicked = {},
    onProfileClicked = {},
    onMessagingClicked = {},
  )
}
