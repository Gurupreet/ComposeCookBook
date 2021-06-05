package com.guru.composecookbook.moviesapp.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.guru.composecookbook.moviesapp.data.db.models.Movie

@Dao
@TypeConverters()
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToWatchList(movie: Movie)

    @Transaction
    @Query("select * from movies_table")
    fun getMyWatchlist(): LiveData<List<Movie>>

    @Query("DELETE FROM movies_table")
    fun deleteAll()

    @Query("select * from movies_table where id =:movieId")
    fun getMovieDetail(movieId: String): LiveData<Movie>

    @Delete
    fun removeFromMYWatchlist(movie: Movie)
}