package com.guru.composecookbook.ui.demoui.spotify

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.demoui.spotify.data.SpotifyDataProvider
import com.guru.composecookbook.ui.utils.VerticalGrid
import com.guru.composecookbook.ui.utils.horizontalGradientBackground

@Composable
fun SpotifySearchScreen() {
    val scrollState = rememberScrollState(0f)
    val surfaceGradient = SpotifyDataProvider.spotifySurfaceGradient(isSystemInDarkTheme())

    Box(modifier = Modifier.fillMaxSize().horizontalGradientBackground(surfaceGradient)) {
        Text(
            text = "Search",
            style = typography.h3.copy(fontWeight = FontWeight.ExtraBold),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 80.dp, bottom = 40.dp)
                .fillMaxSize().alpha(1f - scrollState.value / 200)
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
            Icon(imageVector = Icons.Default.Search, tint = Color.LightGray)
            Text(
                text = "Artists, songs, or podcasts",
                color = MaterialTheme.colors.surface,
                modifier = Modifier.padding(8.dp),
                style = typography.h6.copy(fontSize = 14.sp),
            )

        }
    }
}

@Composable
fun SpotifySearchGrid() {
    val items = remember { SpotifyDataProvider.albums }
    //This is not Lazy at the moment Soon we will have LazyLayout coming then will
    //Update it so we have better performance
    VerticalGrid {
        items.forEach {
            SpotifySearchGridItem(it)
        }
    }
}


@Preview
@Composable
fun PreviewSpotifySearch() {
    SpotifySearchScreen()
}