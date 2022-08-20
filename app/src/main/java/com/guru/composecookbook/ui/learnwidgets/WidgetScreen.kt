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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WidgetScreen() {
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

@Composable
fun WidgetScreenContent(
    modifier: Modifier = Modifier,
) {
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


@Preview
@Composable
fun PreviewScreen() {
    WidgetScreen()
}