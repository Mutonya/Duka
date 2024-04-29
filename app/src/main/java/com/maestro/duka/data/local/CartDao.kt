package com.maestro.duka.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(cartItem: CartItems)

//    @Delete
//    suspend fun clearCart(id: CartItems)

    @Query("SELECT * FROM CartItems")

    fun getCartItems(): Flow<List<CartItems>>

    //    @Query("SELECT * FROM CartItems  WHERE cartId = cartId")
    @Query("SELECT * FROM CartItems  WHERE cartId=:cartId")
    fun getSingleCartItem(cartId:Int):CartItems?

}