package com.project.mynews.data.model

import android.graphics.drawable.Drawable

data class NewsItem(
    val author: String?,
    val title: String?,
    val description: String?,
    val link: String?,
    val image: Drawable?
)