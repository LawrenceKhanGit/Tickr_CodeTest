package com.practice.mynews

import android.app.Application
import com.practice.mynews.internal.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyNewsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyNewsApplication)
            androidLogger(Level.DEBUG)
            modules(listOf(apiModule, networkModule, databaseModule, repositoryModule, viewmodelModule))
        }
    }
}