package com.maestro.duka.ui.home.tabs

import android.health.connect.datatypes.units.Length
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.maestro.duka.data.remote.dto.ProductsResponse
import com.maestro.duka.di.DukaApplication
import com.maestro.duka.ui.home.vm.HomeViewModel
import com.maestro.duka.ui.onboarding.WelcomeScreen
import com.maestro.duka.ui.theme.DukaTheme
import com.maestro.duka.utils.Resource

@Composable

fun ScreenHome(
    homeViewModel: HomeViewModel?
){

    val fetchproductState by homeViewModel!!.productsResponse.collectAsState()
    homeViewModel!!.fetchProducts()

    subscribeToEvents(fetchproductState)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Home",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }

}

@Composable
fun subscribeToEvents(fetchproductState: Resource<ProductsResponse>) {

    when(fetchproductState){
        is Resource.Error -> {
            Toast.makeText(DukaApplication.context,fetchproductState.message.toString(),Toast.LENGTH_LONG).show()

        }
        Resource.Idle -> {
            //do nothing

        }
        is Resource.Loading -> {
            CircularProgressIndicator()
        }
        is Resource.Success -> {
            Log.e("FetchedProducts",fetchproductState.toString())

        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DukaTheme {
        ScreenHome(null)
    }
}