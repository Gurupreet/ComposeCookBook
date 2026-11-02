package com.guru.composecookbook.meditation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.meditation.R

val sans =
  FontFamily(
    listOf(
      Font(R.font.sans_pro_regular, FontWeight.Normal),
      Font(R.font.sans_pro_light, FontWeight.Medium),
      Font(R.font.sans_pro_semi_bold, FontWeight.SemiBold),
      Font(R.font.sans_pro_bold, FontWeight.Bold),
      Font(R.font.sans_pro_black, FontWeight.Black),
    )
  )

// Set of Material typography styles to start with. M2 roles mapped to M3: h1 -> displayLarge,
// h2 -> displayMedium, h6 -> titleLarge, body1 -> bodyLarge. The module's M2 Typography set the
// DeepBlue h1/h2/body1 plus a defaultFontFamily of sans; M3 Typography has no defaultFontFamily,
// so each used style carries the Sans Pro family explicitly. titleLarge keeps the M2 framework
// h6 default's 20sp medium weight (the module did not override h6 and one call site, the daily
// thoughts title, does not set its own weight), and adds the module's Sans Pro family and DeepBlue
// brand color; call sites that set their own color override the brand color.
val Typography =
  Typography(
    displayLarge =
      TextStyle(
        color = DeepBlue,
        fontFamily = sans,
        fontWeight = FontWeight.Black,
        fontSize = 22.sp,
      ),
    displayMedium =
      TextStyle(
        color = DeepBlue,
        fontFamily = sans,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
      ),
    titleLarge =
      TextStyle(
        color = DeepBlue,
        fontFamily = sans,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
      ),
    bodyLarge =
      TextStyle(
        color = DeepBlue,
        fontFamily = sans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
      ),
  )
