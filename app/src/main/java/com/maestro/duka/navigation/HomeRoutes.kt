package com.maestro.duka.navigation

sealed class HomeRoutes (val route:String){

     data object DetailsScreen:HomeRoutes("detailsScreen")
}