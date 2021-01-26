package com.guru.composecookbook.ui.components

import androidx.compose.animation.transition
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.ui.Animations.DiagonalShimmerAnimationDefinitions
import com.guru.composecookbook.ui.Animations.DiagonalShimmerAnimationDefinitions.*


@Composable
fun DiagonalCardShimmer(
    imageHeight: Dp,
    padding: Dp = 16.dp
){
    WithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val cardWidthPx = with(AmbientDensity.current) { (maxWidth - (padding*2)).toPx() }
        val cardHeightPx = with(AmbientDensity.current) { (imageHeight - padding).toPx() }

        val cardAnimationDefinition = remember{
            DiagonalShimmerAnimationDefinitions(
                widthPx = cardWidthPx,
                heightPx = cardHeightPx,
            )
        }

        val cardShimmerTranslateAnim = transition(
            definition = cardAnimationDefinition.shimmerTranslateAnimation,
            initState = AnimationState.START,
            toState = AnimationState.END
        )

        val colors = listOf(
            Color.LightGray.copy(alpha = .9f),
            Color.LightGray.copy(alpha = .3f),
            Color.LightGray.copy(alpha = .9f),
        )
        val xCardShimmer = cardShimmerTranslateAnim[cardAnimationDefinition.xShimmerPropKey]
        val yCardShimmer = cardShimmerTranslateAnim[cardAnimationDefinition.yShimmerPropKey]

        ScrollableColumn {
            repeat(5) {
                ShimmerCardItem(
                    colors = colors,
                    xShimmer = xCardShimmer,
                    yShimmer = yCardShimmer,
                    cardHeight = imageHeight,
                    gradientWidth = cardAnimationDefinition.gradientWidth,
                    padding = padding
                )
            }
        }
    }

}

@Composable
fun ShimmerCardItem(
    colors: List<Color>,
    xShimmer: Float,
    yShimmer: Float,
    cardHeight: Dp,
    gradientWidth: Float,
    padding: Dp
) {
    val brush = Brush.linearGradient(
        colors,
        start = Offset(xShimmer - gradientWidth, yShimmer - gradientWidth),
        end = Offset(xShimmer, yShimmer)
    )
    Column(modifier = Modifier.padding(padding)) {
        Surface(
            shape = MaterialTheme.shapes.small,
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .preferredSize(cardHeight)
                    .background(brush = brush)
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
                    .preferredHeight(cardHeight / 10)
                    .background(brush = brush)
            )
        }
    }

}