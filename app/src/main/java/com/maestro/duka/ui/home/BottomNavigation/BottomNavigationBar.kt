package com.maestro.duka.ui.home.BottomNavigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable

fun BottomNavigationBar(navHostController: NavHostController){
    val screens = listOf(
        BottomNavItems.Home,
        BottomNavItems.BookMark,
        BottomNavItems.Cart,
        BottomNavItems.History
    )

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    NavigationBar (modifier = Modifier){
        screens.forEach { screens ->


            AddItem(
                screen = screens,
                currentDestination =currentDestination ,
                navHostController = navHostController
            )

        }
    }

}
@Composable
fun RowScope.AddItem(
    screen: BottomNavItems,
    currentDestination: NavDestination?,
    navHostController: NavHostController

){
    NavigationBarItem(
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        }==true,
        onClick = {
            navHostController.navigate(screen.route)
        },
        icon = {
            Icon(painter = painterResource(id = screen.icon), contentDescription =null )

        },
        label = {
            Text(text = screen.title)
        },
        alwaysShowLabel = true
    )

}