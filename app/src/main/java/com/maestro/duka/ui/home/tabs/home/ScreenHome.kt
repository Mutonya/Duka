package com.maestro.duka.ui.home.tabs.home

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maestro.duka.R
import com.maestro.duka.data.remote.dto.ProductsResponseItem
import com.maestro.duka.ui.core.ProductsCard
import com.maestro.duka.ui.core.SearchBar
import com.maestro.duka.ui.home.events.HomeUiState
import com.maestro.duka.ui.home.vm.HomeViewModel
import com.maestro.duka.ui.theme.Blueback
import com.maestro.duka.ui.theme.DukaTheme

@Composable

fun ScreenHome(
    homeViewModel: HomeViewModel? = hiltViewModel(),
    navigateToSearch:() ->Unit,
    navigateToDetails:(ProductsResponseItem) ->Unit
){

    val viewState = homeViewModel?.homeUiState?.collectAsState()
    if (viewState != null) {
        Log.e("ViewState",viewState.value.toString())
        if (viewState.value.loading){
            CircularProgressIndicator()
        }else if (viewState.value.error.isNotEmpty()){
            //show error
        }else{
            HomeScreen(homeUiState = viewState.value, navigateToSearch = {
                navigateToSearch.invoke()
            }) {
                navigateToDetails(it)
            }
        }

    }



}

@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
    navigateToSearch:() ->Unit,
    navigateToDetails:(ProductsResponseItem) ->Unit){

    LazyColumn (
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = 20.dp)
    ){
        item {
            SearchView(navigateToSearch)
            Spacer(modifier =Modifier.height(8.dp) )
        }
        items(homeUiState.products.size) { it ->

            ProductsCard(modifier = Modifier,productItem = homeUiState.products[it]) { product ->
                navigateToDetails.invoke(product)
            }
        }
    }

}

@Composable
private fun ProductsVertical(
    homeUiState: HomeUiState,
    navigateToDetails: (ProductsResponseItem) -> Unit
) {
    LazyVerticalGrid(modifier = Modifier.fillMaxSize(), columns = GridCells.Fixed(2)) {


        items(homeUiState.products.size) { it ->

            ProductsCard(modifier = Modifier,productItem = homeUiState.products[it]) { product ->
                navigateToDetails.invoke(product)
            }
        }
    }
}

@Composable
private fun SearchView(navigateToSearch: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp).copy(topStart = ZeroCornerSize, topEnd = ZeroCornerSize),
        colors = CardDefaults.cardColors(
            containerColor = Blueback
        )
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            color = Color.White,
            text = stringResource(id = R.string.jane)
        )
        SearchBar(modifier = Modifier.padding(8.dp),
            text = "", readOnly = true,
            onValueChange = {},
            onclick = navigateToSearch,
            onSearch = {})

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DukaTheme {
//HomeScreen()
    }
}