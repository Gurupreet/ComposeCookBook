package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.guru.composecookbook.theme.components.Material3Card
import com.guru.composecookbook.theme.green200
import com.guru.composecookbook.theme.green500
import com.guru.composecookbook.theme.green700
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.utils.TestTags


/**
 * Composable function demonstrating different types of layouts including Rows, Columns, Boxes,
 * and ConstraintLayout.
 */
@Composable
fun Layouts() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        TypesOfRows()
        TypeOfColumns()
        TypeOfBoxs()
        ConstraintLayouts()
    }
}

/**
 * Composable function demonstrating different horizontal arrangements in Rows.
 */
@Composable
fun TypesOfRows() {
    Text(text = "Rows", style = typography.h6, modifier = Modifier.padding(8.dp))
    Text(
        text = "Arrangement.Start ", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    // Row with Arrangement.Start
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .testTag(TestTags.HOME_LAYOUTS_ROW_START)
    ) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.End ", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    // Row with Arrangement.End
    Row(
        horizontalArrangement = Arrangement.End, modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .testTag(TestTags.HOME_LAYOUTS_ROW_END)
    ) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.Center ", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    // Row with Arrangement.Center
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .testTag(TestTags.HOME_LAYOUTS_ROW_CENTER)
    ) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.SpaceAround ", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    // Row with Arrangement.SpaceAround
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .testTag(TestTags.HOME_LAYOUTS_ROW_SPACE_AROUND)
    ) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.SpaceBetween ", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    // Row with Arrangement.SpaceBetween
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .testTag(TestTags.HOME_LAYOUTS_ROW_SPACE_BETWEEN)
    ) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.SpaceEvenly ", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    // Row with Arrangement.SpaceEvenly
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .testTag(TestTags.HOME_LAYOUTS_ROW_SPACE_EVENLY)
    ) {
        MultipleTexts()
    }
}

/**
 * Composable function demonstrating different vertical arrangements in Columns.
 */
@Composable
fun TypeOfColumns() {
    val columnModifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .height(150.dp)
        .background(MaterialTheme.colorScheme.surfaceVariant)
    Text(text = "Column", style = typography.h6, modifier = Modifier.padding(8.dp))

    Text(
        text = "Arrangement.Top", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    // Column with Arrangement.Top
    Column(
        verticalArrangement = Arrangement.Top, modifier = columnModifier
            .testTag(TestTags.HOME_LAYOUTS_COLUMN_TOP)
    ) {
        MultipleTexts()
    }


    Text(
        text = "Arrangement.Bottom", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    // Column with Arrangement.Bottom
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = columnModifier
            .testTag(TestTags.HOME_LAYOUTS_COLUMN_BOTTOM)

    ) {
        MultipleTexts()
    }


    Text(
        text = "Arrangement.Center + Alignment.CenterHorizontally", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    // Column with Arrangement.Center + Alignment.CenterHorizontally
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = columnModifier
            .testTag(TestTags.HOME_LAYOUTS_COLUMN_CENTER)
    ) {
        MultipleTexts()
    }


    Text(
        text = "Arrangement.SpaceAround", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    // Column with Arrangement.SpaceAround
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = columnModifier
            .testTag(TestTags.HOME_LAYOUTS_COLUMN_SPACE_AROUND)
    ) {
        MultipleTexts()
    }


    Text(
        text = "Arrangement.SpaceEvenly", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    // Column with Arrangement.SpaceEvenly
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = columnModifier
            .testTag(TestTags.HOME_LAYOUTS_COLUMN_SPACE_EVENLY)
    ) {
        MultipleTexts()
    }


    Text(
        text = "Arrangement.SpaceBetween", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    // Column with Arrangement.SpaceBetween
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = columnModifier
            .testTag(TestTags.HOME_LAYOUTS_COLUMN_SPACE_BETWEEN)
    ) {
        MultipleTexts()
    }
}


/**
 * Composable function demonstrating usage of Box layout with different alignments.
 */
@Composable
fun TypeOfBoxs() {
    Text(text = "Box", style = typography.h6, modifier = Modifier.padding(8.dp))
    val boxModifier = Modifier
        .padding(8.dp)
        .background(MaterialTheme.colorScheme.surfaceVariant)
        .fillMaxWidth()
        .height(250.dp)

    Text(
        text = "Children with no align", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )

    // Box with children having no alignment specified
    Box(
        modifier = boxModifier
            .testTag(TestTags.HOME_LAYOUTS_BOX_NO_ALIGN)
    ) {
        // Nested Material3Card elements inside the Box
        Material3Card(
            backgroundColor = green700,
            elevation = 4.dp,
            modifier = Modifier.size(200.dp)
        ) {}
        Material3Card(
            backgroundColor = green500,
            elevation = 4.dp,
            modifier = Modifier.size(150.dp)
        ) {}
        Material3Card(
            backgroundColor = green200,
            elevation = 4.dp,
            modifier = Modifier.size(100.dp)
        ) {}
    }


    Text(
        text = "Children with Topstart, center & bottomEnd align", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    // Box with children aligned to TopStart, Center, and BottomEnd respectively
    Box(
        modifier =
        boxModifier
            .testTag(TestTags.HOME_LAYOUTS_BOX_TOP_CENTER_AND_NO_ALIGN)
    ) {
        Material3Card(
            backgroundColor = green700, elevation = 4.dp, modifier = Modifier
                .size(200.dp)
                .align(Alignment.TopStart)
        ) {}
        Material3Card(
            backgroundColor = green500, elevation = 4.dp, modifier = Modifier
                .size(150.dp)
                .align(Alignment.Center)
        ) {}
        Material3Card(
            backgroundColor = green200, elevation = 4.dp, modifier = Modifier
                .size(100.dp)
                .align(Alignment.BottomEnd)
        ) {}
    }
}


/**
 * Composable function demonstrating usage of ConstraintLayout.
 */
@Composable
fun ConstraintLayouts() {
    Text(text = "ConstraintLayouts", style = typography.h6, modifier = Modifier.padding(8.dp))

    // ConstraintLayout with multiple elements constrained to each other
    ConstraintLayout(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .fillMaxWidth()
            .height(150.dp)
            .testTag(TestTags.HOME_LAYOUTS_CONSTRAINT_LAYOUT)
    ) {
        // References for ConstraintLayout elements
        val (mainButton, mainText, seconderyText, outlineButton) = createRefs()

        // Main button constrained to parent top
        Button(
            onClick = { },
            modifier = Modifier.constrainAs(mainButton) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text("Main button")
        }

        // Main text constrained below main button and to the start of main button
        Text("Main Text", Modifier.constrainAs(mainText) {
            top.linkTo(parent.top, margin = 16.dp)
            absoluteLeft.linkTo(mainButton.end, margin = 16.dp)
        })

        // Secondary text constrained below main text and to the start of main button
        Text("Secondary Text", Modifier.constrainAs(seconderyText) {
            top.linkTo(mainText.bottom, margin = 16.dp)
            absoluteLeft.linkTo(mainButton.end, margin = 16.dp)
        })

        // Outline button constrained below secondary text and to the start of secondary text
        OutlinedButton(
            onClick = { /* Do something */ },
            modifier = Modifier.constrainAs(outlineButton) {
                top.linkTo(seconderyText.bottom, margin = 16.dp)
                start.linkTo(seconderyText.end, margin = 16.dp)
            }
        ) {
            Text("Outline Button")
        }
    }
}


/**
 * Composable function used for showing sample data.
 */
@Composable
fun MultipleTexts() {
    Text(text = "First", modifier = Modifier.padding(8.dp))
    Text(text = "Second", modifier = Modifier.padding(8.dp))
    Text(text = "Third", modifier = Modifier.padding(8.dp))
}


/**
 * Preview function for the Layouts content.
 */
@Preview
@Composable
fun PreviewLayouts() {
    Layouts()
}