package com.project.mynews.ui.fragments.favorites

import androidx.lifecycle.ViewModel
import com.project.mynews.data.model.NewsItem
import com.project.mynews.data.repository.LocalNewsRepository
import com.project.mynews.data.repository.RemoteNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val localNewsRepository: LocalNewsRepository,
) : ViewModel() {

    suspend fun getAllNews(): List<NewsItem> {
        return localNewsRepository.getAllNews()
    }
}