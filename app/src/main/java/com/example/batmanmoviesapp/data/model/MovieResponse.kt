package com.example.batmanmoviesapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("Search")
    val search: List<Movie> = arrayListOf(),
    @SerializedName("totalResults")
    val totalResults: String = "",
    @SerializedName("Response")
    val response: String = "",
)
