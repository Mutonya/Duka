package com.maestro.duka.ui.home.details

import com.maestro.duka.data.remote.dto.ProductsResponseItem
import com.maestro.duka.ui.auth.LoginUiEvents

sealed class DetailsEvent {

    data class BookMarkClicked(val productsResponseItem: ProductsResponseItem):DetailsEvent()
    data class ShareClicked(val productsResponseItem: ProductsResponseItem):DetailsEvent()
    object BackButtonClicked:DetailsEvent()


}