package com.guru.composecookbook.ui.demoui.youtube

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun YoutubeChip(selected: Boolean, text: String) {
    Surface(
        color = when {
            selected -> MaterialTheme.colors.onSurface
            else -> Color.Transparent
        },
        contentColor = when {
            selected -> MaterialTheme.colors.onPrimary
            else -> Color.LightGray
        },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 1.dp,
            color = when {
                selected -> MaterialTheme.colors.primary
                else -> Color.LightGray
            }
        ),
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(8.dp)
        )

    }
}