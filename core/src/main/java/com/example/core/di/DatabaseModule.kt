package com.example.core.di

import androidx.room.Room
import com.example.core.data.local.room.QuoteDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { get<QuoteDatabase>().quoteDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            QuoteDatabase::class.java, "Quotes.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}