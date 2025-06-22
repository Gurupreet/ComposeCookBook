package com.guru.composecookbook.tiktok.components.profile

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.guru.composecookbook.data.model.Album
import com.guru.composecookbook.tiktok.R

@Composable
fun ProfileAppBar(album: Album, navHostController: NavHostController) {
  TopAppBar(
    title = { Text(text = album.artist) },
    backgroundColor = MaterialTheme.colors.surface,
    navigationIcon = {
      IconButton(onClick = { navHostController.popBackStack() }) {
        Icon(
          imageVector = Icons.Filled.ArrowBack,
          contentDescription = stringResource(id = R.string.cd_back)
        )
      }
    },
    actions = {
      IconButton(onClick = {}) {
        Icon(
          imageVector = Icons.Filled.MoreVert,
          contentDescription = stringResource(id = R.string.cd_back)
        )
      }
    }
  )
}
