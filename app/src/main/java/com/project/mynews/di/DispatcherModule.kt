package com.project.mynews.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FileDispatcher

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Suppress("InjectDispatcher")
    @NetworkDispatcher
    @Singleton
    @Provides
    fun provideNetworkDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Suppress("InjectDispatcher")
    @FileDispatcher
    @Singleton
    @Provides
    fun provideFileDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
