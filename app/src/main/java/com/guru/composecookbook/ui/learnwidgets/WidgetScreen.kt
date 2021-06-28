package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.ui.utils.TestTags

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun WidgetScreen() {
    Scaffold(
        modifier = Modifier.testTag(TestTags.WIDGET_SCREEN_ROOT),
        topBar = {
            TopAppBar(
                title = { Text(text = "All Material Widgets") },
                elevation = 8.dp
            )
        },
        content = {
            WidgetScreenContent()
        }
    )
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun WidgetScreenContent() {
    LazyColumn(state = rememberLazyListState()) {
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


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewScreen() {
    WidgetScreen()
}