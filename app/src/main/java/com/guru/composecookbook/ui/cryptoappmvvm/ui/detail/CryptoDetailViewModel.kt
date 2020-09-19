package com.guru.composecookbook.ui.cryptoappmvvm.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.guru.composecookbook.ui.cryptoappmvvm.data.repository.CryptoRepository
import com.guru.composecookbook.ui.cryptoappmvvm.di.DemoDIGraph
import kotlinx.coroutines.Dispatchers

class CryptoDetailViewModel(
    val cryptoRepository: CryptoRepository = DemoDIGraph.cryptoRepository
) : ViewModel() {
    //live data to read room database
    val favCryptoLiveData = liveData(Dispatchers.IO) {
        emitSource(cryptoRepository.getFavourite())
    }
}