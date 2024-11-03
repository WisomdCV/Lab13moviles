package com.example.lab13_moviles

import ColorChangingBoxScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.lab13_moviles.ui.theme.Lab13movilesTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab13movilesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ColorChangingBoxScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}