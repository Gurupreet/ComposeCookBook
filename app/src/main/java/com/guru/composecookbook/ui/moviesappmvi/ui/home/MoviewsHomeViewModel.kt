package com.guru.composecookbook.ui.moviesappmvi.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.guru.composecookbook.ui.moviesappmvi.data.models.Movie
import com.guru.composecookbook.ui.moviesappmvi.data.repository.MovieRepository
import com.guru.composecookbook.ui.moviesappmvi.di.MovieDIGraph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MoviesHomeViewModel(
    private val movieRepository: MovieRepository = MovieDIGraph.movieRepository
) : ViewModel() {

    val nowShowingLiveData = MutableLiveData<List<Movie>>()
    val errorLiveData = MutableLiveData<String>()

    //live data to read room database
    val genresLiveData = liveData(Dispatchers.IO) {
        emitSource(movieRepository.getGenres())
    }
    val myWatchlist = liveData(Dispatchers.IO) {
        emitSource(movieRepository.getMyWatchlist())
    }

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
            movieRepository.fetchAndSaveGenresToDatabase().collect { }
        }
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.getMyWatchlist()
        }
    }

    fun addToMyWatchlist(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        movieRepository.addToMyWatchlist(movie)
    }

    fun removeFromMyWatchlist(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        movieRepository.removeFromMyWatchlist(movie)
    }

}