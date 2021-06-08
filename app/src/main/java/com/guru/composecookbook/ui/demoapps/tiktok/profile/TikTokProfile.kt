package com.guru.composecookbook.ui.demoapps.tiktok.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.guru.composecookbook.R
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.theme.tiktokRed
import com.guru.composecookbook.spotify.data.models.Album
import com.guru.composecookbook.spotify.data.SpotifyDataProvider
import com.guru.composecookbook.verticalgrid.VerticalGrid

@Composable
fun TikTokProfile(userId: String = "10", navHostController: NavHostController) {
    val album: Album = SpotifyDataProvider.albums.first { it.id.toString() == userId }
    ComposeCookBookTheme(darkTheme = false) {
        Scaffold(
            topBar = { ProfileAppBar(album, navHostController) }
        ) {
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                item { ProfileTopSection(album) }
            }
        }
    }
}

@Composable
fun ProfileTopSection(album: Album) {
    Spacer(modifier = Modifier.height(16.dp))
    Image(
        painter = painterResource(id = album.imageId),
        contentDescription = null,
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
    )
    Text(
        text = "@${album.artist.trim()}",
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(8.dp)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            Text(text = "15", style = MaterialTheme.typography.h6)
            Text(text = "Following", style = MaterialTheme.typography.subtitle2)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            Text(text = "157k", style = MaterialTheme.typography.h6)
            Text(text = "Followers", style = MaterialTheme.typography.subtitle2)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 32.dp)
        ) {
            Text(text = "1.78M", style = MaterialTheme.typography.h6)
            Text(text = "Likes", style = MaterialTheme.typography.subtitle2)
        }
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                backgroundColor = tiktokRed,
                contentColor = Color.White
            ),
            modifier = Modifier
                .height(50.dp)
                .padding(horizontal = 2.dp)
        ) {
            Text(text = "Follow", modifier = Modifier.padding(horizontal = 32.dp))
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_instagram),
            contentDescription = null,
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
                .padding(2.dp)
                .border(
                    border = BorderStroke(
                        1.dp,
                        color = MaterialTheme.colors.onSurface
                    ), shape = RoundedCornerShape(4.dp)
                )
        )
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = null,
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
                .padding(2.dp)
                .border(
                    border = BorderStroke(
                        1.dp,
                        color = MaterialTheme.colors.onSurface
                    ), shape = RoundedCornerShape(4.dp)
                )
        )
    }
    Text(
        text = "My Songs and albums- ${album.song}, ${album.descriptions}: Stay Tuned",
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 32.dp),
        style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Center
    )
    ProfileTabs()
}

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

    val list = remember { SpotifyDataProvider.albums }
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