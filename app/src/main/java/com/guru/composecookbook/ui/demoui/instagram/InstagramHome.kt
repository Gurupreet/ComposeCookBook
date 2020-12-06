package com.guru.composecookbook.ui.demoui.instagram

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.guru.composecookbook.R
import com.guru.composecookbook.data.DemoDataProvider

@Composable
fun InstagramHome() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Instagram") },
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface,
                elevation = 8.dp,
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = vectorResource(id = R.drawable.ic_instagram))
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = vectorResource(id = R.drawable.ic_send))
                    }
                }
            )
        },
        bodyContent = {
            InstagramHomeContent()
        }
    )
}

@Composable
fun InstagramHomeContent() {
    Column {
        InstagramStories()
        Divider()
        InstagramPostsList()
    }
}

@Composable
fun InstagramPostsList() {
    val posts = remember { DemoDataProvider.tweetList.filter { it.tweetImageId != 0 } }
    LazyColumnFor(items = posts) {
        InstagramListItem(post = it)
    }
}

@Preview
@Composable
fun PreviewInstagramHome() {
    InstagramHome()
}