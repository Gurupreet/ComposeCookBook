package com.guru.composecookbook.ui.cryptoappmvvm.ui.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope.gravity
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.ui.cryptoappmvvm.Models.CryptoHomeUIState
import com.guru.composecookbook.ui.cryptoappmvvm.data.CryptoDemoDataProvider
import com.guru.composecookbook.ui.cryptoappmvvm.ui.home.components.CryptoListItem
import com.guru.composecookbook.ui.cryptoappmvvm.ui.home.components.MyWalletCard
import com.guru.composecookbook.ui.demoui.spotify.data.SpotifyDataProvider
import com.guru.composecookbook.ui.utils.horizontalGradientBackground


@Composable
fun CryptoHomeScreen(onCryptoHomeEvents: (CryptoHomeEvents) -> Unit) {
    val viewModel: CryptoHomeViewModel = viewModel()
    val uiState by viewModel.viewStateFlow.collectAsState()
    val surfaceGradient = SpotifyDataProvider.spotifySurfaceGradient(isSystemInDarkTheme())

    Surface {
        Column(modifier = Modifier.horizontalGradientBackground(surfaceGradient)) {
            MyWalletCard()
            CryptoList(uiState, onCryptoHomeEvents)
        }
    }
}

@Composable
fun CryptoList(uiState: CryptoHomeUIState, onCryptoHomeEvents: (CryptoHomeEvents) -> Unit) {
    if (uiState.cryptos.isEmpty()) {
        CircularProgressIndicator(
            modifier = Modifier.gravity(Alignment.CenterHorizontally).padding(24.dp)
        )
    } else {
        LazyColumnFor(items = uiState.cryptos) {
            CryptoListItem(
                it,
                onCryptoHomeEvents
            )
        }
    }
}

@Preview
@Composable
fun PreviewCryptoHomeScreen() {
    val uiState = CryptoHomeUIState(CryptoDemoDataProvider.demoList, false)
    CryptoList(uiState = uiState, {})
}

