package com.guru.composecookbook.ui.cryptoappmvvm.Models

data class CryptoHomeUIState(
    val cryptos: List<Crypto> = emptyList(),
    val isLoading: Boolean = false,
)