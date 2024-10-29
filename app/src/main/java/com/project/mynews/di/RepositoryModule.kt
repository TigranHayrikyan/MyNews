package com.project.mynews.di

import android.content.Context
import com.project.mynews.data.network.api.NetworkService
import com.project.mynews.data.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(
        @ApplicationContext context: Context,
        networkService: NetworkService,
        @NetworkDispatcher dispatcher: CoroutineDispatcher
    ): NewsRepository {
        return NewsRepository(
            networkService = networkService,
            context = context,
            networkDispatcher = dispatcher
        )
    }
}
