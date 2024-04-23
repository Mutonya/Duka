package com.maestro.duka.domain.usecases.BookMark

import com.maestro.duka.data.remote.dto.ProductsResponseItem
import kotlinx.coroutines.flow.Flow

interface BookMarkUseCaseImpl {
    suspend fun upsertProduct(product: ProductsResponseItem)
    suspend fun deleteProduct(product: ProductsResponseItem)
    fun getAllProducts(): Flow<List<ProductsResponseItem>>
   suspend fun getSingleArticle(id:Int):ProductsResponseItem?
}