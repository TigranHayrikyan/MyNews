package com.project.mynews.ui.fragments.newsDetail

import androidx.lifecycle.ViewModel
import com.project.mynews.data.db.NewsItemEntity
import com.project.mynews.data.model.NewsItem
import com.project.mynews.data.repository.LocalNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsDetailFragmentViewModel @Inject constructor(
    private val localNewsRepository: LocalNewsRepository,
) : ViewModel() {

    suspend fun isNewsExists(publishedAt: String?): Boolean {
        return localNewsRepository.isNewsExists(publishedAt ?: "")
    }

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

    suspend fun deleteNews(publishedAt: String) {
        localNewsRepository.deleteNews(publishedAt)
    }
}