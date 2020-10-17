package com.guru.composecookbook.ui.advancelists

import android.util.Log
import androidx.compose.animation.transition
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ListItem
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.HorizontalGradient
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.ui.Animations.AnimationDefinitions
import com.guru.composecookbook.ui.utils.horizontalGradientBackground

enum class ShimmerAnimationType {
    FADE, TRANSLATE, FADETRANSLATE
}

@Preview
@Composable
fun ShimmerList(
    shimmerAnimationType: ShimmerAnimationType = ShimmerAnimationType.FADETRANSLATE,
) {
    var colorState by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
    var colorFinalState by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }
    val dpStartState by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
    val dpEndState by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }

    val dpAnim = transition(
        definition = AnimationDefinitions.shimmerDpAnimDefinition,
        initState = dpStartState,
        toState = dpEndState
    )

    val colorAnim = transition(
        definition = AnimationDefinitions.shimmerAnimDefinition,
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
            colorAnim[AnimationDefinitions.shimmerColorPropKey],
            colorAnim[AnimationDefinitions.shimmerColorPropKey].copy(alpha = 0.5f)
        )
    } else {
        listOf(Color.LightGray, Color.LightGray.copy(alpha = 0.8f))
    }

    val dpValue = if (shimmerAnimationType != ShimmerAnimationType.FADE) {
         dpAnim[AnimationDefinitions.shimmerDpPropKey]
    } else {
        2000.dp
    }

    Column {
        ShimmerItem(list, dpValue.value)
        ShimmerItemBig(list, dpValue.value)
        ShimmerItem(list, dpValue.value)
        ShimmerItem(list, dpValue.value)
    }
}

@Composable
fun ShimmerItem(lists: List<Color>, floatAnim: Float = 0f) {
   Row(modifier = Modifier.padding(16.dp)) {
       Spacer(modifier = Modifier.preferredSize(100.dp).background(brush = HorizontalGradient(lists, 0f, floatAnim)))
       Column(modifier = Modifier.padding(8.dp)) {
           Spacer(modifier = Modifier.fillMaxWidth().preferredHeight(30.dp).padding(8.dp).horizontalGradientBackground(lists))
           Spacer(modifier = Modifier.fillMaxWidth().preferredHeight(30.dp).padding(8.dp).horizontalGradientBackground(lists))
           Spacer(modifier = Modifier.fillMaxWidth().preferredHeight(30.dp).padding(8.dp).horizontalGradientBackground(lists))
       }
   }
}

@Composable
fun ShimmerItemBig(lists: List<Color>, floatAnim: Float = 0f) {
    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.fillMaxWidth().preferredSize(200.dp).background(brush = HorizontalGradient(
          lists, 0f,    floatAnim)))
        Spacer(modifier = Modifier.height(8.dp))
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .preferredHeight(30.dp)
            .padding(vertical = 8.dp)
            .background(brush = HorizontalGradient(lists, 0f,    floatAnim)))
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .preferredHeight(30.dp)
            .padding(vertical = 8.dp)
            .background(brush = HorizontalGradient(lists, 0f,    floatAnim)))
    }
}