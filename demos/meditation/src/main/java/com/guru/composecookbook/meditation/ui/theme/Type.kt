package com.guru.composecookbook.meditation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.meditation.R

val sans = FontFamily(
    listOf(
        Font(R.font.sans_pro_regular, FontWeight.Normal),
        Font(R.font.sans_pro_light, FontWeight.Medium),
        Font(R.font.sans_pro_semi_bold, FontWeight.SemiBold),
        Font(R.font.sans_pro_bold, FontWeight.Bold),
        Font(R.font.sans_pro_black, FontWeight.Black),
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        color = DeepBlue,
        fontFamily = sans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    h1 = TextStyle(
        color = DeepBlue,
        fontFamily = sans,
        fontWeight = FontWeight.Black,
        fontSize = 22.sp
    ),
    h2 = TextStyle(
        color = DeepBlue,
        fontFamily = sans,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    defaultFontFamily = sans
)