package com.guru.composecookbook.cryptoapp.ui.detail.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.coil.rememberCoilPainter
import com.guru.composecookbook.charts.BarCharts
import com.guru.composecookbook.charts.LineChart
import com.guru.composecookbook.cryptoapp.data.db.models.Crypto
import com.guru.composecookbook.cryptoapp.ui.detail.CryptoDetailViewModel
import com.guru.composecookbook.cryptoapp.ui.detail.CryptoDetailViewModelFactory
import com.guru.composecookbook.cryptoapp.ui.internal.extensions.roundToTwoDecimals
import com.guru.composecookbook.cryptoapp.ui.internal.theme.Colors
import com.guru.composecookbook.theme.*
import com.guru.composecookbook.theme.modifiers.horizontalGradientBackground


@Composable
fun CryptoDetailScreen(crypto: Crypto, onBack: () -> Unit) {
    val surfaceGradient = Colors.cryptoSurfaceGradient(isSystemInDarkTheme())
    Scaffold(
        bottomBar = { CryptoBottomBar(onBack) },
        floatingActionButton = { CryptoFloatingActionButton() },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Box(modifier = Modifier.horizontalGradientBackground(surfaceGradient)) {
            val scrollState = rememberScrollState(0)
            CryptoTopSection(crypto, scrollState)
            Column(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .verticalScroll(state = scrollState)
            ) {
                Spacer(modifier = Modifier.height(200.dp))
                CryptoCharts(crypto)
                StatisticsSection(crypto)
                FavSection()
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Composable
fun CryptoTopSection(crypto: Crypto, scrollState: ScrollState) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .alpha(
                animateFloatAsState(
                    (1 - scrollState.value / 150)
                        .coerceIn(0, 1)
                        .toFloat()
                ).value
            )
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Row(modifier = Modifier.padding(top = 20.dp)) {
            Text(
                text = crypto.name,
                style = typography.h6,
                modifier = Modifier.padding(end = 8.dp)
            )
            Image(
                painter = rememberCoilPainter(request = crypto.image),
                modifier = Modifier.size(28.dp),
                contentDescription = null
            )
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
fun CryptoCharts(crypto: Crypto) {
    Card(
        modifier = Modifier.padding(vertical = 8.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Column {
            LineChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                yAxisValues = crypto.chartData,
                lineColors = if (crypto.dailyChange > 0) gradientGreenColors else gradientRedColors
            )
            Spacer(modifier = Modifier.height(10.dp))
            BarCharts(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                yAxisValues = crypto.chartData,
                barColors = gradientBluePurple,
                barWidth = 2f
            )
        }
    }
}

@Composable
fun CryptoBottomBar(onBack: () -> Unit) {
    BottomAppBar(
        cutoutShape = CircleShape
    ) {
        IconButton(onClick = onBack) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
        }
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
        }
    }
}

@Composable
fun CryptoFloatingActionButton() {
    var pressed by remember { mutableStateOf(false) }
    ExtendedFloatingActionButton(
        icon = { Icon(imageVector = Icons.Default.Add, contentDescription = "") },
        text = { Text(text = "Trade") },
        onClick = { pressed = !pressed },
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.width(animateDpAsState(if (pressed) 200.dp else 120.dp).value)
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
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
    val viewModel: CryptoDetailViewModel = viewModel(
        factory = CryptoDetailViewModelFactory(LocalContext.current)
    )
    val favCryptos by viewModel.favCryptoLiveData.observeAsState(emptyList())
    if (favCryptos.isNotEmpty()) {
        Text(
            text = "Favorite Cryptos",
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
            style = typography.h5
        )
        LazyRow {
            items(
                items = favCryptos,
                itemContent = { FavoriteCryptoCard(crypto = it) })
        }
    }
}
