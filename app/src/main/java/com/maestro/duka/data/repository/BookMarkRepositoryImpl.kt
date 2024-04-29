package com.maestro.duka.data.repository

import com.maestro.duka.data.local.CartDao
import com.maestro.duka.data.local.CartItems
import com.maestro.duka.data.local.ProductsDao
import com.maestro.duka.data.remote.dto.ProductsResponseItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookMarkRepositoryImpl @Inject constructor(
    private val productsDao: ProductsDao,
    private val cartDao: CartDao
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

    override  fun getSingleArticle(id:Int): Flow<ProductsResponseItem>? {
        return productsDao.getSingleArticle(id)
    }

    override suspend fun addToCart(cartItems: CartItems) {
        return cartDao.addToCart(cartItems)
    }

//    override suspend fun clearCart(id: CartItems) {
//       return cartDao.clearCart(id)
//    }

    override fun getCartItems(): Flow<List<CartItems>> {
        return cartDao.getCartItems()
    }

    override suspend fun getSingleCartItem(id: Int): CartItems {
        return cartDao.getSingleCartItem(id)!!
    }
}