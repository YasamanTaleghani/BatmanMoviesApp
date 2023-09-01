package com.example.batmanmoviesapp.ui.base

import com.example.batmanmoviesapp.data.local.MovieEntity
import com.example.batmanmoviesapp.data.model.Movie

class MovieEntityAdapter {

    companion object {
        fun createMovie(movieEntity: MovieEntity): Movie {
            return Movie(movieEntity.title, movieEntity.year, movieEntity.imdbID, movieEntity.type, movieEntity.poster)
        }

        fun createMovieEntity(movie: Movie): MovieEntity {
            return MovieEntity(movie.title, movie.year, movie.imdbID, movie.type, movie.poster)
        }
     }
}