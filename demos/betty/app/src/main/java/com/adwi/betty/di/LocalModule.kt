package com.adwi.betty.di

import android.app.Application
import androidx.room.Room
import com.adwi.betty.data.local.BettyDatabase
import com.adwi.betty.util.Constants.Companion.BETTY_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application) =
        Room
            .databaseBuilder(
                application,
                BettyDatabase::class.java,
                BETTY_DATABASE
            )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideOdsDao(appDatabase: BettyDatabase) =
        appDatabase.oddsDao()
}