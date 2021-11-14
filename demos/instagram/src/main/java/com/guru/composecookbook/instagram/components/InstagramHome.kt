package com.guru.composecookbook.instagram.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.Tweet
import com.guru.composecookbook.instagram.R
import com.guru.composecookbook.instagram.components.posts.PostList
import com.guru.composecookbook.instagram.components.stories.StoryList

@Composable
fun InstagramHome(
    posts: List<Tweet>,
    profiles: List<Tweet>,
    onLikeClicked: () -> Unit,
    onCommentsClicked: () -> Unit,
    onSendClicked: () -> Unit,
    onProfileClicked: () -> Unit,
    onMessagingClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Instagram") },
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface,
                elevation = 8.dp,
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_instagram),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onMessagingClicked) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_send),
                            contentDescription = "Go to messaging screen"
                        )
                    }
                }
            )
        },
        content = {
            Column {
                StoryList(
                    profiles = profiles,
                    onProfileClicked = onProfileClicked
                )
                Divider()
                PostList(
                    posts = posts,
                    onLikeClicked = onLikeClicked,
                    onCommentsClicked = onCommentsClicked,
                    onSendClicked = onSendClicked
                )
            }
        }
    )
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
        onMessagingClicked = {}
    )
}