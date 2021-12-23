package com.guru.pinlock

import FaIcons
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.lottie.LottieLoadingView
import com.guru.composecookbook.theme.typography
import com.guru.fontawesomecomposelib.FaIcon


const val pinSize = 4
const val password = "2580"

@OptIn(ExperimentalMaterialApi::class,
ExperimentalFoundationApi::class)
@Composable
@Preview
fun PinLockView() {
    var inputPin = remember { mutableStateListOf<Int>() }
    val error = remember { mutableStateOf<String>("") }
    val showSuccess = remember { mutableStateOf(false) }

    val context = LocalContext.current

    // Calculation logic should reside in view model or presenter
    if (inputPin.size == 4) {
        if (inputPin.joinToString("") == password) {
            showSuccess.value = true
            error.value = ""
        } else {
            inputPin.clear()
            error.value = "Wrong pin, Please retry!"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        FaIcon(faIcon = FaIcons.UserLock, size = 64.dp, tint = MaterialTheme.colorScheme.onPrimary)

        Text(
            text = "Enter pin to unlock", style = typography.h6, modifier = Modifier
                .padding(16.dp), color = MaterialTheme.colorScheme.onPrimary
        )

        Spacer(modifier = Modifier.height(100.dp))

        if (showSuccess.value) {
            LottieLoadingView(
                context = context, file =
                "success.json",
                iterations = 1,
                modifier = Modifier.size(100.dp)
            )
        } else {
            // PIN ICONS
            Row {
                (0 until pinSize).forEach {
                    Icon(
                        imageVector = if (inputPin.size > it) Icons.Default.Circle else Icons.Outlined.Circle,
                        contentDescription = it.toString(),
                        modifier = Modifier.padding(8.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }

        Text(
            text = error.value,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement
                .SpaceEvenly
        ) {
            (1..3).forEach {
                PinKeyItem(onClick = { inputPin.add(it) }) {
                    Text(text = it.toString(), style = typography.h6)
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement
                .SpaceEvenly
        ) {
            (4..6).forEach {
                PinKeyItem(onClick = { inputPin.add(it) }) {
                    Text(text = it.toString(), style = typography.h6)
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement
                .SpaceEvenly
        ) {
            (7..9).forEach {
                PinKeyItem(onClick = { inputPin.add(it) }) {
                    Text(
                        text = it.toString(), style = typography.h6, modifier = Modifier
                            .padding(4.dp)
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp), horizontalArrangement =
            Arrangement
                .SpaceEvenly, verticalAlignment = Alignment.CenterVertically
        ) {
            FaIcon(
                faIcon = FaIcons.Fingerprint, size = 36
                    .dp, tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.clickable {
                    context.startActivity(BiometricActivity.newIntent(context))
                }
            )

            PinKeyItem(
                onClick = { inputPin.add(0) }, modifier = Modifier.padding(
                    horizontal = 16
                        .dp, vertical = 8.dp
                )
            ) {
                Text(
                    text = "0", style = typography.h6, modifier = Modifier
                        .padding(4.dp)
                )
            }
            FaIcon(
                faIcon = FaIcons.Backspace, size = 36.dp,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.clickable {
                    if (inputPin.isNotEmpty()) {
                        inputPin.removeLast()
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PinKeyItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier.padding(8.dp),
    shape: Shape = androidx.compose.material.MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
    backgroundColor: Color = MaterialTheme.colorScheme.onPrimary,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = 4.dp,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = backgroundColor,
        contentColor = contentColor,
        tonalElevation = elevation,
        onClick = onClick,
        role = Role.Button,
        indication = rememberRipple()
    ) {
        CompositionLocalProvider(LocalContentAlpha provides contentColor.alpha) {
            ProvideTextStyle(MaterialTheme.typography.displayMedium) {
                Box(
                    modifier = Modifier
                        .defaultMinSize(minWidth = 64.dp, minHeight = 64.dp),
                    contentAlignment = Alignment.Center
                ) { content() }
            }
        }
    }
}


