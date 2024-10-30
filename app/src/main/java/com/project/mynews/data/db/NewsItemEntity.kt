package com.project.mynews.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_items")
data class NewsItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val author: String?,
    val title: String?,
    val description: String?,
    val link: String?,
    val imageUri: String?,
    val content: String?,
    val publishedAt: String?
)