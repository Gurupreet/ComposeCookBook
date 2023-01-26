package com.guru.composecookbook.meditation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun MeditationAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    MaterialTheme(
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}