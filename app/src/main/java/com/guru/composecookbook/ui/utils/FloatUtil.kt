package com.guru.composecookbook.ui.utils

import android.text.TextUtils

/**
 * This is a util class that is created to help the editTexts to convert string values into float.
 */
fun String.toFloatNum(): Float {
    return if (isNotEmpty() && length == 1 &&
        TextUtils.equals(get(0).toString(), ".")
    ) {
        "0.".toFloat()
    } else if (isEmpty()) {
        "0".toFloat()
    } else {
        toFloat()
    }
}