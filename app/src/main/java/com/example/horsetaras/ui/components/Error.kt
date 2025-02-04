package com.example.horsetaras.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.horsetaras.ui.theme.HorseTarasTheme

@Composable
fun ErrorView() {
    Scaffold { innerPadding ->
        Text("🐎 Error Component", modifier = Modifier.padding(innerPadding))
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HorseTarasTheme {
        ErrorView()
    }
}