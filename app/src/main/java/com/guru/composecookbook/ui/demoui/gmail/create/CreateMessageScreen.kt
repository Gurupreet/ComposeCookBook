package com.guru.composecookbook.ui.demoui.gmail.create

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Attachment
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Position
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun CreateMessageScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Compose") },
                backgroundColor = Color.White,
                contentColor = Color.Black,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(asset = Icons.Outlined.ArrowBack)
                    }

                },
                actions = {

                    IconButton(onClick = {}) {
                        Icon(asset = Icons.Outlined.Attachment)
                    }
                    IconButton(onClick = {}) {
                        Icon(asset = Icons.Outlined.Send)
                    }

                    CreateMessageMoreActionPopupMenu()
                }
            )
        },

        bodyContent = { CreateMessageBody(navController) },
    )

}

@Composable
fun CreateMessageMoreActionPopupMenu() {

    val expanded = remember { mutableStateOf(false) }

    val iconButton = @Composable {
        IconButton(onClick = { expanded.value = true }) {
            Icon(asset = Icons.Outlined.MoreVert)
        }
    }

    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
        toggle = iconButton,
        dropdownOffset = Position((-32).dp, (-32).dp),
        dropdownModifier = Modifier.background(Color.White)
    ) {
        DropdownMenuItem(onClick = { /* Handle refresh! */ }) {
            Text("Schedule send")
        }
        DropdownMenuItem(onClick = { /* Handle settings! */ }) {
            Text("Add from Contacts")
        }

        DropdownMenuItem(onClick = { /* Handle send feedback! */ }) {
            Text("Confidential mode")
        }

        DropdownMenuItem(onClick = { /* Handle send feedback! */ }, enabled = false) {
            Text("Save draft")
        }

        DropdownMenuItem(onClick = { /* Handle send feedback! */ }) {
            Text("Discard")
        }

        DropdownMenuItem(onClick = { /* Handle send feedback! */ }) {
            Text("Settings")
        }

        DropdownMenuItem(onClick = { /* Handle send feedback! */ }) {
            Text("Help and feedback")
        }


    }
}
