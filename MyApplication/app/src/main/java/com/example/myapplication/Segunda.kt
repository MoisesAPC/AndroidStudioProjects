package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Segunda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_segunda)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Acceder a los valores
        val tvmensaje = findViewById<TextView>(R.id.tvmensaje)
        val btvolver = findViewById<Button>(R.id.btvolver)
        val bundle = intent.extras
        val nombre = bundle?.getString("nombre")    // El ? es para que los null se manejen de manera automática
        tvmensaje.text = "¡Hola, ${nombre.toString()}!"
        btvolver.setOnClickListener() {
            val intento2 = Intent(this, MainActivity::class.java)
            startActivity(intento2)
        }
    }
}