package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.guru.composecookbook.theme.green200
import com.guru.composecookbook.theme.green500
import com.guru.composecookbook.theme.green700
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.utils.TestTags

@Composable
fun Layouts() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        TypesOfRows()
        TypeOfColumns()
        TypeOfBoxs()
        ConstraintLayouts()
    }
}

@Composable
fun TypesOfRows() {
    Text(text = "Rows", style = typography.h6, modifier = Modifier.padding(8.dp))
    Text(
        text = "Arrangement.Start ", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
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

@Composable
fun TypeOfColumns() {
    val columnModifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .height(150.dp)
        .background(Color.LightGray)
    Text(text = "Column", style = typography.h6, modifier = Modifier.padding(8.dp))
    Text(
        text = "Arrangement.Top", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
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
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = columnModifier
            .testTag(TestTags.HOME_LAYOUTS_COLUMN_SPACE_BETWEEN)
    ) {
        MultipleTexts()
    }
}

@Composable
fun TypeOfBoxs() {
    Text(text = "Box", style = typography.h6, modifier = Modifier.padding(8.dp))
    val boxModifier = Modifier
        .padding(8.dp)
        .background(Color.LightGray)
        .fillMaxWidth()
        .height(250.dp)
    Text(
        text = "Children with no align", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Box(
        modifier = boxModifier
            .testTag(TestTags.HOME_LAYOUTS_BOX_NO_ALIGN)
    ) {
        Card(
            backgroundColor = green700,
            elevation = 4.dp,
            modifier = Modifier.size(200.dp)
        ) {}
        Card(
            backgroundColor = green500,
            elevation = 4.dp,
            modifier = Modifier.size(150.dp)
        ) {}
        Card(
            backgroundColor = green200,
            elevation = 4.dp,
            modifier = Modifier.size(100.dp)
        ) {}
    }
    Text(
        text = "Children with Topstart, center & bottomEnd align", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Box(
        modifier =
        boxModifier
            .testTag(TestTags.HOME_LAYOUTS_BOX_TOP_CENTER_AND_NO_ALIGN)
    ) {
        Card(
            backgroundColor = green700, elevation = 4.dp, modifier = Modifier
                .size(
                    200
                        .dp
                )
                .align(Alignment.TopStart)
        ) {}
        Card(
            backgroundColor = green500, elevation = 4.dp, modifier = Modifier
                .size(
                    150
                        .dp
                )
                .align(Alignment.Center)
        ) {}
        Card(
            backgroundColor = green200, elevation = 4.dp, modifier = Modifier
                .size(
                    100
                        .dp
                )
                .align(Alignment.BottomEnd)
        ) {}
    }
}

@Composable
fun ConstraintLayouts() {
    Text(text = "ConstraintLayouts", style = typography.h6, modifier = Modifier.padding(8.dp))

    ConstraintLayout(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .height(150.dp)
            .testTag(TestTags.HOME_LAYOUTS_CONSTRAINT_LAYOUT)
    ) {
        //refs creations
        val (mainButton, mainText, seconderyText, outlineButton) = createRefs()

        Button(
            onClick = { },
            modifier = Modifier.constrainAs(mainButton) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text("Main button")
        }

        Text("Main Text", Modifier.constrainAs(mainText) {
            top.linkTo(parent.top, margin = 16.dp)
            absoluteLeft.linkTo(mainButton.end, margin = 16.dp)
        })
        Text("Secondary Text", Modifier.constrainAs(seconderyText) {
            top.linkTo(mainText.bottom, margin = 16.dp)
            absoluteLeft.linkTo(mainButton.end, margin = 16.dp)
        })

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

@Composable
fun MultipleTexts() {
    Text(text = "First", modifier = Modifier.padding(8.dp))
    Text(text = "Second", modifier = Modifier.padding(8.dp))
    Text(text = "Third", modifier = Modifier.padding(8.dp))

}

@Preview
@Composable
fun PreviewLayouts() {
    Layouts()
}