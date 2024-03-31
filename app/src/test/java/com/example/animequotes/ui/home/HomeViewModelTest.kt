package com.example.animequotes.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.model.Quote
import com.example.core.domain.usecase.QuotesUseCase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var quotesUseCase: QuotesUseCase

    @Mock
    private lateinit var observer: Observer<ApiResponse<List<Quote>>>

    @Captor
    private lateinit var captor: ArgumentCaptor<ApiResponse<List<Quote>>>

    private lateinit var viewModel: HomeViewModel

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockitoAnnotations.openMocks(this)
        viewModel = HomeViewModel(quotesUseCase)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    private fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()

    @Test
    fun `getQuotesByAnime success`() = runTest {
        val dummyGames = listOf(
            Quote(
                id = 1, quote = "dummy 1", anime = "dummy", character = "dummy"
            ),
            Quote(
                id = 2, quote = "dummy 2", anime = "dummy", character = "dummy"
            ),
            Quote(
                id = 3, quote = "dummy 3", anime = "dummy", character = "dummy"
            )
        )
        val dummyResponse = ApiResponse.Success(dummyGames)
        val query = "query"

        `when`(quotesUseCase.getQuotesByAnime(query)).thenReturn(flowOf(dummyResponse))

        viewModel.query.value = query
        viewModel.searchResult.observeForever(observer)

        verify(observer, timeout(3000)).onChanged(capture(captor))
        assertEquals(dummyResponse, captor.value)

        viewModel.searchResult.removeObserver(observer)
    }

    @Test
    fun `getQuotesByAnime error`() = runTest {

        val query = "query"
        val dummyError = "Error message"
        val dummyResponse = ApiResponse.Error(dummyError)

        `when`(quotesUseCase.getQuotesByAnime(query)).thenReturn(flowOf(dummyResponse))

        viewModel.query.value = query
        viewModel.searchResult.observeForever(observer)

        verify(observer, timeout(3000)).onChanged(capture(captor))
        assertEquals(dummyResponse, captor.value)

        viewModel.searchResult.removeObserver(observer)
    }

}