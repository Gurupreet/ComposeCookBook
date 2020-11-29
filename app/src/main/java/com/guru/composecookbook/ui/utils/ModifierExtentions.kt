package com.guru.composecookbook.ui.utils

import androidx.compose.animation.core.AnimatedFloat
import androidx.compose.animation.core.SpringSpec
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.gesture.Direction
import androidx.compose.ui.gesture.DragObserver
import androidx.compose.ui.gesture.dragGestureFilter
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.unit.Constraints
import kotlin.math.abs

fun Modifier.horizontalGradientBackground(
    colors: List<Color>
) = gradientBackground(colors) { gradientColors, size ->
    HorizontalGradient(
        colors = gradientColors,
        startX = 0f,
        endX = size.width
    )
}

fun Modifier.verticalGradientBackground(
    colors: List<Color>
) = gradientBackground(colors) { gradientColors, size ->
    VerticalGradient(
        colors = gradientColors,
        startY = 0f,
        endY = size.width
    )
}

fun Modifier.diagonalGradientTint(
    colors: List<Color>,
    blendMode: BlendMode
) = gradientTint(colors, blendMode) { gradientColors, size ->
    LinearGradient(
        colors = gradientColors,
        startX = 0f,
        startY = 0f,
        endX = size.width,
        endY = size.height
    )
}

fun Modifier.gradientBackground(
    colors: List<Color>,
    brushProvider: (List<Color>, Size) -> LinearGradient
): Modifier = composed {
    var size by remember { mutableStateOf(Size.Zero) }
    val gradient = remember(colors, size) { brushProvider(colors, size) }
    drawWithContent {
        size = this.size
        drawRect(brush = gradient)
        drawContent()
    }
}

fun Modifier.gradientTint(
    colors: List<Color>,
    blendMode: BlendMode,
    brushProvider: (List<Color>, Size) -> LinearGradient
) = composed {
    var size by remember { mutableStateOf(Size.Zero) }
    val gradient = remember(colors, size) { brushProvider(colors, size) }
    drawWithContent {
        drawContent()
        size = this.size
        drawRect(
            brush = gradient,
            blendMode = blendMode
        )
    }
}

@Composable
fun Modifier.swipeGesture(
    swipeValue: AnimatedFloat,
    swipeDirection: Direction = Direction.LEFT,
    maxSwipe: Float,
    onItemSwiped: () -> Unit
): Modifier {
    return this + dragGestureFilter(
        canDrag = { it == swipeDirection },
        dragObserver = dragObserver(swipeValue = swipeValue, maxSwipe = maxSwipe, onItemSwiped = onItemSwiped)
    ) + object : LayoutModifier {
        override fun MeasureScope.measure(
            measurable: Measurable,
            constraints: Constraints
        ): MeasureResult {
            val children = measurable.measure(constraints)
          //  swipeValue.setBounds(-children.width.toFloat()-100f, children.width.toFloat()+100f)
            return  layout(children.width, children.height) {
                children.place(swipeValue.value.toInt(), 0)
            }
        }
    }
}

@Composable
fun dragObserver(
    swipeValue: AnimatedFloat,
    maxSwipe: Float,
    onItemSwiped: () -> Unit
): DragObserver {

    return object : DragObserver {
        override fun onStart(downPosition: Offset) {
          //  swipeValue.setBounds(-maxSwipe, maxSwipe)
        }

        private fun reset() {
            swipeValue.animateTo(
                0f,
                anim = SpringSpec<Float>(
                    dampingRatio = 0.8f, stiffness = 300f
                )
            )
        }

        override fun onDrag(dragDistance: Offset): Offset {
            swipeValue.snapTo(swipeValue.targetValue + dragDistance.x)
            return dragDistance
        }

        override fun onStop(velocity: Offset) {
            if (abs(swipeValue.targetValue) < 400f) {
                reset()
            } else {
                val animateTo = if (swipeValue.value > 0) maxSwipe else -maxSwipe
                swipeValue.animateTo(
                    animateTo,
                    anim = SpringSpec<Float>(
                        dampingRatio = 0.8f, stiffness = 300f
                    ),
                    onEnd = { _, _ ->
                        // On swiped do something
                        // onItemSwiped.invoke()
                    }
                )
                // actually it should be in animation end but it's bit slow animation I put it out.
                onItemSwiped.invoke()
            }
        }
    }
}
