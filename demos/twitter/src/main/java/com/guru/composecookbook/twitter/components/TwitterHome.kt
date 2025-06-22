package com.guru.composecookbook.twitter.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.R
import com.guru.composecookbook.data.model.Tweet
import com.guru.composecookbook.theme.twitterColor
import com.guru.composecookbook.twitter.components.profiles.ProfilePicture
import com.guru.composecookbook.twitter.components.profiles.ProfilePictureSizes
import com.guru.composecookbook.twitter.components.tweets.TweetItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TwitterHome(
        tweets: List<Tweet>,
        onMessagesClick: () -> Unit,
        onRetweetClick: () -> Unit,
        onLikesClick: () -> Unit,
        onShareClick: () -> Unit,
        onNewTweetClicked: () -> Unit,
        modifier: Modifier = Modifier
) {
    Scaffold(
            modifier = modifier,
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
                        backgroundColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                        elevation = 8.dp,
                        navigationIcon = {
                            ProfilePicture(
                                    profileImageId = R.drawable.p6,
                                    size = ProfilePictureSizes.small,
                                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
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
                ExtendedFloatingActionButton(
                        text = { Text(text = "Tweet") },
                        icon = {
                            Icon(
                                    painter = painterResource(id = R.drawable.ic_twitter),
                                    contentDescription = null
                            )
                        },
                        onClick = onNewTweetClicked,
                        containerColor = twitterColor
                )
            },
            content = { paddingValues ->
                LazyColumn(modifier = Modifier.padding(paddingValues)) {
                    items(tweets) {
                        TweetItem(
                                tweet = it,
                                onMessagesClick = onMessagesClick,
                                onRetweetClick = onRetweetClick,
                                onLikesClick = onLikesClick,
                                onShareClick = onShareClick,
                        )
                    }
                }
            }
    )
}

@Preview
@Composable
fun ShowTwitterScreen() {
    TwitterHome(
            tweets = DemoDataProvider.tweetList,
            onMessagesClick = { /*TODO*/},
            onRetweetClick = { /*TODO*/},
            onLikesClick = { /*TODO*/},
            onShareClick = { /*TODO*/},
            onNewTweetClicked = { /*TODO*/}
    )
}
