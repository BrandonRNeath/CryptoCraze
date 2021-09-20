package com.nemesisprotocol.cryptocraze

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.nemesisprotocol.cryptocraze.presentation.BottomNavigationBar
import com.nemesisprotocol.cryptocraze.presentation.Navigation
import com.nemesisprotocol.cryptocraze.presentation.ui.theme.CryptoCrazeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalCoilApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoCrazeTheme {
                val navController = rememberNavController()

                val userLoggedIn = remember { mutableStateOf(false) }

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
                            BottomNavigationBar(navController)
                        }
                    }
                ) {
                    Navigation(navController, userLoggedIn)
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
