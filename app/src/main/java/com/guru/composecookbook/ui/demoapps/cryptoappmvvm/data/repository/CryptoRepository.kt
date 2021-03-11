package com.guru.composecookbook.ui.demoapps.cryptoappmvvm.data.repository

import androidx.lifecycle.LiveData
import com.guru.composecookbook.ui.demoapps.cryptoappmvvm.data.db.entities.Crypto

interface CryptoRepository {
    suspend fun getPageCryptos(page: Int, pageSize: Int): List<Crypto>
    suspend fun getFavourite(): LiveData<List<Crypto>>
    suspend fun addFavorite(crypto: Crypto)
    suspend fun removeFavorite(crypto: Crypto)
}