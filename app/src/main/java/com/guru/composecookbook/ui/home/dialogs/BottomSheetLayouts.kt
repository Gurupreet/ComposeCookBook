package com.guru.composecookbook.ui.home.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
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
import com.guru.composecookbook.data.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BottomSheetLayouts() {
    BottomSheetDrawer()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetDrawer() {
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    val showBottomSheet = remember { mutableStateOf(false) }

    DismissibleNavigationDrawer(
        drawerContent = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 16.dp)
            ) {
                DrawerContent()
            }
        },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                ScafoldContent(
                    coroutineScope = coroutineScope,
                    showBottomSheet = { showBottomSheet.value = it }
                )
                
                if (showBottomSheet.value) {
                    ModalBottomSheet(
                        onDismissRequest = { showBottomSheet.value = false },
                        sheetState = sheetState,
                        dragHandle = { BottomSheetDefaults.DragHandle() },
                    ) {
                        PlayerBottomSheet()
                    }
                }
            }
        }
    )
}

@Composable
private fun ScafoldContent(
    coroutineScope: CoroutineScope,
    showBottomSheet: (Boolean) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(55.dp),
            onClick = { showBottomSheet(true) }
        ) {
            Text(text = "Show Bottom Sheet")
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
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 14.sp),
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
    Text(text = "Lyrics", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(16.dp))
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
