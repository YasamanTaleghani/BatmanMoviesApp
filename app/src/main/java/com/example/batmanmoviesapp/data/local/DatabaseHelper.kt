package com.example.batmanmoviesapp.data.local


interface DatabaseHelper {
    fun getAllMovies(): List<MovieEntity>

    fun insertAllMovies(movieList: List<MovieEntity>)

    fun movieListSize(): Int
}