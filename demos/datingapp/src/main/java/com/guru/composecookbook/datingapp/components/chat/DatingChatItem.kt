package com.guru.composecookbook.datingapp.components.chat

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.data.model.Album
import com.guru.composecookbook.theme.purple
import com.guru.composecookbook.theme.typography
import kotlin.random.Random

@Composable
fun DatingChatItem(album: Album) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .clickable(onClick = {}), elevation = 2.dp
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            ImageWithChatDot(
                imageId = album.imageId,
                modifier = Modifier.size(50.dp),
                showDot = album.id % 3 == 0
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = album.artist,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    style = typography.h6.copy(fontSize = 14.sp)
                )
                Text(
                    text = randomMessages[album.id - 1],
                    modifier = Modifier.padding(horizontal = 8.dp),
                    style = typography.subtitle2
                )
            }
            Text(
                text = "${Random.nextInt(1, 60)} min",
                color = purple,
                style = typography.body2,
            )

        }
    }
}
