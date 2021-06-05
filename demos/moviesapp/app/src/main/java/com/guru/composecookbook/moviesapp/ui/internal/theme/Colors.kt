package com.guru.composecookbook.moviesapp.ui.internal.theme

import androidx.compose.ui.graphics.Color
import com.guru.composecookbook.theme.graySurface

object Colors {
    fun moviesSurfaceGradient(isDark: Boolean) =
        if (isDark) listOf(graySurface, Color.Black) else listOf(Color.White, Color.LightGray)
}