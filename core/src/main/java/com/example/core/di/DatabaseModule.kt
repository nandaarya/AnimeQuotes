package com.example.core.di

import androidx.room.Room
import com.example.core.data.local.room.QuoteDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { get<QuoteDatabase>().quoteDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("nanda".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            QuoteDatabase::class.java, "Quotes.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}