package com.maestro.duka.data.repository

import com.maestro.duka.data.remote.dto.ProductsResponseItem
import kotlinx.coroutines.flow.Flow

interface BookMarkRepository {

    suspend fun upsertProduct(product:ProductsResponseItem)
    suspend fun deleteArticle(product:ProductsResponseItem)
     fun getAllProducts():Flow<List<ProductsResponseItem>>
    suspend fun getSingleArticle(id:Int):ProductsResponseItem
}