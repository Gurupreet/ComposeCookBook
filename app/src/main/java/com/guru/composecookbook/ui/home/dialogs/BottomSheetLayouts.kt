package com.guru.composecookbook.ui.home.dialogs


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.lottie.LottieLoadingView
import com.guru.composecookbook.spotify.R
import com.guru.composecookbook.theme.typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetLayouts() {
    BottomSheetDrawer()
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetDrawer() {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    BottomSheetScaffold(
        drawerBackgroundColor = MaterialTheme.colorScheme.surface,
        sheetBackgroundColor = MaterialTheme.colorScheme.surface,
        backgroundColor = MaterialTheme.colorScheme.background,
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ScafoldContent(
    coroutineScope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    sheetState: ModalBottomSheetState
) {
    var showFullBottomSheet = remember { mutableStateOf(false) }
    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetState,
        sheetBackgroundColor = MaterialTheme.colorScheme.surface,
        sheetContent = {
            if (showFullBottomSheet.value) {
                FullBottomSheetContent {
                    coroutineScope.launch {
                        sheetState.hide()
                    }
                }
            } else {
                BottomSheetContent()
            }
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
                    showFullBottomSheet.value = false
                    coroutineScope.launch {
                        sheetState.show()
                    }
                }) {
                Text(text = "Modal Bottom sheet")
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(55.dp),
                onClick = {
                    showFullBottomSheet.value = true
                    coroutineScope.launch {
                       // sheetState.snapTo(ModalBottomSheetValue.Expanded)
                        sheetState.show()
                    }
                }) {
                Text(text = "Modal Bottom sheet Full")
            }
        }
    }
}


@Composable
fun BottomSheetContent() {
    DrawerContent()
}

@Composable
fun FullBottomSheetContent(onClose: () -> Unit) {
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.Transparent)
        .padding(top = 8.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieLoadingView(context = LocalContext.current, file = "working.json", modifier = Modifier.size(400.dp))
            Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Share Me")
            }
            TextButton(onClick = { onClose.invoke() }) {
                Text("back")
            }
        }
    }
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
    val backgroundColor = MaterialTheme.colorScheme.background.copy(alpha = 0.7f)
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
