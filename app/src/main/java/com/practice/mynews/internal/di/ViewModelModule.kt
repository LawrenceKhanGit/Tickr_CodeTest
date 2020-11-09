package com.practice.mynews.internal.di

import com.practice.mynews.ui.MyNewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel { MyNewsViewModel(get()) }
}