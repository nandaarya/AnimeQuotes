package com.example.core.utils

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
}