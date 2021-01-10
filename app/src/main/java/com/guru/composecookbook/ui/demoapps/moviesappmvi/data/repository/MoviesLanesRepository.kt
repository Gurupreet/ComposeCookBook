package com.guru.composecookbook.ui.demoapps.moviesappmvi.data.repository

import com.guru.composecookbook.ui.demoapps.moviesappmvi.data.models.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesLanesRepository {
    suspend fun getTrendingMovies(): Flow<List<Movie>>
    suspend fun getPopularMovies(): Flow<List<Movie>>
    suspend fun getTopRatedMovies(): Flow<List<Movie>>
    suspend fun getTopRatedTVShwos(): Flow<List<Movie>>
    suspend fun getTrendingTVShows(): Flow<List<Movie>>
}