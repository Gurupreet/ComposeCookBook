package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.guru.composecookbook.ui.utils.TestTags

/**
 * Composable function `WidgetScreen` displays a screen scaffolded with a top app bar and a scrollable list of material widgets.
 * Uses ExperimentalMaterial3Api for experimental Material3 components.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WidgetScreen() {
    // Scaffold with a top app bar and content
    Scaffold(
        modifier = Modifier.testTag(TestTags.WIDGET_SCREEN_ROOT),
        topBar = {
            SmallTopAppBar(
                title = { Text(text = "All Material Widgets") },
            )
        },
        content = { paddingValues ->
            WidgetScreenContent(
                modifier = Modifier.padding(paddingValues)
            )
        }
    )
}


/**
 * Composable function `WidgetScreenContent` displays a lazy column containing various material widgets.
 * @param modifier Modifier for layout customization.
 */
@Composable
fun WidgetScreenContent(
    modifier: Modifier = Modifier,
) {
    // LazyColumn for displaying a scrollable list of widgets
    LazyColumn(
        state = rememberLazyListState(),
        modifier = modifier,
    ) {
        item { AllButtons() }
        item { Chips() }
        item { TextDemo() }
        item { TextInputs() }
        item { Loaders() }
        item { Toggles() }
        item { AppBars() }
        item { SnackBars() }
        item { UICards() }
    }
}


/**
 * Preview function for the WidgetScreen content.
 */
@Preview
@Composable
fun PreviewScreen() {
    WidgetScreen()
}