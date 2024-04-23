package com.maestro.duka.ui.home.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maestro.duka.domain.usecases.BookMark.BookMarkUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class DetailsViewModel @Inject constructor(
    private val usecase: BookMarkUseCaseImpl
):ViewModel() {

    var sideEffect by mutableStateOf<String?>(null)
        private set


    fun onEvent(event: DetailsEvent){

        when(event){
            DetailsEvent.BackButtonClicked -> {

            }
            is DetailsEvent.BookMarkClicked -> {
                viewModelScope.launch {
                    val product = usecase.getSingleArticle(event.productsResponseItem.id)
                    if (product == null){
                        usecase.upsertProduct(event.productsResponseItem)
                    }else{
                        usecase.deleteProduct(event.productsResponseItem)
                    }
                }
            }
            is DetailsEvent.ShareClicked -> {

            }
        }

    }
}