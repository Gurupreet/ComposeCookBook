package com.guru.composecookbook.ui.moviesappmvi.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guru.composecookbook.ui.moviesappmvi.data.models.Movie
import com.guru.composecookbook.ui.moviesappmvi.data.repository.MovieRepository
import com.guru.composecookbook.ui.moviesappmvi.di.MovieDIGraph
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.onErrorReturn
import kotlinx.coroutines.launch

class MoviesHomeViewModel(
    private val movieRepository: MovieRepository = MovieDIGraph.movieRepository
) : ViewModel() {

    val nowShowingLiveData = MutableLiveData<List<Movie>>()
    val errorLiveData = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            movieRepository.getNowShowing()
                .collect { movies ->
                    if (movies.isNotEmpty()) {
                        nowShowingLiveData.value = movies
                    } else {
                        errorLiveData.value = "Failed to load movies"
                    }
            }
        }
    }
}