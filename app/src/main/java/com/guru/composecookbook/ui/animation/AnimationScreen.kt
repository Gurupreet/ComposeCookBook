import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.ui.utils.RotateIcon
import com.guru.composecookbook.ui.utils.SubtitleText
import com.guru.composecookbook.ui.utils.TitleText
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import com.guru.composecookbook.R
import com.guru.composecookbook.theme.green200
import com.guru.composecookbook.theme.green500
import com.guru.composecookbook.theme.orange
import com.guru.composecookbook.theme.purple
import com.guru.composecookbook.ui.animation.AnimatableSuspendedAnimations
import com.guru.composecookbook.ui.animation.AnimationsForStates
import com.guru.composecookbook.ui.animation.AnimationsWithVisibilityApi
import com.guru.composecookbook.ui.animation.TransitionAnimationsWithMultipleStates
import com.guru.composecookbook.ui.templates.components.ColorPicker
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

enum class MyAnimationState {
    START, MID, END
}

@Composable
fun AnimationScreen() {
    var animateIcon by remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.semantics { testTag = "Animation Screen" },
        topBar = {
            TopAppBar(
                title = { Text(text = "Animations") },
                elevation = 8.dp,
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
        bodyContent = {
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
        item {  Spacer(modifier = Modifier.padding(4.dp)) }
        item { TitleText(title = "State Animations(Fire and forget)") }
        item { AnimationsForStates() }
        item { AnimationsWithVisibilityApi() }
        item { AnimatableSuspendedAnimations() }
        item { TransitionAnimationsWithMultipleStates() }
        item { ColorPicker(onColorSelected = { /*TODO*/ }) }
        item { Spacer(modifier = Modifier.padding(100.dp)) }
    }
}
