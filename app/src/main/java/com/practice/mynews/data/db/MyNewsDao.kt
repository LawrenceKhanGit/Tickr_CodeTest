package com.practice.mynews.data.db

import androidx.room.*

@Dao
interface MyNewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(newsList: List<NewsEntity>)

    @Delete
    suspend fun deleteNewsItem(newsEntity: NewsEntity)

    @Query("select * from `news_table` order by webPublicationDate desc")
    fun getNewsData():List<NewsEntity>
}