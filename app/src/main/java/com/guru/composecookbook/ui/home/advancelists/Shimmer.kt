package com.guru.composecookbook.ui.home.advancelists

import androidx.compose.animation.transition
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.ui.animation.AnimationDefinitions

enum class ShimmerAnimationType {
    FADE, TRANSLATE, FADETRANSLATE, VERTICAL
}

@Preview
@Composable
fun ShimmerList() {
    var shimmerAnimationType by remember { mutableStateOf(ShimmerAnimationType.FADE) }
    var colorState by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
    var colorFinalState by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }
    val dpStartState by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
    val dpEndState by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }

    val shimmerTranslateAnim = transition(
        definition = AnimationDefinitions.shimmerTranslateAnimation,
        initState = dpStartState,
        toState = dpEndState
    )

    val shimmerColorAnim = transition(
        definition = AnimationDefinitions.shimmerColorAnimation,
        initState = colorState,
        toState = colorFinalState,
        onStateChangeFinished = {
            when (it) {
                AnimationDefinitions.AnimationState.START -> {
                    colorState = AnimationDefinitions.AnimationState.START
                    colorFinalState = AnimationDefinitions.AnimationState.MID
                }
                AnimationDefinitions.AnimationState.MID -> {
                    colorState = AnimationDefinitions.AnimationState.MID
                    colorFinalState = AnimationDefinitions.AnimationState.END
                }
                AnimationDefinitions.AnimationState.END -> {
                    colorState = AnimationDefinitions.AnimationState.END
                    colorFinalState = AnimationDefinitions.AnimationState.START
                }
            }
        }
    )

    val list = if (shimmerAnimationType != ShimmerAnimationType.TRANSLATE) {
        listOf(
            shimmerColorAnim[AnimationDefinitions.shimmerColorPropKey],
            shimmerColorAnim[AnimationDefinitions.shimmerColorPropKey].copy(alpha = 0.5f)
        )
    } else {
        listOf(Color.LightGray.copy(alpha = 0.6f), Color.LightGray)
    }

    val dpValue = if (shimmerAnimationType != ShimmerAnimationType.FADE) {
        shimmerTranslateAnim[AnimationDefinitions.shimmerDpPropKey]
    } else {
        2000.dp
    }
    val buttonColors = ButtonDefaults.buttonColors(
        backgroundColor = if (shimmerAnimationType == ShimmerAnimationType.FADE)
            MaterialTheme.colors.primary else Color.LightGray
    )
    ScrollableColumn(modifier = Modifier.fillMaxSize()) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Button(
                onClick = { shimmerAnimationType = ShimmerAnimationType.FADE },
                colors = buttonColors,
                modifier = Modifier.preferredWidth(200.dp).padding(8.dp)
            ) {
                Text(text = "Fading")
            }
            Button(
                onClick = { shimmerAnimationType = ShimmerAnimationType.TRANSLATE },
                colors = buttonColors,
                modifier = Modifier.preferredWidth(200.dp).padding(8.dp)
            ) {
                Text(text = "Translating")
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Button(
                onClick = { shimmerAnimationType = ShimmerAnimationType.FADETRANSLATE },
                colors = buttonColors,
                modifier = Modifier.preferredWidth(200.dp).padding(8.dp)
            ) {
                Text(text = "Fade+Translate")
            }
            Button(
                onClick = { shimmerAnimationType = ShimmerAnimationType.VERTICAL },
                colors = buttonColors,
                modifier = Modifier.preferredWidth(200.dp).padding(8.dp)
            ) {
                Text(text = "Vertical")
            }
        }

        ShimmerItem(list, dpValue.value, shimmerAnimationType == ShimmerAnimationType.VERTICAL)
        ShimmerItemBig(list, dpValue.value, shimmerAnimationType == ShimmerAnimationType.VERTICAL)
        ShimmerItem(list, dpValue.value, shimmerAnimationType == ShimmerAnimationType.VERTICAL)
        ShimmerItem(list, dpValue.value, shimmerAnimationType == ShimmerAnimationType.VERTICAL)
    }
}

@Composable
fun ShimmerItem(lists: List<Color>, floatAnim: Float = 0f, isVertical: Boolean) {
    val brush = if (isVertical) Brush.verticalGradient(lists, 0f, floatAnim) else
        Brush.horizontalGradient(lists, 0f, floatAnim)
    Row(modifier = Modifier.padding(16.dp)) {
        Spacer(
            modifier = Modifier.preferredSize(100.dp)
                .background(brush = brush)
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .preferredHeight(30.dp)
                    .padding(8.dp).background(brush = brush)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .preferredHeight(30.dp)
                    .padding(8.dp)
                    .background(brush = brush)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .preferredHeight(30.dp)
                    .padding(8.dp)
                    .background(brush = brush)
            )
        }
    }
}

@Composable
fun ShimmerItemBig(lists: List<Color>, floatAnim: Float = 0f, isVertical: Boolean) {
    val brush = if (isVertical) Brush.verticalGradient(lists, 0f, floatAnim) else
        Brush.horizontalGradient(lists, 0f, floatAnim)
    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(
            modifier = Modifier.fillMaxWidth().preferredSize(200.dp).background(
                brush = brush
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .preferredHeight(30.dp)
                .padding(vertical = 8.dp)
                .background(brush = brush)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .preferredHeight(30.dp)
                .padding(vertical = 8.dp)
                .background(brush = brush)
        )
    }
}