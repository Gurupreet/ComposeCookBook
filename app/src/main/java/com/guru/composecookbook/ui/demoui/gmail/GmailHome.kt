package com.guru.composecookbook.ui.demoui.gmail

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.ExperimentalLazyDsl
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.ui.Animations.FloatMultiStateAnimationExplode
import kotlin.math.absoluteValue

@Composable
fun GmailHome() {

    val scaffoldState = rememberScaffoldState()
    val fabExpandState = remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            val rippleExplode = remember { mutableStateOf(false) }
            GmailFloatingActionButton(rippleExplode, fabExpandState.value)
            if (rippleExplode.value) {
                FloatMultiStateAnimationExplode(duration = 300)
            }
        },
        drawerContent = { GmailDrawer() },
        drawerBackgroundColor = MaterialTheme.colors.background,
        drawerContentColor = MaterialTheme.colors.onBackground,
        scaffoldState = scaffoldState,
        bodyContent = {
            GmailContent(fabExpandState, scaffoldState)
        }
    )
}

@Composable
fun GmailFloatingActionButton(rippleExplode: MutableState<Boolean>, expandState: Boolean) {

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
                Icon(
                    asset = Icons.Outlined.Edit
                )
            },
            text = {
                Text(
                    text = "Compose",
                    modifier = Modifier.padding(end = 8.dp)
                )
            },
            extended = expandState

        )
    }


}

@OptIn(ExperimentalLazyDsl::class)
@Composable
fun GmailContent(fabExpandState: MutableState<Boolean>, scaffoldState: ScaffoldState) {

    val tweets = remember { DemoDataProvider.tweetList }

    val lazyListState = rememberLazyListState()

    val offsetY = remember { mutableStateOf(0) }
    val oldIndex = remember { mutableStateOf(0) }
    val searchOffsetY = remember { mutableStateOf(0) }

    val searchLayoutHeightPx = with(DensityAmbient.current) { 70.dp.toPx() }

    // ensures that the user intents to have scroll gesture..
    val isVisibleScrolled =
        oldIndex.value != lazyListState.firstVisibleItemIndex ||
        (offsetY.value - lazyListState.firstVisibleItemScrollOffset).absoluteValue > 15

    println("${lazyListState.firstVisibleItemIndex}  ${lazyListState.firstVisibleItemScrollOffset}")

    if (isVisibleScrolled) {
        when {
            oldIndex.value > lazyListState.firstVisibleItemIndex -> {   // down
                fabExpandState.value = true
            }
            oldIndex.value < lazyListState.firstVisibleItemIndex -> {  // up
                fabExpandState.value = false
            }
            oldIndex.value == lazyListState.firstVisibleItemIndex -> {
                fabExpandState.value = offsetY.value > lazyListState.firstVisibleItemScrollOffset
            }
        }

        // for the initial search offset
        if (lazyListState.firstVisibleItemIndex == 0
            && lazyListState.firstVisibleItemScrollOffset < searchLayoutHeightPx
            && !fabExpandState.value
        ) {
            searchOffsetY.value = -lazyListState.firstVisibleItemScrollOffset
        }else if(fabExpandState.value ) {
            searchOffsetY.value = 0
        }else if(!fabExpandState.value) {
            searchOffsetY.value = (-searchLayoutHeightPx).toInt()
        }

    }

    offsetY.value = lazyListState.firstVisibleItemScrollOffset
    oldIndex.value = lazyListState.firstVisibleItemIndex



    Box {

        LazyColumn(state = lazyListState) {

            item {
                Spacer(modifier = Modifier.preferredHeight(72.dp))
            }

            items(tweets) {
                GmailListItem(it)
            }

        }


        SearchLayout(searchOffsetY.value, scaffoldState.drawerState)


    }
}

