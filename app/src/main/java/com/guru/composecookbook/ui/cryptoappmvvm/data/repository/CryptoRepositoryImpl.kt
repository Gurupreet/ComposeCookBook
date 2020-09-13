package com.guru.composecookbook.ui.cryptoappmvvm.data.repository

import androidx.annotation.WorkerThread
import com.guru.composecookbook.ui.cryptoappmvvm.Models.Crypto
import com.guru.composecookbook.ui.cryptoappmvvm.data.CryptoApiMapper
import com.guru.composecookbook.ui.cryptoappmvvm.data.api.CryptoApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CryptoRepositoryImpl(
    private val cryptoApi: CryptoApi,
    private val cryptoApiMapper: CryptoApiMapper
) : CryptoRepository {

    @WorkerThread
    override suspend fun getAllCryptos() = flow {
        val response = cryptoApi.getAllCoins()
        if (response.isSuccessful && !response.body().isNullOrEmpty()) {
            val cryptoApiResponseList = response.body()
            val cryptoList = cryptoApiResponseList?.map { cryptoApiResponse ->
                cryptoApiMapper.map(cryptoApiResponse)
            }
            emit(cryptoList ?: emptyList<Crypto>())
        } else {
            emit(error(response.message() ?: "Failed to load data"))
        }
    }.flowOn(Dispatchers.IO)

    @WorkerThread
    override suspend fun getFavourite(): Flow<List<Crypto>> = flow {
        emit(emptyList<Crypto>())
    }.flowOn(Dispatchers.IO)

}