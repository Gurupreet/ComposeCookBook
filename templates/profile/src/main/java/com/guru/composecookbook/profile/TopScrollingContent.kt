package com.guru.composecookbook.profile

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.data.R
import com.guru.composecookbook.theme.materialTypography

@Composable
fun TopScrollingContent(scrollState: ScrollState) {
    val visibilityChangeFloat = scrollState.value > initialImageFloat - 20
    Row {
        AnimatedImage(scroll = scrollState.value.toFloat())
        Column(
            modifier = Modifier
                .padding(start = 8.dp, top = 48.dp)
                .alpha(animateFloatAsState(if (visibilityChangeFloat) 0f else 1f).value)
        ) {
            Text(
                text = name,
                style = materialTypography.headlineSmall.copy(fontSize = 18.sp),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Android developer",
                style = materialTypography.labelMedium
            )
        }
    }
}

@Composable
fun AnimatedImage(scroll: Float) {
    val dynamicAnimationSizeValue = (initialImageFloat - scroll).coerceIn(36f, initialImageFloat)
    Image(
        painter = painterResource(id = R.drawable.p1),
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = Modifier
            .padding(start = 16.dp)
            .size(animateDpAsState(Dp(dynamicAnimationSizeValue)).value)
            .clip(CircleShape)
    )
}
