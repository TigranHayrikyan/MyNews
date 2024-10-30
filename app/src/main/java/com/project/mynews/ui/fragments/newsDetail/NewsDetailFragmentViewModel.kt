package com.project.mynews.ui.fragments.newsDetail

import androidx.lifecycle.ViewModel
import com.project.mynews.data.db.NewsItemEntity
import com.project.mynews.data.model.NewsItem
import com.project.mynews.data.repository.LocalNewsRepository
import com.project.mynews.data.repository.RemoteNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsDetailFragmentViewModel @Inject constructor(
    private val localNewsRepository: LocalNewsRepository,
) : ViewModel() {

    suspend fun insertNews(newsItem: NewsItem) {
        val newsItemEntity = NewsItemEntity(
            author = newsItem.author,
            title = newsItem.title,
            description = newsItem.description,
            link = newsItem.link,
            imageUri = newsItem.imageUrl,
            publishedAt = newsItem.publishedAt,
            content = newsItem.content
        )
        localNewsRepository.insertNews(newsItemEntity)
    }

    suspend fun deleteAll() {
        localNewsRepository.deleteAll()
    }
}