package com.guru.composecookbook.twitter.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.theme.twitterColor
import com.guru.composecookbook.twitter.R

@Composable
fun TwitterHome() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_twitter),
                        contentDescription = null,
                        tint = twitterColor,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface,
                elevation = 8.dp,
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.p6),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                            .size(32.dp)
                            .clip(CircleShape)
                    )
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.StarBorder,
                        contentDescription = null,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            )
        },
        floatingActionButton = {
            val rippleExplode = remember { mutableStateOf(false) }
            FloatingActionButton(rippleExplode)
            if (rippleExplode.value) {

            }
        },
        content = {
            TwitterHomeContent()
        }
    )
}

@Composable
fun TwitterHomeContent() {
    val tweets = remember { DemoDataProvider.tweetList }
    LazyColumn {
        items(
            items = tweets,
            itemContent = {
                TwitterListItem(tweet = it)
            })
    }
}

@Composable
fun FloatingActionButton(rippleExplode: MutableState<Boolean>) {
    ExtendedFloatingActionButton(
        text = { Text(text = "Tweet") },
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_twitter),
                contentDescription = null
            )
        },
        onClick = { rippleExplode.value = !rippleExplode.value },
        backgroundColor = twitterColor
    )
}

@Preview
@Composable
fun ShowTwitterScreen() {
    TwitterHome()
}