package com.guru.composecookbook.spotify.ui.home.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.spotify.data.SpotifyDataProvider
import com.guru.composecookbook.theme.modifiers.horizontalGradientBackground
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.verticalgrid.VerticalGrid

@Composable
fun SpotifyHome() {
    val scrollState = rememberScrollState(0)
    val surfaceGradient = SpotifyDataProvider.spotifySurfaceGradient(isSystemInDarkTheme())
    Box(modifier = Modifier.fillMaxSize()) {
        ScrollableContent(scrollState = scrollState, surfaceGradient = surfaceGradient)
        Icon(
            imageVector = Icons.Outlined.Settings,
            tint = MaterialTheme.colors.onSurface,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(start = 12.dp, end = 12.dp, top = 36.dp, bottom = 12.dp)
                .alpha(animateFloatAsState(1f - scrollState.value / 200f).value)
        )
        PlayerBottomBar(Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun ScrollableContent(scrollState: ScrollState, surfaceGradient: List<Color>) {
    Column(
        modifier = Modifier
            .horizontalGradientBackground(surfaceGradient)
            .padding(8.dp)
            .verticalScroll(state = scrollState)
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        SpotifyTitle("Good Evening")
        HomeGridSection()
        HomeLanesSection()
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun SpotifyTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = typography.h5.copy(fontWeight = FontWeight.ExtraBold),
        modifier = modifier.padding(start = 8.dp, end = 4.dp, bottom = 8.dp, top = 24.dp)
    )
}

@Composable
fun HomeGridSection() {
    val items = remember { AlbumsDataProvider.albums }
    VerticalGrid {
        items.take(6).forEach {
            SpotifyHomeGridItem(album = it)
        }
    }
}

@Composable
fun HomeLanesSection() {
    val categories = remember { AlbumsDataProvider.listOfSpotifyHomeLanes }
    categories.forEachIndexed { index, lane ->
        SpotifyTitle(text = lane)
        SpotifyLane(index)
    }
}

@Composable
fun SpotifyLane(index: Int) {
    val itemsEven = remember { AlbumsDataProvider.albums }
    val itemsOdd = remember { AlbumsDataProvider.albums.asReversed() }
    LazyRow {
        items(if (index % 2 == 0) itemsEven else itemsOdd) {
            SpotifyLaneItem(album = it)
        }
    }
}

@Preview
@Composable
fun PreviewSpotifyHome() {
    PreviewSpotifyHome()
}