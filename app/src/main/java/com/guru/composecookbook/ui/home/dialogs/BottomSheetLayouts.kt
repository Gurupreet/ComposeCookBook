package com.guru.composecookbook.ui.home.dialogs


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.spotify.R
import com.guru.composecookbook.theme.typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun BottomSheetLayouts() {
    BottomSheetDrawer()
}


@ExperimentalMaterialApi
@Composable
fun BottomSheetDrawer() {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    BottomSheetScaffold(
        content = {
            Box {
                ScafoldContent(coroutineScope, bottomSheetScaffoldState, sheetState)
            }
        },
        sheetContent = {
            PlayerBottomSheet()
        },
        drawerContent = {
            DrawerContent()
        },
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = if (sheetState.isAnimationRunning || sheetState.isVisible) 0.dp else 65
            .dp,
    )
}

@ExperimentalMaterialApi
@Composable
private fun ScafoldContent(
    coroutineScope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    sheetState: ModalBottomSheetState
) {
    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetState,
        sheetContent = {
            BottomSheetContent()
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(55.dp),
                onClick = {
                    coroutineScope.launch {
                        bottomSheetScaffoldState.drawerState.open()
                    }
                }) {
                Text(text = "Navigation Drawer")
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(55.dp),
                onClick = {
                    coroutineScope.launch {
                        bottomSheetScaffoldState.bottomSheetState.expand()
                    }
                }) {
                Text(text = "Bottom Sheet")
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(55.dp),
                onClick = {
                    coroutineScope.launch {
                        sheetState.show()
                    }
                }) {
                Text(text = "Modal Bottom sheet")
            }
        }
    }
}


@Composable
fun BottomSheetContent() {
    DrawerContent()
}

@Composable
fun DrawerContent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), horizontalArrangement = Arrangement
            .SpaceBetween
    ) {
        Text(text = "Item 1")
        Icon(imageVector = Icons.Default.List, contentDescription = "List")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), horizontalArrangement = Arrangement
            .SpaceBetween
    ) {
        Text(text = "Item 2")
        Icon(imageVector = Icons.Default.List, contentDescription = "List")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), horizontalArrangement = Arrangement
            .SpaceBetween
    ) {
        Text(text = "Item 3")
        Icon(imageVector = Icons.Default.List, contentDescription = "List")
    }
}

@Composable
fun PlayerBottomSheet() {
    val backgroundColor = MaterialTheme.colors.background.copy(alpha = 0.7f)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.adele21),
            modifier = Modifier.size(65.dp),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Someone Like you by Adele",
            style = typography.h6.copy(fontSize = 14.sp),
            modifier = Modifier
                .padding(8.dp)
                .weight(1f),
        )
        Icon(
            imageVector = Icons.Default.FavoriteBorder, modifier = Modifier.padding(8.dp),
            contentDescription = null
        )
        Icon(
            imageVector = Icons.Default.PlayArrow, modifier = Modifier.padding(8.dp),
            contentDescription = null
        )
    }
    Text(text = "Lyrics", style = typography.h6, modifier = Modifier.padding(16.dp))
    Text(
        text = "I heard that you're settled down\n" +
            "That you found a girl and you're married now\n" +
            "I heard that your dreams came true\n" +
            "Guess she gave you things, I didn't give to you\n" +
            "Old friend, why are you so shy?\n" +
            "Ain't like you to hold back or hide from the light\n" +
            "I hate to turn up out of the blue, uninvited\n" +
            "But I couldn't stay away, I couldn't fight it\n" +
            "I had hoped you'd see my face\n" +
            "And that you'd be reminded that for me, it isn't over",
        modifier = Modifier.padding(16.dp)
    )

}
