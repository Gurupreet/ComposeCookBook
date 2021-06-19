package com.guru.composecookbook.spotify.ui.details.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.data.model.Album
import com.guru.composecookbook.spotify.data.SpotifyDataProvider
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.theme.extensions.generateDominantColorState
import com.guru.composecookbook.theme.modifiers.horizontalGradientBackground
import com.guru.composecookbook.theme.modifiers.verticalGradientBackground

@Composable
fun SpotifyDetailScreen(album: Album) {
    val scrollState = rememberScrollState(0)
    val context = LocalContext.current
    val image = ImageBitmap.imageResource(context.resources, id = album.imageId).asAndroidBitmap()
    val swatch = remember(album.id) { image.generateDominantColorState() }
    val dominantColors = listOf(Color(swatch.rgb), MaterialTheme.colors.surface)
    val dominantGradient = remember { dominantColors }
    val surfaceGradient = SpotifyDataProvider
        .spotifySurfaceGradient(isSystemInDarkTheme()).asReversed()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalGradientBackground(dominantGradient)
    ) {
        BoxTopSection(album = album, scrollState = scrollState)
        TopSectionOverlay(scrollState = scrollState)
        BottomScrollableContent(scrollState = scrollState, surfaceGradient = surfaceGradient)
        AnimatedToolBar(album, scrollState, surfaceGradient)
    }
}

@Composable
fun AnimatedToolBar(album: Album, scrollState: ScrollState, surfaceGradient: List<Color>) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .horizontalGradientBackground(
                if (Dp(scrollState.value.toFloat()) < 1080.dp)
                    listOf(Color.Transparent, Color.Transparent) else surfaceGradient
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack, tint = MaterialTheme.colors.onSurface,
            contentDescription = null
        )
        Text(
            text = album.song,
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier
                .padding(16.dp)
                .alpha(((scrollState.value + 0.001f) / 1000).coerceIn(0f, 1f))
        )
        Icon(
            imageVector = Icons.Default.MoreVert, tint = MaterialTheme.colors.onSurface,
            contentDescription = null
        )
    }
}

@Composable
fun BottomScrollableContent(scrollState: ScrollState, surfaceGradient: List<Color>) {
    Column(modifier = Modifier.verticalScroll(state = scrollState)) {
        Spacer(modifier = Modifier.height(480.dp))
        Column(modifier = Modifier.horizontalGradientBackground(surfaceGradient)) {
            SongListScrollingSection()
        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Preview
@Composable
fun PreviewDetailScreen() {
    val album = AlbumsDataProvider.album
    ComposeCookBookTheme(true) {
        SpotifyDetailScreen(album = album)
    }

}