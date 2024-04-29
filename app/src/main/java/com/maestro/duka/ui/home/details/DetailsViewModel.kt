package com.maestro.duka.ui.home.details

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maestro.duka.di.DukaApplication
import com.maestro.duka.domain.usecases.BookMark.BookMarkUseCaseImpl
import com.maestro.duka.ui.home.events.BookMarkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class DetailsViewModel @Inject constructor(
    private val usecase: BookMarkUseCaseImpl
):ViewModel() {

    var sideEffect by mutableStateOf<String?>(null)
        private set
    private val _state = mutableStateOf(BookMarkState())
    val state: State<BookMarkState> = _state



    fun onEvent(event: DetailsEvent){
        CoroutineScope(Dispatchers.IO).launch {
            when(event){
                DetailsEvent.BackButtonClicked -> {

                }
                is DetailsEvent.BookMarkClicked -> {


                   usecase.getSingleArticle(event.productsResponseItem.id)?.onEach {
                        Log.e("BK",_state.value.toString())
                        _state.value = _state.value.copy(
                            productResponseItem = it
                        )

                    }?.launchIn(viewModelScope)

                    if (state.value.productResponseItem ==null){
                            usecase.upsertProduct(event.productsResponseItem)
                        }else{
                            usecase.deleteProduct(event.productsResponseItem)
                        }

                }
                is DetailsEvent.ShareClicked -> {

                }

                is DetailsEvent.AddToCart -> {
                    viewModelScope.launch {
                        Toast.makeText(DukaApplication.context,"SHow",Toast.LENGTH_LONG).show()
                        usecase.addToCart(event.cartItems)
                    }
                }
            }
        }



    }


}