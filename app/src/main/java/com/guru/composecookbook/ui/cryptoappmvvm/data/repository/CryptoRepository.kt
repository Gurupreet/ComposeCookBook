package com.guru.composecookbook.ui.cryptoappmvvm.data.repository

import com.guru.composecookbook.ui.cryptoappmvvm.Models.Crypto
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {
    suspend fun getAllCryptos(): Flow<List<Crypto>>
    suspend fun getFavourite(): Flow<List<Crypto>>
}