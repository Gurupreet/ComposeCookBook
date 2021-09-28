package com.adwi.betty.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.adwi.betty.data.local.dao.OddsDao
import com.adwi.betty.data.local.entity.Odd

@Database(
    entities = [
        Odd::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BettyDatabase : RoomDatabase() {

    abstract fun oddsDao(): OddsDao
}