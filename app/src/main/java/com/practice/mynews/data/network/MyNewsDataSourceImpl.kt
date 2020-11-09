package com.practice.mynews.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.practice.mynews.data.network.response.NewsReponse

class MyNewsDataSourceImpl(
    private val myNewsApiService: MyNewsApiService
) : MyNewsDataSource {
    private val TAG = "MyNewsDataSourceImpl"
    private val _fetchedNewsData = MutableLiveData<NewsReponse>()
    override val fetchedNewsData: LiveData<NewsReponse>
        get() = _fetchedNewsData

    override suspend fun fetchNewsData() {
        val fetchedNews = myNewsApiService.fetchLatestNews().await()
        _fetchedNewsData.postValue(fetchedNews)
    }
}