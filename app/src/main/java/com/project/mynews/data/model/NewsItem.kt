package com.project.mynews.data.model

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsItem(
    val author: String?,
    val title: String?,
    val description: String?,
    val link: String?,
    val image: Bitmap?,
    val publishedAt: String?,
    val content: String?
) : Parcelable