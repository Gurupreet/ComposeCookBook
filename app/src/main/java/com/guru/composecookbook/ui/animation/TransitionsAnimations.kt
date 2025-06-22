package com.guru.composecookbook.ui.animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.canvas.MultiStateAnimationCircleFilledCanvas
import com.guru.composecookbook.canvas.MultiStateAnimationCircleStrokeCanvas
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

    val transition = updateTransition(targetState = animationState, label = "transition")

    val animatedColor by transition.animateColor(
        transitionSpec = { tween(500) },
        label = "animatedColor",
    ) { state ->
        when (state) {
            MyAnimationState.START -> startColor
            MyAnimationState.MID -> midColor
            MyAnimationState.END -> endColor
        }
    }

    val position by transition.animateDp(
        transitionSpec = { tween(500) },
        label = "position",
    ) { state ->
        when (state) {
            MyAnimationState.START -> 0.dp
            MyAnimationState.MID -> 80.dp
            MyAnimationState.END -> (-80).dp
        }
    }
    FloatingActionButton(
        containerColor = animatedColor,
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
        containerColor = animatedColor,
        modifier = Modifier.offset(x = position.dp),
        onClick = {
        }) {
        Icon(imageVector = Icons.Default.PlayCircleFilled, contentDescription = null)
    }
}


@Composable
fun FloatMultiStateAnimationCircleStrokeCanvas() {
    SubtitleText(subtitle = "Canvas Circle stroke with transition")
    MultiStateAnimationCircleStrokeCanvas()
}

@Preview
@Composable
fun FloatMultiStateAnimationCircleCanvas(color: Color = green500, radiusEnd: Float = 200f) {
    SubtitleText(subtitle = "Canvas drawing with infinite transition")
    Spacer(modifier = Modifier.height(100.dp))
    MultiStateAnimationCircleFilledCanvas(color, radiusEnd)
    Spacer(modifier = Modifier.height(100.dp))
}