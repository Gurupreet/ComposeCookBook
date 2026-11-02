package com.guru.composecookbook.gmail.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.R
import com.guru.composecookbook.fab.AnimatingFabContent
import com.guru.composecookbook.gmail.ui.create.CreateMessageScreen
import com.guru.composecookbook.gmail.ui.details.MessageDetailScreen
import com.guru.composecookbook.theme.graySurface
import com.guru.composecookbook.theme.green500
import kotlin.math.absoluteValue
import kotlinx.serialization.Serializable

/** Type-safe navigation routes for the Gmail demo. */
@Serializable object GmailHomeRoute

@Serializable object GmailDetailRoute

@Serializable object GmailCreateRoute

@Composable
fun GmailScreen() {
  val navController = rememberNavController()
  NavHost(navController = navController, startDestination = GmailHomeRoute) {
    composable<GmailHomeRoute> { GmailHome(navController = navController) }

    composable<GmailDetailRoute> { MessageDetailScreen(navController = navController) }

    composable<GmailCreateRoute> { CreateMessageScreen(navController = navController) }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GmailHome(navController: NavHostController) {

  val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
  val fabExpandState = remember { mutableStateOf(false) }
  val showUserDialog = remember { mutableStateOf(false) }
  // M2 BottomNavigationItem used the onBackground content color at 0.6 alpha for unselected
  // items; the M3 default (onSurfaceVariant) is full contrast in this theme.
  val navigationBarItemColors =
    NavigationBarItemDefaults.colors(
      selectedIconColor = MaterialTheme.colorScheme.onSurface,
      selectedTextColor = MaterialTheme.colorScheme.onSurface,
      unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
      unselectedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
    )

  // The M2 Scaffold's drawerContent maps to the M3 ModalNavigationDrawer, which wraps the
  // Scaffold instead of hosting it. The M3 drawer needs its own sheet for the background,
  // width and content color that the M2 Scaffold used to provide (M2
  // drawerBackgroundColor/drawerContentColor), so GmailDrawer is wrapped in a
  // ModalDrawerSheet. The scrim keeps the M2 onSurface look (colorScheme.scrim is
  // Color.White in this theme's dark scheme).
  ModalNavigationDrawer(
    drawerContent = {
      ModalDrawerSheet(
        drawerContainerColor = MaterialTheme.colorScheme.background,
        drawerContentColor = MaterialTheme.colorScheme.onBackground,
      ) {
        GmailDrawer()
      }
    },
    drawerState = drawerState,
    scrimColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.32f),
  ) {
    Scaffold(
      floatingActionButton = { GmailFloatingActionButton(navController, fabExpandState.value) },
      content = { paddingValues ->
        GmailContent(
          fabExpandState = fabExpandState,
          drawerState = drawerState,
          navController = navController,
          showUserDialog = showUserDialog,
          modifier = Modifier.padding(paddingValues),
        )
      },
      bottomBar = {
        val background = if (isSystemInDarkTheme()) graySurface else Color.White
        NavigationBar(
          containerColor = background,
          contentColor = MaterialTheme.colorScheme.onSurface,
          // tonalElevation 0 keeps the bar's exact background (background == surface would
          // otherwise pick up a surfaceTint wash).
          tonalElevation = 0.dp,
        ) {
          NavigationBarItem(
            icon = { IconWithBadge(badge = 1, icon = Icons.Outlined.Mail) },
            onClick = {},
            selected = true,
            colors = navigationBarItemColors,
            label = { Text("Mail") },
          )

          NavigationBarItem(
            icon = { IconWithBadge(badge = 0, icon = Icons.Outlined.Call) },
            onClick = {},
            selected = false,
            colors = navigationBarItemColors,
            label = { Text("Meet") },
          )
        }
      },
    )
  }

  UserEmailDialog(showUserDialog)
}

@Composable
fun IconWithBadge(badge: Int, icon: ImageVector, modifier: Modifier = Modifier) {

  Box(modifier = Modifier.size(36.dp)) {
    Icon(
      imageVector = icon,
      modifier = modifier.align(Alignment.BottomCenter),
      contentDescription = null,
    )

    if (badge != 0) {
      Text(
        text = "$badge",
        textAlign = TextAlign.Center,
        fontSize = 12.sp,
        color = MaterialTheme.colorScheme.surface,
        modifier =
          Modifier.padding(top = 4.dp)
            .clip(CircleShape)
            .background(Color.Red)
            .align(Alignment.TopEnd)
            .size(16.dp),
      )
    }
  }
}

@Composable
fun GmailFloatingActionButton(navController: NavHostController, expandState: Boolean) {

  FloatingActionButton(
    onClick = { navController.navigate(GmailCreateRoute) },
    modifier = Modifier.padding(16.dp).height(48.dp).widthIn(min = 48.dp),
    containerColor = MaterialTheme.colorScheme.surface,
    contentColor = MaterialTheme.colorScheme.primary,
  ) {
    AnimatingFabContent(
      icon = {
        Icon(
          imageVector = Icons.Outlined.Edit,
          contentDescription = stringResource(id = R.string.cd_create_new_email),
        )
      },
      text = { Text(text = "Compose", modifier = Modifier.padding(end = 8.dp)) },
      extended = expandState,
    )
  }
}

@Composable
fun GmailContent(
  fabExpandState: MutableState<Boolean>,
  drawerState: DrawerState,
  navController: NavHostController,
  showUserDialog: MutableState<Boolean>,
  modifier: Modifier = Modifier,
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
      oldIndex.value > lazyListState.firstVisibleItemIndex -> { // down
        fabExpandState.value = true
      }
      oldIndex.value < lazyListState.firstVisibleItemIndex -> { // up
        fabExpandState.value = false
      }
      oldIndex.value == lazyListState.firstVisibleItemIndex -> {
        fabExpandState.value = offsetY.value > lazyListState.firstVisibleItemScrollOffset
      }
    }

    // for the initial search offset
    if (
      lazyListState.firstVisibleItemIndex == 0 &&
        lazyListState.firstVisibleItemScrollOffset < searchLayoutHeightPx &&
        !fabExpandState.value
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

  Box(modifier = modifier) {
    LazyColumn(state = lazyListState) {
      item { Spacer(modifier = Modifier.height(72.dp)) }

      items(tweets) {
        val visible = remember(it.id) { mutableStateOf(true) }

        AnimatedVisibility(visible = visible.value) {
          Box(modifier = Modifier.background(green500)) {
            GmailListActionItems(modifier = Modifier.align(Alignment.CenterEnd))
            GmailListItem(it) { navController.navigate(GmailDetailRoute) }
          }
        }
      }
    }

    SearchLayout(searchOffsetY.value, drawerState, showUserDialog) {
      navController.navigate(GmailCreateRoute)
    }
  }
}

@Composable
fun UserEmailDialog(showUserDialog: MutableState<Boolean>) {

  val background = if (isSystemInDarkTheme()) graySurface else Color.White

  if (showUserDialog.value) {
    Dialog(onDismissRequest = { showUserDialog.value = false }) {
      Surface(
        modifier = Modifier,
        shape = MaterialTheme.shapes.medium,
        color = background,
        contentColor = MaterialTheme.colorScheme.onSurface,
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
              modifier = Modifier.fillMaxWidth(),
            )
          }

          GmailUserEmail(R.drawable.p1, "Subash Aryc", "subash@gmail.com", 2)

          Text(
            text = "Manage your Google Account",
            fontSize = 14.sp,
            modifier =
              Modifier.wrapContentWidth()
                .padding(8.dp)
                .border(1.dp, Color.Gray.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .clickable(onClick = {})
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .align(Alignment.CenterHorizontally),
          )

          HorizontalDivider(thickness = 1.dp, modifier = Modifier.fillMaxWidth())

          GmailUserEmail(
            imageId = R.drawable.p2,
            name = "Subash ",
            email = "aryal.subash@yahoo.com",
            badgeCount = 39,
          )
          GmailUserEmail(
            imageId = R.drawable.p2,
            name = "Subash Zi ",
            email = "subashz@gmail.com",
            badgeCount = 10,
          )

          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
          ) {
            Icon(
              imageVector = Icons.Default.PersonAdd,
              tint = MaterialTheme.colorScheme.onSurface,
              modifier = Modifier.padding(8.dp),
              contentDescription = null,
            )

            Text(
              text = "Add another account",
              fontSize = 14.sp,
              color = MaterialTheme.colorScheme.onSurface,
              modifier = Modifier.padding(start = 8.dp),
            )
          }

          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
          ) {
            Icon(
              imageVector = Icons.Default.AccountCircle,
              tint = MaterialTheme.colorScheme.onSurface,
              modifier = Modifier.padding(8.dp),
              contentDescription = null,
            )
            Text(
              text = "Manage accounts on this device",
              fontSize = 14.sp,
              color = MaterialTheme.colorScheme.onSurface,
              modifier = Modifier.padding(start = 8.dp),
            )
          }

          HorizontalDivider(thickness = 1.dp, modifier = Modifier.fillMaxWidth())

          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp).align(Alignment.CenterHorizontally),
          ) {
            Text(
              text = "Privacy Policy",
              fontSize = 12.sp,
              modifier =
                Modifier.clip(RoundedCornerShape(8.dp)).clickable(onClick = {}).padding(8.dp),
            )
            Text(text = "•")
            Text(
              text = "Terms of service",
              fontSize = 12.sp,
              modifier =
                Modifier.clip(RoundedCornerShape(8.dp)).clickable(onClick = {}).padding(8.dp),
            )
          }
        }
      }
    }
  }
}

@Composable
fun GmailUserEmail(imageId: Int, name: String, email: String, badgeCount: Int) {

  Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
    Image(
      painter = painterResource(id = imageId),
      contentDescription = null,
      modifier = Modifier.size(32.dp).clip(CircleShape).clickable(onClick = {}),
    )

    Column(modifier = Modifier.weight(1f).padding(start = 16.dp)) {
      Text(text = name)

      Row {
        Text(text = email, fontSize = 12.sp, modifier = Modifier.weight(1f))

        Text(text = "$badgeCount", fontSize = 12.sp)
      }
    }
  }
}
