package com.guru.composecookbook.ui.cryptoappmvvm.Models

import com.guru.composecookbook.ui.cryptoappmvvm.data.db.entities.Crypto

data class CryptoHomeUIState(
    val cryptos: List<Crypto> = emptyList(),
    val isLoading: Boolean = false,
)