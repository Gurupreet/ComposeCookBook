package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.theme.purple
import com.guru.composecookbook.theme.purple200
import com.guru.composecookbook.theme.typography
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Composable function demonstrating various types of buttons and their customization options.
 */
@OptIn(ExperimentalMaterialApi::class,
ExperimentalAnimationApi::class)
@Composable
fun AllButtons() {
    Text(text = "Buttons", style = typography.h5, modifier = Modifier.padding(8.dp))

    // Row 1: Basic Buttons
    Row {
        Button(onClick = {}, modifier = Modifier.padding(8.dp)) {
            Text(text = "Main Button")
        }
        TextButton(onClick = {}, modifier = Modifier.padding(8.dp)) {
            Text(text = "Text Button")
        }
        TextButton(onClick = {}, modifier = Modifier.padding(8.dp), enabled = false) {
            Text(text = "Text Disabled")
        }
    }

    // Row 2: More Button Variations
    Row {
        Button(onClick = {}, modifier = Modifier.padding(8.dp), enabled = false) {
            Text(text = "Disabled")
        }
        Button(
            onClick = {},
            modifier = Modifier.padding(8.dp),
            elevation = ButtonDefaults.buttonElevation()
        ) {
            Text(text = "Flat")
        }
        Button(
            onClick = {},
            modifier = Modifier.padding(8.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Rounded")
        }
    }

    // Row 3: Other Button Types
    Row {
        OutlinedButton(onClick = {}, modifier = Modifier.padding(8.dp)) {
            Text(text = "Outline")
        }
        Button(onClick = {}, modifier = Modifier.padding(8.dp)) {
            Row {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(text = "Icon Button")
            }
        }
        Button(onClick = {}, modifier = Modifier.padding(8.dp)) {
            Text(text = "Icon Button")
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = null,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
    //custom background buttons
    val outlineButtonColor = ButtonDefaults.outlinedButtonColors(
        contentColor = purple200,
    )
    val mainButtonColor = ButtonDefaults.buttonColors(
        containerColor = purple,
        contentColor = MaterialTheme.colorScheme.surface
    )
    Row {
        OutlinedButton(
            colors = outlineButtonColor,
            onClick = {},
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Outline colors")
        }
        Button(colors = mainButtonColor, onClick = {}, modifier = Modifier.padding(8.dp)) {
            Text(text = "Custom colors")
        }
    }

    // Gradient Background Buttons
    Row {
        val horizontalGradient = Brush.horizontalGradient(
            colors = listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.inversePrimary),
            0f,
            250f
        )
        val verticalGradient = Brush.verticalGradient(
            colors = listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.inversePrimary),
            startY = 0f,
            endY = 100f
        )
        Text(
            text = "Horizontal gradient",
            style = typography.body2.copy(color = Color.White),
            modifier = Modifier
                .padding(12.dp)
                .clickable(onClick = {})
                .clip(RoundedCornerShape(4.dp))
                .background(brush = horizontalGradient)
                .padding(12.dp)
        )
        Text(
            text = "Vertical gradient",
            style = typography.body1.copy(color = Color.White),
            modifier = Modifier
                .padding(12.dp)
                .clickable(onClick = {})
                .clip(RoundedCornerShape(4.dp))
                .background(brush = verticalGradient)
                .padding(12.dp)
        )
    }

    // Swipe Button Examples
    val swipeButtonState = remember {
        mutableStateOf(SwipeButtonState.INITIAL)
    }
    val coroutineScope = rememberCoroutineScope()
    SwipeButton(
        onSwiped = {
            swipeButtonState.value = SwipeButtonState.SWIPED
            coroutineScope.launch {
                delay(2000)
                swipeButtonState.value = SwipeButtonState.COLLAPSED
            }
        },
        swipeButtonState = swipeButtonState.value,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(60.dp),
        iconPadding = PaddingValues(4.dp),
        shape = CircleShape,
    ) {
        Text(text = "PAY NOW", style = typography.h6.copy(fontSize = 16.sp))
    }
    SwipeButton(
        onSwiped = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        iconPadding = PaddingValues(4.dp),
        swipeButtonState = SwipeButtonState.INITIAL,
        shape = CircleShape,
        enabled = false
    ) {
        Text(text = "Swipe", style = typography.body1)
    }
}


/**
 * A Composable function to preview the AllButtons Composable.
 */
@OptIn(ExperimentalAnimationApi::class,
ExperimentalMaterialApi::class)
@Preview
@Composable
fun PreviewButtons() {
    Column {
        AllButtons()
    }

}