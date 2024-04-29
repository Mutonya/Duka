package com.maestro.duka.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.maestro.duka.data.remote.dto.ProductsResponseItem

@Database(entities = [ProductsResponseItem::class,CartItems::class], version = 3)
@TypeConverters(ProductsTypeConvertor::class)

abstract class DukaDatabase:RoomDatabase() {

    abstract val productsDao:ProductsDao
    abstract val cartDao:CartDao
}