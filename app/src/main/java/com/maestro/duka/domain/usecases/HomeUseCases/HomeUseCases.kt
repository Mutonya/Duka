package com.maestro.duka.domain.usecases.HomeUseCases

import com.maestro.duka.data.remote.dto.ProductsResponse
import com.maestro.duka.domain.repository.HomeRepository
import com.maestro.duka.utils.Resource
import javax.inject.Inject

class HomeUseCases @Inject constructor (private val repo:HomeRepository):HomeUseCaseImpl{
    override suspend fun invoke(): ProductsResponse {

        return repo.getProducts()
    }
}