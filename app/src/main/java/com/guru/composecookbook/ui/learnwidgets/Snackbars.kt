package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.typography

/**
 * Composable function demonstrating usage of Snackbar in Jetpack Compose.
 */
@Composable
fun SnackBars() {
    Text(text = "Snackbars", style = typography.h6, modifier = Modifier.padding(8.dp))

    // Basic Snackbar without an action
    Snackbar(modifier = Modifier.padding(4.dp)) {
        Text(text = "This is a basic snackbar")
    }

    // Snackbar with an action item
    Snackbar(
        modifier = Modifier.padding(4.dp),
        action = {
            TextButton(onClick = {}) {
                Text(text = "Remove")
            }
        }
    ) {
        Text(text = "This is a basic Snackbar with action item")
    }

    // Snackbar with action item placed below text
    Snackbar(
        modifier = Modifier.padding(4.dp),
        actionOnNewLine = true,
        action = {
            TextButton(onClick = {}) {
                Text(text = "Remove")
            }
        }
    ) {
        Text(text = "Snackbar with action item below text")
    }
}


/**
 * Preview function for the SnackBars content.
 */
@Preview
@Composable
fun ShowSnackbars() {
    Column {
        SnackBars()
    }
}