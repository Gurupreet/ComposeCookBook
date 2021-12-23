package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.guru.composecookbook.ui.utils.TestTags

@OptIn(ExperimentalMaterial3Api::class,
ExperimentalAnimationApi::class,
ExperimentalMaterialApi::class)
@Composable
fun WidgetScreen() {
    Scaffold(
        modifier = Modifier.testTag(TestTags.WIDGET_SCREEN_ROOT),
        topBar = {
            SmallTopAppBar(
                title = { Text(text = "All Material Widgets") },
            )
        },
        content = {
            WidgetScreenContent()
        }
    )
}

@OptIn(ExperimentalAnimationApi::class,
ExperimentalMaterialApi::class)
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


@OptIn(ExperimentalAnimationApi::class,
ExperimentalMaterialApi::class)
@Preview
@Composable
fun PreviewScreen() {
    WidgetScreen()
}