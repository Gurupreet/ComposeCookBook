package com.guru.composecookbook.moviesapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.guru.composecookbook.moviesapp.data.db.daos.GenreDao
import com.guru.composecookbook.moviesapp.data.db.daos.MoviesDao
import com.guru.composecookbook.moviesapp.data.db.models.Genre
import com.guru.composecookbook.moviesapp.data.db.models.Movie

@Database(entities = [Movie::class, Genre::class], version = 1, exportSchema = false)
@TypeConverters(ListTypeConverter::class)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun genreDao(): GenreDao

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