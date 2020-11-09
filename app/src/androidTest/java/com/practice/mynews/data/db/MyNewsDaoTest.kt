package com.practice.mynews.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Rule

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class MyNewsDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: MyNewsDatabase
    private lateinit var dao: MyNewsDao


    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), MyNewsDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.newsDao()
    }

    @After
    fun tearDown()  {
        database.close()
    }

    @Test
    fun insertNews() = runBlocking {
            withContext(Dispatchers.Main) {
            val newsItem1 = NewsEntity("id1", "date1", "title1", "thumbnail1", "trailtext1")
            val newsItem2 = NewsEntity("id2", "date2", "title2", "thumbnail2", "trailtext2")
            val newsList = mutableListOf<NewsEntity>(newsItem1, newsItem2)
            dao.upsert(newsList)

            val databaseNewsList = dao.getNewsData()
            assertThat(databaseNewsList).contains(newsItem1)
            assertThat(databaseNewsList).contains(newsItem2)
        }
    }


    @Test
    fun deleteNews() = runBlocking {
        val newsItem1 = NewsEntity("id1", "date1", "title1", "thumbnail1", "trailtext1")
        val newsItem2 = NewsEntity("id2", "date2", "title2", "thumbnail2", "trailtext2")
        val newsList = mutableListOf<NewsEntity>(newsItem1, newsItem2)
        dao.upsert(newsList)
        dao.deleteNewsItem(newsItem1)
        val databaseNewsList = dao.getNewsData()
        assertThat(databaseNewsList).doesNotContain(newsItem1)
    }

    @Test
    fun getAllNews() = runBlocking {
        val newsItem1 = NewsEntity("id1", "date1", "title1", "thumbnail1", "trailtext1")
        val newsItem2 = NewsEntity("id2", "date2", "title2", "thumbnail2", "trailtext2")
        val newsList = mutableListOf<NewsEntity>(newsItem1, newsItem2)

        dao.upsert(newsList)
        val result = dao.getNewsData()
        assertThat(result).isNotEmpty()
        assertThat(result).containsExactlyElementsIn(newsList)
        assertThat(result.size).isEqualTo(2)
    }
}