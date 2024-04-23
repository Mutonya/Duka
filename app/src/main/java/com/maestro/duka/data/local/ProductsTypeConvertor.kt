package com.maestro.duka.data.local

import android.util.Log
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.maestro.duka.data.remote.dto.Rating

@ProvidedTypeConverter
class ProductsTypeConvertor {

    @TypeConverter

    fun ratingsToString(rating:Rating):String{
        return "${rating.count},${rating.rate}"
    }

    @TypeConverter

    fun stringToRating(rating: String):Rating {

        return rating.split(",").let {
            Log.e("List",it.toString())
            try {
                Rating(it[1].toInt(),it[0].toDouble())
            }catch (e:Exception){
                Log.e("Exception",e.message.toString())
                Rating(0,0.0)
            }

        }
    }
}