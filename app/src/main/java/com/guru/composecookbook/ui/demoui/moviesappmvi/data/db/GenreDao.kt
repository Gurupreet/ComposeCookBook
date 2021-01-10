package com.guru.composecookbook.ui.demoui.moviesappmvi.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.guru.composecookbook.ui.demoui.moviesappmvi.data.models.Genre

@Dao
interface GenreDao {

    @Transaction
    @Query("select * from genres")
    fun getAllGenres(): LiveData<List<Genre>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllGenres(genres: List<Genre>)
}