package com.guru.composecookbook.ui.cryptoappmvvm.ui.home

import com.guru.composecookbook.ui.cryptoappmvvm.Models.Crypto

data class CryptoHomeUIState(
    val cryptos: List<Crypto> = emptyList(),
    val isLoading: Boolean = false,
)