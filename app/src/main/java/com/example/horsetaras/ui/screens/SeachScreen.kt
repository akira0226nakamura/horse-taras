package com.example.horsetaras.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.horsetaras.model.HorseData
import com.example.horsetaras.ui.theme.HorseTarasTheme

@Composable
fun SearchScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val filteredHorses = remember(searchQuery.text) {
        HorseData.horses.filter {
            it.name.contains(searchQuery.text, ignoreCase = true)
        }
    }

    Scaffold(
        topBar = {
            SearchAppBar(
                searchQuery = searchQuery,
                onSearchQueryChanged = { searchQuery = it },
                onBackClicked = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        HorseList(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            horses = filteredHorses
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    searchQuery: TextFieldValue,
    onSearchQueryChanged: (TextFieldValue) -> Unit,
    onBackClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .background(Color.White, shape = MaterialTheme.shapes.medium) // White background with rounded corners
                    .padding(horizontal = 12.dp, vertical = 6.dp) // Inner padding
            ) {
                BasicTextField(
                    value = searchQuery,
                    onValueChange = onSearchQueryChanged,
                    textStyle = LocalTextStyle.current.copy(color = Color.Black),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF6D4C41), // Brown top bar
            titleContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Back", tint = Color.White)
            }
        },
        actions = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Color.White)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    val fakeNavController = rememberNavController()
    HorseTarasTheme {
        SearchScreen(navController = fakeNavController)
    }
}
