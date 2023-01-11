package com.guru.composecookbook.meditation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.meditation.ui.model.DailyThought
import com.guru.composecookbook.meditation.ui.theme.TextWhite
import com.guru.composecookbook.meditation.ui.theme.dp10
import com.guru.composecookbook.meditation.ui.theme.DarkBlue
import com.guru.composecookbook.meditation.ui.theme.DeepBlue
import com.guru.composecookbook.meditation.R

@Composable
fun DailyThoughtsItem(
    dailyThought: DailyThought
) {
    BoxWithConstraints(
        modifier = Modifier
            .clip(RoundedCornerShape(dp10))
            .background(color = DarkBlue)
            .height(92.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_daily_thoughts),
                contentDescription = "Daily Thoughts",
                Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier.padding(
                    horizontal = 15.dp
                )
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column() {
                        Text(
                            text = dailyThought.title,
                            style = MaterialTheme.typography.h6,
                            color = dailyThought.textColor,
                            fontSize = 18.sp
                        )
                        Text(
                            text = dailyThought.description,
                            color = dailyThought.textColor,
                            style = MaterialTheme.typography.body1,
                            fontSize = 11.sp
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(TextWhite)
                            .padding(10.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_play),
                            contentDescription = "Play",
                            tint = DeepBlue,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}