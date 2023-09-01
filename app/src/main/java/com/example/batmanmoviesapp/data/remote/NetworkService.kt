package com.example.batmanmoviesapp.data.remote

import com.example.batmanmoviesapp.data.model.MovieDetailResponse
import com.example.batmanmoviesapp.data.model.MovieResponse
import com.example.batmanmoviesapp.util.AppConstant
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET(".")
    suspend fun getAllMovies(
        @Query("s") s: String,
        @Query("apiKey") apiKey: String = AppConstant.API_KEY): MovieResponse

    @GET(".")
    suspend fun getMovieDetail(
        @Query("i") i: String,
        @Query("apiKey") apiKey: String = AppConstant.API_KEY): MovieDetailResponse
}