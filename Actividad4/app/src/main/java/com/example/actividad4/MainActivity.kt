package com.example.actividad4

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.actividad4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // Declaramos la variable del tipo ActivityMainBinding. La podemos inicializr más tarde porque es lateinit
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
        val rg = binding.radioGroup
        val tv = binding.textView
        val cb = binding.checkBox
        val espin = binding.spinner
        val tvre = binding.textView2

        // Creamos el adapter para el spinner, en donde los datos se obtienen del strings.xml
        val pro = ArrayAdapter.createFromResource(this, R.array.provincias, com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
        espin.adapter = pro

        // Creamos el adapter para el spinner, en donde los datos se obtienen del array que definimos aquí
        val islas = arrayOf("La Graciosa", "Lanzarote", "Fuerteventura", "Gran Canaria")
        val adap = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, islas)
        espin.adapter = adap

        rg.setOnCheckedChangeListener() { group, checkedId ->
            when(checkedId) {
                binding.radioButton.id -> {
                    tv.text = binding.radioButton.text
                }

                binding.radioButton2.id -> {
                    tv.text = binding.radioButton2.text
                }

                binding.radioButton3.id -> {
                    tv.text = binding.radioButton3.text
                }
            }
        }

        cb.setOnCheckedChangeListener { buttonView, isChecked ->
            var mensaje = ""

            if (isChecked) {
                mensaje = "Abonado"
            }
            else {
                mensaje = "No Abonado"
            }

            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        }

        espin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent:AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                tvre.text = "Has seleccionado ${parent!!.getItemAtPosition(position)}"
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                tvre.text = "No has seleccionado nada"
            }
        }
    }
}