package com.example.batmanmoviesapp.data.repository

import com.example.batmanmoviesapp.data.local.AppDatabase
import com.example.batmanmoviesapp.data.local.DatabaseHelper
import com.example.batmanmoviesapp.data.local.MovieEntity
import javax.inject.Inject

class MovieLocalRepository @Inject constructor(private val appDatabase: AppDatabase) : DatabaseHelper {

    override fun getAllMovies(): List<MovieEntity> {
        return appDatabase.movieDao().getAll()
    }

    override fun insertAllMovies(movieList: List<MovieEntity>) {
        appDatabase.movieDao().insertAll(movieList)
    }

    override fun movieListSize(): Int {
        return appDatabase.movieDao().getAll().size
    }

}