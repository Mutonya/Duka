package com.maestro.duka.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.maestro.duka.ui.home.BottomNavigation.BottomNavItems
import com.maestro.duka.ui.home.tabs.BookMarkScreen
import com.maestro.duka.ui.home.tabs.CartScreen
import com.maestro.duka.ui.home.tabs.HistoryScreen
import com.maestro.duka.ui.home.tabs.ScreenHome

@Composable
fun BottomNavGraph (
    navHostController: NavHostController
){
    NavHost(navController = navHostController, startDestination =BottomNavItems.Home.route ){
        composable(route = BottomNavItems.Home.route){
            ScreenHome()
        }
        composable(route = BottomNavItems.Cart.route){
            CartScreen()
        }
        composable(route = BottomNavItems.BookMark.route){
            BookMarkScreen()
        }
        composable(route = BottomNavItems.History.route){
            HistoryScreen()
        }
    }
}