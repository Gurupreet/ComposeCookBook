package com.guru.composecookbook.tiktok.components.profile

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.guru.composecookbook.data.model.Album
import com.guru.composecookbook.tiktok.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileAppBar(album: Album, navHostController: NavHostController) {
  // The Profile screen is nested inside the outer TikTok Scaffold, which already consumes the
  // status-bar / system-bar window insets; pass 0 here so they are not applied a second time
  // (M2 Scaffold/TopAppBar ignored window insets).
  TopAppBar(
    windowInsets = WindowInsets(0),
    title = { Text(text = album.artist) },
    colors =
      TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.surface,
        titleContentColor = MaterialTheme.colorScheme.onSurface,
        navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
        actionIconContentColor = MaterialTheme.colorScheme.onSurface,
      ),
    navigationIcon = {
      IconButton(onClick = { navHostController.popBackStack() }) {
        Icon(
          imageVector = Icons.Filled.ArrowBack,
          contentDescription = stringResource(id = R.string.cd_back),
        )
      }
    },
    actions = {
      IconButton(onClick = {}) {
        Icon(
          imageVector = Icons.Filled.MoreVert,
          contentDescription = stringResource(id = R.string.cd_back),
        )
      }
    },
  )
}
