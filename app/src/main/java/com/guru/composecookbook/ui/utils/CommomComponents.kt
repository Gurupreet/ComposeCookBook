package com.guru.composecookbook.ui.utils

import androidx.compose.animation.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.theme.typography

@Composable
fun ComingSoon() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(50.dp)
    ) {
        Text(
            text = "Coming Soon",
            style = typography.h5,
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = "work in progress",
            style = typography.subtitle2,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
fun HeadingSection(modifier: Modifier = Modifier, title: String = "", subtitle: String = "") {
    Column(modifier = modifier.fillMaxWidth().padding(16.dp)) {
        if (title.isNotEmpty()) {
            Text(text = title, style = typography.h6.copy(fontSize = 14.sp))
        }
        if (title.isNotEmpty()) {
            Text(text = subtitle, style = typography.subtitle2)
        }
        Divider()
    }
}

@Composable
fun TitleText(modifier: Modifier = Modifier, title: String) {
    Text(
        text = title,
        style = typography.h6.copy(fontSize = 14.sp),
        modifier = modifier.padding(8.dp)
    )
}

@Composable
fun SubtitleText(subtitle: String, modifier: Modifier = Modifier) {
    Text(text = subtitle, style = typography.subtitle2, modifier = modifier.padding(8.dp))
}

@Composable
fun RotateIcon(
    state: Boolean,
    asset: ImageVector,
    angle: Float,
    duration: Int,
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = asset,
        modifier = modifier
            .graphicsLayer(rotationZ = animate(if (state) 0f else angle, tween(duration)))
    )
}

@Preview
@Composable
fun PreviewHeading() {
    HeadingSection(title = "Title", subtitle = "this is subtitle")
}