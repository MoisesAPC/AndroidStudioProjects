package com.example.actividad6

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    fun showFragment(fragmento: Fragment) {
        val frag = supportFragmentManager.beginTransaction()
        frag.replace(R.id.frameLayout, fragmento)
        frag.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Accedemos a los botones
        val botonFragmento01 = findViewById<Button>(R.id.buttonFragmento1)
        val botonFragmento02 = findViewById<Button>(R.id.buttonFragmento2)

        // Accedemos al spinnner
        val espiner = findViewById<Spinner>(R.id.espiner)

        // Definimos a los listeners de los botones
        botonFragmento01.setOnClickListener {
            val fragmento01 = Fragmento01()

            val datos = Bundle()
            val seleccionado = espiner.selectedItem.toString() // Obtener el valor seleccionado del Spinner
            datos.putString("isla", seleccionado)
            fragmento01.arguments = datos

            showFragment(fragmento01)
        }

        botonFragmento02.setOnClickListener {
            val fragmento02 = Fragmento02()
            showFragment(fragmento02)
        }

        // Creamos el adapter para el spinner, en donde los datos se obtienen del array que definimos aquí
        val islas = arrayOf("La Graciosa", "Lanzarote", "Fuerteventura", "Gran Canaria")
        val adap = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, islas)
        espiner.adapter = adap

        // Configurar el listener del Spinner para que, al hacer click en un item, se actualice
        // automáticamente el texto del textView correspondiente
        espiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val seleccionado = parent.getItemAtPosition(position).toString()
                actualizarFragmento(seleccionado)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No es necesario hacer nada aquí
            }
        }
    }

    private fun actualizarFragmento(seleccionado: String) {
        val fragmento01 = Fragmento01()
        val datos = Bundle()
        datos.putString("isla", seleccionado)
        fragmento01.arguments = datos
        showFragment(fragmento01)
    }
}