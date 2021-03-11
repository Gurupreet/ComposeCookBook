package com.guru.composecookbook.ui.demoapps.cryptoappmvvm.data.api

import com.guru.composecookbook.ui.demoapps.cryptoappmvvm.data.api.apiresponse.CryptoApiResponse
import com.guru.composecookbook.ui.demoapps.cryptoappmvvm.data.api.apiresponse.CryptoDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoApi {

    @GET("coins/markets?vs_currency=usd&order=market_cap_desc&sparkline=true")
    suspend fun getAllCrypto(@Query("page") page: Int = 1, @Query("per_page") pageSize: Int = 20): Response<List<CryptoApiResponse>>

    @GET("coins/{ticker}")
    suspend fun getCryptoDetail(@Path("ticker") ticker: String): Response<CryptoDetailResponse>
}