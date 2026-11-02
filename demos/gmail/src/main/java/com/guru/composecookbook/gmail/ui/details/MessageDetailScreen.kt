package com.guru.composecookbook.gmail.ui.details

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.guru.composecookbook.gmail.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageDetailScreen(navController: NavHostController) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = {},
        colors =
          TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            navigationIconContentColor = Color.Black,
            actionIconContentColor = Color.Black,
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
            Icon(
              imageVector = Icons.Outlined.Archive,
              contentDescription = stringResource(id = R.string.cd_back),
            )
          }
          IconButton(onClick = {}) {
            Icon(imageVector = Icons.Outlined.Delete, contentDescription = null)
          }
          IconButton(onClick = {}) {
            Icon(imageVector = Icons.Outlined.Mail, contentDescription = null)
          }
        },
      )
    },
    content = { paddingValues -> MessageDetailBody(modifier = Modifier.padding(paddingValues)) },
  )
}
