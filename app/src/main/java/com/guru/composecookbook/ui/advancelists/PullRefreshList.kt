package com.guru.composecookbook.ui.advancelists

import androidx.compose.animation.animate
import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.*
import androidx.compose.foundation.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.RotateRight
import androidx.compose.material.icons.filled.SettingsBackupRestore
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.gesture.dragGestureFilter
import androidx.compose.ui.Alignment
import androidx.compose.ui.gesture.Direction
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.ui.demoui.spotify.data.SpotifyDataProvider
import com.guru.composecookbook.ui.demoui.spotify.details.SpotifySongListItem

@Composable
fun PullRefreshList(onPullRefresh: () -> Unit) {
    val albums = SpotifyDataProvider.albums

    val initialYTranslate = -100f
    val maximumYTranslate = 200f
    val animatedProgress = animatedFloat(initVal = initialYTranslate)
    val lazyListState = rememberLazyListState()

    val draggableModifier = Modifier.draggable(
        orientation = Orientation.Vertical,
        reverseDirection = false,
        enabled = lazyListState.firstVisibleItemIndex <= 1,
        onDrag = {
            onPullRefresh.invoke()
            if (animatedProgress.value < maximumYTranslate) {
                animatedProgress.animateTo(
                    targetValue = maximumYTranslate,
                    anim = tween(durationMillis = 1200, easing = LinearEasing),
                )
            }
        },
        onDragStopped = {
            animatedProgress.animateTo(
                targetValue = 3000f,
                anim = repeatable(
                    iterations = 1,
                    animation = tween(durationMillis = 3000, easing = LinearEasing,),
                ),
                onEnd = { _, _ ->
                    animatedProgress.animateTo(
                        targetValue = initialYTranslate,
                        anim = tween(durationMillis = 300, easing = LinearEasing),
                    )
                }
            )
        }
    )

    Box {
        LazyColumnFor(
            items = albums,
            state = lazyListState,
            modifier = draggableModifier
        ) {
            SpotifySongListItem(album = it)
        }
        //Animated Icon
        Icon(
            asset = Icons.Default.RotateRight,
            tint = Color.Black,
            modifier = Modifier.align(Alignment.TopCenter)
                .drawLayer(
                    translationY = animate(animatedProgress.value.coerceIn(initialYTranslate, maximumYTranslate)),
                    rotationZ = animate(animatedProgress.value
                    )
                ).background(Color.LightGray, shape = CircleShape).padding(2.dp)
        )
    }
}
