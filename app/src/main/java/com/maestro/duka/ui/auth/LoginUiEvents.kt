package com.maestro.duka.ui.auth

sealed class LoginUiEvents {

    data class EmailChanged(val email: String) : LoginUiEvents()

    data class PasswordChanged(val password:String):LoginUiEvents()

     object LoginButtonClicked:LoginUiEvents()

}