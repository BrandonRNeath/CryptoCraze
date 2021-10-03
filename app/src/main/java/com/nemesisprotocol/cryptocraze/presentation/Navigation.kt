package com.nemesisprotocol.cryptocraze.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.gson.Gson
import com.nemesisprotocol.cryptocraze.Screen
import com.nemesisprotocol.cryptocraze.domain.payment_info.CryptoCrazeVisaCard
import com.nemesisprotocol.cryptocraze.domain.payment_info.FiatWalletCard
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

        composable(
            Screen.AddPaymentCard.route + "/{savedFiatWalletCard}",
            arguments = listOf(
                navArgument("savedFiatWalletCard") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("savedFiatWalletCard").let { json ->
                val fiatWalletCard = Gson().fromJson(json, FiatWalletCard::class.java)
                AddPaymentScreen(navController, fiatWalletCard)
            }
        }

        composable(
            Screen.AddCryptoCrazeVisaCard.route + "/{savedCryptoCrazeVisaCard}",
            arguments = listOf(
                navArgument("savedCryptoCrazeVisaCard") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("savedCryptoCrazeVisaCard").let { json ->
                val cryptoCrazeVisaCard = Gson().fromJson(json, CryptoCrazeVisaCard::class.java)
                AddCryptoCrazeVisaCardScreen(navController, cryptoCrazeVisaCard)
            }
        }

        composable(Screen.CryptoCrazeVisaCardAdded.route) {
            CryptoCrazeVisaCardAddedScreen(navController)
        }

        composable(Screen.PaymentCardAdded.route) {
            PaymentCardAddedScreen(navController)
        }
    }
}
