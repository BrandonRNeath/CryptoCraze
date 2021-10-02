package com.nemesisprotocol.cryptocraze.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.nemesisprotocol.cryptocraze.Screen
import com.nemesisprotocol.cryptocraze.presentation.home_screen.HomeScreen
import com.nemesisprotocol.cryptocraze.presentation.home_screen.HomeViewModel
import com.nemesisprotocol.cryptocraze.presentation.info_screen.InfoScreen
import com.nemesisprotocol.cryptocraze.presentation.info_screen.coin_info.CoinInfoScreen
import com.nemesisprotocol.cryptocraze.presentation.login.login_screen.LoginScreen
import com.nemesisprotocol.cryptocraze.presentation.login.signup_screen.SignUpScreen
import com.nemesisprotocol.cryptocraze.presentation.messenger.MessengerScreen
import com.nemesisprotocol.cryptocraze.presentation.splash_screen.SplashScreen
import com.nemesisprotocol.cryptocraze.presentation.wallet_screen.CryptoCrazeVisaCardAddedScreen
import com.nemesisprotocol.cryptocraze.presentation.wallet_screen.PaymentCardAddedScreen
import com.nemesisprotocol.cryptocraze.presentation.wallet_screen.WalletScreen
import com.nemesisprotocol.cryptocraze.presentation.wallet_screen.add_wallet.AddCryptoCrazeVisaCardScreen
import com.nemesisprotocol.cryptocraze.presentation.wallet_screen.add_wallet.AddPaymentScreen

@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalComposeUiApi
@Composable
fun Navigation(
    navController: NavHostController,
    userLoggedIn: MutableState<Boolean>,
    homeViewModel: HomeViewModel
) {

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
            HomeScreen(homeViewModel)
        }

        composable(Screen.Wallet.route) {
            WalletScreen(navController)
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

        composable(Screen.AddPaymentCard.route) {
            AddPaymentScreen(navController)
        }

        composable(Screen.AddCryptoCrazeVisaCard.route) {
            AddCryptoCrazeVisaCardScreen(navController)
        }

        composable(Screen.CryptoCrazeVisaCardAdded.route) {
            CryptoCrazeVisaCardAddedScreen(navController)
        }

        composable(Screen.PaymentCardAdded.route) {
            PaymentCardAddedScreen(navController)
        }

    }
}
