package com.guru.composecookbook.cryptoapp.ui.home.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.guru.composecookbook.cryptoapp.data.CryptoDemoDataProvider
import com.guru.composecookbook.cryptoapp.data.db.models.Crypto
import com.guru.composecookbook.cryptoapp.ui.home.CryptoHomeInteractionEvents
import com.guru.composecookbook.cryptoapp.ui.home.CryptoHomeViewModel
import com.guru.composecookbook.cryptoapp.ui.home.CryptoHomeViewModelFactory
import com.guru.composecookbook.cryptoapp.ui.internal.theme.Colors
import com.guru.composecookbook.lottie.LottieCryptoLoadingView
import com.guru.composecookbook.theme.blue
import com.guru.composecookbook.theme.graySurface
import com.guru.composecookbook.theme.modifiers.horizontalGradientBackground
import com.guru.composecookbook.theme.typography
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class, androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun CryptoHomeScreen(onCryptoHomeInteractionEvents: (CryptoHomeInteractionEvents) -> Unit = {}) {
  val viewModel: CryptoHomeViewModel =
    viewModel(factory = CryptoHomeViewModelFactory(LocalContext.current))
  val surfaceGradient = Colors.cryptoSurfaceGradient(isSystemInDarkTheme())
  val favCryptos by viewModel.favCryptoLiveData.observeAsState(emptyList())
  var showFavState by remember { mutableStateOf(false) }
  val listScrollState = rememberLazyListState()
  val cryptos = viewModel.getAllCryptos().collectAsLazyPagingItems()
  Scaffold(
    floatingActionButton = { CryptoFABButton(favCryptos.size) { showFavState = !showFavState } },
  ) { paddingValues ->
    Column(
      modifier =
        Modifier.padding(paddingValues).fillMaxSize().horizontalGradientBackground(surfaceGradient)
    ) {
      MyWalletCard()
      ShowFavorites(showFave = showFavState, favCryptos = favCryptos, onCryptoHomeInteractionEvents)
      CryptoList(cryptos, favCryptos, listScrollState, onCryptoHomeInteractionEvents)
    }
  }
}

@Composable
fun CryptoFABButton(count: Int, showFavState: () -> Unit) {
  val animateRotationModifier =
    Modifier.graphicsLayer(
      // So on every count change this basically runs as we only add 1 at a time
      rotationX = animateFloatAsState(if (count % 2 == 0) 360f else 0f, tween(800)).value
    )
  ExtendedFloatingActionButton(
    text = { Text(text = "$count coins", modifier = animateRotationModifier) },
    onClick = { showFavState.invoke() },
    containerColor = blue,
    icon = {
      Icon(
        imageVector = Icons.Filled.Favorite,
        tint = Color.Red,
        contentDescription = null,
        modifier = animateRotationModifier
      )
    }
  )
}

@Composable
fun ShowFavorites(
  showFave: Boolean,
  favCryptos: List<Crypto>,
  onCryptoHomeInteractionEvents: (CryptoHomeInteractionEvents) -> Unit
) {
  Column(
    modifier =
      Modifier.padding(8.dp)
        .fillMaxWidth()
        .height(animateDpAsState(if (showFave && favCryptos.isNotEmpty()) 100.dp else 1.dp).value)
  ) {
    Text(text = "My Favorites", modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
    LazyRow {
      items(
        items = favCryptos,
        itemContent = { crypto: Crypto ->
          FavoriteItem(crypto = crypto) {
            onCryptoHomeInteractionEvents(
              CryptoHomeInteractionEvents.OpenDetailScreen(crypto = crypto)
            )
          }
        }
      )
    }
  }
}

@Composable
fun FavoriteItem(crypto: Crypto, openCryptoDetail: () -> Unit) {
  Row(
    modifier =
      Modifier.padding(16.dp)
        .background(graySurface)
        .clip(RoundedCornerShape(8.dp))
        .clickable(onClick = openCryptoDetail)
        .padding(16.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Image(
      painter = rememberImagePainter(data = crypto.image),
      modifier = Modifier.size(24.dp),
      contentDescription = null
    )
    Text(
      text = crypto.symbol,
      style = typography.h6.copy(fontSize = 20.sp),
      modifier = Modifier.padding(horizontal = 8.dp).height(32.dp),
      color = MaterialTheme.colorScheme.onSurface,
      textAlign = TextAlign.Center
    )
  }
}

@Composable
fun CryptoList(
  cryptos: LazyPagingItems<Crypto>,
  favCryptos: List<Crypto>,
  listScrollState: LazyListState,
  onCryptoHomeInteractionEvents: (CryptoHomeInteractionEvents) -> Unit
) {
  val context = LocalContext.current

  LazyColumn(state = listScrollState) {
    items(
      count = cryptos.itemCount,
      key = { index -> cryptos[index]?.symbol ?: index.toString() }
    ) { index ->
      val crypto = cryptos[index]
      crypto?.let {
        val isFav = favCryptos.contains(crypto)
        CryptoListItem(crypto, isFav, onCryptoHomeInteractionEvents)
      }
    }

    cryptos.apply {
      when {
        loadState.refresh is LoadState.Loading ->
          item { LottieCryptoLoadingView(context = context) }
        loadState.append is LoadState.Loading -> {
          item { LottieCryptoLoadingView(context = context) }
        }
      }
    }
  }
  // reload if first visible item is 10 positions away
}

@Preview
@Composable
fun PreviewCryptoHomeScreen() {
  val crypto = CryptoDemoDataProvider.bitcoin
  Column {
    FavoriteItem(crypto = crypto, {})
    // CryptoList(uiState = uiState, emptyList(), {})
  }
}
