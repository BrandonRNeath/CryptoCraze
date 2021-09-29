package com.nemesisprotocol.cryptocraze

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.nemesisprotocol.cryptocraze.presentation.BottomNavigationBar
import com.nemesisprotocol.cryptocraze.presentation.Navigation
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
                val navController = rememberNavController()

                val userLoggedIn = remember { mutableStateOf(false) }

                val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
                    bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
                )
                BottomSheetScaffold(
                    scaffoldState = bottomSheetScaffoldState,
                    sheetContent = {
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
                                Row() {
                                    Button(
                                        modifier = Modifier.padding(16.dp),
                                        onClick = {  }) {
                                        Text("Buy")
                                    }
                                    Button(
                                        modifier = Modifier.padding(16.dp),
                                        onClick = {  }) {
                                        Text("Sell")
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
                            Navigation(navController, userLoggedIn)
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
