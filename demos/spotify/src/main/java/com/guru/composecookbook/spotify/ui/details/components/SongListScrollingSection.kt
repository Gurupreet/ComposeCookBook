package com.guru.composecookbook.spotify.ui.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.theme.green700
import com.guru.composecookbook.theme.typography


@Composable
fun SongListScrollingSection() {
    ShuffleButton()
    DownloadedRow()
    val items = remember { AlbumsDataProvider.albums }
    items.forEach {
        SpotifySongListItem(album = it)
    }

}

@Composable
fun DownloadedRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Download",
            style = typography.h6.copy(fontSize = 14.sp),
            color = MaterialTheme.colors.onSurface
        )
        var switched by remember { mutableStateOf(true) }
        Switch(
            checked = switched,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colors.primary
            ),
            modifier = Modifier.padding(8.dp),
            onCheckedChange = { switched = it }
        )
    }
}

@Composable
fun ShuffleButton() {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(backgroundColor = green700),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 100.dp)
            .clip(CircleShape),
    ) {
        Text(
            text = "SHUFFLE PLAY",
            style = typography.h6.copy(fontSize = 14.sp),
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
    }
}
