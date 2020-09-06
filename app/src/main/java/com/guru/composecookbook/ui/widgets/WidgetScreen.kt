package com.guru.composecookbook.ui.widgets

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

@Composable
fun WidgetScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "All Material Widgets") },
                elevation = 8.dp
            )
        },
        bodyContent = {
            WidgetScreenContent()
        }
    )
}

@Composable
fun WidgetScreenContent() {
    ScrollableColumn {
        Column {
            AllButtons()
            Chips()
            TextInputs()
            Loaders()
            Toggles()
            SnackBars()
            UICards()
        }
    }
}


@Preview
@Composable
fun PreviewScreen() {
    WidgetScreen()
}