package com.guru.composecookbook.gmail.ui.create

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Attachment
import androidx.compose.material.icons.outlined.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.guru.composecookbook.gmail.R

@Composable
fun CreateMessageScreen(navController: NavHostController) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text(text = "Compose") },
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
        navigationIcon = {
          IconButton(onClick = { navController.popBackStack() }) {
            Icon(
              imageVector = Icons.Outlined.ArrowBack,
              contentDescription = stringResource(id = R.string.cd_back)
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
        }
      )
    },
    content = { paddingValues ->
      CreateMessageBody(
        modifier = Modifier.padding(paddingValues),
      )
    },
  )
}
