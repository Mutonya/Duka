package com.maestro.duka.ui.home.vm

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maestro.duka.data.remote.dto.ProductsResponse
import com.maestro.duka.data.remote.dto.ProductsResponseItem
import com.maestro.duka.di.DukaApplication
import com.maestro.duka.domain.usecases.HomeUseCases.HomeUseCaseImpl
import com.maestro.duka.ui.home.events.HomeUiState
import com.maestro.duka.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class HomeViewModel @Inject constructor(
    private val homeUseCaseImpl: HomeUseCaseImpl

) :ViewModel() {

    private val _fetchProductsResponse = MutableStateFlow<Resource<ProductsResponse>>(Resource.Idle)
    private val _state = MutableStateFlow(HomeUiState())
    val homeUiState:StateFlow<HomeUiState> = _fetchProductsResponse.map { resource ->
        when (resource) {
            is Resource.Loading -> HomeUiState(loading = true)
            is Resource.Success -> HomeUiState(products = resource.data)
            is Resource.Error -> HomeUiState(error = resource.message ?: "")
            else -> HomeUiState()
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, HomeUiState())

    init {
        fetchProducts()
    }





    private fun fetchProducts(){
        viewModelScope.launch {


            try {
                _fetchProductsResponse.value = Resource.Loading()
                val result = homeUseCaseImpl.invoke()
                _fetchProductsResponse.value = Resource.Success(result)
                Log.e("FetchedProducts",_fetchProductsResponse.value.toString())
            } catch (e: Exception) {
                _fetchProductsResponse.value = Resource.Error(e.message ?: "Unknown error")
                Log.e("Error",_fetchProductsResponse.value.toString())
            }
        }
    }

}