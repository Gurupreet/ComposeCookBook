package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.green200
import com.guru.composecookbook.theme.green500
import com.guru.composecookbook.theme.green700
import com.guru.composecookbook.theme.typography

@Composable
fun Layouts() {
    ScrollableColumn {
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
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.End ", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Row(horizontalArrangement = Arrangement.End, modifier = Modifier.padding(8.dp).fillMaxWidth()) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.Center ", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.SpaceAround ", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.SpaceBetween ", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.SpaceEvenly ", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        MultipleTexts()
    }
}

@Composable
fun TypeOfColumns() {
    val columnModifier = Modifier.padding(8.dp)
        .fillMaxWidth()
        .height(150.dp)
        .background(Color.LightGray)
    Text(text = "Column", style = typography.h6, modifier = Modifier.padding(8.dp))
    Text(
        text = "Arrangement.Top", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Column(verticalArrangement = Arrangement.Top, modifier = columnModifier) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.Bottom", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Column(verticalArrangement = Arrangement.Bottom, modifier = columnModifier) {
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
    ) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.SpaceAround", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Column(verticalArrangement = Arrangement.SpaceAround, modifier = columnModifier) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.SpaceEvenly", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = columnModifier) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.SpaceBetween", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = columnModifier) {
        MultipleTexts()
    }
}

@Composable
fun TypeOfBoxs() {
    Text(text = "Box", style = typography.h6, modifier = Modifier.padding(8.dp))
    val boxModifier = Modifier.padding(8.dp)
        .background(Color.LightGray)
        .fillMaxWidth()
        .height(250.dp)
    Text(
        text = "Children with no align", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Box(modifier = boxModifier) {
        Card(
            backgroundColor = green700,
            elevation = 4.dp,
            modifier = Modifier.preferredSize(200.dp)
        ) {}
        Card(
            backgroundColor = green500,
            elevation = 4.dp,
            modifier = Modifier.preferredSize(150.dp)
        ) {}
        Card(
            backgroundColor = green200,
            elevation = 4.dp,
            modifier = Modifier.preferredSize(100.dp)
        ) {}
    }
    Text(
        text = "Children with Topstart, center & bottomEnd align", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Box(modifier = boxModifier) {
        Card(
            backgroundColor = green700, elevation = 4.dp, modifier = Modifier.preferredSize(
                200
                    .dp
            ).align(Alignment.TopStart)
        ) {}
        Card(
            backgroundColor = green500, elevation = 4.dp, modifier = Modifier.preferredSize(
                150
                    .dp
            ).align(Alignment.Center)
        ) {}
        Card(
            backgroundColor = green200, elevation = 4.dp, modifier = Modifier.preferredSize(
                100
                    .dp
            ).align(Alignment.BottomEnd)
        ) {}
    }
}

@Composable
fun ConstraintLayouts() {
    Text(text = "ConstraintLayouts", style = typography.h6, modifier = Modifier.padding(8.dp))

    ConstraintLayout(
        modifier = Modifier.background(Color.LightGray).fillMaxWidth()
            .preferredHeight(150.dp)
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