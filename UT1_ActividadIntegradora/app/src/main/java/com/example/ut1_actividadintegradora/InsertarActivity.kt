package com.example.ut1_actividadintegradora

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ut1_actividadintegradora.databinding.ActivityInsertarBinding
import com.example.ut1_actividadintegradora.databinding.ActivityMainBinding

class InsertarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInsertarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_insertar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializamos la variable creándolo y vinculándolo
        binding = ActivityInsertarBinding.inflate(layoutInflater)
        // Mostramos la vista raíz
        setContentView((binding.root))

        // Accedemos a los componentes utilizando binding.(id del componente)
        val insertarVolverButton = binding.insertarVolverButton
        val categoriaIdSpinner = binding.categoriaIdSpinner

        val islas = arrayOf("La Graciosa", "Lanzarote", "Fuerteventura", "Gran Canaria", "Tenerife")
        val adap = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, islas)
        categoriaIdSpinner.adapter = adap

        insertarVolverButton.setOnClickListener {
            finish()
        }
    }
}