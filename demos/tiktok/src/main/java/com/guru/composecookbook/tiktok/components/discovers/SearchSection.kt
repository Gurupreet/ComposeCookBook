package com.guru.composecookbook.tiktok.components.discovers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.tiktok.TiktokDemoDataProvider

@Composable
fun SearchSection() {
    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Row(
            modifier = Modifier
                .weight(1f)
                .background(TiktokDemoDataProvider.customGray)
                .padding(4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                modifier = Modifier.padding(4.dp),
                contentDescription = null
            )
            Text(
                text = "Search",
                color = Color.DarkGray,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Default.QrCodeScanner, contentDescription = null)
        }
    }
}
