package com.guru.composecookbook.ui.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.theme.typography
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons

/**
 * A Composable function that displays a heading section with a title, subtitle, and a divider.
 *
 * @param modifier Modifier to apply to the Column layout.
 * @param title The title text to display.
 * @param subtitle The subtitle text to display.
 */
@Composable
fun HeadingSection(modifier: Modifier = Modifier, title: String = "", subtitle: String = "") {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (title.isNotEmpty()) {
            Text(text = title, style = typography.h6.copy(fontSize = 14.sp))
        }
        if (title.isNotEmpty()) {
            Text(text = subtitle, style = typography.subtitle2)
        }
        Divider()
    }
}


/**
 * A Composable function that displays a title text with a specific style.
 *
 * @param modifier Modifier to apply to the Text component.
 * @param title The title text to display.
 */
@Composable
fun TitleText(modifier: Modifier = Modifier, title: String) {
    androidx.compose.material3.Text(
        text = title,
        style = typography.h6.copy(fontSize = 14.sp),
        modifier = modifier.padding(8.dp)
    )
}


/**
 * A Composable function that displays a subtitle text with a specific style.
 *
 * @param subtitle The subtitle text to display.
 * @param modifier Modifier to apply to the Text component.
 */
@Composable
fun SubtitleText(subtitle: String, modifier: Modifier = Modifier) {
    androidx.compose.material3.Text(text = subtitle, style = typography.subtitle2, modifier = modifier.padding(8.dp))
}


/**
 * A Composable function that displays a rotating icon.
 *
 * @param state A Boolean to control the rotation state of the icon.
 * @param asset The ImageVector asset to use as the icon.
 * @param angle The angle in degrees to rotate the icon.
 * @param duration The duration of the rotation animation in milliseconds.
 * @param modifier Modifier to apply to the icon.
 */
@Composable
fun RotateIcon(
    state: Boolean,
    asset: ImageVector,
    angle: Float,
    duration: Int,
    modifier: Modifier = Modifier
) {
    FaIcon(
        faIcon = FaIcons.Play,
        size = 20.dp,
        tint = LocalContentColor
            .current.copy(
                alpha =
                LocalContentAlpha.current
            ),
        modifier = modifier
            .padding(2.dp)
            .graphicsLayer(
                rotationZ = animateFloatAsState(if (state) 0f else angle, tween(duration))
                    .value
            )
    )
}


/**
 * A Composable function to preview the HeadingSection Composable.
 */
@Preview
@Composable
fun PreviewHeading() {
    HeadingSection(title = "Title", subtitle = "this is subtitle")
}