package com.guru.composecookbook.datingapp.components.chat

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lens
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.green500
import com.guru.composecookbook.theme.instagramGradient

@Composable
fun MatchesImage(imageId: Int) {
    Image(
        painter = painterResource(id = imageId),
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .height(65.dp)
            .width(65.dp)
            .clip(CircleShape)
            .border(
                shape = CircleShape,
                border = BorderStroke(
                    width = 3.dp,
                    brush = Brush.linearGradient(
                        colors = instagramGradient,
                        start = Offset(0f, 0f),
                        end = Offset(100f, 100f)
                    )
                )
            ),
        contentDescription = null
    )
}

@Composable
fun ImageWithChatDot(imageId: Int, modifier: Modifier, showDot: Boolean = true) {
    if (showDot) {
        Box {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.clip(CircleShape)
            )
            Icon(
                imageVector = Icons.Default.Lens,
                contentDescription = null,
                tint = green500,
                modifier = Modifier
                    .size(14.dp)
                    .align(Alignment.BottomEnd)
            )
        }
    } else {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.clip(CircleShape)
        )
    }
}
