package com.maestro.duka.domain.repository

import com.maestro.duka.data.remote.dto.ProductsResponse
import com.maestro.duka.utils.Resource

interface HomeRepository {

    suspend fun getProducts(): ProductsResponse
}