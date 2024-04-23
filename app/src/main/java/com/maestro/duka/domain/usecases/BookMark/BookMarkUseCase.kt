package com.maestro.duka.domain.usecases.BookMark

import android.util.Log
import com.maestro.duka.data.remote.dto.ProductsResponseItem
import com.maestro.duka.data.repository.BookMarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookMarkUseCase @Inject constructor(
   private val bookMarkRepository: BookMarkRepository
):BookMarkUseCaseImpl {
    override suspend fun upsertProduct(product: ProductsResponseItem) {
        return bookMarkRepository.upsertProduct(product)
    }

    override suspend fun deleteProduct(product: ProductsResponseItem) {
        return bookMarkRepository.deleteArticle(product)
    }

    override  fun getAllProducts(): Flow<List<ProductsResponseItem>> {
        Log.e("ALL",bookMarkRepository.getAllProducts().toString())
       return bookMarkRepository.getAllProducts()
    }

    override suspend fun getSingleArticle(id: Int): ProductsResponseItem {
        return bookMarkRepository.getSingleArticle(id)
    }
}