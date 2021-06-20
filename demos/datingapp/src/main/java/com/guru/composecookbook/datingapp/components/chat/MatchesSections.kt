package com.guru.composecookbook.datingapp.components.chat

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.theme.purple
import com.guru.composecookbook.theme.typography

@Composable
fun MatchSection() {
    val matches = AlbumsDataProvider.albums.takeLast(10)
    Text(
        text = "New matches",
        style = typography.h6.copy(fontSize = 16.sp),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        color = purple
    )
    LazyRow {
        items(
            items = matches,
            itemContent = { MatchesImage(imageId = it.imageId) })
    }
    Spacer(modifier = Modifier.height(24.dp))
}
