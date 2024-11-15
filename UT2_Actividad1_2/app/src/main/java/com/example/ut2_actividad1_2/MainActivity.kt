package com.example.ut2_actividad1_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ut2_actividad1_2.ui.theme.UT2_Actividad1_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Login()
        }
    }
}

@Composable
fun Login() {
    Column (
        // Modificadores para que ocupe todo el espacio y este centrado verticalmente
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        // Definimos las variables de las que queremos guardar su estado
        var usuario by remember { mutableStateOf("") }
        var clave by remember { mutableStateOf("") }
        var resultado by remember { mutableStateOf("Sin respuesta") }

        // Campo del nombre de usuario (equivalente a EditText en vistas)
        OutlinedTextField(
            // Valor
            value = usuario,
            // Nuevo valor
            onValueChange = { usuario = it },
            // Etiqueta
            label = { Text("Nombre de usuario") },
            // Modificadores ancho, el espaciado y una sola línea
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            singleLine = true
        )

        // Campo de la contraseña (equivalente a EditText en vistas)
        OutlinedTextField(
            // Valor
            value = clave,
            // Nuevo valor
            onValueChange = { clave = it },
            // Etiqueta
            label = { Text("Contraseña") },
            // Modificadores ancho, el espaciado y una sola línea
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            singleLine = true,
            // Hacemos que en el campo de la contraseña se vean puntitos en vez de las letras que escribamos
            visualTransformation = PasswordVisualTransformation()
        )

        // Botón de Confirmar
        Button(
            onClick = {
                var cadena = ""

                if (clave.length < 8) {
                    cadena += "La clave debe de tener al menos 8 caracteres.\n"
                }
                if (usuario.length == 0) {
                    cadena += "No se puede dejar el usuario vacío."
                }

                resultado = cadena
            },
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "Confirmar")
        }

        // Texto que muestra el resultado del login
        Text (
            text = resultado,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginPreview() {
    Login()
}