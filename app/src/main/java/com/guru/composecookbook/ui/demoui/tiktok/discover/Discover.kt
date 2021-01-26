package com.guru.composecookbook.ui.demoui.tiktok.discover

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.R
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.theme.typography
import dev.chrisbanes.accompanist.coil.CoilImage

val lanes =
    listOf("OhHO ohNO", "FunFacts", "HappyDeepavli", "HalloweenIsHere", "BoomBoom", "No no no no")
val customGray = Color.LightGray.copy(alpha = 0.5f)

@Composable
fun DiscoverScreen() {
    ComposeCookBookTheme(darkTheme = false) {
        Surface {
            Column {
                SearchSection()
                LanesSection()
                Spacer(modifier = Modifier.height(400.dp))
            }
        }
    }
}

@Composable
fun LanesSection() {
    LazyColumnForIndexed(items = lanes) { index, laneItem ->
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            Row(modifier = Modifier.padding(8.dp)) {
                Icon(
                    vectorResource(id = R.drawable.ic_hashtag_solid),
                    modifier = Modifier.size(32.dp)
                        .border(
                            border = BorderStroke(0.5.dp, Color.LightGray),
                            shape = CircleShape
                        )
                )
                Column(modifier = Modifier.padding(horizontal = 16.dp).weight(1f)) {
                    Text(
                        text = laneItem,
                        modifier = Modifier.padding(vertical = 4.dp),
                        style = typography.h6.copy(fontSize = 14.sp)
                    )
                    Text(text = "Trending Hashtag", style = typography.subtitle2)
                }
                Text(
                    text = "${laneItem.length}.2M",
                    style = typography.h6.copy(fontSize = 12.sp),
                    modifier = Modifier.background(customGray)
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
            LazyRowFor(items = (1..8).toList()) {
                CoilImage(
                    data = "https://picsum.photos/id/${it}/200/200",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(150.dp).width(120.dp).padding(2.dp),

                    )
            }
        }
    }
}

@Composable
fun SearchSection() {
    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Row(modifier = Modifier.weight(1f).background(customGray).padding(4.dp)) {
            Icon(Icons.Default.Search, modifier = Modifier.padding(4.dp))
            Text(
                text = "Search",
                color = Color.DarkGray,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
        IconButton(onClick = {}) {
            Icon(Icons.Default.QrCodeScanner)
        }
    }
}