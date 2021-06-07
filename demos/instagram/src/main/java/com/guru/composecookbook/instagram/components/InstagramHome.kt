package com.guru.composecookbook.instagram.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.instagram.R

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
                        Icon(
                            painter = painterResource(id = R.drawable.ic_instagram),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_send),
                            contentDescription = null
                        )
                    }
                }
            )
        },
        content = {
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
    LazyColumn {
        items(
            items = posts,
            itemContent = {
                InstagramListItem(post = it)
            })
    }
}

@Preview
@Composable
fun PreviewInstagramHome() {
    InstagramHome()
}