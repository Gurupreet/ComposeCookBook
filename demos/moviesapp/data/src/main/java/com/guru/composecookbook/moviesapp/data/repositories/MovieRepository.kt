package com.guru.composecookbook.moviesapp.data.repositories

import androidx.lifecycle.LiveData
import com.guru.composecookbook.moviesapp.data.db.models.Genre
import com.guru.composecookbook.moviesapp.data.db.models.Movie
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