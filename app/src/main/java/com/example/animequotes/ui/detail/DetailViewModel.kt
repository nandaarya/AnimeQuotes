package com.example.animequotes.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.model.Quote
import com.example.core.domain.usecase.QuotesUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val quotesUseCase: QuotesUseCase): ViewModel() {
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