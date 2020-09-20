package com.guru.composecookbook.ui.cryptoappmvvm.ui.home.components

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.theme.green500
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.cryptoappmvvm.data.CryptoDemoDataProvider
import com.guru.composecookbook.ui.cryptoappmvvm.data.db.entities.Crypto
import com.guru.composecookbook.ui.cryptoappmvvm.ui.home.CryptoHomeInteractionEvents
import com.guru.composecookbook.ui.cryptoappmvvm.utils.roundToThreeDecimals
import com.guru.composecookbook.ui.cryptoappmvvm.utils.roundToTwoDecimals
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun CryptoListItem(
    crypto: Crypto,
    isFav: Boolean = false,
    onCryptoHomeInteractionEvents: (CryptoHomeInteractionEvents) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .clickable(onClick = { onCryptoHomeInteractionEvents(CryptoHomeInteractionEvents.OpenDetailScreen(crypto)) })
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CoilImage(
            data = crypto.image,
            modifier = Modifier.preferredSize(40.dp).padding(4.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = crypto.symbol,
            style = typography.h6.copy(fontSize = 16.sp),
            modifier = Modifier.padding(4.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "$${crypto.price}",
                style = typography.h6,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().gravity(Alignment.End)
            )
            Text(
                text = "${crypto.dailyChange.roundToThreeDecimals()}" +
                        " (${crypto.dailyChangePercentage.roundToTwoDecimals()} %)",
                style = typography.subtitle2,
                color = if (crypto.dailyChange > 0) green500 else Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().align(Alignment.End)
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
                asset = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
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