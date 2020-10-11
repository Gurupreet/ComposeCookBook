package com.guru.composecookbook.ui.cryptoappmvvm.Models

import com.guru.composecookbook.ui.cryptoappmvvm.data.db.entities.Crypto

data class CryptoHomeUIState(
    val cryptos: MutableList<Crypto> = mutableListOf(),
    val isLoading: Boolean = false,
)