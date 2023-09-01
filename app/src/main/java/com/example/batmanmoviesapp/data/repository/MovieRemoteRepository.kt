package com.example.batmanmoviesapp.data.repository

import com.example.batmanmoviesapp.data.model.Movie
import com.example.batmanmoviesapp.data.model.MovieDetailResponse
import com.example.batmanmoviesapp.data.remote.NetworkService
import javax.inject.Inject

class MovieRemoteRepository @Inject constructor(private val networkService: NetworkService) {
    suspend fun getAllMovies(s: String) : List<Movie> {
        return networkService.getAllMovies(s).search
    }

    suspend fun getMovieDetail(i: String) : MovieDetailResponse {
        return networkService.getMovieDetail(i)
    }
}