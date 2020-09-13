package com.guru.composecookbook.ui.cryptoappmvvm.ui.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.gravity
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.viewinterop.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.ui.demoui.spotify.data.SpotifyDataProvider
import com.guru.composecookbook.ui.utils.horizontalGradientBackground


@Composable
fun CryptoHomeScreen() {
    val viewModel: CryptoHomeViewModel = viewModel()
    val uiState by viewModel.viewStateFlow.collectAsState()
    val surfaceGradient = SpotifyDataProvider.spotifySurfaceGradient(isSystemInDarkTheme())

    Surface {
        Column(modifier = Modifier.horizontalGradientBackground(surfaceGradient)) {
            MyWalletCard()
            Spacer(modifier = Modifier.height(8.dp))
            CryptoList(uiState)
        }
    }
}

@Composable
fun CryptoList(uiState: CryptoHomeUIState) {
    if (uiState.cryptos.isEmpty()) {
        CircularProgressIndicator(modifier = Modifier.gravity(Alignment.CenterHorizontally).padding(24.dp))
    } else {
        LazyColumnFor(items = uiState.cryptos) {
            CryptoListItem(crypto = it)
        }
    }
}
@Preview
@Composable
fun PreviewCryptoHomeScreen() {
    CryptoHomeScreen()
}

