package com.guru.composecookbook.ui.moviesappmvi.di

import android.content.Context
import com.guru.composecookbook.ui.moviesappmvi.data.db.MoviesDatabase

object MovieDIGraph {
    fun movieDatabase(context: Context) = MoviesDatabase.getInstance(context)
    fun moviesDao(context: Context) = movieDatabase(context).moviesDao()
}