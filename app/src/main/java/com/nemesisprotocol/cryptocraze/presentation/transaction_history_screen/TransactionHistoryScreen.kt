package com.nemesisprotocol.cryptocraze.presentation.transaction_history_screen

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nemesisprotocol.cryptocraze.presentation.crypto_transaction_screen.CryptoTransactionViewModel
import java.util.*

@Composable
fun TransactionHistoryScreen() {
    val cryptoTransactionViewModel: CryptoTransactionViewModel = hiltViewModel()
    val transactionHistory = cryptoTransactionViewModel.transactionHistory.collectAsState()
    val columnWeight1 = .20f
    val columnWeight2 = .40f
    val pattern = "dd-MM-yyyy"
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.ENGLISH)

    LazyColumn(Modifier.fillMaxSize()) {
        item {
            Row(Modifier.background(Color.Gray)) {
                TableCell(text = "Symbol", weight = columnWeight1)
                TableCell(text = "Crypto Amount", weight = columnWeight1)
                TableCell(text = "Amount", weight = columnWeight2)
                TableCell(text = "Date", weight = columnWeight2)
                TableCell(text = "Type", weight = columnWeight1)
            }
        }
        items(transactionHistory.value) {
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = it.cryptoSymbol, weight = columnWeight1)
                TableCell(text = it.cryptoAmount.toString(), weight = columnWeight1)
                TableCell(text = it.amount, weight = columnWeight2)
                TableCell(text = simpleDateFormat.format(it.timestamp), weight = columnWeight2)
                TableCell(text = it.transactionType.toString(), weight = columnWeight1)
            }
        }
    }
}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .wrapContentWidth()
    )
}
