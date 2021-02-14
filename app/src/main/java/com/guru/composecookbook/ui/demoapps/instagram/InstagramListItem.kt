package com.guru.composecookbook.ui.demoapps.instagram

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.R
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.Tweet
import com.guru.composecookbook.theme.typography

@Composable
fun InstagramListItem(post: Tweet) {
    Column {
        ProfileInfoSection(post)
        InstagramImage(imageId = post.tweetImageId)
        InstagramIconSection()
        InstagramLikesSection(post)
        Text(
            text = "View all ${post.commentsCount} comments",
            style = typography.caption,
            modifier = Modifier.padding(start = 8.dp, top = 4.dp),
            color = Color.Gray
        )
        Text(
            text = "${post.time} ago",
            style = typography.caption,
            modifier = Modifier.padding(start = 8.dp, top = 2.dp, bottom = 8.dp),
            color = Color.Gray
        )
    }
}

@Composable
private fun InstagramLikesSection(post: Tweet) {
    Row(
        modifier = Modifier.padding(start = 8.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = post.authorImageId),
            modifier = Modifier.preferredSize(20.dp).clip(CircleShape),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Liked by ${post.author} and ${post.likesCount} others",
            style = typography.caption,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
private fun InstagramIconSection() {
    Row {
        var fav by remember { mutableStateOf(false) }
        IconToggleButton(checked = fav, onCheckedChange = { fav = it }) {
            val icon = if (fav) Icons.Default.Favorite else Icons.Default.FavoriteBorder
            val tint = if (fav) Color.Red else MaterialTheme.colors.onBackground
            Icon(imageVector = icon, modifier = Modifier.preferredSize(44.dp), tint = tint, contentDescription = null)
        }
        IconToggleButton(checked = false, onCheckedChange = {}) {
            Icon(
                painter = painterResource(id = R.drawable.ic_speech_bubble),
                contentDescription = null,
                modifier = Modifier.preferredSize(44.dp)
            )
        }
        IconToggleButton(checked = false, onCheckedChange = {}) {
            Icon(
                painter = painterResource(id = R.drawable.ic_send),
                contentDescription = null,
                modifier = Modifier.preferredSize(44.dp)
            )
        }
    }
}

@Composable
private fun ProfileInfoSection(post: Tweet) {
    Row(
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = post.authorImageId),
            modifier = Modifier.preferredSize(32.dp).clip(CircleShape),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        // Which ever element has weight defined will take most space available
        Text(
            text = post.author,
            style = typography.body1,
            modifier = Modifier.padding(8.dp).weight(1f),
            textAlign = TextAlign.Left
        )
        Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
    }
}

@Composable
private fun InstagramImage(imageId: Int) {
    if (imageId != 0) {
        Image(
            painter = painterResource(id = imageId),
            modifier = Modifier.fillMaxWidth().preferredHeight(450.dp),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun PreviewInstagramItem() {
    val post = DemoDataProvider.tweet
    InstagramListItem(post = post)
}