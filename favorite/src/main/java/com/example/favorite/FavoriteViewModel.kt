package com.example.favorite

import androidx.lifecycle.ViewModel
import com.example.core.domain.usecase.QuotesUseCase

class FavoriteViewModel(private val quotesUseCase: QuotesUseCase): ViewModel() {
}
