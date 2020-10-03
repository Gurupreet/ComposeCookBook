package com.guru.composecookbook.ui.datingapp

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lens
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.theme.green500
import com.guru.composecookbook.theme.instagramGradient
import com.guru.composecookbook.theme.purple
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.demoui.spotify.data.Album
import com.guru.composecookbook.ui.demoui.spotify.data.SpotifyDataProvider
import com.guru.composecookbook.ui.utils.verticalGradientBackground
import kotlin.random.Random

@Composable
fun DatingChatScreen() {
    val items = SpotifyDataProvider.albums.take(6)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalGradientBackground(listOf(Color.White, purple.copy(alpha = 0.2f)))
    ) {
        MatchSection()
        LazyColumnFor(
            items = items,
            modifier = Modifier.fillMaxSize()
        ) {
            DatingChatItem(it)
        }

    }
}

@Composable
fun MatchSection() {
    val matches = SpotifyDataProvider.albums.takeLast(10)
    Text(
        text = "New matches",
        style = typography.h6.copy(fontSize = 16.sp),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        color = purple
    )
    LazyRowFor(items = matches) {
        MatchesImage(imageId = it.imageId)
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun DatingChatItem(album: Album) {
    Card(modifier = Modifier.padding(6.dp).clickable(onClick = {}), elevation = 2.dp) {
        Row(modifier = Modifier.padding(12.dp)) {
            ImageWithChatDot(
                imageId = album.imageId,
                modifier = Modifier.preferredSize(50.dp),
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

@Composable
fun ImageWithChatDot(imageId: Int, modifier: Modifier, showDot: Boolean = true) {
    if (showDot) {
        Box {
            Image(
                asset = imageResource(id = imageId),
                contentScale = ContentScale.Crop,
                modifier = modifier.clip(CircleShape)
            )
            Icon(
                asset = Icons.Default.Lens,
                tint = green500,
                modifier = Modifier.preferredSize(14.dp).align(Alignment.BottomEnd)
            )
        }
    } else {
        Image(
            asset = imageResource(id = imageId),
            contentScale = ContentScale.Crop,
            modifier = modifier.clip(CircleShape)
        )
    }
}

@Composable
fun MatchesImage(imageId: Int) {
    val modifier = Modifier
        .padding(start = 8.dp, end = 8.dp, top = 8.dp)
        .preferredHeight(65.dp)
        .preferredWidth(65.dp)
        .clip(CircleShape)
        .border(
            shape = CircleShape,
            border = BorderStroke(
                3.dp,
                brush = LinearGradient(
                    instagramGradient,
                    startX = 0f,
                    endX = 100f,
                    startY = 0f,
                    endY = 100f
                )
            )
        )
    Image(asset = imageResource(id = imageId), modifier = modifier)
}

val randomMessages = listOf(
    "Miss you ❤️❤️",
    "Hey how are you? \uD83E\uDD1A \uD83D\uDC94",
    "Same here \uD83D\uDE18",
    "See ya tomorrow \uD83D\uDE00",
    "That's sad to hear \uD83D\uDE1E",
    "Can we ? \uD83D\uDE0D\uD83D\uDE0D"
)

@Preview
@Composable
fun PreviewDatingChatScreen() {
    DatingChatScreen()
}