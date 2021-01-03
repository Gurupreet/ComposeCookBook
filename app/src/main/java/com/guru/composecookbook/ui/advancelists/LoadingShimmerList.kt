package com.guru.composecookbook.ui.advancelists

import androidx.compose.animation.transition
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.HorizontalGradient
import androidx.compose.ui.graphics.VerticalGradient
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.ui.Animations.AnimationDefinitions


/**
 * Design taken from:
 * https://github.com/Gurupreet/ComposeCookBook/blob/master/app/src/main/java/com/guru/composecookbook/ui/advancelists/Shimmer.kt
 */

@Composable
fun LoadingListShimmer() {

    val dpStartState by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
    val dpEndState by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }

    val shimmerTranslateAnim = transition(
        definition = AnimationDefinitions.shimmerTranslateAnimation,
        initState = dpStartState,
        toState = dpEndState
    )

    val shimmerColorAnim = transition(
        definition = AnimationDefinitions.shimmerColorAnimation,
        initState = AnimationDefinitions.AnimationState.START,
        toState = AnimationDefinitions.AnimationState.END,
    )

    val list = listOf(
        shimmerColorAnim[AnimationDefinitions.shimmerColorPropKey],
        shimmerColorAnim[AnimationDefinitions.shimmerColorPropKey].copy(alpha = 0.5f)
    )
    val dpValue = shimmerTranslateAnim[AnimationDefinitions.shimmerDpPropKey]


    ScrollableColumn() {
        ShimmerCardItem(list, dpValue.value, true)
        ShimmerCardItem(list, dpValue.value, true)
        ShimmerCardItem(list, dpValue.value, true)
        ShimmerCardItem(list, dpValue.value, true)
        ShimmerCardItem(list, dpValue.value, true)
        ShimmerCardItem(list, dpValue.value, true)
    }

}

@Composable
fun ShimmerCardItem(
    lists: List<Color>,
    floatAnim: Float = 0f,
    isVertical: Boolean
) {

    val brush = if (isVertical) VerticalGradient(lists, 0f, floatAnim) else
        HorizontalGradient(lists, 0f, floatAnim)

    Column(modifier = Modifier.padding(16.dp)) {
        Surface(
            shape = MaterialTheme.shapes.small
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .preferredSize(200.dp)
                    .background(
                        brush = brush
                    )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Surface(
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .padding(vertical = 8.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .preferredHeight(15.dp)
                    .background(brush = brush)
            )
        }
        Surface(
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .padding(vertical = 8.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .preferredHeight(15.dp)
                    .background(brush = brush)
            )
        }
    }
}
















