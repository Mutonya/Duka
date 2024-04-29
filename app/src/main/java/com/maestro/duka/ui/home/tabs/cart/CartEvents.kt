package com.maestro.duka.ui.home.tabs.cart

import com.maestro.duka.ui.auth.LoginUiEvents

sealed class CartEvents {
    data object checkButtonClicked: CartEvents()
    data object showPayment:CartEvents()
}