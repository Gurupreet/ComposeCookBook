package com.guru.composecookbook.ui.demoui.tiktok

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.setContent
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.theme.tiktokBlack
import com.guru.composecookbook.ui.demoui.spotify.data.Album
import com.guru.composecookbook.ui.demoui.tiktok.discover.DiscoverScreen
import com.guru.composecookbook.ui.demoui.tiktok.home.HomeScreen
import com.guru.composecookbook.ui.demoui.tiktok.home.TiktokCreateIcon
import com.guru.composecookbook.ui.demoui.tiktok.home.TiktokDemoDataProvider
import com.guru.composecookbook.ui.demoui.tiktok.profile.TikTokProfile

sealed class TiktokHomeInteractionEvents {
    data class OpenProfile(val album: Album) : TiktokHomeInteractionEvents()
    data class ShareVideo(val album: Album) : TiktokHomeInteractionEvents()
    object OpenComments : TiktokHomeInteractionEvents()
}

class TiktokActivity : AppCompatActivity() {
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
        bodyContent = { TikTokBodyContent(navController) }
    )
}

@Composable
fun TikTokBottomNavigation(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

    BottomNavigation(backgroundColor = tiktokBlack) {
        TiktokDemoDataProvider.bottomBarList.forEach { tiktokScreen ->
            BottomNavigationItem(
                icon = { BottomBarIcon(tiktokScreen) },
                selected = currentRoute == tiktokScreen.route,
                onClick = {
                    navController.popBackStack(navController.graph.startDestination, false)
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
        TikTokScreen.Home -> Icon(Icons.Filled.Home)
        TikTokScreen.Discover -> Icon(Icons.Filled.Search)
        TikTokScreen.Create -> TiktokCreateIcon()
        TikTokScreen.Inbox -> Icon(Icons.Filled.Inbox)
        TikTokScreen.Me -> Icon(Icons.Filled.Person)
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
        composable(TikTokScreen.Me.route) { TikTokProfile("10", navController) }
        // This navigation is for going to user profile but it should be moved to separate place
        composable("${TikTokScreen.Profile.route}/{userId}") { backStackEntry ->
            TikTokProfile(backStackEntry.arguments?.getString("userId")!!, navController)
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

