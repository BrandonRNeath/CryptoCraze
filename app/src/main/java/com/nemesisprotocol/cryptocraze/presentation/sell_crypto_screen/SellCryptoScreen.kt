package com.nemesisprotocol.cryptocraze.presentation.sell_crypto_screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.nemesisprotocol.cryptocraze.domain.crypto_data.CryptoDataPriceInfo

@Composable
fun SellCryptoScreen(cryptoData: CryptoDataPriceInfo) {
    Text(text = cryptoData.toString())
}
