package com.guru.composecookbook.ui.cryptoappmvvm.data.api

import com.guru.composecookbook.ui.cryptoappmvvm.data.api.apiresponse.CryptoApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoApi {

    @GET("coins/markets?vs_currency=usd&order=market_cap_desc&per_page=50&page=1&sparkline=false")
    suspend fun getAllCoins(): Response<List<CryptoApiResponse>>
    //TODO Support pagination
}