package com.guru.composecookbook.ui.moviesappmvi.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
data class Movie(
    @PrimaryKey val id: Long,
    val title: String,
    val poster_path: String?,
    val overview: String,
    val adult: Boolean,
    var tagline: String?,
    val budget: Double?,
    val revenue: Double?,
    val runtime: Int?,
    val homepage: String?,
    val status: String?,
    var addedTime: Long?
)