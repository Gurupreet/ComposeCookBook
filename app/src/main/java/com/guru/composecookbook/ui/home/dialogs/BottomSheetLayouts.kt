package com.guru.composecookbook.ui.home.dialogs


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.ui.home.lists.VerticalListView
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
    val showCustomScrim = remember { mutableStateOf(false) }
    BottomSheetScaffold(
        content = {
            Box {
                ScafoldContent(coroutineScope, bottomSheetScaffoldState, showCustomScrim)
                if (showCustomScrim.value) {
                    CustomBottomSheetBackgroundScrim(scaffoldState = bottomSheetScaffoldState)
                }
            }
        },
        sheetContent = {
          BottomSheetContent()
        },
        drawerContent = {
           DrawerContent()
        },
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetElevation = 16.dp,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    )
}

@ExperimentalMaterialApi
@Composable
private fun ScafoldContent(
    coroutineScope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    showCustomScrim: MutableState<Boolean>
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(55.dp),
            onClick = {
                showCustomScrim.value = false
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
                showCustomScrim.value = true
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                }
            }) {
            Text(text = "BottomSheet + Custom Scrim")
        }
    }
}


@Composable
fun BottomSheetContent() {
    Text(text = "Bottom Sheet", textAlign = TextAlign.Center, modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp))
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(55.dp),
        onClick = {
        }) {
        Text(text = "Action 1")
    }
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(55.dp),
        onClick = {
        }) {
        Text(text = "Action 2")
    }
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(55.dp),
        onClick = {
        }) {
        Text(text = "Action 3")
    }
}

@Composable
fun DrawerContent() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp), horizontalArrangement = Arrangement
        .SpaceBetween) {
        Text(text = "Item 1")
        Icon(imageVector = Icons.Default.List, contentDescription = "List")
    }

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp), horizontalArrangement = Arrangement
        .SpaceBetween) {
        Text(text = "Item 2")
        Icon(imageVector = Icons.Default.List, contentDescription = "List")
    }

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp), horizontalArrangement = Arrangement
        .SpaceBetween) {
        Text(text = "Item 3")
        Icon(imageVector = Icons.Default.List, contentDescription = "List")
    }
}

@ExperimentalMaterialApi
@Composable
fun CustomBottomSheetBackgroundScrim(scaffoldState: BottomSheetScaffoldState) {
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetProgress = scaffoldState.bottomSheetState.progress
    val bottomSheetFraction = bottomSheetProgress.fraction
    if ((bottomSheetProgress.from == BottomSheetValue.Collapsed && bottomSheetFraction < 1.0)
        || bottomSheetProgress.from == BottomSheetValue.Expanded && scaffoldState.bottomSheetState.progress.fraction.toInt() == 1) {
        Spacer(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.onSurface.copy(alpha = DrawerDefaults.ScrimOpacity))
            .clickable {
                coroutineScope.launch {
                    scaffoldState.bottomSheetState.collapse()
                }
            }
        )
    }
}