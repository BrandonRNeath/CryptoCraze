package com.nemesisprotocol.cryptocraze.presentation.home_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.annotation.ExperimentalCoilApi
import com.nemesisprotocol.cryptocraze.presentation.home_screen.components.CryptoDataListItem

data class Crypto(val cryptoName: String)


@ExperimentalCoilApi
@ExperimentalComposeUiApi
@Composable
fun HomeScreen() {

    val homeViewModel: HomeViewModel = hiltViewModel()
    val listScrollState = rememberLazyListState()
    val pagingCryptoDataItems = homeViewModel.getAllCryptos().collectAsLazyPagingItems()

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

        LazyColumn(state = listScrollState) {
            itemsIndexed(pagingCryptoDataItems) { _, crypto ->
                crypto?.let {
                    CryptoDataListItem(crypto)
                }
            }
            pagingCryptoDataItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(top = 32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                            Text(text = "Loading Crypto Data...")
                        }
                    }
                    loadState.append is LoadState.Loading -> {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .padding(top = 32.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator()
                                Text(text = "Loading Crypto Data...")
                            }
                        }
                    }
                }
            }
        }
    }
}
