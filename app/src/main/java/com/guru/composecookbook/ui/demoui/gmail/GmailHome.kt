package com.guru.composecookbook.ui.demoui.gmail

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.R
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.Animations.FloatMultiStateAnimationExplode

@Composable
fun GmailHome() {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Search in emails", style = typography.body2) },
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface,
                elevation = 8.dp,
                navigationIcon = {
                    IconButton(onClick = { scaffoldState.drawerState.open() }) {
                        Icon(asset = Icons.Outlined.Menu)
                    }
                },
                actions = {
                    Image(
                        asset = imageResource(id = R.drawable.p3),
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                            .preferredSize(32.dp).clip(CircleShape)
                    )
                }
            )
        },
        floatingActionButton = {
            val rippleExplode = remember { mutableStateOf(false) }
            GmailFloatingActionButton(rippleExplode)
            if (rippleExplode.value) {
                FloatMultiStateAnimationExplode(duration = 300)
            }
        },
        drawerContent = { GmailDrawer() },
        drawerBackgroundColor = MaterialTheme.colors.background,
        drawerContentColor = MaterialTheme.colors.onBackground,
        scaffoldState = scaffoldState,
        bodyContent = {
            GmailContent()
        }
    )
}

@Composable
fun GmailFloatingActionButton(rippleExplode: MutableState<Boolean>) {
    ExtendedFloatingActionButton(
        text = { Text(text = "Compose") },
        icon = { Icon(asset = Icons.Outlined.Edit) },
        onClick = { rippleExplode.value = !rippleExplode.value },
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.primary
    )
}

@Composable
fun GmailContent() {
    val tweets = remember { DemoDataProvider.tweetList }
    LazyColumnFor(items = tweets) { item ->
        GmailListItem(item)
    }
}