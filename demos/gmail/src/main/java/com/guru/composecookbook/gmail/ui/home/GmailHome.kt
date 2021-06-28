package com.guru.composecookbook.gmail.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.fab.AnimatingFabContent
import com.guru.composecookbook.gmail.R
import com.guru.composecookbook.gmail.ui.create.CreateMessageScreen
import com.guru.composecookbook.gmail.ui.details.MessageDetailScreen
import com.guru.composecookbook.theme.graySurface
import com.guru.composecookbook.theme.green500
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
    val showUserDialog = remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            GmailFloatingActionButton(navController, fabExpandState.value)
        },
        drawerContent = { GmailDrawer() },
        drawerBackgroundColor = MaterialTheme.colors.background,
        drawerContentColor = MaterialTheme.colors.onBackground,
        scaffoldState = scaffoldState,
        content = {
            GmailContent(fabExpandState, scaffoldState, navController, showUserDialog)
        },
        bottomBar = {
            val background = if (isSystemInDarkTheme()) graySurface else Color.White
            BottomNavigation(
                backgroundColor = background
            ) {
                BottomNavigationItem(
                    icon = {
                        IconWithBadge(badge = 1, icon = Icons.Outlined.Mail)
                    },
                    onClick = {
                    },
                    selected = true,
                    label = { Text("Mail") },
                )

                BottomNavigationItem(
                    icon = {
                        IconWithBadge(badge = 0, icon = Icons.Outlined.Call)
                    },
                    onClick = {
                    },
                    selected = true,
                    label = { Text("Meet") },
                )


            }
        },
    )

    UserEmailDialog(showUserDialog)
}

@Composable
fun IconWithBadge(badge: Int, icon: ImageVector, modifier: Modifier = Modifier) {

    Box(modifier = Modifier.size(36.dp)) {
        Icon(
            imageVector = icon,
            modifier = modifier.align(
                Alignment.BottomCenter
            ),
            contentDescription = null
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
                    .size(16.dp)
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
            .height(48.dp)
            .widthIn(min = 48.dp),
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.primary
    ) {
        AnimatingFabContent(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = null
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GmailContent(
    fabExpandState: MutableState<Boolean>,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    showUserDialog: MutableState<Boolean>
) {

    val tweets = remember { DemoDataProvider.tweetList }

    val lazyListState = rememberLazyListState()

    val offsetY = remember { mutableStateOf(0) }
    val oldIndex = remember { mutableStateOf(0) }
    val searchOffsetY = remember { mutableStateOf(0) }

    val searchLayoutHeightPx = with(LocalDensity.current) { 70.dp.toPx() }

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
                Spacer(modifier = Modifier.height(72.dp))
            }

            items(tweets) {
                val visible = remember(it.id) { mutableStateOf(true) }

                AnimatedVisibility(visible = visible.value) {
                    Box(modifier = Modifier.background(green500)) {
                        GmailListActionItems(modifier = Modifier.align(Alignment.CenterEnd))
                        GmailListItem(it) {
                            navController.navigate("detail")
                        }
                    }
                }
            }

        }


        SearchLayout(searchOffsetY.value, scaffoldState.drawerState, showUserDialog)

    }
}

@Composable
fun UserEmailDialog(showUserDialog: MutableState<Boolean>) {

    val background = if (isSystemInDarkTheme()) graySurface else Color.White

    if (showUserDialog.value) {
        Dialog(
            onDismissRequest = {
                showUserDialog.value = false
            }
        ) {

            Surface(
                modifier = Modifier,
                shape = MaterialTheme.shapes.medium,
                color = background,
                contentColor = MaterialTheme.colors.onSurface
            ) {

                Column {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { showUserDialog.value = false }) {
                            Icon(Icons.Outlined.Close, contentDescription = null)
                        }

                        Text(
                            text = "Google",
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                    }

                    GmailUserEmail(R.drawable.p1, "Subash Aryc", "subash@gmail.com", 2)

                    Text(
                        text = "Manage your Google Account",
                        fontSize = 14.sp,
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(8.dp)
                            .border(1.dp, Color.Gray.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp))
                            .clickable(onClick = {})
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Divider(
                        thickness = 1.dp,
                        modifier = Modifier.fillMaxWidth()
                    )

                    GmailUserEmail(
                        imageId = R.drawable.p2,
                        name = "Subash ",
                        email = "aryal.subash@yahoo.com",
                        badgeCount = 39
                    )
                    GmailUserEmail(
                        imageId = R.drawable.p2,
                        name = "Subash Zi ",
                        email = "subashz@gmail.com",
                        badgeCount = 10
                    )


                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PersonAdd,
                            tint = MaterialTheme.colors.onSurface,
                            modifier = Modifier.padding(8.dp),
                            contentDescription = null
                        )

                        Text(
                            text = "Add another account",
                            fontSize = 14.sp,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }


                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            tint = MaterialTheme.colors.onSurface,
                            modifier = Modifier.padding(8.dp),
                            contentDescription = null
                        )
                        Text(
                            text = "Manage accounts on this device",
                            fontSize = 14.sp,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Divider(
                        thickness = 1.dp,
                        modifier = Modifier.fillMaxWidth()
                    )


                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "Privacy Policy",
                            fontSize = 12.sp,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .clickable(onClick = {})
                                .padding(8.dp)
                        )
                        Text(
                            text = "•"
                        )
                        Text(
                            text = "Terms of service",
                            fontSize = 12.sp,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .clickable(onClick = {})
                                .padding(8.dp)

                        )
                    }

                }
            }
        }

    }

}

@Composable
fun GmailUserEmail(imageId: Int, name: String, email: String, badgeCount: Int) {

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .clickable(onClick = {

                })
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            Text(text = name)

            Row {
                Text(
                    text = email,
                    fontSize = 12.sp,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "$badgeCount",
                    fontSize = 12.sp
                )
            }
        }


    }
}