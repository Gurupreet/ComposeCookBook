package com.guru.composecookbook.ui.demoui.instagram

import androidx.compose.foundation.Border
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.drawBorder
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.Tweet
import com.guru.composecookbook.theme.typography

@Composable
fun InstagramStories() {
    val posts = remember { DemoDataProvider.tweetList }
    LazyRowFor(posts, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
        StoryListItem(post = it)
    }
}

@Composable
fun StoryListItem(post: Tweet) {
    var imageModifier =
        if (post.id == 1) {
            Modifier
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                .preferredHeight(60.dp)
                .preferredWidth(60.dp)
                .clip(CircleShape)
                .drawBorder(
                    shape = CircleShape,
                    border = Border(
                        3.dp,
                        color = Color.LightGray
                    )
                )
        } else {
            Modifier
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                .preferredHeight(60.dp)
                .preferredWidth(60.dp)
                .clip(CircleShape)
                .drawBorder(
                    shape = CircleShape,
                    border = Border(
                        3.dp,
                        brush = LinearGradient(
                            listOf(Color.Magenta, Color.Red, Color.Yellow),
                            startX = 0f,
                            endX = 100f,
                            startY = 0f,
                            endY = 100f
                        )
                    )
                )
        }

    Column(horizontalGravity = Alignment.CenterHorizontally) {
        Image(
            asset = imageResource(id = post.authorImageId),
            contentScale = ContentScale.Crop,
            modifier = imageModifier
        )
        Text(text = post.author, style = typography.caption, textAlign = TextAlign.Center)
    }
}