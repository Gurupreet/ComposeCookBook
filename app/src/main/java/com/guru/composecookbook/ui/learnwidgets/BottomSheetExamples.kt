package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetExamples() {
  var showModalBottomSheet by remember { mutableStateOf(false) }

  Column(
    modifier = Modifier.fillMaxWidth().padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    // Modal Bottom Sheet
    Button(onClick = { showModalBottomSheet = true }) { Text("Show Modal Bottom Sheet") }

    if (showModalBottomSheet) {
      ModalBottomSheet(
        onDismissRequest = { showModalBottomSheet = false },
        dragHandle = { BottomSheetDefaults.DragHandle() }
      ) {
        Column(
          modifier = Modifier.fillMaxWidth().padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
          ListItem(
            headlineContent = { Text("Share") },
            leadingContent = { Icon(Icons.Default.Share, contentDescription = null) }
          )
          ListItem(
            headlineContent = { Text("Copy link") },
            leadingContent = { Icon(Icons.Default.Share, contentDescription = null) }
          )
          ListItem(
            headlineContent = { Text("Edit") },
            leadingContent = { Icon(Icons.Default.Share, contentDescription = null) }
          )
        }
      }
    }
  }
}
