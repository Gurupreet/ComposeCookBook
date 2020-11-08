package com.guru.composecookbook.ui.Animations

import androidx.compose.animation.ColorPropKey
import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.*
import androidx.compose.animation.core.AnimationConstants.Infinite
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object AnimationDefinitions {
    // Each animation should be explained as a definition and using states.
    enum class AnimationState {
        START, MID, END
    }

    //color animation
    val colorPropKey = ColorPropKey(label = "color")
    val colorAnimDefinition = transitionDefinition<AnimationState> {
        state(AnimationState.START) { this[colorPropKey] = Color.Green }
        state(AnimationState.MID) { this[colorPropKey] = Color.Blue }
        state(AnimationState.END) { this[colorPropKey] = Color.Magenta }

        transition(
            AnimationState.START to AnimationState.MID,
            AnimationState.MID to AnimationState.END,
            AnimationState.END to AnimationState.START
        ) {
            colorPropKey using tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing,
            )
        }
    }

    //float animation
    val floatPropKey = FloatPropKey("value")
    fun floatAnimDefinition(
        start: Float,
        end: Float,
        repeat: Boolean,
        duration: Int = 2000
    ) = transitionDefinition<AnimationState> {
        state(AnimationState.START) { this[floatPropKey] = start }
        state(AnimationState.END) { this[floatPropKey] = end }

        transition(AnimationState.START, AnimationState.END) {
            floatPropKey using repeatable(
                iterations = if (repeat) Infinite else 1,
                animation = tween(
                    durationMillis = duration,
                    easing = LinearEasing
                )
            )
        }
    }

    //dp animation
    val dpPropKey = DpPropKey("dp")
    val dpAnimDefinition = transitionDefinition<AnimationState> {
        state(AnimationState.START) { this[dpPropKey] = 0.dp }
        state(AnimationState.END) { this[dpPropKey] = 100.dp }

        transition(AnimationState.START, AnimationState.END) {
            dpPropKey using repeatable(
                iterations = Infinite,
                animation = tween(
                    durationMillis = 800,
                    easing = LinearEasing
                )
            )
        }
    }

    val shimmerColorPropKey = ColorPropKey(label = "shimmerColor")
    val shimmerColorAnimation = transitionDefinition<AnimationState> {
        state(AnimationState.START) {
            this[shimmerColorPropKey] = Color.LightGray.copy(alpha = 0.6f)
        }
        state(AnimationState.MID) { this[shimmerColorPropKey] = Color.LightGray.copy(alpha = 0.9f) }
        state(AnimationState.END) { this[shimmerColorPropKey] = Color.LightGray }
        transition(
            AnimationState.START to AnimationState.MID,
            AnimationState.MID to AnimationState.END,
            AnimationState.END to AnimationState.START
        ) {
            shimmerColorPropKey using tween(
                durationMillis = 600,
                easing = FastOutSlowInEasing,
            )
        }
    }

    val shimmerDpPropKey = DpPropKey("shimmerdp")
    val shimmerTranslateAnimation = transitionDefinition<AnimationState> {
        state(AnimationState.START) { this[shimmerDpPropKey] = 100.dp }
        state(AnimationState.END) { this[shimmerDpPropKey] = 2000.dp }

        transition(AnimationState.START, AnimationState.END) {
            shimmerDpPropKey using repeatable(
                iterations = Infinite,
                animation = tween(
                    durationMillis = 1200,
                    easing = LinearEasing
                )
            )
        }
    }

    val bounceDpPropKey = DpPropKey("bounce")
    fun bounceAnimationDefinition(start: Dp, mid: Dp, end: Dp) =
        transitionDefinition<AnimationState> {
            state(AnimationState.START) { this[bounceDpPropKey] = start }
            state(AnimationState.MID) { this[bounceDpPropKey] = mid }
            state(AnimationState.END) { this[bounceDpPropKey] = end }

            transition(
                AnimationState.START to AnimationState.MID,
                AnimationState.MID to AnimationState.END
            ) {
                bounceDpPropKey using SpringSpec(
                    dampingRatio = 300f,
                    stiffness = 0.4f,
                )
            }
        }

}