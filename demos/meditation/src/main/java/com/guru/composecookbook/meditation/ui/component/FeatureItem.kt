package com.guru.composecookbook.meditation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.meditation.ui.model.Feature
import com.guru.composecookbook.meditation.ui.theme.ButtonBlue
import com.guru.composecookbook.meditation.ui.theme.TextWhite

@Composable
fun FeatureItem(
    feature: Feature,
    modifier: Modifier = Modifier,
    textColor : Color
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Image(
                painter = painterResource(id = feature.image),
                contentDescription = feature.title,
                modifier = Modifier.align(Alignment.TopEnd)
            )

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxHeight().padding(start = 15.dp)
            ) {
                Text(
                    text = feature.title,
                    style = MaterialTheme.typography.h6,
                    color = textColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = feature.description,
                    color = textColor,
                    style = MaterialTheme.typography.body1,
                    fontSize = 14.sp
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(bottom = 15.dp, start = 15.dp, end = 15.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = feature.time,
                    color = textColor,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Start",
                    color = TextWhite,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable {
                            // Handle the click
                        }
                        .clip(RoundedCornerShape(12.dp))
                        .background(ButtonBlue)
                        .padding(vertical = 6.dp, horizontal = 15.dp)
                )
            }
        }
    }
}