package com.guru.composecookbook.ui.cryptoappmvvm.utils

import java.text.DecimalFormat

private val formatter2 = DecimalFormat("##.##")
private val formatter3 = DecimalFormat("##.###")
fun Double.roundToTwoDecimals() = formatter2.format(this).toString()
fun Double.roundToThreeDecimals() = formatter3.format(this).toString()