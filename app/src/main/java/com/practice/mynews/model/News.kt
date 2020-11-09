package com.practice.mynews.model

data class News(
    val id: String,
    val webPublicationDate: String,
    val webTitle: String,
    val thumbnail: String?,
    val trailText: String
)