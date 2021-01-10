package com.guru.composecookbook.ui.demoapps.cryptoappmvvm.Models

import com.guru.composecookbook.ui.demoapps.cryptoappmvvm.data.db.entities.Crypto

data class CryptoHomeUIState(
    val cryptos: MutableList<Crypto> = mutableListOf(),
    val isLoading: Boolean = false,
)