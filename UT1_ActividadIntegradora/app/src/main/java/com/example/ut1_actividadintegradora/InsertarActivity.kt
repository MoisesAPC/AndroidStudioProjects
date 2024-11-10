package com.example.ut1_actividadintegradora

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ut1_actividadintegradora.databinding.ActivityInsertarBinding

class InsertarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInsertarBinding
    // Esta variable almacena la ID de la categoria seleccionada.
    // La usamos en el "categoriaIdSpinner.onItemSelectedListener" para obtener la ID de la categoría a partir
    // del nombre del producto, haciendo la búsqueda en la lista de categorías de "ProductosSQLiteOpenHelper"
    private var categoriaSeleccionadaId: Int = 0

    // Creamos el adapter del spinner de categorias en base al array de categorias de ProductosSQLiteOpenHelper
    // Lo recorremos usando categorias.map { it.nombre }
    private fun inicializarSpinnerCategorias(categoriaIdSpinner: Spinner, categorias: List<ProductosSQLiteOpenHelper.Categoria>) {
        val nombresCategoria = categorias.map { it.nombre }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombresCategoria)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categoriaIdSpinner.adapter = adapter

        categoriaIdSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, posicion: Int, id: Long) {
                categoriaSeleccionadaId = categorias[posicion].id
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                categoriaSeleccionadaId = 0
            }
        }
    }

    private fun limpiar(nombreEditText: EditText, marcaEditText: EditText, precioEditText: EditText, disponibleCheckbox: CheckBox) {
        nombreEditText.text.clear()
        marcaEditText.text.clear()
        precioEditText.text.clear()
        disponibleCheckbox.isChecked = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_insertar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_mainAct)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializamos binding
        binding = ActivityInsertarBinding.inflate(layoutInflater)
        setContentView((binding.root))

        // Accedemos a los componentes utilizando binding.(id del componente)
        val insertarVolverButton = binding.insertarVolverButton
        val insertarSubmitButton = binding.insertarSubmitButton
        val categoriaIdSpinner = binding.categoriaIdSpinner
        val nombreEditText = binding.nombreEditText
        val marcaEditText = binding.marcaEditText
        val precioEditText = binding.precioEditText
        val disponibleCheckbox = binding.disponibleCheckbox

        inicializarSpinnerCategorias(categoriaIdSpinner, ProductosSQLiteOpenHelper.categorias)

        insertarSubmitButton.setOnClickListener {
            val baseDeDatos = ProductosSQLiteOpenHelper(this@InsertarActivity, ProductosSQLiteOpenHelper.nombreBaseDeDatos, null, ProductosSQLiteOpenHelper.version)
            baseDeDatos.insertarProducto(
                nombreEditText.text.toString(),
                marcaEditText.text.toString(),
                precioEditText.text.toString().toDouble(),
                categoriaSeleccionadaId,
                disponibleCheckbox.isChecked
            )
            baseDeDatos.cerrarConexion()
            limpiar(nombreEditText, marcaEditText, precioEditText, disponibleCheckbox)
            Toast.makeText(this, "INSERCIÓN CORRECTA", Toast.LENGTH_SHORT).show()
        }

        insertarVolverButton.setOnClickListener {
            finish()
        }
    }
}