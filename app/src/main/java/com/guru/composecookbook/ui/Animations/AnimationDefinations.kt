package com.guru.composecookbook.ui.Animations

import androidx.compose.animation.ColorPropKey
import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.*
import androidx.compose.animation.core.AnimationConstants.Infinite
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object AnimationDefinations {
    // Each animation should be explained as a definition and using states.
    enum class AnimationState {
        START, MID, END
    }

    //color animation
    val colorPropKey = ColorPropKey()
    val colorAnimDefinition = transitionDefinition<Int> {
        state(0) { this[colorPropKey] = Color.Green }
        state(1) { this[colorPropKey] = Color.Blue }
        state(2) { this[colorPropKey] = Color.Magenta }

        transition(0 to 1, 1 to 2, 2 to 0) {
            colorPropKey using tween<Color>(
                durationMillis = 1000,
                easing = FastOutSlowInEasing,

                )
        }
    }


    //float animation
    val floatPropKey = FloatPropKey()
    fun floatAnimDefinition(
        start: Float,
        end: Float,
        repeat: Boolean,
        duration: Int = 2000
    ) = transitionDefinition<Int> {
        state(AnimationState.START.ordinal) { this[floatPropKey] = start }
        state(AnimationState.END.ordinal) { this[floatPropKey] = end }

        transition(0, 2) {
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
    val dpPropKey = DpPropKey()
    val dpAnimDefinition = transitionDefinition<Int> {
        state(AnimationState.START.ordinal) { this[dpPropKey] = 0.dp }
        state(AnimationState.END.ordinal) { this[dpPropKey] = 100.dp }

        transition(0, 2) {
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