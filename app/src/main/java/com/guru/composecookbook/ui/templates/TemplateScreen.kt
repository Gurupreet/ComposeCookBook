package com.guru.composecookbook.ui.templates

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.ui.utils.TestTags

/**
 * A Composable function that displays a screen with a list of templates in a LazyColumn.
 * Each item in the list is a button that, when clicked, starts the corresponding template activity.
 *
 * @param darkTheme A Boolean indicating whether the dark theme is enabled.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TemplateScreen(darkTheme: Boolean) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag(TestTags.TEMPLATE_SCREEN_ROOT)
    ) {
        items(templates.size) { index ->
            val template = templates[index]
            Button(
                onClick = {
                    context.startActivity(TemplatesActivity.newIntent(context, template, darkTheme))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(text = template, modifier = Modifier.padding(8.dp))
            }
        }
    }

}


/**
 * A list of template names to be displayed in the TemplateScreen.
 */
val templates = listOf(
    "Login",
    "Profiles",
    "On-boarding",
    "Charts",
    "Adding Payment Card",
    "Pin Lock/BioMetric",
    "Empty Screens",
    "Settings",
    "Loaders",
    "Canvas Drawing",
    "Animations",
    "Timer",
    "Clock View",
    "Cascade Menu",
)