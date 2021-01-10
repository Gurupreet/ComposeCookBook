package com.guru.composecookbook.ui.demoapps.cryptoappmvvm.di

import com.guru.composecookbook.App
import com.guru.composecookbook.ui.demoapps.cryptoappmvvm.data.CryptoApiMapper
import com.guru.composecookbook.ui.demoapps.cryptoappmvvm.data.api.CryptoApi
import com.guru.composecookbook.ui.demoapps.cryptoappmvvm.data.db.CryptoDatabase
import com.guru.composecookbook.ui.demoapps.cryptoappmvvm.data.repository.CryptoRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DemoDIGraph {

    private const val timeOut = 20L
    private const val BASE_URL = "https://api.coingecko.com/api/v3/"
    private val context = App.applicationContext()

    // create retrofit
    private val okHttpClient by lazy {
        OkHttpClient.Builder().apply {
            readTimeout(timeOut, TimeUnit.SECONDS)
            connectTimeout(timeOut, TimeUnit.SECONDS)
        }.build()
    }

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val cryptoApi = retrofit.create(CryptoApi::class.java)

    private val cryptoApiMapper = CryptoApiMapper()

    //create room db
    private val cryptoDatabase = CryptoDatabase.getInstance(context)
    private val cryptoDao = cryptoDatabase.cryptoDao()

    //pass dependencies to repository
    val cryptoRepository by lazy {
        CryptoRepositoryImpl(cryptoApi, cryptoDao, cryptoApiMapper)
    }

    val mainDispatcher: CoroutineDispatcher
        get() = Dispatchers.Main

    val ioDispatcher: CoroutineDispatcher
        get() = Dispatchers.IO

}