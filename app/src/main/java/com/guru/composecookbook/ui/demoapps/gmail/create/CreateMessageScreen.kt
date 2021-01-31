package com.guru.composecookbook.ui.demoapps.gmail.create

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
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

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
                        Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = null)
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

        bodyContent = { CreateMessageBody(navController) },
    )

}

@Composable
fun CreateMessageMoreActionPopupMenu() {

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
        dropdownModifier = Modifier.background(MaterialTheme.colors.surface)
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
