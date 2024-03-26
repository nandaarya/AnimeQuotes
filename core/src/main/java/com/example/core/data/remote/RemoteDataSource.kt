package com.example.core.data.remote

import android.util.Log
import com.example.core.data.remote.network.ApiResponse
import com.example.core.data.remote.network.ApiService
import com.example.core.domain.model.Quote
import com.example.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getRandomQuotes(): Flow<ApiResponse<List<Quote>>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val response = apiService.getRandomQuotes()
                val dataArray = response.map {
                    DataMapper.mapResponseToDomain(it)
                }
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                if (e is HttpException && e.code() == 404) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Error(e.toString()))
                }
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getQuotesByAnime(anime: String): Flow<ApiResponse<List<Quote>>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val response = apiService.getQuotesByAnime(title = anime)
                val dataArray = response.map {
                    DataMapper.mapResponseToDomain(it)
                }
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                if (e is HttpException && e.code() == 404) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Error(e.toString()))
                }
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getQuotesByCharacter(name: String): Flow<ApiResponse<List<Quote>>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val response = apiService.getQuotesByCharacter(name = name)
                val dataArray = response.map {
                    DataMapper.mapResponseToDomain(it)
                }
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                if (e is HttpException && e.code() == 404) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Error(e.toString()))
                }
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}