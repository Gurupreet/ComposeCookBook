package com.guru.composecookbook.meditation.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.guru.composecookbook.meditation.ui.theme.dp10
import com.guru.composecookbook.meditation.ui.theme.dp20
import com.guru.composecookbook.meditation.ui.theme.dp5

@Composable
fun spacerHeight5() = Spacer(modifier = Modifier.height(dp5))

@Composable
fun spacerHeight10() = Spacer(modifier = Modifier.height(dp10))

@Composable
fun spacerHeight20() = Spacer(modifier = Modifier.height(dp20))

@Composable
fun spacerWidth5() = Spacer(modifier = Modifier.width(dp5))

@Composable
fun spacerWidth10() = Spacer(modifier = Modifier.width(dp10))

@Composable
fun spacerWidth20() = Spacer(modifier = Modifier.width(dp20))
