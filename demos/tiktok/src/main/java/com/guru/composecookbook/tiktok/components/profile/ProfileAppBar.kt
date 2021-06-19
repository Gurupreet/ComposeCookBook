package com.guru.composecookbook.tiktok.components.profile

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.guru.composecookbook.data.model.Album

@Composable
fun ProfileAppBar(album: Album, navHostController: NavHostController) {
    TopAppBar(
        title = { Text(text = album.artist) },
        backgroundColor = MaterialTheme.colors.surface,
        navigationIcon = {
            IconButton(onClick = { navHostController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null)
            }
        }
    )
}
