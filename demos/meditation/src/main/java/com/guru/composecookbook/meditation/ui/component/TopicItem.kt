package com.guru.composecookbook.meditation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.meditation.ui.model.Topic
import com.guru.composecookbook.meditation.ui.theme.dp10

@Composable
fun TopicItem(
    topic: Topic,
    itemSize: Dp
) {
    Box(modifier = Modifier.padding(horizontal = 7.5.dp, vertical = 7.5.dp)) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .size(itemSize)
                .background(color = topic.color)
        ) {
            Image(
                painter = painterResource(id = topic.image),
                contentDescription = "Topic",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.align(Alignment.TopCenter)
            )
            Text(
                topic.title,
                style = MaterialTheme.typography.h2,
                fontSize = 18.sp,
                lineHeight = 26.sp,
                color = topic.textColor,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(
                        Alignment.BottomStart
                    )
                    .padding(bottom = dp10, start = dp10)
            )
        }
    }
}