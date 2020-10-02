package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.contentColor
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.EmphasisAmbient
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.R
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.demoui.youtube.YoutubeChip

@Composable
fun Chips() {
    // There is no in-built chips but you can make yours like below
    Text(text = "Custom Chips", style = typography.h6, modifier = Modifier.padding(8.dp))
    Row(modifier = Modifier.padding(8.dp)) {
        YoutubeChip(selected = true, text = "Chip")
        YoutubeChip(selected = false, text = "Inactive")
        CustomImageChip(text = "custom", imageId = R.drawable.p2, selected = true)
        Spacer(modifier = Modifier.padding(4.dp))
        CustomImageChip(text = "custom2", imageId = R.drawable.p6, selected = false)
    }
}


//Inspired from jetcaster sample. I hope compose can add simple Chip UI element that can
// support images or icons with multiple states.
@Composable
private fun CustomImageChip(
    text: String,
    imageId: Int,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        color = when {
            selected -> MaterialTheme.colors.primary
            else -> Color.Transparent
        },
        contentColor = when {
            selected -> MaterialTheme.colors.onPrimary
            else -> EmphasisAmbient.current.high.applyEmphasis(contentColor())
        },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 1.dp,
            color = when {
                selected -> MaterialTheme.colors.primary
                else -> EmphasisAmbient.current.disabled.applyEmphasis(contentColor())
            }
        ),
        modifier = modifier
    ) {
        Row(modifier = Modifier) {
            Image(
                asset = imageResource(imageId),
                modifier = Modifier.padding(8.dp).preferredSize(20.dp).clip(CircleShape)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
            )
        }
    }
}