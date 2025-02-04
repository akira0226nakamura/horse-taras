package com.example.horsetaras.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import com.example.horsetaras.R
import com.example.horsetaras.ui.theme.HorseTarasTheme
import com.example.horsetaras.model.Horse
import com.example.horsetaras.model.HorseData
import com.example.horsetaras.ui.components.LottieLoadingAnimation
import com.example.horsetaras.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController) {
    val allHorses = remember { HorseData.horses }
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Horse Lists") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF6D4C41), // Example: Brown color
                    titleContentColor = Color.White // Title text color
                ),
            )
        }
    ) { innerPadding ->
        HorseList(modifier = Modifier.padding(innerPadding), navController, allHorses)
    }
}

@Composable
fun HorseList(modifier: Modifier = Modifier, navController: NavController, horses: List<Horse>) {
    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(horses) { horse ->
            HorseItem(horse, navController)
        }
    }
}

@Composable
fun HorseItem(horse: Horse, navController: NavController) {
    val apiUrl = Constants.HORSES_ENDPOINT
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("detail/${horse.id}")
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Box(modifier = Modifier.size(96.dp)) {
                SubcomposeAsyncImage(
                    model = "${apiUrl}/${horse.images[0]}",
                    contentDescription = horse.name,
                    loading = {
                        LottieLoadingAnimation()
                    },
                    error = {
                        Image(
                            painter = painterResource(id = R.drawable.no_image),
                            contentDescription = "Default Horse Image",
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                    },
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = horse.name, style = MaterialTheme.typography.titleLarge)
                Text(text = "Breed: ${horse.breed}")
                Text(text = "Age: ${horse.age} years")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    val fakeNavController = rememberNavController() // Mock navigation controller

    HorseTarasTheme {
        ListScreen(navController = fakeNavController)
    }
}