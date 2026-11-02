package com.guru.composecookbook.cascademenu.cascade

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class CascadeMenuColors(val backgroundColor: Color, val contentColor: Color)

@Composable
fun cascadeMenuColors(
  backgroundColor: Color = MaterialTheme.colorScheme.surface,
  contentColor: Color = MaterialTheme.colorScheme.onSurface,
): CascadeMenuColors {
  return CascadeMenuColors(backgroundColor, contentColor)
}
