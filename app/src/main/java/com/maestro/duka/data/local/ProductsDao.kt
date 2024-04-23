package com.maestro.duka.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maestro.duka.data.remote.dto.ProductsResponseItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)

    suspend fun upsertProducts(productsResponseItem: ProductsResponseItem)

    @Delete
    suspend fun deleteProduct(productsResponseItem: ProductsResponseItem)
    @Query("SELECT * FROM ProductsResponseItem")

    fun getAllProducts(): Flow<List<ProductsResponseItem>>

    @Query("SELECT * FROM ProductsResponseItem  WHERE id=:id")
    fun getSingleArticle(id:Int):ProductsResponseItem?

}