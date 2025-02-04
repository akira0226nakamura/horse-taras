package com.example.horsetaras.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.horsetaras.ui.screens.DetailScreen
import com.example.horsetaras.ui.screens.HomeScreen
import com.example.horsetaras.ui.screens.ListScreen
import com.example.horsetaras.ui.screens.SearchScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.List.route) { ListScreen(navController) }
        composable(Screen.Search.route) { SearchScreen(navController) }
        composable("detail/{horseId}") { backStackEntry ->
            val horseId = backStackEntry.arguments?.getString("horseId")?.toIntOrNull()
            DetailScreen(navController, horseId)
        }
    }
}
