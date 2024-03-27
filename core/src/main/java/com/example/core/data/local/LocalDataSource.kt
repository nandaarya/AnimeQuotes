package com.example.core.data.local

import com.example.core.data.local.room.QuoteDao
import com.example.core.domain.model.Quote
import com.example.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LocalDataSource(private val quoteDao: QuoteDao) {
    fun getFavoriteQuotes(): Flow<List<Quote>> {
        return quoteDao.getFavoriteQuotes().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    suspend fun saveFavoriteQuote(quote: Quote) {
        val quoteEntity = DataMapper.mapDomainToEntity(quote)
        withContext(Dispatchers.IO) { quoteDao.saveFavoriteQuote(quoteEntity) }
    }

    suspend fun deleteFavoriteQuote(quote: Quote) {
        val quoteEntity = DataMapper.mapDomainToEntity(quote)
        withContext(Dispatchers.IO) { quoteDao.deleteFavoriteQuote(quoteEntity) }
    }

    fun checkFavoriteStatus(id: Int): Flow<Boolean> = quoteDao.checkFavoriteStatus(id)
}