package com.guru.composecookbook.ui.cryptoappmvvm.ui.home

import androidx.compose.animation.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.gravity
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.drawLayer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.theme.*
import com.guru.composecookbook.ui.cryptoappmvvm.Models.CryptoHomeUIState
import com.guru.composecookbook.ui.cryptoappmvvm.data.CryptoDemoDataProvider
import com.guru.composecookbook.ui.cryptoappmvvm.data.db.entities.Crypto
import com.guru.composecookbook.ui.cryptoappmvvm.ui.home.components.CryptoListItem
import com.guru.composecookbook.ui.cryptoappmvvm.ui.home.components.MyWalletCard
import com.guru.composecookbook.ui.demoui.spotify.data.SpotifyDataProvider
import com.guru.composecookbook.ui.utils.horizontalGradientBackground
import dev.chrisbanes.accompanist.coil.CoilImage


@Composable
fun CryptoHomeScreen(onCryptoHomeEvents: (CryptoHomeEvents) -> Unit) {
    val viewModel: CryptoHomeViewModel = viewModel()
    val uiState by viewModel.viewStateFlow.collectAsState()
    val surfaceGradient = SpotifyDataProvider.spotifySurfaceGradient(isSystemInDarkTheme())
    val favCryptos by viewModel.favCryptoLiveData.observeAsState(emptyList())
    var showFavState by remember { mutableStateOf(false) }
    //TODO Pagnation: Right now we can't get scrollState info for lazyColumnfor.
    Scaffold(
        floatingActionButton = {
            CryptoFABButton(favCryptos.size) {
                showFavState = !showFavState
            }
        },
    ) {
        Column(modifier = Modifier.horizontalGradientBackground(surfaceGradient)) {
            MyWalletCard()
            ShowFavorites(showFave = showFavState, favCryptos = favCryptos)
            CryptoList(uiState, favCryptos, onCryptoHomeEvents)
        }
    }
}

@Composable
fun CryptoFABButton(count: Int, showFavState: () -> Unit) {
    val animateRotationModifier = Modifier.drawLayer(
        //So on every count change this basically runs as we only add 1 at a time
        rotationX = animate(if (count % 2 == 0) 360f else 0f, tween(800))
    )
    ExtendedFloatingActionButton(
        text = { Text(text = "$count coins", modifier = animateRotationModifier) },
        onClick = { showFavState.invoke() },
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
fun ShowFavorites(showFave: Boolean, favCryptos: List<Crypto>) {
    Column(
        modifier = Modifier.padding(8.dp)
            .fillMaxWidth()
            .height(animate(if (showFave && favCryptos.isNotEmpty()) 100.dp else 1.dp))
    ) {
        Text(text = "My Favorites", modifier = Modifier.padding(8.dp))
        LazyRowFor(items = favCryptos) {
            FavoriteItem(crypto = it)
        }
    }
}

@Composable
fun FavoriteItem(crypto: Crypto) {
    Row(
        modifier = Modifier.padding(16.dp)
            .background(graySurface)
            .clip(RoundedCornerShape(8.dp))
            .padding(16.dp),
    ) {
        CoilImage(data = crypto.image, modifier = Modifier.size(24.dp))
        Text(
            text = crypto.symbol,
            modifier = Modifier.padding(horizontal = 8.dp).height(24.dp),
            color = MaterialTheme.colors.onSurface,
            textAlign = TextAlign.Center
        )
    }
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
    val crypto = CryptoDemoDataProvider.bitcoin
    Column {
        FavoriteItem(crypto = crypto)
        CryptoList(uiState = uiState, emptyList(), {})
    }
}

