package com.example.ut2_actividad4

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ut2_actividad4.ui.theme.UT2_Actividad4Theme
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * "Scaffold" es el andamiaje (la estructura de la aplicación)
 * Dentro voy a poner los componentes de la aplciación
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UT2_Actividad4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyAppScaffold()
                }
            }
        }
    }
}

// Lo del "OptIn" es para que funcione el "TopAppBarDefaults"
@OptIn(ExperimentalMaterial3Api::class)
// Con el "UnusedMaterial3ScaffoldPaddingParameter", podemos usar el "Scaffold"
// sin tener que rellenar todos sus argumentos
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyAppScaffold() {
    val navController = rememberNavController()

    Scaffold(
        // Barra superior (barra de título)
        topBar = {
            TopAppBar(
                // Título de aplicación
                title = { Text(text = "MyApp") },
                // Color de la barra
                colors = TopAppBarDefaults.topAppBarColors(Color.Blue)
            )
        },

        // Barra inferior (barra de navegación)
        bottomBar = {
            NavigationBar {
                // Botón de Inicio
                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate("inicio") },
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Inicio") },
                    label = { Text(text = "Home") }
                )

                // Botón de Mapa
                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate("mapa") },
                    icon = { Icon(Icons.Filled.Place, contentDescription = "Mapas") },
                    label = { Text(text = "Mapa") }
                )

                // Botón de Corrutinas
                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate("corrutinas") },
                    icon = { Icon(Icons.Filled.Build, contentDescription = "Corrutinas") },
                    label = { Text(text = "Corrutinas") }
                )
            }
        }
    ) {
        // El árbol de navegación
        NavHost(navController = navController, startDestination = "inicio") {
            composable("inicio") {
                PantallaInicio()
            }
            composable("mapa") {
                PantallaMapa()
            }
            composable("corrutinas") {
                PantallaCorrutinas()
            }
        }
    }
}

/**
 * Esta pantalla solo imprime dos líneas de texto
 */
@Composable
fun PantallaInicio() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "App con Scaffold (topBar y bottomBar", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = "Ver. 0.1", fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

/**
 * Esta pantalla muestra una barra de carga utilizando las corrutinas
 */
@Composable
fun PantallaCorrutinas() {
    var texto by remember { mutableStateOf("Inicio") }
    val corutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Corrutinas", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text(texto)
        Button(
            onClick = {
                corutineScope.launch(Dispatchers.Default) {
                    for (i in 1 .. 10) {
                        // Esperamos un segundo, y luego incrementamos en 1
                        simularTarea()
                        texto = "$i"
                    }
                }
            }
        ) {
            Text("Comenzar.")
        }
    }
}

@Composable
fun PantallaMapa() {
    // Definimos un objeto latitud y longitud que será donde nos posicionaremos
    val coordenadasAuditorio = LatLng(27.9063166, -15.4491194)

    // Definimos el estado de la cámara y nos posicionaremos en el Teatro Auditorios de Agüimes
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(coordenadasAuditorio, 10f)
    }

    // Usamos el elemento Composable "GoogleMap" para agregar un Mapa
    GoogleMap(
        // Hacemos que el mapa ocupe el espacio permitido
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        // Definimos el marcador rojo (Marker),
        // el cual apunta al lugar indicado (el Teatro de Agüimes, en este caso)
        Marker(
            state = MarkerState(position = coordenadasAuditorio),
            title = "Teatro Auditorio de Agüimes",
            snipper = "Marcador en el Teatro Auditorio de Agüimes"
        )
    }
}

/**
 * Simula la realización de una tarea
 * (espera 1 segundo sin hacer nada)
 */
fun simularTarea() {
    try {
        Thread.sleep(1000)
    } catch (e: InterruptedException) {}
}

@Preview(showSystemUi = true)
@Composable
fun MyAppScaffoldPreview() {
    UT2_Actividad4Theme {
        MyAppScaffold()
    }
}