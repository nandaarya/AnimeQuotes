package com.example.animequotes.ui.di

import com.example.animequotes.ui.detail.DetailViewModel
import com.example.animequotes.ui.home.HomeViewModel
import com.example.core.domain.usecase.QuotesInteractor
import com.example.core.domain.usecase.QuotesUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<QuotesUseCase> { QuotesInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}