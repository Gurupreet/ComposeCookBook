package com.guru.composecookbook.gmail.ui.details

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.guru.composecookbook.gmail.R

@Composable
fun MessageDetailScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                backgroundColor = Color.White,
                contentColor = Color.Black,
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
                        Icon(
                            imageVector = Icons.Outlined.Archive,
                            contentDescription = stringResource(id = R.string.cd_back)
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Outlined.Delete, contentDescription = null)
                    }
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Outlined.Mail, contentDescription = null)

                    }
                }
            )
        },
        content = { paddingValues ->
            MessageDetailBody(
                modifier = Modifier.padding(paddingValues),
            )
        }
    )
}
