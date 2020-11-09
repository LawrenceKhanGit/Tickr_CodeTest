package com.practice.mynews.data.network.response

import com.practice.mynews.data.db.NewsEntity
import com.practice.mynews.internal.NetworkMapper
import com.practice.mynews.model.News

class NewsNetworkMapper: NetworkMapper<NewsReponse, NewsEntity, Result> {
    override fun toEntityObject(networkResult: Result): NewsEntity {
        return NewsEntity(
            id = networkResult.id,
            webPublicationDate = networkResult.webPublicationDate,
            webTitle = networkResult.webTitle,
            thumbnail = networkResult.fields.thumbnail,
            trailText = networkResult.fields.trailText

        )
    }

    override fun toEntityObjectList(networkObject: NewsReponse): List<NewsEntity> {
        return networkObject.response.results.map { newsResponseResult->
            toEntityObject(newsResponseResult)
        }
    }
}