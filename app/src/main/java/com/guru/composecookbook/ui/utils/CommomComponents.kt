package com.guru.composecookbook.ui.utils

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope.gravity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.typography

@Composable
fun ComingSoon() {
    Column(modifier = Modifier.fillMaxWidth().gravity(Alignment.CenterHorizontally).padding(50.dp)) {
        Text(
            text = "Coming Soon",
            style = typography.h5,
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = "work in progress",
            style = typography.subtitle2,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}