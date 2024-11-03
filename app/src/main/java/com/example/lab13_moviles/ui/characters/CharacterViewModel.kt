package com.example.lab13_moviles.ui.characters

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CharacterViewModel : ViewModel() {
    var isDarkMode = mutableStateOf(false)
        private set

    fun toggleDarkMode() {
        isDarkMode.value = !isDarkMode.value
    }

    val characters = listOf(
        "Personaje 1",
        "Personaje 2",
        "Personaje 3"
    )
}