package com.maestro.duka.data.repository

import com.maestro.duka.data.remote.FakeStoreApi
import com.maestro.duka.data.remote.dto.ProductsResponse
import com.maestro.duka.data.remote.dto.ProductsResponseItem
import com.maestro.duka.domain.repository.HomeRepository
import com.maestro.duka.utils.Resource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val fakeStoreApi: FakeStoreApi
):HomeRepository {
    override suspend fun getProducts(): ProductsResponse {
        val response = fakeStoreApi.fetchproducts()

        return response
    }
}