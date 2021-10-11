package com.adwi.betty.ui.main

import com.adwi.betty.data.local.entity.Odd
import com.adwi.betty.data.repository.OddsRepositoryInterface
import com.adwi.betty.data.repository.fullSportList
import com.adwi.betty.di.IoDispatcher
import com.adwi.betty.ui.base.BaseViewModel
import com.adwi.betty.util.onDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: OddsRepositoryInterface,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val olderThanNow = System.currentTimeMillis()

    private var _odds = MutableStateFlow<List<Odd>>(emptyList())
    val odds: StateFlow<List<Odd>> get() = _odds

    private var _progress = MutableStateFlow(0f)
    val progress: StateFlow<Float> get() = _progress


    init {
        onDispatcher(ioDispatcher) {
            if (repository.getOddsLocal().first().isNullOrEmpty()) {
                fetchRemote()
            }
        }
        onDispatcher(ioDispatcher) {
            repository.deleteOddsOlderThan(olderThanNow)
        }
        onDispatcher(ioDispatcher) {
            getOdds()
        }
    }

    private fun fetchRemote() {
        onDispatcher(ioDispatcher) {
            fullSportList.forEachIndexed { index, sportKey ->
                repository.fetchOddsRemote(sportKey)
                setProgress(index)
            }
        }
    }

    private fun getOdds() {
        onDispatcher(ioDispatcher) {
            repository.getOddsLocal().collect { list ->
                _odds.value = list
            }
        }
    }

    fun onManualRefresh() {
        onDispatcher(ioDispatcher) {
            with(repository) {
                deleteAllOdds()
            }
            fetchRemote()
        }
    }

    fun setProgress(progress: Int) {
        _progress.value = (progress + 1) * 1f / fullSportList.size
    }
}

enum class Range { All, LOW, MEDIUM, HIGH }