package com.guru.composecookbook.meditation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.meditation.ui.model.BottomMenuContent
import com.guru.composecookbook.meditation.ui.spacerHeight5
import com.guru.composecookbook.meditation.ui.theme.AquaBlue
import com.guru.composecookbook.meditation.ui.theme.ButtonBlue
import com.guru.composecookbook.meditation.ui.theme.Gray

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .clickable {
                    onItemClick()
                }
                .padding(15.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) Color.White else Gray,
                modifier = Modifier.size(20.dp)
            )
        }
        spacerHeight5()
        Text(
            text = item.title,
            style = MaterialTheme.typography.h2,
            color = if(isSelected) activeTextColor else inactiveTextColor,
            fontSize = 14.sp
        )
    }
}