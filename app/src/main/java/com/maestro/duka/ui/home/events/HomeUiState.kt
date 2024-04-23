package com.maestro.duka.ui.home.events

import com.maestro.duka.data.remote.dto.ProductsResponseItem

data class HomeUiState (
    val products: ArrayList<ProductsResponseItem> = arrayListOf(),
    val loading:Boolean = false,
    val error:String=""
)