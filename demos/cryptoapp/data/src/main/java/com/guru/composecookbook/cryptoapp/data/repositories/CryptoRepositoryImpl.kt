package com.guru.composecookbook.cryptoapp.data.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.guru.composecookbook.cryptoapp.data.api.CryptoApi
import com.guru.composecookbook.cryptoapp.data.api.CryptoApiMapper
import com.guru.composecookbook.cryptoapp.data.db.daos.CryptoDao
import com.guru.composecookbook.cryptoapp.data.db.models.Crypto

class CryptoRepositoryImpl(
    private val cryptoApi: CryptoApi,
    private val cryptoDao: CryptoDao,
    private val cryptoApiMapper: CryptoApiMapper
) : CryptoRepository {

    @WorkerThread
    override suspend fun getPageCryptos(page: Int, pageSize: Int): List<Crypto> {
        val response = cryptoApi.getAllCrypto(page)
        return if (response.isSuccessful && !response.body().isNullOrEmpty()) {
            val cryptoApiResponseList = response.body()
            val cryptoList = cryptoApiResponseList?.map { cryptoApiResponse ->
                cryptoApiMapper.map(cryptoApiResponse)
            }
            cryptoList ?: emptyList()
        } else {
            emptyList()
        }
    }

    override suspend fun getFavourite(): LiveData<List<Crypto>> = cryptoDao.getFavCryptos()

    override suspend fun addFavorite(crypto: Crypto) {
        cryptoDao.addFav(crypto)
    }

    override suspend fun removeFavorite(crypto: Crypto) {
        cryptoDao.removeFav(crypto)
    }

}