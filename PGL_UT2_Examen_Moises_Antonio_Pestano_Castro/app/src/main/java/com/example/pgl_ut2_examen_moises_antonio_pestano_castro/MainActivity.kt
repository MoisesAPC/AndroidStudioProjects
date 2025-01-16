package com.example.pgl_ut2_examen_moises_antonio_pestano_castro

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pgl_ut2_examen_moises_antonio_pestano_castro.ui.theme.PGL_UT2_Examen_Moises_Antonio_Pestano_CastroTheme
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PGL_UT2_Examen_Moises_Antonio_Pestano_CastroTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppMain()
                }
            }
        }
    }
}

// Definimos la base de datos
val baseDeDatos = FirebaseFirestore.getInstance()
// Accedemos a la colección de Fincas
val fincas = baseDeDatos.collection("Fincas")
// Listado de Fincas
var listaFincas = mutableStateListOf<Fincas>()

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
fun AppMain() {
    val navController = rememberNavController()
    val estadoDrawer = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    // Menú hamburguesa
    // @note El "Scaffold" de de ir DENTRO del "ModalNavigationDrawer"
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                // Texto del título
                Text(text = "Moisés Antonio Pestano Castro - Supuesto Práctico UT2", fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = Center)

                // Opción de "Inicio"
                NavigationDrawerItem(
                    label = { Text("Inicio") },
                    selected = true,
                    onClick = {
                        navController.navigate("inicio")
                        coroutineScope.launch { estadoDrawer.close() }
                    },
                    icon = { Icon(Icons.Filled.Home, null) }
                )

                // Opción de "Nums Narcisistas"
                NavigationDrawerItem(
                    label = { Text("Nums Narcisistas") },
                    selected = true,
                    onClick = {
                        navController.navigate("nums_narcisistas")
                        coroutineScope.launch { estadoDrawer.close() }
                    },
                    icon = { Icon(Icons.Filled.Add, null) }
                )

                // Opción de "Base de Datos"
                NavigationDrawerItem(
                    label = { Text("Base de Datos") },
                    selected = true,
                    onClick = {
                        navController.navigate("base_de_datos")
                        coroutineScope.launch { estadoDrawer.close() }
                    },
                    icon = { Icon(Icons.Filled.Search, null) }
                )
            }
        },
        drawerState = estadoDrawer
    ) {
        Scaffold(
            // Menú superior
            topBar = {
                TopAppBar(
                    // Título
                    title = {
                        Text("Moisés Antonio Pestano Castro - Supuesto Práctico UT2")
                    },

                    // Botón del menú hamburguesa
                    navigationIcon = {
                        IconButton(
                            // Al hacer click en el botón, abrimos el menú hamburguesa
                            onClick = {
                                coroutineScope.launch { estadoDrawer.open() }
                            }
                        ) {
                            Icon(Icons.Filled.Menu, contentDescription = null)
                        }
                    }
                )
            }
        ) {
            // Árbol de navegación (empezamos desde la pantalla "inicio")
            NavHost(navController = navController, startDestination = "inicio") {
                composable("inicio") {
                    PantallaInicio()
                }

                composable("nums_narcisistas") {
                    PantallaNumsNarcisistas()
                }

                composable("base_de_datos") {
                    PantallaBaseDeDatos()
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    PGL_UT2_Examen_Moises_Antonio_Pestano_CastroTheme {
        AppMain();
    }
}