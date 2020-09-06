package com.guru.composecookbook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope.weight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.theme.home.HomeScreen
import com.guru.composecookbook.ui.Animations.AnimationScreen
import com.guru.composecookbook.ui.demoui.DemoUIList
import com.guru.composecookbook.ui.demoui.instagram.InstagramHome
import com.guru.composecookbook.ui.widgets.WidgetScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCookBookTheme {
                // A surface container using the 'background' color from the theme
                val darkTheme = remember { mutableStateOf(false) }
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
fun HomeScreenContent(homeScreen: BottomNavType, darkTheme: MutableState<Boolean>) {
    Column(Modifier.weight(1f)) {
        Crossfade(homeScreen) { screen ->
            Surface(color = MaterialTheme.colors.background) {
                when (screen) {
                    BottomNavType.HOME -> HomeScreen(darkTheme)
                    BottomNavType.WIDGETS -> WidgetScreen()
                    BottomNavType.ANIMATION -> AnimationScreen()
                    BottomNavType.DEMOUI -> DemoUIList()
                    BottomNavType.PROFILE -> Text(text = "Coming Soon..")

                }
            }
        }
    }
}

@Composable
fun MainAppContent(darkTheme: MutableState<Boolean>) {
    //Default home screen state is always HOME
    var homeScreenState = state { BottomNavType.HOME }
    Column(modifier = Modifier.fillMaxSize()) {
        HomeScreenContent(homeScreen = homeScreenState.value, darkTheme = darkTheme)
        BottomNavigationContent(homeScreenState)
    }
}

@Composable
fun BottomNavigationContent(homeScreenState: MutableState<BottomNavType>) {
    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(asset = Icons.Outlined.Home) },
            selected = homeScreenState.value == BottomNavType.HOME,
            onSelect = { homeScreenState.value = BottomNavType.HOME },
            label = { Text(text = stringResource(id = R.string.navigation_item_home)) },
        )
        BottomNavigationItem(
            icon = { Icon(asset = Icons.Outlined.List) },
            selected = homeScreenState.value == BottomNavType.WIDGETS,
            onSelect = { homeScreenState.value = BottomNavType.WIDGETS },
            label = { Text(text = stringResource(id = R.string.navigation_item_widgets)) }
        )
        BottomNavigationItem(
            icon = { Icon(asset = Icons.Outlined.PlayArrow) },
            selected = homeScreenState.value == BottomNavType.ANIMATION,
            onSelect = { homeScreenState.value = BottomNavType.ANIMATION },
            label = { Text(text = stringResource(id = R.string.navigation_item_animation)) }
        )
        BottomNavigationItem(
            icon = { Icon(asset = Icons.Outlined.Dashboard) },
            selected = homeScreenState.value == BottomNavType.DEMOUI,
            onSelect = { homeScreenState.value = BottomNavType.DEMOUI },
            label = { Text(text = stringResource(id = R.string.navigation_item_demoui)) }
        )
        BottomNavigationItem(
            icon = { Icon(asset = Icons.Outlined.Person) },
            selected = homeScreenState.value == BottomNavType.PROFILE,
            onSelect = { homeScreenState.value = BottomNavType.PROFILE },
            label = { Text(text = stringResource(id = R.string.navigation_item_profile)) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val darkThemeState = state { false }
    BaseView(darkThemeState.value) {
        MainAppContent(darkThemeState)
    }
}