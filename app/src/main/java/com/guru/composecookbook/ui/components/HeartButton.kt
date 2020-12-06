package com.guru.composecookbook.ui.components

import androidx.compose.animation.ColorPropKey
import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.TransitionState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp


enum class HeartButtonState {
    IDLE, ACTIVE
}

val color = ColorPropKey()
val size = DpPropKey()

@Composable
fun AnimatedHeartButton(
    modifier: Modifier,
    buttonState: MutableState<HeartButtonState>,
    onToggle: () -> Unit,
    iconSize: Dp,
    expandIconSize: Dp,
) {

    val transitionDefinition = transitionDefinition<HeartButtonState> {
        state(HeartButtonState.IDLE) {
            this[color] = Color.LightGray
            this[size] = iconSize
        }

        state(HeartButtonState.ACTIVE) {
            this[color] = Color.Red
            this[size] = iconSize
        }

        transition(HeartButtonState.IDLE to HeartButtonState.ACTIVE) {
            color using tween(durationMillis = 500)

            size using keyframes {
                durationMillis = 500
                expandIconSize at 100
                iconSize at 200
            }
        }

        transition(HeartButtonState.ACTIVE to HeartButtonState.IDLE) {
            color using tween(durationMillis = 500)

            size using keyframes {
                durationMillis = 500
                expandIconSize at 100
                iconSize at 200
            }
        }
    }

    val toState = if (buttonState.value == HeartButtonState.IDLE) {
        HeartButtonState.ACTIVE
    } else {
        HeartButtonState.IDLE
    }

    val state = transition(
        definition = transitionDefinition,
        initState = buttonState.value,
        toState = toState
    )

    HeartButton(
        modifier = modifier,
        buttonState = buttonState,
        state = state,
        onToggle = onToggle
    )
}

@Composable
fun HeartButton(
    modifier: Modifier,
    buttonState: MutableState<HeartButtonState>,
    state: TransitionState,
    onToggle: () -> Unit,
) {
    if (buttonState.value == HeartButtonState.ACTIVE) {
        Icon(
            imageVector = Icons.Default.Favorite.copy(
                defaultHeight = state[size],
                defaultWidth = state[size],
            ),
            modifier = modifier
                .clickable(
                    onClick = onToggle
                ),
            tint = state[color]
        )
    } else {
        Icon(
            imageVector = Icons.Default.Favorite.copy(
                defaultHeight = state[size],
                defaultWidth = state[size],
            ),
            modifier = modifier
                .clickable(
                    onClick = onToggle
                ),
            tint = state[color]
        )
    }
}




























