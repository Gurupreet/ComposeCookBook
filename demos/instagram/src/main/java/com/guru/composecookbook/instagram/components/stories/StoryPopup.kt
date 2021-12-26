package com.guru.composecookbook.instagram.components.stories

import androidx.compose.ui.res.painterResource

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.model.Item
import com.guru.composecookbook.instagram.components.profile.ProfilePicture
import com.guru.composecookbook.instagram.components.profile.ProfileSizes
import com.guru.composecookbook.theme.instagramGradient
import kotlinx.coroutines.launch

@Composable
fun StoryPopup(imageIds: List<Item>, closePopup: () -> Unit) {

    var currentStoryCount by remember { mutableStateOf(0) }

    var storyPaused by remember { mutableStateOf(false) }

    var percent = remember { Animatable(0f) }

    val config = LocalConfiguration.current
    val width = with(LocalDensity.current) { config.screenWidthDp.dp.toPx() }
    val leftClickSectionX = width/4
    val rightClickSectionX = width - width/4
    val coroutineScope = rememberCoroutineScope()
    val storySize = imageIds.size
    val brushGradient = Brush.linearGradient(
        colors = instagramGradient,
        start = Offset(x = 0f, y = 0f),
        end = Offset(x = 100f, y = 100f)
    )

    BackHandler() {
        closePopup.invoke()
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTapGestures(
                onPress = { pressScope ->
                    storyPaused = true
                    if (tryAwaitRelease()) {
                        storyPaused = false
                    }
                }
            ) { offset ->
                when {
                    offset.x < leftClickSectionX -> {
                        if (currentStoryCount > 0) {
                            currentStoryCount--
                            coroutineScope.launch {
                                percent.snapTo(0f)
                            }
                        }
                    }
                    offset.x > rightClickSectionX -> {
                        if (currentStoryCount < storySize - 1) {
                            currentStoryCount++
                            coroutineScope.launch {
                                percent.snapTo(0f)
                            }
                        } else {
                            closePopup.invoke()
                        }
                    }
                }
            }
        }) {
            Image(
                painter = painterResource(id = imageIds[currentStoryCount].imageId),
                modifier = Modifier.fillMaxSize(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)) {
            LaunchedEffect(currentStoryCount, storyPaused) { // (2)
                if (storyPaused) {
                    percent.stop()
                } else {
                    percent.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(
                            durationMillis = (5000 * (1f - percent.value)).toInt(), // (3)
                            easing = LinearEasing
                        )
                    )
                    if (storySize - 1 > currentStoryCount) {
                        percent.snapTo(0f)
                        currentStoryCount++

                    } else {
                        closePopup.invoke()
                    }
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                (0 until storySize).forEach { storyIndex ->
                    val progress = when {
                        storyIndex < currentStoryCount -> 1f
                        storyIndex > currentStoryCount -> 0f
                        else -> percent.value
                    }
                    LinearProgressIndicator(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 4.dp),
                        progress = progress,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                ProfilePicture(
                    imageId = imageIds[0].imageId,
                    contentDescription = null,
                    size = ProfileSizes.medium,
                    modifier = Modifier
                        .border(
                            shape = CircleShape,
                            border = BorderStroke(width = 3.dp, brush = brushGradient)
                        )

                )
                Text(
                    text = "My Story", modifier = Modifier
                        .padding(8.dp)
                        .weight(1f),
                    color = MaterialTheme.colors.onBackground
                )
                IconButton(onClick = { closePopup.invoke() }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close",  tint = MaterialTheme.colors.onBackground)
                }
            }
        }

    }
}
