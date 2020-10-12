package com.guru.composecookbook.ui.Animations

import androidx.compose.animation.ColorPropKey
import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.*
import androidx.compose.animation.core.AnimationConstants.Infinite
import androidx.compose.ui.graphics.Color
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
                    durationMillis = 1000,
                    easing = LinearEasing
                )
            )
        }
    }
}