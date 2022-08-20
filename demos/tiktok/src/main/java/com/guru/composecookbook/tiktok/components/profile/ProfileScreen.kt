package com.guru.composecookbook.tiktok.components.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.data.model.Album
import com.guru.composecookbook.theme.ComposeCookBookTheme

@Composable
fun ProfileScreen(userId: String = "10", navHostController: NavHostController) {
    val album: Album = AlbumsDataProvider.albums.first { it.id.toString() == userId }
    ComposeCookBookTheme(darkTheme = false) {
        Scaffold(
            topBar = { ProfileAppBar(album, navHostController) }
        ) { paddingValues ->
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(paddingValues),
            ) {
                item { ProfileTopSection(album) }
            }
        }
    }
}





