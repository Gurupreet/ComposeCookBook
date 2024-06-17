package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.material.RadioButton
import androidx.compose.material.Slider
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.typography

/**
 * Composable function `Toggles` demonstrates various UI elements such as Checkbox, Switch, RadioButtons, and Sliders.
 */
@Composable
fun Toggles() {

    // Display header text with custom style and padding
    Text(
        text = "Toggles, Switch, Sliders",
        style = typography.h6,
        modifier = Modifier.padding(8.dp)
    )

    // Checkbox with mutable state
    var checked by remember { mutableStateOf(true) }
    var switched by remember { mutableStateOf(true) }
    Row {

        // Checkbox with mutable state
        Checkbox(
            checked = checked,
            modifier = Modifier.padding(8.dp),
            onCheckedChange = { checked = !checked })

        // Switch with mutable state and custom color for checked state
        Switch(
            checked = switched,
            colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier.padding(8.dp),
            onCheckedChange = { switched = it }
        )
    }

    // RadioButtons example with selectable options
    var selected by remember { mutableStateOf("Kotlin") }
    Row {
        RadioButton(selected = selected == "Kotlin", onClick = { selected = "Kotlin" })
        Text(
            text = "Kotlin",
            modifier = Modifier
                .clickable(onClick = { selected = "Kotlin" })
                .padding(start = 4.dp)
        )
        Spacer(modifier = Modifier.size(4.dp))
        RadioButton(selected = selected == "Java", onClick = { selected = "Java" })
        Text(
            text = "Java",
            modifier = Modifier
                .clickable(onClick = { selected = "Java" })
                .padding(start = 4.dp)
        )
        Spacer(modifier = Modifier.size(4.dp))
        RadioButton(selected = selected == "Swift", onClick = { selected = "Swift" })
        Text(
            text = "Swift",
            modifier = Modifier
                .clickable(onClick = { selected = "Swift" })
                .padding(start = 4.dp)
        )
    }


    // Sliders examples with different configurations
    var sliderState by remember { mutableStateOf(0f) }

    // Basic Slider with continuous range from 0 to 1
    Slider(value = sliderState, modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        onValueChange = { newValue ->
            sliderState = newValue
        }
    )

    var sliderState2 by remember { mutableStateOf(20f) }

    // Slider with defined value range (0 to 100) and 5 discrete steps
    Slider(value = sliderState2, modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        valueRange = 0f..100f,
        steps = 5,
        onValueChange = { newValue ->
            sliderState2 = newValue
        }
    )
}