package com.maestro.duka.domain.usecases.HomeUseCases

import com.maestro.duka.data.remote.dto.ProductsResponse
import com.maestro.duka.utils.Resource

interface HomeUseCaseImpl {

    suspend operator fun invoke():ProductsResponse
}