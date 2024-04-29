package com.maestro.duka.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maestro.duka.data.remote.dto.ProductsResponseItem
import com.maestro.duka.data.remote.dto.Rating
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class CartItems(
    @PrimaryKey val cartId:Int,
    val selectedColor:Int?=null,
    val selectedSize:String?=null,
    val quantity:String?,
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String

): Parcelable
