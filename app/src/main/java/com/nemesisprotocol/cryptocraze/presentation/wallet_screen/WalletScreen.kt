package com.nemesisprotocol.cryptocraze.presentation.wallet_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nemesisprotocol.cryptocraze.R
import com.nemesisprotocol.cryptocraze.presentation.wallet_screen.add_wallet.AddCryptoCrazeVisaCardDialog
import com.nemesisprotocol.cryptocraze.presentation.wallet_screen.add_wallet.AddPaymentCardDialog

@Composable
fun WalletScreen(navController: NavHostController) {
    val walletViewModel: WalletViewModel = hiltViewModel()
    val addPaymentCardDialog = remember { mutableStateOf(false) }
    val addCryptoCrazeVisaCardDialog = remember { mutableStateOf(false) }
    AddPaymentCardDialog(addPaymentCardDialog, navController)
    AddCryptoCrazeVisaCardDialog(addCryptoCrazeVisaCardDialog, navController)
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
            fontStyle = FontStyle.Italic,
            fontSize = 18.sp
        )
        Text(
            text = "£61,740.84 GBP",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Text(
            text = "+1.25% | +£1,501.12",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            textAlign = TextAlign.Center,
            color = Color.Green,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                .clickable {
                    addCryptoCrazeVisaCardDialog.value = true
                }
        ) {
            Row {
                Text(
                    text = "Crypto Craze Visa Card",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .weight(1f)
                        .padding(start = 8.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.visa_card_icon),
                    contentDescription = null,
                    Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .padding(end = 8.dp)
                )
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                .clickable {
                    addPaymentCardDialog.value = true
                }
        ) {
            Row {
                Text(
                    text = "Fiat Wallet",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .weight(1f)
                        .padding(start = 8.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_wallet_24),
                    contentDescription = null,
                    Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .padding(end = 8.dp)
                )
            }
        }
    }
}
