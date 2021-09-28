package com.adwi.betty.data.local.dao

import androidx.room.*
import com.adwi.betty.data.local.entity.Odd
import kotlinx.coroutines.flow.Flow

@Dao
interface OddsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOdds(odds: List<Odd>)

    @Query("SELECT * FROM odd_table ORDER BY difference DESC")
    fun getAllOdds(): Flow<List<Odd>>

    @Update
    suspend fun updateOdd(odd: Odd)

    @Query("DELETE FROM odd_table")
    suspend fun deleteAllOdds()

    @Query("DELETE FROM odd_table WHERE commenceTime < :timestampInMillis AND hasBetOn = 0")
    suspend fun deleteOddsWithNoBetsOlderThan(timestampInMillis: Long)
}