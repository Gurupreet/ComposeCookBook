package com.guru.composecookbook.ui.moviesappmvi.data.repository

import androidx.lifecycle.LiveData
import com.guru.composecookbook.ui.moviesappmvi.data.api.MovieApi
import com.guru.composecookbook.ui.moviesappmvi.data.db.MoviesDao
import com.guru.composecookbook.ui.moviesappmvi.data.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val moviesDao: MoviesDao
) : MovieRepository {

    override suspend fun getNowShowing(): Flow<List<Movie>> = flow {
        val response = movieApi.getMovies(1)
        if (response.isSuccessful) {
            emit(response.body()?.movies ?: emptyList<Movie>())
        } else {
            emit(emptyList<Movie>())
        }

    }.flowOn(Dispatchers.IO)

    override suspend fun getMyWatchlist(): LiveData<List<Movie>> {
        return moviesDao.getMyWatchlist()
    }

    override suspend fun addToMyWatchlist(movie: Movie) {
        moviesDao.addMovieDetail(movie)
    }

    override suspend fun removeFromMyWatchlist(movie: Movie) {
        moviesDao.deleteAll()
    }

}