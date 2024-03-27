package com.example.animequotes.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.model.Quote
import com.example.core.domain.usecase.QuotesUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val quotesUseCase: QuotesUseCase) : ViewModel() {

    fun checkFavoriteStatus(id: Int) =
        quotesUseCase.checkFavoriteStatus(id).asLiveData()

    fun saveFavoriteQuote(quote: Quote) {
        viewModelScope.launch {
            quotesUseCase.saveFavoriteQuote(quote)
        }
    }

    fun deleteFavoriteQuote(quote: Quote) {
        viewModelScope.launch {
            quotesUseCase.deleteFavoriteQuote(quote)
        }
    }

    fun getQuotesByCharacter(character: String): LiveData<ApiResponse<List<Quote>>> {
        val resultLiveData = MutableLiveData<ApiResponse<List<Quote>>>()

        viewModelScope.launch {
            quotesUseCase.getQuotesByCharacter(character).collect { result ->
                resultLiveData.value = result
            }
        }

        return resultLiveData
    }
}