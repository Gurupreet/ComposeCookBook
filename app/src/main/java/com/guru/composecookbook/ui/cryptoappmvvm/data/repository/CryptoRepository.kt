package com.guru.composecookbook.ui.cryptoappmvvm.data.repository

import androidx.lifecycle.LiveData
import com.guru.composecookbook.ui.cryptoappmvvm.data.db.entities.Crypto
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {
    suspend fun getAllCryptos(page: Int): Flow<List<Crypto>>
    suspend fun getFavourite(): LiveData<List<Crypto>>
    suspend fun addFavorite(crypto: Crypto)
    suspend fun removeFavorite(crypto: Crypto)
}