package com.guru.composecookbook.cryptoapp.data.repositories

import androidx.lifecycle.LiveData
import com.guru.composecookbook.cryptoapp.data.db.models.Crypto

interface CryptoRepository {
    suspend fun getPageCryptos(page: Int, pageSize: Int): List<Crypto>
    suspend fun getFavourite(): LiveData<List<Crypto>>
    suspend fun addFavorite(crypto: Crypto)
    suspend fun removeFavorite(crypto: Crypto)
}