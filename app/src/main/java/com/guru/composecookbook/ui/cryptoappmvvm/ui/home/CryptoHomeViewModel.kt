package com.guru.composecookbook.ui.cryptoappmvvm.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guru.composecookbook.ui.cryptoappmvvm.data.repository.CryptoRepository
import com.guru.composecookbook.ui.cryptoappmvvm.di.DemoDIGraph
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CryptoHomeViewModel(
    cryptoRepository: CryptoRepository = DemoDIGraph.cryptoRepository
): ViewModel() {

    private val _viewStateFlow = MutableStateFlow(CryptoHomeUIState(isLoading = true))
    val viewStateFlow: StateFlow<CryptoHomeUIState> = _viewStateFlow

    init {
        viewModelScope.launch {
            cryptoRepository.getAllCryptos().collect {
                _viewStateFlow.value = CryptoHomeUIState(
                    isLoading = false,
                    cryptos = it
                )
            }
        }
    }
}