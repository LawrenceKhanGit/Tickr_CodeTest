package com.practice.mynews.internal.di

import com.practice.mynews.data.repository.MyNewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        MyNewsRepository(get(), get())
    }
}