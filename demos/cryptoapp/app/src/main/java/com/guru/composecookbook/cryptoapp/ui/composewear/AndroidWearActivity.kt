package com.guru.composecookbook.cryptoapp.ui.composewear

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.wear.compose.material.*
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import coil.compose.rememberImagePainter
import com.guru.composecookbook.charts.LineChart
import com.guru.composecookbook.cryptoapp.data.db.models.Crypto
import com.guru.composecookbook.cryptoapp.ui.home.CryptoHomeViewModel
import com.guru.composecookbook.cryptoapp.ui.home.CryptoHomeViewModelFactory
import com.guru.composecookbook.cryptoapp.ui.internal.extensions.roundToTwoDecimals
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme
import com.guru.composecookbook.theme.gradientGreenColors
import com.guru.composecookbook.theme.gradientRedColors
import com.guru.composecookbook.theme.green500

const val SYMBOL_ID = "symbolId"
class AndroidWearActivity : ComponentActivity() {

    @OptIn(ExperimentalWearMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCookBookMaterial3Theme(true) {
                WearStockApp()
            }

        }

    }

    @ExperimentalWearMaterialApi
    @Composable
    fun WearStockApp() {
        val viewModel: CryptoHomeViewModel = viewModel(factory = CryptoHomeViewModelFactory(LocalContext.current))
        val pagingItems = viewModel.getAllCryptos().collectAsLazyPagingItems()
        val scrollState = rememberScalingLazyListState()

        Scaffold(
            positionIndicator = {
                PositionIndicator(scalingLazyListState = scrollState)
            },
            vignette = {
                Vignette(vignettePosition = VignettePosition.TopAndBottom)
            }
        ) {
            val navController = rememberSwipeDismissableNavController()
            SwipeDismissableNavHost(
                navController = navController,
                startDestination = WearScreen.StockList.route
            ) {
                composable(route = WearScreen.StockList.route) {
                   StockListScreen(scrollState = scrollState, items = pagingItems) { symbol ->
                       navController.navigate(WearScreen.StockDetail.route+"/${symbol}")
                   }
                }
                composable(
                    route = WearScreen.StockDetail.route+"/{$SYMBOL_ID}",
                    arguments = listOf(navArgument(SYMBOL_ID) { type = NavType.StringType })
                ) { backStackEntry ->
                    val symbol = backStackEntry.arguments?.getString(SYMBOL_ID) ?: "btc"
                    val crypto = pagingItems.itemSnapshotList.items.firstOrNull { it.symbol == symbol }
                    crypto?.let {
                        StockDetailScreen(crypto = it)
                    }
                }
            }
        }
    }
}


@Composable
fun StockListScreen(scrollState: ScalingLazyListState, items: LazyPagingItems<Crypto>, onItemSelected: (String) -> Unit) {
    ScalingLazyColumn(
        state = scrollState,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 8.dp)) {
        item {
            Text(
                "Cookbook wear",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
        items(items.itemCount) {
            val crypto = items[it]!!
            WearStockListItem(crypto, onItemSelected)
        }
    }
}

@Composable
fun WearStockListItem(crypto: Crypto, onItemSelected: (String) -> Unit) {
    Card(onClick = { onItemSelected.invoke(crypto.symbol) }, shape = MaterialTheme.shapes.small) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberImagePainter(data = crypto.image),
                modifier = Modifier
                    .size(32.dp)
                    .padding(4.dp),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(horizontal = 4.dp)) {
                Text(text = crypto.symbol.uppercase())
                Text(text = "$"+crypto.price.roundToTwoDecimals())
            }
         
            LineChart(
                modifier = Modifier
                    .height(40.dp)
                    .padding(horizontal = 8.dp)
                    .weight(1f),
                yAxisValues = crypto.chartData,
                lineColors = if (crypto.dailyChange > 0) gradientGreenColors else gradientRedColors
            )
        }
    }
}

@Composable
fun StockDetailScreen(crypto: Crypto) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberImagePainter(data = crypto.image),
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(text = crypto.symbol.uppercase(), fontWeight = FontWeight.Bold)
        }
        Text(text = "$"+crypto.price.roundToTwoDecimals(), style = MaterialTheme.typography.title3)
        val plusMinusIndicator = if (crypto.dailyChange > 0) "+" else "-"
        Text(
            text = "$plusMinusIndicator${crypto.dailyChange} (${crypto.dailyChangePercentage}%)",
            color = if (crypto.dailyChange > 0) green500 else Color.Red
        )
        LineChart(
            modifier = Modifier
                .height(80.dp)
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            yAxisValues = crypto.chartData,
            lineColors = if (crypto.dailyChange > 0) gradientGreenColors else gradientRedColors
        )
        StatisticsCard(crypto)
    }
}

@Composable
fun StatisticsCard(crypto: Crypto) {
    Card(onClick = { /*TODO*/ }, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "24H High -  ${crypto.high.roundToTwoDecimals()}")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "24H Low -  ${crypto.low.roundToTwoDecimals()}")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Vol -  ${crypto.volume.roundToTwoDecimals()}")
        }
    }
}

sealed class WearScreen(
    val route: String
) {
    object StockList : WearScreen("list")
    object StockDetail : WearScreen("detail")
}