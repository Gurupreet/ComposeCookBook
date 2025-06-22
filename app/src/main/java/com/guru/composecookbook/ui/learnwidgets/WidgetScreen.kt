package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.ui.utils.TestTags

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WidgetScreen(onBackPressed: () -> Unit = {}) {
  Scaffold(
    modifier = Modifier.testTag(TestTags.WIDGET_SCREEN_ROOT),
    topBar = {
      TopAppBar(
        title = { Text(text = "Material 3 Components") },
        navigationIcon = {
          IconButton(onClick = onBackPressed) { Icon(Icons.Default.ArrowBack, "Back") }
        },
        colors =
          TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
          )
      )
    }
  ) { paddingValues ->
    WidgetScreenContent(modifier = Modifier.padding(paddingValues))
  }
}

@Composable
fun WidgetScreenContent(
  modifier: Modifier = Modifier,
) {
  LazyColumn(
    state = rememberLazyListState(),
    modifier = modifier,
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(24.dp)
  ) {
    item { ComponentSection("Buttons", content = { AllButtons() }) }
    item { ComponentSection("Inputs", content = { TextInputs() }) }
    item { ComponentSection("Text & Typography", content = { TextDemo() }) }
    item { ComponentSection("Selection", content = { Chips() }) }
    item { ComponentSection("Progress", content = { Loaders() }) }
    item { ComponentSection("Navigation", content = { AppBars() }) }
    item { ComponentSection("Toggles & Switches", content = { Toggles() }) }
    item { ComponentSection("Containers", content = { UICards() }) }
    item { ComponentSection("Feedback", content = { SnackBars() }) }
    // New Material 3 Components
    item { ComponentSection("Search", content = { SearchBars() }) }
    item { ComponentSection("Lists", content = { ListItems() }) }
    item { ComponentSection("Dialogs", content = { DialogExamples() }) }
    item { ComponentSection("Navigation Rails", content = { NavigationRailExample() }) }
    item { ComponentSection("Bottom Sheets", content = { BottomSheetExamples() }) }
    item { ComponentSection("Segmented Buttons", content = { SegmentedButtons() }) }
    item { ComponentSection("Badges", content = { BadgeExamples() }) }
  }
}

@Composable
fun ComponentSection(title: String, content: @Composable () -> Unit) {
  Surface(
    modifier = Modifier.fillMaxWidth(),
    tonalElevation = 1.dp,
    shape = MaterialTheme.shapes.medium
  ) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
      Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(16.dp)
      )
      content()
    }
  }
}

@Preview
@Composable
fun PreviewScreen() {
  MaterialTheme { WidgetScreen() }
}
