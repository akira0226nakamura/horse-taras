package com.example.horsetaras.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import com.example.horsetaras.R
import com.example.horsetaras.model.HorseData
import com.example.horsetaras.ui.components.LottieLoadingAnimation
import com.example.horsetaras.ui.theme.HorseTarasTheme
import com.example.horsetaras.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, horseId: Int?) {
    val horse = remember { HorseData.horses.find { it.id == horseId } }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(horse?.name ?: "Horse Details") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF6D4C41), // Brown color
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigateUp() },
                        colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (horse != null) {
                val hasImages = horse.images.isNotEmpty()
                val apiUrl = Constants.HORSES_ENDPOINT
                val pagerState = rememberPagerState { horse.images.size }

                // Image Slider (Only if there are images)
                if (hasImages) {
                    Column {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.fillMaxWidth()
                        ) { page ->
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                SubcomposeAsyncImage(
                                    model = "${apiUrl}/${horse.images[page]}",
                                    contentDescription = horse.name,
                                    loading = { LottieLoadingAnimation() },
                                    error = {
                                        Image(
                                            painter = painterResource(id = R.drawable.no_image),
                                            contentDescription = "Default Horse Image",
                                            modifier = Modifier.fillMaxWidth(),
                                            contentScale = ContentScale.Fit // Ensures full width without cropping
                                        )
                                    },
                                    contentScale = ContentScale.Fit, // Ensures full width without cropping
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                } else {
                    // Default Image when no images are available
                    Image(
                        painter = painterResource(id = R.drawable.no_image),
                        contentDescription = "No Image Available",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                // Horse Details
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Breed: ${horse.breed}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "Age: ${horse.age} years",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "Price: $${horse.price}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(8.dp)
                )

                // Rating Module
                RatingBar(rating = horse.rating, modifier = Modifier.padding(8.dp))
            } else {
                Text(text = "Horse not found", modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
fun RatingBar(rating: Float, modifier: Modifier = Modifier) {
    val maxRating = 5
    val displayRating = rating.coerceIn(0f, maxRating.toFloat()) // Prevents invalid ratings

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        for (i in 1..maxRating) {
            Icon(
                imageVector = if (i <= displayRating) Icons.Filled.Star else Icons.Outlined.StarBorder,
                contentDescription = null,
                tint = Color(0xFFFFD700), // Gold color for stars
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "$displayRating / 5.0", style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    val fakeNavController = rememberNavController()

    HorseTarasTheme {
        DetailScreen(navController = fakeNavController, 1)
    }
}
