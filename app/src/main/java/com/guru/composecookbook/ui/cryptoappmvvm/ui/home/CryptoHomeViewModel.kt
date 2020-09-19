package com.guru.composecookbook.ui.cryptoappmvvm.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.guru.composecookbook.ui.cryptoappmvvm.Models.CryptoHomeUIState
import com.guru.composecookbook.ui.cryptoappmvvm.data.db.entities.Crypto
import com.guru.composecookbook.ui.cryptoappmvvm.data.repository.CryptoRepository
import com.guru.composecookbook.ui.cryptoappmvvm.di.DemoDIGraph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CryptoHomeViewModel(
    val cryptoRepository: CryptoRepository = DemoDIGraph.cryptoRepository
) : ViewModel() {
    //flow implementation to get all crypto
    private val _viewStateFlow = MutableStateFlow(CryptoHomeUIState(isLoading = true))
    val viewStateFlow: StateFlow<CryptoHomeUIState> = _viewStateFlow

    //live data to read room database
    val favCryptoLiveData = liveData(Dispatchers.IO) {
        emitSource(cryptoRepository.getFavourite())
    }

    init {
        viewModelScope.launch {
            getAllCryptos()
        }
    }

    private suspend fun getAllCryptos() {
        cryptoRepository.getAllCryptos().collect {
            _viewStateFlow.value = CryptoHomeUIState(
                isLoading = false,
                cryptos = it
            )
        }
    }

    fun addToFav(crypto: Crypto) {
        viewModelScope.launch(Dispatchers.IO) {
            cryptoRepository.addFavorite(crypto = crypto)
        }
    }

    fun removeFav(crypto: Crypto) {
        viewModelScope.launch(Dispatchers.IO) {
            cryptoRepository.removeFavorite(crypto = crypto)
        }
    }
}