package com.practice.mynews.internal.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.practice.mynews.data.network.MyNewsDataSource
import com.practice.mynews.data.network.MyNewsDataSourceImpl
import com.practice.mynews.internal.Constants
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        val cache = Cache(
            androidApplication().cacheDir,
            10 * 1024 * 1024 // 10 MB
        )

        OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor { chain ->
                val url = chain
                    .request()
                    .url()
                    .newBuilder()
                    .addQueryParameter(Constants.API_KEY_FIELD, Constants.API_KEY_VALUE)
                    .addQueryParameter(Constants.SHOW_FIELDS_FIELD, Constants.SHOW_FIELDS_VALUE)
                    .addQueryParameter(Constants.ORDERBY_FIELD, Constants.ORDERBY_VALUE)
                    .build()

                val request = chain
                    .request()
                    .newBuilder()
                    .url(url)
                    .build()

                chain.proceed(request)
            }
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor { chain ->
                val request = chain.request()
                val response = chain.proceed(request)
                chain.proceed(request)
            }
            .build()
    }

    // Provide Retrofit
    single<Retrofit> {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    single {
        MyNewsDataSourceImpl(get()) as MyNewsDataSource
    }



}