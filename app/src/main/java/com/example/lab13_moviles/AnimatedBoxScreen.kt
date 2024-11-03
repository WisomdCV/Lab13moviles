import androidx.compose.animation.*
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class ScreenState { Cargando, Contenido, Error }

@Composable
fun AnimatedBoxScreen(modifier: Modifier = Modifier) {
    var isVisible by remember { mutableStateOf(false) }
    var moveBox by remember { mutableStateOf(false) }

    val boxSize by animateDpAsState(targetValue = if (moveBox) 150.dp else 100.dp)
    val boxOffsetX by animateDpAsState(targetValue = if (moveBox) 50.dp else 0.dp)
    val boxOffsetY by animateDpAsState(targetValue = if (moveBox) 50.dp else 0.dp)

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { isVisible = !isVisible }) {
            Text(text = if (isVisible) "Ocultar Cuadro" else "Mostrar Cuadro")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { moveBox = !moveBox }) {
            Text(text = "Mover y Cambiar Tamaño")
        }

        Spacer(modifier = Modifier.weight(1f))

        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(durationMillis = 800)),
            exit = fadeOut(animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy))
        ) {
            Box(
                modifier = Modifier
                    .offset(x = boxOffsetX, y = boxOffsetY)
                    .size(boxSize)
                    .background(Color.White)
            )
        }
    }
}

@Composable
fun ColorChangingBoxScreen(modifier: Modifier = Modifier) {
    var isBlue by remember { mutableStateOf(true) }
    var useSpringAnimation by remember { mutableStateOf(false) }

    val animationSpec: AnimationSpec<Color> = if (useSpringAnimation) {
        spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessMedium)
    } else {
        tween(durationMillis = 1000)
    }

    val backgroundColor by animateColorAsState(
        targetValue = if (isBlue) Color.Blue else Color.Green,
        animationSpec = animationSpec
    )

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { isBlue = !isBlue }) {
            Text(text = "Cambiar Color")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { useSpringAnimation = !useSpringAnimation }) {
            Text(text = if (useSpringAnimation) "Usar Tween" else "Usar Spring")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(backgroundColor)
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun StatusScreen(modifier: Modifier = Modifier) {
    var currentState by remember { mutableStateOf(ScreenState.Cargando) }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            currentState = when (currentState) {
                ScreenState.Cargando -> ScreenState.Contenido
                ScreenState.Contenido -> ScreenState.Error
                ScreenState.Error -> ScreenState.Cargando
            }
        }) {
            Text(text = "Cambiar Estado")
        }

        Spacer(modifier = Modifier.height(16.dp))


        AnimatedContent(
            targetState = currentState,
            transitionSpec = {
                fadeIn(animationSpec = tween(500)) with fadeOut(animationSpec = tween(500))
            }
        ) { state ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(
                        when (state) {
                            ScreenState.Cargando -> Color.Yellow
                            ScreenState.Contenido -> Color.Green
                            ScreenState.Error -> Color.Red
                        }
                    )
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = when (state) {
                        ScreenState.Cargando -> "Cargando..."
                        ScreenState.Contenido -> "Contenido Cargado"
                        ScreenState.Error -> "Error al Cargar"
                    },
                    color = Color.White
                )
            }
        }
    }
}
