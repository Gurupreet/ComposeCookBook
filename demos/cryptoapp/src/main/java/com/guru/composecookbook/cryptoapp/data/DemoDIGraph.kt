package com.guru.composecookbook.cryptoapp.data

import android.content.Context
import com.guru.composecookbook.cryptoapp.data.api.CryptoApi
import com.guru.composecookbook.cryptoapp.data.api.CryptoApiMapper
import com.guru.composecookbook.cryptoapp.data.db.CryptoDatabase
import com.guru.composecookbook.cryptoapp.data.repositories.CryptoRepository
import com.guru.composecookbook.cryptoapp.data.repositories.CryptoRepositoryImpl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DemoDIGraph {
    private const val timeOut = 20L
    private const val BASE_URL = "https://api.coingecko.com/api/v3/"

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

    fun createRepository(context: Context): CryptoRepository {
        val db = CryptoDatabase.getInstance(context)
        return CryptoRepositoryImpl(
            cryptoApi = retrofit.create(CryptoApi::class.java),
            cryptoDao = db.cryptoDao(),
            cryptoApiMapper = CryptoApiMapper()
        )
    }
}
