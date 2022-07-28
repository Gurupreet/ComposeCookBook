package com.guru.composecookbook.twitter.components.icons

import androidx.compose.foundation.layout.*
import androidx.compose.material.icon
import androidx.compose.material.iconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.twitter.R


object IconCounters {
    @Composable
    fun MessagesCounter(
        counter: Int,
        modifier: Modifier = Modifier,
        onClick: () -> Unit
    ) {
        IconCounter(
            painter = painterResource(id = R.drawable.ic_speech_bubble),
            counter = counter,
            modifier = modifier.semantics {
                contentDescription = "$counter reactions. Create a reply too!"
            },
            onClick = onClick
        )
    }

    @Composable
    fun RetweetCounter(
        counter: Int,
        modifier: Modifier = Modifier,
        onClick: () -> Unit
    ) {
        IconCounter(
            painter = painterResource(id = R.drawable.ic_retweet_solid),
            counter = counter,
            modifier = modifier.semantics {
                contentDescription = "$counter retweets. Retweet this tweet too!"
            },
            onClick = onClick
        )
    }

    @Composable
    fun LikesCounter(
        counter: Int,
        modifier: Modifier = Modifier,
        onClick: () -> Unit
    ) {
        IconCounter(
            painter = rememberVectorPainter(Icons.Default.FavoriteBorder),
            counter = counter,
            modifier = modifier.semantics {
                contentDescription = "$counter likes. Like this tweet too!"
            },
            onClick = onClick
        )
    }
}

@Composable
internal fun IconCounter(
    painter: Painter,
    counter: Int,
    modifier: Modifier = Modifier,
    color: Color = Color.LightGray,
    textStyle: TextStyle = MaterialTheme.typography.caption,
    onClick: () -> Unit
) {
    IconButton(modifier = modifier, onClick = onClick) {
        Row(modifier = Modifier.height(16.dp)) {
            Icon(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                tint = color
            )
            Text(
                text = counter.toString(),
                modifier = Modifier.padding(start = 8.dp),
                color = color,
                style = textStyle
            )
        }
    }
}

@Preview
@Composable
fun IconCounterPreview() {
    Column {
        IconCounters.MessagesCounter(
            counter = 122,
            onClick = {}
        )
        IconCounters.RetweetCounter(
            counter = 105,
            onClick = {}
        )
        IconCounters.LikesCounter(
            counter = 32,
            onClick = {}
        )
    }
}
