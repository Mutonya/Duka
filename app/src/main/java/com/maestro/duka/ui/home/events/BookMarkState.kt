package com.maestro.duka.ui.home.events

import com.maestro.duka.data.local.CartItems
import com.maestro.duka.data.remote.dto.ProductsResponseItem

data class BookMarkState(
    val products:List<ProductsResponseItem> = emptyList(),
    val productResponseItem: ProductsResponseItem? =null,
    val cartItems: CartItems? =null


)
