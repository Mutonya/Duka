package com.maestro.duka.ui.home.BottomNavigation

import com.maestro.duka.R

sealed class BottomNavItems (
    val route:String,
    val title:String,
    val icon:Int
){
    object Home:BottomNavItems(
        route = "home",
        title = "Home",
        icon = R.drawable.btn_1
    )
    object BookMark:BottomNavItems(
        route = "bookmark",
        title = "Wish list",
        icon = R.drawable.btn_2
    )
    object Cart:BottomNavItems(
        route = "cart",
        title = "Cart",
        icon = R.drawable.btn_3
    )
    object History:BottomNavItems(
        route = "history",
        title = "History",
        icon = R.drawable.btn_4
    )
}