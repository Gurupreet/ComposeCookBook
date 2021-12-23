package com.adwi.betty.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.adwi.betty.R
import com.adwi.betty.ui.main.MainViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BettyScaffold(
    viewModel: MainViewModel,
    content: @Composable (PaddingValues) -> Unit
) {

    val scaffoldState = rememberScaffoldState()

    val snackbarMessage by viewModel.snackBarMessage.collectAsState("")
    if (snackbarMessage.isNotEmpty()) {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(snackbarMessage)
            viewModel.snackBarMessage.value = ""
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            BettyToolBar(appName = stringResource(id = R.string.app_name))
        },
        content = content
    )
}