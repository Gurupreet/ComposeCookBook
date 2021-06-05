package com.guru.composecookbook.cryptoapp.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import com.guru.composecookbook.charts.LineChart
import com.guru.composecookbook.cryptoapp.data.CryptoDemoDataProvider
import com.guru.composecookbook.cryptoapp.data.db.models.Crypto
import com.guru.composecookbook.cryptoapp.ui.home.CryptoHomeInteractionEvents
import com.guru.composecookbook.cryptoapp.ui.internal.extensions.roundToThreeDecimals
import com.guru.composecookbook.cryptoapp.ui.internal.extensions.roundToTwoDecimals
import com.guru.composecookbook.theme.gradientGreenColors
import com.guru.composecookbook.theme.gradientRedColors
import com.guru.composecookbook.theme.green500
import com.guru.composecookbook.theme.typography


@Composable
fun CryptoListItem(
    crypto: Crypto,
    isFav: Boolean = false,
    onCryptoHomeInteractionEvents: (CryptoHomeInteractionEvents) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                onCryptoHomeInteractionEvents(
                    CryptoHomeInteractionEvents.OpenDetailScreen(
                        crypto
                    )
                )
            })
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberCoilPainter(request = crypto.image),
            modifier = Modifier
                .size(40.dp)
                .padding(4.dp),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.weight(0.4f)) {
            Text(
                text = crypto.symbol,
                style = typography.h6.copy(fontSize = 16.sp),
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = "$${crypto.price}",
                style = typography.h6.copy(fontSize = 14.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            LineChart(
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp)
                    .align(Alignment.CenterHorizontally),
                yAxisValues = crypto.chartData,
                lineColors = if (crypto.dailyChange > 0) gradientGreenColors else gradientRedColors
            )
            Text(
                text = crypto.dailyChange.roundToThreeDecimals() +
                    " (${crypto.dailyChangePercentage.roundToTwoDecimals()} %)",
                style = typography.subtitle2,
                color = if (crypto.dailyChange > 0) green500 else Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.End)
            )
        }
        IconToggleButton(
            checked = isFav,
            onCheckedChange = { hasFav ->
                if (hasFav)
                    onCryptoHomeInteractionEvents(CryptoHomeInteractionEvents.AddedToFav(crypto))
                else {
                    onCryptoHomeInteractionEvents(CryptoHomeInteractionEvents.RemoveFav(crypto))
                }
            }
        ) {
            Icon(
                imageVector = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = null,
                tint = if (isFav) Color.Red else MaterialTheme.colors.onSurface
            )
        }
    }
}

@Preview
@Composable
fun PreviewCryptoItem() {
    val crypto = CryptoDemoDataProvider.bitcoin
    CryptoListItem(crypto = crypto, false, {})
}