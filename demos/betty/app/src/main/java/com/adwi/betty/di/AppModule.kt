package com.adwi.betty.di

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import com.adwi.betty.util.NotificationUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @OptIn(ExperimentalAnimationApi::class)
    @OptIn(ExperimentalMaterialApi::class)
    @OptIn(ExperimentalFoundationApi::class)
    @Singleton
    @Provides
    fun provideNotificationTools(
        @ApplicationContext context: Context
    ) = NotificationUtil(context)
}