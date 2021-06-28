package com.guru.composecookbook.tiktok

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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

        setContent {
            ComposeCookBookTheme(darkTheme = true) {
                TiktokAppContent()
            }
        }
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
        content = { TikTokBodyContent(navController) }
    )
}

@Composable
fun TikTokBottomNavigation(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.arguments?.getString("route")

    BottomNavigation(backgroundColor = tiktokBlack) {
        TiktokDemoDataProvider.bottomBarList.forEach { tiktokScreen ->
            BottomNavigationItem(
                icon = { BottomBarIcon(tiktokScreen) },
                selected = currentRoute == tiktokScreen.route,
                onClick = {
                    navController.popBackStack(navController.graph.startDestinationId, false)
                    if (currentRoute != tiktokScreen.route) {
                        navController.navigate(tiktokScreen.route)
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
    }
}

@Composable
fun TikTokBodyContent(navController: NavHostController) {
    NavHost(navController, startDestination = TikTokScreen.Home.route) {
        composable(TikTokScreen.Home.route) {
            HomeScreen(tiktokInteractionEvents = { handleInteractionEvent(it, navController) })
        }
        composable(TikTokScreen.Discover.route) { DiscoverScreen() }
        composable(TikTokScreen.Create.route) { Text(text = "Create:TODO") }
        composable(TikTokScreen.Inbox.route) { Text(text = "Inbox:TODO") }
        composable(TikTokScreen.Me.route) { ProfileScreen("10", navController) }
        // This navigation is for going to user profile but it should be moved to separate place
        composable("${TikTokScreen.Profile.route}/{userId}") { backStackEntry ->
            ProfileScreen(backStackEntry.arguments?.getString("userId")!!, navController)
        }
    }
}

fun handleInteractionEvent(
    tiktokHomeInteractionEvents: TiktokHomeInteractionEvents,
    navController: NavHostController
) {
    when (tiktokHomeInteractionEvents) {
        is TiktokHomeInteractionEvents.OpenProfile -> {
            navController.navigate("${TikTokScreen.Profile.route}/${tiktokHomeInteractionEvents.album.id}")
        }
        else -> {
            //TODO
        }
    }
}

