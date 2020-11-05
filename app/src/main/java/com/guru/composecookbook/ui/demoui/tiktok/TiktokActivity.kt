package com.guru.composecookbook.ui.demoui.tiktok

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import com.guru.composecookbook.theme.ComposeCookBookTheme
import androidx.compose.runtime.getValue
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.guru.composecookbook.theme.tiktokBlack
import com.guru.composecookbook.ui.demoui.tiktok.home.HomeScreen
import com.guru.composecookbook.ui.demoui.tiktok.home.TiktokCreateIcon
import com.guru.composecookbook.ui.demoui.tiktok.home.TiktokDemoDataProvider
import com.guru.composecookbook.ui.demoui.tiktok.profile.TikTokProfile


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
        TikTokScreen.Home -> Icon(asset = Icons.Filled.Home)
        TikTokScreen.Discover -> Icon(asset = Icons.Filled.Search)
        TikTokScreen.Create -> TiktokCreateIcon()
        TikTokScreen.Inbox -> Icon(asset = Icons.Filled.Inbox)
        TikTokScreen.Me -> Icon(asset = Icons.Filled.Person)
    }
}

@Composable
fun TikTokBodyContent(navController: NavHostController) {
    NavHost(navController, startDestination = TikTokScreen.Home.route) {
        composable(TikTokScreen.Home.route) { HomeScreen() }
        composable(TikTokScreen.Discover.route) { Text(text = "Discover") }
        composable(TikTokScreen.Create.route) { Text(text = "Create") }
        composable(TikTokScreen.Inbox.route) { Text(text = "Create") }
        composable(TikTokScreen.Me.route) { TikTokProfile() }
    }
}
