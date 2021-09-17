package com.guru.composecookbook.lottie

import android.animation.ValueAnimator
import android.content.Context
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation


@Composable
fun LottieLoadingView(
    context: Context,
    file: String,
    modifier: Modifier = Modifier,
    iterations: Int = 10
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset(file))
    LottieAnimation(composition, modifier = modifier.defaultMinSize(300.dp), iterations = iterations)

    // OLD ANDROID VIEW IMPLEMENTATION
//    val lottieView = remember {
//        LottieAnimationView(context).apply {
//            setAnimation(file)
//            repeatCount = ValueAnimator.INFINITE
//        }
//    }
//    AndroidView(
//        { lottieView },
//        modifier = modifier
//    ) {
//        it.playAnimation()
//    }


}
