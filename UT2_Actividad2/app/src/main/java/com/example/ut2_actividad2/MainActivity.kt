package com.example.ut2_actividad2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ut2_actividad2.ui.theme.UT2_Actividad2Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UT2_Actividad2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val myAppNavController = rememberNavController()
    Navegacion(miNavController = myAppNavController)
}

/**
 * Esta función contiene el grafo de navegación,
 * es decir, todas las pantallas hacia las que se puede acceder
 */
@Composable
fun Navegacion(miNavController: NavHostController) {
    NavHost(navController = miNavController, startDestination = "pantalla1") {
        composable("pantalla1") {
            Pantalla1(miNavController)
        }

        composable("pantalla2") {
            Pantalla2(miNavController)
        }

        composable("corrutinas") {
            Corrutinas(miNavController)
        }
    }
}

@Composable
fun Pantalla1(miNavController: NavHostController) {
    // Creamos una columna
    Column (
        // Que ocuper todo el tamaño y tenga un espaciado de 16
        // (.dp para padding, y .sp para tamaño de letra)
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        // Centrado vertical y horizontalmente
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Colocamos un Texto
        Text("Primera pantalla")

        // Un espacio de 16 dp
        Spacer(modifier = Modifier.height(16.dp))

        // Botón para volver a la otra pantalla (la pantalla2)

        Button (
            onClick = { miNavController.navigate("pantalla2") }
        ) {
            // Texto del botón
            Text(text = "A la segunda pantalla")
        }
    }
}

@Composable
fun Pantalla2(miNavController: NavHostController) {
    // Creamos una columna
    Column (
        // Que ocuper todo el tamaño y tenga un espaciado de 16
        // (.dp para padding, y .sp para tamaño de letra)
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        // Centrado vertical y horizontalmente
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Colocamos un Texto
        Text("Segunda pantalla")

        // Un espacio de 16 dp
        Spacer(modifier = Modifier.height(16.dp))

        // Botón para volver a la otra pantalla (la de corrutinas)

        Button (
            onClick = { miNavController.navigate("corrutinas") }
        ) {
            // Texto del botón
            Text(text = "A corrutinas")
        }
    }
}

@Composable
fun Corrutinas(miNavController: NavHostController) {
    // Valor para el progreso de la barra que tiene que ser float
    var valor by remember { mutableStateOf(0f) }
    // Para crear la corrutina (el hilo, Thread)
    // Si no fuese por la corrutina, la app dejaría de responder al cabo de unos segundos
    // tras hacer click en el botón de "Actualizar"
    val coroutineScope = rememberCoroutineScope()

    // Creamos una columna
    Column (
        // Que ocuper todo el tamaño y tenga un espaciado de 16
        // (.dp para padding, y .sp para tamaño de letra)
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        // Centrado vertical y horizontalmente
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Colocamos un Texto
        Text("Tercera pantalla")

        // Colocamos la barra de progreso
        LinearProgressIndicator(progress = { valor })

        // Un espacio de 16 dp
        Spacer(modifier = Modifier.height(16.dp))

        // Botón para ejecutar la corrutina
        Button (
            onClick = {
                coroutineScope.launch(Dispatchers.Default) {
                    // La barra de progreso va a subir de 10 en 10
                    for (i in 1..10) {
                        simularTarea()
                        valor = i / 10f
                    }
                }
            }
        ) {
            // Texto del botón
            Text(text = "Actualizar")
        }

        // Botón para volver a la otra pantalla (la pantalla1)
        Button (
            onClick = { miNavController.navigate("pantalla1") }
        ) {
            // Texto del botón
            Text(text = "A la pantalla 1")
        }
    }
}

// Simula una tarea que tarda en hacerse
fun simularTarea() {
    try {
        Thread.sleep(1000)
    }
    catch (e: InterruptedException) {}
}

@Preview(showSystemUi = true)
@Composable
fun MyAppPreview() {
    MyApp()
}