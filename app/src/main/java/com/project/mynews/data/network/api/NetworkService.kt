package com.project.mynews.data.network.api

import com.project.mynews.data.network.news.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {
    @GET("top-headlines?sources=techcrunch&apiKey=1feb85180eb2447db47c25e3464987a6")
    suspend fun getLast6MonthsNews(): Response<NewsResponse>

    @GET("top-headlines?sources=techcrunch&apiKey=1feb85180eb2447db47c25e3464987a6")
    suspend fun getTechCrunchNews(): Response<NewsResponse>
}
