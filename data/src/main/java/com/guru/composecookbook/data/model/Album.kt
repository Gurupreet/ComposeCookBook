package com.guru.composecookbook.data.model

import java.io.Serializable

data class Album(
    val id: Int,
    val genre: String = "Pop",
    val artist: String,
    val song: String,
    val descriptions: String,
    val imageId: Int,
    val swiped: Boolean = false
) : Serializable