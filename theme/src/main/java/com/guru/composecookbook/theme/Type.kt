package com.guru.composecookbook.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Material 2 bridge for unmigrated modules; removed in the #238 cleanup PR.
val typography =
  Typography(
    body1 =
      TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal, fontSize = 16.sp),
    body2 =
      TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal, fontSize = 14.sp),
    button =
      TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.W500, fontSize = 14.sp),
    caption =
      TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal, fontSize = 12.sp),
    subtitle1 =
      TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.Gray,
      ),
    subtitle2 =
      TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = Color.Gray,
      ),
  )

// Legacy explicit text styles used directly by some screens; not installed as the theme
// typography (ComposeCookBookTheme/ComposeCookBookMaterial3Theme keep the Material 3
// Typography defaults). The role names below therefore do not imply an M2 -> M3 mapping.
// Bridge kept for unmigrated call sites; removed in the #238 cleanup PR.
val materialTypography =
  androidx.compose.material3.Typography(
    bodyLarge =
      TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal, fontSize = 16.sp),
    bodyMedium =
      TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal, fontSize = 14.sp),
    bodySmall =
      TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal, fontSize = 14.sp),
    displayMedium =
      TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.W500, fontSize = 14.sp),
    labelSmall =
      TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal, fontSize = 12.sp),
    labelLarge =
      TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.Gray,
      ),
    labelMedium =
      TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = Color.Gray,
      ),
  )
