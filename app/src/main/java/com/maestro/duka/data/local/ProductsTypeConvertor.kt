package com.maestro.duka.data.local

import android.util.Log
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.maestro.duka.data.remote.dto.ProductsResponseItem
import com.maestro.duka.data.remote.dto.Rating

@ProvidedTypeConverter
class ProductsTypeConvertor {

    @TypeConverter

    fun ratingsToString(rating:Rating):String{
        return "${rating.count},${rating.rate}"
    }
    @TypeConverter
    fun productsToString(productsResponseItem: ProductsResponseItem):String{
        return  "${productsResponseItem.id}," +
                "${productsResponseItem.image}," +
                "${productsResponseItem.description}," +
                "${productsResponseItem.price}," +
                "${productsResponseItem.title}," +
                "${productsResponseItem.category}," +
                ratingsToString(productsResponseItem.rating)
    }

    @TypeConverter

    fun stringToRating(rating: String):Rating {

        return rating.split(",").let {
            Log.e("List",it.toString())
            try {
                Rating(count = it[0].toInt(), rate = it[1].toDouble())
            }catch (e:Exception){
                Log.e("Exception",e.message.toString())
                Rating(count = 0, rate = 0.0)
            }

        }
    }

    @TypeConverter
    fun stringToCart(cartItem:String):ProductsResponseItem{

      return cartItem.split(",").let {
          Log.e("We are Logging It ", it.toString())
          try {
              ProductsResponseItem(
                  it[0],
                  it[1],
                  it[2].toInt(),
                  it[3],
                  it[4].toDouble(),
                  stringToRating(it[5]),
                  it[6]
              )
          }catch (e:Exception){
              Log.e("ListProduct ", e.message.toString())
              ProductsResponseItem(category ="" ,
                  description = "",
                  id = 0,
                  image = "",
                  price = 0.0,
                  rating =stringToRating("0,0.0",) ,
                  title ="" )
          }

      }
    }
}