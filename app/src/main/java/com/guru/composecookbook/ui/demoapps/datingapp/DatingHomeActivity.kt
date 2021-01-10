package com.guru.composecookbook.ui.demoapps.datingapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.filled.Style
import androidx.compose.material.icons.filled.Textsms
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.guru.composecookbook.R
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.theme.purple
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.demoapps.datingapp.components.ProfileScreen

class DatingHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.purple)

        setContent {
            ComposeCookBookTheme(false) {
                val navType = savedInstanceState { DatingNavType.PEOPLES }
                Scaffold(
                    topBar = { DatingHomeAppbar(navType) },
                    bottomBar = { DatingBottomBar(navType) }
                ) {
                    DatingHomeContent(navType)
                }
            }
        }
    }

    companion object {
        const val DARK_THEME = "darkTheme"
        fun newIntent(context: Context, isDarkTheme: Boolean) =
            Intent(context, DatingHomeActivity::class.java).apply {
                putExtra(DARK_THEME, isDarkTheme)
            }
    }
}

@Composable
fun DatingHomeContent(navType: MutableState<DatingNavType>) {
    Crossfade(current = navType) {
        when (navType.value) {
            DatingNavType.PEOPLES -> DatingHomeScreen()
            DatingNavType.CHATS -> DatingChatScreen()
            DatingNavType.PROFILE -> ProfileScreen()
        }
    }
}

@Composable
fun DatingBottomBar(navType: MutableState<DatingNavType>) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = purple,
        elevation = 4.dp
    ) {
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Filled.Style) },
            selected = navType.value == DatingNavType.PEOPLES,
            onClick = { navType.value = DatingNavType.PEOPLES },
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Filled.Textsms) },
            selected = navType.value == DatingNavType.CHATS,
            onClick = { navType.value = DatingNavType.CHATS },
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Filled.PersonPin) },
            selected = navType.value == DatingNavType.PROFILE,
            onClick = { navType.value = DatingNavType.PROFILE },
        )
    }
}

@Composable
fun DatingHomeAppbar(navType: MutableState<DatingNavType>) {
    val title = when (navType.value) {
        DatingNavType.PEOPLES -> "Discover"
        DatingNavType.CHATS -> "Chats"
        DatingNavType.PROFILE -> "My Profile"
    }
    TopAppBar(
        title = { Text(title, style = typography.h6) },
        actions = {
            IconButton(onClick = {}) {
                Icons.Default.MyLocation
            }
        },
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.surface
    )
}

enum class DatingNavType {
    PEOPLES, CHATS, PROFILE
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview9() {
    ComposeCookBookTheme {
        DatingHomeScreen()
    }
}