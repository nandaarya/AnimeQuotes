package com.example.core.domain.usecase

import com.example.core.domain.model.Quote
import com.example.core.domain.repository.IQuotesRepository

class QuotesInteractor(private val quotesRepository: IQuotesRepository): QuotesUseCase {
    override suspend fun getRandomQuotes() = quotesRepository.getRandomQuotes()
    override suspend fun getQuotesByAnime(anime: String) = quotesRepository.getQuotesByAnime(anime)
    override suspend fun getQuotesByCharacter(character: String) = quotesRepository.getQuotesByCharacter(character)
    override fun getFavoriteQuotes() = quotesRepository.getFavoriteQuotes()
    override suspend fun saveFavoriteQuote(quote: Quote) = quotesRepository.saveFavoriteQuote(quote)
    override suspend fun deleteFavoriteQuote(quote: Quote) = quotesRepository.deleteFavoriteQuote(quote)
    override fun checkFavoriteStatus(id: Int) = quotesRepository.checkFavoriteStatus(id)
}