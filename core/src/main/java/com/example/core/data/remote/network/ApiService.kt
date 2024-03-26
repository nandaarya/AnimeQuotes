package com.example.core.data.remote.network

import com.example.core.data.remote.response.GetQuotesByAnimeResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/quotes")
    suspend fun getRandomQuotes(): List<GetQuotesByAnimeResponseItem>

    @GET("/api/quotes/anime")
    suspend fun getQuotesByAnime(
        @Query("title") title: String? = null
    ): List<GetQuotesByAnimeResponseItem>

    @GET("/api/quotes/character")
    suspend fun getQuotesByCharacter(
        @Query("name") name: String? = null
    ): List<GetQuotesByAnimeResponseItem>
}