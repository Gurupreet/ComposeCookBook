package com.guru.composecookbook.ui.advancelists

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.drawLayer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.Tweet
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.demoui.youtube.YoutubeChip
import com.guru.composecookbook.ui.utils.VerticalGrid

@Composable
fun AnimatedLists() {
    val tweets = DemoDataProvider.tweetList
    val animations = listOf("Fade", "ScaleX", "ScaleY", "Bounce")

    Column {
        var selectedIndex by remember { mutableStateOf(0) }
        VerticalGrid(columns = 4, modifier = Modifier.padding(vertical = 12.dp)) {
            animations.forEachIndexed { index, title ->
                YoutubeChip(
                    selected = index == selectedIndex,
                    text = title,
                    modifier = Modifier.padding(8.dp).clickable(onClick = {
                        selectedIndex = index
                    })
                )
            }
        }
        LazyColumnFor(items = tweets) {
            AnimatedListItem(tweet = it, selectedIndex)
        }
    }
}

@Composable
fun AnimatedListItem(tweet: Tweet, selectedIndex: Int) {
    var animatedProgress = animatedFloat(0f)

    onActive {
        animatedProgress.animateTo(
            targetValue = 1f,
            anim = tween(1000)
        )
    }

    val animatedModifier = when (selectedIndex) {
        0 -> Modifier.padding(8.dp).drawOpacity(animatedProgress.value)
        1 -> Modifier.padding(8.dp).drawLayer(scaleX = animatedProgress.value)
        2 -> Modifier.padding(8.dp).drawLayer(scaleY = animatedProgress.value)
        3 -> {
            Modifier.padding(8.dp)
                .drawLayer(scaleY = animatedProgress.value, scaleX = animatedProgress.value)
        }
        else -> Modifier.padding(8.dp).drawOpacity(animatedProgress.value)
    }
    Row(
        modifier = animatedModifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            asset = imageResource(id = tweet.authorImageId),
            contentScale = ContentScale.Crop,
            modifier = Modifier.preferredSize(55.dp).padding(4.dp)
        )
        Column(modifier = Modifier.padding(horizontal = 4.dp).weight(1f)) {
            Text(
                text = tweet.author,
                style = typography.h6.copy(fontSize = 16.sp),
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = tweet.text,
                style = typography.subtitle2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Icon(
            asset = Icons.Default.MoreVert,
            tint = Color.LightGray,
            modifier = Modifier.padding(4.dp)
        )
    }
}