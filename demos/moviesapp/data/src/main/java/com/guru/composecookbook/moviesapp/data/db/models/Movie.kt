package com.guru.composecookbook.moviesapp.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "movies_table")
data class Movie(
    @PrimaryKey val id: Long,
    var title: String,
    val name: String?,
    val poster_path: String?,
    val backdrop_path: String?,
    val release_date: String?,
    val vote_average: Double?,
    val genre_ids: List<Int>?,
    val overview: String,
    val adult: Boolean,
    var tagline: String?,
    val budget: Double?,
    val revenue: Double?,
    val runtime: Int?,
    val homepage: String?,
    val status: String?,
    var addedTime: Long?,
    var dominantRgb: Int = 0
) : Serializable