package com.maestro.duka.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.maestro.duka.ui.auth.AuthScreen
import com.maestro.duka.ui.auth.LoginScreen
import com.maestro.duka.ui.auth.SignUpScreen
import com.maestro.duka.ui.onboarding.WelcomeScreen

@Composable

fun AuthenticationNavigation(
    navHostController: NavHostController,
    startDestination:String
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {

        composable(route = AuthScreens.WelcomeScreen.route) {
            WelcomeScreen(navHostController)
        }
        composable(route = AuthScreens.LoginScreen.route) {
            LoginScreen()
        }
        composable(route = AuthScreens.SignUpScreen.route) {
            SignUpScreen()
        }
        composable(route = AuthScreens.AuthScreen.route) {
            AuthScreen(navHostController)
        }



    }

}