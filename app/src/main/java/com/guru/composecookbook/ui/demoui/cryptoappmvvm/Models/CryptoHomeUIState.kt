package com.guru.composecookbook.ui.demoui.cryptoappmvvm.Models

import com.guru.composecookbook.ui.demoui.cryptoappmvvm.data.db.entities.Crypto

data class CryptoHomeUIState(
    val cryptos: MutableList<Crypto> = mutableListOf(),
    val isLoading: Boolean = false,
)