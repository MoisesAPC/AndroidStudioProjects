package com.example.ut2_actividad1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ut2_actividad1.ui.theme.UT2_Actividad1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Saludo("Hola 2 DAM")
        }
    }
}

@Composable
fun Saludo(msg: String) {
    /**
     * Vamos a trabajar con Row y con Column.
     */
    // Definimos Row
    Row {
        // Definimos una imágen que será el fondo de Android y su descripción
        // Vamos a añadir modificadores a la imagen
        // Lo vamos a hacer ciruclar y de un tamaño de 128
        Image(painter = painterResource(id = R.drawable.ic_launcher_background)
            , contentDescription = "Fondo de Android"
            , modifier = Modifier
                    .size(128.dp)
                    .clip(CircleShape)
        )

        // Añadimos a su derecha los textos uno debajo del otro usando Column
        /**
         * Vamos a poner varios Text (equivalentes a TexViews en vistas)
         * Vemos que se solapan las ordenes estilo LinearLayout.
         *
         * Empezamos con columnas.
         * Se ordenan unas debajo de otras.
         */
        Column {
            Text(text = "Imagen del fondo de Android")
            Text(text = "No queda muy bien")
            Text(text = "Pero lo mejoraremos")
        }
    }

    /**
     * Vamos a crear un LazyColumn equivalente a un RecyclerView vertical
     * (Comenta todo lo de abajo para ver el Row y el Image de arriba)
     */
    // Definimos la lista con los valores
    val paises = mutableListOf("España", "Francia", "Alemania", "Rusia", "China", "Japón")

    LazyColumn(modifier = Modifier.fillMaxSize().background(Color.Blue).padding(10.dp)) {
        items(paises) { pais ->
            Text(text = pais)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun VistaPrevia() {
    Saludo("Hola 2º DAM con JetPack Compose")
}