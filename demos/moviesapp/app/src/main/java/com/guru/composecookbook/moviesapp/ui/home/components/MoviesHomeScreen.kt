package com.guru.composecookbook.moviesapp.ui.home.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.moviesapp.R
import com.guru.composecookbook.moviesapp.ui.home.MoviesHomeInteractionEvents
import com.guru.composecookbook.theme.extensions.generateDominantColorState
import com.guru.composecookbook.theme.modifiers.verticalGradientBackground
import com.guru.composecookbook.theme.typography

@Composable
fun MovieHomeScreen(moviesHomeInteractionEvents: (MoviesHomeInteractionEvents) -> Unit) {
    MovieHomeScreenContent(moviesHomeInteractionEvents)
}

@Composable
fun MovieHomeScreenContent(moviesHomeInteractionEvents: (MoviesHomeInteractionEvents) -> Unit) {
    //TODO dynamic gradient from poster via coil right now It's just getting from local images
    val imageId = remember { mutableStateOf(R.drawable.camelia) }
    val context = LocalContext.current
    val defaultBitmap =
        ImageBitmap.imageResource(context.resources, imageId.value).asAndroidBitmap()
    val currentBitmap = remember { mutableStateOf(defaultBitmap) }
    val swatch = currentBitmap.value.generateDominantColorState()
    val dominantColors = listOf(Color(swatch.rgb), Color.Black)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .verticalGradientBackground(dominantColors),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // use `item` for separate elements like headers
        // and `items` for lists of identical elements
        item { Spacer(modifier = Modifier.height(30.dp)) }
        item {
            Text(
                text = "Now Showing",
                style = typography.h5.copy(fontWeight = FontWeight.ExtraBold),
                modifier = Modifier.padding(16.dp)
            )
        }
        item { MoviesPager(imageId, moviesHomeInteractionEvents) }
    }
}
