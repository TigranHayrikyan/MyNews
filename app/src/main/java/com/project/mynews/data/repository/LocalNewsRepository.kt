package com.project.mynews.data.repository

import android.content.Context
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import com.project.mynews.R
import com.project.mynews.data.db.NewsItemDao
import com.project.mynews.data.db.NewsItemEntity
import com.project.mynews.data.model.NewsItem
import com.project.mynews.di.NetworkDispatcher
import com.project.mynews.utils.getImage
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LocalNewsRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    @NetworkDispatcher private val networkDispatcher: CoroutineDispatcher,
    private val newsItemDao: NewsItemDao,
) {
    suspend fun insertNews(newsItemEntity: NewsItemEntity) {
        newsItemDao.insert(newsItemEntity)
    }

    suspend fun getAllNews(): List<NewsItem> {
        val listOfNews = mutableListOf<NewsItem>()
        val allNewsItems = newsItemDao.getAllNewsItems()
        allNewsItems.forEach { news ->
            val newsItem = NewsItem(
                author = "By ${news.author ?: "Unknown source"}",
                title = news.title ?: "",
                description = news.description ?: "",
                link = news.imageUri ?: "",
                image = context.getImage(networkDispatcher, news.imageUri ?: "")
                    ?: context.getDrawable(R.drawable.item_background)?.toBitmap(),
                publishedAt = news.publishedAt ?: "",
                imageUrl = news.imageUri ?: "",
                content = news.content ?: "",
            )
            listOfNews.add(newsItem)
        }
        return listOfNews
    }

    suspend fun deleteAll() {
        newsItemDao.deleteAll()
    }
}