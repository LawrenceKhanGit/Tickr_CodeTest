package com.practice.mynews.data.db

import com.practice.mynews.internal.EntityMapper
import com.practice.mynews.model.News

class NewsEntityMapper: EntityMapper<NewsEntity, News> {
    override fun toModelObject(entityObject: NewsEntity): News {
        return News(id = entityObject.id,
        webPublicationDate = entityObject.webPublicationDate,
        webTitle = entityObject.webTitle,
        thumbnail = entityObject.thumbnail,
        trailText = entityObject.trailText)
    }

    override fun toModelObjectList(entityObjectList: List<NewsEntity>): List<News> {
        return entityObjectList.map { newsEntity ->
            toModelObject(newsEntity)
        }
    }
}