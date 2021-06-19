package com.guru.composecookbook.tiktok.components.discovers

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.tiktok.R
import com.guru.composecookbook.tiktok.TiktokDemoDataProvider
import com.guru.composecookbook.tiktok.TiktokDemoDataProvider.customGray

@Composable
fun LanesSection() {
    LazyColumn {
        itemsIndexed(
            items = TiktokDemoDataProvider.lanes,
            itemContent = { _, laneItem -> LaneSection(laneItem) }
        )
    }
}

@Composable
fun LaneSection(
    laneItem: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(vertical = 8.dp)) {
        Row(modifier = Modifier.padding(8.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_hashtag_solid),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .border(
                        border = BorderStroke(0.5.dp, Color.LightGray),
                        shape = CircleShape
                    )
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = laneItem,
                    modifier = Modifier.padding(vertical = 4.dp),
                    style = typography.h6.copy(fontSize = 14.sp)
                )
                Text(text = "Trending Hashtag", style = typography.subtitle2)
            }
            Text(
                text = "${laneItem.length}.2M",
                style = typography.h6.copy(fontSize = 12.sp),
                modifier = Modifier
                    .background(customGray)
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }
        LazyRow {
            items(
                items = (1..8).toList(),
                itemContent = { MediaItem(it) }
            )
        }
    }
}
