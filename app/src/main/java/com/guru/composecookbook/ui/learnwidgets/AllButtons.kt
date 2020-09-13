package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.HorizontalGradient
import androidx.compose.ui.graphics.VerticalGradient
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.theme.green200
import com.guru.composecookbook.theme.green500
import com.guru.composecookbook.theme.green700
import com.guru.composecookbook.theme.typography

@Composable
fun AllButtons() {
    Text(text = "Buttons", style = typography.h5, modifier = Modifier.padding(8.dp))

    Row {
        Button(onClick = {}, modifier = Modifier.padding(8.dp)) {
            Text(text = "Main Button")
        }
        TextButton(onClick = {}, modifier = Modifier.padding(8.dp)) {
            Text(text = "Text Button")
        }
        TextButton(onClick = {}, modifier = Modifier.padding(8.dp), enabled = false) {
            Text(text = "Text Disabled")
        }
    }
    Row {
        Button(onClick = {}, modifier = Modifier.padding(8.dp), enabled = false) {
            Text(text = "Disabled")
        }
        Button(onClick = {}, modifier = Modifier.padding(8.dp), elevation = 0.dp) {
            Text(text = "Flat")
        }
        Button(
            onClick = {},
            modifier = Modifier.padding(8.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Rounded")
        }
    }
    Row {
        OutlinedButton(onClick = {}, modifier = Modifier.padding(8.dp)) {
            Text(text = "Outline")
        }
        Button(onClick = {}, modifier = Modifier.padding(8.dp)) {
            Row {
                Icon(asset = Icons.Default.FavoriteBorder, modifier = Modifier.padding(end = 4.dp))
                Text(text = "Icon Button")
            }
        }
        Button(onClick = {}, modifier = Modifier.padding(8.dp)) {
            Text(text = "Icon Button")
            Icon(asset = Icons.Default.FavoriteBorder, modifier = Modifier.padding(start = 4.dp))
        }
    }
    Row {
        val horizontalGradient = HorizontalGradient(
            colors = listOf(green200, green500, green700),
            0f,
            250f
        )
        val verticalGradient = VerticalGradient(
            colors = listOf(green200, green500, green700),
            startY = 0f,
            endY = 100f
        )
        Text(
            text = "Horizontal gradient",
            style = typography.body2.copy(color = Color.White),
            modifier = Modifier.padding(12.dp).clickable(onClick = {})
                .clip(RoundedCornerShape(16.dp))
                .background(brush = horizontalGradient).padding(12.dp)
        )
        Text(
            text = "Vertical gradient",
            style = typography.body1.copy(color = Color.White),
            modifier = Modifier.padding(12.dp).clickable(onClick = {})
                .clip(RoundedCornerShape(16.dp))
                .background(brush = verticalGradient).padding(12.dp)
        )
    }
}

@Preview
@Composable
fun PreviewButtons() {
    Column {
        AllButtons()
    }

}