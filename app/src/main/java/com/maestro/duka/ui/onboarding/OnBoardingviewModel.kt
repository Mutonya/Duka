package com.maestro.duka.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maestro.duka.domain.usecases.AuthUseCases.LocalUserManagerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class OnBoardingviewModel @Inject constructor(
    private val localUserManagerUseCases: LocalUserManagerUseCases
):ViewModel() {


    fun onEvent(event: OnBoardingEvent){
        when(event){
            OnBoardingEvent.SaveAppEntry -> {
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry() = viewModelScope.launch {
        localUserManagerUseCases.saveAppEntry()
    }
}