package com.maestro.duka.domain.usecases.BookMark

import android.util.Log
import com.maestro.duka.data.local.CartItems
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

    override  fun getSingleArticle(id: Int): Flow<ProductsResponseItem>? {
        return bookMarkRepository.getSingleArticle(id)
    }

    override suspend fun addToCart(cartItems: CartItems) {
        return bookMarkRepository.addToCart(cartItems)
    }

//    override suspend fun clearCart(id: CartItems) {
//       return bookMarkRepository.clearCart(id)
//    }

    override fun getCartItems(): Flow<List<CartItems>> {
        return bookMarkRepository.getCartItems()
    }

    override suspend fun getSingleCartItem(id: Int): CartItems {
        return bookMarkRepository.getSingleCartItem(id)
    }
}