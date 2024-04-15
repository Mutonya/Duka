package com.maestro.duka.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maestro.duka.ui.auth.AuthScreen
import com.maestro.duka.ui.auth.LoginScreen
import com.maestro.duka.ui.auth.SignUpScreen
import com.maestro.duka.ui.auth.vm.AuthViewModel
import com.maestro.duka.ui.onboarding.OnBoardingviewModel
import com.maestro.duka.ui.onboarding.WelcomeScreen

@Composable

fun AuthenticationNavigation(

    startDestination:String
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(route = AuthScreens.WelcomeScreen.route) {
            val viewModel :OnBoardingviewModel = hiltViewModel()
            WelcomeScreen(navController,event = viewModel::onEvent)
        }
        composable(route = AuthScreens.AuthScreen.route) {
            AuthScreen(navController)
        }
        composable(route = AuthScreens.LoginScreen.route) {
           val authViewModel: AuthViewModel = hiltViewModel()
            LoginScreen(authViewModel)
        }
        composable(route = AuthScreens.SignUpScreen.route) {
            SignUpScreen()
        }




    }

}