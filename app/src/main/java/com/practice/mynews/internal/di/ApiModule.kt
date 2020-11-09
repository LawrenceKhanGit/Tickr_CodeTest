package com.practice.mynews.internal.di

import com.practice.mynews.data.network.MyNewsApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single {
        get<Retrofit>().create(MyNewsApiService::class.java)
    }
}