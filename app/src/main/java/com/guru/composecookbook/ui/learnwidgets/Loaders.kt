package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.typography

@Composable
fun Loaders() {
    Text(text = "Progress bars", style = typography.h6, modifier = Modifier.padding(8.dp))

    Row(modifier = Modifier.padding(8.dp)) {
        LinearProgressIndicator()
        CircularProgressIndicator()
        CircularProgressIndicator(strokeWidth = 8.dp)
    }

    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator()
        Text(text = "Loading with text...", modifier = Modifier.padding(8.dp))
    }


}