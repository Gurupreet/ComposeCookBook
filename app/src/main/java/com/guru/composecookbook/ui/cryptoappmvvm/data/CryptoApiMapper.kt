package com.guru.composecookbook.ui.cryptoappmvvm.data

import com.guru.composecookbook.ui.cryptoappmvvm.Models.Crypto
import com.guru.composecookbook.ui.cryptoappmvvm.data.api.apiresponse.CryptoApiResponse

class CryptoApiMapper {
    fun map(cryptoApiResponse: CryptoApiResponse) = Crypto(
        name = cryptoApiResponse.name,
        symbol = cryptoApiResponse.symbol,
        price = cryptoApiResponse.current_price,
        image = cryptoApiResponse.image,
        dailyChange = cryptoApiResponse.price_change_24h,
        dailyChangePercentage = cryptoApiResponse.price_change_percentage_24h,
        high = cryptoApiResponse.high_24h,
        low = cryptoApiResponse.low_24h
    )
}