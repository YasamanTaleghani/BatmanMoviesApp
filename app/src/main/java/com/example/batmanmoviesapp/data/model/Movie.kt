package com.example.batmanmoviesapp.data.model

import com.google.gson.annotations.SerializedName

data class Movie (
    @SerializedName("Title")
    val title: String = "",
    @SerializedName("Year")
    val year: String = "",
    @SerializedName("imdbID")
    val imdbID: String = "",
    @SerializedName("Type")
    val Type: String = "",
    @SerializedName("Poster")
    val Poster: String = "",
)