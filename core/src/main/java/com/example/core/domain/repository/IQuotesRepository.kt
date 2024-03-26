package com.example.core.domain.repository

import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface IQuotesRepository {
    suspend fun getRandomQuotes(): Flow<ApiResponse<List<Quote>>>
    suspend fun getQuotesByAnime(anime: String): Flow<ApiResponse<List<Quote>>>
    suspend fun getQuotesByCharacter(character: String): Flow<ApiResponse<List<Quote>>>
    fun getFavoriteQuotes(): Flow<ApiResponse<List<Quote>>>
    suspend fun saveFavoriteQuote(quote: Quote)
    suspend fun deleteFavoriteQuote(quote: Quote)
    fun checkFavoriteStatus(id: Int): Flow<Boolean>
}