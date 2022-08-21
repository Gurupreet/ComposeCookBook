package com.guru.composecookbook.moviesapp.ui.home

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

@Suppress("UNCHECKED_CAST")
class MoviesHomeViewModelFactory(val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesHomeViewModel(context) as T
    }
}

class MoviesHomeViewModel(context: Context) : ViewModel() {
    private val movieRepository: MovieRepository = MovieDIGraph.createMovieRepository(context)

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