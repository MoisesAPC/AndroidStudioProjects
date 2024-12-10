package com.example.ut2_actividadintegradora

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun PantallaInicio() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "UT2 - Actividad Integradora", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = "Ver. 1.0", fontSize = 16.sp, fontWeight = FontWeight.Bold)
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
        var id by remember { mutableStateOf("") }
        var nombre by remember { mutableStateOf("") }
        var marca by remember { mutableStateOf("") }
        var precio by remember { mutableStateOf("0") }

        // Edit text de "ID"
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text(text = "ID") },
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
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text(text = "Nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        // Edit text de "Marca"
        OutlinedTextField(
            value = marca,
            onValueChange = { marca = it },
            label = { Text(text = "Marca") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        // Edit text de "Precio"
        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            // Con esto hacemos que el text field solo acepte entradas en decimal
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            label = { Text(text = "Precio") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        // Botón de insertar registro
        Button(
            onClick = {
                // Al hacer click en el botón, creamos el objeto "Producto"
                // con los parámetros especificados y lo añadimos a la
                // base de datos
                val producto = Productos(id, nombre, marca, precio.toFloat())

                // No insertaremos el registro si los edit text están vacío
                if (id != "" && nombre != "" && marca != "" && precio != "") {
                    productos.document(id).set(producto)
                    Toast.makeText(contexto, "Registro insertado", Toast.LENGTH_SHORT).show()

                    // Limpiamos los valores de los edit text al hacer click en el botón
                    id = ""
                    nombre = ""
                    marca = ""
                    precio = "0"
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
        var id by remember { mutableStateOf("") }
        var nombre by remember { mutableStateOf("") }
        var marca by remember { mutableStateOf("") }
        var precio by remember { mutableStateOf("0") }

        // Edit text de "ID"
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text(text = "ID") },
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
        Text(text = "ID: $id")
        Text(text = "Nombre del producto: $nombre")
        Text(text = "Marca: $marca")
        Text(text = "Precio: $precio")

        // Botón de buscar
        Button(
            onClick = {
                if (id != "") {
                    // Si la entrada existe ("if (it.exists())")
                    // obtener las variables del registro
                    productos.document(id.toString()).get().addOnSuccessListener {
                        // No insertaremos el registro si los edit text están vacío
                        if (it.exists()) {
                            id = it.get("id").toString()
                            nombre = it.get("nombre").toString()
                            marca = it.get("marca").toString()
                            precio = it.get("precio").toString()
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
        var marca by remember { mutableStateOf("") }
        var precio by remember { mutableStateOf("0") }

        // Edit text de "ID"
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text(text = "ID") },
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
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text(text = "Nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        // Edit text de "Marca"
        OutlinedTextField(
            value = marca,
            onValueChange = { marca = it },
            label = { Text(text = "Marca") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        // Edit text de "Precio"
        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            // Con esto hacemos que el text field solo acepte entradas en decimal
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            label = { Text(text = "Precio") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        // Botón de actualizar registro
        Button(
            onClick = {

                // No insertaremos el registro si los edit text están vacío
                if (id != "" && nombre != "" && marca != "" && precio != "") {
                    // Actualizamos todos los valores utilizando un hashMap con clave to valor,
                    // donde valor será los valores que estén escritos en los "OutlinedTextField"
                    productos.document(id.toString()).set(
                        hashMapOf(
                            "id" to id.toString(),
                            "nombre" to nombre.toString(),
                            "marca" to marca.toString(),
                            "precio" to precio.toFloat()
                        )
                    )
                    Toast.makeText(contexto, "Registro actualizado", Toast.LENGTH_SHORT).show()

                    id = ""
                    nombre = ""
                    marca = ""
                    precio = "0"
                }
                else {
                    Toast.makeText(contexto, "ERROR: Hay campos vacíos", Toast.LENGTH_SHORT).show()
                }
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

        // Edit text de "ID"
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text(text = "ID") },
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
                if (id != "") {
                // Si la entrada existe ("if (it.exists())")
                // eliminar la entrada ("it.reference.delete()")
                productos.document(id.toString()).get().addOnSuccessListener {
                        if (it.exists()) {
                            it.reference.delete()
                            Toast.makeText(contexto, "Registro eliminado", Toast.LENGTH_SHORT)
                                .show()

                            id = ""
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
        // Itera en el array "listaProductos" y muestra todas sus entradas
        // usando la función "MostrarProducto"
        items(listaProductos) { producto ->
            MostrarProductos(producto)
        }
    }
}

/**
 * Pantallas del Mapa (localización de El Burrero)
 */

@Composable
fun PantallaMapa() {
    // Definimos un objeto latitud y longitud que será donde nos posicionaremos
    val coordenadasElBurrero = LatLng(27.908299, -15.388448)

    // Definimos el estado de la cámara y nos posicionaremos en el Teatro Auditorios de Agüimes
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(coordenadasElBurrero, 10f)
    }

    // Usamos el elemento Composable "GoogleMap" para agregar un Mapa
    GoogleMap(
        // Hacemos que el mapa ocupe el espacio permitido
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        // Definimos el marcador rojo (Marker),
        // el cual apunta al lugar indicado
        Marker(
            state = MarkerState(position = coordenadasElBurrero),
            title = "El Burrero",
            snippet = "Marcador del Burrero"
        )
    }
}

/**
 * Pantallas de corrutinas (Hilos)
 */

@Composable
fun PantallaHilos() {
    val contexto = LocalContext.current

    var texto by remember { mutableStateOf("Inicio") }
    val corutineScope = rememberCoroutineScope()

    var valorInicial by remember { mutableStateOf("0") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hilos", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        // Edit text del valor inicial de la cuenta atrás
        OutlinedTextField(
            value = valorInicial,
            onValueChange = { valorInicial = it },
            // Con esto hacemos que el text field solo acepte entradas en decimal
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            label = { Text(text = "Valor inicial") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(texto)
        Button(
            onClick = {
                if (valorInicial != "") {
                    corutineScope.launch(Dispatchers.Default) {
                        // Con el "downTo", hacemos que el contador decremente
                        for (i in valorInicial.toInt() downTo 0) {
                            // Esperamos un segundo, y luego decrementamos en 1
                            simularTarea()
                            texto = "$i"
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

/**
 * Simula la realización de una tarea
 * (espera 1 segundo sin hacer nada)
 */
fun simularTarea() {
    try {
        Thread.sleep(1000)
    } catch (e: InterruptedException) {}
}
