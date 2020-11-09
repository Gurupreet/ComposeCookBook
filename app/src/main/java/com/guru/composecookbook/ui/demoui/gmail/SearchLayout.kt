package com.guru.composecookbook.ui.demoui.gmail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.drawLayer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.absoluteValue

@Composable
fun SearchLayout(scroll: Float, expandStatus: MutableState<Boolean>, drawerState: DrawerState) {

    val searchLayoutHeightDp = 70.dp
    val searchLayoutHeightPx = with(DensityAmbient.current) { searchLayoutHeightDp.toPx() }

    val oldOffset = remember { mutableStateOf(0f) }

    val offset = remember { mutableStateOf(0f) }

    val isScrolledUp = scroll >= oldOffset.value

    // ensures that the user intents to have scroll gesture..
    val isVisibleScrolled = (oldOffset.value - scroll).absoluteValue > 20


    val lastUpScrolledOffset = remember { mutableStateOf(0f) }
    val downScrolledOffsetStatus = remember { mutableStateOf(false) }

    if (isVisibleScrolled)
        oldOffset.value = scroll

    if (isScrolledUp && isVisibleScrolled) {

        // for initial offset.
        if (scroll <= searchLayoutHeightPx) {
            offset.value = scroll
            println("scrolled up value: $scroll")

        } else if (downScrolledOffsetStatus.value) {        // for offset during mid-gesture , up -> down -> up..

            offset.value = (lastUpScrolledOffset.value - scroll)
            if (scroll < lastUpScrolledOffset.value) {
                downScrolledOffsetStatus.value = false
            }

            expandStatus.value = false

        }


    } else if (!isScrolledUp && isVisibleScrolled) {

        if (!downScrolledOffsetStatus.value) {
            lastUpScrolledOffset.value = scroll
            downScrolledOffsetStatus.value = true
        }
        expandStatus.value = true
        offset.value = 0f


        println("scrolled down value: $scroll")


    }



    Row(
        modifier = Modifier
            .drawLayer(translationY = -offset.value)
            .preferredHeight(searchLayoutHeightDp)
            .padding(8.dp)
            .drawShadow(8.dp, shape = RoundedCornerShape(8.dp), clip = false)
            .background(Color.White, shape = RoundedCornerShape(8.dp))

    ) {


        IconButton(
            onClick = { drawerState.open() },
            modifier = Modifier.align(Alignment.CenterVertically),
            icon = { Icon(asset = Icons.Outlined.Menu) }
        )

        TextField(
            value = TextFieldValue("Search in emails"),
            onValueChange = {},
            modifier = Modifier.weight(1f),
            backgroundColor = Color.White,
            activeColor = Color.White,
            inactiveColor = Color.White,
            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp)
        )

        IconButton(
            onClick = {},
            modifier = Modifier.align(Alignment.CenterVertically),
            icon = { Icon(asset = Icons.Outlined.Person) }
        )

    }


}



