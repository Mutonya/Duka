package com.maestro.duka.navigation

sealed class AuthScreens(
    val route:String
) {
    object WelcomeScreen : AuthScreens("welcome")
    object AuthScreen: AuthScreens("auth")
    object LoginScreen: AuthScreens("login")
    object SignUpScreen: AuthScreens("signup")

}