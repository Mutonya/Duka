package com.maestro.duka.ui.home.events

import com.maestro.duka.data.remote.dto.ProductsResponseItem

sealed class HomeUiEvents {

    data class CardItemClicked(val productItem: ProductsResponseItem) : HomeUiEvents()
}