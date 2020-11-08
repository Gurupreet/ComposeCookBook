package com.guru.composecookbook.ui.demoui.gmail

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.R
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.Animations.FloatMultiStateAnimationExplode
import kotlin.math.absoluteValue

@Composable
fun GmailHome() {

    val scaffoldState = rememberScaffoldState()
    val lazyListState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Search in emails", style = typography.body2) },
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface,
                elevation = 8.dp,
                navigationIcon = {
                    IconButton(onClick = { scaffoldState.drawerState.open() }) {
                        Icon(asset = Icons.Outlined.Menu)
                    }
                },
                actions = {
                    Image(
                        asset = imageResource(id = R.drawable.p3),
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                            .preferredSize(32.dp).clip(CircleShape)
                    )
                }
            )
        },
        floatingActionButton = {
            val rippleExplode = remember { mutableStateOf(false) }
            GmailFloatingActionButton(rippleExplode, lazyListState)
            if (rippleExplode.value) {
                FloatMultiStateAnimationExplode(duration = 300)
            }
        },
        drawerContent = { GmailDrawer() },
        drawerBackgroundColor = MaterialTheme.colors.background,
        drawerContentColor = MaterialTheme.colors.onBackground,
        scaffoldState = scaffoldState,
        bodyContent = {
            GmailContent(lazyListState)
        }
    )
}

@Composable
fun GmailFloatingActionButton(rippleExplode: MutableState<Boolean>, lazyListState: LazyListState) {

    val isExpanded = remember { mutableStateOf(false) }

    val oldOffset = remember { mutableStateOf(0) }
    val oldIndex = remember { mutableStateOf(0) }

    // ensures that the user intents to have scroll gesture..
    val isVisibleScrolled =
        (oldOffset.value - lazyListState.firstVisibleItemScrollOffset).absoluteValue > 20

    if (oldIndex.value > lazyListState.firstVisibleItemIndex && isVisibleScrolled) {

        println("scroll down ")
        isExpanded.value = true

    } else if (oldIndex.value < lazyListState.firstVisibleItemIndex && isVisibleScrolled) {

        println("scroll up")
        isExpanded.value = false

    }

//    else if (oldIndex.value == lazyListState.firstVisibleItemIndex && isVisibleScrolled) {
//        if (oldOffset.value > lazyListState.firstVisibleItemScrollOffset) {
//            println("scroll down small")
//        } else {
//            println("scroll up small")
//        }
//    }

    if (!lazyListState.isAnimationRunning) {
        oldOffset.value = lazyListState.firstVisibleItemScrollOffset
        oldIndex.value = lazyListState.firstVisibleItemIndex
    }


    FloatingActionButton(
        onClick = {
            rippleExplode.value = !rippleExplode.value
        },
        modifier = Modifier
            .padding(16.dp)
            .preferredHeight(48.dp)
            .widthIn(min = 48.dp),
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.primary
    ) {
        AnimatingFabContent(
            icon = {
                androidx.compose.material.Icon(
                    asset = Icons.Outlined.Edit
                )
            },
            text = {
                Text(
                    text = "Compose",
                    modifier = Modifier.padding(end = 8.dp)
                )
            },
            extended = isExpanded.value

        )
    }


}

@Composable
fun GmailContent(lazyListState: LazyListState) {

    val tweets = remember { DemoDataProvider.tweetList }
    LazyColumnFor(items = tweets, state = lazyListState) { item ->
        GmailListItem(item)
    }

}