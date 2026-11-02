package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.InternalTextApi
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TextInputs() {
  Text(
    text = "Text Inputs",
    style = MaterialTheme.typography.titleLarge,
    modifier = Modifier.padding(8.dp),
  )

  var text by remember { mutableStateOf(TextFieldValue("")) }

  // The M2 defaults the fields used before: label and placeholder onSurface at 0.6 (focused
  // label primary), unfocused indicator onSurface at 0.42, focused indicator primary, filled
  // container onSurface at 0.04. M3 uses onSurfaceVariant for those, which the theme sets to
  // full-contrast, so the colors are set explicitly to keep the M2 look.
  val filledFieldColors =
    TextFieldDefaults.colors(
      focusedContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.04f),
      unfocusedContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.04f),
      focusedLabelColor = MaterialTheme.colorScheme.primary,
      unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
      focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
      unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
      focusedIndicatorColor = MaterialTheme.colorScheme.primary,
      unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.42f),
    )
  val outlinedFieldColors =
    OutlinedTextFieldDefaults.colors(
      focusedBorderColor = MaterialTheme.colorScheme.primary,
      unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
      focusedLabelColor = MaterialTheme.colorScheme.primary,
      unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
      focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
      unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
    )

  // TODO Explore CoreTextField
  //    CoreTextField(
  //        value = text,
  //        onValueChange = { newValue -> text = newValue },
  //        modifier = Modifier.padding(8.dp).size(0.dp),
  //        cursorColor = Color.Magenta
  //    )
  TextField(
    value = text,
    onValueChange = { newValue -> text = newValue },
    modifier = Modifier.padding(8.dp).fillMaxWidth(),
    label = { Text("label") },
    placeholder = { Text("placeholder") },
    colors = filledFieldColors,
  )

  OutlinedTextField(
    value = text,
    modifier = Modifier.padding(8.dp).fillMaxWidth(),
    label = { Text(text = "Password") },
    placeholder = { Text(text = "12334444") },
    visualTransformation = PasswordVisualTransformation(),
    onValueChange = { text = it },
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    colors = outlinedFieldColors,
  )

  OutlinedTextField(
    value = text,
    leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
    modifier = Modifier.padding(8.dp).fillMaxWidth(),
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    label = { Text(text = "Email address") },
    placeholder = { Text(text = "Your email") },
    onValueChange = { text = it },
    colors = outlinedFieldColors,
  )
  OutlinedTextField(
    value = text,
    leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
    trailingIcon = { Icon(imageVector = Icons.Default.Edit, contentDescription = null) },
    modifier = Modifier.padding(8.dp).fillMaxWidth(),
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    label = { Text(text = "Email address") },
    placeholder = { Text(text = "Your email") },
    onValueChange = { text = it },
    colors = outlinedFieldColors,
  )

  var numberText by remember { mutableStateOf(TextFieldValue("")) }
  OutlinedTextField(
    value = numberText,
    modifier = Modifier.padding(8.dp).fillMaxWidth(),
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    label = { Text(text = "Phone number") },
    placeholder = { Text(text = "88888888") },
    onValueChange = { numberText = it },
    colors = outlinedFieldColors,
  )
}

@InternalTextApi
@Preview
@Composable
fun PreviewInputs() {
  TextInputs()
}
