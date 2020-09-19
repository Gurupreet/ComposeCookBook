package com.guru.composecookbook.ui.cryptoappmvvm.ui.home

import androidx.compose.animation.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope.gravity
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.theme.blue
import com.guru.composecookbook.ui.cryptoappmvvm.Models.CryptoHomeUIState
import com.guru.composecookbook.ui.cryptoappmvvm.data.CryptoDemoDataProvider
import com.guru.composecookbook.ui.cryptoappmvvm.data.db.entities.Crypto
import com.guru.composecookbook.ui.cryptoappmvvm.ui.home.components.CryptoListItem
import com.guru.composecookbook.ui.cryptoappmvvm.ui.home.components.MyWalletCard
import com.guru.composecookbook.ui.demoui.spotify.data.SpotifyDataProvider
import com.guru.composecookbook.ui.utils.horizontalGradientBackground


@Composable
fun CryptoHomeScreen(onCryptoHomeEvents: (CryptoHomeEvents) -> Unit) {
    val viewModel: CryptoHomeViewModel = viewModel()
    val uiState by viewModel.viewStateFlow.collectAsState()
    val surfaceGradient = SpotifyDataProvider.spotifySurfaceGradient(isSystemInDarkTheme())
    val favCryptos by viewModel.favCryptoLiveData.observeAsState(emptyList())
    //TODO Pagnation: Right now we can't get scrollState info for lazyColumnfor.
    Scaffold(
        floatingActionButton = { CryptoFABButton(favCryptos.size) },
    ) {
        Column(modifier = Modifier.horizontalGradientBackground(surfaceGradient)) {
            MyWalletCard()
            CryptoList(uiState, favCryptos, onCryptoHomeEvents)
        }
    }
}

@Composable
fun CryptoFABButton(count: Int) {
    val animateRotationModifier = Modifier.drawLayer(
        //So on every count change this basically runs as we only add 1 at a time
        rotationX = animate(if (count % 2 == 0) 360f else 0f, tween(800))
    )
    ExtendedFloatingActionButton(
        text = { Text(text = "$count coins", modifier = animateRotationModifier) },
        onClick = {},
        backgroundColor = blue,
        icon = {
            Icon(
                asset = Icons.Filled.Favorite,
                tint = Color.Red,
                modifier = animateRotationModifier
            )
        }
    )
}

@Composable
fun CryptoList(
    uiState: CryptoHomeUIState,
    favCryptos: List<Crypto>,
    onCryptoHomeEvents: (CryptoHomeEvents) -> Unit
) {
    if (uiState.cryptos.isEmpty()) {
        CircularProgressIndicator(
            modifier = Modifier.gravity(Alignment.CenterHorizontally).padding(24.dp)
        )
    } else {
        LazyColumnFor(items = uiState.cryptos) {
            val isFav = favCryptos.contains(it)
            CryptoListItem(
                it,
                isFav,
                onCryptoHomeEvents
            )
        }
    }
}

@Preview
@Composable
fun PreviewCryptoHomeScreen() {
    val uiState = CryptoHomeUIState(CryptoDemoDataProvider.demoList, false)
    CryptoList(uiState = uiState, emptyList(), {})
}

