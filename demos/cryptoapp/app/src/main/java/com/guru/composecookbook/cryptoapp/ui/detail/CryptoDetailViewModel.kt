package com.guru.composecookbook.cryptoapp.ui.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.guru.composecookbook.cryptoapp.data.DemoDIGraph
import com.guru.composecookbook.cryptoapp.data.repositories.CryptoRepository
import kotlinx.coroutines.Dispatchers

class CryptoDetailViewModelFactory(val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CryptoDetailViewModel(context) as T
    }
}

class CryptoDetailViewModel(context: Context) : ViewModel() {
    private val cryptoRepository: CryptoRepository = DemoDIGraph.createRepository(context)

    //live data to read room database
    val favCryptoLiveData = liveData(Dispatchers.IO) {
        emitSource(cryptoRepository.getFavourite())
    }
}