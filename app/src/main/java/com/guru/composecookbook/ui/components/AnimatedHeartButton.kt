package com.guru.composecookbook.ui.components

import androidx.compose.animation.ColorPropKey
import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.TransitionState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.R
import com.guru.composecookbook.ui.components.HeartButtonState.ACTIVE
import com.guru.composecookbook.ui.components.HeartButtonState.IDLE
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.ExperimentalCoroutinesApi


enum class HeartButtonState {
    IDLE, ACTIVE
}

val color = ColorPropKey()
val size = DpPropKey()

@ExperimentalCoroutinesApi
@Composable
fun AnimatedHeartButton(
    modifier: Modifier,
    buttonState: MutableState<HeartButtonState>,
    onToggle: () -> Unit,
    iconSize: Dp,
    expandIconSize: Dp,
) {

    val transitionDefinition = transitionDefinition<HeartButtonState> {
        state(IDLE) {
            this[color] = Color.LightGray
            this[size] = iconSize
        }

        state(ACTIVE) {
            this[color] = Color.Red
            this[size] = iconSize
        }

        transition(IDLE to ACTIVE) {
            color using tween(durationMillis = 500)

            size using keyframes {
                durationMillis = 500
                expandIconSize at 100
                iconSize at 200
            }
        }

        transition(ACTIVE to IDLE) {
            color using tween(durationMillis = 500)

            size using keyframes {
                durationMillis = 500
                expandIconSize at 100
                iconSize at 200
            }
        }
    }

    val toState = if (buttonState.value == IDLE) {
        ACTIVE
    } else {
        IDLE
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

@ExperimentalCoroutinesApi
@Composable
private fun HeartButton(
    modifier: Modifier,
    buttonState: MutableState<HeartButtonState>,
    state: TransitionState,
    onToggle: () -> Unit,
) {
    if (buttonState.value == ACTIVE) {
        CoilImage(
            R.drawable.heart_red,
            modifier = modifier
                .clickable(
                    onClick = onToggle,
                    indication = null,
                )
                .width(state[size])
                .height(state[size])
            ,
        )
    } else {
        CoilImage(
            R.drawable.heart_grey,
            modifier = modifier
                .clickable(
                    onClick = onToggle,
                    indication = null,
                )
                .width(state[size])
                .height(state[size]),
        )

    }
}



@ExperimentalCoroutinesApi
@Preview
@Composable
fun AnimatedHeartBtn(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        horizontalArrangement = Arrangement.Center
    ){
        val state = remember{ mutableStateOf(IDLE)}
        AnimatedHeartButton(
            modifier = Modifier.align(Alignment.CenterVertically),
            buttonState = state,
            onToggle = {
                state.value = if(state.value == IDLE) ACTIVE else IDLE
            },
            iconSize = 50.dp,
            expandIconSize = 80.dp
        )
    }

}























