package com.maestro.duka.ui.home.tabs.bookmark

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.maestro.duka.ui.core.ProductsCard
import com.maestro.duka.ui.home.events.BookMarkState

@Composable
fun BookMarkScreen(state:BookMarkState){

    LazyVerticalGrid(modifier = Modifier.fillMaxSize(), columns = GridCells.Fixed(2)) {
        Log.e("BookMark",state.products.toString())


        items(state.products.size){ it ->

            ProductsCard(modifier = Modifier,productItem = state.products[it]) {product ->

            }
        }
    }

}