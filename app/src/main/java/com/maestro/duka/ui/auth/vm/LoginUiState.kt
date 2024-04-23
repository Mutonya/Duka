package com.maestro.duka.ui.auth.vm

data class LoginUiState(
    var email:String = "",
    var password:String= "",
    var emailError:Boolean= false,
    var passwordEror:Boolean = false

)
