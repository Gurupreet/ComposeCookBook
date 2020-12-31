package com.guru.composecookbook.ui.dialogs


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.R
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.theme.typography

@Composable
fun BottomSheetLayouts() {
    BottomSheetDrawer()
}


@Composable
fun BottomSheetDrawer(
) {
    var sheetState by remember { mutableStateOf(BottomSheetState(show = true)) }
    var drawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)

    BottomDrawerLayout(
        drawerState = drawerState,
        drawerShape = if (sheetState.rounded) RoundedCornerShape(16.dp) else RectangleShape,
        drawerContent = {
            Text(
                text = "Bottom sheet content",
                style = typography.h6,
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            if (sheetState.image) {
                Image(
                    bitmap = imageResource(id = R.drawable.food2),
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = DemoDataProvider.longText,
                style = typography.caption,
                modifier = Modifier.padding(16.dp)
            )
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text(text = "Close Sheet")
            }
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "TODO: NOT WORKING PROPERLY FIX OPEN CLOSE STATES",
                style = typography.h6,
                color = MaterialTheme.colors.onError
            )
            Button(
                onClick = {
                    sheetState = sheetState.copy(show = true)
                    drawerState.open()
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text(text = "Simple bottom sheet")
            }
            Button(
                onClick = {
                    sheetState =
                        sheetState.copy(show = true, image = true, buttons = true, rounded = false)
                    drawerState.open()
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text(text = "Image and buttons")
            }
            Button(
                onClick = {
                    sheetState = sheetState.copy(show = true, fullScree = true, rounded = false)
                    drawerState.open()
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text(text = "Full Screen")
            }
            Button(
                onClick = {
                    sheetState =
                        sheetState.copy(show = true, image = true, buttons = true, rounded = true)
                    drawerState.open()
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text(text = "Rounded Sheet")
            }
        }
    }
}

@Preview
@Composable
fun PreviewBottomSheetLayouts() {
    BottomSheetLayouts()
}

data class BottomSheetState(
    var show: Boolean = false,
    var image: Boolean = false,
    var buttons: Boolean = false,
    var fullScree: Boolean = false,
    var rounded: Boolean = false
)