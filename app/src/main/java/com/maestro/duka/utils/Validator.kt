package com.maestro.duka.utils

import java.util.regex.Pattern

object Validator {

    fun validateFirstName(fName:String): ValidationResults {
        return ValidationResults(fName.isNotEmpty() && fName.length>3)

    }
    fun validateLastName(lastname:String): ValidationResults {
        return ValidationResults(lastname.isNotEmpty() && lastname.length>3)

    }
    fun validateEmail(email:String): ValidationResults {
        val EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )
        return ValidationResults(email.isNotEmpty() && EMAIL_ADDRESS_PATTERN.matcher(email).matches())
    }
    fun validatePassword(password:String): ValidationResults {
        return ValidationResults(password.isNotEmpty() && password.length>6)
    }
    fun validatepolicy(status:Boolean): ValidationResults {
        return ValidationResults(status)
    }
}
data class ValidationResults(
    val Status:Boolean = false
)