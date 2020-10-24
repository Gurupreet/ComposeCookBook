package com.guru.composecookbook.ui.advancelists

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.Tweet
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.demoui.youtube.YoutubeChip
import com.guru.composecookbook.ui.utils.VerticalGrid

@Composable
fun AnimatedLists() {
    val tweets = DemoDataProvider.tweetList
    val animations = listOf("Fade", "Scale", "Slide", "Fade+Slide", "Slide up", "RotateX")

    Column {
        var selectedIndex by remember { mutableStateOf(0) }
        VerticalGrid(columns = 3, modifier = Modifier.padding(vertical = 12.dp)) {
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

    val animatedModifier = when (selectedIndex) {
        0 -> {
            val animatedProgress = animatedFloat(0f)
            onActive {
                animatedProgress.animateTo(
                    targetValue = 1f,
                    anim = tween(600)
                )
            }
            Modifier.padding(8.dp).drawOpacity(animatedProgress.value)
        }
        1 -> {
            val animatedProgress = animatedFloat(0.8f)
            onActive {
                animatedProgress.animateTo(
                    targetValue = 1f,
                    anim = tween(300, easing = LinearEasing)
                )
            }
            Modifier.padding(8.dp)
                .drawLayer(scaleY = animatedProgress.value, scaleX = animatedProgress.value)
        }
        2 -> {
            val animatedProgress = animatedFloat(300f)
            onActive {
                animatedProgress.animateTo(
                    targetValue = 0f,
                    anim = tween(300, easing = FastOutSlowInEasing)
                )
            }
            Modifier.padding(8.dp).drawLayer(translationX = animatedProgress.value)
        }
        3 -> {
            val animatedProgress = animatedFloat(-300f)
            val opacityProgress = animatedFloat(0f)
            onActive {
                animatedProgress.animateTo(
                    targetValue = 0f,
                    anim = tween(300, easing = LinearEasing)
                )
                opacityProgress.animateTo(
                    targetValue = 1f,
                    anim = tween(600)
                )
            }
            Modifier.padding(8.dp)
                .drawLayer(translationX = animatedProgress.value)
                .drawOpacity(opacityProgress.value)
        }
        4 -> {
            val animatedProgress = animatedFloat(300f)
            val opacityProgress = animatedFloat(0f)
            onActive {
                animatedProgress.animateTo(
                    targetValue = 0f,
                    anim = tween(300, easing = LinearEasing)
                )
                opacityProgress.animateTo(
                    targetValue = 1f,
                    anim = tween(600)
                )
            }
            Modifier.padding(8.dp)
                .drawLayer(translationY = animatedProgress.value)
                .drawOpacity(opacityProgress.value)
        }
        5 -> {
            val animatedProgress = animatedFloat(0f)
            onActive {
                animatedProgress.animateTo(
                    targetValue = 360f,
                    anim = tween(400, easing = FastOutSlowInEasing)
                )
            }
            Modifier.padding(8.dp)
                .drawLayer(rotationX = animatedProgress.value)
        }
        else -> {
            val animatedProgress = animatedFloat(0.8f)
            onActive {
                animatedProgress.animateTo(
                    targetValue = 1f,
                    anim = tween(300)
                )
            }
            Modifier.padding(8.dp).drawOpacity(animatedProgress.value)
        }
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