package com.example.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "quote")
    val quote: String,

    @ColumnInfo(name = "anime")
    val anime: String,

    @ColumnInfo(name = "character")
    val character: String,

    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean = false,
)