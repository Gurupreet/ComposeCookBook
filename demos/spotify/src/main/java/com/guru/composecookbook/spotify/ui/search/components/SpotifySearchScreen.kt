package com.guru.composecookbook.spotify.ui.search.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.spotify.data.SpotifyDataProvider
import com.guru.composecookbook.theme.modifiers.horizontalGradientBackground
import com.guru.composecookbook.theme.typography

@Composable
fun SpotifySearchScreen() {
    val scrollState = rememberScrollState(0)
    val surfaceGradient = SpotifyDataProvider.spotifySurfaceGradient(isSystemInDarkTheme())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .horizontalGradientBackground(surfaceGradient)
    ) {
        Text(
            text = "Search",
            style = typography.h3.copy(fontWeight = FontWeight.ExtraBold),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 80.dp, bottom = 40.dp)
                .fillMaxSize()
                .alpha(1f - scrollState.value / 200)
            // Just reducing the opacity by small fraction when scroll happens
        )
        Column(
            modifier = Modifier.verticalScroll(scrollState)
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

@Preview
@Composable
fun PreviewSpotifySearch() {
    SpotifySearchScreen()
}