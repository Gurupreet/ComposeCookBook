package com.guru.composecookbook.ui.animation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.iconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.colorpicker.ColorPicker
import com.guru.composecookbook.ui.utils.RotateIcon
import com.guru.composecookbook.ui.utils.TestTags
import com.guru.composecookbook.ui.utils.TitleText

enum class MyAnimationState {
    START, MID, END
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimationScreen() {
    var animateIcon by remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.testTag(TestTags.ANIM_SCREEN_ROOT),
        topBar = {
            SmallTopAppBar(
                title = { Text(text = "Animations") },
                navigationIcon = {
                    IconButton(onClick = { animateIcon = !animateIcon }) {
                        RotateIcon(
                            state = animateIcon,
                            asset = Icons.Filled.PlayArrow,
                            angle = 1440f,
                            duration = 3000
                        )
                    }
                }
            )
        },
        content = {
            AnimationScreenContent()
        }
    )
}

@Composable
fun AnimationScreenContent() {
    LazyColumn(
        state = rememberLazyListState(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { Spacer(modifier = Modifier.padding(4.dp)) }
        item { TitleText(title = "State Animations(Fire and forget)") }
        item { AnimationsForStates() }
        item { AnimationsWithVisibilityApi() }
        item { AnimatableSuspendedAnimations() }
        item { TransitionAnimationsWithMultipleStates() }
        item { ColorPicker(onColorSelected = { /*TODO*/ }) }
        item { Spacer(modifier = Modifier.padding(100.dp)) }
    }
}
