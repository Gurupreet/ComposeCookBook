package com.guru.composecookbook.tiktok

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.theme.tiktokBlack
import com.guru.composecookbook.tiktok.components.discovers.DiscoverScreen
import com.guru.composecookbook.tiktok.components.home.HomeScreen
import com.guru.composecookbook.tiktok.components.home.TiktokCreateIcon
import com.guru.composecookbook.tiktok.components.profile.ProfileScreen

class TiktokActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // We can also use  SystemUiController(window) to control status theme inside compose.
    window.statusBarColor = ContextCompat.getColor(this, android.R.color.black)

    setContent { ComposeCookBookTheme(darkTheme = true) { TiktokAppContent() } }
  }

  companion object {
    fun newIntent(context: Context) = Intent(context, TiktokActivity::class.java)
  }
}

@Composable
fun TiktokAppContent() {
  val navController = rememberNavController()
  Scaffold(
    bottomBar = { TikTokBottomNavigation(navController) },
    content = { paddingValues ->
      TikTokBodyContent(navController = navController, modifier = Modifier.padding(paddingValues))
    },
  )
}

@Composable
fun TikTokBottomNavigation(navController: NavHostController) {
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination

  // M2 BottomNavigation derived its content color from the background (tiktokBlack -> white
  // icons/labels). The demo hardcodes the bar background to tiktokBlack in both light and dark
  // (it forces darkTheme = true), so onSurface (white in the dark palette) reproduces the old
  // white-on-black look. The M3 selected indicator pill is new and acceptable; the unselected
  // 0.6 alpha is kept from the M2 unselected content alpha.
  NavigationBar(
    containerColor = tiktokBlack,
    contentColor = MaterialTheme.colorScheme.onSurface,
    tonalElevation = 0.dp,
  ) {
    val onSurface = MaterialTheme.colorScheme.onSurface
    val itemColors =
      NavigationBarItemDefaults.colors(
        selectedIconColor = onSurface,
        selectedTextColor = onSurface,
        indicatorColor = onSurface.copy(alpha = 0.12f),
        unselectedIconColor = onSurface.copy(alpha = 0.6f),
        unselectedTextColor = onSurface.copy(alpha = 0.6f),
      )
    TiktokDemoDataProvider.bottomBarList.forEach { tiktokScreen ->
      val isSelected = currentDestination?.hasRoute(tiktokScreen::class) == true
      NavigationBarItem(
        icon = { BottomBarIcon(tiktokScreen) },
        selected = isSelected,
        colors = itemColors,
        onClick = {
          navController.popBackStack(navController.graph.startDestinationId, false)
          if (!isSelected) {
            navController.navigate(tiktokScreen)
          }
        },
        label = {
          if (tiktokScreen != TikTokScreen.Create) {
            Text(text = tiktokScreen.route)
          }
        },
      )
    }
  }
}

@Composable
fun BottomBarIcon(screen: TikTokScreen) {
  when (screen) {
    TikTokScreen.Home -> Icon(imageVector = Icons.Filled.Home, contentDescription = null)
    TikTokScreen.Discover -> Icon(imageVector = Icons.Filled.Search, contentDescription = null)
    TikTokScreen.Create -> TiktokCreateIcon()
    TikTokScreen.Inbox -> Icon(imageVector = Icons.Filled.Inbox, contentDescription = null)
    TikTokScreen.Me -> Icon(imageVector = Icons.Filled.Person, contentDescription = null)
    is TikTokScreen.Profile -> {
      /* this branch added to satisfy exhaustive when */
    }
  }
}

@Composable
fun TikTokBodyContent(navController: NavHostController, modifier: Modifier = Modifier) {
  NavHost(
    navController = navController,
    startDestination = TikTokScreen.Home,
    modifier = modifier,
  ) {
    composable<TikTokScreen.Home> {
      HomeScreen(tiktokInteractionEvents = { handleInteractionEvent(it, navController) })
    }
    composable<TikTokScreen.Discover> { DiscoverScreen() }
    composable<TikTokScreen.Create> { Text(text = "Create:TODO") }
    composable<TikTokScreen.Inbox> { Text(text = "Inbox:TODO") }
    composable<TikTokScreen.Me> { ProfileScreen("10", navController) }
    // This navigation is for going to user profile but it should be moved to separate place
    composable<TikTokScreen.Profile> { backStackEntry ->
      ProfileScreen(backStackEntry.toRoute<TikTokScreen.Profile>().userId, navController)
    }
  }
}

fun handleInteractionEvent(
  tiktokHomeInteractionEvents: TiktokHomeInteractionEvents,
  navController: NavHostController,
) {
  when (tiktokHomeInteractionEvents) {
    is TiktokHomeInteractionEvents.OpenProfile -> {
      navController.navigate(
        TikTokScreen.Profile(userId = tiktokHomeInteractionEvents.album.id.toString())
      )
    }
    else -> {
      // TODO
    }
  }
}
