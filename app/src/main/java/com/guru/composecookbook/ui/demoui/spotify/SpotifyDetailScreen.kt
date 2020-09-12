package com.guru.composecookbook.ui.demoui.spotify

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.demoui.spotify.data.Album
import com.guru.composecookbook.ui.demoui.spotify.data.SpotifyDataProvider
import com.guru.composecookbook.ui.utils.horizontalGradientBackground

@Composable
fun SpotifyDetailScreen(album: Album) {
    val scrollState = rememberScrollState(0f)
    val surfaceGradient = SpotifyDataProvider.spotifySurfaceGradient(isSystemInDarkTheme())
    Stack(modifier = Modifier.fillMaxSize().horizontalGradientBackground(surfaceGradient)) {
        Text(
            text = "Search",
            style = typography.h3.copy(fontWeight = FontWeight.ExtraBold),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 80.dp, bottom = 40.dp)
                .fillMaxSize().drawOpacity(1f - scrollState.value/200)
            // Just reducing the opacity by small fraction when scroll happens
        )
        ScrollableColumn(
            scrollState = scrollState,
            modifier = Modifier
        ) {
            Spacer(modifier = Modifier.height(180.dp))
            Column(modifier = Modifier.horizontalGradientBackground(surfaceGradient)) {
                SpotifySearchBar()
                SpotifySearchGrid()
            }
            Spacer(modifier = Modifier.height(200.dp))
        }
    }
}