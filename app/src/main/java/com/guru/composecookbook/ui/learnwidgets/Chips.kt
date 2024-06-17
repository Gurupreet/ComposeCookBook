package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.R
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.utils.SubtitleText
import com.guru.composecookbook.youtube.components.YoutubeChip

/**
 * Composable function demonstrating custom chips and chip-like buttons.
 */
@Composable
fun Chips() {
    // There is no in-built chips but you can make yours like below
    Text(text = "Custom Chips", style = typography.h6, modifier = Modifier.padding(8.dp))

    SubtitleText(subtitle = "Custom chips with surface")

    // Row displaying custom chips with different configurations
    Row(modifier = Modifier.padding(8.dp)) {
        YoutubeChip(selected = true, text = "Chip", modifier = Modifier.padding(horizontal = 8.dp))
        YoutubeChip(
            selected = false,
            text = "Inactive",
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        CustomImageChip(text = "custom", imageId = com.guru.composecookbook.data.R.drawable.p2, selected = true)
        Spacer(modifier = Modifier.padding(8.dp))
        CustomImageChip(text = "custom2", imageId = com.guru.composecookbook.data.R.drawable.p6, selected = false)
    }

    SubtitleText(subtitle = "Buttons with circle clipping.")

    // Row displaying chip-like buttons with circular clipping
    Row(modifier = Modifier.padding(8.dp)) {
        Button(
            onClick = {},
            modifier = Modifier
                .padding(8.dp)
                .clip(CircleShape)
        ) {
            Text(text = "Chip button")
        }
        Button(
            onClick = {},
            enabled = false,
            modifier = Modifier
                .padding(8.dp)
                .clip(CircleShape)
        ) {
            Text(text = "Disabled chip")
        }
    }
}


/**
 * Private composable function representing a custom image chip.
 *
 * Inspired from jetcaster sample. I hope compose can add simple Chip UI element that can
 * support images or icons with multiple states.
 *
 * @param text The text content of the chip.
 * @param imageId The resource ID of the image/icon to be displayed in the chip.
 * @param selected The selected state of the chip.
 * @param modifier Modifier for styling and layout customization.
 */

@Composable
private fun CustomImageChip(
    text: String,
    imageId: Int,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        color = when {
            selected -> MaterialTheme.colorScheme.primary
            else -> Color.Transparent
        },
        contentColor = when {
            selected -> MaterialTheme.colorScheme.onPrimary
            else -> Color.LightGray
        },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 1.dp,
            color = when {
                selected -> MaterialTheme.colorScheme.primary
                else -> Color.LightGray
            }
        ),
        modifier = modifier
    ) {
        Row(modifier = Modifier) {

            // Image/icon displayed in the chip, clipped with CircleShape
            Image(
                painter = painterResource(imageId),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(20.dp)
                    .clip(CircleShape)
            )

            // Text content of the chip
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
            )
        }
    }
}