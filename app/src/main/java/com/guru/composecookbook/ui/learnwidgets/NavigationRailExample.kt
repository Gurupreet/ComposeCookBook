package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NavigationRailExample() {
  var selectedItem by remember { mutableStateOf(0) }
  val items = listOf("Home", "Search", "Profile")
  val icons = listOf(Icons.Default.Home, Icons.Default.Search, Icons.Default.Person)

  Row(modifier = Modifier.fillMaxWidth().height(350.dp)) {
    NavigationRail(
      modifier = Modifier.fillMaxHeight(),
      containerColor = MaterialTheme.colorScheme.inverseOnSurface,
      header = {
        FloatingActionButton(onClick = {}, modifier = Modifier.padding(vertical = 8.dp)) {
          Icon(Icons.Default.Add, contentDescription = "Add")
        }
      }
    ) {
      items.forEachIndexed { index, item ->
        NavigationRailItem(
          selected = selectedItem == index,
          onClick = { selectedItem = index },
          icon = { Icon(icons[index], contentDescription = item) },
          label = { Text(item) }
        )
      }
    }

    // Example content
    Box(modifier = Modifier.fillMaxSize().padding(8.dp), contentAlignment = Alignment.Center) {
      Text(text = "Selected: ${items[selectedItem]}", style = MaterialTheme.typography.titleLarge)
    }
  }
}
