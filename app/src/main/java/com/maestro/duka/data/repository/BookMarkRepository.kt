package com.maestro.duka.data.repository

import com.maestro.duka.data.local.CartItems
import com.maestro.duka.data.remote.dto.ProductsResponseItem
import kotlinx.coroutines.flow.Flow

interface BookMarkRepository {

    suspend fun upsertProduct(product:ProductsResponseItem)
    suspend fun deleteArticle(product:ProductsResponseItem)
     fun getAllProducts():Flow<List<ProductsResponseItem>>
     fun getSingleArticle(id:Int): Flow<ProductsResponseItem>?


    suspend fun addToCart(cartItems: CartItems)

//    suspend fun clearCart(id: CartItems)

    fun getCartItems():Flow<List<CartItems>>


   suspend fun getSingleCartItem(id:Int):CartItems


}