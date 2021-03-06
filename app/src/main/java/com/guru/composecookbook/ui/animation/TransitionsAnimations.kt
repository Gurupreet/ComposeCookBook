package com.guru.composecookbook.ui.animation

import MyAnimationState
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.green200
import com.guru.composecookbook.theme.green500
import com.guru.composecookbook.theme.orange
import com.guru.composecookbook.theme.purple
import com.guru.composecookbook.ui.utils.SubtitleText
import com.guru.composecookbook.ui.utils.TitleText

@Composable
fun TransitionAnimationsWithMultipleStates() {
    TitleText(title = "Multi-state Animations via Transition and States")
    MultiStateColorPositionAnimation()
    Divider()
    MultiStateInfiniteTransition()
    Divider()
    FloatMultiStateAnimationCircleStrokeCanvas()
    Divider()
    FloatMultiStateAnimationCircleCanvas()
}

@Composable
fun MultiStateColorPositionAnimation() {
    SubtitleText(subtitle = "Multi State color and position transition")
    Spacer(modifier = Modifier.height(80.dp))
    var animationState by remember { mutableStateOf(MyAnimationState.START) }
    val startColor = green200
    val midColor = purple
    val endColor = orange

    val transition = updateTransition(targetState = animationState)

    val animatedColor by transition.animateColor(
        transitionSpec = { tween(500) }
    ) { state ->
        when (state) {
            MyAnimationState.START -> startColor
            MyAnimationState.MID -> midColor
            MyAnimationState.END -> endColor
        }
    }

    val position by transition.animateDp(
        transitionSpec = { tween(500) }
    ) { state ->
        when (state) {
            MyAnimationState.START -> 0.dp
            MyAnimationState.MID -> 80.dp
            MyAnimationState.END -> (-80).dp
        }
    }
    FloatingActionButton(
        backgroundColor = animatedColor,
        modifier = Modifier.offset(x = position, y = position),
        onClick = {
            animationState = when (animationState) {
                MyAnimationState.START -> MyAnimationState.MID
                MyAnimationState.MID -> MyAnimationState.END
                MyAnimationState.END -> MyAnimationState.START
            }
        }) {
        Icon(imageVector = Icons.Default.PlayCircleFilled, contentDescription = null)
    }
}

@Composable
fun MultiStateInfiniteTransition() {
    SubtitleText(subtitle = "Multi State infinite transition")
    Spacer(modifier = Modifier.height(80.dp))
    val startColor = green200
    val endColor = orange

    val transition = rememberInfiniteTransition()

    val animatedColor by transition.animateColor(
        initialValue = startColor,
        targetValue = endColor,
        animationSpec = infiniteRepeatable(tween(800), RepeatMode.Reverse)
    )

    val position by transition.animateFloat(
        initialValue = -80f,
        targetValue = 80f,
        animationSpec = infiniteRepeatable(tween(800), RepeatMode.Reverse)
    )

    FloatingActionButton(
        backgroundColor = animatedColor,
        modifier = Modifier.offset(x = position.dp),
        onClick = {
        }) {
        Icon(imageVector = Icons.Default.PlayCircleFilled, contentDescription = null)
    }
}


@Composable
fun FloatMultiStateAnimationCircleStrokeCanvas() {
    SubtitleText(subtitle = "Canvas Circle stroke with transition")
    val transition = rememberInfiniteTransition()

    val animatedFloat by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(800), RepeatMode.Restart)
    )

    val stroke = Stroke(8f)
    Canvas(
        modifier = Modifier
            .padding(16.dp)
            .size(100.dp)
    ) {
        val diameter = size.minDimension
        val radius = diameter / 2f
        val insideRadius = radius - stroke.width
        val topLeftOffset = Offset(10f, 10f)
        val size = Size(insideRadius * 2, insideRadius * 2)
        var rotationAngle = animatedFloat - 50f
        drawArc(
            color = green500,
            startAngle = rotationAngle,
            sweepAngle = 150f,
            topLeft = topLeftOffset,
            size = size,
            useCenter = false,
            style = stroke,
        )
        rotationAngle += 40
    }
}

@Preview
@Composable
fun FloatMultiStateAnimationCircleCanvas(color: Color = green500, radiusEnd: Float = 200f) {
    SubtitleText(subtitle = "Canvas drawing with infinite transition")
    Spacer(modifier = Modifier.height(100.dp))
    val transition = rememberInfiniteTransition()
    val floatAnim by transition.animateFloat(
        initialValue = 10f,
        targetValue = radiusEnd,
        animationSpec = infiniteRepeatable(tween(1200), RepeatMode.Reverse)
    )

    Canvas(modifier = Modifier.padding(16.dp)) {
        val centerOffset = Offset(
            10f,
            10f
        )
        val radius = floatAnim
        drawCircle(
            color = color.copy(alpha = 0.8f),
            radius = radius,
            center = centerOffset,
        )
        drawCircle(
            color = color.copy(alpha = 0.4f),
            radius = radius / 2,
            center = centerOffset,
        )
        drawCircle(
            color = color.copy(alpha = 0.2f),
            radius = radius / 4,
            center = centerOffset,
        )
    }
    Spacer(modifier = Modifier.height(100.dp))
}
