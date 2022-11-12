package com.guru.composecookbook.cryptoapp.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.guru.composecookbook.cryptoapp.data.DemoDIGraph
import com.guru.composecookbook.cryptoapp.data.db.models.Crypto
import com.guru.composecookbook.cryptoapp.data.paging.PageNumSource
import com.guru.composecookbook.cryptoapp.data.repositories.CryptoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CryptoHomeViewModelFactory(val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CryptoHomeViewModel(context) as T
    }
}

class CryptoHomeViewModel(context: Context) : ViewModel() {
    private val cryptoRepository: CryptoRepository = DemoDIGraph.createRepository(context)

    //live data to read room database
    val favCryptoLiveData = liveData(Dispatchers.IO) {
        emitSource(cryptoRepository.getFavourite())
    }

    fun getAllCryptos(pageSize: Int = 20) =
        Pager(config = PagingConfig(pageSize = pageSize, initialLoadSize = pageSize)) {
            PageNumSource { pageNo, pageSize ->
                cryptoRepository.getPageCryptos(pageNo, pageSize)
            }
        }.flow.cachedIn(viewModelScope)


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

    fun getCryptoFromSymbol(symbol: String) {

    }
}