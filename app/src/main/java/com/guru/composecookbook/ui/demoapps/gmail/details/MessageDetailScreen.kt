package com.guru.composecookbook.ui.demoapps.gmail.details

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

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
                        Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = null)
                    }

                },
                actions = {

                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Outlined.Archive, contentDescription = null)
                    }
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Outlined.Delete, contentDescription = null)
                    }

                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Outlined.Mail, contentDescription = null)

                    }

                    TopBarMoreActionPopupMenu()
                }
            )
        },

        bodyContent = { MessageDetailBody() }
    )

}

@Composable
fun TopBarMoreActionPopupMenu() {

    val expanded = remember { mutableStateOf(false) }

    val iconButton = @Composable {
        IconButton(onClick = { expanded.value = true }) {
            Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = null)
        }
    }

    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
        toggle = iconButton,
        dropdownOffset = DpOffset((-32).dp, (-32).dp),
        dropdownModifier = Modifier.background(Color.White)
    ) {
        DropdownMenuItem(onClick = { /* Handle refresh! */ }) {
            Text("Move To")
        }
        DropdownMenuItem(onClick = { /* Handle settings! */ }) {
            Text("Print")
        }

        DropdownMenuItem(onClick = { /* Handle send feedback! */ }) {
            Text("Report Spam")
        }

        DropdownMenuItem(onClick = { /* Handle send feedback! */ }) {
            Text("Help and feedback")
        }
    }
}
