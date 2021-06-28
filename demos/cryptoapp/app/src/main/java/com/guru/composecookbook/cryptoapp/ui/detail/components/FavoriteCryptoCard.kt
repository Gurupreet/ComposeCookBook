package com.guru.composecookbook.cryptoapp.ui.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.guru.composecookbook.cryptoapp.data.CryptoDemoDataProvider
import com.guru.composecookbook.cryptoapp.data.db.models.Crypto
import com.guru.composecookbook.cryptoapp.ui.internal.extensions.roundToTwoDecimals
import com.guru.composecookbook.theme.green500
import com.guru.composecookbook.theme.typography

@Composable
fun FavoriteCryptoCard(crypto: Crypto) {
    Card(elevation = 8.dp, modifier = Modifier.padding(end = 8.dp, top = 8.dp)) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .width(120.dp)
                .height(180.dp)
        ) {
            Row(modifier = Modifier.weight(1f)) {
                Image(
                    painter = rememberCoilPainter(request = crypto.image),
                    modifier = Modifier.size(24.dp),
                    contentDescription = null
                )
                Text(
                    text = crypto.name,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    style = typography.body2
                )
            }
            Text(
                text = crypto.price.roundToTwoDecimals() + " usd",
                style = typography.h6,
                color = if (crypto.dailyChangePercentage > 0) green500 else Color.Red
            )
            Text(
                text = "${crypto.dailyChangePercentage.roundToTwoDecimals()} %",
                style = typography.h6,
                color = if (crypto.dailyChangePercentage > 0) green500 else Color.Red
            )
        }
    }
}

@Preview
@Composable
fun PreviewFavCryptoCard() {
    val crypto = CryptoDemoDataProvider.bitcoin
    FavoriteCryptoCard(crypto = crypto)
}