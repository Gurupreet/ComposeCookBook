package com.guru.composecookbook.ui.demoapps.datingapp.components

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.tween
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.gesture.DragObserver
import androidx.compose.ui.gesture.rawDragGestureFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.AmbientConfiguration
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.ui.demoapps.datingapp.SwipeResult
import kotlin.math.abs

@Composable
fun DraggableCard(
    item: Any,
    modifier: Modifier = Modifier,
    onSwiped: (Any, Any) -> Unit,
    content: @Composable () -> Unit
) {
    val configuration = AmbientConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val swipeXLeft = -(screenWidth.value * 3.2).toFloat()
    val swipeXRight = (screenWidth.value * 3.2).toFloat()
    val swipeYTop = -400f
    val swipeYBottom = 400f
    val swipeX = animatedFloat(initVal = 0f)
    val swipeY = animatedFloat(initVal = 0f)
    val tweenTime = 400

    val dragObserver = object : DragObserver {

        override fun onStart(downPosition: Offset) {
            swipeX.setBounds(swipeXLeft, swipeXRight)
            swipeY.setBounds(swipeYTop, swipeYBottom)
            super.onStart(downPosition)
        }

        override fun onStop(velocity: Offset) {
            // if it's swiped 1/3rd
            if (abs(swipeX.targetValue) < abs(swipeXRight) / 4) {
                swipeX.animateTo(0f, tween(tweenTime))
            } else {
                if (swipeX.targetValue > 0) {
                    swipeX.animateTo(swipeXRight, tween(tweenTime))
                } else {
                    swipeX.animateTo(swipeXLeft, tween(tweenTime))
                }
            }

            swipeY.animateTo(0f, tween(tweenTime))
            super.onStop(velocity)
        }


        override fun onCancel() {
            swipeX.animateTo(0f, tween(tweenTime))
            swipeX.animateTo(0f, tween(tweenTime))
            super.onCancel()
        }

        override fun onDrag(dragDistance: Offset): Offset {
            swipeX.animateTo(swipeX.targetValue + dragDistance.x)
            swipeY.animateTo(swipeY.targetValue + dragDistance.y)
            return super.onDrag(dragDistance)
        }
    }

    val rotationFraction = (swipeX.value / 60).coerceIn(-40f, 40f)

    if (abs(swipeX.value) < swipeXRight - 50f) {
        Card(
            elevation = 16.dp,
            modifier = modifier.rawDragGestureFilter(dragObserver).graphicsLayer(
                translationX = swipeX.value,
                translationY = swipeY.value,
                rotationZ = rotationFraction,
            ).clip(RoundedCornerShape(16.dp))
        ) {
            content()
        }
    } else {
        // on swiped
        val swipeResult = if (swipeX.value > 0) SwipeResult.ACCEPTED else SwipeResult.REJECTED
        onSwiped(swipeResult, item)
    }
}