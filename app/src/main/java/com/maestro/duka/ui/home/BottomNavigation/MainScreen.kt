package com.maestro.duka.ui.home.BottomNavigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.maestro.duka.navigation.BottomNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val backstate = navController.currentBackStackEntryAsState().value
    val isBottomVisible = remember(key1 = backstate) {
        backstate?.destination?.route == BottomNavItems.Cart.route||
                backstate?.destination?.route == BottomNavItems.BookMark.route||
                backstate?.destination?.route == BottomNavItems.Home.route||
                backstate?.destination?.route == BottomNavItems.History.route
    }

    Scaffold (modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomVisible){
                NavigationBar {
                    BottomNavigationBar(navHostController = navController)
                }
            }

        }
    ){


        Box(modifier = Modifier.padding(bottom = it.calculateBottomPadding())){
            val bottomPadding = it.calculateBottomPadding()
            BottomNavGraph(navHostController = navController,Modifier.padding(bottom = bottomPadding) )
        }


    }
}