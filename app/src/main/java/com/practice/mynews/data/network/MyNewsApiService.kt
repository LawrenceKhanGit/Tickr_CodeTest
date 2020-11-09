package com.practice.mynews.data.network

import com.practice.mynews.data.network.response.NewsReponse
import com.practice.mynews.internal.Constants
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

//https://content.guardianapis.com/search?api-key=6b4a67d3-4b61-43f1-8d33-5da7219eb187
// &show-fields=trailText,thumbnail&order-by=newest

interface MyNewsApiService {
    @GET(Constants.URL_LINK)
    fun fetchLatestNews(
    ): Deferred<NewsReponse>
}