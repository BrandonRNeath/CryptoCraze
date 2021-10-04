package com.nemesisprotocol.cryptocraze.presentation.buy_crypto_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nemesisprotocol.cryptocraze.domain.crypto_data.CryptoDataPriceInfo
import com.nemesisprotocol.cryptocraze.domain.payment_info.CryptoCrazeVisaCard
import com.nemesisprotocol.cryptocraze.domain.payment_info.FiatWalletCard
import com.nemesisprotocol.cryptocraze.presentation.wallet_screen.WalletViewModel
import com.nemesisprotocol.cryptocraze.presentation.wallet_screen.add_wallet.InputItem

@Composable
fun BuyCryptoScreen(cryptoData: CryptoDataPriceInfo) {
    val walletViewModel: WalletViewModel = hiltViewModel()
    val paymentCards = walletViewModel.paymentCards.collectAsState()
    val cryptoCrazeVisaCards = walletViewModel.cryptoCrazeVisaCards.collectAsState()
    val fiatWalletOptionSelected = remember { mutableStateOf(false) }
    val cryptoCrazeVisaCardOptionSelected = remember { mutableStateOf(false) }
    val canPurchase = remember { mutableStateOf(false) }
    var amountOfCrypto by remember { mutableStateOf(TextFieldValue()) }
    var selectedFiatWallet: FiatWalletCard? = null
    var selectedCryptoCrazeVisaCard: CryptoCrazeVisaCard? = null
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
                    text = "Buy ${cryptoData.symbol.uppercase()}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = Bold,
                    fontSize = 24.sp
                )
                Text(
                    text = "Enter how much ${cryptoData.symbol.uppercase()} you would like to buy",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = Bold,
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
                    fontWeight = Bold
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                        .clickable(!cryptoCrazeVisaCardOptionSelected.value) {
                            fiatWalletOptionSelected.value = !fiatWalletOptionSelected.value
                            if (!fiatWalletOptionSelected.value) selectedFiatWallet = null
                        }
                ) {
                    Row {
                        Text(
                            text = "From Fiat Wallet",
                            Modifier
                                .padding(start = 18.dp, top = 8.dp)
                                .weight(1f),
                            fontStyle = FontStyle.Italic
                        )
                        if (fiatWalletOptionSelected.value) Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                        else Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
        }
        if (fiatWalletOptionSelected.value) {
            items(paymentCards.value) {
                val isSelected = remember { mutableStateOf(false) }
                Card(
                    backgroundColor = if (isSelected.value) Color.Gray else MaterialTheme.colors.surface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                        .clickable {
                            isSelected.value = !isSelected.value
                            canPurchase.value = isSelected.value
                            selectedFiatWallet = it
                        }
                ) {
                    val cardNumberAsString = it.cardNumber.toString()
                    val lastFourDigits =
                        cardNumberAsString.substring(cardNumberAsString.length - 4)
                    Text("Card ending **** **** **** $lastFourDigits", fontSize = 18.sp)
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                    .clickable(!fiatWalletOptionSelected.value) {
                        cryptoCrazeVisaCardOptionSelected.value =
                            !cryptoCrazeVisaCardOptionSelected.value
                        if (!cryptoCrazeVisaCardOptionSelected.value) selectedCryptoCrazeVisaCard =
                            null
                    }
            ) {
                Row {
                    Text(
                        text = "From Crypto Craze Visa Card",
                        Modifier
                            .padding(start = 18.dp, top = 16.dp)
                            .weight(1f),
                        fontStyle = FontStyle.Italic
                    )
                    if (cryptoCrazeVisaCardOptionSelected.value) Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                    else Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }

        if (cryptoCrazeVisaCardOptionSelected.value) {
            items(cryptoCrazeVisaCards.value) {
                val isSelected = remember { mutableStateOf(false) }
                Card(
                    backgroundColor = if (isSelected.value) Color.Gray else MaterialTheme.colors.surface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                        .clickable {
                            isSelected.value = !isSelected.value
                            canPurchase.value = isSelected.value
                            selectedCryptoCrazeVisaCard = it
                        }
                ) {
                    Text(
                        "Crypto Craze ${it.cryptoCrazeVisaColour.name} Visa Card",
                        fontSize = 18.sp
                    )
                }
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
                    onClick = {
                        if (selectedFiatWallet != null) {

                        } else {

                        }
                    },
                    enabled = canPurchase.value && amountOfCrypto.text.isNotEmpty(),
                    modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                ) {
                    if (amountOfCrypto.text.isNotEmpty()) Text(
                        text = "Buy ${amountOfCrypto.text} ${cryptoData.symbol.uppercase()}",
                        fontSize = 24.sp
                    )
                    else Text(text = "Buy ${cryptoData.symbol.uppercase()}", fontSize = 24.sp)
                }
            }
        }
    }
}
