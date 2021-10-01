package com.nemesisprotocol.cryptocraze.presentation.wallet_screen.add_wallet

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nemesisprotocol.cryptocraze.R
import com.nemesisprotocol.cryptocraze.presentation.wallet_screen.CryptoCrazeVisaColour

@ExperimentalAnimationApi
@Composable
fun AddCryptoCrazeVisaCardScreen() {

    val cardColour = remember { mutableStateOf(CryptoCrazeVisaColour.BLACK) }

    Column(modifier = Modifier.fillMaxSize()) {
        CryptoCrazeVisaCard(cardColour)
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.crypto_craze_visa_card_black),
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clickable { cardColour.value = CryptoCrazeVisaColour.BLACK },
                )
                Image(
                    painter = painterResource(id = R.drawable.crypto_craze_visa_card_white),
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clickable { cardColour.value = CryptoCrazeVisaColour.WHITE },
                )
                Image(
                    painter = painterResource(id = R.drawable.crypto_craze_visa_card_blue),
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clickable { cardColour.value = CryptoCrazeVisaColour.BLUE },
                )
            }
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.crypto_craze_visa_card_red),
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clickable { cardColour.value = CryptoCrazeVisaColour.RED },
                )
                Image(
                    painter = painterResource(id = R.drawable.crypto_craze_visa_card_green),
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clickable { cardColour.value = CryptoCrazeVisaColour.GREEN },
                )
                Image(
                    painter = painterResource(id = R.drawable.crypto_craze_visa_card_silver),
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clickable { cardColour.value = CryptoCrazeVisaColour.SILVER },
                )
            }
        }
    }
}
