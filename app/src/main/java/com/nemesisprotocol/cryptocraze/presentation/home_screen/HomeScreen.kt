package com.nemesisprotocol.cryptocraze.presentation.home_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nemesisprotocol.cryptocraze.presentation.home_screen.components.FavouriteCryptoRow

data class Crypto(val cryptoName: String)


@ExperimentalComposeUiApi
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
    ) {
        Text(
            text = "Account Balance",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontStyle = Italic,
            fontSize = 18.sp
        )
        Text(
            text = "£61,740.84 GBP",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            textAlign = TextAlign.Center,
            fontWeight = Bold,
            fontSize = 24.sp
        )
        Text(
            text = "+1.25% | +£1,501.12",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            textAlign = TextAlign.Center,
            color = Color.Green,
            fontWeight = Bold,
            fontSize = 14.sp
        )
        val names = listOf(
            Crypto("Bitcoin"), Crypto("ETH"), Crypto("DogeCoin"), Crypto("ADA")
        )

        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items = names, itemContent = { item ->
                FavouriteCryptoRow(cryptoName = item.cryptoName)
            })
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Show Favourite Coins",
            style = TextStyle(textDecoration = TextDecoration.Underline),
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

    }

}

