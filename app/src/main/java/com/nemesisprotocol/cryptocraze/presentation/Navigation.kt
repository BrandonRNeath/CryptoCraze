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
import com.nemesisprotocol.cryptocraze.domain.crypto_data.CryptoDataPriceInfo
import com.nemesisprotocol.cryptocraze.domain.payment_info.CryptoCrazeVisaCard
import com.nemesisprotocol.cryptocraze.domain.payment_info.FiatWalletCard
import com.nemesisprotocol.cryptocraze.presentation.crypto_transaction_screen.CryptoTransactionConfirmation
import com.nemesisprotocol.cryptocraze.presentation.crypto_transaction_screen.CryptoTransactionFailed
import com.nemesisprotocol.cryptocraze.presentation.crypto_transaction_screen.CryptoTransactionScreen
import com.nemesisprotocol.cryptocraze.presentation.crypto_transaction_screen.TransactionType
import com.nemesisprotocol.cryptocraze.presentation.home_screen.HomeScreen
import com.nemesisprotocol.cryptocraze.presentation.home_screen.HomeViewModel
import com.nemesisprotocol.cryptocraze.presentation.info_screen.InfoScreen
import com.nemesisprotocol.cryptocraze.presentation.info_screen.coin_info.CoinInfoScreen
import com.nemesisprotocol.cryptocraze.presentation.login.login_screen.LoginScreen
import com.nemesisprotocol.cryptocraze.presentation.login.signup_screen.SignUpScreen
import com.nemesisprotocol.cryptocraze.presentation.messenger.MessengerScreen
import com.nemesisprotocol.cryptocraze.presentation.portfolio_screen.PortfolioScreen
import com.nemesisprotocol.cryptocraze.presentation.splash_screen.SplashScreen
import com.nemesisprotocol.cryptocraze.presentation.transaction_history_screen.TransactionHistoryScreen
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
    homeViewModel: HomeViewModel,
    currentRoute: MutableState<String>
) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            currentRoute.value = Screen.Splash.route
            SplashScreen(navController = navController)
        }

        composable(Screen.Login.route) {
            currentRoute.value = Screen.Login.route
            LoginScreen(userLoggedIn = userLoggedIn, navController = navController)
        }

        composable(Screen.SignUp.route) {
            currentRoute.value = Screen.SignUp.route
            SignUpScreen(navController = navController)
        }

        composable(Screen.Home.route) {
            currentRoute.value = Screen.Home.route
            HomeScreen(homeViewModel)
        }

        composable(Screen.Wallet.route) {
            currentRoute.value = Screen.Wallet.route
            WalletScreen(navController)
        }

        composable(Screen.Messenger.route) {
            currentRoute.value = Screen.Messenger.route
            MessengerScreen()
        }

        composable(Screen.Info.route) {
            currentRoute.value = Screen.Info.route
            InfoScreen(navController = navController)
        }

        composable(Screen.CoinDetailScreen.route + "/{coinId}") {
            currentRoute.value = Screen.CoinDetailScreen.route
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
            currentRoute.value = Screen.AddPaymentCard.route
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
            currentRoute.value = Screen.AddCryptoCrazeVisaCard.route
        }

        composable(Screen.CryptoCrazeVisaCardAdded.route) {
            currentRoute.value = Screen.CryptoCrazeVisaCardAdded.route
            CryptoCrazeVisaCardAddedScreen(navController)
        }

        composable(Screen.PaymentCardAdded.route) {
            currentRoute.value = Screen.PaymentCardAdded.route
            PaymentCardAddedScreen(navController)
        }

        composable(
            Screen.CryptoTransaction.route + "/{cryptoDataPriceInfo}/{transactionType}",
            arguments = listOf(
                navArgument("cryptoDataPriceInfo") {
                    type = NavType.StringType
                },
                navArgument("transactionType") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val transactionType = backStackEntry.arguments?.getString("transactionType")
            backStackEntry.arguments?.getString("cryptoDataPriceInfo").let { json ->
                val cryptoData = Gson().fromJson(json, CryptoDataPriceInfo::class.java)
                CryptoTransactionScreen(
                    cryptoData,
                    TransactionType.valueOf(transactionType!!),
                    navController
                )
            }
            currentRoute.value = Screen.CryptoTransaction.route
        }

        composable(
            Screen.CryptoTransactionConfirmation.route + "/{transactionType}",
            arguments = listOf(
                navArgument("transactionType") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val transactionType = backStackEntry.arguments?.getString("transactionType")
            CryptoTransactionConfirmation(
                navController = navController,
                transactionType = TransactionType.valueOf(transactionType!!)
            )
            currentRoute.value = Screen.CryptoTransactionConfirmation.route
        }

        composable(
            Screen.CryptoTransactionFailed.route + "/{transactionType}",
            arguments = listOf(
                navArgument("transactionType") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val transactionType = backStackEntry.arguments?.getString("transactionType")
            CryptoTransactionFailed(
                navController = navController,
                transactionType = TransactionType.valueOf(transactionType!!)
            )
            currentRoute.value = Screen.CryptoTransactionFailed.route
        }

        composable(Screen.Portfolio.route) {
            currentRoute.value = Screen.Portfolio.route
            PortfolioScreen()
        }

        composable(Screen.TransactionHistory.route) {
            currentRoute.value = Screen.TransactionHistory.route
            TransactionHistoryScreen()
        }
    }
}
