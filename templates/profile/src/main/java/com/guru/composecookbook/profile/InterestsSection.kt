package com.guru.composecookbook.profile

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.tags.InterestTag
import com.guru.composecookbook.theme.typography

@Composable
fun InterestsSection() {
    Text(
        text = "My Interests",
        style = typography.h6,
        modifier = Modifier.padding(start = 8.dp, top = 16.dp)
    )
    Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp))
    Row(modifier = Modifier.padding(start = 8.dp, top = 8.dp)) {
        InterestTag("Android")
        InterestTag("Compose")
        InterestTag("Flutter")
        InterestTag("SwiftUI")
    }
    Row(modifier = Modifier.padding(start = 8.dp)) {
        InterestTag("Video games")
        InterestTag("Podcasts")
        InterestTag("Basketball")
    }
}
