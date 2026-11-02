package com.guru.composecookbook.gmail.ui.create

import androidx.compose.foundation.background
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
    modifier = Modifier.background(MaterialTheme.colorScheme.surface),
  ) {
    DropdownMenuItem(onClick = { /* Handle refresh! */ }, text = { Text("Schedule send") })
    DropdownMenuItem(onClick = { /* Handle settings! */ }, text = { Text("Add from Contacts") })

    DropdownMenuItem(
      onClick = { /* Handle send feedback! */ },
      text = { Text("Confidential mode") },
    )

    DropdownMenuItem(
      onClick = { /* Handle send feedback! */ },
      enabled = false,
      text = { Text("Save draft") },
    )

    DropdownMenuItem(onClick = { /* Handle send feedback! */ }, text = { Text("Discard") })

    DropdownMenuItem(onClick = { /* Handle send feedback! */ }, text = { Text("Settings") })

    DropdownMenuItem(
      onClick = { /* Handle send feedback! */ },
      text = { Text("Help and feedback") },
    )
  }
}
