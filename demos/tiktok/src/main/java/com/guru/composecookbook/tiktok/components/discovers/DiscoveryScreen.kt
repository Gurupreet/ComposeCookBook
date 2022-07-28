package com.guru.composecookbook.tiktok.components.discovers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme

@Composable
fun DiscoverScreen() {
    ComposeCookBookMaterial3Theme(darkTheme = false) {
        Surface {
            Column {
                SearchSection()
                LanesSection()
                Spacer(modifier = Modifier.height(400.dp))
            }
        }
    }
}
