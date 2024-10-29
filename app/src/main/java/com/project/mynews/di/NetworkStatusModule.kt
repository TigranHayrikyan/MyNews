package com.project.mynews.di

import android.content.Context
import com.project.mynews.utils.NetworkStatus
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkStatusModule {

    @Provides
    @Singleton
    fun provideConnectionManager(@ApplicationContext context: Context): NetworkStatus {
        return NetworkStatus(context)
    }
}
