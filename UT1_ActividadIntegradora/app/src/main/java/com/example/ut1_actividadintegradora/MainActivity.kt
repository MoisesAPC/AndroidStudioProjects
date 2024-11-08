package com.example.ut1_actividadintegradora

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ut1_actividadintegradora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializamos la variable creándolo y vinculándolo
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Mostramos la vista raíz
        setContentView((binding.root))

        // Accedemos a los componentes utilizando binding.(id del componente)
        val insertarButton = binding.insertarButton
        val cerrarButton = binding.cerrarButton

        insertarButton.setOnClickListener {
            val intent = Intent(this@MainActivity, InsertarActivity::class.java)
            startActivity(intent)
        }

        cerrarButton.setOnClickListener {
            finish()
        }
    }
}