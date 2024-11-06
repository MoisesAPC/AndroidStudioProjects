package com.example.actividad7

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Le asignamos los datos al array
        val reci = findViewById<RecyclerView>(R.id.recview)
        val datos = Array(50) { i -> Equipos("Equipo $i", i) }

        // Al hacer click en cada objeto AdaptadorEquipo, se muestra el mensaje
        val adaptador = AdaptadorEquipos(datos) {
            Toast.makeText(this,"Has seleccionado -> ${it.nombre}",Toast.LENGTH_LONG).show()
        }

        /* reci.layoutManager = GridLayoutManager(this,4) con esto se ve cuadriculado */
        // Hacemos que el scrolling sea en vertical
        reci.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        reci.adapter = adaptador

        // Añadimos la línea divisoria
        reci.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
    }
}
