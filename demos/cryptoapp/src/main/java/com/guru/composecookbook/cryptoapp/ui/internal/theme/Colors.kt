package com.guru.composecookbook.cryptoapp.ui.internal.theme

import androidx.compose.ui.graphics.Color
import com.guru.composecookbook.theme.graySurface

object Colors {
    fun cryptoSurfaceGradient(isDark: Boolean) =
        if (isDark) listOf(graySurface, Color.Black) else listOf(Color.White, Color.LightGray)
}