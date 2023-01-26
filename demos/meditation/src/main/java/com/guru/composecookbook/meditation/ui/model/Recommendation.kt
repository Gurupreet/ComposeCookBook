package com.guru.composecookbook.meditation.ui.model

import com.guru.composecookbook.meditation.R

data class Recommendation(
    val title : String,
    val description : String,
    val image : Int
)

val recommendationList = listOf<Recommendation>(
    Recommendation(
        "Focus",
        description = "MEDITATION • 3-10 MIN",
        R.drawable.ic_recommendation_1
    ),
    Recommendation(
        "Happiness",
        description = "MEDITATION • 3-10 MIN",
        R.drawable.ic_recommendation_2
    ),
    Recommendation(
        "Focus",
        description = "MEDITATION • 3-10 MIN",
        R.drawable.ic_recommendation_1
    ),
    Recommendation(
        "Happiness",
        description = "MEDITATION • 3-10 MIN",
        R.drawable.ic_recommendation_2
    ),
)
