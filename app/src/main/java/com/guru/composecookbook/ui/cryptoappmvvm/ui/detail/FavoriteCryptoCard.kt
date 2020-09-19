package com.guru.composecookbook.ui.cryptoappmvvm.ui.detail

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.theme.green500
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.cryptoappmvvm.data.CryptoDemoDataProvider
import com.guru.composecookbook.ui.cryptoappmvvm.data.db.entities.Crypto
import com.guru.composecookbook.ui.cryptoappmvvm.utils.roundToTwoDecimals
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun FavoriteCryptoCard(crypto: Crypto) {
    Card(elevation = 8.dp, modifier = Modifier.padding(end = 8.dp, top = 8.dp)) {
        Column(modifier = Modifier.padding(16.dp).preferredWidth(120.dp).preferredHeight(180.dp)) {
            Row(modifier = Modifier.weight(1f)) {
                CoilImage(data = crypto.image, modifier = Modifier.preferredSize(24.dp))
                Text(
                    text = crypto.name,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    style = typography.body2
                )
            }
            Text(
                text = crypto.price.roundToTwoDecimals()+" usd",
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