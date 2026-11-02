package com.guru.composecookbook.ui.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons

@Composable
fun HeadingSection(modifier: Modifier = Modifier, title: String = "", subtitle: String = "") {
  Column(modifier = modifier.fillMaxWidth().padding(16.dp)) {
    if (title.isNotEmpty()) {
      Text(
        text = title,
        style =
          MaterialTheme.typography.titleLarge.copy(
            // M2 h6 had no lineHeight; resetting it keeps the row height unchanged.
            fontSize = 14.sp,
            lineHeight = TextUnit.Unspecified,
          ),
      )
    }
    if (title.isNotEmpty()) {
      Text(
        text = subtitle,
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Normal),
        color = Color.Gray,
      )
    }
    HorizontalDivider()
  }
}

@Composable
fun TitleText(modifier: Modifier = Modifier, title: String) {
  Text(
    text = title,
    style =
      MaterialTheme.typography.titleLarge.copy(
        // M2 h6 had no lineHeight; resetting it keeps the row height unchanged.
        fontSize = 14.sp,
        lineHeight = TextUnit.Unspecified,
      ),
    modifier = modifier.padding(8.dp),
  )
}

@Composable
fun SubtitleText(subtitle: String, modifier: Modifier = Modifier) {
  Text(
    text = subtitle,
    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Normal),
    color = Color.Gray,
    modifier = modifier.padding(8.dp),
  )
}

@Composable
fun RotateIcon(
  state: Boolean,
  asset: ImageVector,
  angle: Float,
  duration: Int,
  modifier: Modifier = Modifier,
) {
  FaIcon(
    faIcon = FaIcons.Play,
    size = 20.dp,
    tint = LocalContentColor.current,
    modifier =
      modifier
        .padding(2.dp)
        .graphicsLayer(
          rotationZ = animateFloatAsState(if (state) 0f else angle, tween(duration)).value
        ),
  )
}

@Preview
@Composable
fun PreviewHeading() {
  HeadingSection(title = "Title", subtitle = "this is subtitle")
}
