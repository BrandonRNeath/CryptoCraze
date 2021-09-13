package com.nemesisprotocol.cryptocraze.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nemesisprotocol.cryptocraze.Screen
import com.nemesisprotocol.cryptocraze.presentation.home_screen.HomeScreen
import com.nemesisprotocol.cryptocraze.presentation.login.login_screen.LoginScreen
import com.nemesisprotocol.cryptocraze.presentation.login.signup_screen.SignUpScreen
import com.nemesisprotocol.cryptocraze.presentation.splash_screen.SplashScreen


@ExperimentalComposeUiApi
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }

        composable(Screen.SignUp.route) {
            SignUpScreen(navController = navController)
        }

        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }

    }

}