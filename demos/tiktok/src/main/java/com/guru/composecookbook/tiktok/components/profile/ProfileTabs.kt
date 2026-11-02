package com.guru.composecookbook.tiktok.components.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.VerticalDistribute
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    modifier =
      Modifier.height(1.dp).background(MaterialTheme.colorScheme.onSurface).padding(top = 4.dp)
  )
  // The old M2 TabRow(backgroundColor = surface) drew its content (selected and unselected
  // icons) and the full-width indicator in onSurface (contentColorFor(surface)), with a 0.6
  // alpha on the unselected icon. Match that look rather than the M3 primary defaults
  // (primary-colored content + 24dp indicator): containerColor = surface, contentColor =
  // onSurface, a full-tab-width 2.dp onSurface indicator (the M2 indicator height) and no
  // M3 divider (the manual 1.dp onSurface line above already provides the separator).
  SecondaryTabRow(
    selectedTabIndex = selectedIndex,
    containerColor = MaterialTheme.colorScheme.surface,
    contentColor = MaterialTheme.colorScheme.onSurface,
    indicator = {
      TabRowDefaults.SecondaryIndicator(
        Modifier.tabIndicatorOffset(selectedIndex),
        height = 2.dp,
        color = MaterialTheme.colorScheme.onSurface,
      )
    },
    divider = {},
  ) {
    Tab(
      selected = selectedIndex == 0,
      onClick = { selectedIndex = 0 },
      modifier = Modifier.padding(12.dp),
      selectedContentColor = MaterialTheme.colorScheme.onSurface,
      unselectedContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
    ) {
      Icon(imageVector = Icons.Default.VerticalDistribute, contentDescription = null)
    }
    Tab(
      selected = selectedIndex == 1,
      onClick = { selectedIndex = 1 },
      modifier = Modifier.padding(12.dp),
      selectedContentColor = MaterialTheme.colorScheme.onSurface,
      unselectedContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
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
        modifier = Modifier.fillMaxWidth().height(150.dp),
        contentScale = ContentScale.Crop,
      )
    }
  }
}
