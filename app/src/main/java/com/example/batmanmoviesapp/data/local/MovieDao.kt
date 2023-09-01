package com.example.batmanmoviesapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {
    @Query("SELECT * FROM movieentity")
    fun getAll(): List<MovieEntity>

    @Insert
    fun insertAll(movieList: List<MovieEntity>)
}