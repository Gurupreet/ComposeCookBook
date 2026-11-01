package com.guru.composecookbook.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

// Material 2 bridge for unmigrated modules; removed in the #238 cleanup PR.
val shapes =
  Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp),
  )

// Material 3 shapes mirroring the Material 2 corner radii.
val materialShapes =
  androidx.compose.material3.Shapes(
    extraSmall = RoundedCornerShape(0.dp),
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp),
    extraLarge = RoundedCornerShape(0.dp),
  )
