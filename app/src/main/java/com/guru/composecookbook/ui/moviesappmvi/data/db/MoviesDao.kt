package com.guru.composecookbook.ui.moviesappmvi.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.guru.composecookbook.ui.moviesappmvi.data.models.Movie

@Dao
interface MoviesDao {
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insertMovieList(movies: List<Movie>)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun addMovieDetail(movie: Movie)

    @Transaction
    @Query("select * from movies_table")
    fun getShowingMovies(): LiveData<List<Movie>>
//
//    @Query("DELETE FROM movies_table")
//    fun deleteAll()
//
//    @Query("select * from movies_table where id =:movieId")
//    fun getMovieDetail(movieId: String): LiveData<Movie>
}