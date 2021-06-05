package com.guru.composecookbook.cryptoapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.guru.composecookbook.cryptoapp.data.db.daos.CryptoDao
import com.guru.composecookbook.cryptoapp.data.db.models.Crypto

@Database(entities = [Crypto::class], version = 4, exportSchema = false)
@TypeConverters(ListTypeConverter::class)
abstract class CryptoDatabase : RoomDatabase() {
    abstract fun cryptoDao(): CryptoDao

    companion object {
        private var instance: CryptoDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context.applicationContext).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CryptoDatabase::class.java, "crypto.db"
            ).build()
    }
}