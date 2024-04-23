package com.maestro.duka.ui.auth.vm

import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maestro.duka.data.remote.dto.AuthResponse
import com.maestro.duka.data.remote.dto.Login
import com.maestro.duka.di.DukaApplication
import com.maestro.duka.domain.usecases.AuthUseCases.AuthUseCaseImpl
import com.maestro.duka.ui.auth.LoginUiEvents
import com.maestro.duka.utils.Resource
import com.maestro.duka.utils.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class AuthViewModel @Inject constructor(
    private val authusecase: AuthUseCaseImpl
):ViewModel() {
    var loginUiState = mutableStateOf(LoginUiState())
    var allvalidationPassed = mutableStateOf(false)
    var loginProgress = mutableStateOf(false)
    private val _loginState = MutableStateFlow<Resource<AuthResponse>>(Resource.Idle)
    val loginState:StateFlow<Resource<AuthResponse>> = _loginState




    fun onEvent(events: LoginUiEvents){
        when(events){
            is LoginUiEvents.EmailChanged -> {
                //update the state
                loginUiState.value = loginUiState.value.copy(
                    email = events.email
                )

            }
            LoginUiEvents.LoginButtonClicked -> {
              login()

            }
            is LoginUiEvents.PasswordChanged -> {
                //update the state with the value
                loginUiState.value = loginUiState.value.copy(
                    password = events.password
                )

            }
        }
       // validateLoginData()
    }

    private fun  login(){
        viewModelScope.launch {
            val email = loginUiState.value.email
            val password = loginUiState.value.password
            val emails = "mor_2314"
            val passwords = "83r5^_"

            val logindata = Login(
                username =  emails,
                password = passwords
            )

            _loginState.value = Resource.Loading()

            val result = authusecase.invoke(logindata)


            _loginState.value = result


        }

    }
    private fun validateLoginData() {
        val emailResult = Validator.validateEmail(
            email = loginUiState.value.email
        )
        val passwordResult= Validator.validatePassword(
            password = loginUiState.value.password
        )

        loginUiState.value = loginUiState.value.copy(
            emailError = emailResult.Status,
            passwordEror = passwordResult.Status
        )
        allvalidationPassed.value = emailResult.Status && passwordResult.Status

    }




}