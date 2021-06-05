package com.guru.composecookbook.moviesapp.data

import android.content.Context
import com.guru.composecookbook.moviesapp.data.api.MovieApi
import com.guru.composecookbook.moviesapp.data.db.MoviesDatabase
import com.guru.composecookbook.moviesapp.data.repositories.MovieRepository
import com.guru.composecookbook.moviesapp.data.repositories.MovieRepositoryImpl
import com.guru.composecookbook.moviesapp.data.repositories.MoviesLaneRepositoryImpl
import com.guru.composecookbook.moviesapp.data.repositories.MoviesLanesRepository

object MovieDIGraph {
    // create retrofit
    private val movieApi = MovieApi.invoke()

    fun createMovieRepository(context: Context): MovieRepository {
        val db = MoviesDatabase.getInstance(context)
        return MovieRepositoryImpl(
            movieApi = movieApi,
            moviesDao = db.moviesDao(),
            genreDao = db.genreDao()
        )
    }

    fun createMoviesLaneRepository(context: Context): MoviesLanesRepository {
        val db = MoviesDatabase.getInstance(context)
        return MoviesLaneRepositoryImpl(movieApi = movieApi)
    }
}