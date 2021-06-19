package com.guru.composecookbook.tiktok.components.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.VerticalDistribute
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.verticalgrid.VerticalGrid

@Composable
fun ProfileTabs() {
    var selectedIndex by remember { mutableStateOf(0) }
    Spacer(
        modifier = Modifier
            .height(1.dp)
            .background(MaterialTheme.colors.onSurface)
            .padding(top = 4.dp)
    )
    TabRow(selectedTabIndex = selectedIndex, backgroundColor = MaterialTheme.colors.surface) {
        Tab(
            selected = selectedIndex == 0,
            onClick = { selectedIndex = 0 },
            modifier = Modifier.padding(12.dp)
        ) {
            Icon(imageVector = Icons.Default.VerticalDistribute, contentDescription = null)
        }
        Tab(
            selected = selectedIndex == 0,
            onClick = { selectedIndex = 1 },
            modifier = Modifier.padding(12.dp)
        ) {
            Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = null)
        }
    }

    val list = remember { AlbumsDataProvider.albums }
    VerticalGrid(columns = 3) {
        list.forEach {
            Image(
                painter = painterResource(id = it.imageId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}
