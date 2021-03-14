package com.guru.composecookbook.ui.demoapps.datingapp.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.ui.demoapps.datingapp.SwipeResult
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
fun DraggableCard(
    item: Any,
    modifier: Modifier = Modifier,
    onSwiped: (Any, Any) -> Unit,
    content: @Composable () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val swipeXLeft = -(screenWidth.value * 3.2).toFloat()
    val swipeXRight = (screenWidth.value * 3.2).toFloat()
    val swipeYTop = -1000f
    val swipeYBottom = 1000f
    val swipeX = remember { Animatable(0f) }
    val swipeY = remember { Animatable(0f) }
    swipeX.updateBounds(swipeXLeft, swipeXRight)
    swipeY.updateBounds(swipeYTop, swipeYBottom)
    val rotationFraction = (swipeX.value / 60).coerceIn(-40f, 40f)
    val graphicLayer = Modifier.graphicsLayer(
        translationX = swipeX.value,
        translationY = swipeY.value,
        rotationZ = rotationFraction,
    )
    if (abs(swipeX.value) < swipeXRight - 50f) {
        Card(
            elevation = 16.dp,
            modifier = modifier
                .dragContent(
                    swipeX = swipeX,
                    swipeY = swipeY,
                    maxX = swipeXRight,
                    onSwiped = { _, _ ->
                    }
                )
                .then(graphicLayer)
                .clip(RoundedCornerShape(16.dp))
        ) {
            content()
        }
    } else {
        // on swiped
        val swipeResult = if (swipeX.value > 0) SwipeResult.ACCEPTED else SwipeResult.REJECTED
        onSwiped(swipeResult, item)
//    }

    }
}

fun Modifier.dragContent(
    swipeX: Animatable<Float, AnimationVector1D>,
    swipeY: Animatable<Float, AnimationVector1D>,
    maxX: Float,
    onSwiped: (Any, Any) -> Unit
): Modifier = composed {
    val coroutineScope = rememberCoroutineScope()
    pointerInput(Unit) {
        this.detectDragGestures(
            onDragCancel = {
                coroutineScope.apply {
                    launch {  swipeX.animateTo(0f) }
                    launch {  swipeY.animateTo(0f) }
                }
            },
            onDragEnd = {
                coroutineScope.apply {
                    // if it's swiped 1/4th
                    if (abs(swipeX.targetValue) < abs(maxX) / 4) {
                        launch {
                            swipeX.animateTo(0f, tween(400))
                        }
                        launch {
                            swipeY.animateTo(0f, tween(400))
                        }
                    } else {
                        launch {
                            if (swipeX.targetValue > 0) {
                                swipeX.animateTo(maxX, tween(400))
                            } else {
                                swipeX.animateTo(-maxX, tween(400))
                            }
                        }
                    }
                }
            }
        ) { change, dragAmount ->
            change.consumePositionChange()
            coroutineScope.apply {
                launch { swipeX.animateTo(swipeX.targetValue + dragAmount.x) }
                launch { swipeY.animateTo(swipeY.targetValue + dragAmount.y) }
            }
        }
    }
}