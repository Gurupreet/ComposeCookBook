package com.guru.composecookbook.ui.demoapps.cryptoappmvvm.ui.home

import android.animation.ValueAnimator
import android.content.Context
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.airbnb.lottie.LottieAnimationView
import com.guru.composecookbook.theme.blue
import com.guru.composecookbook.theme.graySurface
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.demoapps.cryptoappmvvm.data.CryptoDemoDataProvider
import com.guru.composecookbook.ui.demoapps.cryptoappmvvm.data.db.entities.Crypto
import com.guru.composecookbook.ui.demoapps.cryptoappmvvm.ui.home.components.CryptoListItem
import com.guru.composecookbook.ui.demoapps.cryptoappmvvm.ui.home.components.MyWalletCard
import com.guru.composecookbook.ui.demoapps.spotify.data.SpotifyDataProvider
import com.guru.composecookbook.ui.utils.horizontalGradientBackground
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@Composable
fun CryptoHomeScreen(onCryptoHomeInteractionEvents: (CryptoHomeInteractionEvents) -> Unit = {}) {
    val viewModel: CryptoHomeViewModel = viewModel()
    val surfaceGradient = SpotifyDataProvider.spotifySurfaceGradient(isSystemInDarkTheme())
    val favCryptos by viewModel.favCryptoLiveData.observeAsState(emptyList())
    var showFavState by remember { mutableStateOf(false) }
    val listScrollState = rememberLazyListState()
    val pagingItems = viewModel.getAllCryptos().collectAsLazyPagingItems()
    Scaffold(
        floatingActionButton = {
            CryptoFABButton(favCryptos.size) {
                showFavState = !showFavState
            }
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .horizontalGradientBackground(surfaceGradient)
        ) {
            MyWalletCard()
            ShowFavorites(
                showFave = showFavState,
                favCryptos = favCryptos,
                onCryptoHomeInteractionEvents
            )
            CryptoList(pagingItems, favCryptos, listScrollState, onCryptoHomeInteractionEvents)
        }
    }
}

@Composable
fun CryptoFABButton(count: Int, showFavState: () -> Unit) {
    val animateRotationModifier = Modifier.graphicsLayer(
        //So on every count change this basically runs as we only add 1 at a time
        rotationX = animateFloatAsState(if (count % 2 == 0) 360f else 0f, tween(800)).value
    )
    ExtendedFloatingActionButton(
        text = { Text(text = "$count coins", modifier = animateRotationModifier) },
        onClick = { showFavState.invoke() },
        backgroundColor = blue,
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
        modifier = Modifier
            .padding(8.dp)
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
                })
        }
    }
}

@Composable
fun FavoriteItem(crypto: Crypto, openCryptoDetail: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .background(graySurface)
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = openCryptoDetail)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CoilImage(data = crypto.image, modifier = Modifier.size(24.dp), contentDescription = null)
        Text(
            text = crypto.symbol,
            style = typography.h6.copy(fontSize = 20.sp),
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .height(32.dp),
            color = MaterialTheme.colors.onSurface,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CryptoList(
    pagingItems: LazyPagingItems<Crypto>,
    favCryptos: List<Crypto>,
    listScrollState: LazyListState,
    onCryptoHomeInteractionEvents: (CryptoHomeInteractionEvents) -> Unit
) {
    val context = LocalContext.current

    LazyColumn(state = listScrollState) {
        itemsIndexed(pagingItems) { index, crypto ->
            crypto?.let {
                val isFav = favCryptos.contains(crypto)
                CryptoListItem(
                    crypto,
                    isFav,
                    onCryptoHomeInteractionEvents
                )
            }
        }

        pagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> item {
                    LottieLoadingView(context = context)
                }
                loadState.append is LoadState.Loading -> {
                    item { LottieLoadingView(context = context) }
                }
            }
        }
    }
    //reload if first visible item is 10 positions away
}

@Composable
fun LottieLoadingView(context: Context) {
    val lottieView = remember {
        LottieAnimationView(context).apply {
            setAnimation("cryptoload.json")
            repeatCount = ValueAnimator.INFINITE
        }
    }
    AndroidView(
        { lottieView }, modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        it.playAnimation()
    }
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

