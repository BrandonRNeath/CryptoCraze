package com.nemesisprotocol.cryptocraze.presentation.crypto_transaction_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nemesisprotocol.cryptocraze.Screen
import com.nemesisprotocol.cryptocraze.domain.crypto_data.CryptoDataPriceInfo
import com.nemesisprotocol.cryptocraze.domain.payment_info.CryptoCrazeVisaCard
import com.nemesisprotocol.cryptocraze.domain.payment_info.FiatWalletCard
import com.nemesisprotocol.cryptocraze.domain.transaction_history.TransactionRecord

@Composable
fun CryptoTransactionDialog(
    cryptoTransactionDialogOpenState: MutableState<Boolean>,
    navController: NavHostController,
    cryptoData: CryptoDataPriceInfo,
    amountOfCrypto: TextFieldValue,
    selectedFiatWallet: FiatWalletCard?,
    selectedCryptoCrazeVisaCard: CryptoCrazeVisaCard?,
    transactionType: TransactionType,
    cryptoTransactionViewModel: CryptoTransactionViewModel
) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        if (cryptoTransactionDialogOpenState.value) {
            val roundedAmount =
                "%.2f".format(cryptoData.price * amountOfCrypto.text.toDouble()).toDouble()
            AlertDialog(
                modifier = Modifier.wrapContentSize(),
                onDismissRequest = {
                    cryptoTransactionDialogOpenState.value = false
                },
                title = {
                    if (transactionType == TransactionType.BUY) {
                        Text(text = "Confirm Purchase")
                    } else {
                        Text(text = "Confirm Sale")
                    }
                },
                text =
                {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Row {
                            Text(
                                text = "Amount",
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterVertically)
                                    .weight(1f)
                                    .padding(start = 8.dp)
                            )
                            Text(
                                text = "${amountOfCrypto.text} ${cryptoData.symbol.uppercase()} = £$roundedAmount",
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterVertically)
                                    .padding(end = 8.dp)
                            )
                        }
                        Row {
                            Text(
                                text = "Rate",
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterVertically)
                                    .weight(1f)
                                    .padding(start = 8.dp)
                            )
                            Text(
                                text = "1 ${cryptoData.symbol.uppercase()} = £${cryptoData.price}",
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterVertically)
                                    .padding(end = 8.dp)
                            )
                        }

                        Row {
                            Text(
                                text = "Method",
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterVertically)
                                    .weight(1f)
                                    .padding(start = 8.dp)
                            )
                            if (selectedFiatWallet != null) {
                                val cardNumberAsString = selectedFiatWallet.cardNumber.toString()
                                val lastFourDigits =
                                    cardNumberAsString.substring(cardNumberAsString.length - 4)
                                Text(
                                    text = "Card Ending $lastFourDigits",
                                    modifier = Modifier
                                        .align(alignment = Alignment.CenterVertically)
                                        .padding(end = 8.dp)
                                )
                            } else if (selectedCryptoCrazeVisaCard != null) {
                                Text(
                                    text = "CC Visa Card ${selectedCryptoCrazeVisaCard.cryptoCrazeVisaColour}",
                                    modifier = Modifier
                                        .align(alignment = Alignment.CenterVertically)
                                        .padding(end = 8.dp)
                                )
                            }
                        }

                        Row {
                            if (transactionType == TransactionType.BUY) {
                                Text(
                                    text = "Total Cost",
                                    modifier = Modifier
                                        .align(alignment = Alignment.CenterVertically)
                                        .weight(1f)
                                        .padding(start = 8.dp)
                                )
                            } else {
                                Text(
                                    text = "Total Sale",
                                    modifier = Modifier
                                        .align(alignment = Alignment.CenterVertically)
                                        .weight(1f)
                                        .padding(start = 8.dp)
                                )
                            }
                            Text(text = "£$roundedAmount")
                        }
                    }
                },
                confirmButton = {
                    Button(
                        modifier = Modifier.padding(8.dp),
                        onClick = {
                            cryptoTransactionViewModel.addTransactionRecord(
                                TransactionRecord(
                                    cryptoSymbol = cryptoData.symbol.uppercase(),
                                    cryptoAmount = amountOfCrypto.text.toDouble(),
                                    amount = "£$roundedAmount",
                                    transactionType = transactionType
                                )
                            )
                            cryptoTransactionDialogOpenState.value = false
                            navController.navigate(Screen.CryptoTransactionConfirmation.route + "/$transactionType")
                        }
                    ) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    Button(
                        modifier = Modifier.padding(8.dp),
                        onClick = {
                            cryptoTransactionDialogOpenState.value = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }, shape = RoundedCornerShape(30.dp)
                )
            }
        }
    }
    