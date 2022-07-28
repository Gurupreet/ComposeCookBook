//package com.guru.composecookbook.ui.home.advancelists
//
//TODO fix animation
//import androidx.compose.animation.transition
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//
//
///**
// * Design taken from:
// * https://github.com/Gurupreet/ComposeCookBook/blob/master/app/src/main/java/com/guru/composecookbook/ui/advancelists/Shimmer.kt
// */
//
//@Composable
//fun LoadingListShimmer() {
//
//    val dpStartState by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
//    val dpEndState by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }
//
//    val shimmerTranslateAnim = transition(
//        definition = AnimationDefinitions.shimmerTranslateAnimation,
//        initState = dpStartState,
//        toState = dpEndState
//    )
//
//    val shimmerColorAnim = transition(
//        definition = AnimationDefinitions.shimmerColorAnimation,
//        initState = AnimationDefinitions.AnimationState.START,
//        toState = AnimationDefinitions.AnimationState.END,
//    )
//
//    val list = listOf(
//        shimmerColorAnim[AnimationDefinitions.shimmerColorPropKey],
//        shimmerColorAnim[AnimationDefinitions.shimmerColorPropKey].copy(alpha = 0.5f)
//    )
//    val dpValue = shimmerTranslateAnim[AnimationDefinitions.shimmerDpPropKey]
//
//
//    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
//        ShimmerCardItem(list, dpValue.value, true)
//        ShimmerCardItem(list, dpValue.value, true)
//        ShimmerCardItem(list, dpValue.value, true)
//        ShimmerCardItem(list, dpValue.value, true)
//        ShimmerCardItem(list, dpValue.value, true)
//        ShimmerCardItem(list, dpValue.value, true)
//    }
//
//}
//
//@Composable
//fun ShimmerCardItem(
//    lists: List<Color>,
//    floatAnim: Float = 0f,
//    isVertical: Boolean
//) {
//
//    val brush = if (isVertical) Brush.verticalGradient(lists, 0f, floatAnim) else
//        Brush.horizontalGradient(lists, 0f, floatAnim)
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        Surface(
//            shape = MaterialTheme.shapes.small
//        ) {
//            Spacer(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .size(200.dp)
//                    .background(
//                        brush = brush
//                    )
//            )
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Surface(
//            shape = MaterialTheme.shapes.small,
//            modifier = Modifier
//                .padding(vertical = 8.dp)
//        ) {
//            Spacer(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(15.dp)
//                    .background(brush = brush)
//            )
//        }
//        Surface(
//            shape = MaterialTheme.shapes.small,
//            modifier = Modifier
//                .padding(vertical = 8.dp)
//        ) {
//            Spacer(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(15.dp)
//                    .background(brush = brush)
//            )
//        }
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
