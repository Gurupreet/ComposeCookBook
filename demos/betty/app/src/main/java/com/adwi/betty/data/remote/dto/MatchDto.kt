package com.adwi.betty.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MatchDto(
    @SerializedName("id") val id: String,
    @SerializedName("sport_key") val sportKey: String,
    @SerializedName("sport_title") val sportName: String,
    @SerializedName("commence_time") val commenceTime: String,
    @SerializedName("home_team") val homeTeam: String?,
    @SerializedName("away_team") val awayTeam: String?,
    @SerializedName("bookmakers") val bookmakers: List<BookmakerDto>
)

data class BookmakerDto(
    @SerializedName("key") var key: String,
    @SerializedName("title") var title: String,
    @SerializedName("last_update") var lastUpdate: String,
    @SerializedName("markets") var markets: List<MarketDto>
)

data class MarketDto(
    @SerializedName("key") var key: String,
    @SerializedName("outcomes") var outcomes: List<OutcomeDto>
)

data class OutcomeDto(
    @SerializedName("name") var name: String,
    @SerializedName("price") var price: Double
)