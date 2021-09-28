package com.nemesisprotocol.cryptocraze.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.nemesisprotocol.cryptocraze.Screen
import com.nemesisprotocol.cryptocraze.presentation.home_screen.HomeScreen
import com.nemesisprotocol.cryptocraze.presentation.info_screen.InfoScreen
import com.nemesisprotocol.cryptocraze.presentation.info_screen.coin_info.CoinInfoScreen
import com.nemesisprotocol.cryptocraze.presentation.login.login_screen.LoginScreen
import com.nemesisprotocol.cryptocraze.presentation.login.signup_screen.SignUpScreen
import com.nemesisprotocol.cryptocraze.presentation.messenger.MessengerScreen
import com.nemesisprotocol.cryptocraze.presentation.splash_screen.SplashScreen
import com.nemesisprotocol.cryptocraze.presentation.wallet_screen.WalletScreen

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@Composable
fun Navigation(navController: NavHostController, userLoggedIn: MutableState<Boolean>) {

    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(userLoggedIn = userLoggedIn, navController = navController)
        }

        composable(Screen.SignUp.route) {
            SignUpScreen(navController = navController)
        }

        composable(Screen.Home.route) {
            HomeScreen()
        }

        composable(Screen.Wallet.route) {
            WalletScreen()
        }

        composable(Screen.Messenger.route) {
            MessengerScreen()
        }

        composable(Screen.Info.route) {
            InfoScreen(navController = navController)
        }

        composable(Screen.CoinDetailScreen.route + "/{coinId}") {
            CoinInfoScreen()
        }

    }
}
