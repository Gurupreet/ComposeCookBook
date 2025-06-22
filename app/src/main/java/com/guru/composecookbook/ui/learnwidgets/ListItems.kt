package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItems() {
  Column(
    modifier = Modifier.fillMaxWidth().padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    // One-line list item
    ListItem(
      headlineContent = { Text("One-line list item") },
      leadingContent = { Icon(Icons.Default.Star, contentDescription = null) }
    )

    Divider()

    // Two-line list item
    ListItem(
      headlineContent = { Text("Two-line list item") },
      supportingContent = { Text("Secondary text") },
      leadingContent = { Icon(Icons.Default.Favorite, contentDescription = null) },
      trailingContent = { Text("meta") }
    )

    Divider()

    // Three-line list item
    ListItem(
      headlineContent = { Text("Three-line list item") },
      overlineContent = { Text("OVERLINE") },
      supportingContent = { Text("Secondary text\nThird line of text") },
      leadingContent = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
      trailingContent = { Switch(checked = true, onCheckedChange = {}) }
    )
  }
}
