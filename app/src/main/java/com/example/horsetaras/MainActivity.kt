package com.example.horsetaras

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.horsetaras.ui.navigation.HorseTarasApp
import com.example.horsetaras.ui.theme.HorseTarasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HorseTarasTheme {
                HorseTarasApp()
            }
        }
    }
}