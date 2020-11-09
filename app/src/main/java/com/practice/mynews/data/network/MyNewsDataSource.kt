package com.practice.mynews.data.network

import androidx.lifecycle.LiveData
import com.practice.mynews.data.network.response.NewsReponse

interface MyNewsDataSource {
    val fetchedNewsData: LiveData<NewsReponse>

    suspend fun fetchNewsData()
}