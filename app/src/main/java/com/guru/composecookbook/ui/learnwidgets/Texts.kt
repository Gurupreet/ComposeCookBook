package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.utils.SubtitleText

@Composable
fun TextDemo() {
    Text(text = "Text Views", style = typography.h5, modifier = Modifier.padding(8.dp))

    SubtitleText(subtitle = "font weights")
    val textModifier = Modifier.padding(horizontal = 8.dp)
    Row {
        Text(text = "Plain", modifier = textModifier)
        Text(
            text = "Medium Bold",
            style = typography.body1.copy(fontWeight = FontWeight.Medium),
            modifier = textModifier
        )
        Text(
            text = "Bold",
            style = typography.body1.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = "Extra Bold",
            style = typography.body1.copy(fontWeight = FontWeight.Bold)
        )
    }

    SubtitleText(subtitle = "font family dynamic")
    Row {
        Text(text = "Default", modifier = textModifier)
        Text(
            text = "Cursive",
            style = typography.body1.copy(fontFamily = FontFamily.Cursive),
            modifier = textModifier
        )
        Text(
            text = "SansSerif",
            style = typography.body1.copy(fontFamily = FontFamily.SansSerif),
            modifier = textModifier
        )
        Text(
            text = "Monospace",
            style = typography.body1.copy(fontFamily = FontFamily.Monospace),
            modifier = textModifier
        )
    }

    SubtitleText(subtitle = "Text decorations")
    Row {
        Text(text = "Default", modifier = textModifier)
        Text(
            text = "Underline",
            textDecoration = TextDecoration.Underline,
            modifier = textModifier
        )
        Text(
            text = "LineThrough",
            textDecoration = TextDecoration.LineThrough,
            modifier = textModifier
        )
        Text(
            text = "Monospace",
            textDecoration = TextDecoration.combine(
                listOf(
                    TextDecoration.Underline,
                    TextDecoration.LineThrough
                )
            ),
            modifier = textModifier
        )
    }

    SubtitleText(subtitle = "Overflow")
    Text(
        text = "Ellipsis: This text is supposed to ellipsis with max 1 line allowed for this",
        overflow = TextOverflow.Ellipsis,
        modifier = textModifier,
        maxLines = 1
    )
    Text(
        text = "Clip: This text is supposed to clip with max 1 line allowed for this",
        overflow = TextOverflow.Clip,
        modifier = textModifier,
        maxLines = 1
    )


}

@Preview
@Composable
fun PreviewTextDemo() {
    Column {
        TextDemo()
    }
}