package com.guru.composecookbook.ui.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.components.Material3Card
import com.guru.composecookbook.theme.green200
import com.guru.composecookbook.ui.utils.SubtitleText
import com.guru.composecookbook.ui.utils.TitleText
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun AnimatableSuspendedAnimations() {
    TitleText(title = "Suspending Animations via Animatable(floatValue) with Bounds")
    SubtitleText(subtitle = "Use it with PointerInput or LaunchedEffects")
    SimpleDismissUsingAnimatable()
    Divider()
    PointerInputWithAnimateable()
    Divider()
    DraggableCardWithAnimatable()
    Divider()
}

@Composable
fun SimpleDismissUsingAnimatable() {
    val animatableValue = remember { Animatable(0f) }
    animatableValue.updateBounds(-100f, 100f)
    var enable by remember { mutableStateOf(true) }
    Material3Card(
        backgroundColor = green200,
        modifier = Modifier
            .size(100.dp)
            .offset(x = Dp(animatableValue.value))
            .clickable { enable = !enable },
    ) {
        LaunchedEffect(enable) {
            if (enable) {
                animatableValue.animateTo(100f)
            } else {
                animatableValue.animateTo(-100f)
            }
        }
    }
}

@Composable
fun PointerInputWithAnimateable() {
    SubtitleText(subtitle = "Drag using Pointer input with Animatable in coroutines")
    val pointerAnimatableX = remember { Animatable(0f) }

    val modifier = Modifier.pointerInput(Unit) {
        coroutineScope {
            while (true) {
                val firstDownId = awaitPointerEventScope {
                    awaitFirstDown().id
                }
                awaitPointerEventScope {
                    horizontalDrag(firstDownId, onDrag = {
                        launch {
                            pointerAnimatableX.animateTo(it.position.x)
                        }
                    })
                }
            }
        }
    }
    Material3Card(
        backgroundColor = green200,
        modifier = modifier
            .size(100.dp)
            .offset(x = pointerAnimatableX.value.dp)
    ) {
    }
}

@Composable
fun DraggableCardWithAnimatable() {
    SubtitleText(subtitle = "Drag using Pointer input with Animatable in coroutines")
    Spacer(modifier = Modifier.height(100.dp))
    val pointerAnimatableX = remember { Animatable(0f) }
    val pointerAnimatableY = remember { Animatable(0f) }

    // pointerAnimatableX.updateBounds(-200f, 200f)
    //pointerAnimatableY.updateBounds(-200f, 200f)

    val modifier = Modifier.pointerInput(Unit) {
        coroutineScope {
            detectDragGestures(onDragCancel = {
                launch {
                    pointerAnimatableX.snapTo(0f)
                    pointerAnimatableY.snapTo(0f)
                }
            }) { _, dragAmount ->
                launch {
                    pointerAnimatableX.animateTo(dragAmount.x)
                    pointerAnimatableY.animateTo(dragAmount.y)
                }
            }
        }
    }
    Material3Card(
        backgroundColor = green200,
        modifier = modifier
            .size(100.dp)
            .offset(x = pointerAnimatableX.value.dp, y = pointerAnimatableX.value.dp)
    ) {
    }
    Spacer(modifier = Modifier.height(100.dp))
}