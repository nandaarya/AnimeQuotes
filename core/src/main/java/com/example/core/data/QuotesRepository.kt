package com.example.core.data

import com.example.core.data.remote.RemoteDataSource
import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.model.Quote
import com.example.core.domain.repository.IQuotesRepository
import kotlinx.coroutines.flow.Flow

class QuotesRepository(
    private val remoteDataSource: RemoteDataSource,
//    private val localDataSource: LocalDataSource
) : IQuotesRepository {
    override suspend fun getRandomQuotes(): Flow<ApiResponse<List<Quote>>> =
        remoteDataSource.getRandomQuotes()

    override suspend fun getQuotesByAnime(anime: String): Flow<ApiResponse<List<Quote>>> =
        remoteDataSource.getQuotesByAnime(anime)

    override suspend fun getQuotesByCharacter(character: String): Flow<ApiResponse<List<Quote>>> =
        remoteDataSource.getQuotesByCharacter(character)

    override fun getFavoriteQuotes(): Flow<ApiResponse<List<Quote>>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveFavoriteQuote(quote: Quote) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavoriteQuote(quote: Quote) {
        TODO("Not yet implemented")
    }

    override fun checkFavoriteStatus(id: Int): Flow<Boolean> {
        TODO("Not yet implemented")
    }

}