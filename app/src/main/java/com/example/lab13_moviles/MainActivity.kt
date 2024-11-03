package com.example.lab13_moviles

import AnimatedBoxScreen
import ColorChangingBoxScreen
import StatusScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab13_moviles.ui.theme.Lab13movilesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab13movilesTheme {
                var showColorChangingBox by remember { mutableStateOf(false) }
                var showStatusScreen by remember { mutableStateOf(false) }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Button(onClick = { showColorChangingBox = !showColorChangingBox }) {
                            Text(text = if (showColorChangingBox) "Mostrar Caja Animada" else "Mostrar Caja de Otro Color")
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(onClick = { showStatusScreen = !showStatusScreen }) { // Botón para mostrar el StatusScreen
                            Text(text = if (showStatusScreen) "Mostrar Otras Funcionalidades" else "Mostrar Estado")
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        Spacer(modifier = Modifier.weight(1f))

                        when {
                            showStatusScreen -> {
                                StatusScreen()
                            }
                            showColorChangingBox -> {
                                ColorChangingBoxScreen()
                            }
                            else -> {
                                AnimatedBoxScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}
