package com.example.lab13_moviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import com.example.lab13_moviles.ui.characters.CharacterListScreen
import com.example.lab13_moviles.ui.characters.CharacterViewModel
import com.example.lab13_moviles.ui.theme.Lab13movilesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab13movilesTheme {
                val viewModel = remember { CharacterViewModel() }
                CharacterListScreen(viewModel = viewModel)
            }
        }
    }
}
