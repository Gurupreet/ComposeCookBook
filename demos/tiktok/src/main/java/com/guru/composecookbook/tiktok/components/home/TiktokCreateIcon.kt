package com.guru.composecookbook.tiktok.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.tiktokBlue
import com.guru.composecookbook.theme.tiktokRed

@Preview
@Composable
fun TiktokCreateIcon() {
    Box(contentAlignment = Alignment.Center) {
        Row {
            Spacer(
                modifier = Modifier
                    .height(30.dp)
                    .width(24.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(tiktokBlue)
            )
            Spacer(
                modifier = Modifier
                    .height(30.dp)
                    .width(24.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(tiktokRed)
            )
        }
        Icon(
            imageVector = Icons.Filled.Add,
            tint = Color.Black,
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .padding(vertical = 4.dp, horizontal = 8.dp)
        )
    }
}