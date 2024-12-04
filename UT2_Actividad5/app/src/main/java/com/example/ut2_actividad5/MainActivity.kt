package com.example.ut2_actividad5

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ut2_actividad5.ui.theme.UT2_Actividad5Theme
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UT2_Actividad5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Mymenu()
                }
            }
        }
    }
}

// Definimos la base de datos
val baseDeDatos = FirebaseFirestore.getInstance()
// Accedemos a la colección de Equipos
val equipos = baseDeDatos.collection("Equipos")
// Listado de Equipos
var listaEquipos = mutableStateListOf<Equipos>()

// Lo del "OptIn" es para que funcione el "TopAppBarDefaults"
@OptIn(ExperimentalMaterial3Api::class)
// Con el "UnusedMaterial3ScaffoldPaddingParameter", podemos usar el "Scaffold"
// sin tener que rellenar todos sus argumentos
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Mymenu() {
    val navController = rememberNavController()
    val estadoDrawer = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    // Menú hamburguesa
    // @note El "Scaffold" de de ir DENTRO del "ModalNavigationDrawer"
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                // Texto del título
                Text(text = "CRUD", fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = Center)

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
                        rellenarListaEquipos()
                        navController.navigate("todos")
                        coroutineScope.launch { estadoDrawer.close() }
                    },
                    icon = { Icon(Icons.Filled.Menu, null) }
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
                        Text("CRUD en Firebase")
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
            }
        }
    }
}

@Composable
fun PantallaInicio() {

}

@Composable
fun PantallaInsertar() {
    val contexto = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Insertar", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        var id by remember { mutableStateOf("") }
        var nombre by remember { mutableStateOf("") }
        var titulos by remember { mutableStateOf("0") }

        // Edit text de "Identificador"
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text(text = "Identificador") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        // Edit text de "Nombre"
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text(text = "Nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        // Edit text de "Títulos"
        OutlinedTextField(
            value = titulos,
            onValueChange = { titulos = it },
            label = { Text(text = "Títulos") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        // Botón de insertar registro
        Button(
            onClick = {
                // Al hacer click en el botón, creamos el objeto "Equipo"
                // con los parámetros especificados y lo añadimos a la
                // base de datos
                val equipo = Equipos(id, nombre, titulos.toInt())
                equipos.document(id).set(equipo)
                Toast.makeText(contexto, "Registro insertado", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text("Insertar")
        }
    }
}

@Composable
fun PantallaBuscar() {
    val contexto = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Buscar", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        var id by remember { mutableStateOf("") }
        var nombre by remember { mutableStateOf("") }
        var titulos by remember { mutableStateOf("0") }

        // Edit text de "Identificador"
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text(text = "Identificador") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        // Mostramos el nombre del equipo y sus títulos
        Text(text = "Nombre del equipo: $nombre")
        Text(text = "Títulos: $titulos")

        // Botón de buscar
        Button(
            onClick = {
                // Si la entrada existe ("if (it.exists())")
                // obtener las variables de equipo (id, nombre, titulos)
                equipos.document(id.toString()).get().addOnSuccessListener {
                    if (it.exists()) {
                        id = it.get("id").toString()
                        nombre = it.get("nombre").toString()
                        titulos = it.get("titulos").toString()
                        Toast.makeText(contexto, "Registro encontrado", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(contexto, "Registro NO encontrado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        ) {
            Text("Buscar por ID")
        }
    }
}

@Composable
fun PantallaUpdate() {
    val contexto = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Actualizar", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        var id by remember { mutableStateOf("") }
        var nombre by remember { mutableStateOf("") }
        var titulos by remember { mutableStateOf("0") }

        // Edit text de "Identificador"
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text(text = "Identificador") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        // Edit text de "Nombre"
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text(text = "Nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        // Edit text de "Títulos"
        OutlinedTextField(
            value = titulos,
            onValueChange = { titulos = it },
            label = { Text(text = "Títulos") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        // Botón de actualizar registro
        Button(
            onClick = {
                // Actualizamos todos los valores utilizando un hashMap con clave to valor,
                // donde valor será los valores que estén escritos en los "OutlinedTextField"
                equipos.document(id.toString()).set(
                    hashMapOf(
                        "id" to id.toString(),
                        "nombre" to nombre.toString(),
                        "titulos" to titulos.toInt()
                    )
                )
                Toast.makeText(contexto, "Registro actualizado", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun PantallaEliminar() {
    val contexto = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Eliminar", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        var id by remember { mutableStateOf("") }

        // Edit text de "Identificador"
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text(text = "Identificador") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        // Botón de eliminar
        Button(
            onClick = {
                // Si la entrada existe ("if (it.exists())")
                // eliminar la entrada ("it.reference.delete()")
                equipos.document(id.toString()).get().addOnSuccessListener {
                    if (it.exists()) {
                        it.reference.delete()
                        Toast.makeText(contexto, "Registro eliminado", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(contexto, "Registro NO eliminado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        ) {
            Text("Eliminar por ID")
        }
    }
}

@Composable
fun PantallaTodos() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        // Añadimos un margen superior. Sino lo hacemos,
        // la primera entrada no se verá porque se renderizará por detrás del
        // menú superior de la aplicación (donde pone "MyApp")
        contentPadding = PaddingValues(top = 80.dp)
    ) {
        // Itera en el array "listaEquipos" y muestra todas sus entradas
        // usando la función "MostrarEquipo"
        items(listaEquipos) { equipo ->
            MostrarEquipo(equipo)
        }
    }
}

// Muestra una entrada equipos
@Composable
fun MostrarEquipo(equipo: Equipos) {
    HorizontalDivider(modifier = Modifier.fillMaxWidth().width(16.dp))
    Text(text = "Identificador: ${equipo.id}")
    Text(text = "Nombre: ${equipo.nombre}")
    Text(text = "Titulos: ${equipo.titulos.toString()}")
    HorizontalDivider(modifier = Modifier.fillMaxWidth().width(16.dp))
}

// Rellena "listaEquipos" con todos los equipos guardados en la colección
// en la base de datos
private fun rellenarListaEquipos() {
    listaEquipos.clear()
    equipos.get().addOnSuccessListener {
        for(entrada in it) {
            val equipo = Equipos(
                entrada.data.get("id").toString(),
                entrada.data.get("nombre").toString(),
                entrada.data.get("titulos").toString().toInt(),
            )
            listaEquipos.add(equipo)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    UT2_Actividad5Theme {
        Mymenu()
    }
}