package com.guru.composecookbook.ui.moviesappmvi.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.guru.composecookbook.ui.moviesappmvi.data.models.Movie
import com.guru.composecookbook.ui.moviesappmvi.utils.ListTypeConverter

@Database(entities = [Movie::class], version = 1)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun moviesDao(): MoviesDao

    companion object {
        private var instance: MoviesDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context.applicationContext).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MoviesDatabase::class.java, "movie_database.db"
            ).build()
    }
}