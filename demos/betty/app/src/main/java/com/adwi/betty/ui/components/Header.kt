package com.adwi.betty.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@ExperimentalAnimationApi
@Composable
fun BettyToolBar(appName: String) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(bottom = 16.dp, top = 16.dp)
    ) {
        Text(
            text = appName,
            color = MaterialTheme.colors.onPrimary,
            fontSize = 48.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

// PREVIEWS ----------------------------------------------------------------------------------

@ExperimentalAnimationApi
@Preview(name = "BettyHeader")
@Composable
fun BettyHeaderPreview() {
    BettyToolBar(appName = "Betty")
}