package com.example.ut1_actividadintegradora

import android.content.Intent
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
import com.example.ut1_actividadintegradora.databinding.ActivityBuscarBinding
import com.example.ut1_actividadintegradora.databinding.ActivityModificarBinding

class ModificarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModificarBinding

    // Esta variable almacena la ID de la categoria seleccionada.
    // La usamos en el "categoriaIdSpinner.onItemSelectedListener" para obtener la ID de la categoría a partir
    // del nombre del producto, haciendo la búsqueda en la lista de categorías de "ProductosSQLiteOpenHelper"
    private var categoriaSeleccionadaId: Int = 0

    // ID del producto que se ha buscado con el botón de BUSCAR
    private var productoId: Int = -1

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
        productoId = -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_modificar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializamos binding
        binding = ActivityModificarBinding.inflate(layoutInflater)
        setContentView((binding.root))

        // Accedemos a los componentes utilizando binding.(id del componente)
        val buscarButModificar = binding.buscarButModificar
        val buscarNombreModificarEd = binding.buscarNombreModificarEd
        val volverModificarBut = binding.volverModificarBut
        val modificarNomEd = binding.modificarNomEd
        val marcaModificarEd = binding.marcaModificarEd
        val PrecioModificarEd = binding.PrecioModificarEd
        val disponibleModificarEd = binding.disponibleModificarEd
        val espinerModificarEd = binding.espinerModificarEd
        val modificarButtonEd = binding.modificarButtonEd

        inicializarSpinnerCategorias(espinerModificarEd, ProductosSQLiteOpenHelper.categorias)

        modificarButtonEd.setOnClickListener {
            val baseDeDatos = ProductosSQLiteOpenHelper(this@ModificarActivity, ProductosSQLiteOpenHelper.nombreBaseDeDatos, null, ProductosSQLiteOpenHelper.version)
            baseDeDatos.modificarProducto(
                productoId,
                modificarNomEd.text.toString(),
                marcaModificarEd.text.toString(),
                PrecioModificarEd.text.toString().toDouble(),
                categoriaSeleccionadaId,
                disponibleModificarEd.isChecked
            )
            baseDeDatos.cerrarConexion()
            limpiar(modificarNomEd, marcaModificarEd, PrecioModificarEd, disponibleModificarEd)
            Toast.makeText(this, "MODIFICACIÓN CORRECTA", Toast.LENGTH_SHORT).show()
        }

        buscarButModificar.setOnClickListener {
            val nombre: String = buscarNombreModificarEd.text.toString()

            val baseDeDatos = ProductosSQLiteOpenHelper(this@ModificarActivity, ProductosSQLiteOpenHelper.nombreBaseDeDatos, null, ProductosSQLiteOpenHelper.version)
            val producto: Productos? = baseDeDatos.buscarProductoPorNombre(nombre)
            baseDeDatos.cerrarConexion()

            if (producto == null) {
                Toast.makeText(this, "NO SE HA ENCONTRADO EL PRODUCTO $nombre", Toast.LENGTH_LONG).show()
            }
            else {
                productoId = producto.id
                modificarNomEd.setText(producto?.nombre)
                marcaModificarEd.setText(producto?.marca)
                PrecioModificarEd.setText(producto?.precio.toString())
                disponibleModificarEd.isChecked = producto?.disponible == true
            }
        }

        volverModificarBut.setOnClickListener {
            finish()
        }
    }
}