package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        val etnombre = findViewById<EditText>(R.id.etnombre)
        val botonentrar = findViewById<Button>(R.id.botonentrar)

        botonentrar.setOnClickListener() {
            // Crear un canal entre la ventana (activity) actual a la segunda ventana (Segunda)
            val intento1 = Intent(this, Segunda::class.java)
            intento1.putExtra("nombre", etnombre.text.toString())
            startActivity(intento1)
        }
    }
}