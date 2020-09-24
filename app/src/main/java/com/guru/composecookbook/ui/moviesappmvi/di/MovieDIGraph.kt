package com.guru.composecookbook.ui.moviesappmvi.di

import com.guru.composecookbook.App
import com.guru.composecookbook.ui.moviesappmvi.data.api.MovieApi
import com.guru.composecookbook.ui.moviesappmvi.data.db.MoviesDatabase
import com.guru.composecookbook.ui.moviesappmvi.data.repository.MovieRepositoryImpl
import com.guru.composecookbook.ui.moviesappmvi.data.repository.MoviesLaneRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object MovieDIGraph {


    private val context = App.applicationContext()

    // create retrofit
    private val movieApi = MovieApi.invoke()


    //create room db
    private val movieDatabase = MoviesDatabase.getInstance(context)
    private val movieDao = movieDatabase.moviesDao()
    private val genreDao = movieDatabase.genreDao()

    //pass dependencies to repository
    val movieRepository by lazy {
        MovieRepositoryImpl(movieApi, movieDao, genreDao)
    }

    val moviesLanesRepository by lazy {
        MoviesLaneRepositoryImpl(movieApi)
    }

    val mainDispatcher: CoroutineDispatcher
        get() = Dispatchers.Main

    val ioDispatcher: CoroutineDispatcher
        get() = Dispatchers.IO

}