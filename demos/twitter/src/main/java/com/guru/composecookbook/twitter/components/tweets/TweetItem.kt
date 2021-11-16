package com.guru.composecookbook.twitter.components.tweets

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.Tweet
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.twitter.components.icons.IconCounterBar
import com.guru.composecookbook.twitter.components.profiles.ProfileInfo
import com.guru.composecookbook.twitter.components.profiles.ProfilePicture
import com.guru.composecookbook.twitter.components.profiles.ProfilePictureSizes

@Composable
fun TweetItem(
    tweet: Tweet,
    onMessagesClick: () -> Unit,
    onRetweetClick: () -> Unit,
    onLikesClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.padding(8.dp)) {
        ProfilePicture(
            profileImageId = tweet.authorImageId,
            size = ProfilePictureSizes.medium
        )
        Column(modifier = Modifier.padding(start = 8.dp).fillMaxWidth()) {
            ProfileInfo(
                profileName = tweet.author,
                profilePing = tweet.handle,
                time = tweet.time
            )
            Text(text = tweet.text, style = typography.body1)
            TweetImage(
                imageId = tweet.tweetImageId,
                modifier = Modifier.padding(top = 8.dp)
            )
            IconCounterBar(
                commentCount = tweet.commentsCount,
                retweetCount = tweet.retweetCount,
                likesCount = tweet.likesCount,
                onMessagesClick = onMessagesClick,
                onRetweetClick = onRetweetClick,
                onLikesClick = onLikesClick,
                onShareClick = onShareClick,
                modifier = Modifier.fillMaxWidth().padding(end = 16.dp)
            )
            Divider(thickness = 0.5.dp)
        }
    }
}

@Preview
@Composable
fun PreviewTwitterItem() {
    val tweet = DemoDataProvider.tweet
    TweetItem(
        tweet = tweet,
        onMessagesClick = { /*TODO*/ },
        onRetweetClick = { /*TODO*/ },
        onLikesClick = { /*TODO*/ },
        onShareClick = { /*TODO*/ },
    )
}
