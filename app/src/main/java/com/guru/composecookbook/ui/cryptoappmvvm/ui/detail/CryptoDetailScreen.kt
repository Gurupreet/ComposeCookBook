package com.guru.composecookbook.ui.cryptoappmvvm.ui.detail

import androidx.compose.animation.animate
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ReadMore
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.theme.green700
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.cryptoappmvvm.data.db.entities.Crypto
import com.guru.composecookbook.ui.cryptoappmvvm.utils.roundToTwoDecimals
import com.guru.composecookbook.ui.demoui.spotify.data.SpotifyDataProvider
import com.guru.composecookbook.ui.lists.VerticalListItemSmall
import com.guru.composecookbook.ui.utils.horizontalGradientBackground
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun CryptoDetailScreen(crypto: Crypto, onBack: () -> Unit) {
    val surfaceGradient = SpotifyDataProvider.spotifySurfaceGradient(isSystemInDarkTheme())
    Scaffold(
        bottomBar = { CryptoBottomBar() },
        floatingActionButton = { CryptoFloatingActionButton() },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center
    ) {
        val scrollState = rememberScrollState(0f)
        Stack(modifier = Modifier.horizontalGradientBackground(surfaceGradient)) {
            CryptoTopSection(crypto, scrollState, onBack)
            ScrollableColumn(
                modifier = Modifier
                    .padding(top = 34.dp, start = 16.dp, end = 16.dp), scrollState = scrollState
            ) {
                Spacer(modifier = Modifier.height(150.dp))
                //TODO: Charts
                StatisticsSection(crypto)
                FavSection()
                NewsSection(crypto)
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Composable
fun CryptoTopSection(crypto: Crypto, scrollState: ScrollState, onBack: () -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp)
            .drawOpacity(animate((1 - scrollState.value / 150).coerceIn(0f, 1f)))
    ) {
        Spacer(modifier = Modifier.height(25.dp))
        Icon(
            asset = Icons.Default.ArrowBack,
            modifier = Modifier.clickable(onClick = onBack).padding(vertical = 8.dp)
        )
        Row(modifier = Modifier.padding(top = 20.dp)) {
            Text(
                text = crypto.name,
                style = typography.h6,
                modifier = Modifier.padding(end = 8.dp)
            )
            CoilImage(data = crypto.image, modifier = Modifier.preferredSize(28.dp))
        }

        Text(
            text = "${crypto.price.roundToTwoDecimals()} USD",
            style = typography.h6.copy(fontWeight = FontWeight.ExtraBold)
        )
        Text(
            text = "${crypto.dailyChange.roundToTwoDecimals()} " +
                    " (${crypto.dailyChangePercentage.roundToTwoDecimals()}%) Today",
            color = if (crypto.dailyChange > 0) green700 else Color.Red
        )
    }
}

@Composable
fun CryptoBottomBar() {
    BottomAppBar(
        cutoutShape = CircleShape
    ) {
        IconButton(onClick = {}) {
            Icon(asset = Icons.Default.ReadMore)
        }
        IconButton(onClick = {}) {
            Icon(asset = Icons.Default.MoreVert)
        }
    }
}

@Composable
fun CryptoFloatingActionButton() {
    var pressed by remember { mutableStateOf(false) }
    ExtendedFloatingActionButton(
        icon = { Icon(asset = Icons.Default.Add) },
        text = { Text(text = "Trade") },
        onClick = { pressed = !pressed },
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.width(animate(if (pressed) 200.dp else 120.dp))
    )
}

@Composable
fun StatisticsSection(crypto: Crypto) {
    val valueModifier = Modifier.padding(bottom = 16.dp, top = 4.dp)
    Text(
        text = "Statistics",
        modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
        style = typography.h5
    )
    Card(
        modifier = Modifier.padding(vertical = 8.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "24 High", style = typography.subtitle2)
                Text(text = crypto.high.roundToTwoDecimals(), modifier = valueModifier)
                Text(text = "24 Low", style = typography.subtitle2)
                Text(text = crypto.high.roundToTwoDecimals(), modifier = valueModifier)
                Text(text = "24h Change", style = typography.subtitle2)
                Text(text = crypto.dailyChange.roundToTwoDecimals(), modifier = valueModifier)
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Volume", style = typography.subtitle2)
                Text(text = crypto.volume.toString(), modifier = valueModifier)
                Text(text = "Supply", style = typography.subtitle2)
                Text(text = crypto.supply.toString(), modifier = valueModifier)
                Text(text = "Market Cap", style = typography.subtitle2)
                Text(text = crypto.marketCap.toString(), modifier = valueModifier)
            }
        }
    }
}

@Composable
fun FavSection() {
    val viewModel: CryptoDetailViewModel = viewModel()
    val favCryptos by viewModel.favCryptoLiveData.observeAsState(emptyList())
    if (favCryptos.isNotEmpty()) {
        Text(
            text = "Favorite Cryptos",
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
            style = typography.h5
        )
        LazyRowFor(items = favCryptos) {
            FavoriteCryptoCard(crypto = it)
        }
    }
}

@Composable
fun NewsSection(crypto: Crypto) {
    //TODO Add some crypto news api
    val valueModifier = Modifier.padding(bottom = 16.dp, top = 4.dp)
    Text(
        text = "Recent News",
        modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
        style = typography.h5
    )
    Card(
        modifier = Modifier.padding(vertical = 8.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Column {
            val demoItem = DemoDataProvider.item
            VerticalListItemSmall(item = demoItem)
            VerticalListItemSmall(item = demoItem)
            VerticalListItemSmall(item = demoItem)
            Text(
                text = "See More",
                modifier = Modifier.gravity(Alignment.End).clickable(onClick = {}).padding(16.dp)
            )
        }

    }
}