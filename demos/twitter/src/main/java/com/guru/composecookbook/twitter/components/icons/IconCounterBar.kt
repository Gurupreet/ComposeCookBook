package com.guru.composecookbook.twitter.components.icons

import androidx.compose.foundation.layout.*
import androidx.compose.material.icon
import androidx.compose.material.iconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun IconCounterBar(
    commentCount: Int,
    retweetCount: Int,
    likesCount: Int,
    onMessagesClick: () -> Unit,
    onRetweetClick: () -> Unit,
    onLikesClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconCounters.MessagesCounter(
            counter = commentCount,
            onClick = onMessagesClick
        )
        IconCounters.RetweetCounter(
            counter = retweetCount,
            onClick = onRetweetClick
        )
        IconCounters.LikesCounter(
            counter = likesCount,
            onClick = onLikesClick
        )
        IconButton(onClick = onShareClick) {
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
fun IconCounterBarPreview() {
    IconCounterBar(
        commentCount = 122,
        retweetCount = 6,
        likesCount = 32,
        onMessagesClick = { /*TODO*/ },
        onRetweetClick = { /*TODO*/ },
        onLikesClick = { /*TODO*/ },
        onShareClick = { /*TODO*/ })
}
