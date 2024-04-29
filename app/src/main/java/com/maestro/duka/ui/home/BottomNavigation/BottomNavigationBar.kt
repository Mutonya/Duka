package com.maestro.duka.ui.home.BottomNavigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AssistChipDefaults.IconSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.maestro.duka.R
import com.maestro.duka.ui.theme.DukaTheme
import com.maestro.duka.ui.theme.fonts

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

    NavigationBar (modifier = Modifier.fillMaxWidth().height(56.dp),
            containerColor = Color.White,
            tonalElevation = 10.dp){
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
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                Icon(painter = painterResource(id = screen.icon),
                    contentDescription =null,
                    Modifier.size(IconSize))

                Spacer(modifier = Modifier.height(4.dp))
                Text(text = screen.title, fontFamily = fonts, style = MaterialTheme.typography.labelSmall)
            }


        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = colorResource(id = R.color.body),
            unselectedTextColor = colorResource(id = R.color.body),
            indicatorColor = MaterialTheme.colorScheme.background
        )
    )

}

@Preview
@Composable
fun previewBottom(){
    DukaTheme {

        BottomNavigationBar(rememberNavController())
    }
}