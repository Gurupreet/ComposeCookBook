package com.guru.composecookbook.youtube.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.theme.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YoutubeHome() {
  // The old M2 TopAppBar used a dark-only elevation (0.dp light, 8.dp dark). M3 TopAppBar has
  // no elevation unless it scrolls, so restore the dark shadow by wrapping the bar in a
  // Surface with shadowElevation in dark mode (derived from the colorScheme, not the system
  // setting). Light mode stays flat as before.
  val isDark = MaterialTheme.colorScheme.surface.luminance() < 0.5f
  Scaffold(
    topBar = {
      Surface(
        shadowElevation = if (isDark) 8.dp else 0.dp,
        modifier = Modifier.fillMaxWidth(),
      ) {
        TopAppBar(
          title = { Text(text = "Youtube") },
          colors =
            TopAppBarDefaults.topAppBarColors(
              containerColor = MaterialTheme.colorScheme.surface,
              titleContentColor = MaterialTheme.colorScheme.onSurface,
              navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
              actionIconContentColor = MaterialTheme.colorScheme.onSurface,
            ),
          navigationIcon = {
            Icon(
              painter = painterResource(id = R.drawable.ic_youtube),
              contentDescription = null,
              tint = Color.Red,
              modifier = Modifier.padding(start = 16.dp),
            )
          },
          actions = {
            Icon(
              imageVector = Icons.Default.Videocam,
              contentDescription = null,
              modifier = Modifier.padding(horizontal = 8.dp),
            )
            Icon(
              imageVector = Icons.Default.Search,
              contentDescription = null,
              modifier = Modifier.padding(horizontal = 8.dp),
            )
            Image(
              painter = painterResource(id = R.drawable.p3),
              contentDescription = null,
              modifier =
                Modifier.padding(vertical = 4.dp, horizontal = 8.dp).size(24.dp).clip(CircleShape),
            )
          },
        )
      }
    },
    content = { paddingValues ->
      Surface(
        // The old M2 Surface(elevation) was 0.dp in light and 8.dp in dark. The shadow is the
        // faithful M3 equivalent here; derive light/dark from the applied colorScheme (not the
        // system setting) since this is a theme-driven composable.
        shadowElevation = if (isDark) 8.dp else 0.dp,
        modifier = Modifier.padding(paddingValues),
      ) {
        YoutubeContent()
      }
    },
  )
}

@Composable
fun YoutubeContent() {
  val tweets = remember { DemoDataProvider.tweetList.filter { it.tweetImageId > 0 } }
  // There is performance issue when using LazyRowFor and LazyColumnFor inside scrollableColumn
  // So using column for now.
  Column {
    HorizontalDivider()
    LazyRow(
      modifier = Modifier.padding(vertical = 8.dp),
      contentPadding = PaddingValues(horizontal = 12.dp),
    ) {
      items(
        items = tweets,
        itemContent = {
          YoutubeChip(
            selected = it.id == 2,
            text = it.author,
            modifier = Modifier.padding(horizontal = 4.dp),
          )
        },
      )
    }
    LazyColumn { items(items = tweets, itemContent = { item -> YoutubeListItem(item) }) }
  }
}
