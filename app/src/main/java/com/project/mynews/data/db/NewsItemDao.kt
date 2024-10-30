package com.project.mynews.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(newsItemEntity: NewsItemEntity)

    @Query("SELECT * FROM news_items")
    suspend fun getAllNewsItems(): List<NewsItemEntity>

    @Query("DELETE FROM news_items")
    suspend fun deleteAll()

    @Query("DELETE FROM news_items WHERE publishedAt = :publishedAt")
    suspend fun deleteNews(publishedAt: String)

    @Query("SELECT EXISTS(SELECT * FROM news_items WHERE publishedAt = :publishedAt)")
    suspend fun isNewsExists(publishedAt: String): Boolean

}