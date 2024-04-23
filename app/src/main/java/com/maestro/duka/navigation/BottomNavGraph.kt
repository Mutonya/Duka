package com.maestro.duka.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.maestro.duka.data.remote.dto.ProductsResponseItem
import com.maestro.duka.ui.home.BottomNavigation.BottomNavItems
import com.maestro.duka.ui.home.details.DetailsScreen
import com.maestro.duka.ui.home.details.DetailsViewModel
import com.maestro.duka.ui.home.tabs.BookMarkScreen
import com.maestro.duka.ui.home.tabs.CartScreen
import com.maestro.duka.ui.home.tabs.HistoryScreen
import com.maestro.duka.ui.home.tabs.ScreenHome
import com.maestro.duka.ui.home.vm.BookMarkViewModel

import com.maestro.duka.ui.home.vm.HomeViewModel

@Composable
fun BottomNavGraph (
    navHostController: NavHostController
){
    NavHost(navController = navHostController, startDestination = BottomNavItems.Home.route ){
        composable(route = BottomNavItems.Home.route){
            val homeViewModel:HomeViewModel = hiltViewModel()

            ScreenHome(navigateToSearch = { navigateToTab(navHostController,BottomNavItems.Cart.route) }) {
                navigateToDetails(navHostController,it)
                
            }
        }
        composable(route = BottomNavItems.Cart.route){
            CartScreen()
        }
        composable(route = BottomNavItems.BookMark.route){
            val bookMarkViewMode:BookMarkViewModel = hiltViewModel()
            BookMarkScreen(state = bookMarkViewMode.state.value)
        }
        composable(route = BottomNavItems.History.route){
            HistoryScreen()
        }
        composable(route = HomeRoutes.DetailsScreen.route){
            navHostController.previousBackStackEntry?.savedStateHandle?.get<ProductsResponseItem?>("product")?.let {
               val bookMarkViewMode:DetailsViewModel = hiltViewModel()

            DetailsScreen(productItem = it, event =bookMarkViewMode::onEvent ) {
                navHostController.navigateUp()
            }

            }


        }
    }
}
private fun navigateToDetails(navController: NavController, product: ProductsResponseItem) {
    navController.currentBackStackEntry?.savedStateHandle?.set("product", product)
    navController.navigate(
        route = HomeRoutes.DetailsScreen.route
    )
}
private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}