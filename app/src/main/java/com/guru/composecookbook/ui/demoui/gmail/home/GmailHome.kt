package com.guru.composecookbook.ui.demoui.gmail.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.ui.demoui.gmail.create.CreateMessageScreen
import com.guru.composecookbook.ui.demoui.gmail.details.MessageDetailScreen
import kotlin.math.absoluteValue


@Composable
fun GmailScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            GmailHome(navController = navController)
        }

        composable("detail") {
            MessageDetailScreen(navController = navController)
        }

        composable("create") {
            CreateMessageScreen(navController = navController)
        }

    }


}

@Composable
fun GmailHome(navController: NavHostController) {

    val scaffoldState = rememberScaffoldState()
    val fabExpandState = remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            GmailFloatingActionButton(navController, fabExpandState.value)
        },
        drawerContent = { GmailDrawer() },
        drawerBackgroundColor = MaterialTheme.colors.background,
        drawerContentColor = MaterialTheme.colors.onBackground,
        scaffoldState = scaffoldState,
        bodyContent = {
            GmailContent(fabExpandState, scaffoldState, navController)
        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White
            ) {

                BottomNavigationItem(
                    icon = {
                        IconWithBadge(badge = 1, icon = Icons.Outlined.Mail)
                    },
                    onClick = {
                    },
                    selected = true,
                    label = { Text("Mail") },
                    alwaysShowLabels = true
                )


                BottomNavigationItem(

                    icon = {

                        IconWithBadge(badge = 0, icon = Icons.Outlined.Call)

                    },
                    onClick = {
                    },
                    selected = true,
                    label = { Text("Meet") },
                    alwaysShowLabels = true
                )


            }
        },
    )
}

@Composable
fun IconWithBadge(badge: Int, icon: ImageVector, modifier: Modifier = Modifier) {

    Box(modifier = Modifier.preferredSize(36.dp)) {
        Icon(
            icon,
            modifier = modifier.align(
                Alignment.BottomCenter
            )
        )

        if (badge != 0) {
            Text(
                text = "$badge",
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = MaterialTheme.colors.surface,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clip(CircleShape)
                    .background(Color.Red)
                    .align(Alignment.TopEnd)
                    .preferredSize(16.dp)
            )
        }
    }

}


@Composable
fun GmailFloatingActionButton(navController: NavHostController, expandState: Boolean) {

    FloatingActionButton(
        onClick = {
            navController.navigate("create")
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
                    Icons.Outlined.Edit
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

//@OptIn(ExperimentalLazyDsl::class)
@Composable
fun GmailContent(
    fabExpandState: MutableState<Boolean>,
    scaffoldState: ScaffoldState,
    navController: NavHostController
) {

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
        } else if (fabExpandState.value) {
            searchOffsetY.value = 0
        } else if (!fabExpandState.value) {
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
                GmailListItem(it) {
                    navController.navigate("detail")
                }
            }

        }


        SearchLayout(searchOffsetY.value, scaffoldState.drawerState)


    }
}

