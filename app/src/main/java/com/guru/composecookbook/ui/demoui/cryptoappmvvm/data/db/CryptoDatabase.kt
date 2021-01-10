package com.guru.composecookbook.ui.demoui.cryptoappmvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.guru.composecookbook.ui.demoui.cryptoappmvvm.data.db.daos.CryptoDao
import com.guru.composecookbook.ui.demoui.cryptoappmvvm.data.db.entities.Crypto

@Database(entities = [Crypto::class], version = 3)
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