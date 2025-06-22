package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BadgeExamples() {
  Column(
    modifier = Modifier.fillMaxWidth().padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Simple Badge
    BadgedBox(badge = { Badge { Text("8") } }) {
      Icon(Icons.Default.Notifications, contentDescription = "Notifications")
    }

    // Badge with different colors
    BadgedBox(
      badge = {
        Badge(
          containerColor = MaterialTheme.colorScheme.error,
          contentColor = MaterialTheme.colorScheme.onError,
        ) {
          Text("99+")
        }
      }
    ) {
      Icon(Icons.Default.Email, contentDescription = "Messages")
    }

    // Badge on Navigation Item
    NavigationBar {
      NavigationBarItem(
        icon = {
          BadgedBox(badge = { Badge { Text("12") } }) {
            Icon(Icons.Default.Notifications, contentDescription = "Notifications")
          }
        },
        label = { Text("Notifications") },
        selected = true,
        onClick = {}
      )
    }
  }
}
