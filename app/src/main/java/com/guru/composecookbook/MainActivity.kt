package com.guru.composecookbook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import androidx.ui.tooling.preview.Preview
import com.google.android.gms.ads.MobileAds
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.ui.Animations.AnimationScreen
import com.guru.composecookbook.ui.demoui.DemoUIList
import com.guru.composecookbook.ui.home.HomeScreen
import com.guru.composecookbook.ui.learnwidgets.WidgetScreen
import com.guru.composecookbook.ui.profile.ProfileScreen
import com.guru.composecookbook.ui.utils.RotateIcon

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //for adView demo
        MobileAds.initialize(this)
        setContent {
            ComposeCookBookTheme {
                // A surface container using the 'background' color from the theme
                val darkTheme = savedInstanceState { false }
                BaseView(darkTheme.value) {
                    MainAppContent(darkTheme)
                }
            }
        }
    }
}

@Composable
fun BaseView(darkTheme: Boolean, content: @Composable() () -> Unit) {
    ComposeCookBookTheme(darkTheme = darkTheme) {
        content()
    }
}

@Composable
fun HomeScreenContent(
    homeScreen: BottomNavType,
    darkTheme: MutableState<Boolean>,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Crossfade(homeScreen) { screen ->
            Surface(color = MaterialTheme.colors.background) {
                when (screen) {
                    BottomNavType.HOME -> HomeScreen(darkTheme)
                    BottomNavType.WIDGETS -> WidgetScreen()
                    BottomNavType.ANIMATION -> AnimationScreen()
                    BottomNavType.DEMOUI -> DemoUIList()
                    BottomNavType.PROFILE -> ProfileScreen()

                }
            }
        }
    }
}

@Composable
fun MainAppContent(darkTheme: MutableState<Boolean>) {
    //Default home screen state is always HOME
    var homeScreenState = savedInstanceState { BottomNavType.HOME }
    Column {
        HomeScreenContent(
            homeScreen = homeScreenState.value,
            darkTheme = darkTheme,
            modifier = Modifier.weight(1f)
        )
        BottomNavigationContent(homeScreenState)
    }
}

@Composable
fun BottomNavigationContent(homeScreenState: MutableState<BottomNavType>) {
    var animate by remember { mutableStateOf(false) }
    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(asset = Icons.Outlined.Home) },
            selected = homeScreenState.value == BottomNavType.HOME,
            onClick = {
                homeScreenState.value = BottomNavType.HOME
                animate = false
            },
            label = { Text(text = stringResource(id = R.string.navigation_item_home)) },
        )
        BottomNavigationItem(
            icon = { Icon(asset = Icons.Outlined.List) },
            selected = homeScreenState.value == BottomNavType.WIDGETS,
            onClick = {
                homeScreenState.value = BottomNavType.WIDGETS
                animate = false
            },
            label = { Text(text = stringResource(id = R.string.navigation_item_widgets)) }
        )
        BottomNavigationItem(
            icon = {
                RotateIcon(
                    state = animate,
                    asset = Icons.Default.PlayArrow,
                    angle = 720f,
                    duration = 2000
                )
            },
            selected = homeScreenState.value == BottomNavType.ANIMATION,
            onClick = {
                homeScreenState.value = BottomNavType.ANIMATION
                animate = true
            },
            label = { Text(text = stringResource(id = R.string.navigation_item_animation)) }
        )
        BottomNavigationItem(
            icon = { Icon(asset = Icons.Outlined.Dashboard) },
            selected = homeScreenState.value == BottomNavType.DEMOUI,
            onClick = {
                homeScreenState.value = BottomNavType.DEMOUI
                animate = false
            },
            label = { Text(text = stringResource(id = R.string.navigation_item_demoui)) }
        )
        BottomNavigationItem(
            icon = { Icon(asset = Icons.Outlined.Person) },
            selected = homeScreenState.value == BottomNavType.PROFILE,
            onClick = {
                homeScreenState.value = BottomNavType.PROFILE
                animate = false
            },
            label = { Text(text = stringResource(id = R.string.navigation_item_profile)) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val darkThemeState = mutableStateOf(false)
    BaseView(darkThemeState.value) {
        MainAppContent(darkThemeState)
    }
}