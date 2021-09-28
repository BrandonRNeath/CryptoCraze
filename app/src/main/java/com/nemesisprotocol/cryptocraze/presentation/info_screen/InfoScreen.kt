package com.nemesisprotocol.cryptocraze.presentation.info_screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.nemesisprotocol.cryptocraze.presentation.info_screen.coin_info_list.CoinInfoListScreen

@Composable
fun InfoScreen(navController: NavController) {
    CoinInfoListScreen(navController = navController)
}
