package com.adwi.betty.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.adwi.betty.ui.components.BettyOddList
import com.adwi.betty.ui.components.BettyScaffold
import com.adwi.betty.ui.main.MainViewModel

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    viewModel: MainViewModel
) {
    val odds by viewModel.odds.collectAsState()
    val progress by viewModel.progress.collectAsState()

    BettyScaffold(
        viewModel = viewModel
    ) {
        BettyOddList(
            progress = progress,
            onRefresh = { viewModel.onManualRefresh() },
            list = odds,
            modifier = Modifier.fillMaxSize()
        )
    }
}