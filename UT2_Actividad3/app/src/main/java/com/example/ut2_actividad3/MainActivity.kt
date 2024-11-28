package com.example.ut2_actividad3

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ut2_actividad3.ui.theme.UT2_Actividad3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdministrarContactos()
        }
    }
}

@Composable
fun AdministrarContactos() {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column {
        // Entrada del nombre
        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre: ") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )

        // Entrada del correo
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Correo: ") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )

        // Botón de Agregar
        Button(
            onClick = {
                val nuevoContacto = Contacto(nombre, email)
                contactos.add(nuevoContacto)
                nombre = ""
                email = "" },
            // Desactivamos el botón de Agregar
            enabled = email.isNotEmpty() && nombre.isNotEmpty(),
            modifier = Modifier
                .padding(5.dp)
        )
        {
            Text(
                text = "Agregar",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        // El espacio donde se va a mostrar la lista de los contactos
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow)
        )
        {
            /**
             * También se puede usar lo siguiente para mostrar la lista
             * en caso de que no importe mucho el orden en que se muestre (usando el items)
             *
             * Usamos el itemsIndexed en específico porque necesitamos saber el índice
             * para poder saber qué entrada eliminar
             */
            /*
            items(contactos) {contacto ->
                MostrarContacto(contacto)
            }
            */
            itemsIndexed(contactos) {indice, contacto ->
                MostrarContacto(indice, contacto)
            }
        }
    }
}

@Composable
fun MostrarContacto(indice: Int, contacto: Contacto) {
    var mostrar by remember { mutableStateOf(false) }

    if (mostrar) {
        DialogoAlerta(titulo = "Atención", descripcion = "Borrar Contacto", respuesta = {
            if (it) {
                contactos.removeAt(indice)
            }
            mostrar = false
        })
    }

    Text(text = contacto.nombre)
    Text(text = contacto.email)

    // Dibujamos el ícono de borrado
    // Cuando hagamos click en el ícono (Modifier.clickable),
    // la variable "mostrar" pasará a ser true, de manera que el DialogoAlerta se abrirá
    Image(painter = painterResource(id = R.drawable.ic_delete), contentDescription = null,
        modifier = Modifier.clickable {
            mostrar = true
        }
    )

    // Dibujamos la línea divisoria
    Divider(modifier = Modifier
        .fillMaxWidth()
        .width(4.dp), color = Color.Black
    )
}

// Diálogo de alerta cuando intentamos borrar un contacto
@Composable
fun DialogoAlerta(titulo: String, descripcion: String, respuesta: (Boolean) -> Unit) {
    Column {
        // El mutableStateOf es para que el estado pueda cambiar
        var dialogoVisible by remember { mutableStateOf(true) }

        if (dialogoVisible) {
            AlertDialog(
                onDismissRequest = {
                    dialogoVisible = false
                    respuesta(false)
                },

                title = {
                    Text(text = "$titulo")
                },

                text = {
                    Text(text = "$descripcion")
                },

                // Botón de Confirmar
                confirmButton = {
                    Button(onClick = {
                        dialogoVisible = false
                        respuesta(true)
                    }) {
                        Text("Confirmar")
                    }
                },

                // Botón de Cancelar
                dismissButton = {
                    Button(onClick = {
                        dialogoVisible = false
                        respuesta(false)
                    }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    UT2_Actividad3Theme {
        AdministrarContactos()
    }
}