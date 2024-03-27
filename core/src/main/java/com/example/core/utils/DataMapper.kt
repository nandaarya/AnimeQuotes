package com.example.core.utils

import com.example.core.data.local.entity.QuoteEntity
import com.example.core.data.remote.response.GetQuotesByAnimeResponseItem
import com.example.core.domain.model.Quote

object DataMapper {
    fun mapResponseToDomain(input: GetQuotesByAnimeResponseItem): Quote =
        Quote(
            id = input.id,
            quote = input.quote,
            anime = input.anime,
            character = input.character,
            isFavorite = false
        )

    fun mapEntitiesToDomain(input: List<QuoteEntity>): List<Quote> =
        input.map {
            Quote(
                id = it.id,
                quote = it.quote,
                anime = it.anime,
                character = it.character,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Quote) = QuoteEntity(
        id = input.id,
        quote = input.quote,
        anime = input.anime,
        character = input.character,
        isFavorite = input.isFavorite
    )
}