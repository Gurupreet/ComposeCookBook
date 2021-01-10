package com.guru.composecookbook.ui.demoui.moviesappmvi.data.repository

import androidx.lifecycle.LiveData
import com.guru.composecookbook.ui.demoui.moviesappmvi.data.models.Genre
import com.guru.composecookbook.ui.demoui.moviesappmvi.data.models.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getNowShowing(): Flow<List<Movie>>
    suspend fun getSimilarMovies(movieId: String): Flow<List<Movie>>
    suspend fun getMyWatchlist(): LiveData<List<Movie>>
    suspend fun addToMyWatchlist(movie: Movie)
    suspend fun removeFromMyWatchlist(movie: Movie)
    suspend fun fetchAndSaveGenresToDatabase(): Flow<List<Genre>>
    suspend fun getGenres(): LiveData<List<Genre>>
}