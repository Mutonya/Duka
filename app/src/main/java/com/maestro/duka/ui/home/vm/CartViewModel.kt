package com.maestro.duka.ui.home.vm

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maestro.duka.data.remote.dto.PaymentResponse
import com.maestro.duka.domain.repository.PaymentRepositoryImpl
import com.maestro.duka.domain.usecases.BookMark.BookMarkUseCaseImpl
import com.maestro.duka.domain.usecases.Payments.PaymentsUseCaseImpl
import com.maestro.duka.ui.home.events.BookMarkState
import com.maestro.duka.ui.home.events.CartState
import com.maestro.duka.ui.home.tabs.cart.CartEvents
import com.maestro.duka.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val bookmarkuseCase: BookMarkUseCaseImpl,
    private val paymentsUseCaseImpl: PaymentsUseCaseImpl
):ViewModel() {


    private val _state = mutableStateOf(CartState())
    val state: State<CartState> = _state




    init {

        getCartItems()
        fetchPublishingKey()
    }

    private fun getCartItems() {
        bookmarkuseCase.getCartItems().onEach {
            _state.value = _state.value.copy(
                cartItems = it
            )
        }.launchIn(viewModelScope)
    }


//    fun onEvent(events: CartEvents){
//        when(events){
//            CartEvents.checkButtonClicked -> {
//                fetchPublishingKey()
//            }
//            CartEvents.showPayment -> {
//
//            }
//        }
//    }


    private fun fetchPublishingKey(){
        viewModelScope.launch {
            val response = paymentsUseCaseImpl.invoke()

            subscribeToFeetchingPublishing(response)


        }
    }

    private fun subscribeToFeetchingPublishing(response:Resource<PaymentResponse>) {
        Log.e("Hello",response.toString())
        when(response){
            is Resource.Error -> {

            }
            Resource.Idle -> {

            }
            is Resource.Loading -> {


            }
            is Resource.Success -> {
                Log.e("Success",response.data.toString())
            _state.value = _state.value.copy(
                paymentInitiated = true,
                customer = response.data.customer,
                publishableKey = response.data.publishableKey,
                ephemeralKey = response.data.ephemeralKey,
                paymentIntent = response.data.paymentIntent
            )
            }
        }

    }


}