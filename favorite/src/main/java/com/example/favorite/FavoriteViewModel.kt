package com.example.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.QuotesUseCase

class FavoriteViewModel(quotesUseCase: QuotesUseCase): ViewModel() {
    val favoritesQuotes = quotesUseCase.getFavoriteQuotes().asLiveData()
}
