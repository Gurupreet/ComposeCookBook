package com.guru.composecookbook.spotify.ui.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.theme.typography

@Composable
fun SpotifySearchBar() {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(RoundedCornerShape(4.dp)),
        backgroundColor = MaterialTheme.colors.onSurface
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                tint = Color.LightGray,
                contentDescription = null
            )
            Text(
                text = "Artists, songs, or podcasts",
                color = MaterialTheme.colors.surface,
                modifier = Modifier.padding(8.dp),
                style = typography.h6.copy(fontSize = 14.sp),
            )

        }
    }
}
