package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WidgetScreen() {
    Scaffold(
        modifier = Modifier.semantics { testTag = "Widget Screen" },
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
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            AllButtons()
            Chips()
            TextDemo()
            TextInputs()
            Loaders()
            Toggles()
            AppBars()
            SnackBars()
            UICards()
        }
}


@Preview
@Composable
fun PreviewScreen() {
    WidgetScreen()
}