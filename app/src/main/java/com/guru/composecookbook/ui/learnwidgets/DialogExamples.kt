package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogExamples() {
  Column(
    modifier = Modifier.fillMaxWidth().padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    var showBasicDialog by remember { mutableStateOf(false) }
    var showConfirmationDialog by remember { mutableStateOf(false) }
    var showAlertDialog by remember { mutableStateOf(false) }

    // Basic Dialog
    Button(onClick = { showBasicDialog = true }) { Text("Show Basic Dialog") }

    if (showBasicDialog) {
      AlertDialog(
        onDismissRequest = { showBasicDialog = false },
        content = {
          Surface(
            modifier = Modifier.wrapContentWidth().wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = 6.dp
          ) {
            Column(modifier = Modifier.padding(16.dp)) {
              Text(text = "Basic Dialog", style = MaterialTheme.typography.headlineSmall)
              Spacer(modifier = Modifier.height(16.dp))
              Text(text = "This is a basic dialog with custom content.")
              Spacer(modifier = Modifier.height(24.dp))
              TextButton(
                onClick = { showBasicDialog = false },
                modifier = Modifier.align(Alignment.End)
              ) {
                Text("Dismiss")
              }
            }
          }
        }
      )
    }

    // Confirmation Dialog
    Button(onClick = { showConfirmationDialog = true }) { Text("Show Confirmation Dialog") }

    if (showConfirmationDialog) {
      AlertDialog(
        onDismissRequest = { showConfirmationDialog = false },
        title = { Text("Confirmation") },
        text = { Text("Are you sure you want to proceed?") },
        confirmButton = {
          TextButton(onClick = { showConfirmationDialog = false }) { Text("Confirm") }
        },
        dismissButton = {
          TextButton(onClick = { showConfirmationDialog = false }) { Text("Cancel") }
        }
      )
    }

    // Alert Dialog with Icon
    Button(onClick = { showAlertDialog = true }) { Text("Show Alert Dialog") }

    if (showAlertDialog) {
      AlertDialog(
        onDismissRequest = { showAlertDialog = false },
        icon = { Icon(Icons.Default.Add, contentDescription = null) },
        title = { Text("Alert") },
        text = { Text("This is an important alert message.") },
        confirmButton = { TextButton(onClick = { showAlertDialog = false }) { Text("OK") } }
      )
    }
  }
}
