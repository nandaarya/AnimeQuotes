package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quote(
    val id: Int,
    val quote: String,
    val anime: String,
    val character: String,
    var isFavorite: Boolean = false,
) : Parcelable