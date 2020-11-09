package com.practice.mynews.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class NewsEntity(
    @PrimaryKey
    val id: String,
    val webPublicationDate: String,
    val webTitle: String,
    val thumbnail: String,
    val trailText: String
)