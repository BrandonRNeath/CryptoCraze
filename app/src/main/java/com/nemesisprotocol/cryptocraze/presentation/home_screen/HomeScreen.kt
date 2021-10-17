package com.nemesisprotocol.cryptocraze.presentation.home_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
import com.nemesisprotocol.cryptocraze.presentation.PortfolioViewModel
import com.nemesisprotocol.cryptocraze.presentation.home_screen.components.CryptoDataListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class Crypto(val cryptoName: String)

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    val listScrollState = rememberLazyListState()
    val pagingCryptoDataItems = homeViewModel.getAllCryptos().collectAsLazyPagingItems()
    val portfolioViewModel: PortfolioViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()
    val portfolio = portfolioViewModel.portfolio.collectAsState()
    val portfolioCurrentValue = remember { mutableStateOf(0.0) }

    LaunchedEffect(Dispatchers.IO) {
        var currentValue = 0.0
        for (portfolioValue in portfolio.value) {
            val cryptoData = homeViewModel.getCryptoBySymbol(portfolioValue.cryptoSymbol.lowercase())
            currentValue += portfolioValue.cryptoAmount * cryptoData[0].price
        }
        portfolioCurrentValue.value += currentValue
    }

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
            text = if (portfolio.value.isEmpty()) "£0.00 GBP" else "£${portfolioCurrentValue.value} GBP",
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
                    val isFav =
                        remember { mutableStateOf(false) }
                    coroutineScope.launch {
                        isFav.value = homeViewModel.checkFavExists(crypto.name)
                    }

                    CryptoDataListItem(crypto, isFav, viewModel = homeViewModel)
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
