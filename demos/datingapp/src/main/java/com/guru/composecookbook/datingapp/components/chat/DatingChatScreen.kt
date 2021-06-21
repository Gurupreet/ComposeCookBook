package com.guru.composecookbook.datingapp.components.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.theme.modifiers.verticalGradientBackground
import com.guru.composecookbook.theme.purple

val randomMessages = listOf(
    "Miss you ❤️❤️",
    "Hey how are you? \uD83E\uDD1A \uD83D\uDC94",
    "Same here \uD83D\uDE18",
    "See ya tomorrow \uD83D\uDE00",
    "That's sad to hear \uD83D\uDE1E",
    "Can we ? \uD83D\uDE0D\uD83D\uDE0D"
)

@Composable
fun DatingChatScreen() {
    val items = AlbumsDataProvider.albums.take(6)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalGradientBackground(listOf(Color.White, purple.copy(alpha = 0.2f)))
    ) {
        MatchSection()
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = items,
                itemContent = {
                    DatingChatItem(it)
                }
            )
        }

    }
}

@Preview
@Composable
fun PreviewDatingChatScreen() {
    DatingChatScreen()
}
