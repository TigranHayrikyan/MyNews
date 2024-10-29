package com.project.mynews.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.project.mynews.R
import com.project.mynews.data.model.NewsItem
import com.project.mynews.data.network.api.NetworkService
import com.project.mynews.di.NetworkDispatcher
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import java.util.concurrent.ExecutionException
import javax.inject.Inject

class NewsRepository @Inject constructor(
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
                        image = getImage(context = context, article.urlToImage ?: "")
                            ?: context.getDrawable(R.drawable.item_background)?.toBitmap(),
                        publishedAt = article.publishedAt ?: "",
                        content = article.content ?: ""
                    )
                    Log.d("TAG", "getLast6MonthsNews: $newsItem")
                    listOfNews.add(newsItem)
                }
            }
        }
        return listOfNews
    }

    private suspend fun getImage(context: Context, iconUrl: String): Bitmap? {
        return withContext(networkDispatcher) {
            try {
                Glide.with(context)
                    .asBitmap()
                    .load(iconUrl)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                    .submit()
                    .get()
            } catch (e: ExecutionException) {
                Log.e("TAG", "getAppIcon: ${e.message}")
                null
            } catch (e: SocketTimeoutException) {
                Log.e("TAG", "getAppIcon: ${e.message}")
                null
            }
        }
    }
}