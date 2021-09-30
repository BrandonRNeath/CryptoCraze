package com.nemesisprotocol.cryptocraze

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.annotation.ExperimentalCoilApi
import com.nemesisprotocol.cryptocraze.presentation.BottomNavigationBar
import com.nemesisprotocol.cryptocraze.presentation.BottomSheetContent
import com.nemesisprotocol.cryptocraze.presentation.Navigation
import com.nemesisprotocol.cryptocraze.presentation.home_screen.HomeViewModel
import com.nemesisprotocol.cryptocraze.presentation.ui.theme.CryptoCrazeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalCoilApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoCrazeTheme {

                val homeViewModel: HomeViewModel = hiltViewModel()
                val pagingCryptoDataItems = homeViewModel.getAllCryptos().collectAsLazyPagingItems()

                val navController = rememberNavController()

                val userLoggedIn = remember { mutableStateOf(false) }

                val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
                    bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
                )
                val bottomSheetContent = remember { mutableStateOf(BottomSheetContent.BUY_SELL) }
                val listScrollState = rememberLazyListState()


                BottomSheetScaffold(
                    scaffoldState = bottomSheetScaffoldState,
                    sheetContent = {
                        if (bottomSheetScaffoldState.bottomSheetState.currentValue == BottomSheetValue.Collapsed) {
                            bottomSheetContent.value = BottomSheetContent.BUY_SELL
                        }
                        if (bottomSheetContent.value == BottomSheetContent.BUY_SELL) {
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Row {
                                        Button(
                                            modifier = Modifier.padding(16.dp),
                                            onClick = {
                                                bottomSheetContent.value = BottomSheetContent.BUY
                                            }) {
                                            Text("Buy")
                                        }
                                        Button(
                                            modifier = Modifier.padding(16.dp),
                                            onClick = {
                                                bottomSheetContent.value = BottomSheetContent.SELL
                                            }) {
                                            Text("Sell")
                                        }
                                    }
                                }
                            }
                        }
                        if (bottomSheetContent.value == BottomSheetContent.BUY) {
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .height(400.dp)
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .padding(top = 8.dp, start = 8.dp)
                                        .size(32.dp)
                                        .clickable {
                                            bottomSheetContent.value =
                                                BottomSheetContent.BUY_SELL
                                        },
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "ArrowBack",
                                    tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                                )
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {

                                    Text(
                                        modifier = Modifier.padding(16.dp),
                                        text = "Buy Cryptocurrency",
                                        fontSize = 24.sp,
                                        textAlign = TextAlign.Center
                                    )

                                    LazyColumn(state = listScrollState) {
                                        itemsIndexed(pagingCryptoDataItems) { _, crypto ->
                                            crypto?.let {
                                                Card(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .fillMaxHeight()
                                                        .height(64.dp)
                                                        .padding(
                                                            start = 8.dp,
                                                            end = 8.dp,
                                                            top = 16.dp
                                                        )
                                                ) {
                                                    Column(
                                                        modifier = Modifier.fillMaxSize(),
                                                        verticalArrangement = Arrangement.Center,
                                                    ) {
                                                        Text(
                                                            text = "(${it.symbol.uppercase()}) ${it.name}",
                                                            fontSize = 18.sp
                                                        )
                                                    }
                                                }
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
                        }
                        if (bottomSheetContent.value == BottomSheetContent.SELL) {
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .height(400.dp)
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .padding(top = 8.dp, start = 8.dp)
                                        .size(32.dp)
                                        .clickable {
                                            bottomSheetContent.value =
                                                BottomSheetContent.BUY_SELL
                                        },
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "ArrowBack",
                                    tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                                )
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {

                                    Text(
                                        modifier = Modifier.padding(16.dp),
                                        text = "Sell Cryptocurrency",
                                        fontSize = 24.sp,
                                        textAlign = TextAlign.Center
                                    )

                                    LazyColumn(state = listScrollState) {
                                        itemsIndexed(pagingCryptoDataItems) { _, crypto ->
                                            crypto?.let {
                                                Card(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .fillMaxHeight()
                                                        .height(64.dp)
                                                        .padding(
                                                            start = 8.dp,
                                                            end = 8.dp,
                                                            top = 16.dp
                                                        )
                                                ) {
                                                    Column(
                                                        modifier = Modifier.fillMaxSize(),
                                                        verticalArrangement = Arrangement.Center,
                                                    ) {
                                                        Text(
                                                            text = "(${it.symbol.uppercase()}) ${it.name}",
                                                            fontSize = 18.sp
                                                        )
                                                    }
                                                }
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
                        }
                    }, sheetPeekHeight = 0.dp
                ) {
                    Scaffold(
                        topBar = {
                            if (userLoggedIn.value) {
                                TopAppBar(
                                    title = {
                                    },
                                    navigationIcon = {
                                        IconButton(onClick = { }) {
                                            Icon(Icons.Rounded.Settings, "")
                                        }
                                    },
                                )
                            }
                        },
                        bottomBar = {
                            if (userLoggedIn.value) {
                                BottomNavigationBar(navController, bottomSheetScaffoldState)
                            }
                        }
                    ) {
                        Box(modifier = Modifier.padding(it)) {
                            Navigation(navController, userLoggedIn, homeViewModel)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CryptoCrazeTheme {
        Greeting("Android")
    }
}
