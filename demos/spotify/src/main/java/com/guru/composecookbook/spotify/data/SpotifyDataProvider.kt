package com.guru.composecookbook.spotify.data

import androidx.compose.ui.graphics.Color
import com.guru.composecookbook.theme.graySurface

object SpotifyDataProvider {
    fun spotifySurfaceGradient(isDark: Boolean) =
        if (isDark) listOf(graySurface, Color.Black) else listOf(Color.White, Color.LightGray)
}