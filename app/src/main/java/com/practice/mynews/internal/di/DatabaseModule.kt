package com.practice.mynews.internal.di

import android.app.Application
import androidx.room.Room
import com.practice.mynews.data.db.MyNewsDao
import com.practice.mynews.data.db.MyNewsDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase(application: Application): MyNewsDatabase {
        return Room.databaseBuilder(application, MyNewsDatabase::class.java, "news.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


    fun provideDao(databaseMy: MyNewsDatabase): MyNewsDao {
        return databaseMy.newsDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}