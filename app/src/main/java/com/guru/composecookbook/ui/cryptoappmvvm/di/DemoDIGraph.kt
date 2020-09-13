package com.guru.composecookbook.ui.cryptoappmvvm.di

import com.guru.composecookbook.ui.cryptoappmvvm.data.CryptoApiMapper
import com.guru.composecookbook.ui.cryptoappmvvm.data.CryptoDemoDataProvider
import com.guru.composecookbook.ui.cryptoappmvvm.data.api.CryptoApi
import com.guru.composecookbook.ui.cryptoappmvvm.data.repository.CryptoRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object DemoDIGraph {

    private const val timeOut = 20L
    private const val BASE_URL = "https://api.coingecko.com/api/v3/"

    private val okHttpClient by lazy {
        OkHttpClient.Builder().apply {
            readTimeout(timeOut, TimeUnit.SECONDS)
            connectTimeout(timeOut, TimeUnit.SECONDS)
        }.build()
    }

    private val retrofit =  Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val cryptoApi = retrofit.create(CryptoApi::class.java)

    private val cryptoApiMapper = CryptoApiMapper()

    val cryptoRepository by lazy {
        CryptoRepositoryImpl(cryptoApi, cryptoApiMapper)
    }

    val mainDispatcher: CoroutineDispatcher
        get() = Dispatchers.Main

    val ioDispatcher: CoroutineDispatcher
        get() = Dispatchers.IO

}