package com.guru.composecookbook.moviesapp.ui.trending

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.guru.composecookbook.moviesapp.data.MovieDIGraph
import com.guru.composecookbook.moviesapp.data.db.models.Movie
import com.guru.composecookbook.moviesapp.data.repositories.MoviesLanesRepository
import kotlinx.coroutines.launch

class TrendingViewModelFactory(val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TrendingViewModel(context) as T
    }
}

class TrendingViewModel(context: Context) : ViewModel() {
    private val moviesLanesRepository: MoviesLanesRepository =
        MovieDIGraph.createMoviesLaneRepository(context)
    val trendingMoviesLiveData = MutableLiveData<List<Movie>>()
    val popularMoviesLiveData = MutableLiveData<List<Movie>>()
    val topRatedMovies = MutableLiveData<List<Movie>>()
    val topRatedTVShows = MutableLiveData<List<Movie>>()
    val trendingTVShowsLiveData = MutableLiveData<List<Movie>>()

    init {
        viewModelScope.launch {
            moviesLanesRepository.getTrendingMovies().collect {
                trendingMoviesLiveData.value = it
            }
            moviesLanesRepository.getPopularMovies().collect {
                popularMoviesLiveData.value = it
            }
            moviesLanesRepository.getTopRatedMovies().collect {
                topRatedMovies.value = it
            }
            // TODO create new model for TV showsq
//            moviesLanesRepository.getTopRatedTVShwos().collect {
//                topRatedTVShows.value = it
//            }
//            moviesLanesRepository.getTrendingTVShows().collect {
//                trendingTVShowsLiveData.value = it
//            }
        }
    }
}