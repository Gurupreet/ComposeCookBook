package com.guru.composecookbook.twitter.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.Tweet
import com.guru.composecookbook.theme.twitterColor
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.twitter.R

@Composable
fun TwitterListItem(tweet: Tweet) {
    Row {
        AuthorImage(tweet)
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            NameAndHandle(tweet)
            Text(text = tweet.text, style = typography.body1)
            TweetImage(tweet.tweetImageId)
            TweetIconSection(tweet = tweet)
            Divider(thickness = 0.5.dp)
        }
    }
}

@Composable
private fun NameAndHandle(tweet: Tweet) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        //username
        Text(text = tweet.author, style = typography.h6, modifier = Modifier.padding(end = 4.dp))
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = twitterColor,
            modifier = Modifier
                .size(18.dp)
                .align(Alignment.CenterVertically)
                .padding(top = 2.dp)
        )
        //handle
        Text(
            text = tweet.handle + " . " + tweet.time,
            modifier = Modifier.padding(start = 8.dp),
            style = typography.body1,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun AuthorImage(tweet: Tweet) {
    Image(
        painter = painterResource(id = tweet.authorImageId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(50.dp)
            .clip(CircleShape)
    )
}

@Composable
private fun TweetImage(imageId: Int) {
    if (imageId != 0) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun TweetIconSection(tweet: Tweet) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {}) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_speech_bubble),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.LightGray
                )
                Text(
                    text = tweet.commentsCount.toString(),
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.LightGray,
                    style = typography.caption
                )
            }
        }
        IconButton(onClick = {}) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_retweet_solid),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.LightGray
                )
                Text(
                    text = tweet.retweetCount.toString(),
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.LightGray,
                    style = typography.caption
                )
            }
        }
        IconButton(onClick = {}) {
            Row {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.LightGray
                )
                Text(
                    text = tweet.likesCount.toString(),
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.LightGray,
                    style = typography.caption
                )
            }
        }
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = Color.LightGray
            )
        }
    }

}


@Preview
@Composable
fun PreviewTwitterItem() {
    val tweet = DemoDataProvider.tweet
    TwitterListItem(tweet = tweet)
}