package com.maestro.duka.data.remote

import com.maestro.duka.data.remote.dto.PaymentResponse
import retrofit2.http.GET

interface StripeApi {
    @GET("index.php")
    suspend fun getPaymentInfofromApi(
    ): PaymentResponse
}