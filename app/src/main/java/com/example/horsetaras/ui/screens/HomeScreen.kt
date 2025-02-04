package com.example.horsetaras.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.horsetaras.ui.theme.HorseTarasTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Welcome Taras Home") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF6D4C41), // Example: Brown color
                    titleContentColor = Color.White // Title text color
                ),
                actions = {
                    IconButton(
                        onClick = {
                            // TODO: Handle search click
                            navController.navigate("search")
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.White // Ensures the button has white color
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.White // Ensures the icon inside is white
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        Text("🐎 Home Screen", modifier = Modifier.padding(innerPadding))
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val fakeNavController = rememberNavController() // Mock navigation controller

    HorseTarasTheme {
        HomeScreen(navController = fakeNavController)
    }
}
