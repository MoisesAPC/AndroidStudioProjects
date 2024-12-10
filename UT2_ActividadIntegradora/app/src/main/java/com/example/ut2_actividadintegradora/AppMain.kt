package com.example.ut2_actividadintegradora

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
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
                Text(text = "UT2 Actividad Integradora", fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = Center)

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

                // Opción de "Insertar"
                NavigationDrawerItem(
                    label = { Text("Insertar") },
                    selected = true,
                    onClick = {
                        navController.navigate("insertar")
                        coroutineScope.launch { estadoDrawer.close() }
                    },
                    icon = { Icon(Icons.Filled.Add, null) }
                )

                // Opción de "Buscar"
                NavigationDrawerItem(
                    label = { Text("Buscar") },
                    selected = true,
                    onClick = {
                        navController.navigate("buscar")
                        coroutineScope.launch { estadoDrawer.close() }
                    },
                    icon = { Icon(Icons.Filled.Search, null) }
                )

                // Opción de "Actualizar"
                NavigationDrawerItem(
                    label = { Text("Actualizar") },
                    selected = true,
                    onClick = {
                        navController.navigate("actualizar")
                        coroutineScope.launch { estadoDrawer.close() }
                    },
                    icon = { Icon(Icons.Filled.Edit, null) }
                )

                // Opción de "Eliminar"
                NavigationDrawerItem(
                    label = { Text("Eliminar") },
                    selected = true,
                    onClick = {
                        navController.navigate("eliminar")
                        coroutineScope.launch { estadoDrawer.close() }
                    },
                    icon = { Icon(Icons.Filled.Delete, null) }
                )

                // Opción de mostrar todas las entradas de la colección
                NavigationDrawerItem(
                    label = { Text("Todos") },
                    selected = true,
                    onClick = {
                        rellenarListaProductos()
                        navController.navigate("todos")
                        coroutineScope.launch { estadoDrawer.close() }
                    },
                    icon = { Icon(Icons.Filled.Menu, null) }
                )

                // Línea horizontal divisoria
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth().height(16.dp).padding(vertical = 16.dp)
                )

                // Botón de Mapa
                NavigationDrawerItem(
                    label = { Text("Mapa") },
                    selected = true,
                    onClick = {
                        rellenarListaProductos()
                        navController.navigate("mapa")
                        coroutineScope.launch { estadoDrawer.close() }
                    },
                    icon = { Icon(Icons.Filled.Place, null) }
                )

                // Línea horizontal divisoria
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth().height(16.dp).padding(vertical = 16.dp)
                )

                // Botón de Corrutinas
                NavigationDrawerItem(
                    label = { Text("Hilos") },
                    selected = true,
                    onClick = {
                        rellenarListaProductos()
                        navController.navigate("hilos")
                        coroutineScope.launch { estadoDrawer.close() }
                    },
                    icon = { Icon(Icons.Filled.Build, null) }
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
                        Text("UT2 Actividad Integradora")
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

                composable("insertar") {
                    PantallaInsertar()
                }

                composable("buscar") {
                    PantallaBuscar()
                }

                composable("actualizar") {
                    PantallaUpdate()
                }

                composable("eliminar") {
                    PantallaEliminar()
                }

                composable("todos") {
                    PantallaTodos()
                }

                composable("mapa") {
                    PantallaMapa()
                }

                composable("hilos") {
                    PantallaHilos()
                }
            }
        }
    }
}

// Muestra una entrada productos
@Composable
fun MostrarProductos(producto: Productos) {
    HorizontalDivider(modifier = Modifier.fillMaxWidth().width(16.dp))
    Text(text = "ID: ${producto.id}")
    Text(text = "Nombre: ${producto.nombre}")
    Text(text = "Marca: ${producto.marca}")
    Text(text = "Precio: ${producto.precio.toString()}")
    HorizontalDivider(modifier = Modifier.fillMaxWidth().width(16.dp))
}

// Rellena "listaProductos" con todos los productos guardados en la colección
// en la base de datos
private fun rellenarListaProductos() {
    listaProductos.clear()
    productos.get().addOnSuccessListener {
        for(entrada in it) {
            val producto = Productos(
                entrada.data.get("id").toString(),
                entrada.data.get("nombre").toString(),
                entrada.data.get("marca").toString(),
                entrada.data.get("precio").toString().toFloat(),
            )
            listaProductos.add(producto)
        }
    }
}