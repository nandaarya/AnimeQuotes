package com.example.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.local.entity.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1, exportSchema = false)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

}