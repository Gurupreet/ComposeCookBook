package com.guru.composecookbook.ui.demoui.tiktok.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.R

@Composable
fun TikTokProfile() {
    Scaffold(
        topBar = { ProfileAppBar() }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                asset = imageResource(id = R.drawable.james),
                modifier = Modifier.size(100.dp)
                    .clip(CircleShape)
            )
            Text(text = "@usernameislong", style = MaterialTheme.typography.h6, modifier = Modifier.padding(8.dp))
            Row(modifier = Modifier.fillMaxWidth().padding(12.dp), horizontalArrangement = Arrangement.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(horizontal = 24.dp)) {
                    Text(text = "15", style = MaterialTheme.typography.h6)
                    Text(text = "Following", style = MaterialTheme.typography.subtitle2)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(horizontal = 24.dp)) {
                    Text(text = "157k", style = MaterialTheme.typography.h6)
                    Text(text = "Followers", style = MaterialTheme.typography.subtitle2)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(horizontal = 32.dp)) {
                    Text(text = "1.78M", style = MaterialTheme.typography.h6)
                    Text(text = "Likes", style = MaterialTheme.typography.subtitle2)
                }

            }
        }
    }
}

@Composable
fun ProfileAppBar() {
    TopAppBar(
        title = { Text(text = "Name") },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icons.Default.ArrowBack
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icons.Default.MoreVert
            }
        }
    )
}