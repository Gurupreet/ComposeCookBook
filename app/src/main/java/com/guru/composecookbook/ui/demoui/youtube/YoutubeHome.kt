package com.guru.composecookbook.ui.demoui.youtube

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Videocam

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.R
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.theme.typography

@Composable
fun YoutubeHome() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Youtube") },
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface,
                elevation = 8.dp,
                navigationIcon = {
                    Icon(
                        asset = vectorResource(id = R.drawable.ic_youtube),
                        tint = Color.Red,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                },
                actions = {
                    Icon(
                        asset = Icons.Default.Videocam,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Icon(
                        asset = Icons.Default.Search,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Image(
                        asset = imageResource(id = R.drawable.p3),
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                            .preferredSize(24.dp).clip(CircleShape)
                    )
                }
            )
        },
        bodyContent = {
            YoutubeContent()
        }
    )
}

@Composable
fun YoutubeContent() {
    val tweets = remember { DemoDataProvider.tweetList.filter { it.tweetImageId > 0 } }
    // There is performance issue when using LazyRowFor and LazyColumnFor inside scrollableColumn
    // So using column for now.
    Column {
        LazyRowFor(items = tweets, modifier = Modifier.padding(vertical = 8.dp)) {
            YoutubeChip(selected = it.id == 2, text = it.author)
        }
        LazyColumnFor(items = tweets) { item ->
            YoutubeListItem(item)
        }
    }
}