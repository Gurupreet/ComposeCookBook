package com.guru.composecookbook.lottie

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LottieCryptoLoadingView(context: Context) {
    LottieLoadingView(
        context = context,
        file = "cryptoload.json",
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    )
}
