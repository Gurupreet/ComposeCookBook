package com.guru.composecookbook.theme.modifiers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode

fun Modifier.horizontalGradientBackground(
    colors: List<Color>
) = gradientBackground(colors) { gradientColors, size ->
    Brush.horizontalGradient(
        colors = gradientColors,
        startX = 0f,
        endX = size.width
    )
}

fun Modifier.verticalGradientBackground(
    colors: List<Color>
) = gradientBackground(colors) { gradientColors, size ->
    Brush.verticalGradient(
        colors = gradientColors,
        startY = 0f,
        endY = size.width
    )
}

fun Modifier.diagonalGradientTint(
    colors: List<Color>,
    blendMode: BlendMode
) = gradientTint(colors, blendMode) { gradientColors, size ->
    Brush.linearGradient(
        colors = gradientColors,
        start = Offset(
            x = 0f,
            y = 0f
        ),
        end = Offset(
            x = size.width,
            y = size.height
        ),
        tileMode = TileMode.Clamp
    )
}

fun Modifier.gradientBackground(
    colors: List<Color>,
    brushProvider: (List<Color>, Size) -> Brush
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
    brushProvider: (List<Color>, Size) -> Brush
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

//TODO fix drag obervers
//fun Modifier.swipeGesture(
//    swipeValue: AnimatedFloat,
//    swipeDirection: Direction = Direction.LEFT,
//    maxSwipe: Float,
//    onItemSwiped: () -> Unit
//): Modifier = composed {
//    (this then dragGestureFilter(
//        canDrag = { it == swipeDirection },
//        dragObserver = dragObserver(
//            swipeValue = swipeValue,
//            maxSwipe = maxSwipe,
//            onItemSwiped = onItemSwiped
//        )
//    )).then(object : LayoutModifier {
//        override fun MeasureScope.measure(
//            measurable: Measurable,
//            constraints: Constraints
//        ): MeasureResult {
//            val children = measurable.measure(constraints)
//            //  swipeValue.setBounds(-children.width.toFloat()-100f, children.width.toFloat()+100f)
//            return layout(children.width, children.height) {
//                children.place(swipeValue.value.toInt(), 0)
//            }
//        }
//    })
//}
//
//@Composable
//fun dragObserver(
//    swipeValue: AnimatedFloat,
//    maxSwipe: Float,
//    onItemSwiped: () -> Unit
//): DragObserver {
//
//    return object : DragObserver {
//        override fun onStart(downPosition: Offset) {
//            //  swipeValue.setBounds(-maxSwipe, maxSwipe)
//        }
//
//        private fun reset() {
//            swipeValue.animateTo(
//                0f,
//                anim = SpringSpec(
//                    dampingRatio = 0.8f, stiffness = 300f
//                )
//            )
//        }
//
//        override fun onDrag(dragDistance: Offset): Offset {
//            swipeValue.snapTo(swipeValue.targetValue + dragDistance.x)
//            return dragDistance
//        }
//
//        override fun onStop(velocity: Offset) {
//            if (abs(swipeValue.targetValue) < 400f) {
//                reset()
//            } else {
//                val animateTo = if (swipeValue.value > 0) maxSwipe else -maxSwipe
//                swipeValue.animateTo(
//                    animateTo,
//                    anim = SpringSpec<Float>(
//                        dampingRatio = 0.8f, stiffness = 300f
//                    ),
//                    onEnd = { _, _ ->
//                        // On swiped do something
//                        // onItemSwiped.invoke()
//                    }
//                )
//                // actually it should be in animation end but it's bit slow animation I put it out.
//                onItemSwiped.invoke()
//            }
//        }
//    }
//}
