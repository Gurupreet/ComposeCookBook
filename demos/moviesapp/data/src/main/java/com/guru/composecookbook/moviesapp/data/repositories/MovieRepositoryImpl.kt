package com.guru.composecookbook.moviesapp.data.repositories

import androidx.lifecycle.LiveData
import com.guru.composecookbook.moviesapp.data.api.MovieApi
import com.guru.composecookbook.moviesapp.data.db.daos.GenreDao
import com.guru.composecookbook.moviesapp.data.db.daos.MoviesDao
import com.guru.composecookbook.moviesapp.data.db.models.Genre
import com.guru.composecookbook.moviesapp.data.db.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val moviesDao: MoviesDao,
    private val genreDao: GenreDao
) : MovieRepository {

    override suspend fun getNowShowing(): Flow<List<Movie>> = flow {
        val response = movieApi.getMovies(1)
        if (response.isSuccessful) {
            emit(response.body()?.movies ?: emptyList<Movie>())
        } else {
            emit(emptyList<Movie>())
        }

    }.catch { e ->
        emit(emptyList<Movie>())
    }.flowOn(Dispatchers.IO)

    override suspend fun getSimilarMovies(movieId: String): Flow<List<Movie>> = flow {
        val response = movieApi.getSimilarMovies(movieId)
        if (response.isSuccessful) {
            emit(response.body()?.movies ?: emptyList<Movie>())
        } else {
            emit(emptyList<Movie>())
        }

    }.catch {
        emit(emptyList<Movie>())
    }.flowOn(Dispatchers.IO)

    override suspend fun getMyWatchlist(): LiveData<List<Movie>> {
        return moviesDao.getMyWatchlist()
    }

    override suspend fun addToMyWatchlist(movie: Movie) {
        moviesDao.addToWatchList(movie)
    }

    override suspend fun removeFromMyWatchlist(movie: Movie) {
        moviesDao.removeFromMYWatchlist(movie)
    }

    override suspend fun getGenres(): LiveData<List<Genre>> = genreDao.getAllGenres()

    override suspend fun fetchAndSaveGenresToDatabase(): Flow<List<Genre>> = flow {
        val response = movieApi.getGenres()
        if (response.isSuccessful) {
            response.body()?.let {
                if (it.genres.isNotEmpty()) {
                    genreDao.insertAllGenres(it.genres)
                }
            }
        } else {
            emit(emptyList<Genre>())
        }
    }.catch {
        emit(emptyList<Genre>())
    }.flowOn(Dispatchers.IO)

}