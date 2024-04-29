package com.maestro.duka.data.remote.dto

data class PaymentResponse(
    val customer: String,
    val ephemeralKey: String,
    val paymentIntent: String,
    val publishableKey: String
)
