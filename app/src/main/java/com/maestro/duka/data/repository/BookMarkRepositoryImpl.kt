package com.maestro.duka.data.repository

import com.maestro.duka.data.local.ProductsDao
import com.maestro.duka.data.remote.dto.ProductsResponseItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookMarkRepositoryImpl @Inject constructor(
    private val productsDao: ProductsDao
):BookMarkRepository {
    override suspend fun upsertProduct(product: ProductsResponseItem) {
        return productsDao.upsertProducts(product)
    }

    override suspend fun deleteArticle(product: ProductsResponseItem) {
        return productsDao.deleteProduct(product)
    }

    override  fun getAllProducts(): Flow<List<ProductsResponseItem>> {
        return productsDao.getAllProducts()
    }

    override suspend fun getSingleArticle(id:Int): ProductsResponseItem {
        return productsDao.getSingleArticle(id)!!
    }
}