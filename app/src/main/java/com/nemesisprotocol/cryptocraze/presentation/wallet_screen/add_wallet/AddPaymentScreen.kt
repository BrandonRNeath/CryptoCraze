package com.nemesisprotocol.cryptocraze.presentation.wallet_screen.add_wallet

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nemesisprotocol.cryptocraze.R

@ExperimentalAnimationApi
@Composable
fun AddPaymentScreen() {
    var cardNameText by remember { mutableStateOf(TextFieldValue()) }
    var cardNumber by remember { mutableStateOf(TextFieldValue()) }
    var expiryNumber by remember { mutableStateOf(TextFieldValue()) }
    var cvcNumber by remember { mutableStateOf(TextFieldValue()) }
    Scaffold {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Fiat Wallet",
                    fontSize = 26.sp,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 0.dp
                    )
                )
            }
            PaymentCard(
                cardNameText,
                cardNumber,
                expiryNumber,
                cvcNumber
            )
            LazyColumn(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 16.dp)
                    .fillMaxWidth()
            ) {
                item {
                    InputItem(
                        textFieldValue = cardNameText,
                        label = stringResource(id = R.string.card_holder_name),
                        onTextChanged = { cardNameText = it },
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
                            onTextChanged = { if (it.text.length <= 4) expiryNumber = it },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp)
                        )
                        InputItem(
                            textFieldValue = cvcNumber,
                            label = stringResource(id = R.string.cvc),
                            keyboardType = KeyboardType.Number,
                            onTextChanged = { if (it.text.length <= 3) cvcNumber = it },
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
                            color = MaterialTheme.colors.onPrimary,
                            modifier = Modifier.padding(horizontal = 30.dp, vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}
