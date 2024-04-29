package com.maestro.duka.ui.home.events

import com.maestro.duka.data.local.CartItems

data class CartState (
    val cartItems:List<CartItems> = emptyList(),
    var paymentSuccess:Boolean = false,
    var paymentInitiated:Boolean = false,
    val paymentMessage:String?= "",
    val loading:Boolean = false,
    val customer: String? = "",
    val ephemeralKey: String?="",
    val paymentIntent: String?="",
    val publishableKey: String?=""
)