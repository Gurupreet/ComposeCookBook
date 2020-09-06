package com.guru.composecookbook.ui.demoui.twitter

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.ColumnScope.gravity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.R
import com.guru.composecookbook.data.DemoDataProvider

val twitterColor = Color(0xFF1DA1F2)
@Composable
fun TwitterHome() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Icon(
                        asset = vectorResource(id = R.drawable.ic_twitter),
                        tint = twitterColor,
                        modifier = Modifier.fillMaxWidth().gravity(Alignment.CenterHorizontally)
                    )
                },
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface,
                elevation = 8.dp,
                navigationIcon = {
                    Image(
                        asset = imageResource(id = R.drawable.p6),
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                            .preferredSize(32.dp).clip(CircleShape)
                    )
                },
                actions = {
                    Icon(
                        asset = Icons.Default.StarBorder,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton()
        },
        bodyContent = {
            TwitterHomeContent()
        }
    )
}

@Composable
fun TwitterHomeContent() {
    val tweets = remember { DemoDataProvider.tweetList }
    LazyColumnFor(items = tweets) {
        TwitterListItem(tweet = it)
    }
}

@Composable
fun FloatingActionButton() {
    ExtendedFloatingActionButton(
        text = { Text(text = "Tweet") },
        icon = { Icon(asset = vectorResource(id = R.drawable.ic_twitter)) },
        onClick = {},
        backgroundColor = twitterColor
    )
}

@Preview
@Composable
fun ShowTwitterScreen() {
    TwitterHome()
}