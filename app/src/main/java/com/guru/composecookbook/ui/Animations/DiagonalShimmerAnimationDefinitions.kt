package com.guru.composecookbook.ui.Animations

import androidx.compose.animation.core.*

class DiagonalShimmerAnimationDefinitions(
    private val widthPx: Float,
    private val heightPx: Float,
    private val animationDuration: Int = 1300,
    private val animationDelay: Int = 300
) {
    var gradientWidth: Float = (0.2f * heightPx)

    enum class AnimationState {
        START, END
    }

    val xShimmerPropKey = FloatPropKey("xShimmer")
    val yShimmerPropKey = FloatPropKey("yShimmer")

    val shimmerTranslateAnimation = transitionDefinition<AnimationState> {
        state(AnimationState.START) {
            this[xShimmerPropKey] = 0f
            this[yShimmerPropKey] = 0f
        }
        state(AnimationState.END) {
            this[xShimmerPropKey] =  widthPx + gradientWidth
            this[yShimmerPropKey] = heightPx + gradientWidth
        }

        transition(AnimationState.START, AnimationState.END) {
            xShimmerPropKey using infiniteRepeatable(
                animation = tween(
                    durationMillis = animationDuration,
                    easing = LinearEasing,
                    delayMillis = animationDelay
                ),
                repeatMode = RepeatMode.Restart
            )
            yShimmerPropKey using infiniteRepeatable(
                animation = tween(
                    durationMillis = animationDuration,
                    easing = LinearEasing,
                    delayMillis = animationDelay
                ),
                repeatMode = RepeatMode.Restart
            )
        }
    }


}