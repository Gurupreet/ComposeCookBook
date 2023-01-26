package com.guru.composecookbook.meditation.ui.model

import androidx.compose.ui.graphics.Color
import com.guru.composecookbook.meditation.R
import com.guru.composecookbook.meditation.ui.theme.Blue
import com.guru.composecookbook.meditation.ui.theme.DarkGray
import com.guru.composecookbook.meditation.ui.theme.DeepBlue
import com.guru.composecookbook.meditation.ui.theme.Green
import com.guru.composecookbook.meditation.ui.theme.LightOrange
import com.guru.composecookbook.meditation.ui.theme.Red
import com.guru.composecookbook.meditation.ui.theme.TextWhite

data class Topic(
    val title: String,
    val image: Int,
    val color : Color,
    val textColor : Color
)

val topicList = listOf(
    Topic("Reduce Stress", R.drawable.ic_topic_1, Blue, TextWhite),
    Topic("Improve Performance", R.drawable.ic_topic_2, Red, TextWhite),
    Topic("Increase Happiness", R.drawable.ic_topic_3, LightOrange, DeepBlue),
    Topic("Reduce Anxiety", R.drawable.ic_topic_4, Color.Yellow, DeepBlue),
    Topic("Personal Growth", R.drawable.ic_topic_5, Green, TextWhite),
    Topic("Better Sleep", R.drawable.ic_topic_6, DarkGray, TextWhite),
)
