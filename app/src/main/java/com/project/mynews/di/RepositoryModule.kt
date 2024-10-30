package com.project.mynews.di

import android.content.Context
import com.project.mynews.data.db.NewsDB
import com.project.mynews.data.network.api.NetworkService
import com.project.mynews.data.repository.LocalNewsRepository
import com.project.mynews.data.repository.RemoteNewsRepository
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
    fun provideRemoteNewsRepository(
        @ApplicationContext context: Context,
        networkService: NetworkService,
        @NetworkDispatcher dispatcher: CoroutineDispatcher
    ): RemoteNewsRepository {
        return RemoteNewsRepository(
            networkService = networkService,
            context = context,
            networkDispatcher = dispatcher,
        )
    }

    @Provides
    @Singleton
    fun provideLocalNewsRepository(
        @ApplicationContext context: Context,
        @NetworkDispatcher dispatcher: CoroutineDispatcher
    ): LocalNewsRepository {
        return LocalNewsRepository(
            context = context,
            networkDispatcher = dispatcher,
            newsItemDao = NewsDB.getDatabase(context).newsItemDao()
        )
    }
}
