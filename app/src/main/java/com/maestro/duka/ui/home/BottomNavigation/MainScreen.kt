package com.maestro.duka.ui.home.BottomNavigation

import android.annotation.SuppressLint
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.maestro.duka.navigation.BottomNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold (
        bottomBar = {
            NavigationBar {
                BottomNavigationBar(navHostController = navController)
            }
        }
    ){

        BottomNavGraph(navHostController = navController)
    }
}