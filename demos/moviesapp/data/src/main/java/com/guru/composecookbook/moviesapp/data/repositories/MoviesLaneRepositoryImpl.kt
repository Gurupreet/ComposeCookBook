package com.guru.composecookbook.moviesapp.data.repositories

import com.guru.composecookbook.moviesapp.data.api.MovieApi
import com.guru.composecookbook.moviesapp.data.db.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MoviesLaneRepositoryImpl(private val movieApi: MovieApi) : MoviesLanesRepository {

    override suspend fun getTrendingMovies(): Flow<List<Movie>> = flow {
        val response = movieApi.getTrendingMovies()
        if (response.isSuccessful) {
            emit(response.body()?.movies ?: emptyList<Movie>())
        } else {
            emit(emptyList<Movie>())
        }

    }.catch {
        emit(emptyList<Movie>())
    }.flowOn(Dispatchers.Default)

    override suspend fun getTrendingTVShows(): Flow<List<Movie>> = flow {
        val response = movieApi.getTrendingTVShows()
        if (response.isSuccessful) {
            emit(response.body()?.movies ?: emptyList<Movie>())
        } else {
            emit(emptyList<Movie>())
        }

    }.catch {
        emit(emptyList<Movie>())
    }.flowOn(Dispatchers.Default)

    override suspend fun getPopularMovies(): Flow<List<Movie>> = flow {
        val response = movieApi.getPopularMovies()
        if (response.isSuccessful) {
            emit(response.body()?.movies ?: emptyList<Movie>())
        } else {
            emit(emptyList<Movie>())
        }

    }.catch {
        emit(emptyList<Movie>())
    }.flowOn(Dispatchers.Default)

    override suspend fun getTopRatedMovies(): Flow<List<Movie>> = flow {
        val response = movieApi.getTopRatedMovies()
        if (response.isSuccessful) {
            emit(response.body()?.movies ?: emptyList<Movie>())
        } else {
            emit(emptyList<Movie>())
        }

    }.catch {
        emit(emptyList<Movie>())
    }.flowOn(Dispatchers.Default)

    override suspend fun getTopRatedTVShwos(): Flow<List<Movie>> = flow {
        val response = movieApi.getTopRatedTvShows()
        if (response.isSuccessful) {
            emit(response.body()?.movies ?: emptyList<Movie>())
        } else {
            emit(emptyList<Movie>())
        }

    }.catch {
        emit(emptyList<Movie>())
    }.flowOn(Dispatchers.Default)
}