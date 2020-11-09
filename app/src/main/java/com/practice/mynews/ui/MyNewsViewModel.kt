package com.practice.mynews.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.mynews.data.repository.MyNewsRepository
import com.practice.mynews.internal.LoadingState
import kotlinx.coroutines.launch

class MyNewsViewModel(val myNewsRepository: MyNewsRepository): ViewModel() {
    private val TAG = "MyNewsViewModel"

    val newsList = myNewsRepository.newsList

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    init {
        getNewsData()
    }

    fun getNewsData() {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.LOADING
                myNewsRepository.fetchNewsData()
                _loadingState.value = LoadingState.LOADED
            } catch (e: Exception) {
                Log.e(TAG, "getNewsData: e", )
                _loadingState.value = LoadingState.error(e.message)
            }
        }
    }
}