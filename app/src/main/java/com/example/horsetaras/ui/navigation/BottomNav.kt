package com.example.horsetaras.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun HorseTarasApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        NavGraph(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun BottomNavBar(navController: NavHostController) {
    // Only include Home and List in the bottom nav
    val items = listOf(Screen.Home, Screen.List)

    val currentRoute = currentRoute(navController)

    NavigationBar {
        items.forEach { screen ->
            val isSelected = currentRoute == screen.route

            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.title) },
                label = { Text(screen.title) },
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route)
                }
            )
        }
    }
}

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    data object Home : Screen("home", "Home", Icons.Default.Home)
    data object List : Screen("list", "Horses", Icons.Default.AccountTree)
    data object Search : Screen("search", "Search", Icons.Default.Search)
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    return navBackStackEntry?.destination?.route
}
