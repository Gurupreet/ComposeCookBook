package com.guru.composecookbook.ui.demoui.gmail.create

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CreateMessageBody(navController: NavController) {

    Column {

        Row {

            Text(
                text = "From",
                modifier = Modifier.padding(16.dp).align(Alignment.CenterVertically)
            )
            Text(
                text = "subash@gmail.com",
                modifier = Modifier.weight(1f)
                    .padding(vertical = 16.dp)
                    .align(Alignment.CenterVertically)
            )
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowDown,
                modifier = Modifier.align(Alignment.CenterVertically).padding(16.dp)
            )
        }

        Divider(color = Color.LightGray, thickness = 0.5.dp)

        Row {

            Text(
                text = "To",
                modifier = Modifier.padding(16.dp).align(Alignment.CenterVertically)
            )

            BasicTextField(
                value = TextFieldValue("Subash"),
                onValueChange = { },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                onImeActionPerformed = { ImeAction.Done },
                modifier = Modifier.weight(1f)
                    .padding(vertical = 16.dp)
                    .align(Alignment.CenterVertically)
            )
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowDown,
                modifier = Modifier.align(Alignment.CenterVertically).padding(16.dp)
            )
        }

        Divider(color = Color.LightGray, thickness = 0.5.dp)

        BasicTextField(
            value = TextFieldValue("Subject"),
            onValueChange = { },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onImeActionPerformed = { ImeAction.Done },
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 16.dp)
        )

        Divider(color = Color.LightGray, thickness = 0.5.dp)


        BasicTextField(
            value = TextFieldValue("Compose email"),
            onValueChange = { },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onImeActionPerformed = { ImeAction.Done },
            modifier = Modifier.fillMaxWidth().weight(1f)
                .padding(vertical = 16.dp, horizontal = 16.dp)
        )

    }
}