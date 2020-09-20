package com.guru.composecookbook.ui.moviesappmvi.data.repository

import androidx.lifecycle.LiveData
import com.guru.composecookbook.ui.moviesappmvi.data.api.apiresponses.MovieListResponse
import com.guru.composecookbook.ui.moviesappmvi.data.models.Movie
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieRepository {

    suspend fun getNowShowing(): Flow<List<Movie>>
    suspend fun getMyWatchlist(): LiveData<List<Movie>>
    suspend fun addToMyWatchlist(movie: Movie)
    suspend fun removeFromMyWatchlist(movie: Movie)

}