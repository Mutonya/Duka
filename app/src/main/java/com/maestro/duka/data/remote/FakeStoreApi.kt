package com.maestro.duka.data.remote

import com.maestro.duka.data.remote.dto.AuthResponse
import com.maestro.duka.data.remote.dto.Login
import com.maestro.duka.data.remote.dto.PaymentResponse
import com.maestro.duka.data.remote.dto.ProductsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FakeStoreApi {
    @POST("auth/login")

    suspend fun loginUser(@Body login: Login):AuthResponse

    @GET("products")

    suspend fun fetchproducts(
    ):ProductsResponse


}