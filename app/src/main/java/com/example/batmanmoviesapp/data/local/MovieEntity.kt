package com.example.batmanmoviesapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "year") val year: String?,
    @ColumnInfo(name = "imdbID") val imdbID: String?,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "poster") val poster: String?,
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
