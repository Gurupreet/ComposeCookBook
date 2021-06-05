package com.guru.composecookbook.moviesapp.data.repositories

import com.guru.composecookbook.moviesapp.data.db.models.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesLanesRepository {
    suspend fun getTrendingMovies(): Flow<List<Movie>>
    suspend fun getPopularMovies(): Flow<List<Movie>>
    suspend fun getTopRatedMovies(): Flow<List<Movie>>
    suspend fun getTopRatedTVShwos(): Flow<List<Movie>>
    suspend fun getTrendingTVShows(): Flow<List<Movie>>
}