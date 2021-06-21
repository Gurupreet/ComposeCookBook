package com.guru.composecookbook.lottie

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LottieFoodView(context: Context) {
    LottieLoadingView(
        context = context,
        file = "food.json",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}
