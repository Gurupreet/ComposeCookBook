package com.guru.composecookbook.paymentcard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPaymentScreen() {
    var nameText by remember { mutableStateOf(TextFieldValue()) }
    var cardNumber by remember { mutableStateOf(TextFieldValue()) }
    var expiryNumber by remember { mutableStateOf(TextFieldValue()) }
    var cvcNumber by remember { mutableStateOf(TextFieldValue()) }
    Scaffold { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            PaymentCard(
                nameText,
                cardNumber,
                expiryNumber,
                cvcNumber
            )
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                item {
                    InputItem(
                        textFieldValue = nameText,
                        label = stringResource(id = R.string.card_holder_name),
                        onTextChanged = { nameText = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    )
                }

                item {
                    InputItem(
                        textFieldValue = cardNumber,
                        label = stringResource(id = R.string.card_holder_number),
                        keyboardType = KeyboardType.Number,
                        onTextChanged = { cardNumber = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        visualTransformation = CreditCardFilter
                    )
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        InputItem(
                            textFieldValue = expiryNumber,
                            label = stringResource(id = R.string.expiry_date),
                            keyboardType = KeyboardType.Number,
                            onTextChanged = { expiryNumber = it },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp)
                        )
                        InputItem(
                            textFieldValue = cvcNumber,
                            label = stringResource(id = R.string.cvc),
                            keyboardType = KeyboardType.Number,
                            onTextChanged = { cvcNumber = it },
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp)
                        )
                    }
                }

                item {
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.save),
                            modifier = Modifier.padding(horizontal = 30.dp, vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}
