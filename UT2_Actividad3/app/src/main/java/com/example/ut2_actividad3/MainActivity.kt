package com.example.ut2_actividad3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ut2_actividad3.ui.theme.UT2_Actividad3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdministrarContactor()
        }
    }
}

@Composable
fun AdministrarContactor() {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre: ") },
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        )

        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Correo: ") },
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        )

        Button(onClick = {
            val nuevoContacto = Contacto(nombre, email)
            contactos.add(nuevoContacto)
            nombre = ""
            email = ""},
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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow)
        )
        {
            itemsIndexed(contactos) {indice, contacto ->
                MostrarContacto(indice, contacto)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    UT2_Actividad3Theme {
        AdministrarContactor()
    }
}