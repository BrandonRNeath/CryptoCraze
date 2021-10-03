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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nemesisprotocol.cryptocraze.R
import com.nemesisprotocol.cryptocraze.Screen
import com.nemesisprotocol.cryptocraze.domain.payment_info.FiatWalletCard
import com.nemesisprotocol.cryptocraze.presentation.wallet_screen.WalletViewModel

@ExperimentalAnimationApi
@Composable
fun AddPaymentScreen(navController: NavHostController, fiatWalletCard: FiatWalletCard?) {
    val existingFiatWalletCard = fiatWalletCard != null
    val walletViewModel: WalletViewModel = hiltViewModel()
    var cardNameText by remember {
        mutableStateOf(
            if (existingFiatWalletCard) TextFieldValue(
                fiatWalletCard!!.cardName
            ) else TextFieldValue()
        )
    }
    var cardNumber by remember {
        mutableStateOf(
            if (existingFiatWalletCard) TextFieldValue(
                fiatWalletCard?.cardNumber.toString()
            ) else TextFieldValue()
        )
    }
    var expiryNumber by remember {
        mutableStateOf(
            if (existingFiatWalletCard) TextFieldValue(
                fiatWalletCard?.expiryNumber.toString()
            ) else TextFieldValue()
        )
    }
    var cvvNumber by remember {
        mutableStateOf(
            if (existingFiatWalletCard) TextFieldValue(
                fiatWalletCard?.cvvNumber.toString()
            ) else TextFieldValue()
        )
    }
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
                cvvNumber
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
                            textFieldValue = cvvNumber,
                            label = stringResource(id = R.string.cvv),
                            keyboardType = KeyboardType.Number,
                            onTextChanged = { if (it.text.length <= 3) cvvNumber = it },
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp)
                        )
                    }
                }

                item {
                    Button(
                        onClick = {
                                walletViewModel.addFiatWallet(
                                FiatWalletCard(
                                    cardNumber.text.toLong(),
                                    cardNameText.text,
                                    expiryNumber.text.toInt(),
                                    cvvNumber.text.toInt()
                                )
                            )
                            navController.navigate(Screen.PaymentCardAdded.route)
                        },
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
