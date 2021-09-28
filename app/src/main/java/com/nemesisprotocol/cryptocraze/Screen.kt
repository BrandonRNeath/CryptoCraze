package com.nemesisprotocol.cryptocraze

sealed class Screen(val route: String, val icon: Int?, val title: String) {
    object Splash : Screen("splash_screen", null, "Splash")
    object Login : Screen("login_screen", null, "Login")
    object SignUp : Screen("sign_up_screen", null, "Signup")
    object Home : Screen("home_screen", R.drawable.ic_home_24, "Home")
    object Settings : Screen("settings_screen", null, "Settings")
    object Wallet : Screen("wallet_screen", R.drawable.ic_wallet_24, "Wallet")
    object Info : Screen("info_screen", R.drawable.ic_info_24, "Info")
    object CoinDetailScreen: Screen("coin_info_screen", null, "CoinInfo")
    object Messenger : Screen("message_screen", R.drawable.ic_messenger_24, "Messenger")
}
