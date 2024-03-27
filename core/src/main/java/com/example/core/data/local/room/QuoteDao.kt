package com.example.core.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.local.entity.QuoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {
    @Query("SELECT * FROM quotes")
    fun getFavoriteQuotes(): Flow<List<QuoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavoriteQuote(quote: QuoteEntity)

    @Delete
    suspend fun deleteFavoriteQuote(quote: QuoteEntity)

    @Query("SELECT EXISTS(SELECT * from quotes where id = :id)")
    fun checkFavoriteStatus(id: Int): Flow<Boolean>
}