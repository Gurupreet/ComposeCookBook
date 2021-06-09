package com.guru.composecookbook.gmail.ui.create

import androidx.compose.foundation.background
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun CreateMessageMoreActionPopupMenu() {
    val expanded = remember { mutableStateOf(false) }
    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
        // toggle = iconButton,
        offset = DpOffset((-32).dp, (-32).dp),
        modifier = Modifier.background(MaterialTheme.colors.surface)
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
