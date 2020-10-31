package com.guru.composecookbook.ui.demoui.tiktok

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.R
import com.guru.composecookbook.ui.home.HomeScreenItems

@Composable
fun HomeScreen() {
    val bottomBarHeight = 50.dp
    Box(modifier = Modifier.fillMaxSize().padding(bottom = bottomBarHeight).clip(RoundedCornerShape(4.dp))) {
        // DraggableVideoPlayer
        Image(
            asset = imageResource(id = R.drawable.adele21),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        VideoOverLayUI()
    }
}

@Preview
@Composable
fun VideoOverLayUI() {
    Row(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        VideoInfoSection(Modifier.weight(1f))
        VideoIconsSection()
    }
}

@Composable
fun VideoIconsSection() {
    Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(asset = Icons.Default.FavoriteBorder, modifier = Modifier.size(48.dp))
        Spacer(modifier = Modifier.height(12.dp))
        Icon(asset = Icons.Default.FavoriteBorder, modifier = Modifier.size(48.dp))
        Spacer(modifier = Modifier.height(12.dp))
        Icon(asset = Icons.Default.FavoriteBorder, modifier = Modifier.size(48.dp))
        Spacer(modifier = Modifier.height(12.dp))
        Icon(asset = Icons.Default.FavoriteBorder, modifier = Modifier.size(48.dp))
    }
}

@Composable
fun VideoInfoSection(modifier: Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(
            text = "@Username",
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(text = "This is video description", style = MaterialTheme.typography.body2)
        Text(
            text = "#cool #tiktok #videos",
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Medium)
        )
    }

}