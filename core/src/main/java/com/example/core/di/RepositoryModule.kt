package com.example.core.di

import com.example.core.data.QuotesRepository
import com.example.core.data.local.LocalDataSource
import com.example.core.data.remote.RemoteDataSource
import com.example.core.domain.repository.IQuotesRepository
import com.example.core.utils.AppExecutors
import org.koin.dsl.module

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IQuotesRepository> {
        QuotesRepository(
            get(),
            get(),
        )
    }
}