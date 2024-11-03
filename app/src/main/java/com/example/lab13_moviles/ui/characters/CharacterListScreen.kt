package com.example.lab13_moviles.ui.characters

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab13_moviles.R
import kotlinx.coroutines.delay

@Composable
fun CharacterListScreen(viewModel: CharacterViewModel) {
    // Estado y variables para manejar personajes y animaciones
    var selectedCharacter by remember { mutableStateOf("") }
    var isMovingLeft by remember { mutableStateOf(false) }
    var imageOffset by remember { mutableFloatStateOf(0f) }
    var showCharacter1Image by remember { mutableStateOf(false) }
    var showCharacter2Image by remember { mutableStateOf(false) }
    var showCharacter3Image by remember { mutableStateOf(false) }
    var imageSize1 by remember { mutableStateOf(100.dp) }
    var imageSize2 by remember { mutableStateOf(100.dp) }
    var imageSize3 by remember { mutableStateOf(100.dp) }
    var isCharacter1Selected by remember { mutableStateOf(false) }
    var isCharacter2Selected by remember { mutableStateOf(false) }
    var isCharacter3Selected by remember { mutableStateOf(false) }

    // Determina el color de fondo según el modo oscuro
    val backgroundColor by animateColorAsState(
        targetValue = if (viewModel.isDarkMode.value) Color.Black else Color.White
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        // Barra superior con el título
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .background(Color(0xFF7E1E1E)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Kirbys by Wisom - Laboratorio 13",
                color = Color.White,
                modifier = Modifier.padding(top = 30.dp)
            )
        }

        // Contenido principal de la lista
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen para cambiar al modo oscuro
            Image(
                painter = painterResource(id = R.drawable.luna),
                contentDescription = "Cambiar a modo oscuro",
                modifier = Modifier
                    .size(50.dp)
                    .clickable { viewModel.toggleDarkMode() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Imagen que se mueve de lado a lado
            Image(
                painter = painterResource(id = R.drawable.kyrbo),
                contentDescription = "Imagen que se mueve",
                modifier = Modifier
                    .size(50.dp)
                    .offset(x = imageOffset.dp, y = 0.dp)
                    .clickable {
                        isMovingLeft = !isMovingLeft
                    }
            )

            // Lógica para mover la imagen de izquierda a derecha
            LaunchedEffect(isMovingLeft) {
                while (true) {
                    imageOffset = when {
                        isMovingLeft && imageOffset > -80f -> imageOffset - 10f
                        !isMovingLeft && imageOffset < 80f -> imageOffset + 10f
                        else -> imageOffset
                    }
                    delay(50)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botones para seleccionar personajes
            CharacterButton("Personaje 1", isCharacter1Selected) {
                isCharacter1Selected = !isCharacter1Selected
                selectedCharacter = if (isCharacter1Selected) "Personaje 1" else ""
                showCharacter1Image = !showCharacter1Image
                showCharacter2Image = false
                showCharacter3Image = false
            }

            CharacterButton("Personaje 2", isCharacter2Selected) {
                isCharacter2Selected = !isCharacter2Selected
                selectedCharacter = if (isCharacter2Selected) "Personaje 2" else ""
                showCharacter2Image = !showCharacter2Image
                showCharacter1Image = false
                showCharacter3Image = false
            }

            CharacterButton("Personaje 3", isCharacter3Selected) {
                isCharacter3Selected = !isCharacter3Selected
                selectedCharacter = if (isCharacter3Selected) "Personaje 3" else ""
                showCharacter3Image = !showCharacter3Image
                showCharacter1Image = false
                showCharacter2Image = false
            }

            // Imagen y descripción del personaje 1
            AnimatedVisibility(
                visible = showCharacter1Image,
                enter = fadeIn(animationSpec = tween(500)),
                exit = fadeOut(animationSpec = tween(500))
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    // Animar el tamaño de la imagen al presionar
                    val animatedImageSize by animateDpAsState(targetValue = imageSize1)

                    Image(
                        painter = painterResource(id = R.drawable.almaita),
                        contentDescription = "Imagen de Personaje 1",
                        modifier = Modifier
                            .size(animatedImageSize)
                            .clickable {
                                // Cambiar el tamaño de la imagen al hacer clic
                                imageSize1 = if (imageSize1.value == 100f) 300.dp else 100.dp
                            }
                    )
                    Text(
                        text = "Kirby All Might\nKirby con capacidad de utilizar el One For All, " +
                                "con una fuerza inconmensurable. Con su indomable espíritu y su feroz " +
                                "determinación, siempre está listo para proteger a los más débiles.",
                        color = if (viewModel.isDarkMode.value) Color.White else Color.Black,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            // Imagen y descripción del personaje 2
            AnimatedVisibility(
                visible = showCharacter2Image,
                enter = fadeIn(animationSpec = tween(500)),
                exit = fadeOut(animationSpec = tween(500))
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    // Animar el tamaño de la imagen al presionar
                    val animatedImageSize by animateDpAsState(targetValue = imageSize2)

                    Image(
                        painter = painterResource(id = R.drawable.kyrbo2),
                        contentDescription = "Imagen de Personaje 2",
                        modifier = Modifier
                            .size(animatedImageSize)
                            .clickable {
                                // Cambiar el tamaño de la imagen al hacer clic
                                imageSize2 = if (imageSize2.value == 100f) 300.dp else 100.dp
                            }
                    )
                    Text(
                        text = "Kirby The Rock\nKirby con la capacidad de hacer películas como personaje " +
                                "musculoso y mirada matadora, además de ser un salvavidas con bastante " +
                                "musculatura. ¡Sus poses de lucha son legendarias!",
                        color = if (viewModel.isDarkMode.value) Color.White else Color.Black,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            // Imagen y descripción del personaje 3
            AnimatedVisibility(
                visible = showCharacter3Image,
                enter = fadeIn(animationSpec = tween(500)),
                exit = fadeOut(animationSpec = tween(500))
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    // Animar el tamaño de la imagen al presionar
                    val animatedImageSize by animateDpAsState(targetValue = imageSize3)

                    Image(
                        painter = painterResource(id = R.drawable.kyrbo3),
                        contentDescription = "Imagen de Personaje 3",
                        modifier = Modifier
                            .size(animatedImageSize)
                            .clickable {
                                // Cambiar el tamaño de la imagen al hacer clic
                                imageSize3 = if (imageSize3.value == 100f) 300.dp else 100.dp
                            }
                    )
                    Text(
                        text = "Kirby Dukenukem\nKirby con la mentalidad de nada más y nada menos que " +
                                "un verdadero héroe en el campo de batalla y en la diversión, " +
                                "siempre listo para enfrentarse a los desafíos con una sonrisa y " +
                                "un poderoso grito de guerra.",
                        color = if (viewModel.isDarkMode.value) Color.White else Color.Black,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

// Componente de botón para personajes
@Composable
fun CharacterButton(label: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color.Gray else Color.LightGray
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
    ) {
        Text(text = label)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCharacterList() {
    CharacterListScreen(CharacterViewModel())
}
