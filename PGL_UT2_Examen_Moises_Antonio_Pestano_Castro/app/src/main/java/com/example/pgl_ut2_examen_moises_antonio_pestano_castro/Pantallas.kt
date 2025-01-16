package com.example.pgl_ut2_examen_moises_antonio_pestano_castro

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Math.pow

@Composable
fun PantallaInicio() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Moisés Antonio Pestano Castro - Supuesto Práctico UT2", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = "Ver. 1.0", fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun PantallaNumsNarcisistas() {
    val contexto = LocalContext.current
    var texto by remember { mutableStateOf("Inicio") }
    val corutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(text = "Nums Narcisistas", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        var valor_inicial by remember { mutableStateOf("0") }
        var valor_final by remember { mutableStateOf("0") }

        // Edit text del valor inicial
        OutlinedTextField(
            value = valor_inicial,
            onValueChange = { valor_inicial = it },
            label = { Text(text = "Valor inicial") },
            // Con esto hacemos que el text field solo acepte entradas numéricas (integer)
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        // Edit text del valor final
        OutlinedTextField(
            value = valor_final,
            onValueChange = { valor_final = it },
            label = { Text(text = "Valor final") },
            // Con esto hacemos que el text field solo acepte entradas numéricas (integer)
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Texto de salida que muestra los números narcisistas
        Text(texto)

        Button(
            onClick = {
                texto = ""
                if (valor_inicial != "" && valor_final != "") {
                    corutineScope.launch(Dispatchers.Default) {
                        for (i in valor_inicial.toInt()..valor_final.toInt()) {
                            if (calcularNumsNarcisistas(i)) {
                                texto = "$i"
                            }
                            esperarUnSegundo()
                        }
                    }
                }
                else {
                    Toast.makeText(contexto, "ERROR: Hay campos vacíos", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Comenzar.")
        }
    }
}

// Cuenta los dígitos que tiene un número
fun contarDigitos(valor: Int): Int {
    if (valor == 0) {
        return 0;
    }

    return 1 + contarDigitos(valor / 10);
}

// Retorna "true" si el valor pasado es narcisita
fun calcularNumsNarcisistas(valor: Int): Boolean {

    // Contamos los digitos
    var digitos: Int = contarDigitos(valor)
    var n: Int = valor
    var suma: Int = 0

    // Calculamos la suma de los dígitos elevada al cuadrado
    while (n > 0) {
        suma += (pow(n.toDouble() % 10, digitos.toDouble())).toInt()
        n /= 10
    }

    return (valor == suma)
}

/**
 * Espera 1 segundo sin hacer nada
 */
fun esperarUnSegundo() {
    try {
        Thread.sleep(1000)
    } catch (e: InterruptedException) {}
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaBaseDeDatos() {
    val navController = rememberNavController()

    Scaffold(
        // Barra superior (barra de título)
        topBar = {
            TopAppBar(
                title = { Text(text = "Base de Datos") },
                // Color de la barra
                colors = TopAppBarDefaults.topAppBarColors(Color.Blue)
            )
        },

        // Barra inferior (barra de navegación)
        bottomBar = {
            NavigationBar {
                // Botón de Insertar
                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate("insertar") },
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Insertar") },
                    label = { Text(text = "Insertar") }
                )

                // Botón de Buscar
                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate("buscar") },
                    icon = { Icon(Icons.Filled.Place, contentDescription = "Buscar") },
                    label = { Text(text = "Buscar") }
                )

                // Botón de Eliminar
                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate("eliminar") },
                    icon = { Icon(Icons.Filled.Build, contentDescription = "Eliminar") },
                    label = { Text(text = "Eliminar") }
                )

                // Botón de Mostrar Todos
                NavigationBarItem(
                    selected = true,
                    onClick = {
                        // Rellenamos "listaFincas" con el contenido de la colección "Fincas" de Firebase
                        rellenarListaFincas()
                        navController.navigate("mostrar_todos")
                    },
                    icon = { Icon(Icons.Filled.Build, contentDescription = "Mostrar Todos") },
                    label = { Text(text = "Mostrar Todos") }
                )
            }
        }
    ) {
        // El árbol de navegación
        NavHost(navController = navController, startDestination = "insertar") {
            composable("insertar") {
                PantallaInsertar()
            }
            composable("buscar") {
                PantallaBuscar()
            }
            composable("eliminar") {
                PantallaEliminar()
            }
            composable("mostrar_todos") {
                PantallaMostrarTodos()
            }
        }
    }
}

/**
 * Pantallas del CRUD
 */
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
        var Codigofinca by remember { mutableStateOf("") }
        var Nombre by remember { mutableStateOf("") }
        var Direccion by remember { mutableStateOf("") }
        var Localidad by remember { mutableStateOf("") }
        var Superficie by remember { mutableStateOf("0") }

        // Edit text de "Codigofinca"
        OutlinedTextField(
            value = Codigofinca,
            onValueChange = { Codigofinca = it },
            label = { Text(text = "Codigofinca") },
            // Con esto hacemos que el text field solo acepte entradas numéricas (integer)
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        // Edit text de "Nombre"
        OutlinedTextField(
            value = Nombre,
            onValueChange = { Nombre = it },
            label = { Text(text = "Nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        // Edit text de "Direccion"
        OutlinedTextField(
            value = Direccion,
            onValueChange = { Direccion = it },
            label = { Text(text = "Direccion") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        // Edit text de "Localidad"
        OutlinedTextField(
            value = Localidad,
            onValueChange = { Localidad = it },
            label = { Text(text = "Localidad") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        // Edit text de "Superficie"
        OutlinedTextField(
            value = Superficie,
            onValueChange = { Superficie = it },
            // Con esto hacemos que el text field solo acepte entradas en decimal
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            label = { Text(text = "Superficie") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        // Botón de insertar registro
        Button(
            onClick = {
                // Al hacer click en el botón, creamos el objeto "Finca"
                // con los parámetros especificados y lo añadimos a la
                // base de datos
                val finca = Fincas(Codigofinca, Nombre, Direccion, Localidad, Superficie.toDouble())

                // No insertaremos el registro si los edit text están vacío
                if (Codigofinca != "" && Nombre != "" && Direccion != "" && Superficie != "") {
                    fincas.document(Codigofinca).set(finca)
                    Toast.makeText(contexto, "Registro insertado", Toast.LENGTH_SHORT).show()

                    // Limpiamos los valores de los edit text al hacer click en el botón
                    Codigofinca = ""
                    Nombre = ""
                    Direccion = ""
                    Localidad = ""
                    Superficie = "0"
                }
                else {
                    Toast.makeText(contexto, "ERROR: Hay campos vacíos", Toast.LENGTH_SHORT).show()
                }
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
        var Codigofinca by remember { mutableStateOf("") }
        var Nombre by remember { mutableStateOf("") }
        var Direccion by remember { mutableStateOf("") }
        var Localidad by remember { mutableStateOf("") }
        var Superficie by remember { mutableStateOf("0") }

        // Edit text de "Codigofinca"
        OutlinedTextField(
            value = Codigofinca,
            onValueChange = { Codigofinca = it },
            label = { Text(text = "Codigofinca") },
            // Con esto hacemos que el text field solo acepte entradas numéricas (integer)
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        // Mostramos las variables del registro
        Text(text = "Codigofinca: $Codigofinca")
        Text(text = "Nombre: $Nombre")
        Text(text = "Direccion: $Direccion")
        Text(text = "Localidad: $Localidad")
        Text(text = "Superficie: $Superficie")

        // Botón de buscar
        Button(
            onClick = {
                if (Codigofinca != "") {
                    // Si la entrada existe ("if (it.exists())")
                    // obtener las variables del registro
                    fincas.document(Codigofinca.toString()).get().addOnSuccessListener {
                        // No insertaremos el registro si los edit text están vacío
                        // o bien si Codigofinca retorna null
                        if (it.exists() && it.get("codigofinca") != null) {
                            Codigofinca = it.get("codigofinca").toString()
                            Nombre = it.get("nombre").toString()
                            Direccion = it.get("direccion").toString()
                            Localidad = it.get("localidad").toString()
                            Superficie = it.get("superficie").toString()
                            Toast.makeText(contexto, "Registro encontrado", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            Toast.makeText(contexto, "Registro NO encontrado", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else {
                    Toast.makeText(contexto, "ERROR: Hay campos vacíos", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Buscar por Codigofinca")
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
        var Codigofinca by remember { mutableStateOf("") }

        // Edit text de "Codigofinca"
        OutlinedTextField(
            value = Codigofinca,
            onValueChange = { Codigofinca = it },
            label = { Text(text = "Codigofinca") },
            // Con esto hacemos que el text field solo acepte entradas numéricas (integer)
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        // Botón de eliminar
        Button(
            onClick = {
                if (Codigofinca != "") {
                    // Si la entrada existe ("if (it.exists())")
                    // eliminar la entrada ("it.reference.delete()")
                    fincas.document(Codigofinca.toString()).get().addOnSuccessListener {
                        if (it.exists()) {
                            it.reference.delete()
                            Toast.makeText(contexto, "Registro eliminado", Toast.LENGTH_SHORT)
                                .show()

                            Codigofinca = ""
                        }
                        else {
                            Toast.makeText(contexto, "Registro NO eliminado", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
                else {
                    Toast.makeText(contexto, "ERROR: Hay campos vacíos", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Eliminar por Codigofinca")
        }
    }
}

@Composable
fun PantallaMostrarTodos() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        // Añadimos un margen superior. Sino lo hacemos,
        // la primera entrada no se verá porque se renderizará por detrás del
        // menú superior de la aplicación (donde esta el nombre de la app)
        contentPadding = PaddingValues(top = 100.dp)
    ) {
        // Itera en el array "listaFincas" y muestra todas sus entradas
        // usando la función "MostrarFinca"
        items(listaFincas) { finca ->
            MostrarFincas(finca)
        }
    }
}

// Muestra una entrada fincas
@Composable
fun MostrarFincas(finca: Fincas) {
    HorizontalDivider(modifier = Modifier.fillMaxWidth().width(16.dp))
    Text(text = "Codigofinca: ${finca.Codigofinca}")
    Text(text = "Nombre: ${finca.Nombre}")
    Text(text = "Direccion: ${finca.Direccion}")
    Text(text = "Localidad: ${finca.Localidad}")
    Text(text = "Superficie: ${finca.Superficie.toString()}")
    HorizontalDivider(modifier = Modifier.fillMaxWidth().width(16.dp))
}

// Rellena "listaFincas" con todos los fincas guardados en la colección
// en la base de datos
private fun rellenarListaFincas() {
    listaFincas.clear()

    fincas.get().addOnSuccessListener {
        for(entrada in it) {
            val finca = Fincas(
                entrada.data.get("codigofinca").toString(),
                entrada.data.get("nombre").toString(),
                entrada.data.get("direccion").toString(),
                entrada.data.get("localidad").toString(),
                entrada.data.get("superficie").toString().toDouble(),
            )
            listaFincas.add(finca)
        }
    }
}
