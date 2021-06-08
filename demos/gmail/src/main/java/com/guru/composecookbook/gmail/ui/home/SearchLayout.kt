package com.guru.composecookbook.gmail.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.gmail.R
import com.guru.composecookbook.theme.graySurface
import com.guru.composecookbook.theme.typography
import kotlinx.coroutines.launch

@Composable
fun SearchLayout(offset: Int, drawerState: DrawerState, showUserDialog: MutableState<Boolean>) {

    val searchLayoutHeightDp = 70.dp
    val background = if (isSystemInDarkTheme()) graySurface else Color.White.copy(alpha = 0.8f)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .graphicsLayer(translationY = offset.toFloat())
            .height(searchLayoutHeightDp)
            .padding(8.dp)
            .shadow(8.dp, shape = RoundedCornerShape(8.dp), clip = false)
            .background(background, shape = RoundedCornerShape(8.dp))

    ) {

        val coroutineScope = rememberCoroutineScope()
        IconButton(
            onClick = {
                coroutineScope.launch {
                    drawerState.open()
                }
            },
        ) {
            Icon(imageVector = Icons.Outlined.Menu, contentDescription = null)
        }

        TextField(
            value = TextFieldValue(""),
            placeholder = { Text("Search in emails") },
            onValueChange = {},
            modifier = Modifier.weight(1f),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = background,
                cursorColor = MaterialTheme.colors.onSurface,
                focusedIndicatorColor = background,
                disabledIndicatorColor = background
            ),
            textStyle = typography.body2
        )

        Image(
            painter = painterResource(id = R.drawable.p3),
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .size(32.dp)
                .clip(CircleShape)
                .clickable(onClick = {
                    showUserDialog.value = true
                })
        )

    }
}




