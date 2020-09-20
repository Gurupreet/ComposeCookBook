package com.guru.composecookbook.ui.moviesappmvi.di

import android.content.Context
import com.guru.composecookbook.App
import com.guru.composecookbook.ui.cryptoappmvvm.data.CryptoApiMapper
import com.guru.composecookbook.ui.cryptoappmvvm.data.api.CryptoApi
import com.guru.composecookbook.ui.cryptoappmvvm.data.db.CryptoDatabase
import com.guru.composecookbook.ui.cryptoappmvvm.data.repository.CryptoRepositoryImpl
import com.guru.composecookbook.ui.moviesappmvi.data.api.MovieApi
import com.guru.composecookbook.ui.moviesappmvi.data.db.MoviesDatabase
import com.guru.composecookbook.ui.moviesappmvi.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MovieDIGraph {


    private val context = App.applicationContext()

    // create retrofit
    private val movieApi = MovieApi.invoke()


    //create room db
    private val movieDatabase = MoviesDatabase.getInstance(context)
    private val movieDao = movieDatabase.moviesDao()

    //pass dependencies to repository
    val movieRepository by lazy {
        MovieRepositoryImpl(movieApi, movieDao)
    }

    val mainDispatcher: CoroutineDispatcher
        get() = Dispatchers.Main

    val ioDispatcher: CoroutineDispatcher
        get() = Dispatchers.IO

}