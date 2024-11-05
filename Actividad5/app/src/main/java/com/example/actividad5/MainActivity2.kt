package com.example.actividad5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    fun volverAtras() {
        val intento2 = Intent(this@MainActivity2, MainActivity::class.java)
        startActivity(intento2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Definimos un objeto Bundle para acceder a los datos que nos han enviado
        val bundle = intent.extras
        val usuario = bundle?.getString("usuario")

        // Accedemos a los componentes
        val logTextView = findViewById<TextView>(R.id.logTextView)
        val volverButton = findViewById<Button>(R.id.volverButton)

        // Cambiamos el texto del textview para que diga "Logueado como {usuario}"
        logTextView.text = "Logueado como ${usuario}"

        // Capturamos el evento de cuando se pulsa el botón de Volver
        volverButton.setOnClickListener {
            //volverAtras()
            finish()    // Cerramos MainActivity2
        }
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "OnStart_Activity2", Toast.LENGTH_LONG).show();
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "onPause_Activity2", Toast.LENGTH_LONG).show();
    }
}