package com.guru.composecookbook.ui.cryptoappmvvm.data.api.apiresponse

data class CryptoApiResponse(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val current_price: Double,
    val market_cap: Long,
    val total_volume: Long,
    val high_24h: Double,
    val low_24h: Double,
    val price_change_24h: Double,
    val price_change_percentage_24h: Double,
    val supply: Long
)
