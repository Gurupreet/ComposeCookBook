package com.guru.composecookbook.paymentcard

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.guru.composecookbook.theme.helpers.TextFieldDefaultsMaterial

@Composable
fun InputItem(
  textFieldValue: TextFieldValue,
  label: String,
  onTextChanged: (TextFieldValue) -> Unit,
  modifier: Modifier = Modifier,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  textStyle: TextStyle = MaterialTheme.typography.labelMedium,
  keyboardType: KeyboardType = KeyboardType.Text
) {
  OutlinedTextField(
    value = textFieldValue,
    onValueChange = { onTextChanged(it) },
    keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = ImeAction.Next),
    textStyle = textStyle,
    maxLines = 1,
    singleLine = true,
    label = {
      CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(text = label, style = MaterialTheme.typography.labelMedium)
      }
    },
    colors = TextFieldDefaultsMaterial.outlinedTextFieldColors(),
    modifier = modifier,
    visualTransformation = visualTransformation
  )
}
