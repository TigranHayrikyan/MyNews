package com.project.mynews.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import com.project.mynews.R
import com.project.mynews.data.model.NewsItem
import com.project.mynews.data.network.api.NetworkService
import com.project.mynews.di.NetworkDispatcher
import com.project.mynews.utils.getImage
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RemoteNewsRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    @NetworkDispatcher private val networkDispatcher: CoroutineDispatcher,
    private val networkService: NetworkService,
) {
    @SuppressLint("UseCompatLoadingForDrawables")
    suspend fun getLastNews(): List<NewsItem> {
        val listOfNews = mutableListOf<NewsItem>()
        val lastNews = networkService.getLastNews()
        Log.d("TAG", "getLast6MonthsNews: $lastNews")
        lastNews.body().let { body ->
            body?.articles.let { articles ->
                articles?.forEach { article ->
                    val newsItem = NewsItem(
                        author = "By ${article.author ?: "Unknown source"}",
                        title = article.title ?: "",
                        description = article.description ?: "",
                        link = article.url ?: "",
                        image = context.getImage(networkDispatcher,article.urlToImage ?: "")
                            ?: context.getDrawable(R.drawable.item_background)?.toBitmap(),
                        publishedAt = article.publishedAt ?: "",
                        imageUrl = article.urlToImage,
                        content = article.content ?: ""
                    )
                    listOfNews.add(newsItem)
                }
            }
        }
        return listOfNews
    }
}