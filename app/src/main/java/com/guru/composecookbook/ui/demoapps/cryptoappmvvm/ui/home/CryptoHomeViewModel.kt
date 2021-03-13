package com.guru.composecookbook.ui.demoapps.cryptoappmvvm.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.guru.composecookbook.ui.demoapps.cryptoappmvvm.data.db.entities.Crypto
import com.guru.composecookbook.ui.demoapps.cryptoappmvvm.data.repository.CryptoRepository
import com.guru.composecookbook.ui.demoapps.cryptoappmvvm.di.DemoDIGraph
import com.guru.composecookbook.ui.utils.PageNumSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CryptoHomeViewModel(
    val cryptoRepository: CryptoRepository = DemoDIGraph.cryptoRepository
) : ViewModel() {

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
}