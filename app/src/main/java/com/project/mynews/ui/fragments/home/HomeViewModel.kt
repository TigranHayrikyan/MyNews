package com.project.mynews.ui.fragments.home

import androidx.lifecycle.ViewModel
import com.project.mynews.data.model.NewsItem
import com.project.mynews.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    suspend fun getLatestNews(): List<NewsItem> {
        return newsRepository.getLast6MonthsNews()
    }

}