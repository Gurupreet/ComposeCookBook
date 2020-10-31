package com.guru.composecookbook.ui.demoui.tiktok

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.guru.composecookbook.R
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.theme.graySurface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.*


class TiktokActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    BottomNavigation(backgroundColor = graySurface) {
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
                label = { Text(text = tiktokScreen.route) },
            )
        }
    }
}

@Composable
fun BottomBarIcon(screen: TikTokScreen) {
    when (screen) {
        TikTokScreen.Home -> Icon(asset = Icons.Filled.Home)
        TikTokScreen.Discover -> Icon(asset = Icons.Filled.Search)
        TikTokScreen.Create -> Icon(asset = Icons.Filled.Add)
        TikTokScreen.Inbox -> Icon(asset = Icons.Filled.Inbox)
        TikTokScreen.Me -> Icon(asset = Icons.Filled.Person)
    }
}

@Composable
fun TikTokBodyContent(navController: NavHostController) {
    NavHost(navController, startDestination = TikTokScreen.Home.route) {
        composable(TikTokScreen.Home.route) { Text(text = "Home")}
        composable(TikTokScreen.Discover.route) { Text(text = "Discover") }
        composable(TikTokScreen.Create.route) { Text(text = "Create") }
        composable(TikTokScreen.Inbox.route) { Text(text = "Create") }
        composable(TikTokScreen.Me.route) { Text(text = "Create") }
    }
}
