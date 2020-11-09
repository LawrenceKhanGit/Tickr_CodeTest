package com.practice.mynews.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.practice.mynews.data.db.MyNewsDao
import com.practice.mynews.data.db.NewsEntityMapper
import com.practice.mynews.data.network.MyNewsDataSource
import com.practice.mynews.data.network.response.NewsNetworkMapper
import com.practice.mynews.data.network.response.NewsReponse
import com.practice.mynews.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyNewsRepository(
    private val myNewsDao: MyNewsDao,
    private val myNewsDataSource: MyNewsDataSource
) {

    private val _newsList = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>>
    get() = _newsList

    init {
        myNewsDataSource.fetchedNewsData.observeForever{ newsResponse ->
            persistFetchedNewsData(newsResponse)
        }
    }

    private fun persistFetchedNewsData(networkResponse: NewsReponse) {
        GlobalScope.launch(Dispatchers.IO) {
            val entityList = NewsNetworkMapper().toEntityObjectList(networkResponse)
            myNewsDao.upsert(entityList)
        }
    }

    suspend fun fetchNewsData() {
        GlobalScope.launch(Dispatchers.IO) {
            myNewsDataSource.fetchNewsData()
            val newsEntityList = myNewsDao.getNewsData()
                _newsList.postValue(NewsEntityMapper().toModelObjectList(newsEntityList))
            }
        }
}