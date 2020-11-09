package com.practice.mynews.data.network.response

data class Result(
    val fields: Fields,
    val id: String,
    val webPublicationDate: String,
    val webTitle: String
)