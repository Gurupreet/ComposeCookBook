package com.guru.composecookbook.tags

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.green200
import com.guru.composecookbook.theme.green700
import com.guru.composecookbook.theme.typography

@Stable
interface TagColors {
    @Composable
    fun backgroundColor(enabled: Boolean): State<Color>

    @Composable
    fun contentColor(enabled: Boolean): State<Color>
}

@Immutable
private class DefaultTagColors(
    private val backgroundColor: Color,
    private val contentColor: Color
) : TagColors {
    @Composable
    override fun backgroundColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(newValue = backgroundColor)
    }

    @Composable
    override fun contentColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(newValue = contentColor)
    }
}

object TagDefaults {
    @Composable
    fun tagColors(
        backgroundColor: Color = green200.copy(alpha = .2f),
        contentColor: Color = green700
    ): TagColors = DefaultTagColors(backgroundColor = backgroundColor, contentColor = contentColor)
}

@Composable
fun InterestTag(
    text: String,
    modifier: Modifier = Modifier,
    colors: TagColors = TagDefaults.tagColors(),
    shape: Shape = RoundedCornerShape(4.dp),
    style: TextStyle = typography.body2.copy(fontWeight = FontWeight.Bold),
    onClick: () -> Unit = {}
) {
    val tagModifier = modifier
        .padding(4.dp)
        .clickable(onClick = onClick)
        .clip(shape = shape)
        .background(colors.backgroundColor(enabled = true).value)
        .padding(horizontal = 8.dp, vertical = 4.dp)
    Text(
        text = text,
        color = colors.contentColor(enabled = true).value,
        modifier = tagModifier,
        style = style
    )
}
