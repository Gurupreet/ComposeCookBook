package com.guru.composecookbook.moviesapp.ui.details

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.guru.composecookbook.moviesapp.data.MovieDIGraph
import com.guru.composecookbook.moviesapp.data.db.models.Movie
import com.guru.composecookbook.moviesapp.data.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModelFactory(val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(context) as T
    }
}

class MovieDetailViewModel(context: Context) : ViewModel() {
    private val movieRepository: MovieRepository = MovieDIGraph.createMovieRepository(context)

    val similarMoviesLiveData = MutableLiveData<List<Movie>>()
    val genresLiveData = liveData(Dispatchers.IO) {
        emitSource(movieRepository.getGenres())
    }

    fun getSimilarMovies(movieId: String) {
        viewModelScope.launch {
            movieRepository.getSimilarMovies(movieId)
                .collect { movies ->
                    if (movies.isNotEmpty()) {
                        similarMoviesLiveData.value = movies
                    }
                }
        }
    }
}