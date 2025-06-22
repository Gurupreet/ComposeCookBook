package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SegmentedButtons() {
  Column(
    modifier = Modifier.fillMaxWidth().padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    var selectedOption by remember { mutableStateOf(0) }
    val options = listOf("Option 1", "Option 2", "Option 3")

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      options.forEachIndexed { index, option ->
        FilterChip(
          selected = selectedOption == index,
          onClick = { selectedOption = index },
          label = { Text(option) },
          leadingIcon =
            if (selectedOption == index) {
              { Icon(Icons.Default.Done, contentDescription = null) }
            } else null
        )
      }
    }

    // Icon Only Segmented Buttons
    var selectedIconOption by remember { mutableStateOf(0) }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      repeat(3) { index ->
        FilterChip(
          selected = selectedIconOption == index,
          onClick = { selectedIconOption = index },
          label = {},
          leadingIcon = { Icon(Icons.Default.Android, contentDescription = null) }
        )
      }
    }

    // Single Choice Segmented Button
    var selected by remember { mutableStateOf("Option 1") }
    SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
      options.forEach { option ->
        SegmentedButton(
          selected = selected == option,
          onClick = { selected = option },
          shape =
            SegmentedButtonDefaults.itemShape(
              index = options.indexOf(option),
              count = options.size
            ),
        ) {
          Text(option)
        }
      }
    }
  }
}
