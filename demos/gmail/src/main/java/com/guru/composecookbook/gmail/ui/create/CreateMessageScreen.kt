package com.guru.composecookbook.gmail.ui.create

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Attachment
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.guru.composecookbook.gmail.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateMessageScreen(navController: NavHostController) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text(text = "Compose") },
        colors =
          TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface,
          ),
        navigationIcon = {
          IconButton(onClick = { navController.popBackStack() }) {
            Icon(
              imageVector = Icons.Outlined.ArrowBack,
              contentDescription = stringResource(id = R.string.cd_back),
            )
          }
        },
        actions = {
          IconButton(onClick = {}) {
            Icon(imageVector = Icons.Outlined.Attachment, contentDescription = null)
          }
          IconButton(onClick = {}) {
            Icon(imageVector = Icons.Outlined.Send, contentDescription = null)
          }
          CreateMessageMoreActionPopupMenu()
        },
      )
    },
    content = { paddingValues -> CreateMessageBody(modifier = Modifier.padding(paddingValues)) },
  )
}
