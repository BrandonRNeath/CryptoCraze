package com.nemesisprotocol.cryptocraze.presentation.sell_crypto_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nemesisprotocol.cryptocraze.domain.crypto_data.CryptoDataPriceInfo
import com.nemesisprotocol.cryptocraze.presentation.wallet_screen.WalletViewModel
import com.nemesisprotocol.cryptocraze.presentation.wallet_screen.add_wallet.InputItem

@Composable
fun SellCryptoScreen(cryptoData: CryptoDataPriceInfo) {
    val walletViewModel: WalletViewModel = hiltViewModel()
    val paymentCards = walletViewModel.paymentCards.collectAsState()
    val cryptoCrazeVisaCards = walletViewModel.cryptoCrazeVisaCards.collectAsState()

    var amountOfCrypto by remember {
        mutableStateOf(TextFieldValue())
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Sell ${cryptoData.symbol.uppercase()}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Text(
                    text = "Enter how much ${cryptoData.symbol.uppercase()} you would like to sell",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    InputItem(
                        textFieldValue = amountOfCrypto,
                        label = "",
                        keyboardType = KeyboardType.Number,
                        onTextChanged = { amountOfCrypto = it },
                        modifier = Modifier
                            .padding(start = 32.dp, top = 8.dp, bottom = 8.dp, end = 16.dp)
                            .fillMaxWidth(),
                        textStyle = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Normal,
                            fontSize = 24.sp
                        )
                    )
                    if (amountOfCrypto.text.isNotEmpty()) {
                        Text(
                            text = "${amountOfCrypto.text} ${cryptoData.symbol.uppercase()} = £${cryptoData.price * amountOfCrypto.text.toDouble()}",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                        )
                    }
                }
                Text(
                    text = "Select Payment method below",
                    Modifier.padding(start = 18.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "From Fiat Wallet",
                    Modifier.padding(start = 18.dp, top = 8.dp),
                    fontStyle = FontStyle.Italic
                )
            }
        }

        items(paymentCards.value) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                    .clickable {
                    }
            ) {
                val cardNumberAsString = it.cardNumber.toString()
                val lastFourDigits =
                    cardNumberAsString.substring(cardNumberAsString.length - 4)
                Text("Card ending **** **** **** $lastFourDigits", fontSize = 18.sp)
            }
        }
        item {
            Text(
                text = "From Crypto Craze Visa Card",
                Modifier.padding(start = 18.dp, top = 16.dp),
                fontStyle = FontStyle.Italic
            )
        }

        items(cryptoCrazeVisaCards.value) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                    .clickable {
                    }
            ) {
                Text(
                    "Crypto Craze ${it.cryptoCrazeVisaColour.name} Visa Card",
                    fontSize = 18.sp
                )
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {},
                    enabled = false,
                    modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                ) {
                    Text(text = "Sell ${cryptoData.symbol.uppercase()}", fontSize = 24.sp)
                }
            }
        }
    }
}
