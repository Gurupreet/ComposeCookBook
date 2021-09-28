package com.adwi.betty.di

import com.adwi.betty.data.local.BettyDatabase
import com.adwi.betty.data.remote.OddsApi
import com.adwi.betty.data.repository.OddsRepository
import com.adwi.betty.data.repository.OddsRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideSettingsRepository(
        api: OddsApi,
        database: BettyDatabase
    ) = OddsRepository(api, database) as OddsRepositoryInterface
}