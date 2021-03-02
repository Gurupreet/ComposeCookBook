package com.guru.composecookbook.ui.demoapps.spotify

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.palette.graphics.Palette
import com.guru.composecookbook.theme.graySurface
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.demoapps.spotify.data.Album
import com.guru.composecookbook.ui.demoapps.spotify.data.SpotifyDataProvider
import com.guru.composecookbook.ui.demoapps.spotify.details.SpotifyDetailActivity
import com.guru.composecookbook.ui.utils.horizontalGradientBackground

@Composable
fun SpotifyHomeGridItem(album: Album) {
    val cardColor = if (isSystemInDarkTheme()) graySurface else MaterialTheme.colors.background
    Card(
        elevation = 4.dp,
        backgroundColor = cardColor,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .padding(8.dp)
            .clickable(onClick = {})
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = album.imageId),
                contentDescription = null,
                modifier = Modifier.size(55.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = album.song,
                style = typography.h6.copy(fontSize = 14.sp),
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
fun SpotifyLaneItem(album: Album) {
    val context = LocalContext.current
    val album = remember { album }
    Column(
        modifier =
        Modifier.width(180.dp).padding(8.dp)
            .clickable(
                onClick = {
                    //Disclaimer: We should pass event top level and there should startActivity
                    context.startActivity(SpotifyDetailActivity.newIntent(context, album))
                })
    ) {
        Image(
            painter = painterResource(id = album.imageId),
            modifier = Modifier.width(180.dp)
                .height(160.dp),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            text = "${album.song}: ${album.descriptions}",
            style = typography.body2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Composable
fun SpotifySearchGridItem(album: Album) {
    val context = LocalContext.current
    val imageBitmap =
        imageFromResource(res = context.resources, resId = album.imageId).asAndroidBitmap()
    val swatch = remember(album.id) { generateDominantColorState(imageBitmap) }
    val dominantGradient =
        remember { listOf(Color(swatch.rgb), Color(swatch.rgb).copy(alpha = 0.6f)) }
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = {
                //Disclaimer: We should pass event top level and there should startActivity
                context.startActivity(SpotifyDetailActivity.newIntent(context, album))
            })
            .height(100.dp)
            .clip(RoundedCornerShape(8.dp))
            .horizontalGradientBackground(dominantGradient),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = album.song,
            style = typography.h6.copy(fontSize = 14.sp),
            modifier = Modifier.padding(8.dp)
        )
        Image(
            painter = painterResource(id = album.imageId),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.size(70.dp)
                .align(Alignment.Bottom)
                .graphicsLayer(translationX = 40f, rotationZ = 32f, shadowElevation = 16f)
        )
    }
}

@Preview
@Composable
fun PreviewSpotifyHomeGridItem() {
    val album = remember { SpotifyDataProvider.album }
    SpotifyHomeGridItem(album)
}

fun generateDominantColorState(bitmap: Bitmap): Palette.Swatch {
    return Palette.Builder(bitmap)
        .resizeBitmapArea(0)
        .maximumColorCount(16)
        .generate()
        .swatches
        .maxByOrNull { swatch -> swatch.population }!!

}

@Preview
@Composable
fun PreviewLaneItem() {
    val album = remember { SpotifyDataProvider.album }
    SpotifyLaneItem(album)
}

@Preview
@Composable
fun SpotifySearchGridItem() {
    val album = remember { SpotifyDataProvider.album }
    SpotifySearchGridItem(album)
}