package com.example.animequotes.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.model.Quote
import com.example.core.domain.usecase.QuotesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class HomeViewModel(private val quotesUseCase: QuotesUseCase) : ViewModel() {

    // Data pertama yang dicari untuk saat pertama membuka aplikasi
    // akan diganti dengan random quote
    val query = MutableStateFlow<String?>("")

    val searchResult = query
        .debounce(1000)
        .distinctUntilChanged()
        .map { query ->
            if (query.isNullOrBlank()) {
                return@map null
            } else {
                return@map query.trim()
            }
        }
        .flatMapLatest { sQuery ->
            quotesUseCase.getQuotesByAnime(sQuery.toString())
        }
        .asLiveData()

}