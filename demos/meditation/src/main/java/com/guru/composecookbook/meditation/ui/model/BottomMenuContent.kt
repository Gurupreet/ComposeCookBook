package com.guru.composecookbook.meditation.ui.model

import androidx.annotation.DrawableRes
import com.guru.composecookbook.meditation.R

data class BottomMenuContent(
    val title: String,
    @DrawableRes val iconId: Int
)

val bottomList = listOf(
    BottomMenuContent("Home", R.drawable.ic_home),
    BottomMenuContent("Sleep", R.drawable.ic_sleep),
    BottomMenuContent("Meditate", R.drawable.ic_meditate),
    BottomMenuContent("Music", R.drawable.ic_music),
    BottomMenuContent("Profile", R.drawable.ic_profile),
)
