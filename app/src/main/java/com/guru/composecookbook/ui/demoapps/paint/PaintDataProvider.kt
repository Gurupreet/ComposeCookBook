package com.guru.composecookbook.ui.demoapps.paint

import androidx.compose.ui.graphics.Color

object PaintDataProvider {
    val brushColors = listOf(
        Color.Black,
        Color.LightGray,
        Color.Gray,
        Color.Red,
        Color.Yellow,
        Color.Blue,
        Color.Magenta,
        Color.Green,
        Color.Cyan
    )

    val strokeList = (1..40 step 5).toList()
}