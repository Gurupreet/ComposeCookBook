package com.guru.composecookbook.ui.demoui.spotify.details

import androidx.compose.animation.animate
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.gravity
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.theme.green700
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.demoui.spotify.SpotifySearchBar
import com.guru.composecookbook.ui.demoui.spotify.SpotifySearchGrid
import com.guru.composecookbook.ui.demoui.spotify.data.Album
import com.guru.composecookbook.ui.demoui.spotify.data.SpotifyDataProvider
import com.guru.composecookbook.ui.demoui.spotify.generateDominantColorState
import com.guru.composecookbook.ui.utils.horizontalGradientBackground
import com.guru.composecookbook.ui.utils.verticalGradientBackground

@Composable
fun SpotifyDetailScreen(album: Album) {
    val album = remember { album }
    val scrollState = rememberScrollState(0f)
    val image = imageResource(id = album.imageId).asAndroidBitmap()
    val swatch = remember(album.id) { generateDominantColorState(image) }
    val dominantColors = listOf(Color(swatch.rgb), MaterialTheme.colors.surface)
    val dominantGradient = remember { dominantColors }
    val surfaceGradient = SpotifyDataProvider
        .spotifySurfaceGradient(isSystemInDarkTheme()).asReversed()

    Stack(modifier = Modifier.fillMaxSize().verticalGradientBackground(dominantGradient)) {
        StackTopSection(album = album, scrollState = scrollState)
        TopSectionOverlay(scrollState = scrollState)
        BottomScrollableContent(scrollState = scrollState, surfaceGradient = surfaceGradient)
        AnimatedToolBar(album, scrollState, surfaceGradient)
    }
}

@Composable
fun AnimatedToolBar(album: Album, scrollState: ScrollState, surfaceGradient: List<Color>) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalGravity = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .horizontalGradientBackground(
                    if (Dp(scrollState.value) < 1080.dp)
                    listOf(Color.Transparent, Color.Transparent) else surfaceGradient
                )
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Icon(asset = Icons.Default.ArrowBack, tint = MaterialTheme.colors.onSurface)
            Text(
                text = album.song,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .padding(16.dp)
                    .drawOpacity((scrollState.value + 0.001f) / 1000)
            )
            Icon(asset = Icons.Default.MoreVert, tint = MaterialTheme.colors.onSurface)
        }
}

@Composable
fun TopSectionOverlay(scrollState: ScrollState) {
        //slowly increase alpha till it reaches 1
        val dynamicAlpha =
            if (((scrollState.value+0.00f)/1000) > 1f) 1f else  (scrollState.value+0.00f)/1000
        Box(
            modifier = Modifier.fillMaxWidth()
                .height(400.dp)
                .background(MaterialTheme.colors.surface.copy(
                    alpha = animate(dynamicAlpha)
                ))
        )
}

@Composable
fun BottomScrollableContent(scrollState: ScrollState, surfaceGradient: List<Color>) {
    ScrollableColumn(scrollState = scrollState, modifier = Modifier) {
        Spacer(modifier = Modifier.height(480.dp))
        Column(modifier = Modifier.horizontalGradientBackground(surfaceGradient)) {
            SongListScrollingSection()
        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
fun SongListScrollingSection() {
    ShuffleButton()
    DownloadedRow()
    val items = remember { SpotifyDataProvider.albums }
    items.forEach {
        SpotifySongListItem(album = it)
    }

}

@Composable
fun DownloadedRow() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
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
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.padding(8.dp),
            onCheckedChange = { switched = it }
        )
    }
}

@Composable
fun ShuffleButton() {
    Button(
        onClick = {},
        backgroundColor = green700,
        modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp).gravity(Alignment.CenterHorizontally)
            .clip(CircleShape)
    ) {
        Text(
            text = "SHUFFLE PLAY",
            style = typography.h6.copy(fontSize = 14.sp),
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
    }
}

@Composable
fun StackTopSection(album: Album, scrollState: ScrollState) {
    Column(horizontalGravity = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(100.dp).fillMaxWidth())
        //animate as scroll value increase but not fast so divide by random number 50
        val dynamicValue =
            if (250.dp - Dp(scrollState.value / 50) < 10.dp) 10.dp //prevent going 0 cause crash
            else 250.dp - Dp(scrollState.value / 20)
        val animateImageSize = animate(dynamicValue)
        Image(
            asset = imageResource(id = album.imageId),
            modifier = Modifier
                .preferredSize(animateImageSize)
                .padding(8.dp)
        )
        Text(
            text = album.song,
            style = typography.h5.copy(fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colors.onSurface
        )
        Text(
            text = "FOLLOWING",
            color = MaterialTheme.colors.onSurface,
            style = typography.h6.copy(fontSize = 12.sp),
            modifier = Modifier
                .padding(4.dp)
                .border(
                    border = BorderStroke(2.dp, MaterialTheme.colors.primaryVariant),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(vertical = 4.dp, horizontal = 24.dp)
        )
        Text(
            text = album.descriptions,
            style = typography.subtitle2,
            modifier = Modifier.padding(4.dp)
        )
    }
}


@Preview
@Composable
fun PreviewDetailScreen() {
    val album = SpotifyDataProvider.album
    ComposeCookBookTheme(true) {
        SpotifyDetailScreen(album = album)
    }

}