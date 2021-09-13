package com.nemesisprotocol.cryptocraze

sealed class Screen(val route: String) {
    object Splash: Screen("splash_screen")
    object Login : Screen("login_screen")
    object SignUp : Screen("sign_up_screen")
    object Home : Screen("home_screen")
}
