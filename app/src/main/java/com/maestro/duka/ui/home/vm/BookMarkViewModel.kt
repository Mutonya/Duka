package com.maestro.duka.ui.home.vm

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maestro.duka.data.remote.dto.ProductsResponseItem
import com.maestro.duka.di.DukaApplication
import com.maestro.duka.domain.usecases.BookMark.BookMarkUseCaseImpl
import com.maestro.duka.ui.home.events.BookMarkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val bookmarkuseCase:BookMarkUseCaseImpl
):ViewModel() {

    private val _state = mutableStateOf(BookMarkState())
    val state:State<BookMarkState> = _state

    init {
        getArticles()
    }
    private fun getArticles(){

            bookmarkuseCase.getAllProducts().onEach {
                Log.e("BK",_state.value.toString())
                _state.value = _state.value.copy(
                    products = it
                )
            }.launchIn(viewModelScope)

    }

    fun bookmarkProduct(productsResponseItem: ProductsResponseItem){
        viewModelScope.launch {

            bookmarkuseCase.upsertProduct(productsResponseItem)
            Toast.makeText(DukaApplication.context,"Success",Toast.LENGTH_LONG).show()
        }
    }
}