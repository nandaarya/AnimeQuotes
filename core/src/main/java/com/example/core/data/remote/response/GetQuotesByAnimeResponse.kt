package com.example.core.data.remote.response

import com.google.gson.annotations.SerializedName

//data class GetQuotesByAnimeResponse(
//
//	@field:SerializedName("GetRandomQuotesByAnimeResponse")
//	val getQuotesByAnimeResponse: List<GetQuotesByAnimeResponseItem>
//)

data class GetQuotesByAnimeResponseItem(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("quote")
	val quote: String,

	@field:SerializedName("anime")
	val anime: String,

	@field:SerializedName("character")
	val character: String,
)
