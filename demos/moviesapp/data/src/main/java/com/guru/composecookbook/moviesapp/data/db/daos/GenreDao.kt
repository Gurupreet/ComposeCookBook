package com.guru.composecookbook.moviesapp.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.guru.composecookbook.moviesapp.data.db.models.Genre

@Dao
interface GenreDao {

    @Transaction
    @Query("select * from genres")
    fun getAllGenres(): LiveData<List<Genre>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllGenres(genres: List<Genre>)
}