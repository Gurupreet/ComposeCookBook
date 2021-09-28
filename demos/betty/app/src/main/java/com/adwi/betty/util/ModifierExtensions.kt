package com.adwi.betty.util

import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

@Stable
fun Modifier.visible(visibility: Boolean): Modifier {
    return if (visibility) {
        this.then(alpha(1f))
    } else {
        this.then(alpha(0f))
    }
}